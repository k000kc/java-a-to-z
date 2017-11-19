package ru.apetrov.Bombermen;

import ru.apetrov.Bombermen.Players.Bombermen;
import ru.apetrov.Bombermen.Players.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrey on 29.08.2017.
 */
public class Game {

    /**
     * Монстры.
     */
    private List<Monster> monsters;

    /**
     * число монстров.
     */
    private int numberOfMonsters;

    /**
     * игровое поле.
     */
    private Board board;

    /**
     * Игрок.
     */
    private Bombermen bombermen;

    /**
     * Конструктор.
     * @param height высота поля.
     * @param width ширина поля.
     * @param numberOfMonsters число монстров.
     */
    public Game(int height, int width, int numberOfMonsters) {
        this.numberOfMonsters = numberOfMonsters;
        this.monsters = new ArrayList<>(this.numberOfMonsters);
        this.board = new Board(height, width);
        this.addMonsters();
        this.addBomberman();
    }

    /**
     * Добавить монстра на поле.
     */
    private void addMonsters() {
        Random random = new Random();
        for (int i = 0; i < this.numberOfMonsters; i++) {
            while (true) {
                int x = random.nextInt(this.board.getHeight());
                int y = random.nextInt(this.board.getWidth());
                try {
                    if (this.board.getBoard()[x][y].tryLock(1, TimeUnit.MILLISECONDS)) {
                        String nameOfMonster = String.format("%s: ", "Monster-" + i);
                        this.monsters.add(new Monster(this.board, new Position(x, y), nameOfMonster));
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Добавить игрока на поле.
     */
    private void addBomberman() {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(this.board.getHeight());
            int y = random.nextInt(this.board.getWidth());
            try {
                if (this.board.getBoard()[x][y].tryLock(1, TimeUnit.MILLISECONDS)) {
                    this.bombermen = new Bombermen(this.board, new Position(x, y), "BOMBERMAN");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Запуск игры.
     */
    private void startGame() {
        for (Monster monster : this.monsters) {
            new Thread(monster).start();
        }
        new Thread(this.bombermen).start();
    }

    /**
     * MAIN.
     * @param args args.
     */
    public static void main(String[] args) {
        Game game = new Game(10, 10, 5);
        game.startGame();
    }
}
