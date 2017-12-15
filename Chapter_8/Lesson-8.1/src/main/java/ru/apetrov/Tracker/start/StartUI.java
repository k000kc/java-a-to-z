package ru.apetrov.Tracker.start;

import java.sql.SQLException;

/**
 * Запускаем треккер.
 */
public class StartUI{

	/**
	 * Класс ввода.
	 */
	private Input input;

	/**
	 * конструктор.
	 * @param input ввод.
	 */
	public StartUI(Input input){
		this.input = input;
	}

	/**
	 * инициализируем меню.
	 * @param tracker треккер.
	 */
	public void init(Tracker tracker){
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		do{
			menu.show();
			menu.select(input.ask("Select:", menu.getRanges()));
		}while(!"y".equals(this.input.ask("Exit?(y/n): ")));
	}

	/**
	 * main.
	 * @param args args.
	 */
	public static void main(String[] args){
		try (Tracker tracker = new Tracker()) {
			Input input = new ValidateInput();
			new StartUI(input).init(tracker);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}