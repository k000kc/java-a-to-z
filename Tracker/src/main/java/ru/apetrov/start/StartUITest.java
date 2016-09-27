package ru.apetrov.start;

public class StartUITest{

	public static void main(String[] args){
		Input input = new StubInput(new String[] {"create stub task"});
		new StartUI(input).init();
	}
}