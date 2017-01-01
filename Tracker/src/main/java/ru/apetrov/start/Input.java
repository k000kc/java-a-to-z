package ru.apetrov.start;

/**
 * интерфейс ввода.
 */
public interface Input{

	/**
	 * просим ввести данные.
	 * @param question вопрос.
	 * @return строку выбранного действия.
	 */
	String ask(String question);

	/**
	 * просим ввести данные.
	 * @param question вопрос.
	 * @param range действие.
	 * @return номер действия.
	 */
	int ask(String question, int[] range);
}