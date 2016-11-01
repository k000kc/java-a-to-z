package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class King extends Figure {

    	public King(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {
        	boolean result = false;

        	if (Math.abs(this.getPosition().getY() - position.getY()) == 1 && Math.abs(this.getPosition().getX() - position.getX()) == 1){
           		result = true;
        	}

        	if (Math.abs(this.getPosition().getY() - position.getY()) == 1 && this.getPosition().getX() == position.getX()){
            		result = true;
        	}

        	if (Math.abs(this.getPosition().getX() - position.getX()) == 1 && this.getPosition().getY() == position.getY()){
            		result = true;
        	}
        	return result;
    	}
}