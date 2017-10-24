package com.epam.lab.serialization;

import com.epam.lab.model.Book;
import com.epam.lab.model.PublishingHouse;
import com.epam.lab.streams.LibraryStreams;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kate on 24.10.2017.
 */
public class TextSerializerTest {
    private String fileName;

    private List<PublishingHouse> initializeTestData() {
        List<Book> books = LibraryStreams.initializeListOfBooks();
        PublishingHouse corpus = new PublishingHouse("Corpus", books);
        PublishingHouse act = new PublishingHouse("ACT"
                , books.stream()
                .filter(book -> book.getAuthors().stream().anyMatch(author -> author.getName().equals("Remark")))
                .collect(Collectors.toList()));
        return new ArrayList<PublishingHouse>() {{
            add(corpus);
            add(act);
        }};
    }

    @Test
    @Before
    public void testTextSerialization() throws IOException {
        System.out.println("-------Text Serialization-------");
        List<PublishingHouse> publishingHouses = initializeTestData();
        TextSerializer textSerializer = new TextSerializer();
        this.fileName = Files.createTempFile("serializer_test", ".txt").toString();
        System.out.println("-------To file = " + this.fileName);
        publishingHouses.forEach(System.out::println);
        textSerializer.serialize(this.fileName, publishingHouses);
    }


    @Test
    public void testTextDeserialization() throws IOException {
        System.out.println("-------Text Deserialization-------");
        System.out.println("-------From file = " + this.fileName);
        TextSerializer textSerializer = new TextSerializer();
        Collection<PublishingHouse> publishingHouses = textSerializer.deserialize(this.fileName);
        publishingHouses.forEach(System.out::println);
    }
}
