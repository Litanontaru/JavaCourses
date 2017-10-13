package com.epam.lab.injector.cache;

/**
 * Created by Kate on 09.10.2017.
 */
public interface Cache {
    void put(Integer key, String data);
    String get(Integer key);
}
