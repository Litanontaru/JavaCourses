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
 * Created by Kate on 23.10.2017.
 */
public class ByteSerializerTest {
    private String fileName;

    private List<PublishingHouse> initializeTestData() {
        List<Book> books = LibraryStreams.initializeListOfBooks();
        PublishingHouse hornsAndHooves = new PublishingHouse("Horns and Hooves", books);
        PublishingHouse bullsAndCows = new PublishingHouse("Bulls and Cows"
                , books.stream()
                .filter(book -> book.getAuthors().stream().anyMatch(author -> author.getName().equals("Rowling")))
                .collect(Collectors.toList()));
        return new ArrayList<PublishingHouse>() {{
            add(hornsAndHooves);
            add(bullsAndCows);
        }};
    }

    @Test
    @Before
    public void testByteSerialization() throws IOException {
        System.out.println("-------Byte Serialization-------");
        List<PublishingHouse> publishingHouses = initializeTestData();
        ByteSerializer byteSerializer = new ByteSerializer();
        this.fileName = Files.createTempFile("serializer_test", ".ser").toString();
        System.out.println("-------To file = " + this.fileName);
        publishingHouses.forEach(System.out::println);
        byteSerializer.serialize(this.fileName, publishingHouses);
    }

    @Test
    public void testByteDeserialization() throws IOException {
        System.out.println("-------Byte Deserialization-------");
        System.out.println("-------From file = " + this.fileName);
        ByteSerializer byteSerializer = new ByteSerializer();
        Collection<PublishingHouse> publishingHouses = byteSerializer.deserialize(this.fileName);
        publishingHouses.forEach(System.out::println);
    }
}
