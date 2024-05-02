package com.udesc.dsd.view;

import com.udesc.dsd.model.Malha;

import javax.swing.*;
import java.awt.*;

public class MalhaView extends JFrame {
    private final JPanel malhaPanel;
    private final int[][] malha;

    public MalhaView(int[][] malha) {
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
            case Malha.ESTRADA_CIMA:
            case Malha.ESTRADA_DIREITA:
            case Malha.ESTRADA_BAIXO:
            case Malha.ESTRADA_ESQUERDA:
                return Color.GRAY;
            case Malha.CRUZAMENTO_CIMA:
            case Malha.CRUZAMENTO_BAIXO:
            case Malha.CRUZAMENTO_ESQUERDA:
            case Malha.CRUZAMENTO_DIREITA:
            case Malha.CRUZAMENTO_CIMA_DIREITA:
            case Malha.CRUZAMENTO_CIMA_ESQUERDA:
            case Malha.CRUZAMENTO_BAIXO_DIREITA:
            case Malha.CRUZAMENTO_BAIXO_ESQUERDA:
                return Color.YELLOW;
            default:
                return Color.WHITE;
        }
    }
}
