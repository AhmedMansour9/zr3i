package com.schemecode.zr3i.ui.activities.login_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.material.textfield.TextInputEditText;
import com.schemecode.zr3i.R;
import com.schemecode.zr3i.ui.activities.forget_activity.ForgetActivity;
import com.schemecode.zr3i.ui.activities.home_activity.HomeActivity;
import com.schemecode.zr3i.ui.activities.register_activity.RegisterActivity;
import com.schemecode.zr3i.ui.activities.register_activity.RegisterPresenter;
import com.schemecode.zr3i.ui.activities.splash_activity.LoginSplashActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginContract.view {
    private TextView tvForget, tvCreateAccount, tvLoginErrors;
    private TextInputEditText etEmail, etPass;
    private Button btnLogin;
    private ProgressBar progressBarLogin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LoginPresenter loginPresenter;
    private AppCompatCheckBox cbRemember;
    private ImageView imgLoginFacebook;
    private CallbackManager callbackManager;
    private boolean activity_result = false;
    private String email, id, social_type;
    AccessToken access_token;
    private JSONObject json;
    GraphRequest request;
    String name;
    private int login_error_counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("MahmoudPref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loginPresenter = new LoginPresenter(this);

        loginPresenter.start();

    }

    @Override
    public void saveCsrfToken(String token) {
        Log.d("TAG", "saveCsrfToken: "+token);
        editor.putString("token_csrf", token);
        editor.commit();
//        Toast.makeText(this,token,Toast.LENGTH_LONG).show();
        String token_csrf = sharedPreferences.getString("token_csrf", null);
//        loginPresenter.login(token_csrf,etEmail.getText().toString().trim(), etPass.getText().toString().trim(), "-", "android", "hash_key");

    }

    @Override
    public void initUi() {
        etEmail = findViewById(R.id.mail_edit_text);
        etPass = findViewById(R.id.pass_edit_text);
        btnLogin = findViewById(R.id.login_buttom);
        tvForget = findViewById(R.id.forget_pass_text_view);
        tvCreateAccount = findViewById(R.id.register_text_view_login);
        progressBarLogin = findViewById(R.id.login_progress_bar);
        tvLoginErrors = findViewById(R.id.errors_text_view_login_activity);
        imgLoginFacebook = findViewById(R.id.im_lo_facebook);
        callbackManager = CallbackManager.Factory.create();

    }

    @Override
    public void handleCLicks() {
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        tvForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgetIntent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(forgetIntent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loginPresenter.getCrsToken();
                tvLoginErrors.setVisibility(View.GONE);
                loginPresenter.login(etEmail.getText().toString().trim(), etPass.getText().toString().trim(), "-", "android", "hash_key");

            }
        });

        imgLoginFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLogin();
                activity_result = true;
            }
        });
    }

    @Override
    public void showError(String text) {
        tvLoginErrors.setText(text);
        tvLoginErrors.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void saveNewTokenToPref(String token, String secret, String message, String imageUrl) {
        editor.putBoolean("is_remembered", true);
        editor.putString("token", token);
        editor.putString("secret", secret);
        editor.putString("image_url", imageUrl);
        editor.putString("message", message);
        editor.commit();
        Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
        loginIntent.putExtra("message", message);
        startActivity(loginIntent);
        finishAffinity();
    }


    @Override
    protected void onPause() {
        super.onPause();
        loginPresenter.setView(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        loginPresenter.setView(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.setView(LoginActivity.this);
    }

    private void facebookLogin() {
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("sucess", "sucess");
                access_token = loginResult.getAccessToken();
                request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        json = response.getJSONObject();
                        Log.e("json", json.toString());
                        if (json != null) {
                            try {
                                email = json.getString("email");
                                Log.e("phone", json.toString());
                                String name = object.getString("name");
                                id = object.getString("id");
                                social_type = "facebook";
                                email = object.getString("email");
                                name = object.getString("name");
                                Log.e("fbid", id);
                                Log.e("email", email);
                                Log.e("name", name);
                                loginPresenter.fbLogin(email, name, id, "facebook");
                            } catch (JSONException e) {
                                Log.e("failed", e.getMessage());
                                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(LoginActivity.this, "canceled", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onError(FacebookException error) {
                if (login_error_counter < 1) {
                    facebookLogin();
                }
                login_error_counter++;
            }
        });
    }

    @Override
    public void unAuthorized(String message) {
        if (getSharedPreferences("MahmoudPref", MODE_PRIVATE).edit().clear().commit() == true) {
            Intent intent = new Intent(this, LoginSplashActivity.class);
            startActivity(intent);
            finishAffinity();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (activity_result == true) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}