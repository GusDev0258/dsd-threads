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
    public boolean tryEnter(Vehicle vehicle) {
        var acquired = semaphore.tryAcquire();
        if (acquired) {
            this.setVehicle(vehicle);
            notifyCarEntered(vehicle);
        }
        return acquired;

    }

    @Override
    public void releaseVehicle() {
        semaphore.release();
        notifyCarLeft(getVehicle());
        this.setVehicle(null);
    }

    public Semaphore getSemaphore() {
        return this.semaphore;
    }
}
