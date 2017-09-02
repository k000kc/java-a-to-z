package ru.apetrov.Bombermen;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Board {

    /**
     * Высота игрового поля.
     */
    private final int height;

    /**
     * Ширина игрового поля.
     */
    private final int width;

    /**
     * Игровое поле.
     */
    private final Lock[][] board;

    /**
     * Конструктор.
     * @param height высота.
     * @param width ширина.
     */
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new ReentrantLock[height][width];
        this.initBoard();
    }

    /**
     * Инициализация игрового поля.
     */
    private void initBoard() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * геттер.
     * @return высота.
     */
    public int getHeight() {
        return height;
    }

    /**
     * геттер.
     * @return ширина.
     */
    public int getWidth() {
        return width;
    }

    /**
     * геттер.
     * @return игровое поле.
     */
    public Lock[][] getBoard() {
        return board;
    }
}
