package ru.apetrov.Bombermen.Players;

import ru.apetrov.Bombermen.Board;
import ru.apetrov.Bombermen.Position;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 11.09.2017.
 */
public class Monster extends Players {

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
    public Monster(Board board, Position position, String name) {
        this.position = position;
        this.name = name;
        this.board = board;
        this.lock = new ReentrantLock();
    }

    /**
     * Метод передвигает игрока на новую случайную позицию, изначально проверяя,
     * не выходит ли новая позиция за границы игрового поля. Затем пытается заблокировать новую позицию,
     * если блокировка удалась, тогда присваивает новую позицию игроку, и снимает блокировку со старой позиции.
     */
    public void move() {
        this.lock.lock();
        do {
            Position newPosition = this.getNewPosition(this.position);
            if (this.isValidate(newPosition, this.board)) {
                Lock newLock = this.board.getBoard()[newPosition.getX()][newPosition.getY()];
                try {
                    if (newLock.tryLock(500, TimeUnit.MILLISECONDS)) {
                        this.lock.unlock();
                        this.lock = newLock;
                        this.position = newPosition;
                        this.board.getBoard()[this.position.getX()][this.position.getY()].lock();
                        System.out.println(this.name + " " + this.position.getX() + " " + position.getY());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    }

    @Override
    public void run() {
        this.move();
    }
}
