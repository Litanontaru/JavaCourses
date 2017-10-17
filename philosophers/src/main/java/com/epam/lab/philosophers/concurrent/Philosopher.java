package com.epam.lab.philosophers.concurrent;

import java.util.Calendar;

/**
 * Created by Kate on 16.10.2017.
 */
public class Philosopher implements Runnable {
    private static final long TIMEOUT = 500;

    private int id;
    private Fork greaterForkId;
    private Fork smallerForkId;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.greaterForkId = (rightFork.getId() > leftFork.getId()) ? rightFork : leftFork;
        this.smallerForkId = (rightFork.getId() > leftFork.getId()) ? leftFork : rightFork;
    }

    @Override
    public void run() {
        while (true) {
            // philosopher can't get fork with greater number before he get fork with smaller number
            occupyFork(smallerForkId);
            occupyFork(greaterForkId);

            eating();

            releaseFork(smallerForkId);
            releaseFork(greaterForkId);
        }
    }

    private void eating() {
        System.out.println("Philosopher (" + this.id + ") is eating spaghetti " +
                "by " + smallerForkId.getId() + " and " + greaterForkId.getId() + " forks");
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            System.out.println("Error - " + e.getMessage());
        }
    }

    private void occupyFork(Fork fork) {
        long currentMillis = Calendar.getInstance().getTimeInMillis();
        while (!fork.tryOccupy()) {
            if ((Calendar.getInstance().getTimeInMillis() - currentMillis) > TIMEOUT) {
                System.out.println("Philosopher (" + this.id + ") is thinking");
                currentMillis = Calendar.getInstance().getTimeInMillis();
            }
        }
    }

    private void releaseFork(Fork fork) {
        fork.release();
    }
}
