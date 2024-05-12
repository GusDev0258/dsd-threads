package com.udesc.dsd.model;

import com.udesc.dsd.model.factory.AbstractCellFactory;
import com.udesc.dsd.model.observer.GridCarObserver;
import com.udesc.dsd.model.strategy.EntranceStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
    private static Grid instance = null;
    private List<RoadCell> entrances = new ArrayList<>();
    private List<RoadCell> exits = new ArrayList<>();
    private Map<Point, RoadCell> cells = new HashMap<>();
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
                    var cell = AbstractCellFactory.createCell(x, y, currentDirection);
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
                RoadCell neighborCell;
                //Cima
                if (y > 0 && gridMap[y - 1][x] != Direction.NADA) {
                    neighborCell = cells.get(new Point(x, y - 1));
                    currentCell.setUpNeighbour(neighborCell);
                    currentCell.addCrossingNeighbor(neighborCell);
                }
                //Baixo
                if (y < gridMap.length - 1 && gridMap[y + 1][x] != Direction.NADA) {
                    neighborCell = cells.get(new Point(x, y + 1));
                    currentCell.setDownNeighbour(neighborCell);
                    currentCell.addCrossingNeighbor(neighborCell);
                }
                //Esquerda
                if (x > 0 && gridMap[y][x - 1] != Direction.NADA) {
                    neighborCell = cells.get(new Point(x - 1, y));
                    currentCell.setLeftNeighbour(neighborCell);
                    currentCell.addCrossingNeighbor(neighborCell);
                }
                //Direita
                if (x < gridMap[y].length - 1 && gridMap[y][x + 1] != Direction.NADA) {
                    neighborCell = cells.get(new Point(x + 1, y));
                    currentCell.setRightNeighbour(neighborCell);
                    currentCell.addCrossingNeighbor(neighborCell);
                }
            }
        }
    }


    private void checkAndMakeCellIsEntranceOrExit(RoadCell cell, int y, int x) {
        var isCellEntrance = EntranceStrategy.execute(x, y, cell.getDirection(), getRowCount(), getColumCount());
        if (isCellEntrance == EntranceStrategy.ENTRANCE) {
            cell.setEntrance(true);
            addEntrance(cell);
        } else if (isCellEntrance == EntranceStrategy.EXIT) {
            cell.setExit(true);
            addExit(cell);
        }
    }


    public RoadCell getGridCellAt(int x, int y) {
        if (cells.size() > 0) {
            return cells.get(new Point(x, y));
        }
        return null;
    }

    public List<RoadCell> getEntrances() {
        return entrances;
    }

    public List<RoadCell> getExits() {
        return exits;
    }

    public Map<Point, RoadCell> getCellsMap() {
        return cells;
    }

    public List<RoadCell> getCells() {
        return cells.values().stream().toList();
    }

    public void addCell(Point coordinates, RoadCell cell) {
        this.cells.put(coordinates, cell);
    }

    public void addEntrance(RoadCell cell) {
        if (cell.isEntrance()) {
            this.entrances.add(cell);
        }
    }

    public void addExit(RoadCell cell) {
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
