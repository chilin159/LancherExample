package com.example.chilin_wang.launcherexample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.chilin_wang.launcherexample.LauncherApplication;
import com.example.chilin_wang.launcherexample.R;
import com.example.chilin_wang.launcherexample.controller.LauncherController;
import com.example.chilin_wang.launcherexample.model.AppLoaderFactory;

public class MainActivity extends AppCompatActivity implements IActivity {

    private RecyclerView mRecyclerView;
    private LauncherAdapter mLauncherAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private LauncherApplication mApplication;
    private LauncherController mLauncherController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApplication = (LauncherApplication) getApplication();
        mApplication.setCurrentActivity(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLauncherController = mApplication.getController();

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mLauncherAdapter = new LauncherAdapter(mApplication);
        mRecyclerView.setAdapter(mLauncherAdapter);
        mLauncherController.loadAppList(AppLoaderFactory.LOAD_APP_LIST_BY_PACKAGEMANAGER);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication.clearCurrentActivity();
    }

    @Override
    public void updateActivityUI() {
        mLauncherAdapter.notifyDataSetChanged();
    }

    @Override
    public int getActivityFlag() {
        return LauncherApplication.FLAG_MAIN_ACTIVITY;
    }
}
