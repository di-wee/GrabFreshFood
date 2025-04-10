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
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categories` (
  `product_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`product_id`,`category_id`),
  KEY `fk_cat_pro_category_idx` (`category_id`),
  CONSTRAINT `fk_cat_pro_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_cat_pro_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,1),(2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(35,1),(36,1),(37,1),(38,1),(39,1),(40,1),(69,1),(70,1),(71,1),(72,1),(99,1),(100,1),(101,1),(102,1),(125,1),(126,1),(127,1),(128,1),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(41,2),(42,2),(43,2),(44,2),(45,2),(46,2),(73,2),(74,2),(75,2),(76,2),(77,2),(78,2),(103,2),(104,2),(105,2),(106,2),(107,2),(108,2),(129,2),(130,2),(131,2),(132,2),(133,2),(134,2),(15,3),(16,3),(17,3),(18,3),(47,3),(48,3),(49,3),(50,3),(51,3),(52,3),(53,3),(79,3),(80,3),(81,3),(82,3),(109,3),(110,3),(135,3),(136,3),(19,4),(20,4),(21,4),(22,4),(23,4),(24,4),(54,4),(55,4),(56,4),(57,4),(58,4),(83,4),(84,4),(85,4),(86,4),(87,4),(88,4),(111,4),(112,4),(113,4),(114,4),(137,4),(138,4),(139,4),(140,4),(25,5),(26,5),(27,5),(28,5),(29,5),(30,5),(59,5),(60,5),(61,5),(62,5),(63,5),(64,5),(89,5),(90,5),(91,5),(92,5),(93,5),(94,5),(115,5),(116,5),(117,5),(118,5),(119,5),(120,5),(31,6),(32,6),(33,6),(34,6),(65,6),(66,6),(67,6),(68,6),(95,6),(96,6),(97,6),(98,6),(121,6),(122,6),(123,6),(124,6);
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
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
