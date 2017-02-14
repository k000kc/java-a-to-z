package ru.apetrov.TicTacToe.Player;

import ru.apetrov.TicTacToe.Field.GameField;

/**
 * Created by Andrey on 12.02.2017.
 */
public interface Player {

    char getSymbol();

    void move();
}
