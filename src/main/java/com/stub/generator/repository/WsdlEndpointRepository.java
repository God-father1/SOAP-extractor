package com.stub.generator.repository;


import com.stub.generator.model.WsdlEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WsdlEndpointRepository extends JpaRepository<WsdlEndpoint, Long> {
    Optional<WsdlEndpoint> findByEndpointUrlAndOperationName(String endpointUrl, String operationName);
}