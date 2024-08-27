DROP TABLE IF EXISTS statements_info;
DROP TABLE IF EXISTS statements;
DROP TABLE IF EXISTS users;

CREATE TABLE statements_info (
                                 id BIGSERIAL PRIMARY KEY,
                                 statement_id BIGINT NOT NULL UNIQUE,
                                 is_ready BOOLEAN,
                                 status BOOLEAN,
                                 creation_date TIMESTAMP NULL,
                                 last_modified_date TIMESTAMP NULL
);

CREATE TABLE statements (
                            id BIGSERIAL PRIMARY KEY,
                            full_name VARCHAR(255),
                            group_name VARCHAR(255),
                            phone_number VARCHAR(20),
                            institute TEXT,
                            type_of_statement TEXT,
                            telegram_id BIGINT,
                            year_entry VARCHAR(10)
);

CREATE TABLE users (
                       user_id BIGSERIAL PRIMARY KEY,
                       user_name VARCHAR(36) NOT NULL,
                       encrypted_password VARCHAR(128) NOT NULL
);
