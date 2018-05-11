package com.clean.debug;

import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class DebugModule {
    private static IConfig sConfig;

    public static void install(IConfig config) {
        if (config == null) {
            sConfig = new DefaultConfig();
        } else {
            sConfig = config;
        }

        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return sConfig.enable() && sConfig.debugLevel();
            }
        });
    }

    public static void uninstall() {
        sConfig = null;
        Logger.clearLogAdapters();
    }

}
