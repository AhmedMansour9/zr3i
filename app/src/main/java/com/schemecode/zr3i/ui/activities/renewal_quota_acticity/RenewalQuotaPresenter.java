package com.schemecode.zr3i.ui.activities.renewal_quota_acticity;

import com.schemecode.zr3i.data.api.ApiClient;
import com.schemecode.zr3i.data.api.ApiService;
import com.schemecode.zr3i.data.models.plan_renew.AccPlanRenew;
import com.schemecode.zr3i.data.models.usage.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RenewalQuotaPresenter implements RenewalQuotaContract.presenter {

    private RenewalQuotaContract.view view;
    private ApiService apiService;

    public RenewalQuotaPresenter(RenewalQuotaContract.view view) {
        apiService = ApiClient.getClient().create(ApiService.class);
        this.view = view;
    }

    public void setView(RenewalQuotaContract.view view) {
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
    public void renew(int month, String lang, String platform, String hash_key, String token, String secret) {
        if (view != null){
            view.showOrHideProgress(true);
        }
        Call<AccPlanRenew> planRenewCall = apiService.plan_renew(month, lang, platform, hash_key, token, secret);
        planRenewCall.enqueue(new Callback<AccPlanRenew>() {
            @Override
            public void onResponse(Call<AccPlanRenew> call, Response<AccPlanRenew> response) {
                if (view != null){
                    view.showOrHideProgress(false);
                }
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResult() == true && response.body().getSuccess() == true && view != null) {
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
            public void onFailure(Call<AccPlanRenew> call, Throwable t) {
                if (view != null) {
                    view.showOrHideProgress(false);
                    view.inflateDialog(false, t.getMessage());

                }
            }
        });
    }

}
