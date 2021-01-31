package ru.sbt.work2.api;

public interface ThreadPool {
    void start();
    void execute(Runnable runnable);
}
