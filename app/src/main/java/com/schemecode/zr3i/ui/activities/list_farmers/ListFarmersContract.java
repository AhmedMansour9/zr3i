package com.schemecode.zr3i.ui.activities.list_farmers;

import com.schemecode.zr3i.data.models.show_farmers.Datum;

import java.util.List;

public interface ListFarmersContract {
    interface view {
        void initUi();
        void initRecycler();
        void notifyAdapter(List<Datum> farmers);
        void showOrHideProgress(int visiblity);
        void unAuthorized(String message);
        void noFarmers(String message);
    }
    interface presenter {
        void start();
        void getFarmers(int id , String token , String secret);
    }
}
