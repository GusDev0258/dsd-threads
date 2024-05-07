package com.udesc.dsd.model.observer;

import com.udesc.dsd.model.Vehicle;

public interface Observer {
    void onVehicleEnter(Vehicle vehicle);
    void onVehicleLeave(Vehicle vehicle);
}
