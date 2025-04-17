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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `information` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `stock_quantity` int NOT NULL,
  `package_size` double NOT NULL,
  `unit` varchar(255) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `sku` varchar(255) NOT NULL,
  `category_id` int DEFAULT NULL,
  `subcategory_id` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `sku_UNIQUE` (`sku`),
  KEY `FKg7f35cuvad1r3yddcvv6cv059` (`category_category_id`),
  KEY `FKhbsk9l3la1xh1aaxk1r4b8i0a` (`sub_category_subcategory_id`),
  CONSTRAINT `FKg7f35cuvad1r3yddcvv6cv059` FOREIGN KEY (`category_category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `FKhbsk9l3la1xh1aaxk1r4b8i0a` FOREIGN KEY (`sub_category_subcategory_id`) REFERENCES `subcategories` (`subcategory_id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES
(1,'Grab Fresh Raspberries','Sweet and tart berries, ideal for snacking, desserts, and smoothies.',5.0,0,250.0,'g','/product_images/1_fresh_raspberries.jpg','10101001',1,1),
(2,'Grab Honeydew Melon','Sweet, juicy melon with a light green flesh, perfect for refreshing snacks.',4.0,2,1.0,'pcs','/product_images/2_honeydew_melon.jpg','10101002',1,1),
(3,'Navel Oranges','Sweet, seedless citrus fruit, rich in vitamin C.',5.5,40,1.0,'kg','/product_images/3_navel_orange.jpg','10101003',1,1),
(4,'Organic Blueberries','Small, sweet berries, rich in antioxidants, often eaten raw or used in baking.',4.5,14,200.0,'g','/product_images/4_organic_blueberries.jpg','10101004',1,1),
(5,'Ripe Mangoes','Tropical fruit with sweet, juicy flesh, often used in smoothies and desserts.',3.0,30,1.0,'pcs','/product_images/5_ripe_mango.jpg','10101005',1,1),
(6,'Seedless Grapes','Sweet, crisp grapes, perfect for snacking and salads.',7.5,70,1.5,'kg','/product_images/6_seedless_grapes.jpg','10101006',1,1),
(7,'Sweet Strawberries','Juicy, sweet berries, used in desserts, jams, and eaten fresh.',6.0,25,500.0,'g','/product_images/7_sweet_strawberries.jpg','10101007',1,1),
(8,'Dole Fresh Caixin','A leafy green vegetable with a slightly bitter taste, used in Asian cuisine.',3.0,90,300.0,'g','/product_images/8_caixin.jpg','10202008',1,2),
(9,'Earthbound Farm Organic Romaine Lettuce','Crisp lettuce, used in salads and sandwiches.',4.5,130,250.0,'g','/product_images/9_romaine_lettuce.jpg','10202009',1,2),
(10,'Fresh Express Baby Spinach','Tender spinach leaves, used in salads and cooked dishes.',3.75,140,200.0,'g','/product_images/10_baby_spinach.jpg','10202010',1,2),
(11,'Frieda''s Napa Cabbage','Mild-flavored cabbage, used in salads, stir-fries, and kimchi.',3.5,100,300.0,'g','/product_images/11_napa_cabbage.jpg','10202011',1,2),
(12,'Gotham Greens Crisp Green Leaf Lettuce','Crisp, mild lettuce, used in salads and sandwiches.',4.0,120,200.0,'g','/product_images/12_lettuce.jpg','10202012',1,2),
(13,'Green Giant Tender Kai Lan','A leafy green vegetable with a slightly bitter taste, used in Asian cooking.',2.75,110,250.0,'g','/product_images/13_kai_lan.jpg','10202013',1,2),
(14,'Melissa''s Baby Bok Choy','Mild-flavored cabbage, used in stir-fries and soups.',2.5,80,250.0,'g','/product_images/14_baby_bok_choy.jpg','10202014',1,2),
(15,'GreenLeaf Sweet Basil','Aromatic herb, used in Italian and Mediterranean cuisine.',2.5,150,50.0,'g','/product_images/15_sweet_basil.jpg','10203015',1,3),
(16,'GardenJoy Fresh Parsley','Fresh herb, used as a garnish and in cooking.',2.75,170,60.0,'g','/product_images/16_fresh_parsley.jpg','10203016',1,3),
(17,'SunHarvest Cinnamon Stick','Aromatic spice, used in baking and cooking.',3.5,160,20.0,'g','/product_images/17_cinnamon_sticks.jpg','10203017',1,3),
(18,'Nature''s Bounty Rosemary','Fragrant herb, used to flavor meats and vegetables.',3.0,135,40.0,'g','/product_images/18_rosemary.jpg','10203018',1,3),
(19,'EarthSprout Thyme','Aromatic herb, used in savory dishes.',2.8,185,55.0,'g','/product_images/19_thyme.jpg','10203019',1,3),
(20,'FarmHouse Full Cream Milk','High-protein milk with a rich, creamy taste.',6.75,150,2.0,'L','/product_images/20_full_cream_milk_2l.jpg','10204020',2,4),
(21,'FarmFresh Organic Fresh Milk','Organic milk, known for its quality and taste.',5.5,140,1.0,'L','/product_images/21_fresh_milk_1l.jpg','10204021',2,4),
(22,'Oatly Oat Milk','Plant-based milk alternative made from oats.',4.25,160,1.0,'L','/product_images/22_oatmilk_1l.jpg','10204022',2,4),
(23,'Pacific Foods Rice Milk','Plant-based milk alternative made from rice.',3.99,170,1.0,'L','/product_images/23_rice_milk.jpg','10204023',2,4),
(24,'Silk Soy Milk','Plant-based milk alternative made from soybeans.',4.5,180,1.0,'L','/product_images/24_soy_milk.jpg','10204024',2,4),
(25,'Aged Smoked Gouda','Semi-hard cheese with a smoky flavor.',9.75,40,200.0,'g','/product_images/25_gouda_cheese.jpg','10205025',2,5),
(26,'Creamy Gorgonzola','Soft, pungent cheese with a blue-veined texture.',8.99,15,150.0,'g','/product_images/26_gorgonzola_cheese.jpg','10205026',2,5),
(27,'Extra Sharp White Cheddar','Hard cheese with a strong, tangy flavor.',9.0,80,200.0,'g','/product_images/27_cheddar_cheese.jpg','10205027',2,5),
(28,'French Double Cream Brie','Soft, creamy cheese with a mild flavor.',12.5,30,200.0,'g','/product_images/28_brie_cheese.jpg','10205028',2,5),
(29,'Fresh Buffalo Mozzarella','Soft, delicate cheese, often used in salads and pizzas.',11.0,60,200.0,'g','/product_images/29_mozzarella_cheese.jpg','10205029',2,5),
(30,'Pepper Jack Cheese with Jalapenos','Semi-hard cheese with a spicy flavor.',7.25,50,200.0,'g','/product_images/30_pepper_jack_cheese.jpg','10205030',2,5),
(31,'Sharp Italian Provolone','Semi-hard cheese with a sharp, tangy flavor.',8.5,70,200.0,'g','/product_images/31_provolone_cheese.jpg','10205031',2,5),
(32,'Breyer''s Natural Vanilla Heavy Cream','Rich cream, used in desserts and cooking.',5.0,210,48.0,'oz','/product_images/32_heavy_cream.jpg','10206032',2,6),
(33,'Dannon Light & Fit Greek Yogurt','Low-calorie Greek yogurt, a source of protein.',1.25,200,5.3,'oz','/product_images/33_greek_yogurt.jpg','10206033',2,6),
(34,'Land O Lakes Whipped Cream','Light, sweet cream, used as a topping.',3.0,20,8.0,'oz','/product_images/34_whipped_cream.jpg','10206034',2,6),
(35,'Yoplait Strawberry Yogurt','Fruit-flavored yogurt, a popular snack.',1.5,190,6.0,'oz','/product_images/35_strawberry_yogurt.jpg','10206035',2,6),
(36,'Classic Chicken Eggs','Standard eggs, used in various cooking and baking applications.',4.5,90,12.0,'pcs','/product_images/36_chicken_eggs_12.jpg','10207036',2,7),
(37,'Free-Range Chicken Eggs','Eggs from chickens raised outdoors.',5.25,100,10.0,'pcs','/product_images/37_free_range_chicken_eggs_10pcs.jpg','10207037',2,7),
(38,'Jumbo Duck Eggs','Larger eggs with a richer flavor than chicken eggs.',8.0,120,6.0,'pcs','/product_images/38_jumbo_duck_eggs_6pcs.jpg','10207038',2,7),
(39,'Organic Brown Eggs','Eggs from organically raised chickens.',6.0,110,12.0,'pcs','/product_images/39_organic_brown_eggs_12pcs.jpg','10207039',2,7),
(40,'Quail Eggs','Small eggs, often used as a delicacy.',8.0,130,18.0,'pcs','/product_images/40_quail_eggs_18pc.jpg','10207040',2,7),
(41,'Allen Brothers Beef Chuck Roast','A cut of beef, suitable for roasting and braising.',20.0,180,1.0,'kg','/product_images/41_beef_chuck_roast.jpg','10308041',3,8),
(42,'Certified Angus Beef Brisket','A cut of beef, often smoked or barbecued.',15.0,180,500.0,'g','/product_images/42_beef_brisket.jpg','10308042',3,8),
(43,'Meyer Natural Angus Beef Steak','High-quality steak, known for its tenderness and flavor.',18.0,150,200.0,'g','/product_images/43_beef_steak.jpg','10308043',3,8),
(44,'Niman Ranch Ground Beef','Ground beef from sustainably raised cattle.',12.0,160,500.0,'g','/product_images/44_ground_beef.jpg','10308044',3,8),
(45,'Omaha Steaks Beef Short Ribs','Tender ribs, often braised or grilled.',22.0,190,750.0,'g','/product_images/45_beef_short_rib.jpg','10308045',3,8),
(46,'Snake River Farms Beef Tenderloin Steak','A very tender cut of beef.',35.0,170,250.0,'g','/product_images/46_beef_tenderloin_steak.jpg','10308046',3,8),
(47,'USDA Prime Ribeye Steak','A high-quality cut of beef, known for its marbling and flavor.',25.0,140,200.0,'g','/product_images/47_ribeye_steak.jpg','10308047',3,8),
(48,'Berkshire Pork Boneless Loin Roast','Lean and tender pork roast.',18.0,50,1.0,'kg','/product_images/48_pork_loin.jpg','10309048',3,9),
(49,'Kurobuta Pork Belly Slices','Rich and flavorful pork belly.',9.5,50,500.0,'g','/product_images/49_pork_belly_slices.jpg','10308049',3,9),
(50,'Niman Ranch Minced Pork','Ground pork from sustainably raised pigs.',6.0,30,300.0,'g','/product_images/50_minced_pork.jpg','10308050',3,9),
(51,'Smithfield Pork Ribs','Ribs from pigs.',8.75,40,500.0,'g','/product_images/51_pork_rib.jpg','10308051',3,9),
(52,'Boneless Chicken Breast Fillets','Lean chicken breast, used in a variety of dishes.',8.5,70,500.0,'g','/product_images/52_chicken_breast_fillet.jpg','10310052',3,10),
(53,'Perdue Chicken Drumsticks','Dark meat chicken, suitable for grilling and roasting.',5.0,80,500.0,'g','/product_images/53_chicken_drumstick.jpg','10308053',3,10),
(54,'Pilgrim''s Pride Chicken Wings','Chicken wings, often fried or baked.',7.5,100,500.0,'g','/product_images/54_chicken_wings.jpg','10308054',3,10),
(55,'Tyson Chicken Thighs','Dark meat chicken, suitable for grilling and roasting.',6.0,90,500.0,'g','/product_images/55_chicken_thigh.jpg','10308055',3,10),
(56,'Australian Lamb Chops','Tender and flavorful lamb chops.',15.5,20,500.0,'g','/product_images/56_lamb_chops.jpg','10311056',3,11),
(57,'Mountain States Lamb Shoulder','A cut of lamb, suitable for roasting and braising.',16.5,25,750.0,'g','/product_images/57_lamb_shoulder.jpg','10308057',3,11),
(58,'Superior Farms Ground Lamb','Ground lamb from sustainably raised sheep.',12.0,200,500.0,'g','/product_images/58_ground_lamb.jpg','10308058',3,11),
(59,'Thomas Farms Lamb Leg Roast','A cut of lamb, suitable for roasting.',22.0,15,1.0,'kg','/product_images/59_lamb_leg_roast.jpg','10308059',3,11),
(60,'Atlantic Cod Fillet','White fish with a mild flavor.',15.5,140,1.0,'lb','/product_images/60_atlantic_cod_fillet.jpg','10312060',3,12),
(61,'Farm-Raised Tilapia Fillet','Mild-flavored white fish.',8.99,130,1.0,'lb','/product_images/61_tilapa_fillet.jpg','10308061',3,12),
(62,'Large Gulf Shrimp, peeled & deveined','Shellfish, used in various cuisines.',14.5,120,1.0,'lb','/product_images/62_gulf_shrimp_peeled.jpg','10308062',3,12),
(63,'Wild-Caught Alaskan Salmon Fillet','Rich and flavorful fish.',18.99,110,1.0,'lb','/product_images/63_salmon_fillet.jpg','10308063',3,12),
(64,'Bob''s Red Mill Quinoa','A gluten-free grain, used in salads and side dishes.',12.0,190,2.0,'kg','/product_images/64_quinoa_2kg.jpg','10413064',4,13),
(65,'Hakubaku Soba Noodles','Japanese noodles made from buckwheat.',5.5,200,500.0,'g','/product_images/65_soba_noodles.jpg','10413065',4,13),
(66,'Indomie Original Instant Noodles','Quick and easy noodles, often seasoned with spices.',3.75,180,5.0,'pcs','/product_images/66_indomie_instant_noodles.jpg','10413066',4,13),
(67,'Nissin Assorted Cup Noodles','Instant noodles in a cup, with a variety of flavors.',6.0,180,6.0,'pcs','/product_images/67_cup_noodles_6packs.jpg','10413067',4,13),
(68,'Tamanishiki Sushi Rice','Short-grain rice, used for sushi.',22.0,20,5.0,'kg','/product_images/68_sushi_rice_5kg.jpg','10413068',4,13),
(69,'Tilda Basmati Rice','Long-grain rice, known for its aroma and flavor.',18.99,150,5.0,'kg','/product_images/69_basmati_rice.jpg','10413069',4,13),
(70,'Uncle Ben''s Brown Rice','Long-grain brown rice.',7.25,160,2.0,'kg','/product_images/70_brown_rice_2kg.jpg','10413070',4,13),
(71,'Goya Black Beans','Canned beans, used in Mexican and Latin American cuisine.',1.1,15,15.0,'oz','/product_images/71_black_beans.jpg','10414071',4,14),
(72,'Green Giant Canned Peas','Canned peas, a common vegetable side dish.',1.0,50,15.0,'oz','/product_images/72_canned_peas.jpg','10414072',4,14),
(73,'Swanson Chicken Broth','Canned broth, used as a base for soups and stews.',2.5,40,32.0,'oz','/product_images/73_chicken_broth.jpg','10414073',4,14),
(74,'Del Monte Corn Kernels','Canned corn, a common vegetable side dish.',1.99,30,15.25,'oz','/product_images/74_corn_kernels.jpg','10414074',4,14),
(75,'Hunts Diced Tomatoes','Canned tomatoes, used in sauces and stews.',1.25,25,14.5,'oz','/product_images/75_diced_tomatoes.jpg','10414075',4,14),
(76,'Gold Medal All-Purpose Flour','Flour, used for baking and cooking.',3.5,60,5.0,'lb','/product_images/76_all_purpose_flour.jpg','10415076',4,15),
(77,'Clabber Girl Baking Powder','Leavening agent, used in baking.',2.25,80,8.0,'oz','/product_images/77_baking_powder.jpg','10415077',4,15),
(78,'Domino Granulated Sugar','Sugar, used in baking and cooking.',3.0,70,44.0,'lb','/product_images/78_granulated_sugar.jpg','10415078',4,15),
(79,'McCormick Vanilla Extract','Flavoring extract, used in baking.',4.0,90,2.0,'oz','/product_images/79_vanilla_extract.jpg','10415079',4,15),
(80,'Heinz Ketchup','Tomato-based condiment.',2.75,100,20.0,'oz','/product_images/80_ketchup.jpg','10416080',4,16),
(81,'Hellmann Mayonnaise','Creamy condiment',3.25,120,15.0,'oz','/product_images/81_mayonnaise.jpg','10416081',4,16),
(82,'French''s Mustard','Yellow mustard condiment.',1.5,110,12.0,'oz','/product_images/82_mustard.jpg','10416082',4,16),
(83,'Kikkoman Soy Sauce','Salty sauce, used in Asian cuisine.',2.0,130,10.0,'oz','/product_images/83_soy_sauce.jpg','10416083',4,16),
(84,'McCormick Black Pepper','Ground pepper, used as a seasoning.',2.5,150,3.0,'oz','/product_images/84_black_pepper.jpg','10417084',4,17),
(85,'McCormick Cinnamon','Ground spice, used in baking and cooking.',3.0,170,2.3,'oz','/product_images/85_cinnamon.jpg','10417085',4,17),
(86,'McCormick Garlic Powder','Ground garlic, used as a seasoning.',2.75,160,3.5,'oz','/product_images/86_garlic_powder.jpg','10417086',4,17),
(87,'Morton Salt','Table salt, used as a seasoning.',1.99,140,26.0,'oz','/product_images/87_salt.jpg','10417087',4,17),
(88,'Bragg Apple Cider Vinegar','Vinegar, used in cooking and dressings.',3.0,120,16.0,'oz','/product_images/88_apple_cider_vinegar.jpg','10418088',4,18),
(89,'Colavita Balsamic Vinegar','Vinegar, used in dressings and marinades.',4.0,80,8.5,'oz','/product_images/89_balsamic_vinegar.jpg','10418089',4,18),
(90,'Bertolli Olive Oil','Cooking oil, used in various cuisines.',6.0,17,16.9,'oz','/product_images/90_olive_oil.jpg','10418090',4,18),
(91,'Crisco Vegetable Oil','Cooking oil, used for frying and baking.',4.5,200,48.0,'oz','/product_images/91_vegetable_oil.jpg','10418091',4,18),
(92,'Goya Guava Nectar','Sweet fruit drink.',3.99,65,1.0,'L','/product_images/92_guava_nectar.jpg','10519092',5,19),
(93,'Minute Maid Orange Juice','Citrus fruit drink.',4.0,85,1.0,'L','/product_images/93_orange_juice.jpg','10519093',5,19),
(94,'Ocean Spray Cranberry Juice','Tart fruit drink.',5.0,45,1.0,'L','/product_images/94_cranberry_juice_1l.jpg','10519094',5,19),
(95,'Real Activ Mango Juice','Sweet fruit drink.',4.25,110,1.0,'L','/product_images/95_mango_juice_1l.jpg','10519095',5,19),
(96,'Simply Grapefruit Juice','Citrus fruit drink.',4.75,200,1.0,'L','/product_images/96_grapefruit_juice.jpg','10519096',5,19),
(97,'Tropicana Apple Juice','Fruit drink.',4.5,7,1.0,'L','/product_images/97_apple_juice.jpg','10519097',5,19),
(98,'Arizona Iced Tea with Lemon','Sweetened tea drink.',2.25,218,250.0,'ml','/product_images/98_ice_lemon_tea_bottle.jpg','10520098',5,20),
(99,'Dunkin'' Original Iced Latte','Coffee drink.',4.0,176,300.0,'ml','/product_images/99_iced_latte.jpg','10520099',5,20),
(100,'Honest Tea Organic Green Tea','Tea drink.',2.75,30,250.0,'ml','/product_images/100_green_tea_bottle.jpg','10520100',5,20),
(101,'Starbucks Iced Coffee','Coffee drink.',4.25,39,270.0,'ml','/product_images/101_iced_coffee_bottle.jpg','10520101',5,20),
(102,'Coca-Cola','Carbonated soft drink.',1.75,150,355.0,'ml','/product_images/102_coca_cola_can.jpg','10521102',5,21),
(103,'Pepsi','Carbonated soft drink.',1.75,100,355.0,'ml','/product_images/103_pepsi_bottle.jpg','10521103',5,21),
(104,'Sprite','Carbonated soft drink.',2.5,130,2.0,'L','/product_images/104_sprite_bottle_2l.jpg','10521104',5,21),
(105,'Aquafina','Bottled water.',1.0,95,600.0,'ml','/product_images/105_aquafina_water_500ml.jpg','10522105',5,22),
(106,'Dasani','Bottled water.',1.25,20,750.0,'ml','/product_images/106_dasani_water_750ml.jpg','10522106',5,22),
(107,'Fiji Water','Bottled water.',2.5,25,350.0,'ml','/product_images/107_fiji_water_500ml.jpg','10522107',5,22),
(108,'Amber Orchard Brandy','Fruit-based spirit.',35.99,20,700.0,'ml','/product_images/108_brandy_700ml.jpg','10523108',5,23),
(109,'Arctic Peak Vodka','Clear distilled spirit.',22.5,4,700.0,'ml','/product_images/109_vodka_1l.jpg','10523109',5,23),
(110,'Citrus Sun Triple Sec','Orange-flavored liqueur.',18.75,100,500.0,'ml','/product_images/110_citrus_sun_500ml.jpg','10523110',5,23),
(111,'Dark Spiced Mariner Rum','Dark rum, often spiced.',26.0,75,750.0,'ml','/product_images/111_rum.jpg','10523111',5,23),
(112,'Highland Agave Tequila','Tequila, made from agave.',39.99,30,750.0,'ml','/product_images/112_tequila_750ml.jpg','10523112',5,23),
(113,'Kentucky Barrel Bourbon','Whiskey, made from corn.',32.0,80,750.0,'ml','/product_images/113_bourbon_750ml.jpg','10523113',5,23),
(114,'Smoky Oak Reserve Whiskey','Whiskey, with a smoky flavor.',45.0,60,750.0,'ml','/product_images/114_whiskey_750ml.jpg','10523114',5,23),
(115,'Wild Juniper Gin','Gin, flavored with juniper berries.',29.5,50,750.0,'ml','/product_images/115_gin_750ml.jpg','10523115',5,23),
(116,'Citrus Burst All-Purpose Cleaner','Cleaning solution, with a citrus scent.',5.25,20,500.0,'ml','/product_images/116_all_purpose_cleaner.jpg','10624116',6,24),
(117,'High Efficiency Laundry Detergent','Cleaning solution for clothes.',8.99,200,2.0,'L','/product_images/117_laundry_detergent_2l.jpg','10624117',6,24),
(118,'Lemon Fresh Dish Soap','Cleaning solution for dishes.',3.75,190,1.0,'L','/product_images/118_lemon_fresh_dish_soap.jpg','10624118',6,24),
(119,'Streak-Free Window Cleaner','Cleaning solution for windows.',4.0,25,750.0,'ml','/product_images/119_window_cleaner_750ml.jpg','10624119',6,24),
(120,'Ultra Strength Bleach','Cleaning solution for disinfecting and whitening.',4.5,180,1.0,'L','/product_images/120_bleach_1l.jpg','10624120',6,24),
(121,'Bounty Kitchen Towels','Paper towels for cleaning.',5.5,30,2.0,'pcs','/product_images/121_bounty_kitchen_towels.jpg','10625121',6,25),
(122,'Brawny Paper Towels','Paper towels for cleaning.',6.0,50,2.0,'pcs','/product_images/122_brawny_paper_towel.jpg','10625122',6,25),
(123,'Charmin Toilet Paper','Toilet paper.',12.0,60,10.0,'pcs','/product_images/123_toilet_paper.jpg','10625123',6,25),
(124,'Kleenex Facial Tissues','Tissues for personal hygiene.',7.0,15,3.0,' pcs','/product_images/124_facial_tissue.jpg','10625124',6,25),
(125,'Vanity Fair Paper Napkins','Napkins for table settings.',4.0,40,100.0,'pcs','/product_images/125_napkins_100pcs.jpg','10625125',6,25),
(126,'Tiger Balm Mosquito Repellent - Aerosol Spray','Spray for repelling mosquitoes.',8.8,90,120.0,'ml','/product_images/126_mosquito_repellent.jpg','10626126',6,26),
(127,'Combat Bait Insecticide - Ant Killer Mini','Bait for killing ants.',7.6,70,12.0,'pcs','/product_images/127_ant_killer.jpg','10626127',6,26),
(128,'Baygon Water-Based Multi Insect Killer','Spray for killing various insects.',8.65,70,600.0,'ml','/product_images/128_insect_killer.jpg','10626128',6,26),
(129,'4-Tier Wire Shelving Unit','Shelving unit for storage.',40.0,110,1.0,'pcs','/product_images/129_shelving_unit.jpg','10627129',6,27),
(130,'Fabric Drawer Organizers','Inserts for organizing drawers.',15.0,120,6.0,'pcs','/product_images/130_drawer_organiser.jpg','10627130',6,27),
(131,'Sterilite Storage Bins','Plastic bins for storage.',25.0,100,4.0,'pcs','/product_images/131_storage_bin.jpg','10627131',6,27);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-15 16:10:22


