package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RotateArrayTest{

	int[][] inArray = {{1, 2, 3},
			   {4, 5, 6},
			   {7, 8, 9}
	};

	int [][] outArray = {{7, 4, 1},
			     {8, 5, 2},
			     {9, 6, 3}
			
	};
	
	@Test
	public void rotateTest(){
		RotateArray result = new RotateArray(inArray);
		assertThat(result.rotate(inArray), is(outArray)); 
	}
}