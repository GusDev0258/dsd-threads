package com.udesc.dsd.view;

import com.udesc.dsd.controller.GridController;
import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.SimulationSettings;
import com.udesc.dsd.model.Vehicle;
import com.udesc.dsd.model.observer.CellObserver;

import javax.swing.*;
import java.awt.*;

public class CellLabel extends JLabel implements CellObserver {
    private final Cell labelCell;
    public CellLabel(GridController controller, int j, int i) {
       this.labelCell = controller.getGrid().getGridCellAt(j,i);
       this.labelCell.addObserver(this);
       initializeCellStyle();
    }
    private void initializeCellStyle() {
        if(this.labelCell != null) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setLabelIcon();
        }
    }
    private void setLabelIcon(){
       ImageIcon icon = new ImageIcon(this.labelCell.getCellImagePath());
       this.setIcon(icon);
    }

    @Override
    public void onCarEntered(Vehicle vehicle, Cell cell) {
       if(cell == this.labelCell) {
           ImageIcon icon = new ImageIcon(SimulationSettings.CAR_IMAGE_PATH);
           this.setIcon(icon);
       }
    }

    @Override
    public void onCarLeft(Vehicle vehicle, Cell cell) {
        if(cell == this.labelCell) {
            setLabelIcon();
        }
    }
}
