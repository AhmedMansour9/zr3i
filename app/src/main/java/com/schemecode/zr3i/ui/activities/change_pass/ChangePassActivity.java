package com.schemecode.zr3i.ui.activities.change_pass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.splash_activity.LoginSplashActivity;
import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorDialog;
import com.schemecode.zr3i.ui.dialogs.success_dialog.SuccessDialog;

public class ChangePassActivity extends AppCompatActivity implements ChangePassContract.view{

    ChangePassPresenter changePassPresenter ;
    private AppCompatButton btnChange ;
    private TextInputEditText etCurrentPass , etNewPass , etNewPassConfirmation ;
    private SharedPreferences sharedPreferences;
    private String token , secret ;
    private ImageView imgback ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        sharedPreferences = getSharedPreferences("MahmoudPref" , MODE_PRIVATE);
        token = sharedPreferences.getString("token" , "token");
        secret = sharedPreferences.getString("secret" , "secret");
        changePassPresenter = new ChangePassPresenter(this);
        changePassPresenter.start();
    }

    @Override
    public void initUi() {
        btnChange = findViewById(R.id.change_pass_button);
        etCurrentPass = findViewById(R.id.current_pass_edit_text);
        etNewPass = findViewById(R.id.new_pass_edit_text);
        etNewPassConfirmation = findViewById(R.id.new_pass_confirmation_edit_text);
        imgback = findViewById(R.id.back_image_view);
    }

    @Override
    public void handleClicks() {
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCurrentPass.getText().toString().trim().matches("") ||
                        etCurrentPass.getText().toString().trim().matches("") ||
                                etCurrentPass.getText().toString().trim().matches("")){
                    Toast.makeText(ChangePassActivity.this, "الرجاء التأكد من ملئ البيانات", Toast.LENGTH_SHORT).show();
                }
                else if (!etNewPass.getText().toString().trim().equals(etNewPassConfirmation.getText().toString().trim())){
                    Toast.makeText(ChangePassActivity.this, "كلمتي السر غير متطابقة", Toast.LENGTH_SHORT).show();
                }
                else {
                    changePassPresenter.changePass(etCurrentPass.getText().toString().trim() ,
                            etNewPass.getText().toString().trim() , etNewPassConfirmation.getText().toString().trim() , token , secret);
                }
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void login() {
        Intent login = new Intent(ChangePassActivity.this , LoginActivity.class);
        startActivity(login);
    }

    @Override
    public void showSuccessOrFailureDialog(boolean is_success , String message) {
        if (is_success == true){
            SuccessDialog successDialog = new SuccessDialog(ChangePassActivity.this , message);
            successDialog.show();
        }
        else {
            ErrorDialog errorDialog = new ErrorDialog(ChangePassActivity.this , message);
            errorDialog.show();
        }
    }

    @Override
    public void unAuthorized(String message) {
        if (getSharedPreferences("MahmoudPref" , MODE_PRIVATE).edit().clear().commit() == true){
            Intent intent = new Intent(this , LoginSplashActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        changePassPresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        changePassPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        changePassPresenter.setView(ChangePassActivity.this);
    }
}