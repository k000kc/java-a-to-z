package ru.apetrov.MyTreeSet2;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 06.05.2017.
 */
public class TreeTest {

    /**
     * check method add().
     */
    @Test
    public void whenAddElement() {
        Tree<Integer> tree = new Tree<>();
        tree.add(3, 2);
        tree.add(2, 4);
        boolean res = tree.add(2, 3);
        System.out.println(tree);
        assertThat(res, is(true));
    }

    /**
     * check method next().
     */
    @Test
    public void whenNextElement() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(2, 4);
        System.out.println(tree);

        Iterator<Integer> iterator = tree.iterator();

        System.out.println(iterator.hasNext());
        iterator.next();
        iterator.next();
        iterator.next();
        int result = iterator.next();
        System.out.println(result);

        assertThat(result, is(4));
    }
}