package com.schemecode.zr3i.ui.dialogs.error_dialog;

public interface ErrorContract {
    interface view{
        void initUi();
        void setData(String text);
    }

    interface presenter{
        void start(String text);
    }
}
