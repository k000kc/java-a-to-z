package ru.apetrov;

/**
 * Created by Andrey on 16.06.2017.
 */
public class SpaceThreads implements Runnable {

    /**
     * text.
     */
    private final String text;

    /**
     * Constructor.
     * @param text text.
     */
    public SpaceThreads(String text) {
        this.text = text;
    }

    /**
     * run.
     */
    @Override
    public void run() {
        System.out.printf("%s%s\n", "Number of spaces = " ,this.numberOfSpace(this.text));
    }

    /**
     * Get numbers of space in text.
     * @param text text.
     * @return numbers of space.
     */
    private int numberOfSpace(String text) {
        int result = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("Error in %s: Exceeded waiting time!\n", Thread.currentThread().getName());
                break;
            }
            if (text.charAt(i) == ' ') {
                result++;
            }
        }
        return result;
    }
}
