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
		Input input = new StubInput(new String[] {"0","first item", "this is a first item", "y"});
		new StartUI(input).init(tracker);
		for (Item item : tracker.getAll()){
			if(item != null){
				assertThat(item.getName(), is("first item"));
				assertThat(item.getDescription(), is("this is a first item"));
			}
		}
	}

	@Test
	public void whenEditItemThenSelectIdItem(){
		String resultId = null;
		tracker.add(new Task("first item", "this is a first item"));
		for(Item item : tracker.findByName("first item")){
			if(item.getName().equals("first item")){
				resultId = item.getId();
			}
		}
		
		Input inputEdit = new StubInput(new String[] {"1", resultId, "edit item", "this is a edit item", "y"});
		new StartUI(inputEdit).init(tracker);
		Item editItem = tracker.findById(resultId);

		assertThat(editItem.getName(), is("edit item"));
	}

	@Test
	public void whenRemoveItemThenSelectIdItem(){
		String resultId = null;
		tracker.add(new Task("first item", "this is a first item"));
		for(Item item : tracker.findByName("first item")){
			if(item.getName().equals("first item")){
				resultId = item.getId();
			}
		}

		Input inputDelete = new StubInput(new String[] {"2",resultId, "y"});
		new StartUI(inputDelete).init(tracker);
		for(Item item : tracker.getAll()){
			assertNull(item);
		}		
	}

	@Test
	public void whenFindByNameThenInputName(){	
		tracker.add(new Task("first item", "this is a first item"));
		Input input = new StubInput(new String[] {"3", "first item", "y"});
		new StartUI(input).init(tracker);
		for (Item item : tracker.findByName("first item")) {
			assertThat(item.getName(), is("first item"));
		}
	}

	@Test
	public void whenFindByDescriptionThenInputDescription(){	
		tracker.add(new Task("first item", "this is a first item"));
		Input input = new StubInput(new String[] {"4", "this is a first item", "y"});
		new StartUI(input).init(tracker);
		for (Item item : tracker.findByDesc("this is a first item")) {
			assertThat(item.getDescription(), is("this is a first item"));
		}
	}

	@Test
	public void whenAddCommentThenInputIdAndAddComment(){
		String resultId = null;
		tracker.add(new Task("first item", "this is a first item"));
		for(Item item : tracker.findByName("first item")){
			if(item.getName().equals("first item")){
				resultId = item.getId();
			}
		}
		
		Input inputComment = new StubInput(new String[] {"5", resultId, "This is a comment", "y"});
		new StartUI(inputComment).init(tracker);
		for (Item item : tracker.getAll()){
			if(item != null){
				assertThat(item.getComment().getValue(), is("This is a comment"));
			}
		}
	}

	@Test
	public void whenGetAllItem(){
		tracker.add(new Task("first item", "this is a first item"));
		Input input = new StubInput(new String[] {"6", "y"});
		new StartUI(input).init(tracker);
		for (Item item : tracker.getAll()){
			if(item != null){
				assertThat(item.getName(), is("first item"));
			}
		}	
	}
}