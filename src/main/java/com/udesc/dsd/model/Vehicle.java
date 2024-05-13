package com.udesc.dsd.model;

import com.udesc.dsd.model.observer.CarObserver;
import com.udesc.dsd.model.strategy.CrossingChoiceStrategy;
import com.udesc.dsd.model.strategy.CrossingStepsStrategy;
import com.udesc.dsd.model.strategy.RandomCrossingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vehicle extends Thread {
    private final Grid grid;
    private final Random random = new Random();
    public List<CarObserver> observers = new ArrayList<>();
    List<RoadCell> crossingPath = new ArrayList<>();
    List<String> crossingPossibilities = new ArrayList<>();
    private int x, y;
    private int speed;
    private boolean isOutOfGrid = false;
    private RoadCell currentCell;
    private RoadCell step1, step2, step3, destiny;
    //O cruzamento poderá ter no máximo 4 possibilidades:
    private boolean crossingUp;
    private boolean crossingRight;
    private boolean crossingDown;
    private boolean crossingLeft;
    private String image;

    public Vehicle(int x, int y, Grid grid) {
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.speed = 200 + random.nextInt(800);
        this.image = getRandomImage();
    }

    public void addObserver(CarObserver obs) {
        this.observers.add(obs);
    }

    public void removeObserver() {
        this.observers.clear();
    }

    public void notifyObserversCarIsOutOfGrid() {
        for (CarObserver obs : observers) {
            obs.onVehicleLeft(this);
        }
    }

    public int getCarSpeed() {
       return this.speed;
    }
    public String getCarImage() {
        return this.image;
    }

    public List<RoadCell> getCrossingPath() {
        return this.crossingPath;
    }

    public void addStepsToCrossingPath(RoadCell step) {
        this.crossingPath.add(step);
    }

    public RoadCell getStep1() {
        return step1;
    }

    public void setStep1(RoadCell step1) {
        this.step1 = step1;
    }

    public RoadCell getStep2() {
        return step2;
    }

    public void setStep2(RoadCell step2) {
        this.step2 = step2;
    }

    public RoadCell getStep3() {
        return step3;
    }

    public void setStep3(RoadCell step3) {
        this.step3 = step3;
    }

    public RoadCell getDestiny() {
        return destiny;
    }

    public void setDestiny(RoadCell destiny) {
        this.destiny = destiny;
    }

    public boolean isCrossingUp() {
        return crossingUp;
    }

    public void setCrossingUp(boolean crossingUp) {
        this.crossingUp = crossingUp;
    }

    public boolean isCrossingRight() {
        return crossingRight;
    }

    public void setCrossingRight(boolean crossingRight) {
        this.crossingRight = crossingRight;
    }

    public boolean isCrossingDown() {
        return crossingDown;
    }

    public void setCrossingDown(boolean crossingDown) {
        this.crossingDown = crossingDown;
    }

    public boolean isCrossingLeft() {
        return crossingLeft;
    }

    public void setCrossingLeft(boolean crossingLeft) {
        this.crossingLeft = crossingLeft;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public RoadCell getCurrentCell() {
        return this.currentCell;
    }

    public void setCurrentCell(RoadCell cell) {
        this.currentCell = cell;
        this.setX(this.getCurrentCell().getPositionX());
        this.setY(this.getCurrentCell().getPositionY());
    }

    public List<String> getCrossingPossibilities() {
        return crossingPossibilities;
    }

    public void setCrossingPossibilities(List<String> crossingPossibilities) {
        this.crossingPossibilities = crossingPossibilities;
    }

    public void addCrossingPobility(String possibility) {
        this.crossingPossibilities.add(possibility);
    }

    public String getRandomImage() {
        List<String> carImages = new ArrayList<>();
        carImages.add(SimulationSettings.CAR_IMAGE_PATH);
        carImages.add(SimulationSettings.CAR_IMAGE_PATH_2);
        carImages.add(SimulationSettings.CAR_IMAGE_PATH_3);
        carImages.add(SimulationSettings.CAR_IMAGE_PATH_4);
        var randomIndex = random.nextInt(0, carImages.size());
        return carImages.get(randomIndex);
    }

    public void removeCarFromGrid() {
        if (this.currentCell != null) {
            this.currentCell.releaseVehicle();
            this.isOutOfGrid = true;
            this.notifyObserversCarIsOutOfGrid();
        }
    }

    @Override
    public void run() {
        while (!isOutOfGrid && !SimulationSettings.FORCED_SIMULATION) {
            try {
                Thread.sleep(speed);
                moveCarStraightForward();
                Thread.sleep(speed);
                if (currentCell.isNextCellACrossing()) {
                    CrossingChoiceStrategy.execute(this);
                    String destino = RandomCrossingStrategy.execute(this);// essa aqui é a escolha do carro apos chegar
                    // em um
                    // cruzamento
                    while (!isAllPathFree(destino)) {
                        Thread.sleep(100 + random.nextInt(450));
                    }
                    followPath();
                }
                System.out.println("movimentei!" + this.threadId());
            } catch (InterruptedException exception) {
                System.out.println("Encerrado");
                this.removeCarFromGrid();
                Thread.currentThread().interrupt();
                exception.printStackTrace();
            }
        }
    }

    private boolean isAllPathFree(String destino) {
        CrossingStepsStrategy.execute(this, destino);
        List<RoadCell> acquiredCells = new ArrayList<>();
        for (RoadCell step : this.crossingPath) {
            if (step.acquireCell()) {
                acquiredCells.add(step);
            } else {
                for (RoadCell cell : acquiredCells) {
                    cell.releaseCell();
                }
                return false;
            }
        }
        return true;
    }

    private void followPath() {
        for (RoadCell step : this.crossingPath) {
            if (!step.isOccupied()) {
                step.tryEnter(this);
            } else if (step.isOccupied() && step.getVehicle() == null) {
                step.insertCarIntoCell(this);
                this.getCurrentCell().releaseVehicle();
                this.setCurrentCell(step);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                this.removeCarFromGrid();
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        this.releaseCarFromAcquiredCrossingCells(this.crossingPath);
    }

    private void releaseCarFromAcquiredCrossingCells(List<RoadCell> cellsToLeave) {
        for (RoadCell cell : cellsToLeave) {
            if (cell.isCrossing()) {
                cell.releaseVehicle();
            }
        }
    }

    private void moveCarStraightForward() {
        RoadCell nextCell = null;
        switch (getCurrentCell().getDirection()) {
            case Direction.ESTRADA_CIMA:
            case Direction.ESTRADA_DIREITA:
            case Direction.ESTRADA_BAIXO:
            case Direction.ESTRADA_ESQUERDA:
                nextCell = chooseCellToMoveVehicleForward();
                break;
            default:
                break;
        }
        if (nextCell != null) {
            moveCar(nextCell);
        }

        if (getCurrentCell().isExit()) {
            try {
                Thread.sleep(1000);
                removeCarFromGrid();
            } catch (InterruptedException exception) {
                this.removeCarFromGrid();
                Thread.currentThread().interrupt();
                exception.printStackTrace();
            }
        }
    }

    private RoadCell chooseCellToMoveVehicleForward() {
        return currentCell.getValidAdjacentCell(getCurrentCell().getDirection());
    }

    private void moveCar(RoadCell nextCell) {
        boolean enterCell = false;
        while (!enterCell) {
            enterCell = nextCell.tryEnter(this);
            if (!enterCell) {
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    this.removeCarFromGrid();
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            } else {
                this.currentCell.releaseVehicle();
                this.setCurrentCell(nextCell);
                setX(nextCell.getPositionX());
                setY(nextCell.getPositionY());
            }
        }
    }
    //Porém a direção que o carro já está (antes de entrar no cruzamento) não pode ser escolhida pois o carro não faz meia volta
    //verifica quais caminhos existem ao final do cruzamento para que o carro escolha um aleatório
    //dentre as possibilidades que o carro pode ter, escolhe um aleatoriamente
}