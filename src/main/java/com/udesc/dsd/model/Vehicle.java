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
        if(this.currentCell != null) {
            this.currentCell.releaseVehicle();
            this.currentCell.setVehicle(null);
        }
        this.isOutOfGrid = true;
        return true;
    }
    @Override
    public void run() {
        while(!isOutOfGrid) {
            try {
                move();
                Thread.sleep(1000 / speed);
            } catch(InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void move() {
        if (currentCell == null || currentCell.getNeighbours().isEmpty()) {
            return;
        }

        Cell nextCell = chooseNextCell();

        if (nextCell != null && nextCell.tryEnter()) {
            Cell previousCell = this.currentCell;
            setCurrentCell(nextCell);
            if (previousCell != null) {
                previousCell.releaseVehicle();
            }
            this.x = nextCell.getPositionX();
            this.y = nextCell.getPositionY();
        }
    }
    private Cell chooseNextCell() {
        for(Cell neighbor: currentCell.getNeighboursCells()) {
            if(!neighbor.isOccupied()){
                return neighbor;
            }
        }
        return null;
    }
}
