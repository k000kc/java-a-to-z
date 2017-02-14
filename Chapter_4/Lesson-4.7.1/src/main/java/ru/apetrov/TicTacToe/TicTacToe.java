package ru.apetrov.TicTacToe;

import ru.apetrov.TicTacToe.Field.GameField;
import ru.apetrov.TicTacToe.Input.Input;
import ru.apetrov.TicTacToe.Player.Computer;
import ru.apetrov.TicTacToe.Player.Player;
import ru.apetrov.TicTacToe.Player.User;
import ru.apetrov.TicTacToe.Validator.CheckGame;

/**
 * Created by Andrey on 09.02.2017.
 */
public class TicTacToe {

    private GameField gameField;

    private CheckGame checkGame;

    private boolean isMove = false;

    private int size;

    private Player[] players = new Player[2];

    public void initPlayers() {
        players[0] = new User('X',this.gameField);
        players[1] = new Computer('O', this.gameField);
    }

    public void selectFirstMove(Input input) {

        int firstMove;
        do {
            firstMove = input.ask(String.format("%s%n%s%n%s", "Who makes the first move", "User - 1", "Comp - 2"));
        } while (firstMove != 1 && firstMove != 2);
        if (firstMove == 1) {
            players[0].move();
            this.isMove = true;
        } else {
            players[1].move();
            this.isMove = false;
        }
    }

    public void initGame(Input input) {
        do {
            this.size = input.ask("Input size of field");
        } while (this.size < 3);
        this.gameField = new GameField(this.size);
        this.checkGame = new CheckGame(this.gameField);
        this.initPlayers();
        this.selectFirstMove(input);
        do {
            this.gameField.showField();
            if (!this.isMove) {
                this.players[0].move();
                this.isMove = true;
            } else {
                this.players[1].move();
                this.isMove = false;
            }
        } while (!this.checkGame.isGameOver());
        this.gameField.showField();
    }

    public static void main(String[] args) {
        Input input = new Input();
        new TicTacToe().initGame(input);
    }
}
