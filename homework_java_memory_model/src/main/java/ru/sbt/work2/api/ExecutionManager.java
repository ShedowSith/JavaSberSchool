package ru.sbt.work2.api;

public interface ExecutionManager {
    Context execute(Runnable callback, Runnable... tasks);
}
