package com.schemecode.zr3i.ui.activities.upgrade_account_activity;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.plan_renew.AccPlanRenew;
import com.schemecode.zr3i.data.models.plan_upgrade.AccPlanUpgrade;
import com.schemecode.zr3i.data.models.plans.DataPlans;
import com.schemecode.zr3i.data.models.plans.Datum;
import com.schemecode.zr3i.data.models.usage.Data;
import com.schemecode.zr3i.ui.activities.renewal_quota_acticity.RenewalQuotaContract;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpgradeAccountPresenter implements UpgradeAccountContract.presenter{

    private UpgradeAccountContract.view view;
    private ApiService apiService;

    public UpgradeAccountPresenter(UpgradeAccountContract.view view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
    }

    public void setView(UpgradeAccountContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        this.view.initUi();
        this.view.initSpinner();
        this.view.handleCLicks();
    }

    @Override
    public void setData(Data data) {
        this.view.setData(data);
    }


    @Override
    public void upgrade(int planid, String lang, String platform, String hash_key, String token, String secret) {
        if (view != null){
            view.showOrHideProgress(true);
        }
        Call<AccPlanUpgrade> planUpgradeCall = apiService.plan_upgrade(planid, lang, platform, hash_key, token, secret);
        planUpgradeCall.enqueue(new Callback<AccPlanUpgrade>() {
            @Override
            public void onResponse(Call<AccPlanUpgrade> call, Response<AccPlanUpgrade> response) {
                if (view != null){
                    view.showOrHideProgress(false);
                }
                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        if (response.body().getMessage().equals("فضلا قم بتسجيل الدخــول أولا") && view != null) {
                            view.unAuthorized(response.body().getMessage());
                        }
                        else if (response.body().getResult() == true && response.body().getSuccess() == true && view != null) {
                            view.passPaymentUrl(response.body().getData().getPaymentUrl());
                        } else if (response.body().getResult() == false && response.body().getSuccess() == false && view != null) {
                            view.inflateDialog(false, response.body().getMessage());
                        }
                    }
                } else {
                    if (view != null) {
                        view.inflateDialog(false, response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<AccPlanUpgrade> call, Throwable t) {
                if (view != null) {
                    view.showOrHideProgress(false);
                    view.inflateDialog(false, t.getMessage());

                }
            }
        });
    }
}
