package com.example.chilin_wang.launcherexample.controller;

import android.os.Message;

import com.example.chilin_wang.launcherexample.LauncherApplication;
import com.example.chilin_wang.launcherexample.model.LauncherAppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chilin_Wang on 2017/9/12.
 */

public class LauncherController {
    private LauncherApplication mApplication;
    private List<LauncherAppInfo> mAppList = new ArrayList<>();

    public LauncherController(LauncherApplication application) {
        mApplication = application;
    }

    public List<LauncherAppInfo> getAppList() {
        return mAppList;
    }

    public void setAppList(List<LauncherAppInfo> appList) {
        mAppList = appList;
    }

    public void loadAppList(int loadBy) {
        mApplication.getWorkerHandler().removeMessages(LauncherApplication.MSG_LOAD_APP_LIST);
        Message msg = Message.obtain();
        msg.arg1 = loadBy;
        msg.what = LauncherApplication.MSG_LOAD_APP_LIST;
        mApplication.getWorkerHandler().sendMessage(msg);
    }

    public void sendOnItemClickMessage(Object obj) {
        Message msg = Message.obtain();
        msg.obj = obj;
        msg.what = mApplication.MSG_ON_ITEM_CLICK;
        mApplication.getWorkerHandler().sendMessage(msg);
    }

    public void sendOnItemLongClickMessage(Object obj) {
        Message msg = Message.obtain();
        msg.obj = obj;
        msg.what = mApplication.MSG_ON_ITEM_LONG_CLICK;
        mApplication.getWorkerHandler().sendMessage(msg);
    }

}
