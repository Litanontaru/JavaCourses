package com.epam.lab.patterns;

import com.epam.lab.patterns.vertices.Branch;
import com.epam.lab.patterns.vertices.Vertex;

import java.util.*;

/**
 * Created by Kate on 22.10.2017.
 */
public class Tree implements Iterable<Vertex> {
    private Vertex root;

    public Tree(Vertex root) {
        this.root = root;
    }

    @Override
    public Iterator<Vertex> iterator() {
        return breadthIterator();
    }

    public Iterator<Vertex> depthIterator() {
        return new TreeIterator(false);
    }

    public Iterator<Vertex> breadthIterator() {
        return new TreeIterator(true);
    }

    private class TreeIterator implements Iterator<Vertex> {
        LinkedList<Vertex> deque;// need methods from List and Deque interfaces so specified exact class
        boolean isBreadthDirection;// by default iterator goes in depth

        public TreeIterator(boolean isBreadthDirection) {
            this.deque = new LinkedList<>();
            this.deque.add(root);
            this.isBreadthDirection = isBreadthDirection;
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public Vertex next() {
            Vertex currentElement = deque.pop();

            int positionForInsert = (isBreadthDirection) ? deque.size() : 0;
            if (currentElement instanceof Branch) {
                deque.addAll(positionForInsert, ((Branch) currentElement).getDescendants());
            }

            return currentElement;
        }
    }
}
