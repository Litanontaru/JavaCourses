package com.epam.lab.entities.visitors;

/**
 * Created by Kate on 24.10.2017.
 */
public interface Visitable {
    void accept(Visitor visitor);
}
