package ru.apetrov.NonblockingCache;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 17.08.2017.
 */
public class NonblockingCacheTest {

    /**
     * cache.
     */
    private NonblockingCache cache;

    /**
     * madel.
     */
    private Model model1;

    /**
     * initialization.
     */
    @Before
    public void init() {
        this.cache = new NonblockingCache();
        this.model1 = new Model(1, "first");
        this.cache.add(model1);
    }

    /**
     * check addd.
     */
    @Test
    public void whenAddNewModeleThenCheckSizeCache() {
        int result = cache.size();
        assertThat(result, is(1));
    }

    /**
     * check delete.
     */
    @Test
    public void whenDeleteModelThenCheckSizeCache() {
        this.cache.delete(model1);
        int result = this.cache.size();
        assertThat(result, is(0));
    }

    /**
     * check update.
     */
    @Test
    public void whenUpdateModelThenCheckModel() {
        this.cache.update(new Model(1, "second"));
        String result = this.cache.get(1).getName();
        assertThat(result, is("second"));
    }
}