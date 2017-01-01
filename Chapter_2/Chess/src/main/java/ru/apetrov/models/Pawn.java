package ru.apetrov.models;

import ru.apetrov.*;

/**
 * Класс пешки.
 */
public class Pawn extends Figure {

	/**
	 * класс проверок хода.
	 */
	private MoveChecking checking = new MoveChecking();

	/**
	 * Проверяет первый ли ход совершает пешка.
	 */
	private boolean isFirstMove = true;

	/**
	 * конструктор.
	 * @param position позиция.
	 */
	public Pawn(Position position) {
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

			if (this.isFirstMove) {
				if (this.getPosition().getX() == position.getX() && this.getPosition().getY() != position.getY()) {
					if (Math.abs(this.getPosition().getY() - position.getY()) <= 2) {
						result = true;
					}
				}
				this.isFirstMove = false;
			} else {
				if (this.getPosition().getX() == position.getX() && Math.abs(this.getPosition().getY() - position.getY()) == 1) {
					result = true;
				}
			}
		}
		return result;
	}
}
