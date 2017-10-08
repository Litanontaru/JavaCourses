package com.epam.lab.filterlist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kate on 08.10.2017.
 */
public class ReduceMethodTest {

    @Test
    public void reduceStringTest() {
        FilterList<String> list = new FilterList<>(new String[]{"one", "two", "three", "four"}, new String[]{"one"});
        String result = list.reduce(new BiFunctionInterface<String>() {
            @Override
            public String biFunction(String x, String y) {
                return x + y;
            }
        });
        assertEquals("twothreefour", result);
    }

    @Test
    public void reduceIntTest() {
        FilterList<Integer> list = new FilterList<>(new Integer[]{1, 2, 3, 4}, new Integer[]{0});
        int result = list.reduce(new BiFunctionInterface<Integer>() {
            @Override
            public Integer biFunction(Integer x, Integer y) {
                return x + y;
            }
        });
        assertEquals(10, result);
    }
}
