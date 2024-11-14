package com.stub.generator.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RequestResponseLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlPath;
    private String httpMethod;
    private String headers;
    @Lob
    private String requestBody;
    @Lob
    private String responseBody;
    private String statusCode;
    private LocalDateTime timestamp;
    private String responsePayload;
    private String operation;



    // Getters and Setters
}
