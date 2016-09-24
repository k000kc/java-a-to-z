package ru.apetrov.start;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import ru.apetrov.models.*;

public class TrackerTest{
	
	@Test
	public void whenNeedItemThenAddItem(){
		Tracker tracker = new Tracker();
		Item item = new Item("first item", "first desc", 0);
		assertThat(tracker.add(item), is(item));	
	}

	@Test
	public void whenNeedCommentThenAddComment(){
		Tracker tracker = new Tracker();
		Item item = new Item("first item", "first desc", 0);
		Comment comment = new Comment("This is a comment");
		tracker.addComment(item, comment);
		String result = "This is a comment";
		assertThat(result, is(item.getComment().getValue()));
	}

	@Test
	public void whenEditItem(){
		Tracker tracker = new Tracker();
		Item item = new Item("first item", "first desc", 0);
		Item editItem = new Item("editItem task", "editItem desk", 0);
		tracker.add(item);
		editItem.setId(item.getId());
		tracker.edit(editItem);
		assertThat(editItem.getName(), is(item.getName()));
		assertThat(editItem.getDescription(), is(item.getDescription()));
	}

	@Test
	public void whenSelectItemthenDeleteItem(){
		Tracker tracker = new Tracker();
		Item item1 = new Item("first item", "first desc", 0);
		Item item2 = new Item("second item", "second desc", 1);
		tracker.add(item1);
		tracker.add(item2);
		tracker.remove(item2);
		Item[] result = {item1};
		assertThat(tracker.getAll(), is(result));
	
	}

	@Test
	public void whenInputIdThenFindItemById(){
		Tracker tracker = new Tracker();
		Item item1 = new Item("first item", "first desc", 0);
		Item item2 = new Item("second item", "second desc", 1);
		tracker.add(item1);
		tracker.add(item2);
		Item result = tracker.findById(item1.getId());
		assertThat(result, is(item1));
	}

	@Test
	public void whenInputNameThenFindItemByName(){
		Tracker tracker = new Tracker();
		Item item1 = new Item("first item", "first desc", 0);
		Item item2 = new Item("second item", "second desc", 1);
		tracker.add(item1);
		tracker.add(item2);
		Item[] items = {item1}; 
		Item[] result = tracker.findByName(item1.getName());
		assertThat(result, is(items));		
	}

	@Test
	public void whenInputDescriptionThenFindItemByDescription(){
		Tracker tracker = new Tracker();
		Item item1 = new Item("first item", "first desc", 0);
		Item item2 = new Item("second item", "second desc", 1);
		tracker.add(item1);
		tracker.add(item2);
		Item[] items = {item1}; 
		Item[] result = tracker.findByDesc(item1.getDescription());
		assertThat(result, is(items));		
	}

	@Test
	public void whenGetAllItems(){
		Tracker tracker = new Tracker();
		Item item1 = new Item("first item", "first desc", 0);
		Item item2 = new Item("second item", "second desc", 1);
		tracker.add(item1);
		tracker.add(item2);
		Item[] items = {item1, item2}; 
		Item[] result = tracker.getAll();
		assertThat(result, is(items));	
	}
}