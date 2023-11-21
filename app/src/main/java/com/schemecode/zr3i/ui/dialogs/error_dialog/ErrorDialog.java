package com.schemecode.zr3i.ui.dialogs.error_dialog;

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
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;


public class ErrorDialog extends Dialog implements ErrorContract.view {

    private AppCompatButton btnConfirm;
    private TextView tvError;
    private ErrorPresenter errorPresenter;
    private String error;
    private Context context;
    private String type ;

    public ErrorDialog(@NonNull Context context, String error, String type) {
        super(context);
        this.error = error;
        this.type = type;
    }

    public ErrorDialog(@NonNull Context context, String error) {
        super(context);
        this.context = context;
        this.error = error;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setWindowAnimations(R.style.DialogAnimation);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.error_dialog_layout);
        errorPresenter = new ErrorPresenter(this);
        errorPresenter.start(this.error);
    }

    @Override
    public void initUi() {
        btnConfirm = findViewById(R.id.error_confirm_button);
        tvError = findViewById(R.id.error_text_view);
    }

    @Override
    public void setData(String text) {
        tvError.setText(text);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.equals("فضلا قم بتسجيل الدخــول أولا")){
                    Intent intent = new Intent(getContext() , LoginActivity.class);
                    context.startActivity(intent);
                }
                else {
                    if (type != null){
                        dismiss();
                    }
                    else {
                        Activity activity = (Activity) context;
                        activity.finish();
                    }
                }
            }
        });
    }
}
