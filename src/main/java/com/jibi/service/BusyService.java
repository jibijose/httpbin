package com.jibi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BusyService {

    public void run(int percentage, int seconds) {
        long startTime = System.currentTimeMillis();
        long durationMillis = seconds * 1000;
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
