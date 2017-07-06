package com.brightnesscontrol.activites;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.brightnesscontrol.R;

public class MainActivity extends AppCompatActivity {

    private SeekBar brightnessSeekBar;
    private int brightness = 0;
    private ContentResolver cResolver;
    private Context ctx = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layoutbrightness);

//        WindowManager.LayoutParams wlp = getWindow().getAttributes();
//        wlp.dimAmount = 0;
//        wlp.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS |
//                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//        getWindow().setAttributes(wlp);

        Button btnStart = (Button) findViewById(R.id.startBtn);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BrightnessControl.class);
                startActivity(intent);
            }
        });

        cResolver = getContentResolver();

        brightnessSeekBar = (SeekBar) findViewById(R.id.brightbar);
        brightnessSeekBar.setMax(255);
        brightnessSeekBar.setProgress(brightness);

        brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
                try {
                    brightness = Settings.System.getInt(cResolver, Settings.System.SCREEN_BRIGHTNESS);
                    System.out.println("Final brightness::::::::::::" + brightness);
                } catch (Settings.SettingNotFoundException e) {
                    System.out.println("Cannot access system brightness");
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Settings.System.putInt(cResolver,
                        Settings.System.SCREEN_BRIGHTNESS_MODE,
                        Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
                brightness = progress;
                Settings.System.putInt(cResolver, Settings.System.SCREEN_BRIGHTNESS, brightness);
            }
        });
    }
}

