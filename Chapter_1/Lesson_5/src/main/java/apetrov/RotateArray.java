package apetrov;

public class RotateArray{

	public int[][] values;

	public RotateArray(int[][] values){
		this.values = values;
	}

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