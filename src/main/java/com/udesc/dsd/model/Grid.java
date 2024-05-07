package com.udesc.dsd.model;

import com.udesc.dsd.model.factory.CellFactory;
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

    private Grid() {
    }

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public void initializeCells() {
        if (gridMap.length != 0) {
            for (int i = 0; i < getRowCount(); i++) {
                for (int j = 0; i < getColumCount(); j++) {
                    var currentDirection = gridMap[i][j];
                    if (currentDirection != Direction.NADA) {
                        var cell = CellFactory.createCell(i, j, currentDirection);
                        this.checkAndMakeCellIsEntranceOrExit(cell, i, j);
                        cells.put(new Point(i, j), cell);

                    }
                }
            }
        }
    }

    public void initializeCellNeighbours() {
        if (cells.size() != 0 && gridMap.length != 0) {
            for (Point point : cells.keySet()) {
                int x = point.getPositionX();
                int y = point.getPositionY();
                if (y > 0 && gridMap[y - 1][x] != Direction.NADA) {
                    cells.get(point).addNeighbour(cells.get(new Point(x + 1, y)));
                }
                if (x < gridMap[y].length - 1 && gridMap[y][x + 1] != Direction.NADA) {
                    cells.get(point).addNeighbour(cells.get(new Point(x + 1, y)));
                }
                if (y < gridMap.length - 1 && gridMap[y + 1][x] != Direction.NADA) {
                    cells.get(point).addNeighbour(cells.get(new Point(x, y + 1)));
                }
                if (x > 0 && gridMap[y][x - 1] != Direction.NADA) {
                    cells.get(point).addNeighbour(cells.get(new Point(x - 1, y)));
                }
            }
        }
    }

    private void checkAndMakeCellIsEntranceOrExit(Cell cell, int i, int j) {
        var isCellEntrance = EntranceStrategy.execute(i, j, cell.getDirection(), getRowCount(),
                getColumCount());
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
