package com.udesc.dsd.model;

public class SimulationSettings {
    public static SimulationSettings instance = null;
    private final static int SIMULATION_MODE_SEMAPHORE = 0;
    private final static int SIMULATION_MODE_MONITOR = 1;
    private int carQuantity  = 0;

    private int mode = SIMULATION_MODE_SEMAPHORE;
    private SimulationSettings(){}

    public static SimulationSettings getInstance() {
        if(instance == null){
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

    public void setSimulationMode(int mode) {
       this.mode = mode;
    }
    public int getSimulationMode() {
        return this.mode;
    }

    public int getSimulationModeSemaphore() {
        return SIMULATION_MODE_SEMAPHORE;
    }
    public int getSimulationModeMonitor() {
        return SIMULATION_MODE_MONITOR;
    }
}
