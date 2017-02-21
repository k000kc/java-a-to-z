package ru.apetrov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Andrey on 21.02.2017.
 */
public class PoolTest {

    @Test
    public void whenInputArrayThenGetMaximumSizeIsland() {
        int[][] sea = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                                  {0, 1, 0, 1, 0, 1, 1, 1, 1, 0},
                                  {0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                                  {0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
                                  {0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                                  {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        Pool island = new Pool(sea);
        int result = island.maxUnion();
        assertThat(result, is(16));
    }

}