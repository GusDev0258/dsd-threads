package com.udesc.dsd.controller;

import com.udesc.dsd.model.*;
import com.udesc.dsd.model.factory.VehicleFactory;
import com.udesc.dsd.model.observer.GridCarObserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GridController implements GridCarObserver, Runnable {
    private File file = null;
    private Grid grid = Grid.getInstance();
    private SimulationSettings settings = SimulationSettings.getInstance();
    private Map<Long, Vehicle> cars = new HashMap<>();
    private int carQtd = 0;

    private Thread workerThread;
    public GridController() {
        workerThread = new Thread(this);
        workerThread.start();
    }

    public void close() {
        workerThread.interrupt();
        settings.stopSimulation();
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
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
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
        while (carQtd < settings.getCarQuantity() && settings.isSimulationRunning()) {
            Cell entrance = findEmptyEntrance();
            if (entrance != null ) {
                Vehicle car = VehicleFactory.createVehicle(new Point(entrance.getPositionX(), entrance.getPositionY())
                        , entrance, grid);
                entrance.tryEnter(car);
                car.start();
                grid.notifyVehicleEnter(car);
                carQtd++;
            }
        }
    }

    private Cell findEmptyEntrance() {
        List<Cell> entrances = grid.getEntrances();
        var randomIndex = new Random().nextInt(0, entrances.size());
        var entrance = entrances.get(randomIndex);
        if (!entrance.isOccupied()) {
            return entrance;
        }
        return null;
    }

    public List<Cell> getGridCells() {
        return grid.getCells();
    }

    @Override
    public void onVehicleEnter(Vehicle vehicle) {
        cars.put(vehicle.threadId(), vehicle);
    }

    @Override
    public void onVehicleLeave(Vehicle vehicle) {
        cars.remove(vehicle.threadId());
    }

    @Override
    public void run() {
        while (true) {
            if (shouldPopulateMoreCars()) {
                populateCarsIntoTheGrid();
            }
            try {
                    Thread.sleep(2000 );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private boolean shouldPopulateMoreCars() {
        return settings.isSimulationRunning();
    }
}