package ru.apetrov;

import java.util.StringTokenizer;

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
        StringTokenizer tokenizer = new StringTokenizer(text);
        while (tokenizer.hasMoreTokens()) {
            if (Thread.currentThread().isInterrupted()) {
//                try {
//                    throw new InterruptedException(String.format("Error in %s: Exceeded waiting time!", Thread.currentThread().getName()));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.printf("Error in %s: Exceeded waiting time!\n", Thread.currentThread().getName());
                break;
            }
            tokenizer.nextToken();
            result++;
        }
        return result;
    }
}
