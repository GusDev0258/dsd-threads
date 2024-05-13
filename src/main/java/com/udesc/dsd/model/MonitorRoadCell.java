package com.udesc.dsd.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorRoadCell extends RoadCell {
    private final ReentrantLock lock;

    public MonitorRoadCell(int x, int y, int direction) {
        super(x, y, direction);
        this.lock = new ReentrantLock();
    }

    @Override
    public boolean isOccupied() {
        return lock.isLocked();
    }

    @Override
    public boolean tryEnter(Vehicle vehicle) {
        boolean acquired = lock.tryLock();
        if (acquired) {
            this.setVehicle(vehicle);
            notifyCarEntered(vehicle);
        }
        return acquired;
    }

    @Override
    public boolean acquireCell() {
        return lock.tryLock();
    }

    @Override
    public void insertCarIntoCell(Vehicle vehicle) {
        this.setVehicle(vehicle);
        notifyCarEntered(vehicle);
    }

    @Override
    public void releaseCell() {
        lock.unlock();
    }

    @Override
    public void releaseVehicle() {
        var vehicle = getVehicle();
        if (vehicle != null && lock.isHeldByCurrentThread()) {
            this.setVehicle(null);
            lock.unlock();
            notifyCarLeft(vehicle);
        }
    }

    public Lock getLock() {
        return this.lock;
    }
}
