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
        	return result;
    	}
}
