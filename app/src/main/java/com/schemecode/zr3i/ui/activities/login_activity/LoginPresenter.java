package com.schemecode.zr3i.ui.activities.login_activity;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.fblogin.AuthSocialLogin;
import com.schemecode.zr3i.data.models.login.AuthLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.presenter {

    private LoginContract.view view;
    private ApiService apiService;

    public LoginPresenter(LoginContract.view view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
    }

    public void setView(LoginContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.handleCLicks();
    }

    @Override
    public void login(String mail , String pass , String lang , String platform ,  String hashKey) {
        if (view != null){
            view.showProgress();
        }
        Call<AuthLogin> loginCall = apiService.login(mail , pass , lang , platform , hashKey);
        loginCall.enqueue(new Callback<AuthLogin>() {
            @Override
            public void onResponse(Call<AuthLogin> call, Response<AuthLogin> response) {
                if (view != null){
                    view.hideProgress();
                }
                if (response.isSuccessful()){
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() == true && response.body().getSuccess() == true && response.body().getAlert().equals("success")){
                        if (view != null){
                            view.saveNewTokenToPref(response.body().getToken() ,
                                    response.body().getSecret() ,
                                    response.body().getMessage() , response.body().getImage_url());
                        }
                    }
                    else {
                        if (view != null){
                            view.showError(response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthLogin> call, Throwable t) {
                if (view != null){
                    view.hideProgress();
                    view.showError(t.getMessage());
                }
            }
        });
    }

    @Override
    public void fbLogin(String email , String name , String id , String social_type) {
        if (view != null){
            view.showProgress();
        }
        Call<AuthSocialLogin> authSocialLoginCall = apiService.fbLogin(email , name , id , social_type ,"ar" , "android" , "hash_key");
        authSocialLoginCall.enqueue(new Callback<AuthSocialLogin>() {
            @Override
            public void onResponse(Call<AuthSocialLogin> call, Response<AuthSocialLogin> response) {
                if (response.isSuccessful()){
                    if (response.body().getResult() == true && response.body().getSuccess() == true && response.body().getAlert().equals("success")){
                        if (view != null){
                            view.saveNewTokenToPref(response.body().getToken() , response.body().getSecret() , response.body().getMessage() , response.body().getImage_url());
                        }
                    }
                    else {
                        view.showError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthSocialLogin> call, Throwable t) {
                if (view != null){
                    view.hideProgress();
                    view.showError(t.getMessage());
                }
            }
        });
    }

}
