package com.epam.lab.entities.visitors;

import com.epam.lab.model.Author;
import com.epam.lab.model.Book;
import com.epam.lab.model.Gender;
import com.epam.lab.model.PublishingHouse;
import javafx.util.Pair;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.epam.lab.entities.utils.FileDelimiters.*;
import static com.epam.lab.entities.utils.FileFields.*;

/**
 * Created by Kate on 24.10.2017.
 */
public class StringToModelImporter {
    private static final int KEY_VALUE_PAIR_LENGTH = 2;
    private Map<Long, Author> idAuthorMap;
    private Map<Long, Book> idBookMap;
    private Map<Long, PublishingHouse> idPublHouseMap;

    public StringToModelImporter() {
        this.idBookMap = new HashMap<>();
        this.idAuthorMap = new HashMap<>();
        this.idPublHouseMap = new HashMap<>();
    }

    public Collection<PublishingHouse> importPublishingHouses(String fromString) {
        try (Scanner scanner = new Scanner(new StringReader(fromString))) {
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                switch (nextLine) {
                    case ENTITY_DELIMITER + AUTHOR_ENTITY:
                        readAuthorToMap(scanner);
                        break;
                    case ENTITY_DELIMITER + BOOK_ENTITY:
                        readBookToMap(scanner);
                        break;
                    case ENTITY_DELIMITER + PUBLISHING_HOUSE_ENTITY:
                        readPublishingHouseToMap(scanner);
                        break;
                }
            }
        }
        return this.idPublHouseMap.values();
    }

    private void readPublishingHouseToMap(Scanner scanner) {
        long id = Long.MIN_VALUE;
        String name = null;
        List<Book> books = new ArrayList<>();

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.equals(ENTITY_DELIMITER)) {
                Pair<String, String> keyValuePair = getKeyValuePair(nextLine);
                switch (keyValuePair.getKey()) {
                    case ID_FIELD:
                        id = Long.parseLong(keyValuePair.getValue());
                    case NAME_FIELD:
                        name = keyValuePair.getValue();
                        break;
                    case LIST_BOOKS_FIELD: {
                        List<Long> referencesToBooks = getReferences(keyValuePair.getValue());
                        books = this.idBookMap.entrySet().stream()
                                .filter(entry -> referencesToBooks.contains(entry.getKey()))
                                .map(Map.Entry::getValue)
                                .collect(Collectors.toList());
                        break;
                    }
                }
            } else {
                if (id == Long.MIN_VALUE || name == null)
                    throw new IllegalArgumentException("Incorrect Publishing House data");
                PublishingHouse importedPublishingHouse = new PublishingHouse(name, books);
                this.idPublHouseMap.put(id, importedPublishingHouse);
                break;
            }
        }
    }

    private void readBookToMap(Scanner scanner) {
        long id = Long.MIN_VALUE;
        String title = null;
        int year = Integer.MIN_VALUE;
        List<Author> authors = new ArrayList<>();

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.equals(ENTITY_DELIMITER)) {
                Pair<String, String> keyValuePair = getKeyValuePair(nextLine);
                switch (keyValuePair.getKey()) {
                    case ID_FIELD:
                        id = Long.parseLong(keyValuePair.getValue());
                    case TITLE_FIELD:
                        title = keyValuePair.getValue();
                        break;
                    case YEAR_FIELD:
                        year = Integer.parseInt(keyValuePair.getValue());
                        break;
                    case LIST_AUTHORS_FIELD: {
                        List<Long> referencesToAuthors = getReferences(keyValuePair.getValue());
                        authors = this.idAuthorMap.entrySet().stream()
                                .filter(entry -> referencesToAuthors.contains(entry.getKey()))
                                .map(Map.Entry::getValue)
                                .collect(Collectors.toList());
                        break;
                    }
                }
            } else {
                if (id == Long.MIN_VALUE || title == null || year < 0 || (LocalDate.now().getYear() < year) || authors.size() == 0)
                    throw new IllegalArgumentException("Incorrect Book data");
                Book importedBook = new Book(title, year, authors);
                this.idBookMap.put(id, importedBook);
                break;
            }
        }
    }

    private void readAuthorToMap(Scanner scanner) {
        long id = Long.MIN_VALUE;
        String name = null;
        LocalDate birthday = null;
        LocalDate dayOfDeath = null;
        Gender gender = null;

        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            if (!nextLine.equals(ENTITY_DELIMITER)) {
                Pair<String, String> keyValuePair = getKeyValuePair(nextLine);
                switch (keyValuePair.getKey()) {
                    case ID_FIELD:
                        id = Long.parseLong(keyValuePair.getValue());
                        break;
                    case NAME_FIELD:
                        name = keyValuePair.getValue();
                        break;
                    case BIRTHDAY_FIELD:
                        birthday = LocalDate.parse(keyValuePair.getValue());
                        break;
                    case DAY_OF_DEATH_FIELD:
                        dayOfDeath = LocalDate.parse(keyValuePair.getValue());
                        break;
                    case GENDER_FIELD:
                        gender = Gender.valueOf(keyValuePair.getValue());
                        break;
                }
            } else {
                if (id == Long.MIN_VALUE || name == null || birthday == null || (dayOfDeath != null && dayOfDeath.isBefore(birthday)) || gender == null)
                    throw new IllegalArgumentException("Incorrect Author data");
                Author importedAuthor = new Author(name, birthday, dayOfDeath, gender);
                this.idAuthorMap.put(id, importedAuthor);
                break;
            }
        }
    }

    private List<Long> getReferences(String stringList) {
        stringList = stringList.trim();
        if (!stringList.startsWith(LIST_ENTITIES_START) || !stringList.endsWith(LIST_ENTITIES_END))
            throw new IllegalArgumentException("Incorrect list of references - " + stringList);
        String[] splittedReferences = stringList.split("(\\" + LIST_ENTITIES_START + "|\\" + LIST_ENTITIES_END + "|" + REFERENCE_DELIMITER + ")");
        List<Long> references = new ArrayList<>();
        for (String ref : splittedReferences) {
            if (!ref.trim().isEmpty()) {
                try {
                    references.add(Long.parseLong(ref.trim()));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Incorrect reference to other object - " + stringList);
                }
            }
        }
        return references;
    }

    private Pair<String, String> getKeyValuePair(String keyValueLine) {
        String[] keyValue = keyValueLine.split(KEY_VALUE_DELIMITER, KEY_VALUE_PAIR_LENGTH);
        if (keyValue.length != KEY_VALUE_PAIR_LENGTH)
            throw new IllegalArgumentException("Incorrect line " + keyValueLine);
        return new Pair<>(keyValue[0], keyValue[1]);
    }

}
