-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 15-Jun-2018 às 12:32
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `grrecurso`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `id_categoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `cd_categoria` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco`
--

CREATE TABLE IF NOT EXISTS `endereco` (
  `id_endereco` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(200) NOT NULL,
  `cep` varchar(30) DEFAULT NULL,
  `cidade` varchar(80) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `rua` varchar(200) NOT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`),
  KEY `FKekdpb8k6gmp3lllla9d1qgmxk` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `endereco_aud`
--

CREATE TABLE IF NOT EXISTS `endereco_aud` (
  `id_endereco` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `bairro` varchar(200) DEFAULT NULL,
  `cep` varchar(30) DEFAULT NULL,
  `cidade` varchar(80) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  `numero` varchar(10) DEFAULT NULL,
  `rua` varchar(200) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`,`REV`),
  KEY `FKscj98nilkbrwixypnsfep1tgv` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `grrecursorevisionentity`
--

CREATE TABLE IF NOT EXISTS `grrecursorevisionentity` (
  `id` int(11) NOT NULL,
  `timestamp` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `grrecursorevisionentity`
--

INSERT INTO `grrecursorevisionentity` (`id`, `timestamp`, `username`) VALUES
(1, 1467804242243, 'developer@test.com'),
(2, 1467855538445, 'developer@test.com'),
(3, 1467856467984, 'developer@test.com'),
(4, 1467856534840, 'developer@test.com'),
(5, 1467856853512, 'developer@test.com'),
(6, 1468186937032, 'developer@test.com'),
(7, 1468187710508, 'developer@test.com'),
(8, 1468188167108, 'developer@test.com'),
(9, 1468188462693, 'developer@test.com'),
(10, 1468188470926, 'developer@test.com'),
(11, 1468188476561, 'developer@test.com'),
(12, 1468189672865, 'developer@test.com'),
(13, 1468189701500, 'developer@test.com'),
(14, 1468189731258, 'developer@test.com'),
(15, 1468189738938, 'developer@test.com'),
(16, 1469411843496, 'developer@test.com'),
(17, 1469578090514, 'developer@test.com'),
(18, 1469578100202, 'developer@test.com'),
(19, 1469588836492, 'developer@test.com'),
(20, 1469588872445, 'developer@test.com'),
(21, 1469588976353, 'developer@test.com'),
(22, 1469889860342, 'developer@test.com'),
(23, 1469890122096, 'developer@test.com'),
(24, 1469890428262, 'developer@test.com'),
(25, 1469890482694, 'developer@test.com'),
(26, 1489924833566, 'api@admin.com'),
(27, 1489925370187, 'api@admin.com'),
(28, 1491569990781, 'developer@test.com'),
(29, 1491570733656, 'jonas@teste.com'),
(30, 1491573120035, 'jonas@teste.com'),
(31, 1491681436927, 'jonas@teste.com'),
(32, 1491681517990, 'jonas@teste.com'),
(33, 1491682113250, 'jonas@teste.com'),
(34, 1491826238993, 'developer@test.com'),
(35, 1491911409060, 'developer@test.com'),
(36, 1491911468560, 'developer@test.com'),
(37, 1491911537354, 'developer@test.com'),
(38, 1491911727750, 'developer@test.com'),
(39, 1491911783432, 'developer@test.com'),
(40, 1491912000211, 'developer@test.com'),
(41, 1491912085157, 'developer@test.com'),
(42, 1491912119387, 'developer@test.com'),
(43, 1491912175230, 'developer@test.com'),
(44, 1491913862609, 'developer@test.com'),
(45, 1491915601765, 'developer@test.com'),
(46, 1492545149048, 'developer@test.com'),
(47, 1492545175076, 'developer@test.com'),
(48, 1492545194265, 'developer@test.com'),
(49, 1492545973890, 'developer@test.com'),
(50, 1492545999482, 'developer@test.com'),
(51, 1492550036707, 'developer@test.com'),
(52, 1492625074647, 'developer@test.com'),
(53, 1492635666328, 'developer@test.com'),
(54, 1492635690119, 'developer@test.com'),
(55, 1492639101908, 'developer@test.com'),
(56, 1492639409419, 'developer@test.com'),
(57, 1492639427149, 'developer@test.com'),
(58, 1492639510154, 'developer@test.com'),
(59, 1492725325441, 'api@admin.com'),
(60, 1493055149360, 'api@admin.com'),
(61, 1493055168663, 'api@admin.com'),
(62, 1493055177942, 'api@admin.com'),
(63, 1493056178943, 'api@admin.com'),
(64, 1493057490785, 'api@admin.com'),
(65, 1493061230486, 'api@admin.com'),
(66, 1493061237642, 'api@admin.com'),
(67, 1493061292935, 'api@admin.com'),
(68, 1493071939861, 'api@admin.com'),
(69, 1493073215918, 'api@admin.com'),
(70, 1493075404748, 'api@admin.com'),
(71, 1493075488081, 'api@admin.com'),
(72, 1493075498076, 'api@admin.com'),
(73, 1493075739990, 'api@admin.com'),
(74, 1493075805493, 'api@admin.com'),
(75, 1493075875964, 'api@admin.com'),
(76, 1493075927773, 'api@admin.com'),
(77, 1493075968630, 'api@admin.com'),
(78, 1493076099154, 'api@admin.com'),
(79, 1493076120435, 'api@admin.com'),
(80, 1493076175834, 'api@admin.com'),
(81, 1493143582911, 'api@admin.com'),
(82, 1493147073371, 'api@admin.com'),
(83, 1493147215312, 'admin@admin.com'),
(84, 1493150481053, 'api@admin.com'),
(85, 1493150544769, 'api@admin.com'),
(86, 1493150691821, 'api@admin.com'),
(87, 1493150721001, 'api@admin.com'),
(88, 1493150742110, 'api@admin.com'),
(89, 1493150781510, 'api@admin.com'),
(90, 1493150808621, 'weblogic@oracle.com'),
(91, 1493154759673, 'api@admin.com'),
(92, 1493154843783, 'api@admin.com'),
(93, 1493155526274, 'api@admin.com'),
(94, 1493155694235, 'api@admin.com'),
(95, 1493155764595, 'api@admin.com'),
(96, 1493157239099, 'api@admin.com'),
(97, 1493157259127, 'api@admin.com'),
(98, 1493157645572, 'api@admin.com'),
(99, 1493157654613, 'api@admin.com'),
(100, 1493158619996, 'api@admin.com'),
(101, 1493158653216, 'admin@admin.com'),
(102, 1493159080191, 'api@admin.com'),
(103, 1493159313599, 'api@admin.com'),
(104, 1493160043926, 'api@admin.com'),
(105, 1493160138863, 'api@admin.com'),
(106, 1493160152156, 'api@admin.com'),
(107, 1493160182542, 'api@admin.com'),
(108, 1493160194188, 'api@admin.com'),
(109, 1493160557717, 'api@admin.com'),
(110, 1493160574842, 'api@admin.com'),
(111, 1493160696572, 'api@admin.com'),
(112, 1493160754104, 'api@admin.com'),
(113, 1493160781773, 'api@admin.com'),
(114, 1493160786981, 'api@admin.com'),
(115, 1493160815921, 'api@admin.com'),
(116, 1493160925562, 'api@admin.com'),
(117, 1493160938344, 'api@admin.com'),
(118, 1493160946871, 'api@admin.com'),
(119, 1493228908968, 'api@admin.com'),
(120, 1493228923993, 'api@admin.com'),
(121, 1493228929595, 'api@admin.com'),
(122, 1493228933913, 'api@admin.com'),
(123, 1493230944250, 'admin@admin.com'),
(124, 1493231978173, 'admin@admin.com'),
(125, 1493232057159, 'admin@admin.com'),
(126, 1493232066884, 'admin@admin.com'),
(127, 1493232712090, 'admin@admin.com'),
(128, 1493233182453, 'admin@admin.com'),
(129, 1493234962628, 'admin@admin.com'),
(130, 1493235169308, 'admin@admin.com'),
(131, 1493237299074, 'admin@admin.com'),
(132, 1493237700590, 'admin@admin.com'),
(133, 1493237837165, 'admin@admin.com'),
(134, 1493238437926, 'admin@admin.com'),
(135, 1493239221737, 'admin@admin.com'),
(136, 1493239629241, 'admin@admin.com'),
(137, 1493239642134, 'weblogic@oracle.com'),
(138, 1493242014719, 'admin@admin.com'),
(139, 1493243069245, 'admin@admin.com'),
(140, 1493244101029, 'admin@admin.com'),
(141, 1493244116373, 'admin@admin.com'),
(142, 1493244122009, 'weblogic@oracle.com'),
(143, 1493244129802, 'weblogic@oracle.com'),
(144, 1493244594560, 'admin@admin.com'),
(145, 1493245332126, 'admin@admin.com'),
(146, 1493245340726, 'weblogic@oracle.com'),
(147, 1493245407213, 'weblogic@oracle.com'),
(148, 1493245490767, 'weblogic@oracle.com'),
(149, 1493245496580, 'admin@admin.com'),
(150, 1493245563693, 'admin@admin.com'),
(151, 1493245575847, 'admin@admin.com'),
(152, 1493245581879, 'weblogic@oracle.com'),
(153, 1493245588837, 'weblogic@oracle.com'),
(154, 1493245705404, 'admin@admin.com'),
(155, 1493245799100, 'admin@admin.com'),
(156, 1493245809871, 'admin@admin.com'),
(157, 1493245847687, 'admin@admin.com'),
(158, 1493245863021, 'admin@admin.com'),
(159, 1493245886397, 'admin@admin.com'),
(160, 1493245890971, 'admin@admin.com'),
(161, 1493246129907, 'admin@admin.com'),
(162, 1493246176882, 'admin@admin.com'),
(163, 1493246182023, 'weblogic@oracle.com'),
(164, 1493246304832, 'weblogic@oracle.com'),
(165, 1493246310745, 'admin@admin.com'),
(166, 1493246374921, 'admin@admin.com'),
(167, 1493246822740, 'admin@admin.com'),
(168, 1493247192433, 'admin@admin.com'),
(169, 1493247379692, 'admin@admin.com'),
(170, 1493247528816, 'admin@admin.com'),
(171, 1493247599906, 'admin@admin.com'),
(172, 1493247604730, 'weblogic@oracle.com'),
(173, 1493247715553, 'weblogic@oracle.com'),
(174, 1493247728951, 'weblogic@oracle.com'),
(175, 1493314037773, 'admin@admin.com'),
(176, 1493314048578, 'admin@admin.com'),
(177, 1493314053872, 'weblogic@oracle.com'),
(178, 1493314091185, 'weblogic@oracle.com'),
(179, 1493314096505, 'weblogic@oracle.com'),
(180, 1493314128497, 'weblogic@oracle.com'),
(181, 1493314135374, 'admin@admin.com'),
(182, 1493314147693, 'admin@admin.com'),
(183, 1493316669388, 'admin@admin.com'),
(184, 1493316704473, 'admin@admin.com'),
(185, 1493316728274, 'weblogic@oracle.com'),
(186, 1493317599104, 'admin@admin.com'),
(187, 1493333762785, 'weblogic@oracle.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `grupo_recurso`
--

CREATE TABLE IF NOT EXISTS `grupo_recurso` (
  `id_grupo_recurso` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `descricao` varchar(400) NOT NULL,
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id_grupo_recurso`),
  UNIQUE KEY `UK_438ers4vrjv2xn5ci5dxb9dv0` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(188);

-- --------------------------------------------------------

--
-- Estrutura da tabela `modulo`
--

CREATE TABLE IF NOT EXISTS `modulo` (
  `id_modulo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_modulo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `modulo`
--

INSERT INTO `modulo` (`id_modulo`, `nome`, `status`) VALUES
(1, 'MT', 1),
(2, 'CBA', 1),
(3, 'RR', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `modulo_aud`
--

CREATE TABLE IF NOT EXISTS `modulo_aud` (
  `id_modulo` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_modulo`,`REV`),
  KEY `FK8drnns84nab159k2atar7vyec` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `modulo_aud`
--

INSERT INTO `modulo_aud` (`id_modulo`, `REV`, `REVTYPE`, `nome`, `status`) VALUES
(1, 35, 1, 'MT', 1),
(1, 36, 1, 'MT', 1),
(1, 37, 1, 'MT', 1),
(1, 43, 1, 'MT', 1),
(1, 46, 1, 'MT', 1),
(1, 48, 1, 'MT', 1),
(1, 49, 1, 'MT', 1),
(1, 50, 1, 'MT', 1),
(1, 54, 1, 'MT', 1),
(1, 55, 1, 'MT', 1),
(1, 60, 1, 'MT', 1),
(1, 68, 1, 'MT', 1),
(1, 69, 1, 'MT', 1),
(1, 70, 1, 'MT', 1),
(1, 71, 1, 'MT', 1),
(1, 72, 1, 'MT', 1),
(1, 73, 1, 'MT', 1),
(1, 74, 1, 'MT', 1),
(1, 75, 1, 'MT', 1),
(1, 76, 1, 'MT', 1),
(1, 77, 1, 'MT', 1),
(1, 78, 1, 'MT', 1),
(1, 79, 1, 'MT', 1),
(1, 80, 1, 'MT', 1),
(1, 81, 1, 'MT', 1),
(1, 82, 1, 'MT', 1),
(1, 83, 1, 'MT', 1),
(1, 84, 1, 'MT', 1),
(1, 85, 1, 'MT', 1),
(1, 86, 1, 'MT', 1),
(1, 87, 1, 'MT', 1),
(1, 88, 1, 'MT', 1),
(1, 89, 1, 'MT', 1),
(1, 90, 1, 'MT', 1),
(1, 91, 1, 'MT', 1),
(1, 92, 1, 'MT', 1),
(1, 93, 1, 'MT', 1),
(1, 94, 1, 'MT', 1),
(1, 95, 1, 'MT', 1),
(1, 96, 1, 'MT', 1),
(1, 97, 1, 'MT', 1),
(1, 98, 1, 'MT', 1),
(1, 99, 1, 'MT', 1),
(1, 100, 1, 'MT', 1),
(1, 101, 1, 'MT', 1),
(1, 102, 1, 'MT', 1),
(1, 103, 1, 'MT', 1),
(1, 104, 1, 'MT', 1),
(1, 105, 1, 'MT', 1),
(1, 106, 1, 'MT', 1),
(1, 107, 1, 'MT', 1),
(1, 108, 1, 'MT', 1),
(1, 109, 1, 'MT', 1),
(1, 110, 1, 'MT', 1),
(1, 111, 1, 'MT', 1),
(1, 112, 1, 'MT', 1),
(1, 113, 1, 'MT', 1),
(1, 114, 1, 'MT', 1),
(1, 115, 1, 'MT', 1),
(1, 116, 1, 'MT', 1),
(1, 117, 1, 'MT', 1),
(1, 118, 1, 'MT', 1),
(1, 119, 1, 'MT', 1),
(1, 120, 1, 'MT', 1),
(1, 121, 1, 'MT', 1),
(1, 122, 1, 'MT', 1),
(1, 123, 1, 'MT', 1),
(1, 124, 1, 'MT', 1),
(1, 125, 1, 'MT', 1),
(1, 126, 1, 'MT', 1),
(1, 127, 1, 'MT', 1),
(1, 128, 1, 'MT', 1),
(1, 129, 1, 'MT', 1),
(1, 130, 1, 'MT', 1),
(1, 131, 1, 'MT', 1),
(1, 132, 1, 'MT', 1),
(1, 133, 1, 'MT', 1),
(1, 134, 1, 'MT', 1),
(1, 135, 1, 'MT', 1),
(1, 136, 1, 'MT', 1),
(1, 137, 1, 'MT', 1),
(1, 138, 1, 'MT', 1),
(1, 139, 1, 'MT', 1),
(1, 140, 1, 'MT', 1),
(1, 141, 1, 'MT', 1),
(1, 142, 1, 'MT', 1),
(1, 143, 1, 'MT', 1),
(1, 144, 1, 'MT', 1),
(1, 145, 1, 'MT', 1),
(1, 146, 1, 'MT', 1),
(1, 147, 1, 'MT', 1),
(1, 148, 1, 'MT', 1),
(1, 149, 1, 'MT', 1),
(1, 150, 1, 'MT', 1),
(1, 151, 1, 'MT', 1),
(1, 152, 1, 'MT', 1),
(1, 153, 1, 'MT', 1),
(1, 154, 1, 'MT', 1),
(1, 155, 1, 'MT', 1),
(1, 156, 1, 'MT', 1),
(1, 157, 1, 'MT', 1),
(1, 158, 1, 'MT', 1),
(1, 159, 1, 'MT', 1),
(1, 160, 1, 'MT', 1),
(1, 161, 1, 'MT', 1),
(1, 162, 1, 'MT', 1),
(1, 163, 1, 'MT', 1),
(1, 164, 1, 'MT', 1),
(1, 165, 1, 'MT', 1),
(1, 166, 1, 'MT', 1),
(1, 167, 1, 'MT', 1),
(1, 168, 1, 'MT', 1),
(1, 169, 1, 'MT', 1),
(1, 170, 1, 'MT', 1),
(1, 171, 1, 'MT', 1),
(1, 172, 1, 'MT', 1),
(1, 173, 1, 'MT', 1),
(1, 174, 1, 'MT', 1),
(1, 175, 1, 'MT', 1),
(1, 176, 1, 'MT', 1),
(1, 177, 1, 'MT', 1),
(1, 178, 1, 'MT', 1),
(1, 179, 1, 'MT', 1),
(1, 180, 1, 'MT', 1),
(1, 181, 1, 'MT', 1),
(1, 182, 1, 'MT', 1),
(1, 183, 1, 'MT', 1),
(1, 184, 1, 'MT', 1),
(1, 185, 1, 'MT', 1),
(1, 186, 1, 'MT', 1),
(1, 187, 1, 'MT', 1),
(2, 16, 1, 'CBA', 1),
(2, 17, 1, 'CBA', 1),
(2, 21, 1, 'CBA', 1),
(2, 22, 1, 'CBA', 1),
(2, 23, 1, 'CBA', 1),
(2, 25, 1, 'CBA', 1),
(2, 35, 1, 'CBA', 1),
(2, 36, 1, 'CBA', 1),
(2, 37, 1, 'CBA', 1),
(2, 53, 1, 'CBA', 1),
(2, 54, 1, 'CBA', 1),
(2, 56, 1, 'CBA', 1),
(2, 57, 1, 'CBA', 1),
(2, 58, 1, 'CBA', 1),
(2, 60, 1, 'CBA', 1),
(2, 68, 1, 'CBA', 1),
(2, 69, 1, 'CBA', 1),
(2, 70, 1, 'CBA', 1),
(2, 71, 1, 'CBA', 1),
(2, 72, 1, 'CBA', 1),
(2, 73, 1, 'CBA', 1),
(2, 74, 1, 'CBA', 1),
(2, 75, 1, 'CBA', 1),
(2, 76, 1, 'CBA', 1),
(2, 77, 1, 'CBA', 1),
(2, 78, 1, 'CBA', 1),
(2, 79, 1, 'CBA', 1),
(2, 80, 1, 'CBA', 1),
(2, 81, 1, 'CBA', 1),
(2, 82, 1, 'CBA', 1),
(2, 84, 1, 'CBA', 1),
(2, 85, 1, 'CBA', 1),
(2, 86, 1, 'CBA', 1),
(2, 90, 1, 'CBA', 1),
(2, 91, 1, 'CBA', 1),
(2, 92, 1, 'CBA', 1),
(2, 93, 1, 'CBA', 1),
(2, 94, 1, 'CBA', 1),
(2, 95, 1, 'CBA', 1),
(2, 96, 1, 'CBA', 1),
(2, 97, 1, 'CBA', 1),
(2, 98, 1, 'CBA', 1),
(2, 99, 1, 'CBA', 1),
(2, 100, 1, 'CBA', 1),
(2, 103, 1, 'CBA', 1),
(2, 106, 1, 'CBA', 1),
(2, 107, 1, 'CBA', 1),
(2, 114, 1, 'CBA', 1),
(2, 115, 1, 'CBA', 1),
(2, 116, 1, 'CBA', 1),
(2, 117, 1, 'CBA', 1),
(2, 121, 1, 'CBA', 1),
(2, 122, 1, 'CBA', 1),
(2, 137, 1, 'CBA', 1),
(2, 142, 1, 'CBA', 1),
(2, 143, 1, 'CBA', 1),
(2, 146, 1, 'CBA', 1),
(2, 147, 1, 'CBA', 1),
(2, 148, 1, 'CBA', 1),
(2, 152, 1, 'CBA', 1),
(2, 153, 1, 'CBA', 1),
(2, 163, 1, 'CBA', 1),
(2, 164, 1, 'CBA', 1),
(2, 172, 1, 'CBA', 1),
(2, 173, 1, 'CBA', 1),
(2, 174, 1, 'CBA', 1),
(2, 177, 1, 'CBA', 1),
(2, 178, 1, 'CBA', 1),
(2, 179, 1, 'CBA', 1),
(2, 180, 1, 'CBA', 1),
(2, 185, 1, 'CBA', 1),
(3, 18, 1, 'RR', 1),
(3, 19, 1, 'RR', 1),
(3, 24, 1, 'RR', 1),
(3, 35, 1, 'RR', 1),
(3, 36, 1, 'RR', 1),
(3, 37, 1, 'RR', 1),
(3, 44, 1, 'RR', 1),
(3, 45, 1, 'RR', 1),
(3, 47, 1, 'RR', 1),
(3, 48, 1, 'RR', 1),
(3, 50, 1, 'RR', 1),
(3, 54, 1, 'RR', 1),
(3, 60, 1, 'RR', 1),
(3, 68, 1, 'RR', 1),
(3, 69, 1, 'RR', 1),
(3, 70, 1, 'RR', 1),
(3, 71, 1, 'RR', 1),
(3, 72, 1, 'RR', 1),
(3, 73, 1, 'RR', 1),
(3, 74, 1, 'RR', 1),
(3, 75, 1, 'RR', 1),
(3, 76, 1, 'RR', 1),
(3, 77, 1, 'RR', 1),
(3, 78, 1, 'RR', 1),
(3, 79, 1, 'RR', 1),
(3, 80, 1, 'RR', 1),
(3, 81, 1, 'RR', 1),
(3, 82, 1, 'RR', 1),
(3, 84, 1, 'RR', 1),
(3, 85, 1, 'RR', 1),
(3, 86, 1, 'RR', 1),
(3, 90, 1, 'RR', 1),
(3, 91, 1, 'RR', 1),
(3, 92, 1, 'RR', 1),
(3, 93, 1, 'RR', 1),
(3, 94, 1, 'RR', 1),
(3, 95, 1, 'RR', 1),
(3, 96, 1, 'RR', 1),
(3, 97, 1, 'RR', 1),
(3, 98, 1, 'RR', 1),
(3, 99, 1, 'RR', 1),
(3, 100, 1, 'RR', 1),
(3, 101, 1, 'RR', 1),
(3, 102, 1, 'RR', 1),
(3, 103, 1, 'RR', 1),
(3, 104, 1, 'RR', 1),
(3, 105, 1, 'RR', 1),
(3, 106, 1, 'RR', 1),
(3, 107, 1, 'RR', 1),
(3, 108, 1, 'RR', 1),
(3, 109, 1, 'RR', 1),
(3, 110, 1, 'RR', 1),
(3, 111, 1, 'RR', 1),
(3, 112, 1, 'RR', 1),
(3, 113, 1, 'RR', 1),
(3, 114, 1, 'RR', 1),
(3, 115, 1, 'RR', 1),
(3, 116, 1, 'RR', 1),
(3, 117, 1, 'RR', 1),
(3, 118, 1, 'RR', 1),
(3, 119, 1, 'RR', 1),
(3, 120, 1, 'RR', 1),
(3, 121, 1, 'RR', 1),
(3, 122, 1, 'RR', 1),
(3, 123, 1, 'RR', 1),
(3, 124, 1, 'RR', 1),
(3, 125, 1, 'RR', 1),
(3, 126, 1, 'RR', 1),
(3, 127, 1, 'RR', 1),
(3, 128, 1, 'RR', 1),
(3, 129, 1, 'RR', 1),
(3, 130, 1, 'RR', 1),
(3, 131, 1, 'RR', 1),
(3, 132, 1, 'RR', 1),
(3, 133, 1, 'RR', 1),
(3, 134, 1, 'RR', 1),
(3, 135, 1, 'RR', 1),
(3, 136, 1, 'RR', 1),
(3, 137, 1, 'RR', 1),
(3, 138, 1, 'RR', 1),
(3, 139, 1, 'RR', 1),
(3, 140, 1, 'RR', 1),
(3, 141, 1, 'RR', 1),
(3, 142, 1, 'RR', 1),
(3, 143, 1, 'RR', 1),
(3, 144, 1, 'RR', 1),
(3, 145, 1, 'RR', 1),
(3, 146, 1, 'RR', 1),
(3, 147, 1, 'RR', 1),
(3, 148, 1, 'RR', 1),
(3, 149, 1, 'RR', 1),
(3, 150, 1, 'RR', 1),
(3, 151, 1, 'RR', 1),
(3, 152, 1, 'RR', 1),
(3, 153, 1, 'RR', 1),
(3, 154, 1, 'RR', 1),
(3, 155, 1, 'RR', 1),
(3, 156, 1, 'RR', 1),
(3, 157, 1, 'RR', 1),
(3, 158, 1, 'RR', 1),
(3, 159, 1, 'RR', 1),
(3, 160, 1, 'RR', 1),
(3, 161, 1, 'RR', 1),
(3, 162, 1, 'RR', 1),
(3, 163, 1, 'RR', 1),
(3, 164, 1, 'RR', 1),
(3, 165, 1, 'RR', 1),
(3, 166, 1, 'RR', 1),
(3, 167, 1, 'RR', 1),
(3, 168, 1, 'RR', 1),
(3, 169, 1, 'RR', 1),
(3, 170, 1, 'RR', 1),
(3, 171, 1, 'RR', 1),
(3, 172, 1, 'RR', 1),
(3, 173, 1, 'RR', 1),
(3, 174, 1, 'RR', 1),
(3, 175, 1, 'RR', 1),
(3, 176, 1, 'RR', 1),
(3, 177, 1, 'RR', 1),
(3, 178, 1, 'RR', 1),
(3, 179, 1, 'RR', 1),
(3, 180, 1, 'RR', 1),
(3, 181, 1, 'RR', 1),
(3, 182, 1, 'RR', 1),
(3, 183, 1, 'RR', 1),
(3, 184, 1, 'RR', 1),
(3, 185, 1, 'RR', 1),
(3, 186, 1, 'RR', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil_usuario`
--

CREATE TABLE IF NOT EXISTS `perfil_usuario` (
  `id_perfil_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime DEFAULT NULL,
  `nome` varchar(200) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_perfil_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `perfil_usuario`
--

INSERT INTO `perfil_usuario` (`id_perfil_usuario`, `data_cadastro`, `nome`, `status`) VALUES
(1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(2, '2017-04-24 00:00:00', 'Gestor MTI', 1),
(5, '2017-04-19 00:00:00', 'Consulta', 1),
(6, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(7, '2017-04-25 00:00:00', 'Perfil Master', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil_usuario_aud`
--

CREATE TABLE IF NOT EXISTS `perfil_usuario_aud` (
  `id_perfil_usuario` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_perfil_usuario`,`REV`),
  KEY `FK6gtahmk914tbfkq1y02twkno7` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `perfil_usuario_aud`
--

INSERT INTO `perfil_usuario_aud` (`id_perfil_usuario`, `REV`, `REVTYPE`, `data_cadastro`, `nome`, `status`) VALUES
(1, 70, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 71, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 72, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 73, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 74, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 75, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 76, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 77, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 78, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 79, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 80, 1, '2017-04-24 00:00:00', 'Master', 1),
(1, 81, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 82, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 83, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 84, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 85, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 86, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 87, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 88, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 89, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 90, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 91, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 92, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 93, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 94, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 95, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 96, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 97, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 98, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 99, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 100, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 101, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 102, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 103, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 104, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 105, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 106, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 107, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 108, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 109, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 110, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 111, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 112, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 113, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 114, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 115, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 116, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 117, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 118, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 119, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 120, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 121, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 122, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 123, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 124, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 125, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 126, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 127, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 128, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 129, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 130, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 131, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 132, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 133, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 134, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 135, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 136, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 137, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 138, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 139, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 140, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 141, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 142, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 143, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 144, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 145, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 146, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 147, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 148, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 149, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 150, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 151, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 152, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 153, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 154, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 155, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 156, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 157, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 158, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 159, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 160, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 161, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 162, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 163, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 164, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 165, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 166, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 167, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 168, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 169, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 170, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 171, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 172, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 173, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 174, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 175, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 176, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 177, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 178, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 179, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 180, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 181, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 182, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 183, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 184, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 185, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 186, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(1, 187, 1, '2017-04-24 00:00:00', 'Perfil Base', 1),
(6, 87, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 88, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 89, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 90, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 99, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 103, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 106, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 107, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 114, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 115, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 116, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 117, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 121, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 122, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 137, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 142, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 143, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 146, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 147, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 148, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 152, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 153, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 163, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 164, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 172, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 173, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 174, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 177, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 178, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 179, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 180, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 185, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(6, 187, 1, '2017-04-25 00:00:00', 'Perfil Analista', 1),
(7, 96, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 97, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 98, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 100, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 101, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 102, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 104, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 105, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 108, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 109, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 110, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 111, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 112, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 113, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 118, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 119, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 120, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 123, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 124, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 125, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 126, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 127, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 128, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 129, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 130, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 131, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 132, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 133, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 134, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 135, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 136, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 138, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 139, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 140, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 141, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 144, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 145, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 149, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 150, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 151, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 154, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 155, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 156, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 157, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 158, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 159, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 160, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 161, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 162, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 165, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 166, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 167, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 168, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 169, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 170, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 171, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 175, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 176, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 181, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 182, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 183, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 184, 1, '2017-04-25 00:00:00', 'Perfil Master', 1),
(7, 186, 1, '2017-04-25 00:00:00', 'Perfil Master', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil_usuario_role`
--

CREATE TABLE IF NOT EXISTS `perfil_usuario_role` (
  `id_perfil_usuario` bigint(20) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  UNIQUE KEY `perfil_role_unico` (`id_perfil_usuario`,`id_role`),
  KEY `FK_p5cox1o37p8a96dhvjr3xmq5h` (`id_role`),
  KEY `id_perfil_usuario` (`id_perfil_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `perfil_usuario_role`
--

INSERT INTO `perfil_usuario_role` (`id_perfil_usuario`, `id_role`) VALUES
(1, 1),
(2, 1),
(6, 2),
(7, 9),
(6, 10);

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil_usuario_role_aud`
--

CREATE TABLE IF NOT EXISTS `perfil_usuario_role_aud` (
  `REV` int(11) NOT NULL,
  `id_perfil_usuario` bigint(20) NOT NULL,
  `roles_id_role` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `id_role` bigint(20) NOT NULL,
  PRIMARY KEY (`REV`,`id_perfil_usuario`,`roles_id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `permissao`
--

CREATE TABLE IF NOT EXISTS `permissao` (
  `id_permissao` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(400) DEFAULT NULL,
  `nome` varchar(200) NOT NULL,
  `action` varchar(4000) DEFAULT NULL,
  `tipo_permissao` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_permissao`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Extraindo dados da tabela `permissao`
--

INSERT INTO `permissao` (`id_permissao`, `descricao`, `nome`, `action`, `tipo_permissao`) VALUES
(1, 'Autênticar no sistema.', 'autenticar.sistema', 'autenticar.sistema', 2),
(2, 'Item de menu para Consulta de solicitação', 'itemMenuSolicitacaoPesquisa', 'itemMenuSolicitacaoPesquisa', 1),
(3, 'Item de menu - Alterar Senha', 'itemMenuAlterarSenha', 'itemMenuAlterarSenha', 1),
(7, 'Exibir Pesquisa Solicitação', 'br.com.grrecurso.managed.solicitacao.SolicitacaoPesquisaAction.exibirPesquisa', 'br.com.grrecurso.managed.solicitacao.SolicitacaoPesquisaAction.exibirPesquisa', 2),
(8, 'Listar Módulos', 'br.com.grrecurso.service.modulo.ModuloService.listaModulos', 'br.com.grrecurso.service.modulo.ModuloService.listaModulos', 2),
(9, 'Menu Incluir Usuário', 'itemMenuIncluirUsuario', 'itemMenuIncluirUsuario', 1),
(10, 'Listar Perfil de Usuário', 'br.com.grrecurso.service.login.PerfilUsuarioService.listAll', 'br.com.grrecurso.service.login.PerfilUsuarioService.listAll', 2),
(11, 'Salvar usuário', 'br.com.grrecurso.service.login.UsuarioService.saveOrUpdate', 'br.com.grrecurso.service.login.UsuarioService.saveOrUpdate', 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `permissao_aud`
--

CREATE TABLE IF NOT EXISTS `permissao_aud` (
  `id_permissao` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `descricao` varchar(400) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `action` varchar(4000) DEFAULT NULL,
  `tipo_permissao` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_permissao`,`REV`),
  KEY `FKd10wq1opy3sf5959nhkl2hl8` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `permissao_aud`
--

INSERT INTO `permissao_aud` (`id_permissao`, `REV`, `REVTYPE`, `descricao`, `nome`, `action`, `tipo_permissao`) VALUES
(1, 51, 0, 'teste', 'teste', NULL, NULL),
(1, 52, 1, 'Permissão para incluir usuário.', 'incluir.usuario', NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `recurso`
--

CREATE TABLE IF NOT EXISTS `recurso` (
  `id_recurso` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `descricao` varchar(400) NOT NULL,
  `nome_interno` varchar(200) NOT NULL,
  `id_grupo_recurso` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_recurso`),
  UNIQUE KEY `UK_e6ocbr0vqgmc05ep4jbgcrhaj` (`nome_interno`),
  KEY `FKc8ythv42uw98385h06iikks9h` (`id_grupo_recurso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id_role` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(400) DEFAULT NULL,
  `nome` varchar(200) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_modulo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_role`),
  KEY `FKk6ea4m1syechgjskwykqqftam` (`id_usuario`),
  KEY `FK2jngsw4gfyvlmxy6p5uvki3w1` (`id_modulo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Extraindo dados da tabela `role`
--

INSERT INTO `role` (`id_role`, `descricao`, `nome`, `status`, `id_usuario`, `id_modulo`) VALUES
(1, 'Básico', 'ROLE_BASE', 1, 1, NULL),
(2, 'Consulta de Solicitação', 'ROLE_SOLICITACAO', 1, 1, NULL),
(8, 'Deployer', 'ROLE_DEPLOYER', 1, 1, NULL),
(9, 'Administrador', 'ROLE_ADMIN', 1, NULL, NULL),
(10, 'Cadastro de Usuário', 'ROLE_INCLUIR_USUARIO', 1, 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `role_aud`
--

CREATE TABLE IF NOT EXISTS `role_aud` (
  `id_role` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `descricao` varchar(400) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `action` varchar(4000) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_role`,`REV`),
  KEY `FK6j2c2jlbe8u8vyh2w5umal2o4` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `role_aud`
--

INSERT INTO `role_aud` (`id_role`, `REV`, `REVTYPE`, `descricao`, `nome`, `action`, `status`, `id_usuario`) VALUES
(1, 12, 1, 'Administrador1', 'Admin', NULL, NULL, NULL),
(1, 15, 1, 'Administrador', 'Admin', NULL, NULL, NULL),
(2, 6, 0, NULL, 'Tester', NULL, NULL, NULL),
(2, 7, 1, NULL, 'Testador', NULL, NULL, NULL),
(2, 8, 1, NULL, 'Tester', NULL, NULL, NULL),
(2, 13, 1, 'Role de teste', 'Tester', NULL, NULL, NULL),
(2, 14, 1, 'Testador', 'Tester', NULL, NULL, NULL),
(3, 28, 0, 'tet', 'teste', NULL, NULL, NULL),
(4, 29, 0, 'teste jonas', 'teste jonas', NULL, NULL, NULL),
(5, 30, 0, 'teste', 'teste', NULL, NULL, NULL),
(6, 31, 0, 'teste', 'teste', NULL, NULL, NULL),
(7, 32, 0, 'asdf', 'asdf', NULL, NULL, NULL),
(8, 33, 0, 'Deployer', 'Deployer', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `role_permissao`
--

CREATE TABLE IF NOT EXISTS `role_permissao` (
  `id_role` bigint(20) NOT NULL,
  `id_permissao` bigint(20) NOT NULL,
  PRIMARY KEY (`id_role`,`id_permissao`),
  KEY `FK_fjg8sh1kvbangxv67026opq66` (`id_permissao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `role_permissao`
--

INSERT INTO `role_permissao` (`id_role`, `id_permissao`) VALUES
(1, 1),
(2, 2),
(9, 3),
(2, 7),
(10, 8),
(10, 9),
(10, 10),
(10, 11);

-- --------------------------------------------------------

--
-- Estrutura da tabela `role_permissao_aud`
--

CREATE TABLE IF NOT EXISTS `role_permissao_aud` (
  `REV` int(11) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  `id_permissao` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id_role`,`id_permissao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `solicitacao`
--

CREATE TABLE IF NOT EXISTS `solicitacao` (
  `id_solicitacao` bigint(20) NOT NULL AUTO_INCREMENT,
  `chamado` varchar(50) NOT NULL,
  `descricao` varchar(400) NOT NULL,
  `situacao_solicitacao` int(11) NOT NULL,
  `id_grupo_recurso` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_modulo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_solicitacao`),
  KEY `FK2cimb7akq28p1ykimb7waan85` (`id_grupo_recurso`),
  KEY `FKktgt8hjhady9nc1xvsox4vrk9` (`id_usuario`),
  KEY `FK2vqct3nnqfmym4rosbm55suhn` (`id_modulo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Extraindo dados da tabela `solicitacao`
--

INSERT INTO `solicitacao` (`id_solicitacao`, `chamado`, `descricao`, `situacao_solicitacao`, `id_grupo_recurso`, `id_usuario`, `id_modulo`) VALUES
(1, '123', 'Teste', 0, NULL, 1, 3),
(2, '4234324', 'testeste', 0, NULL, 1, 3),
(3, '465645', 'adjçasdfa', 0, NULL, 1, 2),
(4, '123123', 'teste', 0, NULL, 1, 3),
(5, '11111111', 'teste 11111', 1, NULL, 1, 2),
(6, '55555', 'teste5555', 0, NULL, 1, 2),
(7, '45664', 'novo', 1, NULL, 1, 2),
(8, '777777', 'sadfjaÃ§sdjf7777', 0, NULL, 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_login` datetime DEFAULT NULL,
  `email` varchar(120) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(400) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `edicao` bit(1) NOT NULL,
  `sexo` char(1) DEFAULT NULL,
  `is_desenv` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `data_login`, `email`, `nome`, `senha`, `status`, `edicao`, `sexo`, `is_desenv`) VALUES
(1, '2018-06-15 06:29:14', 'admin@admin.com', 'Administrador', '$2a$10$WjoNCUkQWA/kfIjdn/b1keAvXgNCKuT8.YTCQbxeSRfeHCAT4Z3rm', 1, b'0', 'M', 1),
(6, NULL, 'weblogic@oracle.com', 'Weblogic', '$2a$10$NjiFjw1eSPdOaNTFf9byVuzz2xoe0/idUgEfnDm2UaPDG3Pw9SLXu', 1, b'0', 'M', 1),
(8, NULL, 'oc4j@oracle.com.br', 'oc4j', '$2a$10$vs6oV6wlsxmjLKsFYx6HkOCYqdJe0vMOXorzKOzvHoszazKQ8wDLS', 1, b'0', 'M', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_aud`
--

CREATE TABLE IF NOT EXISTS `usuario_aud` (
  `id_usuario` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `data_login` datetime DEFAULT NULL,
  `edicao` bit(1) DEFAULT NULL,
  `email` varchar(120) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `senha` varchar(20) DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `is_desenv` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`,`REV`),
  KEY `FKnb5mq8rp7wh6uegm4852qck1f` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario_aud`
--

INSERT INTO `usuario_aud` (`id_usuario`, `REV`, `REVTYPE`, `data_login`, `edicao`, `email`, `nome`, `senha`, `sexo`, `status`, `is_desenv`) VALUES
(1, 1, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', 'welcome2', NULL, 0, NULL),
(1, 2, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, NULL, 0, NULL),
(1, 4, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, NULL, 1, NULL),
(1, 11, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 35, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 36, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 37, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 38, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 39, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 40, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 41, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 42, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 43, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 48, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 49, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 50, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 54, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 60, 1, '2017-04-24 13:32:29', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 61, 1, '2017-04-24 13:32:29', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 62, 1, '2017-04-24 13:32:29', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 63, 1, '2017-04-24 13:32:29', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 64, 1, '2017-04-24 13:32:29', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 65, 1, '2017-04-24 13:32:29', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 66, 1, '2017-04-24 13:32:29', b'0', 'developer@test.com', 'admin', NULL, 'M', 1, NULL),
(1, 67, 1, '2017-04-24 13:32:29', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 68, 1, '2017-04-24 18:12:20', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 69, 1, NULL, b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 70, 1, '2017-04-24 19:10:04', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 71, 1, NULL, b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 72, 1, '2017-04-24 19:11:38', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 73, 1, NULL, b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 74, 1, '2017-04-24 19:16:45', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 75, 1, NULL, b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 76, 1, '2017-04-24 19:18:48', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 77, 1, NULL, b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 78, 1, '2017-04-24 19:21:39', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 79, 1, NULL, b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 80, 1, '2017-04-24 19:22:56', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 81, 1, '2017-04-25 14:06:23', b'0', 'developer@test.com', 'admins', NULL, 'M', 1, NULL),
(1, 82, 1, '2017-04-25 15:04:33', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 84, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 85, 1, '2017-04-25 16:02:25', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 86, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 91, 1, '2017-04-25 17:12:40', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 92, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 93, 1, '2017-04-25 17:25:26', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 94, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 95, 1, '2017-04-25 17:29:25', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 96, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 97, 1, '2017-04-25 17:54:19', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 98, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 100, 1, '2017-04-25 18:17:00', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 101, 1, '2017-04-25 18:17:00', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 102, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 104, 1, '2017-04-25 18:40:44', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 105, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 108, 1, '2017-04-25 18:43:14', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 109, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 110, 1, '2017-04-25 18:49:35', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 111, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 112, 1, '2017-04-25 18:52:34', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 113, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 118, 1, '2017-04-25 18:55:47', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 119, 1, '2017-04-26 13:48:29', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 120, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 123, 1, '2017-04-26 14:22:24', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 124, 1, '2017-04-26 14:39:38', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 125, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 126, 1, '2017-04-26 14:41:07', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 127, 1, '2017-04-26 14:51:52', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 128, 1, '2017-04-26 14:51:52', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 129, 1, '2017-04-26 15:29:22', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 130, 1, '2017-04-26 15:32:49', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 131, 1, '2017-04-26 16:08:19', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 132, 1, '2017-04-26 16:15:00', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 133, 1, '2017-04-26 16:17:17', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 134, 1, '2017-04-26 16:27:18', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 135, 1, '2017-04-26 16:40:22', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 136, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 138, 1, '2017-04-26 17:26:55', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 139, 1, '2017-04-26 17:44:29', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 140, 1, '2017-04-26 18:01:41', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 141, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 144, 1, '2017-04-26 18:09:54', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 145, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 149, 1, '2017-04-26 18:24:57', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 150, 1, '2017-04-26 18:26:04', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 151, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 154, 1, '2017-04-26 18:28:25', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 155, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 156, 1, '2017-04-26 18:30:10', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 157, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 158, 1, '2017-04-26 18:31:03', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 159, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 160, 1, '2017-04-26 18:31:31', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 161, 1, '2017-04-26 18:35:30', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 162, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 165, 1, '2017-04-26 18:38:31', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 166, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 167, 1, '2017-04-26 18:47:03', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 168, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 169, 1, '2017-04-26 18:56:19', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 170, 1, '2017-04-26 18:58:49', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 171, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 175, 1, '2017-04-27 13:27:18', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 176, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 181, 1, '2017-04-27 13:28:55', b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 182, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 183, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 184, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(1, 186, 1, NULL, b'0', 'admin@admin.com', 'Administrador', NULL, 'M', 1, NULL),
(2, 3, 0, NULL, b'0', 'weblogic@test.com', 'weblogic', NULL, NULL, 1, NULL),
(2, 10, 1, NULL, b'0', 'weblogic@test.com', 'weblogic', NULL, 'F', 1, NULL),
(2, 26, 1, NULL, b'0', 'weblogic@test.com', 'weblogic1', NULL, 'F', 1, NULL),
(2, 27, 1, NULL, b'0', 'weblogic@test.com', 'weblogic', NULL, 'F', 1, NULL),
(3, 5, 0, NULL, b'0', 'oracle@test.com', 'oracle', NULL, NULL, 0, NULL),
(3, 9, 1, NULL, b'0', 'oracle@test.com', 'oracle', NULL, 'M', 0, NULL),
(4, 20, 0, NULL, b'0', 'jonas@teste.com', 'jonas', NULL, 'F', 1, NULL),
(5, 34, 0, NULL, b'0', 'node.js@node.com', 'Node', NULL, 'F', 1, NULL),
(5, 44, 1, NULL, b'0', 'node.js@node.com', 'Node', NULL, 'F', 1, NULL),
(5, 45, 1, NULL, b'0', 'node.js@node.com', 'Node', NULL, 'F', 1, NULL),
(5, 59, 1, NULL, b'0', 'node.js@node.com', 'Nodes', NULL, 'F', 1, NULL),
(6, 83, 0, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 87, 1, '2017-04-25 16:05:21', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 88, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 89, 1, '2017-04-25 16:06:21', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 90, 1, '2017-04-25 16:06:21', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 99, 1, '2017-04-25 18:00:55', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 103, 1, '2017-04-25 18:28:34', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 106, 1, '2017-04-25 18:42:32', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 107, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 114, 1, '2017-04-25 18:53:07', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 115, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 116, 1, '2017-04-25 18:55:26', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 117, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 121, 1, '2017-04-26 13:48:50', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 122, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 137, 1, '2017-04-26 16:47:22', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 142, 1, '2017-04-26 18:02:02', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 143, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 146, 1, '2017-04-26 18:22:21', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 147, 1, '2017-04-26 18:23:27', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 148, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 152, 1, '2017-04-26 18:26:22', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 153, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 163, 1, '2017-04-26 18:36:22', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 164, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 172, 1, '2017-04-26 19:00:05', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 173, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 174, 1, '2017-04-26 19:02:09', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 177, 1, '2017-04-27 13:27:34', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 178, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 179, 1, '2017-04-27 13:28:16', b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 180, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(6, 185, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'M', 1, NULL),
(8, 187, 0, NULL, b'0', 'oc4j@oracle.com', 'oc4j', NULL, 'M', 1, NULL);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_modulos`
--

CREATE TABLE IF NOT EXISTS `usuario_modulos` (
  `id_usuario` bigint(20) NOT NULL,
  `id_modulo` bigint(20) NOT NULL,
  KEY `FKfe7qrgfqawugr9iabatc219ov` (`id_modulo`),
  KEY `FK298rqi62hp5j08f7h7m1edf15` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario_modulos`
--

INSERT INTO `usuario_modulos` (`id_usuario`, `id_modulo`) VALUES
(1, 1),
(1, 3),
(6, 1),
(6, 3),
(6, 2),
(8, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_modulos_aud`
--

CREATE TABLE IF NOT EXISTS `usuario_modulos_aud` (
  `REV` int(11) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_modulo` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id_usuario`,`id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario_modulos_aud`
--

INSERT INTO `usuario_modulos_aud` (`REV`, `id_usuario`, `id_modulo`, `REVTYPE`) VALUES
(37, 1, 1, 2),
(37, 1, 2, 2),
(37, 1, 3, 2),
(43, 1, 1, 0),
(44, 5, 3, 0),
(48, 1, 3, 0),
(49, 1, 3, 2),
(50, 1, 3, 0),
(54, 1, 2, 0),
(83, 6, 1, 0),
(90, 6, 2, 0),
(90, 6, 3, 0),
(101, 1, 2, 2),
(187, 8, 1, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_perfil_usuario`
--

CREATE TABLE IF NOT EXISTS `usuario_perfil_usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `id_perfil_usuario` bigint(20) NOT NULL,
  KEY `FK50ktisfhsc3asherd0roukcn` (`id_perfil_usuario`),
  KEY `FK4sj58upn357wh3a0dqxqkxg4k` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario_perfil_usuario`
--

INSERT INTO `usuario_perfil_usuario` (`id_usuario`, `id_perfil_usuario`) VALUES
(1, 1),
(6, 1),
(6, 6),
(1, 7),
(8, 1),
(8, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_perfil_usuario_aud`
--

CREATE TABLE IF NOT EXISTS `usuario_perfil_usuario_aud` (
  `REV` int(11) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_perfil_usuario` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id_usuario`,`id_perfil_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario_perfil_usuario_aud`
--

INSERT INTO `usuario_perfil_usuario_aud` (`REV`, `id_usuario`, `id_perfil_usuario`, `REVTYPE`) VALUES
(83, 6, 1, 0),
(187, 8, 1, 0),
(187, 8, 6, 0);

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `endereco`
--
ALTER TABLE `endereco`
  ADD CONSTRAINT `FKekdpb8k6gmp3lllla9d1qgmxk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`);

--
-- Limitadores para a tabela `endereco_aud`
--
ALTER TABLE `endereco_aud`
  ADD CONSTRAINT `FKscj98nilkbrwixypnsfep1tgv` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `modulo_aud`
--
ALTER TABLE `modulo_aud`
  ADD CONSTRAINT `FK8drnns84nab159k2atar7vyec` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `perfil_usuario_aud`
--
ALTER TABLE `perfil_usuario_aud`
  ADD CONSTRAINT `FK6gtahmk914tbfkq1y02twkno7` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `perfil_usuario_role`
--
ALTER TABLE `perfil_usuario_role`
  ADD CONSTRAINT `FKeqkwli5q42gpoaao4okjqmupg` FOREIGN KEY (`id_perfil_usuario`) REFERENCES `perfil_usuario` (`id_perfil_usuario`),
  ADD CONSTRAINT `FKg85fc7i8fds5gprn8uqqke2sl` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `FK_p5cox1o37p8a96dhvjr3xmq5h` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Limitadores para a tabela `perfil_usuario_role_aud`
--
ALTER TABLE `perfil_usuario_role_aud`
  ADD CONSTRAINT `FK4mai656tyr2vqpxfn8wum8n5d` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `permissao_aud`
--
ALTER TABLE `permissao_aud`
  ADD CONSTRAINT `FKd10wq1opy3sf5959nhkl2hl8` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `recurso`
--
ALTER TABLE `recurso`
  ADD CONSTRAINT `FKc8ythv42uw98385h06iikks9h` FOREIGN KEY (`id_grupo_recurso`) REFERENCES `grupo_recurso` (`id_grupo_recurso`);

--
-- Limitadores para a tabela `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `FK2jngsw4gfyvlmxy6p5uvki3w1` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`),
  ADD CONSTRAINT `FKk6ea4m1syechgjskwykqqftam` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Limitadores para a tabela `role_aud`
--
ALTER TABLE `role_aud`
  ADD CONSTRAINT `FK6j2c2jlbe8u8vyh2w5umal2o4` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `role_permissao`
--
ALTER TABLE `role_permissao`
  ADD CONSTRAINT `FK5b5m7nw3b6daj1tvpg0glxtc9` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `FKqlft3esjibh4q9ctt8nyslv0f` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id_permissao`),
  ADD CONSTRAINT `FK_5a5mw16unmgwhas5crw7t1xhf` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `FK_fjg8sh1kvbangxv67026opq66` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id_permissao`);

--
-- Limitadores para a tabela `role_permissao_aud`
--
ALTER TABLE `role_permissao_aud`
  ADD CONSTRAINT `FK_l8a2dcrdiw1n3bsplr6iib1ry` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `solicitacao`
--
ALTER TABLE `solicitacao`
  ADD CONSTRAINT `FK2cimb7akq28p1ykimb7waan85` FOREIGN KEY (`id_grupo_recurso`) REFERENCES `grupo_recurso` (`id_grupo_recurso`),
  ADD CONSTRAINT `FK2vqct3nnqfmym4rosbm55suhn` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`),
  ADD CONSTRAINT `FKktgt8hjhady9nc1xvsox4vrk9` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Limitadores para a tabela `usuario_aud`
--
ALTER TABLE `usuario_aud`
  ADD CONSTRAINT `FKnb5mq8rp7wh6uegm4852qck1f` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `usuario_modulos`
--
ALTER TABLE `usuario_modulos`
  ADD CONSTRAINT `FK298rqi62hp5j08f7h7m1edf15` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FKfe7qrgfqawugr9iabatc219ov` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`);

--
-- Limitadores para a tabela `usuario_modulos_aud`
--
ALTER TABLE `usuario_modulos_aud`
  ADD CONSTRAINT `FKsv2f0u7jdnbnqb9s5rvccx60j` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Limitadores para a tabela `usuario_perfil_usuario`
--
ALTER TABLE `usuario_perfil_usuario`
  ADD CONSTRAINT `FK4sj58upn357wh3a0dqxqkxg4k` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FK50ktisfhsc3asherd0roukcn` FOREIGN KEY (`id_perfil_usuario`) REFERENCES `perfil_usuario` (`id_perfil_usuario`);

--
-- Limitadores para a tabela `usuario_perfil_usuario_aud`
--
ALTER TABLE `usuario_perfil_usuario_aud`
  ADD CONSTRAINT `FK81v4jm3dvogmywhhwmjeoi319` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
