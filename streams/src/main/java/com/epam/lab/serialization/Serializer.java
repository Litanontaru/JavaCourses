package com.epam.lab.serialization;

import com.epam.lab.model.PublishingHouse;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Kate on 22.10.2017.
 */
public interface Serializer {

    void serialize(String fileName, Collection<PublishingHouse> publishingHouses) throws IOException;

    Collection<PublishingHouse> deserialize(String fileName) throws IOException;
}
