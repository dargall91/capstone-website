-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: alert_db
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
  KEY `CMACMessageNumber` (`CMACMessageNumber`,`CMACCapIdentifier`),
  CONSTRAINT `cmac_area_description_ibfk_1` FOREIGN KEY (`CMACMessageNumber`, `CMACCapIdentifier`) REFERENCES `cmac_message` (`CMACMessageNumber`, `CMACCapIdentifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmac_area_description`
--

LOCK TABLES `cmac_area_description` WRITE;
/*!40000 ALTER TABLE `cmac_area_description` DISABLE KEYS */;
INSERT INTO `cmac_area_description` VALUES (1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Fisher',48151),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Jones',48253),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Taylor',48441),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Callahan',48059),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Fisher',48151),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Jones',48253),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Taylor',48441),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Callahan',48059),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Fisher',48151),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Jones',48253),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Taylor',48441),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Callahan',48059),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Fisher',48151),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Jones',48253),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Taylor',48441),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z','Callahan',48059),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Fisher',48151),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Jones',48253),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Taylor',48441),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z','Callahan',48059),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Fisher',48151),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Jones',48253),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Taylor',48441),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z','Callahan',48059),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Fisher',48151),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Jones',48253),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Taylor',48441),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z','Callahan',48059),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Fisher',48151),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Jones',48253),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Taylor',48441),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Callahan',48059),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Fisher',48151),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Jones',48253),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Taylor',48441),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z','Callahan',48059),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Fisher',48151),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Jones',48253),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Taylor',48441),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Callahan',48059),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Fisher',48151),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Jones',48253),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Taylor',48441),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z','Callahan',48059),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Fisher',48151),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Jones',48253),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Taylor',48441),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z','Callahan',48059),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Fisher',48151),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Jones',48253),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Taylor',48441),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z','Callahan',48059);
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
  `CMACUgrency` varchar(20) NOT NULL,
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
  KEY `CMACMessageNumber` (`CMACMessageNumber`,`CMACCapIdentifier`),
  CONSTRAINT `cmac_polygon_coordinates_ibfk_1` FOREIGN KEY (`CMACMessageNumber`, `CMACCapIdentifier`) REFERENCES `cmac_message` (`CMACMessageNumber`, `CMACCapIdentifier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmac_polygon_coordinates`
--

LOCK TABLES `cmac_polygon_coordinates` WRITE;
/*!40000 ALTER TABLE `cmac_polygon_coordinates` DISABLE KEYS */;
INSERT INTO `cmac_polygon_coordinates` VALUES (1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.27,-100.15),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.15),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.16),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.72,-100.17),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.85,-99.61),(1,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.27,-100.15),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.15),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.16),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.72,-100.17),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.85,-99.61),(2,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.27,-100.15),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.15),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.16),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.72,-100.17),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.85,-99.61),(3,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.27,-100.15),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.15),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.52,-100.16),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.72,-100.17),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.85,-99.61),(4,'NOAA-NWS-ALERTS Texas 2017-06-01:32:50Z',32.21,-99.62),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.21,-99.62),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.27,-100.15),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.52,-100.15),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.52,-100.16),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.72,-100.17),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.85,-99.61),(5,'NOAA-NWS-ALERTS Texas 2022-11-02:04:24Z',32.21,-99.62),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.21,-99.62),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.27,-100.15),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.52,-100.15),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.52,-100.16),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.72,-100.17),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.85,-99.61),(6,'NOAA-NWS-ALERTS Texas 2022-11-08:29:59Z',32.21,-99.62),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.21,-99.62),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.27,-100.15),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.52,-100.15),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.52,-100.16),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.72,-100.17),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.85,-99.61),(7,'NOAA-NWS-ALERTS Texas 2022-11-11:48:01Z',32.21,-99.62),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.21,-99.62),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.27,-100.15),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.52,-100.15),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.52,-100.16),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.72,-100.17),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.85,-99.61),(8,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.21,-99.62),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.21,-99.62),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.27,-100.15),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.52,-100.15),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.52,-100.16),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.72,-100.17),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.85,-99.61),(9,'NOAA-NWS-ALERTS Texas 2022-11-17:18:31Z',32.21,-99.62),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.21,-99.62),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.27,-100.15),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.52,-100.15),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.52,-100.16),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.72,-100.17),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.85,-99.61),(10,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.21,-99.62),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.21,-99.62),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.27,-100.15),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.52,-100.15),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.52,-100.16),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.72,-100.17),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.85,-99.61),(11,'NOAA-NWS-ALERTS Texas 2022-10-17:18:31Z',32.21,-99.62),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.21,-99.62),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.27,-100.15),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.52,-100.15),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.52,-100.16),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.72,-100.17),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.85,-99.61),(12,'NOAA-NWS-ALERTS Texas 2022-11-20:01:39Z',32.21,-99.62),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.21,-99.62),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.27,-100.15),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.52,-100.15),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.52,-100.16),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.72,-100.17),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.85,-99.61),(13,'NOAA-NWS-ALERTS Texas 2022-11-20:53:21Z',32.21,-99.62);
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-28 21:24:14
