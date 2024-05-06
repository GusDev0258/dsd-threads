package com.udesc.dsd.model;

public class Vehicle extends Thread{
    private int x,y;
    private int speed;

    private Cell currentCell;

    public Vehicle(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Cell getCurrentCell() {
       return this.currentCell;
    }

    public void setCurrentCell(Cell cell) {
       this.currentCell = cell;
    }
    @Override
    public void run() {

    }
}
