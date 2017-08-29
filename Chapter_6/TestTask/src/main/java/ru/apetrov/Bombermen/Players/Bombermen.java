package ru.apetrov.Bombermen.Players;

import ru.apetrov.Bombermen.Board;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Bombermen extends Players {

    public Bombermen(Board board, int x, int y, String name) {
        super(board, x, y, name);
    }

    @Override
    public void run() {
        super.move();
    }
}
