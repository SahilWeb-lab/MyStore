-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2025 at 07:42 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ecom_micro`
--

-- --------------------------------------------------------

--
-- Table structure for table `account_status`
--

CREATE TABLE `account_status` (
  `id` bigint(20) NOT NULL,
  `account_status` tinyint(4) DEFAULT NULL,
  `account_verification_token` varchar(255) DEFAULT NULL,
  `password_reset_token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `account_status`
--

INSERT INTO `account_status` (`id`, `account_status`, `account_verification_token`, `password_reset_token`) VALUES
(1, 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `city`, `country`, `fullname`, `phone_number`, `state`, `street`, `zipcode`, `user_id`) VALUES
(1, 'Ranchi', 'India', 'Updated', '7677734448', 'Jharkhand', 'Birsa Chowk', '834001', 1),
(2, 'Ranchi', 'India', 'Mandal Sahil', '7677734448', 'Jharkhand', 'Birsa Chowk', '834001', 1),
(5, 'New York', 'America', 'Admin', '6206101764', 'USA', 'Blue Street', '433332', 5),
(6, 'Ranchi', 'India', 'Sahil Mandal', '6206101764', 'Jharkhand', 'Jap-2 Saket Nagar Tatisilwai', '835103', 5),
(7, 'Ranchi', 'India', 'Sahil Mandal', '6206101764', 'Jharkhand', 'Jap-2 Saket Nagar Tatisilwai', '835103', 6);

-- --------------------------------------------------------

--
-- Table structure for table `brands`
--

CREATE TABLE `brands` (
  `id` int(11) NOT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `logo_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brands`
--

INSERT INTO `brands` (`id`, `is_deleted`, `logo_url`, `name`) VALUES
(1, b'0', 'levis.png', 'Levi\'s'),
(2, b'0', 'lee.png', 'Lee'),
(3, b'0', 'diesel.png', 'Diesel'),
(4, b'0', 'wrangler.png', 'Wrangler'),
(5, b'0', 'nike.png', 'Nike'),
(6, b'0', 'adidas.png', 'Adidas'),
(7, b'0', 'puma.png', 'Puma'),
(8, b'0', 'reebok.png', 'Reebok'),
(9, b'0', 'newbalance.png', 'New Balance'),
(10, b'0', 'fossil.png', 'Fossil'),
(11, b'0', 'casio.png', 'Casio'),
(12, b'0', 'timex.png', 'Timex'),
(13, b'0', 'seiko.png', 'Seiko'),
(14, b'0', 'rolex.png', 'Rolex'),
(15, b'0', 'northface.png', 'North Face'),
(16, b'0', 'columbia.png', 'Columbia'),
(17, b'0', 'zara.png', 'Zara'),
(18, b'0', 'rayban.png', 'Ray-Ban'),
(19, b'0', 'oakley.png', 'Oakley'),
(20, b'0', 'gucci.png', 'Gucci'),
(21, b'0', 'polaroid.png', 'Polaroid'),
(22, b'0', 'wildcraft.png', 'Wildcraft'),
(23, b'0', 'skybags.png', 'Skybags'),
(24, b'0', 'tommyhilfiger.png', 'Tommy Hilfiger'),
(25, b'0', 'allensolly.png', 'Allen Solly'),
(26, b'0', 'hidesign.png', 'Hidesign'),
(27, b'0', 'newera.png', 'New Era'),
(28, b'0', 'burberry.png', 'Burberry'),
(29, b'0', 'timberland.png', 'Timberland'),
(30, b'0', 'clarks.png', 'Clarks'),
(31, b'0', 'bata.png', 'Bata'),
(32, b'0', 'woodland.png', 'Woodland'),
(33, b'0', 'arrow.png', 'Arrow'),
(34, b'0', 'gap.png', 'Gap'),
(35, b'0', 'vanheusen.png', 'Van Heusen'),
(36, b'0', 'louisphilippe.png', 'Louis Philippe'),
(37, b'0', 'peterengland.png', 'Peter England'),
(38, b'0', 'forever21.png', 'Forever 21'),
(39, b'0', 'mango.png', 'Mango'),
(40, b'0', 'shein.png', 'Shein'),
(41, b'0', 'uniqlo.png', 'Uniqlo'),
(42, b'0', 'pandora.png', 'Pandora'),
(43, b'0', 'swarovski.png', 'Swarovski'),
(44, b'0', 'tiffany.png', 'Tiffany & Co.'),
(45, b'0', 'claires.png', 'Claire\'s'),
(46, b'0', 'tanishq.png', 'Tanishq'),
(47, b'0', 'miabytanishq.png', 'Mia by Tanishq');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `total_amount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `user_id`, `total_amount`) VALUES
(2, 2, 0),
(3, 1, 0),
(4, 5, 48889),
(5, 6, 0);

-- --------------------------------------------------------

--
-- Table structure for table `cart_item`
--

CREATE TABLE `cart_item` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `cart_id` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `total_price` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cart_item`
--

INSERT INTO `cart_item` (`id`, `quantity`, `cart_id`, `product_id`, `total_price`) VALUES
(13, 10, 4, 5, 29990),
(14, 2, 4, 1, 13800),
(15, 1, 4, 3, 5099);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `image_url`, `is_deleted`, `name`) VALUES
(1, 'sneakers.png', b'0', 'Sneakers'),
(2, 'watches.png', b'0', 'Watches'),
(3, 'jackets.png', b'0', 'Jackets'),
(4, 'sunglasses.png', b'0', 'Sunglasses'),
(5, 'backpacks.png', b'0', 'Backpacks'),
(6, 'belts.png', b'0', 'Belts'),
(7, 'hats.png', b'0', 'Hats'),
(8, 'scarves.png', b'0', 'Scarves'),
(9, 'gloves.png', b'0', 'Gloves'),
(10, 'shoes.png', b'0', 'Shoes'),
(11, 'pants.png', b'0', 'Pants'),
(12, 'shirts.png', b'0', 'Shirts'),
(13, 'dresses.png', b'0', 'Dresses'),
(14, 'sweaters.png', b'0', 'Sweaters'),
(15, 'bracelets.png', b'0', 'Bracelets'),
(16, 'earrings.png', b'0', 'Earrings'),
(17, 'necklaces.png', b'0', 'Necklaces'),
(18, 'coats.png', b'0', 'Coats'),
(19, 'jeans.png', b'0', 'Jeans'),
(20, 'wallets.png', b'0', 'Wallets'),
(21, 'test_category.png', b'0', 'Test Category');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `order_status` tinyint(4) DEFAULT NULL,
  `payment_method` tinyint(4) DEFAULT NULL,
  `payment_status` tinyint(4) DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `order_date`, `order_status`, `payment_method`, `payment_status`, `total_amount`, `address_id`, `user_id`) VALUES
(1, '2025-07-07 23:35:25.000000', 2, 0, 1, 5099, 2, 1),
(2, '2025-07-07 23:44:36.000000', 5, 0, 1, 14995, 2, 1),
(3, '2025-07-07 23:53:54.000000', 0, 0, 1, 14995, 2, 1),
(4, '2025-07-17 21:49:08.000000', 0, 0, 1, 6900, 7, 6);

-- --------------------------------------------------------

--
-- Table structure for table `order_item`
--

CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_item`
--

INSERT INTO `order_item` (`id`, `product_id`, `quantity`, `total_price`, `unit_price`, `order_id`) VALUES
(1, 3, 1, 5099, 5099, 1),
(2, 5, 5, 14995, 2999, 2),
(3, 5, 5, 14995, 2999, 3),
(4, 1, 1, 6900, 6900, 4);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `badges` varchar(255) DEFAULT NULL,
  `colors` varchar(255) DEFAULT NULL,
  `discounted_price` float DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `original_price` float DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `sizes` varchar(255) DEFAULT NULL,
  `stock_status` int(11) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `badges`, `colors`, `discounted_price`, `image_url`, `is_deleted`, `name`, `original_price`, `rating`, `sizes`, `stock_status`, `brand_id`, `category_id`) VALUES
(1, 'Hot', 'Black, White, Red', 6900, 'sneakers.png', b'0', 'Nike Air Zoom', 7000, 4, '7, 8, 9, 10', 49, 5, 1),
(2, 'Top Rated', 'Silver, Black', 9999, 'watches.png', b'0', 'Fossil Gen 5', 11000, 4, 'One Size', 25, 10, 2),
(3, 'Best Seller', 'Blue, Grey', 5099, 'jackets.png', b'0', 'Columbia Wind Jacket', 6000, 4, 'M, L, XL', 39, 16, 3),
(4, 'Premium', 'Gold, Black', 8699, 'sunglasses.png', b'0', 'Ray-Ban Aviator', 9500, 4, 'One Size', 70, 18, 4),
(5, 'Editor’s Pick', 'Blue, Black', 2999, 'jeans.png', b'0', 'Levi\'s Skinny Jeans', 3500, 4, '30, 32, 34, 36', 50, 1, 19),
(6, 'Trending', 'Black, Red', 1999, 'backpacks.png', b'0', 'Adidas Running Backpack', 2200, 4, 'Medium', 100, 6, 5),
(7, 'Luxury', 'Gold', 7899, 'earrings.png', b'0', 'Tanishq Gold Earrings', 8500, 4, 'One Size', 15, 46, 16),
(8, 'Budget Pick Updated', 'brown, black Updated', 119, 'wallet1_updated.png', b'0', 'Leather Wallet Updated', 99, 3, 'M', 200, 1, 20),
(9, 'Trending', 'Black, Red', 1999, 'backpacks.png', b'0', 'Test 1', 2200, 4, 'Medium', 100, 6, 5),
(10, 'Luxury', 'Gold', 7899, 'earrings.png', b'0', 'Test 2', 8500, 4, 'One Size', 15, 46, 16),
(11, 'Best Seller', 'red, green, blue', 2599, 'test.png', b'0', 'Latest Product', 3000, 4, 'S, M, L, XL, XXL', 100, 1, 3),
(12, 'Limited Edition', 'black, white', 1699, 'my_product.png', b'0', 'My Product', 1999, 4, 'S, M, L', 100, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `sub_category`
--

CREATE TABLE `sub_category` (
  `id` int(11) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `account_status_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `password`, `profile`, `role_id`, `account_status_id`) VALUES
(1, 'mandalsahil253@gmail', 'Mandal Sahil', '$2a$10$xD5NpCt4Wnx.lOiHbIWK5.uvaDYQ9iPDrfiO3CVycDhMQ04G.MfWm', NULL, 1, NULL),
(2, 'rohan@gmail', 'Rohan Raj', '$2a$10$tRw06/2HGH9IbwVWT6dOuOKJTihw.zGWOpdAeCX/Q3QlAzu8FzyEq', NULL, 2, NULL),
(5, 'test@gmail', 'Test User', '$2a$10$7rXF1/398bVbGE1T0v8p6eY9sq6dmIdcjTgDZFsSSnuqnPA8SO5xS', NULL, 2, NULL),
(6, 'admin@gmail', 'Admin', '$2a$10$Sgn1xZRGCBBC.0eII425x.AnGOAzPjRgL.PyEZA.gx9pPqQtOxh0y', NULL, 2, NULL),
(34, 'mrsahilmandal@gmail.com', 'Sahil Mandal', '$2a$10$8DkiLpYnSrG/gUE71x415.19iiGblStEWxvOTyEXtjfEwHn.BLyzq', NULL, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist`
--

CREATE TABLE `wishlist` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wishlist`
--

INSERT INTO `wishlist` (`id`, `user_id`) VALUES
(3, 1),
(2, 2),
(4, 5),
(5, 6);

-- --------------------------------------------------------

--
-- Table structure for table `wishlist_products`
--

CREATE TABLE `wishlist_products` (
  `wishlist_id` bigint(20) NOT NULL,
  `products_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `wishlist_products`
--

INSERT INTO `wishlist_products` (`wishlist_id`, `products_id`) VALUES
(3, 1),
(3, 2),
(3, 3),
(2, 1),
(4, 1),
(4, 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account_status`
--
ALTER TABLE `account_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`);

--
-- Indexes for table `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK9emlp6m95v5er2bcqkjsw48he` (`user_id`);

--
-- Indexes for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  ADD KEY `FKqkqmvkmbtiaqn2nfqf25ymfs2` (`product_id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf5464gxwc32ongdvka2rtvw96` (`address_id`),
  ADD KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`);

--
-- Indexes for table `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKa3a4mpsfdf4d2y6r8ra3sc8mv` (`brand_id`),
  ADD KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sub_category`
--
ALTER TABLE `sub_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK81h8gsm5ydwixhpejbv7rdttx` (`account_status_id`),
  ADD KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`);

--
-- Indexes for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKd4r80jm8s41fgoa0xv9yy5lo8` (`user_id`);

--
-- Indexes for table `wishlist_products`
--
ALTER TABLE `wishlist_products`
  ADD KEY `FKepf55008bt0th0tswldm6vkaj` (`products_id`),
  ADD KEY `FKhlq0ylq5sxd70s0pembuumc1d` (`wishlist_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account_status`
--
ALTER TABLE `account_status`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `brands`
--
ALTER TABLE `brands`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `cart_item`
--
ALTER TABLE `cart_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `sub_category`
--
ALTER TABLE `sub_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `wishlist`
--
ALTER TABLE `wishlist`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `cart_item`
--
ALTER TABLE `cart_item`
  ADD CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  ADD CONSTRAINT `FKqkqmvkmbtiaqn2nfqf25ymfs2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKf5464gxwc32ongdvka2rtvw96` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`);

--
-- Constraints for table `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `FKa3a4mpsfdf4d2y6r8ra3sc8mv` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`),
  ADD CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKsdwiqho2tvg6if7lrb65w4tud` FOREIGN KEY (`account_status_id`) REFERENCES `account_status` (`id`);

--
-- Constraints for table `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `FKd4r80jm8s41fgoa0xv9yy5lo8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `wishlist_products`
--
ALTER TABLE `wishlist_products`
  ADD CONSTRAINT `FKepf55008bt0th0tswldm6vkaj` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKhlq0ylq5sxd70s0pembuumc1d` FOREIGN KEY (`wishlist_id`) REFERENCES `wishlist` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
