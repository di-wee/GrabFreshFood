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
INSERT INTO `subcategories` VALUES (1,'Fruits',1,'A wide variety of fresh fruits like apples, bananas, and oranges.'),(2,'Vegetables',1,'Fresh vegetables including leafy greens, root veggies, and more.'),(3,'Herbs & Spices',1,'Fresh herbs and essential spices for everyday cooking.'),(4,'Milk',2,'Dairy and non-dairy milk options including whole, skim, and almond milk.'),(5,'Cheese',2,'A selection of cheeses including cheddar, mozzarella, and specialty types.'),(6,'Yogurt & Cream',2,'Yogurts and creams for cooking, desserts, and snacks.'),(7,'Eggs',2,'Farm-fresh eggs in various sizes and packaging.'),(8,'Beef',3,'Cuts of beef including steaks, ground beef, and roasts.'),(9,'Pork',3,'Pork options such as chops, bacon, and sausages.'),(10,'Poultry',3,'Chicken and turkey cuts, including whole and processed.'),(11,'Lamb',3,'Various lamb cuts for grilling, roasting, or stewing.'),(12,'Seafood',3,'Fresh and frozen fish, shrimp, and other seafood.'),(13,'Grains & Rice',4,'Whole grains, white and brown rice, quinoa, and more.'),(14,'Canned Goods',4,'Canned vegetables, beans, soups, and ready-to-eat items.'),(15,'Baking Supplies',4,'Flour, sugar, baking soda, and other baking essentials.'),(16,'Condiments & Sauces',4,'Ketchup, mustard, mayonnaise, soy sauce, and more.'),(17,'Spices & Seasoning',4,'Salt, pepper, spice blends, and specialty seasonings.'),(18,'Oils & Vinegars',4,'Cooking oils, olive oil, and a range of vinegars.'),(19,'Juices',5,'Fruit and vegetable juices, both fresh and bottled.'),(20,'Coffee & Tea',5,'Ground coffee, tea bags, and specialty drink mixes.'),(21,'Sodas & Soft Drinks',5,'Carbonated drinks, flavored sodas, and energy drinks.'),(22,'Water',5,'Bottled water, sparkling water, and mineral water.'),(23,'Alcoholic Beverages',5,'Beer, wine, spirits, and other alcoholic drinks.'),(24,'Cleaning Supplies',6,'Household cleaning products like sprays, mops, and wipes.'),(25,'Paper Products',6,'Paper towels, toilet paper, napkins, and tissues.'),(26,'Pest Control',6,'Insecticides, traps, and repellents for home use.'),(27,'Misc',6,'Misc Items');
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

-- Dump completed on 2025-04-14 19:27:53
