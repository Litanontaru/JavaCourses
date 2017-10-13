package com.epam.lab.injector;

import com.epam.lab.injector.cache.CacheInitializer;
import com.epam.lab.injector.cacheusers.CacheWriter;
import com.epam.lab.injector.cacheusers.SubClass;
import com.epam.lab.injector.cacheusers.SuperClass;

/**
 * Created by Kate on 13.10.2017.
 */
public class Worker {
    private static final String CACHE_PACKAGE_NAME = "com.epam.lab.injector.cache";

    public static void checkInjectorWork() {
        CacheInitializer cacheHolder = new CacheInitializer(CACHE_PACKAGE_NAME);
        Injector injector = new Injector(cacheHolder);

        CacheWriter writer = new CacheWriter();
        injector.inject(writer);
        writer.addTrashToCache();

        System.out.println("----Class with cache----");
        SuperClass cacheReader = new SuperClass();
        injector.inject(cacheReader);
        System.out.println(cacheReader.getClass().getSimpleName() + ": " + cacheReader.getValue(0));

        System.out.println("----Hierarchy of classes which using different caches----");
        SubClass allCacheReader = new SubClass();
        injector.inject(allCacheReader);
        allCacheReader.printHierarchy();
    }
}
