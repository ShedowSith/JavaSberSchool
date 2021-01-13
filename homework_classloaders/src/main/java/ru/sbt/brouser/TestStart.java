package ru.sbt.brouser;

import ru.sbt.plugin.Plugin;

public class TestStart {
    public static void main(String[] args) {
        PluginManager pluginManager = new PluginManager("C:\\temp");
        try {
            Plugin plugin = pluginManager.load("print-plugin1", "ru.sbt.plugins.PrintPlugin");
            Plugin plugin2 = pluginManager.load("print-plugin2", "ru.sbt.plugins.PrintPlugin");
            plugin.doUseFull();
            plugin2.doUseFull();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }

    }
}
