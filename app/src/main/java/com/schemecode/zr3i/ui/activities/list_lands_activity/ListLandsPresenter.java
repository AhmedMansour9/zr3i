package com.schemecode.zr3i.ui.activities.list_lands_activity;

import android.util.Log;
import android.view.View;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.list_lands.Data;
import com.schemecode.zr3i.data.models.list_lands.LandList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLandsPresenter implements ListLandsContract.presenter{
    private ApiService apiService;
    private ListLandsContract.view view;

    public ListLandsPresenter(ListLandsContract.view view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
    }

    public void setView(ListLandsContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.initRecycler();
        this.view.handleClicks();
    }

    @Override
    public void getLmyLandsList(int page , String token, String secret) {
        view.showOrHideProgress(View.VISIBLE);
        Call<LandList> landListCall = apiService.listLands(page, "ar" , "android" , "hash_key" , token , secret);
        landListCall.enqueue(new Callback<LandList>() {
            @Override
            public void onResponse(Call<LandList> call, Response<LandList> response) {
                if (view != null){
                    view.showOrHideProgress(View.GONE);
                }
                if (response.isSuccessful()){
                    if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null){
                        view.unAuthorized(response.body().getMessage());
                    }
                    else if (response.body().getSuccess() == true && response.body().getAlert().equals("success") || response.body().getAlert().equals("danger")){
                        if (view != null){
                            if (response.body().getResult()!= null && response.body().getResult() == true){
                                view.notifyAdapter(response.body().getData().getData() , response.body().getData().getLastPage());
                                view.paginate();
                            }
                            else if (response.body().getResult() != null && response.body().getResult() == false){
                                view.noData();
                            }
                        }
                    }
                    else {
                        view.showToast(response.body().getMessage());
                    }
                }
                else {
                    view.showFailureDialog("خطأ في الإتصال بالخادم");
                }
            }

            @Override
            public void onFailure(Call<LandList> call, Throwable t) {
                if (view != null){
                    Log.e("failure", t.getMessage());
                    Log.e("failure" , "failure");
                    view.noData();
                    view.showOrHideProgress(View.GONE);
                }
            }
        });
    }
}
