package com.stub.generator.repository;

import com.stub.generator.model.WsdlSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemaRepository extends JpaRepository<WsdlSchema, Long> {
}