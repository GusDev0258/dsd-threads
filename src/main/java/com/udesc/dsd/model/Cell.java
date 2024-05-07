package com.udesc.dsd.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Cell {
    private int positionX;
    private int positionY;

    private int direction;

    private Vehicle vehicle;

    private boolean isExit;
    private boolean isEntrance;

    private boolean isCrossing = false;
    private String vehicleImagePath;

    private Map<Point, Cell> neighbours = new HashMap<>();
    public Cell(int x, int y, int direction) {
        this.positionX = x;
        this.positionY = y;
        this.direction = direction;
    }

    public void addNeighbour(Cell neighbour) {
        Point point = new Point(neighbour.getPositionX(), neighbour.getPositionY());
        neighbours.put(point, neighbour);
    }
    public Map<Point,Cell> getNeighbours() {
        return neighbours;
    }
    public List<Cell> getNeighboursCells() {
       return neighbours.values().stream().toList();
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
    public void setVehicleImage(){
        if (direction > Direction.ESTRADA_ESQUERDA) {
//            setVehicleImagePath("assets/stone.png");
            if (direction >= Direction.CRUZAMENTO_CIMA) {
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
