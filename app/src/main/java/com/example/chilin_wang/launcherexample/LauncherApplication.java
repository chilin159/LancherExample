package com.example.chilin_wang.launcherexample;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.example.chilin_wang.launcherexample.controller.LauncherController;
import com.example.chilin_wang.launcherexample.model.AppLoaderFactory;
import com.example.chilin_wang.launcherexample.model.LauncherAppInfo;
import com.example.chilin_wang.launcherexample.view.IActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chilin_Wang on 2017/9/11.
 */

public class LauncherApplication extends Application {
    public static final int MSG_UPDATE_APP_LIST = 100;
    public static final int MSG_LOAD_APP_LIST = 101;
    public static final int MSG_ON_ITEM_CLICK = 102;
    public static final int MSG_ON_ITEM_LONG_CLICK = 103;
    public static final int FLAG_MAIN_ACTIVITY = 201;
    private IActivity mIActivity;
    private UIHandler mHandler;
    private HandlerThread mWorkerHandlerThread;
    private WorkerHandler mWorkerHandler;
    private LauncherController mLauncherController = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mLauncherController = new LauncherController(this);
        mWorkerHandlerThread = new HandlerThread("workerhandlerthread");
        mHandler = new UIHandler(this);
        mWorkerHandlerThread.start();
        mWorkerHandler = new WorkerHandler(this, mWorkerHandlerThread.getLooper());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (mWorkerHandlerThread != null) {
            mWorkerHandlerThread.quit();
        }
        if (mWorkerHandler != null) {
            mWorkerHandler.removeCallbacksAndMessages(null);
        }
        if (mLauncherController != null) {
            mLauncherController = null;
        }
        clearCurrentActivity();
    }

    public LauncherController getController() {
        return mLauncherController;
    }

    public Handler getUIHandler() {
        return mHandler;
    }

    public Handler getWorkerHandler() {
        return mWorkerHandler;
    }

    public IActivity getCurrentActivity() {
        return mIActivity;
    }

    public void setCurrentActivity(IActivity activity) {
        mIActivity = activity;
    }

    public void clearCurrentActivity() {
        mIActivity = null;
    }

    private static class UIHandler extends Handler {
        private LauncherApplication mApplication;

        public UIHandler(LauncherApplication application) {
            mApplication = application;
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_UPDATE_APP_LIST:
                    mApplication.getCurrentActivity().updateActivityUI();
                    break;
            }
        }
    }


    private static class WorkerHandler extends Handler {
        private WeakReference<LauncherController> mLauncherController;
        private LauncherApplication mApplication;
        private Context mContext;
        private Message mMessage;

        public WorkerHandler(LauncherApplication application, Looper looper) {
            super(looper);
            if (mApplication == null) {
                mApplication = application;
            }
            if (mLauncherController == null) {
                mLauncherController = new WeakReference<LauncherController>(mApplication.getController());
            }
            if (mContext == null) {
                mContext = mApplication.getApplicationContext();
            }
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_APP_LIST:
                    List<LauncherAppInfo> appList = new ArrayList<>();
                    AppLoaderFactory appLoaderFactory = new AppLoaderFactory(mContext);
                    appList = appLoaderFactory.loadAppList(msg.arg1);
                    mLauncherController.get().setAppList(appList);

                    mApplication.getUIHandler().removeMessages(MSG_UPDATE_APP_LIST);
                    mMessage = Message.obtain();
                    mMessage.what = MSG_UPDATE_APP_LIST;
                    mApplication.getUIHandler().sendMessage(mMessage);
                    break;

                case MSG_ON_ITEM_CLICK:
                    switch (mApplication.getCurrentActivity().getActivityFlag()) {
                        case FLAG_MAIN_ACTIVITY:
                            try {
                                LauncherAppInfo item = (LauncherAppInfo) msg.obj;
                                Intent intent = item.getLaunchIntentForPackage();
                                mContext.startActivity(intent);
                            } catch (NullPointerException e) {
                                Toast.makeText(mContext, "Intent is null, can't startActivity!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                    break;

                case MSG_ON_ITEM_LONG_CLICK:
                    switch (mApplication.getCurrentActivity().getActivityFlag()) {
                        case FLAG_MAIN_ACTIVITY:
                            LauncherAppInfo item = (LauncherAppInfo) msg.obj;
                            mLauncherController.get().getAppList().remove(item);

                            mApplication.getUIHandler().removeMessages(MSG_UPDATE_APP_LIST);
                            mMessage = Message.obtain();
                            mMessage.what = MSG_UPDATE_APP_LIST;
                            mApplication.getUIHandler().sendMessage(mMessage);
                            Toast.makeText(mContext, "Remove " + item.getAppName(), Toast.LENGTH_SHORT).show();
                            break;
                    }
                    break;
            }
        }
    }
}
