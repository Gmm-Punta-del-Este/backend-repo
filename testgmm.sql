-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-05-2020 a las 05:04:34
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `testgmm`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apartamento`
--

CREATE TABLE `apartamento` (
  `nro_apto` int(11) NOT NULL,
  `libre` bit(1) DEFAULT NULL,
  `notas` varchar(255) DEFAULT NULL,
  `documento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apartamento_actividad`
--

CREATE TABLE `apartamento_actividad` (
  `id` int(11) NOT NULL,
  `acompaniante` varchar(255) DEFAULT NULL,
  `documento_entra` varchar(255) DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `nombre_entra` varchar(255) DEFAULT NULL,
  `documento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consumo`
--

CREATE TABLE `consumo` (
  `id` int(11) NOT NULL,
  `consumo_final` datetime DEFAULT NULL,
  `consumo_inicial` datetime NOT NULL,
  `nro_apto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contacto_inquilino`
--

CREATE TABLE `contacto_inquilino` (
  `telefono` varchar(255) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `documento` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contacto_propietario`
--

CREATE TABLE `contacto_propietario` (
  `telefono` varchar(255) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `documento` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contacto_usuario`
--

CREATE TABLE `contacto_usuario` (
  `telefono` varchar(255) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `documento` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inhouse_entrada`
--

CREATE TABLE `inhouse_entrada` (
  `id` int(11) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `timestamp` datetime NOT NULL,
  `tipo_persona` int(11) NOT NULL,
  `nro_apto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inhouse_llegada`
--

CREATE TABLE `inhouse_llegada` (
  `id` int(11) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `timestamp` datetime NOT NULL,
  `tipo_persona` int(11) NOT NULL,
  `nro_apto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inhouse_salida`
--

CREATE TABLE `inhouse_salida` (
  `id` int(11) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `timestamp` datetime NOT NULL,
  `tipo_persona` int(11) NOT NULL,
  `nro_apto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nota`
--

CREATE TABLE `nota` (
  `id` int(11) NOT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `documento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona_inquilino`
--

CREATE TABLE `persona_inquilino` (
  `documento` varchar(255) NOT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `contacto` tinyblob DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona_propietario`
--

CREATE TABLE `persona_propietario` (
  `documento` varchar(255) NOT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `contacto` tinyblob DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona_usuario`
--

CREATE TABLE `persona_usuario` (
  `documento` varchar(255) NOT NULL,
  `apellido1` varchar(255) DEFAULT NULL,
  `apellido2` varchar(255) DEFAULT NULL,
  `contacto` tinyblob DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tipo_usuario` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio_inquilino`
--

CREATE TABLE `servicio_inquilino` (
  `id` int(11) NOT NULL,
  `costo` float DEFAULT NULL,
  `hecho` datetime NOT NULL,
  `interno` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `notas` varchar(255) DEFAULT NULL,
  `solicitado` datetime NOT NULL,
  `documento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio_propietario`
--

CREATE TABLE `servicio_propietario` (
  `id` int(11) NOT NULL,
  `costo` float DEFAULT NULL,
  `hecho` datetime NOT NULL,
  `interno` bit(1) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `notas` varchar(255) DEFAULT NULL,
  `solicitado` datetime NOT NULL,
  `documento` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apartamento`
--
ALTER TABLE `apartamento`
  ADD PRIMARY KEY (`nro_apto`),
  ADD KEY `FKo5we4x9ysm65d873ac3g5p8sm` (`documento`);

--
-- Indices de la tabla `apartamento_actividad`
--
ALTER TABLE `apartamento_actividad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKjnp1icq9fdk62yp3jrfmjrb0h` (`documento`);

--
-- Indices de la tabla `consumo`
--
ALTER TABLE `consumo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpfp0broxcsuvp2di0k43rax6x` (`nro_apto`);

--
-- Indices de la tabla `contacto_inquilino`
--
ALTER TABLE `contacto_inquilino`
  ADD PRIMARY KEY (`documento`,`telefono`);

--
-- Indices de la tabla `contacto_propietario`
--
ALTER TABLE `contacto_propietario`
  ADD PRIMARY KEY (`documento`,`telefono`);

--
-- Indices de la tabla `contacto_usuario`
--
ALTER TABLE `contacto_usuario`
  ADD PRIMARY KEY (`documento`,`telefono`);

--
-- Indices de la tabla `inhouse_entrada`
--
ALTER TABLE `inhouse_entrada`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfas7wc69gcxeoe13s5t468qsl` (`nro_apto`);

--
-- Indices de la tabla `inhouse_llegada`
--
ALTER TABLE `inhouse_llegada`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKodctgem25ufxd08nk415diq3l` (`nro_apto`);

--
-- Indices de la tabla `inhouse_salida`
--
ALTER TABLE `inhouse_salida`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqa6lrlxg5vg5ngv3t0hi1x362` (`nro_apto`);

--
-- Indices de la tabla `nota`
--
ALTER TABLE `nota`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKer3o6jdxj9ejf8hbnjigxghv0` (`documento`);

--
-- Indices de la tabla `persona_inquilino`
--
ALTER TABLE `persona_inquilino`
  ADD PRIMARY KEY (`documento`);

--
-- Indices de la tabla `persona_propietario`
--
ALTER TABLE `persona_propietario`
  ADD PRIMARY KEY (`documento`);

--
-- Indices de la tabla `persona_usuario`
--
ALTER TABLE `persona_usuario`
  ADD PRIMARY KEY (`documento`);

--
-- Indices de la tabla `servicio_inquilino`
--
ALTER TABLE `servicio_inquilino`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt6eahgj94wq4x9e4bw91g6ofl` (`documento`);

--
-- Indices de la tabla `servicio_propietario`
--
ALTER TABLE `servicio_propietario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKl60of5kvxqxo6ejl5vmqu8n4b` (`documento`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apartamento_actividad`
--
ALTER TABLE `apartamento_actividad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `consumo`
--
ALTER TABLE `consumo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inhouse_entrada`
--
ALTER TABLE `inhouse_entrada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inhouse_llegada`
--
ALTER TABLE `inhouse_llegada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `inhouse_salida`
--
ALTER TABLE `inhouse_salida`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `nota`
--
ALTER TABLE `nota`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `servicio_inquilino`
--
ALTER TABLE `servicio_inquilino`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `servicio_propietario`
--
ALTER TABLE `servicio_propietario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `apartamento`
--
ALTER TABLE `apartamento`
  ADD CONSTRAINT `FKo5we4x9ysm65d873ac3g5p8sm` FOREIGN KEY (`documento`) REFERENCES `persona_propietario` (`documento`);

--
-- Filtros para la tabla `apartamento_actividad`
--
ALTER TABLE `apartamento_actividad`
  ADD CONSTRAINT `FKjnp1icq9fdk62yp3jrfmjrb0h` FOREIGN KEY (`documento`) REFERENCES `persona_usuario` (`documento`);

--
-- Filtros para la tabla `consumo`
--
ALTER TABLE `consumo`
  ADD CONSTRAINT `FKpfp0broxcsuvp2di0k43rax6x` FOREIGN KEY (`nro_apto`) REFERENCES `apartamento` (`nro_apto`);

--
-- Filtros para la tabla `contacto_inquilino`
--
ALTER TABLE `contacto_inquilino`
  ADD CONSTRAINT `FK2qllp2a08n5felh6ndmksyktq` FOREIGN KEY (`documento`) REFERENCES `persona_inquilino` (`documento`);

--
-- Filtros para la tabla `contacto_propietario`
--
ALTER TABLE `contacto_propietario`
  ADD CONSTRAINT `FKn0qodle7na0q5hq115k6m6qxh` FOREIGN KEY (`documento`) REFERENCES `persona_propietario` (`documento`);

--
-- Filtros para la tabla `contacto_usuario`
--
ALTER TABLE `contacto_usuario`
  ADD CONSTRAINT `FKd7c9qdhxpjg0rk6gl1gj89enp` FOREIGN KEY (`documento`) REFERENCES `persona_usuario` (`documento`);

--
-- Filtros para la tabla `inhouse_entrada`
--
ALTER TABLE `inhouse_entrada`
  ADD CONSTRAINT `FKfas7wc69gcxeoe13s5t468qsl` FOREIGN KEY (`nro_apto`) REFERENCES `apartamento` (`nro_apto`);

--
-- Filtros para la tabla `inhouse_llegada`
--
ALTER TABLE `inhouse_llegada`
  ADD CONSTRAINT `FKodctgem25ufxd08nk415diq3l` FOREIGN KEY (`nro_apto`) REFERENCES `apartamento` (`nro_apto`);

--
-- Filtros para la tabla `inhouse_salida`
--
ALTER TABLE `inhouse_salida`
  ADD CONSTRAINT `FKqa6lrlxg5vg5ngv3t0hi1x362` FOREIGN KEY (`nro_apto`) REFERENCES `apartamento` (`nro_apto`);

--
-- Filtros para la tabla `nota`
--
ALTER TABLE `nota`
  ADD CONSTRAINT `FKer3o6jdxj9ejf8hbnjigxghv0` FOREIGN KEY (`documento`) REFERENCES `persona_usuario` (`documento`);

--
-- Filtros para la tabla `servicio_inquilino`
--
ALTER TABLE `servicio_inquilino`
  ADD CONSTRAINT `FKt6eahgj94wq4x9e4bw91g6ofl` FOREIGN KEY (`documento`) REFERENCES `persona_inquilino` (`documento`);

--
-- Filtros para la tabla `servicio_propietario`
--
ALTER TABLE `servicio_propietario`
  ADD CONSTRAINT `FKl60of5kvxqxo6ejl5vmqu8n4b` FOREIGN KEY (`documento`) REFERENCES `persona_propietario` (`documento`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
