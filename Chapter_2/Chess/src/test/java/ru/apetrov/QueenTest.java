package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;

public class QueenTest{

	@Test
	public void whenMoveQueen1(){
		ChessBoard board = new ChessBoard();
		Queen queen = new Queen(new Position(4, 4));
		board.addFigure(queen);
		Position position = new Position(1, 1);
		board.move(queen, position);
		assertThat(queen.getPosition(), is(position));
	}

	@Test
	public void whenMoveQueen2(){
		ChessBoard board = new ChessBoard();
		Queen queen = new Queen(new Position(4, 4));
		board.addFigure(queen);
		Position position = new Position(4, 1);
		board.move(queen, position);
		assertThat(queen.getPosition(), is(position));
	}

	@Test
	public void whenMoveQueen3(){
		ChessBoard board = new ChessBoard();
		Queen queen = new Queen(new Position(4, 4));
		board.addFigure(queen);
		Position position = new Position(6, 2);
		board.move(queen, position);
		assertThat(queen.getPosition(), is(position));
	}

	@Test
	public void whenMoveQueen4(){
		ChessBoard board = new ChessBoard();
		Queen queen = new Queen(new Position(4, 4));
		board.addFigure(queen);
		Position position = new Position(6, 4);
		board.move(queen, position);
		assertThat(queen.getPosition(), is(position));
	}

	@Test
	public void whenMoveQueen5(){
		ChessBoard board = new ChessBoard();
		Queen queen = new Queen(new Position(4, 4));
		board.addFigure(queen);
		Position position = new Position(6, 6);
		board.move(queen, position);
		assertThat(queen.getPosition(), is(position));
	}

	@Test
	public void whenMoveQueen6(){
		ChessBoard board = new ChessBoard();
		Queen queen = new Queen(new Position(4, 4));
		board.addFigure(queen);
		Position position = new Position(4, 6);
		board.move(queen, position);
		assertThat(queen.getPosition(), is(position));
	}

	@Test
	public void whenMoveQueen7(){
		ChessBoard board = new ChessBoard();
		Queen queen = new Queen(new Position(4, 4));
		board.addFigure(queen);
		Position position = new Position(2, 6);
		board.move(queen, position);
		assertThat(queen.getPosition(), is(position));
	}

	@Test
	public void whenMoveQueen8(){
		ChessBoard board = new ChessBoard();
		Queen queen = new Queen(new Position(4, 4));
		board.addFigure(queen);
		Position position = new Position(1, 4);
		board.move(queen, position);
		assertThat(queen.getPosition(), is(position));
	}
}