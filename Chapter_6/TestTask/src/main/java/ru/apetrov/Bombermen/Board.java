package ru.apetrov.Bombermen;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Board {

    private final int height;
    private final int width;

    private final Lock[][] board;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new ReentrantLock[height][width];
        this.initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Lock[][] getBoard() {
        return board;
    }
}
