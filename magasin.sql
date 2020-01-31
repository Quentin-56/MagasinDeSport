-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 31 jan. 2020 à 17:08
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `magasin`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `idArticle` int(11) NOT NULL AUTO_INCREMENT,
  `details` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix` double NOT NULL,
  `quantite` int(11) NOT NULL,
  `quantiteReserve` int(11) NOT NULL,
  `rayonA_idRayon` int(11) DEFAULT NULL,
  PRIMARY KEY (`idArticle`),
  KEY `FK8vc8h1sccxdyv5uvtabba97hq` (`rayonA_idRayon`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`idArticle`, `details`, `nom`, `prix`, `quantite`, `quantiteReserve`, `rayonA_idRayon`) VALUES
(2, 'Bonnet de natation arena smartcap - le port du bonnet s\'accompagne d\'une nouvelle gestuelle conçu pour cheveux longs - ergonomique, rapide à enfiler, esthétique, le bandeau maintient les cheveux bien en place. ', 'BONNET DE BAIN ARENA', 14.99, 12, 0, 1),
(3, 'Découvrez ces lunettes de protection zoggs predator, offrant une superbe durabilité grâce à leur technologie de cadre unique bio-tech ™, une conception durable.', 'LUNETTE DE NATATION ZOGGS PREDATOR', 17.99, 20, 0, 1),
(4, 'Le dos ouvert mais couvrant permet une liberté de mouvement pour la nageuse.', 'MAILLOT DE BAIN NATATION FILLE', 5.99, 30, 0, 1),
(5, 'Avec son très joli look et sa rayure colorée sur la jambe gauche, le Volley Sun\'s est un incontournable ultra tendance !\n', 'BOARDSHORT PLAGE HOMME', 12.99, 15, 3, 1),
(6, 'La serviette de sport nike est en tissu doux ultra-absorbant et agréable pour la peau. Tissu en coton premium doux et absorbant dimensions : 35,5 cm (l) x 81 cm (l) composition : 100 % coton lavable en machine', 'ADULTE NIKE SERVIETTE', 27.99, 23, 0, 1),
(7, 'Festival haut de maillot de bain col haut à rayures', 'HAUT DE MAILLOT DE BAIN COL HAUT À RAYURES', 11.9, 11, 0, 1),
(8, 'Sa forme boxer lui permet une liberté de mouvement. Matière permettant de s\'adapter aux formes', 'SLIP DE BAIN PISCINE HOMME ATHLITECH', 7.19, 38, 0, 1),
(9, 'Serviette piscine en microfibre, parfait pour se sécher grâce à sa capacité d\'absorption. Taille: 110x175cm.', 'SERVIETTE PISCINE ADULTE ATHLITECH', 4.49, 33, 0, 1),
(10, 'Améliore ta technique, une longueur à la fois. Ce maillot de bain est conçu dans une matière douce qui résiste au chlore, issue de filets de pêche recyclés et autres nylons collectés.', 'MAILLOT DE BAIN 1 PIECE NATATION FEMME ', 27.99, 15, 0, 1),
(11, 'Reste concentré, protégé de toute distractions. Ce pantalon d\'entraînement de football est conçu en tissu aéré qui sèche rapidement pour te garder au frais sur le terrain.', 'JOGGING FOOTBALL HOMME ', 34.99, 24, 0, 2),
(12, 'Le survêtement est idéal pour vos échauffements et vos phases de récupération. Son tissu extensible intègre une technologie anti-transpiration pour vous garder au sec tout en vous offrant une liberté de mouvement optimale.', 'SURVETEMENT HOMME', 71.99, 36, 1, 2),
(13, 'Le sac de sport de training  pour Femme vous permet de conserver votre équipement au sec et organisé lors de vos allers-retours à la salle de sport grâce à sa matière résistante et patinée et à son compartiment principal zippé.', 'SAC DE SPORT OUTDOOR FEMME', 19.99, 14, 0, 2),
(14, 'Elles enveloppent le pied pour une vitesse profilée. Une plaque polyvalente multi-surfaces assure une excellente adhérence sur les surfaces naturelles et artificielles.', 'CHAUSSURES BASSES FOOTBALL JUNIOR', 34.99, 21, 0, 2),
(15, 'Montrez vos couleurs et soutenez votre équipe favorite avec la gamme d\'accessoires officiels Athlitech. Gants de terrain pour des performances optimales et un confort ultime par temps froid.', 'GANTS FOOTBALL MIXTE', 17.49, 21, 0, 2),
(16, 'Il présente une surface homogène pour des performances de haute qualité et un revêtement extérieur texturé qui améliore son vol et son toucher.', 'BALLON FOOTBALL', 24.95, 33, 0, 2),
(17, 'Les protège-tibias Lite Neymar combine une couverture ultra-fine avec un amorti et une diffusion des impacts supérieurs dans un design qui peut résister aux exigences du jeu de niveau élite. Une coque légère enveloppe le tibia pour un maintien naturel. ', 'PROTEGE-TIBIAS FOOTBALL MIXTE ', 18.89, 17, 0, 2),
(18, 'Genouillère anatomique rembourrée avec une mousse polyuréthanne assurant parfaitement son rôle de protection de la rotule. Genouillères unisexe pour adulte.', 'HANDBALL ERREA GENOUILLÈRE', 18.79, 22, 0, 3),
(19, 'Le sport indoor aura désormais une saveur de victoire avec ce modèle Asics à vos pieds ! C\'est dans un coloris noir qu\'elle nous révèle sa plus belle facette', 'CHAUSSURES BASSES HANDBALL HOMME ', 79.99, 16, 0, 3),
(20, 'Composition : 75% Coton, 24% Nylon, 1% Lycra Pieds bouclette - chevrons Chaussettes Handball Authentic avec chevrons tissés', 'HANDBALL CHAUSSETTES AUTHENTIC INDOOR', 3.5, 25, 0, 3),
(21, 'Utilisation : match et entrainement Particularités : ballon certifié ffhb. Très bon confort de jeu Construction : 32 panneaux cousus main – vessie en latex « o wing » Matière : revêtement polyuréthane « honey comb » pour un meilleur grip', 'BALLON DE HANDBALL ENTRAINEMENT', 27.49, 9, 0, 3),
(22, 'Chaussures handball blanc pour homme', 'HANDBALL HOMME CHAUSSURE PERFORMANCE ESSENCE INDOOR', 55, 11, 0, 3);

-- --------------------------------------------------------

--
-- Structure de la table `chefmagasin`
--

DROP TABLE IF EXISTS `chefmagasin`;
CREATE TABLE IF NOT EXISTS `chefmagasin` (
  `idPersonne` int(11) NOT NULL,
  `magasin_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idPersonne`),
  KEY `FKjnvkbkwnk4e2m4io7ugmohjau` (`magasin_nom`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chefmagasin`
--

INSERT INTO `chefmagasin` (`idPersonne`, `magasin_nom`) VALUES
(1, 'PolySports');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
CREATE TABLE IF NOT EXISTS `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequences`
--

INSERT INTO `hibernate_sequences` (`sequence_name`, `next_val`) VALUES
('default', 0);

-- --------------------------------------------------------

--
-- Structure de la table `magasin`
--

DROP TABLE IF EXISTS `magasin`;
CREATE TABLE IF NOT EXISTS `magasin` (
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`nom`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `magasin`
--

INSERT INTO `magasin` (`nom`) VALUES
('PolySports');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `idPersonne` int(11) NOT NULL AUTO_INCREMENT,
  `identifiant` varchar(255) DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idPersonne`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`idPersonne`, `identifiant`, `motDePasse`, `nom`, `prenom`) VALUES
(1, 'admin', 'Adminsys1', 'Bondiot', 'Jean'),
(2, 'nicolas.miseine', 'azA3tgbs', 'Miseine', 'Nicolas'),
(3, 'mateo.falcao', 'veg5ZnkL', 'Falcao', 'Mateo'),
(4, 'marie.janotti', 'fnbdNA3b', 'Janotti', 'Marie'),
(5, 'steven.michard', 'chiOs9ens', 'Michard', 'Steven');

-- --------------------------------------------------------

--
-- Structure de la table `rayon`
--

DROP TABLE IF EXISTS `rayon`;
CREATE TABLE IF NOT EXISTS `rayon` (
  `idRayon` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  `magasin_nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idRayon`),
  KEY `FKr1w17s0tb8a6kmn97m3iekdw0` (`magasin_nom`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rayon`
--

INSERT INTO `rayon` (`idRayon`, `nom`, `magasin_nom`) VALUES
(1, 'Natation', NULL),
(2, 'Football', NULL),
(3, 'Handball', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `vendeur`
--

DROP TABLE IF EXISTS `vendeur`;
CREATE TABLE IF NOT EXISTS `vendeur` (
  `idPersonne` int(11) NOT NULL,
  `rayonV_idRayon` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPersonne`),
  KEY `FK60v16bc9v29m717wlnl49vm56` (`rayonV_idRayon`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vendeur`
--

INSERT INTO `vendeur` (`idPersonne`, `rayonV_idRayon`) VALUES
(2, 1),
(3, 2),
(4, 3),
(5, 3);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
