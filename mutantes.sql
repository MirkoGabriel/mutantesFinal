-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 13-11-2020 a las 04:03:15
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mutantes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dna`
--

CREATE TABLE `dna` (
  `id` int(11) NOT NULL,
  `dna` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `dna`
--

INSERT INTO `dna` (`id`, `dna`, `tipo`) VALUES
(1, 'ATGCGA.CAGTGC.TTATGT.AGAAGG.CCCCTA.TCACTT', 'mutante'),
(2, 'ATGCGA.CAGTGC.TTATTT.AGACGG.GCGCTA.TCACTA', 'humano'),
(3, 'ACGTA.GTACG.TTAGC.ACGCT.TGCTC', 'mutante');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `dna`
--
ALTER TABLE `dna`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `dna`
--
ALTER TABLE `dna`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
