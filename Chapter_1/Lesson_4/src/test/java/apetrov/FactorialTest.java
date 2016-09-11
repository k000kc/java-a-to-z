package apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FactorialTest{

	@Test
	public void getFactorialTest(){
		Factorial factorial = new Factorial();
		int result = factorial.getFactorial(5);
		assertThat(result, is(120));
	}
}