-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: englishtest
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `answers`
--

DROP TABLE IF EXISTS `answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answers` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `familyName` varchar(45) NOT NULL,
  `Perpetual` varchar(45) DEFAULT NULL,
  `InANutshell` varchar(45) DEFAULT NULL,
  `Unutterable` varchar(45) DEFAULT NULL,
  `Ambiguous` varchar(45) DEFAULT NULL,
  `Scrumptuous` varchar(45) DEFAULT NULL,
  `Ample` varchar(45) DEFAULT NULL,
  `Preposterous` varchar(45) DEFAULT NULL,
  `PickedInMap` varchar(45) DEFAULT NULL,
  `HaveABoneToPick` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`,`firstName`,`familyName`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `firstName_UNIQUE` (`firstName`,`familyName`),
  UNIQUE KEY `familyName_UNIQUE` (`familyName`,`firstName`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answers`
--

LOCK TABLES `answers` WRITE;
/*!40000 ALTER TABLE `answers` DISABLE KEYS */;
INSERT INTO `answers` VALUES (22,'Kosta','Vigulear','null','null','null','null','null','Minimal','','null',''),(35,'Andrei','Vigulear','Eternal','Something of small size, amount, or scope','Indescribable','Equivocal','Tasty','Abundant','Insane Absurd Bizarre ','Phone','To argue about something'),(38,'Natalia','Pahomova','Eternal','Something of small size, amount, or scope','Indescribable','Equivocal','null','Minimal','Insane Absurd Bizarre ','Phone','To argue about something important');
/*!40000 ALTER TABLE `answers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-09 17:17:35
