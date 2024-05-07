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

    @Override
    public boolean tryEnter() {
        return semaphore.tryAcquire();
    }

    @Override
    public void releaseVehicle() {
        semaphore.release();
    }

    public Semaphore getSemaphore() {
        return this.semaphore;
    }
}
