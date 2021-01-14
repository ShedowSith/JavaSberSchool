package ru.sbt.brouser;

import java.net.URL;
import java.net.URLClassLoader;

public class PluginLoader extends URLClassLoader {
    public PluginLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> loadedClass = findLoadedClass(name);
        if (loadedClass == null) {
            try {
                loadedClass = findClass(name);
            } catch (ClassNotFoundException e) {
                loadedClass = super.loadClass(name);
            }
        }
        return loadedClass;
    }
}
