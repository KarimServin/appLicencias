CREATE DATABASE  IF NOT EXISTS `applicenciasdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `applicenciasdb`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: applicenciasdb
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `licencia`
--

DROP TABLE IF EXISTS `licencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `licencia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `clase_licencia` enum('A','B','C','D','E','F','G') DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `titular_dni` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `version_anterior_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5pomi4xbkdwptjs6r2bw8s43b` (`titular_dni`),
  KEY `FKo6n5p7qaf50kgcvc6gvhxopya` (`usuario_id`),
  KEY `FKos4rqn5uojbpqky9kr19mo79y` (`version_anterior_id`),
  CONSTRAINT `FK5pomi4xbkdwptjs6r2bw8s43b` FOREIGN KEY (`titular_dni`) REFERENCES `titular` (`dni`),
  CONSTRAINT `FKo6n5p7qaf50kgcvc6gvhxopya` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKos4rqn5uojbpqky9kr19mo79y` FOREIGN KEY (`version_anterior_id`) REFERENCES `licencia` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `licencia`
--

LOCK TABLES `licencia` WRITE;
/*!40000 ALTER TABLE `licencia` DISABLE KEYS */;
INSERT INTO `licencia` VALUES (1,'A','2025-06-15','2030-06-15','',40703262,1,NULL),(2,'B','2025-06-15','2030-06-15','',40703262,1,NULL),(3,'F','2025-06-15','2030-01-22','',40703262,1,NULL),(4,'A','2025-06-21','2026-02-19','',48595213,1,NULL),(5,'C','2025-06-21','2030-11-05','',41700972,1,NULL),(6,'E','2025-06-21','2030-11-05','',41700972,1,NULL),(7,'D','2025-06-21','2028-06-05','',12991564,1,NULL),(8,'G','2025-06-21','2028-02-19','',48595213,1,NULL);
/*!40000 ALTER TABLE `licencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titular`
--

DROP TABLE IF EXISTS `titular`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `titular` (
  `dni` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `es_donante` bit(1) NOT NULL,
  `factor_sanguineo` char(1) NOT NULL,
  `fecha_licencia_claseb` date DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `grupo_sanguineo` char(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` bigint NOT NULL,
  `tuvo_licencia_profesional` bit(1) NOT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `FKecaks6h6cqdqb1vobn92s41lf` (`usuario_id`),
  CONSTRAINT `FKecaks6h6cqdqb1vobn92s41lf` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titular`
--

LOCK TABLES `titular` WRITE;
/*!40000 ALTER TABLE `titular` DISABLE KEYS */;
INSERT INTO `titular` VALUES (12991564,'robdannavarro@hotmail.com',_binary '\0','+','1978-02-01','1958-06-05','A','Navarro Roberto',1154456877,_binary '',1,'General Paz 5684 CABA'),(13458779,'silvia@hotmail.com',_binary '','-','1990-12-29','1959-12-25','B','Moreyra Silvia',3424354923,_binary '\0',1,'sarmiento 601'),(40703262,'adfad@afad.com',_binary '','+',NULL,'1998-01-22','O','Robledo Carlos',3497658920,_binary '\0',NULL,NULL),(41700972,'maty@hotmail.com',_binary '\0','+','2016-12-12','1998-11-05','A','Di Stefano Matias',3483432119,_binary '',1,'Dorrego 1284'),(45012487,'calberto@yahoo.com',_binary '','-',NULL,'2004-01-20','O','Alberto Carlos',3454342341,_binary '\0',1,'uruguay 321, Santa Fe'),(48595213,'ejemplo@mail.com',_binary '\0','+',NULL,'2008-02-19','O','Perez Juan',4389345092,_binary '\0',1,'llerena 1234'),(49000001,'mblas@gmail.com',_binary '','+',NULL,'2007-05-13','O','Blas Mario',3422348103,_binary '\0',1,'san lorenzo 382, Santa Fe');
/*!40000 ALTER TABLE `titular` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contrasenia` varchar(255) DEFAULT NULL,
  `es_superusuario` bit(1) NOT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKpuhr3k3l7bj71hb7hk7ktpxn0` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin',_binary '\0','admin'),(2,'superadmin',_binary '','superadmin');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-22  0:23:09
