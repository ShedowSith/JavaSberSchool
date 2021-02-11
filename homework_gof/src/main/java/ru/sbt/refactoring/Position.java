package ru.sbt.refactoring;

public class Position {
    private int x;
    private int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position changeX(int x) {
        this.x += x;
        return this;
    }

    public Position changeY(int y) {
        this.y += y;
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
