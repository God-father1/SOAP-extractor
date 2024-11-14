package com.stub.generator.controller;

import com.stub.generator.service.RestMockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/mock-rest")
public class RestMockController {

    @Autowired
    private RestMockService restMockService;

    @PostMapping("/{operation}")
    public ResponseEntity<String> handleRestRequest(
            @PathVariable String operation,
            @RequestBody LinkedHashMap<String, Object> requestPayload) {

        // Check for cached response
        String response = restMockService.getCachedResponse(operation, requestPayload);

        if (response == null) {
            // Forward request to external server, get and store response if not cached
            response = restMockService.fetchAndCacheResponse(operation, requestPayload);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{operation}")
    public ResponseEntity<String> getData(
            @PathVariable String operation,
            @RequestBody LinkedHashMap<String, Object> requestPayload) {

        // Check for cached response
        String response = restMockService.getCachedResponse(operation, requestPayload);

        if (response == null) {
            // Forward request to external server, get and store response if not cached
            response = restMockService.fetchAndCacheResponse(operation, requestPayload);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{operation}")
    public ResponseEntity<String> deleteData(
            @PathVariable String operation,
            @RequestBody LinkedHashMap<String, Object> requestPayload) {

        // Check for cached response
        String response = restMockService.getCachedResponse(operation, requestPayload);

        if (response == null) {
            // Forward request to external server, get and store response if not cached
            response = restMockService.fetchAndCacheResponse(operation, requestPayload);
        }

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{operation}")
    public ResponseEntity<String> updateData(
            @PathVariable String operation,
            @RequestBody LinkedHashMap<String, Object> requestPayload) {

        // Check for cached response
        String response = restMockService.getCachedResponse(operation, requestPayload);

        if (response == null) {
            // Forward request to external server, get and store response if not cached
            response = restMockService.fetchAndCacheResponse(operation, requestPayload);
        }

        return ResponseEntity.ok(response);
    }

}
