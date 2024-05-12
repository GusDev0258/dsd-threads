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
    public boolean acquireCell() {
        return semaphore.tryAcquire();
    }

    @Override
    public void insertCarIntoCell(Vehicle vehicle) {
        this.setVehicle(vehicle);
        notifyCarEntered(vehicle);
    }

    @Override
    public void releaseCell() {
        semaphore.release();
    }

    @Override
    public void releaseVehicle() {
        var vehicle = getVehicle();
        this.setVehicle(null);
        semaphore.release();
        notifyCarLeft(vehicle);
    }

    public Semaphore getSemaphore() {
        return this.semaphore;
    }
}
