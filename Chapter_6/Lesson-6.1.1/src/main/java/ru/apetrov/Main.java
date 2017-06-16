package ru.apetrov;

/**
 * Created by Andrey on 16.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        SpaceThreads spaceThreads = new SpaceThreads("In this line, spaces are counted");
        WordThreads wordThreads = new WordThreads("In this line words are counted");

        Thread t1 = new Thread(spaceThreads);
        Thread t2 = new Thread(wordThreads);

        t1.start();
        t2.start();
    }
}
