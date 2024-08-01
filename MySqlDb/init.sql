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
-- Current Database: `customersdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `customersdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `customersdb`;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` binary(16) NOT NULL,
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
INSERT INTO `customer` VALUES (_binary '˚Ûf\‡FÒé\'ôo9',_binary '\0','This is Customer 2','Customer 2'),(_binary '$ûGj_9B∏¶Ñ<9e\◊g',_binary '','This is Customer 3','Customer 3'),(_binary '%Ñ8{ΩpHå±mYHß≤±M',_binary '','This is Customer 1','Customer 1'),(_binary 'ÿì∏#ÜîOµ\œf≤é\·aˇ',_binary '\0','This is Customer 4','Customer 4');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_customer_orders_ids`
--

DROP TABLE IF EXISTS `customer_customer_orders_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_customer_orders_ids` (
  `customer_customer_id` binary(16) NOT NULL,
  `customer_orders_ids` binary(16) DEFAULT NULL,
  KEY `FKj2brpw9uxtmwexk8dmarkgha7` (`customer_customer_id`),
  CONSTRAINT `FKj2brpw9uxtmwexk8dmarkgha7` FOREIGN KEY (`customer_customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_customer_orders_ids`
--

LOCK TABLES `customer_customer_orders_ids` WRITE;
/*!40000 ALTER TABLE `customer_customer_orders_ids` DISABLE KEYS */;
INSERT INTO `customer_customer_orders_ids` VALUES (_binary '$ûGj_9B∏¶Ñ<9e\◊g',_binary 'Œé≠\ﬂ\∆\”DØÜ\Ê:Tu>%'),(_binary '$ûGj_9B∏¶Ñ<9e\◊g',_binary '.NO≠g˝CMÑ;ºïB:\r\\'),(_binary '%Ñ8{ΩpHå±mYHß≤±M',_binary 'ß\ZwXUOç\Ì\'ÉôéEw'),(_binary '%Ñ8{ΩpHå±mYHß≤±M',_binary '$√Æ˙\ÂKÆâ4∂	\0Zã');
/*!40000 ALTER TABLE `customer_customer_orders_ids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'customersdb'
--

--
-- Dumping routines for database 'customersdb'
--

--
-- Current Database: `ordersdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ordersdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ordersdb`;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` binary(16) NOT NULL,
  `order_customer_id` binary(16) DEFAULT NULL,
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
INSERT INTO `order` VALUES (_binary '$√Æ˙\ÂKÆâ4∂	\0Zã',_binary '%Ñ8{ΩpHå±mYHß≤±M','This is Order 1','Order 1'),(_binary '.NO≠g˝CMÑ;ºïB:\r\\',_binary '$ûGj_9B∏¶Ñ<9e\◊g','This is Order 4','Order 4'),(_binary 'ß\ZwXUOç\Ì\'ÉôéEw',_binary '%Ñ8{ΩpHå±mYHß≤±M','This is Order 2','Order 2'),(_binary 'Œé≠\ﬂ\∆\”DØÜ\Ê:Tu>%',_binary '$ûGj_9B∏¶Ñ<9e\◊g','This is Order 3','Order 3');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_order_products_ids`
--

DROP TABLE IF EXISTS `order_order_products_ids`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_order_products_ids` (
  `order_order_id` binary(16) NOT NULL,
  `order_products_ids` binary(16) DEFAULT NULL,
  KEY `FK91xk2y082c9xvjqar39vmw47w` (`order_order_id`),
  CONSTRAINT `FK91xk2y082c9xvjqar39vmw47w` FOREIGN KEY (`order_order_id`) REFERENCES `order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_order_products_ids`
--

LOCK TABLES `order_order_products_ids` WRITE;
/*!40000 ALTER TABLE `order_order_products_ids` DISABLE KEYS */;
INSERT INTO `order_order_products_ids` VALUES (_binary 'ß\ZwXUOç\Ì\'ÉôéEw',_binary '7\‚Q^/E◊ü(™EOs?ñ'),(_binary 'ß\ZwXUOç\Ì\'ÉôéEw',_binary ' ∞\≈Õî\»B&Ñè@oçl7'),(_binary 'Œé≠\ﬂ\∆\”DØÜ\Ê:Tu>%',_binary '7\‚Q^/E◊ü(™EOs?ñ'),(_binary 'Œé≠\ﬂ\∆\”DØÜ\Ê:Tu>%',_binary ' ∞\≈Õî\»B&Ñè@oçl7'),(_binary 'Œé≠\ﬂ\∆\”DØÜ\Ê:Tu>%',_binary ')\Z£3\ÃG\Î¨5ô\…Û?¡'),(_binary '.NO≠g˝CMÑ;ºïB:\r\\',_binary '7\‚Q^/E◊ü(™EOs?ñ'),(_binary '.NO≠g˝CMÑ;ºïB:\r\\',_binary ' ∞\≈Õî\»B&Ñè@oçl7'),(_binary '.NO≠g˝CMÑ;ºïB:\r\\',_binary ')\Z£3\ÃG\Î¨5ô\…Û?¡'),(_binary '.NO≠g˝CMÑ;ºïB:\r\\',_binary 'âæïı≠\rA:∫∞…°˚ª\Ï¶'),(_binary '$√Æ˙\ÂKÆâ4∂	\0Zã',_binary '7\‚Q^/E◊ü(™EOs?ñ');
/*!40000 ALTER TABLE `order_order_products_ids` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` binary(16) NOT NULL,
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
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'ordersdb'
--

--
-- Dumping routines for database 'ordersdb'
--

--
-- Current Database: `productsdb`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `productsdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `productsdb`;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` binary(16) NOT NULL,
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
INSERT INTO `product` VALUES (_binary ')\Z£3\ÃG\Î¨5ô\…Û?¡','This is Product 3','Product 3',30,300),(_binary '7\‚Q^/E◊ü(™EOs?ñ','This is Product 1','Product 1',10,100),(_binary 'âæïı≠\rA:∫∞…°˚ª\Ï¶','This is Product 4','Product 4',40,400),(_binary ' ∞\≈Õî\»B&Ñè@oçl7','This is Product 2','Product 2',20,200);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'productsdb'
--

--
-- Dumping routines for database 'productsdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-01 13:24:22
