package ru.apetrov;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 11.11.2016.
 */
public class RemoveBannedWordsTest {

    /**
     * init class.
     */
    private RemoveBannedWords bannedWords = new RemoveBannedWords();

    /**
     * Check input stream on the abuse words.
     */
    @Test
    public void whenWodrEqualsAbuseThenRemoveWord() {
        String[] abuses = {"angered "};
        String result = null;
        try (InputStream inputStream = new ByteArrayInputStream("I angered the program".getBytes());
            OutputStream outputStream = new ByteArrayOutputStream()) {
            bannedWords.dropAbuses(inputStream, outputStream, abuses);
            result = outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result, is("I the program"));
    }
}
