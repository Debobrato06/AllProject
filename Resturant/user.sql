-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 14, 2019 at 06:08 AM
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
-- Database: `hrsemail_restau`
--

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
  `photo` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 for active user, 0 for not active user',
  `emailVerified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 for verify, 0 for not verify',
  `mobileVerified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '1 for verify, 0 for not verify'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `username`, `email`, `password`, `address`, `roadHouse`, `phone`, `userType`, `photo`, `status`, `emailVerified`, `mobileVerified`) VALUES
(1, 'Md Rayhanuzzaman', 'Roky', 'roky', 'rzroky@hrsoftbd.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 1, '12/6 solimullah road', '01709372481', 'admin', 'assets/userPhoto/IMG_20190325144033.jpg', 1, 0, 0),
(2, 'mamun', 'khan', 'mamun214', 'mamun214@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 16, '23/67, black road.', '01716545454', 'user', 'assets/userPhoto/al_mamun.jpg', 1, 0, 0),
(3, 'Md.Rabiul', 'Alam', 'rabiul', 'rabiuls39@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 487, 'Polashikura', '01738244627', 'admin', 'assets/userPhoto/n_20190119114514.jpg', 1, 0, 0),
(4, 'Foishaal', 'Ahmed', 'Foishaal', 'Foishaal@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 97, 'Madaripur', '01738244627', 'user', 'assets/userPhoto/personstandingpinksilhouettep_20190820165942.jpg', 1, 0, 0),
(6, 'Raju', 'Ahmed', 'raju', 'raju@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 0, '', '01738244627', 'customer', '', 1, 0, 0),
(7, 'Shrabon', 'Ahmed', 'Shrabon', 'Shrabon@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', 0, '', '01763643663', 'customer', '', 1, 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`,`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
