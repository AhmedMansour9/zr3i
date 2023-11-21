package com.schemecode.zr3i.ui.dialogs.error_dialog;

public class ErrorPresenter implements ErrorContract.presenter{

    private ErrorContract.view view;

    public ErrorPresenter(ErrorContract.view view) {
        this.view = view;
    }

    @Override
    public void start(String text) {
        this.view.initUi();
        this.view.setData(text);
    }
}
