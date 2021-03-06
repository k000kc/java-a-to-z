package ru.apetrov.models;

import ru.apetrov.*;

/**
 * Фигура слона.
 */
public class Elephant extends Figure{

	/**
	 * класс проверок хода.
	 */
	private MoveChecking checking = new MoveChecking();

	/**
	 * Конструктор.
	 * @param position позиция.
	 */
	public Elephant(Position position) {
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

		if (checking.boardRangeChecking(position) && checking.positionOccupiedChecking(this, position) && checking.moveByDiagonal(this, position)) {

			if (Math.abs(this.getPosition().getY() - position.getY()) == Math.abs(this.getPosition().getX() - position.getX())) {
				result = true;
			}
		}
		return result;
	}
}
