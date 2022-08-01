DROP DATABASE IF EXISTS `shop`;

CREATE DATABASE `shop`;

USE `shop`;

CREATE TABLE `brand` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '브랜드 id',
                          `name` varchar(255) NOT NULL COMMENT '브랜드 이름',
                          `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '브랜드 정보 생성시간',
                          `updated_at` datetime COMMENT '브랜드 정보 수정시간',
                          `deleted_at` datetime COMMENT '브랜드 정보 삭제시간',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `name_unique_key` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT '브랜드';


CREATE TABLE `product` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '상품 id',
                            `name` varchar(255) NOT NULL COMMENT '상품 이름',
                            `price` bigint(20) NOT NULL COMMENT '상품 가격',
                            `category_id` bigint(20) NOT NULL COMMENT '카테고리 id',
                            `brand_id` bigint(20) NOT NULL COMMENT '브랜드 id',
                            `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '상품 생성시간',
                            `updated_at` datetime COMMENT '상품 수정시간',
                            `deleted_at` datetime COMMENT '상품 삭제시간',
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `name_unique_key` (`name`),
                            KEY `category_id_index` (`category_id`),
                            KEY `brand_id_index` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='상품';

CREATE TABLE `category` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '카테고리 id',
                             `name` varchar(255) NOT NULL COMMENT '카테고리 이름',
                             `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '카테고리 생성시간',
                             `updated_at` datetime COMMENT '카테고리 수정시간',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT ='카테고리';

-- 브랜드 샘플 데이터 생성
INSERT INTO `brand` (`id`, `name`)
VALUES
(1, 'A'),
(2, 'B'),
(3, 'C'),
(4, 'D'),
(5, 'E'),
(6, 'F'),
(7, 'G'),
(8, 'H'),
(9, 'I');

-- 카테고리 샘플 데이터 생성
INSERT INTO `category` (`id`, `name`)
VALUES
(1, '상의'),
(2, '아우터'),
(3, '바지'),
(4, '스니커즈'),
(5, '가방'),
(6, '모자'),
(7, '양말'),
(8, '액세서리');

-- 상품 샘플 데이터 생성
INSERT INTO `product` (`id`, `name`, `price`, `category_id`, `brand_id`)
VALUES
(1, 'A-상의', 11200, 1, 1),
(2, 'A-아우터', 5500, 2, 1),
(3, 'A-바지', 4200, 3, 1),
(4, 'A-스니커즈', 9000, 4, 1),
(5, 'A-가방', 2000, 5, 1),
(6, 'A-모자', 1700, 6, 1),
(7, 'A-양말', 1800, 7, 1),
(8, 'A-액세서리', 2300, 8, 1),
(9, 'B-상의', 10500, 1, 2),
(10, 'B-아우터', 5900, 2, 2),
(11, 'B-바지', 3800, 3, 2),
(12, 'B-스니커즈', 9100, 4, 2),
(13, 'B-가방', 2100, 5, 2),
(14, 'B-모자', 2000, 6, 2),
(15, 'B-양말', 2000, 7, 2),
(16, 'B-액세서리', 2200, 8, 2),
(17, 'C-상의', 10000, 1, 3),
(18, 'C-아우터', 6200, 2, 3),
(19, 'C-바지', 3300, 3, 3),
(20, 'C-스니커즈', 9200, 4, 3),
(21, 'C-가방', 2200, 5, 3),
(22, 'C-모자', 1900, 6, 3),
(23, 'C-양말', 2200, 7, 3),
(24, 'C-액세서리', 2100, 8, 3),
(25, 'D-상의', 10100, 1, 4),
(26, 'D-아우터', 5100, 2, 4),
(27, 'D-바지', 3000, 3, 4),
(28, 'D-스니커즈', 9500, 4, 4),
(29, 'D-가방', 2500, 5, 4),
(30, 'D-모자', 1500, 6, 4),
(31, 'D-양말', 2400, 7, 4),
(32, 'D-액세서리', 2000, 8, 4),
(33, 'E-상의', 10700, 1, 5),
(34, 'E-아우터', 5000, 2, 5),
(35, 'E-바지', 3800, 3, 5),
(36, 'E-스니커즈', 9900, 4, 5),
(37, 'E-가방', 2300, 5, 5),
(38, 'E-모자', 1800, 6, 5),
(39, 'E-양말', 2100, 7, 5),
(40, 'E-액세서리', 2100, 8, 5),
(41, 'F-상의', 11200, 1, 6),
(42, 'F-아우터', 7200, 2, 6),
(43, 'F-바지', 4000, 3, 6),
(44, 'F-스니커즈', 9300, 4, 6),
(45, 'F-가방', 2100, 5, 6),
(46, 'F-모자', 1600, 6, 6),
(47, 'F-양말', 2300, 7, 6),
(48, 'F-액세서리', 1900, 8, 6),
(49, 'G-상의', 10500, 1, 7),
(50, 'G-아우터', 5800, 2, 7),
(51, 'G-바지', 3900, 3, 7),
(52, 'G-스니커즈', 9000, 4, 7),
(53, 'G-가방', 2200, 5, 7),
(54, 'G-모자', 1700, 6, 7),
(55, 'G-양말', 2100, 7, 7),
(56, 'G-액세서리', 2000, 8, 7),
(57, 'H-상의', 10800, 1, 8),
(58, 'H-아우터', 6300, 2, 8),
(59, 'H-바지', 3100, 3, 8),
(60, 'H-스니커즈', 9700, 4, 8),
(61, 'H-가방', 2100, 5, 8),
(62, 'H-모자', 1600, 6, 8),
(63, 'H-양말', 2000, 7, 8),
(64, 'H-액세서리', 2000, 8, 8),
(65, 'I-상의', 11400, 1, 9),
(66, 'I-아우터', 6700, 2, 9),
(67, 'I-바지', 3200, 3, 9),
(68, 'I-스니커즈', 9500, 4, 9),
(69, 'I-가방', 2400, 5, 9),
(70, 'I-모자', 1700, 6, 9),
(71, 'I-양말', 1700, 7, 9),
(72, 'I-액세서리', 2400, 8, 9);




