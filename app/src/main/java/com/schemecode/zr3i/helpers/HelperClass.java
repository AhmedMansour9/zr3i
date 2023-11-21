package com.schemecode.zr3i.helpers;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelperClass {
    public static void alignDialogRTL(Dialog dialog, Context context) {
        // Get message text view
        TextView message = (TextView)dialog.findViewById(android.R.id.message);

        // Defy gravity
        message.setGravity(Gravity.RIGHT);

        // Get title text view
        TextView title = (TextView)dialog.findViewById(context.getResources().getIdentifier("alertTitle", "id", "android"));

        // Defy gravity (again)
        title.setGravity(Gravity.RIGHT);

        // Get title's parent layout
        LinearLayout parent = ((LinearLayout)title.getParent());

        // Get layout params
        LinearLayout.LayoutParams originalParams = (LinearLayout.LayoutParams)parent.getLayoutParams();

        // Set width to WRAP_CONTENT
        originalParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;

        // Defy gravity (last time)
        originalParams.gravity = Gravity.RIGHT |  Gravity.CENTER_VERTICAL;

        // Set updated layout params
        parent.setLayoutParams(originalParams);
    }
}
