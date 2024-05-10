package com.udesc.dsd.model;

import com.udesc.dsd.model.observer.CellObserver;
import com.udesc.dsd.model.strategy.ChooseDirectionImageStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Cell  {
    private int positionX;
    private int positionY;
    private int direction;
    private Vehicle vehicle;
    private boolean isExit;
    private boolean isEntrance;
    private boolean isCrossing = false;
    private String cellImagePath;
    private Cell upNeighbor;
    private Cell downNeighbor;
    private Cell rightNeighbor;
    private Cell leftNeighbor;

    private List<CellObserver> observers = new ArrayList<>();

    private List<Cell> crossingNeighbors = new ArrayList<>();

    public Cell(int x, int y, int direction) {
        this.positionX = x;
        this.positionY = y;
        this.direction = direction;
        this.setCellImage();
    }

    public void addObserver(CellObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(CellObserver observer) {
        observers.add(observer);
    }

    public void notifyCarEntered(Vehicle vehicle){
        for ( CellObserver obs: observers) {
            obs.onCarEntered(vehicle, this);
        }
    }
    public void notifyCarLeft(Vehicle vehicle){
        for(CellObserver observer: observers) {
            observer.onCarLeft(vehicle, this);
        }
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

    public Cell getUpNeighbor() {
        return upNeighbor;
    }

    public void setUpNeighbor(Cell upNeighbor) {
        this.upNeighbor = upNeighbor;
    }

    public Cell getDownNeighbor() {
        return downNeighbor;
    }

    public void setDownNeighbor(Cell downNeighbor) {
        this.downNeighbor = downNeighbor;
    }

    public Cell getRightNeighbor() {
        return rightNeighbor;
    }

    public void setRightNeighbor(Cell rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public Cell getLeftNeighbor() {
        return leftNeighbor;
    }

    public void setLeftNeighbor(Cell leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    abstract public boolean tryEnter(Vehicle vehicle);

    abstract public void releaseVehicle();

    public void setCellImage() {
        this.cellImagePath = ChooseDirectionImageStrategy.execute(this.direction);
    }
    public String getCellImagePath() {
        return this.cellImagePath;
    }
    protected Cell getValidAdjacentCell(int direction) {
        Cell targetCell = null;
        switch (direction) {
            case Direction.ESTRADA_CIMA -> {
                targetCell = this.getUpNeighbor();
                break;
            }
            case Direction.ESTRADA_DIREITA -> {
                targetCell = this.getRightNeighbor();
                break;
            }
            case Direction.ESTRADA_BAIXO -> {
                targetCell = this.getDownNeighbor();
                break;
            }
            case Direction.ESTRADA_ESQUERDA -> {
                targetCell = this.getLeftNeighbor();
                break;
            }

        }
        return Objects.requireNonNullElseGet(targetCell, () -> throwInvalidDirection());
    }

    private Cell throwInvalidDirection() {
        throw new IllegalStateException("Invalid direction detected in " + this.getDirection());
    }

    public List<Cell> getCrossingNeighbors() {
        return this.crossingNeighbors;
    }

    public void addCrossingNeighbor(Cell cell) {
        if(cell.isCrossing()) {
            this.crossingNeighbors.add(cell);
        }
    }

    //validacao para dizer se a célula está antes de em um cruzamento
    public boolean isNextCellACrossing(){
        return !this.isCrossing() && this.getCrossingNeighbors().size() == 1;
    }
}
