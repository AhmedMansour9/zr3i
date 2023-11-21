package com.schemecode.zr3i.ui.activities.edit_land_details;

import android.view.View;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.countries.DataCountries;
import com.schemecode.zr3i.data.models.update_land.LandsUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditLandPresenter implements EditLandDetailsContract.presenter {

    private ApiService apiService;
    private EditLandDetailsContract.view view;

    public EditLandPresenter(EditLandDetailsContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(EditLandDetailsContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.initSpinners();
        this.view.setData();
        getCountries();
        this.view.handleClicks();
    }

    @Override
    public void editLand(String owner, String city, String cityArea, String contractSpace, int countryId, int cropPlantingCycles, String desc, int id, String lang, String platform, String hashKey, String token, String secret) {
        Call<LandsUpdate> landsUpdateCall = apiService.updateLand(owner, city, cityArea, contractSpace, countryId, cropPlantingCycles, desc, id, "ar", "android", "hash_key", token, secret);
        if (view != null) {
            view.showOrHideProgressBar(View.VISIBLE);
        }
        landsUpdateCall.enqueue(new Callback<LandsUpdate>() {
            @Override
            public void onResponse(Call<LandsUpdate> call, Response<LandsUpdate> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() == true && response.body().getSuccess() == true && response.body().getAlert().equals("success")) {
                        if (view != null) {
                            view.showSuuccessOrFailureDialog(true, response.body().getMessage());
                        }
                    } else {
                        if (view != null) {
                            view.showSuuccessOrFailureDialog(false, response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LandsUpdate> call, Throwable t) {
                if (view != null) {
                    view.showSuuccessOrFailureDialog(false, t.getMessage());
                }
            }
        });
    }


    @Override
    public void getCountries() {
        Call<DataCountries> countriesCall = apiService.getCountries("ar", "android", "hash_key");
        countriesCall.enqueue(new Callback<DataCountries>() {
            @Override
            public void onResponse(Call<DataCountries> call, Response<DataCountries> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult() == true && response.body().getSuccess() == true) {
                        if (view != null) {
                            view.getListOfCountires(response.body().getData());
                        }
                    } else {
                        if (view != null) {
                            view.showToast(response.message());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DataCountries> call, Throwable t) {
                if (view != null) {
                    view.showToast(t.getMessage());
                }
            }
        });
    }

}
