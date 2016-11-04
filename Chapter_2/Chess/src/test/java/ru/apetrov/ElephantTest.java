package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;

public class ElephantTest{

	@Test
	public void whenMoveElephantLeftUp(){
		ChessBoard board = new ChessBoard();
		Elephant elephant = new Elephant(new Position(4, 4));
		board.addFigure(elephant);
		Position position = new Position(1, 1);
		board.move(elephant, position);
		assertThat(elephant.getPosition(), is(position));
	}

	@Test
	public void whenMoveElephantRightUp(){
		ChessBoard board = new ChessBoard();
		Elephant elephant = new Elephant(new Position(4, 4));
		board.addFigure(elephant);
		Position position = new Position(6, 2);
		board.move(elephant, position);
		assertThat(elephant.getPosition(), is(position));
	}

	@Test
	public void whenMoveElephantLeftDown(){
		ChessBoard board = new ChessBoard();
		Elephant elephant = new Elephant(new Position(4, 4));
		board.addFigure(elephant);
		Position position = new Position(2, 6);
		board.move(elephant, position);
		assertThat(elephant.getPosition(), is(position));
	}

	@Test
	public void whenMoveElephantRightDown(){
		ChessBoard board = new ChessBoard();
		Elephant elephant = new Elephant(new Position(4, 4));
		board.addFigure(elephant);
		Position position = new Position(6, 6);
		board.move(elephant, position);
		assertThat(elephant.getPosition(), is(position));
	}


	

}