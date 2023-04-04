package com.example.basegit;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadPoolTest {

    @Test
    public void testSingleThreadExecutor() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(() -> "Hello World -> " + Thread.currentThread().getName());
        String s = submit.get();
        System.out.println(s);
        System.out.println("Main thread -> " + Thread.currentThread().getName());
    }

    @Test
    public void testThreadPoolExecutor() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        assertEquals(2, executorService.getPoolSize());
        assertEquals(2, executorService.getQueue().size());
    }

    @Test
    public void testCacheThreadPoolExecutor() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        assertEquals(4, executorService.getPoolSize());
        assertEquals(0, executorService.getQueue().size());
    }

    @Test
    public void testScheduleThreadPullExecutor() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> scheduleTask = scheduledExecutorService.schedule(() -> "Schedule task...", 2, TimeUnit.SECONDS);
        String o = (String) scheduleTask.get();
        System.out.println(o);
        System.out.println("Main thread ");

    }

    @Test
    public void testScheduleFixRateThreadPullExecutor() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(12);
        ScheduledFuture<?> scheduleTask = scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("Fixed rate -> " + Thread.currentThread().getName());
            countDownLatch.countDown();
        }, 2, 1, TimeUnit.SECONDS);
        countDownLatch.await(5, TimeUnit.SECONDS);
        scheduleTask.cancel(true);
    }
}
