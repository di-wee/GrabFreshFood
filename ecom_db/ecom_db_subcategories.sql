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
-- Table structure for table `subcategories`
--

DROP TABLE IF EXISTS `subcategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subcategories` (
  `subcategory_id` int NOT NULL AUTO_INCREMENT,
  `subcategory_name` varchar(255) NOT NULL,
  `category_id` int NOT NULL,
  `information` text,
  PRIMARY KEY (`subcategory_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `subcategories_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subcategories`
--

LOCK TABLES `subcategories` WRITE;
/*!40000 ALTER TABLE `subcategories` DISABLE KEYS */;
INSERT INTO `subcategories` VALUES (1,'Berries',1,'Assorted berries like blueberries and strawberries.'),(2,'Citrus',1,'Citrus fruits like oranges and lemons.'),(3,'Tropical',1,'Tropical fruits like mangoes and pineapples.'),(4,'Leafy Greens',1,'Leafy green vegetables.'),(5,'Chillies',1,'Various types of chillies.'),(6,'Fresh Milk',2,'Fresh cow milk.'),(7,'Flavored Milk',2,'Flavored milk like chocolate milk.'),(8,'Chicken Eggs',2,'Standard chicken eggs.'),(9,'Duck Eggs',2,'Duck eggs.'),(10,'Sliced Cheese',2,'Pre-sliced cheese.'),(11,'Specialty Cheese',2,'Specialty cheeses like blue cheese.'),(12,'Chicken Breasts',3,'Chicken breast cuts.'),(13,'Chicken Thighs',3,'Chicken thigh cuts.'),(14,'Minced Pork',3,'Ground pork.'),(15,'Pork Ribs',3,'Pork ribs.'),(16,'Pork Belly',3,'Pork belly cuts.'),(17,'Steaks',3,'Beef steak cuts.'),(18,'Brisket',3,'Beef brisket cuts.'),(19,'Jasmine Rice',4,'Jasmine rice.'),(20,'Brown Rice',4,'Brown rice.'),(21,'Instant Noodles',4,'Packaged instant noodles.'),(22,'Rice Noodles',4,'Rice vermicelli noodles.'),(23,'Pasta',4,'Various pasta types.'),(24,'Green Tea',5,'Green tea varieties.'),(25,'Black Tea',5,'Black tea varieties.'),(26,'Apple Juice',5,'Apple juice.'),(27,'Orange Juice',5,'Orange juice.'),(28,'Latte',5,'Latte coffee.'),(29,'Americano',5,'Americano coffee.'),(30,'Vodka',5,'Vodka.'),(31,'Rum',5,'Rum.'),(32,'Bleach',6,'Laundry bleach.'),(33,'Liquid Detergent',6,'Liquid laundry detergent.'),(34,'Toilet Paper',6,'Toilet paper rolls.'),(35,'Facial Tissues',6,'Facial tissue boxes.');
/*!40000 ALTER TABLE `subcategories` ENABLE KEYS */;
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
