package ru.apetrov;

public class Main{

	/**
	 * main.
	 * @param args args.
	 */
	public static void main(String[] args) {

		/**
		 * первое число.
		 */
		int x1 = 1;

		/**
		 * второе числоа.
		 */
		int x2 = 10;
		Square square = new Square(1, 2, 3);
        
        	for (int i = x1; i < x2; i++){
            		System.out.println(square.calculate(i));
        	}
    	}
}