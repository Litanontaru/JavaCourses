package com.epam.lab.injector;

import com.epam.lab.injector.cache.Cache;
import com.epam.lab.injector.cache.annotations.CacheDeclaration;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * Created by Kate on 12.10.2017.
 */
public class Injector {
    private static final Class CACHE_ANNOTATION = CacheDeclaration.class;
    private String nameOfCachePackage;
    private Map<String,Class<? extends Cache>> caches;

    public Injector(String nameOfCachePackage){
    }
/*
    private void findCaches(String nameOfCachePackage){

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = nameOfCachePackage.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        caches = new HashMap<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }
    private List findClasses(File directory, String packageName) {
        List classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }*/
}
