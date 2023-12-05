package com.schemecode.zr3i.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.splash_activity.LoginSplashActivity;

public class SubDomainScreen extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private AppCompatButton confirmBtn;
    private EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_domain_screen);
        sharedPreferences = getSharedPreferences("AppLink", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        confirmBtn = findViewById(R.id.confirm_Btn);
        urlEditText = findViewById(R.id.url_edit_text);
        confirmBtn.setOnClickListener(view -> {
            if (!urlEditText.getText().toString().isEmpty()) {
                editor.putString("link", "http://"+urlEditText.getText().toString()+".zr3i-stagging.strategizeit.us/api/v1/");
                editor.commit();
                MainApp.BASE_URL = "http://"+urlEditText.getText().toString()+".zr3i-stagging.strategizeit.us/api/v1/";
                Intent loginIntent = new Intent(SubDomainScreen.this, LoginActivity.class);
                startActivity(loginIntent);


            }
        });


    }
}