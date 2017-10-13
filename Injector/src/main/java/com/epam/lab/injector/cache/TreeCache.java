package com.epam.lab.injector.cache;

import com.epam.lab.injector.cache.annotations.CacheDeclaration;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Kate on 09.10.2017.
 */
@CacheDeclaration(name = "TREE_CACHE")
public class TreeCache implements Cache {
    private Map<Integer, String> databaseCache = new TreeMap<>();

    @Override
    public void put(Integer key, String data) {
        databaseCache.put(key, data);
    }

    @Override
    public String get(Integer key) {
        return databaseCache.get(key);
    }
}
