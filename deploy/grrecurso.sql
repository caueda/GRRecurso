-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 16, 2017 at 11:31 PM
-- Server version: 5.6.17
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
-- Table structure for table `categoria`
--

CREATE TABLE IF NOT EXISTS `categoria` (
  `id_categoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `cd_categoria` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `endereco`
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
  KEY `FK_j5329u59ukgh9afyyr4h5wyvi` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `endereco_aud`
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
  KEY `FK_1octn1iw2guo5xquj10xmqj3c` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `grrecursorevisionentity`
--

CREATE TABLE IF NOT EXISTS `grrecursorevisionentity` (
  `id` int(11) NOT NULL,
  `timestamp` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `grrecursorevisionentity`
--

INSERT INTO `grrecursorevisionentity` (`id`, `timestamp`, `username`) VALUES
(1, 1467809724374, 'developer@test.com'),
(2, 1467838000914, 'developer@test.com'),
(3, 1467839489871, 'developer@test.com'),
(4, 1467839503032, 'developer@test.com'),
(5, 1467839671151, 'developer@test.com'),
(6, 1467839679656, 'developer@test.com'),
(7, 1467841543695, 'developer@test.com'),
(8, 1467841883501, 'developer@test.com'),
(9, 1467841959287, 'developer@test.com'),
(10, 1467842047144, 'developer@test.com'),
(11, 1467904915052, 'developer@test.com'),
(12, 1467905303090, 'developer@test.com'),
(13, 1467982934995, 'developer@test.com'),
(14, 1468008490165, 'developer@test.com'),
(15, 1468008498947, 'developer@test.com'),
(16, 1468008506046, 'developer@test.com'),
(17, 1468008517890, 'developer@test.com'),
(18, 1468010412868, 'developer@test.com'),
(19, 1468246721679, 'developer@test.com'),
(20, 1468246748018, 'developer@test.com'),
(21, 1469806722640, 'developer@test.com'),
(22, 1469806883451, 'developer@test.com'),
(23, 1469806901459, 'developer@test.com'),
(24, 1469806924142, 'developer@test.com'),
(25, 1469806934082, 'developer@test.com'),
(26, 1489602763028, 'developer@test.com'),
(27, 1489602785644, 'developer@test.com'),
(28, 1489602805917, 'developer@test.com'),
(29, 1489602847840, 'developer@test.com'),
(30, 1489603597672, 'developer@test.com'),
(31, 1489616103836, 'developer@test.com');

-- --------------------------------------------------------

--
-- Table structure for table `grupo_recurso`
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
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(32);

-- --------------------------------------------------------

--
-- Table structure for table `modulo`
--

CREATE TABLE IF NOT EXISTS `modulo` (
  `id_modulo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_modulo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `modulo`
--

INSERT INTO `modulo` (`id_modulo`, `nome`, `status`) VALUES
(1, 'MT', 1),
(2, 'RR', 1),
(3, 'CBA', 1);

-- --------------------------------------------------------

--
-- Table structure for table `modulo_aud`
--

CREATE TABLE IF NOT EXISTS `modulo_aud` (
  `id_modulo` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_modulo`,`REV`),
  KEY `FK_kufxrpqpoef4qf8yitl242ysg` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `modulo_aud`
--

INSERT INTO `modulo_aud` (`id_modulo`, `REV`, `REVTYPE`, `nome`, `status`) VALUES
(1, 23, 1, 'MT', 1),
(1, 25, 1, 'MT', 1);

-- --------------------------------------------------------

--
-- Table structure for table `perfil_usuario`
--

CREATE TABLE IF NOT EXISTS `perfil_usuario` (
  `id_perfil_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime DEFAULT NULL,
  `nome` varchar(200) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_perfil_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `perfil_usuario_aud`
--

CREATE TABLE IF NOT EXISTS `perfil_usuario_aud` (
  `id_perfil_usuario` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_perfil_usuario`,`REV`),
  KEY `FK_8box9i6rdkrrwx9i7jtjojcpm` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `recurso`
--

CREATE TABLE IF NOT EXISTS `recurso` (
  `id_recurso` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_cadastro` datetime NOT NULL,
  `descricao` varchar(400) NOT NULL,
  `nome_interno` varchar(200) NOT NULL,
  `id_grupo_recurso` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_recurso`),
  UNIQUE KEY `UK_e6ocbr0vqgmc05ep4jbgcrhaj` (`nome_interno`),
  KEY `FK_8fypw5ic5nbyxh01ewdejxv70` (`id_grupo_recurso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id_role` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(400) DEFAULT NULL,
  `nome` varchar(200) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `descricao`, `nome`) VALUES
(1, 'Papel Administrador do Sistema', 'admin'),
(2, 'Papel de testador', 'Tester');

-- --------------------------------------------------------

--
-- Table structure for table `role_aud`
--

CREATE TABLE IF NOT EXISTS `role_aud` (
  `id_role` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `descricao` varchar(400) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_role`,`REV`),
  KEY `FK_fb8gsknyuwjmq94eixyha6ak` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_aud`
--

INSERT INTO `role_aud` (`id_role`, `REV`, `REVTYPE`, `descricao`, `nome`) VALUES
(2, 20, 0, 'Papel de testador', 'Tester');

-- --------------------------------------------------------

--
-- Table structure for table `solicitacao`
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
  KEY `FK_oj3wjip3j4odbhfgnspy838ni` (`id_grupo_recurso`),
  KEY `FK_oll5kge5o49a2mwf6b6en09kw` (`id_usuario`),
  KEY `FK_teoacaaee1f2jif3au1g1aum3` (`id_modulo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `solicitacao`
--

INSERT INTO `solicitacao` (`id_solicitacao`, `chamado`, `descricao`, `situacao_solicitacao`, `id_grupo_recurso`, `id_usuario`, `id_modulo`) VALUES
(1, '123456789', 'teste', 0, NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_login` datetime DEFAULT NULL,
  `email` varchar(120) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `edicao` bit(1) NOT NULL,
  `sexo` char(1) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `data_login`, `email`, `nome`, `senha`, `status`, `edicao`, `sexo`) VALUES
(1, '2016-06-21 11:36:36', 'developer@test.com', 'Administrator', '$2a$10$KK01eM1ciq24Cz7cIJF.pu4J3h9Wr4tkZu1sgx.tTzJ1s7jWaw6cq', 1, b'0', 'F'),
(2, NULL, 'larry.harrison@oracle.com', 'Larry Harrison', '$2a$10$KK01eM1ciq24Cz7cIJF.pu4J3h9Wr4tkZu1sgx.tTzJ1s7jWaw6cq', 1, b'0', 'M'),
(3, NULL, 'logan@xmen.com', 'Logan', '$2a$10$KK01eM1ciq24Cz7cIJF.pu4J3h9Wr4tkZu1sgx.tTzJ1s7jWaw6cq', 1, b'0', 'M'),
(4, NULL, 'oracle@oracle.com', 'oracle', '$2a$10$KK01eM1ciq24Cz7cIJF.pu4J3h9Wr4tkZu1sgx.tTzJ1s7jWaw6cq', 1, b'0', 'M'),
(5, NULL, 'weblogic@oracle.com', 'Weblogic', '$2a$10$KK01eM1ciq24Cz7cIJF.pu4J3h9Wr4tkZu1sgx.tTzJ1s7jWaw6cq', 1, b'0', 'F'),
(6, NULL, 'wildfly@redhat.com', 'JBoss Wildfly', '$2a$10$KK01eM1ciq24Cz7cIJF.pu4J3h9Wr4tkZu1sgx.tTzJ1s7jWaw6cq', 1, b'0', 'M'),
(8, NULL, 'admin@docker.com', 'Docker Admin', '$2a$10$KK01eM1ciq24Cz7cIJF.pu4J3h9Wr4tkZu1sgx.tTzJ1s7jWaw6cq', 1, b'0', 'M'),
(9, NULL, 'luizrabelo@teste.com', 'Luiz Rabelo', '$2a$10$KK01eM1ciq24Cz7cIJF.pu4J3h9Wr4tkZu1sgx.tTzJ1s7jWaw6cq', 1, b'0', 'M');

-- --------------------------------------------------------

--
-- Table structure for table `usuario_aud`
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
  PRIMARY KEY (`id_usuario`,`REV`),
  KEY `FK_6oba3a8pnwggj7ddhebhewru6` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_aud`
--

INSERT INTO `usuario_aud` (`id_usuario`, `REV`, `REVTYPE`, `data_login`, `edicao`, `email`, `nome`, `senha`, `sexo`, `status`) VALUES
(1, 15, 1, '2016-06-21 11:36:36', b'0', 'developer@test.com', 'Administrator', NULL, 'F', 1),
(2, 14, 1, NULL, b'0', 'teste@teste.com', 'teste', NULL, 'M', 0),
(2, 21, 1, NULL, b'0', 'teste@teste.com', 'teste', NULL, 'M', 0),
(2, 24, 1, NULL, b'0', 'teste@teste.com', 'teste', NULL, 'M', 0),
(2, 26, 1, NULL, b'0', 'larry.harrison@oracle.com', 'Larry Harrison', NULL, 'M', 1),
(3, 27, 1, NULL, b'0', 'logan@xmen.com', 'Logan', NULL, 'M', 1),
(4, 3, 1, NULL, b'0', 'oracle@oracle.com', 'oracle', NULL, NULL, NULL),
(4, 4, 1, NULL, b'0', 'oracle@oracle.com', 'oracle', NULL, NULL, NULL),
(5, 2, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, 0),
(5, 5, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, NULL),
(5, 6, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, NULL),
(5, 7, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, NULL),
(5, 8, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, NULL),
(5, 9, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, NULL),
(5, 10, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, NULL),
(5, 11, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, 1),
(5, 12, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, NULL, 0),
(5, 17, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, 'M', 1),
(5, 18, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, 'F', 1),
(5, 22, 1, NULL, b'0', 'ricardo@teste.com', 'ricardo', NULL, 'F', 1),
(5, 28, 1, NULL, b'0', 'weblogic@oracle.com', 'Weblogic', NULL, 'F', 1),
(6, 29, 1, NULL, b'0', 'wildfly@redhat.com', 'JBoss Wildfly', NULL, 'M', 1),
(8, 13, 0, NULL, b'0', 'jboss@teste.com', 'jboss', NULL, 'M', 1),
(8, 16, 1, NULL, b'0', 'jboss@teste.com', 'jboss', NULL, 'F', 1),
(8, 19, 1, NULL, b'0', 'jboss@teste.com', 'jboss', NULL, 'M', 1),
(8, 30, 1, NULL, b'0', 'admin@docker.com', 'Docker Admin', NULL, 'M', 1),
(9, 31, 0, NULL, b'0', 'luizrabelo@teste.com', 'Luiz Rabelo', NULL, 'M', 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuario_modulos`
--

CREATE TABLE IF NOT EXISTS `usuario_modulos` (
  `id_usuario` bigint(20) NOT NULL,
  `id_modulo` bigint(20) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_modulo`),
  KEY `FK_kowji5qvg5og30icavllrr1v7` (`id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_modulos`
--

INSERT INTO `usuario_modulos` (`id_usuario`, `id_modulo`) VALUES
(1, 1),
(1, 2),
(1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `usuario_modulos_aud`
--

CREATE TABLE IF NOT EXISTS `usuario_modulos_aud` (
  `REV` int(11) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_modulo` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id_usuario`,`id_modulo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `usuario_perfil_usuario`
--

CREATE TABLE IF NOT EXISTS `usuario_perfil_usuario` (
  `id_usuario` bigint(20) NOT NULL,
  `id_perfil_usuario` bigint(20) NOT NULL,
  KEY `FK_3utu5tv3mydhwkt1pumpktwxu` (`id_perfil_usuario`),
  KEY `FK_d1y0h7lujtcsii8dmc9dwx8xk` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `usuario_perfil_usuario_aud`
--

CREATE TABLE IF NOT EXISTS `usuario_perfil_usuario_aud` (
  `REV` int(11) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_perfil_usuario` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id_usuario`,`id_perfil_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `usuario_role`
--

CREATE TABLE IF NOT EXISTS `usuario_role` (
  `id_usuario` bigint(20) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  KEY `FK_t2810c7g4l83wght217kwpux9` (`id_role`),
  KEY `FK_rjjwsv1npynmiou408xtu912m` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario_role`
--

INSERT INTO `usuario_role` (`id_usuario`, `id_role`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuario_role_aud`
--

CREATE TABLE IF NOT EXISTS `usuario_role_aud` (
  `REV` int(11) NOT NULL,
  `id_usuario` bigint(20) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`REV`,`id_usuario`,`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `endereco`
--
ALTER TABLE `endereco`
  ADD CONSTRAINT `FK_j5329u59ukgh9afyyr4h5wyvi` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id_usuario`);

--
-- Constraints for table `endereco_aud`
--
ALTER TABLE `endereco_aud`
  ADD CONSTRAINT `FK_1octn1iw2guo5xquj10xmqj3c` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Constraints for table `modulo_aud`
--
ALTER TABLE `modulo_aud`
  ADD CONSTRAINT `FK_kufxrpqpoef4qf8yitl242ysg` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Constraints for table `perfil_usuario_aud`
--
ALTER TABLE `perfil_usuario_aud`
  ADD CONSTRAINT `FK_8box9i6rdkrrwx9i7jtjojcpm` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Constraints for table `recurso`
--
ALTER TABLE `recurso`
  ADD CONSTRAINT `FK_8fypw5ic5nbyxh01ewdejxv70` FOREIGN KEY (`id_grupo_recurso`) REFERENCES `grupo_recurso` (`id_grupo_recurso`);

--
-- Constraints for table `role_aud`
--
ALTER TABLE `role_aud`
  ADD CONSTRAINT `FK_fb8gsknyuwjmq94eixyha6ak` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Constraints for table `solicitacao`
--
ALTER TABLE `solicitacao`
  ADD CONSTRAINT `FK_oj3wjip3j4odbhfgnspy838ni` FOREIGN KEY (`id_grupo_recurso`) REFERENCES `grupo_recurso` (`id_grupo_recurso`),
  ADD CONSTRAINT `FK_oll5kge5o49a2mwf6b6en09kw` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FK_teoacaaee1f2jif3au1g1aum3` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`);

--
-- Constraints for table `usuario_aud`
--
ALTER TABLE `usuario_aud`
  ADD CONSTRAINT `FK_6oba3a8pnwggj7ddhebhewru6` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Constraints for table `usuario_modulos`
--
ALTER TABLE `usuario_modulos`
  ADD CONSTRAINT `FK_kowji5qvg5og30icavllrr1v7` FOREIGN KEY (`id_modulo`) REFERENCES `modulo` (`id_modulo`),
  ADD CONSTRAINT `FK_r8u05degcf6ud67c5t8g695dy` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Constraints for table `usuario_modulos_aud`
--
ALTER TABLE `usuario_modulos_aud`
  ADD CONSTRAINT `FK_k9mtmay4qh9xhlxh4clucircf` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Constraints for table `usuario_perfil_usuario`
--
ALTER TABLE `usuario_perfil_usuario`
  ADD CONSTRAINT `FK_3utu5tv3mydhwkt1pumpktwxu` FOREIGN KEY (`id_perfil_usuario`) REFERENCES `perfil_usuario` (`id_perfil_usuario`),
  ADD CONSTRAINT `FK_d1y0h7lujtcsii8dmc9dwx8xk` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Constraints for table `usuario_perfil_usuario_aud`
--
ALTER TABLE `usuario_perfil_usuario_aud`
  ADD CONSTRAINT `FK_lonxa8t353ioy1okdw6pdfyy` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

--
-- Constraints for table `usuario_role`
--
ALTER TABLE `usuario_role`
  ADD CONSTRAINT `FK_rjjwsv1npynmiou408xtu912m` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FK_t2810c7g4l83wght217kwpux9` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Constraints for table `usuario_role_aud`
--
ALTER TABLE `usuario_role_aud`
  ADD CONSTRAINT `FK_mjv8n914yitb8sk185d54xh08` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
