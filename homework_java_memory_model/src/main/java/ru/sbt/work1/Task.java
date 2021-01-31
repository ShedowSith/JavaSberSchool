package ru.sbt.work1;

import ru.sbt.work1.exceptions.RuntimeException;
import ru.sbt.work1.proxy.ProxyCallable;

import java.util.concurrent.Callable;

public class Task<T> {
    private Callable<? extends T> callable;
    public Task(Callable<? extends T> callable){
        this.callable = new ProxyCallable(callable);
    }
    public synchronized T get() throws RuntimeException {
        T result = null;
        try {
            result = callable.call();
        } catch (Exception e) {
            throw new RuntimeException("Error method call");
        }
        return result;
    }
}
