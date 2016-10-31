package ru.apetrov;

public abstract class Figure {

    	private Position position;

    	public Figure(Position position) {
        	this.position = position;
    	}

    	public Position getPosition() {
        	return position;
    	}

    	public void setPosition(Position position) {
        	this.position = position;
    	}

    	public abstract boolean moveTo(Position position);
}