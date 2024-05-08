package com.udesc.dsd.model.strategy;

import com.udesc.dsd.model.Direction;
import com.udesc.dsd.model.SimulationSettings;

public class ChooseDirectionImageStrategy {

    public static String execute(int direction) {
        switch(direction) {
            case Direction.NADA -> {
                return SimulationSettings.STONE_CELL_IMAGE_PATH;
            }
            case Direction.ESTRADA_CIMA -> {
                return SimulationSettings.UP_CELL_IMAGE_PATH;
            }
            case Direction.ESTRADA_DIREITA -> {
                return SimulationSettings.RIGHT_CELL_IMAGE_PATH;
            }
            case Direction.ESTRADA_BAIXO -> {
                return SimulationSettings.DOWN_CELL_IMAGE_PATH;
            }
            case Direction.ESTRADA_ESQUERDA -> {
                return SimulationSettings.LEFT_CELL_IMAGE_PATH;
            }
            default -> {
                return SimulationSettings.CROSS_CELL_IMAGE_PATH;
            }
        }
    }
}
