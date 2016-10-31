package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class Rook extends Figure {

    	public Rook(Position position) {
        	super(position);
    	}

    	@Override
    	public boolean moveTo(Position position) {
        	return false;
    	}
}