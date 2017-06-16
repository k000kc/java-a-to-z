package ru.apetrov;

/**
 * Created by Andrey on 16.06.2017.
 */
public class SpaceThreads implements Runnable {

    private final String text;

    public SpaceThreads(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        System.out.println(this.numberOfSpace(this.text));
    }

    private int numberOfSpace(String text) {
        int result = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                result++;
            }
        }
        return result;
    }
}