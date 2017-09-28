package com.example.chilin_wang.launcherexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chilin_Wang on 2017/9/12.
 */

public class TestAppLoader implements IAppLoader {
    @Override
    public List<LauncherAppInfo> loadAppList() {
        List<LauncherAppInfo> appList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LauncherAppInfo appInfo = new LauncherAppInfo();
            appInfo.setAppName("test"+i);
            appList.add(appInfo);
        }
        return appList;
    }
}
