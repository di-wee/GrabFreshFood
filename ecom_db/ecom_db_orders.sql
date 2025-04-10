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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `order_date` date NOT NULL,
  `total_amount` double NOT NULL,
  `shipping_address` varchar(255) NOT NULL,
  `payment_method` varchar(255) NOT NULL,
  `order_status` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id_idx` (`customer_id`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,15,'2024-03-10',120,'456 Orchard Rd, #10-02','Credit Card','Shipped'),(2,39,'2024-03-15',85,'789 Kallang Bahru, #02-06','Nets','Delivered'),(3,22,'2024-03-20',150,'456 Clementi Rd, #08-12','Bank Transfer','Processing'),(4,31,'2024-03-25',90,'789 Sengkang East Ave, #12-11','Credit Card','Pending Payment'),(5,19,'2024-03-30',110,'789 Toa Payoh Ctrl, #02-03','Nets','Shipped'),(6,27,'2024-04-02',75,'789 Bedok Reservoir Rd, #03-07','Bank Transfer','Delivered'),(7,33,'2024-04-05',130,'123 Ang Mo Kio Ave 10, #13-08','Credit Card','Processing'),(8,25,'2024-04-08',95,'123 Tampines Ave 1, #09-11','Nets','Pending Payment'),(9,37,'2024-04-12',160,'123 Queenstown Rd, #04-12','Bank Transfer','Shipped'),(10,12,'2024-04-15',80,'1212 Tampines Ave 1, #03-01','Credit Card','Delivered'),(11,29,'2024-04-18',100,'123 Bukit Batok St 25, #07-09','Nets','Processing'),(12,1,'2024-04-21',140,'123 Ang Mo Kio Ave 3, #01-01','Bank Transfer','Pending Payment'),(13,35,'2024-04-24',88,'789 Toa Payoh West, #08-10','Credit Card','Shipped'),(14,23,'2024-04-27',115,'789 Hougang St 21, #15-03','Nets','Delivered'),(15,11,'2024-04-30',92,'789 Toa Payoh Ctrl, #02-03','Bank Transfer','Processing'),(16,30,'2024-05-03',125,'456 Pasir Ris Dr 10, #10-06','Credit Card','Pending Payment'),(17,24,'2024-05-06',105,'101 Jurong East St 13, #06-08','Nets','Shipped'),(18,38,'2024-05-09',155,'456 Geylang East Ave 2, #11-09','Bank Transfer','Delivered'),(19,26,'2024-05-12',98,'456 Woodlands Dr 16, #11-04','Credit Card','Processing'),(20,36,'2024-05-15',135,'101 Bukit Merah Lane 1, #16-05','Nets','Pending Payment');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
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
