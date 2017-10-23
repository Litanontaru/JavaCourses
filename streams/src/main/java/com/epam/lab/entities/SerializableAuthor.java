package com.epam.lab.entities;

import com.epam.lab.model.Author;
import com.epam.lab.model.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * Created by Kate on 22.10.2017.
 */
public class SerializableAuthor implements Serializable {
    public static final long serialVersionUID = 1;
    private String name;
    private LocalDate birthday;
    private LocalDate dayOfDeath;
    private Gender gender; // enum implements Serializable by default
    private long id;

    public SerializableAuthor(Author author) {
        this.name = author.getName();
        this.birthday = author.getBirthday();
        this.dayOfDeath = author.getDayOfDeath();
        this.gender = author.getGender();
        this.id = Calendar.getInstance().getTimeInMillis() + author.hashCode();
    }

    public Author resolveAuthor() {
        return new Author(name, birthday, dayOfDeath, Gender.valueOf(gender.name()));
    }

    public long getId() {
        return id;
    }
}
