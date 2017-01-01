package ru.apetrov.models;

import ru.apetrov.*;

/**
 * Фигура коня.
 */
public class Horse extends Figure{

	/**
	 * класс проверок хода.
	 */
	private MoveChecking checking = new MoveChecking();

	/**
	 * Конструктор.
	 * @param position позиция.
	 */
	public Horse(Position position) {
		super(position);
	}

	/**
	 * корректность хода.
	 * @param position новая позиция.
	 * @return возможность хода.
	 */
	@Override
	public boolean moveTo(Position position) {

		boolean result = false;

		if (checking.boardRangeChecking(position) && checking.positionOccupiedChecking(this, position)) {

			if (Math.abs(this.getPosition().getY() - position.getY()) == 2 && Math.abs(this.getPosition().getX() - position.getX()) == 1) {
				result = true;
			}

			if (Math.abs(this.getPosition().getY() - position.getY()) == 1 && Math.abs(this.getPosition().getX() - position.getX()) == 2) {
				result = true;
			}
		}
		return result;
	}
}