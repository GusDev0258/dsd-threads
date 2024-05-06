package com.udesc.dsd.model;

public class DirectionCell extends Cell {
    private boolean occupied;

    public DirectionCell(int x, int y, int direction) {
        super(x, y, direction);
        this.occupied = false;
    }

    @Override
    public boolean isOccupied() {
        return this.occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}