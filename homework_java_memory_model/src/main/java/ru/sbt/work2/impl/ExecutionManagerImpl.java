package ru.sbt.work2.impl;

import ru.sbt.work2.api.Context;
import ru.sbt.work2.api.ExecutionManager;
import ru.sbt.work2.api.ThreadPool;

public class ExecutionManagerImpl implements ExecutionManager {
    private volatile ContextImpl context;
    private boolean isRunning;
    @Override
    public Context execute(Runnable callback, Runnable... tasks) {
        isRunning = true;
        context = new ContextImpl();
        ThreadPool threadPool = new FixedThreadPool(3, context);
        for (Runnable task: tasks){
            threadPool.execute(task);
        }
        threadPool.start();
        new Thread(() -> {
            while (isRunning){
                if (context.isFinished()){
                    callback.run();
                    isRunning = false;
                }
            }
        }).start();

        return context;
    }
}
