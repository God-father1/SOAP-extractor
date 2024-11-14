package com.stub.generator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WsdlEndpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endpoint;
    private String operation;
    private String schema;
    private String requestPayload;
    private String responsePayload;

    // Constructors, getters, and setters

    public WsdlEndpoint() {}

    public WsdlEndpoint(String endpoint, String operation, String schema, String requestPayload, String responsePayload) {
        this.endpoint = endpoint;
        this.operation = operation;
        this.schema = schema;
        this.requestPayload = requestPayload;
        this.responsePayload = responsePayload;
    }

    // Getters and setters here...
}
