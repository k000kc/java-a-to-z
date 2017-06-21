package ru.apetrov;

import java.util.Scanner;

/**
 * Created by Andrey on 16.06.2017.
 */
public class MyThread {

    private SpaceThreads spaceThreads;
    private WordThreads wordThreads;
    private String text;

    public MyThread(String text) {
        this.text = text;
        this.spaceThreads = new SpaceThreads(text);
        this.wordThreads = new WordThreads(text);
    }

    private void startThreads() throws InterruptedException {
        System.out.printf("%s\n%s%s\n", "Start the program:", "Text to check: - ", this.text);
        Thread thread1 = new Thread(this.spaceThreads);
        Thread thread2 = new Thread(this.wordThreads);
        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        while (thread1.isAlive() && thread2.isAlive()) {
            thread1.join(5);
            thread2.join(5);
            if ((System.currentTimeMillis() - start > 10)) {
                if (thread1.isAlive()) {
                    thread1.interrupt();
                    thread1.join();
                }
                if (thread2.isAlive()) {
                    thread2.interrupt();
                    thread2.join();
                }
            }
        }

        System.out.println("Finish of the program!");
    }

    private static String inputText() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input text:");
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String text = MyThread.inputText();
        long start = System.currentTimeMillis();

        try {
            new MyThread(text).startThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long finish = System.currentTimeMillis() - start;
        System.out.println(finish + "ms");
    }
}
