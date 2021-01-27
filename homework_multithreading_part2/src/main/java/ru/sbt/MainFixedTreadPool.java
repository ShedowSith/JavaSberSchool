package ru.sbt;

import ru.sbt.api.ThreadPool;
import ru.sbt.streamimpl.FixedThreadPool;

public class MainFixedTreadPool {
    public static void main(String[] args) {
        ThreadPool fixedThreadPool = new FixedThreadPool(3);
        for (int i = 0; i<10; i++){
            fixedThreadPool.execute(() -> {
                for(int k=0;k<10;k++){
                    System.out.println(String.format("Thread %s run, iteration %d", Thread.currentThread().getName(), k));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedThreadPool.start();

    }
}
