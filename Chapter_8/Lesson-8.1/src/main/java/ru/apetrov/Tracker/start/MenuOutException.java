package ru.apetrov.Tracker.start;

/**
 * Наше исключение.
 */
public class MenuOutException extends RuntimeException{

	/**
	 * показать исключение.
	 * @param msg сообщение.
	 */
	public MenuOutException(String msg){
		super(msg);
	}
}