package com.schemecode.zr3i.ui.activities.list_lands_activity;

import com.schemecode.zr3i.data.models.list_lands.Datum;

import java.util.List;

public interface ListLandsContract {
    interface view {
        void initUi();
        void initRecycler();
        void notifyAdapter(List<Datum> list , int lastPage);
        void showToast(String text);
        void showOrHideProgress(int visibilty);
        void handleClicks();
        void showFailureDialog(String message);
        void noData();
        void unAuthorized(String message);
        void paginate();
    }
    interface presenter{
        void start();
        void getLmyLandsList(int page,String token , String secret);
    }
}
