package com.schemecode.zr3i.ui.activities.forget_activity;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.forget.AuthForget;
import com.schemecode.zr3i.data.models.login.AuthLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPresenter implements ForgetContract.presenter {

    private ForgetContract.view view;
    private ApiService apiService;

    public ForgetPresenter(ForgetContract.view view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
    }

    public void setView(ForgetContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.handleCLicks();
    }

    @Override
    public void forget(String mail, String lang, String platform, String hashKey) {
        view.showProgress();
        Call<AuthForget> forgetCall = apiService.forgetPass(mail, lang, platform, hashKey);
        forgetCall.enqueue(new Callback<AuthForget>() {
            @Override
            public void onResponse(Call<AuthForget> call, Response<AuthForget> response) {
                if (view != null) {
                    view.hideProgress();
                }
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() == true && response.body().getSuccess() == true && response.body().getAlert().equals("success")) {
                        if (view != null) {
                            view.showErrorOrSuccess(response.body().getMessage());
                        }
                    } else {
                        if (view != null){
                            view.showErrorOrSuccess(response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthForget> call, Throwable t) {
                if (view != null) {
                    view.hideProgress();
                    view.showErrorOrSuccess(t.getMessage());
                }
            }
        });
    }
}
