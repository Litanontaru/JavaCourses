package com.epam.lab.entities.visitors;

import com.epam.lab.entities.SerializableAuthor;
import com.epam.lab.entities.SerializableBook;
import com.epam.lab.entities.SerializablePublishingHouse;

import java.util.Collection;

import static com.epam.lab.entities.visitors.utils.FileDelimiters.*;
import static com.epam.lab.entities.visitors.utils.FileFields.*;

/**
 * Created by Kate on 24.10.2017.
 */
public class StringExportVisitor implements Visitor {
    private StringBuilder exportString;

    public String export(Collection<? extends Visitable> objectsToExport) {
        exportString = new StringBuilder();
        exportString.append(AREA_DELIMITER).append(LINE_DELIMITER);
        for (Visitable object : objectsToExport) {
            exportString.append(ENTITY_DELIMITER).append(LINE_DELIMITER);
            object.accept(this);
        }
        exportString.append(ENTITY_DELIMITER).append(LINE_DELIMITER);
        exportString.append(AREA_DELIMITER).append(LINE_DELIMITER);
        String stringToExport = exportString.toString();
        exportString = null;
        return stringToExport;
    }

    @Override
    public void visit(SerializableAuthor author) {
        exportString.append(ID_FIELD).append(KEY_VALUE_DELIMITER).append(author.getId()).append(LINE_DELIMITER);
        exportString.append(NAME_FIELD).append(KEY_VALUE_DELIMITER).append(author.getName()).append(LINE_DELIMITER);
        exportString.append(BIRTHDAY_FIELD).append(KEY_VALUE_DELIMITER).append(author.getBirthday()).append(LINE_DELIMITER);
        if (author.getDayOfDeath() != null) {
            exportString.append(DAY_OF_DEATH_FIELD).append(KEY_VALUE_DELIMITER).append(author.getDayOfDeath()).append(LINE_DELIMITER);
        }
        exportString.append(GENDER_FIELD).append(KEY_VALUE_DELIMITER).append(author.getGender()).append(LINE_DELIMITER);
    }

    @Override
    public void visit(SerializableBook book) {
        exportString.append(ID_FIELD).append(KEY_VALUE_DELIMITER).append(book.getId()).append(LINE_DELIMITER);
        exportString.append(TITLE_FIELD).append(KEY_VALUE_DELIMITER).append(book.getTitle()).append(LINE_DELIMITER);
        exportString.append(YEAR_FIELD).append(KEY_VALUE_DELIMITER).append(book.getYear()).append(LINE_DELIMITER);
        exportString.append(LIST_AUTHORS_FIELD).append(KEY_VALUE_DELIMITER).append(LIST_ENTITIES_START);
        for (SerializableAuthor author : book.getAuthors()) {
            exportString.append(author.getId()).append(REFERENCE_DELIMITER);
        }
        exportString.delete(exportString.length() - REFERENCE_DELIMITER.length(), exportString.length());
        exportString.append(LIST_ENTITIES_END).append(LINE_DELIMITER);
    }

    @Override
    public void visit(SerializablePublishingHouse publishingHouse) {
        exportString.append(ID_FIELD).append(KEY_VALUE_DELIMITER).append(publishingHouse.getId()).append(LINE_DELIMITER);
        exportString.append(NAME_FIELD).append(KEY_VALUE_DELIMITER).append(publishingHouse.getName()).append(LINE_DELIMITER);
        exportString.append(LIST_BOOKS_FIELD).append(KEY_VALUE_DELIMITER).append(LIST_ENTITIES_START);
        for (SerializableBook book : publishingHouse.getBooks()) {
            exportString.append(book.getId()).append(REFERENCE_DELIMITER);
        }
        exportString.delete(exportString.length() - REFERENCE_DELIMITER.length(), exportString.length());
        exportString.append(LIST_ENTITIES_END).append(LINE_DELIMITER);
    }
}
