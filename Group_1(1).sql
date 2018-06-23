-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 23, 2018 at 05:40 PM
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for table `cars`
--

CREATE TABLE IF NOT EXISTS `cars` (
  `car_ID` varchar(7) NOT NULL DEFAULT '',
  `client_ID` varchar(9) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`car_ID`),
  KEY `client_ID` (`client_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`car_ID`, `client_ID`) VALUES
('890', '098'),
('111', '111'),
('1', '1111'),
('123111', '1111'),
('2121', '1121212'),
('123', '123'),
('666', '123123123'),
('1q', '1q'),
('asa', '2121'),
('asaa', '2121'),
('2222222', '222222222'),
('32', '3232'),
('322', '332'),
('3e', '3e'),
('2334', '42334'),
('234', '4234'),
('34', '43'),
('341', '431'),
('34g', '43g'),
('54', '54'),
('456656', '645645'),
('7', '7'),
('75', '75'),
('aaa', 'aaaa'),
('aq', 'aq'),
('bg', 'bg'),
('cd', 'cd'),
('eee', 'ee'),
('verrtf', 'errte'),
('vert', 'ert'),
('vertf', 'erte'),
('df', 'fd'),
('fefe', 'fefe'),
('fre', 'fre'),
('345g', 'g345'),
('g465', 'g46'),
('ge45', 'ge4g'),
('eg5', 'ge5'),
('g456g', 'ge6'),
('gdf', 'gfd'),
('gg', 'gg'),
('hihi', 'hihi'),
('iviv', 'iviv'),
('12', 'lkdjgflkj'),
('okm', 'okm'),
('poopi', 'poopi'),
('popo', 'popo'),
('q12', 'q12'),
('qq2', 'q2'),
('qq', 'qq1'),
('q', 'qqqq'),
('W', 'QWQW'),
('rrr', 'rr'),
('sw', 'sw'),
('terte', 'treter'),
('w2', 'w2'),
('wiwi', 'WABALABAi'),
('we', 'we'),
('xs', 'xs'),
('xsa', 'xsa'),
('xz', 'xz'),
('xza', 'xza'),
('i67', 'y76'),
('t6y', 'yt6'),
('zq', 'zq'),
('z', 'zzz');

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `client_ID` varchar(9) CHARACTER SET latin1 NOT NULL DEFAULT '00000000',
  `client_first_name` varchar(30) CHARACTER SET latin1 NOT NULL,
  `client_last_name` varchar(30) CHARACTER SET latin1 NOT NULL,
  `client_password` text CHARACTER SET latin1 NOT NULL,
  `client_type` varchar(30) CHARACTER SET latin1 NOT NULL,
  `client_email` varchar(100) CHARACTER SET latin1 NOT NULL,
  `client_telephone` varchar(12) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`client_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`client_ID`, `client_first_name`, `client_last_name`, `client_password`, `client_type`, `client_email`, `client_telephone`) VALUES
('098', '890', '890', 'Advanced', 'ADVANCED', '890', '890'),
('1', 'yosi', 'cohen', 'aaa', 'temp', 'mail@mailsrv.com', '555555'),
('109087', '123', '123', '123', 'SUBSCRIBED', '123', '123'),
('111', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '11:11', 'Occasional'),
('1111', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'xc', 'Occasional'),
('111111', 'snir', 'vit', 'Advanced', 'ADVANCED', '123@123', '123123'),
('1111111', '1', '1', 'Advanced', 'ADVANCED', '1', '1'),
('11111111', '1', '1', 'Advanced', 'ADVANCED', '1', '1'),
('111111111', 'snir', 'vit', 'Advanced', 'ADVANCED', '123@123', '123123'),
('1121212', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '22', 'Occasional'),
('123', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '123', 'Occasional'),
('123123123', 'kaki', 'kaki', 'Advanced', 'ADVANCED', '666@666', '11111'),
('1q', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '1q', 'Occasional'),
('204594156', 'ad', 'matai', 'mtai', 'ad', 'Advanced', 'ADVANCED'),
('2121', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '21', 'Occasional'),
('222222222', 'vitrack', 'tamam', 'Advanced', 'ADVANCED', '222@222', '2222222'),
('3232', '32', '32', 'Advanced', 'ADVANCED', '32', '32'),
('332', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '32', 'Occasional'),
('3e', '3e', '3e', 'Advanced', 'ADVANCED', '3e', '3e'),
('42334', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '234234', 'Occasional'),
('4234', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '234234', 'Occasional'),
('43', '43', '43', 'Advanced', 'ADVANCED', '43', '43'),
('431', '431', '431', 'Advanced', 'ADVANCED', '431', '431'),
('43g', '34g', '3g4g', 'Advanced', 'ADVANCED', '3g', '34gg'),
('54', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '54', 'Occasional'),
('645645', '456456', '456456', 'Advanced', 'ADVANCED', '456565', '456456'),
('7', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '7', 'Occasional'),
('75', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '7', 'Occasional'),
('aaaa', 'aa', 'aaa', 'Advanced', 'ADVANCED', 'aa', 'aaa'),
('aq', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'aq', 'Occasional'),
('asa', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '21', 'Occasional'),
('asaa', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '21', 'Occasional'),
('bg', 'bg', 'gb', 'Advanced', 'ADVANCED', 'bg', 'bg'),
('c123', '231', '1c23', 'c123', 'SUBSCRIBED', 'c123', 'c123'),
('cd', 'cdc', 'dcd', 'Advanced', 'ADVANCED', 'cd', 'cd'),
('cxcx', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'xc', 'Occasional'),
('ee', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'e', 'Occasional'),
('errte', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '1', 'Occasional'),
('ert', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'wewr', 'Occasional'),
('erte', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'wewr', 'Occasional'),
('evgenia', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'evgenia', 'Occasional'),
('fd', 'fd', 'fd', 'Advanced', 'ADVANCED', 'df', 'fd'),
('fefe', 'fefe', 'fefe', 'Advanced', 'ADVANCED', 'fefe', 'fefe'),
('fre', 'fre', 'fre', 'Advanced', 'ADVANCED', 'fre', 'fre'),
('g345', 'g345', 'g345', 'Advanced', 'ADVANCED', 'g34', 'g345'),
('g46', 'g346', 'g4', 'Advanced', 'ADVANCED', '456g', '456g'),
('ge4g', 'ge45', 'ge45', 'Advanced', 'ADVANCED', 'ge45', 'g45'),
('ge5', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'v45', 'Occasional'),
('ge6', 'ge5', 'g4', 'Advanced', 'ADVANCED', '456g', 'gge4'),
('gfd', 'gfd', 'gdf', 'Advanced', 'ADVANCED', 'gdf', 'gdf'),
('gg', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'g', 'Occasional'),
('hihi', 'hihi', 'hihi', 'Advanced', 'ADVANCED', 'hihi', 'hihi'),
('ivan', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'ivan', 'Occasional'),
('iviv', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'iviv', 'Occasional'),
('lkdjgflkj', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'sdf', 'Occasional'),
('oi', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'oi', 'Occasional'),
('okm', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'okm', 'Occasional'),
('papa', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'papa', 'Occasional'),
('poopi', 'poopi', 'poopi', 'poopi', 'SUBSCRIBED', 'poopi', 'poopi'),
('popo', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'popo', 'Occasional'),
('q', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '1', 'Occasional'),
('q12', '2', 'QQ', 'Advanced', 'ADVANCED', 'Q', 'Q'),
('q1q', 'q', 'q', 'Advanced', 'ADVANCED', 'qq', 'qq'),
('q2', 'q', 'q', 'Advanced', 'ADVANCED', 'qq', 'qq'),
('qq', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '1', 'Occasional'),
('qq1', 'qq', 'qq', 'Advanced', 'ADVANCED', 'q', 'q'),
('qqq', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '111', 'Occasional'),
('qqqq', 'qqq', 'q', 'Advanced', 'ADVANCED', 'qq', 'qqq'),
('qw', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '1', 'Occasional'),
('QWQW', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '11', 'Occasional'),
('rr', '1', '1', 'Advanced', 'ADVANCED', 'r', '1'),
('sa', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'sa', 'Occasional'),
('sniro', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'sniro', 'Occasional'),
('snirski', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'snirski', 'Occasional'),
('snirsnir', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'snirsnir', 'Occasional'),
('snirvt', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'snirvt', 'Occasional'),
('ss', 'dddd', 'sss', 'sss', 'SUBSCRIBED', 'sss', 's'),
('sw', 'sw', 'sw', 'Advanced', 'ADVANCED', 'sw', 'sw'),
('talya', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'talya', 'Occasional'),
('treter', 'terter', 'terte', 'Advanced', 'ADVANCED', 'teter', 'terte'),
('w2', 'w2', 'w2', 'Advanced', 'ADVANCED', 'w2', 'w2'),
('WABALABA', 'WABALABA', 'WABALABA', 'WABALABA', 'SUBSCRIBED', 'WABALABA', 'WABALABA'),
('WABALABAi', 'WABALABAi', 'WABALABA', 'WABALABA', 'SUBSCRIBED', 'WABALABA', 'WABALABA'),
('we', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '11:11', 'Occasional'),
('xs', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'xs', 'Occasional'),
('xsa', 'xsa', 'xsa', 'Advanced', 'ADVANCED', 'xsa', 'xsa'),
('xz', 'xz', 'xz', 'Advanced', 'ADVANCED', 'xz', 'xz'),
('xza', 'xza', 'xza', 'Advanced', 'ADVANCED', 'xza', 'xza'),
('y76', '67i', 'i67', 'Advanced', 'ADVANCED', '67i', '67i'),
('yt6', 't6y', 'yt6', 'Advanced', 'ADVANCED', 'yt6', 't6y'),
('zq', 'zq', 'zq', 'Advanced', 'ADVANCED', 'zq', 'zq'),
('zzz', 'z', 'zz', 'Advanced', 'ADVANCED', 'z', 'zz');

-- --------------------------------------------------------

--
-- Table structure for table `complaints`
--

CREATE TABLE IF NOT EXISTS `complaints` (
  `complaint_number` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `client_ID` varchar(9) CHARACTER SET latin1 DEFAULT NULL,
  `attendant_ID` varchar(9) CHARACTER SET latin1 DEFAULT NULL,
  `compensation` float DEFAULT '0',
  `description` text,
  `response` text,
  PRIMARY KEY (`complaint_number`),
  KEY `client_ID` (`client_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `complaints`
--

INSERT INTO `complaints` (`complaint_number`, `date`, `client_ID`, `attendant_ID`, `compensation`, `description`, `response`) VALUES
(4, '2018-06-23 14:07:56', '1', NULL, 0, '1somelonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglongtext g', NULL),
(7, '2018-06-23 13:09:39', '2', NULL, 0, '2TAKE2ONsomelonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglongtext', NULL),
(8, '2018-06-23 13:15:20', '1', NULL, 0, '1somelonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglongtext', NULL),
(9, '2018-06-23 14:11:16', '1', '2', 0, '1somelonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglongtext', NULL),
(10, '2018-06-23 14:36:06', '1', '3', 15666.7, '1somelonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglonglongtext', NULL),
(11, '2018-06-23 13:20:44', '2', NULL, 0, 'TAKE 2 ON some long long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long long text ', NULL),
(13, '2018-06-23 14:35:06', '2', '1', 37.5, 'TAKE 3 ON some long long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long text ', '37.5');

-- --------------------------------------------------------

--
-- Table structure for table `Houses`
--

CREATE TABLE IF NOT EXISTS `Houses` (
  `Num` int(11) NOT NULL,
  `House` varchar(1) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Table structure for table `Messages`
--

CREATE TABLE IF NOT EXISTS `Messages` (
  `messages_id` int(11) NOT NULL AUTO_INCREMENT,
  `messages_text` varchar(50) NOT NULL,
  `messages_confirmation` int(11) NOT NULL,
  `client_ID` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`messages_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=58 ;

--
-- Dumping data for table `Messages`
--

INSERT INTO `Messages` (`messages_id`, `messages_text`, `messages_confirmation`, `client_ID`) VALUES
(26, 'your subscription is end in a week', 0, '1'),
(27, 'your subscription is end in a week', 0, '1'),
(28, 'your subscription is end in a week', 0, '1'),
(29, 'your subscription is end in a week', 0, '1'),
(30, 'your subscription is end in a week', 0, '1'),
(31, 'your subscription is end in a week', 0, '1'),
(32, 'your subscription is end in a week', 0, '1'),
(33, 'your subscription is end in a week', 0, '1'),
(34, 'your subscription is end in a week', 0, '1'),
(35, 'your subscription is end in a week', 0, '1'),
(36, 'your subscription is end in a week', 0, '1'),
(37, 'your subscription is end in a week', 0, '1'),
(38, 'your subscription is end in a week', 0, '1'),
(39, 'your subscription is end in a week', 0, '1'),
(40, 'your subscription is end in a week', 0, '1'),
(41, 'your subscription is end in a week', 0, '1'),
(42, 'your subscription is end in a week', 0, '1'),
(43, 'your subscription is end in a week', 0, '1'),
(44, 'your subscription is end in a week', 0, '1'),
(45, 'your subscription is end in a week', 0, '1'),
(46, 'your subscription is end in a week', 0, '1'),
(47, 'your subscription is end in a week', 0, '1'),
(48, 'your subscription is end in a week', 0, '1'),
(49, 'your subscription is end in a week', 0, '1'),
(50, 'your subscription is end in a week', 0, '1'),
(51, 'you are late to order 21', 0, NULL),
(52, 'your subscription is end in a week', 0, '1'),
(53, 'you are late to order 21', 0, NULL),
(54, 'your subscription is end in a week', 0, '1'),
(55, 'your subscription is end in a week', 0, '1'),
(56, 'your subscription is end in a week', 0, '1'),
(57, 'your subscription is end in a week', 0, '1');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_status` varchar(50) NOT NULL,
  `order_car_id` varchar(50) NOT NULL,
  `order_payment_method` varchar(50) NOT NULL DEFAULT 'CREDIT',
  `order_type` varchar(50) NOT NULL,
  `order_parking_id` int(10) DEFAULT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `order_price` double DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `order_car_id` (`order_car_id`),
  KEY `order_parking_id` (`order_parking_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=57 ;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `order_status`, `order_car_id`, `order_payment_method`, `order_type`, `order_parking_id`, `start_date`, `end_date`, `order_price`) VALUES
(21, 'PENDING', 'q12', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-23 08:22:00', '2018-06-25 21:00:00', 0),
(22, 'PENDING', 'xza', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-24 08:11:00', '2018-06-25 21:00:00', NULL),
(23, 'PENDING', 'gdf', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-24 08:11:00', '2018-06-24 21:00:00', NULL),
(24, 'PENDING', 'cd', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-24 08:11:00', '2018-06-24 21:00:00', NULL),
(25, 'PENDING', 'zq', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-24 08:11:00', '2018-06-24 21:00:00', NULL),
(26, 'PENDING', 'aaa', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-24 08:11:00', '2018-06-24 21:00:00', NULL),
(27, 'PENDING', 'z', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-24 08:11:00', '2018-06-24 21:00:00', NULL),
(33, 'PENDING', 'bg', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-25 08:11:00', '2018-06-25 21:00:00', NULL),
(34, 'PENDING', 'w2', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-24 08:11:00', '2018-06-24 21:00:00', NULL),
(35, 'PENDING', 'qq2', 'CREDIT', 'IN_ADVANCE', NULL, '2018-06-24 08:11:00', '2018-06-25 08:11:00', NULL),
(40, 'PENDING', '12', 'CREDIT', 'OCCASIONAL', 1, '2018-06-23 13:13:39', '2018-06-30 21:00:00', NULL),
(44, 'sa', 'sw', 'CREDIT', 'sa', 1, '2018-06-23 13:20:18', '0000-00-00 00:00:00', NULL),
(47, 'PENDING', '345g', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-24 09:12:00', '2018-06-25 09:12:00', NULL),
(48, 'PENDING', '32', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-25 08:11:00', '2018-06-26 08:11:00', NULL),
(49, 'ONGOING', 'eg5', 'CREDIT', 'OCCASIONAL', 1, '2018-06-23 13:27:54', '2018-06-24 19:22:00', NULL),
(50, 'PENDING', '3e', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-24 08:11:00', '2018-06-25 08:11:00', NULL),
(51, 'PENDING', 'g456g', 'CASH', 'IN_ADVANCE', 2, '2018-06-24 19:22:00', '2018-07-02 19:22:00', NULL),
(52, 'PENDING', '34', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-24 08:11:00', '2018-06-25 08:11:00', NULL),
(53, 'PENDING', '341', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-24 08:11:00', '2018-06-25 08:11:00', 24),
(54, 'PENDING', '322', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-23 14:05:48', '2018-06-24 08:11:00', 18.086666666666666),
(55, 'PENDING', '7', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-23 14:07:52', '2018-06-24 08:11:00', 18.052222222222223),
(56, 'PENDING', '75', 'CREDIT', 'IN_ADVANCE', 2, '2018-06-23 14:09:18', '2018-06-24 08:11:00', 54.084999999999994);

-- --------------------------------------------------------

--
-- Table structure for table `order_prices`
--

CREATE TABLE IF NOT EXISTS `order_prices` (
  `parking_id` int(11) NOT NULL,
  `order_type` varchar(50) NOT NULL,
  `order_price_per_hour` double NOT NULL,
  PRIMARY KEY (`parking_id`,`order_type`),
  KEY `parking_id` (`parking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_prices`
--

INSERT INTO `order_prices` (`parking_id`, `order_type`, `order_price_per_hour`) VALUES
(1, 'IN_ADVANCE', 1),
(1, 'OCCASIONAL', 2),
(1, 'SUBSCRIBED', 200),
(2, 'IN_ADVANCE', 3),
(2, 'OCCASIONAL', 4),
(2, 'SUBSCRIBED', 230);

-- --------------------------------------------------------

--
-- Table structure for table `ParkingStation`
--

CREATE TABLE IF NOT EXISTS `ParkingStation` (
  `parking_id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_address` varchar(500) NOT NULL,
  `parking_size` int(11) NOT NULL,
  PRIMARY KEY (`parking_id`),
  KEY `parking_id` (`parking_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `ParkingStation`
--

INSERT INTO `ParkingStation` (`parking_id`, `parking_address`, `parking_size`) VALUES
(1, 'TelAviv', 5),
(2, 'Haifa', 3);

-- --------------------------------------------------------

--
-- Table structure for table `ParkingStationSlots`
--

CREATE TABLE IF NOT EXISTS `ParkingStationSlots` (
  `ParkingStationSlot_id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_id` int(11) NOT NULL,
  `car_ID` varchar(7) NOT NULL,
  `level` int(11) NOT NULL,
  `row` int(11) NOT NULL,
  `col` int(11) NOT NULL,
  `ParkingStationSlot_status` int(11) NOT NULL,
  PRIMARY KEY (`ParkingStationSlot_id`),
  KEY `parking_id` (`parking_id`),
  KEY `car_ID` (`car_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=173 ;

--
-- Dumping data for table `ParkingStationSlots`
--

INSERT INTO `ParkingStationSlots` (`ParkingStationSlot_id`, `parking_id`, `car_ID`, `level`, `row`, `col`, `ParkingStationSlot_status`) VALUES
(66, 2, '0', 1, 1, 3, 1),
(67, 2, '0', 1, 2, 3, 1),
(69, 2, '0', 1, 4, 3, 2),
(79, 2, '0', 2, 4, 2, 1),
(101, 2, '0', 1, 1, 1, 0),
(102, 2, '0', 1, 1, 2, 0),
(103, 2, '0', 1, 1, 3, 0),
(104, 2, '0', 1, 2, 1, 0),
(105, 2, '0', 1, 2, 2, 0),
(106, 2, '0', 1, 2, 3, 0),
(107, 2, '0', 1, 3, 1, 0),
(108, 2, '0', 1, 3, 2, 0),
(109, 2, '0', 1, 3, 3, 0),
(110, 2, '0', 2, 1, 1, 0),
(111, 2, '0', 2, 1, 2, 0),
(112, 2, '0', 2, 1, 3, 0),
(113, 2, '0', 2, 2, 1, 0),
(114, 2, '0', 2, 2, 2, 0),
(115, 2, '0', 2, 2, 3, 0),
(116, 2, '0', 2, 3, 1, 0),
(117, 2, '0', 2, 3, 2, 0),
(118, 2, '0', 2, 3, 3, 0),
(119, 2, '0', 3, 1, 1, 0),
(120, 2, '0', 3, 1, 2, 0),
(121, 2, '0', 3, 1, 3, 0),
(122, 2, '0', 3, 2, 1, 0),
(123, 2, '0', 3, 2, 2, 0),
(124, 2, '0', 3, 2, 3, 0),
(125, 2, '0', 3, 3, 1, 0),
(126, 2, '0', 3, 3, 2, 0),
(127, 2, '0', 3, 3, 3, 0),
(128, 1, '0', 1, 1, 1, 0),
(129, 1, '0', 1, 1, 2, 0),
(130, 1, '0', 1, 1, 3, 0),
(131, 1, '0', 1, 1, 4, 0),
(132, 1, '0', 1, 1, 5, 0),
(133, 1, '0', 1, 2, 1, 0),
(134, 1, '0', 1, 2, 2, 0),
(135, 1, '0', 1, 2, 3, 0),
(136, 1, '0', 1, 2, 4, 0),
(137, 1, '0', 1, 2, 5, 0),
(138, 1, '0', 1, 3, 1, 0),
(139, 1, '0', 1, 3, 2, 0),
(140, 1, '0', 1, 3, 3, 0),
(141, 1, '0', 1, 3, 4, 0),
(142, 1, '0', 1, 3, 5, 0),
(143, 1, '0', 2, 1, 1, 0),
(144, 1, '0', 2, 1, 2, 0),
(145, 1, '0', 2, 1, 3, 0),
(146, 1, '0', 2, 1, 4, 0),
(147, 1, '0', 2, 1, 5, 0),
(148, 1, '0', 2, 2, 1, 0),
(149, 1, '0', 2, 2, 2, 0),
(150, 1, '0', 2, 2, 3, 0),
(151, 1, '0', 2, 2, 4, 0),
(152, 1, '0', 2, 2, 5, 0),
(153, 1, '0', 2, 3, 1, 0),
(154, 1, '0', 2, 3, 2, 0),
(155, 1, '0', 2, 3, 3, 0),
(156, 1, '0', 2, 3, 4, 0),
(157, 1, '0', 2, 3, 5, 0),
(158, 1, '0', 3, 1, 1, 0),
(159, 1, '0', 3, 1, 2, 0),
(160, 1, '0', 3, 1, 3, 0),
(161, 1, '0', 3, 1, 4, 0),
(162, 1, '0', 3, 1, 5, 0),
(163, 1, '0', 3, 2, 1, 0),
(164, 1, '0', 3, 2, 2, 0),
(165, 1, '0', 3, 2, 3, 0),
(166, 1, '0', 3, 2, 4, 0),
(167, 1, '0', 3, 2, 5, 0),
(168, 1, '0', 3, 3, 1, 0),
(169, 1, '0', 3, 3, 2, 0),
(170, 1, '0', 3, 3, 3, 0),
(171, 1, '0', 3, 3, 4, 0),
(172, 1, '0', 3, 3, 5, 0);

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
-- Table structure for table `subscriptions`
--

CREATE TABLE IF NOT EXISTS `subscriptions` (
  `client_ID` varchar(9) CHARACTER SET latin1 NOT NULL DEFAULT 'blat',
  `car_ID` varchar(7) NOT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`client_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subscriptions`
--

INSERT INTO `subscriptions` (`client_ID`, `car_ID`, `start_date`, `end_date`) VALUES
('1', '', '2018-06-10 19:05:31', '2018-06-29 21:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `Teams`
--

CREATE TABLE IF NOT EXISTS `Teams` (
  `Num` int(11) NOT NULL,
  `Team` text CHARACTER SET latin1 NOT NULL,
  `City` text CHARACTER SET latin1 NOT NULL,
  `Established` year(4) NOT NULL,
  `Coach` text CHARACTER SET latin1 NOT NULL,
  `Wins` int(11) NOT NULL,
  PRIMARY KEY (`Num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `worker_id` varchar(9) NOT NULL,
  `worker_Fname` varchar(40) NOT NULL,
  `worker_Lname` varchar(40) NOT NULL,
  `worker_email` varchar(250) NOT NULL,
  `worker_password` text NOT NULL,
  `worker_phone` varchar(12) NOT NULL,
  `worker_type` varchar(15) NOT NULL,
  `parking_id` int(11) NOT NULL,
  PRIMARY KEY (`worker_id`),
  KEY `parking_id` (`parking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `workers`
--

INSERT INTO `workers` (`worker_id`, `worker_Fname`, `worker_Lname`, `worker_email`, `worker_password`, `worker_phone`, `worker_type`, `parking_id`) VALUES
('204594154', 'snir', 'vit', '111@111', '111111', '1111111', 'CEO', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`client_ID`) REFERENCES `clients` (`client_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`order_car_id`) REFERENCES `cars` (`car_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`order_parking_id`) REFERENCES `ParkingStation` (`parking_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `order_prices`
--
ALTER TABLE `order_prices`
  ADD CONSTRAINT `order_prices_ibfk_1` FOREIGN KEY (`parking_id`) REFERENCES `ParkingStation` (`parking_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subscriptions`
--
ALTER TABLE `subscriptions`
  ADD CONSTRAINT `subscriptions_ibfk_1` FOREIGN KEY (`client_ID`) REFERENCES `clients` (`client_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `workers`
--
ALTER TABLE `workers`
  ADD CONSTRAINT `workers_ibfk_1` FOREIGN KEY (`parking_id`) REFERENCES `ParkingStation` (`parking_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
