package com.udesc.dsd.model.observer;

import com.udesc.dsd.model.RoadCell;
import com.udesc.dsd.model.Vehicle;

public interface CellObserver {
    void onCarEntered(Vehicle vehicle, RoadCell cell);
    void onCarLeft(Vehicle vehicle, RoadCell cell);
}
