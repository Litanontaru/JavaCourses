package com.epam.lab.filterlist;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kate on 08.10.2017.
 */
public class MapMethodTest {

    @Test
    public void mapTest() {
        FilterList<String> list = new FilterList<>(new String[]{"one", "two", "three", "four"}, new String[]{"one"});
        FilterList<Integer> listLengths = list.map(new FunctionInterface<String, Integer>() {
            @Override
            public Integer function(String x) {
                return x.length();
            }
        });
        int[] expectedResult = new int[]{5, 4}; // predicate also goes through the map function so we exclude 3

        int i = 0;
        for (int element : listLengths) {
            assertEquals(expectedResult[i++], element);
        }
    }


    @Test
    public void mapSameTypesTest() {
        FilterList<String> list = new FilterList<>(new String[]{"one", "two", "three", "four"}, new String[]{"one"});
        FilterList<String> actualList = list.map(new FunctionInterface<String, String>() {
            @Override
            public String function(String x) {
                return x+" beer";
            }
        });
        String[] expectedResult = new String[]{"two beer", "three beer","four beer"}; // predicate also goes through the map function so we exclude 3

        int i = 0;
        for (String element : actualList) {
            assertEquals(expectedResult[i++], element);
        }
    }
}
