package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.MonitorRoadCell;

public class MonitorCellFactory implements CellFactory{
    @Override
    public Cell createCell(int positionX, int positionY, int direction) {
        var cell = new MonitorRoadCell(positionX, positionY, direction);
        return cell;
    }
}
