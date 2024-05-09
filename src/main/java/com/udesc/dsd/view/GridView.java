package com.udesc.dsd.view;

import com.udesc.dsd.controller.GridController;
import com.udesc.dsd.model.factory.CellFactory;

import javax.swing.*;
import java.awt.*;

public class GridView extends JFrame {
    private final JPanel malhaPanel;
    private GridController gridController;

    public GridView(GridController gridController) {
        this.gridController = gridController;
        malhaPanel = new JPanel(new GridLayout(gridController.getGrid().getColumCount(),
                gridController.getGrid().getRowCount()));
        for (int i = 0; i < gridController.getGrid().getRowCount(); i++) {
            for (int j = 0; j < gridController.getGrid().getColumCount(); j++) {
                var gridCell = gridController.getGrid().getGridCellAt(j,i);
                JLabel cell = new CellLabel(gridCell);
                malhaPanel.add(cell);
            }
        }
        add(malhaPanel);
        setTitle("Simulação de Trânsito");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gridController.startSimulation();
    }
}