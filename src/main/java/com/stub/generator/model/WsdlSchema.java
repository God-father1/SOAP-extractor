package com.stub.generator.model;

import jakarta.persistence.*;

@Entity
public class WsdlSchema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private WsdlEndpoint endpoint;

    @Lob
    private String inputSchema;

    @Lob
    private String outputSchema;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public WsdlEndpoint getEndpoint() { return endpoint; }
    public void setEndpoint(WsdlEndpoint endpoint) { this.endpoint = endpoint; }
    public String getInputSchema() { return inputSchema; }
    public void setInputSchema(String inputSchema) { this.inputSchema = inputSchema; }
    public String getOutputSchema() { return outputSchema; }
    public void setOutputSchema(String outputSchema) { this.outputSchema = outputSchema; }

    @Override
    public String toString() {
        return "WsdlSchema{" +
                "id=" + id +
                ", endpoint=" + endpoint +
                ", inputSchema='" + inputSchema + '\'' +
                ", outputSchema='" + outputSchema + '\'' +
                '}';
    }
}
