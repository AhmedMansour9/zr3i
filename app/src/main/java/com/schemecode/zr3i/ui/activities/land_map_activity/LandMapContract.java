package com.schemecode.zr3i.ui.activities.land_map_activity;

import com.schemecode.zr3i.data.models.show_lands.Data;

import java.util.HashMap;

public interface LandMapContract {
    interface view {
        void initUi();
        void handleClicks();
        void initMap();
        void setData(Data data);
        void showToast(String message);
        void unAuthorized(String message);
        void showMapTypeSelectorDialog();
        void inflateSuccessDialog(boolean isSuccess , String message);
    }

    interface presenter {
        void start();
        void inflateDialog(boolean isSuccess , String message);
        void getLandObject(int id , String token , String secret);
        void editLandMap(int id , HashMap<String , String> listHashMap , String actualSpace , String token , String secret);
    }
}
