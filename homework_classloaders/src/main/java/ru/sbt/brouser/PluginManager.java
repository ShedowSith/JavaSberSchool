package ru.sbt.brouser;

import ru.sbt.plugin.Plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {
    private final String pluginRootDirectory;
    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;

    }
    public Plugin load(String pluginName, String pluginClassName) throws ClassNotFoundException {
        File pluginDir = new File(pluginRootDirectory);
        Plugin plugin = null;
        File[] jars = pluginDir.listFiles(file -> file.isFile() && file.getName().endsWith(".jar"));
        for (File jar : jars) {
            if(jar.getName().equals(pluginName+".jar")){
                try {
                    URL jarURL = jar.toURI().toURL();
                    PluginLoader classLoader = new PluginLoader(new URL[]{jarURL}, ClassLoader.getSystemClassLoader());
                    Class<?> pluginClass = classLoader.loadClass(pluginClassName);
                    plugin = (Plugin) pluginClass.newInstance();

                } catch (MalformedURLException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }

        }
        if(plugin == null){
            throw new ClassNotFoundException("Класс не найден.");
        }
        return plugin;
    }
}
