package com.schemecode.zr3i.ui.activities.upgrade_account_activity;

import com.schemecode.zr3i.data.models.plans.Datum;
import com.schemecode.zr3i.data.models.usage.Data;

import java.util.List;

public interface UpgradeAccountContract {
    interface view {
        void initUi();
        void initSpinner();
        void handleCLicks();
        void passPaymentUrl(String url);
        void setData(Data data);
        void showOrHideProgress(boolean isLoading);
        void unAuthorized(String message);
        void inflateDialog(boolean isSuccess , String message);
    }
    interface presenter {
        void start();
        void setData(Data data);
        void upgrade(int planid ,String lang , String platform , String hash_key ,String token , String secret);
    }
}
