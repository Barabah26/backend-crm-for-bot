DROP TABLE IF EXISTS statements;

CREATE TABLE statements (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            full_name VARCHAR(255),
                            group_name VARCHAR(255),
                            phone_number VARCHAR(20),
                            statement TEXT,
                            status BOOLEAN,
                            telegram_id BIGINT,
                            year_entry VARCHAR(10)
);