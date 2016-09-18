package ru.apetrov.models;

public class Item{
	public String name;
	
	public String description;

	public long create;

	public Item(){
	}

	public Item(String name, String description, long create){
		this.name = name;
		this.description = description;
		this.create = create;
	}
	
	public String getName(){
		return this.name;
	}

	public String getDescription(){
		return this.description;
	}

	public long getCreate(){
		return this.create;
	}
}