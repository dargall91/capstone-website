CREATE DATABASE  IF NOT EXISTS `alert_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `alert_db`;
-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: localhost    Database: alert_db
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.22.04.1

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
-- Dumping data for table `cmac_alert_text`
--

LOCK TABLES `cmac_alert_text` WRITE;
/*!40000 ALTER TABLE `cmac_alert_text` DISABLE KEYS */;
INSERT INTO `cmac_alert_text` VALUES (1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service'),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','English','Flash Flood Warning this area until 9:30 PM CDT. NWS','Flash Flood Warning this area until 9:30 PM CDT. Avoid flood areas. Do not drive on flooded roads. Check local radio and television stations for more information. National Weather Service'),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Spanish','Aviso de inundación de destello esta área hasta las 9:30 PM CDT. NWS','Advertencia de inundación de emergencia esta área hasta las 9:30 PM CDT. Evite las zonas de inundación. No conduzca en carreteras inundadas. Consulte las emisoras de radio y televisión locales para obtener más información. National Weather Service');
/*!40000 ALTER TABLE `cmac_alert_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmac_area_description`
--

DROP TABLE IF EXISTS `cmac_area_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cmac_area_description` (
  `CMACMessageNumber` int NOT NULL,
  `CMACCapIdentifier` varchar(150) NOT NULL,
  `AreaName` varchar(500) NOT NULL,
  `CMASGeocode` varchar(20) NOT NULL,
  `AreaId` int NOT NULL,
  KEY `CMACMessageNumber` (`CMACMessageNumber`,`CMACCapIdentifier`),
  CONSTRAINT `cmac_area_description_ibfk_1` FOREIGN KEY (`CMACMessageNumber`, `CMACCapIdentifier`) REFERENCES `cmac_message` (`CMACMessageNumber`, `CMACCapIdentifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmac_area_description`
--

LOCK TABLES `cmac_area_description` WRITE;
/*!40000 ALTER TABLE `cmac_area_description` DISABLE KEYS */;
INSERT INTO `cmac_area_description` VALUES (1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Fisher, TX','48151',1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Jones, TX','48253',1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Taylor, TX','48441',1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Callahan, TX','48059',1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Fisher, TX','48151',1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Jones, TX','48253',1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Taylor, TX','48441',1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Callahan, TX','48059',1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Fisher, TX','48151',1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Jones, TX','48253',1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Taylor, TX','48441',1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Callahan, TX','48059',1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Fisher, TX','48151',1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Jones, TX','48253',1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Taylor, TX','48441',1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Callahan, TX','48059',1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Fisher, TX','48151',1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Jones, TX','48253',1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Taylor, TX','48441',1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Callahan, TX','48059',1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Fisher, TX','48151',1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Jones, TX','48253',1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Taylor, TX','48441',1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Callahan, TX','48059',1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Fisher, TX','48151',1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Jones, TX','48253',1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Taylor, TX','48441',1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Callahan, TX','48059',1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Fisher, TX','48151',1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Jones, TX','48253',1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Taylor, TX','48441',1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Callahan, TX','48059',1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Fisher, TX','48151',1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Jones, TX','48253',1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Taylor, TX','48441',1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Callahan, TX','48059',1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Fisher, TX','48151',1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Jones, TX','48253',1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Taylor, TX','48441',1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Callahan, TX','48059',1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Fisher, TX','48151',1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Jones, TX','48253',1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Taylor, TX','48441',1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Callahan, TX','48059',1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Fisher, TX','48151',1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Jones, TX','48253',1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Taylor, TX','48441',1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Callahan, TX','48059',1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Fisher, TX','48151',1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Jones, TX','48253',1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Taylor, TX','48441',1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Callahan, TX','48059',1);
/*!40000 ALTER TABLE `cmac_area_description` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `cmac_circle_coordinates`
--

LOCK TABLES `cmac_circle_coordinates` WRITE;
/*!40000 ALTER TABLE `cmac_circle_coordinates` DISABLE KEYS */;
/*!40000 ALTER TABLE `cmac_circle_coordinates` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `cmac_message`
--

LOCK TABLES `cmac_message` WRITE;
/*!40000 ALTER TABLE `cmac_message` DISABLE KEYS */;
INSERT INTO `cmac_message` VALUES (1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','w-nws.webmaster@noaa.gov','2017-06-03 01:32:50','Actual','Alert','NWS San Angelo TX','2017-06-03 02:30:00','Met','Severe','Expected','Likely',NULL),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','w-nws.webmaster@noaa.gov','2017-06-03 01:32:50','Actual','Alert','NWS San Angelo TX','2017-06-03 02:30:00','Met','Severe','Expected','Likely',NULL),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','w-nws.webmaster@noaa.gov','2017-06-03 01:32:50','Actual','Alert','NWS San Angelo TX','2017-06-03 02:30:00','Met','Severe','Expected','Likely',NULL),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','w-nws.webmaster@noaa.gov','2017-06-03 01:32:50','Actual','Alert','NWS San Angelo TX','2017-06-03 02:30:00','Met','Severe','Expected','Likely',NULL),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','w-nws.webmaster@noaa.gov','2022-11-11 02:04:24','Actual','Alert','NWS San Angelo TX','2022-11-11 04:00:00','Met','Severe','Expected','Likely',NULL),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','w-nws.webmaster@noaa.gov','2022-11-12 08:29:59','Actual','Alert','NWS San Angelo TX','2022-11-12 12:00:00','Met','Severe','Expected','Likely',NULL),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','w-nws.webmaster@noaa.gov','2022-11-13 11:48:01','Actual','Alert','NWS San Angelo TX','2022-11-13 16:30:00','Met','Severe','Expected','Likely',NULL),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','w-nws.webmaster@noaa.gov','2022-11-12 17:18:31','Actual','Alert','NWS San Angelo TX','2022-11-13 17:18:31','Met','Severe','Expected','Likely',NULL),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','w-nws.webmaster@noaa.gov','2022-11-13 17:18:31','Actual','Alert','NWS San Angelo TX','2022-11-13 18:18:31','Met','Severe','Expected','Likely',NULL),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','w-nws.webmaster@noaa.gov','2022-10-13 17:18:31','Actual','Alert','NWS San Angelo TX','2022-10-13 18:18:31','Met','Severe','Expected','Likely',NULL),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','w-nws.webmaster@noaa.gov','2022-10-13 17:18:31','Actual','Alert','NWS San Angelo TX','2022-10-13 18:18:31','Met','Severe','Expected','Likely',NULL),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','w-nws.webmaster@noaa.gov','2022-11-13 20:01:39','Actual','Alert','NWS San Angelo TX','2022-11-14 02:00:00','Met','Severe','Expected','Likely',NULL),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','w-nws.webmaster@noaa.gov','2022-11-13 20:53:21','Actual','Update','NWS San Angelo TX','2022-11-14 04:00:00','Met','Severe','Expected','Likely',NULL);
/*!40000 ALTER TABLE `cmac_message` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `cmac_polygon_coordinates`
--

LOCK TABLES `cmac_polygon_coordinates` WRITE;
/*!40000 ALTER TABLE `cmac_polygon_coordinates` DISABLE KEYS */;
INSERT INTO `cmac_polygon_coordinates` VALUES (1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62,1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.27,-100.15,1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.15,1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.16,1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.72,-100.17,1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.85,-99.61,1),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62,1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62,1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.27,-100.15,1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.15,1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.16,1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.72,-100.17,1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.85,-99.61,1),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62,1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62,1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.27,-100.15,1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.15,1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.16,1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.72,-100.17,1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.85,-99.61,1),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62,1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62,1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.27,-100.15,1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.15,1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.16,1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.72,-100.17,1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.85,-99.61,1),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62,1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.21,-99.62,1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.27,-100.15,1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.52,-100.15,1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.52,-100.16,1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.72,-100.17,1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.85,-99.61,1),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.21,-99.62,1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.21,-99.62,1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.27,-100.15,1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.52,-100.15,1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.52,-100.16,1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.72,-100.17,1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.85,-99.61,1),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.21,-99.62,1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.21,-99.62,1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.27,-100.15,1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.52,-100.15,1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.52,-100.16,1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.72,-100.17,1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.85,-99.61,1),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.21,-99.62,1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.21,-99.62,1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.27,-100.15,1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.52,-100.15,1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.52,-100.16,1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.72,-100.17,1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.85,-99.61,1),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.21,-99.62,1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.21,-99.62,1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.27,-100.15,1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.52,-100.15,1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.52,-100.16,1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.72,-100.17,1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.85,-99.61,1),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.21,-99.62,1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.21,-99.62,1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.27,-100.15,1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.52,-100.15,1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.52,-100.16,1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.72,-100.17,1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.85,-99.61,1),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.21,-99.62,1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.21,-99.62,1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.27,-100.15,1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.52,-100.15,1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.52,-100.16,1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.72,-100.17,1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.85,-99.61,1),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.21,-99.62,1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.21,-99.62,1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.27,-100.15,1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.52,-100.15,1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.52,-100.16,1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.72,-100.17,1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.85,-99.61,1),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.21,-99.62,1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.21,-99.62,1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.27,-100.15,1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.52,-100.15,1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.52,-100.16,1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.72,-100.17,1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.85,-99.61,1),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.21,-99.62,1);
/*!40000 ALTER TABLE `cmac_polygon_coordinates` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `device_upload_data`
--

LOCK TABLES `device_upload_data` WRITE;
/*!40000 ALTER TABLE `device_upload_data` DISABLE KEYS */;
INSERT INTO `device_upload_data` VALUES (1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',1,NULL,NULL,NULL,48151,48151,'2017-06-03 01:41:47','2017-06-03 01:42:14',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',2,NULL,NULL,NULL,48151,48152,'2017-06-03 01:37:50','2017-06-03 01:38:21',_binary '\0',_binary '',_binary '\0',_binary '\0'),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',3,NULL,NULL,NULL,48441,48441,'2017-06-03 01:33:50','2017-06-03 01:36:01',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',4,NULL,NULL,NULL,48253,48253,'2017-06-03 02:01:12','2017-06-03 02:01:59',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',5,NULL,NULL,NULL,48254,48253,'2017-06-03 02:00:32','2017-06-03 02:03:59',_binary '',_binary '\0',_binary '\0',_binary '\0'),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',6,NULL,NULL,NULL,48441,48441,'2017-06-03 01:36:24','2017-06-03 01:38:01',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',7,NULL,NULL,NULL,48059,48441,'2017-06-03 01:34:10','2017-06-03 01:34:12',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',8,NULL,NULL,NULL,48059,48059,'2017-06-03 02:01:01','2017-06-03 02:01:59',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',9,NULL,NULL,NULL,48060,48060,'2017-06-03 01:45:16','2017-06-03 01:47:09',_binary '',_binary '',_binary '\0',_binary '\0'),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',10,NULL,NULL,NULL,48151,48441,'2017-06-03 01:33:01','2017-06-03 01:33:14',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',11,NULL,NULL,NULL,48441,48151,'2017-06-03 01:33:18','2017-06-03 01:33:22',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',12,NULL,NULL,NULL,48253,48253,'2017-06-03 01:33:29','2017-06-03 01:33:36',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',13,NULL,NULL,NULL,48151,48151,'2022-11-11 02:41:47','2022-11-11 02:42:01',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',14,NULL,NULL,NULL,48151,48152,'2022-11-11 02:07:50','2022-11-11 02:08:21',_binary '\0',_binary '',_binary '\0',_binary '\0'),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',15,NULL,NULL,NULL,48254,48253,'2022-11-11 02:05:32','2022-11-11 02:05:59',_binary '',_binary '\0',_binary '\0',_binary '\0'),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',16,NULL,NULL,NULL,48060,48060,'2022-11-11 02:09:16','2022-11-11 02:09:26',_binary '',_binary '',_binary '\0',_binary '\0'),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',17,NULL,NULL,NULL,48441,48441,'2022-11-12 08:31:50','2022-11-12 08:34:03',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',18,NULL,NULL,NULL,48253,48253,'2022-11-12 08:32:12','2022-11-12 08:01:59',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',19,NULL,NULL,NULL,48254,48253,'2022-11-12 08:30:32','2022-11-12 08:03:59',_binary '',_binary '\0',_binary '\0',_binary '\0'),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',20,NULL,NULL,NULL,48151,48441,'2022-11-12 08:33:01','2022-11-12 08:33:14',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',21,NULL,NULL,NULL,48441,48441,'2022-11-13 11:49:24','2022-11-13 11:49:32',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',22,NULL,NULL,NULL,48059,48441,'2022-11-13 11:51:10','2022-11-13 11:51:12',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',23,NULL,NULL,NULL,48059,48441,'2022-11-13 11:55:10','2022-11-13 11:55:49',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',24,NULL,NULL,NULL,48059,48059,'2022-11-13 11:50:01','2022-11-13 11:50:19',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',25,NULL,NULL,NULL,48060,48060,'2022-11-13 11:48:16','2022-11-13 11:48:47',_binary '',_binary '',_binary '\0',_binary '\0'),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',26,NULL,NULL,NULL,48151,48441,'2022-11-12 17:19:01','2022-11-12 17:19:57',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',27,NULL,NULL,NULL,48441,48151,'2022-11-12 17:20:18','2022-11-12 17:20:22',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',28,NULL,NULL,NULL,48253,48253,'2022-11-12 17:18:58','2022-11-12 17:19:06',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',29,NULL,NULL,NULL,48254,48253,'2022-11-13 17:22:32','2022-11-13 17:23:29',_binary '',_binary '\0',_binary '\0',_binary '\0'),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',30,NULL,NULL,NULL,48059,48059,'2022-11-13 17:24:01','2022-11-13 17:24:14',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',31,NULL,NULL,NULL,48253,48253,'2022-10-13 17:19:29','2022-10-13 17:19:41',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32,NULL,NULL,NULL,48151,48151,'2022-10-13 17:20:47','2022-10-13 17:21:00',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',33,NULL,NULL,NULL,48254,48253,'2022-10-13 17:19:32','2022-10-13 17:19:51',_binary '',_binary '\0',_binary '\0',_binary '\0'),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',34,NULL,NULL,NULL,48151,48152,'2022-10-13 17:21:50','2022-10-13 17:22:07',_binary '\0',_binary '',_binary '\0',_binary '\0'),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',35,NULL,NULL,NULL,48060,48060,'2022-10-13 17:22:16','2022-10-13 17:22:34',_binary '',_binary '',_binary '\0',_binary '\0'),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',36,NULL,NULL,NULL,48059,48441,'2022-10-13 17:19:10','2022-10-13 17:19:21',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',37,NULL,NULL,NULL,48151,48151,'2022-10-13 17:18:47','2022-10-13 17:18:56',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',38,NULL,NULL,NULL,48441,48441,'2022-10-13 17:25:50','2022-10-13 17:25:59',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',39,NULL,NULL,NULL,48151,48151,'2022-11-13 20:02:02','2022-11-13 20:02:18',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',40,NULL,NULL,NULL,48253,48253,'2022-11-13 20:03:32','2022-11-13 20:03:47',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',41,NULL,NULL,NULL,48151,48151,'2022-11-13 20:01:50','2022-11-13 20:02:01',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',42,NULL,NULL,NULL,48059,48059,'2022-11-13 20:04:16','2022-11-13 20:04:31',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',43,NULL,NULL,NULL,48059,48441,'2022-11-13 20:53:10','2022-11-13 20:53:28',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',44,NULL,NULL,NULL,48151,48151,'2022-11-13 20:56:47','2022-11-13 20:57:00',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',45,NULL,NULL,NULL,48441,48441,'2022-11-13 20:55:50','2022-11-13 20:56:08',_binary '\0',_binary '\0',_binary '\0',_binary '\0'),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',46,NULL,NULL,NULL,48151,48151,'2022-11-13 20:54:23','2022-11-13 20:54:45',_binary '\0',_binary '\0',_binary '\0',_binary '\0');
/*!40000 ALTER TABLE `device_upload_data` ENABLE KEYS */;
UNLOCK TABLES;

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
/*!50003 DROP PROCEDURE IF EXISTS `InsertAlertText` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAlertText`(
	messageNumber INT,
    capIdentifier VARCHAR(180),
    languageName VARCHAR(20),
    shortMessage VARCHAR (200),
    longMessage VARCHAR(2000)
)
BEGIN
	INSERT INTO cmac_alert_text
    VALUES(messageNumber, capIdentifier, languageName, shortMessage, longMessage);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertAreaDescription` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertAreaDescription`(
	messageNumber INT,
    capIdentifier VARCHAR(180),
    areaName VARCHAR(500),
    geocode VARCHAR(20),
    areaId INT
)
BEGIN
	INSERT INTO cmac_area_description
    VALUES(messageNumber, capIdentifier, areaName, geocode, areaId);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertCircleCoordinates` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertCircleCoordinates`(
	messageNumber INT,
    capIdentifier VARCHAR(180),
    latitude DECIMAL(5,2),
    longitude DECIMAL(5,2),
    areaId INT
)
BEGIN
	INSERT INTO cmac_circle_coordinates
    VALUES(messageNumber, capIdentifier, latitude, longitude, areaId);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertCmacMessage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertCmacMessage`(
	capIdentifier VARCHAR(180),
    sender VARCHAR(180),
    sentDateTime VARCHAR(50),
    messageStatus VARCHAR(20),
    messageType VARCHAR(20),
    senderName VARCHAR(180),
    expiresTime VARCHAR(50),
    category VARCHAR(20),
    severity VARCHAR(20),
    urgency VARCHAR(20),
    certainty VARCHAR(20),
    referenceIdentifier VARCHAR(180),
    OUT messageNumber INT
)
BEGIN
	INSERT INTO cmac_message
	VALUES(NULL, capIdentifier, sender, sentDateTime, messageStatus, messageType, senderName, expiresTime, category, severity, urgency,
    certainty, referenceIdentifier);
    SET messageNumber = LAST_INSERT_ID();
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `InsertPolygonCoordinates` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertPolygonCoordinates`(
	messageNumber INT,
    capIdentifier VARCHAR(180),
    latitude DECIMAL(5,2),
    longitude DECIMAL(5,2),
    areaId INT
)
BEGIN
	INSERT INTO cmac_polygon_coordinates
    VALUES(messageNumber, capIdentifier, latitude, longitude, areaId);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `UploadDeviceData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `UploadDeviceData`(
	messageNumber INT,
    capIdentifier VARCHAR(180),
    locationReceived INT,
    locationDisplayed INT,
    timeReceived DATETIME,
    timeDisplayed DATETIME,
    receivedOutside BIT,
    displayedOutside BIT,
    receivedExpired BIT,
    displayedExpired BIT,
    OUT uploadId INT
)
BEGIN
	INSERT INTO device_upload_data
    VALUES(messageNumber, capIdentifier, NULL, NULL, NULL, NULL, locationReceived, locationDisplayed, timeReceived, timeDisplayed,
    receivedOutside, displayedOutside, receivedExpired, displayedExpired);
    SET uploadId = LAST_INSERT_ID();
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

-- Dump completed on 2023-01-19 19:47:07
