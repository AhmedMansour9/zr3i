package com.schemecode.zr3i.ui.activities.splash_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.BuildConfig;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.home_activity.HomeActivity;
import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;
import com.schemecode.zr3i.ui.activities.register_activity.RegisterActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginSplashActivity extends AppCompatActivity implements SplashScreenContract.view{

    private Button btnLogin , btnRegister , btnSignInWithFacebook;
    private CallbackManager callbackManager;
    private boolean activity_result = false;
    private String email, id, social_type;
    AccessToken access_token;
    private JSONObject json;
    GraphRequest request;
    private SplashPresenter splashPresenter;
    private SharedPreferences sharedPreferences ;
    private SharedPreferences.Editor editor ;
    private int login_error_counter = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        splashPresenter = new SplashPresenter(this);
        splashPresenter.start();
    }

    @Override
    public void initUi() {
        btnLogin = findViewById(R.id.login_splash_screen_text_view);
        btnRegister = findViewById(R.id.register_splash_screen_text_view);
        btnSignInWithFacebook = findViewById(R.id.login_with_facebook);
        callbackManager = CallbackManager.Factory.create();

    }

    @Override
    public void handleClicks() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(LoginSplashActivity.this , LoginActivity.class);
                startActivity(loginIntent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginSplashActivity.this , RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        btnSignInWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogin();
                activity_result = true;
            }
        });
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveNewTokenToPref(String token, String secret, String message , String imageUrl) {
        editor.putBoolean("is_remembered" , true);
        editor.putString("token" , token);
        editor.putString("secret" , secret);
        editor.putString("image_url" , imageUrl);
        editor.putString("message" , message);
        editor.commit();
        Intent loginIntent = new Intent(LoginSplashActivity.this , HomeActivity.class);
        loginIntent.putExtra("message" , message);
        startActivity(loginIntent);
        finish();
    }

    private void facebookLogin() {
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        LoginManager.getInstance().logInWithReadPermissions((Activity) LoginSplashActivity.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("sucess" ,"sucess");
                access_token = loginResult.getAccessToken();
                request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        json = response.getJSONObject();
                        Log.e("json" , json.toString());
                        if (json != null) {
                            try {
                                email = json.getString("email");
                                Log.e("phone", json.toString());
                                String name = object.getString("name");
                                id = object.getString("id");
                                social_type = "facebook";
                                email = object.getString("email");
                                name = object.getString("name");
                                Log.e("fbid" , id);
                                Log.e("email" , email);
                                Log.e("name" , name);
                                splashPresenter.fbLogin(email , name , id , "facebook");
                            } catch (JSONException e) {
                                Log.e("failed" ,e.getMessage());
                                Toast.makeText(LoginSplashActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,gender,birthday,name,first_name,last_name,link,email,picture");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginSplashActivity.this, "canceled", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onError(FacebookException error) {
                Log.e("FacebookExceptionErrorrrr" , error.getMessage());
                Log.e("FacebookExceptionErrorrrr" , String.valueOf(error.getCause().getMessage()));
                Log.e("FacebookExceptionErrorrrr" , error.getLocalizedMessage());
                if (login_error_counter < 1){
                    facebookLogin();
                }
                login_error_counter ++ ;

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (activity_result == true) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

}