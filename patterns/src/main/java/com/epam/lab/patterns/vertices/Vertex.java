package com.epam.lab.patterns.vertices;

import com.epam.lab.patterns.visitors.VertexVisitor;

/**
 * Created by Kate on 22.10.2017.
 */
public interface Vertex {
    void print();

    void accept(VertexVisitor visitor);
}