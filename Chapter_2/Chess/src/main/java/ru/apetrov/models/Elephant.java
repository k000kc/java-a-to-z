package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class Elephant extends Figure{

    	public Elephant(Position position) {
        	super(position);
    	}

    	@Override
   	public boolean moveTo(Position position) {
        	boolean result = false;

        	if (Math.abs(this.getPosition().getY() - position.getY()) == Math.abs(this.getPosition().getX() - position.getX())){
            		result = true;
        	}
        	return result;
    	}
}
