package com.udesc.dsd.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    private static Grid instance = null;

    private List<Cell> entrances = new ArrayList<>();
    private List<Cell> exits = new ArrayList<>();

    private int rowCount;
    private int columCount;

    private Cell[][] cells;
    private int[][] gridMap;
    private Grid(){
    }

    public static Grid getInstance() {
        if(instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public List<Cell> getEntrances() {
        return entrances;
    }

    public void setEntrances(List<Cell> entrances) {
        this.entrances = entrances;
    }

    public List<Cell> getExits() {
        return exits;
    }

    public void setExits(List<Cell> exits) {
        this.exits = exits;
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

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int[][] getGridMap() {
        return gridMap;
    }

    public void setGridMap(int[][] gridMap) {
        this.gridMap = gridMap;
    }
}
