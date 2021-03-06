package com.learn.data.repository;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.learn.data.entity.ApkEntity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.learn.data.common.Const.TAG_DATA;

public class ApkRepo {

    public static List<ApkEntity> getLocalAppList(PackageManager packageManager) {
        List<ApkEntity> appList = new ArrayList<ApkEntity>();
        try {
            List<PackageInfo> packageList = packageManager.getInstalledPackages(0);
            for (PackageInfo packageInfo : packageList) {

                // pass system app
                if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                    continue;
                }

                // pass no icon app
                if (packageInfo.applicationInfo.loadIcon(packageManager) == null) {
                    continue;
                }

                // set each package entity
                ApkEntity appInfo = new ApkEntity(packageInfo.packageName,
                        packageInfo.applicationInfo.loadIcon(packageManager));
                appList.add(appInfo);
            }
        } catch (Exception e) {
            Logger.w("getLocalAppList ex", e);
        }
        return appList;
    }

}
