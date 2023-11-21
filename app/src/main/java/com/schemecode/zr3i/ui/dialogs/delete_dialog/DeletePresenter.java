package com.schemecode.zr3i.ui.dialogs.delete_dialog;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.delete_farmer.FarmersDelete;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePresenter implements DeleteContract.presenter{
    private ApiService apiService;
    private DeleteContract.view view;

    public DeletePresenter(DeleteContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.handleClicks();
    }

    @Override
    public void delete(int id , String token, String secret) {
        view.showProgress(true);
        Call<FarmersDelete> farmersDeleteCall = apiService.deleteFarmer(id , "ar" , "android" , "hash_key" , token , secret);
        farmersDeleteCall.enqueue(new Callback<FarmersDelete>() {
            @Override
            public void onResponse(Call<FarmersDelete> call, Response<FarmersDelete> response) {
                if (response.isSuccessful()){
                    if (response.body().getResult() == true && response.body().getSuccess() == true){
                        if (view != null){
                            view.showProgress(false);

                            view.showToast(response.body().getMessage());
                        }
                    }
                    else {
                        if (view != null){
                            view.showProgress(false);

                            view.showToast(response.body().getMessage());
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<FarmersDelete> call, Throwable t) {
                if (view != null){
                    view.showToast(t.getMessage());
                }
            }
        });
    }

    @Override
    public void deleteLand(int id, String token, String secret) {
        Call<FarmersDelete> farmersDeleteCall = apiService.deleteLand(id , "ar" , "android" , "hash_key" , token , secret);
        farmersDeleteCall.enqueue(new Callback<FarmersDelete>() {
            @Override
            public void onResponse(Call<FarmersDelete> call, Response<FarmersDelete> response) {
                if (response.isSuccessful()){
                    if (response.body().getResult() == true && response.body().getSuccess() == true){
                        if (view != null){
                            view.showToast(response.body().getMessage());
                        }
                    }
                    else {
                        if (view != null){
                            view.showToast(response.body().getMessage());
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<FarmersDelete> call, Throwable t) {
                if (view != null){
                    view.showToast(t.getMessage());
                }
            }
        });
    }
}
