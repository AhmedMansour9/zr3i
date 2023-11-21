package com.schemecode.zr3i.ui.dialogs.success_dialog;

public interface SuccessDialogContract {
    interface view{
        void initUi();
        void setData(String text);
    }

    interface presenter{
        void start(String text);
    }
}
