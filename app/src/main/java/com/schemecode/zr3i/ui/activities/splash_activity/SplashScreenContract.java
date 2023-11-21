package com.schemecode.zr3i.ui.activities.splash_activity;

public interface SplashScreenContract {
    interface view {
        void initUi();
        void handleClicks();
        void showError(String message);
        void saveNewTokenToPref(String token , String secret , String message , String imageUrl);
    }
    interface presenter {
        void start();
        void fbLogin(String email , String name , String id , String social_type);
    }
}
