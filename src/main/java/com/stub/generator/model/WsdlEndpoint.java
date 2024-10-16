package com.stub.generator.model;
import jakarta.persistence.*;

@Entity
public class WsdlEndpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endpointUrl;
    private String operationName;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEndpointUrl() { return endpointUrl; }
    public void setEndpointUrl(String endpointUrl) { this.endpointUrl = endpointUrl; }
    public String getOperationName() { return operationName; }
    public void setOperationName(String operationName) { this.operationName = operationName; }

    @Override
    public String toString() {
        return "WsdlEndpoint{" +
                "id=" + id +
                ", endpointUrl='" + endpointUrl + '\'' +
                ", operationName='" + operationName + '\'' +
                '}';
    }
}
