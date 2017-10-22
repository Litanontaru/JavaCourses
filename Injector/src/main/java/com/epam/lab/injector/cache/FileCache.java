package com.epam.lab.injector.cache;

import com.epam.lab.injector.cache.annotations.CacheDeclaration;
import com.epam.lab.injector.cache.annotations.CacheName;

import java.io.*;

/**
 * Created by Kate on 09.10.2017.
 */
@CacheDeclaration(name = CacheName.FILE_CACHE)
public class FileCache implements Cache {
    private static final String KEY_VALUE_DELIMITER = "=";
    private static final int LIMIT_FOR_SPLIT = 2;

    private File cacheFile;

    public FileCache() {
        try {
            cacheFile = File.createTempFile("cache", null);
        } catch (IOException e) {
            throw new CacheException("Can't initialize cache", e);
        }
    }

    @Override
    public void put(Integer key, String data) {
        try (FileWriter fw = new FileWriter(cacheFile, true);
             BufferedWriter writer = new BufferedWriter(fw);
             PrintWriter in = new PrintWriter(writer)) {
            in.println(key + KEY_VALUE_DELIMITER + data);
        } catch (IOException e) {
            throw new CacheException("Can't write data to cache", e);
        }
    }

    @Override
    public String get(Integer key) {
        String keyString = String.valueOf(key);
        try (BufferedReader reader = new BufferedReader(new FileReader(cacheFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] keyValue = currentLine.split(KEY_VALUE_DELIMITER, LIMIT_FOR_SPLIT);
                if (keyValue.length == LIMIT_FOR_SPLIT && keyValue[0].equals(keyString)) {
                    return keyValue[1];
                }
            }
        } catch (IOException e) {
            throw new CacheException("Can't read data from cache", e);
        }
        return null;
    }

}
