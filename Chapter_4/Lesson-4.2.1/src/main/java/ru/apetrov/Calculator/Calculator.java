package ru.apetrov.Calculator;

/**
 * @author apetrov
 * Calculator class.
 */
public class Calculator {
/**
 * field result.
 */
    private double result;

    /**
     * add operation.
     * @param first - first
     * @param second - second
    */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
    * sub operation.
    * @param first - first
    * @param second - second
    */
    public void sub(double first, double second) {
        this.result = first - second;
    }

    /**
    * mult operation.
    * @param first - first
    * @param second - second
    */
    public void mult(double first, double second) {
        this.result = first * second;
    }

    /**
    * div operation.
    * @param first - first
    * @param second - second
    */
    public void div(double first, double second) {
        if (second != 0) {
            this.result = first / second;
        } else {
            System.out.println("You can not divide by 0");
        }
    }

    /**
    * Result.
    * @return result
    */
    public double getResult() {
        return this.result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
