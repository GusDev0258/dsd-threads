package com.udesc.dsd.model;

public class Vehicle extends Thread{
    private int x,y;
    private int speed;

    private boolean isOutOfGrid = false;

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
        this.currentCell.setVehicle(this);
       this.currentCell = cell;
    }

    public boolean removeCarFromGrid() {
        this.currentCell.releaseVehicle();
        this.currentCell.setVehicle(null);
        this.isOutOfGrid = true;
    }
    @Override
    public void run() {

    }



    public void move() throws InterruptedException {
        try {
           var enter =  this.currentCell.tryEnter();
            sleep(1000);
            if(enter) {
               this.setCurrentCell(this.currentCell.getNextCell());
               move();
            }
        } catch( InterruptedException exception) {
            throw new InterruptedException(exception.getMessage());
        }


    }
}
