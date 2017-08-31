package ru.apetrov.Bombermen.Players;

import ru.apetrov.Bombermen.Board;
import ru.apetrov.Bombermen.Position;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 29.08.2017.
 */
public abstract class Players implements Runnable {

    private Position position;
    private String name;
    private final Board board;

    public Players(Board board, Position position, String name) {
        this.position = position;
        this.name = name;
        this.board = board;
    }

    public void move() {
        do {
            Position newPosition = this.getNewPosition();
            if (this.isValidate(newPosition)) {
                Lock lock = this.board.getBoard()[newPosition.getX()][newPosition.getY()];
                try {
                    if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
                        this.position = newPosition;
                        this.board.getBoard()[this.position.getX()][this.position.getY()].lock();
                        System.out.println(this.name + " " + this.position.getX() + " " + position.getY());
                        Thread.sleep(1000);
//                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        } while (true);
    }

    private Position getNewPosition() {
        Position result = null;
        Movement movement = Movement.getRandomMovment();
        if (movement == Movement.UP) {
            result = new Position(position.getX(), position.getY() - 1);
        } else if (movement == Movement.DOWN) {
            result = new Position(position.getX(), position.getY() + 1);
        } else if (movement == Movement.LEFT) {
            result = new Position(position.getX() - 1, position.getY());
        } else if (movement == Movement.RIGHT){
            result = new Position(position.getX() + 1, position.getY());
        }
        return result;
    }

    private boolean isValidate(Position position) {
        return (position.getX() >= 0 && position.getX() < board.getWidth() && position.getY() >= 0 && position.getY() < board.getHeight());
    }

    private enum Movement {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        private static Movement getRandomMovment() {
            Random random = new Random();
            int result = random.nextInt(values().length);
            return values()[result];
        }
    }
}
