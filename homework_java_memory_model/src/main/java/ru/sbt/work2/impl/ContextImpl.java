package ru.sbt.work2.impl;

import ru.sbt.work2.api.Context;

public class ContextImpl implements Context {
    private volatile int completedTaskCount;
    private volatile int failedTaskCount;
    private volatile int interruptedTaskCount;
    private volatile boolean interrupt = false;
    private volatile boolean finished = false;
    @Override
    public int getCompletedTaskCount() {
        return this.completedTaskCount;
    }

    @Override
    public int getFailedTaskCount() {
        return this.failedTaskCount;
    }

    @Override
    public int getInterruptedTaskCount() {
        return this.interruptedTaskCount;
    }

    @Override
    public void interrupt() {
        this.interrupt = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
    public void finished(){
        this.finished = true;
    }
    public void addCompletedTaskCount() {
        this.completedTaskCount ++ ;
    }

    public void addFailedTaskCount() {
        this.failedTaskCount ++;
    }

    public void addInterruptedTaskCount() {
        this.interruptedTaskCount ++;
    }
    public boolean isInterrupt(){
        return interrupt;
    }
}
