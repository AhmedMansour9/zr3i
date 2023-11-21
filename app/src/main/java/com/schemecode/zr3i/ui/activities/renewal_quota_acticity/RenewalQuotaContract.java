package com.schemecode.zr3i.ui.activities.renewal_quota_acticity;

import com.schemecode.zr3i.data.models.usage.Data;

public interface RenewalQuotaContract {
    interface view {
        void initUi();
        void initSpinner();
        void handleCLicks();
        void passPaymentUrl(String url);
        void setData(Data data);
        void showOrHideProgress(boolean isLoading);
        void inflateDialog(boolean isSuccess , String message);
        void unAuthorized(String message);
    }
    interface presenter {
        void start();
        void setData(Data data);
        void renew(int month ,String lang , String platform , String hash_key ,String token , String secret);
    }
}
