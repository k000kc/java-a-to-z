package ru.apetrov.models;

public class Comment{
	
	private String value;
    	private String id;

	public Comment(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}
	
	public void setValue(String value){
		this.value = value;
	}

    	public String getId() {
        	return id;
    	}

    	public void setId(String id) {
        	this.id = id;
    	}
}