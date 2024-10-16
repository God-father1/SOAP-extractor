package com.stub.generator.repository;

import com.stub.generator.model.WsdlEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointRepository extends JpaRepository<WsdlEndpoint, Long> {
}
