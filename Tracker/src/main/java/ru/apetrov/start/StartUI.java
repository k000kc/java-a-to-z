package ru.apetrov.start;

import ru.apetrov.models.*;

public class StartUI{

	private Input input;

	public StartUI(Input input){
		this.input = input;
	}

	public void init(){
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		do{
			menu.show();
			int key = Integer.valueOf(input.ask("Select:"));
			menu.select(key);
		}while(!"y".equals(this.input.ask("Exit?(y/n): ")));
	}

	public static void main(String[] args){
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
}