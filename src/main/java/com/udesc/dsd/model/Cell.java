package com.udesc.dsd.model;

public abstract class Cell {
    private int positionX;
    private int positionY;

    private int direction;

    private Cell nextCell = null;

    private Vehicle vehicle;

    private boolean isExit;
    private boolean isEntrance;

    private boolean isCrossing = false;

    public Cell(int x, int y, int direction) {
        this.positionX = x;
        this.positionY = y;
        this.direction = direction;
    }

    public abstract boolean isOccupied();

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Cell getNextCell() {
        return nextCell;
    }

    public void setNextCell(Cell nextCell) {
        this.nextCell = nextCell;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean isEntrance() {
        return isEntrance;
    }

    public void setEntrance(boolean entrance) {
        isEntrance = entrance;
    }

    public boolean isCrossing() {
        if (this.direction >= Direction.CRUZAMENTO_CIMA && this.direction <= Direction.CRUZAMENTO_BAIXO_ESQUERDA) {
            this.isCrossing = true;
        }
        return this.isCrossing;
    }

    abstract public boolean tryEnter();

    abstract public void releaseVehicle();

}
