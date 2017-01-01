package ru.apetrov.start;

/**
 * Ручной ввод.
 */
public class ValidateInput extends ConsoleInput{

	/**
	 * приглашаем к вводу.
	 * @param question вопрос.
	 * @param range выбор.
	 * @return результат.
	 */
	public int ask(String question, int[] range){
		boolean invalid = true;
		int value = -1;
		do {
			try {
				value = super.ask(question, range);
				invalid = false;
			} catch(MenuOutException moe){
				System.out.println("Please select key from menu.");
			} catch(NumberFormatException nfe){
				System.out.println("Please enter validate data again.");
			}
		} while(invalid);
		return value;
	}

}