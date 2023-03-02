package com.example.smartcontroller.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getCharacter();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Stream.of(Status.values())
                .filter(s -> s.getCharacter().equalsIgnoreCase(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}