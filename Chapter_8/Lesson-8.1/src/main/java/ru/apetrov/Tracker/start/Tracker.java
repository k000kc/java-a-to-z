package ru.apetrov.Tracker.start;

import ru.apetrov.Tracker.models.Comment;
import ru.apetrov.Tracker.models.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Трекер.
 */
public class Tracker {

	private Connection connection;

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
			Statement statement = this.connection.createStatement();
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS items(id serial PRIMARY KEY, name CHARACTER VARYING(20) NOT NULL, description CHARACTER VARYING(20) NOT NULL, create_date TIMESTAMP, comment TEXT);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * добавить заявку.
	 * @param item заявка.
	 * @return заявка.
	 */
	public void add(Item item){
		try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO items(name, description, create_date) VALUES(?, ?, CURRENT_TIMESTAMP(0))")) {
			statement.setString(1, item.getName());
			statement.setString(2, item.getDescription());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * добавить коментарий.
	 * @param item заявка.
	 * @param comment коментарий.
	 */
	public void addComment(Item item, Comment comment){
		try(PreparedStatement statement = this.connection.prepareStatement("UPDATE items SET comment = ? WHERE id = ?")) {
			statement.setString(1, comment.getValue());
			statement.setInt(2, Integer.parseInt(item.getId()));
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * исправить заявку.
	 * @param item заявка.
	 */
	public void edit(Item item){
		try	(PreparedStatement statement = this.connection.prepareStatement("UPDATE items SET name = ?, description = ?, comment = ?, create_date = CURRENT_TIMESTAMP(0) WHERE id = ?")){
			statement.setString(1, item.getName());
			statement.setString(2, item.getDescription());
			if (item.getComment() != null) {
				statement.setString(3, item.getComment().getValue());
			} else {
				statement.setString(3, "not text");
			}
			statement.setInt(4, Integer.parseInt(item.getId()));
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * удалить заявку.
	 * @param item заявка.
	 */
	public  void remove(Item item){
		try (PreparedStatement statement = this.connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
			statement.setInt(1, Integer.parseInt(item.getId()));
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * поиск заявки по id.
	 * @param id id
	 * @return заявка.
	 */
	public Item findById(String id){
		Item result = null;
		try (PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
			statement.setInt(1, Integer.parseInt(id));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result = new Item(resultSet.getString("name"), resultSet.getString("description"), resultSet.getTimestamp("create_date"));
				result.setId(String.valueOf(resultSet.getInt("id")));
				result.setComment(new Comment(resultSet.getString("comment")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * поиск по имени.
	 * @param name имя.
	 * @return заявки.
	 */
	public List<Item> findByName(String name){
		List<Item> result = new ArrayList<>();
		try	(PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM items WHERE name = ?")) {
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Item item = new Item(resultSet.getString("name"), resultSet.getString("description"), resultSet.getTimestamp("create_date"));
				item.setId(String.valueOf(resultSet.getInt("id")));
				item.setComment(new Comment(resultSet.getString("comment")));
				result.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Поиск по описанию.
	 * @param desc описание.
	 * @return заявка.
	 */
	public List<Item> findByDesc(String desc){
		List<Item> result = new ArrayList<>();
		try	(PreparedStatement statement = this.connection.prepareStatement("SELECT * FROM items WHERE description LIKE ?")) {
			statement.setString(1, "%" + desc + "%");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Item item = new Item(resultSet.getString("name"), resultSet.getString("description"), resultSet.getTimestamp("create_date"));
				item.setId(String.valueOf(resultSet.getInt("id")));
				item.setComment(new Comment(resultSet.getString("comment")));
				result.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * показать все заявки.
	 * @return заявки.
	 */
	public List<Item> getAll(){
		List<Item> result = new ArrayList<>();
		try (Statement statement = this.connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
			while (resultSet.next()) {
				Item item = new Item(resultSet.getString("name"), resultSet.getString("description"), resultSet.getTimestamp("create_date"));
				item.setId(String.valueOf(resultSet.getInt("id")));
				item.setComment(new Comment(resultSet.getString("comment")));
				result.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
