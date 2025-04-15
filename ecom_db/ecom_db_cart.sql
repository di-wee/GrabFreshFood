-- MySQL dump 10.13  Distrib 8.0.41, for macos15 (arm64)
--
-- Host: localhost    Database: ecom_db
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `creation_date` date NOT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `customer_id_UNIQUE` (`customer_id`),
  CONSTRAINT `fk_cart_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1, 1, '2024-11-25'),
(2, 2, '2024-11-26'),
(3, 3, '2024-11-27'),
(4, 4, '2024-11-28'),
(5, 5, '2024-11-29'),
(6, 6, '2024-11-30'),
(7, 7, '2024-12-01'),
(8, 8, '2024-12-02'),
(9, 9, '2024-12-03'),
(10, 10, '2024-12-04'),
(11, 11, '2024-12-05'),
(12, 12, '2024-12-06'),
(13, 13, '2024-12-07'),
(14, 14, '2024-12-08'),
(15, 15, '2024-12-09'),
(16, 16, '2024-12-10'),
(17, 17, '2024-12-11'),
(18, 18, '2024-12-12'),
(19, 19, '2024-12-13'),
(20, 20, '2024-12-14'),
(21, 21, '2024-12-15'),
(22, 22, '2024-12-16'),
(23, 23, '2024-12-17'),
(24, 24, '2024-12-18'),
(25, 25, '2024-12-19'),
(26, 26, '2024-12-20'),
(27, 27, '2024-12-21'),
(28, 28, '2024-12-22'),
(29, 29, '2024-12-23'),
(30, 30, '2024-12-24'),
(31, 31, '2024-12-25'),
(32, 32, '2024-12-26'),
(33, 33, '2024-12-27'),
(34, 34, '2024-12-28'),
(35, 35, '2024-12-29'),
(36, 36, '2024-12-30'),
(37, 37, '2024-12-31'),
(38, 38, '2025-01-01'),
(39, 39, '2025-01-02'),
(40, 40, '2025-01-03'),
(41, 41, '2025-01-04'),
(42, 42, '2025-01-05'),
(43, 43, '2025-01-06'),
(44, 44, '2025-01-07'),
(45, 45, '2025-01-08'),
(46, 46, '2025-01-09'),
(47, 47, '2025-01-10'),
(48, 48, '2025-01-11'),
(49, 49, '2025-01-12'),
(50, 50, '2025-01-13'),
(51, 51, '2025-01-14'),
(52, 52, '2025-01-15'),
(53, 53, '2025-01-16'),
(54, 54, '2025-01-17'),
(55, 55, '2025-01-18'),
(56, 56, '2025-01-19'),
(57, 57, '2025-01-20'),
(58, 58, '2025-01-21'),
(59, 59, '2025-01-22'),
(60, 60, '2025-01-23'),
(61, 61, '2025-01-24'),
(62, 62, '2025-01-25'),
(63, 63, '2025-01-26'),
(64, 64, '2025-01-27'),
(65, 65, '2025-01-28'),
(66, 66, '2025-01-29'),
(67, 67, '2025-01-30'),
(68, 68, '2025-01-31'),
(69, 69, '2025-02-01'),
(70, 70, '2025-02-02'),
(71, 71, '2025-02-03'),
(72, 72, '2025-02-04'),
(73, 73, '2025-02-05'),
(74, 74, '2025-02-06'),
(75, 75, '2025-02-07'),
(76, 76, '2025-02-08'),
(77, 77, '2025-02-09'),
(78, 78, '2025-02-10'),
(79, 79, '2025-02-11'),
(80, 80, '2025-02-12'),
(81, 81, '2025-02-13'),
(82, 82, '2025-02-14'),
(83, 83, '2025-02-15'),
(84, 84, '2025-02-16'),
(85, 85, '2025-02-17'),
(86, 86, '2025-02-18'),
(87, 87, '2025-02-19'),
(88, 88, '2025-02-20'),
(89, 89, '2025-02-21'),
(90, 90, '2025-02-22'),
(91, 91, '2025-02-23'),
(92, 92, '2025-02-24'),
(93, 93, '2025-02-25'),
(94, 94, '2025-02-26'),
(95, 95, '2025-02-27'),
(96, 96, '2025-02-28'),
(97, 97, '2025-03-01'),
(98, 98, '2025-03-02'),
(99, 99, '2025-03-03'),
(100, 100, '2025-03-04');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-10  4:11:58