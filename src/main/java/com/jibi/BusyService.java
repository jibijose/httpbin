package com.jibi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusyService {

    int percentage;
    long durationMillis;

    public BusyService(int percentage, long seconds) {
        this.percentage = percentage;
        this.durationMillis = seconds * 1000;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - startTime < durationMillis) {
                if (System.currentTimeMillis() % 100 == 0) {
                    Thread.sleep((long) Math.floor((1 - percentage / 100) * 100));
                }
            }
        } catch (Exception exception) {
            log.warn("Thread is interrupted", exception);
        }
    }

}
