package com.example.chilin_wang.launcherexample.model;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chilin_Wang on 2017/9/5.
 */

public class LauncherAppInfo {
    private String mAppName;
    private String mPackageName;
    private Drawable mIcon;
    private Intent mLaunchIntentForPackage;

    public void setAppName(String appName) {
        mAppName = appName;
    }

    public void setPackageName(String packageName) {
        mPackageName = packageName;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public void setLaunchIntentForPackage(Intent intent) {
        mLaunchIntentForPackage = intent;
    }

    public String getAppName() {
        return mAppName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public Intent getLaunchIntentForPackage() {
        return mLaunchIntentForPackage;
    }

}
