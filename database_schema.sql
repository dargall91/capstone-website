CREATE DATABASE  IF NOT EXISTS `alert_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `alert_db`;
-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: alert_db
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.22.04.2

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
-- Table structure for table `cmac_alert_text`
--

DROP TABLE IF EXISTS `cmac_alert_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmac_alert_text` (
  `CMACMessageNumber` int NOT NULL,
  `CMACCapIdentifier` varchar(150) NOT NULL,
  `CMACLanguage` varchar(20) NOT NULL,
  `CMACShortMessage` varchar(200) NOT NULL,
  `CMACLongMessage` varchar(2000) DEFAULT NULL,
  KEY `CMACMessageNumber` (`CMACMessageNumber`,`CMACCapIdentifier`),
  CONSTRAINT `cmac_alert_text_ibfk_1` FOREIGN KEY (`CMACMessageNumber`, `CMACCapIdentifier`) REFERENCES `cmac_message` (`CMACMessageNumber`, `CMACCapIdentifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmac_area_description`
--

DROP TABLE IF EXISTS `cmac_area_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmac_area_description` (
  `CMACMessageNumber` int NOT NULL,
  `CMACCapIdentifier` varchar(180) NOT NULL,
  `AreaNames` varchar(500) NOT NULL,
  `CMASGeocodes` varchar(20) NOT NULL,
  `SAME` varchar(45) NOT NULL,
  `CMACPolygon` varchar(2000) DEFAULT NULL,
  `CMACCircle` varchar(2000) DEFAULT NULL,
  KEY `CMACMessageNumber` (`CMACMessageNumber`,`CMACCapIdentifier`),
  CONSTRAINT `cmac_area_description_ibfk_1` FOREIGN KEY (`CMACMessageNumber`, `CMACCapIdentifier`) REFERENCES `cmac_message` (`CMACMessageNumber`, `CMACCapIdentifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmac_circle_coordinates`
--

DROP TABLE IF EXISTS `cmac_circle_coordinates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmac_circle_coordinates` (
  `CMACMessageNumber` int NOT NULL,
  `CMACCapIdentifier` varchar(180) NOT NULL,
  `Latitude` decimal(5,2) NOT NULL,
  `Longitude` decimal(5,2) NOT NULL,
  `AreaId` int NOT NULL,
  KEY `CMACMessageNumber` (`CMACMessageNumber`,`CMACCapIdentifier`),
  CONSTRAINT `cmac_circle_coordinates_ibfk_1` FOREIGN KEY (`CMACMessageNumber`, `CMACCapIdentifier`) REFERENCES `cmac_message` (`CMACMessageNumber`, `CMACCapIdentifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmac_message`
--

DROP TABLE IF EXISTS `cmac_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmac_message` (
  `CMACMessageNumber` int NOT NULL AUTO_INCREMENT,
  `CMACCapIdentifier` varchar(180) NOT NULL,
  `CMACSender` varchar(180) NOT NULL,
  `CMACDateTime` datetime NOT NULL,
  `CMACStatus` varchar(20) NOT NULL,
  `CMACMessageType` varchar(20) NOT NULL,
  `CMACSenderName` varchar(180) NOT NULL,
  `CMACExpiresDateTime` datetime NOT NULL,
  `CMACCategory` varchar(20) NOT NULL,
  `CMACSeverity` varchar(20) NOT NULL,
  `CMACUrgency` varchar(20) NOT NULL,
  `CMACCertainty` varchar(20) NOT NULL,
  `CMACReferencedCapIdentifier` varchar(180) DEFAULT NULL,
  PRIMARY KEY (`CMACMessageNumber`,`CMACCapIdentifier`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmac_polygon_coordinates`
--

DROP TABLE IF EXISTS `cmac_polygon_coordinates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmac_polygon_coordinates` (
  `CMACMessageNumber` int NOT NULL,
  `CMACCapIdentifier` varchar(180) NOT NULL,
  `Latitude` decimal(5,2) NOT NULL,
  `Longitude` decimal(5,2) NOT NULL,
  `AreaId` int NOT NULL,
  KEY `CMACMessageNumber` (`CMACMessageNumber`,`CMACCapIdentifier`),
  CONSTRAINT `cmac_polygon_coordinates_ibfk_1` FOREIGN KEY (`CMACMessageNumber`, `CMACCapIdentifier`) REFERENCES `cmac_message` (`CMACMessageNumber`, `CMACCapIdentifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `device_upload_data`
--

DROP TABLE IF EXISTS `device_upload_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device_upload_data` (
  `CMACMessageNumber` int NOT NULL,
  `CMACCapIdentifier` varchar(180) NOT NULL,
  `UploadID` int NOT NULL AUTO_INCREMENT,
  `DeviceOS` varchar(180) DEFAULT NULL,
  `DeviceOSVersion` varchar(180) DEFAULT NULL,
  `DeviceModel` varchar(180) DEFAULT NULL,
  `LocationReceived` int DEFAULT NULL,
  `LocationDisplayed` int DEFAULT NULL,
  `TimeReceived` datetime DEFAULT NULL,
  `TimeDisplayed` datetime DEFAULT NULL,
  `ReceivedOutsideArea` bit(1) DEFAULT b'0',
  `DisplayedOutsideArea` bit(1) DEFAULT b'0',
  `ReceivedAfterExpired` bit(1) DEFAULT b'0',
  `DisplayedAfterExpired` bit(1) DEFAULT b'0',
  PRIMARY KEY (`UploadID`),
  KEY `CMACMessageNumber` (`CMACMessageNumber`,`CMACCapIdentifier`),
  CONSTRAINT `device_upload_data_ibfk_1` FOREIGN KEY (`CMACMessageNumber`, `CMACCapIdentifier`) REFERENCES `cmac_message` (`CMACMessageNumber`, `CMACCapIdentifier`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'alert_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `GetMessageList` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetMessageList`(
	sender VARCHAR(180),
    pageNum INT,
    messageNumber INT,
    messageType VARCHAR(20),
    fromDate VARCHAR(10),
    toDate VARCHAR(10),
    orderByDate BIT,
    orderByDesc BIT
)
BEGIN
	DECLARE offsetVal INT DEFAULT 9 * (IF(pageNum < 1, 1, pageNum) - 1);
    
	SELECT cmac_message.CMACMessageNumber, CMACDateTime, CMACMessageType, COUNT(*) AS DeviceCount,
	CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(TimeReceived, CMACDateTime)))) AS TIME) AS AvgTime,
	MAX(TIMEDIFF(TimeReceived, CMACDateTime)) AS LongTime,
	MIN(TIMEDIFF(TimeReceived, CMACDateTime)) AS ShortTime,
	CAST(SEC_TO_TIME(AVG(TIME_TO_SEC(TIMEDIFF(TimeDisplayed, TimeReceived)))) AS TIME) AS AvgDelay,
	SUM(ReceivedOutsideArea) AS ReceivedOutsideCount,
	SUM(DisplayedOutsideArea) AS DisplayedOutsideCount,
	SUM(ReceivedAfterExpired) AS ReceivedExpiredCount,
	SUM(DisplayedAfterExpired) AS DisplayedExpiredCount
	FROM alert_db.device_upload_data JOIN alert_db.cmac_message
	ON cmac_message.CMACMessageNumber = device_upload_data.CMACMessageNumber
    WHERE CMACSender = sender
    AND cmac_message.CMACMessageNumber = IFNULL(messageNumber, cmac_message.CMACMessageNumber)
    AND CMACMessageType = IFNULL(messageType, CMACMessageType)
    AND CMACDateTime >= IFNULL(fromDate, DATE("2016-01-01"))
    AND CMACDateTime < DATE_ADD(IFNULL(toDate, CURDATE()), INTERVAL 1 DAY)
    GROUP BY cmac_message.CMACMessageNumber, CMACDateTime, CMACMessageType
    ORDER BY
		CASE WHEN NOT orderByDate AND NOT orderByDesc THEN cmac_message.CMACMessageNumber END ASC,
        CASE WHEN NOT orderByDate AND orderByDesc THEN cmac_message.CMACMessageNumber END DESC,
        CASE WHEN orderByDate AND NOT orderByDesc THEN CMACDateTime END ASC,
        CASE WHEN orderByDate AND orderByDesc THEN CMACDateTime END DESC
    LIMIT 10 OFFSET offsetVal;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `GetOldestMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetOldestMessage`(
	out messageNumber VARCHAR(8),
    out capIdentifier VARCHAR(180)
)
BEGIN
	SELECT CMACMessageNumber, CMACCapIdentifier
    INTO messageNumber, capIdentifier
	FROM alert_db.cmac_message
	WHERE CMACExpiresDateTime > NOW()
	ORDER BY CMACDateTime ASC
	LIMIT 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-02 20:03:29
