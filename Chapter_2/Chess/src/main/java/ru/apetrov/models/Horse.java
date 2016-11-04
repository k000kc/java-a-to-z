package ru.apetrov.models;

import ru.apetrov.*;

public class Horse extends Figure{

    	private MoveChecking checking = new MoveChecking();

    	public Horse(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {

        	boolean result = false;

        	if (checking.boardRangeChecking(position) && checking.positionOccupiedChecking(this, position)) {

            		if (Math.abs(this.getPosition().getY() - position.getY()) == 2 && Math.abs(this.getPosition().getX() - position.getX()) == 1) {
                		result = true;
            		}

            		if (Math.abs(this.getPosition().getY() - position.getY()) == 1 && Math.abs(this.getPosition().getX() - position.getX()) == 2) {
                		result = true;
            		}
        	}
        	return result;
    	}
}