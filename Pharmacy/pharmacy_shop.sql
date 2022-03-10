-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 22, 2020 at 09:56 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pharmacy_shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_drug`
--

CREATE TABLE `tbl_drug` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_bin NOT NULL,
  `medicine_group_id` int(11) NOT NULL,
  `company_id` int(11) DEFAULT NULL,
  `insert_by` int(11) NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_update` varchar(16) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tbl_drug`
--

INSERT INTO `tbl_drug` (`id`, `name`, `medicine_group_id`, `company_id`, `insert_by`, `insert_time`, `last_update`) VALUES
(1, 'Napa', 1, 3, 1, '2018-11-25 15:01:52', '0000-00-00 00:00'),
(2, 'NapaDol', 1, 3, 1, '2018-11-25 15:02:04', '0000-00-00 00:00'),
(3, 'napaExtend', 1, 3, 1, '2018-11-25 15:02:14', '0000-00-00 00:00'),
(4, 'napaExtra', 1, 3, 1, '2018-11-25 15:02:22', '0000-00-00 00:00'),
(5, 'ACE', 1, 1, 1, '2018-11-25 15:04:34', '0000-00-00 00:00'),
(6, 'Remac', 2, 1, 1, '2018-11-25 15:07:13', '0000-00-00 00:00'),
(7, 'Ranolin', 3, 1, 1, '2018-11-25 15:07:52', '0000-00-00 00:00'),
(8, 'Reset 1g', 1, 2, 1, '2018-11-25 15:11:31', '0000-00-00 00:00'),
(9, 'Klarix', 2, 2, 1, '2018-11-25 15:12:42', '0000-00-00 00:00'),
(10, 'Ralozine', 3, 2, 1, '2018-11-25 15:13:20', '0000-00-00 00:00');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_medicine`
--

CREATE TABLE `tbl_medicine` (
  `id` int(11) NOT NULL,
  `drug_id` int(11) NOT NULL,
  `power` text COLLATE utf8_bin NOT NULL,
  `medicine_type_id` int(11) NOT NULL COMMENT 'sirap, osudh, injection etc...',
  `quantity` int(11) NOT NULL,
  `buy_amount` float DEFAULT NULL,
  `sale_per_quantity` float NOT NULL,
  `expired` date NOT NULL DEFAULT '0000-00-00',
  `representative_id` int(11) DEFAULT NULL COMMENT 'user id of representative',
  `company_id` int(11) DEFAULT NULL,
  `shop_id` int(11) NOT NULL,
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00:00',
  `insert_by` int(11) NOT NULL,
  `remark` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_medicine`
--

INSERT INTO `tbl_medicine` (`id`, `drug_id`, `power`, `medicine_type_id`, `quantity`, `buy_amount`, `sale_per_quantity`, `expired`, `representative_id`, `company_id`, `shop_id`, `insert_time`, `insert_by`, `remark`) VALUES
(1, 1, '325', 5, 100, 150, 2, '2019-11-30', NULL, NULL, 1, '2018-11-25 21:46:50', 1, 'removed remark'),
(2, 3, '625', 5, 30, 120, 5, '2018-11-30', NULL, NULL, 1, '2018-11-25 21:46:50', 1, ''),
(3, 8, '1g', 4, 25, 1350, 100, '2019-01-31', NULL, NULL, 1, '2018-11-25 21:46:50', 1, ''),
(4, 7, '500mg', 5, 40, 160, 5, '2019-11-30', NULL, NULL, 1, '2018-11-25 21:46:50', 1, ''),
(5, 6, '500 mg', 5, 40, 1000, 30, '2018-12-12', NULL, NULL, 1, '2018-11-25 21:46:50', 1, ''),
(6, 4, '500mg', 5, 100, 2.5, 3, '2019-10-31', NULL, NULL, 1, '2019-10-21 11:51:26', 1, 'For Test'),
(7, 1, '500mg', 5, 100, 3, 4, '2019-12-31', NULL, NULL, 1, '2019-10-21 14:41:45', 3, 'Argent Needed'),
(8, 10, '500mg', 6, 100, 2.5, 3, '2019-10-31', NULL, NULL, 1, '2019-10-21 16:51:50', 4, 'test');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_medicine_group`
--

CREATE TABLE `tbl_medicine_group` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_bin NOT NULL,
  `shop_id` int(11) NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_medicine_group`
--

INSERT INTO `tbl_medicine_group` (`id`, `name`, `shop_id`, `insert_time`, `insert_by`) VALUES
(1, 'Paracetamol', 1, '2018-11-25 15:01:31', 1),
(2, 'Clarithromycin', 1, '2018-11-25 15:06:52', 1),
(3, 'Ranolazine', 1, '2018-11-25 15:07:40', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_medicine_type`
--

CREATE TABLE `tbl_medicine_type` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_medicine_type`
--

INSERT INTO `tbl_medicine_type` (`id`, `name`) VALUES
(4, 'Liquid'),
(5, 'Tablet'),
(6, 'Capsules'),
(7, 'Suppositories'),
(8, 'Drops'),
(9, 'Inhalers'),
(10, 'Injections');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_money_receipt`
--

CREATE TABLE `tbl_money_receipt` (
  `id` int(11) NOT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `money_receipt_code` varchar(20) COLLATE utf8_bin NOT NULL,
  `discount` float DEFAULT NULL,
  `net_amount` float NOT NULL,
  `client_name` text COLLATE utf8_bin,
  `client_contact` text COLLATE utf8_bin,
  `client_address` text COLLATE utf8_bin,
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00:00',
  `insert_by` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '1 =good client, 2= best client, 3= bad client',
  `is_online` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 = not, 1 = yes'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_money_receipt`
--

INSERT INTO `tbl_money_receipt` (`id`, `shop_id`, `money_receipt_code`, `discount`, `net_amount`, `client_name`, `client_contact`, `client_address`, `insert_time`, `insert_by`, `status`, `is_online`) VALUES
(1, 1, '20181125091716', 24, 500, 'Rayhanuzzaman Roky', 'Phone: 01709372481', '12/6 Solimullah Road', '2018-11-25 21:18:13', 1, 1, 0),
(2, 1, '20181125115106', 5, 20, 'ROKMUNUR ZAMAN John Doe', 'Phone: (555) 539-1037', '795 Folsom Ave, Suite 600', '2018-11-25 23:51:31', 1, 1, 0),
(3, 1, '20190829035613', 20, 300, 'Rayhanuzzaman Roky', 'Phone: 01834014071', '12/6 Solimullah Road, Mohammadpur', '2019-08-29 15:58:11', 1, 1, 0),
(4, 1, '20191021115737', 1, 115, 'Foisal Ahmed', '01738244627', 'Dhaka', '2019-10-21 11:58:49', 1, 1, 0),
(5, 1, '20191021051246', 0, 0, 'John Doe', 'Phone: (555) 539-1037', '795 Folsom Ave, Suite 600', '2019-10-21 17:15:35', 4, 1, 0),
(6, 1, '20191021051805', 20, 350, 'John Doe', 'Phone: (555) 539-1037', '795 Folsom Ave, Suite 600', '2019-10-21 17:26:57', 4, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rack`
--

CREATE TABLE `tbl_rack` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_bin NOT NULL,
  `shop_id` int(11) NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `insert_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_rack`
--

INSERT INTO `tbl_rack` (`id`, `name`, `shop_id`, `insert_time`, `insert_by`) VALUES
(1, 'A-1', 1, '2018-11-25 15:27:13', 1),
(2, 'B-1', 1, '2018-11-25 15:27:16', 1),
(3, 'B-2', 1, '2018-11-25 15:27:20', 1),
(4, 'A-2', 1, '2018-11-25 15:27:23', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rack_join_medicine_group`
--

CREATE TABLE `tbl_rack_join_medicine_group` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `rack_id` int(11) NOT NULL,
  `insert_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- Dumping data for table `tbl_rack_join_medicine_group`
--

INSERT INTO `tbl_rack_join_medicine_group` (`id`, `group_id`, `rack_id`, `insert_time`) VALUES
(1, 1, 1, '2018-11-25 15:27:59'),
(3, 3, 3, '2018-11-25 15:28:12'),
(4, 2, 2, '2019-10-21 05:44:38');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sale_record`
--

CREATE TABLE `tbl_sale_record` (
  `id` int(11) NOT NULL,
  `medicine_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `discount` float NOT NULL,
  `net_amount` float NOT NULL,
  `money_receipt_id` int(11) DEFAULT NULL COMMENT 'always money receipt can no be generate',
  `insert_time` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT '0000-00-00 00:00:00',
  `insert_by` int(11) NOT NULL,
  `is_online` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 = not, 1 = yes'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `tbl_sale_record`
--

INSERT INTO `tbl_sale_record` (`id`, `medicine_id`, `quantity`, `discount`, `net_amount`, `money_receipt_id`, `insert_time`, `insert_by`, `is_online`) VALUES
(1, 1, 2, 0, 4, 1, '2018-11-25 21:18:13', 1, 0),
(2, 4, 4, 0, 20, 1, '2018-11-25 21:18:13', 1, 0),
(3, 3, 5, 0, 500, 1, '2018-11-25 21:18:13', 1, 0),
(4, 2, 5, 0, 25, 2, '2018-11-25 23:51:31', 1, 0),
(5, 1, 10, 0, 20, 3, '2019-08-29 15:58:11', 1, 0),
(6, 0, 0, 0, 0, 3, '2019-08-29 15:58:11', 1, 0),
(7, 5, 10, 0, 300, 3, '2019-08-29 15:58:11', 1, 0),
(8, 1, 18, 0, 36, 4, '2019-10-21 11:58:49', 1, 0),
(9, 2, 10, 0, 50, 4, '2019-10-21 11:58:49', 1, 0),
(10, 6, 10, 0, 30, 4, '2019-10-21 11:58:49', 1, 0),
(11, 6, 0, 0, 0, 5, '2019-10-21 17:15:35', 4, 0),
(12, 1, 0, 0, 0, 5, '2019-10-21 17:15:35', 4, 0),
(13, 1, 10, 0, 20, 6, '2019-10-21 17:26:57', 4, 0),
(14, 2, 10, 0, 50, 6, '2019-10-21 17:26:57', 4, 0),
(15, 5, 10, 0, 300, 6, '2019-10-21 17:26:57', 4, 0);

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
(1, 'Md. Rayhanuzzaman ', 'Roky', 'admin', 'rzrokycse@gmail.com', '402e679962724aa5f8ba96f57560a049eb6673f3', 11, '12/6 mpur', '01834014071', 'admin', NULL, 'assets/userPhoto/user2-160x160_jpg.jpg', 1, 1, 1),
(2, 'Turjo', 'Alom', 'Turzo123', 'rony@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 416, '12/43, green road.', '01716545454', 'user', NULL, 'assets/userPhoto/man13.jpg', 1, 0, 0),
(3, 'Ruhul ', 'Shah', 'ruhul123', 'ruhul@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 491, '12/90, Solimullah Road.', '01812676767', 'representative', 'Google', 'assets/userPhoto/bdictnews24jac-ma.jpg', 1, 0, 0),
(4, 'Foishaal', 'Ahmed', 'Foishaal', 'Foishaal@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 97, 'Madaripur', '01738244627', 'user', NULL, 'assets/userPhoto/personstandingpinksilhouettep_20191021150535.jpg', 1, 0, 0);

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
-- Indexes for table `tbl_drug`
--
ALTER TABLE `tbl_drug`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_medicine`
--
ALTER TABLE `tbl_medicine`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_medicine_group`
--
ALTER TABLE `tbl_medicine_group`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_medicine_type`
--
ALTER TABLE `tbl_medicine_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_money_receipt`
--
ALTER TABLE `tbl_money_receipt`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `money_receipt_id` (`money_receipt_code`);

--
-- Indexes for table `tbl_rack`
--
ALTER TABLE `tbl_rack`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_rack_join_medicine_group`
--
ALTER TABLE `tbl_rack_join_medicine_group`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sale_record`
--
ALTER TABLE `tbl_sale_record`
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
-- AUTO_INCREMENT for table `tbl_drug`
--
ALTER TABLE `tbl_drug`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `tbl_medicine`
--
ALTER TABLE `tbl_medicine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `tbl_medicine_group`
--
ALTER TABLE `tbl_medicine_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `tbl_medicine_type`
--
ALTER TABLE `tbl_medicine_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `tbl_money_receipt`
--
ALTER TABLE `tbl_money_receipt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `tbl_rack`
--
ALTER TABLE `tbl_rack`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tbl_rack_join_medicine_group`
--
ALTER TABLE `tbl_rack_join_medicine_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tbl_sale_record`
--
ALTER TABLE `tbl_sale_record`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
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
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
