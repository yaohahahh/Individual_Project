-- Database export via SQLPro (https://www.sqlprostudio.com/)
-- Exported by awhite at 31-07-2024 16:10.
-- WARNING: This file may contain descructive statements such as DROPs.
-- Please ensure that you are running the script at the proper location.


-- BEGIN TABLE Category
DROP TABLE IF EXISTS Category;
CREATE TABLE `Category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `institution_id` int NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `institution_id` (`institution_id`),
  CONSTRAINT `institution_id` FOREIGN KEY (`institution_id`) REFERENCES `Institution` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Inserting 40 rows into Category
-- Insert batch #1
INSERT INTO Category (id, name, institution_id, type) VALUES
(73, 'Academics', 17, 'Society'),
(74, 'Engagement', 17, 'Society'),
(75, 'Operations', 17, 'Environment'),
(76, 'Planning & Administration', 17, 'Economy'),
(77, 'Innovation & Leadership', 17, 'Economy'),
(83, 'Academics', 19, 'Society'),
(84, 'Engagement', 19, 'Society'),
(85, 'Operations', 19, 'Environment'),
(86, 'Planning & Administration', 19, 'Economy'),
(87, 'Innovation & Leadership', 19, 'Economy'),
(88, 'Academics', 20, 'Society'),
(89, 'Engagement', 20, 'Society'),
(90, 'Operations', 20, 'Environment'),
(91, 'Planning & Administration', 20, 'Economy'),
(92, 'Innovation & Leadership', 20, 'Economy'),
(93, 'Academics', 21, 'Society'),
(94, 'Engagement', 21, 'Society'),
(95, 'Operations', 21, 'Environment'),
(96, 'Planning & Administration', 21, 'Economy'),
(97, 'Innovation & Leadership', 21, 'Economy'),
(98, 'Academics', 22, 'Society'),
(99, 'Engagement', 22, 'Society'),
(100, 'Operations', 22, 'Environment'),
(101, 'Planning & Administration', 22, 'Economy'),
(102, 'Innovation & Leadership', 22, 'Economy'),
(103, 'Academics', 23, 'Society'),
(104, 'Engagement', 23, 'Society'),
(105, 'Operations', 23, 'Environment'),
(106, 'Planning & Administration', 23, 'Economy'),
(107, 'Innovation & Leadership', 23, 'Economy'),
(108, 'Academics', 24, 'Society'),
(109, 'Engagement', 24, 'Society'),
(110, 'Operations', 24, 'Environment'),
(111, 'Planning & Administration', 24, 'Economy'),
(112, 'Innovation & Leadership', 24, 'Economy'),
(113, 'Academics', 25, 'Society'),
(114, 'Engagement', 25, 'Society'),
(115, 'Operations', 25, 'Environment'),
(116, 'Planning & Administration', 25, 'Economy'),
(117, 'Innovation & Leadership', 25, 'Economy');

-- END TABLE Category

-- BEGIN TABLE Impact_Area
DROP TABLE IF EXISTS Impact_Area;
CREATE TABLE `Impact_Area` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `category_id` int NOT NULL,
  `point` float DEFAULT '0',
  `total_point` float DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Inserting 112 rows into Impact_Area
-- Insert batch #1
INSERT INTO Impact_Area (id, name, category_id, point, total_point) VALUES
(90, 'Curriculum', 73, 27.57, 40),
(91, 'Research', 73, 8, 18),
(92, 'Campus Engagement', 74, 15.67, 21),
(93, 'Public Engagement', 74, 12.89, 20),
(94, 'Buildings & Grounds', 75, 7.86, 20),
(95, 'Energy & Climate', 75, 13, 21),
(96, 'Food & Dining', 75, 3.47, 8),
(97, 'Procurement & Waste', 75, 9.67, 16),
(98, 'Transportation', 75, 5.35, 7),
(99, 'Coordination & Planning', 76, 8.5, 9),
(100, 'Investment', 76, 2.46, 8),
(101, 'Social Equity', 76, 9.12, 10),
(102, 'Wellbeing & Work', 76, 2.25, 7),
(103, 'Innovation & Leadership', 77, 4, 4),
(118, 'Curriculum', 83, 30.11, 40),
(119, 'Research', 83, 11.55, 18),
(120, 'Campus Engagement', 84, 15, 21),
(121, 'Public Engagement', 84, 11.31, 20),
(122, 'Buildings & Grounds', 85, 13.42, 20),
(123, 'Energy & Climate', 85, 13.64, 21),
(124, 'Food & Dining', 85, 2.73, 8),
(125, 'Procurement & Waste', 85, 12.32, 16),
(126, 'Transportation', 85, 5, 7),
(127, 'Coordination & Planning', 86, 5.75, 9),
(128, 'Investment', 86, 8, 8),
(129, 'Social Equity', 86, 3.91, 10),
(130, 'Wellbeing & Work', 86, 4, 7),
(131, 'Innovation & Leadership', 87, 4, 4),
(132, 'Curriculum', 88, 32, 40),
(133, 'Research', 88, 17.88, 18),
(134, 'Campus Engagement', 89, 15.91, 21),
(135, 'Public Engagement', 89, 17.8, 20),
(136, 'Buildings & Grounds', 90, 7.85, 20),
(137, 'Energy & Climate', 90, 13.6, 21),
(138, 'Food & Dining', 90, 2, 8),
(139, 'Procurement & Waste', 90, 9, 16),
(140, 'Transportation', 90, 5.55, 7),
(141, 'Coordination & Planning', 91, 6.75, 9),
(142, 'Investment', 91, 1.42, 8),
(143, 'Social Equity', 91, 8.39, 10),
(144, 'Wellbeing & Work', 91, 3.25, 7),
(145, 'Innovation & Leadership', 92, 4, 4),
(146, 'Curriculum', 93, 39.39, 40),
(147, 'Research', 93, 18, 18),
(148, 'Campus Engagement', 94, 20.51, 21),
(149, 'Public Engagement', 94, 19.2, 20),
(150, 'Buildings & Grounds', 95, 7.49, 20),
(151, 'Energy & Climate', 95, 12.6, 21),
(152, 'Food & Dining', 95, 3, 8),
(153, 'Procurement & Waste', 95, 10.43, 16),
(154, 'Transportation', 95, 4.9, 7),
(155, 'Coordination & Planning', 96, 9, 9),
(156, 'Investment', 96, 4.75, 8),
(157, 'Social Equity', 96, 9.61, 10),
(158, 'Wellbeing & Work', 96, 3.33, 7),
(159, 'Innovation & Leadership', 97, 4, 4),
(160, 'Curriculum', 98, 38, 40),
(161, 'Research', 98, 18, 18),
(162, 'Campus Engagement', 99, 19.76, 21),
(163, 'Public Engagement', 99, 18.72, 20),
(164, 'Buildings & Grounds', 100, 13.4, 20),
(165, 'Energy & Climate', 100, 12.25, 21),
(166, 'Food & Dining', 100, 3.64, 8),
(167, 'Procurement & Waste', 100, 12.35, 16),
(168, 'Transportation', 100, 5.3, 7),
(169, 'Coordination & Planning', 101, 9, 9),
(170, 'Investment', 101, 6.19, 8),
(171, 'Social Equity', 101, 9.44, 10),
(172, 'Wellbeing & Work', 101, 5.59, 7),
(173, 'Innovation & Leadership', 102, 4, 4),
(174, 'Curriculum', 103, 22.11, 40),
(175, 'Research', 103, 16.6, 18),
(176, 'Campus Engagement', 104, 16.33, 21),
(177, 'Public Engagement', 104, 14.2, 20),
(178, 'Buildings & Grounds', 105, 10.65, 20),
(179, 'Energy & Climate', 105, 9.78, 21),
(180, 'Food & Dining', 105, 3.4, 8),
(181, 'Procurement & Waste', 105, 12.96, 16),
(182, 'Transportation', 105, 7, 7),
(183, 'Coordination & Planning', 106, 6.5, 9),
(184, 'Investment', 106, 4.01, 8),
(185, 'Social Equity', 106, 7.77, 10),
(186, 'Wellbeing & Work', 106, 1.35, 7),
(187, 'Innovation & Leadership', 107, 4, 4),
(188, 'Curriculum', 108, 25.26, 40),
(189, 'Research', 108, 17.1, 18),
(190, 'Campus Engagement', 109, 17.46, 21),
(191, 'Public Engagement', 109, 16.77, 20),
(192, 'Buildings & Grounds', 110, 8.53, 20),
(193, 'Energy & Climate', 110, 7.68, 21),
(194, 'Food & Dining', 110, 2.93, 8),
(195, 'Procurement & Waste', 110, 8.73, 16),
(196, 'Transportation', 110, 5.45, 7),
(197, 'Coordination & Planning', 111, 7.88, 9),
(198, 'Investment', 111, 0, 8),
(199, 'Social Equity', 111, 9.5, 10),
(200, 'Wellbeing & Work', 111, 5.04, 7),
(201, 'Innovation & Leadership', 112, 4, 4),
(202, 'Curriculum', 113, 17.9, 40),
(203, 'Research', 113, 15.71, 18),
(204, 'Campus Engagement', 114, 11.85, 21),
(205, 'Public Engagement', 114, 9.74, 20),
(206, 'Buildings & Grounds', 115, 6, 20),
(207, 'Energy & Climate', 115, 9.35, 21),
(208, 'Food & Dining', 115, 1.63, 8),
(209, 'Procurement & Waste', 115, 9.03, 16),
(210, 'Transportation', 115, 5.12, 7),
(211, 'Coordination & Planning', 116, 9, 9),
(212, 'Investment', 116, 2.9, 8),
(213, 'Social Equity', 116, 6.69, 10),
(214, 'Wellbeing & Work', 116, 4.13, 7),
(215, 'Innovation & Leadership', 117, 4, 4);

-- END TABLE Impact_Area

-- BEGIN TABLE Institution
DROP TABLE IF EXISTS Institution;
CREATE TABLE `Institution` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `score` float NOT NULL,
  `rating` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Inserting 8 rows into Institution
-- Insert batch #1
INSERT INTO Institution (id, name, fullName, score, rating, link) VALUES
(17, 'BU', 'Boston University', 66.3, 'Gold', 'https://reports.aashe.org/institutions/boston-university-ma/report/2024-05-31/'),
(19, 'UCLA', 'California State University, Los Angeles', 70.79, 'Gold', 'https://reports.aashe.org/institutions/california-state-university-los-angeles-ca/report/2022-06-20/'),
(20, 'CMU', 'Carnegie Mellon University', 75.9, 'Gold', 'https://reports.aashe.org/institutions/carnegie-mellon-university-pa/report/2021-12-06/'),
(21, 'CU', 'Cornell University', 86.33, 'Platinum', 'https://reports.aashe.org/institutions/cornell-university-ny/report/2024-03-01/'),
(22, 'UCI', 'University of California, Irvine', 88.8, 'Platinum', 'https://reports.aashe.org/institutions/university-of-california-irvine-ca/report/2021-08-11/'),
(23, 'PU', 'Princeton University', 68.42, 'Gold', 'https://reports.aashe.org/institutions/princeton-university-nj/report/2021-08-25/'),
(24, 'MIT', 'Massachusetts Institute of Technology', 69.97, 'Gold', 'https://reports.aashe.org/institutions/massachusetts-institute-of-technology-ma/report/2021-09-30/'),
(25, 'UoS', 'University of Sydney', 70, 'Silver', 'https://reports.aashe.org/institutions/university-of-sydney-nsw/report/2021-07-30/');

-- END TABLE Institution

DROP VIEW IF EXISTS creditdata;
CREATE OR REPLACE VIEW creditdata
AS
select `i`.`name` AS `InstitutionName`,`c`.`name` AS `CategoryName`,`ia`.`name` AS `Impact_AreaName`,`ct`.`name` AS `CreditName`,`ct`.`indicator` AS `indicator`,`ct`.`point` AS `point` from (((`sustainability_data`.`institution` `i` join `sustainability_data`.`category` `c` on((`i`.`id` = `c`.`institution_id`))) join `sustainability_data`.`impact_area` `ia` on((`c`.`id` = `ia`.`category_id`))) join `sustainability_data`.`credit` `ct` on((`ia`.`id` = `ct`.`impact_area_id`)));

