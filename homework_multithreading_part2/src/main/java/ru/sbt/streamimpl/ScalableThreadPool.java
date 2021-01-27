package ru.sbt.streamimpl;
import ru.sbt.api.ThreadPool;

import java.util.ArrayDeque;

public class ScalableThreadPool implements ThreadPool {
    private int min;
    private int max;
    private ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    private volatile boolean isRunning;
    private volatile int count;
    private Thread caretaker;

    public ScalableThreadPool(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public void stop(){
        isRunning = false;
    }
    @Override
    public void start() {
        isRunning = true;
        for (int i = 0; i < min; i++) {
            new Thread(() -> {
                while (isRunning){
                    Runnable task = getTask();
                    if (task != null) {
                        task.run();
                    }
                }
            }).start();
        }
        caretaker = new Thread(() -> {
            while (isRunning){
                if(count<(max-min)){
                    Runnable task = getTask();
                    if (task != null) {
                        count++;
                        new Thread(() -> {
                            task.run();
                            count--;
                        }).start();
                    }
                }
            }
        });
        caretaker.start();
    }

    private synchronized Runnable getTask(){
        return tasks.poll();
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.add(runnable);
    }
}
