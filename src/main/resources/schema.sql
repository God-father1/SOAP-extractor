CREATE TABLE wsdl_endpoints (
    id SERIAL PRIMARY KEY,
    endpoint_url VARCHAR(255),
    operation_name VARCHAR(255)
);

CREATE TABLE wsdl_schemas (
    id SERIAL PRIMARY KEY,
    endpoint_id INTEGER,
    input_schema TEXT,
    output_schema TEXT,
    FOREIGN KEY (endpoint_id) REFERENCES wsdl_endpoints(id)
);
