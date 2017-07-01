package ru.apetrov;

import java.util.Scanner;

/**
 * Created by Andrey on 16.06.2017.
 */
public class MyThread {

    /**
     * Input text.
     * @return text.
     */
    private static String inputText() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input text:");
        return scanner.nextLine();
    }

    /**
     * main.
     * @param args args.
     * @throws InterruptedException exeption.
     */
    public static void main(String[] args) throws InterruptedException {
        String text = MyThread.inputText();
        System.out.printf("%s\n%s%s\n", "Start the program:", "Text to check: - ", text);
        Thread thread1 = new Thread(new SpaceThreads(text));
        Thread thread2 = new Thread(new WordThreads(text));
        long start = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        while (thread1.isAlive() && thread2.isAlive()) {
            thread1.join(1);
            thread2.join(1);
            if ((System.currentTimeMillis() - start > 1)) {
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

        long finish = System.currentTimeMillis() - start;
        System.out.println(finish + "ms");
    }
}
