package com.stub.generator.controller;
import com.stub.generator.entity.WsdlEndpoint;
import com.stub.generator.service.WsdlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

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
}

