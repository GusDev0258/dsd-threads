package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.RoadCell;
import com.udesc.dsd.model.SemaphoreRoadCell;

public class SemaphoreCellFactory implements CellFactory {
    @Override
    public RoadCell createCell(int positionX, int positionY, int direction) {
        var cell = new SemaphoreRoadCell(positionX, positionY, direction);
        return cell;
    }
}
