package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class King extends Figure {

    	public King(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {
        	return false;
    	}
}