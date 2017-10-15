package com.epam.lab.bank.lock;

import com.epam.lab.bank.Bank;

import java.util.concurrent.locks.Lock;

public class BankUser implements Runnable {
    private static final int AMOUNT_TO_GET = 10;
    private Bank bank;
    private Lock lock;

    public BankUser(Bank bank, Lock lock) {
        this.bank = bank;
        this.lock = lock;
    }

    public void run() {
        boolean canContinue = true;
        while (canContinue) {
            lock.lock();
            try {
                //System.out.println(Thread.currentThread() + " get lock");
                if (this.bank.hasMoney(AMOUNT_TO_GET)) {
                    this.bank.getMoney(AMOUNT_TO_GET);
                } else {
                    canContinue = false;
                }
            } finally {
                //System.out.println("Thread-" + Thread.currentThread() + " realize lock. Can continue? " + canContinue);
                lock.unlock();
            }
        }
    }
}
