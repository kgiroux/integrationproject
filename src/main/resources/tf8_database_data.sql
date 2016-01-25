USE `ir2016`;
INSERT INTO `personne` (`nom`, `prenom`, `mail`, `mdp`, `droits`) VALUES
('a', 'a', 'a@a.a', UPPER(MD5('a')), 1000),
('ADMIN', 'Admin', 'admin@esigelec.fr', UPPER(MD5('admin')), 1000),
('JOUEUR', 'Joueur', 'joueur@esigelec.fr', UPPER(MD5('joueur')), 0);

INSERT INTO `question` (`libelle`) VALUES
('Quelle est la capitale de la France ?'),
('Combien y-a-t-il de Kevin dans notre classe ?');

INSERT INTO `proposition` (`libelle`, `id_question`, `estBonneReponse`) VALUES
('Rouen', 1, 0),
('Paris', 1, 1),
('Marseille', 1, 0),
('Nice', 1, 0),
('1', 2, 0),
('2', 2, 0),
('3', 2, 1),
('4', 2, 0);

INSERT INTO `quiz` (`libelle`) VALUES
('Culture générale');

INSERT INTO `quiz_question` (`id_quiz`, `id_question`) VALUES
(1, 1),
(1, 2);