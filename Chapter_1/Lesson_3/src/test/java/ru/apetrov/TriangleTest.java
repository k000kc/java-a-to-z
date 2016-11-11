package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriangleTest{
	
	Point a = new Point(0, 0);
	Point b = new Point(0, 2);
	Point c = new Point(2, 0);
	Triangle triangle = new Triangle(a, b, c);
	
	@Test
	public void existTest(){
		boolean result = triangle.exist();
		assertTrue(result);
	}

	@Test
	public void areaTest(){
		double result = triangle.area();
		assertThat(result, is(2.0));
	}
}