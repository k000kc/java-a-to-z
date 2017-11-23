package ru.apetrov.Tracker.models;

/**
 * Заявка.
 */
public class Item{

	/**
	 * id заявки.
	 */
	private String id;

	/**
	 * имя заявки.
	 */
	public String name;

	/**
	 * Описание заявки.
	 */
	public String description;

	/**
	 * Время создания заявки.
	 */
	public long create;

	/**
	 * Комментарий к заявке.
	 */
	private Comment comment;

	/**
	 * Пустой конструктор.
	 */
	public Item(){
	}

	/**
	 * Конструктор.
	 * @param name имя.
	 * @param description описание.
	 * @param create время создания.
	 */
	public Item(String name, String description, long create){
		this.name = name;
		this.description = description;
		this.create = create;
	}

	/**
	 * геттер.
	 * @return имя.
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * сеттер.
	 * @param name имя.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * геттер.
	 * @return описание.
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * сеттер.
	 * @param description описание.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * геттер.
	 * @return время создания.
	 */
	public long getCreate(){
		return this.create;
	}

	/**
	 * сеттер.
	 * @param create время создания.
	 */
	public void setCreate(long create) {
		this.create = create;
	}

	/**
	 * геттер.
	 * @return id.
	 */
	public String getId(){
		return this.id;
	}

	/**
	 * сеттер.
	 * @param id id
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * геттер.
	 * @return комментария.
	 */
	public Comment getComment(){
		return this.comment;
	}

	/**
	 * сеттер.
	 * @param comment комментарий.
	 */
	public void setComment(Comment comment){
		this.comment = comment;
	}
}