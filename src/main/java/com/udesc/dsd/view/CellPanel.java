package com.udesc.dsd.view;

import com.udesc.dsd.model.Cell;
import com.udesc.dsd.model.Vehicle;
import com.udesc.dsd.model.observer.Observer;

import javax.swing.*;

public class CellPanel extends JPanel implements Observer {
    private Cell cell;
    private JLabel icon;

    public CellPanel(Cell cell) {
        this.cell = cell;
        this.icon = new JLabel(new ImageIcon(cell.getVehicleImagePath()));
        this.add(icon);
    }

    public Cell getCell() {
        return this.cell;
    }

    @Override
    public void onVehicleEnter(Vehicle vehicle) {
       this.icon.setIcon(new ImageIcon(this.getCell().getVehicleImagePath()));
       this.repaint();
    }

    @Override
    public void onVehicleLeave(Vehicle vehicle) {
        this.icon.setIcon(new ImageIcon(this.getCell().getVehicleImagePath()));
        this.repaint();
    }
}
