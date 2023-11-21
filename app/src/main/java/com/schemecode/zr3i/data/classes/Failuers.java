package com.schemecode.zr3i.data.classes;

import android.content.Context;
import android.content.Intent;

import com.schemecode.zr3i.ui.activities.login_activity.LoginActivity;

public class Failuers {
    private Context context;

    public Failuers(Context context) {
        this.context = context;
    }

    public void login(){
        Intent intent = new Intent(context , LoginActivity.class);
        context.startActivity(intent);
    }
}
