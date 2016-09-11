package apetrov;

public class BubbleSortArray {
    
	public int[] values;
    
	public BubbleSortArray(int[] values){
        	this.values = values;
    	}

	public int[] sort(){

        	int temp;
		int[] result = values;

		for(int i = 0; i < result.length; i++){
            		for(int j = 0; j < result.length-1; j++){
				if(result[j] > result[j+1]){
                    			temp = result[j+1];
                    			result[j+1] = result[j];
                    			result[j] = temp;
                		}
            		}
		}
        	return result;
	}
}