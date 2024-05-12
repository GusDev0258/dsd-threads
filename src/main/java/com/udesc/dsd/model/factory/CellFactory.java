package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.RoadCell;

public interface CellFactory {
    public RoadCell createCell(int positionX, int positionY, int direction);
}
