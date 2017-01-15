package ru.apetrov.Calculator;

/**
 * Created by Andrey on 09.01.2017.
 */
public class TrigAction extends Calculator {

    /**
     * Синус.
     * @param value значение.
     */
    public void sinus(double value) {
        this.setResult(Math.sin(value));
    }

    /**
     * Косинус.
     * @param value значение.
     */
    public void cosinus(double value) {
        this.setResult(Math.cos(value));
    }

    /**
     * Тангенс.
     * @param value значение.
     */
    public void tangens(double value) {
        this.setResult(Math.tan(value));
    }

    /**
     * Катангенс.
     * @param value значение.
     */
    public void catangens(double value) {
        this.setResult(1.0 / Math.tan(value));
    }
}
