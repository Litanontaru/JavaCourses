package com.epam.lab.streams;

import com.epam.lab.model.Author;
import com.epam.lab.model.Book;
import com.epam.lab.model.Gender;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ekmi0715 on 10/13/2017.
 */
public class LibraryStreams {

    public static void workWithStreams() {
        List<Book> books = initializeListOfBooks();
        List<Author> authors = books.stream()
                .flatMap(b -> b.getAuthors().stream())
                .distinct().collect(Collectors.toList());

        printAuthorsSortedByAge(authors);
        printAuthorsAverageAge(authors);
        printElderlyAuthors(authors);
        printBooksAndYears(books);
        printAuthorsInCoAuthorship(books);
        printAuthorsAndBooks(books);
    }

    public static List<Book> initializeListOfBooks() {
        List<Author> authors = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Author remark = new Author("Remark", LocalDate.of(1898, 6, 22),
                LocalDate.of(1970, 9, 25), Gender.MALE);
        authors.add(remark);
        books.add(new Book("Three Comrades", 1937, remark));
        books.add(new Book("Arch of Triumph", 1945, remark));

        Author sapkowski = new Author("Sapkowski", LocalDate.of(1948, 6, 21), Gender.MALE);
        authors.add(sapkowski);
        books.add(new Book("The Last Wish", 1992, sapkowski));
        books.add(new Book("Sword of Destiny", 1993, sapkowski));

        Author rowling = new Author("Rowling", LocalDate.of(1965, 7, 31), Gender.FEMALE);
        authors.add(rowling);
        books.add(new Book("Harry Potter and the Philosopher's Stone", 1997, rowling));
        books.add(new Book("Harry Potter and the Order of the Phoenix", 2003, rowling));
        books.add(new Book("Harry Potter and the Deathly Hallows", 2007, rowling));

        Author[] gangOfFour = new Author[]{
                new Author("Gamma", LocalDate.of(1961, 5, 13), Gender.MALE),
                new Author("Helm", LocalDate.of(1961, 1, 1), Gender.MALE),
                new Author("Johnson", LocalDate.of(1955, 10, 7), Gender.MALE),
                new Author("Vlissides", LocalDate.of(1961, 8, 2),
                        LocalDate.of(2005, 11, 24), Gender.MALE)};
        authors.addAll(Arrays.asList(gangOfFour));
        books.add(new Book("Design Patterns: Elements of Reusable Object-Oriented Software",
                1994, gangOfFour));

        Author[] ilfAndPetrov = new Author[]{
                new Author("Ilf", LocalDate.of(1897, 10, 15),
                        LocalDate.of(1937, 4, 13), Gender.MALE),
                new Author("Petrov", LocalDate.of(1903, 12, 13),
                        LocalDate.of(1942, 7, 2), Gender.MALE)
        };
        authors.addAll(Arrays.asList(ilfAndPetrov));
        books.add(new Book("One-story America", 1937, ilfAndPetrov));
        books.add(new Book("The Twelve Chairs", 1928, ilfAndPetrov));

        Author rand = new Author("Rand", LocalDate.of(1905, 1, 20),
                LocalDate.of(1982, 5, 6), Gender.FEMALE);
        authors.add(rand);
        books.add(new Book("Atlas Shrugged", 1957, rand));

        return books;
    }

    public static void printAuthorsSortedByAge(List<Author> authors) {
        System.out.println("----Sorted by age list of authors----");
        authors.stream()
                .sorted(Comparator.comparingInt(Author::getCurrentAge))
                .forEach(System.out::println);
    }

    public static void printAuthorsAverageAge(List<Author> authors) {
        System.out.println("----Average age----");
        double averageAge = authors.stream()
                .mapToInt(Author::getCurrentAge)
                .average().getAsDouble();
        System.out.println("Average age of all authors = " + averageAge);
    }

    public static void printElderlyAuthors(List<Author> authors) {
        final int workingAgeLimitFemale = 63;
        final int workingAgeLimitMale = 65;
        System.out.println("----Elderly----");
        authors.stream()
                .filter(a -> a.getCurrentAge() >
                        ((a.getGender() == Gender.MALE) ? workingAgeLimitMale : workingAgeLimitFemale))
                .forEach(System.out::println);
    }

    public static void printBooksAndYears(List<Book> books) {
        System.out.println("----Books and Years----");
        int currentYear = LocalDate.now().getYear();
        books.stream()
                .collect(Collectors.toMap(Book::getTitle, b -> currentYear - b.getYear()))
                .entrySet()
                .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
    }

    public static void printAuthorsInCoAuthorship(List<Book> books) {
        final int singleAuthorCount = 1;
        System.out.println("----Co-authorship----");
        books.stream()
                .flatMap(b -> (b.getAuthors().size() > singleAuthorCount) ? b.getAuthors().stream() : Stream.empty())
                .distinct()
                .forEach(System.out::println);
    }

    public static void printAuthorsAndBooks(List<Book> books) {
        System.out.println("----Author and Books----");
        books.stream()
                .flatMap(book -> book.getAuthors().stream()
                        .map(author -> new Pair<>(author.getName(), book.getTitle())))
                .collect(Collectors.groupingBy(Pair::getKey, Collectors.mapping(Pair::getValue, Collectors.joining(", "))))
                .entrySet()
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }

}
