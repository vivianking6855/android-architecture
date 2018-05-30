package com.learn.data.cache;


/**
 * Created by vivian on 2017/11/13.
 * global variable manager,
 * light data cache and share
 * if you need large data cache,user LruCache or DiskCache
 */
public class GlobalManager {
    public int mDataCount;

    /**
     * Gets instance with inner static class way
     *
     * @return the instance
     */
    public static GlobalManager getInstance() {
        return GlobalManager.Holder.INSTANCE;
    }

    private GlobalManager() {
    }

    private static class Holder {
        private static final GlobalManager INSTANCE = new GlobalManager();
    }

    public void init(){

    }

    public void release(){

    }
}
