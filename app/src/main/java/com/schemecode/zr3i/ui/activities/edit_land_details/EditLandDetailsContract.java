package com.schemecode.zr3i.ui.activities.edit_land_details;

import com.schemecode.zr3i.data.models.countries.Datum;

import java.util.List;

import retrofit2.http.Field;

public interface EditLandDetailsContract {
    interface view {
        void initUi();
        void initSpinners();
        void setData();
        void handleClicks();
        void unAuthorized(String message);
        void getListOfCountires(List<Datum> countires);
        void showToast(String message);
        void showOrHideProgressBar(int visibilty);
        void showSuuccessOrFailureDialog(boolean is_success, String message);
    }

    interface presenter {
        void start();
        void getCountries();
        void editLand(String owner, String city, String cityArea, String contractSpace, int countryId, int cropPlantingCycles, String desc, int id, String lang,
                      String platform, String hashKey, String token, String secret);
    }
}
