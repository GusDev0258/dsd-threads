package com.udesc.dsd.model;

public abstract class Cell {
    private int positionX;
    private int positionY;

    public Cell(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public abstract boolean isOccupied();


}
