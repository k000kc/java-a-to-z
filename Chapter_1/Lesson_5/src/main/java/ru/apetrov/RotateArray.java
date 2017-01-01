package ru.apetrov;

/**
 * Класс поворачивает массив на 90 градусов
*/
public class RotateArray{

	/**
	 * исходный массив.
	 */
	public int[][] values;

	/**
	 * конструктор.
	 * @param values исходний массив.
	 */
	public RotateArray(int[][] values){
		this.values = values;
	}

	/**
	 * Метод поворачивает массив на 90 градусов
	 * @return result
	 */
	public int[][] rotate(int[][] values){
		int[][] result = values;
		int temp;
		int n = result.length;
		for(int i = 0; i < n; i++){
			for(int j = i; j < n - i - 1; j++){
				temp = result[i][j];
				result[i][j] = result[n - j - 1][i];
				result[n - j - 1][i] = result[n - i - 1][n - j - 1];
				result[n - i - 1][n - j - 1] = result[j][n - i - 1];
				result[j][n - i -1] = temp;
			}
		}
	return result;
	}
}
