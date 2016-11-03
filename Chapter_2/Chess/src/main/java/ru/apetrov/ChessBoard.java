package ru.apetrov;

import ru.apetrov.models.*;

public class ChessBoard {

	private Figure[][] figures = new Figure[8][8];

    	public Figure[][] getFigures() {
        	return figures;
    	}

    	public void move(Figure figure, Position position){

        	if (figure.moveTo(position)){
            		removeFigure(figure);
            		figure.setPosition(position);
            		addFigure(figure);
        	}
    	}

    	public void addFigure(Figure figure){
        	this.figures[figure.getPosition().getY()][figure.getPosition().getX()] = figure;
    	}

    	public  void removeFigure(Figure figure){
        	this.figures[figure.getPosition().getY()][figure.getPosition().getX()] = null;
    	}

}
