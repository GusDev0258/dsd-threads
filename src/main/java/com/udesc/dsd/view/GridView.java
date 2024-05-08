package com.udesc.dsd.view;

import com.udesc.dsd.controller.GridController;

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
                CellLabel celula = new CellLabel();
                celula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celula.setAlignmentX(0.5f);
                celula.setBackground(Color.WHITE);
                malhaPanel.add(celula);
            }
        }
        add(malhaPanel);
        setTitle("Simulação de Tráfego");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gridController.startSimulation();
    }
}
