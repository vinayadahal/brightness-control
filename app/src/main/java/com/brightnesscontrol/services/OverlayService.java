package com.brightnesscontrol.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.brightnesscontrol.activites.MainActivity;
import com.brightnesscontrol.commons.StaticVariables;

public class OverlayService extends Service {


    MainActivity objMainActivity = new MainActivity();
    StaticVariables objStaticVariable = new StaticVariables();

    final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

    @Override
    public IBinder onBind(Intent i) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        objStaticVariable.ctx_overlay = this;
        objStaticVariable.oView = new LinearLayout(this);
        objStaticVariable.oView.setBackgroundColor(0xdd000000); // The translucent black color
        objStaticVariable.oView.getBackground().setAlpha(Integer.parseInt(objMainActivity.readAlphaFromFile()));
        objStaticVariable.oView.setSystemUiVisibility(flags);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                0 | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(objStaticVariable.oView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (objStaticVariable.oView != null) {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            wm.removeView(objStaticVariable.oView);
        }
    }
}
