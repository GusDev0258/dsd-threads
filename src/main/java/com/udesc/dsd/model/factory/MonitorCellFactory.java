package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.RoadCell;
import com.udesc.dsd.model.MonitorRoadCell;

public class MonitorCellFactory implements CellFactory{
    @Override
    public RoadCell createCell(int positionX, int positionY, int direction) {
        var cell = new MonitorRoadCell(positionX, positionY, direction);
        return cell;
    }
}
