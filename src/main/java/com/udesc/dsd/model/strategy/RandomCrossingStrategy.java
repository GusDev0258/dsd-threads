package com.udesc.dsd.model.strategy;

import com.udesc.dsd.model.Vehicle;

import java.util.Random;

import static com.udesc.dsd.model.strategy.CrossingStepsStrategy.*;

public class RandomCrossingStrategy {
    private static Random random = new Random();

    public static String execute(Vehicle vehicle) {
        return crossingChoice(vehicle);
    }

    private static String crossingChoice(Vehicle vehicle) {
        makeCrossingPossibilities(vehicle);
        return vehicle.getCrossingPossibilities().get(random.nextInt(0,
                vehicle.getCrossingPossibilities().size()));
    }

    //popula a lista com possibilidades de escolha do veículo
    private static void makeCrossingPossibilities(Vehicle vehicle) {
        vehicle.getCrossingPossibilities().clear();
        if (vehicle.isCrossingUp()) {
            vehicle.addCrossingPobility(CROSS_POSSIBILITY_UP);
        }
        if (vehicle.isCrossingRight()) {
            vehicle.addCrossingPobility(CROSS_POSSIBILITY_RIGHT);
        }
        if (vehicle.isCrossingDown()) {
            vehicle.addCrossingPobility(CROSS_POSSIBILITY_DOWN);
        }
        if (vehicle.isCrossingLeft()) {
            vehicle.addCrossingPobility(CROSS_POSSIBILITY_LEFT);
        }
    }

}
