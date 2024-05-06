package com.udesc.dsd.model;

public enum CellDirection {
    NADA(0),
    ESTRADA_CIMA(1),
    ESTRADA_DIREITA(2),
    ESTRADA_BAIXO(3),
    ESTRADA_ESQUERDA(4),
    CRUZAMENTO_CIMA(5),
    CRUZAMENTO_DIREITA(6),
    CRUZAMENTO_BAIXO(7),
    CRUZAMENTO_ESQUERDA(8),
    CRUZAMENTO_CIMA_DIREITA(9),
    CRUZAMENTO_CIMA_ESQUERDA(10),
    CRUZAMENTO_BAIXO_DIREITA(11),
    CRUZAMENTO_BAIXO_ESQUERDA(12);

    private int direction;

    CellDirection(int direction) {
        this.direction = direction;
    }

    private int getDirection() {
        return this.direction;
    }
}
