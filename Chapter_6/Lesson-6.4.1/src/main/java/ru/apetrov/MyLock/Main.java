package ru.apetrov.MyLock;

/**
 * Created by Andrey on 09.08.2017.
 */
public class Main {

    /**
     * Класс замка.
     */
    private final Lock lock = new Lock();

    /**
     * Цикл.
     */
    private void loop() {
        this.lock.lock();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s work\n", Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.lock.unLock();
    }

    /**
     * main.
     * @param args args.
     */
    public static void main(String[] args) {
        Main start = new Main();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                start.loop();
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                start.loop();
            }
        });

        thread.start();
        thread1.start();
    }
}
