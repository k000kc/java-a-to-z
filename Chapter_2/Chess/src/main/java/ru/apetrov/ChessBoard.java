package ru.apetrov;

import ru.apetrov.models.Horse;

public class ChessBoard {

	private Figure[][] figures = new Figure[8][8];

    	public void move(Figure figure, Position position){
        	if (boardRangeChecking(position) && positionOccupiedChecking(figure, position)){
            		if (!figures[figure.getPosition().getY()][figure.getPosition().getX()].equals(new Horse(position))){
                		if (moveByDiagonal(figure, position) && moveByVerticaleOrHorizontale(figure, position)){
                    			this.removeFigure(figure);
                    			figure.setPosition(position);
                    			this.addFigure(figure);
                		}
            		}else {
                		if (moveByDiagonal(figure, position) && moveByVerticaleOrHorizontale(figure, position)){
                    			this.removeFigure(figure);
                    			figure.setPosition(position);
                    			this.addFigure(figure);
                		}
            		}
        	}
    	}

    	public void addFigure(Figure figure){
        	this.figures[figure.getPosition().getY()][figure.getPosition().getX()] = figure;
    	}

    	public  void removeFigure(Figure figure){
        	this.figures[figure.getPosition().getY()][figure.getPosition().getX()] = null;
    	}

    	public boolean boardRangeChecking(Position position){

        	boolean result = false;

        	if (!position.equals(null)){
            		if (position.getY() <= figures.length && position.getY() >= 0 && position.getX() <= figures.length && position.getX() >= 0){
                		result = true;
            		}
        	}
        	return result;
    	}

    	public boolean positionOccupiedChecking(Figure figure, Position position){
        	boolean result = false;

        	if (!figure.getPosition().equals(null)){
            		if (!figure.getPosition().equals(position)){
                		result = true;
            		}
        	}
        	return result;
    	}

    	public boolean moveByDiagonal(Figure figure, Position position){
        	boolean result = false;

        	if (figure.getPosition().getY() < position.getY() && figure.getPosition().getX() > position.getX()){
            		for (int y = figure.getPosition().getY() + 1; y <= position.getY(); y++){
                		int x = figure.getPosition().getX() - 1;
                		if (this.figures[y][x] == null){
                    			result = true;
                		}
                		x--;
            		}
        	}

        	if (figure.getPosition().getY() < position.getY() && figure.getPosition().getX() < position.getX()){
            		for (int y = figure.getPosition().getY() + 1; y <= position.getY(); y++){
                		int x = figure.getPosition().getX() + 1;
                		if (this.figures[y][x] == null){
                    			result = true;
                		}
                		x++;
            		}
        	}

        	if (figure.getPosition().getY() > position.getY() && figure.getPosition().getX() < position.getX()){
            		for (int y = figure.getPosition().getY() - 1; y >= position.getY(); y-- ){
                		int x = figure.getPosition().getX() + 1;
                		if (this.figures[y][x] == null){
                    			result = true;
                		}
                		x++;
            		}
        	}

        	if (figure.getPosition().getY() > position.getY() && figure.getPosition().getX() > position.getX()){
            		for (int y = figure.getPosition().getY() - 1; y < position.getY(); y--){
                		int x = figure.getPosition().getX() - 1;
                		if (this.figures[y][x] == null){
                    			result = true;
                		}
                		x--;
            		}
        	}
        	return result;
	}

    	public boolean moveByVerticaleOrHorizontale(Figure figure, Position position){
        	boolean result = false;

        	if (figure.getPosition().getX() == position.getX()){
            		if (figure.getPosition().getY() < position.getY()){
                		for (int y = figure.getPosition().getY() + 1; y <= position.getY(); y++){
                    			int x = figure.getPosition().getX();
                    			if (figures[y][x] == null){
                        			result = true;
                    			}
                		}
            		}else {
                		for (int y = figure.getPosition().getY() - 1; y >= position.getY(); y--){
                    			int x = figure.getPosition().getX();
                    			if (figures[y][x] == null){
                        			result = true;
                    			}
                		}
            		}
        	}

        	if (figure.getPosition().getY() == position.getY()){
            		if (figure.getPosition().getX() < position.getX()){
                		for (int x = figure.getPosition().getX() + 1; x <= position.getX(); x++){
                    			int y = figure.getPosition().getY();
                    			if (figures[y][x] == null){
                        			result = true;
                    			}
                		}
            		}else {
                		for (int x = figure.getPosition().getX() - 1; x >= position.getX(); x--){
                    			int y = figure.getPosition().getY();
                    			if (figures[y][x] == null){
                        			result = true;
                    			}
                		}
            		}
        	}
        	return result;
    	}
}
