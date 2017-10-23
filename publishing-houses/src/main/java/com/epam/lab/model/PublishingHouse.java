package com.epam.lab.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kate on 22.10.2017.
 */
public class PublishingHouse {
    private String name;
    private List<Book> books;

    public PublishingHouse(String name, Book... books) {
        this(name, Arrays.asList(books));
    }

    public PublishingHouse(String name, Collection<Book> books) {
        this.name = name;
        this.books = new ArrayList<>(books);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublishingHouse that = (PublishingHouse) o;

        return name.equals(that.name) && (books != null ? books.equals(that.books) : that.books == null);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final String lineDelimiter = "\n";
        StringBuilder info = new StringBuilder("Publishing House \"" + this.name + "\" is pleased to present books:" + lineDelimiter);
        for (Book book : books) {
            info.append(book.toString()).append(lineDelimiter);
        }
        return info.toString();
    }
}
