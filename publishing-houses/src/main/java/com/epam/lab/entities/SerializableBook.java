package com.epam.lab.entities;

import com.epam.lab.entities.visitors.Visitable;
import com.epam.lab.entities.visitors.Visitor;
import com.epam.lab.model.Author;
import com.epam.lab.model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kate on 22.10.2017.
 */
public class SerializableBook implements Serializable, Visitable {
    public static final long serialVersionUID = 1;
    private String title;
    private int year;
    private List<SerializableAuthor> authors;
    private long id;

    public SerializableBook(Book book, List<SerializableAuthor> serializableAuthors) {
        this.title = book.getTitle();
        this.year = book.getYear();
        this.authors = new ArrayList<>(serializableAuthors);
        this.id = Calendar.getInstance().getTimeInMillis() + book.hashCode();
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<SerializableAuthor> getAuthors() {
        return authors;
    }

    public long getId() {
        return id;
    }

    public Book resolveBook(List<Author> authors) {
        return new Book(this.title, this.year, authors);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
