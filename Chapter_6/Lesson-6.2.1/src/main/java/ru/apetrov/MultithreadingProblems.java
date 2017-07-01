package ru.apetrov;

/**
 * Created by Andrey on 01.07.2017.
 */
public class MultithreadingProblems {

    /**
     * count.
     */
    private static int count = 0;

    /**
     * main.
     * @param args args.
     * @throws InterruptedException exeption.
     */
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            Thread a = new Thread(new Runnable() {
                @Override
                public void run() {
                    count++;
                }

            });

            Thread b = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (count % 2 == 0) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(count);
                    }
                }
            });

            a.start();
            b.start();
        }
    }
}
