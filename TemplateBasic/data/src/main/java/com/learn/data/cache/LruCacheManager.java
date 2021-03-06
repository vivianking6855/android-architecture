package com.learn.data.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.util.LruCache;

/**
 * Created by vivian on 2017/11/16.
 */

public class LruCacheManager {
    // lru cache
    private static LruCache<String, String> mLruStringCache;

    /**
     * Gets instance with inner static class way
     *
     * @return the instance
     */
    public static LruCacheManager getInstance() {
        return LruCacheManager.Holder.INSTANCE;
    }

    private LruCacheManager() {
    }

    private static class Holder {
        private static final LruCacheManager INSTANCE = new LruCacheManager();
    }

    /**
     * default size is 1/8 of available memory (K)
     * @param context context
     * @return default size
     */
    public static int getDefaultLruCacheSize(Context context) {
        // Get memory class of this device, exceeding this amount will throw an
        // OutOfMemory exception.
        final int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
        // Use 1/8th of the available memory for this memory cache.
        return 1024 * 1024 * memClass / 8;
    }

    /**
     * Gets disk lru cache.
     *
     * @param context the context
     * @return the disk lru cache
     */
    public LruCache<String, String> getLruStringCache(Context context) {
        if (mLruStringCache == null) {
            // create cache path
            mLruStringCache = new LruCache<String, String>(getDefaultLruCacheSize(context)) {
                @Override
                protected int sizeOf(String key, String value) {
                    // modify sizeof to evaluate image size, default is count
                    return key.length() + value.length();

                    // if bitmap ,you may overwrite it like this
                    // return bitmap.getByteCount() / 1024;
                }
            };
        }

        return mLruStringCache;
    }

}
