DROP TABLE IF EXISTS `cart_view`;
DROP TABLE IF EXISTS `products_view`;
DROP TABLE IF EXISTS `users_view`;
DROP TABLE IF EXISTS `cart`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `users_block`;
DROP TABLE IF EXISTS `users_purchase`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `images`;

CREATE TABLE `cart` (
  `id` int(13) NOT NULL,
  `user_id` int(13) NOT NULL,
  `product_id` int(13) NOT NULL,
  `amount` int(13) NOT NULL,
  `price` int(13) NOT NULL,
  `date` timestamp NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `cart_view` (
`id` int(13)
,`user_id` int(13)
,`product_id` int(13)
,`amount` int(13)
,`price` decimal(22,2)
,`date` timestamp
,`status` int(11)
);

CREATE TABLE `categories` (
  `id` int(13) NOT NULL,
  `identifier` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `locale_ua` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL,
  `locale_en` varchar(56) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `images` (
  `id` int(13) NOT NULL,
  `uuid` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mime` varchar(48) COLLATE utf8mb4_unicode_ci NOT NULL,
  `data` mediumblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

CREATE TABLE `users_block` (
  `id` int(13) NOT NULL,
  `user_id` int(13) NOT NULL,
  `reason` varchar(300) COLLATE utf8mb4_unicode_ci NOT NULL,
  `start_time` int(16) NOT NULL,
  `end_time` int(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `users_purchase` (
  `id` int(13) NOT NULL,
  `user_id` int(13) NOT NULL,
  `product_id` int(13) NOT NULL,
  `amount` int(13) NOT NULL,
  `price` int(13) NOT NULL,
  `date` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

DROP TABLE IF EXISTS `cart_view`;

CREATE VIEW `cart_view`  AS SELECT `cart`.`id` AS `id`, `cart`.`user_id` AS `user_id`, `cart`.`product_id` AS `product_id`, `cart`.`amount` AS `amount`, CASE WHEN `cart`.`status` <> 2 THEN select `cart`.`amount` * `products`.`price` from `products` where `products`.`id` = `cart`.`product_id` ELSE `cart`.`price` END AS `price`, `cart`.`date` AS `date`, `cart`.`status` AS `status` FROM `cart` WHERE 1 ;

DROP TABLE IF EXISTS `products_view`;

CREATE VIEW `products_view`  AS SELECT `products`.`id` AS `id`, `products`.`shortname` AS `shortname`, `products`.`fullname` AS `fullname`, `products`.`description` AS `description`, `products`.`amount` AS `amount`, `products`.`price` AS `price`, `products`.`color` AS `color`, `products`.`size_unit` AS `size_unit`, `products`.`size_value` AS `size_value`, `products`.`category_id` AS `category_id`, (select sum(`cart`.`amount`) from `cart` where `cart`.`product_id` = `products`.`id` and `cart`.`status` = 2) AS `bought_counter`, `products`.`preview_image` AS `preview_image`, `products`.`start_date` AS `start_date` FROM `products` WHERE 1 ;

DROP TABLE IF EXISTS `users_view`;

CREATE VIEW `users_view`  AS SELECT `users`.`id` AS `id`, `users`.`name` AS `name`, `users`.`surname` AS `surname`, `users`.`email` AS `email`, `users`.`login` AS `login`, `users`.`password` AS `password`, `users`.`birth_date` AS `birth_date`, `users`.`balance` AS `balance`, `users`.`avatar` AS `avatar`, `users`.`status` AS `status`, CASE WHEN exists(select 1 from `users_block` where `users_block`.`user_id` = `users`.`id` and `users_block`.`start_time` <= unix_timestamp() and `users_block`.`end_time` >= unix_timestamp() limit 1) THEN 1 ELSE 0 END AS `blocked` FROM `users` WHERE 1 ;

ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `images`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users_block`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `users_purchase`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `cart`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

ALTER TABLE `categories`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

ALTER TABLE `images`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

ALTER TABLE `products`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users_block`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users_purchase`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT;

ALTER TABLE `cart`
  ADD CONSTRAINT CART_IBFK_1 FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
ALTER TABLE `cart`
    ADD CONSTRAINT CART_IBFK_2 FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE;

ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE CASCADE;

ALTER TABLE `users_block`
  ADD CONSTRAINT `users_block_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;
