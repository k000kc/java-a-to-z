package ru.apetrov.Tracker.start;

/**
 * Заглушка для теста.
 */
public class StubInput implements Input {

	/**
	 * массив ответов.
	 */
	private String[] answers;

	/**
	 * позиция.
	 */
	private int position = 0;

	/**
	 * конструктор.
	 * @param answers ответы.
	 */
	public StubInput(String[] answers){
		this.answers = answers;
	}

	/**
	 * приглашение к вводу.
	 * @param question вопрос.
	 * @return строка с выбранным действием.
	 */
	public String ask(String question){
		return answers[position++];
	}

	/**
	 * приглашение к вводу.
	 * @param question вопрос.
	 * @param range действие.
	 * @return выбранное действие.
	 */
	public int ask(String question, int[] range){
		int key = Integer.valueOf(this.ask(question));
		boolean exist = false;
		for(int value : range){
			if (value == key){
				exist = true;
				break;
			}
		}
		if(exist){
			return key;
		}else{
			throw new MenuOutException("Out of menu range.");
		}
	}
}