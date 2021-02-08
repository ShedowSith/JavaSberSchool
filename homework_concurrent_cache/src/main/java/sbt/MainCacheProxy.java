package sbt;

import sbt.annotation.CacheType;
import sbt.api.Service;
import sbt.api.ServiceImpl;
import sbt.proxy.CacheProxy;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainCacheProxy {
    public static void main(String[] args) {
        CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(3);
        CacheProxy proxy = new CacheProxy("file", "m", 100, false, new Class[]{Double.class}, CacheType.IN_MEMORY);
        Service service = proxy.cache(new ServiceImpl());
        Runnable runnable1 = () -> {
            try {
                startSignal.await();
                List<String> work = service.run("Item", 4.0, new Date());
                work.forEach(System.out::println);
                endGate.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Runnable runnable2 = () -> {
            try {
                startSignal.await();
                List<String> work2 = service.run("Item2", 5.0, new Date());
                work2.forEach(System.out::println);
                endGate.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        Runnable runnable3 = () -> {
            try {
                startSignal.await();
                List<String> work3 = service.run("Item3", 4.0, new Date());
                work3.forEach(System.out::println);
                endGate.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(runnable1);
        executor.submit(runnable2);
        executor.submit(runnable3);
        startSignal.countDown(); // стартуем
        try {
            endGate.await(); // ожидаем завершения работы потоков
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
