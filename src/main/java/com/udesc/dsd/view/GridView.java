package com.udesc.dsd.view;

import com.udesc.dsd.controller.GridController;
import com.udesc.dsd.model.Direction;
import com.udesc.dsd.model.factory.CellFactory;

import javax.swing.*;
import java.awt.*;

public class GridView extends JFrame {
    private final JPanel malhaPanel;
    private GridController gridController;
    public GridView(GridController gridController) {
        this.gridController = gridController;
        malhaPanel =
                new JPanel(new GridLayout(gridController.getGrid().getRowCount(), gridController.getGrid().getColumCount()));
        for (int i = 0; i < gridController.getGrid().getGridMap().length; i++) {
            for (int j = 0; j < gridController.getGrid().getGridMap()[i].length; j++) {
                var currentDirection = gridController.getGrid().getGridMap()[i][j];
                var cell = CellFactory.createCell(i, j, currentDirection);
                JPanel celula = new CellPanel(cell);
                celula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celula.setBackground(getColorFromValue(currentDirection));
                malhaPanel.add(celula);
            }
        }
        add(malhaPanel);
        setTitle("Simulação de Tráfego");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gridController.startSimulation();
    }
    private Color getColorFromValue(int value) {
        switch (value) {
            case Direction.ESTRADA_CIMA:
                return Color.BLUE;
            case Direction.ESTRADA_DIREITA:
                return Color.RED;
            case Direction.ESTRADA_BAIXO:
                return Color.GREEN;
            case Direction.ESTRADA_ESQUERDA:
                return Color.GRAY;
            case Direction.CRUZAMENTO_CIMA:
            case Direction.CRUZAMENTO_BAIXO:
            case Direction.CRUZAMENTO_ESQUERDA:
            case Direction.CRUZAMENTO_DIREITA:
            case Direction.CRUZAMENTO_CIMA_DIREITA:
            case Direction.CRUZAMENTO_CIMA_ESQUERDA:
            case Direction.CRUZAMENTO_BAIXO_DIREITA:
            case Direction.CRUZAMENTO_BAIXO_ESQUERDA:
                return Color.YELLOW;
            default:
                return Color.WHITE;
        }
    }
}
