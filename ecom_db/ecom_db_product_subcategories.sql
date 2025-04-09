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
INSERT INTO `product_subcategories` VALUES (1,1),(2,1),(35,1),(36,1),(69,1),(99,1),(125,1),(3,2),(4,2),(37,2),(38,2),(70,2),(100,2),(126,2),(5,3),(6,3),(71,3),(101,3),(127,3),(7,4),(8,4),(39,4),(40,4),(72,4),(102,4),(128,4),(9,6),(41,6),(73,6),(74,6),(129,6),(130,6),(10,7),(42,7),(11,8),(43,8),(75,8),(105,8),(131,8),(12,9),(44,9),(76,9),(106,9),(132,9),(13,10),(45,10),(77,10),(133,10),(134,10),(14,11),(46,11),(78,11),(107,11),(108,11),(15,12),(47,12),(79,12),(16,13),(48,13),(80,13),(17,14),(49,14),(81,14),(135,14),(18,15),(50,15),(82,15),(136,15),(51,16),(52,17),(109,17),(110,17),(53,18),(19,19),(54,19),(83,19),(111,19),(20,20),(55,20),(84,20),(137,20),(21,21),(85,21),(22,22),(138,22),(56,23),(86,23),(112,23),(23,24),(57,24),(87,24),(113,24),(114,24),(139,24),(140,24),(24,25),(58,25),(88,25),(25,26),(59,26),(89,26),(115,26),(26,27),(60,27),(90,27),(116,27),(27,28),(61,28),(91,28),(117,28),(28,29),(62,29),(92,29),(118,29),(29,30),(63,30),(93,30),(119,30),(30,31),(64,31),(94,31),(120,31),(31,32),(65,32),(32,33),(66,33),(95,33),(96,33),(121,33),(122,33),(33,34),(67,34),(97,34),(34,35),(68,35),(98,35),(123,35),(124,35);
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

-- Dump completed on 2025-04-10  4:11:58
