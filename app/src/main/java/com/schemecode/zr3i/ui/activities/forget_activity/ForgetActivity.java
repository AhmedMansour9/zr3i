package com.schemecode.zr3i.ui.activities.forget_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;

public class ForgetActivity extends AppCompatActivity implements ForgetContract.view{

    private Button btnSendLink ;
    private TextInputEditText etEmail ;
    private ProgressBar pbForget ;
    private TextView tvErrors , tvLogin;
    private ForgetPresenter forgetPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        forgetPresenter = new ForgetPresenter(this);
        forgetPresenter.start();
    }

    @Override
    public void initUi() {
        etEmail = findViewById(R.id.mail_forget_edit_text);
        tvLogin = findViewById(R.id.login_forget_Activity_text_view);
        btnSendLink = findViewById(R.id.restore_pass_button);
        pbForget = findViewById(R.id.forget_progress_bar);
        tvErrors = findViewById(R.id.errors_text_view_forget_activity);
    }

    @Override
    public void handleCLicks() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSendLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText().toString().trim().matches("")){
                    showErrorOrSuccess("من فضلك قم بكتابة البريد الإلكتروني");
                }
                else {
                    forgetPresenter.forget(etEmail.getText().toString().trim() , "ar" , "android" , "hash_key");
                }
            }
        });
    }

    @Override
    public void showErrorOrSuccess(String text) {
        tvErrors.setText(text);
        tvErrors.setVisibility(View.VISIBLE);
    }
    @Override
    public void showProgress() {
        pbForget.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbForget.setVisibility(View.GONE);
    }

    @Override
    public void unAuthorized(String message) {
        if (getSharedPreferences("MahmoudPref" , MODE_PRIVATE).edit().clear().commit() == true){
            Intent intent = new Intent(this , LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        forgetPresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        forgetPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        forgetPresenter.setView(null);
    }
}