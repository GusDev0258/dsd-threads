package com.udesc.dsd.model;

import com.udesc.dsd.model.strategy.ChooseDirectionImageStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Cell {
    private final String CAR_PATH = "src/main/resources/car_icon.png";
    private int positionX;
    private int positionY;
    private int direction;
    private Vehicle vehicle;
    private boolean isExit;
    private boolean isEntrance;
    private boolean isCrossing = false;
    private String vehicleImagePath;
    private Map<Point, Cell> neighbors = new HashMap<>();
    public Cell(int x, int y, int direction) {
        this.positionX = x;
        this.positionY = y;
        this.direction = direction;
        updateVehicleImage();
    }
    public void addNeighbor(Cell neighbor) {
        Point point = new Point(neighbor.getPositionX(), neighbor.getPositionY());
        neighbors.put(point, neighbor);
    }
    public Map<Point, Cell> getNeighbors() {
        return neighbors;
    }
    public List<Cell> getNeighboursCells() {
        return neighbors.values().stream().toList();
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
    abstract public boolean tryEnter(Vehicle vehicle);
    abstract public void releaseVehicle();
    public void updateVehicleImage() {
        if (direction > Direction.NADA) {
            setVehicleImagePath(ChooseDirectionImageStrategy.execute(direction));
        } else if (this.vehicle != null) {
            setVehicleImagePath(this.CAR_PATH);
        }
    }
    public String getVehicleImagePath() {
        updateVehicleImage();
        return vehicleImagePath;
    }
    public void setVehicleImagePath(String imagePath) {
        this.vehicleImagePath = imagePath;
    }
}
