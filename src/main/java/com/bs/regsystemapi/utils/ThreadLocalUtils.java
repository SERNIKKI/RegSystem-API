package com.bs.regsystemapi.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ThreadLocal工具类
 * 本工具类中ThreadLocal存储的是 Map<String, Object>
 * @Date 2022/4/26 17:26
 */
public class ThreadLocalUtils {

    private static final ThreadLocal<Map<String, Object>> RESOURCES = new ThreadLocal() {
        protected Map<String, Object> initialValue() {
            return new HashMap<String, Object>(4);
        }
    };

    public static void removeResources() {
        if (Objects.nonNull(RESOURCES)) {
            RESOURCES.remove();
        }
    }

    public static Map<String , Object> getResources() {
        return RESOURCES.get();
    }

    public static void set(String  key, Object value) {
        Objects.requireNonNull(key, "key cannot be null");
        getResources().put(key, value);
    }

    public static Object get(Object key) {
        return getResources().get(key);
    }

    public static Object remove(Object key) {
        return getResources().remove(key);
    }

}
