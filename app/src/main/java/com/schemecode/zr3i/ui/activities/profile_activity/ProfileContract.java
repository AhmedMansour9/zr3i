package com.schemecode.zr3i.ui.activities.profile_activity;

import android.content.Context;
import android.net.Uri;

import com.schemecode.zr3i.data.models.countries.Datum;
import com.schemecode.zr3i.data.models.profile.AccProfile;
import com.schemecode.zr3i.data.models.profile.Data;

import java.util.List;

public interface ProfileContract {
    interface view{
        void initUi();
        void initSpinners();
        void getListOfCountires(List<Datum> countriesList);
        void setData(Data accProfile);
        void handleClicks();
        void unAuthorized(String message);
        void showOrHideProgressProgress(int visibilty);
        void setDataToShared(String image);
        void inflateSuccessDialog(boolean is_success , String message);
        void setBtnEnabledOrDisable(boolean is_enabled);
        Context returnContext();
        boolean permission();
    }
    interface presenter {
        void start();
        void getCountries();
        void updateProfile(String firstName , String lastName , int gender , int countryId , String city , String mobile , int year , int day , int month , String email , Uri uri, String token , String secret);
        void getMyProfile(String token , String secret);
    }
}
