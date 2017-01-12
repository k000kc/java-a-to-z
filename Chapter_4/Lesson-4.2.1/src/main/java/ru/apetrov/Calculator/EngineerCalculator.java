package ru.apetrov.Calculator;

/**
 * Created by Andrey on 09.01.2017.
 */
public class EngineerCalculator extends MenuCalculator {

    /**
     * Конструктор.
     *
     * @param input      ввод.
     * @param calculator ссылка на экземпляр калькулятора.
     * @param size размер массива.
     */
    public EngineerCalculator(Input input, TrigAction calculator, int size) {
        super(input, calculator, size);
    }


    /**
     * Инициализация тригонометрических действий.
     */
    @Override
    protected void fillAction() {
        super.fillAction();
        this.actions[5] = new Sinus("sin", "Sinus");
        this.actions[6] = new Cosinus("cos", "Cosinus");
        this.actions[7] = new Tangens("tan", "Tangens");
        this.actions[8] = new Catangens("cat", "Catangens");
    }

    /**
     * Синус.
     */
    private class Sinus extends BaseActions {

        /**
         * Конструктор.
         *
         * @param key  ключ.
         * @param name имя.
         */
        private Sinus(String key, String name) {
            super(key, name);
        }

        /**
         * Реализация.
         * @param calculator класс.
         * @param input ввод.
         */
        @Override
        public void execute(TrigAction calculator, Input input) {
            calculator.sinus(first);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    /**
     * Косинус.
     */
    private class Cosinus extends BaseActions {

        /**
         * Конструктор.
         *
         * @param key  ключ.
         * @param name имя.
         */
        private Cosinus(String key, String name) {
            super(key, name);
        }

        /**
         * Реализация.
         * @param calculator класс.
         * @param input ввод.
         */
        @Override
        public void execute(TrigAction calculator, Input input) {
            calculator.cosinus(first);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    /**
     * Тангенс.
     */
    private class Tangens extends BaseActions {

        /**
         * Конструктор.
         *
         * @param key  ключ.
         * @param name имя.
         */
        private Tangens(String key, String name) {
            super(key, name);
        }

        /**
         * Реализация.
         * @param calculator класс.
         * @param input ввод.
         */
        @Override
        public void execute(TrigAction calculator, Input input) {
            calculator.tangens(first);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    /**
     * Катангенс.
     */
    private class Catangens extends BaseActions {

        /**
         * Конструктор.
         *
         * @param key  ключ.
         * @param name имя.
         */
        private Catangens(String key, String name) {
            super(key, name);
        }

        /**
         * Реализация.
         * @param calculator класс.
         * @param input ввод.
         */
        @Override
        public void execute(TrigAction calculator, Input input) {
            calculator.catangens(first);
            System.out.printf("%s%n%", calculator.getResult());
        }
    }
}
