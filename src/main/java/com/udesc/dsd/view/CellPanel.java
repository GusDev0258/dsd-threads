package com.udesc.dsd.view;

import com.udesc.dsd.model.Cell;

import javax.swing.*;

public class CellPanel extends JPanel {
    private Cell cell;
    public CellPanel(Cell cell) {
       this.cell = cell;
    }

    public Cell getCell() {
        return this.cell;
    }
}
