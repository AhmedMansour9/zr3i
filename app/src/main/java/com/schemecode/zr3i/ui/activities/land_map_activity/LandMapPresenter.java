package com.schemecode.zr3i.ui.activities.land_map_activity;

import android.util.Log;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.show_lands.LandShow;
import com.schemecode.zr3i.data.models.update_map.LandsUpdateMap;

import org.json.JSONException;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandMapPresenter implements LandMapContract.presenter{

    private LandMapContract.view view;
    private ApiService apiService;

    public LandMapPresenter(LandMapContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(LandMapContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.initMap();
        this.view.handleClicks();
    }

    @Override
    public void inflateDialog(boolean isSuccess, String message) {
        if (view != null){
            view.inflateSuccessDialog(isSuccess , message);

        }
    }

    @Override
    public void getLandObject(int id , String token , String secret) {
        Call<LandShow> showCall = apiService.showLands("ar" , "android" , "hash_key" , token , secret , id);
        showCall.enqueue(new Callback<LandShow>() {
            @Override
            public void onResponse(Call<LandShow> call, Response<LandShow> response) {
                Log.e("response" , "response");
                if (response.isSuccessful()){
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() == true && response.body().getSuccess() == true && response.body().getAlert().equals("success")){
                        if (view != null){
                            view.setData(response.body().getData());
                        }
                    }
                    else {
                        if (view != null){
                            view.inflateSuccessDialog(false , response.body().getMessage());

                        }

                    }
                }
                else {
                    if (view != null){
                        view.inflateSuccessDialog(false , response.message());
                    }

                }
            }

            @Override
            public void onFailure(Call<LandShow> call, Throwable t) {
                if (view != null){
                    view.inflateSuccessDialog(false , t.getMessage());
                }
            }
        });
    }

    @Override
    public void editLandMap(int id ,HashMap<String, String> listHashMap, String actualSpace, String token, String secret) {
        Call<LandsUpdateMap> updateMapCall = apiService.updateMap(id , listHashMap , actualSpace ,"ar" , "android" , "hash_key" , token , secret);
        updateMapCall.enqueue(new Callback<LandsUpdateMap>() {
            @Override
            public void onResponse(Call<LandsUpdateMap> call, Response<LandsUpdateMap> response) {
                if (response.isSuccessful()){
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() == true && response.body().getSuccess() == true){
                        if (view != null){
                            view.inflateSuccessDialog(true , response.body().getMessage());
                        }
                    }
                    else {
                        if (view != null){
                            view.inflateSuccessDialog(false , response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<LandsUpdateMap> call, Throwable t) {
                if (view != null){
                    view.inflateSuccessDialog(false , t.getMessage());
                }
            }
        });
    }

}
