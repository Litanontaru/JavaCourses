package com.epam.lab.injector.cacheusers;

import com.epam.lab.injector.cache.Cache;
import com.epam.lab.injector.cache.CacheInitializer;
import com.epam.lab.injector.cache.annotations.InjectCache;

import java.util.Random;

/**
 * Created by Kate on 13.10.2017.
 */
public class CacheWriter {
    @InjectCache(name = "MAP_CACHE")
    private Cache cache;

    public void write(String txt) {
        Random random = new Random();
        int key = random.nextInt(CacheInitializer.MAX_CACHE_KEY);
        cache.put(key, txt);
    }

    public void addTrashToCache() {
        Random random = new Random();
        int limit = random.nextInt(CacheInitializer.MAX_CACHE_KEY);
        for (int i = 0; i < limit; i++) {
            write("Trash from " + this.getClass().getSimpleName() + ". " + random.nextInt());
        }
    }
}
