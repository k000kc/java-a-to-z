package ru.apetrov.models;

import ru.apetrov.*;

public class Rook extends Figure {

    	private MoveChecking checking = new MoveChecking();

    	public Rook(Position position) {
        	super(position);
    	}

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