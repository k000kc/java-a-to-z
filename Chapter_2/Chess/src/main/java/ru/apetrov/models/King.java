package ru.apetrov.models;

import ru.apetrov.*;

public class King extends Figure {

    	private MoveChecking checking = new MoveChecking();

    	public King(Position position) {
        	super(position);
    	}

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