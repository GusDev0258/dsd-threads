package com.udesc.dsd.model;

public class Vehicle implements Runnable{

    private int x,y;
    private Grid malha;
    private int velocidade;

    public Vehicle(int x, int y, Grid malha, int velocidade) {
        this.x = x;
        this.y = y;
        this.malha = malha;
        this.velocidade = velocidade;
    }

    @Override
    public void run() {

    }
}
