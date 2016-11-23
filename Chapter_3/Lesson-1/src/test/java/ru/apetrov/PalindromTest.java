package ru.apetrov;

import org.junit.Test;
import ru.apetrov.Palindrom.Palindrom;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 22.11.2016.
 */
public class PalindromTest {

    /**
     * Тест на метод isPalindrom.
     * @throws IOException IOExeption
     */
    @Test
    public void whenInputWordThenCheckIsPolindrom() {
        Palindrom palindrom = new Palindrom();
        boolean result = true;

        try(InputStream inputStream = new ByteArrayInputStream("КоМок".getBytes());) {
            assertThat(palindrom.isPalindrom(inputStream), is(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
