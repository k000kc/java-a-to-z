package ru.apetrov.start;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;

public class StartUITest{

	boolean isExit = false;
	Tracker tracker = new Tracker();
	Input input = new StubInput(new String[] {"1", "first item", "this is a first item", "2", "1", "edit item", "this is a edit item", "3", "1", "4", "second item", "5", "this is a second item", "6", "2", "THIS IS A COMMENT", "7", "8"});

	@Test
	public void initTest(){
		do{

			String action = input.ask("Select an action:\n1. - Add Item;\n2. - Edit Item;\n3. - Remove Item;\n4. - Find by Name;\n5. - Find by Descriotion;\n6. - Add Comment;\n7. - Get All Item.\n8. - Exit\n");

			if (action.equals("1")){
				String name = input.ask("Enter the name of the Item:");
				String desc = input.ask("Enter the description of the Item:");
				tracker.add(new Task(name, desc));
				for (Item item : tracker.getAll()){
					if(item != null){
						item.setId("1");
						assertThat(item.getName(), is("first item"));
						assertThat(item.getDescription(), is("this is a first item"));
					}
				}
		
			}

			if (action.equals("2")){
				String id =  input.ask("Enter the id of the Item:");
				if (tracker.findById(id) != null){
					String name = input.ask("Enter the new name of the Item:");
					String desc = input.ask("Enter the new description of the Item:");
					Item editItem = new Task(name, desc);
        				editItem.setId(id);
        				tracker.edit(editItem);
					for (Item item : tracker.getAll()){
						if(item != null){
							item.setId("1");
							assertThat(item.getName(), is("edit item"));
							assertThat(item.getDescription(), is("this is a edit item"));
					}
				}
					
				}else{
					System.out.println("Item with this Id does not exist");
				}
		
			}

			if (action.equals("3")){			
				String id =  input.ask("Enter the id of the Item:");
				if (tracker.findById(id) != null){
					Item delItem = tracker.findById(id);
					tracker.remove(delItem);
					for(Item item : tracker.getAll()){
						assertNull(item);
					}
				}else{
					System.out.println("Item with this Id does not exist");
				}			
			}

			if (action.equals("4")){
				tracker.add(new Task("second item", "this is a second item"));
				String name =  input.ask("Enter the name of the Item:");
        			for (Item item : tracker.findByName(name)) {
					item.setId("2");
					assertThat(item.getName(), is("second item"));
        			}		
			}

			if (action.equals("5")){
				String desc =  input.ask("Enter the description of the Item:");
        			for (Item item : tracker.findByDesc(desc)) {
					assertThat(item.getDescription(), is("this is a second item"));
        			}		
			}

			if (action.equals("6")){
				String id =  input.ask("Enter the id of the Item:");
				String text = input.ask("Enter Comment:");
				if (tracker.findById(id) != null){
					Item item = tracker.findById(id);
					Comment comment = new Comment(text); 
					tracker.addComment(item, comment);
					for (Item index : tracker.getAll()){
						if(index != null){
							assertThat(item.getComment().getValue(), is("THIS IS A COMMENT"));		
						}else{
							System.out.println("Item with this Id does not exist");
						}			
					} 
				}
			}


			if (action.equals("7")){
		        	for (Item item : tracker.getAll()){
           				System.out.println(item.getName());
           				System.out.println(item.getDescription());
           				System.out.println(item.getId());
           				System.out.println(item.getComment());
					for (Item index : tracker.getAll()){
						if(index != null){
							assertThat(item.getName(), is("second item"));
							assertThat(item.getDescription(), is("this is a second item"));
							assertThat(item.getComment().getValue(), is("THIS IS A COMMENT"));
							assertThat(item.getId(), is("2"));
						}
					}
        			}
			} 

			if (action.equals("8")){
				isExit = true;
			}

		}while(!isExit);		
	}
}