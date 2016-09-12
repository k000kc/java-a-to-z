package apetrov;

/**
* Класс для вычисления факториала
* @author Andrey
*/
public class Factorial{

	/**
	* Матод вычисляющий факториал
	* @param number
	* @return result
	*/
	public int getFactorial(int number){
		int result = 1;
		for (int i = number; i > 0; i--){
			result *= i;
		}
		return result;
	}
}
