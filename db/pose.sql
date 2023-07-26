-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pose-management
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `algorithm`
--

DROP TABLE IF EXISTS `algorithm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `algorithm` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(255) DEFAULT NULL,
                             `type` int DEFAULT NULL,
                             `sport_category` int DEFAULT NULL,
                             `file` varchar(255) DEFAULT NULL,
                             `template_id` int DEFAULT NULL,
                             `install_type` int DEFAULT NULL,
                             `uploader` int DEFAULT NULL,
                             `docs` text,
                             `example` int DEFAULT NULL,
                             `atrr` json NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm`
--

INSERT INTO `algorithm` VALUES (1,'篮球投篮算法',1,1,'1',1,1,1,'1',1,'1');

--
-- Table structure for table `auth_institution_alg`
--

DROP TABLE IF EXISTS `auth_institution_alg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_institution_alg` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `institution_id` int DEFAULT NULL COMMENT '授权机构',
                                        `auth_type` int DEFAULT NULL COMMENT '授权类型(0：算法，1：功能)',
                                        `auth_alg_id` int DEFAULT NULL COMMENT '授权ID',
                                        `auth_admin` int DEFAULT NULL COMMENT '授权人',
                                        `mark` varchar(255) DEFAULT NULL COMMENT '备注',
                                        `auth_time` datetime DEFAULT NULL COMMENT '授权时间',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='授权机构算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_institution_alg`
--

INSERT INTO `auth_institution_alg` VALUES (1,1,0,1,1,'备注','2023-07-25 00:00:00'),(2,2,0,1,1,'备注','2023-07-26 00:00:00'),(3,3,0,1,1,'备注','2023-07-26 00:00:00'),(4,1,0,1,1,'备注','2023-07-25 00:00:00'),(5,1,0,1,1,'王子健备注','2023-07-26 21:53:00'),(6,1,0,1,0,'王子健创建的','2023-07-26 23:37:52'),(7,1,0,1,0,'王子健创建的最新的','2023-07-26 23:37:52'),(11,2,0,1,0,'456','2023-07-27 00:32:44'),(12,2,0,2,0,'wangzijian','2023-07-27 00:34:13');

--
-- Table structure for table `data_set`
--

DROP TABLE IF EXISTS `data_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_set` (
                            `id` int DEFAULT NULL,
                            `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据集表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_set`
--


--
-- Table structure for table `developer`
--

DROP TABLE IF EXISTS `developer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `developer` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='开发者表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developer`
--


--
-- Table structure for table `institution`
--

DROP TABLE IF EXISTS `institution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `institution` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) DEFAULT NULL COMMENT '机构名',
                               `type` int DEFAULT NULL COMMENT '机构类型（0：培训机构，1：健身场所）',
                               `phone` varchar(255) DEFAULT NULL COMMENT '联系人电话',
                               `email` varchar(255) DEFAULT NULL COMMENT '邮箱 ',
                               `address` varchar(255) DEFAULT NULL COMMENT '地址',
                               `map` varchar(255) DEFAULT NULL COMMENT '地图位置 ',
                               `create_time` datetime DEFAULT NULL COMMENT '添加时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institution`
--

INSERT INTO `institution` VALUES (1,'北京市乐刻健身',1,'10085','123456@qq.com','北京市乐刻健身1234','123','2023-07-25 22:09:53'),(2,'润迪',1,'10085','123456@qq.com','中国','123','2023-07-25 22:09:53'),(3,'XT体育',1,'10085','123456@qq.com','中国','123','2023-07-25 22:09:53');

--
-- Table structure for table `sport_category`
--

DROP TABLE IF EXISTS `sport_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sport_category` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `type` varchar(255) DEFAULT NULL COMMENT '体育类型(0:学校体育 1:群众体育 2:竞技体育)',
                                  `name` varchar(255) DEFAULT NULL COMMENT '分类名',
                                  `creator` int DEFAULT NULL COMMENT '添加人',
                                  `mark` text COMMENT '备注',
                                  `create_time` datetime DEFAULT NULL COMMENT '授权时间 ',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='体育类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport_category`
--

INSERT INTO `sport_category` VALUES (1,'0','田径',0,'备注','2023-07-26 13:47:32'),(2,'0','铅球',0,'备注','2023-07-26 13:47:32'),(3,'0','标枪',0,'备注','2023-07-26 13:47:32'),(4,'0','跳远',0,'备注','2023-07-26 13:47:32'),(5,'0','立定跳远',0,'备注','2023-07-26 13:47:32'),(6,'1','羽毛球',0,'备注','2023-07-26 13:47:32'),(7,'1','篮球',0,'备注','2023-07-26 13:47:32'),(8,'1','游泳',0,'备注','2023-07-26 13:47:32'),(9,'2','击剑',0,'备注','2023-07-26 13:47:32'),(10,'2','拳击',0,'备注','2023-07-26 13:47:32'),(11,'2','乒乓球',0,'备注','2023-07-26 13:47:32');

--
-- Table structure for table `system_info`
--

DROP TABLE IF EXISTS `system_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_info` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_info`
--


--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `template` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `type` int DEFAULT NULL,
                        `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
                        `username` varchar(255) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        `mark` text,
                        `create_time` datetime DEFAULT NULL COMMENT '授权时间',
                        `attr` json DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,0,'王子健管理员','admin','$argon2id$v=19$m=65536,t=2,p=1$7k/vYSb0uoa/uXsxLDo0lQ$It3hw3HFGfoswOVr2U0in1BHHpcj8c1vFhTToxSn/Zc','备注','2023-07-24 22:47:20','{}'),(4,0,'123','1234','123','123','2023-07-23 18:02:37','{}'),(5,0,'123','12345','123','123','2023-07-23 18:02:37','{}'),(6,0,'王子健昵称','wangzijian123','$argon2id$v=19$m=65536,t=2,p=1$8DLavyqz+npDmF3DT2wI5g$XE/GCCbR5K7TDFmdxenuByhfMhdaFLals2APTPYtwWo','记录一下','2023-07-23 22:24:19','{}');

--
-- Table structure for table `wechat`
--

DROP TABLE IF EXISTS `wechat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wechat` (
    `id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小程序表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wechat`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-27  1:21:30
