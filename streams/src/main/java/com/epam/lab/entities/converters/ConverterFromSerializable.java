package com.epam.lab.entities.converters;

import com.epam.lab.entities.SerializableAuthor;
import com.epam.lab.entities.SerializableBook;
import com.epam.lab.entities.SerializablePublishingHouse;
import com.epam.lab.model.Author;
import com.epam.lab.model.Book;
import com.epam.lab.model.PublishingHouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Kate on 23.10.2017.
 */
public class ConverterFromSerializable {
    private Map<Long, Author> idAuthorMap;
    private Map<Long, Book> idBookMap;

    public ConverterFromSerializable() {
        this.idBookMap = new HashMap<>();
        this.idAuthorMap = new HashMap<>();
    }

    private PublishingHouse convert(SerializablePublishingHouse serializablePublishingHouse) {
        List<Book> books = new ArrayList<>(serializablePublishingHouse.getBooks().size());
        for (SerializableBook serializableBook : serializablePublishingHouse.getBooks()) {
            books.add(this.idBookMap.computeIfAbsent(serializableBook.getId(), a -> convert(serializableBook)));
        }
        return serializablePublishingHouse.resolvePublishingHouse(books);
    }

    private Book convert(SerializableBook serializableBook) {
        List<Author> authors = new ArrayList<>(serializableBook.getAuthors().size());
        for (SerializableAuthor serializableAuthor : serializableBook.getAuthors()) {
            authors.add(this.idAuthorMap.computeIfAbsent(serializableAuthor.getId(), a -> convert(serializableAuthor)));
        }
        return serializableBook.resolveBook(authors);
    }

    private Author convert(SerializableAuthor serializableAuthor) {
        return serializableAuthor.resolveAuthor();
    }

    public List<PublishingHouse> convertToPublishingHouses(List<SerializablePublishingHouse> serializablePublishingHouses) {
        return serializablePublishingHouses.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<Book> convertToBooks(List<SerializableBook> books) {
        return books.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public List<Author> convertToAuthors(List<SerializableAuthor> authors) {
        return authors.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
