CREATE TABLE IF NOT EXISTS ipp_resource_field_mapper (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    original_str VARCHAR(255) NOT NULL,
    standard_str VARCHAR(255) NOT NULL,
    multiple INTEGER DEFAULT 1,
    field INTEGER NOT NULL,
    is_delete INTEGER DEFAULT 0,
    user_name VARCHAR(50),
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
