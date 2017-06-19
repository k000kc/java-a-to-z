package ru.apetrov;

/**
 * Created by Andrey on 16.06.2017.
 */
public class WordThreads implements Runnable {

    private final String text;

    public WordThreads(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        System.out.printf("%s%s\n", "Number of word = " ,this.numberOfWord(this.text));
    }

    private int numberOfWord(String text) {
        int result = 0;
        String[] word = text.split(" +");
        result = word.length;
        return result;
    }
}
