package ru.apetrov.models;

import ru.apetrov.*;

/**
 * класс Ферзя.
 */
public class Queen extends Figure {

	/**
	 * класс проверок хода.
	 */
	private MoveChecking checking = new MoveChecking();

	/**
	 * конструктор.
	 * @param position позиция.
	 */
	public Queen(Position position) {
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
				checking.moveByDiagonal(this, position) || checking.moveByVerticaleOrHorizontale(this, position)) {

			if (this.getPosition().getY() == position.getY() || this.getPosition().getX() == position.getX()) {
				result = true;
			}

			if (Math.abs(this.getPosition().getY() - position.getY()) == Math.abs(this.getPosition().getX() - position.getX())) {
				result = true;
			}
		}
		return result;
	}
}
