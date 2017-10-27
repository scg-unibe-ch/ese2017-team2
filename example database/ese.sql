-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 27. Okt 2017 um 21:47
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
-- Tabellenstruktur für Tabelle `driver`
--

CREATE TABLE `driver` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `driver`
--

INSERT INTO `driver` (`id`, `name`, `password`) VALUES
(1, 'Simon Müller', '1234'),
(2, 'Sven Weber', 'abcd'),
(3, 'Kai Wagner', '4321'),
(4, 'Hannes Kraus', 'dcba'),
(5, 'Anja Müller', 'anja12'),
(6, 'Kristina Becker', 'kb1234'),
(7, 'Dana Baumann', 'db12');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `trip`
--

CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL,
  `animal` varchar(100) NOT NULL,
  `animal_count` int(11) NOT NULL,
  `city_1` varchar(100) NOT NULL,
  `city_2` varchar(100) NOT NULL,
  `customer` varchar(100) NOT NULL,
  `date` datetime DEFAULT NULL,
  `name_1` varchar(100) NOT NULL,
  `name_2` varchar(100) NOT NULL,
  `plz_1` int(11) NOT NULL,
  `plz_2` int(11) NOT NULL,
  `street_1` varchar(100) NOT NULL,
  `street_2` varchar(100) NOT NULL,
  `trip_state` int(11) DEFAULT NULL,
  `driver_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `trip`
--

INSERT INTO `trip` (`id`, `animal`, `animal_count`, `city_1`, `city_2`, `customer`, `date`, `name_1`, `name_2`, `plz_1`, `plz_2`, `street_1`, `street_2`, `trip_state`, `driver_id`) VALUES
(2, 'Pferd', 2, 'Basel', 'Bern', 'Josef Jäger', NULL, 'Daniela Weiss', 'Josef Jäger', 4051, 3007, 'Leonhardstrasse 15', 'Zieglerstrasse 7', 0, NULL),
(3, 'Kuh', 3, 'Zürich', 'Luzern', 'Lara Keller', NULL, 'Thomas Schulz', 'Svenja Keller', 8005, 6014, 'Limmatstrasse 152', 'Ritterstrasse 3', 1, 6),
(4, 'Affe', 1, 'Möriken', 'Genf', 'Sören Lang', '2017-10-22 10:30:00', 'Ben Ziegler', 'Sören Lang', 5103, 1203, 'Bruneggerstrasse 10', 'Rue de Saint-Jean 3', 3, NULL),
(5, 'Schaf', 5, 'Genf', 'Solothurn', 'Lennja Brandt', '2018-01-10 12:00:00', 'Lennja Brandt', 'Lucas Herrmann', 1201, 4500, 'Rue de Lyon 4', 'Brühlstrasse 3', 1, 4),
(6, 'Ziege', 10, 'Basel', 'Biel', 'Karl Bergmann', '2017-11-30 10:00:00', 'Nele Ludwig', 'Karl Bergmann', 4057, 2502, 'Feldbergstrasse 2', 'Neumarktstrasse 32', 0, NULL),
(7, 'Reh', 2, 'Biel', 'Zürich', 'Karolina Fischer', '2017-10-27 16:15:00', 'Noah Koch', 'Karolina Fsicher', 2502, 8008, 'Gartenstrasse 12', 'Krezstrasse 22', 3, 7);

--
-- Indizes der exportierten Tabellen
--

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
  ADD KEY `FKpuhkx68hnwy4by2b0onybv5x1` (`driver_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `driver`
--
ALTER TABLE `driver`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `trip`
--
ALTER TABLE `trip`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `FKpuhkx68hnwy4by2b0onybv5x1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
