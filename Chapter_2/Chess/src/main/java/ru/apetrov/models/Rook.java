package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class Rook extends Figure {

    	public Rook(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {
        	boolean result = false;

        	if (this.getPosition().getY() == position.getY() || this.getPosition().getX() == position.getX()){
            		result = true;
        	}
        	return result;
    	}
}