package ru.sbt.refactoring;

public class Field {
    private int a;
    private int b;
    public Field(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
