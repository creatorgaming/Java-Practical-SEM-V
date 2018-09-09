-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 09, 2018 at 07:27 PM
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
-- Database: `banking`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountholderinfo`
--

CREATE TABLE `accountholderinfo` (
  `acno` int(12) NOT NULL COMMENT 'Account Number',
  `name` varchar(50) NOT NULL COMMENT 'Name of accountee',
  `age` int(3) NOT NULL COMMENT 'Age of accountee',
  `phoneNo` varchar(100) NOT NULL COMMENT 'Phone Number of accountee',
  `currentBalance` int(20) NOT NULL COMMENT 'Current Balance of accountee'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accountholderinfo`
--

INSERT INTO `accountholderinfo` (`acno`, `name`, `age`, `phoneNo`, `currentBalance`) VALUES
(1234, 'test', 21, '9968505442', 8900),
(1231124, 'Divyanshu Tyagi', 19, '9968505442', 5000),
(11212254, 'Divyanshu Tyagi', 19, '99680505442', 0),
(123456789, 'taygi', 22, '848532413', 0);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `acno` int(12) NOT NULL,
  `date` date NOT NULL COMMENT 'Date of transaction',
  `amountDeposited` int(50) DEFAULT NULL COMMENT 'Amount Deposited',
  `amountWithdrawn` int(50) DEFAULT NULL COMMENT 'Amount Withdrawn',
  `balance` int(50) NOT NULL COMMENT 'Balance After Transaction'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for transactions of user';

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`acno`, `date`, `amountDeposited`, `amountWithdrawn`, `balance`) VALUES
(1234, '2018-09-08', 800, NULL, 8900),
(1234, '2018-09-08', 100, NULL, 9000),
(1234, '2018-09-08', NULL, 100, 8900),
(1231124, '2018-09-08', 5000, NULL, 5000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountholderinfo`
--
ALTER TABLE `accountholderinfo`
  ADD PRIMARY KEY (`acno`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
