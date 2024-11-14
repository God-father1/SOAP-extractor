package com.stub.generator.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter
public class JpaConverterJson implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> paramMap) {
        try {
            return objectMapper.writeValueAsString(paramMap);
        } catch (Exception e) {
            throw new RuntimeException("Could not convert map to JSON string.");
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Could not convert JSON string to map.");
        }
    }
}
