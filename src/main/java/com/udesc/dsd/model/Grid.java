package com.udesc.dsd.model;

public class Grid {
    public static final int NADA = 0;
    public static final int ESTRADA_CIMA = 1;
    public static final int ESTRADA_DIREITA = 2;
    public static final int ESTRADA_BAIXO = 3;
    public static final int ESTRADA_ESQUERDA = 4;
    public static final int CRUZAMENTO_CIMA = 5;
    public static final int CRUZAMENTO_DIREITA = 6;
    public static final int CRUZAMENTO_BAIXO = 7;
    public static final int CRUZAMENTO_ESQUERDA = 8;
    public static final int CRUZAMENTO_CIMA_DIREITA = 9;
    public static final int CRUZAMENTO_CIMA_ESQUERDA = 10;
    public static final int CRUZAMENTO_BAIXO_DIREITA = 11;
    public static final int CRUZAMENTO_BAIXO_ESQUERDA = 12;

    private int rows, columns;
    private Cell[][] cells;
}
