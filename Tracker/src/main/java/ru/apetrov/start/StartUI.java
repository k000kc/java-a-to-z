package ru.apetrov.start;

import ru.apetrov.models.*;

public class StartUI{

	private Input input;

	public StartUI(Input input){
		this.input = input;
	}

	public void init(){
		Tracker tracker = new Tracker();
		boolean isExit = false;
	
		do{

			String action = input.ask("Select an action:\n1. - Add Item;\n2. - Edit Item;\n3. - Remove Item;\n4. - Find by Name;\n5. - Find by Descriotion;\n6. - Add Comment;\n7. - Get All Item.\n8. - Exit\n");

			if (action.equals("1")){
				String name = input.ask("Enter the name of the Item:");
				String desc = input.ask("Enter the description of the Item:");
				tracker.add(new Task(name, desc));
				continue;		
			}

			if (action.equals("2")){
				String id =  input.ask("Enter the id of the Item:");
				if (tracker.findById(id) != null){
					String name = input.ask("Enter the new name of the Item:");
					String desc = input.ask("Enter the new description of the Item:");
					Item editItem = new Task(name, desc);
        				editItem.setId(id);
        				tracker.edit(editItem);
				}else{
					System.out.println("Item with this Id does not exist");
				}
		
			}

			if (action.equals("3")){
				String id =  input.ask("Enter the id of the Item:");
				if (tracker.findById(id) != null){
					Item delItem = tracker.findById(id);
					tracker.remove(delItem);
				}else{
					System.out.println("Item with this Id does not exist");
				}			
			}

			if (action.equals("4")){
				String name =  input.ask("Enter the name of the Item:");
        			for (Item item : tracker.findByName(name)) {
            				System.out.println(item.getName());
        			}		
			}

			if (action.equals("5")){
				String desc =  input.ask("Enter the description of the Item:");
        			for (Item item : tracker.findByDesc(desc)) {
            				System.out.println(item.getName());
        			}		
			}

			if (action.equals("6")){
				String id =  input.ask("Enter the id of the Item:");
				String text = input.ask("Enter Comment:");
				if (tracker.findById(id) != null){
					Item item = tracker.findById(id);
					Comment comment = new Comment(text); 
					tracker.addComment(item, comment);
				}else{
					System.out.println("Item with this Id does not exist");
				}			
			}

			if (action.equals("7")){
		        	for (Item item : tracker.getAll()){
           				System.out.println(item.getName());
           				System.out.println(item.getDescription());
           				System.out.println(item.getId());
           				System.out.println(item.getComment());
        			}
			}

			if (action.equals("8")){
				isExit = true;
			}

		}while(!isExit);
	}

	public static void main(String[] args){
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
}