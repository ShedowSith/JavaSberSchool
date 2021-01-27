package ru.sbt;

import ru.sbt.streamimpl.ScalableThreadPool;

public class MainScalableThreadPool {
    public static void main(String[] args) {
        ScalableThreadPool threadPool = new ScalableThreadPool(3, 10);
        for (int i = 0; i<10; i++){
            threadPool.execute(() -> {
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
        threadPool.start();
    }
}
