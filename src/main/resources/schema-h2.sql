DROP TABLE IF EXISTS statements;
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS statements_info;

CREATE TABLE statements_info (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 statement_id BIGINT NOT NULL UNIQUE,
                                 is_ready BOOLEAN,
                                 status BOOLEAN,
                                 creation_date TIMESTAMP NULL,
                                 last_modified_date TIMESTAMP NULL
);



CREATE TABLE statements (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            full_name VARCHAR(255),
                            group_name VARCHAR(255),
                            phone_number VARCHAR(20),
                            institute TEXT,
                            type_of_statement TEXT,
                            telegram_id BIGINT,
                            year_entry VARCHAR(10)
);

CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(36) NOT NULL,
                       encrypted_password VARCHAR(128) NOT NULL
);
