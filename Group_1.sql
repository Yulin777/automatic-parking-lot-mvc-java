-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 07, 2018 at 02:15 PM
-- Server version: 5.5.60-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `Group_1`
--

-- --------------------------------------------------------

--
-- Table structure for table `Budgets`
--

CREATE TABLE IF NOT EXISTS `Budgets` (
  `Num` int(11) NOT NULL,
  `Budget` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Budgets`
--

INSERT INTO `Budgets` (`Num`, `Budget`) VALUES
(2, 1700),
(3, 2000),
(4, 1000),
(5, 2400),
(6, 3100),
(7, 800);

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `client_ID` int(11) NOT NULL AUTO_INCREMENT,
  `client_first_name` varchar(30) NOT NULL,
  `client_last_name` varchar(30) NOT NULL,
  `client_type` varchar(30) NOT NULL,
  `client_email` varchar(100) NOT NULL,
  `client_telephone` varchar(12) NOT NULL,
  PRIMARY KEY (`client_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`client_ID`, `client_first_name`, `client_last_name`, `client_type`, `client_email`, `client_telephone`) VALUES
(1, 'yosi', 'cohen', 'temp', 'mail@mailsrv.com', '555555'),
(3, 'Jhon', 'Smith', 'temp', 'jhon@mail.com', '33333333'),
(4, 'Jhon2', 'Smith2', 'temp', 'jhon@mail2.com', '4444'),
(5, 'Jhon2', 'Smith2', 'temp', 'jhon@mail2.com', '4444'),
(6, 'Tal', 'Smith', 'regular', 'mail@test.com', '054-6961578'),
(7, 'Maor', 'Cohen', 'temp', 'maor@gmail.com', '052-5653407'),
(8, 'Yoni', 'Cohen', 'regular', 'yoni@gmail.com', '567-4449432'),
(9, 'moshe', 'choen', 'one-time', 'aaa@gmail.com', '04-99999'),
(10, 'moshe', 'choen', 'one-time', 'bbb@gmail.com', '04-59999'),
(11, 'snir', 'cohen', 'regular', 'james@bond.com', '047836453'),
(12, 'mmm', 'vvv', 'temp', 'aaa@gmail.com', '09-11111'),
(13, 'yoni', 'cohen', 'temp', 'mail@mail.com', '44455566'),
(14, 'mmm', 'nnn', 'temp', 'ssss@gmail.com', '88888');

-- --------------------------------------------------------

--
-- Table structure for table `Houses`
--

CREATE TABLE IF NOT EXISTS `Houses` (
  `Num` int(11) NOT NULL,
  `House` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Houses`
--

INSERT INTO `Houses` (`Num`, `House`) VALUES
(1, 'B'),
(2, 'A'),
(3, 'A'),
(4, 'B'),
(5, 'A'),
(6, 'B');

-- --------------------------------------------------------

--
-- Table structure for table `Players`
--

CREATE TABLE IF NOT EXISTS `Players` (
  `Player` int(11) NOT NULL,
  `Name` text NOT NULL,
  `Num` int(11) NOT NULL,
  `Age` int(11) NOT NULL,
  PRIMARY KEY (`Player`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Players`
--

INSERT INTO `Players` (`Player`, `Name`, `Num`, `Age`) VALUES
(1, 'Gur Shelef', 5, 32),
(2, 'Lior Eliyahu', 1, 23),
(3, 'Doron Shefer', 5, 36),
(4, 'Nadav Henefeld', 1, 40),
(5, 'Sharon Sason', 2, 28),
(6, 'Ido Kozikaro', 2, 35),
(7, 'Meir Tapiro', 2, 38),
(8, 'Adi Gordon', 3, 33),
(9, 'Tomer Stainahour', 3, 34),
(10, 'Shimon Amsalem', 4, 31);

-- --------------------------------------------------------

--
-- Table structure for table `Teams`
--

CREATE TABLE IF NOT EXISTS `Teams` (
  `Num` int(11) NOT NULL,
  `Team` text NOT NULL,
  `City` text NOT NULL,
  `Established` year(4) NOT NULL,
  `Coach` text NOT NULL,
  `Wins` int(11) NOT NULL,
  PRIMARY KEY (`Num`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Teams`
--

INSERT INTO `Teams` (`Num`, `Team`, `City`, `Established`, `Coach`, `Wins`) VALUES
(1, 'Maccabi', 'Tel-Aviv', 1960, 'Gershon', 12),
(2, 'Hapoel', 'Jerusalem', 1965, 'Druker', 9),
(3, 'Maccabi', 'Haifa', 1978, 'Ashkenazi', 6),
(4, 'Hapoel', 'Tel-Aviv', 1957, 'Ha-alion', 8),
(5, 'Hapoel', 'Galil-Elion', 1969, 'Katash', 11);

-- --------------------------------------------------------

--
-- Table structure for table `workers`
--

CREATE TABLE IF NOT EXISTS `workers` (
  `worker_id` int(11) NOT NULL AUTO_INCREMENT,
  `worker_Fname` varchar(40) NOT NULL,
  `worker_Lname` varchar(40) NOT NULL,
  `worker_email` varchar(250) NOT NULL,
  `worker_password` varchar(400) NOT NULL,
  `worker_phone` varchar(12) NOT NULL,
  `worker_access_level` int(11) NOT NULL,
  PRIMARY KEY (`worker_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `workers`
--

INSERT INTO `workers` (`worker_id`, `worker_Fname`, `worker_Lname`, `worker_email`, `worker_password`, `worker_phone`, `worker_access_level`) VALUES
(1, 'יוסי', 'כהן', 'yosi@gmail.com', 'e38ad214943daad1d64c102faec29de4afe9da3d', '0545843958', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
