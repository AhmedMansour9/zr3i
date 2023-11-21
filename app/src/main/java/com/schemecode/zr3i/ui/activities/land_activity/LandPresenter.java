package com.schemecode.zr3i.ui.activities.land_activity;

import android.util.Log;

import com.google.android.datatransport.cct.internal.LogEvent;
import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.show_details_lands.LandsShowDetails;
import com.schemecode.zr3i.data.models.show_lands.LandShow;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandPresenter implements LandContract.presenter {

    private LandContract.view view;
    private ApiService apiService;

    public LandPresenter(LandContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(LandContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
    }

    @Override
    public void showLand(int id, String token, String secret) {
        if (view != null){
            view.showProgress(true);
        }
        Call<LandsShowDetails> showCall = apiService.showDetailsLand("ar", "android", "hash_key", token, secret, id);
        showCall.enqueue(new Callback<LandsShowDetails>() {
            @Override
            public void onResponse(Call<LandsShowDetails> call, Response<LandsShowDetails> response) {
                if (view != null){
                    view.showProgress(false);
                }
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() != null && response.body().getResult() == false && response.body().getIsLandDetailsAvailable() == false){
                        if (view != null){
                            view.quotaFinishedError(response.body().getMessage());
                            return;
                        }
                    }
                    else if (response.body().getResult() == true && response.body().getSuccess() == true && response.body().getAlert().equals("success")) {
                        if (view != null) {
                            try {
                                view.setData(response.body().getData() , response.body().getIsLandDetailsAvailable() , response.body().getMessage());
                                view.handleClicks();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                        }
                    }
                }
                else {
                    if (view != null){
                        view.showToast(response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<LandsShowDetails> call, Throwable t) {
                if (view != null){
                    view.showProgress(false);
                    view.showToast(t.getMessage());

                }
            }
        });
    }
}
