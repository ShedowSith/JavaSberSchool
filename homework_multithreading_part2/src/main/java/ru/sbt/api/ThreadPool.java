package ru.sbt.api;

public interface ThreadPool {
    void start();
    void execute(Runnable runnable);
}
