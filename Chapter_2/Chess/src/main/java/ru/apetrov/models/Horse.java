package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class Horse extends Figure{


    	public Horse(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {
        	boolean result = false;

        	if (Math.abs(this.getPosition().getY() - position.getY()) == 2 && Math.abs(this.getPosition().getX() - position.getX()) == 1){
            		result = true;
        	}

        	if (Math.abs(this.getPosition().getY() - position.getY()) == 1 && Math.abs(this.getPosition().getX() - position.getX()) == 2){
            		result = true;
        	}
        	return result;
    	}
}