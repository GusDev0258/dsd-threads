package com.udesc.dsd.model.factory;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.SimulationSettings;

public class AbstractCellFactory {
    public static Cell createCell(int positionX, int positionY, int direction) {
       if(SimulationSettings.getInstance().getSimulationMode() == SimulationSettings.SIMULATION_MODE_SEMAPHORE ) {
          return new SemaphoreCellFactory().createCell(positionX,positionY,direction);
       }else {
           return new MonitorCellFactory().createCell(positionX, positionY, direction);
       }
    }
}
