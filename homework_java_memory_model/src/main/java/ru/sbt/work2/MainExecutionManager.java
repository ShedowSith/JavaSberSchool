package ru.sbt.work2;

import ru.sbt.work2.api.Context;
import ru.sbt.work2.api.ExecutionManager;
import ru.sbt.work2.impl.ExecutionManagerImpl;

public class MainExecutionManager {
    public static void main(String[] args) {
        ExecutionManager executionManager = new ExecutionManagerImpl();
        Runnable [] runnables = new Runnable[8];
        runnables[0] = () -> {
                throw new RuntimeException();
        };

        for (int i= 1; i<runnables.length; i++){
            int finalI = i;
            runnables[i] = () -> {
                try {
                    Thread.sleep(500* finalI);
                    System.out.println("Task "+ finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }
        Runnable callback = () -> System.out.println("callback выполнился после завершения всех задач");

        Context context = executionManager.execute(callback, runnables);
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        context.interrupt(); // Выпоняем отмену тасков


        System.out.println("Успешно завершено = " + context.getCompletedTaskCount());
        System.out.println("Не было выполнено = " + context.getInterruptedTaskCount());
        System.out.println("Выполнено с ошибкой = " + context.getFailedTaskCount());
        try {
            Thread.sleep(2600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Успешно завершено = " + context.getCompletedTaskCount());
        System.out.println("Не было выполнено = " + context.getInterruptedTaskCount());
        System.out.println("Выполнено с ошибкой = " + context.getFailedTaskCount());
    }
}
