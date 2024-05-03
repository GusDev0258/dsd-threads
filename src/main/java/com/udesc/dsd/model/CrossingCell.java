package com.udesc.dsd.model;

import java.util.concurrent.Semaphore;

public class CrossingCell extends Cell {
    private final Semaphore semaphore;

    public CrossingCell(int x, int y) {
        super(x, y);
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
}
