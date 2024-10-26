-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-03-2017 a las 05:21:48
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `contactos`
--
CREATE DATABASE IF NOT EXISTS `contactos` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `contactos`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `ProCuantosContactos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProCuantosContactos` (IN `Busqueda` VARCHAR(200))  READS SQL DATA
    DETERMINISTIC
BEGIN
SELECT
  COUNT(*) AS cuantos
FROM
  `Contacto`
WHERE
   nombre LIKE CONCAT('%', Busqueda, '%') OR apellido LIKE CONCAT('%', Busqueda, '%') OR apodo LIKE CONCAT('%', Busqueda, '%') 
  OR celular LIKE CONCAT('%', Busqueda, '%') OR celular2 LIKE CONCAT('%', Busqueda, '%') OR trabajo LIKE CONCAT('%', Busqueda, '%')
   OR casa LIKE CONCAT('%', Busqueda, '%') OR correo LIKE CONCAT('%', Busqueda, '%') OR correo2 LIKE CONCAT('%', Busqueda, '%') 
   OR fechaInsert LIKE CONCAT('%', Busqueda, '%') ;
END$$

DROP PROCEDURE IF EXISTS `ProEliminarContacto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProEliminarContacto` (IN `Id_Contacto` INT(11))  BEGIN
DELETE FROM `contacto` WHERE `idcontacto`=Id_Contacto;
END$$

DROP PROCEDURE IF EXISTS `ProInsertarContacto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProInsertarContacto` (IN `Nombre` VARCHAR(45), IN `Apellido` VARCHAR(45), IN `Apodo` VARCHAR(45), IN `Celular` INT(11), IN `Celular2` INT(11), IN `Trabajo` INT(11), IN `Casa` INT(11), IN `Correo` VARCHAR(45), IN `Correo2` VARCHAR(45))  BEGIN
INSERT INTO `contacto`( `nombre`, `apellido`, `apodo`, `celular`, `celular2`, `trabajo`, `casa`, `correo`, `correo2`, `fechaInsert`) 
values(Nombre,Apellido,Apodo,Celular,Celular2,Trabajo,Casa,Correo,Correo2,now());
END$$

DROP PROCEDURE IF EXISTS `ProLeeContacto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProLeeContacto` (IN `desde` BIGINT, IN `cuantos` BIGINT, IN `Busqueda` VARCHAR(200))  READS SQL DATA
BEGIN
SELECT
  RIGHT(`Contacto`.`idContacto`, 5) AS idContacto,
  `Contacto`.`nombre`,
  `Contacto`.`apellido`,
  `Contacto`.`apodo`,
  `Contacto`.`celular`,
  `Contacto`.`celular2`,
  `Contacto`.`trabajo`,
  `Contacto`.`casa`,
  `Contacto`.`correo`,
  `Contacto`.`correo2`,
  `Contacto`.`fechaInsert`
FROM
  `Contacto`
WHERE
  nombre LIKE CONCAT('%', Busqueda, '%') OR apellido LIKE CONCAT('%', Busqueda, '%') OR apodo LIKE CONCAT('%', Busqueda, '%') 
  OR celular LIKE CONCAT('%', Busqueda, '%') OR celular2 LIKE CONCAT('%', Busqueda, '%') OR trabajo LIKE CONCAT('%', Busqueda, '%')
   OR casa LIKE CONCAT('%', Busqueda, '%') OR correo LIKE CONCAT('%', Busqueda, '%') OR correo2 LIKE CONCAT('%', Busqueda, '%') 
   OR fechaInsert LIKE CONCAT('%', Busqueda, '%')
LIMIT desde, cuantos ;
END$$

DROP PROCEDURE IF EXISTS `ProLeerContactos`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProLeerContactos` (IN `id` BIGINT)  SELECT
  RIGHT(`Contacto`.`idcontacto`, 5) AS idcontacto,
  `Contacto`.`nombre`,
  `Contacto`.`apellido`,
  `Contacto`.`apodo`,
  `Contacto`.`celular`,
  `Contacto`.`celular2`,
  `Contacto`.`trabajo`,
  `Contacto`.`casa`,
  `Contacto`.`correo`,
  `Contacto`.`correo2`,
  `Contacto`.`fechaInsert`
FROM
  `Contacto`
WHERE
  idContacto = id$$

DROP PROCEDURE IF EXISTS `ProModificarContacto`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ProModificarContacto` (IN `Nombre` VARCHAR(45), IN `Apellido` VARCHAR(45), IN `Apodo` VARCHAR(45), IN `Celular` INT(11), IN `Celular2` INT(11), IN `Trabajo` INT(11), IN `Casa` INT(11), IN `Correo` VARCHAR(45), IN `Correo2` VARCHAR(45), IN `Id_Contacto` INT(11))  BEGIN
UPDATE `contacto` SET `nombre`=Nombre,`apellido`=Apellido,`apodo`=Apodo,`celular`=Celular,`celular2`=Celular2,
`trabajo`=Trabajo,`casa`=Casa,`correo`=Correo,`correo2`=Correo2,`fechaInsert`=now() WHERE `idcontacto`=Id_Contacto;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria_contacto`
--

DROP TABLE IF EXISTS `auditoria_contacto`;
CREATE TABLE `auditoria_contacto` (
  `id_auditoria` int(11) NOT NULL,
  `nombre_nuevo` varchar(45) DEFAULT NULL,
  `apellido_nuevo` varchar(45) DEFAULT NULL,
  `apodo_nuevo` varchar(45) DEFAULT NULL,
  `celular_nuevo` int(11) DEFAULT NULL,
  `celular2_nuevo` int(11) DEFAULT NULL,
  `trabajo_nuevo` int(11) DEFAULT NULL,
  `casa_nuevo` int(11) DEFAULT NULL,
  `correo_nuevo` varchar(45) DEFAULT NULL,
  `correo2_nuevo` varchar(45) DEFAULT NULL,
  `nombre_anterior` varchar(45) DEFAULT NULL,
  `apellido_anterior` varchar(45) DEFAULT NULL,
  `apodo_anterior` varchar(45) DEFAULT NULL,
  `celular_anterior` int(11) DEFAULT NULL,
  `celular2_anterior` int(11) DEFAULT NULL,
  `trabajo_anterior` int(11) DEFAULT NULL,
  `casa_anterior` int(11) DEFAULT NULL,
  `correo_anterior` varchar(45) DEFAULT NULL,
  `correo2_anterior` varchar(45) DEFAULT NULL,
  `modificado` timestamp NOT NULL,
  `proceso` varchar(45) DEFAULT NULL,
  `usuario_bd` varchar(45) DEFAULT NULL,
  `id_contacto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Estructura de tabla para la tabla `contacto`
--

DROP TABLE IF EXISTS `contacto`;
CREATE TABLE `contacto` (
  `idcontacto` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `apodo` varchar(45) DEFAULT NULL,
  `celular` int(11) DEFAULT NULL,
  `celular2` int(11) DEFAULT NULL,
  `trabajo` int(11) DEFAULT NULL,
  `casa` int(11) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `correo2` varchar(45) DEFAULT NULL,
  `fechaInsert` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Disparadores `contacto`
--
DROP TRIGGER IF EXISTS `elimina_auditori_contacto`;
DELIMITER $$
CREATE TRIGGER `elimina_auditori_contacto` AFTER DELETE ON `contacto` FOR EACH ROW insert into auditoria_contacto(nombre_anterior,apellido_anterior,apodo_anterior,celular_anterior,celular2_anterior,
 trabajo_anterior,casa_anterior,correo_anterior,correo2_anterior,modificado,proceso,usuario_bd,id_contacto)
values(old.nombre,old.apellido,old.apodo,old.celular,old.celular2,old.trabajo,old.casa,old.correo,old.correo2,
now(),"Eliminado",current_user(),old.idcontacto)
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `inserta_auditoria_contacto`;
DELIMITER $$
CREATE TRIGGER `inserta_auditoria_contacto` AFTER INSERT ON `contacto` FOR EACH ROW insert into auditoria_contacto(nombre_nuevo,apellido_nuevo,apodo_nuevo,celular_nuevo,celular2_nuevo,
 trabajo_nuevo,casa_nuevo,correo_nuevo,correo2_nuevo,modificado,proceso,usuario_bd,id_contacto)
values(new.nombre,new.apellido,new.apodo,new.celular,new.celular2,
new.trabajo,new.casa,new.correo,new.correo2,now(),"Insertado",current_user(),new.idcontacto)
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `modifica_auditoria_contacto`;
DELIMITER $$
CREATE TRIGGER `modifica_auditoria_contacto` BEFORE UPDATE ON `contacto` FOR EACH ROW insert into auditoria_contacto(nombre_nuevo,apellido_nuevo,apodo_nuevo,celular_nuevo,celular2_nuevo,
 trabajo_nuevo,casa_nuevo,correo_nuevo,correo2_nuevo,nombre_anterior,apellido_anterior,apodo_anterior,celular_anterior,celular2_anterior,
 trabajo_anterior,casa_anterior,correo_anterior,correo2_anterior,modificado,proceso,usuario_bd,id_contacto)
values(new.nombre,new.apellido,new.apodo,new.celular,new.celular2,
new.trabajo,new.casa,new.correo,new.correo2,
old.nombre,old.apellido,old.apodo,old.celular,old.celular2,old.trabajo,old.casa,old.correo,old.correo2,
now(),"Modificado",current_user(),new.idcontacto)
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `auditoria_contacto`
--
ALTER TABLE `auditoria_contacto`
  ADD PRIMARY KEY (`id_auditoria`);

--
-- Indices de la tabla `contacto`
--
ALTER TABLE `contacto`
  ADD PRIMARY KEY (`idcontacto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auditoria_contacto`
--
ALTER TABLE `auditoria_contacto`
  MODIFY `id_auditoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;
--
-- AUTO_INCREMENT de la tabla `contacto`
--
ALTER TABLE `contacto`
  MODIFY `idcontacto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
