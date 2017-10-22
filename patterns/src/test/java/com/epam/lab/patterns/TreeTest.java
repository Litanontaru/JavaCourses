package com.epam.lab.patterns;

import com.epam.lab.patterns.iterabletrees.DepthIteratorTree;
import com.epam.lab.patterns.utils.TreeGenerator;
import com.epam.lab.patterns.utils.TreeSearch;
import com.epam.lab.patterns.vertices.Branch;
import com.epam.lab.patterns.vertices.HasValueVertex;
import com.epam.lab.patterns.vertices.Leaf;
import com.epam.lab.patterns.vertices.Vertex;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kate on 22.10.2017.
 */
public class TreeTest {
    private static final String PRE_POST_FIX_FOR_TESTS_OUTPUT = "------";

    /***
     * Instantiates tree
     *         1
     *      2  -  3
     * (4,5,6) - (7,8)
     * @return new Tree object
     */
    private Tree instantiateTestTree() {
        List<Vertex> descLevel3Parent2 = new ArrayList<Vertex>() {{
            add(new Leaf(4));
            add(new Leaf(5));
            add(new Leaf(6));
        }};
        List<Vertex> descLevel3Parent3 = new ArrayList<Vertex>() {{
            add(new Leaf(7));
            add(new Leaf(8));
        }};
        Branch branchLevel2Value2 = new Branch(2, descLevel3Parent2);
        Branch branchLevel2Value3 = new Branch(3, descLevel3Parent3);

        Branch root = new Branch(1, Arrays.asList(new Vertex[]{branchLevel2Value2, branchLevel2Value3}));

        return new Tree(root);
    }

    @Test
    public void testPrintTree() {
        System.out.println(PRE_POST_FIX_FOR_TESTS_OUTPUT + "Print random tree (breadth iterator)" + PRE_POST_FIX_FOR_TESTS_OUTPUT);
        Tree tree = TreeGenerator.generateRandomTree();
        for (Vertex vertex : tree) {
            vertex.print();
        }
    }

    @Test
    public void testPrintInDepthTree() {
        System.out.println(PRE_POST_FIX_FOR_TESTS_OUTPUT + "Print random tree (depth iterator)" + PRE_POST_FIX_FOR_TESTS_OUTPUT);
        Tree tree = TreeGenerator.generateRandomTree();
        DepthIteratorTree depthIteratorTree = new DepthIteratorTree(tree);
        for (Vertex vertex : depthIteratorTree) {
            vertex.print();
        }
    }

    @Test
    public void testDepthSearch() {
        System.out.println(PRE_POST_FIX_FOR_TESTS_OUTPUT + "Depth search" + PRE_POST_FIX_FOR_TESTS_OUTPUT);
        Tree tree = instantiateTestTree();
        int expectedValue = 5;
        System.out.println("Search vertex with value " + expectedValue);
        HasValueVertex actualVertex = (HasValueVertex) TreeSearch.depthFirstSearch(tree, expectedValue);
        assertEquals(expectedValue, actualVertex.getValue());
        System.out.println("Founded vertex: ");
        actualVertex.print();
    }

    @Test
    public void testBreadthSearch() {
        System.out.println(PRE_POST_FIX_FOR_TESTS_OUTPUT + "Breadth search" + PRE_POST_FIX_FOR_TESTS_OUTPUT);
        Tree tree = instantiateTestTree();
        int expectedValue = 3;
        System.out.println("Search vertex with value " + expectedValue);
        HasValueVertex actualVertex = (HasValueVertex) TreeSearch.breadthFirstSearch(tree, expectedValue);
        assertEquals(expectedValue, actualVertex.getValue());
        System.out.println("Founded vertex: ");
        actualVertex.print();
    }
}
