package com.brightnesscontrol.listeners;


import android.widget.SeekBar;

import com.brightnesscontrol.activites.MainActivity;
import com.brightnesscontrol.services.OverlayService;

public class BrightnessSeekBarListener implements SeekBar.OnSeekBarChangeListener {

    public static int alphaValue;
    boolean flag = false;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
        alphaValue = (255 - progress);
        new OverlayService().oView.getBackground().setAlpha(alphaValue);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (!flag) {
            new OverlayService().oView.getBackground().setAlpha(Integer.parseInt(new MainActivity().readAlphaFromFile()));
            flag = true;
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        new OverlayService().oView.getBackground().setAlpha(alphaValue);
    }
}
