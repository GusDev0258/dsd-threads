package com.udesc.dsd.view;

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
            case Grid.ESTRADA_CIMA:
            case Grid.ESTRADA_DIREITA:
            case Grid.ESTRADA_BAIXO:
            case Grid.ESTRADA_ESQUERDA:
                return Color.GRAY;
            case Grid.CRUZAMENTO_CIMA:
            case Grid.CRUZAMENTO_BAIXO:
            case Grid.CRUZAMENTO_ESQUERDA:
            case Grid.CRUZAMENTO_DIREITA:
            case Grid.CRUZAMENTO_CIMA_DIREITA:
            case Grid.CRUZAMENTO_CIMA_ESQUERDA:
            case Grid.CRUZAMENTO_BAIXO_DIREITA:
            case Grid.CRUZAMENTO_BAIXO_ESQUERDA:
                return Color.YELLOW;
            default:
                return Color.WHITE;
        }
    }
}
