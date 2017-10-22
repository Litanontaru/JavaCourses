package com.epam.lab.patterns.visitors;

import com.epam.lab.patterns.vertices.Branch;
import com.epam.lab.patterns.vertices.Leaf;
import com.epam.lab.patterns.vertices.Vertex;

/**
 * Created by Kate on 22.10.2017.
 */
public class AddVisitor implements VertexVisitor {
    private static final int VALUE_FOR_ADDING_TO_BRANCH = 1;
    private static final int VALUE_FOR_ADDING_TO_LEAF = 2;

    @Override
    public void visit(Branch branch) {
        branch.setValue(branch.getValue() + VALUE_FOR_ADDING_TO_BRANCH);
    }

    @Override
    public void visit(Leaf leaf) {
        leaf.setValue(leaf.getValue() + VALUE_FOR_ADDING_TO_LEAF);
    }
}
