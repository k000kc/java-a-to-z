package apetrov;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MaxTriangleSideTest {

	@Test
	public void maxSideTest(){
		MaxTriangleSide maximum = new MaxTriangleSide();
		double result = maximum.maxSide(1, 2, 3);
		assertThat(result, is(3.0));
	}
}