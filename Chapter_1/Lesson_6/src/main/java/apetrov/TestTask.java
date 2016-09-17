package apetrov;

public class TestTask{
	
	public boolean contains(String origin, String sub){
		
		char[] originArray = origin.toCharArray();
		char[] subArray = origin.toCharArray();

		int sizeSub;
		boolean result = false;

		for(int i = 0; i < originArray.length; i++){
			if(originArray[i] == subArray[0]){
				sizeSub = 1;
				if(subArray.length == sizeSub){
					result = true;
				}
				int index = i;

				for(int j = 1; j < subArray.length; j++){
					if(subArray[j] == originArray[index+1]){
						sizeSub++;
						if(sizeSub == subArray.length){
							result = true;
						}
					}
					index++;
				}
			}
		}
		return result;	
	}
}