package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class Horse extends Figure{


    	public Horse(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {
        	return false;
    	}
}