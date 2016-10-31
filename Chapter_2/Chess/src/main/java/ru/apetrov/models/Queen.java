package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class Queen extends Figure {

    	public Queen(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {
        	return false;
    	}
}
