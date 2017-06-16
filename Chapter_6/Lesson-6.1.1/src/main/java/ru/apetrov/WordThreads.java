package ru.apetrov;

import java.util.StringTokenizer;

/**
 * Created by Andrey on 16.06.2017.
 */
public class WordThreads implements Runnable {

    /**
     * text.
     */
    private final String text;

    /**
     * Constructor.
     * @param text
     */
    public WordThreads(String text) {
        this.text = text;
    }

    /**
     * run.
     */
    @Override
    public void run() {
        System.out.printf("%s%s\n", "Number of word = " ,this.numberOfWord(this.text));
    }

    /**
     * get numbers of word in text.
     * @param text text.
     * @return numbers of word.
     */
    private int numberOfWord(String text) {
        int result = 0;
        StringTokenizer tokenizer = new StringTokenizer(text);
        while (tokenizer.hasMoreTokens()) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("Error in %s: Exceeded waiting time!\n", Thread.currentThread().getName());
                break;
            }
            tokenizer.nextToken();
            result++;
        }
        return result;
    }
}
