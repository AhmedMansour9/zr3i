package com.schemecode.zr3i.ui.activities.register_activity;

import com.schemecode.zr3i.data.models.plans.DataPlans;
import com.schemecode.zr3i.data.models.plans.Datum;

import java.util.List;

import retrofit2.http.Field;

public interface RegisterContract {
    interface view {
        void initUi();
        void initRecycler();
        void intSpinners();
        void handleClicks();
        void showProgressBar(String kindOfProgress);
        void hideProgressBar(String kindOfProgress);
        void getListOfPlans(List<Datum> plansList);
        void getListOfCountires(List<com.schemecode.zr3i.data.models.countries.Datum> countriesList);
        void getAccountTypes(List<com.schemecode.zr3i.data.models.account_types.Datum> accountsList);
        void showToast(String text);
        void unAuthorized(String message);
        void showErrorTextView(String error);
        void saveToSharedAndGoToHomeAct(String token , String secret , String message);
        void disableButtonAndShowProgressBar();
        void enableButtonAndHideProgressBar();
    }

    interface presenter {
        void start();
        void getPlans();
        void getCountries();
        void getAccountTypes();
        void callErrorMethod(String error);
        void register(String first_name, String last_name, int gender, int country_id, String city,
                      String mobile, int year, int day, int month, int plan,  String address, int accountType,  String email, String password, String passConfirmation,
                      String lang, String platform, String hashKey);
    }
}
