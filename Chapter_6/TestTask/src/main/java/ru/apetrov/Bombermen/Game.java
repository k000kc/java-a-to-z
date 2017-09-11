package ru.apetrov.Bombermen;

import ru.apetrov.Bombermen.Players.Monsters;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Game {

    /**
     * MAIN.
     * @param args args.
     */
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        Thread thread = new Thread(new Monsters(board, new Position(0, 0), "BOMBERMEN"));
        Thread thread1 = new Thread(new Monsters(board, new Position(0, 1), "MONSTR"));
        thread.start();
        thread1.start();
    }
}
