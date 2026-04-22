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
  `Name` varchar(255) NOT NULL,
  `PrimaryType` varchar(255) DEFAULT NULL,
  `SecondaryType` varchar(255) DEFAULT NULL,
  `Grade` int DEFAULT NULL,
  `HP` int DEFAULT NULL,
  `Set` varchar(100) NOT NULL,
  PRIMARY KEY (`CardID`),
  CONSTRAINT `chk_card_primary_type` CHECK (`PrimaryType` IS NULL OR `PrimaryType` IN ('Fire','Water','Grass','Psychic','Lightning','Fighting','Colorless')),
  CONSTRAINT `chk_card_secondary_type` CHECK (`SecondaryType` IS NULL OR `SecondaryType` IN ('Fire','Water','Grass','Psychic','Lightning','Fighting','Colorless')),
  CONSTRAINT `chk_card_grade` CHECK (`Grade` IS NULL OR `Grade` BETWEEN 1 AND 10),
  CONSTRAINT `chk_card_hp` CHECK (`HP` IS NULL OR `HP` > 0)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'Alakazam','Psychic',NULL,NULL,80,'Base'),(2,'Blastoise','Water',NULL,NULL,100,'Base'),(3,'Chansey','Colorless',NULL,NULL,120,'Base'),(4,'Charizard','Fire',NULL,NULL,120,'Base'),(5,'Clefairy','Colorless',NULL,NULL,40,'Base'),(6,'Gyarados','Water',NULL,NULL,100,'Base'),(7,'Hitmonchan','Fighting',NULL,NULL,70,'Base'),(8,'Machamp','Fighting',NULL,NULL,100,'Base'),(9,'Magneton','Lightning',NULL,NULL,60,'Base'),(10,'Mewtwo','Psychic',NULL,NULL,60,'Base'),(11,'Nidoking','Grass',NULL,NULL,90,'Base'),(12,'Ninetales','Fire',NULL,NULL,80,'Base'),(13,'Poliwrath','Water',NULL,NULL,90,'Base'),(14,'Raichu','Lightning',NULL,NULL,80,'Base'),(15,'Venusaur','Grass',NULL,NULL,100,'Base'),(16,'Zapdos','Lightning',NULL,NULL,90,'Base'),(17,'Beedrill','Grass',NULL,NULL,80,'Base'),(18,'Dragonair','Colorless',NULL,NULL,80,'Base'),(19,'Dugtrio','Fighting',NULL,NULL,70,'Base'),(20,'Electabuzz','Lightning',NULL,NULL,70,'Base'),(21,'Electrode','Lightning',NULL,NULL,80,'Base'),(22,'Pidgeotto','Colorless',NULL,NULL,60,'Base'),(23,'Arcanine','Fire',NULL,NULL,100,'Base'),(24,'Charmeleon','Fire',NULL,NULL,80,'Base'),(25,'Dewgong','Water',NULL,NULL,80,'Base'),(26,'Dratini','Colorless',NULL,NULL,40,'Base'),(27,'Farfetch\'d','Colorless',NULL,NULL,50,'Base'),(28,'Growlithe','Fire',NULL,NULL,60,'Base'),(29,'Haunter','Psychic',NULL,NULL,60,'Base'),(30,'Ivysaur','Grass',NULL,NULL,60,'Base'),(31,'Jynx','Psychic',NULL,NULL,70,'Base'),(32,'Kadabra','Psychic',NULL,NULL,60,'Base'),(33,'Kakuna','Grass',NULL,NULL,80,'Base'),(34,'Machoke','Fighting',NULL,NULL,80,'Base'),(35,'Magikarp','Water',NULL,NULL,30,'Base'),(36,'Magmar','Fire',NULL,NULL,50,'Base'),(37,'Nidorino','Grass',NULL,NULL,60,'Base'),(38,'Poliwhirl','Water',NULL,NULL,60,'Base'),(39,'Porygon','Colorless',NULL,NULL,30,'Base'),(40,'Raticate','Colorless',NULL,NULL,60,'Base'),(41,'Seel','Water',NULL,NULL,60,'Base'),(42,'Wartortle','Water',NULL,NULL,70,'Base'),(43,'Abra','Psychic',NULL,NULL,30,'Base'),(44,'Bulbasaur','Grass',NULL,NULL,40,'Base'),(45,'Caterpie','Grass',NULL,NULL,40,'Base'),(46,'Charmander','Fire',NULL,NULL,50,'Base'),(47,'Diglett','Fighting',NULL,NULL,30,'Base'),(48,'Doduo','Colorless',NULL,NULL,50,'Base'),(49,'Drowzee','Psychic',NULL,NULL,50,'Base'),(50,'Gastly','Psychic',NULL,NULL,30,'Base'),(51,'Koffing','Grass',NULL,NULL,50,'Base'),(52,'Machop','Fighting',NULL,NULL,50,'Base'),(53,'Magnemite','Lightning',NULL,NULL,40,'Base'),(54,'Metapod','Grass',NULL,NULL,70,'Base'),(55,'Nidoran M','Grass',NULL,NULL,40,'Base'),(56,'Onix','Fighting',NULL,NULL,90,'Base'),(57,'Pidgey','Colorless',NULL,NULL,40,'Base'),(58,'Pikachu','Lightning',NULL,NULL,40,'Base'),(59,'Poliwag','Water',NULL,NULL,40,'Base'),(60,'Ponyta','Fire',NULL,NULL,40,'Base'),(61,'Rattata','Colorless',NULL,NULL,30,'Base'),(62,'Sandshrew','Fighting',NULL,NULL,40,'Base'),(63,'Squirtle','Water',NULL,NULL,40,'Base'),(64,'Starmie','Water',NULL,NULL,60,'Base'),(65,'Staryu','Water',NULL,NULL,40,'Base'),(66,'Tangela','Grass',NULL,NULL,50,'Base'),(67,'Voltorb','Lightning',NULL,NULL,40,'Base'),(68,'Vulpix','Fire',NULL,NULL,50,'Base'),(69,'Weedle','Grass',NULL,NULL,40,'Base'),(70,'Clefairy Doll',NULL,NULL,NULL,10,'Base'),(71,'Computer Search',NULL,NULL,NULL,NULL,'Base'),(72,'Devolution Spray',NULL,NULL,NULL,NULL,'Base'),(73,'Impostor Professor Oak',NULL,NULL,NULL,NULL,'Base'),(74,'Item Finder',NULL,NULL,NULL,NULL,'Base'),(75,'Lass',NULL,NULL,NULL,NULL,'Base'),(76,'Pokemon Breeder',NULL,NULL,NULL,NULL,'Base'),(77,'Pokemon Trader',NULL,NULL,NULL,NULL,'Base'),(78,'Scoop Up',NULL,NULL,NULL,NULL,'Base'),(79,'Super Energy Removal',NULL,NULL,NULL,NULL,'Base'),(80,'Defender',NULL,NULL,NULL,NULL,'Base'),(81,'Energy Retrieval',NULL,NULL,NULL,NULL,'Base'),(82,'Full Heal',NULL,NULL,NULL,NULL,'Base'),(83,'Maintenance',NULL,NULL,NULL,NULL,'Base'),(84,'PlusPower',NULL,NULL,NULL,NULL,'Base'),(85,'Pokemon Center',NULL,NULL,NULL,NULL,'Base'),(86,'Pokemon Flute',NULL,NULL,NULL,NULL,'Base'),(87,'Pokedex',NULL,NULL,NULL,NULL,'Base'),(88,'Professor Oak',NULL,NULL,NULL,NULL,'Base'),(89,'Revive',NULL,NULL,NULL,NULL,'Base'),(90,'Super Potion',NULL,NULL,NULL,NULL,'Base'),(91,'Bill',NULL,NULL,NULL,NULL,'Base'),(92,'Energy Removal',NULL,NULL,NULL,NULL,'Base'),(93,'Gust of Wind',NULL,NULL,NULL,NULL,'Base'),(94,'Potion',NULL,NULL,NULL,NULL,'Base'),(95,'Switch',NULL,NULL,NULL,NULL,'Base'),(96,'Double Colorless Energy',NULL,NULL,NULL,NULL,'Base'),(97,'Fighting Energy',NULL,NULL,NULL,NULL,'Base'),(98,'Fire Energy',NULL,NULL,NULL,NULL,'Base'),(99,'Grass Energy',NULL,NULL,NULL,NULL,'Base'),(100,'Lightning Energy',NULL,NULL,NULL,NULL,'Base'),(101,'Psychic Energy',NULL,NULL,NULL,NULL,'Base'),(102,'Water Energy',NULL,NULL,NULL,NULL,'Base');
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `card_move`
--

LOCK TABLES `card_move` WRITE;
/*!40000 ALTER TABLE `card_move` DISABLE KEYS */;
INSERT INTO `card_move` VALUES (1,1),(2,2),(3,3),(3,4),(4,5),(5,6),(5,7),(6,8),(25,8),(6,9),(7,10),(52,10),(7,11),(19,11),(8,12),(9,13),(9,14),(10,15),(10,16),(11,17),(11,18),(12,19),(12,20),(13,21),(13,22),(14,23),(14,24),(15,25),(16,26),(16,27),(17,28),(18,28),(17,29),(18,30),(19,31),(20,32),(53,32),(20,33),(21,34),(22,35),(22,36),(23,37),(23,38),(24,39),(27,39),(24,40),(25,41),(26,42),(35,42),(46,42),(49,42),(58,42),(67,42),(27,43),(28,44),(29,45),(29,46),(30,47),(30,48),(66,48),(31,49),(31,50),(32,51),(32,52),(33,53),(33,54),(34,55),(34,56),(35,57),(36,58),(60,58),(36,59),(37,60),(37,61),(38,62),(38,63),(39,64),(39,65),(40,66),(61,66),(40,67),(41,68),(42,69),(42,70),(43,71),(44,72),(45,73),(46,74),(47,75),(56,75),(47,76),(48,77),(49,78),(50,79),(50,80),(51,81),(53,82),(54,83),(54,84),(66,84),(55,85),(56,86),(57,87),(58,88),(59,89),(60,90),(62,91),(63,92),(63,93),(64,94),(64,95),(65,96),(68,97),(69,98);
/*!40000 ALTER TABLE `card_move` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `collection` (
  `CollectionID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CollectionID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `collection_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `collection_card_view`
--

DROP TABLE IF EXISTS `collection_card_view`;
/*!50001 DROP VIEW IF EXISTS `collection_card_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `collection_card_view` AS SELECT 
 1 AS `CollectionID`,
 1 AS `CardID`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `move`
--

DROP TABLE IF EXISTS `move`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `move` (
  `MoveID` int NOT NULL AUTO_INCREMENT,
  `Damage` int DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Element` varchar(20) DEFAULT NULL,
  `EnergyAmnt` int DEFAULT NULL,
  PRIMARY KEY (`MoveID`),
  CONSTRAINT `chk_move_damage` CHECK (`Damage` IS NULL OR `Damage` >= 0),
  CONSTRAINT `chk_move_element` CHECK (`Element` IS NULL OR `Element` IN ('Fire','Water','Grass','Psychic','Lightning','Fighting','Colorless')),
  CONSTRAINT `chk_move_energy` CHECK (`EnergyAmnt` IS NULL OR `EnergyAmnt` >= 0)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `move`
--

LOCK TABLES `move` WRITE;
/*!40000 ALTER TABLE `move` DISABLE KEYS */;
INSERT INTO `move` VALUES (1,30,'Confuse Ray','Flip a coin. If heads, the Defending Pokémon is now Confused.','Psychic',3),(2,40,'Hydro Pump','Does 40 damage plus 10 more damage for each Water Energy attached to Blastoise but not used to pay for this attack\'s Energy cost.','Water',3),(3,NULL,'Scrunch','Flip a coin. If heads, prevent all damage done to Chansey during your opponent\'s next turn.','Colorless',2),(4,80,'Double-edge','Chansey does 80 damage to itself.','Colorless',4),(5,100,'Fire Spin','Discard 2 Energy cards attached to Charizard in order to use this attack.','Fire',4),(6,NULL,'Sing','Flip a coin. If heads, the Defending Pokémon is now Asleep.','Colorless',1),(7,NULL,'Metronome','Choose 1 of the Defending Pokémon\'s attacks. Metronome copies that attack.','Colorless',3),(8,50,'Dragon Rage',NULL,'Water',3),(9,40,'Bubblebeam','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Water',4),(10,20,'Jab',NULL,'Fighting',1),(11,40,'Special Punch',NULL,'Fighting',3),(12,60,'Seismic Toss',NULL,'Fighting',4),(13,30,'Thunder Wave','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Lightning',3),(14,80,'Selfdestruct','Does 20 damage to each Pokémon on each Bench. Magneton does 80 damage to itself.','Lightning',4),(15,10,'Psychic','Does 10 damage plus 10 more damage for each Energy card attached to the Defending Pokémon.','Psychic',2),(16,NULL,'Barrier','Discard 1 Psychic Energy card attached to Mewtwo to prevent all effects of attacks done to Mewtwo during your opponent\'s next turn.','Psychic',2),(17,30,'Thrash','Flip a coin. If heads, this attack does 30 damage plus 10 more damage.','Grass',3),(18,20,'Toxic','The Defending Pokémon is now Poisoned. It now takes 20 Poison damage instead of 10.','Grass',3),(19,NULL,'Lure','If your opponent has any Benched Pokémon, choose 1 of them and switch it with the Active Pokémon.','Colorless',2),(20,80,'Fire Blast','Discard 1 Fire Energy card attached to Ninetales in order to use this attack.','Fire',4),(21,30,'Water Gun','Does 30 damage plus 10 more damage for each Water Energy attached to Poliwrath but not used to pay for this attack\'s Energy cost.','Water',3),(22,40,'Whirlpool','If the Defending Pokémon has any Energy cards attached to it, choose 1 of them and discard it.','Water',4),(23,20,'Agility','Flip a coin. If heads, during your opponent\'s next turn, prevent all effects of attacks, including damage, done to Raichu.','Lightning',3),(24,60,'Thunder','Flip a coin. If tails, Raichu does 30 damage to itself.','Lightning',4),(25,60,'Solarbeam',NULL,'Grass',4),(26,60,'Thunder','Flip a coin. If tails, Zapdos does 30 damage to itself.','Lightning',4),(27,100,'Thunderbolt','Discard all Energy cards attached to Zapdos in order to use this attack.','Lightning',4),(28,30,'Twineedle','Flip 2 coins. This attack does 30 damage times the number of heads.','Colorless',3),(29,40,'Poison Sting','Flip a coin. If heads, the Defending Pokémon is now Poisoned.','Grass',3),(30,20,'Hyper Beam','If the Defending Pokémon has any Energy cards attached to it, choose 1 of them and discard it.','Colorless',4),(31,70,'Earthquake','Does 10 damage to each of your own Benched Pokémon.','Fighting',4),(32,10,'Thundershock','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Lightning',1),(33,30,'Thunderpunch','Flip a coin. If heads, this attack does 30 damage plus 10 more damage; if tails, Electabuzz does 10 damage to itself.','Lightning',2),(34,50,'Electric Shock','Flip a coin. If tails, Electrode does 10 damage to itself.','Lightning',3),(35,20,'Whirlwind','If your opponent has any Benched Pokémon, he or she chooses 1 of them and switches it with the Defending Pokémon.','Colorless',2),(36,NULL,'Mirror Move','If Pidgeotto was attacked last turn, do the final result of that attack on Pidgeotto to the Defending Pokémon.','Colorless',3),(37,50,'Flamethrower','Discard 1 Fire Energy card attached to Arcanine in order to use this attack.','Fire',3),(38,80,'Take Down','Arcanine does 30 damage to itself.','Fire',4),(39,30,'Slash',NULL,'Colorless',3),(40,50,'Flamethrower','Discard 1 Fire Energy card attached to Charmeleon in order to use this attack.','Fire',3),(41,30,'Ice Beam','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Water',4),(42,10,'Pound',NULL,'Colorless',1),(43,30,'Leek Slap','Flip a coin. If tails, this attack does nothing.','Colorless',1),(44,20,'Flare',NULL,'Fire',2),(45,NULL,'Hypnosis','The Defending Pokémon is now Asleep.','Psychic',1),(46,50,'Dream Eater','You can\'t use this attack unless the Defending Pokémon is Asleep.','Psychic',2),(47,30,'Vine Whip',NULL,'Grass',3),(48,20,'Poisonpowder','The Defending Pokémon is now Poisoned.','Grass',3),(49,10,'Doubleslap','Flip 2 coins. This attack does 10 damage times the number of heads.','Psychic',1),(50,20,'Meditate','Does 20 damage plus 10 more damage for each damage counter on the Defending Pokémon.','Psychic',3),(51,NULL,'Recover','Discard 1 Psychic Energy card attached to Kadabra. Remove all damage counters from Kadabra.','Psychic',2),(52,50,'Super Psy',NULL,'Psychic',3),(53,NULL,'Stiffen','Flip a coin. If heads, prevent all damage done to Kakuna during your opponent\'s next turn.','Colorless',2),(54,20,'Poisonpowder','Flip a coin. If heads, the Defending Pokémon is now Poisoned.','Grass',2),(55,50,'Karate Chop','Does 50 damage minus 10 damage for each damage counter on Machoke.','Fighting',3),(56,60,'Submission','Machoke does 20 damage to itself.','Fighting',4),(57,10,'Flail','Does 10 damage times the number of damage counters on Magikarp.','Water',1),(58,30,'Fire Punch',NULL,'Fire',2),(59,50,'Flamethrower','Discard 1 Fire Energy card attached to Magmar in order to use this attack.','Fire',3),(60,30,'Double Kick','Flip 2 coins. This attack does 30 damage times the number of heads.','Grass',3),(61,50,'Horn Drill',NULL,'Grass',4),(62,NULL,'Amnesia','Choose 1 of the Defending Pokémon\'s attacks. That Pokémon can\'t use that attack during your opponent\'s next turn.','Water',2),(63,30,'Doubleslap','Flip 2 coins. This attack does 30 damage times the number of heads.','Water',3),(64,NULL,'Conversion 1','If the Defending Pokémon has a Weakness, you may change it to a type of your choice.','Colorless',1),(65,NULL,'Conversion 2','Change Porygon\'s Resistance to a type of your choice.','Colorless',2),(66,20,'Bite',NULL,'Colorless',1),(67,NULL,'Super Fang','Does damage equal to half the Defending Pokémon\'s remaining HP.','Colorless',3),(68,10,'Headbutt',NULL,'Water',1),(69,NULL,'Withdraw','Flip a coin. If heads, prevent all damage done to Wartortle during your opponent\'s next turn.','Water',2),(70,40,'Bite',NULL,'Water',3),(71,10,'Psyshock','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Psychic',1),(72,20,'Leech Seed','Unless all damage from this attack is prevented, you may remove 1 damage counter from Bulbasaur.','Grass',2),(73,10,'String Shot','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Grass',1),(74,30,'Ember','Discard 1 Fire Energy card attached to Charmander in order to use this attack.','Fire',2),(75,10,'Dig',NULL,'Fighting',1),(76,30,'Mud Slap',NULL,'Fighting',2),(77,10,'Fury Attack','Flip 2 coins. This attack does 10 damage times the number of heads.','Colorless',1),(78,10,'Confuse Ray','Flip a coin. If heads, the Defending Pokémon is now Confused.','Psychic',2),(79,NULL,'Sleeping Gas','Flip a coin. If heads, the Defending Pokémon is now Asleep.','Psychic',1),(80,NULL,'Destiny Bond','Discard 1 Psychic Energy card attached to Gastly. If a Pokémon Knocks Out Gastly during your opponent\'s next turn, Knock Out that Pokémon.','Psychic',2),(81,10,'Foul Gas','Flip a coin. If heads, the Defending Pokémon is now Poisoned; if tails, it is now Confused.','Grass',2),(82,40,'Selfdestruct','Does 10 damage to each Pokémon on each Bench. Magnemite does 40 damage to itself.','Lightning',2),(83,NULL,'Stiffen','Flip a coin. If heads, prevent all damage done to Metapod during your opponent\'s next turn.','Colorless',2),(84,20,'Stun Spore','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Grass',2),(85,30,'Horn Hazard','Flip a coin. If tails, this attack does nothing.','Grass',1),(86,NULL,'Harden','During your opponent\'s next turn, whenever 30 or less damage is done to Onix, prevent that damage.','Fighting',2),(87,10,'Whirlwind','If your opponent has any Benched Pokémon, choose 1 of them and switch it with the Defending Pokémon.','Colorless',2),(88,30,'Thunder Jolt','Flip a coin. If tails, Pikachu does 10 damage to itself.','Lightning',2),(89,10,'Water Gun','Does 10 damage plus 10 more damage for each Water Energy attached to Poliwag.','Water',1),(90,20,'Smash Kick',NULL,'Colorless',2),(91,10,'Sand-attack','If the Defending Pokémon tries to attack during your opponent\'s next turn, your opponent flips a coin. If tails, that attack does nothing.','Fighting',1),(92,10,'Bubble','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Water',1),(93,NULL,'Withdraw','Flip a coin. If heads, prevent all damage done to Squirtle during your opponent\'s next turn.','Water',2),(94,NULL,'Recover','Discard 1 Water Energy card attached to Starmie. Remove all damage counters from Starmie.','Water',2),(95,20,'Star Freeze','Flip a coin. If heads, the Defending Pokémon is now Paralyzed.','Water',3),(96,20,'Slap',NULL,'Water',1),(97,10,'Confuse Ray','Flip a coin. If heads, the Defending Pokémon is now Confused.','Fire',2),(98,10,'Poison Sting','Flip a coin. If heads, the Defending Pokémon is now Poisoned.','Grass',1);
/*!40000 ALTER TABLE `move` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trade`
--

DROP TABLE IF EXISTS `trade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trade` (
  `TradeID` int NOT NULL AUTO_INCREMENT,
  `TradeTime` datetime DEFAULT NULL,
  `InitiatorUserID` int NOT NULL,
  `ReceiverUserID` int NOT NULL,
  `InitiatorCardID` int NOT NULL,
  `ReceiverCardID` int NOT NULL,
  `Status` enum('Request','Accepted','Denied','Blocked') DEFAULT NULL,
  PRIMARY KEY (`TradeID`),
  KEY `InitiatorUserID` (`InitiatorUserID`),
  KEY `ReceiverUserID` (`ReceiverUserID`),
  KEY `InitiatorCardID` (`InitiatorCardID`),
  KEY `ReceiverCardID` (`ReceiverCardID`),
  CONSTRAINT `trade_ibfk_1` FOREIGN KEY (`InitiatorUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `trade_ibfk_2` FOREIGN KEY (`ReceiverUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `trade_ibfk_3` FOREIGN KEY (`InitiatorCardID`) REFERENCES `card` (`CardID`),
  CONSTRAINT `trade_ibfk_4` FOREIGN KEY (`ReceiverCardID`) REFERENCES `card` (`CardID`),
  CONSTRAINT `chk_trade_different_users` CHECK (`InitiatorUserID` <> `ReceiverUserID`),
  CONSTRAINT `chk_trade_different_cards` CHECK (`InitiatorCardID` <> `ReceiverCardID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trade`
--

LOCK TABLES `trade` WRITE;
/*!40000 ALTER TABLE `trade` DISABLE KEYS */;
/*!40000 ALTER TABLE `trade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `TransactionID` int NOT NULL AUTO_INCREMENT,
  `TransactionTime` datetime DEFAULT (NOW()),
  `SellerUserID` int NOT NULL,
  `BuyerUserID` int NOT NULL,
  `CardID` int DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`TransactionID`),
  KEY `SellerUserID` (`SellerUserID`),
  KEY `BuyerUserID` (`BuyerUserID`),
  KEY `CardID` (`CardID`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`SellerUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`BuyerUserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `transaction_ibfk_3` FOREIGN KEY (`CardID`) REFERENCES `card` (`CardID`),
  CONSTRAINT `chk_transaction_different_users` CHECK (`SellerUserID` <> `BuyerUserID`),
  CONSTRAINT `chk_transaction_price` CHECK (`Price` > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Role` enum('User','Admin') NOT NULL DEFAULT 'User',
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `PasswordHash` varchar(255) NOT NULL,
  `PasswordSalt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `uq_user_username` (`Username`),
  CONSTRAINT `chk_user_firstname` CHECK (`FirstName` REGEXP '^[^0-9]+$'),
  CONSTRAINT `chk_user_lastname` CHECK (`LastName` REGEXP '^[^0-9]+$'),
  CONSTRAINT `chk_user_password_length` CHECK (LENGTH(`PasswordHash`) >= 8)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_card`
--

DROP TABLE IF EXISTS `user_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_card` (
  `UserCardID` int NOT NULL AUTO_INCREMENT,
  `UserID` int NOT NULL,
  `CardID` int NOT NULL,
  `CollectionID` int NOT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`UserCardID`),
  UNIQUE KEY `uq_user_card` (`UserID`,`CardID`),
  KEY `UserID` (`UserID`),
  KEY `CardID` (`CardID`),
  KEY `CollectionID` (`CollectionID`),
  CONSTRAINT `user_card_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `user_card_ibfk_2` FOREIGN KEY (`CardID`) REFERENCES `card` (`CardID`),
  CONSTRAINT `user_card_ibfk_3` FOREIGN KEY (`CollectionID`) REFERENCES `collection` (`CollectionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_card`
--

LOCK TABLES `user_card` WRITE;
/*!40000 ALTER TABLE `user_card` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `collection_card_view`
--

/*!50001 DROP VIEW IF EXISTS `collection_card_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb3 */;
/*!50001 SET character_set_results     = utf8mb3 */;
/*!50001 SET collation_connection      = utf8mb3_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `collection_card_view` AS select distinct `user_card`.`CollectionID` AS `CollectionID`,`user_card`.`CardID` AS `CardID` from `user_card` where (`user_card`.`CollectionID` is not null) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-18 19:52:02
