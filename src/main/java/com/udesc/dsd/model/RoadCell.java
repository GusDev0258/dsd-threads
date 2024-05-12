package com.udesc.dsd.model;

import com.udesc.dsd.model.observer.CellObserver;
import com.udesc.dsd.model.strategy.ChooseDirectionImageStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class RoadCell {
    private int positionX;
    private int positionY;
    private int direction;
    private Vehicle vehicle;
    private boolean isExit;
    private boolean isEntrance;
    private boolean isCrossing = false;
    private String cellImagePath;
    private RoadCell upNeighbour;
    private RoadCell downNeighbour;
    private RoadCell rightNeighbour;
    private RoadCell leftNeighbour;

    private RoadCell nextCrossingCell;
    private List<CellObserver> observers = new ArrayList<>();

    private List<RoadCell> crossingNeighbours = new ArrayList<>();

    public RoadCell(int x, int y, int direction) {
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

    public RoadCell getNextCrossingCell() {
        return nextCrossingCell;
    }

    public void setNextCrossingCell(RoadCell nextCrossingCell) {
        this.nextCrossingCell = nextCrossingCell;
    }

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

    protected void setVehicle(Vehicle vehicle) {
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

    public RoadCell getUpNeighbour() {
        return upNeighbour;
    }

    public void setUpNeighbour(RoadCell upNeighbour) {
        this.upNeighbour = upNeighbour;
    }

    public RoadCell getDownNeighbour() {
        return downNeighbour;
    }

    public void setDownNeighbour(RoadCell downNeighbour) {
        this.downNeighbour = downNeighbour;
    }

    public RoadCell getRightNeighbour() {
        return rightNeighbour;
    }

    public void setRightNeighbour(RoadCell rightNeighbour) {
        this.rightNeighbour = rightNeighbour;
    }

    public RoadCell getLeftNeighbour() {
        return leftNeighbour;
    }

    public void setLeftNeighbour(RoadCell leftNeighbour) {
        this.leftNeighbour = leftNeighbour;
    }

    abstract public boolean tryEnter(Vehicle vehicle);
    abstract public boolean acquireCell();

    abstract public void insertCarIntoCell(Vehicle vehicle);
    abstract public void releaseCell();
    abstract public void releaseVehicle();

    public void setCellImage() {
        this.cellImagePath = ChooseDirectionImageStrategy.execute(this.direction);
    }
    public String getCellImagePath() {
        return this.cellImagePath;
    }
    protected RoadCell getValidAdjacentCell(int direction) {
        RoadCell targetCell = null;
        switch (direction) {
            case Direction.ESTRADA_CIMA -> {
                targetCell = this.getUpNeighbour();
                break;
            }
            case Direction.ESTRADA_DIREITA -> {
                targetCell = this.getRightNeighbour();
                break;
            }
            case Direction.ESTRADA_BAIXO -> {
                targetCell = this.getDownNeighbour();
                break;
            }
            case Direction.ESTRADA_ESQUERDA -> {
                targetCell = this.getLeftNeighbour();
                break;
            }

        }
        return Objects.requireNonNullElseGet(targetCell, () -> throwInvalidDirection());
    }

    private RoadCell throwInvalidDirection() {
        throw new IllegalStateException("Invalid direction detected in " + this.getDirection());
    }

    public List<RoadCell> getCrossingNeighbours() {
        return this.crossingNeighbours;
    }

    public void addCrossingNeighbor(RoadCell cell) {
        if(cell.isCrossing()) {
            this.crossingNeighbours.add(cell);
        }
    }

    //validacao para dizer se a célula está antes de em um cruzamento
    public boolean isNextCellACrossing(){
        if(!this.isCrossing() && this.getCrossingNeighbours().size() == 1)  {
          switch(direction) {
              case Direction.ESTRADA_CIMA -> {
                  if(this.upNeighbour.isCrossing()){
                      this.setNextCrossingCell(this.getUpNeighbour());
                      return true;
                  }
              }
              case Direction.ESTRADA_DIREITA -> {
                  if(this.rightNeighbour.isCrossing()) {
                      this.setNextCrossingCell(this.getRightNeighbour());
                      return true;
                  }
              }
              case Direction.ESTRADA_BAIXO -> {
                  if(this.downNeighbour.isCrossing()){
                      this.setNextCrossingCell(this.getDownNeighbour());
                      return true;
                  }
              }
              case Direction.ESTRADA_ESQUERDA -> {
                  if(this.leftNeighbour.isCrossing()) {
                      this.setNextCrossingCell(this.getLeftNeighbour());
                      return true;
                  }
              }
              default -> {
                  break;
              }
          }
        }
        return false;
    }
}
