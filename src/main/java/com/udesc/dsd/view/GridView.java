package com.udesc.dsd.view;
import com.udesc.dsd.controller.GridController;

import javax.swing.*;
import java.awt.*;

public class GridView extends JFrame {
    private final JPanel gridPanel;
    private GridController gridController;

    public GridView(GridController gridController) {
        this.gridController = gridController;
        gridPanel = new JPanel(new GridLayout(gridController.getGrid().getColumCount(),
                gridController.getGrid().getRowCount()));
        for (int i = 0; i < gridController.getGrid().getRowCount(); i++) {
            for (int j = 0; j < gridController.getGrid().getColumCount(); j++) {
                JLabel cell = new CellLabel(gridController,j,i);
                gridPanel.add(cell);
            }
        }
        add(gridPanel);
        setTitle("Simulação de Trânsito");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gridController.startSimulation();
    }
}