package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;

public class RookTest{

	@Test
	public void whenMoveRook1(){
		ChessBoard board = new ChessBoard();
		Rook rook = new Rook(new Position(4, 4));
		board.addFigure(rook);
		Position position = new Position(4, 1);
		board.move(rook, position);
		assertThat(rook.getPosition(), is(position));
	}

	@Test
	public void whenMoveRook2(){
		ChessBoard board = new ChessBoard();
		Rook rook = new Rook(new Position(4, 4));
		board.addFigure(rook);
		Position position = new Position(6, 4);
		board.move(rook, position);
		assertThat(rook.getPosition(), is(position));
	}

	@Test
	public void whenMoveRook3(){
		ChessBoard board = new ChessBoard();
		Rook rook = new Rook(new Position(4, 4));
		board.addFigure(rook);
		Position position = new Position(4, 6);
		board.move(rook, position);
		assertThat(rook.getPosition(), is(position));
	}

	@Test
	public void whenMoveRook4(){
		ChessBoard board = new ChessBoard();
		Rook rook = new Rook(new Position(4, 4));
		board.addFigure(rook);
		Position position = new Position(1, 4);
		board.move(rook, position);
		assertThat(rook.getPosition(), is(position));
	}
}