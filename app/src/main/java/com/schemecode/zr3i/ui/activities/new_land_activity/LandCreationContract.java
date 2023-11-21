package com.schemecode.zr3i.ui.activities.new_land_activity;

import com.schemecode.zr3i.data.classes.LatLng;
import com.schemecode.zr3i.data.models.countries.Datum;
import com.schemecode.zr3i.data.models.store_land.StoreLandRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

public interface LandCreationContract {
    interface view {
        void initMap();
        void initUi();
        void initSpinners();
        void handleClicks();
        void initDialog();
//        void getListOfCountires(List<Datum> countriesList);
//        void getListOfCrops(List<com.schemecode.zr3i.data.models.crops.Datum> cropsList);
        void showError(String txt);
        void showSuccessMessage(String txt);
        void showMapTypeSelectorDialog();
        void unAuthorized(String message);
        void showOrHideProgressProgress(int visibilty);
    }
    interface presenter {
        void start();
        void getCountries();
        void getCrops();
        void storeLand(StoreLandRequest storeLandRequest);
    }
}
