package com.epam.lab.injector.cacheusers;

import com.epam.lab.injector.cache.Cache;
import com.epam.lab.injector.cache.CacheInitializer;
import com.epam.lab.injector.cache.annotations.CacheName;
import com.epam.lab.injector.cache.annotations.InjectCache;

import java.util.Random;

/**
 * Created by Kate on 13.10.2017.
 */
public class SubClass extends MiddleClass {
    @InjectCache(name = CacheName.TREE_CACHE)
    public static Cache cache;

    public void printHierarchy() {
        Random random = new Random();
        int key = random.nextInt(CacheInitializer.MAX_CACHE_KEY);

        System.out.println("Super - " + getValue(key));
        System.out.println("Middle - " + readValue(key));
        System.out.println("Sub - " + cache.get(key));
    }
}
