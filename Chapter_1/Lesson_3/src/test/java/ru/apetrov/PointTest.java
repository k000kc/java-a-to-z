package ru.apetrov;

import org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PointTest{

	@Test
	public void distanceToTest() throws Exception {
		Point a = new Point(1, 1);
		Point b = new Point(1, 2);
		double result = a.distanceTo(b);
		assertThat(result, is(1.0));
	}
}