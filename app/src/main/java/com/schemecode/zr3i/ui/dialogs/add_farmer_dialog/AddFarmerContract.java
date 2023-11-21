package com.schemecode.zr3i.ui.dialogs.add_farmer_dialog;

public interface AddFarmerContract {
    interface view {
        void initUi();
        void handleClicks();
        void showToast(String text);
    }
    interface presenter{
        void start();
        void addFarmer(int landId , String owner , String age , String phone , String city , String mail , String nationalId , String address , String token , String secret);
    }
}
