package com.example.basegit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Value(value = "${arg:2}")
    private Integer arg;
    @Value("#{spelTestComponent.salaryByWorkers['John']}")
    private int add;

    @Scheduled(fixedRateString = "${schedule.time}")
    public void runTask() {
        System.out.println("ARG - " + arg);
        System.out.println("add - " + add);
    }
}
