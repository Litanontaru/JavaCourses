package com.epam.lab.patterns;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kate on 22.10.2017.
 */
public class Branch extends Leaf {
    private List<Vertex> descendants;

    public Branch(int value, Vertex... descendants) {
        super(value);
        this.descendants = new ArrayList<>(Arrays.asList(descendants));
    }

    @Override
    public void print() {
        System.out.println("Branch, value" + this.getValue());
    }
}
