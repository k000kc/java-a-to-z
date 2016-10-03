package ru.apetrov;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;
import ru.apetrov.start.*;

public class StartUITest{

	Tracker tracker = new Tracker();


	@Test
	public void whenAddItemThenInputNameAndDesc(){
		Tracker tracker = new Tracker();
		Input input = new StubInput(new String[] {"first item", "this is a first item"});
		new StartUI(input).addItem(tracker);
		for (Item item : tracker.getAll()){
			if(item != null){
				assertThat(item.getName(), is("first item"));
				assertThat(item.getDescription(), is("this is a first item"));
			}
		}
	}

	@Test
	public void whenEditItemThenSelectIdItem(){
		Tracker tracker = new Tracker();
		String resultId = null;
		Input inputFirst = new StubInput(new String[] {"first item", "this is a first item"});
		new StartUI(inputFirst).addItem(tracker);

		for(Item item : tracker.findByName("first item")){
			if(item.getName().equals("first item")){
				resultId = item.getId();
			}
		}
		
		Input inputEdit = new StubInput(new String[] {resultId, "edit item", "this is a edit item"});
		new StartUI(inputEdit).editItem(tracker);
		Item editItem = tracker.findById(resultId);

		assertThat(editItem.getName(), is("edit item"));
	}

	@Test
	public void whenRemoveItemThenSelectIdItem(){
		Tracker tracker = new Tracker();
		String resultId = null;
		Input inputFirst = new StubInput(new String[] {"first item", "this is a first item"});
		new StartUI(inputFirst).addItem(tracker);

		for(Item item : tracker.findByName("first item")){
			if(item.getName().equals("first item")){
				resultId = item.getId();
			}
		}

		Input inputDelete = new StubInput(new String[] {resultId});
		new StartUI(inputDelete).removeItem(tracker);
		for(Item item : tracker.getAll()){
			assertNull(item);
		}		
	}

	@Test
	public void whenFindByNameThenInputName(){
		Tracker tracker = new Tracker();	
		Input inputFirst = new StubInput(new String[] {"first item", "this is a first item"});
		new StartUI(inputFirst).addItem(tracker);
		for (Item item : tracker.findByName("first item")) {
			assertThat(item.getName(), is("first item"));
		}
	}

	@Test
	public void whenFindByDescriptionThenInputDescription(){
		Tracker tracker = new Tracker();	
		Input inputFirst = new StubInput(new String[] {"first item", "this is a first item"});
		new StartUI(inputFirst).addItem(tracker);
		for (Item item : tracker.findByDesc("first item")) {
			assertThat(item.getDescription(), is("this is a first item"));
		}		
	}

	@Test
	public void whenAddCommentThenInputIdAndAddComment(){
		Tracker tracker = new Tracker();
		String resultId = null;
		Input inputFirst = new StubInput(new String[] {"first item", "this is a first item"});
		new StartUI(inputFirst).addItem(tracker);

		for(Item item : tracker.findByName("first item")){
			if(item.getName().equals("first item")){
				resultId = item.getId();
			}
		}
		
		Input inputComment = new StubInput(new String[] {resultId, "This is a comment"});
		new StartUI(inputComment).addCommentItem(tracker);
		for (Item item : tracker.getAll()){
			if(item != null){
				assertThat(item.getComment().getValue(), is("This is a comment"));
			}
		}
	}

	@Test
	public void whenGetAllItem(){
		Tracker tracker = new Tracker();	
		Input inputFirst = new StubInput(new String[] {"first item", "this is a first item"});
		new StartUI(inputFirst).addItem(tracker);
		for (Item item : tracker.getAll()){
			if(item != null){
				assertThat(item.getName(), is("first item"));
			}
		}	
	}
}