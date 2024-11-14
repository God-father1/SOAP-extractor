package com.stub.generator.repository;

import com.stub.generator.entity.RequestResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestResponseLogRepository extends JpaRepository<RequestResponseLog, Long> {
    Optional<RequestResponseLog> findByOperation(String operation);
}
