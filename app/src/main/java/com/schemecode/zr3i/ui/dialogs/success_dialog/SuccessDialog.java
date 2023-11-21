package com.schemecode.zr3i.ui.dialogs.success_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.home_activity.HomeActivity;
import com.schemecode.zr3i.ui.activities.profile_activity.ProfileActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorPresenter;

public class SuccessDialog extends Dialog implements SuccessDialogContract.view{
    private AppCompatButton btnConfirm;
    private TextView tvSuccessMessage;
    private SuccessDialogPresenter successDialogPresenter;
    private String success;
    private Context context;
    String type ;

    public SuccessDialog(@NonNull Context context, String success, String type) {
        super(context);
        this.success = success;
        this.type = type;
    }

    public SuccessDialog(@NonNull Context context , String success) {
        super(context);
        this.context = context;
        this.success = success;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setWindowAnimations(R.style.DialogAnimation);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.success_dialog_layout);
        successDialogPresenter = new SuccessDialogPresenter(this);
        successDialogPresenter.start(success);
    }

    @Override
    public void initUi() {
        btnConfirm = findViewById(R.id.success_confirm_button);
        tvSuccessMessage = findViewById(R.id.success_text_view);
    }

    @Override
    public void setData(String text) {
        tvSuccessMessage.setText(text);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type != null){
                    dismiss();
                }
                else {
                    dismiss();
                    Activity activity = (Activity) context;
                    if (activity instanceof HomeActivity) {
                        getContext().startActivity(new Intent(getContext(), HomeActivity.class));
                    } else if (activity instanceof ProfileActivity){
                        getContext().startActivity(new Intent(getContext(), HomeActivity.class));
                    }
                    else {
                        activity.finish();
                    }
                }

            }
        });
    }
}
