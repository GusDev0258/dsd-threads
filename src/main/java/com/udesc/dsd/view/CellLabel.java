package com.udesc.dsd.view;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.SimulationSettings;
import com.udesc.dsd.model.Vehicle;
import com.udesc.dsd.model.observer.Observer;

import javax.swing.*;
import java.awt.*;

public class CellLabel extends JLabel {
    private final Cell labelCell;
    public CellLabel(Cell cell) {
       this.labelCell = cell;
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
}
