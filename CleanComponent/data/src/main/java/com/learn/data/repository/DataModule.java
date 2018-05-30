package com.learn.data.repository;

import android.content.Context;

import com.learn.data.cache.DiskCacheManager;
import com.learn.data.cache.GlobalManager;
import com.learn.data.common.DiskLruCacheUtils;

/**
 * The type Data module.
 */
public class DataModule {

    private Context appContext;

    /**
     * Gets instance with inner static class way
     *
     * @return the instance
     */
    public static DataModule getInstance() {
        return Holder.INSTANCE;
    }

    private DataModule() {
    }

    private static class Holder {
        private static final DataModule INSTANCE = new DataModule();
    }

    /**
     * Install some data modules
     *
     * @param context the context
     */
    public void install(Context context) {
        appContext = context.getApplicationContext();
        // init cache
        GlobalManager.getInstance().init();

        DiskCacheManager.getInstance().init(context);
        DiskLruCacheUtils.setDiskLruCache(DiskCacheManager.getInstance().getDiskLruCache(context));
    }

    /**
     * Uninstall data modules
     */
    public void uninstall() {
        GlobalManager.getInstance().release();
        DiskCacheManager.getInstance().release();
    }
}
