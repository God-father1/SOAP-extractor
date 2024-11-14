package com.stub.generator.service;

import com.stub.generator.entity.WsdlData;
import com.stub.generator.repository.RestResponseRepository;
import com.stub.generator.repository.WsdlDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;

import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Part;
import javax.wsdl.PortType;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import java.util.Map;
import java.util.Optional;

@Service
public class WsdlService {

    @Autowired
    private WsdlDataRepository wsdlDataRepository;



    public void parseAndSaveWsdl(MultipartFile file) throws Exception {
        // Initialize WSDL Reader
        WSDLFactory factory = WSDLFactory.newInstance();
        WSDLReader reader = factory.newWSDLReader();
        reader.setFeature("javax.wsdl.verbose", false);

        // Parse WSDL from the uploaded file
        Definition wsdlDefinition = reader.readWSDL(null, new InputSource(file.getInputStream()));

        // Extract and save operations
        String targetNamespace = wsdlDefinition.getTargetNamespace();
        Map<QName, PortType> portTypes = wsdlDefinition.getAllPortTypes();

        for (PortType portType : portTypes.values()) {
            for (Object opObj : portType.getOperations()) {
                Operation operation = (Operation) opObj;

                String operationName = operation.getName();
                String requestSchema = extractSchema(operation.getInput().getMessage().getParts());
                String responsePayload = extractSchema(operation.getOutput().getMessage().getParts());

                // Create WsdlData entry
                WsdlData wsdlData = new WsdlData();
                wsdlData.setEndpoint(targetNamespace);
                wsdlData.setOperation(operationName);
                wsdlData.setRequestSchema(requestSchema);
                wsdlData.setResponsePayload(responsePayload);

                // Save to database
                if (!saveWsdlData(wsdlData.getEndpoint(), wsdlData.getOperation(), wsdlData.getRequestSchema(), wsdlData.getResponsePayload()))
                    wsdlDataRepository.save(wsdlData);
            }
        }
    }

    private String extractSchema(Map<String, Part> parts) {
        StringBuilder schemaBuilder = new StringBuilder();
        for (Part part : parts.values()) {
            schemaBuilder.append(part.getName()).append(": ").append(part.getTypeName()).append("; ");
        }
        return schemaBuilder.toString();
    }

    public String getMockResponse(String operation) {
        return wsdlDataRepository.findByOperation(operation)
                .map(WsdlData::getResponsePayload)
                .orElse("<error>Endpoint or operation not found</error>");
    }

//    public void saveRestMock(String operation, String urlPath, String method, String headers, String responsePayload) {
//        WsdlData restMockData = new WsdlData();
//        restMockData.setOperation(operation);
//        restMockData.setUrlPath(urlPath);
//        restMockData.setHttpMethod(method);
//        restMockData.setHeaders(headers);
//        restMockData.setResponsePayload(responsePayload);
//
//        wsdlDataRepository.save(restMockData);
//    }
//
//

    // For SOAP: Method to save SOAP mock data after checking for duplicates
    public boolean saveWsdlData(String endpoint, String operation, String requestSchema, String responsePayload) {
        // Check if the endpoint and operation already exist
        Optional<WsdlData> existingData = wsdlDataRepository.findByEndpointAndOperation(endpoint, operation);

        if (existingData.isPresent()) {
            System.out.println("Endpoint and operation already exist. Skipping save or consider updating...");
            // Optionally, update the existing entry if needed
            // existingData.get().setResponsePayload(responsePayload);
            // wsdlDataRepository.save(existingData.get());
            return false;
        } else {
            // Save new SOAP data if not a duplicate
            WsdlData wsdlData = new WsdlData();
            wsdlData.setEndpoint(endpoint);
            wsdlData.setOperation(operation);
            wsdlData.setRequestSchema(requestSchema);
            wsdlData.setResponsePayload(responsePayload);
            wsdlDataRepository.save(wsdlData);
            return true;
        }
    }

    // For REST: Method to save REST mock data after checking for duplicates
    public void saveRestMock(String operation, String urlPath, String method, String headers, String responsePayload) {
        // Check if the operation and URL path with the same method already exist
        Optional<WsdlData> existingRestData = wsdlDataRepository.findByOperationAndHttpMethodAndUrlPath(operation, method, urlPath);

        if (existingRestData.isPresent()) {
            System.out.println("REST endpoint already exists. Skipping save or consider updating...");
            // Optionally, update the existing entry if needed
            // existingRestData.get().setResponsePayload(responsePayload);
            // wsdlDataRepository.save(existingRestData.get());
        } else {
            // Save new REST data if not a duplicate
            WsdlData restMockData = new WsdlData();
            restMockData.setOperation(operation);
            restMockData.setUrlPath(urlPath);
            restMockData.setHttpMethod(method);
            restMockData.setHeaders(headers);
            restMockData.setResponsePayload(responsePayload);
            wsdlDataRepository.save(restMockData);
        }
    }

    // Other methods for retrieving responses
    public String getMockResponse(String operation, String method, String urlPath) {
        Optional<WsdlData> mockData = wsdlDataRepository.findByOperationAndHttpMethodAndUrlPath(operation, method, urlPath);
        return mockData.map(WsdlData::getResponsePayload)
                .orElse("<error>Endpoint or operation not found</error>");
    }
}
