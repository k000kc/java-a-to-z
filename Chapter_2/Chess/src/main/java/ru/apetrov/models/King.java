package ru.apetrov.models;

import ru.apetrov.*;

/**
 * Класс короля.
 */
public class King extends Figure {

	/**
	 * класс проверок хода.
	 */
	private MoveChecking checking = new MoveChecking();

	/**
	 * конструктор.
	 * @param position позиция.
	 */
	public King(Position position) {
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

			if (Math.abs(this.getPosition().getY() - position.getY()) == 1 && Math.abs(this.getPosition().getX() - position.getX()) == 1) {
				result = true;
			}

			if (Math.abs(this.getPosition().getY() - position.getY()) == 1 && this.getPosition().getX() == position.getX()) {
				result = true;
			}

			if (Math.abs(this.getPosition().getX() - position.getX()) == 1 && this.getPosition().getY() == position.getY()) {
				result = true;
			}
		}
		return result;
   	}
}