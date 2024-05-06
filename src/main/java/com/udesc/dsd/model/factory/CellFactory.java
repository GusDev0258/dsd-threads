package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.CrossingCell;
import com.udesc.dsd.model.DirectionCell;
//TODO: Criar uma maneira de saber se é entrada ou saída
public class CellFactory {
    public static Cell createCell(int positionX, int positionY, int direction) {
       if(direction >= 5 && direction <= 12) {
           return new CrossingCell(positionX,positionY,direction);
       }
       return new DirectionCell(positionX,positionY,direction);
    }
}
