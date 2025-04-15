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
-- Table structure for table `product_subcategories`
--

DROP TABLE IF EXISTS `product_subcategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_subcategories` (
  `product_id` int NOT NULL,
  `subcategory_id` int NOT NULL,
  PRIMARY KEY (`product_id`,`subcategory_id`),
  KEY `subcategory_id` (`subcategory_id`),
  CONSTRAINT `product_subcategories_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `product_subcategories_ibfk_2` FOREIGN KEY (`subcategory_id`) REFERENCES `subcategories` (`subcategory_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_subcategories`
--

LOCK TABLES `product_subcategories` WRITE;
/*!40000 ALTER TABLE `product_subcategories` DISABLE KEYS */;
INSERT INTO `product_subcategories` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,3),(16,3),(17,3),(18,3),(19,3),(20,4),(21,4),(22,4),(23,4),(24,4),(25,5),(26,5),(27,5),(28,5),(29,5),(30,5),(31,5),(32,6),(33,6),(34,6),(35,6),(36,7),(37,7),(38,7),(39,7),(40,7),(41,8),(42,8),(43,8),(44,8),(45,8),(46,8),(47,8),(48,9),(49,9),(50,9),(51,9),(52,10),(53,10),(54,10),(55,10),(56,11),(57,11),(58,11),(59,11),(60,12),(61,12),(62,12),(63,12),(64,13),(65,13),(66,13),(67,13),(68,13),(69,13),(70,13),(71,14),(72,14),(73,14),(74,14),(75,14),(76,15),(77,15),(78,15),(79,15),(80,16),(81,16),(82,16),(83,16),(84,17),(85,17),(86,17),(87,17),(88,18),(89,18),(90,18),(91,18),(92,19),(93,19),(94,19),(95,19),(96,19),(97,19),(98,20),(99,20),(100,20),(101,20),(102,21),(103,21),(104,21),(105,22),(106,22),(107,22),(108,23),(109,23),(110,23),(111,23),(112,23),(113,23),(114,23),(115,23),(116,24),(117,24),(118,24),(119,24),(120,24),(121,25),(122,25),(123,25),(124,25),(125,25),(126,26),(127,26),(128,26),(129,27),(130,27),(131,27);
/*!40000 ALTER TABLE `product_subcategories` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-14 19:27:53
