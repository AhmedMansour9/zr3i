package com.schemecode.zr3i.ui.activities.splash_activity;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.fblogin.AuthSocialLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashPresenter implements SplashScreenContract.presenter{

    private SplashScreenContract.view view;
    private ApiService apiService ;

    public SplashPresenter(SplashScreenContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.handleClicks();
    }

    @Override
    public void fbLogin(String email, String name, String id, String social_type) {

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
                    view.showError(t.getMessage());
                }
            }
        });
    }
}
