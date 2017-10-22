package com.epam.lab.patterns.utils;

import com.epam.lab.patterns.Tree;
import com.epam.lab.patterns.iterabletrees.BreadthIteratorTree;
import com.epam.lab.patterns.iterabletrees.DepthIteratorTree;
import com.epam.lab.patterns.vertices.HasValueVertex;
import com.epam.lab.patterns.vertices.Vertex;

/**
 * Created by Kate on 22.10.2017.
 */
public class TreeSearch {

    public static Vertex depthFirstSearch(Tree tree, int value) {
        return search(new DepthIteratorTree(tree), value);
    }


    public static Vertex breadthFirstSearch(Tree tree, int value) {
        return search(new BreadthIteratorTree(tree), value);
    }

    private static Vertex search(Iterable<Vertex> tree, int value) {
        for (Vertex vertex : tree) {
            if (vertex instanceof HasValueVertex && ((HasValueVertex) vertex).getValue() == value) {
                return vertex;
            }
        }
        return null;
    }
}
