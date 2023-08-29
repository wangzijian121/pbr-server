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
CREATE TABLE `algorithm`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `name`           varchar(255) DEFAULT NULL,
    `type`           int          DEFAULT NULL COMMENT '类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
    `sport_category` int          DEFAULT NULL,
    `template_id`    int          DEFAULT NULL,
    `install_type`   int          DEFAULT NULL COMMENT '部署方式（0：云端部署 1：本地部署）',
    `uploader`       int          DEFAULT NULL,
    `file`           varchar(255) DEFAULT NULL,
    `docs`           text,
    `example`        text,
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm`
--

INSERT INTO `algorithm`
VALUES (1, '足球算法1', 0, 1, 1, 1, 1, 'ea', '文档', '这是一个案例', '2023-08-05 23:04:09'),
       (2, '足球算法2', 0, 1, 1, 1, 1, 'ea', '文档', '这是一个案例', '2023-08-05 23:04:09'),
       (3, '足球算法2', 1, 1, 1, 1, 1, 'ea', '文档', '这是一个案例', '2023-08-06 15:43:11'),
       (5, '王子健算法', 0, 1, 1, 1, 1, 'ea', '文档', '这是一个案例', '2023-08-06 15:43:11');

--
-- Table structure for table `auth_institution_alg`
--

DROP TABLE IF EXISTS `auth_institution_alg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_institution_alg`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `institution_id` int          DEFAULT NULL COMMENT '授权机构',
    `auth_type`      int          DEFAULT NULL COMMENT '授权类型(0：算法，1：功能)',
    `auth_alg_id`    int          DEFAULT NULL COMMENT '授权ID',
    `auth_admin`     int          DEFAULT NULL COMMENT '授权人',
    `mark`           varchar(255) DEFAULT NULL COMMENT '备注',
    `auth_time`      datetime     DEFAULT NULL COMMENT '授权时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='授权机构算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_institution_alg`
--

INSERT INTO `auth_institution_alg`
VALUES (1, 1, 0, 1, 1, '备注', '2023-07-25 00:00:00'),
       (2, 2, 0, 1, 1, '备注', '2023-07-26 00:00:00'),
       (3, 3, 0, 1, 1, '备注', '2023-07-26 00:00:00'),
       (4, 1, 0, 1, 1, '备注', '2023-07-25 00:00:00'),
       (5, 1, 0, 1, 1, '王子健备注', '2023-07-26 21:53:00'),
       (6, 1, 0, 1, 0, '王子健创建的', '2023-07-26 23:37:52'),
       (7, 1, 0, 1, 0, '王子健创建的最新的', '2023-07-26 23:37:52'),
       (11, 2, 0, 1, 0, '456', '2023-07-27 00:32:44'),
       (12, 3, 0, 3, 0, '这是我新建的授权', '2023-07-27 10:09:22');

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charge`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `type`           int      DEFAULT NULL COMMENT '收费类型（0免费 1按次付费 2按月付费 3按季付费 4 按年付费 5永久） ',
    `institution_id` int      DEFAULT NULL COMMENT '机构ID',
    `charge_time`    datetime DEFAULT NULL COMMENT '收款时间时间',
    `confirm_people` int      DEFAULT NULL COMMENT '确认人',
    `confirm_time`   datetime DEFAULT NULL COMMENT '到账确认时间',
    `status`         int      DEFAULT NULL COMMENT '状态（0：已到账，1：未到账）',
    `mark`           text COMMENT '备注信息',
    `create_time`    datetime DEFAULT NULL COMMENT '录入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='付费表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge`
--

INSERT INTO `charge`
VALUES (1, 0, 2, '2023-07-28 09:27:06', 1, '2023-07-28 09:27:06', 0, '备注', '2023-07-28 09:27:06');

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commission`
(
    `id`          int      NOT NULL AUTO_INCREMENT,
    `review_id`   int      NOT NULL COMMENT '佣金项ID',
    `money`       double   NOT NULL COMMENT '佣金金额',
    `status`      int      NOT NULL COMMENT '状态(0:未付款，1：已付款)',
    `mark`        varchar(255) DEFAULT NULL COMMENT '备注',
    `create_time` datetime NOT NULL COMMENT '添加时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='佣金发放表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

INSERT INTO `commission`
VALUES (1, 1, 100.5, 1, '备注1', '2021-01-01 10:00:00'),
       (2, 2, 200.75, 1, '备注1', '2021-02-02 12:30:00'),
       (3, 3, 150.25, 0, '备注1', '2021-03-03 15:45:00'),
       (4, 4, 300, 1, '备注1', '2021-04-04 18:15:00'),
       (5, 5, 75.5, 0, '备注1', '2021-05-05 09:30:00'),
       (6, 6, 250.25, 1, '备注1', '2021-06-06 14:20:00'),
       (7, 7, 180.75, 0, '备注1', '2021-07-07 16:45:00'),
       (8, 8, 400, 1, '备注1', '2021-08-08 19:00:00'),
       (9, 9, 120.5, 1, '备注1', '2021-09-09 11:10:00'),
       (11, 12, 123.45, 0, '备注', '2023-08-01 21:45:35');

--
-- Table structure for table `data_set`
--

DROP TABLE IF EXISTS `data_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_set`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `name`           varchar(255) DEFAULT NULL COMMENT '数据集名',
    `type`           int          DEFAULT NULL COMMENT '数据集类型（0:普通数据集 ,1:专用数据集）',
    `sport_category` int          DEFAULT NULL COMMENT '支持的体育类型',
    `file`           varchar(255) DEFAULT NULL COMMENT '文件',
    `demo`           text COMMENT '数据集样例',
    `install_type`   int          DEFAULT NULL COMMENT '部署方式（0：云端部署 1：本地部署）',
    `uploader`       int          DEFAULT NULL COMMENT '上传人',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据集表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_set`
--

INSERT INTO `data_set`
VALUES (1, '足球数据集', 1, 1, '4b4901b8-7af4-444a-b044-3067902cfaf2', 'demo', 1, 1, '2023-08-05 23:57:51');

--
-- Table structure for table `developer_review`
--

DROP TABLE IF EXISTS `developer_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `developer_review`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `commit_name`  varchar(255) DEFAULT NULL COMMENT '提交名',
    `developer_id` int          DEFAULT NULL COMMENT '开发者',
    `type`         int          DEFAULT NULL COMMENT '数据类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
    `file`         varchar(255) DEFAULT NULL COMMENT '算法或数据集文件',
    `demo`         varchar(255) DEFAULT NULL COMMENT '数据集样例',
    `status`       int          DEFAULT NULL COMMENT '审核状态(0:未审核 1：审核通过 2：审核未通过)',
    `mark`         varchar(255) DEFAULT NULL COMMENT '备注',
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='开发者审核表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developer_review`
--

INSERT INTO `developer_review`
VALUES (1, '王子健提交的算法', 6, 0, '4b4901b8-7af4-444a-b044-3067902cfaf2', '1234', 2, '算法运行失败', '2023-07-31 20:43:54'),
       (2, '王子健提交的算法2', 6, 2, '8fef3000-edf4-4944-809e-f580565879f4', '1234', 2, '备注', '2023-08-03 22:09:54');

--
-- Table structure for table `institution`
--

DROP TABLE IF EXISTS `institution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `institution`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL COMMENT '机构名',
    `type`        int          DEFAULT NULL COMMENT '机构类型（0：培训机构，1：健身场所）',
    `phone`       varchar(255) DEFAULT NULL COMMENT '联系人电话',
    `email`       varchar(255) DEFAULT NULL COMMENT '邮箱 ',
    `address`     varchar(255) DEFAULT NULL COMMENT '地址',
    `map`         varchar(255) DEFAULT NULL COMMENT '地图位置 ',
    `create_time` datetime     DEFAULT NULL COMMENT '添加时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institution`
--

INSERT INTO `institution`
VALUES (1, 'lekeJS', 0, '10086', '123@qq.com', 'beijing', 'map', '2023-07-27 13:50:06'),
       (2, '润迪2', 1, '10085', '123456@qq.com', '中国', '123', '2023-07-25 22:09:53'),
       (3, 'XT体育', 1, '10085', '123456@qq.com', '中国', '123', '2023-07-25 22:09:53');

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `full_name`   varchar(255) DEFAULT NULL,
    `alias`       varchar(255) DEFAULT NULL,
    `suffix`      varchar(255) DEFAULT NULL COMMENT '后缀',
    `size`        bigint       DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表元数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources`
VALUES (19, '特征识别平台.zip', 'a3bb355f-ade5-4fec-8855-021cb7c5f7e0', 'zip', 4975585, '2023-08-04 00:24:21',
        '2023-08-04 00:24:21'),
       (20, '特征识别平台.zip', 'a2279d7d-f6d0-424f-891b-34a892680d35', 'zip', 4975585, '2023-08-04 22:22:56',
        '2023-08-04 22:22:56'),
       (21, '特征识别平台.zip', 'f91af1f9-7452-4cb4-847c-978b8bd6d2ae', 'zip', 4975585, '2023-08-04 22:23:29',
        '2023-08-04 22:23:29'),
       (22, '特征识别平台.zip', '8fef3000-edf4-4944-809e-f580565879f4', 'zip', 4975585, '2023-08-05 17:33:19',
        '2023-08-05 17:33:19'),
       (23, 'DG5501488_x64.ZIP', '1cbee4af-507a-4102-9c24-22b4c0911c98', 'ZIP', 39449361, '2023-08-05 17:58:33',
        '2023-08-05 17:58:33'),
       (24, '特征识别平台.zip', '879fbbe5-49f0-4a9f-8fbf-602fc8537ced', 'zip', 4975585, '2023-08-05 17:58:37',
        '2023-08-05 17:58:37');

--
-- Table structure for table `sport_category`
--

DROP TABLE IF EXISTS `sport_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sport_category`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `type`        varchar(255) DEFAULT NULL COMMENT '体育类型(0:学校体育 1:群众体育 2:竞技体育)',
    `name`        varchar(255) DEFAULT NULL COMMENT '分类名',
    `user_id`     int          DEFAULT NULL COMMENT '添加人',
    `mark`        text COMMENT '备注',
    `create_time` datetime     DEFAULT NULL COMMENT '授权时间 ',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='体育类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport_category`
--

INSERT INTO `sport_category`
VALUES (1, '0', '田径2', 0, '备注', '2023-07-26 13:47:32'),
       (2, '0', '铅球', 0, '备注', '2023-07-26 13:47:32'),
       (3, '0', '标枪', 0, '备注', '2023-07-26 13:47:32'),
       (4, '0', '跳远', 0, '备注', '2023-07-26 13:47:32'),
       (5, '0', '立定跳远', 0, '备注', '2023-07-26 13:47:32'),
       (6, '1', '羽毛球', 0, '备注', '2023-07-26 13:47:32'),
       (7, '1', '篮球', 0, '备注', '2023-07-26 13:47:32'),
       (8, '1', '游泳', 0, '备注', '2023-07-26 13:47:32'),
       (9, '2', '击剑', 0, '备注', '2023-07-26 13:47:32'),
       (11, '2', '乒乓球', 0, '备注', '2023-07-26 13:47:32'),
       (12, '0', '网球2', 1, '网球添加2', '2023-07-27 15:12:28'),
       (13, '1', '铅球', 1, '汪汪汪', '2023-07-27 15:26:35'),
       (14, '0', 'wang', 1, '备注', '2023-07-27 15:49:14'),
       (15, '0', 'jm测试', 1, '备注', '2023-07-27 15:49:14'),
       (16, '0', '测试', 1, '测试', '2023-07-27 20:50:30'),
       (17, '0', '更新后', 1, '更新后', '2023-07-27 20:50:24');

--
-- Table structure for table `system_info`
--

DROP TABLE IF EXISTS `system_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_info`
(
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
CREATE TABLE `template`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL COMMENT '模板名',
    `content`     json         DEFAULT NULL COMMENT '模板内容',
    `create_time` datetime     DEFAULT NULL,
    `mark`        text COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

INSERT INTO `template`
VALUES (1, '模板2', '{
  \"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}', '2023-07-28 14:37:49',
        '修改后备注');

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`          int NOT NULL AUTO_INCREMENT,
    `type`        int          DEFAULT NULL,
    `nickname`    varchar(255) DEFAULT NULL COMMENT '昵称',
    `username`    varchar(255) DEFAULT NULL,
    `password`    varchar(255) DEFAULT NULL,
    `mark`        text,
    `create_time` datetime     DEFAULT NULL COMMENT '授权时间',
    `attr`        json         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user`
VALUES (1, 0, '王子健', 'root',
        '$argon2id$v=19$m=65536,t=2,p=1$1VdMY/Yxaal5oXNyd10g5A$jNxdp7UHCVNgp8M5EV9lkRi15ZheMaRbSqKGpWuNCbI', '密码123456',
        '2023-07-27 15:02:54', '{}'),
       (4, 0, '程序员小王', 'wangzijian121',
        '$argon2id$v=19$m=65536,t=2,p=1$Qpo5pGW3a20epFq6MXHbaA$out6LUQ8s7AcFi1USfCMQ4cfNQ9NZczA9Q4QRE9/Ht4', '小明',
        '2023-07-27 15:08:12', '{}'),
       (6, 0, '程序员小王', 'wangzijian123',
        '$argon2id$v=19$m=65536,t=2,p=1$8DLavyqz+npDmF3DT2wI5g$XE/GCCbR5K7TDFmdxenuByhfMhdaFLals2APTPYtwWo', '开发者',
        '2023-07-23 22:24:19', '{}'),
       (12, 0, '程序员小王123', 'wangzijian',
        '$argon2id$v=19$m=65536,t=2,p=1$foqzw2Z+4eZyagRxNCzDMA$mO+e27FQfdYUlCTX5kZsFdUQqDxYeKb076uKv5o/oiM', '小明',
        '2023-07-27 15:08:12', '{}');

--
-- Table structure for table `wechat`
--

DROP TABLE IF EXISTS `wechat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wechat`
(
    `id`             int NOT NULL AUTO_INCREMENT,
    `wechat_id`      varchar(255) DEFAULT NULL COMMENT '小程序ID',
    `name`           varchar(255) DEFAULT NULL COMMENT '小程序名',
    `institution_id` int          DEFAULT NULL COMMENT '机构ID',
    `status`         int          DEFAULT NULL COMMENT '进度(0已部署，1审核中)',
    `mark`           text COMMENT '备注',
    `create_time`    datetime     DEFAULT NULL COMMENT '授权时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小程序表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wechat`
--

INSERT INTO `wechat`
VALUES (1, '1289371982739123', '润迪体育助手', 1, 0, '润迪开通的小助手', '2023-07-28 09:49:59'),
       (2, '321546', '王子健小程序', 1, 1, '备注', '2023-07-28 11:31:30');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-06 20:03:52
