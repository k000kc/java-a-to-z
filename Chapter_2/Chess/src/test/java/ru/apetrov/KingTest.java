package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;

public class KingTest{

	@Test
	public void whenMoveKing1(){
		ChessBoard board = new ChessBoard();
		King king = new King(new Position(4, 4));
		board.addFigure(king);
		Position position = new Position(3, 3);
		board.move(king, position);
		assertThat(king.getPosition(), is(position));
	}

	@Test
	public void whenMoveKing2(){
		ChessBoard board = new ChessBoard();
		King king = new King(new Position(4, 4));
		board.addFigure(king);
		Position position = new Position(4, 3);
		board.move(king, position);
		assertThat(king.getPosition(), is(position));
	}

	@Test
	public void whenMoveKing3(){
		ChessBoard board = new ChessBoard();
		King king = new King(new Position(4, 4));
		board.addFigure(king);
		Position position = new Position(5, 3);
		board.move(king, position);
		assertThat(king.getPosition(), is(position));
	}

	@Test
	public void whenMoveKing4(){
		ChessBoard board = new ChessBoard();
		King king = new King(new Position(4, 4));
		board.addFigure(king);
		Position position = new Position(5, 4);
		board.move(king, position);
		assertThat(king.getPosition(), is(position));
	}

	@Test
	public void whenMoveKing5(){
		ChessBoard board = new ChessBoard();
		King king = new King(new Position(4, 4));
		board.addFigure(king);
		Position position = new Position(5, 5);
		board.move(king, position);
		assertThat(king.getPosition(), is(position));
	}

	@Test
	public void whenMoveKing6(){
		ChessBoard board = new ChessBoard();
		King king = new King(new Position(4, 4));
		board.addFigure(king);
		Position position = new Position(4, 5);
		board.move(king, position);
		assertThat(king.getPosition(), is(position));
	}

	@Test
	public void whenMoveKing7(){
		ChessBoard board = new ChessBoard();
		King king = new King(new Position(4, 4));
		board.addFigure(king);
		Position position = new Position(3, 5);
		board.move(king, position);
		assertThat(king.getPosition(), is(position));
	}

	@Test
	public void whenMoveKing8(){
		ChessBoard board = new ChessBoard();
		King king = new King(new Position(4, 4));
		board.addFigure(king);
		Position position = new Position(3, 4);
		board.move(king, position);
		assertThat(king.getPosition(), is(position));
	}
}