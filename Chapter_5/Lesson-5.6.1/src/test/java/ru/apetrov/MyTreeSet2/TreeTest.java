package ru.apetrov.MyTreeSet2;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Andrey on 06.05.2017.
 */
public class TreeTest {

    @Test
    public void whenAddElement() {
        Tree<Integer> tree = new Tree<>();
        tree.add(3, 2);
        tree.add(2, 4);
        boolean res = tree.add(2, 3);
        System.out.println(tree);
        assertThat(res, is(true));
    }
}