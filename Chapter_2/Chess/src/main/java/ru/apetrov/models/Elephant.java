package ru.apetrov.models;

import ru.apetrov.*;

public class Elephant extends Figure{

    	private MoveChecking checking = new MoveChecking();

    	public Elephant(Position position) {
        	super(position);
    	}

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
