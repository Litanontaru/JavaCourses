package com.epam.lab.injector.cache;

import com.epam.lab.injector.cache.annotations.CacheDeclaration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kate on 09.10.2017.
 */
@CacheDeclaration(name = "MAP_CACHE")
public class MapCache implements Cache {
    private Map<Integer, String> cache = new HashMap<Integer, String>();

    @Override
    public void put(Integer key, String data) {
        cache.put(key, data);
    }

    @Override
    public String get(Integer key) {
        return cache.get(key);
    }
}
