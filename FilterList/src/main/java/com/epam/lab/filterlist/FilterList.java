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
        int lastReturnedIndex;

        @Override
        public boolean hasNext() {
            int i = currentIndex;
            while (i < size && isElementInPredicate(list[i]))
                i++;
            return i < size;
        }

        @Override
        public Integer next() {
            while (hasNext() && isElementInPredicate(list[currentIndex])) {
                currentIndex++;
            }
            if (currentIndex < size) {
                lastReturnedIndex = currentIndex;
                currentIndex++;
                return list[lastReturnedIndex];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (size > 0) {
                size--;
                for (int i = lastReturnedIndex; i < size; i++) {
                    list[i] = list[i + 1];
                }
                currentIndex = lastReturnedIndex;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
