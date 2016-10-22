package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MergerArrayTest{

	@Test
	public void whenInputTwoArratThenOutputResultArray(){
		int[] a = new int[]{1, 3, 6, 7, 9, 12};
		int[] b = new int[]{2, 4, 5, 8, 10, 11, 14};
		int[] result = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14};
		MergerArray mergerArray = new MergerArray();
		int[] array = mergerArray.merger(a, b);
		assertThat(result, is(array));
	}
}
