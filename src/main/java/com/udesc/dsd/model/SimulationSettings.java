package com.udesc.dsd.model;

public class SimulationSettings {
    public final static int SIMULATION_MODE_SEMAPHORE = 0;
    public final static int SIMULATION_MODE_MONITOR = 1;
    public final static String CAR_IMAGE_PATH = "src/main/resources/car_icon.png";
    public final static String UP_CELL_IMAGE_PATH = "src/main/resources/cell_up.png";
    public final static String RIGHT_CELL_IMAGE_PATH = "src/main/resources/cell_right.png";
    public final static String DOWN_CELL_IMAGE_PATH = "src/main/resources/cell_bottom.png";
    public final static String LEFT_CELL_IMAGE_PATH = "src/main/resources/cell_left.png";
    public final static String STONE_CELL_IMAGE_PATH = "src/main/resources/stone.png";
    public final static String CROSS_CELL_IMAGE_PATH = "src/main/resources/cross.png";
    public static SimulationSettings instance = null;
    private int carQuantity = 0;

    private boolean simulationRunning = false;

    private int mode = SIMULATION_MODE_SEMAPHORE;

    private SimulationSettings() {
    }

    public static SimulationSettings getInstance() {
        if (instance == null) {
            instance = new SimulationSettings();
        }
        return instance;
    }

    public void setSimulationCarQuantity(int quantity) {
        this.carQuantity = quantity;
    }

    public int getCarQuantity() {
        return carQuantity;
    }

    public int getSimulationMode() {
        return this.mode;
    }

    public void setSimulationMode(int mode) {
        this.mode = mode;
    }


    public void stopSimulation() {
        this.simulationRunning = false;
    }

    public void startSimulation() {
        this.simulationRunning = true;
    }

    public boolean isSimulationRunning() {
        return this.simulationRunning;
    }
}
