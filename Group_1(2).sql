-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 24, 2018 at 02:07 AM
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
('111e', '098'),
('222222', '098'),
('890', '098'),
('111', '111'),
('1', '1111'),
('123111', '1111'),
('543', '1111'),
('2121', '1121212'),
('123', '123'),
('666', '123123123'),
('1q', '1q'),
('1221', '2121'),
('asa', '2121'),
('asaa', '2121'),
('2222222', '222222222'),
('2334', '42334'),
('234', '4234'),
('341', '431'),
('34g', '43g'),
('54', '54'),
('456656', '645645'),
('7', '7'),
('8', '7'),
('75', '75'),
('aaa', 'aaaa'),
('aq', 'aq'),
('asd', 'asd'),
('bg', 'bg'),
('cd', 'cd'),
('eee', 'ee'),
('verrtf', 'errte'),
('vert', 'ert'),
('vertf', 'erte'),
('df', 'fd'),
('fefe', 'fefe'),
('fre', 'fre'),
('frfr', 'frfrf'),
('345g', 'g345'),
('g465', 'g46'),
('ge45', 'ge4g'),
('g456g', 'ge6'),
('gdf', 'gfd'),
('gg', 'gg'),
('hihi', 'hihi'),
('iviv', 'iviv'),
('k', 'kk'),
('12', 'lkdjgflkj'),
('okm', 'okm'),
('poopi', 'poopi'),
('popo', 'popo'),
('q12', 'q12'),
('qq2', 'q2'),
('qq', 'qq1'),
('q', 'qqqq'),
('qwqw', 'qqw'),
('W', 'QWQW'),
('rrr', 'rr'),
('sw', 'sw'),
('terte', 'treter'),
('vf', 'vf'),
('w2', 'w2'),
('wiwi', 'WABALABAi'),
('we', 'we'),
('weaa', 'weaa'),
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
('098', '890', '890', 'aa', 'ADVANCED', '890', '890'),
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
('123456789', 'moti', 'cohen', '111', 'SUBSCRIBED', 'mymail@mail.com', '0546961455'),
('1q', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '1q', 'Occasional'),
('204594154', '1', '1', 'Advanced', 'ADVANCED', '111', '1111'),
('204594156', 'ad', 'matai', 'mtai', 'ad', 'Advanced', 'ADVANCED'),
('2121', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '21', 'Occasional'),
('222222222', 'vitrack', 'tamam', 'Advanced', 'ADVANCED', '222@222', '2222222'),
('42334', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '234234', 'Occasional'),
('4234', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '234234', 'Occasional'),
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
('asd', 'asd', 'asd', 'Advanced', 'ADVANCED', 'asd', 'asd'),
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
('frfrf', 'Occasional', 'Occasional', 'frfr', 'OCCASIONAL', 'frfr', 'Occasional'),
('g345', 'g345', 'g345', 'Advanced', 'ADVANCED', 'g34', 'g345'),
('g46', 'g346', 'g4', 'Advanced', 'ADVANCED', '456g', '456g'),
('ge4g', 'ge45', 'ge45', 'Advanced', 'ADVANCED', 'ge45', 'g45'),
('ge6', 'ge5', 'g4', 'Advanced', 'ADVANCED', '456g', 'gge4'),
('gfd', 'gfd', 'gdf', 'Advanced', 'ADVANCED', 'gdf', 'gdf'),
('gg', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'g', 'Occasional'),
('hihi', 'hihi', 'hihi', 'Advanced', 'ADVANCED', 'hihi', 'hihi'),
('ivan', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'ivan', 'Occasional'),
('iviv', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'iviv', 'Occasional'),
('kk', 'k', 'kk', 'Advanced', 'ADVANCED', 'k', 'k'),
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
('qqw', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', 'qw', 'Occasional'),
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
('vf', 'vf', 'vf', 'vf', 'SUBSCRIBED', 'vf', 'vf'),
('w2', 'w2', 'w2', 'Advanced', 'ADVANCED', 'w2', 'w2'),
('WABALABA', 'WABALABA', 'WABALABA', 'WABALABA', 'SUBSCRIBED', 'WABALABA', 'WABALABA'),
('WABALABAi', 'WABALABAi', 'WABALABA', 'WABALABA', 'SUBSCRIBED', 'WABALABA', 'WABALABA'),
('we', 'Occasional', 'Occasional', 'Occasional', 'OCCASIONAL', '11:11', 'Occasional'),
('wea', 'we', 'we', 'Advanced', 'ADVANCED', 'we', 'we'),
('weaa', 'we', 'we', 'Advanced', 'ADVANCED', 'we', 'we'),
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
(11, '2018-06-22 13:20:44', '2', NULL, 0, 'TAKE 2 ON some long long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long longlong long long long text ', NULL),
(13, '2018-06-23 16:27:01', '2', '1', 0, 'TAKE 3 ON some long long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long long long long longlong long long longlong long long longlong long long long text ', '37.5');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=95 ;

--
-- Dumping data for table `Messages`
--

INSERT INTO `Messages` (`messages_id`, `messages_text`, `messages_confirmation`, `client_ID`) VALUES
(83, 'your subscription is end in a week', 0, '1'),
(84, 'your subscription is end in a week', 0, '1'),
(85, 'you are late to order 21', 0, NULL),
(86, 'you are late to order 40', 0, NULL),
(87, 'you are late to order 54', 0, NULL),
(88, 'you are late to order 55', 0, NULL),
(89, 'you are late to order 56', 0, NULL),
(90, 'your subscription is end in a week', 0, '1'),
(91, 'your subscription is end in a week', 0, '1'),
(92, 'your subscription is end in a week', 0, '1'),
(93, 'your subscription is end in a week', 0, '1'),
(94, 'your subscription is end in a week', 0, '1');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=79 ;

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
(40, 'ONGOING', '12', 'CREDIT', 'OCCASIONAL', 1, '2018-06-23 13:13:39', '2018-06-30 21:00:00', NULL),
(44, 'ONGOING', 'sw', 'CREDIT', 'sa', 1, '2018-06-23 13:20:18', '0000-00-00 00:00:00', NULL),
(47, 'ONGOING', '345g', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-24 09:12:00', '2018-06-25 09:12:00', NULL),
(53, 'ONGOING', '341', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-24 08:11:00', '2018-06-25 08:11:00', 24),
(57, 'PENDING', 'qwqw', 'CREDIT', 'IN_ADVANCE', 2, '2018-06-23 16:01:57', '2018-06-24 08:11:00', 48.4525),
(58, 'PENDING', 'weaa', 'CREDIT', 'IN_ADVANCE', 2, '2018-06-24 08:11:00', '2018-06-25 08:11:00', 72),
(61, 'PENDING', '7', 'CASH', 'IN_ADVANCE', 2, '2018-06-24 09:12:00', '2018-06-25 09:12:00', NULL),
(62, 'PENDING', '7', 'CASH', 'IN_ADVANCE', 2, '2018-06-24 09:12:00', '2018-06-25 09:12:00', 72),
(65, 'PENDING', '111', 'CASH', 'IN_ADVANCE', 1, '2018-06-23 08:00:00', '2018-07-06 08:00:00', 312),
(66, 'ONGOING', '1', 'CASH', 'IN_ADVANCE', 1, '2018-06-23 07:10:00', '2018-06-30 07:10:00', 168),
(67, 'PENDING', '1', 'CASH', 'IN_ADVANCE', 1, '2018-06-24 07:00:00', '2018-06-25 08:12:00', 1683),
(68, 'ONGOING', '1221', 'CASH', 'IN_ADVANCE', 1, '2018-06-24 08:11:00', '2018-06-26 08:11:00', 48),
(69, 'PENDING', 'asd', 'CREDIT', 'IN_ADVANCE', 1, '2018-06-24 08:11:00', '2018-06-25 08:11:00', 24),
(70, 'PENDING', 'k', 'CREDIT', 'IN_ADVANCE', 2, '2018-06-24 08:11:00', '2018-06-26 08:11:00', 144),
(71, 'PENDING', '7', 'CASH', 'IN_ADVANCE', 1, '2018-06-23 21:45:19', '2018-06-24 08:11:00', 10.428055555555556),
(74, 'PENDING', 'frfr', 'CREDIT', 'IN_ADVANCE', 2, '2018-06-23 21:57:39', '2018-06-24 08:11:00', 30.6675),
(75, 'PENDING', '7', 'CASH', 'IN_ADVANCE', 1, '2018-06-23 22:08:11', '2018-06-24 08:11:00', 10.046944444444444),
(76, 'PENDING', '7', 'CASH', 'IN_ADVANCE', 1, '2018-06-23 22:11:53', '2018-06-24 08:11:00', NULL),
(77, 'PENDING', '7', 'CASH', 'IN_ADVANCE', 1, '2018-06-23 22:23:59', '2018-06-24 08:11:00', 9.783611111111112),
(78, 'PENDING', 'w2', 'CASH', 'IN_ADVANCE', 1, '2018-06-24 09:12:00', '2018-06-25 09:12:00', 24);

-- --------------------------------------------------------

--
-- Table structure for table `order_prices`
--

CREATE TABLE IF NOT EXISTS `order_prices` (
  `parking_id` int(11) NOT NULL,
  `order_type` varchar(50) NOT NULL,
  `order_price_per_hour` double NOT NULL,
  `ceo_approval` varchar(10) NOT NULL DEFAULT 'no',
  PRIMARY KEY (`parking_id`,`order_type`),
  KEY `parking_id` (`parking_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_prices`
--

INSERT INTO `order_prices` (`parking_id`, `order_type`, `order_price_per_hour`, `ceo_approval`) VALUES
(1, 'IN_ADVANCE', 1, 'no'),
(1, 'OCCASIONAL', 2, 'no'),
(1, 'SUBSCRIBED', 200, 'no'),
(2, 'IN_ADVANCE', 3, 'no'),
(2, 'OCCASIONAL', 4, 'no'),
(2, 'SUBSCRIBED', 230, 'no'),
(4, 'IN_ADVANCE', 4, 'no'),
(4, 'OCCASIONAL', 5, 'no'),
(4, 'SUBSCRIBED', 288, 'no');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `ParkingStation`
--

INSERT INTO `ParkingStation` (`parking_id`, `parking_address`, `parking_size`) VALUES
(1, 'TelAviv', 5),
(2, 'Haifa', 3),
(4, 'Jerusalem', 5);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=218 ;

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
(172, 1, '0', 3, 3, 5, 0),
(173, 1, '0', 1, 1, 1, 0),
(174, 1, '0', 1, 1, 2, 0),
(175, 1, '0', 1, 1, 3, 0),
(176, 1, '0', 1, 1, 4, 0),
(177, 1, '0', 1, 1, 5, 0),
(178, 1, '0', 1, 2, 1, 0),
(179, 1, '0', 1, 2, 2, 0),
(180, 1, '0', 1, 2, 3, 0),
(181, 1, '0', 1, 2, 4, 0),
(182, 1, '0', 1, 2, 5, 0),
(183, 1, '0', 1, 3, 1, 0),
(184, 1, '0', 1, 3, 2, 0),
(185, 1, '0', 1, 3, 3, 0),
(186, 1, '0', 1, 3, 4, 0),
(187, 1, '0', 1, 3, 5, 0),
(188, 1, '0', 2, 1, 1, 0),
(189, 1, '0', 2, 1, 2, 0),
(190, 1, '0', 2, 1, 3, 0),
(191, 1, '0', 2, 1, 4, 0),
(192, 1, '0', 2, 1, 5, 0),
(193, 1, '0', 2, 2, 1, 0),
(194, 1, '0', 2, 2, 2, 0),
(195, 1, '0', 2, 2, 3, 0),
(196, 1, '0', 2, 2, 4, 0),
(197, 1, '0', 2, 2, 5, 0),
(198, 1, '0', 2, 3, 1, 0),
(199, 1, '0', 2, 3, 2, 0),
(200, 1, '0', 2, 3, 3, 0),
(201, 1, '0', 2, 3, 4, 0),
(202, 1, '0', 2, 3, 5, 0),
(203, 1, '0', 3, 1, 1, 0),
(204, 1, '0', 3, 1, 2, 0),
(205, 1, '0', 3, 1, 3, 0),
(206, 1, '0', 3, 1, 4, 0),
(207, 1, '0', 3, 1, 5, 0),
(208, 1, '0', 3, 2, 1, 0),
(209, 1, '0', 3, 2, 2, 0),
(210, 1, '0', 3, 2, 3, 0),
(211, 1, '0', 3, 2, 4, 0),
(212, 1, '0', 3, 2, 5, 0),
(213, 1, '0', 3, 3, 1, 0),
(214, 1, '0', 3, 3, 2, 0),
(215, 1, '0', 3, 3, 3, 0),
(216, 1, '0', 3, 3, 4, 0),
(217, 1, '0', 3, 3, 5, 0);

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
('1', '1', '1', '1@1', 'aaa', '1', 'ParkingManger', 1),
('123456789', 'Unresponded', 'Complaints', 'un@comp.com', 'aaa', '1234567890', 'CustomerService', 1),
('204594154', 'snir', 'vit', '111@111', '111111', '1111111', 'CEO', 1);

-- --------------------------------------------------------

--
-- Table structure for table `workersMessages`
--

CREATE TABLE IF NOT EXISTS `workersMessages` (
  `messages_id` int(11) NOT NULL AUTO_INCREMENT,
  `messages_text` varchar(256) CHARACTER SET utf8 NOT NULL,
  `messages_confirmation` int(11) NOT NULL,
  `worker_ID` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`messages_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `workersMessages`
--

INSERT INTO `workersMessages` (`messages_id`, `messages_text`, `messages_confirmation`, `worker_ID`) VALUES
(1, 'Conplaint ID:11 is one day old and still has no response!!', 0, '123456789'),
(2, 'Conplaint ID:11 is one day old and still has no response!!', 0, '123456789'),
(3, 'Conplaint ID:11 is one day old and still has no response!!', 0, '123456789'),
(4, 'Conplaint ID:11 is one day old and still has no response!!', 0, '123456789');

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
