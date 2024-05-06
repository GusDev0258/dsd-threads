package com.udesc.dsd.view;

import com.udesc.dsd.model.Direction;
import com.udesc.dsd.model.Grid;
import com.udesc.dsd.model.factory.CellFactory;
import com.udesc.dsd.model.strategy.EntranceStrategy;

import javax.swing.*;
import java.awt.*;

public class GridView extends JFrame {
    private final JPanel malhaPanel;

    private final Grid grid = Grid.getInstance();
    public GridView() {
        malhaPanel = new JPanel(new GridLayout(grid.getRowCount(), grid.getColumCount()));
        for(int i = 0; i < grid.getGridMap().length; i++) {
            for (int j = 0; j < grid.getGridMap()[i].length; j++) {
                var currentDirection = grid.getGridMap()[i][j];
                var cell = CellFactory.createCell(i, j, currentDirection);
                var isCellEntrance = EntranceStrategy.execute(i, j, currentDirection, grid.getRowCount(),
                        grid.getColumCount());
                if(isCellEntrance == EntranceStrategy.ENTRANCE) {
                    cell.setEntrance(true);
                    grid.addEntrance(cell);
                }else if(isCellEntrance == EntranceStrategy.EXIT) {
                    cell.setExit(true);
                    grid.addExit(cell);
                }
                grid.addCell(cell);
                JPanel celula = new CellPanel(cell);
                celula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celula.setBackground(getColorFromValue(currentDirection));
                malhaPanel.add(celula);
            }
        }
        add(malhaPanel);
        setTitle("Malha");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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
