package ru.apetrov.Calculator;

/**
 * Created by Andrey on 05.01.2017.
 */
public class MenuCalculator {

    Input input;
    Calculator calculator;
    BaseActions[] actions = new BaseActions[5];
    boolean isClean = false;
    boolean isExit = false;
    double first;
    double second;
    String operation;

    public MenuCalculator(Input input, Calculator calculator) {
        this.input = input;
        this.calculator = calculator;
        fillAction();
    }

    public void fillAction() {
        actions[0] = new Add("+", "Addition");
        actions[1] = new Sub("-", "Subtraction");
        actions[2] = new Mult("*", "Multiplication");
        actions[3] = new Div("/", "Division");
        actions[4] = new CleanResult("clean", "Clean Result");
    }

    private void show() {
        for (BaseActions action : actions) {
            System.out.println(action.info());
        }
    }

    private void select(String key) {
        for (BaseActions action : actions) {
            if (key.equalsIgnoreCase(action.getKey())) {
                action.execute(this.calculator, this.input);
            }
        }
    }

    public void init() {
        show();
        isClean = false;
        while (!isExit) {
            this.first = this.input.askArgument("Введите число:");
            isClean = false;
            while (!isClean) {
                this.operation = this.input.ask("Введите операцию:");
                select(this.operation);
                this.first = calculator.getResult();
            }
            if (input.ask("Выйти из программы? (y/n):").equalsIgnoreCase("y")) {
                isExit = true;
            }
        }
    }

    private class Add extends BaseActions {

        public Add(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Calculator calculator, Input input) {
            second = input.askArgument("Введите второй аргумент");
            calculator.add(first, second);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    private class Sub extends BaseActions {
        public Sub(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Calculator calculator, Input input) {
            second = input.askArgument("Введите второй аргумент");
            calculator.sub(first, second);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    private class Mult extends BaseActions {
        public Mult(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Calculator calculator, Input input) {
            second = input.askArgument("Введите второй аргумент");
            calculator.mult(first, second);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    private class Div extends BaseActions {
        public Div(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Calculator calculator, Input input) {
            second = input.askArgument("Введите второй аргумент");
            calculator.div(first, second);
            System.out.printf("%s%n", calculator.getResult());
        }
    }

    private class CleanResult extends BaseActions {

        public CleanResult(String key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Calculator calculator, Input input) {
            isClean = true;
        }
    }
}
