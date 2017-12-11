-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 10. Dez 2017 um 15:27
-- Server-Version: 10.1.26-MariaDB
-- PHP-Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `ese`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `address`
--

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL,
  `city` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `number` varchar(255) NOT NULL,
  `plz` int(11) NOT NULL,
  `street` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `address`
--

INSERT INTO `address` (`id`, `city`, `firstname`, `lastname`, `number`, `plz`, `street`) VALUES
(1, 'Basel', 'Daniela', 'Weiss', '15a', 4051, 'Leonhardstrasse'),
(2, 'Bern', 'Josef', 'Jäger', '7', 3007, 'Ziegelstrasse'),
(3, 'Zürich', 'Thomas', 'Schulz', '1a', 8005, 'Limmatstrasse'),
(4, 'Luzern', 'Svenja', 'Keller', '17k', 6014, 'Ritterstrasse'),
(5, 'Möriken', 'Ben', 'Ziegler', '10', 5103, 'Bruneggerstrasse'),
(6, 'Genf', 'Sören', 'Lang', '2', 1203, 'Rue de Saint-Jean'),
(7, 'Genf', 'Lennja', 'Brandt', '6d', 1201, 'Rue de Lyon'),
(8, 'Solothurn', 'Lucas', 'Herrmann', '7g', 4500, 'Brühlstrasse'),
(9, 'Biel', 'Noah', 'Koch', '50a', 2502, 'Gartenstrasse'),
(10, 'Zürich', 'Karolina', 'Fischer', '1', 8008, 'Krezstrasse'),
(11, 'Heimberg', 'Larissa', 'Schwarz', '80', 3627, 'Stationsweg'),
(12, 'Bern', 'Dario', 'Hofer', '3b', 3027, 'Holenackerstrasse'),
(15, 'Genf', 'Lennja', 'Brandt', '6d', 1201, 'Rue de Lyon'),
(16, 'Solothurn', 'Lucas', 'Herrmann', '7g', 4500, 'Brühlstrasse');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('admin@anitrans.ch', 'ROLE_ADMIN'),
('k.becker@gmail.com', 'ROLE_DRIVER'),
('kai.wagner@hotmail.com', 'ROLE_DRIVER');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `driver`
--

CREATE TABLE `driver` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `plz` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `driver`
--

INSERT INTO `driver` (`id`, `email`, `city`, `firstname`, `lastname`, `number`, `plz`, `street`) VALUES
(3, 'kai.wagner@hotmail.com', 'Bern', 'Kai', 'Wagner', '12a', 3001, 'Bahnstrasse', b'1111111111111111111111111111111'),
(6, 'k.becker@gmail.com', 'Basel', 'Krisina', 'Becker', '12b', 4001, 'Bachweg', b'1111111111111111111111111111111');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `trip`
--

CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL,
  `animal` varchar(100) NOT NULL,
  `animal_count` int(11) NOT NULL,
  `customer` varchar(100) NOT NULL,
  `date` datetime DEFAULT NULL,
  `trip_state` int(11) DEFAULT NULL,
  `driver_id` bigint(20) DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL,
  `address1_id` bigint(20) DEFAULT NULL,
  `address2_id` bigint(20) DEFAULT NULL,
  `estimate_hours` int(11) DEFAULT NULL,
  `estimate_minutes` int(11) DEFAULT NULL,
  `used_hours` bigint(20) DEFAULT NULL,
  `used_minutes` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `trip`
--

INSERT INTO `trip` (`id`, `animal`, `animal_count`, `customer`, `date`, `trip_state`, `driver_id`, `feedback`, `vehicle_id`, `address1_id`, `address2_id`, `estimate_hours`, `estimate_minutes`, `used_hours`, `used_minutes`) VALUES
(2, 'horse', 2, 'Josef Jäger', '2017-12-07 00:00:00', 2, 3, NULL, 2, 1, 2, NULL, NULL, NULL, NULL),
(3, 'cow', 3, 'Lara Keller', '2017-10-29 15:04:00', 4, 6, 'Test test test', NULL, 3, 4, NULL, NULL, NULL, NULL),
(4, 'monkey', 1, 'Sören Lang', '2017-10-22 10:30:00', 3, NULL, NULL, NULL, 5, 6, NULL, NULL, NULL, NULL),
(5, 'sheep', 5, 'Lennja Brandt', '2018-01-10 12:00:00', 0, NULL, NULL, NULL, 15, 16, NULL, NULL, NULL, NULL),
(7, 'deer', 2, 'Karolina Fischer', '2017-10-31 16:15:00', 3, NULL, NULL, NULL, 9, 10, NULL, NULL, NULL, NULL),
(8, 'Cow', 2, 'Dario Steiner', '2017-11-17 10:30:00', 2, 6, NULL, 1, 11, 12, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('admin@anitrans.ch', 'anitrans', 1),
('k.becker@gmail.com', 'kb1234', 1),
('kai.wagner@hotmail.com', '4321', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `vehicle`
--

CREATE TABLE `vehicle` (
  `id` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `used` int(11) NOT NULL,
  `length` int(11) NOT NULL,
  `width` int(11) NOT NULL,
  `image_data` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `vehicle`
--

INSERT INTO `vehicle` (`id`, `count`, `name`, `used`, `length`, `width`, `image_data`) VALUES
(1, 4, 'Big Transporter', 1, 400, 230, NULL);
INSERT INTO `vehicle` (`id`, `count`, `name`, `used`, `length`, `width`, `image_data`) VALUES
(2, 3, 'Medium Transporter', 1, 250, 200, NULL);
INSERT INTO `vehicle` (`id`, `count`, `name`, `used`, `length`, `width`, `image_data`) VALUES
(3, 5, 'Small Transporter', 0, 120, 100, NULL);
INSERT INTO `vehicle` (`id`, `count`, `name`, `used`, `length`, `width`, `image_data`) VALUES
(4, 7, 'Tiny Transporter', 0, 50, 50, NULL);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `authorities`
--
ALTER TABLE `authorities`
  ADD UNIQUE KEY `ix_auth_username` (`username`,`authority`);

--
-- Indizes für die Tabelle `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `trip`
--
ALTER TABLE `trip`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_njqi2m607gyhjxaa793nrmae0` (`address1_id`),
  ADD UNIQUE KEY `UK_l6a8xkkna9xrbqqqjokbc1csr` (`address2_id`),
  ADD KEY `FKpuhkx68hnwy4by2b0onybv5x1` (`driver_id`),
  ADD KEY `FKrji8htecrp06ao6s7nfubswnr` (`vehicle_id`);

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indizes für die Tabelle `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `address`
--
ALTER TABLE `address`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT für Tabelle `driver`
--
ALTER TABLE `driver`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT für Tabelle `trip`
--
ALTER TABLE `trip`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT für Tabelle `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Constraints der Tabelle `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `FK3xilwuneq2acvhigcx901usv9` FOREIGN KEY (`address2_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FKpfxe3jn8usxxmo5dkhcoai2ng` FOREIGN KEY (`address1_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FKpuhkx68hnwy4by2b0onybv5x1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`),
  ADD CONSTRAINT `FKrji8htecrp06ao6s7nfubswnr` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`),
  ADD CONSTRAINT `FKsb6ucd0fdxh1inone0o83l6nq` FOREIGN KEY (`id`) REFERENCES `address` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
