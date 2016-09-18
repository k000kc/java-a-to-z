package ru.apetrov.models;

public class Task extends Item {

	public Task(String name, String desc){
		this.name = name;
		this.description = desc;
	}

	public String calculatePrice(){
		return "100%";
	}

}