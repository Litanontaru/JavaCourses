package com.epam.lab.patterns.visitors;

import com.epam.lab.patterns.vertices.Branch;
import com.epam.lab.patterns.vertices.Leaf;

/**
 * Created by Kate on 22.10.2017.
 */
public interface VertexVisitor {

    void visit(Branch branch);

    void visit(Leaf leaf);
}
