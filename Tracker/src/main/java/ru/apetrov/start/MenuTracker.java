package ru.apetrov.start;

import ru.apetrov.models.*;

public class MenuTracker {

	private Input input;
	private Tracker tracker;
	private UserAction[] actions = new UserAction[7];	

	public MenuTracker(Input input, Tracker tracker){
		this.input = input;
		this.tracker = tracker;
	}
	
	public int[] getRanges(){
		int[] result = new int[this.actions.length];
		for (int index = 0; index < this.actions.length; index++){
			result[index] = index;
		}
		return result;
	}

	public void fillActions(){
		this.actions[0] = new AddItem();
		this.actions[1] = new EditItem();
		this.actions[2] = new RemoveItem();		
		this.actions[3] = new FindName();
		this.actions[4] = new FindDescription();
		this.actions[5] = new AddComment();
		this.actions[6] = new ShowItems();
	}

	public void select(int key){
		this.actions[key].execute(this.input, this.tracker);
	}

	public void show(){
		for(UserAction action : this.actions){
			if(action != null){
				System.out.println(action.info());
			}
		}
	}

	private class AddItem implements UserAction{
		public int key(){
			return 0;
		}

		public void execute(Input input, Tracker tracker){
			String name = input.ask("Enter the name of the Item:");
			String desc = input.ask("Enter the desc of the Item:");
			tracker.add(new Task(name, desc));
		}

		public String info(){
			return String.format("%s. %s", this.key(), "Add the new item.");
		}
	}

	private class EditItem implements UserAction{

		public int key(){
			return 1;
		}

		public void execute(Input input, Tracker tracker){
			String id = input.ask("Enter the Item's Id:");
			
			if(tracker.findById(id) != null){			

				String name = input.ask("Enter the new name of the Item:");
				String desc = input.ask("Enter the new desc of the Item:");
				Task task = new Task(name, desc);
				task.setId(id);
				tracker.edit(task);
			}else{
				System.out.println("Item with this Id does not exist.");
			}
		}

		public String info(){
			return String.format("%s. %s", this.key(), "Edit the item.");
		}
	}

	private class RemoveItem implements UserAction{

		public int key(){
			return 2;
		}

		public void execute(Input input, Tracker tracker){
			String id = input.ask("Enter the Item's Id:");
			if (tracker.findById(id) != null){
				Item delItem = tracker.findById(id);
				tracker.remove(delItem);
			}else{
				System.out.println("Item with this Id does not exist");
			}
		}

		public String info(){
			return String.format("%s. %s", this.key(), "Remove the item.");
		}
	}

	private class FindName implements UserAction{
		public int key(){
			return 3;
		}

		public void execute(Input input, Tracker tracker){
			String name = input.ask("Enter the name of the Item:");
        		for (Item item : tracker.findByName(name)) {
	        		System.out.println(item.getName());
        		}
		}

		public String info(){
			return String.format("%s. %s", this.key(), "Find by name items.");
		}
	}

	private class FindDescription implements UserAction{
		public int key(){
			return 4;
		}

		public void execute(Input input, Tracker tracker){
			String desc = input.ask("Enter the description of the Item:");
        		for (Item item : tracker.findByDesc(desc)) {
        			System.out.println(item.getName() + " - " + item.getDescription());
        		}
		}

		public String info(){
			return String.format("%s. %s", this.key(), "Find by description items.");
		}
	}

	private class AddComment implements UserAction{
		public int key(){
			return 5;
		}

		public void execute(Input input, Tracker tracker){
			String id =  input.ask("Enter the id of the Item:");
			if (tracker.findById(id) != null){
				String text = input.ask("Enter Comment:");
				Item item = tracker.findById(id);
				Comment comment = new Comment(text); 
				tracker.addComment(item, comment);
			}else{
				System.out.println("Item with this Id does not exist");
			}
		}

		public String info(){
			return String.format("%s. %s", this.key(), "Add comment.");
		}
	}	

	private class ShowItems implements UserAction{
		public int key(){
			return 6;
		}

		public void execute(Input input, Tracker tracker){
			for(Item item : tracker.getAll()){
				System.out.println(String.format("%s. %s. %s. %s", item.getId(), item.getName(), item.getDescription(), item.getComment()));
			}
		}

		public String info(){
			return String.format("%s. %s", this.key(), "Show all items.");
		}
	}
}