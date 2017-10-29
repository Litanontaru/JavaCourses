package com.epam.lab.philosophers.synch;

/**
 * Created by Kate on 16.10.2017.
 */
public class Fork {
    private static final int FORK_IS_NOT_OCCUPIED = -1;
    //поле может быть final
    private int id;
    private volatile int ownerId = FORK_IS_NOT_OCCUPIED;

    public Fork(int id) {
        this.id = id;
    }

    public synchronized void occupy(int ownerId) {
        if (this.isOccupied() && this.ownerId != ownerId)
            throw new IllegalStateException("Fork has already been occupied by owner - " + this.ownerId);
        this.ownerId = ownerId;
    }

    public int getId() {
        return this.id;
    }

    public synchronized void release() {
        this.ownerId = FORK_IS_NOT_OCCUPIED;
    }

    public synchronized boolean isOccupied() {
        return (this.ownerId != FORK_IS_NOT_OCCUPIED);
    }
}
