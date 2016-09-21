package ru.apetrov.start;

import ru.apetrov.models.*;
import java.util.*;

public class Tracker{
	
	private Item[] items = new Item[10];
	private int position = 0;
	private static final Random RN = new Random();

	public Item add(Item item){
		item.setId(this.generateId());
		this.items[position++] = item;
		return item;
	}

    	public  void remove(Item item){
        	for (int index = 0; index < this.items.length; index++){
            		if (this.items[index].equals(item)){
                		this.items[index] = null;
                		break;
            		}
        	}

        	for (int i = 0; i < this.items.length-1; i++){
            		if (this.items[i] == null){
                		for (int j = i + 1; j < this.items.length; j++){
                    			if (items[j] != null){
                        			items[i] = items[j];
                        			items[j] = null;
                        			break;
                    			}
                		}
            		}
        	}
        	position--;
    	}

	protected Item findById(String id){
		Item result = null;
		for (Item item : items){
			if(item != null && item.getId().equals(id)){
				result = item;
				break;
			}
		}
		return result;
	}

    	protected Item[] findByName(String name){
        	int numbDuplicateName = 0;
        	for(Item item : items){
            		if (item != null && item.getName().equals(name)){
                		numbDuplicateName++;
            		}
        	}

        	Item[] result = new Item[numbDuplicateName];
        	int namePosition = 0;
        	for (int index = 0; index < items.length; index++){
            		if (items[index] != null && items[index].getName().equals(name)){
                		result[namePosition++] = items[index];
            		}
        	}
        	return result;
    	}	

	String generateId(){
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}

	public Item[] getAll(){
		Item[] result = new Item[position];
		for(int index = 0; index != this.position; index++){
			result[index] = this.items[index];
		}
		return result;
	}
}