package com.example.chilin_wang.launcherexample.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chilin_Wang on 2017/9/12.
 */

public class PackageManagerLoader implements IAppLoader {
    private Context mContext;
    private PackageManager mPackageManager;

    public PackageManagerLoader(Context context) {
        mContext = context;
        mPackageManager = mContext.getPackageManager();
    }

    @Override
    public List<LauncherAppInfo> loadAppList() {
        List<LauncherAppInfo> appList = new ArrayList<>();
        List<PackageInfo> list = mPackageManager.getInstalledPackages(0);
        for (int i = 0; i < list.size(); i++) {
            LauncherAppInfo appInfo = new LauncherAppInfo();
            appInfo.setAppName(list.get(i).applicationInfo.loadLabel(
                    mPackageManager).toString());
            appInfo.setPackageName(list.get(i).packageName);
            appInfo.setIcon(list.get(i).applicationInfo
                    .loadIcon(mPackageManager));
            appInfo.setLaunchIntentForPackage(mPackageManager.getLaunchIntentForPackage(list.get(i).packageName));
            if (mPackageManager.getLaunchIntentForPackage(appInfo.getPackageName()) != null) {
                appList.add(appInfo);
            }
        }
        return appList;
    }

}
