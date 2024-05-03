package com.udesc.dsd.model;

public class DirectionCell extends Cell {
    private boolean occupied;

    public DirectionCell(int x, int y) {
        super(x, y);
        this.occupied = false;
    }

    @Override
    public boolean isOccupied() {
        return this.occupied;
    }
}
