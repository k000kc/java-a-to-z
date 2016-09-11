package apetrov;

public class Square{

	/**
	* Вычисляет формулу квадратного уравнения
	* @author Andrey
	*/	
	public int a;
	public int b;
	public int c;

	public Square(int a, int b, int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	* Вычисляем значение функции в точке
	* @param x
	* @return Значение функции
	*/
	public float calculate(int x){
		return (float)(this.a * Math.pow(x, 2) + this.b * x + this.c);
	}
}
