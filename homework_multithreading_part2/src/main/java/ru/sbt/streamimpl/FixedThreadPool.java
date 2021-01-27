package ru.sbt.streamimpl;
import ru.sbt.api.ThreadPool;

import java.util.ArrayDeque;


public class FixedThreadPool implements ThreadPool {
    private int count;
    private ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    private volatile boolean isRunning;

    public FixedThreadPool(int count) {
        this.count = count;
    }

    public void stop(){
        isRunning = false;
    }
    @Override
    public void start() {
        isRunning = true;
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                while (isRunning){
                    Runnable task = getTask();
                    if (task != null) {
                        task.run();
                    }
                }
            }).start();
        }
    }

    private synchronized Runnable getTask(){
        return tasks.poll();
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.add(runnable);
    }

}
