package com.schemecode.zr3i.ui.activities.register_activity;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.account_types.DataAccountTypes;
import com.schemecode.zr3i.data.models.countries.DataCountries;
import com.schemecode.zr3i.data.models.plans.DataPlans;
import com.schemecode.zr3i.data.models.plans.Datum;
import com.schemecode.zr3i.data.models.register.AuthRegister;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements RegisterContract.presenter {

    private RegisterContract.view view;
    private ApiService apiService;

    public RegisterPresenter(RegisterContract.view view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
    }

    public void setView(RegisterContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.initRecycler();
        this.view.intSpinners();
        this.view.handleClicks();
    }

    @Override
    public void getPlans() {
        view.showProgressBar("quota");
        List<Datum> plansList = new ArrayList<>();
        Call<DataPlans> plansCall = apiService.getPlans("ar", "android", "hash_key");
        plansCall.enqueue(new Callback<DataPlans>() {
            @Override
            public void onResponse(Call<DataPlans> call, Response<DataPlans> response) {
                if (view != null) {
                    view.hideProgressBar("quota");
                }
                if (response.isSuccessful()) {
                    if (response.body().getSuccess() == true && response.body().getResult() == true) {
                        if (view != null) {
                            view.getListOfPlans(response.body().getData());
                        }
                    } else {
                        if (view != null) {
                            view.showToast(response.message());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DataPlans> call, Throwable t) {
                if (view != null) {
                    view.hideProgressBar("quota");
                    view.showToast(t.getMessage());
                }
            }
        });
    }

    @Override
    public void getCountries() {
        view.showProgressBar("countries");
        Call<DataCountries> countriesCall = apiService.getCountries("ar", "android", "hash_key");
        countriesCall.enqueue(new Callback<DataCountries>() {
            @Override
            public void onResponse(Call<DataCountries> call, Response<DataCountries> response) {
                if (view != null) {
                    view.hideProgressBar("countries");
                }
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
                    view.hideProgressBar("countries");
                    view.showToast(t.getMessage());

                }
            }
        });
    }

    @Override
    public void getAccountTypes() {
        view.showProgressBar("account_type");
        Call<DataAccountTypes> countriesCall = apiService.getAccountTypes("ar", "android", "hash_key");
        countriesCall.enqueue(new Callback<DataAccountTypes>() {
            @Override
            public void onResponse(Call<DataAccountTypes> call, Response<DataAccountTypes> response) {
                if (view != null) {
                    view.hideProgressBar("account_type");
                }
                if (response.isSuccessful()) {
                    if (response.body().getResult() == true && response.body().getSuccess() == true) {
                        if (view != null) {
                            view.getAccountTypes(response.body().getData());
                        }
                    } else {
                        if (view != null) {
                            view.showToast(response.message());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DataAccountTypes> call, Throwable t) {
                if (view != null) {
                    view.hideProgressBar("account_type");
                    view.showToast(t.getMessage());
                }
            }
        });
    }

    @Override
    public void callErrorMethod(String error) {
        if (view != null) {
            view.showErrorTextView(error);
        }
    }

    @Override
    public void register(String first_name, String last_name, int gender, int country_id,
                         String city, String mobile, int year, int day, int month, int plan, String address,
                         int accountType, String email, String password, String passConfirmation, String lang, String platform, String hashKey) {
        Call<AuthRegister> registerCall = apiService.register(first_name, last_name, gender, country_id, city, mobile, year, day
                , month, plan, address, accountType, email, password, passConfirmation, lang, platform, hashKey);
        view.disableButtonAndShowProgressBar();
        registerCall.enqueue(new Callback<AuthRegister>() {
            @Override
            public void onResponse(Call<AuthRegister> call, Response<AuthRegister> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAlert().equals("success") && response.body().getSuccess() == true) {
                        if (view != null) {
                            view.enableButtonAndHideProgressBar();
                            view.saveToSharedAndGoToHomeAct(response.body().getToken(), response.body().getSecret() , response.body().getMessage());
                        }
                    }
                    else if (response.body().getAlert().equals("danger")){
                        view.enableButtonAndHideProgressBar();
                        view.showErrorTextView(response.body().getMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<AuthRegister> call, Throwable t) {
                view.enableButtonAndHideProgressBar();
            }
        });
    }

}
