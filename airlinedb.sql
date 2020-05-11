/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.1.36-MariaDB : Database - airlinedb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`airlinedb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `airlinedb`;

/*Table structure for table `tbl_customer` */

DROP TABLE IF EXISTS `tbl_customer`;

CREATE TABLE `tbl_customer` (
  `Customer_No` varchar(20) NOT NULL,
  `Firstname` varchar(50) NOT NULL,
  `Lastname` varchar(50) NOT NULL,
  `Middlename` varchar(50) NOT NULL,
  `PassportID` varchar(30) NOT NULL,
  `Birthdate` varchar(20) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Contact` varchar(11) NOT NULL,
  `Address` text NOT NULL,
  `Date_Created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Customer_No`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_customer` */

/*Table structure for table `tbl_flight` */

DROP TABLE IF EXISTS `tbl_flight`;

CREATE TABLE `tbl_flight` (
  `FlightID` varchar(20) NOT NULL,
  `Customer_No` varchar(20) DEFAULT NULL,
  `Flight_Name` varchar(50) NOT NULL,
  `Source` varchar(50) NOT NULL,
  `Departure` varchar(50) NOT NULL,
  `Setflight` varchar(50) NOT NULL,
  `Departure_Time` varchar(10) NOT NULL,
  `Air_Time` varchar(10) NOT NULL,
  `Flight_Charge` varchar(10) NOT NULL,
  PRIMARY KEY (`FlightID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_flight` */

/*Table structure for table `tbl_ticket` */

DROP TABLE IF EXISTS `tbl_ticket`;

CREATE TABLE `tbl_ticket` (
  `TicketNo` varchar(20) NOT NULL,
  `CustomerNo` varchar(20) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `PassportID` varchar(20) NOT NULL,
  `FlightNo` varchar(20) NOT NULL,
  `FlightName` varchar(50) NOT NULL,
  `Dept_Time` varchar(20) NOT NULL,
  `Class` varchar(20) NOT NULL,
  `Seat` int(11) NOT NULL,
  `Price` double NOT NULL,
  `Amount` double NOT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `Date_Record` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`TicketNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_ticket` */

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Secret_Question` varchar(50) NOT NULL,
  `Secret_Answer` varchar(50) NOT NULL,
  `Date_Login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_user` */

insert  into `tbl_user`(`UserID`,`Username`,`Password`,`Secret_Question`,`Secret_Answer`,`Date_Login`) values 
(2,'admin','admin','What is your favorite color?','black','2020-05-09 16:13:49'),
(3,'yaw','yaw123','What is your contact number?','123','2020-05-10 10:20:44'),
(4,'JohnRay','john12345','What is your favorite color?','Gray','2020-05-10 10:26:12');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
