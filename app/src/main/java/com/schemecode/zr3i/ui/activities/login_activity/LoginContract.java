package com.schemecode.zr3i.ui.activities.login_activity;

public interface LoginContract {
    interface view {
        void saveCsrfToken(String token);
        void initUi();
        void handleCLicks();
        void showError(String text);
        void showProgress();
        void hideProgress();
        void unAuthorized(String message);
        void saveNewTokenToPref(String token , String secret , String message , String imageUrl);

    }
    interface presenter {
        void start();
        void login(String mail , String pass , String lang , String platform ,  String hashKey);
        void fbLogin(String email , String name , String id , String social_type);
        void getCrsToken();
    }
}
