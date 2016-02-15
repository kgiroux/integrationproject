CREATE DATABASE  IF NOT EXISTS `ir2016` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ir2016`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: ir2016
-- ------------------------------------------------------
-- Server version	5.5.8-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `choisir`
--

DROP TABLE IF EXISTS `choisir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `choisir` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_personne` int(11) NOT NULL,
  `id_quiz` int(11) NOT NULL,
  `id_proposition` int(11) NOT NULL,
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_choisir_proposition` (`id_proposition`),
  KEY `fk_choisir_personne` (`id_personne`),
  KEY `fk_choisir_quiz` (`id_quiz`),
  CONSTRAINT `fk_choisir_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_choisir_proposition` FOREIGN KEY (`id_proposition`) REFERENCES `proposition` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_choisir_quiz` FOREIGN KEY (`id_quiz`) REFERENCES `quiz` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choisir`
--

LOCK TABLES `choisir` WRITE;
/*!40000 ALTER TABLE `choisir` DISABLE KEYS */;
/*!40000 ALTER TABLE `choisir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personne`
--

DROP TABLE IF EXISTS `personne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `droits` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personne`
--

LOCK TABLES `personne` WRITE;
/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
INSERT INTO `personne` VALUES (1,'a','a','a@a.a','0CC175B9C0F1B6A831C399E269772661',1000),(2,'f','f','j@j.j','0CC175B9C0F1B6A831C399E269772661',0),(3,'b','b','b@b.b','92EB5FFEE6AE2FEC3AD71C777531578F',0);
/*!40000 ALTER TABLE `personne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proposition`
--

DROP TABLE IF EXISTS `proposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proposition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  `id_question` int(11) DEFAULT NULL,
  `estBonneReponse` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pro_quest` (`id_question`),
  CONSTRAINT `fk_pro_quest` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposition`
--

LOCK TABLES `proposition` WRITE;
/*!40000 ALTER TABLE `proposition` DISABLE KEYS */;
INSERT INTO `proposition` VALUES (9,'paris',1,1),(10,'rouen',1,0),(11,'toulouse',1,0),(12,'rennes',1,0),(13,'caen',2,0),(14,'madrid',2,1),(15,'bruxelles',3,1),(16,'bourges',3,0),(17,'pau',3,0),(18,'marseille',2,0),(19,'12',4,0),(20,'13',4,0),(21,'14',4,0),(22,'15',4,0),(23,'12',5,0),(24,'13',5,0),(25,'14',5,0),(26,'15',5,0),(27,'3',6,1),(28,'1',6,0),(29,'2',6,0),(30,'8',6,0),(31,'9',6,0),(32,'7',6,0),(33,'33',7,1),(34,'7',7,0),(35,'8',7,0),(36,'9',7,0),(42,'4',9,1),(43,'3',10,1),(44,'sdc',10,0),(45,'sdc',10,0),(46,'sdc',10,1);
/*!40000 ALTER TABLE `proposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'capitale de france ?'),(2,'capitale d espagne?'),(3,'capitale de belgique?'),(4,'3+8 ?'),(5,'3+8?'),(6,'1+2?'),(7,'11+22?'),(9,'ddd'),(10,'dsdc');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  `dateDebutQuiz` timestamp NULL DEFAULT NULL,
  `dateFinQuiz` timestamp NULL DEFAULT NULL,
  `noQuestionCourante` int(11) NOT NULL,
  `dateDebutQuestion` timestamp NULL DEFAULT NULL,
  `etape` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `libelle` (`libelle`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (4,'histoire','2016-02-15 20:29:06',NULL,0,'2016-02-15 20:29:06',1),(5,'mathematique',NULL,NULL,0,'2016-02-14 19:54:07',0),(6,'science',NULL,NULL,0,'2016-02-14 19:54:12',0),(7,'geo',NULL,NULL,0,'2016-02-14 19:54:10',0),(8,'complet','2016-02-15 20:28:05',NULL,2,'2016-02-15 20:28:21',1),(9,'test','2016-02-15 20:34:55',NULL,0,'2016-02-15 20:34:55',1),(10,'blabla','2016-02-15 20:58:28',NULL,2,'2016-02-15 20:58:40',3);
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz_question`
--

DROP TABLE IF EXISTS `quiz_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quiz_question` (
  `id_quiz` int(11) NOT NULL,
  `id_question` int(11) NOT NULL,
  PRIMARY KEY (`id_quiz`,`id_question`),
  KEY `fk_qq_question` (`id_question`),
  CONSTRAINT `fk_qq_question` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_qq_quiz` FOREIGN KEY (`id_quiz`) REFERENCES `quiz` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_question`
--

LOCK TABLES `quiz_question` WRITE;
/*!40000 ALTER TABLE `quiz_question` DISABLE KEYS */;
INSERT INTO `quiz_question` VALUES (4,1),(5,1),(7,1),(8,1),(10,1),(5,2),(7,2),(8,2),(5,3),(6,3),(8,3),(10,3),(8,4),(10,4),(5,5),(6,5),(8,5),(5,6),(8,6),(9,7);
/*!40000 ALTER TABLE `quiz_question` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-15 22:01:55
