package ru.apetrov.DopTask;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Andrey on 18.10.2017.
 */
public class DopTaskTest {

    /**
     * проверка первого метода.
     */
    @Test
    public void checkWordTest() {
        DopTask dopTask = new DopTask();
        String word1 = "слово";
        String word2 = "волос";
        boolean result = dopTask.checkWord(word1, word2);
        assertThat(true, is(result));
    }

    /**
     * проверка первого метода.
     */
    @Test
    public void checkWordTest1() {
        DopTask dopTask = new DopTask();
        String word1 = "слово";
        String word2 = "волос";
        boolean result = dopTask.checkWord1(word1, word2);
        assertThat(true, is(result));
    }
}