package apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RemoveDuplicateArrayTest{

	@Test
	public void rmDuplicateTest(){
		String[] inArray = new String[]{"2","1","6","1","3","7","2","1","5","3"};
		String[] outArray = new String[]{"2","1","6","3","7","5"};
		RemoveDuplicateArray array = new RemoveDuplicateArray(inArray);
		String[] result = array.rmDuplicate(inArray);
		assertThat(result, is(outArray));
	}
}
