package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.RoadCell;

public class CellFactory {
    public static Cell createCell(int positionX, int positionY, int direction) {
        var cell = new RoadCell(positionX, positionY, direction);
        return cell;
    }
}
