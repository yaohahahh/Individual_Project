-- Database export via SQLPro (https://www.sqlprostudio.com/)
-- Exported by awhite at 31-07-2024 16:28.
-- WARNING: This file may contain descructive statements such as DROPs.
-- Please ensure that you are running the script at the proper location.


-- BEGIN TABLE admin
DROP TABLE IF EXISTS admin;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Inserting 2 rows into admin
-- Insert batch #1
INSERT INTO admin (id, username, password, name, role) VALUES
(1, 'admin', 'admin', 'admin', 'ADMIN'),
(2, '235', '23', '235', 'ADMIN');

-- END TABLE admin

-- BEGIN TABLE student
DROP TABLE IF EXISTS student;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'STUDENT',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Inserting 3 rows into student
-- Insert batch #1
INSERT INTO student (id, username, password, name, email, sex, avatar, role) VALUES
(1, '1', '3', '2', '3', '4', '4', 'STUDENT'),
(2, '2', '3', '2', NULL, NULL, NULL, 'STUDENT'),
(3, '19080223', '123456', '19080223', NULL, NULL, NULL, 'STUDENT');

-- END TABLE student

