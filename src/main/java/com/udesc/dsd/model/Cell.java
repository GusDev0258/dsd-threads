package com.udesc.dsd.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Cell {
    private int positionX;
    private int positionY;

    private int direction;

    private Cell nextCell = null;

    private Vehicle vehicle;

    private boolean isExit;
    private boolean isEntrance;

    private boolean isCrossing = false;
    private String vehicleImagePath;

    private Cell upCell;
    private Cell bottomCell;
    private Cell leftCell;
    private Cell rightCell;

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

    public Cell getUpCell() {
        return upCell;
    }

    public void setUpCell(Cell upCell) {
        this.upCell = upCell;
    }

    public Cell getBottomCell() {
        return bottomCell;
    }

    public void setBottomCell(Cell bottomCell) {
        this.bottomCell = bottomCell;
    }

    public Cell getLeftCell() {
        return leftCell;
    }

    public void setLeftCell(Cell leftCell) {
        this.leftCell = leftCell;
    }

    public Cell getRightCell() {
        return rightCell;
    }

    public void setRightCell(Cell rightCell) {
        this.rightCell = rightCell;
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
