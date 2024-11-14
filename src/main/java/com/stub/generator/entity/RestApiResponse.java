package com.stub.generator.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.stub.generator.service.JpaConverterJson;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestApiResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String operation;

    @Convert(converter = JpaConverterJson.class)
    @JsonDeserialize(as = LinkedHashMap.class)
    private LinkedHashMap<String, Object> requestPayload;  // Use JSON or a serialized format to store request payload

    @Lob
    private String responsePayload;

    public RestApiResponse(String operation, LinkedHashMap<String, Object> requestPayload, String responsePayload) {
        this.operation = operation;
        this.requestPayload = requestPayload;
        this.responsePayload = responsePayload;
    }


    // Constructors, getters, and setters
}
