package com.udesc.dsd.model.strategy;

import com.udesc.dsd.model.Direction;
import com.udesc.dsd.model.Grid;
import com.udesc.dsd.model.RoadCell;
import com.udesc.dsd.model.Vehicle;

public class CrossingChoiceStrategy {
    public static void execute(Vehicle vehicle) {
        verifyCrossingChoicePossibilities(vehicle);
    }

    private static void verifyCrossingChoicePossibilities(Vehicle vehicle) {
        resetCrossingPossibilities(vehicle);
        int x = vehicle.getX();
        int y = vehicle.getY();
        switch (vehicle.getCurrentCell().getDirection()) {
            case Direction.ESTRADA_CIMA: //significa que ele vem de baixo, no cruzamento pode escolher entre subir, esquerda ou direita
                if (returnCellDirection(x, y - 3) == Direction.ESTRADA_CIMA) { //valida se a célula acima do cruzamento existe e é uma estrada
                    vehicle.setCrossingUp(true);
                }
                if (returnCellDirection(x + 1, y - 1) == Direction.ESTRADA_DIREITA) { //valida se a célula para a direita do cruzamento existe e é uma estrada
                    vehicle.setCrossingRight(true);
                }
                vehicle.setCrossingDown(false);
                if (returnCellDirection(x - 2, y - 2) == Direction.ESTRADA_ESQUERDA) { //valida se a célula para a esquerda do cruzamento existe e é uma estrada
                    vehicle.setCrossingLeft(true);
                }
                break;
            case Direction.ESTRADA_DIREITA: //significa que ele vem da esquerda, no cruzamento pode escolher entre subir, descer ou direita e é uma estrada
                if (returnCellDirection(x + 2, y - 2) == Direction.ESTRADA_CIMA) {
                    vehicle.setCrossingUp(true);
                }
                if (returnCellDirection(x + 3, y) == Direction.ESTRADA_DIREITA) {
                    vehicle.setCrossingRight(true);
                }
                if (returnCellDirection(x + 1, y + 1) == Direction.ESTRADA_BAIXO) {
                    vehicle.setCrossingDown(true);
                }
                vehicle.setCrossingLeft(false);
                break;
            case Direction.ESTRADA_BAIXO: //significa que ele vem de cima, no cruzamento pode escolher entre descer, esquerda ou direita
                vehicle.setCrossingUp(false);
                if (returnCellDirection(x + 2, y + 2) == Direction.ESTRADA_DIREITA) {
                    vehicle.setCrossingRight(true);
                }
                if (returnCellDirection(x, y + 3) == Direction.ESTRADA_BAIXO) {
                    vehicle.setCrossingDown(true);
                }
                if (returnCellDirection(x - 1, y + 1) == Direction.ESTRADA_ESQUERDA) {
                    vehicle.setCrossingLeft(true);
                }
                break;
            case Direction.ESTRADA_ESQUERDA: //significa que ele vem da direita, no cruzamento pode escolher entre descer, esquerda ou subir
                if (returnCellDirection(x - 1, y - 1) == Direction.ESTRADA_CIMA) {
                    vehicle.setCrossingUp(true);
                }
                vehicle.setCrossingRight(false);
                if (returnCellDirection(x - 2, y + 2) == Direction.ESTRADA_BAIXO) {
                    vehicle.setCrossingDown(true);
                }
                if (returnCellDirection(x - 3, y) == Direction.ESTRADA_ESQUERDA) {
                    vehicle.setCrossingLeft(true);
                }
                break;
        }
    }

    private static void resetCrossingPossibilities(Vehicle vehicle) {
        vehicle.setCrossingUp(false);
        vehicle.setCrossingRight(false);
        vehicle.setCrossingLeft(false);
        vehicle.setCrossingDown(false);
    }

    private static int returnCellDirection(int x, int y) {
        RoadCell cell = Grid.getInstance().getGridCellAt(x, y);
        return cell.getDirection();
    }
}
