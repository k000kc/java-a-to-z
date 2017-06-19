package ru.apetrov;

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

    private void startThreads() {
        System.out.printf("%s\n%s%s\n", "Start the program:", "Text to check: - ", this.text);
        Thread thread1 = new Thread(this.spaceThreads);
        Thread thread2 = new Thread(this.wordThreads);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish of the program!");
    }

    public static void main(String[] args) {
        new MyThread("String for checking the execution of the program").startThreads();
    }
}
