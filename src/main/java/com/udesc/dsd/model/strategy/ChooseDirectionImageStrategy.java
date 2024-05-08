package com.udesc.dsd.model.strategy;

import com.udesc.dsd.model.Direction;

public class ChooseDirectionImageStrategy {
    private final static String UP_CELL_IMAGE_PATH = "src/main/resources/cell_up.png";
    private final static String RIGHT_CELL_IMAGE_PATH = "src/main/resources/cell_right.png";
    private final static String DOWN_CELL_IMAGE_PATH ="src/main/resources/cell_bottom.png";
    private final static String LEFT_CELL_IMAGE_PATH ="src/main/resources/cell_left.png";
    private final static String STONE_CELL_IMAGE_PATH = "src/main/resources/stone.png";
    private final static String CROSS_CELL_IMAGE_PATH = "src/main/resources/cross.png";
    public static String execute(int direction) {
        switch(direction) {
            case Direction.NADA -> {
                return STONE_CELL_IMAGE_PATH;
            }
            case Direction.ESTRADA_CIMA -> {
                return UP_CELL_IMAGE_PATH;
            }
            case Direction.ESTRADA_DIREITA -> {
                return RIGHT_CELL_IMAGE_PATH;
            }
            case Direction.ESTRADA_BAIXO -> {
                return DOWN_CELL_IMAGE_PATH;
            }
            case Direction.ESTRADA_ESQUERDA -> {
                return LEFT_CELL_IMAGE_PATH;
            }
            default -> {
                return CROSS_CELL_IMAGE_PATH;
            }
        }
    }
}
