package ru.apetrov.Calculator;

/**
 * Created by Andrey on 05.01.2017.
 */
public class MenuCalculator {

    /**
     * Система ввода.
     */
    private Input input;

    /**
     * Класс калькулятор.
     */
    private Calculator calculator;

    /**
     * Массив действий.
     */
    private BaseActions[] actions = new BaseActions[5];

    /**
     * Переменная для записи аргумента (первого).
     */
    private double first;

    /**
     * Отчистить результат.
     */
    private boolean isClean = false;

    /**
     * Конструктор.
     * @param input ввод.
     * @param calculator ссылка на экземпляр калькулятора.
     */
    public MenuCalculator(Input input, Calculator calculator) {
        this.input = input;
        this.calculator = calculator;
        this.fillAction();
    }

    /**
     * Инициализация массива действий.
     */
    private void fillAction() {
        this.actions[0] = new Add("+", "Addition");
        this.actions[1] = new Sub("-", "Subtraction");
        this.actions[2] = new Mult("*", "Multiplication");
        this.actions[3] = new Div("/", "Division");
        this.actions[4] = new CleanResult("clean", "Clean Result");
    }

    /**
     * показать массив действий.
     */
    private void show() {
        for (BaseActions action : this.actions) {
            System.out.println(action.info());
        }
    }

    /**
     * Выбор действия.
     * @param key ключ действия.
     */
    private void select(String key) {
        for (BaseActions action : this.actions) {
            if (key.equalsIgnoreCase(action.getKey())) {
                action.execute(this.calculator, this.input);
            }
        }
    }

    /**
     * Работа программы.
     */
    public void init() {
        this.show();
        boolean isExit = false;
        while (!isExit) {
            this.first = this.input.askArgument("Введите число:");
            this.isClean = false;
            while (!this.isClean) {
                String operation = this.input.ask("Введите операцию:");
                this.select(operation);
                this.first = this.calculator.getResult();
            }
            if (input.ask("Выйти из программы? (y/n):").equalsIgnoreCase("y")) {
                isExit = true;
            }
        }
    }

    /**
     * Сложение.
     */
    private class Add extends BaseActions {

        /**
         * Конструктор.
         * @param key ключ.
         * @param name имя.
         */
        public Add(String key, String name) {
            super(key, name);
        }

        /**
         * Реализация.
         * @param calculator калькулятор.
         * @param input ввод.
         */
        @Override
        public void execute(Calculator calculator, Input input) {
            double second = input.askArgument("Введите второй аргумент");
            calculator.add(first, second);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    /**
     * Вычитание.
     */
    private class Sub extends BaseActions {

        /**
         * Конструктор.
         * @param key ключ
         * @param name имя.
         */
        public Sub(String key, String name) {
            super(key, name);
        }

        /**
         * Реализация.
         * @param calculator калькулятор.
         * @param input ввод.
         */
        @Override
        public void execute(Calculator calculator, Input input) {
            double second = input.askArgument("Введите второй аргумент");
            calculator.sub(first, second);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    /**
     * Умножение.
     */
    private class Mult extends BaseActions {

        /**
         * Конструктор.
         * @param key ключ.
         * @param name имя.
         */
        public Mult(String key, String name) {
            super(key, name);
        }


        /**
         * Реализация.
         * @param calculator калькулятор.
         * @param input ввод.
         */
        @Override
        public void execute(Calculator calculator, Input input) {
            double second = input.askArgument("Введите второй аргумент");
            calculator.mult(first, second);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    /**
     * Деление.
     */
    private class Div extends BaseActions {

        /**
         * Деление.
         * @param key ключ.
         * @param name имя.
         */
        public Div(String key, String name) {
            super(key, name);
        }

        /**
         * Реализация.
         * @param calculator калькулятор.
         * @param input ввлд.
         */
        @Override
        public void execute(Calculator calculator, Input input) {
            double second = input.askArgument("Введите второй аргумент");
            calculator.div(first, second);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    /**
     * Очистка результата.
     */
    private class CleanResult extends BaseActions {

        /**
         * Конструтор.
         * @param key ключ.
         * @param name имя.
         */
        public CleanResult(String key, String name) {
            super(key, name);
        }

        /**
         * Реализация.
         * @param calculator калькулятор.
         * @param input ввод.
         */
        @Override
        public void execute(Calculator calculator, Input input) {
            isClean = true;
        }
    }
}
