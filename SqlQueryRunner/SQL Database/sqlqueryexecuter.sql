-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2018 at 07:08 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sqlqueryexecuter`
--

-- --------------------------------------------------------

--
-- Table structure for table `firsttable`
--

CREATE TABLE `firsttable` (
  `Col1` varchar(3) DEFAULT NULL,
  `Col2` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `firsttable`
--

INSERT INTO `firsttable` (`Col1`, `Col2`) VALUES
('abc', 'aa'),
('Ty', 'HE');

-- --------------------------------------------------------

--
-- Table structure for table `firsttablev2`
--

CREATE TABLE `firsttablev2` (
  `Col1` varchar(4) DEFAULT NULL,
  `Col2` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `firsttablev2`
--

INSERT INTO `firsttablev2` (`Col1`, `Col2`) VALUES
('3A', '5B'),
('3D', '56G');

-- --------------------------------------------------------

--
-- Table structure for table `hello`
--

CREATE TABLE `hello` (
  `Col1` varchar(13) DEFAULT NULL,
  `sadas` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hello`
--

INSERT INTO `hello` (`Col1`, `sadas`) VALUES
('Tyagi', 'IS GREAT');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
