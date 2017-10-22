package com.epam.lab.patterns.iterabletrees;

import com.epam.lab.patterns.Tree;
import com.epam.lab.patterns.vertices.Vertex;

import java.util.Iterator;

/**
 * Created by Kate on 22.10.2017.
 */
public class DepthIteratorTree implements Iterable<Vertex> {
    private Tree tree;

    public DepthIteratorTree(Tree tree) {
        this.tree = tree;
    }

    @Override
    public Iterator<Vertex> iterator() {
        return tree.depthIterator();
    }
}
