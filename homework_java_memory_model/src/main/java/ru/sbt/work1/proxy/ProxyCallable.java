package ru.sbt.work1.proxy;

import java.lang.reflect.Proxy;
import java.util.concurrent.Callable;

public class ProxyCallable<T> implements Callable {
    private Callable<? extends T> callable;

    public ProxyCallable(Callable<? extends T> callable) {
        this.callable = (Callable<? extends T>) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Callable.class},
                new CustomInvocationHandler(callable)
        );
    }

    @Override
    public Object call() throws Exception {
        return callable.call();
    }
}
