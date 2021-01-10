package ru.school;


import org.junit.Test;

public class BeanUtilsTest{
    @Test
    public void assignTest(){
        TestBeanUtilsClass1 toClass = new TestBeanUtilsClass1();
        TestBeanUtilsClass1 fromClass = new TestBeanUtilsClass1();
        TestBeanUtilsClass2 to2Class = new TestBeanUtilsClass2();
        fromClass.setParamInt1(55);
        fromClass.setParamString2("Тест");
        System.out.println("toClass getInt = "+toClass.getParamInt1()+" , getString = "+toClass.getParamString2());
        BeanUtils.assign(toClass, fromClass);
        System.out.println("toClass getInt = "+toClass.getParamInt1()+" , getString = "+toClass.getParamString2());
        System.out.println("---------------------------");
        System.out.println("to2Class getDouble = "+to2Class.getParamDouble1()+" , getString = "+to2Class.getParamString2());
        BeanUtils.assign(to2Class, fromClass);
        System.out.println("to2Class getDouble = "+to2Class.getParamDouble1()+" , getString = "+to2Class.getParamString2());

    }
}