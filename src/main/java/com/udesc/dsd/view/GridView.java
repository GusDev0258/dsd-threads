package com.udesc.dsd.view;

import com.udesc.dsd.model.Direction;
import com.udesc.dsd.model.Grid;

import javax.swing.*;
import java.awt.*;

public class GridView extends JFrame {
    private final JPanel malhaPanel;
    private final int[][] malha;

    public GridView(int[][] malha) {
        this.malha = malha;
        malhaPanel = new JPanel(new GridLayout(malha.length, malha[0].length));
        for(int i = 0; i < malha.length; i++) {
            for (int j = 0; j < malha[i].length; j++) {
                JPanel celula = new JPanel();
                celula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celula.setBackground(getColorFromValue(malha[i][j]));
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
            case Direction.ESTRADA_DIREITA:
            case Direction.ESTRADA_BAIXO:
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
