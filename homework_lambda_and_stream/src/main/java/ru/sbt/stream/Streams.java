package ru.sbt.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.function.Function;

public class Streams<T> {
    private List<T> list;

    private void setList(List<T> list){
        this.list = new ArrayList<>(list);
    };

    public static <T> Streams<T> of(List<T> list){
        Streams streams = new Streams();
        streams.setList(list);
        return streams;
    }
    public Streams<T> filter(Predicate<? super T> predicate){
        List<T> result = new ArrayList<>();
        for(T a : this.list)
            if(predicate.test(a)) {
                result.add(a);
            }
        this.setList(result);
        return this;
    }
    public Streams<T> transform(UnaryOperator<T> operator){
        List<T> result = new ArrayList<>();
        for(T a : this.list){
            result.add(operator.apply(a));
        }
        this.setList(result);
        return this;
    }
    public <K, Y> Map<K,Y> toMap(Function<? super T, ? extends K> key,Function<? super T, ? extends Y> value){
        Map<K,Y> map = new HashMap<>();
        for(T a : this.list){
            map.put(key.apply(a), value.apply(a));
        }
        return map;
    }
}
