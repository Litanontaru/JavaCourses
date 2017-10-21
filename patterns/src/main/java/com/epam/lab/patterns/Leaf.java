package com.epam.lab.patterns;

/**
 * Created by Kate on 22.10.2017.
 */
public class Leaf implements Vertex {
    private int value;

    public Leaf(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.println("Leaf, value " + this.value);
    }
}
