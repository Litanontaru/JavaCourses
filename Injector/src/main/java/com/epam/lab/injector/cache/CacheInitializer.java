package com.epam.lab.injector.cache;

import com.epam.lab.injector.cache.annotations.CacheDeclaration;
import com.epam.lab.injector.utils.ClassUtils;

import java.util.*;

/**
 * Created by Kate on 13.10.2017.
 */
public class CacheInitializer {
    public static final int MAX_CACHE_KEY = 20;
    private static final Class CACHE_ANNOTATION = CacheDeclaration.class;
    private Map<String, Cache> caches;

    public CacheInitializer(String packageName) {
        caches = new HashMap<>();
        findCacheClasses(packageName);
        initializeCacheInstances();
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
        Random random = new Random();

        for (Map.Entry<String, Cache> entry : caches.entrySet()) {
            for (int i = 0; i < MAX_CACHE_KEY; i++) {
                entry.getValue().put(i, "Timestamp - "
                        + Calendar.getInstance().getTimeInMillis()
                        + ". String from " + entry.getKey() + " "
                        + random.nextInt());
            }
        }
    }

    public Cache getCache(String cacheName) {
        return caches.get(cacheName);
    }

}
