package ru.apetrov;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Andrey on 05.11.2016.
 */
public class CheckByteStreamTest {

    /**
     * init class.
     */
    private CheckByteStream cbs = new CheckByteStream();

    /**
     * field result.
     */
    private boolean result = false;

    /**
     * Check Even number.
     */
    @Test
    public void whenInputEvenNumberThenCheckNumber() {
        try (InputStream inputStream = new ByteArrayInputStream("4".getBytes())) {
            result = cbs.isNumber(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    /**
     * Check odd number.
     */
    @Test
    public void whenInputOddNumberThenCheckNumber() {
        try (InputStream inputStream = new ByteArrayInputStream("3".getBytes())) {
            result = cbs.isNumber(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(result);
    }
}
