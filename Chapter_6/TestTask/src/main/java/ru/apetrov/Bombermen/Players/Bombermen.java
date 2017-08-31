package ru.apetrov.Bombermen.Players;

import ru.apetrov.Bombermen.Board;
import ru.apetrov.Bombermen.Position;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Bombermen extends Players {

    public Bombermen(Board board, Position position, String name) {
        super(board, position, name);
    }

    @Override
    public void run() {
        super.move();
    }
}
