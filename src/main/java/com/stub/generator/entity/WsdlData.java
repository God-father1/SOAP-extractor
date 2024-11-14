package com.stub.generator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class WsdlData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String endpoint;
    private String operation;
    private String requestSchema;
    private String responsePayload;

    // REST-specific fields
    private String httpMethod;
    private String urlPath;
    private String headers;

    // Constructors, Getters, and Setters
}
