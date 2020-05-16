-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.26 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 test 的数据库结构
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `test`;

-- 导出  表 test.ex_service 结构
CREATE TABLE IF NOT EXISTS `ex_service` (
  `service_id` int(4) DEFAULT NULL COMMENT '//服务编号',
  `sever_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '//服务人员编号',
  `ex_reason` text COLLATE utf8_bin COMMENT '//申请原因',
  `ex_resoult` text COLLATE utf8_bin COMMENT '//申请结果'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='//异常服务列表';

-- 正在导出表  test.ex_service 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `ex_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `ex_service` ENABLE KEYS */;

-- 导出  表 test.faq_table 结构
CREATE TABLE IF NOT EXISTS `faq_table` (
  `id` int(4) NOT NULL COMMENT 'faq编号',
  `faqName` varchar(40) COLLATE utf8_bin NOT NULL COMMENT 'faq问题',
  `faqDescription` text COLLATE utf8_bin NOT NULL COMMENT 'faq问题描述',
  `faqInfo` text COLLATE utf8_bin NOT NULL COMMENT 'faq问题解决',
  `faqSoftware` varchar(40) COLLATE utf8_bin NOT NULL COMMENT 'faq软件信息',
  `faqDate` date DEFAULT NULL COMMENT 'faq时间信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='faq数据库\r\n';

-- 正在导出表  test.faq_table 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `faq_table` DISABLE KEYS */;
INSERT INTO `faq_table` (`id`, `faqName`, `faqDescription`, `faqInfo`, `faqSoftware`, `faqDate`) VALUES
	(1, '问题1', '大苏打撒旦', '大撒大撒', 'QQ', '2020-05-02'),
	(2, '问题2', '唯有我有人', '甜蜜v摩羯而我却', 'AA', '2020-05-02'),
	(3, '问题3', '德尔惠', '五年白醋我', 'CC', '2020-05-02'),
	(11, '前端来的', '前端来的问题描述', 'ABC', 'xx', '2020-05-11'),
	(4, 'dsada', 'dwqdwd', 'dqdwq', 'dwq', '2020-05-12'),
	(5, 'dwqdwq', 'dwqeqwebhiu', 'wqeun', 'wewq ', '2020-05-12'),
	(6, 'iutrui', '7856', '32y9', '2632', '2020-05-12'),
	(7, 'dwae', 'fw', 'fw', 'fsa', '2020-05-12'),
	(8, 'qriuwhqiu', 'iubiuyhxbcibayb', 'idhwqndbuiwbqy', 'we', '2020-05-12'),
	(9, 'feyuqbu', ' vuicnie', 'fioqnn', 'cwq', '2020-05-12'),
	(10, 'cheinc', 'jencoq', 'fen', '', '2020-05-12'),
	(12, 'cejoij', 'feiuohi', 'fnueisn', 'cne', '2020-05-12'),
	(13, 'dwaihbib', 'asd', 'dsad', 'asd', '2020-05-12');
/*!40000 ALTER TABLE `faq_table` ENABLE KEYS */;

-- 导出  表 test.message_table 结构
CREATE TABLE IF NOT EXISTS `message_table` (
  `id` int(4) NOT NULL COMMENT '编号',
  `getName` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '接收人',
  `sendName` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '发送人',
  `justMessage` text COLLATE utf8_bin NOT NULL COMMENT '消息',
  `messageDate` date NOT NULL COMMENT '日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='//信息表';

-- 正在导出表  test.message_table 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `message_table` DISABLE KEYS */;
INSERT INTO `message_table` (`id`, `getName`, `sendName`, `justMessage`, `messageDate`) VALUES
	(1, 'BB', 'Admin', '0002换人', '2020-05-11'),
	(2, 'BB', 'Admin', '0003换人', '2020-05-11');
/*!40000 ALTER TABLE `message_table` ENABLE KEYS */;

-- 导出  表 test.order_table 结构
CREATE TABLE IF NOT EXISTS `order_table` (
  `id` int(11) NOT NULL,
  `orderId` varchar(20) COLLATE utf8_bin NOT NULL,
  `userName` varchar(20) COLLATE utf8_bin NOT NULL,
  `softwareName` varchar(40) COLLATE utf8_bin NOT NULL,
  `buyDate` date NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  test.order_table 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `order_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_table` ENABLE KEYS */;

-- 导出  表 test.permission 结构
CREATE TABLE IF NOT EXISTS `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin NOT NULL,
  `url` varchar(128) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  test.permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;

-- 导出  表 test.product_table 结构
CREATE TABLE IF NOT EXISTS `product_table` (
  `pd_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `Edition` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='//产品表信息';

-- 正在导出表  test.product_table 的数据：~11 rows (大约)
/*!40000 ALTER TABLE `product_table` DISABLE KEYS */;
INSERT INTO `product_table` (`pd_name`, `Edition`) VALUES
	('腾讯QQ', '1.1'),
	('腾讯QQ', '1.2'),
	('腾讯QQ', '1.3'),
	('腾讯QQ', '1.4'),
	('腾讯QQ', '1.5.1'),
	('腾讯QQ', '1.5.2'),
	('网易云音乐', '1.0'),
	('网易云音乐', '2.0'),
	('网易云音乐', '3.0'),
	('网易云音乐', '4.0'),
	('网易云音乐', '5.0');
/*!40000 ALTER TABLE `product_table` ENABLE KEYS */;

-- 导出  表 test.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  test.role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- 导出  表 test.role_permission 结构
CREATE TABLE IF NOT EXISTS `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  test.role_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;

-- 导出  表 test.server_table 结构
CREATE TABLE IF NOT EXISTS `server_table` (
  `id` int(4) NOT NULL COMMENT '服务人员编号',
  `serverName` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '服务人员账号',
  `serverPassword` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '服务人员密码',
  `serverSoftware` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '服务产品名',
  `serverState` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '服务人员状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='//服务人员表';

-- 正在导出表  test.server_table 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `server_table` DISABLE KEYS */;
INSERT INTO `server_table` (`id`, `serverName`, `serverPassword`, `serverSoftware`, `serverState`) VALUES
	(1, 'A123', '123456', '类型1', '在职'),
	(2, 'BB', '123456', '类型2', '在职'),
	(3, 'C100', '123456', '类型2', '在职'),
	(4, 'D100', '123456', '类型2', '在职'),
	(4, 'B100', '123456', '类型3', '在职');
/*!40000 ALTER TABLE `server_table` ENABLE KEYS */;

-- 导出  表 test.service_table 结构
CREATE TABLE IF NOT EXISTS `service_table` (
  `id` int(4) NOT NULL COMMENT '编号',
  `userName` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '客户名',
  `softwareName` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '产品名',
  `serverName` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '服务人员名',
  `serviceState` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '当前服务状态',
  `serviceKind` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '问题类型',
  `serviceInfo` mediumtext COLLATE utf8_bin NOT NULL COMMENT '问题描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='正在处理的售后服务';

-- 正在导出表  test.service_table 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `service_table` DISABLE KEYS */;
INSERT INTO `service_table` (`id`, `userName`, `softwareName`, `serverName`, `serviceState`, `serviceKind`, `serviceInfo`) VALUES
	(1, 'AA', 'KH1', 'A123', '处理中', '类型1', '飒飒就能看见你卡视角'),
	(2, 'BB', 'KH2', 'BB', '处理中', '类型2', '法撒旦撒'),
	(3, 'BB', 'KH3', 'BB', '异常', '类型2', '地方'),
	(0, 'CC', 'KH4', 'C100', '处理中', '类型2', '丰富');
/*!40000 ALTER TABLE `service_table` ENABLE KEYS */;

-- 导出  表 test.software_table 结构
CREATE TABLE IF NOT EXISTS `software_table` (
  `id` int(4) DEFAULT NULL,
  `softwareName` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `softwareInfo` text COLLATE utf8_bin,
  `updateDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  test.software_table 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `software_table` DISABLE KEYS */;
INSERT INTO `software_table` (`id`, `softwareName`, `softwareInfo`, `updateDate`) VALUES
	(1, '类型1', 'dsakhd', '2020-05-11'),
	(2, '类型2', 'wew', '2020-05-11'),
	(3, '类型3', 'weq', '2020-05-11'),
	(4, '类型4', 'ewq', '2020-05-11');
/*!40000 ALTER TABLE `software_table` ENABLE KEYS */;

-- 导出  表 test.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '账号',
  `password` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  test.user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`) VALUES
	(5, 'B1000', '123456');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  表 test.user_role 结构
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  test.user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
	(1, 5, 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

-- 导出  表 test.user_table 结构
CREATE TABLE IF NOT EXISTS `user_table` (
  `id` int(4) DEFAULT NULL COMMENT '编号',
  `userName` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '客户名',
  `userPassword` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '客户密码',
  `userState` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- 正在导出表  test.user_table 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `user_table` DISABLE KEYS */;
INSERT INTO `user_table` (`id`, `userName`, `userPassword`, `userState`) VALUES
	(1, 'AA', '123456', '发送消息'),
	(2, 'BB', '123456', '发送消息'),
	(3, 'CC', '123456', '封禁账户'),
	(4, 'B100', '123456', '正常'),
	(5, 'B1000', '123456', '正常');
/*!40000 ALTER TABLE `user_table` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
