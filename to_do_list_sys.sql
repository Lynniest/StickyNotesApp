-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 29, 2023 at 06:59 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `to_do_list_sys`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorytable`
--

CREATE TABLE `categorytable` (
  `cate_name` varchar(32) NOT NULL,
  `cate_created_date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categorytable`
--

INSERT INTO `categorytable` (`cate_name`, `cate_created_date`) VALUES
('Test Category', '2023-04-25 06:49:02'),
('Test Category 2', '2023-04-25 06:49:10'),
('Test Category 3', '2023-04-25 07:27:13');

-- --------------------------------------------------------

--
-- Table structure for table `stickynotestable`
--

CREATE TABLE `stickynotestable` (
  `note_id` int(11) NOT NULL,
  `title` varchar(32) NOT NULL,
  `content` varchar(128) NOT NULL,
  `date_created` varchar(20) NOT NULL,
  `completed` tinyint(1) DEFAULT 0,
  `sticked` tinyint(1) DEFAULT 0,
  `category` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stickynotestable`
--

INSERT INTO `stickynotestable` (`note_id`, `title`, `content`, `date_created`, `completed`, `sticked`, `category`) VALUES
(22, 'Some Title', 'Do Something Funky', '2023-04-29 01:43:22', 0, 1, 'Test Category'),
(23, 'God\' Menu', 'Is Ray Lizzy\'s biological father?', '2023-04-29 01:44:32', 0, 1, 'Test Category'),
(24, 'Do The Laundary', 'Before June 1st*', '2023-04-29 01:45:50', 0, 1, 'Test Category 3'),
(25, 'Finish the list', 'Work as fast as you can', '2023-04-29 01:46:29', 0, 1, 'Test Category 2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categorytable`
--
ALTER TABLE `categorytable`
  ADD PRIMARY KEY (`cate_name`);

--
-- Indexes for table `stickynotestable`
--
ALTER TABLE `stickynotestable`
  ADD PRIMARY KEY (`note_id`),
  ADD KEY `fk_category` (`category`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `stickynotestable`
--
ALTER TABLE `stickynotestable`
  MODIFY `note_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `stickynotestable`
--
ALTER TABLE `stickynotestable`
  ADD CONSTRAINT `fk_category` FOREIGN KEY (`category`) REFERENCES `categorytable` (`cate_name`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
