package com.epam.lab.injector;

import com.epam.lab.injector.cache.Cache;
import com.epam.lab.injector.cache.CacheInitializer;
import com.epam.lab.injector.cache.annotations.InjectCache;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kate on 12.10.2017.
 */
public class Injector {

    public static void inject(Object target, CacheInitializer cacheHolder) {
        Set<Field> fieldSet = new HashSet<>();
        fieldSet.addAll(Arrays.asList(target.getClass().getDeclaredFields()));
        for (Field f : target.getClass().getDeclaredFields()) {
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
