package ru.apetrov.MyTreeSet;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 30.04.2017.
 */
public class MyTreeSetTest {

    /**
     * test module.
     */
    @Test
    public void test() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        tree.addChild(8);
        tree.addChild(5);
        tree.addChild(10);
        tree.addChild(7);

        System.out.println(tree);
        System.out.println(tree.getSize());
        System.out.println();

        System.out.println(tree.getChildren());
    }

    /**
     * check method addChild().
     */
    @Test
    public void whenAddNewElementThenGetTrue() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        assertThat(tree.addChild(2), is(true));
    }

    /**
     * check method addChild().
     */
    @Test
    public void whenAddDuplicateElementThenGetFalse() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        tree.addChild(2);
        assertThat(tree.addChild(2), is(false));
    }

    /**
     * check method getChildren().
     */
    @Test
    public void whenAddingElementsThenGetSortedList() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        tree.addChild(8);
        tree.addChild(5);
        tree.addChild(10);
        tree.addChild(7);

        List<Integer> result;
        result = tree.getChildren();

        assertThat(result.toArray(), is(new int[]{5, 7, 8, 10}));
    }

    /**
     * check method search().
     */
    @Test
    public void whenInputElementToSearchThenGetThisElement() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        tree.addChild(8);
        tree.addChild(5);
        tree.addChild(10);
        tree.addChild(7);

        Integer result = tree.search(7);

        assertThat(result, is(7));
    }

    /**
     * check method search().
     */
    @Test
    public void whenNotFoundetElementThenGetNull() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        tree.addChild(8);
        tree.addChild(5);
        tree.addChild(10);
        tree.addChild(7);

        Integer result = tree.search(2);

        assertNull(result);
    }
}