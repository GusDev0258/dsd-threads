package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.RoadCell;

//TODO: Criar uma maneira de saber se é entrada ou saída
public class CellFactory {
    public static Cell createCell(int positionX, int positionY, int direction) {
        var cell = new RoadCell(positionX, positionY, direction);
        return cell;
    }
}
