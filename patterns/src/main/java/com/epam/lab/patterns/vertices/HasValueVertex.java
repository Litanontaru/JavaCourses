package com.epam.lab.patterns.vertices;


/**
 * Created by Kate on 22.10.2017.
 */
public abstract class HasValueVertex implements Vertex {
    private int value;

    protected HasValueVertex(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
