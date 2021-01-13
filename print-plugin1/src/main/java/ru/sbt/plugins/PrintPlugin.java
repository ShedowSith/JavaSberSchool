package ru.sbt.plugins;

import ru.sbt.plugin.Plugin;

public class PrintPlugin implements Plugin {
    @Override
    public void doUseFull() {
        System.out.println("Plugin 1 "+this.getClass().getName());
    }
}
