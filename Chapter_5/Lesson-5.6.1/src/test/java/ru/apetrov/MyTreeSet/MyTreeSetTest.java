package ru.apetrov.MyTreeSet;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 30.04.2017.
 */
public class MyTreeSetTest {

    @Test
    public void test() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        tree.addChild(8);
        tree.addChild(5);
        tree.addChild(10);
        tree.addChild(7);

        System.out.println(tree);
        System.out.println(tree.getSize());
    }

    @Test
    public void whenAddNewElementThenGetTrue() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        assertThat(tree.addChild(2), is(true));
    }

    @Test
    public void whenAddDuplicateElementThenGetFalse() {
        SimpleTree<Integer> tree = new MyTreeSet<>();
        tree.addChild(2);
        assertThat(tree.addChild(2), is(false));
    }

}