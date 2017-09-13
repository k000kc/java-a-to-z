package ru.apetrov.Bombermen.Players;
import ru.apetrov.Bombermen.Board;
import ru.apetrov.Bombermen.Position;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Bombermen extends Players {

    /**
     * позиция игрока.
     */
    private Position position;

    /**
     * имя игрока.
     */
    private String name;

    /**
     * игровое поле.
     */
    private final Board board;

    /**
     * Блокировка позиции.
     */
    private Lock lock;

    /**
     * Конструктор.
     * @param board игровое поле.
     * @param position позиция игрока.
     * @param name имя игрока.
     */
    public Bombermen(Board board, Position position, String name) {
        this.position = position;
        this.name = name;
        this.board = board;
        this.lock = new ReentrantLock();
    }

    @Override
    public void move() {

    }

    @Override
    public Movement getMovement() {
        return null;
    }



    @Override
    public void run() {
        this.move();
    }
}
