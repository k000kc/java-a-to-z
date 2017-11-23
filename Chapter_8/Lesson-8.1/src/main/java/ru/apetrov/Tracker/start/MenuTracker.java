package ru.apetrov.Tracker.start;

import ru.apetrov.Tracker.models.Comment;
import ru.apetrov.Tracker.models.Item;
import ru.apetrov.Tracker.models.Task;

/**
 * Меню.
 */
public class MenuTracker {

	/**
	 * ввода.
	 */
	private Input input;

	/**
	 * реализация действий.
	 */
	private Tracker tracker;

	/**
	 * массив действий.
	 */
	private UserAction[] actions = new UserAction[7];

	/**
	 * конструктор.
	 * @param input ввод.
	 * @param tracker действия.
	 */
	public MenuTracker(Input input, Tracker tracker){
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 *
	 * @return
	 */
	public int[] getRanges(){
		int[] result = new int[this.actions.length];
		for (int index = 0; index < this.actions.length; index++){
			result[index] = index;
		}
		return result;
	}

	/**
	 * инициалицация.
	 */
	public void fillActions(){
		this.actions[0] = new AddItem();
		this.actions[1] = new EditItem();
		this.actions[2] = new RemoveItem();		
		this.actions[3] = new FindName();
		this.actions[4] = new FindDescription();
		this.actions[5] = new AddComment();
		this.actions[6] = new ShowItems();
	}

	/**
	 * выбор действия.
	 * @param key ключ.
	 */
	public void select(int key){
		this.actions[key].execute(this.input, this.tracker);
	}

	/**
	 * показать список действий.
	 */
	public void show(){
		for(UserAction action : this.actions){
			if(action != null){
				System.out.println(action.info());
			}
		}
	}

	/**
	 * добавить заявку.
	 */
	private class AddItem extends BaseAction{

		public AddItem(){
			super("Add Item");
		}

		public int key(){
			return 0;
		}

		public void execute(Input input, Tracker tracker){
			String name = input.ask("Enter the name of the Item:");
			String desc = input.ask("Enter the desc of the Item:");
			tracker.add(new Task(name, desc));
		}

	}

	/**
	 * изменить заявку.
	 */
	private class EditItem extends BaseAction{

		public EditItem(){
			super("Edit Item");
		}

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
	}

	/**
	 * удалить заявку.
	 */
	private class RemoveItem extends BaseAction{

		public RemoveItem(){
			super("Remove Item");
		}

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
	}

	/**
	 * найти заявку по имени.
	 */
	private class FindName extends BaseAction{

		public FindName(){
			super("Find by name");
		}

		public int key(){
			return 3;
		}

		public void execute(Input input, Tracker tracker){
			String name = input.ask("Enter the name of the Item:");
        		for (Item item : tracker.findByName(name)) {
	        		System.out.println(item.getName());
        		}
		}
	}

	/**
	 * найти заявку по описанию.
	 */
	private class FindDescription extends BaseAction{

		public FindDescription(){
			super("Find by Description");
		}

		public int key(){
			return 4;
		}

		public void execute(Input input, Tracker tracker){
			String desc = input.ask("Enter the description of the Item:");
        		for (Item item : tracker.findByDesc(desc)) {
        			System.out.println(item.getName() + " - " + item.getDescription());
        		}
		}
	}

	/**
	 * добавить косентарий.
	 */
	private class AddComment extends BaseAction{

		public AddComment(){
			super("Add Comment");
		}

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
	}

	/**
	 * показать список заявок.
	 */
	private class ShowItems extends BaseAction{

		public ShowItems(){
			super("Show all Items");
		}

		public int key(){
			return 6;
		}

		public void execute(Input input, Tracker tracker){
			for(Item item : tracker.getAll()){
				System.out.println(String.format("%s. %s. %s. %s", item.getId(), item.getName(), item.getDescription(), item.getComment()));
			}
		}
	}
}