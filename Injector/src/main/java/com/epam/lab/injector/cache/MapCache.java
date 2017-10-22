package com.epam.lab.injector.cache;

import com.epam.lab.injector.cache.annotations.CacheDeclaration;
import com.epam.lab.injector.cache.annotations.CacheName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kate on 09.10.2017.
 */
@CacheDeclaration(name = CacheName.MAP_CACHE)
public class MapCache implements Cache {
    private Map<Integer, String> cache = new HashMap<>();

    @Override
    public void put(Integer key, String data) {
        cache.put(key, data);
    }

    @Override
    public String get(Integer key) {
        return cache.get(key);
    }
}
