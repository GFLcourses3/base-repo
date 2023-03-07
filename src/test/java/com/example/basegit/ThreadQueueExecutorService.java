package com.example.basegit;

import org.junit.jupiter.api.Test;

import java.util.Queue;
import java.util.concurrent.*;

public class ThreadQueueExecutorService {

    private static final Queue<String> scenarioQueue = new ConcurrentLinkedQueue<>();
    private static final Queue<String> proxyQueue = new ConcurrentLinkedQueue<>();
    private static final CountDownLatch countDownLatch = new CountDownLatch(12);

    @Test
    public void threadQueue() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable scenarioTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Scenario writer thread - " + Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                    scenarioQueue.add("Scenario - " + i);
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                countDownLatch.countDown();
            }
        };

        Runnable proxyTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Proxy writer thread - " + Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                    proxyQueue.add("Proxy - " + i);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                countDownLatch.countDown();
            }
        };

        executorService.submit(scenarioTask);
        executorService.submit(proxyTask);

        int workerAmount = 10;
        for (int i = 0; i <= workerAmount; i++) {
            Runnable worker = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        String scenario = scenarioQueue.poll();
                        String proxy = proxyQueue.poll();
                        System.out.println(String.format("Worker thread - %s; Scenario - %s; Proxy - %s",
                                Thread.currentThread().getName(), scenario, proxy));
                    }
                    countDownLatch.countDown();
                }
            };
            executorService.submit(worker);
        }
        System.out.println("Wait for job finish...");
        countDownLatch.await();
        System.out.println("Shutdown executor...");
        executorService.shutdown();
    }
}
