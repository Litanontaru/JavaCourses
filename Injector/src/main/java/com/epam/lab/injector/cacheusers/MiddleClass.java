package com.epam.lab.injector.cacheusers;

import com.epam.lab.injector.cache.Cache;
import com.epam.lab.injector.cache.annotations.InjectCache;

/**
 * Created by Kate on 13.10.2017.
 */
public class MiddleClass extends SuperClass {

    @InjectCache(name = "MAP_CACHE")
    private Cache cache;

    public String readValue(int key) {
        String value = cache.get(key);
        if (value == null)
            return "No value found";

        return value;
    }

}
