package com.epam.lab.patterns.utils;

import com.epam.lab.patterns.vertices.Branch;
import com.epam.lab.patterns.vertices.Leaf;
import com.epam.lab.patterns.Tree;
import com.epam.lab.patterns.vertices.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Kate on 22.10.2017.
 */
public class TreeGenerator {
    private static final int MAX_DEPTH = 3;
    private static final int MIN_DEPTH = 1;
    private static final int MAX_WIDTH = 3;
    private static final int MIN_WIDTH = 1;
    private static final int MAX_VALUE = 100;
    private static Random random = new Random();

    public static Tree generateRandomTree() {
        int depth = MIN_DEPTH + random.nextInt(MAX_DEPTH);
        Vertex root;
        if (depth == MIN_DEPTH) {
            root = new Leaf(random.nextInt(MAX_VALUE));
        } else {
            root = new Branch(random.nextInt(MAX_VALUE), generateLevel(depth));
        }
        return new Tree(root);
    }

    private static List<Vertex> generateLevel(int level) {
        List<Vertex> descendants;
        if (level > 0) {
            descendants = generateLevel(level - 1);
        } else {
            return generateLeafs(MIN_WIDTH + random.nextInt(MAX_WIDTH));
        }
        Branch branch = new Branch(random.nextInt(MAX_VALUE), descendants);
        descendants = new ArrayList<>();
        descendants.add(branch);
        descendants.addAll(generateDescendants(level, random.nextInt(MAX_WIDTH)));
        return descendants;
    }

    private static List<Vertex> generateDescendants(int level, int count) {
        List<Vertex> descendants = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {

            descendants.add((random.nextBoolean())
                    ? new Branch(random.nextInt(MAX_VALUE), generateLevel(level - 1))
                    : new Leaf(random.nextInt(MAX_VALUE)));
        }
        return descendants;
    }

    private static List<Vertex> generateLeafs(int count) {
        List<Vertex> descendants = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            descendants.add(new Leaf(random.nextInt(MAX_VALUE)));
        }
        return descendants;
    }

}
