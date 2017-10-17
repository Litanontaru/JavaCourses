package com.epam.lab.philosophers.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Kate on 16.10.2017.
 */
public class MainConcurrent {

    public static void main(String[] args) {
        final int countOfObjects = 5;

        Fork[] forks = new Fork[countOfObjects];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i, new ReentrantLock());
        }

        ExecutorService service = Executors.newFixedThreadPool(countOfObjects);
        for (int i = 0; i < countOfObjects; i++) {
            service.submit(new Philosopher(i, forks[i], (i == 0) ? forks[forks.length - 1] : forks[i - 1]));
        }
        service.shutdown();
    }

}
