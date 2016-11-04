package ru.apetrov;

public class MoveChecking {

    	private ChessBoard board = new ChessBoard();

    	public boolean boardRangeChecking(Position position){

        	boolean result = false;
		
		if (position != null){
            		if (position.getY() <= 7 && position.getY() >= 0 && position.getX() <= 7 && position.getX() >= 0){
                		result = true;
            		}
		}
        	return result;
    	}

    	public boolean positionOccupiedChecking(Figure figure, Position position){
        	boolean result = true;

        	if (!figure.getPosition().equals(null)){
          		if (!figure.getPosition().equals(position)){
                		result = true;
            		}
        	}
        	return result;
    	}

    	public boolean moveByDiagonal(Figure figure, Position position){
        	boolean result = true;

        	if (figure.getPosition().getY() < position.getY() && figure.getPosition().getX() > position.getX()){
            		for (int y = figure.getPosition().getY() + 1; y <= position.getY(); y++){
                		int x = figure.getPosition().getX() - 1;
                		if (this.board.getFigures()[y][x] != null){
                    			result = false;
                		}
                		x--;
            		}
        	}

        	if (figure.getPosition().getY() < position.getY() && figure.getPosition().getX() < position.getX()){
            		for (int y = figure.getPosition().getY() + 1; y <= position.getY(); y++){
                		int x = figure.getPosition().getX() + 1;
                		if (this.board.getFigures()[y][x] != null){
                    			result = false;
                		}
                		x++;
            		}
        	}

        	if (figure.getPosition().getY() > position.getY() && figure.getPosition().getX() < position.getX()){
            		for (int y = figure.getPosition().getY() - 1; y >= position.getY(); y-- ){
                		int x = figure.getPosition().getX() + 1;
                		if (this.board.getFigures()[y][x] != null){
                    			result = false;
                		}
                		x++;
            		}
        	}

	        if (figure.getPosition().getY() > position.getY() && figure.getPosition().getX() > position.getX()){
	            	for (int y = figure.getPosition().getY() - 1; y <= position.getY(); y--){
	                	int x = figure.getPosition().getX() - 1;
	                	if (this.board.getFigures()[y][x] != null){
	                    		result = false;
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
                    			if (this.board.getFigures()[y][x] == null){
                        			result = true;
                    			}
                		}
            		}else {
                		for (int y = figure.getPosition().getY() - 1; y >= position.getY(); y--){
                    			int x = figure.getPosition().getX();
                    			if (this.board.getFigures()[y][x] == null){
                        			result = true;
                    			}
                		}
            		}
        	}

        	if (figure.getPosition().getY() == position.getY()){
           		if (figure.getPosition().getX() < position.getX()){
                		for (int x = figure.getPosition().getX() + 1; x <= position.getX(); x++){
                    			int y = figure.getPosition().getY();
                    			if (this.board.getFigures()[y][x] == null){
                        			result = true;
                    			}
                		}
            		}else {
                		for (int x = figure.getPosition().getX() - 1; x >= position.getX(); x--){
                    			int y = figure.getPosition().getY();
                    			if (this.board.getFigures()[y][x] == null){
                        			result = true;
                    			}
                		}
            		}
        	}
        	return result;
    	}
}
