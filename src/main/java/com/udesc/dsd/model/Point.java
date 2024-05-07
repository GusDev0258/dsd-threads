package com.udesc.dsd.model;

public class Point {
    private int positionX;
    private int positionY;

    public Point(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return positionX == point.getPositionX() && positionY == point.getPositionY();
    }

    @Override
    public int hashCode() {
        return 31 * positionX + positionY;
    }
}
