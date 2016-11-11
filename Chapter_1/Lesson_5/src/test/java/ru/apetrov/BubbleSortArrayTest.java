package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BubbleSortArrayTest{
	int[] mas = new int[]{5, 6, 1, 3, 8, 7, 9, 2, 4, 0};
	int[] result = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
	
	@Test
	public void sortTest(){
		BubbleSortArray sortArray = new BubbleSortArray(mas);
		mas = sortArray.sort();
		assertThat(result, is(mas));
	}
}