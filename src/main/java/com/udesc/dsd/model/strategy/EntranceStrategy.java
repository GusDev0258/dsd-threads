package com.udesc.dsd.model.strategy;

import com.udesc.dsd.model.Direction;

public class EntranceStrategy {
    public static final int EXIT = 0;
    public static final int ENTRANCE = 1;
    public static final int NOTHING = 3;

    public static int execute(int posX, int posY, int direction, int rowCount, int colCount) {
        switch(direction){
            case Direction.ESTRADA_DIREITA -> {
                if(posX == 0) return ENTRANCE;
                if(posX == colCount - 1) return EXIT;
            }
            case Direction.ESTRADA_ESQUERDA -> {
                if(posX == colCount - 1) return ENTRANCE;
                if(posX == 0) return EXIT;
            }
            case Direction.ESTRADA_CIMA -> {
                if(posY == 0) return EXIT;
                if(posY == rowCount - 1) return ENTRANCE;
            }
            case Direction.ESTRADA_BAIXO -> {
                if(posY == rowCount - 1) return EXIT;
                if(posY == 0) return ENTRANCE;
            }
        }
        return NOTHING;
    }
}

