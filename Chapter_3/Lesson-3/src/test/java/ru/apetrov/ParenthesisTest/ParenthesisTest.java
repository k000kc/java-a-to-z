package ru.apetrov.ParenthesisTest;

import org.junit.Test;
import ru.apetrov.Parenthesis.Parenthesis;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 29.12.2016.
 */
public class ParenthesisTest {

    @Test
    public void whenCorrectParenthesisThenTrue() {
        Parenthesis parenthesis = new Parenthesis();
        String text = "(((())))";
        assertTrue(parenthesis.checkParenthesis(text));
    }

    @Test
    public void whenUncorrectParenthesisThenFale() {
        Parenthesis parenthesis = new Parenthesis();
        String text = "(()))))";
        assertFalse(parenthesis.checkParenthesis(text));
    }
}