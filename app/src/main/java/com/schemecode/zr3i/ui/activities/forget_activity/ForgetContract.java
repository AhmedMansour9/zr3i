package com.schemecode.zr3i.ui.activities.forget_activity;

public interface ForgetContract {
    interface view {
        void initUi();
        void handleCLicks();
        void showErrorOrSuccess(String text);
        void showProgress();
        void unAuthorized(String message);
        void hideProgress();
    }
    interface presenter {
        void start();
        void forget(String mail , String lang , String platform ,  String hashKey);
    }


}

