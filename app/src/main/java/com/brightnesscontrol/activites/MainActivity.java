package com.brightnesscontrol.activites;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.brightnesscontrol.R;
import com.brightnesscontrol.commons.StaticVariables;
import com.brightnesscontrol.components.ToolBar;
import com.brightnesscontrol.listeners.BrightnessSeekBarListener;
import com.brightnesscontrol.services.OverlayService;
import com.brightnesscontrol.services.SettingWriter;

public class MainActivity extends Activity {

    private SeekBar brightnessSeekBar;
    private String SettingFileName = "alphaValue.txt";
    SettingWriter objSW = new SettingWriter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new StaticVariables().ctx_main_activity = this;
        // creates a custom toolbar
        new ToolBar().toolbarLoader(this, savedInstanceState);

        setContentView(R.layout.layout_brightness);

        brightnessSeekBar = findViewById(R.id.brightbar);
        brightnessSeekBar.setMax(255);
        checkBrightnessLevel();

        brightnessSeekBar.setOnSeekBarChangeListener(new BrightnessSeekBarListener());
        brightnessSeekBar.setVisibility(View.GONE);

        final Intent intent = new Intent(MainActivity.this, OverlayService.class);
        final Button btnStart = findViewById(R.id.startBtn);
        final Button btnStop = findViewById(R.id.stopBtn);
        final TextView txtViewSlider = findViewById(R.id.textViewSilder);


        btnStop.setVisibility(View.GONE);
        btnStart.setVisibility(View.VISIBLE);
        txtViewSlider.setVisibility(View.GONE);

        if (isMyServiceRunning(OverlayService.class)) {
            btnStart.setVisibility(View.GONE);
            btnStop.setVisibility(View.VISIBLE);
            brightnessSeekBar.setVisibility(View.VISIBLE);
            txtViewSlider.setVisibility(View.VISIBLE);
            checkBrightnessLevel();
        }

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(intent);
                brightnessSeekBar.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                btnStop.setVisibility(View.VISIBLE);
                txtViewSlider.setVisibility(View.VISIBLE);

            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intent);
                brightnessSeekBar.setVisibility(View.GONE);
                alphaWriter();
                btnStop.setVisibility(View.GONE);
                btnStart.setVisibility(View.VISIBLE);
                txtViewSlider.setVisibility(View.GONE);
            }
        });

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public String readAlphaFromFile() {
        String alpha;
        if (objSW.checkFile(SettingFileName)) {
            alpha = new SettingWriter().readFile(SettingFileName).toString().trim();
        } else {
            alpha = "0";
        }
        return alpha;
    }

    private void checkBrightnessLevel() {
        if (!objSW.checkFile(SettingFileName)) {
            brightnessSeekBar.setProgress(0);
        } else {
            brightnessSeekBar.setProgress(255 - Integer.parseInt(readAlphaFromFile()));
        }
    }

    private void alphaWriter() {
        String alphaVal = Integer.toString(new BrightnessSeekBarListener().alphaValue);
        new SettingWriter().writeFile(alphaVal, SettingFileName);
    }

    @Override
    public void onDestroy() {
        alphaWriter();
        super.onDestroy();
    }


}

