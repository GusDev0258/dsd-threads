package com.udesc.dsd.model;

import java.util.concurrent.Semaphore;

public class RoadCell extends Cell {
    private final Semaphore semaphore;

    public RoadCell(int x, int y, int direction) {
        super(x, y, direction);
        this.semaphore = new Semaphore(1);
    }

    @Override
    public boolean isOccupied() {
        return semaphore.availablePermits() == 0;
    }

    public boolean tryEnter() {
        return semaphore.tryAcquire();
    }

    public void leave() {
        semaphore.release();
    }

    public Semaphore getSemaphore() {
        return this.semaphore;
    }
}
