package ru.apetrov.DopTask;

import java.util.Arrays;

/**
 * Created by Andrey on 18.10.2017.
 */
public class DopTask {

    public boolean checkWord(String first, String second) {
        boolean result = true;
        if (first.length() != second.length()) {
            result = false;
        } else {
            char[] firstArray = first.toCharArray();
            char[] secondArray = second.toCharArray();
            Arrays.sort(firstArray);
            Arrays.sort(secondArray);
            if (!Arrays.equals(firstArray, secondArray)) {
                result = false;
            }
        }
        return  result;
    }

}
