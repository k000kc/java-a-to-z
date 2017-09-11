package ru.apetrov.Bombermen.Players;

import java.util.Random;

/**
 * Created by Andrey on 11.09.2017.
 */
public enum Movement {

    /**
     * Up, DOWN, LEFT, RIGHT.
     */
    UP, DOWN, LEFT, RIGHT;

    /**
     * Случайным образом выбираем движение игрока.
     * @return направлние движения игрока.
     */
    public static Movement getRandomMovment() {
        Random random = new Random();
        int result = random.nextInt(values().length);
        return values()[result];
    }
}
