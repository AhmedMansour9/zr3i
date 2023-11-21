package com.schemecode.zr3i.ui.activities;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

public class MainApp extends Application {

    public static String BASE_URL = "http://mdi.stagging.zr3i.com/api/v1/";
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("AppLink", MODE_PRIVATE);
        String url = sharedPreferences.getString("link", null);
        if (url != null) {
            BASE_URL = url;
            Log.d("TAG", "onCreate: "+url);
        }

    }
}
