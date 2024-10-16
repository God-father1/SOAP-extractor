package com.stub.generator.repository;

import com.stub.generator.model.WsdlEndpoint;
import com.stub.generator.model.WsdlSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface WsdlSchemaRepository extends JpaRepository<WsdlSchema, Long> {
    Optional<WsdlSchema> findByEndpoint(WsdlEndpoint endpoint);
}
