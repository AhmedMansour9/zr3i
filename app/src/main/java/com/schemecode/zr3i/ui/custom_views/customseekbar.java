package com.schemecode.zr3i.ui.custom_views;

import android.content.Context;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSeekBar;

public class customseekbar extends AppCompatSeekBar {
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public customseekbar(@NonNull Context context) {
        super(context);
    }
}
