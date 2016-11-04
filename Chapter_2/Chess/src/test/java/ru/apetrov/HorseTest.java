package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;

public class HorseTest{

	@Test
	public void whenMoveHorseUpLeft1(){
		ChessBoard board = new ChessBoard();
		Horse horse = new Horse(new Position(4, 4));
		board.addFigure(horse);
		Position position = new Position(3, 2);
		board.move(horse, position);
		assertThat(horse.getPosition(), is(position));
	}

	@Test
	public void whenMoveHorseUpLeft2(){
		ChessBoard board = new ChessBoard();
		Horse horse = new Horse(new Position(4, 4));
		board.addFigure(horse);
		Position position = new Position(2, 3);
		board.move(horse, position);
		assertThat(horse.getPosition(), is(position));
	}

	@Test
	public void whenMoveHorseUpRight1(){
		ChessBoard board = new ChessBoard();
		Horse horse = new Horse(new Position(4, 4));
		board.addFigure(horse);
		Position position = new Position(5, 2);
		board.move(horse, position);
		assertThat(horse.getPosition(), is(position));
	}

	@Test
	public void whenMoveHorseUpRight2(){
		ChessBoard board = new ChessBoard();
		Horse horse = new Horse(new Position(4, 4));
		board.addFigure(horse);
		Position position = new Position(6, 3);
		board.move(horse, position);
		assertThat(horse.getPosition(), is(position));
	}

	@Test
	public void whenMoveHorseDownLeft1(){
		ChessBoard board = new ChessBoard();
		Horse horse = new Horse(new Position(4, 4));
		board.addFigure(horse);
		Position position = new Position(3, 6);
		board.move(horse, position);
		assertThat(horse.getPosition(), is(position));
	}

	@Test
	public void whenMoveHorseDownLeft2(){
		ChessBoard board = new ChessBoard();
		Horse horse = new Horse(new Position(4, 4));
		board.addFigure(horse);
		Position position = new Position(2, 5);
		board.move(horse, position);
		assertThat(horse.getPosition(), is(position));
	}

	@Test
	public void whenMoveHorseDownRight1(){
		ChessBoard board = new ChessBoard();
		Horse horse = new Horse(new Position(4, 4));
		board.addFigure(horse);
		Position position = new Position(5, 6);
		board.move(horse, position);
		assertThat(horse.getPosition(), is(position));
	}

	@Test
	public void whenMoveHorseDownRight2(){
		ChessBoard board = new ChessBoard();
		Horse horse = new Horse(new Position(4, 4));
		board.addFigure(horse);
		Position position = new Position(6, 5);
		board.move(horse, position);
		assertThat(horse.getPosition(), is(position));
	}
}
