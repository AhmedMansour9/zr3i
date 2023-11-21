package com.schemecode.zr3i.ui.dialogs.success_dialog;

import com.schemecode.zr3i.ui.dialogs.error_dialog.ErrorContract;

public class SuccessDialogPresenter implements SuccessDialogContract.presenter{
    private SuccessDialogContract.view view;

    public SuccessDialogPresenter(SuccessDialogContract.view view) {
        this.view = view;
    }

    @Override
    public void start(String text) {
        this.view.initUi();
        this.view.setData(text);
    }
}
