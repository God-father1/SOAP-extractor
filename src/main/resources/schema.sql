CREATE TABLE RequestResponseLog (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    endpoint VARCHAR(255),
    operation VARCHAR(255),
    request_payload TEXT,
    response_payload TEXT,
    schema TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
