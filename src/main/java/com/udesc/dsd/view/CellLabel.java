package com.udesc.dsd.view;

import com.udesc.dsd.controller.GridController;
import com.udesc.dsd.model.RoadCell;
import com.udesc.dsd.model.Vehicle;
import com.udesc.dsd.model.observer.CellObserver;

import javax.swing.*;
import java.awt.*;

public class CellLabel extends JLabel implements CellObserver {
    private final RoadCell labelCell;

    public CellLabel(GridController controller, int y, int x) {
        this.labelCell = controller.getGrid().getGridCellAt(x, y);
        this.labelCell.addObserver(this);
        initializeCellStyle();
    }

    private void initializeCellStyle() {
        if (this.labelCell != null) {
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setLabelIcon(this.labelCell.getCellImagePath());
        }
    }

    private void setLabelIcon(String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        this.setIcon(icon);
        validate();
        repaint();
    }

    @Override
    public void onCarEntered(Vehicle vehicle, RoadCell cell) {
        if (cell.getPositionX() == this.labelCell.getPositionX() && cell.getPositionY() == this.labelCell.getPositionY() && cell.isOccupied() && cell.getVehicle() != null) {
            setLabelIcon(vehicle.getCarImage());
            validate();
            repaint();
        }
    }

    @Override
    public void onCarLeft(Vehicle vehicle, RoadCell cell) {
        if (cell.getPositionX() == this.labelCell.getPositionX() && cell.getPositionY() == this.labelCell.getPositionY()) {
            setLabelIcon(cell.getCellImagePath());
        }
    }
}
