package ru.apetrov;

/**
 * квадратное уравнение.
 */
public class Square{

	/**
	* Вычисляет формулу квадратного уравнения.
	* @author Andrey.
	*/	
	public int a;
	public int b;
	public int c;

	/**
	 * конструктор.
	 * @param a 1 число.
	 * @param b 2 число.
	 * @param c 3 число.
	 */
	public Square(int a, int b, int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	* Вычисляем значение функции в точке.
	* @param x корень.
	* @return Значение функции.
	*/
	public float calculate(int x){
		return (float)(this.a * Math.pow(x, 2) + this.b * x + this.c);
	}
}
