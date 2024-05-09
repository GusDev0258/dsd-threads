package com.udesc.dsd.model;

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
                Thread.sleep(3000);
                move();
                System.out.println("movimentei!" + this.threadId());
            } catch (InterruptedException exception) {
                System.out.println("deu ruim");
                Thread.currentThread().interrupt();
            }
        }
    }

    public void move() {
        if (!getCurrentCell().isEntrance() ) {
            Cell nextCell = null;
            switch (getCurrentCell().getDirection()) {
                case Direction.ESTRADA_CIMA:
                case Direction.ESTRADA_DIREITA:
                case Direction.ESTRADA_BAIXO:
                case Direction.ESTRADA_ESQUERDA:
                    nextCell = chooseNextCellBasedOnDirection();
                    break;
                default:
                    break;
            }

            if (nextCell != null ) {
                moveCar(nextCell);
            }
        } else {
            moveCar(chooseNextCellBasedOnDirection());
        }

        if (getCurrentCell().isExit()) {
            removeCarFromGrid();
        }
    }

    private Cell chooseNextCellBasedOnDirection() {
        return currentCell.getValidAdjacentCell(getCurrentCell().getDirection());
    }

    private void moveCar(Cell nextCell) {
        var enterCell = nextCell.tryEnter(this);
        if (enterCell) {
            setX(nextCell.getPositionX());
            setY(nextCell.getPositionY());
            this.currentCell.releaseVehicle();
            this.setCurrentCell(nextCell);
        }
    }
}
