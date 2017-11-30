package ru.apetrov.Tracker.start;

import ru.apetrov.Tracker.models.Comment;
import ru.apetrov.Tracker.models.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Random;

/**
 * Трекер.
 */
public class Tracker {

	private Connection connection;
	private Statement statement;

	private void initConnection() {
		Properties properties = new Properties();
		ClassLoader loader = Tracker.class.getClassLoader();
		try	(InputStream in = loader.getResourceAsStream("config.properties")) {
			properties.load(in);
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
			this.connection = DriverManager.getConnection(url, username, password);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Tracker() {
		initConnection();
		try {
			this.statement = this.connection.createStatement();
			this.statement.executeUpdate("CREATE TABLE IF NOT EXISTS items(id serial PRIMARY KEY, name CHARACTER VARYING(20) NOT NULL, description CHARACTER VARYING(20) NOT NULL, create_date TIMESTAMP, comment TEXT);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

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
	public boolean add(Item item){
		boolean result = false;
		try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO items(name, description, create_date) VALUES(?, ?, CURRENT_TIMESTAMP(0))")) {
			statement.setString(1, item.getName());
			statement.setString(2, item.getDescription());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
