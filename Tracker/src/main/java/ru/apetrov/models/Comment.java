package ru.apetrov.models;

/**
 * Ксласс коментария.
 */
public class Comment{

	/**
	 * текст коментария.
	 */
	private String value;

	/**
	 * id заявки.
	 */
	private String id;

	/**
	 * конструктор.
	 * @param value коментарий.
	 */
	public Comment(String value){
		this.value = value;
	}

	/**
	 * Геттер.
	 * @return коментарий.
	 */
	public String getValue(){
		return value;
	}

	/**
	 * Сеттер.
	 * @param value комментарий.
	 */
	public void setValue(String value){
		this.value = value;
	}

	/**
	 * Геттер.
	 * @return id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Сеттер.
	 * @param id id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * toString.
	 * @return комментарий.
	 */
	@Override
	public String toString() {
		return this.value;
	}
}