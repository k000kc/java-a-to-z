package ru.apetrov;

/**
 * Класс позиции.
 */
public class Position {

	/**
	 * Координата по Х
	 */
	private int x;
	/**
	 * Координата по У
	 */
	private int y;

	/**
	 * Конструктор.
	 * @param x координата.
	 * @param y координата.
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Геттер.
	 * @return х.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Сеттер.
	 * @param x координата.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Геттер.
	 * @return у.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Сеттер.
	 * @param y координата.
	 */
	public void setY(int y) {
		this.y = y;
	}
}