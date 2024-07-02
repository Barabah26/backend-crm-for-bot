INSERT INTO statements (full_name, group_name, phone_number, statement, status, telegram_id, year_entry) VALUES
                                                                                                             ('Іванов Іван Іванович', 'Група А-1', '+380987654321', 'Потрібна допомога з обслуговування апаратури.', false, 1234567890, '2024'),
                                                                                                             ('Петров Петро Петрович', 'Група Б-2', '+380955555555', 'Проблеми з підключенням до мережі.', false, 9876543210, '2023'),
                                                                                                             ('Сидорова Марія Василівна', 'Група В-3', '+380933333333', 'Не працює принтер у кабінеті 305.', false, 6543210987, '2025');
INSERT INTO users (user_name, encrypted_password, enabled)
VALUES ('user', '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq', TRUE),
       ('admin', '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq', TRUE);
INSERT INTO roles (role_name, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2);