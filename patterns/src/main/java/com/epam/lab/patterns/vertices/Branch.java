package com.epam.lab.patterns.vertices;

import com.epam.lab.patterns.visitors.VertexVisitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kate on 22.10.2017.
 */
public class Branch extends HasValueVertex {
    private List<Vertex> descendants;

    public Branch(int value, Collection<Vertex> descendants) {
        super(value);
        this.descendants = new ArrayList<>(descendants);
    }

    public List<Vertex> getDescendants() {
        return descendants;
    }

    @Override
    public void print() {
        System.out.println("Branch, value " + this.getValue());
    }

    @Override
    public void accept(VertexVisitor visitor) {
        visitor.visit(this);
    }

}
