package ru.apetrov;

/**
 * Класс для удаления дубликатов в массиве
 */
public class RemoveDuplicateArray{

	/**
	 * исходный массив.
	 */
	public String[] values;

	/*
	* Конструктор.
	* @param String[] values
	* @return result
	*/
	public RemoveDuplicateArray(String[] values){
		this.values = values;
	}


	/**
	 * Метод удаляет дубликаты в массиве.
	 * @return
	 */
	public String[] rmDuplicate(){

		/**
		 * Длина результирующего массива.
		 */
		int sizeResult = this.values.length;

		for(int i = 0; i < this.values.length; i++){
			if(this.values[i] != null){
				for(int j = i + 1; j < this.values.length; j++){
					if(this.values[i].equals(this.values[j])){
						this.values[j] = null;
						sizeResult--;
					}
				}
			}
		}
		int index = 0;
		String[] result = new String[sizeResult];
		for(int i = 0; i < this.values.length; i++){
			if(this.values[i] != null){
				result[index] = this.values[i];
				index++;
			}
		}
		return result;
	}
}
