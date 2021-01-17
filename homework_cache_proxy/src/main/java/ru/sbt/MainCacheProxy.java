package ru.sbt;

import ru.sbt.annotation.CacheType;
import ru.sbt.api.Service;
import ru.sbt.api.ServiceImpl;
import ru.sbt.proxy.CacheProxy;

import java.util.Date;
import java.util.List;

public class MainCacheProxy {
    public static void main(String[] args) {
        CacheProxy proxy = new CacheProxy("file", "m", 100, false, new Class[]{Double.class, String.class}, CacheType.IN_FILE);

        Service service = proxy.cache(new ServiceImpl());

        List<String> work = service.run("Item", 4.0, new Date());
        work.forEach(System.out::println);

        List<String> work2 = service.run("Item2", 5.0, new Date());
        work2.forEach(System.out::println);

        List<String> work3 = service.run("Item3", 4.0, new Date());
        work3.forEach(System.out::println);
    }
}
