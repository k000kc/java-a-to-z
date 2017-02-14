package ru.apetrov.TicTacToe.Player;

import ru.apetrov.TicTacToe.Field.GameField;
import ru.apetrov.TicTacToe.Input.Input;

/**
 * Created by Andrey on 12.02.2017.
 */
public class User implements Player {

    private char symbol;

    private GameField gameField;

    public User(char symbol, GameField gameField) {
        this.symbol = symbol;
        this.gameField = gameField;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public void move() {
        Input input = new Input();
        int x;
        int y;
        do {
            x = input.ask("Enter coordinates on the x:");
            y = input.ask("enter coordinates on the y:");
        } while (this.gameField.getFields()[x][y] != '-');
        this.gameField.getFields()[x][y] = this.symbol;
    }
}
