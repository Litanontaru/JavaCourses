package com.epam.lab.philosophers.concurrent;

import java.util.concurrent.locks.Lock;

/**
 * Created by Kate on 16.10.2017.
 */
public class Fork {
    //оба поля могут быть final
    private int id;
    private Lock lock;

    public Fork(int id, Lock lock) {
        this.id = id;
        this.lock = lock;
    }

    public boolean tryOccupy() {
        return this.lock.tryLock();
    }

    public int getId() {
        return this.id;
    }

    public void release() {
        this.lock.unlock();
    }

}
