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
    public Players(Board board, Position position, String name) {
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
            Position newPosition = this.getNewPosition();
            if (this.isValidate(newPosition)) {
                Lock newLock = this.board.getBoard()[newPosition.getX()][newPosition.getY()];
                try {
                    if (newLock.tryLock(500, TimeUnit.MILLISECONDS)) {
                        this.lock.unlock();
                        this.lock = newLock;
                        this.position = newPosition;
                        this.board.getBoard()[this.position.getX()][this.position.getY()].lock();
                        System.out.println(this.name + " " + this.position.getX() + " " + position.getY());
                        Thread.sleep(1000);
//                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (true);
    }

    /**
     * Вычисляется новая позицию для движения игрока.
     * @return новая позиция.
     */
    private Position getNewPosition() {
        Position result = null;
        Movement movement = Movement.getRandomMovment();
        if (movement == Movement.UP) {
            result = new Position(position.getX(), position.getY() - 1);
        } else if (movement == Movement.DOWN) {
            result = new Position(position.getX(), position.getY() + 1);
        } else if (movement == Movement.LEFT) {
            result = new Position(position.getX() - 1, position.getY());
        } else if (movement == Movement.RIGHT) {
            result = new Position(position.getX() + 1, position.getY());
        }
        return result;
    }

    /**
     * Проверяем не выходит ли позиция за границы игрового поля.
     * @param position позиция игрока.
     * @return true если позиция не вышла за границы игрового поля.
     */
    private boolean isValidate(Position position) {
        return (position.getX() >= 0 && position.getX() < board.getWidth() && position.getY() >= 0 && position.getY() < board.getHeight());
    }

    /**
     * Варианты движения игрока.
     */
    private enum Movement {

        /**
         * Up, DOWN, LEFT, RIGHT.
         */
        UP, DOWN, LEFT, RIGHT;

        /**
         * Случайным образом выбираем движение игрока.
         * @return направлние движения игрока.
         */
        private static Movement getRandomMovment() {
            Random random = new Random();
            int result = random.nextInt(values().length);
            return values()[result];
        }
    }
}
