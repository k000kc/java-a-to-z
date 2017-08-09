package ru.apetrov.MyLock;

/**
 * Created by Andrey on 09.08.2017.
 */
public class Lock {

    /**
     * Замок.
     */
    private boolean isLock = false;

    /**
     * Блокировка потоков.
     */
    public void lock() {
        synchronized (this) {
            while (isLock) {
                try {
                    System.out.printf("%s -> lock\n", Thread.currentThread().getName());
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.isLock = true;
        }
    }

    /**
     * Разблокировка потоков.
     */
    public void unLock() {
        synchronized (this) {
            this.notify();
            System.out.printf("%s -> unLock\n", Thread.currentThread().getName());
            this.isLock = false;
        }
    }
}
