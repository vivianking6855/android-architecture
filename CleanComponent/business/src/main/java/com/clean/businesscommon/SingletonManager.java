package com.clean.businesscommon;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Singleton manager.
 * you need to register instance before you get it.
 */
public final class SingletonManager {

    /**
     * objectMap of all singleton service
     */
    private static Map<String, Object> objectMap = new HashMap<>();

    /**
     * The constant DATA_SERVICE.
     */
    public static final String DATA_SERVICE = "data_service";

    /**
     * Gets instance with inner static class way
     *
     * @return the instance
     */
    public static SingletonManager getInstance() {
        return Holder.INSTANCE;
    }

    private SingletonManager() {
    }

    private static class Holder {
        private static final SingletonManager INSTANCE = new SingletonManager();
    }


    /**
     * The interface Log type.
     */
    @StringDef({DATA_SERVICE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceType {
    }

    /**
     * Register service.
     *
     * @param key      the key
     * @param instance the instance
     */
    public static void registerService(@ServiceType String key, Object instance) {
        if (!objectMap.containsKey(key)) {
            objectMap.put(key, instance);
        }
    }

    /**
     * Gets service.
     *
     * @param key the key
     * @return the service
     */
    public static Object getService(@ServiceType String key) {
        return objectMap.get(key);
    }
}
