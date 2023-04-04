package com.example.basegit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class AsyncService {

    @Async
    public Future<String> runAsync() {
        System.out.println("Async Thread - " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
            return new AsyncResult<String>("Async result");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
