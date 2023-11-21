package com.schemecode.zr3i.ui.dialogs.add_farmer_dialog;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.add_farmer.FarmersStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFarmerPresenter implements AddFarmerContract.presenter {

    private AddFarmerContract.view view;
    private ApiService apiService;

    public AddFarmerPresenter(AddFarmerContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(AddFarmerContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.handleClicks();
    }

    @Override
    public void addFarmer(int landId ,String owner, String age, String phone, String city, String mail, String nationalId, String address, String token, String secret) {
        Call<FarmersStore> storeCall = apiService.storeFarmer(landId,owner, phone, mail, city, address, nationalId, age,
                "ar", "android", "hash_key", token, secret);

        storeCall.enqueue(new Callback<FarmersStore>() {
            @Override
            public void onResponse(Call<FarmersStore> call, Response<FarmersStore> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult() == true && response.body().getSuccess() == true) {
                        view.showToast(response.body().getMessage());
                    } else {
                        view.showToast(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<FarmersStore> call, Throwable t) {
                view.showToast(t.getMessage());
            }
        });

    }
}
