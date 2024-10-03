-- Insert data into roles
INSERT INTO roles (name) VALUES
                             ('USER'),
                             ('ADMIN');

-- Insert data into users
INSERT INTO users (user_name, encrypted_password) VALUES
                                                      ('user', '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq'),
                                                      ('admin', '$2a$10$BXH1wlAJPIMXvjnJTBoRuea4CvZwSs8/Zqz4bDRZBDJ6hxvXoHlqq');

-- Insert data into user_roles
INSERT INTO user_roles (user_id, role_id) VALUES
                                              (1, 1),
                                              (2, 2);

-- Insert data into statement
INSERT INTO statement (full_name, group_name, phone_number, faculty, type_of_statement, telegram_id, year_entry) VALUES
                                                                                                                     ('Барабах Павло Романович', 'КН43с', '+380987654321', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567890, '2021'),
                                                                                                                     ('Кмита Сергій Володимирович', 'КН43с', '+380987654321', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567890, '2021'),
                                                                                                                     ('Петренко Іван Васильович', 'КН43с', '+380987654322', 'Факультет цивільного захисту', 'Довідка для військкомату', 1234567891, '2021'),
                                                                                                                     ('Шевченко Олександр Михайлович', 'КН43с', '+380987654323', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567892, '2021'),
                                                                                                                     ('Сидоренко Ольга Петрівна', 'КН43с', '+380987654324', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567893, '2021'),
                                                                                                                     ('Гнатюк Марія Олексіївна', 'КН43с', '+380987654325', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567894, '2021'),
                                                                                                                     ('Коваленко Андрій Іванович', 'КН43с', '+380987654326', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567895, '2021'),
                                                                                                                     ('Бойко Тетяна Сергіївна', 'КН43с', '+380987654327', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567896, '2021'),
                                                                                                                     ('Мельник Василь Петрович', 'КН43с', '+380987654328', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567897, '2021'),
                                                                                                                     ('Зубко Анна Андріївна', 'КН43с', '+380987654329', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567898, '2021'),
                                                                                                                     ('Литвиненко Дмитро Олександрович', 'КН43с', '+380987654330', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567899, '2021'),
                                                                                                                     ('Павленко Ірина Василівна', 'КН43с', '+380987654331', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567800, '2021'),
                                                                                                                     ('Романенко Юлія Іванівна', 'КН43с', '+380987654332', 'Факультет цивільного захисту', 'Довідка з місця навчання', 1234567801, '2021');

-- Insert data into statement_info
INSERT INTO statement_info (id, is_ready, statement_status) VALUES
                                                                  (1, FALSE, 'PENDING'),
                                                                  (2, FALSE, 'PENDING'),
                                                                  (3, FALSE, 'PENDING'),
                                                                  (4, FALSE, 'PENDING'),
                                                                  (5, FALSE, 'PENDING'),
                                                                  (6, FALSE, 'PENDING'),
                                                                  (7, FALSE, 'PENDING'),
                                                                  (8, FALSE, 'PENDING'),
                                                                  (9, FALSE, 'PENDING'),
                                                                  (10, FALSE, 'PENDING'),
                                                                  (11, FALSE, 'PENDING'),
                                                                  (12, FALSE, 'PENDING'),
                                                                  (13, FALSE, 'PENDING'),
                                                                  (14, FALSE, 'PENDING');