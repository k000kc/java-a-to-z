package ru.apetrov.Palindrom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Andrey on 22.11.2016.
 */
public class Palindrom {

    /**
     * lengthWodr - длина слова.
     */
    private final int lengthWodr = 5;

    /**
     * переменная для записи потока.
     */
    private int value;

    /**
     * исходное слово.
     */
    private String word;

    /**
     * перевернутое слово.
     */
    private String reverseWord;

    /**
     * метод проверяет палиндром.
     * @param is входящий поток
     * @return false/true - существует ли палиндром
     * @throws IOException IOExeption
     */
    public boolean isPalindrom(InputStream is) throws IOException {

        boolean result = false;
        StringBuilder builder = new StringBuilder();
        Reader reader = new InputStreamReader(is);

        while ((value = reader.read()) != -1) {
            word = builder.append((char) value).toString();
        }

        reverseWord = builder.reverse().toString();

        if (word.length() == lengthWodr && word.equalsIgnoreCase(reverseWord)) {
            result = true;
        } else {
            throw new IOException(String.format("%s%s", "Слово должно содержать 5 букв", word));
        }
        reader.close();

        return result;
    }
}
