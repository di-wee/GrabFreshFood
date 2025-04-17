-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: grabfreshfood
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `registration_date` datetime NOT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Mei Ling','Tan','meiling.tan@gmail.com','pass123','68878156','Blk 398 Ang Mo Kio Ave 10 #06-511 Singapore 367640','2024-11-25 00:00:00',1,NULL),(2,'Kumar','Ramasamy','kumar.ramasamy@yahoo.com','secure456','85170859','No. 295 Tampines St 9 #07-332 Singapore 634239','2024-11-26 00:00:00',1,NULL),(3,'Ah Meng','Lim','ahmengl@hotmail.com','strong789','88226462','No. 668 Clementi Rd 9 #02-168 Singapore 490319','2024-11-27 00:00:00',1,NULL),(4,'Siti','Abdul','siti.abdul@outlook.com','safe101','80886365','Blk 654 Pasir Ris Dr 1 #03-469 Singapore 263745','2024-11-28 00:00:00',0,NULL),(5,'Jia Wei','Lee','jiawei.lee@gmail.com','pass123','64126953','Blk 299 Jurong West St 6 #08-338 Singapore 179980','2024-11-29 00:00:00',1,NULL),(6,'Anand','Pillai','anand.pillai@yahoo.com','secure456','86188741','No. 499 Toa Payoh Lor 2 #07-766 Singapore 455939','2024-11-30 00:00:00',0,NULL),(7,'Xin Yi','Wong','xinyi.wong@hotmail.com','strong789','65021736','Blk 315 Woodlands Ave 2 #01-302 Singapore 246276','2024-12-01 00:00:00',1,NULL),(8,'Aisha','Rahman','aisha.rahman@outlook.com','safe101','67442853','No. 465 Yishun St 6 #03-150 Singapore 811753','2024-12-02 00:00:00',0,NULL),(9,'Chun Kit','Ng','chunkit.ng@gmail.com','pass123','67996363','Blk 738 Hougang Ave 7 #06-490 Singapore 781591','2024-12-03 00:00:00',1,NULL),(10,'Divya','Nair','divya.nair@yahoo.com','secure456','65186797','No. 140 Bedok North Rd 5 #05-374 Singapore 590145','2024-12-04 00:00:00',0,NULL),(11,'Kai Xuan','Tan','kaixuan.tan@hotmail.com','strong789','85522758','Blk 843 Tampines St 3 #01-618 Singapore 374558','2024-12-05 00:00:00',1,NULL),(12,'Nurul','Aziz','nurul.aziz@outlook.com','safe101','69329384','Blk 615 Pasir Ris Dr 4 #06-274 Singapore 560775','2024-12-06 00:00:00',0,NULL),(13,'Rajesh','Singh','rajesh.singh@gmail.com','pass123','60244896','No. 345 Jurong West St 8 #03-227 Singapore 294561','2024-12-07 00:00:00',1,NULL),(14,'Swee Lan','Goh','sweelan.goh@yahoo.com','secure456','91073989','Blk 733 Clementi Rd 4 #07-613 Singapore 378015','2024-12-08 00:00:00',0,NULL),(15,'Wei Liang','Teo','weiliang.teo@hotmail.com','strong789','68504977','No. 168 Yishun St 2 #02-581 Singapore 753780','2024-12-09 00:00:00',1,NULL),(16,'Zarina','Yusof','zarina.yusof@outlook.com','safe101','87878366','Blk 312 Hougang Ave 5 #06-542 Singapore 516624','2024-12-10 00:00:00',0,NULL),(17,'Boon Kiat','Lim','boonkiat.lim@gmail.com','pass123','93138355','No. 259 Ang Mo Kio Ave 6 #05-737 Singapore 402798','2024-12-11 00:00:00',1,NULL),(18,'Geetha','Rao','geetha.rao@yahoo.com','secure456','83214415','Blk 700 Bedok North Rd 10 #03-681 Singapore 404896','2024-12-12 00:00:00',0,NULL),(19,'Hock Seng','Tan','hockseng.tan@hotmail.com','strong789','65479499','No. 628 Woodlands Ave 7 #02-709 Singapore 739002','2024-12-13 00:00:00',1,NULL),(20,'Ishwarya','Sundaram','ishwarya.sundaram@outlook.com','safe101','67950811','Blk 941 Tampines St 2 #08-159 Singapore 698471','2024-12-14 00:00:00',0,NULL),(21,'Isabella','Rossi','isabella.rossi@gmail.com','pass123','64866491','No. 857 Hougang Ave 9 #07-645 Singapore 706428','2024-12-02 00:00:00',1,''),(22,'Kenji','Tanaka','kenji.tanaka@techcorp.com','secure456','65218993','Blk 773 Toa Payoh Lor 3 #05-251 Singapore 181540','2024-12-02 00:00:00',0,''),(23,'Sophie','Dubois','sophie.dubois@hotmail.com','string789','64908399','No. 115 Pasir Ris Dr 2 #06-491 Singapore 624690','2024-12-02 00:00:00',0,''),(24,'Aisha','Khan','aisha.khan@yahoo.com','password101','85117479','Blk 126 Jurong West St 3 #01-599 Singapore 208273','2024-12-02 00:00:00',1,''),(25,'Diego','Garcia','diego.garcia@gmail.com','secure202','62367248','Blk 947 Bedok North Rd 4 #03-473 Singapore 652429','2024-12-03 00:00:00',1,''),(26,'Elena','Popescu','elena.popescu@outlook.com','string303','66115346','No. 181 Clementi Rd 7 #05-282 Singapore 352087','2024-12-03 00:00:00',1,''),(27,'Min-jun','Park','minjun.park@gmail.com','password404','85927413','Blk 638 Yishun St 3 #07-367 Singapore 738442','2024-12-03 00:00:00',1,''),(28,'Lina','Schmidt','lina.schmidt@companyxde.com','secure505','94719196','No. 430 Ang Mo Kio Ave 5 #06-119 Singapore 144891','2024-12-03 00:00:00',0,''),(29,'Ricardo','Silva','ricardo.silva@gmail.com','pass123','97498516','Blk 932 Tampines St 1 #02-766 Singapore 618337','2024-12-04 00:00:00',1,''),(30,'Fatima','Ali','fatima.ali@hotmail.com','secure456','86201630','No. 583 Woodlands Ave 9 #01-632 Singapore 705486','2024-12-04 00:00:00',0,''),(31,'Oliver','Brown','oliver.brown@gmail.com','string789','69567168','Blk 793 Toa Payoh Lor 1 #03-522 Singapore 472354','2024-12-04 00:00:00',1,''),(32,'Mei Ling','Chen','meiling.chen@yahoo.com','password101','69709781','No. 247 Pasir Ris Dr 3 #07-698 Singapore 514236','2024-12-04 00:00:00',1,''),(33,'Hans','Muller','hans.muller@gmail.com','secure202','67438631','Blk 670 Jurong West St 5 #06-379 Singapore 763284','2024-12-05 00:00:00',0,''),(34,'Yuki','Yamamoto','yuki.yamamoto@gmail.com','string303','80012777','Blk 382 Hougang Ave 8 #05-143 Singapore 659318','2024-12-05 00:00:00',0,''),(35,'Chloe','Leroy','chloe.leroy@outlook.com','password404','66465529','No. 107 Bedok North Rd 1 #02-755 Singapore 622483','2024-12-05 00:00:00',1,''),(36,'Kamal','Singh','kamal.singh@gmail.com','secure505','99100843','Blk 442 Clementi Rd 8 #01-600 Singapore 706022','2024-12-05 00:00:00',1,''),(37,'Antonio','Romero','antonio.romero@gmail.com','pass123','68556993','No. 968 Yishun St 4 #04-167 Singapore 421011','2024-12-06 00:00:00',1,''),(38,'Nadia','Ivanova','nadia.ivanova@yahooru.com','secure456','62493964','Blk 239 Ang Mo Kio Ave 3 #03-563 Singapore 318457','2024-12-06 00:00:00',0,''),(39,'Jian Wei','Li','jianwei.li@gmail.com','string789','83546940','No. 763 Tampines St 8 #05-434 Singapore 662742','2024-12-06 00:00:00',1,''),(40,'Sara','Johansson','sara.johansson@gmail.com','password101','61978597','Blk 811 Woodlands Ave 5 #02-290 Singapore 718590','2024-12-06 00:00:00',0,''),(41,'Luca','Ferrari','luca.ferrari@gmail.com','secure202','69302017','Blk 356 Hougang Ave 2 #01-789 Singapore 312046','2024-12-07 00:00:00',1,''),(42,'Hana','Kim','hana.kim@gmail.com','string303','88457465','No. 458 Toa Payoh Lor 4 #06-480 Singapore 198372','2024-12-07 00:00:00',1,''),(43,'Mathieu','Laurent','mathieu.laurent@gmail.com','password404','64399987','Blk 329 Pasir Ris Dr 5 #04-154 Singapore 527387','2024-12-07 00:00:00',0,''),(44,'Priya','Patel','priya.patel@gmail.com','secure505','66130415','Blk 912 Jurong West St 1 #03-123 Singapore 473928','2024-12-07 00:00:00',1,''),(45,'Gustavo','Almeida','gustavo.almeida@gmail.com','pass123','68817330','No. 754 Bedok North Rd 2 #08-228 Singapore 534000','2024-12-08 00:00:00',1,''),(46,'Olga','Kuznetsova','olga.kuznetsova@gmail.com','secure456','65216957','Blk 411 Clementi Rd 10 #07-650 Singapore 389297','2024-12-08 00:00:00',0,''),(47,'Wei Cheng','Zhang','weicheng.zhang@gmail.com','string789','93751835','No. 672 Yishun St 1 #02-831 Singapore 703644','2024-12-08 00:00:00',0,''),(48,'Ingrid','Svensson','ingrid.svensson@gmail.com','password101','63026469','Blk 103 Ang Mo Kio Ave 7 #05-539 Singapore 372440','2024-12-08 00:00:00',0,''),(49,'Manuel','Lopez','manuel.lopez@gmail.com','secure202','96838030','No. 617 Tampines St 7 #06-770 Singapore 593826','2024-12-09 00:00:00',0,''),(50,'Ji-eun','Lee','jieun.lee@gmail.com','string303','69355556','Blk 714 Woodlands Ave 3 #04-314 Singapore 758947','2024-12-09 00:00:00',1,''),(51,'Marta','Nowak','marta.nowak@gmail.com','pass123','86784431','No. 844 Hougang Ave 6 #01-670 Singapore 683275','2024-12-10 00:00:00',0,''),(52,'Hiroshi','Sato','hiroshi.sato@tech.com','secure456','99896232','Blk 564 Toa Payoh Lor 5 #03-478 Singapore 473688','2024-12-10 00:00:00',0,''),(53,'Isabelle','Lefevre','isabelle.lefevre@hotmail.com','string789','87274811','No. 932 Pasir Ris Dr 1 #02-544 Singapore 515412','2024-12-10 00:00:00',1,''),(54,'Nadia','Rahman','nadia.rahman@yahoo.com','password101','64520693','Blk 715 Jurong West St 2 #07-614 Singapore 722350','2024-12-10 00:00:00',0,''),(55,'Gabriel','Fernandez','gabriel.fernandez@gmail.com','secure202','95119945','No. 217 Bedok North Rd 6 #04-188 Singapore 489178','2024-12-11 00:00:00',0,''),(56,'Irina','Popov','irina.popov@outlook.com','string303','92704313','Blk 503 Clementi Rd 9 #01-349 Singapore 350268','2024-12-11 00:00:00',0,''),(57,'Ji-ho','Kim','jiho.kim@gmail.com','password404','98083104','No. 399 Yishun St 5 #08-312 Singapore 718804','2024-12-11 00:00:00',1,''),(58,'Annette','Weber','annette.weber@companyy.com','secure505','86590148','Blk 162 Ang Mo Kio Ave 9 #05-451 Singapore 689234','2024-12-11 00:00:00',0,''),(59,'Raul','Santos','raul.santos@gmail.com','pass123','66751765','Blk 455 Tampines St 4 #03-730 Singapore 594411','2024-12-12 00:00:00',1,''),(60,'Leila','Hassan','leila.hassan@hotmail.com','secure456','62890187','No. 328 Woodlands Ave 1 #02-107 Singapore 720592','2024-12-12 00:00:00',0,''),(61,'Ethan','Williams','ethan.williams@gmail.com','string789','87547826','Blk 267 Hougang Ave 4 #06-699 Singapore 622387','2024-12-12 00:00:00',1,''),(62,'Xiao Mei','Wang','xiaomei.wang@yahoo.com','password101','80474946','Blk 880 Toa Payoh Lor 8 #01-833 Singapore 564823','2024-12-12 00:00:00',1,''),(63,'Klaus','Becker','klaus.becker@gmail.com','secure202','65338739','No. 688 Pasir Ris Dr 2 #03-645 Singapore 489214','2024-12-13 00:00:00',0,''),(64,'Ayumi','Nakamura','ayumi.nakamura@gmail.com','string303','95446030','Blk 388 Jurong West St 9 #07-200 Singapore 426045','2024-12-13 00:00:00',0,''),(65,'Camille','Roux','camille.roux@outlook.com','password404','62278961','No. 221 Bedok North Rd 3 #04-173 Singapore 593871','2024-12-13 00:00:00',0,''),(66,'Vikram','Kumar','vikram.kumar@gmail.com','secure505','65013287','Blk 998 Clementi Rd 6 #05-141 Singapore 308014','2024-12-13 00:00:00',1,''),(67,'Enrique','Torres','enrique.torres@gmail.com','pass123','96890530','No. 147 Yishun St 8 #02-310 Singapore 687390','2024-12-14 00:00:00',1,''),(68,'Svetlana','Smirnova','svetlana.smirnova@yahooru.com','secure456','95809560','Blk 424 Ang Mo Kio Ave 2 #08-776 Singapore 648362','2024-12-14 00:00:00',1,''),(69,'Li Wei','Chen','liwei.chen@gmail.com','string789','97536852','No. 543 Tampines St 6 #01-493 Singapore 583120','2024-12-14 00:00:00',0,''),(70,'Astrid','Nilsson','astrid.nilsson@gmail.com','password101','62789959','Blk 714 Woodlands Ave 4 #04-318 Singapore 735299','2024-12-14 00:00:00',1,''),(71,'Marco','Conti','marco.conti@gmail.com','secure202','98992616','No. 665 Hougang Ave 1 #03-642 Singapore 704982','2024-12-15 00:00:00',1,''),(72,'So-yeon','Choi','soyeon.choi@gmail.com','string303','98133043','Blk 863 Toa Payoh Lor 2 #05-726 Singapore 415698','2024-12-15 00:00:00',0,''),(73,'Thierry','Moreau','thierry.moreau@gmail.com','password404','95409110','No. 415 Pasir Ris Dr 6 #07-506 Singapore 493019','2024-12-15 00:00:00',1,''),(74,'Deepika','Sharma','deepika.sharma@gmail.com','secure505','93664230','Blk 121 Jurong West St 7 #02-102 Singapore 752183','2024-12-15 00:00:00',0,''),(75,'Carlos','Pereira','carlos.pereira@gmail.com','pass123','66327409','No. 300 Bedok North Rd 8 #06-874 Singapore 514226','2024-12-16 00:00:00',0,''),(76,'Yulia','Kovaleva','yulia.kovaleva@gmail.com','secure456','61560538','Blk 199 Clementi Rd 3 #08-235 Singapore 333967','2024-12-16 00:00:00',1,''),(77,'Ming Hao','Liu','minghao.liu@gmail.com','string789','66384871','No. 826 Yishun St 9 #03-177 Singapore 742603','2024-12-16 00:00:00',0,''),(78,'Solveig','Lundberg','solveig.lundberg@gmail.com','password101','98433135','Blk 329 Ang Mo Kio Ave 8 #01-548 Singapore 408302','2024-12-16 00:00:00',1,''),(79,'Javier','Ruiz','javier.ruiz@gmail.com','secure202','63388597','No. 799 Tampines St 5 #04-335 Singapore 670222','2024-12-17 00:00:00',0,''),(80,'Min-seo','Park','minseo.park@gmail.com','string303','89811835','Blk 402 Woodlands Ave 10 #06-111 Singapore 743200','2024-12-17 00:00:00',1,''),(81,'Anika','Schroeder','anika.schroeder@gmail.com','pass123','66480429','Blk 733 Hougang Ave 3 #08-203 Singapore 622889','2024-12-17 00:00:00',1,''),(82,'Takashi','Kobayashi','takashi.kobayashi@tech.com','secure456','69432110','No. 390 Toa Payoh Lor 6 #02-359 Singapore 413311','2024-12-17 00:00:00',0,''),(83,'Elodie','Girard','elodie.girard@hotmail.com','string789','65824266','Blk 247 Pasir Ris Dr 7 #07-711 Singapore 508726','2024-12-17 00:00:00',0,''),(84,'Rukhsana','Ahmed','rukhsana.ahmed@yahoo.com','password101','61514058','Blk 471 Jurong West St 4 #03-377 Singapore 707866','2024-12-18 00:00:00',0,''),(85,'Federico','Moretti','federico.moretti@gmail.com','secure202','99925148','No. 231 Bedok North Rd 2 #05-508 Singapore 531935','2024-12-18 00:00:00',1,''),(86,'Polina','Kuzmin','polina.kuzmin@outlook.com','string303','64964657','Blk 679 Clementi Rd 5 #06-460 Singapore 379620','2024-12-18 00:00:00',1,''),(87,'Seo-jun','Lee','seojun.lee@gmail.com','password404','68918180','No. 617 Yishun St 6 #08-789 Singapore 747404','2024-12-18 00:00:00',0,''),(88,'Katrin','Richter','katrin.richter@companyz.com','secure505','89918994','Blk 112 Ang Mo Kio Ave 4 #04-381 Singapore 495760','2024-12-18 00:00:00',1,''),(89,'Mateus','Oliveira','mateus.oliveira@gmail.com','pass123','67165088','No. 541 Tampines St 10 #02-264 Singapore 632144','2024-12-19 00:00:00',1,''),(90,'Layla','Youssef','layla.youssef@hotmail.com','secure456','88871660','Blk 798 Woodlands Ave 2 #01-809 Singapore 744222','2024-12-19 00:00:00',0,''),(91,'Owen','Davies','owen.davies@gmail.com','string789','67332700','No. 122 Hougang Ave 10 #03-943 Singapore 672911','2024-12-19 00:00:00',1,''),(92,'Hui Xin','Lim','huixin.lim@yahoo.com','password101','65488112','Blk 334 Toa Payoh Lor 9 #07-487 Singapore 419203','2024-12-19 00:00:00',0,''),(93,'Lukas','Schmidt','lukas.schmidt@gmail.com','secure202','92334225','No. 864 Pasir Ris Dr 8 #06-613 Singapore 508230','2024-12-20 00:00:00',1,''),(94,'Rina','Murakami','rina.murakami@gmail.com','string303','91750200','Blk 933 Jurong West St 6 #05-578 Singapore 712384','2024-12-20 00:00:00',1,''),(95,'Lea','Bernard','lea.bernard@outlook.com','password404','66299860','No. 285 Bedok North Rd 5 #04-256 Singapore 538410','2024-12-20 00:00:00',1,''),(96,'Rajeev','Verma','rajeev.verma@gmail.com','secure505','65754408','Blk 355 Clementi Rd 2 #03-747 Singapore 398534','2024-12-20 00:00:00',1,''),(97,'Santiago','Medina','santiago.medina@gmail.com','pass123','61948299','No. 731 Yishun St 10 #07-468 Singapore 744105','2024-12-21 00:00:00',0,''),(98,'Daria','Kovalev','daria.kovalev@yahoo.com','secure456','85774622','Blk 278 Ang Mo Kio Ave 1 #02-122 Singapore 467702','2024-12-21 00:00:00',1,''),(99,'Zhi Yuan','Huang','zhiyuan.huang@gmail.com','string789','65344733','No. 765 Tampines St 11 #06-605 Singapore 687339','2024-12-21 00:00:00',1,''),(100,'Ebba','Lindgren','ebba.lindgren@gmail.com','password101','68992252','Blk 826 Woodlands Ave 6 #01-330 Singapore 723400','2024-12-21 00:00:00',0,''),(105,'Leonard','Nie','leonardnie@gmail.com','password','83219999','42 Heng Mui Kui Terrace, Singapore 123456','2025-04-17 13:58:56',1,NULL),(106,'Lewis','Huang','huangkaizhenlewis@gmail.com','password','62002000','38 Oxley Road, Singapore 023456','2025-04-17 14:32:09',1,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart_item`
--

DROP TABLE IF EXISTS `shopping_cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart_item` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `cart_id` int NOT NULL,
  `is_checkout` tinyint(1) DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`item_id`),
  KEY `fk_cart_item_product_id_idx` (`product_id`),
  KEY `fk_cart_item_cart_id_idx` (`cart_id`),
  CONSTRAINT `fk_cart_item_cart_id` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_item_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart_item`
--

LOCK TABLES `shopping_cart_item` WRITE;
/*!40000 ALTER TABLE `shopping_cart_item` DISABLE KEYS */;
INSERT INTO `shopping_cart_item` VALUES (1,2,1,1,3),(2,31,2,1,1);
/*!40000 ALTER TABLE `shopping_cart_item` ENABLE KEYS */;
UNLOCK TABLES;

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
  `information` varchar(255) DEFAULT NULL,
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

-- Dump completed on 2025-04-17 16:25:43
