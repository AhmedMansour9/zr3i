package com.schemecode.zr3i.ui.dialogs.delete_dialog;

import androidx.recyclerview.widget.RecyclerView;

public interface DeleteContract {
    interface view{
        void initUi();
        void handleClicks();
        void showToast(String message);
        void showProgress(boolean isShow);
    }

    interface presenter{
        void start();
        void delete(int id , String token , String secret);
        void deleteLand(int id , String token , String secret);
    }
}
