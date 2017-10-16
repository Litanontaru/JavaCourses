package com.epam.lab.philosophers.synch;

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

            smallerForkId.release();
            greaterForkId.release();
        }
    }

    private void eating() {
        System.out.println("Philosopher (" + this.id + ") is eating spaghetti " +
                "by " + smallerForkId.getId() + " and " + greaterForkId.getId() + " forks");
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void occupyFork(Fork fork) {
        synchronized (fork) {
            while (fork.isOccupied()) {
                System.out.println("Philosopher (" + this.id + ") is thinking");
                try {
                    this.wait(TIMEOUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    greaterForkId.release();
                    smallerForkId.release();
                }
            }
            fork.occupy(this.id);
        }
    }
}
