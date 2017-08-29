package ru.apetrov.Bombermen.Players;

import ru.apetrov.Bombermen.Board;

import java.util.Random;

/**
 * Created by Andrey on 29.08.2017.
 */
public abstract class Players implements Runnable {

    private int x;
    private int y;
    private String name;
    private final Board board;

    public Players(Board board, int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.board = board;
    }

    public void move() {
        Movement result = Movement.getMovment();
        if (result == Movement.UP && this.y > 0) {
            this.y++;
        } else if (result == Movement.DOWN && this.y < this.board.sizeBoard()) {
            this.y--;
        } else if (result == Movement.LEFT && this.x > 0) {
            x--;
        } else if (result == Movement.RIGHT && this.x < this.board.sizeBoard()){
            x++;
        }
    }

    private enum Movement {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        private static Movement getMovment() {
            Random random = new Random();
            int result = random.nextInt(values().length);
            return values()[result];
        }
    }
}
