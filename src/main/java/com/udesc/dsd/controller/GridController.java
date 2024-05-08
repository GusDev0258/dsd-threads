package com.udesc.dsd.controller;

import com.udesc.dsd.model.*;
import com.udesc.dsd.model.factory.VehicleFactory;
import com.udesc.dsd.model.observer.Observer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GridController implements Observer {
    private File file = null;
    private Grid grid = Grid.getInstance();
    private SimulationSettings settings = SimulationSettings.getInstance();
    private Map<Long, Vehicle> cars = new HashMap<>();
    public GridController() {
    }
    public File getFile() throws FileNotFoundException {
        if (!this.file.exists()) {
            throw new FileNotFoundException("Arquivo não definido");
        }
        return this.file;
    }
    public void setFile(File file) throws FileNotFoundException {
            this.file = file;
            this.loadGrid();
    }
    public void loadGrid() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.file);
        int columns = scanner.nextInt();
        int rows = scanner.nextInt();
        grid.setColumCount(columns);
        grid.setRowCount(rows);
        var gridMap = new int[rows][columns];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                if (scanner.hasNextInt()) {
                    gridMap[y][x] = scanner.nextInt();
                }
            }
        }
        grid.setGridMap(gridMap);
        scanner.close();
        grid.initializeCells();
    }
    public Grid getGrid() {
        return this.grid;
    }
    public void startSimulation() {
        grid.addObserver(this);
        populateCarsIntoTheGrid();
    }
    public void populateCarsIntoTheGrid() {
        AtomicInteger numActiveCars = new AtomicInteger(0);
        while (numActiveCars.get() < settings.getCarQuantity() && settings.isSimulationRunning()) {
            Cell entrance = findEmptyEntrance();
            if (entrance != null) {
                Vehicle car = VehicleFactory.createVehicle(new Point(entrance.getPositionX(), entrance.getPositionY())
                        , entrance);
                car.start();
                grid.notifyVehicleEnter(car);
                numActiveCars.incrementAndGet();
            }
        }
    }
    private Cell findEmptyEntrance() {
        List<Cell> entrances = grid.getEntrances();
        Collections.shuffle(entrances);
        for (Cell entrance : entrances) {
            if (!entrance.isOccupied()) {
                return entrance;
            }
        }
        return null;
    }
    @Override
    public void onVehicleEnter(Vehicle vehicle) {
        cars.put(vehicle.threadId(), vehicle);
    }
    @Override
    public void onVehicleLeave(Vehicle vehicle) {
        cars.remove(vehicle.threadId());
    }
}