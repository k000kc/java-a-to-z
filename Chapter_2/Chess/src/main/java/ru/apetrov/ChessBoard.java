package ru.apetrov;

/**
 * Класс шахматной доски.
 */
public class ChessBoard {

	/**
	 * Массив фигур.
	 */
	private Figure[][] figures = new Figure[8][8];

	/**
	 * Досуп к клетке с фигурой.
	 * @return возвращает фигурую
	 */
	public Figure[][] getFigures() {
		return figures;
	}

	/**
	 * Шаг фигурой.
	 * @param figure фигура.
	 * @param position новая позиция.
	 */
	public void move(Figure figure, Position position){

		if (figure.moveTo(position)){
			removeFigure(figure);
			figure.setPosition(position);
			addFigure(figure);
		}
	}

	/**
	 * Добавление фигуры на доску.
	 * @param figure фигура.
	 */
	public void addFigure(Figure figure){
		this.figures[figure.getPosition().getY()][figure.getPosition().getX()] = figure;
	}

	/**
	 * Удаляем фигуру с доски.
	 * @param figure фигура.
	 */
	public  void removeFigure(Figure figure){
		this.figures[figure.getPosition().getY()][figure.getPosition().getX()] = null;
	}
}
