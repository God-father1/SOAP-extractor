package com.stub.generator.controller;

import com.stub.generator.model.WsdlEndpoint;
import com.stub.generator.model.WsdlSchema;
import com.stub.generator.repository.EndpointRepository;
import com.stub.generator.repository.SchemaRepository;
import com.stub.generator.service.WsdlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.wsdl.WSDLException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wsdl")
public class WsdlController {

    @Autowired
    private WsdlService wsdlService;

    @Autowired
    private EndpointRepository endpointRepository;

    @Autowired
    private SchemaRepository schemaRepository;

    // Existing upload endpoint (POST)
    @PostMapping("/upload")
    public String uploadWsdl(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty!";
        }
        try {
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);
            wsdlService.parseWsdl(convFile);
            return "WSDL uploaded and parsed successfully!";
        } catch (IOException | WSDLException e) {
            e.printStackTrace();
            return "Failed to upload WSDL: " + e.getMessage();
        }
    }

    // New endpoint to get all endpoints
    @GetMapping("/endpoints")
    public ResponseEntity<List<WsdlEndpoint>> getAllEndpoints() {
        List<WsdlEndpoint> endpoints = endpointRepository.findAll();
        return ResponseEntity.ok(endpoints);
    }

    // New endpoint to get all schemas
    @GetMapping("/schemas")
    public ResponseEntity<List<WsdlSchema>> getAllSchemas() {
        List<WsdlSchema> schemas = schemaRepository.findAll();
        return ResponseEntity.ok(schemas);
    }

    // New endpoint to get API count
    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getApiCount() {
        long endpointCount = endpointRepository.count();
        long schemaCount = schemaRepository.count();

        Map<String, Long> counts = new HashMap<>();
        counts.put("endpoints", endpointCount);
        counts.put("schemas", schemaCount);

        return ResponseEntity.ok(counts);
    }
}
