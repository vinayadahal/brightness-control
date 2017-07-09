package com.brightnesscontrol.components;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.brightnesscontrol.R;

public class ToolBar implements AppCompatCallback {

    private AppCompatDelegate delegate;

    public void toolbarLoader(Context mainCtx, Bundle savedInstanceState) {
        //let's create the delegate, passing the activity at both arguments (Activity, AppCompatCallback)
        delegate = AppCompatDelegate.create((Activity) mainCtx, this);

        //we need to call the onCreate() of the AppCompatDelegate
        delegate.onCreate(savedInstanceState);

        //we use the delegate to inflate the layout
        delegate.setContentView(R.layout.layout_brightness);

        //Finally, let's add the Toolbar
        Toolbar toolbar = (Toolbar) delegate.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(0xffffffff);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println("Menu ITEM::::::::::::::::::::::::: ");
                return false;
            }
        });
        delegate.setSupportActionBar(toolbar);
    }


    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}
