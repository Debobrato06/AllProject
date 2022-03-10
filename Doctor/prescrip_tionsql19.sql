-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 24, 2019 at 12:47 AM
-- Server version: 5.6.41-84.1
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prescrip_tionsql19`
--

-- --------------------------------------------------------

--
-- Table structure for table `password_recovery`
--

CREATE TABLE `password_recovery` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `ipaddress` varchar(250) COLLATE utf8_bin NOT NULL,
  `request_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00:00',
  `method` varchar(10) COLLATE utf8_bin NOT NULL COMMENT 'phone or email',
  `status` int(11) NOT NULL COMMENT '0 = failed, 1 = mail sent, 2 = sucess'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='password will recover using sms only through verified phone number.' ROW_FORMAT=COMPACT;

--
-- Dumping data for table `password_recovery`
--

INSERT INTO `password_recovery` (`id`, `userId`, `ipaddress`, `request_time`, `method`, `status`) VALUES
(1, 3, '103.222.23.37', '2019-09-11 01:25:44', 'Phone', 1),
(2, 3, '103.222.23.37', '2019-09-11 02:14:43', 'Phone', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_activities`
--

CREATE TABLE `tbl_activities` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `module` text COLLATE utf8_unicode_ci,
  `module_field` text COLLATE utf8_unicode_ci,
  `description` text COLLATE utf8_unicode_ci,
  `activity_date_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `icon` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'fa fa-asterisk',
  `deleted` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_activities`
--

INSERT INTO `tbl_activities` (`id`, `user_id`, `module`, `module_field`, `description`, `activity_date_time`, `icon`, `deleted`) VALUES
(1, 3, 'Drug Generic', 'User Drug Generic', 'User Added Drug Generic', '2019-09-10 20:10:16', 'fa fa-check-circle', 0),
(4, 3, 'Drug Company', 'User Drug Company', 'User Delete Drug Company', '2019-09-10 20:34:02', 'fa fa-cube', 0),
(5, 3, 'User Profile', 'User Profile Information', 'User Profile Updated Successfully', '2019-09-11 15:23:27', 'fa fa-user', 0),
(6, 3, 'User Profile', 'User Profile Information', 'User Profile Updated Successfully', '2019-09-11 15:24:39', 'fa fa-user', 0),
(7, 3, 'User Profile', 'User Profile Information', 'User Profile Updated Successfully', '2019-09-11 16:14:29', 'fa fa-user', 0),
(8, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 19:40:50', 'fa fa-file', 0),
(9, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 19:46:58', 'fa fa-file', 0),
(10, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 19:56:47', 'fa fa-file', 0),
(11, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 19:56:52', 'fa fa-file', 0),
(12, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 19:58:02', 'fa fa-file', 0),
(13, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:01:19', 'fa fa-file', 0),
(14, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:08:18', 'fa fa-file', 0),
(15, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:08:22', 'fa fa-file', 0),
(16, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:09:30', 'fa fa-file', 0),
(17, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:13:52', 'fa fa-file', 0),
(18, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:13:57', 'fa fa-file', 0),
(19, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:42:36', 'fa fa-file', 0),
(20, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:42:40', 'fa fa-file', 0),
(21, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 20:42:43', 'fa fa-file', 0),
(22, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 22:29:56', 'fa fa-file', 0),
(23, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 22:30:01', 'fa fa-file', 0),
(24, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 22:30:04', 'fa fa-file', 0),
(25, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 22:30:08', 'fa fa-file', 0),
(26, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-12 22:30:13', 'fa fa-file', 0),
(27, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 16:10:57', 'fa fa-file', 0),
(28, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 16:11:01', 'fa fa-file', 0),
(29, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 16:12:30', 'fa fa-file', 0),
(30, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 17:36:15', 'fa fa-file', 0),
(31, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 17:36:29', 'fa fa-file', 0),
(32, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 17:48:36', 'fa fa-file', 0),
(33, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 17:48:41', 'fa fa-file', 0),
(34, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:17:11', 'fa fa-file', 0),
(35, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:17:18', 'fa fa-file', 0),
(36, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:17:22', 'fa fa-file', 0),
(37, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:17:25', 'fa fa-file', 0),
(38, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:36:47', 'fa fa-file', 0),
(39, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:36:53', 'fa fa-file', 0),
(40, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:36:57', 'fa fa-file', 0),
(41, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:37:02', 'fa fa-file', 0),
(42, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-14 18:37:05', 'fa fa-file', 0),
(43, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 14:36:36', 'fa fa-file', 0),
(44, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 14:36:40', 'fa fa-file', 0),
(45, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 14:36:44', 'fa fa-file', 0),
(46, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 14:40:16', 'fa fa-file', 0),
(47, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 14:54:06', 'fa fa-file', 0),
(48, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 15:02:42', 'fa fa-file', 0),
(49, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 15:02:46', 'fa fa-file', 0),
(50, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 15:02:49', 'fa fa-file', 0),
(51, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 15:16:12', 'fa fa-file', 0),
(52, 3, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-09-16 15:16:15', 'fa fa-file', 0),
(53, 3, 'Drug', 'User Drug', 'User Added Drug', '2019-09-16 15:27:12', 'fa fa-List', 0),
(54, 3, 'User Profile', 'User Profile Information', 'User Profile Updated Successfully', '2019-09-17 23:36:40', 'fa fa-user', 0),
(55, 3, 'User Profile', 'User Profile Photo', 'User Profile Updated Successfully', '2019-09-17 23:36:52', 'fa fa-user', 0),
(56, 3, 'User Profile', 'User Profile Photo', 'User Profile Updated Successfully', '2019-09-25 16:53:49', 'fa fa-user', 0),
(57, 3, 'User Profile', 'User Profile Photo', 'User Profile Updated Successfully', '2019-09-25 16:54:17', 'fa fa-user', 0),
(58, 4, 'Rules For Patient', 'User Rules For Patient', 'User Added Rules For Patient', '2019-10-23 18:55:36', 'fa fa-plus-square', 0),
(59, 4, 'Rules For Patient', 'User Rules For Patient', 'User Update Rules For Patient', '2019-10-23 18:55:50', 'fa fa-plus-square', 0),
(60, 4, 'Rules For Patient', 'User Rules For Patient', 'User Delete Rules For Patient', '2019-10-23 18:55:56', 'fa fa-plus-square', 0),
(61, 4, 'Own Examinations', 'User Own Examinations', 'User Added Own Examinations', '2019-10-23 18:56:04', 'fa fa-file', 0),
(62, 4, 'Chief Complaints', 'User Chief Complaints', 'User Update Chief Complaints', '2019-10-23 18:56:12', 'fa fa-file', 0),
(63, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 18:56:16', 'fa fa-file', 0),
(64, 4, 'Chief Complaints', 'User Chief Complaints', 'User Added Chief Complaints', '2019-10-23 18:56:24', 'fa fa-file', 0),
(65, 4, 'Chief Complaints', 'User Chief Complaints', 'User Update Chief Complaints', '2019-10-23 18:56:36', 'fa fa-file', 0),
(66, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 18:56:40', 'fa fa-file', 0),
(67, 4, 'Medical Test', 'User Medical Test', 'User Added Medical Test', '2019-10-23 18:57:04', 'fa fa-plus-square', 0),
(68, 4, 'Medical Test', 'User Medical Test', 'User Update Medical Test', '2019-10-23 18:57:13', 'fa fa-plus-square', 0),
(69, 4, 'Medical Test', 'User Medical Test', 'User Delete Medical Test', '2019-10-23 18:57:18', 'fa fa-plus-square', 0),
(70, 4, 'Drug Take', 'User Drug Take', 'User Added Drug Take', '2019-10-23 18:58:08', 'fa fa-stethoscope', 0),
(71, 4, 'Drug Take', 'User Drug Take', 'User Update Drug Take', '2019-10-23 18:58:31', 'fa fa-stethoscope', 0),
(72, 4, 'Drug Take', 'User Drug Take', 'User Delete Drug Take', '2019-10-23 18:58:36', 'fa fa-stethoscope', 0),
(73, 4, 'Drug Generic', 'User Drug Generic', 'User Added Drug Generic', '2019-10-23 18:58:49', 'fa fa-check-circle', 0),
(74, 4, 'Drug Generic', 'User Drug Generic', 'User Update Drug Generic', '2019-10-23 18:59:00', 'fa fa-check-circle', 0),
(75, 4, 'Drug Generic', 'User Drug Generic', 'User Delete Drug Generic', '2019-10-23 18:59:09', 'fa fa-check-circle', 0),
(76, 4, 'Drug', 'User Drug', 'User Added Drug', '2019-10-23 19:00:36', 'fa fa-List', 0),
(77, 4, 'Drug', 'User Drug', 'User Edit Drug', '2019-10-23 19:00:56', 'fa fa-List', 0),
(78, 4, 'Drug Company', 'User Drug Company', 'User Added Drug Company', '2019-10-23 19:07:45', 'fa fa-cube', 0),
(79, 4, 'Drug Company', 'User Drug Company', 'User Update Drug Company', '2019-10-23 19:07:56', 'fa fa-cube', 0),
(80, 4, 'Drug Company', 'User Drug Company', 'User Delete Drug Company', '2019-10-23 19:08:01', 'fa fa-cube', 0),
(81, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 19:47:34', 'fa fa-file', 0),
(82, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 19:59:34', 'fa fa-file', 0),
(83, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 19:59:38', 'fa fa-file', 0),
(84, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 19:59:44', 'fa fa-file', 0),
(85, 4, 'Own Examinations', 'User Own Examinations', 'User Added Own Examinations', '2019-10-23 21:36:39', 'fa fa-file', 0),
(86, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 21:36:43', 'fa fa-file', 0),
(87, 4, 'Chief Complaints', 'User Chief Complaints', 'User Added Chief Complaints', '2019-10-23 21:36:49', 'fa fa-file', 0),
(88, 4, 'Drug Take', 'User Drug Take', 'User Added Drug Take', '2019-10-23 21:36:58', 'fa fa-stethoscope', 0),
(89, 4, 'Drug Generic', 'User Drug Generic', 'User Added Drug Generic', '2019-10-23 21:37:04', 'fa fa-check-circle', 0),
(90, 4, 'Own Examinations', 'User Own Examinations', 'User Added Own Examinations', '2019-10-23 21:54:21', 'fa fa-file', 0),
(91, 4, 'Drug Generic', 'User Drug Generic', 'User Delete Drug Generic', '2019-10-23 21:57:12', 'fa fa-check-circle', 0),
(92, 4, 'Drug Take', 'User Drug Take', 'User Delete Drug Take', '2019-10-23 21:59:07', 'fa fa-stethoscope', 0),
(93, 4, 'Own Examinations', 'User Own Examinations', 'User Added Own Examinations', '2019-10-23 21:59:40', 'fa fa-file', 0),
(94, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 22:00:53', 'fa fa-file', 0),
(95, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-23 22:01:04', 'fa fa-file', 0),
(96, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:27:45', 'fa fa-file', 0),
(97, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:30:50', 'fa fa-file', 0),
(98, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:38:49', 'fa fa-file', 0),
(99, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:38:52', 'fa fa-file', 0),
(100, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:38:54', 'fa fa-file', 0),
(101, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:47:58', 'fa fa-file', 0),
(102, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:48:02', 'fa fa-file', 0),
(103, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:48:05', 'fa fa-file', 0),
(104, 4, 'Chief Complaints', 'User Chief Complaints', 'User Delete Chief Complaints', '2019-10-24 14:48:09', 'fa fa-file', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_chief_complaints`
--

CREATE TABLE `tbl_chief_complaints` (
  `id` int(11) NOT NULL,
  `complaint` text COLLATE utf8_bin NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',
  `last_update` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_chief_complaints`
--

INSERT INTO `tbl_chief_complaints` (`id`, `complaint`, `doctor_id`, `insert_time`, `last_update`) VALUES
(2, 'Back Pain', 0, '2019-09-09 11:37:34', '0000-00-00 00:00'),
(3, 'Chest Pain', 3, '2019-09-09 12:38:39', '0000-00-00 00:00'),
(4, 'Fever', 3, '2019-09-09 12:40:37', '0000-00-00 00:00'),
(5, 'Skin Problem', 3, '2019-09-09 12:41:29', '0000-00-00 00:00'),
(6, 'Kidney Problem ', 3, '2019-09-09 12:41:49', '0000-00-00 00:00'),
(7, 'Heart Disease', 3, '2019-09-09 12:42:47', '0000-00-00 00:00'),
(12, '', 4, '2019-10-23 17:36:49', '0000-00-00 00:00');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_clients`
--

CREATE TABLE `tbl_clients` (
  `id` int(11) NOT NULL,
  `phone` varchar(20) COLLATE utf8_bin NOT NULL,
  `patient_name` text COLLATE utf8_bin NOT NULL,
  `gender` varchar(8) COLLATE utf8_bin NOT NULL,
  `address` text COLLATE utf8_bin NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_clients`
--

INSERT INTO `tbl_clients` (`id`, `phone`, `patient_name`, `gender`, `address`, `doctor_id`, `insert_time`) VALUES
(1, '01738244627', 'Foisal Ahmed', 'male', 'Madaripur, Tanore, Rajshahi', 3, '2019-09-14 12:14'),
(2, '01763643663', 'Shrabon Ahmed', 'male', 'Madaripur, Tanore', 3, '2019-09-14 13:51'),
(3, '01763643664', 'Hridoy Ahmed', 'male', 'Madaripur, Tanore', 3, '2019-09-14 14:12'),
(4, '01834014011', 'Md. Hamidul Islam', 'male', 'Mirpur, Dhaka', 4, '2019-09-16 12:01'),
(5, '01755664433', 'polash uddin', 'male', 'dhaka, bangladesh', 4, '2019-09-21 10:07'),
(6, '', '', 'Select O', '', 4, '2019-09-22 12:20'),
(7, 'sfsdf', 'zcvzv', 'male', 'zxvzxv', 3, '2019-10-14 10:31'),
(8, 'test', 'test', 'male', 'test', 3, '2019-10-14 10:33'),
(9, '01755448113', 'test1', 'male', 'dhaka', 3, '2019-10-14 10:36');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_common_pages`
--

CREATE TABLE `tbl_common_pages` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `title` text NOT NULL,
  `body` text NOT NULL,
  `attatched` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_common_pages`
--

INSERT INTO `tbl_common_pages` (`id`, `name`, `title`, `body`, `attatched`) VALUES
(1, 'about', 'About Us', 'About Us Body', 'assets/_20190722142046.png'),
(2, 'terms', 'Terms and Condition', 'Terms and Condition Body', ''),
(3, 'contact_us', 'Contact Us', 'Contact Us Body', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_divission`
--

CREATE TABLE `tbl_divission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_divission`
--

INSERT INTO `tbl_divission` (`id`, `name`) VALUES
(1, 'ঢাকা'),
(2, 'রাজশাহী'),
(3, 'চট্টগ্রাম'),
(4, 'সিলেট'),
(5, 'খুলনা'),
(6, 'বরিশাল'),
(7, 'রংপুর'),
(8, 'ময়মনসিংহ');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_doctor_opinion`
--

CREATE TABLE `tbl_doctor_opinion` (
  `id` int(11) NOT NULL,
  `opinion_text` text COLLATE utf8_bin NOT NULL,
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',
  `doctor_id` int(11) NOT NULL DEFAULT '0' COMMENT '0 = all'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_doctor_opinion`
--

INSERT INTO `tbl_doctor_opinion` (`id`, `opinion_text`, `insert_time`, `doctor_id`) VALUES
(2, 'tsrdxgjhnx cf', '2019-09-01 12:03:06', 0),
(3, 'sghdzfhzdhfzh', '2019-09-01 17:38:23', 0),
(4, 'uuuuuuuuuuu', '2019-09-04 14:24:43', 4),
(5, 'vvvvvvvvvvvv', '2019-09-04 14:24:54', 4),
(6, 'ttttttttttttt', '2019-09-04 14:25:06', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_doctor_rejection`
--

CREATE TABLE `tbl_doctor_rejection` (
  `id` int(11) NOT NULL,
  `table_name` varchar(250) COLLATE utf8_bin NOT NULL,
  `reject_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_doctor_rejection`
--

INSERT INTO `tbl_doctor_rejection` (`id`, `table_name`, `reject_id`, `doctor_id`) VALUES
(10, 'tbl_drug_company', 3, 4),
(11, 'tbl_drug_generic', 3, 4),
(12, 'tbl_drug_take_format', 6, 4),
(13, 'tbl_appoinment_format', 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drug`
--

CREATE TABLE `tbl_drug` (
  `id` int(11) NOT NULL,
  `drug_name` text COLLATE utf8_bin NOT NULL,
  `drug_generic_id` int(11) NOT NULL,
  `drug_company_id` int(11) DEFAULT NULL,
  `doctor_id` int(11) NOT NULL DEFAULT '0' COMMENT ' 0 = all',
  `insert_time` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tbl_drug`
--

INSERT INTO `tbl_drug` (`id`, `drug_name`, `drug_generic_id`, `drug_company_id`, `doctor_id`, `insert_time`) VALUES
(12, 'Facticin', 11, 1, 0, '2019-09-19'),
(13, 'Fona', 12, 1, 0, '2019-09-19'),
(14, 'Geston', 13, 1, 0, '2019-09-19'),
(15, 'Tryptin', 15, 1, 0, '2019-09-19'),
(16, 'Dubarel', 16, 1, 0, '2019-09-19'),
(17, 'Flexilax', 17, 1, 0, '2019-09-19'),
(18, 'Monera', 18, 1, 0, '2019-09-19'),
(19, 'Benzapen', 19, 1, 0, '2019-09-19'),
(20, 'Prosalic™ Lotion', 20, 1, 0, '2019-09-19'),
(21, 'Bisocor®', 21, 1, 0, '2019-09-19'),
(22, 'Calbo-D®', 22, 1, 0, '2019-09-19'),
(23, 'Calborate™', 23, 1, 0, '2019-09-19'),
(24, 'Deflacort™', 24, 1, 0, '2019-09-19'),
(25, 'Lubtear® Eye Drops', 25, 1, 0, '2019-09-19'),
(26, 'Togent®', 26, 1, 0, '2019-09-19'),
(27, 'Revolizer', 27, 1, 0, '2019-09-19'),
(28, 'Tebast™', 28, 1, 0, '2019-09-19'),
(29, 'Pevitin®', 29, 1, 0, '2019-09-19'),
(30, 'Adiva®', 30, 1, 0, '2019-09-19'),
(31, 'Clotinex™', 31, 1, 0, '2019-09-19'),
(32, 'Ace®', 32, 1, 0, '2019-09-19'),
(33, 'Ace Plus®', 32, 1, 0, '2019-09-19'),
(34, 'Viodin®', 33, 1, 0, '2019-09-19'),
(35, 'Prazolok™', 34, 1, 0, '2019-09-19'),
(36, 'Inflagic®', 35, 1, 0, '2019-09-19'),
(37, 'Neurolin®', 36, 1, 0, '2019-09-19'),
(38, 'Probio™', 37, 1, 0, '2019-09-19'),
(39, 'Promtil®', 38, 1, 0, '2019-09-19'),
(40, 'Oxilar®', 39, 1, 0, '2019-09-19'),
(41, 'Ranolin™', 40, 1, 0, '2019-09-19'),
(42, 'RasaletTM', 41, 1, 0, '2019-09-19'),
(43, 'Dyvon®', 42, 1, 0, '2019-09-19'),
(44, 'Retabac™', 43, 1, 0, '2019-09-19'),
(45, 'Redclov™', 44, 1, 0, '2019-09-19'),
(46, 'Ketoral®', 45, 1, 0, '2019-09-19'),
(47, 'Kop®', 46, 1, 0, '2019-09-19'),
(48, 'Torax®', 47, 1, 0, '2019-09-19'),
(49, 'Rabeca®', 48, 1, 0, '2019-09-19'),
(50, 'Pylotrip® R', 49, 1, 0, '2019-09-19'),
(51, 'Racedot™', 49, 1, 0, '2019-09-19'),
(52, 'Acifix', 51, 8, 0, '2019-09-19'),
(53, 'Amdocal', 52, 8, 0, '2019-09-19'),
(54, 'Avidro', 53, 8, 0, '2019-09-19'),
(55, 'Prudex', 54, 8, 0, '2019-09-19'),
(56, 'Primace', 55, 8, 0, '2019-09-19'),
(57, 'Proceptin', 56, 8, 0, '2019-09-19'),
(58, 'Prosan', 57, 8, 0, '2019-09-19'),
(59, 'Prosan HZ', 58, 8, 0, '2019-09-19'),
(60, 'Prosfin', 59, 8, 0, '2019-09-19'),
(61, 'Protolan', 60, 8, 0, '2019-09-19');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drug_company`
--

CREATE TABLE `tbl_drug_company` (
  `id` int(11) NOT NULL,
  `company_name` varchar(255) COLLATE utf8_bin NOT NULL,
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT 'max top, min low',
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',
  `doctor_id` int(11) NOT NULL DEFAULT '0' COMMENT '0 = all'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tbl_drug_company`
--

INSERT INTO `tbl_drug_company` (`id`, `company_name`, `priority`, `insert_time`, `doctor_id`) VALUES
(1, 'Square Pharma', 34, '0000-00-00 00:00', 0),
(2, 'Incepta Pharmaceutical ', 1, '0000-00-00 00:00', 0),
(6, 'Acme Laboratories Limited.', 70, '2019-08-31 08:50:52', 0),
(7, 'APC Pharma Ltd.', 34, '2019-09-03 10:18:11', 0),
(8, ' Beximco Pharma', 89, '2019-09-03 10:18:37', 4),
(9, 'ACI Limited.', 987, '2019-09-03 10:18:47', 2),
(11, 'Amico Laboratories Ltd.', 34, '2019-09-04 11:24:15', 4),
(12, 'Apex Pharmaceuticals Ltd.', 1, '2019-09-04 11:33:41', 4);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drug_continue_rules`
--

CREATE TABLE `tbl_drug_continue_rules` (
  `id` int(11) NOT NULL,
  `type_name_bn` text COLLATE utf8_unicode_ci NOT NULL,
  `type_name_en` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_drug_continue_rules`
--

INSERT INTO `tbl_drug_continue_rules` (`id`, `type_name_bn`, `type_name_en`) VALUES
(1, 'দিন', 'Day'),
(2, 'সপ্তাহ', 'Week'),
(3, 'মাস', 'Month');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drug_generic`
--

CREATE TABLE `tbl_drug_generic` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_bin NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `insert_time` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tbl_drug_generic`
--

INSERT INTO `tbl_drug_generic` (`id`, `name`, `doctor_id`, `insert_time`) VALUES
(11, 'Gemifloxacin', 0, '2019-09-19'),
(12, 'Adapalene', 0, '2019-09-19'),
(13, 'Allylestrenol', 0, '2019-09-19'),
(15, 'Amitriptyline ', 0, '2019-09-19'),
(16, 'Aushokarist', 0, '2019-09-19'),
(17, 'Baclofen', 0, '2019-09-19'),
(18, 'Bacopa extract', 0, '2019-09-19'),
(19, 'Benzathine Penicillin', 0, '2019-09-19'),
(20, 'Betamethasone + Salicylic Acid', 0, '2019-09-19'),
(21, 'Bisoprolol Fumarate', 0, '2019-09-19'),
(22, 'Calcium + Vitamin-D', 0, '2019-09-19'),
(23, 'Calcium Orotate', 0, '2019-09-19'),
(24, 'Deflazacort', 0, '2019-09-19'),
(25, 'Dextran 70 + Hypromellose', 0, '2019-09-19'),
(26, 'Diphenhydramine HCl+ Zn Acetate', 0, '2019-09-19'),
(27, 'Dry Powder Inhaler (DPI) Device', 0, '2019-09-19'),
(28, 'Ebastine BP', 0, '2019-09-19'),
(29, 'Econazole Nitrate +Triamcenolone Acetonide', 0, '2019-09-19'),
(30, 'Efavirenz', 0, '2019-09-19'),
(31, 'Enoxaparin Sodium', 0, '2019-09-19'),
(32, 'Paracetamol', 0, '2019-09-19'),
(33, 'Povidone Iodine', 0, '2019-09-19'),
(34, 'Prazosin', 0, '2019-09-19'),
(35, 'Prednisolone', 0, '2019-09-19'),
(36, 'Pregabalin', 0, '2019-09-19'),
(37, 'Probiotic combination', 0, '2019-09-19'),
(38, 'Prochlorperazine Mesilate', 0, '2019-09-19'),
(39, 'Raloxifene', 0, '2019-09-19'),
(40, 'Ranolazine', 0, '2019-09-19'),
(41, 'Rasagiline', 0, '2019-09-19'),
(42, 'Calcipotriol', 0, '2019-09-19'),
(43, 'Retapamulin', 0, '2019-09-19'),
(44, 'Red clover Isoflavones 40 mg', 0, '2019-09-19'),
(45, 'Ketoconazole', 0, '2019-09-19'),
(46, 'Ketoprofen', 0, '2019-09-19'),
(47, 'Ketorolac Tromethamine', 0, '2019-09-19'),
(48, 'Rabeprazole Sodium INN', 0, '2019-09-19'),
(49, 'Rabeprazole, Amoxicillin & Clarithromycin', 0, '2019-09-19'),
(50, 'Racecadotril BP', 0, '2019-09-19'),
(51, 'Rabeprazole Sodium', 0, '2019-09-19'),
(52, 'Amlodipine besylate', 0, '2019-09-19'),
(53, 'Pizotifen', 0, '2019-09-19'),
(54, 'Dextromethorphan, Pseudoephedrine and Triprolidine', 0, '2019-09-19'),
(55, 'Ramipril', 0, '2019-09-19'),
(56, 'Omeprazole', 0, '2019-09-19'),
(57, 'Losartan Potassium', 0, '2019-09-19'),
(58, 'Losartan Potassium Hydrochlorothiazide', 0, '2019-09-19'),
(59, 'Finasteride', 0, '2019-09-19'),
(60, 'Lansoprazole', 0, '2019-09-19');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drug_take_format`
--

CREATE TABLE `tbl_drug_take_format` (
  `id` int(11) NOT NULL,
  `eat_format` text COLLATE utf8_bin NOT NULL,
  `doctor_id` int(11) NOT NULL DEFAULT '0' COMMENT '0 = all',
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_drug_take_format`
--

INSERT INTO `tbl_drug_take_format` (`id`, `eat_format`, `doctor_id`, `insert_time`) VALUES
(9, '1-1-1', 0, '2019-09-05 14:16:51'),
(10, '1-1-0', 0, '2019-09-05 14:17:28'),
(11, '1-0-0', 0, '2019-09-05 14:17:37');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drug_take_time_rules`
--

CREATE TABLE `tbl_drug_take_time_rules` (
  `id` int(11) NOT NULL,
  `drug_take_time_bn` text COLLATE utf8_unicode_ci NOT NULL,
  `drug_take_time_en` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_drug_take_time_rules`
--

INSERT INTO `tbl_drug_take_time_rules` (`id`, `drug_take_time_bn`, `drug_take_time_en`) VALUES
(1, 'খাবার গ্রহণের আগে', 'Before eating'),
(2, 'খাবার গ্রহণের পরে', 'After eating'),
(3, 'খাবার গ্রহনের সময়', 'During the meal');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drug_type`
--

CREATE TABLE `tbl_drug_type` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_bin NOT NULL,
  `shortcut` varchar(8) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tbl_drug_type`
--

INSERT INTO `tbl_drug_type` (`id`, `name`, `shortcut`) VALUES
(4, 'Liquid', 'Lqd'),
(5, 'Tablet', 'Tab'),
(6, 'Capsules', 'Cap'),
(7, 'Suppositories', 'Sup'),
(8, 'Drops', 'Drp'),
(9, 'Inhalers', 'Inh'),
(10, 'Injections', 'Inj');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_mail_send_setting`
--

CREATE TABLE `tbl_mail_send_setting` (
  `id` int(11) NOT NULL,
  `setting_name` text COLLATE utf8_unicode_ci NOT NULL,
  `value` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_mail_send_setting`
--

INSERT INTO `tbl_mail_send_setting` (`id`, `setting_name`, `value`) VALUES
(1, 'protocol', 'smtp'),
(2, 'smtp_host', 'ssl://dallas117.mysitehosted.com'),
(3, 'smtp_port', '465'),
(4, 'smtp_user', 'dfgdsg'),
(5, 'smtp_pass', 'ggggf'),
(6, 'mailtype', 'text'),
(7, 'charset', 'iso-8859-1'),
(8, 'wordwrap', 'TRUE');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_medical_test`
--

CREATE TABLE `tbl_medical_test` (
  `id` int(11) NOT NULL,
  `test_name` text COLLATE utf8_bin NOT NULL,
  `test_category` varchar(250) COLLATE utf8_bin NOT NULL,
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00',
  `doctor_id` int(11) NOT NULL DEFAULT '0' COMMENT '0 = all'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_medical_test`
--

INSERT INTO `tbl_medical_test` (`id`, `test_name`, `test_category`, `insert_time`, `doctor_id`) VALUES
(9, 'Pregnancy Test', 'Cellular And Chemical Analysis', '2019-09-09 14:09:11', 0),
(10, 'Prenatal Testing', 'Cellular And Chemical Analysis', '2019-09-09 14:10:06', 3),
(11, 'Thyroid Function Test', 'Cellular And Chemical Analysis', '2019-09-09 14:10:31', 0),
(12, 'Brain Scanning', 'Diagnostic Imaging', '2019-09-09 14:11:17', 0),
(13, 'Ultrasound', 'Diagnostic Imaging', '2019-09-09 14:11:57', 3),
(14, 'Mammography', 'Diagnostic Imaging', '2019-09-09 14:13:02', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_own_examinations`
--

CREATE TABLE `tbl_own_examinations` (
  `id` int(11) NOT NULL,
  `oe_title` text COLLATE utf8_bin NOT NULL,
  `doctor_id` int(11) NOT NULL DEFAULT '0',
  `insert_by` int(11) NOT NULL,
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_own_examinations`
--

INSERT INTO `tbl_own_examinations` (`id`, `oe_title`, `doctor_id`, `insert_by`, `insert_time`) VALUES
(3, 'Blood Test', 3, 1, '2019-09-09 12:20:19'),
(4, 'High Pressure', 3, 3, '2019-09-09 12:44:02'),
(5, 'Low Pressure', 3, 3, '2019-09-09 12:44:09'),
(6, 'Eye Test', 3, 3, '2019-09-09 12:44:21'),
(7, 'Diabetic', 3, 3, '2019-09-09 12:48:15'),
(53, 'Urine Test', 3, 3, '2019-09-16 11:16'),
(54, 'Heart Break', 4, 4, '2019-09-16 12:00'),
(55, 'Pressure', 4, 4, '2019-09-16 12:00'),
(65, 'ABCD', 4, 4, '2019-10-24 10:48'),
(66, 'EFGH', 4, 4, '2019-10-24 10:59'),
(67, 'IJKL', 4, 4, '2019-10-24 11:02'),
(68, 'MNOP', 4, 4, '2019-10-24 11:03');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_prescription`
--

CREATE TABLE `tbl_prescription` (
  `id` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `reference_id` int(11) NOT NULL,
  `prescription_date` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0000-00-00',
  `chief_complaint_ids` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `test_ids` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `next_visit_date` date DEFAULT '0000-00-00',
  `rules_for_patient_ids` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `insert_time` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0000-00-00 00:00',
  `insert_by` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_prescription`
--

INSERT INTO `tbl_prescription` (`id`, `pid`, `client_id`, `reference_id`, `prescription_date`, `chief_complaint_ids`, `test_ids`, `next_visit_date`, `rules_for_patient_ids`, `insert_time`, `insert_by`) VALUES
(1, 1, 1, 0, '2019-09-16', '2,3', '13', '2019-09-30', '5,6', '2019-09-16 11:42', 3),
(2, 2, 2, 2, '2019-09-16', '4,5', '11', '2019-09-28', '8,9', '2019-09-16 11:44', 3),
(3, 3, 3, 0, '2019-09-16', '6,7', '11,13', '2019-09-16', '7,9', '2019-09-16 11:48', 3),
(61, 6, 5, 0, '2019-10-24', '2', '0', '2019-10-26', '0', '2019-10-24 11:04', 4),
(60, 5, 5, 0, '2019-10-24', '2', '0', '2019-10-31', '0', '2019-10-24 11:03', 4),
(56, 1, 5, 0, '2019-10-24', '2', '12,14', '2019-10-28', '6,8', '2019-10-24 10:56', 4),
(57, 2, 5, 0, '2019-10-24', '2', '0', '2019-10-31', '6', '2019-10-24 11:00', 4),
(58, 3, 5, 0, '2019-10-24', '2', '0', '2019-10-29', '0', '2019-10-24 11:02', 4),
(59, 4, 5, 0, '2019-10-24', '2', '0', '2019-10-30', '0', '2019-10-24 11:02', 4),
(18, 4, 7, 2, '2019-10-08', '3', '9,12', '2019-10-14', '5,7', '2019-10-14 10:31', 3),
(19, 5, 8, 2, '2019-10-16', '3', '9,12', '2019-10-23', '5,7', '2019-10-14 10:33', 3),
(20, 6, 8, 2, '2019-10-14', '3', '9,12', '2019-10-23', '5,7', '2019-10-14 10:34', 3),
(21, 7, 9, 0, '2019-10-14', '3', '9', '2019-10-23', '5,7', '2019-10-14 10:36', 3),
(22, 8, 9, 0, '2019-10-14', '3', '9', '2019-10-23', '5,7', '2019-10-14 10:41', 3),
(23, 9, 9, 0, '2019-10-14', '3', '9', '2019-10-23', '5,7', '2019-10-14 10:41', 3),
(24, 10, 9, 0, '2019-10-14', '3', '9', '2019-10-23', '5,7', '2019-10-14 10:43', 3),
(25, 11, 9, 0, '2019-10-17', '3,4', '9', '2019-10-23', '5,7', '2019-10-14 10:44', 3),
(26, 12, 6, 0, '2019-10-19', NULL, NULL, '2019-10-19', '6,8,10', '2019-10-19 10:49', 3),
(27, 13, 6, 0, '2019-10-19', NULL, NULL, '2019-10-19', '6,8,10', '2019-10-19 10:50', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_prescription_medicine`
--

CREATE TABLE `tbl_prescription_medicine` (
  `id` int(11) NOT NULL,
  `prescription_id` int(11) NOT NULL,
  `drug_id` int(11) NOT NULL,
  `drug_type_id` int(11) NOT NULL,
  `power` double NOT NULL,
  `bottom_text` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_prescription_medicine`
--

INSERT INTO `tbl_prescription_medicine` (`id`, `prescription_id`, `drug_id`, `drug_type_id`, `power`, `bottom_text`) VALUES
(1, 1, 6, 5, 500, '1-1-1/ খাবার গ্রহণের আগে (30 minute)/2 Tablets/15 দিন'),
(2, 2, 1, 5, 500, '1-1-0/ খাবার গ্রহণের আগে (30 minute)/15 দিন'),
(3, 2, 1, 5, 500, '1-1-1/ খাবার গ্রহণের পরে (30 minute)/2 সপ্তাহ'),
(4, 3, 6, 5, 500, '1-1-0/ খাবার গ্রহণের আগে (15 minute)/10 দিন'),
(5, 3, 1, 4, 250, '1-0-0/ খাবার গ্রহণের পরে (15 minute)/1 মাস'),
(41, 60, 12, 7, 250, ''),
(42, 61, 12, 5, 200, ''),
(37, 56, 12, 5, 250, '1-1-1/ খাবার গ্রহণের আগে (30 minute)/2 Tablet /15 দিন'),
(38, 57, 12, 4, 250, ''),
(39, 58, 12, 4, 500, ''),
(40, 59, 12, 6, 250, '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_prescription_oe`
--

CREATE TABLE `tbl_prescription_oe` (
  `id` int(11) NOT NULL,
  `prescription_id` int(11) NOT NULL,
  `own_examination_id` int(11) NOT NULL,
  `own_examination_value` varchar(250) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_prescription_oe`
--

INSERT INTO `tbl_prescription_oe` (`id`, `prescription_id`, `own_examination_id`, `own_examination_value`) VALUES
(1, 1, 3, '55'),
(2, 1, 5, '60'),
(3, 2, 3, '10'),
(4, 2, 53, '88'),
(5, 3, 4, '160'),
(6, 3, 7, '88'),
(58, 58, 55, '20'),
(57, 58, 54, '10'),
(56, 57, 66, '0'),
(53, 57, 54, '0'),
(54, 57, 55, '0'),
(55, 57, 65, '0'),
(52, 56, 65, '0'),
(50, 56, 54, '0'),
(51, 56, 55, '0'),
(59, 58, 65, '30'),
(60, 58, 66, '40'),
(61, 59, 54, '10'),
(62, 59, 55, '20'),
(63, 59, 65, '30'),
(64, 59, 66, '40'),
(65, 59, 67, '50'),
(66, 60, 54, '10'),
(67, 60, 65, '30'),
(68, 60, 67, '50'),
(69, 61, 54, '10'),
(70, 61, 66, '40'),
(71, 61, 68, '60');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_reference`
--

CREATE TABLE `tbl_reference` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `insert_by` int(11) NOT NULL,
  `insert_time` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0000-00-00 00:00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_reference`
--

INSERT INTO `tbl_reference` (`id`, `name`, `address`, `phone`, `doctor_id`, `insert_by`, `insert_time`) VALUES
(2, 'Foisal', 'Madaripur, Tanore, Rajshahi', '01738244627', 3, 3, '2019-09-12 18:20:44');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rules_for_patient`
--

CREATE TABLE `tbl_rules_for_patient` (
  `id` int(11) NOT NULL,
  `rules` text COLLATE utf8_bin NOT NULL,
  `patient_category` text COLLATE utf8_bin NOT NULL,
  `doctor_id` int(11) NOT NULL DEFAULT '0' COMMENT '0 = all'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_rules_for_patient`
--

INSERT INTO `tbl_rules_for_patient` (`id`, `rules`, `patient_category`, `doctor_id`) VALUES
(5, 'Don\'t Eat Fish ', 'Alergic', 0),
(6, 'Don\'t Eat Peanuts', 'Alergic', 0),
(7, 'Don\'t Eat Red Meat', 'Heart Problem', 0),
(8, 'Don\'t Eat Oily Food', 'Heart Problem', 0),
(9, 'Don\'t Eat Spicy Food', 'Gastic Problem', 0),
(10, 'Don\'t Eat Fried Food', 'Gastic Problem', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sms_send_list`
--

CREATE TABLE `tbl_sms_send_list` (
  `id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL DEFAULT '0',
  `send_date_time` varchar(20) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0000-00-00 00:00',
  `message` text COLLATE utf8_unicode_ci NOT NULL,
  `receiver_numbers` text COLLATE utf8_unicode_ci NOT NULL,
  `insert_by` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_sms_send_list`
--

INSERT INTO `tbl_sms_send_list` (`id`, `doctor_id`, `send_date_time`, `message`, `receiver_numbers`, `insert_by`) VALUES
(3, 3, '2019-09-16 15:34:51', 'test', '01763643664', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sms_send_setting`
--

CREATE TABLE `tbl_sms_send_setting` (
  `id` int(11) NOT NULL,
  `username` text COLLATE utf8_unicode_ci NOT NULL,
  `password` text COLLATE utf8_unicode_ci NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `doctor_id` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_sms_send_setting`
--

INSERT INTO `tbl_sms_send_setting` (`id`, `username`, `password`, `last_update`, `doctor_id`) VALUES
(1, 'username', '123456', '2019-09-16 10:24:05', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_upozilla`
--

CREATE TABLE `tbl_upozilla` (
  `id` int(11) NOT NULL,
  `division_id` int(11) NOT NULL,
  `zilla_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_upozilla`
--

INSERT INTO `tbl_upozilla` (`id`, `division_id`, `zilla_id`, `name`) VALUES
(1, 1, 1, 'সাভার'),
(2, 1, 1, 'ধামরাই'),
(3, 1, 1, 'কেরাণীগঞ্জ'),
(4, 1, 1, 'নবাবগঞ্জ'),
(5, 1, 1, 'দোহার'),
(6, 1, 1, 'তেজগাঁও উন্নয়ন সার্কেল'),
(7, 1, 2, 'কালীগঞ্জ'),
(8, 1, 2, 'কালিয়াকৈর'),
(9, 1, 2, 'কাপাসিয়া'),
(10, 1, 2, 'গাজীপুর সদর'),
(11, 1, 2, 'শ্রীপুর'),
(12, 1, 3, 'বাসাইল'),
(13, 1, 3, 'ভুয়াপুর'),
(14, 1, 3, 'ঘাটাইল'),
(15, 1, 3, 'দেলদুয়ার'),
(16, 1, 3, 'গোপালপুর'),
(17, 1, 3, 'মধুপুর'),
(18, 1, 3, 'মির্জাপুর'),
(19, 1, 3, 'নাগরপুর'),
(20, 1, 3, 'সখিপুর'),
(21, 1, 3, 'টাঙ্গাইল সদর'),
(22, 1, 3, 'কালিহাতী'),
(23, 1, 3, 'ধনবাড়ি'),
(24, 1, 4, 'আড়াইহাজার'),
(25, 1, 4, 'বন্দর'),
(26, 1, 4, 'নারায়ণগঞ্জ সদর'),
(27, 1, 4, 'রূপগঞ্জ'),
(28, 1, 4, 'সোনারগাঁ'),
(29, 1, 5, 'ইটনা'),
(30, 1, 5, 'কটিয়াদি'),
(31, 1, 5, 'ভৈরব'),
(32, 1, 5, 'হোসেনপুর'),
(33, 1, 5, 'তাড়াইল'),
(34, 1, 5, 'পাকুন্দিয়া'),
(35, 1, 5, 'কুলিয়ারচর'),
(36, 1, 5, 'কিশোরগঞ্জ সদর'),
(37, 1, 5, 'করিমগঞ্জ'),
(38, 1, 5, 'বাজিতপুর'),
(39, 1, 5, 'অষ্টগ্রাম'),
(40, 1, 5, 'মিঠামইন'),
(41, 1, 5, 'নিকলী'),
(42, 1, 6, 'বেলাবো'),
(43, 1, 6, 'মনোহরদী'),
(44, 1, 6, 'নরসিংদী সদর'),
(45, 1, 6, 'পলাশ'),
(46, 1, 6, 'রায়পুরা'),
(47, 1, 6, 'শিবপুর'),
(48, 1, 7, 'রাজবাড়ী সদর'),
(49, 1, 7, 'গোয়ালন্দ'),
(50, 1, 7, 'পাংশা'),
(51, 1, 7, 'বালিয়াকান্দি'),
(52, 1, 7, 'কালুখালী'),
(53, 1, 8, 'ফরিদপুর সদর'),
(54, 1, 8, 'আলফাডাঙ্গা'),
(55, 1, 8, 'বোয়ালমারী'),
(56, 1, 8, 'সদরপুর'),
(57, 1, 8, 'নগরকান্দা'),
(58, 1, 8, 'ভাঙ্গা'),
(59, 1, 8, 'চরভদ্রাসন'),
(60, 1, 8, 'মধুখালী'),
(61, 1, 8, 'সালথা'),
(62, 1, 9, 'মাদারীপুর সদর'),
(63, 1, 9, 'শিবচর'),
(64, 1, 9, 'কালকিনি'),
(65, 1, 9, 'রাজৈর'),
(66, 1, 10, 'গোপালগঞ্জ সদর'),
(67, 1, 10, 'কাশিয়ানী'),
(68, 1, 10, 'টুংগীপাড়া'),
(69, 1, 10, 'কোটালীপাড়া'),
(70, 1, 10, 'মুকসুদপুর'),
(71, 1, 11, 'মুন্সিগঞ্জ সদর'),
(72, 1, 11, 'শ্রীনগর'),
(73, 1, 11, 'সিরাজদিখান'),
(74, 1, 11, 'লৌহজং '),
(75, 1, 11, 'গজারিয়া'),
(76, 1, 11, 'টংগিবাড়ী'),
(77, 1, 12, 'হরিরামপুর'),
(78, 1, 12, 'সাটুরিয়া'),
(79, 1, 12, 'মানিকগঞ্জ সদর'),
(80, 1, 12, 'ঘিওর'),
(81, 1, 12, 'শিবালয়'),
(82, 1, 12, 'দৌলতপুর'),
(83, 1, 12, 'সিংগাইর'),
(84, 1, 13, 'শরিয়তপুর সদর'),
(85, 1, 13, 'নড়িয়া'),
(86, 1, 13, 'জাজিরা'),
(87, 1, 13, 'গোসাইরহাট'),
(88, 1, 13, 'ভেদরগঞ্জ'),
(89, 1, 13, 'ডামুড্যা'),
(90, 2, 14, 'পবা'),
(91, 2, 14, 'দুর্গাপুর'),
(92, 2, 14, 'মোহনপুর'),
(93, 2, 14, 'চারঘাট'),
(94, 2, 14, 'পুঠিয়া'),
(95, 2, 14, 'বাঘা'),
(96, 2, 14, 'গোদাগাড়ী'),
(97, 2, 14, 'তানোর'),
(98, 2, 14, 'বাঘমারা'),
(99, 2, 15, 'বেলকুচি'),
(100, 2, 15, 'চৌহালি'),
(101, 2, 15, 'কামারখন্দ'),
(102, 2, 15, 'কাজীপুর'),
(103, 2, 15, 'রায়গঞ্জ'),
(104, 2, 15, 'শাহজাদপুর'),
(105, 2, 15, 'সিরাজগঞ্জ সদর'),
(106, 2, 15, 'তাড়াশ'),
(107, 2, 15, 'উল্লাপাড়া'),
(108, 2, 16, 'সুজানগর'),
(109, 2, 16, 'ঈশ্বরদী'),
(110, 2, 16, 'ভাঙ্গুরা'),
(111, 2, 16, 'পাবনা সদর'),
(112, 2, 16, 'বেড়া'),
(113, 2, 16, 'আটঘরিয়া'),
(114, 2, 16, 'চাটমোহর'),
(115, 2, 16, 'সাঁথিয়া'),
(116, 2, 16, 'ফরিদপুর'),
(117, 2, 17, 'কাহালু'),
(118, 2, 17, 'বগুড়া সদর'),
(119, 2, 17, 'সারিয়াকান্দি'),
(120, 2, 17, 'শাজাহানপুর'),
(121, 2, 17, 'দুপচাঁচিয়া'),
(122, 2, 17, 'আদমদিঘি'),
(123, 2, 17, 'নন্দিগ্রাম'),
(124, 2, 17, 'সোনাতলা'),
(125, 2, 17, 'ধুনট'),
(126, 2, 17, 'গাবতলী'),
(127, 2, 17, 'শেরপুর'),
(128, 2, 17, 'শিবগঞ্জ'),
(129, 2, 18, 'চাঁপাইনবাবগঞ্জ সদর'),
(130, 2, 18, 'গোমস্তাপুর'),
(131, 2, 18, 'নাচোল'),
(132, 2, 18, 'ভোলাহাট'),
(133, 2, 18, 'শিবগঞ্জ'),
(134, 2, 19, 'আক্কেলপুর'),
(135, 2, 19, 'কালাই'),
(136, 2, 19, 'ক্ষেতলাল'),
(137, 2, 19, 'পাঁচবিবি'),
(138, 2, 19, 'জয়পুরহাট সদর'),
(139, 2, 20, 'মহাদেবপুর'),
(140, 2, 20, 'বদলগাছী'),
(141, 2, 20, 'পত্নিতলা'),
(142, 2, 20, 'ধামইরহাট'),
(143, 2, 20, 'নিয়ামতপুর'),
(144, 2, 20, 'মান্দা'),
(145, 2, 20, 'আত্রাই'),
(146, 2, 20, 'রাণীনগর'),
(147, 2, 20, 'নওগাঁ সদর'),
(148, 2, 20, 'সাপাহার'),
(149, 2, 20, 'পোরশা'),
(150, 2, 21, 'নাটোর সদর'),
(151, 2, 21, 'সিংড়া'),
(152, 2, 21, 'বড়াইগ্রাম'),
(153, 2, 21, 'বাগাতিপাড়া'),
(154, 2, 21, 'গুরুদাসপুর'),
(155, 2, 21, 'লালপুর'),
(156, 2, 21, 'নলডাঙ্গা'),
(157, 3, 22, 'রাঙ্গুনিয়া'),
(158, 3, 22, 'সীতাকুণ্ড'),
(159, 3, 22, 'মীরসরাই'),
(160, 3, 22, 'পটিয়া'),
(161, 3, 22, 'সন্দ্বীপ'),
(162, 3, 22, 'বাঁশখালী'),
(163, 3, 22, 'বোয়ালখালী'),
(164, 3, 22, 'আনোয়ারা'),
(165, 3, 22, 'সাতকানিয়া'),
(166, 3, 22, 'লোহাগাড়া'),
(167, 3, 22, 'হাটহাজারী'),
(168, 3, 22, 'ফটিকছড়ি'),
(169, 3, 22, 'রাঊজান'),
(170, 3, 22, 'চন্দনাইশ'),
(171, 3, 23, 'দেবিদ্বার'),
(172, 3, 23, 'বরুড়া'),
(173, 3, 23, 'ব্রাহ্মণপাড়া'),
(174, 3, 23, 'চান্দিনা'),
(175, 3, 23, 'চৌদ্দগ্রাম'),
(176, 3, 23, 'দাউদকান্দি'),
(177, 3, 23, 'হোমনা'),
(178, 3, 23, 'লাকসাম'),
(179, 3, 23, 'মুরাদনগর'),
(180, 3, 23, 'নাঙ্গলকোট'),
(181, 3, 23, 'কুমিল্লা সদর'),
(182, 3, 23, 'মেঘনা'),
(183, 3, 23, 'মনোহরগঞ্জ'),
(184, 3, 23, 'সদর দক্ষিণ'),
(185, 3, 23, 'তিতাস'),
(186, 3, 23, 'বুড়িচং'),
(187, 3, 24, 'ছাগলনাইয়া'),
(188, 3, 24, 'ফেনী সদর'),
(189, 3, 24, 'সোনাগাজী'),
(190, 3, 24, 'ফুলগাজী'),
(191, 3, 24, 'পরশুরাম'),
(192, 3, 24, 'দাগনভুঞা'),
(193, 3, 25, 'ব্রাহ্মণবাড়িয়া সদর'),
(194, 3, 25, 'কসবা'),
(195, 3, 25, 'নাসিরনগর'),
(196, 3, 25, 'সরাইল'),
(197, 3, 25, 'আশুগঞ্জ'),
(198, 3, 25, 'আখাউরা'),
(199, 3, 25, 'নবীনগর'),
(200, 3, 25, 'বাঞ্ছারামপুর'),
(201, 3, 25, 'বিজয়নগর'),
(202, 3, 26, 'রাঙ্গামাটি সদর'),
(203, 3, 26, 'কাপ্তাই'),
(204, 3, 26, 'কাউখালী'),
(205, 3, 26, 'বাঘাইছড়ি'),
(206, 3, 26, 'বরকল'),
(207, 3, 26, 'লংগদু'),
(208, 3, 26, 'রাজস্থলী'),
(209, 3, 26, 'বিলাইছড়ি'),
(210, 3, 26, 'জুরাছড়ি'),
(211, 3, 26, 'নানিয়ারচর'),
(212, 3, 27, 'হাইমচর'),
(213, 3, 27, 'কচুয়া'),
(214, 3, 27, 'শহরাস্তি'),
(215, 3, 27, 'চাঁদপুর সদর'),
(216, 3, 27, 'মতলব উত্তর'),
(217, 3, 27, 'ফরিদ্গঞ্জ'),
(218, 3, 27, 'মতলব দক্ষিণ'),
(219, 3, 27, 'হাজীগঞ্জ'),
(220, 3, 28, 'নোয়াখালী সদর'),
(221, 3, 28, 'কোম্পানীগঞ্জ'),
(222, 3, 28, 'বেগমগঞ্জ'),
(223, 3, 28, 'হাতিয়া'),
(224, 3, 28, 'সুবর্ণচর'),
(225, 3, 28, 'কবিরহাট'),
(226, 3, 28, 'সেনবাগ'),
(227, 3, 28, 'চাটখিল'),
(228, 3, 28, 'সোনাইমুড়ী'),
(229, 3, 29, 'লক্ষ্মীপুর সদর'),
(230, 3, 29, 'কমলনগর'),
(231, 3, 29, 'রায়পুর'),
(232, 3, 29, 'রামগতি'),
(233, 3, 29, 'রামগঞ্জ'),
(234, 3, 30, 'কক্সবাজার সদর'),
(235, 3, 30, 'চকরিয়া'),
(236, 3, 30, 'কুতুবদিয়া'),
(237, 3, 30, 'উখিয়া'),
(238, 3, 30, 'মহেশখালী'),
(239, 3, 30, 'পেকুয়া'),
(240, 3, 30, 'রামু'),
(241, 3, 30, 'টেকনাফ'),
(242, 3, 31, 'খাগড়াছড়ি সদর'),
(243, 3, 31, 'দিঘীনালা'),
(244, 3, 31, 'পানছড়ি'),
(245, 3, 31, 'লক্ষীছড়ি'),
(246, 3, 31, 'মহালছড়ি'),
(247, 3, 31, 'মানিকছড়ি'),
(248, 3, 31, 'রামগড়'),
(249, 3, 31, 'মাটিরাঙ্গা'),
(250, 3, 31, 'গুইমারা'),
(251, 3, 32, 'বান্দরবান সদর'),
(252, 3, 32, 'আলীকদম'),
(253, 3, 32, 'নাইক্ষ্যংছড়ি'),
(254, 3, 32, 'রোয়াংছড়ি'),
(255, 3, 32, 'লামা'),
(256, 3, 32, 'রুমা'),
(257, 3, 32, 'থানচি'),
(258, 4, 33, 'বালাগঞ্জ'),
(259, 4, 33, 'বিয়ানীবাজার'),
(260, 4, 33, 'বিশ্বনাথ'),
(261, 4, 33, 'কোম্পানীগঞ্জ'),
(262, 4, 33, 'ফেঞ্চুগঞ্জ'),
(263, 4, 33, 'গোলাপগঞ্জ'),
(264, 4, 33, 'গোয়াইনঘাট'),
(265, 4, 33, 'জৈন্তাপুর'),
(266, 4, 33, 'কানাইঘাট'),
(267, 4, 33, 'সিলেট সদর'),
(268, 4, 33, 'জকিগঞ্জ'),
(269, 4, 33, 'দক্ষিণ সুরমা'),
(270, 4, 33, 'ওসমানী নগর'),
(271, 4, 34, 'বড়লেখা'),
(272, 4, 34, 'কমলগঞ্জ'),
(273, 4, 34, 'কুলাউরা'),
(274, 4, 34, 'মৌলভীবাজার সদর '),
(275, 4, 34, 'রাজনগর'),
(276, 4, 34, 'শ্রীমঙ্গল'),
(277, 4, 34, 'জুড়ী'),
(278, 4, 35, 'নবীগঞ্জ'),
(279, 4, 35, 'বাহুবল'),
(280, 4, 35, 'আজমিরীগঞ্জ'),
(281, 4, 35, 'বানিয়াচং'),
(282, 4, 35, 'লাখাই'),
(283, 4, 35, 'চুনারুঘাট'),
(284, 4, 35, 'হবিগঞ্জ সদর'),
(285, 4, 35, 'মাধবপুর'),
(286, 4, 36, 'সুনামগঞ্জ সদর'),
(287, 4, 36, 'দক্ষিণ সুনামগঞ্জ'),
(288, 4, 36, 'বিশ্বম্ভরপুর'),
(289, 4, 36, 'ছাতক'),
(290, 4, 36, 'জগন্নাথপুর'),
(291, 4, 36, 'তাহিরপুর'),
(292, 4, 36, 'ধর্মপাশা'),
(293, 4, 36, 'জামালগঞ্জ'),
(294, 4, 36, 'শাল্লা'),
(295, 4, 36, 'দিরাই'),
(296, 4, 36, 'দোয়ারাবাজার'),
(297, 5, 37, 'পাইকগাছা'),
(298, 5, 37, 'ফুলতলা'),
(299, 5, 37, 'দিঘলিয়া'),
(300, 5, 37, 'রূপসা'),
(301, 5, 37, 'তেরখাদা'),
(302, 5, 37, 'ডুমুরিয়া'),
(303, 5, 37, 'বটিয়াঘাটা'),
(304, 5, 37, 'দাকোপ'),
(305, 5, 37, 'কয়রা'),
(306, 5, 38, 'মণিরামপুর'),
(307, 5, 38, 'অভয়নগর'),
(308, 5, 38, 'বাঘারপাড়া'),
(309, 5, 38, 'চৌগাছা'),
(310, 5, 38, 'ঝিকরগাছা'),
(311, 5, 38, 'কেশবপুর'),
(312, 5, 38, 'যশোর সদর'),
(313, 5, 38, 'শার্শা'),
(314, 5, 39, 'আশাশুনি'),
(315, 5, 39, 'দেবহাটা'),
(316, 5, 39, 'কলারোয়া'),
(317, 5, 39, 'সাতক্ষীরা সদর'),
(318, 5, 39, 'শ্যামনগর'),
(319, 5, 39, 'তালা'),
(320, 5, 39, 'কালিগঞ্জ'),
(321, 5, 40, 'মুজিবনগর'),
(322, 5, 40, 'মেহেরপুর সদর'),
(323, 5, 40, 'গাংনী'),
(324, 5, 41, 'নড়াইল সদর'),
(325, 5, 41, 'লোহাগড়া'),
(326, 5, 41, 'কালিয়া'),
(327, 5, 42, 'চুয়াডাঙ্গা সদর'),
(328, 5, 42, 'আলমডাঙ্গা'),
(329, 5, 42, 'দামুড়হুদা'),
(330, 5, 42, 'জীবননগর'),
(331, 5, 43, 'শালিখা'),
(332, 5, 43, 'শ্রীপুর'),
(333, 5, 43, 'মাগুরা সদর'),
(334, 5, 43, 'মহম্মদপুর'),
(335, 5, 44, 'ফকিরহাট'),
(336, 5, 44, 'বাগেরহাট সদর'),
(337, 5, 44, 'মোল্লাহাট'),
(338, 5, 44, 'শরণখোলা'),
(339, 5, 44, 'রামপাল'),
(340, 5, 44, 'মোড়েলগঞ্জ'),
(341, 5, 44, 'কচুয়া'),
(342, 5, 44, 'মোংলা'),
(343, 5, 44, 'চিতলমারী'),
(344, 5, 45, 'ঝিনাইদহ সদর'),
(345, 5, 45, 'শৈলকুপা'),
(346, 5, 45, 'হরিণাকুণ্ডু '),
(347, 5, 45, 'কালীগঞ্জ'),
(348, 5, 45, 'কোটচাঁদপুর'),
(349, 5, 45, 'মহেশপুর'),
(350, 5, 46, 'কুষ্টিয়া সদর'),
(351, 5, 46, 'কুমারখালী'),
(352, 5, 46, 'খোকসা'),
(353, 5, 46, 'মিরপুর'),
(354, 5, 46, 'দৌলতপুর'),
(355, 5, 46, 'ভেড়ামারা'),
(356, 6, 47, 'বরিশাল সদর'),
(357, 6, 47, 'বাকেরগঞ্জ'),
(358, 6, 47, 'বাবুগঞ্জ'),
(359, 6, 47, 'উজিরপুর'),
(360, 6, 47, 'বানারীপাড়া'),
(361, 6, 47, 'গৌরনদী'),
(362, 6, 47, 'আগৈলঝাড়া'),
(363, 6, 47, 'মেহেন্দিগঞ্জ'),
(364, 6, 47, 'মুলাদী'),
(365, 6, 47, 'হিজলা'),
(366, 6, 48, 'ঝালকাঠি সদর'),
(367, 6, 48, 'কাঠালিয়া'),
(368, 6, 48, 'নলছিটি'),
(369, 6, 48, 'রাজাপুর'),
(370, 6, 49, 'বাউফল'),
(371, 6, 49, 'পটুয়াখালী সদর'),
(372, 6, 49, 'দুমকি'),
(373, 6, 49, 'দশমিনা'),
(374, 6, 49, 'কলাপাড়া'),
(375, 6, 49, 'মির্জাগঞ্জ'),
(376, 6, 49, 'গলাচিপা'),
(377, 6, 49, 'রাঙ্গাবালী'),
(378, 6, 50, 'পিরোজপুর সদর'),
(379, 6, 50, 'নাজিরপুর'),
(380, 6, 50, 'কাউখালী'),
(381, 6, 50, 'জিয়ানগর'),
(382, 6, 50, 'ভান্ডারিয়া'),
(383, 6, 50, 'মঠবাড়ীয়া'),
(384, 6, 50, 'নেছারাবাদ'),
(385, 6, 51, 'ভোলা সদর'),
(386, 6, 51, 'বোরহানউদ্দিন'),
(387, 6, 51, 'চরফ্যাশন'),
(388, 6, 51, 'দৌলতখান'),
(389, 6, 51, 'মনপুরা'),
(390, 6, 51, 'তজুমদ্দিন'),
(391, 6, 51, 'লালমোহন'),
(392, 6, 52, 'আমতলী'),
(393, 6, 52, 'বরগুনা সদর'),
(394, 6, 52, 'বেতাগী'),
(395, 6, 52, 'বামনা'),
(396, 6, 52, 'পাথরঘাটা'),
(397, 6, 52, 'তালতলি'),
(398, 7, 53, 'রংপুর সদর'),
(399, 7, 53, 'গঙ্গাচড়া'),
(400, 7, 53, 'তারাগঞ্জ'),
(401, 7, 53, 'বদরগঞ্জ'),
(402, 7, 53, 'মিঠাপুকুর'),
(403, 7, 53, 'কাউনিয়া'),
(404, 7, 53, 'পীরগঞ্জ'),
(405, 7, 53, 'পীরগাছা'),
(406, 7, 54, 'লালমনিরহাট সদর'),
(407, 7, 54, 'আদিতমারী'),
(408, 7, 54, 'কালীগঞ্জ'),
(409, 7, 54, 'হাতীবান্ধা'),
(410, 7, 54, 'পাটগ্রাম'),
(411, 7, 55, 'পঞ্চগড় সদর'),
(412, 7, 55, 'দেবীগঞ্জ'),
(413, 7, 55, 'বোদা'),
(414, 7, 55, 'আটোয়ারী'),
(415, 7, 55, 'তেতুলিয়া'),
(416, 7, 56, 'কুড়িগ্রাম সদর'),
(417, 7, 56, 'নাগেশ্বরী'),
(418, 7, 56, 'ভুরুঙ্গামারী'),
(419, 7, 56, 'ফুলবাড়ী'),
(420, 7, 56, 'রাজারহাট'),
(421, 7, 56, 'উলিপুর'),
(422, 7, 56, 'চিলমারী'),
(423, 7, 56, 'রৌমারী'),
(424, 7, 56, 'চর রাজিবপুর'),
(425, 7, 57, 'নবাবগঞ্জ'),
(426, 7, 57, 'বীরগঞ্জ'),
(427, 7, 57, 'ঘোড়াঘাট'),
(428, 7, 57, 'বিরামপুর'),
(429, 7, 57, 'পার্বতীপুর'),
(430, 7, 57, 'বোচাগঞ্জ'),
(431, 7, 57, 'কাহারোল'),
(432, 7, 57, 'ফুলবাড়ী'),
(433, 7, 57, 'দিনাজপুর সদর'),
(434, 7, 57, 'হাকিমপুর'),
(435, 7, 57, 'খানসামা'),
(436, 7, 57, 'বিরল'),
(437, 7, 57, 'চিরিরবন্দর'),
(438, 7, 58, 'ঠাকুরগাঁও সদর'),
(439, 7, 58, 'পীরগঞ্জ'),
(440, 7, 58, 'রাণীশংকৈল'),
(441, 7, 58, 'হরিপুর'),
(442, 7, 58, 'বালিয়াডাঙ্গী'),
(443, 7, 59, 'সাদুল্লাপুর'),
(444, 7, 59, 'গাইবান্ধা সদর'),
(445, 7, 59, 'পলাশবাড়ী'),
(446, 7, 59, 'সাঘাটা'),
(447, 7, 59, 'গোবিন্দগঞ্জ'),
(448, 7, 59, 'সুন্দরগঞ্জ'),
(449, 7, 59, 'ফুলছড়ি'),
(450, 7, 60, 'সৈয়দপুর'),
(451, 7, 60, 'ডোমার'),
(452, 7, 60, 'ডিমলা'),
(453, 7, 60, 'জলঢাকা'),
(454, 7, 60, 'কিশোরগঞ্জ'),
(455, 7, 60, 'নীলফামারী সদর'),
(456, 8, 61, 'ফুলবাড়ীয়া '),
(457, 8, 61, 'ত্রিশাল'),
(458, 8, 61, 'ভালুকা'),
(459, 8, 61, 'মুক্তাগাছা'),
(460, 8, 61, 'ময়মনসিংহ সদর'),
(461, 8, 61, 'ধোবাউরা'),
(462, 8, 61, 'ফুলপুর'),
(463, 8, 61, 'হালুয়াঘাট'),
(464, 8, 61, 'গৌরীপুর'),
(465, 8, 61, 'গফরগাঁও'),
(466, 8, 61, 'ঈশ্বরগঞ্জ'),
(467, 8, 61, 'নান্দাইল'),
(468, 8, 61, 'তারাকান্দা'),
(469, 8, 62, 'জামালপুর সদর'),
(470, 8, 62, 'মেলান্দহ'),
(471, 8, 62, 'ইসলামপুর'),
(472, 8, 62, 'দেওয়ানগঞ্জ'),
(473, 8, 62, 'সরিষাবাড়ী'),
(474, 8, 62, 'মাদারগঞ্জ'),
(475, 8, 62, 'বকশীগঞ্জ'),
(476, 8, 63, 'বারহাট্টা'),
(477, 8, 63, 'দুর্গাপুর'),
(478, 8, 63, 'কেন্দুয়া'),
(479, 8, 63, 'আটপাড়া'),
(480, 8, 63, 'মদন'),
(481, 8, 63, 'খালিয়াজুরী'),
(482, 8, 63, 'কলমাকান্দা'),
(483, 8, 63, 'মোহনগঞ্জ'),
(484, 8, 63, 'পূর্বধলা'),
(485, 8, 63, 'নেত্রকোনা সদর'),
(486, 8, 64, 'শেরপুর সদর'),
(487, 8, 64, 'নালিতাবাড়ী'),
(488, 8, 64, 'শ্রীবরদী'),
(489, 8, 64, 'নকলা'),
(490, 8, 64, 'ঝিনাইগাতী'),
(491, 1, 1, 'ঢাকা মহানগর');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_zilla`
--

CREATE TABLE `tbl_zilla` (
  `id` int(11) NOT NULL,
  `divission_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tbl_zilla`
--

INSERT INTO `tbl_zilla` (`id`, `divission_id`, `name`) VALUES
(1, 1, 'ঢাকা'),
(2, 1, 'গাজীপুর'),
(3, 1, 'টাঙ্গাইল'),
(4, 1, 'নারায়ণগঞ্জ'),
(5, 1, 'কিশোরগঞ্জ'),
(6, 1, 'নরসিংদী'),
(7, 1, 'রাজবাড়ী'),
(8, 1, 'ফরিদপুর'),
(9, 1, 'মাদারীপুর'),
(10, 1, 'গোপালগঞ্জ'),
(11, 1, 'মুন্সিগঞ্জ'),
(12, 1, 'মানিকগঞ্জ'),
(13, 1, 'শরীয়তপুর'),
(14, 2, 'রাজশাহী'),
(15, 2, 'সিরাজগঞ্জ'),
(16, 2, 'পাবনা'),
(17, 2, 'বগুড়া'),
(18, 2, 'চাঁপাইনবাবগঞ্জ'),
(19, 2, 'জয়পুরহাট'),
(20, 2, 'নওগাঁ'),
(21, 2, 'নাটোর'),
(22, 3, 'চট্টগ্রাম'),
(23, 3, 'কুমিল্লা'),
(24, 3, 'ফেনী'),
(25, 3, 'ব্রাহ্মণবাড়িয়া'),
(26, 3, 'রাঙ্গামাটি'),
(27, 3, 'চাঁদপুর'),
(28, 3, 'নোয়াখালী'),
(29, 3, 'লক্ষ্মীপুর'),
(30, 3, 'কক্সবাজার'),
(31, 3, 'খাগড়াছড়ি'),
(32, 3, 'বান্দরবান'),
(33, 4, 'সিলেট'),
(34, 4, 'মৌলভীবাজার'),
(35, 4, 'হবিগঞ্জ'),
(36, 4, 'সুনামগঞ্জ'),
(37, 5, 'খুলনা'),
(38, 5, 'যশোর'),
(39, 5, 'সাতক্ষীরা'),
(40, 5, 'মেহেরপুর'),
(41, 5, 'নড়াইল'),
(42, 5, 'চুয়াডাঙ্গা'),
(43, 5, 'মাগুড়া'),
(44, 5, 'বাগেরহাট'),
(45, 5, 'ঝিনাইদহ'),
(46, 5, 'কুষ্টিয়া'),
(47, 6, 'বরিশাল'),
(48, 6, 'ঝালকাঠি'),
(49, 6, 'পটুয়াখালী'),
(50, 6, 'পিরোজপুর'),
(51, 6, 'ভোলা'),
(52, 6, 'বরগুনা'),
(53, 7, 'রংপুর'),
(54, 7, 'লালমনিরহাট'),
(55, 7, 'পঞ্চগড়'),
(56, 7, 'কুড়িগ্রাম'),
(57, 7, 'দিনাজপুর'),
(58, 7, 'ঠাকুরগাঁও'),
(59, 7, 'গাইবান্ধা'),
(60, 7, 'নীলফামারী'),
(61, 8, 'ময়মনসিংহ'),
(62, 8, 'জামালপুর'),
(63, 8, 'নেত্রকোনা'),
(64, 8, 'শেরপুর');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstname` text NOT NULL,
  `lastname` text NOT NULL,
  `username` varchar(10) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` text NOT NULL,
  `address` int(11) NOT NULL COMMENT 'thana id',
  `roadHouse` text NOT NULL,
  `phone` text NOT NULL,
  `userType` text NOT NULL,
  `company` text,
  `photo` text NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 for active user, 0 for not active user',
  `emailVerified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 for verify, 0 for not verify',
  `mobileVerified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 for verify, 0 for not verify'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `username`, `email`, `password`, `address`, `roadHouse`, `phone`, `userType`, `company`, `photo`, `status`, `emailVerified`, `mobileVerified`) VALUES
(1, 'Md. Rayhanuzzaman ', 'Roky', 'roky', 'rzrokycse@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 11, '12/6 mpur', '01834014071', 'admin', NULL, 'assets/userPhoto/IMG_20190908161211.jpg', 1, 1, 1),
(2, 'Turjo', 'Alom', 'Turzo123', 'rony@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 416, '12/43, green road.', '01716545454', 'user', NULL, 'assets/userPhoto/man13.jpg', 1, 0, 0),
(3, 'Dr Golam', 'Kibria', 'doctor', 'doctor@gmail.com', '1f0160076c9f42a157f0a8f0dcc68e02ff69045b', 196, '12/90, Solimullah Road.', '01812676767', 'user', 'Google', 'assets/userPhoto/download_20190925125417.jpg', 1, 0, 0),
(4, 'shoriful', 'islam', 'user', 'shoriful@gmail.com', '8cb2237d0679ca88db6464eac60da96345513964', 109, '45/dxbx', '01723456789', 'user', NULL, 'assets/userPhoto/defaultUser.jpg', 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `id` int(11) NOT NULL,
  `value` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='types of user, each type has single controller';

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`id`, `value`, `name`) VALUES
(1, 'user', 'User'),
(2, 'representative', 'Representative');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `password_recovery`
--
ALTER TABLE `password_recovery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_activities`
--
ALTER TABLE `tbl_activities`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_chief_complaints`
--
ALTER TABLE `tbl_chief_complaints`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_clients`
--
ALTER TABLE `tbl_clients`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_common_pages`
--
ALTER TABLE `tbl_common_pages`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `tbl_divission`
--
ALTER TABLE `tbl_divission`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_doctor_opinion`
--
ALTER TABLE `tbl_doctor_opinion`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_doctor_rejection`
--
ALTER TABLE `tbl_doctor_rejection`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_drug`
--
ALTER TABLE `tbl_drug`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_drug_company`
--
ALTER TABLE `tbl_drug_company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_drug_continue_rules`
--
ALTER TABLE `tbl_drug_continue_rules`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_drug_generic`
--
ALTER TABLE `tbl_drug_generic`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_drug_take_format`
--
ALTER TABLE `tbl_drug_take_format`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_drug_take_time_rules`
--
ALTER TABLE `tbl_drug_take_time_rules`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_drug_type`
--
ALTER TABLE `tbl_drug_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_mail_send_setting`
--
ALTER TABLE `tbl_mail_send_setting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_medical_test`
--
ALTER TABLE `tbl_medical_test`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_own_examinations`
--
ALTER TABLE `tbl_own_examinations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_prescription`
--
ALTER TABLE `tbl_prescription`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_prescription_medicine`
--
ALTER TABLE `tbl_prescription_medicine`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_prescription_oe`
--
ALTER TABLE `tbl_prescription_oe`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_reference`
--
ALTER TABLE `tbl_reference`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_rules_for_patient`
--
ALTER TABLE `tbl_rules_for_patient`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sms_send_list`
--
ALTER TABLE `tbl_sms_send_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sms_send_setting`
--
ALTER TABLE `tbl_sms_send_setting`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_upozilla`
--
ALTER TABLE `tbl_upozilla`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_zilla`
--
ALTER TABLE `tbl_zilla`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `password_recovery`
--
ALTER TABLE `password_recovery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_activities`
--
ALTER TABLE `tbl_activities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;

--
-- AUTO_INCREMENT for table `tbl_chief_complaints`
--
ALTER TABLE `tbl_chief_complaints`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_clients`
--
ALTER TABLE `tbl_clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_common_pages`
--
ALTER TABLE `tbl_common_pages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_divission`
--
ALTER TABLE `tbl_divission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_doctor_opinion`
--
ALTER TABLE `tbl_doctor_opinion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_doctor_rejection`
--
ALTER TABLE `tbl_doctor_rejection`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tbl_drug`
--
ALTER TABLE `tbl_drug`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `tbl_drug_company`
--
ALTER TABLE `tbl_drug_company`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_drug_continue_rules`
--
ALTER TABLE `tbl_drug_continue_rules`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_drug_generic`
--
ALTER TABLE `tbl_drug_generic`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `tbl_drug_take_format`
--
ALTER TABLE `tbl_drug_take_format`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tbl_drug_take_time_rules`
--
ALTER TABLE `tbl_drug_take_time_rules`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_drug_type`
--
ALTER TABLE `tbl_drug_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_mail_send_setting`
--
ALTER TABLE `tbl_mail_send_setting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_medical_test`
--
ALTER TABLE `tbl_medical_test`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tbl_own_examinations`
--
ALTER TABLE `tbl_own_examinations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `tbl_prescription`
--
ALTER TABLE `tbl_prescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT for table `tbl_prescription_medicine`
--
ALTER TABLE `tbl_prescription_medicine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `tbl_prescription_oe`
--
ALTER TABLE `tbl_prescription_oe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `tbl_reference`
--
ALTER TABLE `tbl_reference`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_rules_for_patient`
--
ALTER TABLE `tbl_rules_for_patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_sms_send_list`
--
ALTER TABLE `tbl_sms_send_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_sms_send_setting`
--
ALTER TABLE `tbl_sms_send_setting`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_upozilla`
--
ALTER TABLE `tbl_upozilla`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=492;

--
-- AUTO_INCREMENT for table `tbl_zilla`
--
ALTER TABLE `tbl_zilla`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user_type`
--
ALTER TABLE `user_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
