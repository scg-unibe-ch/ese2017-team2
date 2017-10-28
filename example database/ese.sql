-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 28. Okt 2017 um 22:28
-- Server-Version: 10.1.26-MariaDB
-- PHP-Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Datenbank: `ese`
--

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
('admin', 'ROLE_ADMIN'),
('Anja Müller', 'ROLE_DRIVER'),
('Dana Baumann', 'ROLE_DRIVER'),
('Hannes Kraus', 'ROLE_DRIVER'),
('Kai Wagner', 'ROLE_DRIVER'),
('Kristina Becker', 'ROLE_DRIVER'),
('Simon Müller', 'ROLE_DRIVER'),
('Sven Weber', 'ROLE_DRIVER');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `driver`
--

CREATE TABLE `driver` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `driver`
--

INSERT INTO `driver` (`id`, `name`) VALUES
(1, 'Simon Müller'),
(2, 'Sven Weber'),
(3, 'Kai Wagner'),
(4, 'Hannes Kraus'),
(5, 'Anja Müller'),
(6, 'Kristina Becker'),
(7, 'Dana Baumann');

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
(2, 'horse', 2, 'Basel', 'Bern', 'Josef Jäger', NULL, 'Daniela Weiss', 'Josef Jäger', 4051, 3007, 'Leonhardstrasse 15', 'Zieglerstrasse 7', 0, NULL),
(3, 'cow', 3, 'Zürich', 'Luzern', 'Lara Keller', NULL, 'Thomas Schulz', 'Svenja Keller', 8005, 6014, 'Limmatstrasse 152', 'Ritterstrasse 3', 1, 6),
(4, 'monkey', 1, 'Möriken', 'Genf', 'Sören Lang', '2017-10-22 10:30:00', 'Ben Ziegler', 'Sören Lang', 5103, 1203, 'Bruneggerstrasse 10', 'Rue de Saint-Jean 3', 3, NULL),
(5, 'sheep', 5, 'Genf', 'Solothurn', 'Lennja Brandt', '2018-01-10 12:00:00', 'Lennja Brandt', 'Lucas Herrmann', 1201, 4500, 'Rue de Lyon 4', 'Brühlstrasse 3', 1, 4),
(6, 'goat', 10, 'Basel', 'Biel', 'Karl Bergmann', '2017-11-30 10:00:00', 'Nele Ludwig', 'Karl Bergmann', 4057, 2502, 'Feldbergstrasse 2', 'Neumarktstrasse 32', 0, NULL),
(7, 'deer', 2, 'Biel', 'Zürich', 'Karolina Fischer', '2017-10-27 16:15:00', 'Noah Koch', 'Karolina Fsicher', 2502, 8008, 'Gartenstrasse 12', 'Krezstrasse 22', 3, 7);

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
('admin', 'anitrans', 1),
('Anja Müller', 'anja12', 1),
('Dana Baumann', 'db12', 1),
('Hannes Kraus', 'dcba', 1),
('Kai Wagner', '4321', 1),
('Kristina Becker', 'kb1234', 1),
('Simon Müller', '1234', 1),
('Sven Weber', 'abcd', 1);

--
-- Indizes der exportierten Tabellen
--

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
  ADD KEY `FKpuhkx68hnwy4by2b0onybv5x1` (`driver_id`);

--
-- Indizes für die Tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

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
-- Constraints der Tabelle `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

--
-- Constraints der Tabelle `trip`
--
ALTER TABLE `trip`
  ADD CONSTRAINT `FKpuhkx68hnwy4by2b0onybv5x1` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`id`);
COMMIT;
