package com.epam.lab.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kate on 09.10.2017.
 */
public class Book {
    private String title;
    private int year;
    private List<Author> authors;

    public Book(String title, int year, Author... authors) {
        this.title = title;
        this.year = year;
        this.authors = new ArrayList<>();
        this.authors.addAll( Arrays.asList(authors));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }


    @Override
    public String toString() {
        final String lineDelimiter = "\n";
        StringBuilder info = new StringBuilder(this.title + ", " + this.year + lineDelimiter);
        for (Author author : authors) {
            info.append(author.toString()).append(lineDelimiter);
        }
        return info.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (year != book.year) return false;
        return title.equals(book.title);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + year;
        return result;
    }
}
