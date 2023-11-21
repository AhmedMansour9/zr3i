package com.schemecode.zr3i.ui.activities.change_pass;

public interface ChangePassContract {
    interface view {
        void initUi();
        void handleClicks();
        void login();
        void unAuthorized(String message);
        void showSuccessOrFailureDialog(boolean is_success , String message);
    }

    interface presenter {
        void start();
        void changePass(String currentPass , String newPass , String newPassConfiramtion , String token , String secret);
    }
}
