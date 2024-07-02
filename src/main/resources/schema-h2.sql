DROP TABLE IF EXISTS statements;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;


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

CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_name VARCHAR(36) NOT NULL,
                       encrypted_password VARCHAR(128) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

CREATE TABLE roles (
                       role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       role_name VARCHAR(30),
                       user_id BIGINT,
                       FOREIGN KEY (user_id) REFERENCES users(user_id)
);