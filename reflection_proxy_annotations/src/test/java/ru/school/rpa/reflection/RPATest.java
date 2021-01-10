package ru.school.rpa.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class RPATest {
    @Test
    public void reflectionTest() throws IllegalAccessException, InvocationTargetException {
        Method[] methods = MyClass1.class.getDeclaredMethods();
        for (Method method: methods){
            System.out.println("Method = " + method.getName());
        }
        try {
            Method m1 = MyClass1.class.getDeclaredMethod("method2", null);
            m1.setAccessible(true);
            m1.invoke(new MyClass1(), null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Field[] fields = MyClass1.class.getDeclaredFields();
        for (Field field: fields){
            System.out.println("Поле = "+ field.getName());
        }



    }
    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get")) return false;
        if(method.getParameterTypes().length != 0) return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }
    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }
}