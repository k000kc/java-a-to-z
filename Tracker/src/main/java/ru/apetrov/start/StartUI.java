package ru.apetrov.start;

import ru.apetrov.models.*;

public class StartUI{

	private Input input;

	public StartUI(Input input){
		this.input = input;
	}

	public void init(Tracker tracker){
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		do{
			menu.show();
			menu.select(input.ask("Select:", menu.getRanges()));
		}while(!"y".equals(this.input.ask("Exit?(y/n): ")));
	}

	public static void main(String[] args){
		Tracker tracker = new Tracker();
		Input input = new ValidateInput();
		new StartUI(input).init(tracker);
	}
}