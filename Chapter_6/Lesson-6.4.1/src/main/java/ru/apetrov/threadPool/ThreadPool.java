package ru.apetrov.threadPool;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrey on 31.07.2017.
 */
public class ThreadPool {

    /**
     * Колличество ядер.
     */
    private final int numberOfCore;

    /**
     * Очередь заданий.
     */
    private final Queue<Runnable> works;

    /**
     * Пул потоков.
     */
    private Thread[] threads;


    /**
     * Конструктор.
     */
    public ThreadPool() {
        this.numberOfCore = Runtime.getRuntime().availableProcessors();
        this.works = new LinkedList<>();
        this.threads = new Thread[this.numberOfCore];
        this.initCores();
    }

    /**
     * инициализация пула потоков.
     */
    private void initCores() {
        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new Thread(new Worker());
            this.threads[i].start();
        }
    }

    /**
     * Добавление задания.
     * @param work задание.
     */
    public void add(Runnable work) {
        synchronized (this.works) {
            this.works.add(work);
            this.works.notify();
        }
    }

    /**
     * Менеджер заданий.
     */
    private class Worker implements Runnable {

        @Override
        public void run() {
            Runnable r;
            while (true) {
                synchronized (works) {
                    while (works.isEmpty()) {
                        try {
                            works.wait();
                            System.out.printf("wait %s\n", Thread.currentThread().getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    r = (Runnable) works.poll();
                    System.out.printf("add %s\n", Thread.currentThread().getName());
                }
                r.run();
            }
        }
    }

    /**
     * Main.
     * @param args args.
     */
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        Thread work = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.printf("%s working...\n\n", Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("!!!%s end work!!!\n", Thread.currentThread().getName());
            }
        });

        for (int i = 0; i < 10; i++) {
            threadPool.add(work);
        }
    }
}
