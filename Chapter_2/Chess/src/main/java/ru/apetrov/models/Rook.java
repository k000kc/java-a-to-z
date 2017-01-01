package ru.apetrov.models;

import ru.apetrov.*;

/**
 * Класс ладья.
 */
public class Rook extends Figure {

	/**
	 * класс проверок хода.
	 */
	private MoveChecking checking = new MoveChecking();

	/**
	 * конструктор.
	 * @param position позиция.
	 */
	public Rook(Position position) {
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

		if (checking.boardRangeChecking(position) && checking.positionOccupiedChecking(this, position) &&
				checking.moveByVerticaleOrHorizontale(this, position)) {

			if (this.getPosition().getY() == position.getY() || this.getPosition().getX() == position.getX()) {
				result = true;
			}
		}
		return result;
	}
}