package com.schemecode.zr3i.ui.activities.new_land_activity;

import android.view.View;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.classes.LatLng;
import com.schemecode.zr3i.data.models.countries.DataCountries;
import com.schemecode.zr3i.data.models.crops.DataCrops;
import com.schemecode.zr3i.data.models.land_store.LandStore;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandCreationPresenter implements LandCreationContract.presenter {

    private LandCreationContract.view view;
    private ApiService apiService;

    public LandCreationPresenter(LandCreationContract.view view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
    }

    public void setView(LandCreationContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.initSpinners();
        this.view.initMap();
        this.view.handleClicks();
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
                            view.showError(response.message());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DataCountries> call, Throwable t) {
                if (view != null) {
                    view.showError(t.getMessage());
                }
            }
        });
    }

    @Override
    public void getCrops() {
        Call<DataCrops> dataCropsCall = apiService.getCrops("ar", "android", "hash_key");
        dataCropsCall.enqueue(new Callback<DataCrops>() {
            @Override
            public void onResponse(Call<DataCrops> call, Response<DataCrops> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult() == true && response.body().getSuccess() == true) {
                        if (view != null){
                            view.getListOfCrops(response.body().getData());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DataCrops> call, Throwable t) {

            }
        });
    }

    @Override
    public void storeLand(HashMap<String, String> map, String owner, String city, String area, String actualSpace, String contractSpace, int countryId, int cropId, int cropPlantingCycles, String desc, String token, String secret) {
        Call<LandStore> landStoreCall =
                apiService.storeLand(map, actualSpace, owner, city, area, contractSpace, countryId,
                cropId, cropPlantingCycles, desc, "ar", "android", "hash_key", token, secret);
        view.showOrHideProgressProgress(View.VISIBLE);
        landStoreCall.enqueue(new Callback<LandStore>() {
            @Override
            public void onResponse(Call<LandStore> call, Response<LandStore> response) {
                if (view != null) {
                    view.showOrHideProgressProgress(View.GONE);
                }
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() == true && response.body().getAlert().equals("success")) {
                        if (view != null) {
                            view.showSuccessMessage(response.body().getMessage());
                        }
                    } else if (response.body().getResult() == true && response.body().getAlert().equals("danger")) {
                        if (view != null){
                            view.showError(response.body().getMessage());

                        }
                    } else {
                        if (view != null) {
                            view.showError(response.body().getMessage());
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<LandStore> call, Throwable t) {
                if (view != null) {
                    view.showOrHideProgressProgress(View.GONE);
                    view.showError(t.getMessage());
                }
            }
        });

    }

}
