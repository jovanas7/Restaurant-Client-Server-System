/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - restoran_nova
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`restoran_nova` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `restoran_nova`;

/*Table structure for table `artikal` */

DROP TABLE IF EXISTS `artikal`;

CREATE TABLE `artikal` (
  `idArtikal` int(11) NOT NULL AUTO_INCREMENT,
  `nazivArtikla` varchar(255) NOT NULL,
  `tipArtikla` varchar(50) NOT NULL,
  `cenaBezPdv` decimal(10,2) NOT NULL,
  `dostupan` tinyint(1) NOT NULL,
  `pdv` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idArtikal`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `artikal` */

insert  into `artikal`(`idArtikal`,`nazivArtikla`,`tipArtikla`,`cenaBezPdv`,`dostupan`,`pdv`) values 
(1,'Pizza Margherita','HRANA',850.00,1,10.00),
(2,'Pizza Capricciosa','HRANA',1100.00,1,10.00),
(3,'Pasta Carbonara','HRANA',950.00,1,10.00),
(4,'Lasagna','HRANA',1150.00,1,10.00),
(5,'Tiramisu','HRANA',520.00,0,10.00),
(6,'Espresso','PICE',180.00,1,20.00),
(7,'Cappuccino','PICE',220.00,1,20.00),
(8,'Mineralna voda 0.5','PICE',150.00,1,20.00),
(9,'Aperol Spritz','PICE',500.00,1,20.00),
(10,'Prosecco','PICE',450.00,0,20.00);

/*Table structure for table `gost` */

DROP TABLE IF EXISTS `gost`;

CREATE TABLE `gost` (
  `idGost` int(11) NOT NULL AUTO_INCREMENT,
  `imeGosta` varchar(100) NOT NULL,
  `prezimeGosta` varchar(100) NOT NULL,
  `idTipGosta` int(11) NOT NULL,
  PRIMARY KEY (`idGost`),
  KEY `tipGosta` (`idTipGosta`),
  CONSTRAINT `gost_ibfk_1` FOREIGN KEY (`idTipGosta`) REFERENCES `tipgosta` (`idTipGosta`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `gost` */

insert  into `gost`(`idGost`,`imeGosta`,`prezimeGosta`,`idTipGosta`) values 
(1,'Nadja','Ristic',1),
(2,'Stasa','Milosavic',2),
(3,'Anja','Popovic',1),
(5,'Vesna','Petkovic',1),
(6,'Teodora','Stojiljkovic',2),
(7,'Stefan','Tasic',1),
(8,'Lea','Arsic',1);

/*Table structure for table `konobar` */

DROP TABLE IF EXISTS `konobar`;

CREATE TABLE `konobar` (
  `idKonobar` int(11) NOT NULL AUTO_INCREMENT,
  `korisnickoIme` varchar(100) NOT NULL,
  `sifra` varchar(100) NOT NULL,
  `imeKonobara` varchar(100) NOT NULL,
  `prezimeKonobara` varchar(100) NOT NULL,
  `datumZaposlenja` date DEFAULT NULL,
  `datumIstekaUgovora` date DEFAULT NULL,
  PRIMARY KEY (`idKonobar`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `konobar` */

insert  into `konobar`(`idKonobar`,`korisnickoIme`,`sifra`,`imeKonobara`,`prezimeKonobara`,`datumZaposlenja`,`datumIstekaUgovora`) values 
(1,'jelena','jelena123','Jelena','Stojanović','2023-03-15','2026-03-15'),
(2,'jovana','jovana123','Jovana','Stojanović','2022-06-01','2025-06-01');

/*Table structure for table `konobarkvalifikacija` */

DROP TABLE IF EXISTS `konobarkvalifikacija`;

CREATE TABLE `konobarkvalifikacija` (
  `idKonobar` int(11) NOT NULL,
  `idKvalifikacija` int(11) NOT NULL,
  `datumDodele` date DEFAULT NULL,
  PRIMARY KEY (`idKonobar`,`idKvalifikacija`),
  KEY `idKvalifikacija` (`idKvalifikacija`),
  CONSTRAINT `konobarkvalifikacija_ibfk_1` FOREIGN KEY (`idKonobar`) REFERENCES `konobar` (`idKonobar`),
  CONSTRAINT `konobarkvalifikacija_ibfk_2` FOREIGN KEY (`idKvalifikacija`) REFERENCES `kvalifikacija` (`idKvalifikacija`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `konobarkvalifikacija` */

insert  into `konobarkvalifikacija`(`idKonobar`,`idKvalifikacija`,`datumDodele`) values 
(1,1,'2024-01-10'),
(2,2,'2024-02-15');

/*Table structure for table `kvalifikacija` */

DROP TABLE IF EXISTS `kvalifikacija`;

CREATE TABLE `kvalifikacija` (
  `idKvalifikacija` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `opis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idKvalifikacija`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kvalifikacija` */

insert  into `kvalifikacija`(`idKvalifikacija`,`naziv`,`opis`) values 
(1,'Koktel majstor','Priprema koktele i mešana pića profesionalno'),
(2,'Konobar-somelijer','Poznavanje vina i preporuka jela i pića');

/*Table structure for table `narudzbina` */

DROP TABLE IF EXISTS `narudzbina`;

CREATE TABLE `narudzbina` (
  `idNarudzbina` int(11) NOT NULL AUTO_INCREMENT,
  `datumNarucivanja` date DEFAULT NULL,
  `ukupanIznos` decimal(10,2) DEFAULT NULL,
  `nacinPlacanja` varchar(50) DEFAULT NULL,
  `napomena` varchar(255) DEFAULT NULL,
  `idKonobar` int(11) NOT NULL,
  `idGost` int(11) NOT NULL,
  PRIMARY KEY (`idNarudzbina`),
  KEY `idKonobar` (`idKonobar`),
  KEY `idGost` (`idGost`),
  CONSTRAINT `narudzbina_ibfk_1` FOREIGN KEY (`idKonobar`) REFERENCES `konobar` (`idKonobar`),
  CONSTRAINT `narudzbina_ibfk_2` FOREIGN KEY (`idGost`) REFERENCES `gost` (`idGost`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `narudzbina` */

insert  into `narudzbina`(`idNarudzbina`,`datumNarucivanja`,`ukupanIznos`,`nacinPlacanja`,`napomena`,`idKonobar`,`idGost`) values 
(3,'2026-04-19',216.00,'KARTICA',NULL,2,5),
(4,'2026-04-19',1715.00,'KARTICA',NULL,1,1),
(5,'2026-04-24',3685.00,'KARTICA',NULL,2,2),
(6,'2026-05-18',2310.00,'KARTICA','dostava',2,3),
(7,'2026-07-04',1715.00,'KARTICA','',2,5);

/*Table structure for table `stavkanarudzbine` */

DROP TABLE IF EXISTS `stavkanarudzbine`;

CREATE TABLE `stavkanarudzbine` (
  `idNarudzbina` int(11) NOT NULL,
  `rb` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `cenaSaPdv` decimal(10,2) DEFAULT NULL,
  `ukupnaVrednost` decimal(10,2) DEFAULT NULL,
  `idArtikal` int(11) NOT NULL,
  PRIMARY KEY (`idNarudzbina`,`rb`),
  KEY `idArtikal` (`idArtikal`),
  CONSTRAINT `stavkanarudzbine_ibfk_1` FOREIGN KEY (`idNarudzbina`) REFERENCES `narudzbina` (`idNarudzbina`),
  CONSTRAINT `stavkanarudzbine_ibfk_2` FOREIGN KEY (`idArtikal`) REFERENCES `artikal` (`idArtikal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkanarudzbine` */

insert  into `stavkanarudzbine`(`idNarudzbina`,`rb`,`kolicina`,`cenaSaPdv`,`ukupnaVrednost`,`idArtikal`) values 
(3,1,1,216.00,216.00,6),
(4,1,1,935.00,935.00,1),
(4,2,1,600.00,600.00,9),
(4,3,1,180.00,180.00,8),
(5,1,2,1210.00,2420.00,2),
(5,2,1,1265.00,1265.00,4),
(6,1,1,1045.00,1045.00,3),
(6,2,1,1265.00,1265.00,4),
(7,1,1,935.00,935.00,1),
(7,2,1,600.00,600.00,9),
(7,3,1,180.00,180.00,8);

/*Table structure for table `tipgosta` */

DROP TABLE IF EXISTS `tipgosta`;

CREATE TABLE `tipgosta` (
  `idTipGosta` int(11) NOT NULL AUTO_INCREMENT,
  `nazivTipa` varchar(100) NOT NULL,
  `opisTipa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idTipGosta`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `tipgosta` */

insert  into `tipgosta`(`idTipGosta`,`nazivTipa`,`opisTipa`) values 
(1,'REGULAR','Običan gost restorana'),
(2,'VIP','VIP gost sa posebnim tretmanom');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
