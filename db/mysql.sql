-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 05, 2022 at 09:22 AM
-- Server version: 10.5.11-MariaDB
-- PHP Version: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `new_internet_shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(13) NOT NULL,
  `user_id` int(13) NOT NULL,
  `product_id` int(13) NOT NULL,
  `amount` int(13) NOT NULL,
  `price` int(13) NOT NULL,
  `date` timestamp NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `cart_view`
-- (See below for the actual view)
--
CREATE TABLE `cart_view` (
`id` int(13)
,`user_id` int(13)
,`product_id` int(13)
,`amount` int(13)
,`price` decimal(22,2)
,`date` timestamp
,`status` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(13) NOT NULL,
  `identifier` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `locale_ua` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `locale_en` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `id` int(13) NOT NULL,
  `uuid` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mime` varchar(48) COLLATE utf8mb4_unicode_ci NOT NULL,
  `data` mediumblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(13) NOT NULL,
  `shortname` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fullname` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int(13) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `color` int(13) NOT NULL,
  `size_unit` int(13) NOT NULL,
  `size_value` int(13) NOT NULL,
  `category_id` int(13) NOT NULL,
  `preview_image` int(13) NOT NULL,
  `start_date` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `products_view`
-- (See below for the actual view)
--
CREATE TABLE `products_view` (
`id` int(13)
,`shortname` varchar(56)
,`fullname` varchar(200)
,`description` text
,`amount` int(13)
,`price` decimal(10,2)
,`color` int(13)
,`size_unit` int(13)
,`size_value` int(13)
,`category_id` int(13)
,`bought_counter` decimal(34,0)
,`preview_image` int(13)
,`start_date` int(16)
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(13) NOT NULL,
  `name` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `surname` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `login` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `birth_date` date NOT NULL,
  `balance` decimal(10,2) NOT NULL DEFAULT 0.00,
  `status` int(11) NOT NULL DEFAULT 0,
  `avatar` int(13) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users_block`
--

CREATE TABLE `users_block` (
  `id` int(13) NOT NULL,
  `user_id` int(13) NOT NULL,
  `reason` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_time` int(16) NOT NULL,
  `end_time` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users_purchase`
--

CREATE TABLE `users_purchase` (
  `id` int(13) NOT NULL,
  `user_id` int(13) NOT NULL,
  `product_id` int(13) NOT NULL,
  `amount` int(13) NOT NULL,
  `price` int(13) NOT NULL,
  `date` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Stand-in structure for view `users_view`
-- (See below for the actual view)
--
CREATE TABLE `users_view` (
`id` int(13)
,`name` varchar(56)
,`surname` varchar(56)
,`email` varchar(56)
,`login` varchar(56)
,`password` varchar(200)
,`birth_date` date
,`balance` decimal(10,2)
,`avatar` int(13)
,`status` int(11)
,`blocked` int(1)
);

-- --------------------------------------------------------

--
-- Structure for view `cart_view`
--
DROP TABLE IF EXISTS `cart_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `cart_view`  AS SELECT `cart`.`id` AS `id`, `cart`.`user_id` AS `user_id`, `cart`.`product_id` AS `product_id`, `cart`.`amount` AS `amount`, if(`cart`.`status` <> 2,(select `cart`.`amount` * `products`.`price` from `products` where `products`.`id` = `cart`.`product_id`),`cart`.`price`) AS `price`, `cart`.`date` AS `date`, `cart`.`status` AS `status` FROM `cart` WHERE 1 ;

-- --------------------------------------------------------

--
-- Structure for view `products_view`
--
DROP TABLE IF EXISTS `products_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `products_view`  AS SELECT `products`.`id` AS `id`, `products`.`shortname` AS `shortname`, `products`.`fullname` AS `fullname`, `products`.`description` AS `description`, `products`.`amount` AS `amount`, `products`.`price` AS `price`, `products`.`color` AS `color`, `products`.`size_unit` AS `size_unit`, `products`.`size_value` AS `size_value`, `products`.`category_id` AS `category_id`, (select sum(`cart`.`amount`) from `cart` where `cart`.`product_id` = `products`.`id` and `cart`.`status` = 2) AS `bought_counter`, `products`.`preview_image` AS `preview_image`, `products`.`start_date` AS `start_date` FROM `products` WHERE 1 ;

-- --------------------------------------------------------

--
-- Structure for view `users_view`
--
DROP TABLE IF EXISTS `users_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `users_view`  AS SELECT `users`.`id` AS `id`, `users`.`name` AS `name`, `users`.`surname` AS `surname`, `users`.`email` AS `email`, `users`.`login` AS `login`, `users`.`password` AS `password`, `users`.`birth_date` AS `birth_date`, `users`.`balance` AS `balance`, `users`.`avatar` AS `avatar`, `users`.`status` AS `status`, if(exists(select 1 from `users_block` where `users_block`.`user_id` = `users`.`id` and `users_block`.`start_time` <= unix_timestamp() and `users_block`.`end_time` >= unix_timestamp() limit 1),1,0) AS `blocked` FROM `users` WHERE 1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `products_ibfk_1` (`category_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users_block`
--
ALTER TABLE `users_block`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users_purchase`
--
ALTER TABLE `users_purchase`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users_block`
--
ALTER TABLE `users_block`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users_purchase`
--
ALTER TABLE `users_purchase`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users_block`
--
ALTER TABLE `users_block`
  ADD CONSTRAINT `users_block_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
