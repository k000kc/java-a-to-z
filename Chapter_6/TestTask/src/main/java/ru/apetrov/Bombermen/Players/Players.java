package ru.apetrov.Bombermen.Players;

import ru.apetrov.Bombermen.Board;
import ru.apetrov.Bombermen.Position;

/**
 * Created by Andrey on 29.08.2017.
 */
public abstract class Players implements Runnable, Unit {

    /**
     * Метод передвигает игрока на новую случайную позицию, изначально проверяя,
     * не выходит ли новая позиция за границы игрового поля. Затем пытается заблокировать новую позицию,
     * если блокировка удалась, тогда присваивает новую позицию игроку, и снимает блокировку со старой позиции.
     */
    public abstract void move();

    /**
     * Вычисляется новая позицию для движения игрока.
     * @return новая позиция.
     */
    protected Position getNewPosition(Position position) {
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
    protected boolean isValidate(Position position, Board board) {
        return (position.getX() >= 0 && position.getX() < board.getWidth() && position.getY() >= 0 && position.getY() < board.getHeight());
    }
}
