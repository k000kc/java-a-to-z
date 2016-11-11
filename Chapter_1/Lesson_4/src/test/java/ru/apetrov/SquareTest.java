package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SquareTest{
	
	@Test
	public void calculateTest(){
		Square square = new Square(1, 2, 3);
		float result = square.calculate(1);
		assertThat(result, is(6.0f));
	}
}