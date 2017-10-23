package com.epam.lab.serialization;

import com.epam.lab.entities.SerializableAuthor;
import com.epam.lab.entities.SerializableBook;
import com.epam.lab.entities.SerializablePublishingHouse;
import com.epam.lab.entities.converters.ConverterToSerializable;
import com.epam.lab.entities.visitors.StringExportVisitor;
import com.epam.lab.model.PublishingHouse;

import java.io.*;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Kate on 24.10.2017.
 */
public class TextSerializer implements Serializer {
    @Override
    public void serialize(String fileName, Collection<PublishingHouse> publishingHouses) throws IOException {
        try (PrintWriter outSerializationStream =
                     new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            Collection<SerializablePublishingHouse> serPublishingHouses
                    = new ConverterToSerializable().convertPublishingHouses(publishingHouses);
            Collection<SerializableBook> serializableBooks = serPublishingHouses.stream()
                                                                            .flatMap(house -> house.getBooks().stream())
                                                                            .distinct()
                                                                            .collect(Collectors.toList());
            Collection<SerializableAuthor> serializableAuthors = serializableBooks.stream()
                                                                            .flatMap(book -> book.getAuthors().stream())
                                                                            .distinct()
                                                                            .collect(Collectors.toList());
            StringExportVisitor exportVisitor = new StringExportVisitor();
            outSerializationStream.println(exportVisitor.export(serializableAuthors));
            outSerializationStream.println(exportVisitor.export(serializableBooks));
            outSerializationStream.println(exportVisitor.export(serPublishingHouses));
        } catch (IOException e) {
            throw new IOException("Text Serialization is interrupted", e);
        }
    }

    @Override
    public Collection<PublishingHouse> deserialize(String fileName) throws IOException {
        return null;
    }
}
