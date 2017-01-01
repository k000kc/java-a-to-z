package ru.apetrov.models;

/**
 * Подкласс заявки.
 */
public class Task extends Item {

	/**
	 * Конструктор.
	 * @param name имя.
	 * @param desc описание.
	 */
	public Task(String name, String desc){
		this.name = name;
		this.description = desc;
	}

	/**
	 * прайс.
	 * @return 100%.
	 */
	public String calculatePrice(){
		return "100%";
	}
}