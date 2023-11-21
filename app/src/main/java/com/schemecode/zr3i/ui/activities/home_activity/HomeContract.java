package com.schemecode.zr3i.ui.activities.home_activity;

public interface HomeContract {
    interface view {
        void initUi();
        void handleClicks();
        void initDrawer();
        void openDrawer();
        void SelectItemInDrawerMenu();
        void createNewLandActivity();
        void showLandsActivity();
        void profileActivity();
        void unAuthorized(String message);
        void usageActivity();
        void changePassActivity();
        void join(String url);
    }
    interface presenter {
        void start();
        void register();
        void generateMontadaLink(String token , String secret);
    }
}
