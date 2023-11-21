package com.schemecode.zr3i.ui.dialogs.delete_dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.list_lands_activity.ListLandsActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorPresenter;

public class DeleteDialog extends Dialog implements DeleteContract.view {
    private AppCompatButton btnYes , btnNo ;
    private DeletePresenter deletePresenter ;
    private String token , secret ;
    private int id ;
    private Context context;
    String type ;
    private ProgressBar progressBar ;

    public DeleteDialog(@NonNull Context context , String token , String secret , int id , String type) {
        super(context);
        this.context = context;
        this.token = token;
        this.secret = secret;
        this.id = id;
        this.type = type;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setWindowAnimations(R.style.DialogAnimation);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.delete_dialog_layout);
        deletePresenter = new DeletePresenter(this);
        deletePresenter.start();
    }

    @Override
    public void initUi() {
        btnYes = findViewById(R.id.yes_confirm_button);
        btnNo = findViewById(R.id.no_confirm_button);
        progressBar = findViewById(R.id.loading_progress);
    }

    @Override
    public void handleClicks() {
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type.equals("land")){
                    deletePresenter.deleteLand(id , token , secret);

                }
                else {
                    deletePresenter.delete(id , token , secret);

                }
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        Activity activity = (Activity) context;
        if (type.equals("land")){
            Intent intent = new Intent(activity , ListLandsActivity.class);
            context.startActivity(intent);
            activity.finish();
        }
        else {
            context.startActivity(activity.getIntent());
            activity.finish();
        }
    }

    @Override
    public void showProgress(boolean isShow) {
        progressBar.setVisibility(isShow == true ? View.VISIBLE : View.GONE);
    }


}
