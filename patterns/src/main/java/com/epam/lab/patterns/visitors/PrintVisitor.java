package com.epam.lab.patterns.visitors;

import com.epam.lab.patterns.vertices.Branch;
import com.epam.lab.patterns.vertices.Leaf;

/**
 * Created by Kate on 22.10.2017.
 */
public class PrintVisitor implements VertexVisitor {
    @Override
    public void visit(Branch branch) {
        System.out.println("I found branch");
        branch.print();
    }

    @Override
    public void visit(Leaf leaf) {
        System.out.println("I found leaf");
        leaf.print();
    }
}
