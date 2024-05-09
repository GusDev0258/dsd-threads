package com.udesc.dsd.model;

import com.udesc.dsd.model.factory.CellFactory;
import com.udesc.dsd.model.observer.GridCarObserver;
import com.udesc.dsd.model.strategy.EntranceStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    private static Grid instance = null;
    private List<Cell> entrances = new ArrayList<>();
    private List<Cell> exits = new ArrayList<>();
    private Map<Point, Cell> cells = new HashMap<>();
    private int rowCount;
    private int columCount;
    private int[][] gridMap;
    private List<GridCarObserver> observers = new ArrayList<>();

    private Grid() {
    }

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public void addObserver(GridCarObserver observer) {
        observers.add(observer);
    }

    public void notifyVehicleEnter(Vehicle vehicle) {
        for (GridCarObserver obs : observers) {
            obs.onVehicleEnter(vehicle);
        }
    }

    public void notifyVehicleLeave(Vehicle vehicle) {
        for (GridCarObserver obs : observers) {
            obs.onVehicleLeave(vehicle);
        }
    }

    public void initializeCells() {
        if (gridMap.length != 0) {
            for (int y = 0; y < getRowCount(); y++) {
                for (int x = 0; x < getColumCount(); x++) {
                    var currentDirection = gridMap[y][x];
                    var cell = CellFactory.createCell(x, y, currentDirection);
                    this.checkAndMakeCellIsEntranceOrExit(cell, y, x);
                    cells.put(new Point(x, y), cell);
                }
            }
            initializeCellNeighbours();
        }
    }



    public void initializeCellNeighbours() {
        if (!cells.isEmpty() && gridMap.length != 0) {
            for (Point point : cells.keySet()) {
                int x = point.getPositionX();
                int y = point.getPositionY();
                var currentCell = cells.get(point);
                //Cima
                if (y > 0 && gridMap[y - 1][x] != Direction.NADA) {
                    currentCell.setUpNeighbor(cells.get(new Point(x, y - 1)));
                }
                //Baixo
                if (y < gridMap.length - 1 && gridMap[y + 1][x] != Direction.NADA) {
                    currentCell.setDownNeighbor(cells.get(new Point(x, y + 1)));
                }
                //Esquerda
                if (x > 0 && gridMap[y][x - 1] != Direction.NADA) {
                    currentCell.setLeftNeighbor(cells.get(new Point(x - 1, y)));
                }
                //Direita
                if (x < gridMap[y].length - 1 && gridMap[y][x + 1] != Direction.NADA) {
                    currentCell.setRightNeighbor(cells.get(new Point(x + 1, y)));
                }
            }
        }
    }


    private void checkAndMakeCellIsEntranceOrExit(Cell cell, int y, int x) {
        var isCellEntrance = EntranceStrategy.execute(x, y, cell.getDirection(), getRowCount(), getColumCount());
        if (isCellEntrance == EntranceStrategy.ENTRANCE) {
            cell.setEntrance(true);
            addEntrance(cell);
        } else if (isCellEntrance == EntranceStrategy.EXIT) {
            cell.setExit(true);
            addExit(cell);
        }
    }


    public Cell getGridCellAt(int x, int y) {
        if (cells.size() > 0) {
            return cells.get(new Point(x, y));
        }
        return null;
    }

    public List<Cell> getEntrances() {
        return entrances;
    }

    public List<Cell> getExits() {
        return exits;
    }

    public Map<Point, Cell> getCellsMap() {
        return cells;
    }

    public List<Cell> getCells() {
        return cells.values().stream().toList();
    }

    public void addCell(Point coordinates, Cell cell) {
        this.cells.put(coordinates, cell);
    }

    public void addEntrance(Cell cell) {
        if (cell.isEntrance()) {
            this.entrances.add(cell);
        }
    }

    public void addExit(Cell cell) {
        if (cell.isExit()) {
            this.exits.add(cell);
        }
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumCount() {
        return columCount;
    }

    public void setColumCount(int columCount) {
        this.columCount = columCount;
    }

    public int[][] getGridMap() {
        return gridMap;
    }

    public void setGridMap(int[][] gridMap) {
        this.gridMap = gridMap;
    }
}
