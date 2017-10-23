package com.epam.lab.serialization;

import com.epam.lab.entities.converters.ConverterFromSerializable;
import com.epam.lab.entities.converters.ConverterToSerializable;
import com.epam.lab.entities.SerializablePublishingHouse;
import com.epam.lab.model.PublishingHouse;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kate on 23.10.2017.
 */
public class ByteSerializer implements Serializer {

    @Override
    public void serialize(String fileName, Collection<PublishingHouse> publishingHouses) throws IOException {
        try (ObjectOutputStream outSerializationStream =
                     new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            Collection<SerializablePublishingHouse> serPublishingHouses = new ConverterToSerializable().convertPublishingHouses(publishingHouses);
            outSerializationStream.writeInt(serPublishingHouses.size());
            for (SerializablePublishingHouse serPublishingHouse : serPublishingHouses) {
                outSerializationStream.writeObject(serPublishingHouse);
            }
        } catch (IOException e) {
            throw new IOException("Byte Serialization is interrupted", e);
        }
    }

    @Override
    public Collection<PublishingHouse> deserialize(String fileName) throws IOException {
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            int count = inputStream.readInt();
            List<SerializablePublishingHouse> serPublishingHouses = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                serPublishingHouses.add((SerializablePublishingHouse) inputStream.readObject());
            }
            return new ConverterFromSerializable().convertToPublishingHouses(serPublishingHouses);
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Byte Deserialization is interrupted", e);
        }
    }
}
