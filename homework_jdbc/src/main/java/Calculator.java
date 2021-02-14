import api.Cacheable;
import service.H2DB;
import service.Source;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    @Cacheable(H2DB.class)
    public List<Integer> fibonachi(int n) {
        Method method ;
        try {
            method = this.getClass().getDeclaredMethod("fibonachi", int.class);
            if(!method.isAnnotationPresent(Cacheable.class)){
                return calcFib(n);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Метод не существует." + e);
        }
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        Source source = null;
        try {
            source = cacheable.value().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        List list = source.getListFibonachi(n);
        if (list == null) {
            list = calcFib(n);
            source.saveListFibonachi(list);
        }
        return list;
    }
    private List<Integer> calcFib(int n) {
        List<Integer> list = new ArrayList();
        int n0 = 1;
        int n1 = 1;
        list.add(n0);
        if (n <= 1){
            return list;
        }
        list.add(n1);
        for (int i = 3; i<=n; i++){
            int n2 = n0+n1;
            list.add(n2);
            n0 = n1;
            n1 = n2;
        }

        return list;
    }
}

