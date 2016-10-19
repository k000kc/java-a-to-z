package ru.apetrov;

public class MergerArray {
     
	public int[] a;
     	public int[] b;
     
     	public int[] merger(int[] a, int[] b){
     
        	int[] result = new int[a.length + b.length];
         
         	int i = 0;
         	int j = 0;
         	int index = 0;
         
         	while(i < a.length && j < b.length){
             		if (a[i] < b[j]){
                 		result[index++] = a[i];
                 		i++;
             		}else{
                 		result[index++] = b[j];
                 		j++;
             		}
         	}
         
         	while(i < a.length){
             		result[index++] = a[i];
             		i++;
         	}
         	while(j < b.length){
             		result[index++] = b[j];
             		j++;
         	}
         	return result;
     	}
}