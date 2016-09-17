package apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestTaskTest{
	
	String origin = "Hello World";
	String sub = " Wor";
	TestTask testTask = new TestTask();

	@Test
	public void whenInputSubThenCheckOriginTrue(){
	boolean result = testTask.contains(origin, sub);
	assertTrue(result);
	}
}