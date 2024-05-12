package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.Cell;

public interface CellFactory {
    public Cell createCell(int positionX, int positionY, int direction);
}
