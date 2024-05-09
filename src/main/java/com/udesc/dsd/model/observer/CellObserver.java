package com.udesc.dsd.model.observer;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.Vehicle;

public interface CellObserver {
    void onCarEntered(Vehicle vehicle, Cell cell);
    void onCarLeft(Vehicle vehicle, Cell cell);
}
