package com.clean.debug;

import android.util.Log;

import com.clean.BuildConfig;

public class DefaultConfig implements IConfig {
    /**
     * LOG_TAG for high level debug with property: 'adb shell setprop log.tag.codedebug D'
     */
    private static final String LOG_TAG = "codedebug";

    /**
     * The constant CODE_DEBUG.
     * you need set property 'adb shell setprop log.tag.codedebug D'
     * and restart application
     */
    public static final boolean DEBUG_HIGH = Log.isLoggable(LOG_TAG, Log.DEBUG);

    /**
     * The constant BuildConfig.DEBUG.
     */
    public static final boolean DEBUG_LOW = BuildConfig.DEBUG;

    @Override
    public boolean enable() {
        return true;
    }

    @Override
    public boolean debugLevel() {
        return DEBUG_LOW;
    }
}
