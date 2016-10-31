package ru.apetrov.models;

import ru.apetrov.Figure;
import ru.apetrov.Position;

public class Pawn extends Figure {


    	public Pawn(Position position) {
        	super(position);
    	}

   	@Override
    	public boolean moveTo(Position position) {
        	return false;
    	}
}
