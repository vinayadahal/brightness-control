package com.brightnesscontrol.listeners;


import android.widget.SeekBar;

import com.brightnesscontrol.activites.MainActivity;
import com.brightnesscontrol.commons.StaticVariables;

public class BrightnessSeekBarListener implements SeekBar.OnSeekBarChangeListener {

    public static int alphaValue;
    boolean flag = false;
    StaticVariables objStaticVariable = new StaticVariables();

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        alphaValue = (255 - progress);
        objStaticVariable.oView.getBackground().setAlpha(alphaValue);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (!flag) {
            objStaticVariable.oView.getBackground().setAlpha(Integer.parseInt(new MainActivity().readAlphaFromFile()));
            flag = true;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        objStaticVariable.oView.getBackground().setAlpha(alphaValue);
    }
}
