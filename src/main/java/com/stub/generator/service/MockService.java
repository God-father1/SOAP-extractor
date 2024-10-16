package com.stub.generator.service;


import com.stub.generator.model.WsdlEndpoint;
import com.stub.generator.model.WsdlSchema;
import com.stub.generator.repository.WsdlEndpointRepository;
import com.stub.generator.repository.WsdlSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MockService {
    @Autowired
    private WsdlEndpointRepository endpointRepository;

    @Autowired
    private WsdlSchemaRepository schemaRepository;

    public String getMockResponse(String endpointUrl, String operationName) {
        Optional<WsdlEndpoint> wsdlEndpoint = endpointRepository.findByEndpointUrlAndOperationName(endpointUrl, operationName);
        if (wsdlEndpoint.isPresent()) {
            Optional<WsdlSchema> schema = schemaRepository.findByEndpoint(wsdlEndpoint.get());
            if (schema.isPresent()) {
                return schema.get().getOutputSchema();  // Returns a mock response based on schema
            }
        }
        return "<error>Endpoint or operation not found</error>";
    }
}
