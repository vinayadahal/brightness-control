package com.brightnesscontrol.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.brightnesscontrol.activites.MainActivity;

public class OverlayService extends Service {

    public static LinearLayout oView;
    public static Context ctx_overlay;
    MainActivity objMainActivity = new MainActivity();

    @Override
    public IBinder onBind(Intent i) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ctx_overlay = this;
        oView = new LinearLayout(this);
        oView.setBackgroundColor(0xdd000000); // The translucent black color
        oView.getBackground().setAlpha(Integer.parseInt(objMainActivity.readAlphaFromFile()));

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                0 | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.addView(oView, params);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (oView != null) {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            wm.removeView(oView);
        }
    }
}
