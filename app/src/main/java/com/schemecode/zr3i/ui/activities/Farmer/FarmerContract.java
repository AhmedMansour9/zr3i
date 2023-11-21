package com.schemecode.zr3i.ui.activities.Farmer;

import com.schemecode.zr3i.data.models.show_farmers.Datum;

public interface FarmerContract {
    interface view {
        void initUi();
        void handleClicks();
        void setData(Datum farmer);
        void unAuthorized(String message);
        void showSuccessOrFailed(boolean is_success , String message);
        void showOrHideProgressBar(int visibilty);
    }
    interface presenter {
        void start();
        void setData(Datum farmer);
        void updateFarmer(int id , String name , String age  , String mobile , String city , String mail , String nationalId ,String address , String token, String secret);
        void deleteFarmer(int id , String token , String secret);
    }
}
