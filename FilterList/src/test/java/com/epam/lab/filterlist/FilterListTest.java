package com.epam.lab.filterlist;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Kate on 02.10.2017.
 */
public class FilterListTest {

    private void checkFilterList(FilterList actualResult, int[] expectedResult) {
        int i = 0;
        for (int element : actualResult) {
            assertEquals(expectedResult[i++], element);
        }
    }

    @Test
    public void testFilterList() {
        FilterList list = new FilterList(new int[]{1, 2, 5, 7, 9}, new int[]{5, 9, 4});
        int[] expectedList = new int[]{1, 2, 7};
        checkFilterList(list, expectedList);
    }

    @Test
    public void testListWithForbiddenElements() {
        FilterList list = new FilterList(new int[]{2, 3, 4}, new int[]{2, 3, 4});
        int expectedCount = 0;
        assertEquals(expectedCount, list.getSizeWithoutPredicateElems());
    }

    @Test
    public void testFilterListRemove() {
        FilterList list = new FilterList(new int[]{1, 5, 9, 3, 12}, new int[]{5, 9, 4});
        Iterator iter = list.iterator();
        if (iter.hasNext()) {
            iter.remove();
        }
        int[] expectedList = new int[]{3, 12};
        checkFilterList(list, expectedList);
    }

    @Test
    public void testFilterListAddCorrectElement() {
        FilterList list = new FilterList(new int[]{1, 5, 5, 6, 4, 9}, new int[]{5, 9, 4});
        list.add(10);
        int[] expectedList = new int[]{1, 6, 10};
        checkFilterList(list, expectedList);
    }

    @Test
    public void testFilterListAddForbiddenElement() {
        FilterList list = new FilterList(new int[]{1, 5, 5, 6, 4, 9}, new int[]{5, 9, 4});
        list.add(5);
        int[] expectedList = new int[]{1, 6};
        checkFilterList(list, expectedList);
    }


    @Test
    public void testFilterListFullRemove() {
        FilterList list = new FilterList(new int[]{1, 5, 9, 2, 3, 4}, new int[]{5, 9, 4});
        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            iter.remove();
        }
        int expectedCount = 0;
        assertEquals(expectedCount, list.getSizeWithoutPredicateElems());
    }

    @Test
    public void testHasNextInEmptyFilterList() {
        FilterList list = new FilterList(new int[]{}, new int[]{5, 9, 4});
        assertFalse(list.iterator().hasNext());
    }

    @Test
    public void testFilterListAdd() {
        FilterList list = new FilterList(new int[]{}, new int[]{5, 9, 4});
        list.add(5);
        list.add(10);
        list.add(9);
        list.add(2);
        int[] expectedList = new int[]{10, 2};
        checkFilterList(list, expectedList);
    }

    @Test
    public void testFilterListWithEmptyPredicate() {
        FilterList list = new FilterList(new int[]{}, new int[]{});
        int[] expectedArray = new int[]{1, 2, 3, 4, 5};
        for (int elem : expectedArray) {
            list.add(elem);
        }
        checkFilterList(list, expectedArray);
    }
}
