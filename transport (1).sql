-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 12, 2016 at 06:49 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `transport`
--
CREATE DATABASE IF NOT EXISTS `transport` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `transport`;

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE IF NOT EXISTS `admins` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(250) NOT NULL,
  `password` varchar(512) NOT NULL,
  `ID` varchar(25) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `buses`
--

CREATE TABLE IF NOT EXISTS `buses` (
  `bus_id` int(10) NOT NULL AUTO_INCREMENT,
  `bus_name` varchar(11) NOT NULL,
  `model` varchar(11) NOT NULL,
  `commissioned` date DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`bus_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `buses`
--

INSERT INTO `buses` (`bus_id`, `bus_name`, `model`, `commissioned`, `active`) VALUES
(1, 'Hanif', 'HM-22', '2016-08-01', 1),
(2, 'Star-line', 'MM-66', '2016-04-11', 1),
(3, 'GG-Man', 'A-55', '2016-08-24', 1),
(5, 'Shohah', 'KK2', '2016-08-31', 1),
(6, 'Star-gazer', '55-B', '2016-08-17', 0),
(7, 'Fantom', '778D', '2016-08-03', 0),
(8, 'Horn Drill', 'Fanom-33', '2016-08-09', 0),
(9, 'dada', 'dadsadasa', NULL, 1),
(10, 'dsfsd', 'fsdfds', NULL, 1),
(11, 'dasadasd', 'dadadadada', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `daily_schedule`
--

CREATE TABLE IF NOT EXISTS `daily_schedule` (
  `daily_schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`daily_schedule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `daily_schedule`
--

INSERT INTO `daily_schedule` (`daily_schedule_id`, `schedule_id`, `date`, `active`) VALUES
(1, 1, '2016-08-12', 1),
(2, 2, '2016-08-12', 1),
(4, 3, '2016-08-12', 1);

-- --------------------------------------------------------

--
-- Table structure for table `daily_ticket`
--

CREATE TABLE IF NOT EXISTS `daily_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(11) NOT NULL,
  `daily_schedule_id` int(11) NOT NULL,
  `booked` tinyint(4) NOT NULL DEFAULT '0',
  `confirmed` int(11) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Dumping data for table `daily_ticket`
--

INSERT INTO `daily_ticket` (`id`, `ticket_id`, `daily_schedule_id`, `booked`, `confirmed`, `active`) VALUES
(26, 1, 1, 0, 0, 1),
(27, 3, 2, 0, 0, 1),
(28, 6, 1, 0, 0, 1),
(29, 28, 1, 0, 0, 1),
(30, 24, 1, 0, 0, 1),
(31, 31, 1, 0, 0, 1),
(32, 19, 1, 0, 0, 1),
(33, 26, 1, 0, 0, 1),
(34, 1, 2, 0, 0, 1),
(35, 4, 2, 0, 0, 1),
(36, 8, 2, 0, 0, 1),
(37, 20, 2, 0, 0, 1),
(38, 25, 1, 0, 0, 1),
(39, 22, 1, 0, 0, 1),
(40, 21, 1, 0, 0, 1),
(41, 32, 1, 0, 0, 1),
(42, 2, 1, 0, 0, 1),
(43, 3, 1, 0, 0, 1),
(44, 31, 2, 0, 0, 1),
(45, 28, 2, 0, 0, 1),
(46, 2, 2, 0, 0, 1),
(47, 6, 2, 0, 0, 1),
(48, 5, 2, 0, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `keys`
--

CREATE TABLE IF NOT EXISTS `keys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `key` varchar(40) NOT NULL,
  `level` int(2) NOT NULL,
  `ignore_limits` tinyint(1) NOT NULL DEFAULT '0',
  `is_private_key` tinyint(1) NOT NULL DEFAULT '0',
  `ip_addresses` text,
  `date_created` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `keys`
--

INSERT INTO `keys` (`id`, `user_id`, `key`, `level`, `ignore_limits`, `is_private_key`, `ip_addresses`, `date_created`) VALUES
(1, 0, '1234', 0, 0, 0, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE IF NOT EXISTS `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(255) NOT NULL,
  `method` varchar(6) NOT NULL,
  `params` text,
  `api_key` varchar(40) NOT NULL,
  `ip_address` varchar(45) NOT NULL,
  `time` int(11) NOT NULL,
  `rtime` float DEFAULT NULL,
  `authorized` varchar(1) NOT NULL,
  `response_code` smallint(3) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `routes`
--

CREATE TABLE IF NOT EXISTS `routes` (
  `route_id` int(10) NOT NULL AUTO_INCREMENT,
  `destination` varchar(200) NOT NULL,
  `departs` varchar(200) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`route_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `routes`
--

INSERT INTO `routes` (`route_id`, `destination`, `departs`, `active`) VALUES
(1, 'Dhaka', 'Chittagong', 1),
(2, 'Dhaka', 'Rajshahi', 1),
(3, 'Jamalpur', 'Dhaka', 1),
(4, 'dadad', 'ddaa', 1),
(5, 'dadadada', 'dadad', 1),
(6, 'sdfsdf', 'fsdf', 1),
(7, 'Syhlet', 'Dhaka', 1),
(8, 'jh', 'jkbjkbjk', 1),
(9, 'dasdadadasda', 'dasdsad', 1),
(10, 'Moimonshing', 'Mogakhali', 1),
(11, 'dada', 'daasda', 1),
(12, 'dadada', 'dada', 1);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `schedule_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) NOT NULL,
  `bus_id` int(11) NOT NULL,
  `time` varchar(256) NOT NULL,
  `fare` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`schedule_id`, `route_id`, `bus_id`, `time`, `fare`, `active`) VALUES
(1, 1, 1, '01:00:00', 700, 1),
(2, 1, 5, '02:00:00', 500, 1),
(3, 1, 3, '03:00:00', 600, 1),
(4, 3, 1, '11Am', 500, 1),
(5, 7, 3, '12Pm', 1000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE IF NOT EXISTS `tickets` (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `seat_name` varchar(11) NOT NULL,
  PRIMARY KEY (`ticket_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`ticket_id`, `seat_name`) VALUES
(1, 'A1'),
(2, 'A2'),
(3, 'B2'),
(4, 'B1'),
(5, 'C1'),
(6, 'C2'),
(7, 'D2'),
(8, 'D1'),
(9, 'E2'),
(10, 'E1'),
(11, 'F1'),
(12, 'F2'),
(13, 'G1'),
(14, 'G2'),
(15, 'H1'),
(16, 'H2'),
(17, 'F3'),
(18, 'H4'),
(19, 'B1'),
(20, 'E3'),
(21, 'C4'),
(22, 'D4'),
(23, 'F4'),
(24, 'E4'),
(25, 'D3'),
(26, 'C3'),
(27, 'G3'),
(28, 'A4'),
(29, 'H3'),
(30, 'G4'),
(31, 'A3'),
(32, 'B2');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
