-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 09 Février 2024 à 20:53
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `mabase`
--

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `DropIndexIfExists`(indexName VARCHAR(255), tableName VARCHAR(255))
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION BEGIN END;
    SET @query = CONCAT('DROP INDEX IF EXISTS ', indexName, ' ON ', tableName);
    PREPARE stmt FROM @query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `DropTableIfExists`(tableName VARCHAR(255))
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION BEGIN END;
    SET @query = CONCAT('DROP TABLE IF EXISTS ', tableName);
    PREPARE stmt FROM @query;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

CREATE TABLE IF NOT EXISTS `administrateur` (
  `IDADMINISTRATEUR` int(11) NOT NULL DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `IDETUDIANT` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDADMINISTRATEUR`),
  KEY `FK_ADMIN_ETUDIANT` (`IDETUDIANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `administrateur`
--

INSERT INTO `administrateur` (`IDADMINISTRATEUR`, `NOM`, `PRENOM`, `email`, `adresse`, `telephone`, `IDETUDIANT`) VALUES
(1234, 'Lo', 'Awa', 'loawa@gmail.com', 'kaolack', '769231196', 1509354),
(1235, 'Dembele', 'hawa', 'hawadembele@gmail.com', 'ouakam', '777693114', 1509390);

-- --------------------------------------------------------

--
-- Structure de la table `annee_universitaire`
--

CREATE TABLE IF NOT EXISTS `annee_universitaire` (
  `IDANNEE_UNIVERSITAIRE` int(11) NOT NULL DEFAULT '0',
  `DATE_DE_DEBUT` date DEFAULT NULL,
  `DATE_DE_FIN` date DEFAULT NULL,
  `nom_diplome` varchar(255) DEFAULT NULL,
  `etablissement_acceuil` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDANNEE_UNIVERSITAIRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `annee_universitaire`
--

INSERT INTO `annee_universitaire` (`IDANNEE_UNIVERSITAIRE`, `DATE_DE_DEBUT`, `DATE_DE_FIN`, `nom_diplome`, `etablissement_acceuil`) VALUES
(1019354, '2014-03-01', '2019-03-01', 'master', 'bambey'),
(1309354, '2017-03-01', '2022-03-01', 'master', 'Bambey'),
(1409390, '2019-03-01', '2023-03-01', 'licence', 'bambey'),
(1509054, '2020-11-03', NULL, 'bac', 'bambey'),
(1509354, '2020-03-23', '2024-03-04', 'licence', 'bambey'),
(1509390, '2020-03-23', '2024-03-04', 'licence', 'bambey');

-- --------------------------------------------------------

--
-- Structure de la table `consulte`
--

CREATE TABLE IF NOT EXISTS `consulte` (
  `ID_PERSONNEL` int(11) NOT NULL DEFAULT '0',
  `ID_ETUDIANT` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_PERSONNEL`,`ID_ETUDIANT`),
  KEY `ID_ETUDIANT` (`ID_ETUDIANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `derouler`
--

CREATE TABLE IF NOT EXISTS `derouler` (
  `IDETUDIANT` int(11) DEFAULT NULL,
  `IDANNEE_UNIVERSITAIRE` int(11) DEFAULT NULL,
  KEY `derouler_ibfk_1` (`IDETUDIANT`),
  KEY `derouler_ibfk_2` (`IDANNEE_UNIVERSITAIRE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `diplome_de_licence`
--

CREATE TABLE IF NOT EXISTS `diplome_de_licence` (
  `IDETUDIANT` int(11) NOT NULL DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `IDEMPLOI` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDETUDIANT`),
  KEY `diplome_de_licence_ibfk_1` (`IDEMPLOI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `diplome_de_licence`
--

INSERT INTO `diplome_de_licence` (`IDETUDIANT`, `NOM`, `PRENOM`, `adresse`, `email`, `telephone`, `IDEMPLOI`) VALUES
(1019354, 'Faye', 'Ibrahima', 'Dioffior', 'faye@gmail.com', '779937254', NULL),
(1309354, 'Ndiaye', 'coumba', 'Djollof', 'ndiaye@gmail.com', '702785634', NULL),
(1409390, 'Ba', 'Ousmane', 'ogo', 'ba@gmail.com', '759655565', NULL),
(1509354, 'Lo', 'Awa', 'kaolack', 'lo@gmail.com', '769231196', NULL),
(1509390, 'dembele', 'hawa', 'ouakam', 'dembele@gmail.com', '777693114', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `directeur_ciaq`
--

CREATE TABLE IF NOT EXISTS `directeur_ciaq` (
  `IDDIRECTEUR_CIAQ` int(11) NOT NULL DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `IDETUDIANT` int(11) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDDIRECTEUR_CIAQ`),
  KEY `FK_ETUDIANT_ID_CIAQ` (`IDETUDIANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `directeur_ciaq`
--

INSERT INTO `directeur_ciaq` (`IDDIRECTEUR_CIAQ`, `email`, `adresse`, `telephone`, `IDETUDIANT`, `NOM`, `PRENOM`) VALUES
(1234, 'awa@gmail.com', 'Kaolack', '769231196', NULL, 'Lo', 'Awa'),
(1235, 'hawa@gmail.com', 'Dakar', '777693114', NULL, 'Dembele', 'Hawa'),
(1236, 'ciaq@gmail.com', 'Dakar', '771234567', NULL, 'gueye', 'Dahirou');

-- --------------------------------------------------------

--
-- Structure de la table `directeur_scolarite`
--

CREATE TABLE IF NOT EXISTS `directeur_scolarite` (
  `IDDIRECTEUR_SCOLARITE` int(11) NOT NULL DEFAULT '0',
  `email` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `IDETUDIANT` int(11) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDDIRECTEUR_SCOLARITE`),
  KEY `FK_ETUDIANT_ID` (`IDETUDIANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `directeur_scolarite`
--

INSERT INTO `directeur_scolarite` (`IDDIRECTEUR_SCOLARITE`, `email`, `adresse`, `telephone`, `IDETUDIANT`, `NOM`, `PRENOM`) VALUES
(1234, 'awa@gmail.com', 'kaolack', '769231196', NULL, 'lo', 'Awa'),
(1235, 'hawa@gmail.com', 'ouakam', '777693114', NULL, 'dembele', 'hawa'),
(1237, 'scolarite@gmail.com', 'Thies', '761234567', NULL, 'yade', 'lamine');

-- --------------------------------------------------------

--
-- Structure de la table `emploi`
--

CREATE TABLE IF NOT EXISTS `emploi` (
  `IDEMPLOI` int(11) NOT NULL,
  `DATE_DEBUT` date DEFAULT NULL,
  `DATE_FIN` date DEFAULT NULL,
  `ENTREPRISE` varchar(255) DEFAULT NULL,
  `POSTE_OCCUPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDEMPLOI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `emploi`
--

INSERT INTO `emploi` (`IDEMPLOI`, `DATE_DEBUT`, `DATE_FIN`, `ENTREPRISE`, `POSTE_OCCUPE`) VALUES
(1019354, '2020-01-01', '2024-01-01', 'canada telecom', 'administrateur reseau'),
(1309354, '2020-12-01', '2025-12-01', 'sonatel senegal', 'devellopeur web'),
(1409390, '2023-09-01', '2025-09-01', 'sonatel senegal', 'maintenancier'),
(1509354, '2023-09-01', '2023-12-01', 'hayo telecom', 'technicien reseau'),
(1509390, '2023-09-01', '2023-12-01', 'senelec', 'technicien reseau');

-- --------------------------------------------------------

--
-- Structure de la table `encadrer`
--

CREATE TABLE IF NOT EXISTS `encadrer` (
  `IDENSEIGNANT` int(11) NOT NULL DEFAULT '0',
  `IDETUDIANT` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDENSEIGNANT`),
  KEY `FK_ETUDIANT_ENC` (`IDETUDIANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE IF NOT EXISTS `enseignant` (
  `IDENSEIGNANT` int(11) NOT NULL DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `MATIERE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDENSEIGNANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `inscription_master`
--

CREATE TABLE IF NOT EXISTS `inscription_master` (
  `IDINSCRIPTION_MASTER` int(11) NOT NULL DEFAULT '0',
  `niveau_master` varchar(255) DEFAULT NULL,
  `universite_acceuil` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDINSCRIPTION_MASTER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `inscription_master`
--

INSERT INTO `inscription_master` (`IDINSCRIPTION_MASTER`, `niveau_master`, `universite_acceuil`) VALUES
(1019354, 'master 2', 'bambey '),
(1309354, 'master 2', 'ucad"Sénégal"'),
(1409390, 'master 1', 'canada');

-- --------------------------------------------------------

--
-- Structure de la table `inscrire`
--

CREATE TABLE IF NOT EXISTS `inscrire` (
  `IDETUDIANT` int(11) DEFAULT NULL,
  `IDINSCRIPTION_MASTER` int(11) DEFAULT NULL,
  KEY `FK_IDETUDIANT` (`IDETUDIANT`),
  KEY `FK_IDINSCRIPTION_MASTER` (`IDINSCRIPTION_MASTER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `liste_amis`
--

CREATE TABLE IF NOT EXISTS `liste_amis` (
  `IDAMIS` int(11) NOT NULL DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `IDETUDIANT` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDAMIS`),
  KEY `FK_LISTE_AMIS_ETUDIANT` (`IDETUDIANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `liste_amis`
--

INSERT INTO `liste_amis` (`IDAMIS`, `NOM`, `PRENOM`, `IDETUDIANT`) VALUES
(1019354, 'Ndiaye', 'coumba', 1309354),
(1309354, 'Ba', 'ousmane', 1409390),
(1409390, 'lo', 'Awa', 1509354),
(1509354, 'dembele', 'hawa', 1509390),
(1509390, 'Faye', 'Ibrahima', 1019354);

-- --------------------------------------------------------

--
-- Structure de la table `personnel_uadb`
--

CREATE TABLE IF NOT EXISTS `personnel_uadb` (
  `IDPERSONNEL` int(11) NOT NULL DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  `POSTE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDPERSONNEL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `personnel_uadb`
--

INSERT INTO `personnel_uadb` (`IDPERSONNEL`, `NOM`, `PRENOM`, `EMAIL`, `ADRESSE`, `TELEPHONE`, `POSTE`) VALUES
(1234, 'lo', 'awa', 'awa@gmail.com', 'Kaolack', '769231196', 'administrateur'),
(1235, 'Dembele', 'Hawa', 'hawa@gmail.com', 'ouakam', '777693114', 'enseignant'),
(1236, 'gueye', 'Dahirou', 'dahirou@gmail.com', 'Dakar', '771234567', 'Directeur_ciaq'),
(1237, 'yade', 'Lamine', 'lamine@gmail.com', 'Thies', '761234567', 'Directeur_scolarite');

-- --------------------------------------------------------

--
-- Structure de la table `possede`
--

CREATE TABLE IF NOT EXISTS `possede` (
  `IDAMIS` int(11) DEFAULT NULL,
  `IDETUDIANT` int(11) DEFAULT NULL,
  KEY `possede_ibfk_3` (`IDAMIS`),
  KEY `FK_POSSEDE_ETUDIANT` (`IDETUDIANT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type_de_contrat`
--

CREATE TABLE IF NOT EXISTS `type_de_contrat` (
  `IDTYPE_DE_CONTRAT` int(11) NOT NULL DEFAULT '0',
  `nom` varchar(255) DEFAULT NULL,
  `IDEMPLOI` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDTYPE_DE_CONTRAT`),
  KEY `FK_TYPE_DE_CONTRAT_EMPLOI` (`IDEMPLOI`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `type_de_contrat`
--

INSERT INTO `type_de_contrat` (`IDTYPE_DE_CONTRAT`, `nom`, `IDEMPLOI`) VALUES
(10, 'stage', 1509354),
(11, 'stage', 1509390),
(12, 'CDD', 1309354),
(13, 'CDD', 1409390),
(14, 'CDD', 1019354);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `FK_ADMIN_ETUDIANT` FOREIGN KEY (`IDETUDIANT`) REFERENCES `diplome_de_licence` (`IDETUDIANT`);

--
-- Contraintes pour la table `consulte`
--
ALTER TABLE `consulte`
  ADD CONSTRAINT `consulte_ibfk_1` FOREIGN KEY (`ID_PERSONNEL`) REFERENCES `enseignant` (`IDENSEIGNANT`);

--
-- Contraintes pour la table `derouler`
--
ALTER TABLE `derouler`
  ADD CONSTRAINT `derouler_ibfk_1` FOREIGN KEY (`IDETUDIANT`) REFERENCES `enseignant` (`IDENSEIGNANT`),
  ADD CONSTRAINT `derouler_ibfk_2` FOREIGN KEY (`IDANNEE_UNIVERSITAIRE`) REFERENCES `annee_universitaire` (`IDANNEE_UNIVERSITAIRE`);

--
-- Contraintes pour la table `diplome_de_licence`
--
ALTER TABLE `diplome_de_licence`
  ADD CONSTRAINT `diplome_de_licence_ibfk_1` FOREIGN KEY (`IDEMPLOI`) REFERENCES `emploi` (`IDEMPLOI`);

--
-- Contraintes pour la table `directeur_ciaq`
--
ALTER TABLE `directeur_ciaq`
  ADD CONSTRAINT `FK_ETUDIANT_ID_CIAQ` FOREIGN KEY (`IDETUDIANT`) REFERENCES `diplome_de_licence` (`IDETUDIANT`);

--
-- Contraintes pour la table `directeur_scolarite`
--
ALTER TABLE `directeur_scolarite`
  ADD CONSTRAINT `FK_ETUDIANT_ID` FOREIGN KEY (`IDETUDIANT`) REFERENCES `diplome_de_licence` (`IDETUDIANT`);

--
-- Contraintes pour la table `encadrer`
--
ALTER TABLE `encadrer`
  ADD CONSTRAINT `FK_ENCADRER_ENSEIGNANT` FOREIGN KEY (`IDENSEIGNANT`) REFERENCES `enseignant` (`IDENSEIGNANT`),
  ADD CONSTRAINT `FK_ETUDIANT_ENC` FOREIGN KEY (`IDETUDIANT`) REFERENCES `diplome_de_licence` (`IDETUDIANT`);

--
-- Contraintes pour la table `inscrire`
--
ALTER TABLE `inscrire`
  ADD CONSTRAINT `FK_IDETUDIANT` FOREIGN KEY (`IDETUDIANT`) REFERENCES `diplome_de_licence` (`IDETUDIANT`),
  ADD CONSTRAINT `FK_IDINSCRIPTION_MASTER` FOREIGN KEY (`IDINSCRIPTION_MASTER`) REFERENCES `inscription_master` (`IDINSCRIPTION_MASTER`);

--
-- Contraintes pour la table `liste_amis`
--
ALTER TABLE `liste_amis`
  ADD CONSTRAINT `FK_LISTE_AMIS_ETUDIANT` FOREIGN KEY (`IDETUDIANT`) REFERENCES `diplome_de_licence` (`IDETUDIANT`);

--
-- Contraintes pour la table `possede`
--
ALTER TABLE `possede`
  ADD CONSTRAINT `FK_POSSEDE_ETUDIANT` FOREIGN KEY (`IDETUDIANT`) REFERENCES `diplome_de_licence` (`IDETUDIANT`),
  ADD CONSTRAINT `possede_ibfk_3` FOREIGN KEY (`IDAMIS`) REFERENCES `liste_amis` (`IDAMIS`),
  ADD CONSTRAINT `possede_ibfk_4` FOREIGN KEY (`IDAMIS`) REFERENCES `liste_amis` (`IDAMIS`),
  ADD CONSTRAINT `possede_ibfk_5` FOREIGN KEY (`IDAMIS`) REFERENCES `liste_amis` (`IDAMIS`),
  ADD CONSTRAINT `possede_ibfk_6` FOREIGN KEY (`IDAMIS`) REFERENCES `liste_amis` (`IDAMIS`);

--
-- Contraintes pour la table `type_de_contrat`
--
ALTER TABLE `type_de_contrat`
  ADD CONSTRAINT `FK_TYPE_DE_CONTRAT_EMPLOI` FOREIGN KEY (`IDEMPLOI`) REFERENCES `emploi` (`IDEMPLOI`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
