-- MySQL dump 10.13  Distrib 5.5.42, for osx10.6 (i386)
--
-- Host: localhost    Database: ir2016_v2
-- ------------------------------------------------------
-- Server version   5.5.42

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
  `id_personne` int(11) NOT NULL,
  `id_quiz` int(11) NOT NULL,
  `id_proposition` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_personne`,`id_quiz`,`id_proposition`),
  KEY `id_personne` (`id_personne`),
  KEY `fk_id_quiz` (`id_quiz`),
  KEY `fk_id_proposition` (`id_proposition`),
  CONSTRAINT `fk_id_proposition` FOREIGN KEY (`id_proposition`) REFERENCES `proposition` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_id_quiz` FOREIGN KEY (`id_quiz`) REFERENCES `quiz` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personne`
--

LOCK TABLES `personne` WRITE;
/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
INSERT INTO `personne` VALUES (1,'kevin','charles','test@mail.com','12345',1000),(2,'12345','12345','mincong.h@gmail.com','12345',0),(3,'','','','',0),(4,'','','','',0),(5,'','','','',0),(6,'','','','',0),(7,'','','','',0),(8,'','','','',0),(9,'','','','',0),(10,'Test','Test','test@test.com','05A671C66AEFEA124CC08B76EA6D30BB',0);
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
  `idQuestion` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idQuestion` (`idQuestion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proposition`
--

LOCK TABLES `proposition` WRITE;
/*!40000 ALTER TABLE `proposition` DISABLE KEYS */;
INSERT INTO `proposition` VALUES (3,'Java EE',2),(4,'PHP EE',2),(5,'Jyphon EE',2),(6,'Json EE',2);
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
  `idBonneReponse` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idBonneReponse` (`idBonneReponse`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (2,'JEE',3);
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
  `dateDebutQuiz` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dateFinQuiz` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `noQuestionCourante` int(11) NOT NULL,
  `dateDebutQuestion` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `etape` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (1,'','2016-01-13 15:58:41','2016-01-13 15:58:41',0,'2016-01-13 15:58:41',0),(2,'Quiz test','2016-01-13 17:46:56','2016-01-13 17:46:56',0,'2016-01-13 17:46:56',0),(3,'Quiz test','2016-01-13 18:04:54','2016-01-13 18:04:54',0,'2016-01-13 18:04:54',0),(4,'Quiz test','2016-01-13 18:07:06','2016-01-13 18:07:06',0,'2016-01-13 18:07:06',0),(5,'Quiz test','2016-01-13 18:11:53','2016-01-13 18:11:53',0,'2016-01-13 18:11:53',0),(6,'Quiz test','2016-01-13 18:13:09','2016-01-13 18:13:09',0,'2016-01-13 18:13:09',0),(7,'Quiz test','2016-01-13 18:14:37','2016-01-13 18:14:37',0,'2016-01-13 18:14:37',0),(8,'Quiz test','2016-01-13 18:15:40','2016-01-13 18:15:40',0,'2016-01-13 18:15:40',0),(9,'Quiz test','2016-01-13 18:22:23','2016-01-13 18:22:23',0,'2016-01-13 18:22:23',0),(10,'Quiz test','2016-01-13 18:26:32','2016-01-13 18:26:32',0,'2016-01-13 18:26:32',0),(11,'Quiz test','2016-01-13 18:27:19','2016-01-13 18:27:19',0,'2016-01-13 18:27:19',0),(12,'Quiz test','2016-01-13 18:29:14','2016-01-13 18:29:14',0,'2016-01-13 18:29:14',0),(13,'Quiz test','2016-01-13 18:30:25','2016-01-13 18:30:25',0,'2016-01-13 18:30:25',0),(14,'Quiz test','2016-01-13 18:31:41','2016-01-13 18:31:41',0,'2016-01-13 18:31:41',0),(15,'Quiz test','2016-01-13 18:32:25','2016-01-13 18:32:25',0,'2016-01-13 18:32:25',0),(16,'Quiz test','2016-01-13 18:33:09','2016-01-13 18:33:09',0,'2016-01-13 18:33:09',0),(17,'Quiz test','2016-01-13 18:34:49','2016-01-13 18:34:49',0,'2016-01-13 18:34:49',0),(18,'Quiz test','2016-01-13 18:47:41','2016-01-13 18:47:41',0,'2016-01-13 18:47:41',0),(19,'Quiz test','2016-01-13 18:49:04','2016-01-13 18:49:04',0,'2016-01-13 18:49:04',0),(20,'Quiz test','2016-01-13 18:51:26','2016-01-13 18:51:26',0,'2016-01-13 18:51:26',0),(21,'Quiz test','2016-01-13 18:53:32','2016-01-13 18:53:32',0,'2016-01-13 18:53:32',0),(22,'Quiz test','2016-01-13 18:54:31','2016-01-13 18:54:31',0,'2016-01-13 18:54:31',0),(23,'Quiz test','2016-01-13 18:59:47','2016-01-13 18:59:47',0,'2016-01-13 18:59:47',0),(24,'Quiz test','2016-01-13 19:00:57','2016-01-13 19:00:57',0,'2016-01-13 19:00:57',0),(25,'Quiz test','2016-01-13 19:02:21','2016-01-13 19:02:21',0,'2016-01-13 19:02:21',0);
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
  KEY `fk_idQuestion` (`id_question`),
  CONSTRAINT `fk_idQuestion` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idQuiz` FOREIGN KEY (`id_quiz`) REFERENCES `quiz` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_question`
--

LOCK TABLES `quiz_question` WRITE;
/*!40000 ALTER TABLE `quiz_question` DISABLE KEYS */;
INSERT INTO `quiz_question` VALUES (4,2);
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

-- Dump completed on 2016-01-14  2:37:51
