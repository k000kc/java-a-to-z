package ru.apetrov.Parenthesis;

/**
 * Created by Andrey on 29.12.2016.
 */
public class Parenthesis {

    public boolean checkParenthesis(String text) {
        boolean result = false;
        char[] ch = text.toCharArray();
        int check = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '(') {
                check++;
            }

            if (ch[i] == ')') {
                check--;
            }
        }

        if (check == 0) {
            result = true;
        }

        return result;
    }
}
