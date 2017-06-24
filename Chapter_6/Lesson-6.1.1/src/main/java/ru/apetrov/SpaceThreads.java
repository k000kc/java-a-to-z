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
        System.out.printf("%s%s\n", "Number of spaces = " ,this.numberOfSpace(this.text));
    }

    private int numberOfSpace(String text) {
        int result = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Thread.currentThread().isInterrupted()) {
//                try {
//                    throw new InterruptedException(String.format("Error in %s: Exceeded waiting time!" , Thread.currentThread().getName()));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
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
