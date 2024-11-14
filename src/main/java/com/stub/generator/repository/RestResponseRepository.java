package com.stub.generator.repository;

import com.stub.generator.entity.RestApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public interface RestResponseRepository extends JpaRepository<RestApiResponse, Long> {

    @Query("SELECT r.responsePayload FROM RestApiResponse r WHERE r.operation = :operation AND r.requestPayload = :requestPayload")
    String findResponse(@Param("operation") String operation, @Param("requestPayload") LinkedHashMap<String, Object> requestPayload);

    @Transactional
    default void saveCustom(RestApiResponse apiResponse) {
        // Optional: check if the operation and request payload already exist
        String existingResponse = findResponse(apiResponse.getOperation(), apiResponse.getRequestPayload());

        if (existingResponse == null) {
            // Only save if this operation + payload combination isn't in the database
            this.save(apiResponse);
        } else {
            System.out.println("Duplicate request, not saving.");
        }
    }
}

