package com.stub.generator.controller;
import com.stub.generator.entity.RequestResponseLog;
import com.stub.generator.repository.RequestResponseLogRepository;
import com.stub.generator.service.WsdlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/mock")
public class WsdlMockController {

    @Autowired
    private WsdlService wsdlService;

    @PostMapping("/wsdl")
    public ResponseEntity<String> uploadWsdlFile(@RequestParam("file") MultipartFile file) {
        try {
            wsdlService.parseAndSaveWsdl(file);
            return ResponseEntity.ok("WSDL file uploaded and parsed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error parsing WSDL file.");
        }
    }

    @PostMapping("/{operation}")
    public ResponseEntity<String> mockResponse(@PathVariable String operation, @RequestBody String request) {
        String response = wsdlService.getMockResponse(operation);
        return ResponseEntity.ok(response);
    }


    @Autowired
    private RequestResponseLogRepository logRepository;

    @PostMapping("/mock/{operation}")
    public ResponseEntity<String> handleMockRequest(@PathVariable String operation, HttpServletRequest request) {
        Optional<RequestResponseLog> logEntry = logRepository.findByOperation(operation);

        if (logEntry.isPresent()) {
            return ResponseEntity.ok(logEntry.get().getResponsePayload());
        } else {
            // Handle actual logic if no mock response exists
            return ResponseEntity.ok("No response found exception");
        }
    }
}

