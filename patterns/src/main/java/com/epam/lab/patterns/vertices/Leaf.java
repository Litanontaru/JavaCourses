package com.epam.lab.patterns.vertices;

import com.epam.lab.patterns.visitors.VertexVisitor;

/**
 * Created by Kate on 22.10.2017.
 */
public class Leaf extends HasValueVertex {

    public Leaf(int value) {
        super(value);
    }

    @Override
    public void print() {
        System.out.println("Leaf, value " + this.getValue());
    }

    @Override
    public void accept(VertexVisitor visitor) {
        visitor.visit(this);
    }
}
