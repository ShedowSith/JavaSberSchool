package ru.school;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    private final Object delegate;

    public CustomInvocationHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(!method.isAnnotationPresent(Metric.class)) return method.invoke(delegate,args);
        long startTime = System.nanoTime();
        Object result = method.invoke(delegate, args);
        long endTime = System.nanoTime();
        System.out.println("Время работы метода: "+(endTime-startTime)+" (в наносек)");
        return result;
    }
}
