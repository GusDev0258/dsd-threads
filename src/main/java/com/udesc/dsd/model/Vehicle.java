package com.udesc.dsd.model;

import java.util.Random;

public class Vehicle extends Thread {
    private int x, y;
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
        this.currentCell = cell;
        this.currentCell.setVehicle(this);
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
                move();
                System.out.println("movimentei!" + this.threadId());
                Thread.sleep(1000 / speed);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void move() {
        if(currentCell == null) {
            return;
        }
        if(currentCell.isEntrance()) {
            Cell nextCell;
            switch (currentCell.getDirection()) {
                case Direction.ESTRADA_CIMA -> {
                    nextCell = currentCell.getUpNeighbor();
                    moveCar(nextCell);
                    break;
                }
                case Direction.ESTRADA_DIREITA -> {
                    nextCell = currentCell.getRightNeighbor();
                    moveCar(nextCell);
                }
                case Direction.ESTRADA_BAIXO -> {
                    nextCell = currentCell.getDownNeighbor();
                    moveCar(nextCell);
                }
                case Direction.ESTRADA_ESQUERDA -> {
                    nextCell = currentCell.getLeftNeighbor();
                    moveCar(nextCell);
                }
            }


        }
    }
    private void moveCar(Cell nextCell) {
        var enterCell = nextCell.tryEnter(this);
        if(enterCell) {
            this.x = nextCell.getPositionX();
            this.y = nextCell.getPositionY();
            this.currentCell.releaseVehicle();
            this.setCurrentCell(nextCell);
        }
    }
//    public void move() {
//        if (currentCell == null ) {
//            return;
//        }
//        Cell nextCell = chooseNextCell();
//        if (nextCell != null && nextCell.tryEnter(this)) {
//            Cell previousCell = this.currentCell;
//            setCurrentCell(nextCell);
//            if (previousCell != null) {
//                previousCell.releaseVehicle();
//            }
//            this.x = nextCell.getPositionX();
//            this.y = nextCell.getPositionY();
//        }
//        if (currentCell.isExit()) {
//            this.removeCarFromGrid();
//            this.interrupt();
//            Grid.getInstance().notifyVehicleLeave(this);
//        }
//    }
//
//    private Cell chooseNextCell() {
//        for (Cell neighbor : currentCell.getNeighboursCells()) {
//            if(neighbor.getDirection() ==  currentCell.getDirection()) {
//                if (!neighbor.isOccupied()) {
//                    return neighbor;
//                }
//            }
//        }
//        return null;
//    }
//    private void moveForward(Cell destinationCell) {
//       if(destinationCell.getDirection() == currentCell.getDirection()){
//           moveCar(destinationCell);
//       } else if(destinationCell.isCrossing()) {
//
//       }
//    }
//    private void moveCar(Cell destinationCell) {
//       var current = this.currentCell;
//       var entered = destinationCell.tryEnter(this);
//       if(entered) {
//           this.x = destinationCell.getPositionX();
//           this.y = destinationCell.getPositionY();
//           this.setCurrentCell(destinationCell);
//           current.releaseVehicle();
//       }
//    }
}
