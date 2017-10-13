package com.epam.lab.injector.cacheusers;

import com.epam.lab.injector.cache.Cache;
import com.epam.lab.injector.cache.annotations.InjectCache;

/**
 * Created by Kate on 13.10.2017.
 */
public class SuperClass {

    @InjectCache(name="FILE_CACHE")
    private Cache cache;

    public String getValue(int key){
        return cache.get(key);
    }

}
