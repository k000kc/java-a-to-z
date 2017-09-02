package ru.apetrov.Bombermen;

/**
 * Created by Andrey on 30.08.2017.
 */
public class Position {

    /**
     * координата по X.
     */
    private final int x;

    /**
     * координата по Y.
     */
    private final int y;

    /**
     * Конструктор.
     * @param x x
     * @param y y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер.
     * @return X.
     */
    public int getX() {
        return x;
    }

    /**
     * Геттер.
     * @return Y.
     */
    public int getY() {
        return y;
    }
}
