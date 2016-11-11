package ru.apetrov;

public class Main{

	public static void main(String[] args) {

		int x1 = 1;
        	int x2 = 10;
		Square square = new Square(1, 2, 3);
        
        	for (int i = x1; i < x2; i++){
            		System.out.println(square.calculate(i));
        	}
    	}
}