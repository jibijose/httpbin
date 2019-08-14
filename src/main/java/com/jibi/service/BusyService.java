package com.jibi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class BusyService {

    private static int SLOTS = 1000;
    private static int CORES = Runtime.getRuntime().availableProcessors();

    private void run(int percentage, int seconds) {
        ExecutorService executor = Executors.newFixedThreadPool(CORES);
        for (int iCount = 0; iCount < CORES; iCount++) {
            executor.execute();
        }
    }

    public void runSingleProcessor(int percentage, int seconds) {
        long runSlotMillis = percentage * seconds * 1000 / SLOTS / 100;
        long idleSlotMillis = (100 - percentage) * seconds * 1000 / SLOTS / 100;

        for (int iCount = 0; iCount < SLOTS; iCount++) {
            long startTime = System.currentTimeMillis();
            try {
                while (System.currentTimeMillis() - startTime < runSlotMillis) {
                    //TODO do little cpu if needed
                }
                Thread.sleep(idleSlotMillis);
            } catch (Exception exception) {
                log.warn("Thread is interrupted", exception);
            }
        }
    }

}
