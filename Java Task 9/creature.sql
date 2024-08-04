-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2024 at 01:00 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `creature`
--

-- --------------------------------------------------------

--
-- Table structure for table `creatures`
--

CREATE TABLE `creatures` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` enum('GOOD','NEUTRAL','MALICIOUS') NOT NULL,
  `potion_usage` text DEFAULT NULL,
  `date_found` date DEFAULT NULL,
  `wizard_name` varchar(255) DEFAULT NULL,
  `endangered` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `creatures`
--

INSERT INTO `creatures` (`id`, `name`, `type`, `potion_usage`, `date_found`, `wizard_name`, `endangered`) VALUES
(1, 'Phoenix', 'GOOD', 'Restores life', '2023-01-15', 'Dumbledore', 0),
(2, 'Basilisk', 'MALICIOUS', 'Venomous', '2023-05-20', 'Salazar Slytherin', 1),
(3, 'Hippogriff', 'NEUTRAL', 'None', '2022-12-10', 'Hagrid', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `creatures`
--
ALTER TABLE `creatures`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `creatures`
--
ALTER TABLE `creatures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
