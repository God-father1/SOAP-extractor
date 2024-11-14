package com.stub.generator.repository;

import com.stub.generator.entity.WsdlData;
import com.stub.generator.entity.WsdlEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WsdlEndpointRepository extends JpaRepository<WsdlEndpoint, Long> {
    Optional<WsdlData> findByOperation(String operation);
}
