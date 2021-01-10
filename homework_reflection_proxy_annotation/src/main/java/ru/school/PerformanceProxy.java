package ru.school;

import java.lang.reflect.Proxy;

public class PerformanceProxy implements Calculator{
    Calculator calculator;

    public PerformanceProxy(Calculator calculator) {
        this.calculator = (Calculator) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Calculator.class},
                new CustomInvocationHandler(calculator)
        );
    }


    @Override
    public int calc(int number) {
        return calculator.calc(number);
    }
}
