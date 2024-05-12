package com.udesc.dsd.view;

import com.udesc.dsd.controller.GridController;

import javax.swing.*;
import java.awt.*;

public class GridView extends JFrame {
    private final JPanel gridPanel;
    private GridController gridController;


    public GridView(GridController gridController) {
        this.gridController = gridController;
        var rows = gridController.getGrid().getRowCount();
        var columns = gridController.getGrid().getColumCount();
        gridPanel = new JPanel(new GridLayout(rows,columns));
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns;  x++) {
                JLabel cell = new CellLabel(gridController, y, x);
                gridPanel.add(cell);
            }
        }
        add(gridPanel);
        setTitle("Simulação de Trânsito");
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        gridController.start();
    }
}