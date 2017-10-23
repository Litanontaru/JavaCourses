package com.epam.lab.entities.converters;

import com.epam.lab.entities.SerializableAuthor;
import com.epam.lab.entities.SerializableBook;
import com.epam.lab.entities.SerializablePublishingHouse;
import com.epam.lab.model.Author;
import com.epam.lab.model.Book;
import com.epam.lab.model.PublishingHouse;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Kate on 23.10.2017.
 */
public class ConverterToSerializable {
    private Map<Book, SerializableBook> bookSerializableBookMap;
    private Map<Author, SerializableAuthor> authorSerializableAuthorMap;

    public ConverterToSerializable() {
        this.bookSerializableBookMap = new HashMap<>();
        this.authorSerializableAuthorMap = new HashMap<>();
    }

    private SerializablePublishingHouse convert(PublishingHouse publishingHouse) {
        List<SerializableBook> serializableBooks = new ArrayList<>(publishingHouse.getBooks().size());
        for (Book book : publishingHouse.getBooks()) {
            serializableBooks.add(this.bookSerializableBookMap.computeIfAbsent(book, this::convert));
        }
        return new SerializablePublishingHouse(publishingHouse, serializableBooks);
    }

    private SerializableBook convert(Book book) {
        List<SerializableAuthor> serializableAuthors = new ArrayList<>(book.getAuthors().size());
        for (Author author : book.getAuthors()) {
            serializableAuthors.add(this.authorSerializableAuthorMap.computeIfAbsent(author, this::convert));
        }
        return new SerializableBook(book, serializableAuthors);
    }

    private SerializableAuthor convert(Author author) {
        return new SerializableAuthor(author);
    }

    public Collection<SerializablePublishingHouse> convertPublishingHouses(Collection<PublishingHouse> publishingHouses) {
        return publishingHouses.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public Collection<SerializableBook> convertBooks(Collection<Book> books) {
        return books.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public Collection<SerializableAuthor> convertAuthors(Collection<Author> authors) {
        return authors.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
