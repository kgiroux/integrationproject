-- Base de données ir2016 pour le projet quiz de tf8
CREATE TABLE `personne` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`nom` varchar(255) NOT NULL,
	`prenom` varchar(255) NOT NULL,
	`mail` varchar(255) NOT NULL,
	`mdp` varchar(255) NOT NULL,
	`droits` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `question` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `proposition` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`libelle` varchar(255) NOT NULL,
	`id_question` int(11) NOT NULL,
	`estBonneReponse` tinyint(1) NOT NULL,
	CONSTRAINT fk_pro_quest FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `quiz` (
	`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`libelle` varchar(255) NOT NULL,
	`dateDebutQuiz` timestamp NULL DEFAULT NULL,
	`dateFinQuiz` timestamp NULL DEFAULT NULL,
	`noQuestionCourante` int(11) NOT NULL,
	`dateDebutQuestion` timestamp NULL DEFAULT NULL,
	`etape` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `quiz_question` (
	`id_quiz` int(11) NOT NULL,
	`id_question` int(11) NOT NULL,
	PRIMARY KEY (`id_quiz`,`id_question`),
	CONSTRAINT `fk_qq_question` FOREIGN KEY (`id_question`) REFERENCES `question` (`id`) ON DELETE CASCADE,
	CONSTRAINT `fk_qq_quiz` FOREIGN KEY (`id_quiz`) REFERENCES `quiz` (`id`) ON DELETE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `choisir` (
	`id_personne` int(11) NOT NULL,
	`id_quiz` int(11) NOT NULL,
	`id_proposition` int(11) NOT NULL,
	`date` timestamp NULL DEFAULT NULL,
	PRIMARY KEY (`id_personne`,`id_quiz`),
	CONSTRAINT `fk_choisir_proposition` FOREIGN KEY (`id_proposition`) REFERENCES `proposition` (`id`) ON DELETE CASCADE,
	CONSTRAINT `fk_choisir_personne` FOREIGN KEY (`id_personne`) REFERENCES `personne` (`id`) ON DELETE CASCADE,
	CONSTRAINT `fk_choisir_quiz` FOREIGN KEY (`id_quiz`) REFERENCES `quiz` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;