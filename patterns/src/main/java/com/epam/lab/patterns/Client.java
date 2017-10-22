package com.epam.lab.patterns;

import com.epam.lab.patterns.utils.TreeGenerator;
import com.epam.lab.patterns.vertices.Vertex;
import com.epam.lab.patterns.visitors.AddVisitor;
import com.epam.lab.patterns.visitors.VertexVisitor;
import com.epam.lab.patterns.visitors.PrintVisitor;

/**
 * Created by Kate on 22.10.2017.
 */
public class Client {

    private static void visitAll(Iterable<Vertex> structure, VertexVisitor visitor) {
        for (Vertex vertex : structure) {
            vertex.accept(visitor);
        }
    }

    private static void printAll(Iterable<Vertex> structure) {
        visitAll(structure, new PrintVisitor());
    }

    private static void addToAll(Iterable<Vertex> structure) {
        visitAll(structure, new AddVisitor()); // AddVisitor adds 1 to branch and 2 to leaf
    }

    public static void main(String[] args) {
        System.out.println("---Print Tree---");
        Tree randomTree = TreeGenerator.generateRandomTree();
        printAll(randomTree);
        System.out.println("---Add values to Tree (adds 1 to branch and 2 to leaf)---");
        addToAll(randomTree);
        printAll(randomTree);
    }
}
