package ru.sbt.stream;

import org.junit.Test;
import ru.sbt.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class StreamsTest {
    @Test
    public void testStreams(){
        List<Person> someCollection = Arrays.asList(
                new Person(20, "Иван"),
                new Person(40, "Анатолий"),
                new Person(15, "Илья"),
                new Person(25, "Роман"),
                new Person(33, "Александр"),
                new Person(50, "Евгений"));

        Map m = Streams.of(someCollection)
                .filter(p -> p.getAge() > 20)
                .transform(p -> new Person(p.getAge()+30, p.getName()))
                .toMap(p -> p.getName(), p -> p);

        m.forEach((k, v) -> System.out.println("key = "+ k + " value = " + v.toString()));
        System.out.println("------------------------");
        someCollection.stream().forEach(v->System.out.println(v.toString()));

    }
}