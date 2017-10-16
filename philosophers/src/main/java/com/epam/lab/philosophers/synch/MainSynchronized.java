package com.epam.lab.philosophers.synch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kate on 16.10.2017.
 */
public class MainSynchronized {

    public static void main(String[] args) {
        final int countOfObjects = 5;

        Fork[] forks = new Fork[countOfObjects];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i);
        }

        List<Thread> threadPool = new ArrayList<>();
        for (int i = 0; i < countOfObjects; i++) {
            threadPool.add(new Thread(new Philosopher(i, forks[i], (i == 0) ? forks[forks.length - 1] : forks[i - 1])));
        }

        for (Thread thread : threadPool) {
            thread.start();
        }
    }

}
