package com.udesc.dsd.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MonitorRoadCell extends RoadCell {
    private final Lock monitor = new ReentrantLock();
    private Vehicle vehicle;

    public MonitorRoadCell(int x, int y, int direction) {
        super(x, y, direction);
    }

    @Override
    public boolean isOccupied() {
        monitor.lock();
        try {
            return vehicle != null;
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public boolean tryEnter(Vehicle vehicle) {
        if (monitor.tryLock()) {
            try {
                if (this.vehicle == null) {
                    this.vehicle = vehicle;
                    notifyCarEntered(vehicle);
                    return true;
                }
            } finally {
                monitor.unlock();
            }
        }
        return false;
    }

    @Override
    public boolean acquireCell() {
        monitor.lock();
        try {
            if (this.vehicle == null) {
                return true;
            }
            return false;
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public void insertCarIntoCell(Vehicle vehicle) {
        monitor.lock();
        try {
            this.vehicle = vehicle;
            notifyCarEntered(vehicle);
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public void releaseCell() {
        monitor.unlock();
    }

    @Override
    public void releaseVehicle() {
        monitor.lock();
        try {
            Vehicle exitingVehicle = this.vehicle;
            this.vehicle = null;
            notifyCarLeft(exitingVehicle);
        } finally {
            monitor.unlock();
        }
    }
}