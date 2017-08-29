package ru.apetrov.Bombermen;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Board {

    private final int size;
    private final Lock[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new ReentrantLock[size][size];
        this.initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    public int sizeBoard() {
        return this.size;
    }
}
