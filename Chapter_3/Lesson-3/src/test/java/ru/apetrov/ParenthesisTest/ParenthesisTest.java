package ru.apetrov.ParenthesisTest;

import org.junit.Test;
import ru.apetrov.Parenthesis.Parenthesis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by Andrey on 29.12.2016.
 */
public class ParenthesisTest {

    /**
     * Когда скобки закрываются корректно.
     */
    @Test
    public void whenCorrectParenthesisThenTrue() {
        Parenthesis parenthesis = new Parenthesis();
        String text = "(((())))";
        assertTrue(parenthesis.checkParenthesis(text));
    }

    /**
     * Когда скобки закрываются некорректно.
     */
    @Test
    public void whenUncorrectParenthesisThenFale() {
        Parenthesis parenthesis = new Parenthesis();
        String text = "(()))))";
        assertFalse(parenthesis.checkParenthesis(text));
    }


}