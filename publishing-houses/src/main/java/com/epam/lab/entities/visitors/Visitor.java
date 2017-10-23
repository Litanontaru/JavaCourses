package com.epam.lab.entities.visitors;

import com.epam.lab.entities.SerializableAuthor;
import com.epam.lab.entities.SerializableBook;
import com.epam.lab.entities.SerializablePublishingHouse;

/**
 * Created by Kate on 24.10.2017.
 */
public interface Visitor {

    void visit(SerializableAuthor author);

    void visit(SerializableBook book);

    void visit(SerializablePublishingHouse publishingHouse);
}
