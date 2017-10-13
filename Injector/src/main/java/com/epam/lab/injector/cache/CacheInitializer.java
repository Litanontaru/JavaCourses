package com.epam.lab.injector.cache;

import com.epam.lab.injector.cache.annotations.CacheDeclaration;
import com.epam.lab.injector.utils.ClassUtils;

import java.util.*;

/**
 * Created by Kate on 13.10.2017.
 */
public class CacheInitializer {
    private static final Class CACHE_ANNOTATION = CacheDeclaration.class;
    private Map<String, Cache> caches;

    public CacheInitializer(String packageName) {
        caches = new HashMap<>();
        findCacheClasses(packageName);
    }

    private void findCacheClasses(String packageName) {
        List<Class> classes = ClassUtils.getClassesFromPackage(packageName);
        for (Class c : classes) {
            if (c.isAnnotationPresent(CACHE_ANNOTATION)) {
                CacheDeclaration annotation = (CacheDeclaration) c.getAnnotation(CACHE_ANNOTATION);
                try {
                    caches.put(annotation.name(), (Cache) c.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void initializeCacheInstances() {
        final int MAX_CACHE_KEY = 20;
        Random random = new Random();

        for (Cache cache : caches.values()) {
            for (int i = 0; i < MAX_CACHE_KEY; i++) {
                cache.put(i, "Timestamp - " + Calendar.getInstance().getTimeInMillis());
            }
        }
    }

    public Cache getCache(String cacheName) {
        return caches.get(cacheName);
    }

}
