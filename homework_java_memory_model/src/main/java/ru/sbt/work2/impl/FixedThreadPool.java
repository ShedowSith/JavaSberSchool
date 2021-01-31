package ru.sbt.work2.impl;

import ru.sbt.work2.api.ThreadPool;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


public class FixedThreadPool implements ThreadPool {
    private int count;
    private ContextImpl context;
    private ArrayDeque<Runnable> tasks = new ArrayDeque<>();
    private List<Thread> treads = new ArrayList<>();

    public FixedThreadPool(int count, ContextImpl context) {
        this.count = count;
        this.context = context;
    }

    @Override
    public void start() {
        for (int i = 0; i < count; i++) {
            treads.add(new Thread(() -> {
                while (tasks.size()!=0){
                    if (context.isInterrupt()){
                        getTask();
                        context.addInterruptedTaskCount();
                        continue;
                    }
                    Runnable task = getTask();
                    if (task != null) {
                        try {
                            task.run();
                            context.addCompletedTaskCount();
                        }catch (Exception e){
                            context.addFailedTaskCount();
                        }

                    }
                }

            }));
        }
        for (Thread thread: treads){
            thread.start();
        }
        new Thread(()->{
            treads.forEach((t)-> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            context.finished();
        }).start();
    }


    private synchronized Runnable getTask(){
        return tasks.poll();
    }

    @Override
    public void execute(Runnable runnable) {
        tasks.add(runnable);
    }

}
