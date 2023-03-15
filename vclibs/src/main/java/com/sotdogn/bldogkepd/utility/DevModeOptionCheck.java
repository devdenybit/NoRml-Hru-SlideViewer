package com.sotdogn.bldogkepd.utility;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sotdogn.bldogkepd.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DevModeOptionCheck {

    public static Activity activity;
    public static DevModeOptionCheck devModeOptionCheck;

    public DevModeOptionCheck(Activity activity1) {
        activity = activity1;
    }

    public static DevModeOptionCheck getInstance(Activity activity1) {
        activity = activity1;
        if (devModeOptionCheck == null) {
            devModeOptionCheck = new DevModeOptionCheck(activity);
        }
        return devModeOptionCheck;
    }
        Boolean data = false;
    public boolean DevMode_Check() {
        if (DevMode_Machanisum()) {
            showBottomSheetDialog();// if DevMode ON
        }else{
            data = true;
        }
        return data;
    }

    public boolean DevMode_Machanisum() {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) == 16) {
            return android.provider.Settings.Secure.getInt(activity.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0;
        } else if (Integer.parseInt(android.os.Build.VERSION.SDK) >= 17) {
            return android.provider.Settings.Secure.getInt(activity.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) != 0;
        } else return true;
    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity);
        bottomSheetDialog.setContentView(R.layout.dev_bottom_sheet_dialog_layout);
        bottomSheetDialog.setCancelable(false);

        TextView turnoff = bottomSheetDialog.findViewById(R.id.turnoff);

        turnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DevMode_Machanisum()) {
                    activity.startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS));
                } else {
                    DevMode_Check();
                }
            }
        });
        bottomSheetDialog.show();
    }

}
