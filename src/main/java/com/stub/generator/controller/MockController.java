package com.stub.generator.controller;

import com.stub.generator.service.MockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mock")
public class MockController {

    @Autowired
    private MockService mockService;

    @PostMapping("/{endpoint}/{operation}")
    public ResponseEntity<String> handleMockRequest(@PathVariable String endpoint, @PathVariable String operation) {
        String mockResponse = mockService.getMockResponse(endpoint, operation);
        return ResponseEntity.ok(mockResponse);
    }
}
