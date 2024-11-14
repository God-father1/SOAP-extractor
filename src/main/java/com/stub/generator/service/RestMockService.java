package com.stub.generator.service;

import com.stub.generator.entity.RestApiResponse;
import com.stub.generator.repository.RestResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RestMockService {

    @Autowired
    private RestResponseRepository responseRepository;

    public String getCachedResponse(String operation, LinkedHashMap<String, Object> requestPayload) {
        return responseRepository.findResponse(operation, requestPayload);
    }

    public String fetchAndCacheResponse(String operation, LinkedHashMap<String, Object> requestPayload) {
        // Define the URL of the external service
        String serverUrl = "https://dummyjson.com/posts/"+ operation;

        // Forward the request to the external server
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<LinkedHashMap<String, Object>> requestEntity = new HttpEntity<>(requestPayload);
        ResponseEntity<String> serverResponse = restTemplate.postForEntity(serverUrl, requestEntity, String.class);

        String response = serverResponse.getBody();

        // Store the request and response in the database
        responseRepository.saveCustom(new RestApiResponse(operation, requestPayload, response));

        return response;
    }
}
