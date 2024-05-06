package com.udesc.dsd.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static Grid instance = null;
    private List<Cell> entrances = new ArrayList<>();
    private List<Cell> exits = new ArrayList<>();
    private List<Cell> cells = new ArrayList<>();

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

    public List<Cell> getEntrances() {
        return entrances;
    }

    public List<Cell> getExits() {
        return exits;
    }

    public List<Cell> getCells() {
        return cells;
    }
    public void addCell(Cell cell) {
        this.cells.add(cell);
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
