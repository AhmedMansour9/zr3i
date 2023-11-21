package com.schemecode.zr3i.ui.activities.change_pass;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.classes.Failuers;
import com.schemecode.zr3i.data.models.update_pass.AccUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassPresenter implements ChangePassContract.presenter {

    private ChangePassContract.view view;
    private ApiService apiService;

    public ChangePassPresenter(ChangePassContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(ChangePassContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.handleClicks();
    }

    @Override
    public void changePass(String currentPass, String newPass, String newPassConfiramtion, String token, String secret) {
        Call<AccUpdate> updateCall = apiService.changePass(currentPass, newPass, newPassConfiramtion, "ar", "android", "hash_key", token, secret);
        updateCall.enqueue(new Callback<AccUpdate>() {
            @Override
            public void onResponse(Call<AccUpdate> call, Response<AccUpdate> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    } else if (response.body().getAlert().equals("success")) {
                        if (view != null) {
                            view.showSuccessOrFailureDialog(true, response.body().getMessage());
                        }
                    } else {
                        if (view != null) {
                            view.showSuccessOrFailureDialog(false, response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<AccUpdate> call, Throwable t) {

            }
        });
    }


}
