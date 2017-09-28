package com.example.chilin_wang.launcherexample.model;

import android.content.Context;

import java.util.List;

/**
 * Created by Chilin_Wang on 2017/9/12.
 */

public class AppLoaderFactory {
    public static final int LOAD_APP_LIST_BY_PACKAGEMANAGER = 100;
    public static final int LOAD_APP_LIST_BY_TEST = 101;
    private Context mContext;
    private IAppLoader mAppLoader;

    public AppLoaderFactory(Context context) {
        mContext = context;
    }

    public List<LauncherAppInfo> loadAppList(int loadBy) {
        switch(loadBy) {
            case LOAD_APP_LIST_BY_PACKAGEMANAGER:
                mAppLoader = new PackageManagerLoader(mContext);
                break;
            case LOAD_APP_LIST_BY_TEST:
                mAppLoader = new TestAppLoader();
                break;
            default:
                return null;
        }
        return mAppLoader.loadAppList();
    }
}
