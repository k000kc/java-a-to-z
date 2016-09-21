package ru.apetrov.start;

import ru.apetrov.models.*;

public class StartUI{
	
	public static void main(String[] args){
		
		Tracker tracker = new Tracker();
		tracker.add(new Task("first task", "first desc"));
		tracker.add(new Task("second task", "second desc")); 
		for (Item item : tracker.getAll()){
			System.out.println(item.getName());
		}
	}
}