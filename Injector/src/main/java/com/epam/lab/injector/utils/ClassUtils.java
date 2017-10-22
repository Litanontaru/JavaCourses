package com.epam.lab.injector.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Kate on 13.10.2017.
 */
public class ClassUtils {
    private static final char PACKAGE_DELIMITER = '.';
    private static final char DIRECTORY_DELIMITER = '/';
    private static final String CLASS_FILE_EXTENSION = ".class";

    public static List<Class> getClassesFromPackage(String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace(PACKAGE_DELIMITER, DIRECTORY_DELIMITER);
        Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + PACKAGE_DELIMITER + file.getName()));
            } else if (file.getName().endsWith(CLASS_FILE_EXTENSION)) {
                String className = file.getName().substring(0, file.getName().length() - CLASS_FILE_EXTENSION.length());
                try {
                    classes.add(Class.forName(packageName + PACKAGE_DELIMITER + className));
                } catch (ClassNotFoundException e) {
                    throw new ClassNotFoundException("Cache classes aren't found in package " + packageName);
                }
            }
        }
        return classes;
    }
}
