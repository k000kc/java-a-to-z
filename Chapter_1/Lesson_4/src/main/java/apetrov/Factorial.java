package apetrov;

/**
* Класс для вычисления факториала
* @author Andrey
*/
public class Factorial{

	public int number;
	public int result = 1;

	/**
	* Матод вычисляющий факториал
	* @param number
	* @return result
	*/
	public int getFactorial(int number){
		for (int i = number; i > 0; i--){
			result = result*i;
		}
		return result;
	}
}
