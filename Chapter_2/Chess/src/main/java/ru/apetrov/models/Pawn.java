package ru.apetrov.models;

import ru.apetrov.*;

public class Pawn extends Figure {

    	private MoveChecking checking = new MoveChecking();
    	private boolean isFirstMove = true;

    	public Pawn(Position position) {
        	super(position);
    	}

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
