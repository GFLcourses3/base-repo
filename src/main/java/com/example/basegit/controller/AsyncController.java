package com.example.basegit.controller;

import com.example.basegit.AsyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping(path = "/api/run/async")
    public ResponseEntity<String> runAsyncMethod() throws InterruptedException {
        System.out.println("Main Thread - " + Thread.currentThread().getName());
        Future<String> future = asyncService.runAsync();
        while (true) {
            if (future.isDone()) {
                try {
                    return ResponseEntity.ok(future.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            Thread.sleep(1000);
            System.out.println("Async still working ...");
        }
    }
}
