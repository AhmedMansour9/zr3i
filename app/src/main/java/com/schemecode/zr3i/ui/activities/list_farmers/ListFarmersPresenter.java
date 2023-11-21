package com.schemecode.zr3i.ui.activities.list_farmers;

import android.view.View;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.show_farmers.FarmersList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFarmersPresenter implements ListFarmersContract.presenter{

    private ListFarmersContract.view view ;
    private ApiService apiService;

    public ListFarmersPresenter(ListFarmersContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(ListFarmersContract.view view) {
        this.view = view;
    }


    @Override
    public void start() {
        this.view.initUi();
        this.view.initRecycler();
    }

    @Override
    public void getFarmers(int id, String token, String secret) {
        if(view != null){
            view.showOrHideProgress(View.VISIBLE);
        }
        Call<FarmersList> farmersListCall = apiService.listFarmers(id , "ar", "android","hash_key" ,  token , secret);
        farmersListCall.enqueue(new Callback<FarmersList>() {
            @Override
            public void onResponse(Call<FarmersList> call, Response<FarmersList> response) {
                if(view != null){
                    view.showOrHideProgress(View.GONE);
                }
                if (response.isSuccessful()){
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getResult() == true && response.body().getSuccess() == true){
                        if (response.body().getData() != null && response.body().getData().size() != 0){
                            if (view != null){
                                view.notifyAdapter(response.body().getData());
                            }
                        }

                    }
                    else if (response.body().getData() != null && response.body().getData().isEmpty()){
                        if (view != null){
                            view.noFarmers("لا يوجد مزارعين");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<FarmersList> call, Throwable t) {
                if(view != null){
                    view.showOrHideProgress(View.GONE);
                }
            }
        });
    }
}
