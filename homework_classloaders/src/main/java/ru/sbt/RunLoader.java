package ru.sbt;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class RunLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        EncryptedClassLoader classLoader = new EncryptedClassLoader("key", new File("C:\\temp"), ClassLoader.getSystemClassLoader());
        Class<?> module = classLoader.loadClass("ModuleImpl");

        Object object = module.newInstance();
        object.getClass().getDeclaredMethod("print").invoke(object);

    }
}
