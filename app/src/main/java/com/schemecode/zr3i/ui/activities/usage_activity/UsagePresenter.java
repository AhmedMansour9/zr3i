package com.schemecode.zr3i.ui.activities.usage_activity;

import android.util.Log;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.usage.AccUsage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsagePresenter implements UsageContract.presenter{

    private UsageContract.view view;
    private ApiService apiService;

    public UsagePresenter(UsageContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(UsageContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.handleClicks();
    }

    @Override
    public void getUsage(String token, String secret) {
        Call<AccUsage> usageCall = apiService.usage("ar" , "android" , "hash_key" , token , secret);
        usageCall.enqueue(new Callback<AccUsage>() {
            @Override
            public void onResponse(Call<AccUsage> call, Response<AccUsage> response) {
                if (response.isSuccessful()){
                    if (response.body().getMessage() != null && response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        Log.e("error" , response.body().getMessage());
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getData() != null && response.body().getResult() == true && response.body().getSuccess() == true){
                        if (view != null){

                            view.setData(response.body().getData());
                        }
                    }
                    else {
                        view.showToast(response.message());
                    }
                }
                else {
                    view.showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<AccUsage> call, Throwable t) {
                Log.e("failure" , t.getMessage());
            }
        });
    }
}
