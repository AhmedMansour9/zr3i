package com.schemecode.zr3i.ui.activities.splash_screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.home_activity.HomeActivity;
import com.schemecode.zr3i.ui.activities.splash_activity.LoginSplashActivity;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private SharedPreferences sharedPreferences;
    private String token , secret ;
    private boolean is_remembered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        sharedPreferences = getSharedPreferences("MahmoudPref" , MODE_PRIVATE);
        token = sharedPreferences.getString("token" , "token");
        secret = sharedPreferences.getString("secret" , "secret");
        is_remembered = sharedPreferences.getBoolean("is_remembered" , false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, Menu.class);
                if (token.equals("token") && secret.equals("secret") || is_remembered == false){
                     mainIntent = new Intent(SplashActivity.this, LoginSplashActivity.class);
                }
                else {
                    mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                }
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}