package com.example.chilin_wang.launcherexample.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chilin_wang.launcherexample.R;
import com.example.chilin_wang.launcherexample.model.LauncherAppInfo;

/**
 * Created by Chilin_Wang on 2017/9/5.
 */

public class LauncherViewHolder extends RecyclerView.ViewHolder {
    private ImageView mAppIcon;
    private TextView mAppName;
    private TextView mPackageName;

    public LauncherViewHolder(View itemView) {
        super(itemView);
        mAppIcon = (ImageView) itemView.findViewById(R.id.app_icon);
        mAppName = (TextView) itemView.findViewById(R.id.app_name);
        mPackageName = (TextView) itemView.findViewById(R.id.package_name);
    }

    public void bind(LauncherAppInfo appInfo) {
        mAppIcon.setImageDrawable(appInfo.getIcon());
        mAppName.setText(appInfo.getAppName());
        mPackageName.setText(appInfo.getPackageName());
    }
}
