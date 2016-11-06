package ru.apetrov;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

/**
 * Created by Andrey on 05.11.2016.
 */
//@Ignore
public class CheckByteStreamTest {

    CheckByteStream cbs = new CheckByteStream();
    boolean result = false;

    @Test
    public void whenInputEvenNumberThenCheckNumber(){
        InputStream inputStream = new ByteArrayInputStream("4".getBytes());
        try {
            result = cbs.isNumber(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    @Test
    public void whenInputOddNumberThenCheckNumber(){
        InputStream inputStream = new ByteArrayInputStream("3".getBytes());
        try {
            result = cbs.isNumber(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFalse(result);
    }
}
