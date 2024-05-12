package com.udesc.dsd.model.strategy;

import com.udesc.dsd.model.Direction;
import com.udesc.dsd.model.Grid;
import com.udesc.dsd.model.RoadCell;
import com.udesc.dsd.model.Vehicle;

public class CrossingStepsStrategy {
    public static final String CROSS_POSSIBILITY_UP = "crossingUp";
    public static final String CROSS_POSSIBILITY_DOWN = "crossingDown";
    public static final String CROSS_POSSIBILITY_LEFT = "crossingLeft";
    public static final String CROSS_POSSIBILITY_RIGHT = "crossingRight";

    public static void execute(Vehicle vehicle, String destiny) {
        makeCrossingSteps(vehicle, destiny);
    }

    private static void makeCrossingSteps(Vehicle vehicle, String destino) {
        //deve gravar em ordem
        vehicle.getCrossingPath().clear();
        int x = vehicle.getX();
        int y = vehicle.getY();
        switch (destino) {
            case CROSS_POSSIBILITY_UP:
                switch (vehicle.getCurrentCell().getDirection()) {
                    case Direction.ESTRADA_CIMA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x, y - 1));
                        vehicle.setStep2(getGridCellBasedOnCoordinates(x, y - 2));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x, y - 3));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getStep2());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                    case Direction.ESTRADA_DIREITA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x + 1, y));
                        vehicle.setStep2(getGridCellBasedOnCoordinates(x + 2, y));
                        vehicle.setStep3(getGridCellBasedOnCoordinates(x + 2, y - 1));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x + 2, y - 2));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getStep2());
                        vehicle.addStepsToCrossingPath(vehicle.getStep3());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                    case Direction.ESTRADA_ESQUERDA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x - 1, y));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x - 1, y - 1));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                }
                break;
            case CROSS_POSSIBILITY_RIGHT:
                switch (vehicle.getCurrentCell().getDirection()) {
                    case Direction.ESTRADA_CIMA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x, y - 1));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x + 1, y - 1));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                    case Direction.ESTRADA_DIREITA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x + 1, y));
                        vehicle.setStep2(getGridCellBasedOnCoordinates(x + 2, y));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x + 3, y));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getStep2());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                    case Direction.ESTRADA_BAIXO:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x, y + 1));
                        vehicle.setStep2(getGridCellBasedOnCoordinates(x, y + 2));
                        vehicle.setStep3(getGridCellBasedOnCoordinates(x + 1, y + 2));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x + 2, y + 2));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getStep2());
                        vehicle.addStepsToCrossingPath(vehicle.getStep3());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                }
                break;
            case CROSS_POSSIBILITY_DOWN:
                switch (vehicle.getCurrentCell().getDirection()) {
                    case Direction.ESTRADA_DIREITA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x + 1, y));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x + 1, y + 1));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                    case Direction.ESTRADA_BAIXO:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x, y + 1));
                        vehicle.setStep2(getGridCellBasedOnCoordinates(x, y + 2));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x, y + 3));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getStep2());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                    case Direction.ESTRADA_ESQUERDA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x - 1, y));
                        vehicle.setStep2(getGridCellBasedOnCoordinates(x - 2, y));
                        vehicle.setStep3(getGridCellBasedOnCoordinates(x - 2, y + 1));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x - 2, y + 2));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getStep2());
                        vehicle.addStepsToCrossingPath(vehicle.getStep3());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                }
                break;
            case CROSS_POSSIBILITY_LEFT:
                switch (vehicle.getCurrentCell().getDirection()) {
                    case Direction.ESTRADA_CIMA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x, y - 1));
                        vehicle.setStep2(getGridCellBasedOnCoordinates(x, y - 2));
                        vehicle.setStep3(getGridCellBasedOnCoordinates(x - 1, y - 2));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x - 2, y - 2));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getStep2());
                        vehicle.addStepsToCrossingPath(vehicle.getStep3());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                    case Direction.ESTRADA_BAIXO:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x, y + 1));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x - 1, y + 1));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                    case Direction.ESTRADA_ESQUERDA:
                        vehicle.setStep1(getGridCellBasedOnCoordinates(x - 1, y));
                        vehicle.setStep2(getGridCellBasedOnCoordinates(x - 2, y));
                        vehicle.setDestiny(getGridCellBasedOnCoordinates(x - 3, y));
                        vehicle.addStepsToCrossingPath(vehicle.getStep1());
                        vehicle.addStepsToCrossingPath(vehicle.getStep2());
                        vehicle.addStepsToCrossingPath(vehicle.getDestiny());
                        break;
                }
                break;
        }
    }

    private static RoadCell getGridCellBasedOnCoordinates(int x, int y) {
        return Grid.getInstance().getGridCellAt(x, y);
    }
}
