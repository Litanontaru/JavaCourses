package com.epam.lab.filterlist;

import java.util.*;

/**
 * Created by Kate on 02.10.2017.
 */
public class FilterList<T> implements Iterable<T> {
    private ListNode head;
    private final Set<T> predicate;
    private int size;

    private class ListNode {
        T value;
        ListNode next;
        ListNode prev;

        ListNode(T value) {
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public FilterList(Collection<T> elements, Collection<T> predicate) {
        for (T element : elements) {
            addListNode(new ListNode(element));
        }

        this.predicate = new HashSet<>(predicate);
        this.size = elements.size();
    }

    public FilterList(T[] elements, T[] predicate) {
        this(Arrays.asList(elements), Arrays.asList(predicate));
    }

    private void addListNode(ListNode node) {
        if (head == null) {
            head = node;
            head.next = head;
            head.prev = head;
        }
        node.prev = head.prev;
        node.next = head;
        head.prev.next = node;
        head.prev = node;
        size++;
    }

    private boolean isElementInPredicate(T element) {
        return predicate.contains(element);
    }

    /**
     * Method returns size of list which doesn't include elements from predicate
     *
     * @return size without predicate elements
     */
    public int getSizeWithoutPredicateElems() {
        int count = 0;
        for (T element : this) {
            if (!isElementInPredicate(element))
                count++;
        }
        return count;
    }

    public boolean add(T elem) {
        if (!isElementInPredicate(elem)) {
            addListNode(new ListNode(elem));
            return true;
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new FilterIterator();
    }

    private class FilterIterator implements Iterator<T> {
        ListNode curElement = head;
        int currentIndex;

        @Override
        public boolean hasNext() {
            while (currentIndex < size && isElementInPredicate(curElement.value)) {
                currentIndex++;
                curElement = curElement.next;
            }
            return currentIndex < size;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T value = curElement.value;
                currentIndex++;
                curElement = curElement.next;
                return value;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (hasNext()) {
                if (curElement == head) head = curElement.next;
                curElement.prev.next = curElement.next;
                curElement.next.prev = curElement.prev;
                curElement = curElement.next;
                size--;
            } else {
                throw new IllegalStateException();
            }
        }
    }
}
