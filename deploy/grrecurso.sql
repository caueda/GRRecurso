-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 17-Abr-2017 às 16:39
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
(45, 1491915601765, 'developer@test.com');

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
(46);

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
(2, 16, 1, 'CBA', 1),
(2, 17, 1, 'CBA', 1),
(2, 21, 1, 'CBA', 1),
(2, 22, 1, 'CBA', 1),
(2, 23, 1, 'CBA', 1),
(2, 25, 1, 'CBA', 1),
(2, 35, 1, 'CBA', 1),
(2, 36, 1, 'CBA', 1),
(2, 37, 1, 'CBA', 1),
(3, 18, 1, 'RR', 1),
(3, 19, 1, 'RR', 1),
(3, 24, 1, 'RR', 1),
(3, 35, 1, 'RR', 1),
(3, 36, 1, 'RR', 1),
(3, 37, 1, 'RR', 1),
(3, 44, 1, 'RR', 1),
(3, 45, 1, 'RR', 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil_usuario_role`
--

CREATE TABLE IF NOT EXISTS `perfil_usuario_role` (
  `id_perfil_usuario` bigint(20) NOT NULL,
  `roles_id_role` bigint(20) NOT NULL,
  PRIMARY KEY (`id_perfil_usuario`,`roles_id_role`),
  KEY `FK2ixwdgkaql1md8dexulh88e6g` (`roles_id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `perfil_usuario_role_aud`
--

CREATE TABLE IF NOT EXISTS `perfil_usuario_role_aud` (
  `REV` int(11) NOT NULL,
  `id_perfil_usuario` bigint(20) NOT NULL,
  `roles_id_role` bigint(20) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
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
  PRIMARY KEY (`id_permissao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  PRIMARY KEY (`id_permissao`,`REV`),
  KEY `FKd10wq1opy3sf5959nhkl2hl8` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  PRIMARY KEY (`id_role`),
  KEY `FKk6ea4m1syechgjskwykqqftam` (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Extraindo dados da tabela `role`
--

INSERT INTO `role` (`id_role`, `descricao`, `nome`, `status`, `id_usuario`) VALUES
(1, 'Administrador', 'Admin', 1, 1),
(2, 'Testador', 'Tester', 1, 2),
(8, 'Deployer', 'Deployer', 0, 1);

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
  KEY `FKqlft3esjibh4q9ctt8nyslv0f` (`id_permissao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `solicitacao`
--

INSERT INTO `solicitacao` (`id_solicitacao`, `chamado`, `descricao`, `situacao_solicitacao`, `id_grupo_recurso`, `id_usuario`, `id_modulo`) VALUES
(1, '123', 'Teste', 0, NULL, 1, 3),
(2, '4234324', 'testeste', 0, NULL, 1, 3),
(3, '465645', 'adjçasdfa', 0, NULL, 1, 2);

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
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `data_login`, `email`, `nome`, `senha`, `status`, `edicao`, `sexo`) VALUES
(1, '2016-06-27 07:50:20', 'developer@test.com', 'admin', '$2a$10$nWXHqZXTnt4LPMNpI/TEX.jwp7baCyqcrwVrY/9yR/9cblKaV5h8.', 1, b'0', 'M'),
(2, NULL, 'weblogic@test.com', 'weblogic', '$2a$10$nWXHqZXTnt4LPMNpI/TEX.jwp7baCyqcrwVrY/9yR/9cblKaV5h8.', 1, b'0', 'F'),
(3, NULL, 'oracle@test.com', 'oracle', '$2a$10$nWXHqZXTnt4LPMNpI/TEX.jwp7baCyqcrwVrY/9yR/9cblKaV5h8.', 0, b'0', 'M'),
(4, NULL, 'jonas@teste.com', 'jonas', '$2a$10$nWXHqZXTnt4LPMNpI/TEX.jwp7baCyqcrwVrY/9yR/9cblKaV5h8.', 1, b'0', 'F'),
(5, NULL, 'node.js@node.com', 'Node', '$2a$10$2AJIPN8Y/0t4XLTGszYJvOpYKneAivt4ciKNJdZWMTN0htrC30L1O', 1, b'0', 'F');

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
  PRIMARY KEY (`id_usuario`,`REV`),
  KEY `FKnb5mq8rp7wh6uegm4852qck1f` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario_aud`
--

INSERT INTO `usuario_aud` (`id_usuario`, `REV`, `REVTYPE`, `data_login`, `edicao`, `email`, `nome`, `senha`, `sexo`, `status`) VALUES
(1, 1, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', 'welcome2', NULL, 0),
(1, 2, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, NULL, 0),
(1, 4, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, NULL, 1),
(1, 11, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 35, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 36, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 37, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 38, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 39, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 40, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 41, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 42, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(1, 43, 1, '2016-06-27 07:50:20', b'0', 'developer@test.com', 'admin', NULL, 'M', 1),
(2, 3, 0, NULL, b'0', 'weblogic@test.com', 'weblogic', NULL, NULL, 1),
(2, 10, 1, NULL, b'0', 'weblogic@test.com', 'weblogic', NULL, 'F', 1),
(2, 26, 1, NULL, b'0', 'weblogic@test.com', 'weblogic1', NULL, 'F', 1),
(2, 27, 1, NULL, b'0', 'weblogic@test.com', 'weblogic', NULL, 'F', 1),
(3, 5, 0, NULL, b'0', 'oracle@test.com', 'oracle', NULL, NULL, 0),
(3, 9, 1, NULL, b'0', 'oracle@test.com', 'oracle', NULL, 'M', 0),
(4, 20, 0, NULL, b'0', 'jonas@teste.com', 'jonas', NULL, 'F', 1),
(5, 34, 0, NULL, b'0', 'node.js@node.com', 'Node', NULL, 'F', 1),
(5, 44, 1, NULL, b'0', 'node.js@node.com', 'Node', NULL, 'F', 1),
(5, 45, 1, NULL, b'0', 'node.js@node.com', 'Node', NULL, 'F', 1);

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
(4, 1),
(1, 1),
(5, 3);

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
(44, 5, 3, 0);

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

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_role`
--

CREATE TABLE IF NOT EXISTS `usuario_role` (
  `id_usuario` bigint(20) NOT NULL,
  `id_role` bigint(20) NOT NULL,
  KEY `FKk4syxwi0pg7sxaxyr9pej4t4a` (`id_role`),
  KEY `FK3acg56qv9q1c49y77chj0gaxv` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario_role`
--

INSERT INTO `usuario_role` (`id_usuario`, `id_role`) VALUES
(1, 1),
(4, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario_role_aud`
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
  ADD CONSTRAINT `FK2ixwdgkaql1md8dexulh88e6g` FOREIGN KEY (`roles_id_role`) REFERENCES `role` (`id_role`),
  ADD CONSTRAINT `FKeqkwli5q42gpoaao4okjqmupg` FOREIGN KEY (`id_perfil_usuario`) REFERENCES `perfil_usuario` (`id_perfil_usuario`);

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
  ADD CONSTRAINT `FKqlft3esjibh4q9ctt8nyslv0f` FOREIGN KEY (`id_permissao`) REFERENCES `permissao` (`id_permissao`);

--
-- Limitadores para a tabela `role_permissao_aud`
--
ALTER TABLE `role_permissao_aud`
  ADD CONSTRAINT `FKi6a5angalalcihmcd5kise39b` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

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

--
-- Limitadores para a tabela `usuario_role`
--
ALTER TABLE `usuario_role`
  ADD CONSTRAINT `FK3acg56qv9q1c49y77chj0gaxv` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`),
  ADD CONSTRAINT `FKk4syxwi0pg7sxaxyr9pej4t4a` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);

--
-- Limitadores para a tabela `usuario_role_aud`
--
ALTER TABLE `usuario_role_aud`
  ADD CONSTRAINT `FKa75xhw6kw3me81cj0idx8a1vi` FOREIGN KEY (`REV`) REFERENCES `grrecursorevisionentity` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
