package ru.school;

public class CalculatorImpl implements Calculator {
    @Override
    public int calc(int number) {
        if (number==0) return 1;
        return number * calc(--number);
    }
}
