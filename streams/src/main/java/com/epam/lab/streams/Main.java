package com.epam.lab.streams;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Kate on 09.10.2017.
 */
public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        List<Book> books = initializeRealData();

        printAuthorsSortedList(books);
        printAuthorsAverageAge(books);
        printElderlyAuthors(books);
        printBooksAndYears(books);
        printAuthorsInCoAuthorship(books);
        printAuthorsAndBooks(books);
    }

    public static void printAuthorsSortedList(List<Book> books) {
        System.out.println("----Sorted list of authors----");
        books.stream()
                .flatMap(b -> b.getAuthors().stream())
                .distinct()
                .sorted(Comparator.comparingInt(Author::getCurrentAge))
                .forEach(System.out::println);
    }

    public static void printAuthorsAverageAge(List<Book> books) {
        System.out.println("----Average age----");
        double averageAge = books.stream()
                .flatMap(b -> b.getAuthors().stream())
                .distinct()
                .mapToInt(Author::getCurrentAge)
                .average().getAsDouble();
        System.out.println("Average age of all authors = " + averageAge);
    }

    public static void printElderlyAuthors(List<Book> books) {
        final int WORKING_AGE_LIMIT_FEMALE = 63;
        final int WORKING_AGE_LIMIT_MALE = 65;
        System.out.println("----Elderly----");
        books.stream()
                .flatMap(b -> b.getAuthors().stream())
                .distinct()
                .filter(a -> a.getCurrentAge() >
                        ((a.getGender() == Gender.MALE) ? WORKING_AGE_LIMIT_MALE : WORKING_AGE_LIMIT_FEMALE))
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
        final int SINGLE_AUTHOR_COUNT = 1;
        System.out.println("----Co-authorship----");
        books.stream() /*TODO: Одной операцией???*/
                .filter(b -> b.getAuthors().size() > SINGLE_AUTHOR_COUNT)
                .flatMap(b -> b.getAuthors().stream())
                .distinct()
                .forEach(System.out::println);
    }

    public static void printAuthorsAndBooks(List<Book> books) {
        System.out.println("----Author and Books----");
//        books.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.joining()));
/*        books.stream().flatMap(book -> {
            for (Author author: book.getAuthors()) {
                return new 
            }
        }).collect(Collectors.groupingBy(Book::getAuthors));*/
    }


    private static Author generateAuthor() {
        final int LIMIT_OF_DAYS = 28;
        final int LIMIT_OF_MONTHS = 12;
        final int LIMIT_OF_YEARS = 2000;
        return new Author(generateRandomString(),
                LocalDate.of(random.nextInt(LIMIT_OF_YEARS), random.nextInt(LIMIT_OF_MONTHS), random.nextInt(LIMIT_OF_DAYS)),
                (random.nextBoolean())
                        ? LocalDate.of(random.nextInt(LIMIT_OF_YEARS), random.nextInt(LIMIT_OF_MONTHS), random.nextInt(LIMIT_OF_DAYS))
                        : null,
                (random.nextBoolean())
                        ? Gender.MALE
                        : Gender.FEMALE);
    }

    private static Book generateBook(Collection<Author> authors) {
        final int LIMIT_OF_YEARS = 2018;
        return new Book(generateRandomString(), random.nextInt(LIMIT_OF_YEARS), authors);
    }

    private static String generateRandomString() {
        final int COUNT_OF_LETTERS = 26;
        int len = random.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append('a' + random.nextInt(COUNT_OF_LETTERS));
        }
        return sb.toString();
    }

    private static List<Book> initializeRealData() {
        List<Author> authors = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Author author1 = new Author("Remark", LocalDate.of(1898, 6, 22),
                LocalDate.of(1970, 9, 25), Gender.MALE);
        authors.add(author1);
        books.add(new Book("Three Comrades", 1937, author1));
        books.add(new Book("Arch of Triumph", 1945, author1));

        Author author2 = new Author("Sapkowski", LocalDate.of(1948, 6, 21), Gender.MALE);
        authors.add(author2);
        books.add(new Book("The Last Wish", 1992, author2));
        books.add(new Book("Sword of Destiny", 1993, author2));

        Author author3 = new Author("Rowling", LocalDate.of(1965, 7, 31), Gender.FEMALE);
        authors.add(author3);
        books.add(new Book("Harry Potter and the Philosopher's Stone", 1997, author3));
        books.add(new Book("Harry Potter and the Order of the Phoenix", 2003, author3));
        books.add(new Book("Harry Potter and the Deathly Hallows", 2007, author3));

        Author[] gangOfFour = new Author[]{
                new Author("Gamma", LocalDate.of(1961, 5, 13), Gender.MALE),
                new Author("Helm", LocalDate.of(1961, 1, 1), Gender.MALE),
                new Author("Johnson", LocalDate.of(1955, 10, 7), Gender.MALE),
                new Author("Vlissides", LocalDate.of(1961, 8, 2),
                        LocalDate.of(2005, 11, 24), Gender.MALE)};
        authors.addAll(Arrays.asList(gangOfFour));
        books.add(new Book("Design Patterns: Elements of Reusable Object-Oriented Software",
                1994, gangOfFour));

        Author[] authors45 = new Author[]{
                new Author("Ilf", LocalDate.of(1897, 10, 15),
                        LocalDate.of(1937, 4, 13), Gender.MALE),
                new Author("Petrov", LocalDate.of(1903, 12, 13),
                        LocalDate.of(1942, 7, 2), Gender.MALE)
        };
        authors.addAll(Arrays.asList(authors45));
        books.add(new Book("One-story America", 1937, authors45));
        books.add(new Book("The Twelve Chairs", 1928, authors45));

        Author author6 = new Author("Rand", LocalDate.of(1905, 1, 20),
                LocalDate.of(1982, 5, 6), Gender.FEMALE);
        authors.add(author6);
        books.add(new Book("Atlas Shrugged", 1957, author6));

        return books;
    }

    private static List<Book> initializeSyntheticData() {
        final int LIMIT_OF_OBJECTS = 10;
        List<Author> authors = new ArrayList<>();
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < LIMIT_OF_OBJECTS; i++) {
            Author author = generateAuthor();
            authors.add(author);
            for (int j = 0; j < random.nextInt(LIMIT_OF_OBJECTS); j++) {
                books.add(generateBook(random.nextBoolean() ? new ArrayList<Author>() {{ add(author);}} : authors));
            }
        }
        return books;
    }
}
