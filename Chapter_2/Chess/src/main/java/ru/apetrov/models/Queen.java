package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class Queen extends Figure {

    	public Queen(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {
        	boolean result = false;

        	if (this.getPosition().getY() == position.getY() || this.getPosition().getX() == position.getX()){
            		result = true;
        	}

        	if (Math.abs(this.getPosition().getY() - position.getY()) == Math.abs(this.getPosition().getX() - position.getX())){
            		result = true;
        	}
        	return result;
    	}
}
