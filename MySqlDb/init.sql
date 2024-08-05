CREATE DATABASE  IF NOT EXISTS `customersdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `customersdb`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: customersdb
-- ------------------------------------------------------
-- Server version	8.0.38

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
  `customer_id` varchar(255) NOT NULL,
  `customer_authorization` bit(1) NOT NULL,
  `customer_description` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('69751cfc-0734-44e6-87f6-033267b7c356',_binary '\0','This is Customer 3','Customer 3'),('77424d79-9482-4b42-a30f-bdd8c0131f06',_binary '\0','This is Customer 2','Customer 2'),('847f5abe-2c9c-4c68-8896-678355d6c2ed',_binary '','This is Customer 1','Customer 1'),('d7941816-1159-4b93-a8b0-a00be4fb9487',_binary '','This is Customer 4','Customer 4');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_customer_orders_ids`
--

DROP TABLE IF EXISTS `customer_customer_orders_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_customer_orders_ids` (
  `customer_customer_id` varchar(255) NOT NULL,
  `customer_orders_ids` varchar(255) DEFAULT NULL,
  KEY `FKj2brpw9uxtmwexk8dmarkgha7` (`customer_customer_id`),
  CONSTRAINT `FKj2brpw9uxtmwexk8dmarkgha7` FOREIGN KEY (`customer_customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_customer_orders_ids`
--

LOCK TABLES `customer_customer_orders_ids` WRITE;
/*!40000 ALTER TABLE `customer_customer_orders_ids` DISABLE KEYS */;
INSERT INTO `customer_customer_orders_ids` VALUES ('847f5abe-2c9c-4c68-8896-678355d6c2ed','002267fc-652e-4e0c-b12f-8a2d8dbe8100'),('847f5abe-2c9c-4c68-8896-678355d6c2ed','b2231f39-a2f7-4158-8ba1-9e628da2101d'),('d7941816-1159-4b93-a8b0-a00be4fb9487','5aebed72-26e2-4c38-af90-8b149db5e635'),('d7941816-1159-4b93-a8b0-a00be4fb9487','ab0822ef-212a-44c8-9e43-312ac70c853c');
/*!40000 ALTER TABLE `customer_customer_orders_ids` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-05 14:36:31
CREATE DATABASE  IF NOT EXISTS `ordersdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ordersdb`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: ordersdb
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` varchar(255) NOT NULL,
  `order_customer_id` varchar(255) DEFAULT NULL,
  `order_description` varchar(255) DEFAULT NULL,
  `order_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES ('002267fc-652e-4e0c-b12f-8a2d8dbe8100','847f5abe-2c9c-4c68-8896-678355d6c2ed','This is Order 2','Order 2'),('5aebed72-26e2-4c38-af90-8b149db5e635','d7941816-1159-4b93-a8b0-a00be4fb9487','This is Order 3','Order 3'),('ab0822ef-212a-44c8-9e43-312ac70c853c','d7941816-1159-4b93-a8b0-a00be4fb9487','This is Order 4','Order 4'),('b2231f39-a2f7-4158-8ba1-9e628da2101d','847f5abe-2c9c-4c68-8896-678355d6c2ed','This is Order 1','Order 1');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_order_products_ids`
--

DROP TABLE IF EXISTS `order_order_products_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_order_products_ids` (
  `order_order_id` varchar(255) NOT NULL,
  `order_products_ids` varchar(255) DEFAULT NULL,
  KEY `FK91xk2y082c9xvjqar39vmw47w` (`order_order_id`),
  CONSTRAINT `FK91xk2y082c9xvjqar39vmw47w` FOREIGN KEY (`order_order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_order_products_ids`
--

LOCK TABLES `order_order_products_ids` WRITE;
/*!40000 ALTER TABLE `order_order_products_ids` DISABLE KEYS */;
INSERT INTO `order_order_products_ids` VALUES ('002267fc-652e-4e0c-b12f-8a2d8dbe8100','ef7e649a-cc48-4bcf-8f5c-33579d11040c'),('002267fc-652e-4e0c-b12f-8a2d8dbe8100','dbd75f3b-1529-491d-ae4f-651bbb65c815'),('b2231f39-a2f7-4158-8ba1-9e628da2101d','ef7e649a-cc48-4bcf-8f5c-33579d11040c'),('5aebed72-26e2-4c38-af90-8b149db5e635','ef7e649a-cc48-4bcf-8f5c-33579d11040c'),('5aebed72-26e2-4c38-af90-8b149db5e635','dbd75f3b-1529-491d-ae4f-651bbb65c815'),('5aebed72-26e2-4c38-af90-8b149db5e635','ace77cb7-4d75-4d3f-8e65-71da91a11d9d'),('ab0822ef-212a-44c8-9e43-312ac70c853c','ef7e649a-cc48-4bcf-8f5c-33579d11040c'),('ab0822ef-212a-44c8-9e43-312ac70c853c','dbd75f3b-1529-491d-ae4f-651bbb65c815'),('ab0822ef-212a-44c8-9e43-312ac70c853c','ace77cb7-4d75-4d3f-8e65-71da91a11d9d'),('ab0822ef-212a-44c8-9e43-312ac70c853c','a4430faf-d188-4d3e-ace0-3ac2413b559a');
/*!40000 ALTER TABLE `order_order_products_ids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` varchar(255) NOT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` double NOT NULL,
  `product_stock` int NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('a4430faf-d188-4d3e-ace0-3ac2413b559a','This is Product 4.','Product 4',40,400),('ace77cb7-4d75-4d3f-8e65-71da91a11d9d','This is Product 3.','Product 3',30,300),('dbd75f3b-1529-491d-ae4f-651bbb65c815','This is Product 2.','Product 2',20,200),('ef7e649a-cc48-4bcf-8f5c-33579d11040c','This is Product 1.','Product 1',10,100);
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

-- Dump completed on 2024-08-05 14:36:31
