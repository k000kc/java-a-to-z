package ru.apetrov.producerCustomer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrey on 26.07.2017.
 * @param <E> type
 */
public class ProducerCustomer<E> {

    /**
     * Очередь которую будем блокировать.
     */
    private final Queue<E> queue;

    /**
     * переключатель выполнения потоков.
     */
    private boolean switcher;

    /**
     * Конструктор.
     */
    public ProducerCustomer() {
        this.queue = new LinkedList<E>();
        this.switcher = false;
    }

    /**
     * Добавление элемента в очередь.
     * @param element елемент
     */
    public void add(E element) {
        synchronized (this.queue) {
            while (this.switcher) {
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.queue.add(element);
            System.out.printf("add element %s\n", element);
            this.queue.notify();
            this.switcher = true;
        }
    }

    /**
     * Вывод елемента из очереди.
     */
    public void get() {
        synchronized (this.queue) {
            while (!this.switcher || this.queue.peek() == null) {
                try {
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("get %s\n", this.queue.poll());
            this.queue.notify();
            this.switcher = false;
        }
    }

    /**
     * Main.
     * @param args args
     */
    public static void main(String[] args) {
        ProducerCustomer<Integer> prodCust = new ProducerCustomer<>();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    prodCust.add(i);
                }
            }
        });

        Thread customer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    prodCust.get();
                }
            }
        });

        producer.start();
        customer.start();
    }
}