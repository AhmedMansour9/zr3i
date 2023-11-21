package com.schemecode.zr3i.ui.activities.Farmer;

import android.view.View;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.delete_farmer.FarmersDelete;
import com.schemecode.zr3i.data.models.edit_farmers.FarmersUpdate;
import com.schemecode.zr3i.data.models.show_farmers.Datum;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmerPresenter implements FarmerContract.presenter{

    private FarmerContract.view view ;
    private ApiService apiService;

    public FarmerPresenter(FarmerContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(FarmerContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
    }

    @Override
    public void setData(Datum farmer) {
        view.setData(farmer);
        view.handleClicks();
    }

    @Override
    public void updateFarmer(int id , String name , String age  , String mobile , String city , String mail , String nationalId ,String address , String token, String secret) {
        if (view != null){
            view.showOrHideProgressBar(View.VISIBLE);
        }
        Call<FarmersUpdate> farmersUpdateCall = apiService.updateFarmer(id , name , address , mail , city , mail , nationalId , age , "ar" , "android" , "hash_key" , token , secret);
        farmersUpdateCall.enqueue(new Callback<FarmersUpdate>() {
            @Override
            public void onResponse(Call<FarmersUpdate> call, Response<FarmersUpdate> response) {
                if (view != null){
                    view.showOrHideProgressBar(View.GONE);
                }
                if (response.isSuccessful()){
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() == true && response.body().getSuccess() == true && response.body().getAlert().equals("success")){
                        if (view != null){
                            view.showSuccessOrFailed(true , response.body().getMessage());
                        }
                    }
                    else {
                        if (view != null){
                            view.showSuccessOrFailed(false , response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FarmersUpdate> call, Throwable t) {
                if (view != null){
                    view.showSuccessOrFailed(false , t.getMessage());
                    view.showOrHideProgressBar(View.GONE);
                }
            }
        });
    }

    @Override
    public void deleteFarmer(int id , String token , String secret) {
        Call<FarmersDelete> farmersDeleteCall = apiService.deleteFarmer(id , "ar" , "android" , "hash_key" , token , secret);
        farmersDeleteCall.enqueue(new Callback<FarmersDelete>() {
            @Override
            public void onResponse(Call<FarmersDelete> call, Response<FarmersDelete> response) {
                if (response.isSuccessful()){
                    if (view != null){
                        if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                            view.unAuthorized(response.body().getMessage());
                        }
                        else if (response.body().getResult() == true && response.body().getSuccess() == true){
                            view.showSuccessOrFailed(true , response.body().getMessage());
                        }
                        else {
                            view.showSuccessOrFailed(false , response.body().getMessage());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FarmersDelete> call, Throwable t) {
                if (view != null){
                    view.showSuccessOrFailed(false , t.getMessage());
                }
            }
        });
    }
}
