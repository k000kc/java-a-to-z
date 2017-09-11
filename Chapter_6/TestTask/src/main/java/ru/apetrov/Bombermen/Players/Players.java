package ru.apetrov.Bombermen.Players;

import ru.apetrov.Bombermen.Position;

/**
 * Created by Andrey on 29.08.2017.
 */
public abstract class Players implements Runnable {

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
    public abstract Position getNewPosition();

    /**
     * Проверяем не выходит ли позиция за границы игрового поля.
     * @param position позиция игрока.
     * @return true если позиция не вышла за границы игрового поля.
     */
    abstract public boolean isValidate(Position position);
}
