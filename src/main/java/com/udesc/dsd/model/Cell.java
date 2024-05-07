package com.udesc.dsd.model;

public abstract class Cell {
    private int positionX;
    private int positionY;

    private int direction;

    private Cell nextCell = null;

    private Vehicle vehicle;

    private boolean isExit;
    private boolean isEntrance;

    private String vehicleImagePath;

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

    public void setVehicleImage(){
        if (direction > 4) {
//            setVehicleImagePath("assets/stone.png");
            if (direction >= 5) {
                setVehicleImagePath("src/main/resources/car_icon.png");
            }

        } else {
            setVehicleImagePath("src/main/resources/car_icon.png");
        }
    }

    public void setVehicleImagePath(String imagePath) {
        this.vehicleImagePath = imagePath;
    }

    public String getVehicleImagePath() {
        return vehicleImagePath;
    }

}
