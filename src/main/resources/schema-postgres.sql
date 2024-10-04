DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS statement;
DROP TABLE IF EXISTS files;
DROP TABLE IF EXISTS statement_info;


CREATE TABLE statement_info (
                                id BIGINT NOT NULL UNIQUE PRIMARY KEY,
                                is_ready BOOLEAN,
                                statement_status VARCHAR(255)
);

CREATE TABLE statement (
                            id BIGSERIAL PRIMARY KEY,
                            full_name VARCHAR(255),
                            group_name VARCHAR(255),
                            phone_number VARCHAR(20),
                            faculty TEXT,
                            type_of_statement TEXT,
                            telegram_id BIGINT,
                            year_birthday VARCHAR(10)
);

CREATE TABLE users (
                       user_id BIGSERIAL PRIMARY KEY,
                       user_name VARCHAR(36) NOT NULL,
                       encrypted_password VARCHAR(128) NOT NULL
);

CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(50) NOT NULL
);


CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES users(user_id),
                            FOREIGN KEY (role_id) REFERENCES roles(id),
                            PRIMARY KEY (user_id, role_id)
);

CREATE TABLE files (
                       id SERIAL PRIMARY KEY,
                       file_name VARCHAR(255) NOT NULL,
                       file_type VARCHAR(255) NOT NULL,
                       data BYTEA NOT NULL,
                       statement_id BIGINT UNIQUE,
                       CONSTRAINT fk_statement FOREIGN KEY (statement_id) REFERENCES statement_info(id) ON DELETE CASCADE
);



