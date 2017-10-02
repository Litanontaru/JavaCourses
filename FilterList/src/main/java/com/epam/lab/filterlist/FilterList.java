package com.epam.lab.filterlist;

import java.util.*;

/**
 * Created by Kate on 02.10.2017.
 */
public class FilterList implements Iterable<Integer> {
    private int[] list;
    private int[] predicate;
    private int size;

    public FilterList(int[] elements, int[] predicate) {
        this.list = elements;
        this.predicate = predicate;
        this.size = this.list.length;
    }

    private boolean isElementInPredicate(int elem) {
        for (int elemPredicate : predicate) {
            if (elemPredicate == elem)
                return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    public Iterator<Integer> iterator() {
        return new FilterIterator();
    }

    private class FilterIterator implements Iterator<Integer> {
        int currentIndex;

        @Override
        public boolean hasNext() {
            while (currentIndex < size && isElementInPredicate(list[currentIndex]))
                currentIndex++;
            return currentIndex < size;
        }

        @Override
        public Integer next() {
            int i = currentIndex;
            if (hasNext()) {
                currentIndex++;
                return list[i];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (hasNext()) {
                size--;
                for (int i = currentIndex; i < size; i++) {
                    list[i] = list[i + 1];
                }
            }
            throw new IllegalStateException();
        }
    }
}
