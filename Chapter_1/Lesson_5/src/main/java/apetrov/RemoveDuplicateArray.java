package apetrov;

/**
 * Класс для удаления дубликатов в массиве
 */
public class RemoveDuplicateArray{

	public String[] values;

	/*
	* Метод удаляет дубликаты в массиве
	* @param String[] values
	* @return result
	*/
	public RemoveDuplicateArray(String[] values){
		this.values = values;
	}

	public String[] rmDuplicate(String[] values){
		
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
