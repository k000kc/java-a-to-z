package ru.apetrov.start;


public abstract class BaseAction implements UserAction{

	String name;

	public BaseAction(String name){
		this.name = name;
	}

	

	@Override
	public String info(){
		return String.format("%s. %s", this.key(), this.name);
	}
}