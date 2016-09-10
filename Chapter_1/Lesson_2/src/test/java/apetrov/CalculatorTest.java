package apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {

    @Test
    public void add() throws Exception {
        final Calculator calc = new Calculator();
        calc.add(1, 1);
        assertThat(calc.getResult(), is(2.0));
    }

    @Test
    public void sub() throws Exception {
        final Calculator calc = new Calculator();
        calc.sub(2, 1);
        assertThat(calc.getResult(), is(1.0));
    }

    @Test
    public void mult() throws Exception {
        final Calculator calc = new Calculator();
        calc.mult(2, 2);
        assertThat(calc.getResult(), is(4.0));
    }

    @Test
    public void div() throws Exception {
        final Calculator calc = new Calculator();
        calc.div(4, 2);
        assertThat(calc.getResult(), is(2.0));
    }

}
