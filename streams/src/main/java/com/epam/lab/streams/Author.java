package com.epam.lab.streams;

import java.time.LocalDate;

/**
 * Created by Kate on 09.10.2017.
 */
public class Author {
    private static final int DEFAULT_AGE = 0;
    private String name;
    private LocalDate birthday;
    private LocalDate dayOfDeath;
    private Gender gender;

    public Author(String name, LocalDate birthday, LocalDate dayOfDeath, Gender gender) {
        this.name = name;
        this.birthday = birthday;
        this.dayOfDeath = dayOfDeath;
        this.gender = gender;
    }

    public Author(String name, LocalDate birthday, Gender gender) {
        this(name, birthday, null, gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDayOfDeath() {
        return dayOfDeath;
    }

    public void setDayOfDeath(LocalDate dayOfDeath) {
        this.dayOfDeath = dayOfDeath;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getCurrentAge() {
        if (birthday == null)
            return DEFAULT_AGE;
        LocalDate latestDate = (this.dayOfDeath != null) ? dayOfDeath : LocalDate.now();
        int age = latestDate.getYear() - birthday.getYear();
        if (latestDate.getDayOfYear() > birthday.getDayOfYear()) {
            age--;
        }
        return age;
    }

    @Override
    public String toString() {
        final String VALUE_DELIMITER = ", ";
        return this.name
                + VALUE_DELIMITER + this.getCurrentAge()
                + VALUE_DELIMITER + this.gender.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!name.equals(author.name)) return false;
        if (birthday != null ? !birthday.equals(author.birthday) : author.birthday != null) return false;
        if (dayOfDeath != null ? !dayOfDeath.equals(author.dayOfDeath) : author.dayOfDeath != null) return false;
        return gender == author.gender;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (dayOfDeath != null ? dayOfDeath.hashCode() : 0);
        result = 31 * result + gender.hashCode();
        return result;
    }

}
