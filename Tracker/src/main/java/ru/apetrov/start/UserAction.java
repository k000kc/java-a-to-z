package ru.apetrov.start;

/**
 * Интерфейс для действий пользователя.
 */
public interface UserAction {

	/**
	 * ключ действия.
	 * @return ключ.
	 */
	int key();

	/**
	 * Выполнение действия.
	 * @param input Выборка действия.
	 * @param tracker класс реализующий все действия.
	 */
	void execute(Input input, Tracker tracker);

	/**
	 * вывод инвормации действия.
	 * @return информация.
	 */
	String info();
}