package com.udesc.dsd.model;

public class Car extends Thread{
    private final int startX;
    private final int startY;
    private final int[][] malha;

    public Car(int startX, int startY, int[][] malha) {
       this.startX = startX;
       this.startY = startY;
       this.malha = malha;
    }
    @Override
    public void run() {

    }
}
