package com.example.chilin_wang.launcherexample.view;

import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chilin_wang.launcherexample.LauncherApplication;
import com.example.chilin_wang.launcherexample.R;
import com.example.chilin_wang.launcherexample.controller.LauncherController;
import com.example.chilin_wang.launcherexample.model.LauncherAppInfo;

/**
 * Created by Chilin_Wang on 2017/9/5.
 */

public class LauncherAdapter extends RecyclerView.Adapter<LauncherViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {
    private LauncherApplication mApplication;
    private LauncherController mLauncherController;

    public LauncherAdapter(LauncherApplication application) {
        mApplication = application;
        mLauncherController = mApplication.getController();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public LauncherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_launcher, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new LauncherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LauncherViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.itemView.setTag(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mLauncherController.getAppList().size();
    }

    @Override
    public void onClick(View view) {
        mLauncherController.sendOnItemClickMessage((LauncherAppInfo) view.getTag());
    }

    @Override
    public boolean onLongClick(View view) {
        mLauncherController.sendOnItemLongClickMessage((LauncherAppInfo) view.getTag());
        return true;
    }

    public LauncherAppInfo getItem(int position) {
        return mLauncherController.getAppList().get(position);
    }
}
