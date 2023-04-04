package com.example.basegit.interputeException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HandleException {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdlMainException = new CountDownLatch(1);
        CountDownLatch cdlToAwaitException = new CountDownLatch(2);
        Runnable worker = new Runnable() {
            @Override
            public void run() {
                System.out.println("Interrupted steps = " + Thread.currentThread().getName());
                int i = 0;
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("Count of work = " + i);
                        i++;
                        cdlToAwaitException.countDown();
                        TimeUnit.SECONDS.sleep(3L);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupt thread = " + Thread.currentThread().getName());
                        Thread.currentThread().interrupt();
                    }
                }
                cdlMainException.countDown();
            }
        };

        Thread thread = new Thread(worker);
        thread.start();
        cdlToAwaitException.await();
        thread.interrupt();
        cdlMainException.await();
        System.out.println("Application shutdown");
    }
}
