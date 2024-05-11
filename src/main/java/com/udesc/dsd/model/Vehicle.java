package com.udesc.dsd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vehicle extends Thread {
    private int x, y;
    private int speed;
    private boolean isOutOfGrid = false;
    private Cell currentCell;
    private final Grid grid;
    List<Cell> crossingPath = new ArrayList<>();
    List<String> crossingPossibilities = new ArrayList<>();
    private Cell step1, step2, step3, destiny;
    private static final String CROSS_POSSIBILITY_UP = "crossingUp";
    private static final String CROSS_POSSIBILITY_DOWN = "crossingDown";
    private static final String CROSS_POSSIBILITY_LEFT = "crossingLeft";
    private static final String CROSS_POSSIBILITY_RIGHT = "crossingRight";

    //O cruzamento poderá ter no máximo 4 possibilidades:
    private boolean crossingUp;
    private boolean crossingRight;
    private boolean crossingDown;
    private boolean crossingLeft;

    public Vehicle(int x, int y, int speed, Grid grid) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.grid = grid;
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

    public Cell getCurrentCell() {
        return this.currentCell;
    }

    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    private void setCrossingUp(boolean crossingUp){
        this.crossingUp = crossingUp;
    }
    private void setCrossingRight(boolean crossingRight){
        this.crossingRight = crossingRight;
    }
    private void setCrossingDown(boolean crossingDown){
        this.crossingDown = crossingDown;
    }
    private void setCrossingLeft(boolean crossingLeft){
        this.crossingLeft = crossingLeft;
    }
    public boolean removeCarFromGrid() {
        if (this.currentCell != null) {
            this.currentCell.releaseVehicle();
        }
        this.isOutOfGrid = true;
        return true;
    }

    @Override
    public void run() {
        while (!isOutOfGrid) {
            try {
                Thread.sleep(300);
                moveCarStraightForward();
                Thread.sleep(300);
                if (currentCell.isNextCellACrossing()){
                    verifyCrossingChoicePossibilities();
                    String destino = crossingChoice();// essa aqui é a escolha do carro apos chegar em um cruzamento
                    followPath(destino);
                }
                System.out.println("movimentei!" + this.threadId());
            } catch (InterruptedException exception) {
                System.out.println("deu ruim");
                Thread.currentThread().interrupt();
            }
        }
    }

    private void followPath(String destino) {
        List<Cell> path = returnCrossingSteps(destino); //guardar as células que pertencem ao caminho escolhido
        int acquiredCells = 0;
        for (Cell step : path) {
            var acquireStepCell = step.isOccupied();
            if(!acquireStepCell) acquiredCells++;
        }
        if(acquiredCells == path.size()) {
           moveCarThroughCells(path); 
        }else {
            releaseCarFromAcquiredCells(path);    
        }
    }
    
    private void moveCarThroughCells(List<Cell> pathToMoveOn) {
        for(Cell cell : pathToMoveOn) {
            var oldCell = this.getCurrentCell();
            cell.tryEnter(this);
            oldCell.releaseVehicle();
            this.setCurrentCell(cell);
            this.setX(cell.getPositionX());
            this.setY(cell.getPositionY());
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                this.interrupt();
                e.printStackTrace();
            }
        }
    }
    
    private void releaseCarFromAcquiredCells(List<Cell> cellsToLeave) { 
        for (Cell cell : cellsToLeave) {
            if(cell.getVehicle() != null && cell.getVehicle().threadId() == this.threadId()) {
                cell.releaseVehicle();
            }
        }
    }

    private void moveVehicleTo(int nextX, int nextY) {
        Cell nextCell = grid.getGridCellAt(nextX, nextY);
        if (nextCell != null && !nextCell.isOccupied()) {
            moveCar(nextCell);
        }
    }

    public void moveCarStraightForward() {
            Cell nextCell = null;
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
            if (nextCell != null ) {
                moveCar(nextCell);
            }

        if (getCurrentCell().isExit()) {
            try{
               Thread.sleep(2000);
                removeCarFromGrid();
            }catch (InterruptedException exception) {
                this.interrupt();
                exception.printStackTrace();
            }
        }
    }

    private Cell chooseCellToMoveVehicleForward() {
        return currentCell.getValidAdjacentCell(getCurrentCell().getDirection());
    }

    private void moveCar(Cell nextCell) {
        var enterCell = nextCell.tryEnter(this);
        if (enterCell) {
            this.currentCell.releaseVehicle();
            this.setCurrentCell(nextCell);
            setX(nextCell.getPositionX());
            setY(nextCell.getPositionY());
        }
    }

    //Porém a direção que o carro já está (antes de entrar no cruzamento) não pode ser escolhida pois o carro não faz meia volta

    //verifica quais caminhos existem ao final do cruzamento para que o carro escolha um aleatório
    private void verifyCrossingChoicePossibilities(){
        resetCrossingPossibilities();
        switch (getCurrentCell().getDirection()) {
            case Direction.ESTRADA_CIMA: //significa que ele vem de baixo, no cruzamento pode escolher entre subir, esquerda ou direita
                if(returnCellDirection(x,y-3) == Direction.ESTRADA_CIMA) { //valida se a célula acima do cruzamento existe e é uma estrada
                    setCrossingUp(true);
                }
                if(returnCellDirection(x+1,y-1) == Direction.ESTRADA_DIREITA) { //valida se a célula para a direita do cruzamento existe e é uma estrada
                    setCrossingRight(true);
                }
                setCrossingDown(false);
                if(returnCellDirection(x-2,y-2) == Direction.ESTRADA_ESQUERDA) { //valida se a célula para a esquerda do cruzamento existe e é uma estrada
                    setCrossingLeft(true);
                }
                break;
            case Direction.ESTRADA_DIREITA: //significa que ele vem da esquerda, no cruzamento pode escolher entre subir, descer ou direita e é uma estrada
                if(returnCellDirection(x+2,y-2) == Direction.ESTRADA_CIMA) {
                    setCrossingUp(true);
                }
                if(returnCellDirection(x+3,y) == Direction.ESTRADA_DIREITA) {
                    setCrossingRight(true);
                }
                if(returnCellDirection(x+1,y+1) == Direction.ESTRADA_BAIXO) {
                    setCrossingDown(true);
                }
                setCrossingLeft(false);
                break;
            case Direction.ESTRADA_BAIXO: //significa que ele vem de cima, no cruzamento pode escolher entre descer, esquerda ou direita
                setCrossingUp(false);
                if(returnCellDirection(x+2,y+2) == Direction.ESTRADA_DIREITA) {
                    setCrossingRight(true);
                }
                if(returnCellDirection(x,y+3) == Direction.ESTRADA_BAIXO) {
                    setCrossingDown(true);
                }
                if(returnCellDirection(x-1,y+1) == Direction.ESTRADA_ESQUERDA) {
                    setCrossingLeft(true);
                    System.out.println(returnCellDirection(x-1,y+1));
                }
                break;
            case Direction.ESTRADA_ESQUERDA: //significa que ele vem da direita, no cruzamento pode escolher entre descer, esquerda ou subir
                if(returnCellDirection(x-1,y-1) == Direction.ESTRADA_CIMA) {
                    setCrossingUp(true);
                }
                setCrossingRight(false);
                if(returnCellDirection(x-2,y+2) == Direction.ESTRADA_BAIXO) {
                    setCrossingDown(true);
                }
                if(returnCellDirection(x-3,y) == Direction.ESTRADA_ESQUERDA) {
                    setCrossingLeft(true);
                }
                break;
        }
    }

    private void resetCrossingPossibilities(){
        setCrossingUp(false);
        setCrossingRight(false);
        setCrossingLeft(false);
        setCrossingDown(false);
    }

    private String crossingChoice(){
        crossingPossibilities = getCrossingPossibilities();
        Random random = new Random();
        String randomlySelectedAttribute = crossingPossibilities.get(random.nextInt(0, crossingPossibilities.size()));
        return randomlySelectedAttribute;
    }

    //cria lista com possibilidades de escolha
    private List<String> getCrossingPossibilities() {
        if (crossingUp) {
            crossingPossibilities.add(CROSS_POSSIBILITY_UP);
        }
        if (crossingRight) {
            crossingPossibilities.add(CROSS_POSSIBILITY_RIGHT);
        }
        if (crossingDown) {
            crossingPossibilities.add(CROSS_POSSIBILITY_DOWN);
        }
        if (crossingLeft) {
            crossingPossibilities.add(CROSS_POSSIBILITY_LEFT);
        }
        return crossingPossibilities;
    }

    //dentre as possibilidades que o carro pode ter, escolhe um aleatoriamente

    private int returnCellDirection(int x, int y){
        Cell cell = grid.getGridCellAt(x,y);
        int direction = cell.getDirection();
        return direction;
    }

    private List<Cell> returnCrossingSteps(String destino){
        //deve gravar em ordem
        crossingPath.clear();
        switch (destino){
            case CROSS_POSSIBILITY_UP:
                switch (getCurrentCell().getDirection()){
                    case Direction.ESTRADA_CIMA:
                        step1 = getGridCellBasedOnCoordinates(x, y-1);
                        step2 = getGridCellBasedOnCoordinates(x, y-2);
                        destiny = getGridCellBasedOnCoordinates(x, y-3);
                        crossingPath.add(step1);
                        crossingPath.add(step2);
                        crossingPath.add(destiny);
                        break;
                    case Direction.ESTRADA_DIREITA:
                        step1 = getGridCellBasedOnCoordinates(x+1,y);
                        step2 = getGridCellBasedOnCoordinates(x+2, y);
                        step3 = getGridCellBasedOnCoordinates(x+2, y-1);
                        destiny = getGridCellBasedOnCoordinates(x+2, y-2);
                        crossingPath.add(step1);
                        crossingPath.add(step2);
                        crossingPath.add(step3);
                        crossingPath.add(destiny);
                        break;
                    case Direction.ESTRADA_ESQUERDA:
                        step1 = getGridCellBasedOnCoordinates(x-1, y);
                        destiny = getGridCellBasedOnCoordinates(x-1,y-1);
                        crossingPath.add(step1);
                        crossingPath.add(destiny);
                        break;
                }
                break;
            case CROSS_POSSIBILITY_RIGHT:
                switch (getCurrentCell().getDirection()){
                    case Direction.ESTRADA_CIMA:
                        step1 = getGridCellBasedOnCoordinates(x, y-1);
                        destiny = getGridCellBasedOnCoordinates(x+1, y-1);
                        crossingPath.add(step1);
                        crossingPath.add(destiny);
                        break;
                    case Direction.ESTRADA_DIREITA:
                        step1 = getGridCellBasedOnCoordinates(x+1, y);
                        step2 = getGridCellBasedOnCoordinates(x+2, y);
                        destiny = getGridCellBasedOnCoordinates(x+3, y);
                        crossingPath.add(step1);
                        crossingPath.add(step2);
                        crossingPath.add(destiny);
                        break;
                    case Direction.ESTRADA_BAIXO:
                        step1 = getGridCellBasedOnCoordinates(x, y+1);
                        step2 = getGridCellBasedOnCoordinates(x, y+2);
                        step3 = getGridCellBasedOnCoordinates(x+1,y+2);
                        destiny = getGridCellBasedOnCoordinates(x+2, y+2);
                        crossingPath.add(step1);
                        crossingPath.add(step2);
                        crossingPath.add(step3);
                        crossingPath.add(destiny);
                        break;
                }
                break;
            case CROSS_POSSIBILITY_DOWN:
                switch (getCurrentCell().getDirection()){
                    case Direction.ESTRADA_DIREITA:
                        step1 = getGridCellBasedOnCoordinates(x+1, y);
                        destiny = getGridCellBasedOnCoordinates(x+1, y+1);
                        crossingPath.add(step1);
                        crossingPath.add(destiny);
                        break;
                    case Direction.ESTRADA_BAIXO:
                        step1 = getGridCellBasedOnCoordinates(x, y+1);
                        step2 = getGridCellBasedOnCoordinates(x, y+2);
                        destiny = getGridCellBasedOnCoordinates(x, y+3);
                        crossingPath.add(step1);
                        crossingPath.add(step2);
                        crossingPath.add(destiny);
                        break;
                    case Direction.ESTRADA_ESQUERDA:
                        step1 = getGridCellBasedOnCoordinates(x-1, y);
                        step2 = getGridCellBasedOnCoordinates(x-2, y);
                        step3 = getGridCellBasedOnCoordinates(x-2, y+1);
                        destiny = getGridCellBasedOnCoordinates(x-2, y+2);
                        crossingPath.add(step1);
                        crossingPath.add(step2);
                        crossingPath.add(step3);
                        crossingPath.add(destiny);
                        break;
                }
                break;
            case CROSS_POSSIBILITY_LEFT:
                switch (getCurrentCell().getDirection()){
                    case Direction.ESTRADA_CIMA:
                        step1 = getGridCellBasedOnCoordinates(x, y-1);
                        step2 = getGridCellBasedOnCoordinates(x, y-2);
                        step3 = getGridCellBasedOnCoordinates(x-1, y-2);
                        destiny = getGridCellBasedOnCoordinates(x-2, y-2);
                        crossingPath.add(step1);
                        crossingPath.add(step2);
                        crossingPath.add(step3);
                        crossingPath.add(destiny);
                        break;
                    case Direction.ESTRADA_BAIXO:
                        step1 = getGridCellBasedOnCoordinates(x, y+1);
                        destiny = getGridCellBasedOnCoordinates(x-1, y+1);
                        crossingPath.add(step1);
                        crossingPath.add(destiny);
                        break;
                    case Direction.ESTRADA_ESQUERDA:
                        step1 = getGridCellBasedOnCoordinates(x+1, y);
                        step2 = getGridCellBasedOnCoordinates(x+2, y);
                        destiny = getGridCellBasedOnCoordinates(x+3, y);
                        crossingPath.add(step1);
                        crossingPath.add(step2);
                        crossingPath.add(destiny);
                        break;
                }
                break;
        }
        return crossingPath;
    }
    private Cell getGridCellBasedOnCoordinates(int x, int y) {
        return grid.getGridCellAt(x,y);
    }

}
