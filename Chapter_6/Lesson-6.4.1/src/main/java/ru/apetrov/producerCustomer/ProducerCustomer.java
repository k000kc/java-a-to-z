package ru.apetrov.producerCustomer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Andrey on 26.07.2017.
 */
public class ProducerCustomer<E> {

    private Queue<E> queue;

    private boolean isLock = false;

    public ProducerCustomer() {
        this.queue = new LinkedList<E>();
    }

    public void add(E element) {
        synchronized (this) {
            while (this.isLock) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.isLock = true;
        this.queue.add(element);
        System.out.printf("%s add in queue \n", element);

        synchronized (this) {
            notify();
            this.isLock = false;
        }
    }

    public void get() {
        synchronized (this) {
            while (this.isLock || this.queue.peek() == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.isLock = true;
        System.out.printf("get element %s in queue\n", this.queue.poll());

        synchronized (this) {
            notify();
            this.isLock = false;
        }
    }

    public static void main(String[] args) {
        ProducerCustomer<Integer> prodCust = new ProducerCustomer<>();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    prodCust.add(i);
                }
            }
        });

        Thread customer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    prodCust.get();
                }
            }
        });

        producer.start();
        customer.start();
    }
}
