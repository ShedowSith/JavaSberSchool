package ru.school;

import org.junit.Test;

public class CalculatorImplTest {
    @Test
    public void CalculatorImplT(){
        System.out.println(new CalculatorImpl().calc(3));
    }
    @Test
    public void CalculatorImplProxyTest(){
        Calculator calculator=new PerformanceProxy(new CalculatorImpl());
        System.out.println(calculator.calc(3));

    }
}