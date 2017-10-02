package com.epam.lab.filterlist;

import java.util.*;

/**
 * Created by Kate on 02.10.2017.
 */
public class FilterList implements Iterable<Integer> {
    private int[] list;
    private final int[] predicate;
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


    /**
     * Method returns size of list which doesn't include elements from predicate
     * @return size without predicate elements
     */
    public int getSizeWithoutPredicateElems() {
        int count = 0;
        for (int element : list) {
            if (!isElementInPredicate(element))
                count++;
        }
        return count;
    }

    public boolean add(int elem) {
        if (!isElementInPredicate(elem)) {
            if (size == list.length) {
                this.list = Arrays.copyOf(list, list.length + list.length / 2 + 1);
            }
            this.list[size] = elem;
            size++;
            return true;
        }
        return false;
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
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
