package com.epam.lab.entities;

import com.epam.lab.model.Book;
import com.epam.lab.model.PublishingHouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Kate on 22.10.2017.
 */
public class SerializablePublishingHouse implements Serializable {
    public static final long serialVersionUID = 1;
    private String name;
    private List<SerializableBook> books;
    private long id;

    public SerializablePublishingHouse(PublishingHouse house, List<SerializableBook> books) {
        this.name = house.getName();
        this.books = new ArrayList<>(books);
        this.id = Calendar.getInstance().getTimeInMillis() + house.hashCode();
    }

    public String getName() {
        return name;
    }

    public List<SerializableBook> getBooks() {
        return books;
    }

    public long getId() {
        return id;
    }

    public PublishingHouse resolvePublishingHouse(List<Book> books) {
        return new PublishingHouse(this.name, books);
    }

    @Override
    public String toString() {
        return "SerializablePublishingHouse{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
