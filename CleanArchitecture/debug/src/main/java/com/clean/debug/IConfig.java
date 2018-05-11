package com.clean.debug;

import android.util.Log;

/**
 * The interface Config.
 */
public interface IConfig {
    /**
     * if false all debug method will not work
     *
     * @return the boolean
     */
    boolean enable();

    /**
     * {@link }
     * if DEBUG_HIGH, you need set property 'adb shell setprop log.tag.codedebug D'
     *
     * @return the boolean
     */
    boolean debugLevel();
}
