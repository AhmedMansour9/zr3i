package com.schemecode.zr3i.ui.activities.home_activity;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.generate_forum_link.GenerateLink;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.presenter{
    private HomeContract.view view;
    private ApiService apiService ;

    public HomePresenter(HomeContract.view view) {
        this.view = view;
        apiService = ApiClient.getClient().create(ApiService.class);
    }

    public void setView(HomeContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.initDrawer();
        view.SelectItemInDrawerMenu();
        this.view.handleClicks();
    }

    @Override
    public void register() {

    }

    @Override
    public void generateMontadaLink(String token, String secret) {
        Call<GenerateLink> generateLinkCall = apiService.generateMontadeLink("ar" , "android" , "hash_key" , token , secret);
        generateLinkCall.enqueue(new Callback<GenerateLink>() {
            @Override
            public void onResponse(Call<GenerateLink> call, Response<GenerateLink> response) {
                if (response.isSuccessful()){
                    if (response.body().getData() != null && response.body().getData().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                        view.unAuthorized(response.body().getData());
                    }
                    else if (response.body() != null && response.body().getData() != null &&  response.body().getResult() == true && response.body().getSuccess() == true){
                        if (view != null){
                            view.join(response.body().getData());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GenerateLink> call, Throwable t) {

            }
        });
    }
}
