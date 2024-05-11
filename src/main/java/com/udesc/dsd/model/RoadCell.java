package com.udesc.dsd.model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

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
        try{
            var acquired = semaphore.tryAcquire(4000, TimeUnit.MILLISECONDS);
            if (acquired) {
                if(this.getVehicle() == null || this.getVehicle().threadId() != vehicle.threadId()){
                    this.setVehicle(vehicle);
                }
                notifyCarEntered(vehicle);
            }
            return acquired;
        }catch (InterruptedException exception) {
            exception.printStackTrace();
            return false;
        }
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
