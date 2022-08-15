DROP DATABASE IF EXISTS `k-pac`;
CREATE DATABASE `k-pac` /*!40100 DEFAULT CHARACTER SET utf8mb3 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `k-pac`;

DROP TABLE IF EXISTS `k_pac_sets`;
CREATE TABLE `k_pac_sets` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `title` varchar(250) COLLATE utf8_bin DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `k_pacs`;
CREATE TABLE `k_pacs` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `creation_date` datetime(6) DEFAULT NULL,
                          `description` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
                          `title` varchar(250) COLLATE utf8_bin DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

DROP TABLE IF EXISTS `k_pac_sets_k_pacs`;
CREATE TABLE `k_pac_sets_k_pacs` (
                                     `k_pac_set_id` bigint NOT NULL,
                                     `k_pac_id` bigint NOT NULL,
                                     PRIMARY KEY (`k_pac_set_id`,`k_pac_id`),
                                     KEY `FKlb47vvgm56djrn9vtctka8c9m` (`k_pac_id`),
                                     CONSTRAINT `FK87i4rphh2blimw9608kwckeri` FOREIGN KEY (`k_pac_set_id`) REFERENCES `k_pac_sets` (`id`),
                                     CONSTRAINT `FKlb47vvgm56djrn9vtctka8c9m` FOREIGN KEY (`k_pac_id`) REFERENCES `k_pacs` (`id`)
) ENGINE=InnoDB;
