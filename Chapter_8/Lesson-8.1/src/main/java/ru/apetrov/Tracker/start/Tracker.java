package ru.apetrov.Tracker.start;

import ru.apetrov.Tracker.models.Comment;
import ru.apetrov.Tracker.models.Item;

import java.util.Random;

/**
 * Трекер.
 */
public class Tracker{

	/**
	 * Заявки.
	 */
	private Item[] items = new Item[10];

	/**
	 * позиция.
	 */
	private int position = 0;

	/**
	 * случайный id для заявки.
	 */
	private static final Random RN = new Random();

	/**
	 * добавить заявку.
	 * @param item заявка.
	 * @return заявка.
	 */
	public Item add(Item item){
		item.setId(this.generateId());
		this.items[position++] = item;
		return item;
	}

	/**
	 * добавить коментарий.
	 * @param item заявка.
	 * @param comment коментарий.
	 */
	public void addComment(Item item, Comment comment){
		if(item != null){
			item.setComment(comment);
		}
	}

	/**
	 * исправить заявку.
	 * @param item заявка.
	 */
	public void edit(Item item){
		for (Item index : items){
			if (item != null && index.getId().equals(item.getId())){
				index.setName(item.getName());
				index.setDescription(item.getDescription());
				index.setComment(item.getComment());
				break;
			}
		}
	}

	/**
	 * удалить заявку.
	 * @param item заявка.
	 */
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

	/**
	 * поиск заявки по id.
	 * @param id id
	 * @return заявка.
	 */
	public Item findById(String id){
		Item result = null;
		for (Item item : items){
			if(item != null && item.getId().equals(id)){
				result = item;
				break;
			}
		}
		return result;
	}

	/**
	 * поиск по имени.
	 * @param name имя.
	 * @return заявки.
	 */
	public Item[] findByName(String name){
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
				result[namePosition] = items[index];
				namePosition++;
			}
		}
		return result;
	}

	/**
	 * Поиск по описанию.
	 * @param desc описание.
	 * @return заявка.
	 */
	public Item[] findByDesc(String desc){
		int numbDuplicateDesc = 0;
		for(Item item : this.items){
			if (item != null && item.getDescription().equals(desc)){
				numbDuplicateDesc++;
			}
		}

		Item[] result = new Item[numbDuplicateDesc];
		int descPosition = 0;
		for (int index = 0; index < this.items.length; index++){
			if (this.items[index] != null && this.items[index].getDescription().equals(desc)){
				result[descPosition] = this.items[index];
				descPosition++;
			}
		}
		return result;
	}

	/**
	 * генерация случайного id.
	 * @return
	 */
	String generateId(){
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}

	/**
	 * показать все заявки.
	 * @return заявки.
	 */
	public Item[] getAll(){
		Item[] result = new Item[position];
		for(int index = 0; index != this.position; index++){
			result[index] = this.items[index];
		}
		return result;
	}
}
