package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;

public class PawnTest{

	@Test
	public void whenFirstMovePawn(){
		ChessBoard board = new ChessBoard();
		Pawn pawn = new Pawn(new Position(4, 4));
		board.addFigure(pawn);
		Position position = new Position(4, 2);
		board.move(pawn, position);
		assertThat(pawn.getPosition(), is(position));
	}

	@Test
	public void whenMovePawn(){
		ChessBoard board = new ChessBoard();
		Pawn pawn = new Pawn(new Position(4, 4));
		board.addFigure(pawn);
		Position position = new Position(4, 3);
		board.move(pawn, position);
		assertThat(pawn.getPosition(), is(position));
	}
}