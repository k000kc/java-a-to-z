package ru.apetrov.Bombermen;

import ru.apetrov.Bombermen.Players.Bombermen;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Game {
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        Thread thread = new Thread(new Bombermen(board, new Position(0, 0), "BOMBERMEN"));
        Thread thread1 = new Thread(new Bombermen(board, new Position(0, 0), "MONSTR"));
        thread.start();
        thread1.start();
    }
}
