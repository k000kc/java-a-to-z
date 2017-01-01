package ru.apetrov;

/**
 * Класс фигуры.
 */
public abstract class Figure {

    /**
     * Позиция на шахматной доске.
     */
    private Position position;

    /**
     * Конструктор.
     * @param position Позиция на шахматной доске.
     */
    public Figure(Position position) {
        this.position = position;
    }

    /**
     * Геттер.
     * @return Позиция на шахматной доске.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Сеттер.
     * @param position Позиция на шахматной доске.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Возможен ли ход.
     * @param position новая позиция.
     * @return возможность хода.
     */
    public abstract boolean moveTo(Position position);
}