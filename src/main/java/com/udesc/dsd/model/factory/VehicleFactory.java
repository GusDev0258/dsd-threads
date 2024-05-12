package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.Grid;
import com.udesc.dsd.model.Point;
import com.udesc.dsd.model.Vehicle;

import java.util.Random;

public class VehicleFactory {
    public static Vehicle createVehicle(Point point, Cell cell, Grid currentGrid){
        var car = new Vehicle(point.getPositionX(), point.getPositionY(), currentGrid);
        car.setCurrentCell(cell);
        return car;
    }
}
