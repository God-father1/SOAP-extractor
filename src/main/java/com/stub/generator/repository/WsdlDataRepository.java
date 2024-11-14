package com.stub.generator.repository;

import com.stub.generator.entity.WsdlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface WsdlDataRepository extends JpaRepository<WsdlData, Long> {
    Optional<WsdlData> findByOperation(String operation);
    Optional<WsdlData> findByEndpointAndOperation(String endpoint, String operation);
    Optional<WsdlData> findByOperationAndHttpMethodAndUrlPath(String operation, String httpMethod, String urlPath);
}
