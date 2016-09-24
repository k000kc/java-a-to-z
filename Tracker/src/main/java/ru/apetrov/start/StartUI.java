package ru.apetrov.start;

import ru.apetrov.models.*;

public class StartUI{
	
	public static void main(String[] args){
		
		Tracker tracker = new Tracker();

        	Item item1 = new Task("first task", "first desc");
        	Item item2 = new Task("second task", "second desc");
        	Item item3 = new Task("three task", "three desc");
        	Item item4 = new Task("four task", "four desc");
        	Item item5 = new Task("five task", "five desc");
        	Item item6 = new Task("six item", "six desc");

		tracker.add(item1);
        	tracker.add(item2);
        	tracker.add(item3);
        	tracker.add(item4);
        	tracker.add(item5);
        	tracker.add(item6);

        	System.out.println("----------------------get all---------------------------------");
		
        	for (Item item : tracker.getAll()){
            		System.out.println(item.getName());
        	}

        	System.out.println("-----------------------find by name--------------------------------");
		
        	for (Item item : tracker.findByName("three task")) {
            		System.out.println(item.getName());
       	 	}

        	System.out.println("-----------------------find by description--------------------------------");

        	for (Item item : tracker.findByDesc("four desc")) {
            		System.out.println(item.getName());
        	}

        	System.out.println("--------------------------del item-----------------------------");

        	tracker.remove(item3);

        	for (Item item : tracker.getAll()){
            		System.out.println(item.getName());
        	}

        	System.out.println("----------------------edit item---------------------------------");

        	Item editItem = new Task("editItem task", "editItem desk");
        	editItem.setId(item2.getId());
        	tracker.edit(editItem);
        	for (Item item : tracker.getAll()){
            		System.out.println(item.getName());
        	}

        	System.out.println("---------------add comment------------------------");

        	Comment comment = new Comment("This is a comment");
        	tracker.addComment(item6, comment);
        	System.out.println(item6.getComment().getValue());
	}
}