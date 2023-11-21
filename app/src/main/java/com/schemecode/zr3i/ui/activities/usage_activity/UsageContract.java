package com.schemecode.zr3i.ui.activities.usage_activity;

import com.schemecode.zr3i.data.models.usage.Data;

public interface UsageContract {

    interface view{
        void initUi();
        void handleClicks();
        void setData(Data data);
        void showToast(String message);
        void unAuthorized(String message);
    }
    interface presenter {
        void start();
        void getUsage(String token , String secret);
    }
}
