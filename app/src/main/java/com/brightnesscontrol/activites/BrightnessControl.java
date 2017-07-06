package com.brightnesscontrol.activites;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;


import com.brightnesscontrol.R;

import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

public class BrightnessControl extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Starting activity::::::brightness_control::::::::");


        WindowManager.LayoutParams wlp = getWindow().getAttributes();
        wlp.dimAmount = 0;
        wlp.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        getWindow().setAttributes(wlp);

        // passes touch through activity
        Window window = getWindow();
        window.addFlags(FLAG_NOT_TOUCHABLE);

        // helps to show title bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.brightness_control);

        // findViewById(R.id.brightness_control).setBackgroundColor(0x66000000);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK)  //Override Keyback to do nothing in this case.
        {
            new MainActivity().finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);  //-->All others key will work as usual
    }


}
