package com.epam.lab.injector;

import com.epam.lab.injector.cache.Cache;
import com.epam.lab.injector.cache.CacheInitializer;
import com.epam.lab.injector.cache.annotations.InjectCache;

import java.lang.reflect.Field;

/**
 * Created by Kate on 12.10.2017.
 */
public class Injector {
    private CacheInitializer cacheHolder;

    public Injector(CacheInitializer cacheHolder) {
        this.cacheHolder = cacheHolder;
    }

    public void inject(Object target) {
        injectHierarchy(target, target.getClass());
    }

    private void injectHierarchy(Object target, Class targetClass) {
        initializeFields(target, targetClass.getDeclaredFields());
        initializeFields(target, targetClass.getFields());
        if (targetClass.getSuperclass() != Object.class) {
            injectHierarchy(target, targetClass.getSuperclass());
        }
    }

    private void initializeFields(Object target, Field[] fields) {
        for (Field f : fields) {
            if (f.isAnnotationPresent(InjectCache.class)) {
                InjectCache annotation = f.getAnnotation(InjectCache.class);
                String cacheName = annotation.name();
                Cache cache = cacheHolder.getCache(cacheName);
                f.setAccessible(true);
                try {
                    f.set(target, cache);
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
