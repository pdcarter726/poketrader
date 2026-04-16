CREATE DATABASE  IF NOT EXISTS `poketrader` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `poketrader`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: poketrader
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `CardID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `PrimaryType` varchar(255) DEFAULT NULL,
  `SecondaryType` varchar(255) DEFAULT NULL,
  `Grade` int DEFAULT NULL,
  `HP` int DEFAULT NULL,
  `Set` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CardID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `card_move`
--

DROP TABLE IF EXISTS `card_move`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_move` (
  `CardID` int NOT NULL,
  `MoveID` int NOT NULL,
  PRIMARY KEY (`CardID`,`MoveID`),
  KEY `MoveID` (`MoveID`),
  CONSTRAINT `card_move_ibfk_1` FOREIGN KEY (`CardID`) REFERENCES `card` (`CardID`),
  CONSTRAINT `card_move_ibfk_2` FOREIGN KEY (`MoveID`) REFERENCES `move` (`MoveID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection` (
  `CollectionID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CollectionID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `collection_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `collection_card`
--

DROP TABLE IF EXISTS `collection_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection_card` (
  `CollectionID` int NOT NULL,
  `CardID` int NOT NULL,
  PRIMARY KEY (`CollectionID`,`CardID`),
  KEY `CardID` (`CardID`),
  CONSTRAINT `collection_card_ibfk_1` FOREIGN KEY (`CollectionID`) REFERENCES `collection` (`CollectionID`),
  CONSTRAINT `collection_card_ibfk_2` FOREIGN KEY (`CardID`) REFERENCES `card` (`CardID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friendstatus`
--

DROP TABLE IF EXISTS `friendstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friendstatus` (
  `InitiatorUserID` int NOT NULL,
  `ReceiverUserID` int NOT NULL,
  `Status` enum('Request','Accepted','Denied','Blocked') DEFAULT NULL,
  PRIMARY KEY (`InitiatorUserID`,`ReceiverUserID`),
  KEY `ReceiverUserID` (`ReceiverUserID`),
  CONSTRAINT `friendstatus_ibfk_1` FOREIGN KEY (`InitiatorUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `friendstatus_ibfk_2` FOREIGN KEY (`ReceiverUserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `move`
--

DROP TABLE IF EXISTS `move`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `move` (
  `MoveID` int NOT NULL AUTO_INCREMENT,
  `Damage` int DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Element` varchar(20) DEFAULT NULL,
  `EnergyAmnt` int DEFAULT NULL,
  PRIMARY KEY (`MoveID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade` (
  `TradeID` int NOT NULL AUTO_INCREMENT,
  `TradeTime` datetime DEFAULT NULL,
  `InitiatorUserID` int DEFAULT NULL,
  `ReceiverUserID` int DEFAULT NULL,
  `InitiatorCardID` int DEFAULT NULL,
  `ReceiverCardID` int DEFAULT NULL,
  `Status` enum('Request','Accepted','Denied','Blocked') DEFAULT NULL,
  PRIMARY KEY (`TradeID`),
  KEY `InitiatorUserID` (`InitiatorUserID`),
  KEY `ReceiverUserID` (`ReceiverUserID`),
  KEY `InitiatorCardID` (`InitiatorCardID`),
  KEY `ReceiverCardID` (`ReceiverCardID`),
  CONSTRAINT `trade_ibfk_1` FOREIGN KEY (`InitiatorUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`ReceiverUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `trade_ibfk_3` FOREIGN KEY (`InitiatorCardID`) REFERENCES `card` (`CardID`),
  CONSTRAINT `trade_ibfk_4` FOREIGN KEY (`ReceiverCardID`) REFERENCES `card` (`CardID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `TransactionID` int NOT NULL AUTO_INCREMENT,
  `TransactionTime` datetime DEFAULT NULL,
  `SellerUserID` int DEFAULT NULL,
  `BuyerUserID` int DEFAULT NULL,
  `CardID` int DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`TransactionID`),
  KEY `SellerUserID` (`SellerUserID`),
  KEY `BuyerUserID` (`BuyerUserID`),
  KEY `CardID` (`CardID`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`SellerUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`BuyerUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`CardID`) REFERENCES `card` (`CardID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Phone` varchar(10) DEFAULT NULL,
  `PasswordHash` varchar(255) DEFAULT NULL,
  `PasswordSalt` varchar(255) DEFAULT NULL,
  `TwoFAEnabled` tinyint(1) DEFAULT NULL,
  `Verified` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_card`
--

DROP TABLE IF EXISTS `user_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_card` (
  `UserCardID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `CardID` int DEFAULT NULL,
  `CollectionID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`UserCardID`),
  KEY `UserID` (`UserID`),
  KEY `CardID` (`CardID`),
  KEY `CollectionID` (`CollectionID`),
  CONSTRAINT `user_card_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `user_card_ibfk_2` FOREIGN KEY (`CardID`) REFERENCES `card` (`CardID`),
  CONSTRAINT `user_card_ibfk_3` FOREIGN KEY (`CollectionID`) REFERENCES `collection` (`CollectionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-14 13:09:41
