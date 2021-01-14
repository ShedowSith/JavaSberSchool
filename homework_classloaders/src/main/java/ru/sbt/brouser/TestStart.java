package ru.sbt.brouser;

import ru.sbt.plugin.Plugin;
import ru.sbt.plugins.PrintPlugin;

public class TestStart {
    public static void main(String[] args) {
        PluginManager pluginManager = new PluginManager("C:\\temp");
        try {
            Plugin plugin1 = new PrintPlugin();
            plugin1.doUseFull();

            Plugin plugin = pluginManager.load("print-plugin1", "ru.sbt.plugins.PrintPlugin");
            plugin.doUseFull();

            Plugin plugin2 = pluginManager.load("print-plugin2", "ru.sbt.plugins.PrintPlugin");
            plugin2.doUseFull();

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }

    }
}
