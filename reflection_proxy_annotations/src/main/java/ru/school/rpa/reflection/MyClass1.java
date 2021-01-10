package ru.school.rpa.reflection;

public class MyClass1 {
    public int count = 8;
    private String name = "MyClassName";

    public int calc(int n){
        return n*n;
    }
    private void method2(){
        System.out.println("method2() класса "+this.getClass().getName());
    }
}
