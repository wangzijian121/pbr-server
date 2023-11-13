-- MySQL dump 10.13  Distrib 5.7.34, for Linux (x86_64)
--
-- Host: localhost    Database: pose-management-rel
-- ------------------------------------------------------
-- Server version	5.7.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `algorithm`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `name`           varchar(255) DEFAULT NULL,
    `type`           int(11) DEFAULT NULL COMMENT '类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
    `sport_category` int(11) DEFAULT NULL,
    `template_id`    int(11) DEFAULT NULL,
    `install_type`   int(11) DEFAULT NULL COMMENT '部署方式（0：云端部署 1：本地部署）',
    `uploader`       int(11) DEFAULT NULL,
    `file`           varchar(255) DEFAULT NULL,
    `docs`           text,
    `example`        text,
    `url`            varchar(255) DEFAULT NULL COMMENT '算法请求地址',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COMMENT='算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm`
--

LOCK
TABLES `algorithm` WRITE;
/*!40000 ALTER TABLE `algorithm` DISABLE KEYS */;
INSERT INTO `algorithm`
VALUES (1, '足球射门姿势算法', 0, 14, 1, 1, 1, '5cd238fb-5b5f-4ff3-9a6b-56e0ac1c2288', '文档', '这是一个案例',
        'https://wangzijian.top/score/', '2023-08-14 17:47:57'),
       (3, '游泳姿势优化算法', 1, 8, 1, 1, 1, '5cd238fb-5b5f-4ff3-9a6b-56e0ac1c2288', '文档', '这是一个案例',
        'https://wangzijian.top/score/', '2023-08-14 17:45:11'),
       (10, '田径起跑姿势优化算法', 1, 1, 1, 2, 1, '5cd238fb-5b5f-4ff3-9a6b-56e0ac1c2288', '文档', '样例',
        'https://wangzijian.top/score/', '2023-10-19 22:51:50'),
       (14, '足球专用犯规识别算法', 0, 14, 1, 0, 1, '5cd238fb-5b5f-4ff3-9a6b-56e0ac1c2288', '文档', '文档demo',
        'https://wangzijian.top/score/', '2023-08-14 02:27:31'),
       (15, '篮球手部识别算法', 1, 7, 2, 0, 1, '6a4e7644-5f96-4b78-9f34-4dde2ba56f29', '文档', '样例demo',
        'https://wangzijian.top/score/', '2023-10-19 22:50:34'),
       (16, '乒乓球角度识别算法', 1, 11, 4, 0, 12, '5fdd8f88-d1aa-4217-97aa-80734e9a7d60', '文档', 'demo',
        'https://wangzijian.top/score/', '2023-10-19 22:50:16'),
       (17, '铅球算法', 0, 2, 1, 0, 1, '6888c188-2cc8-4d8d-af64-fa2f2e3d2705', '123', '456',
        'https://wangzijian.top/score/', '2023-10-19 22:52:02'),
       (20, '跳远距离识别', 1, 5, 1, 0, 1, '0d3dd12c-b768-43ff-bd30-a8aeeee7c0f7', '1234', '1234',
        'https://wangzijian.top/score/', '2023-10-19 22:51:26'),
       (21, '标枪弧度算法', 0, 21, 1, 1, 1, 'f36b39e3-30ac-45ab-a237-6a338c06eddd', '标枪弧度算法', '标枪弧度算法',
        'https://wangzijian.top/score/', '2023-09-21 15:54:06'),
       (22, '标枪算法', 0, 21, 1, 1, 1, 'b7aab323-9de8-4a36-a513-a35da6ace54a', '如何使用', './bin/start.sh',
        'https://wangzijian.top/score/', '2023-09-22 22:31:23'),
       (24, '高尔夫球道算法', 0, 22, 1, 1, 1, '5cd238fb-5b5f-4ff3-9a6b-56e0ac1c2288', '算法文档', 'demo',
        'https://wangzijian.top/score/', '2023-09-25 01:26:11');
/*!40000 ALTER TABLE `algorithm` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `auth_institution_alg`
--

DROP TABLE IF EXISTS `auth_institution_alg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_institution_alg`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `institution_id` int(11) DEFAULT NULL COMMENT '授权机构',
    `auth_type`      int(11) DEFAULT NULL COMMENT '授权类型(0：算法，1：功能)',
    `auth_alg_id`    int(11) DEFAULT NULL COMMENT '授权ID',
    `auth_admin`     int(11) DEFAULT NULL COMMENT '授权人',
    `mark`           varchar(255) DEFAULT NULL COMMENT '备注',
    `auth_time`      datetime     DEFAULT NULL COMMENT '授权时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='授权机构算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_institution_alg`
--

LOCK
TABLES `auth_institution_alg` WRITE;
/*!40000 ALTER TABLE `auth_institution_alg` DISABLE KEYS */;
INSERT INTO `auth_institution_alg`
VALUES (1, 201, 0, 1, 1, '备注', '2023-08-14 08:19:49'),
       (29, 201, 0, 20, 1, '备注', '2023-08-31 00:09:19'),
       (30, 201, 0, 3, 1, '备注', '2023-09-05 21:38:40'),
       (31, 201, 0, 16, 1, '备注', '2023-09-05 21:38:40'),
       (32, 201, 0, 20, 1, '备注', '2023-09-05 21:38:40'),
       (33, 201, 0, 14, 1, '备注', '2023-09-05 21:38:40'),
       (34, 201, 0, 15, 1, '备注', '2023-09-05 21:38:40'),
       (36, 201, 0, 22, 1, '备注', '2023-09-22 22:40:26'),
       (39, 201, 0, 21, 1, '备注', '2023-09-25 01:09:39'),
       (40, 201, 0, 24, 1, '备注', '2023-09-25 01:34:07');
/*!40000 ALTER TABLE `auth_institution_alg` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `charge`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `type`           int(11) DEFAULT NULL COMMENT '收费类型（0免费 1按次付费 2按月付费 3按季付费 4 按年付费 5永久） ',
    `institution_id` int(11) DEFAULT NULL COMMENT '机构ID',
    `charge_time`    datetime DEFAULT NULL COMMENT '收款时间时间',
    `confirm_people` int(11) DEFAULT NULL COMMENT '确认人',
    `confirm_time`   datetime DEFAULT NULL COMMENT '到账确认时间',
    `status`         int(11) DEFAULT NULL COMMENT '状态（0：已到账，1：未到账）',
    `mark`           text COMMENT '备注信息',
    `create_time`    datetime DEFAULT NULL COMMENT '录入时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='付费表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge`
--

LOCK
TABLES `charge` WRITE;
/*!40000 ALTER TABLE `charge` DISABLE KEYS */;
INSERT INTO `charge`
VALUES (7, 0, 17, '2023-08-30 23:35:02', 1, '2023-08-30 23:35:02', 1, '123', '2023-08-30 23:34:45'),
       (8, 0, 17, '2023-08-30 23:35:29', 1, '2023-08-30 23:35:29', 1, NULL, '2023-08-30 23:35:16'),
       (9, 5, 2, '2023-09-22 22:40:10', 1, '2023-09-22 22:40:10', 1, NULL, '2023-09-22 22:39:58'),
       (10, 5, 200, '2023-09-24 21:14:50', 1, '2023-09-24 21:14:50', 1, NULL, '2023-09-24 21:14:38'),
       (11, 5, 201, '2023-09-25 01:29:57', 1, '2023-09-25 01:29:57', 1, NULL, '2023-09-25 01:29:46');
/*!40000 ALTER TABLE `charge` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commission`
(
    `id`                    int(11) NOT NULL AUTO_INCREMENT,
    `review_id`             int(11) NOT NULL COMMENT '佣金项ID',
    `money`                 double   NOT NULL COMMENT '佣金金额',
    `status`                int(11) NOT NULL COMMENT '状态(0:未付款，1：已付款)',
    `mark`                  text COMMENT '备注',
    `screenshot_of_payment` varchar(255) DEFAULT NULL COMMENT '付款凭据',
    `create_time`           datetime NOT NULL COMMENT '添加时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='佣金发放表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

LOCK
TABLES `commission` WRITE;
/*!40000 ALTER TABLE `commission` DISABLE KEYS */;
INSERT INTO `commission`
VALUES (24, 62, 1000, 1, '支付宝10086', '888ca399-fe20-48bc-933d-8d0e44c78401', '2023-10-19 11:14:11'),
       (26, 48, 7500, 1, '已付款到支付宝10086', '888ca399-fe20-48bc-933d-8d0e44c78401', '2023-10-19 11:14:19'),
       (27, 54, 8800, 1, '', '888ca399-fe20-48bc-933d-8d0e44c78401', '2023-10-19 11:14:26'),
       (28, 58, 700, 1, '', '888ca399-fe20-48bc-933d-8d0e44c78401', '2023-10-19 11:14:45');
/*!40000 ALTER TABLE `commission` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `data_set`
--

DROP TABLE IF EXISTS `data_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_set`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `name`           varchar(255) DEFAULT NULL COMMENT '数据集名',
    `type`           int(11) DEFAULT NULL COMMENT '数据集类型（0:普通数据集 ,1:专用数据集）',
    `sport_category` int(11) DEFAULT NULL COMMENT '支持的体育类型',
    `file`           varchar(255) DEFAULT NULL COMMENT '文件',
    `demo`           text COMMENT '数据集样例',
    `install_type`   int(11) DEFAULT NULL COMMENT '部署方式（0：云端部署 1：本地部署）',
    `uploader`       int(11) DEFAULT NULL COMMENT '上传人',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='数据集表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_set`
--

LOCK
TABLES `data_set` WRITE;
/*!40000 ALTER TABLE `data_set` DISABLE KEYS */;
INSERT INTO `data_set`
VALUES (1, '足球数据集', 1, 1, '23f327e7-e9ca-4966-aa26-dcb24f311ad5', 'demo', 1, 1, '2023-10-19 21:38:09'),
       (5, '篮球投篮动作数据集', 0, 7, '5c4685d7-325e-4735-addf-868b1cb3dc9b', '123', 0, 1, '2023-10-19 22:52:14'),
       (6, '跳远数据集', 1, 5, '5059b39d-73d1-4d06-bda3-0b3baca547df', '123', 0, 1, '2023-10-19 21:40:29'),
       (7, '乒乓球数据集', 0, 11, '4b5585c6-b122-4165-928f-b9c87f37c444', '乒乓球数据集', 0, 1, '2023-09-21 15:52:35');
/*!40000 ALTER TABLE `data_set` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `developer_review`
--

DROP TABLE IF EXISTS `developer_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `developer_review`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `commit_name`  varchar(255) DEFAULT NULL COMMENT '提交名',
    `sport_type`   varchar(255) DEFAULT NULL COMMENT '支持体育类型',
    `developer_id` int(11) DEFAULT NULL COMMENT '开发者',
    `type`         int(11) DEFAULT NULL COMMENT '数据类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
    `file`         varchar(255) DEFAULT NULL COMMENT '算法或数据集文件',
    `docs`         text COMMENT '文档',
    `demo`         varchar(255) DEFAULT NULL COMMENT '数据集样例',
    `mark`         varchar(255) DEFAULT NULL COMMENT '备注',
    `status`       int(11) DEFAULT NULL COMMENT '审核状态（0：未提交 1：已提交未审核 2： 已提交未通过 3： 已提交已通过）',
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COMMENT='开发者审核表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developer_review`
--

LOCK
TABLES `developer_review` WRITE;
/*!40000 ALTER TABLE `developer_review` DISABLE KEYS */;
INSERT INTO `developer_review`
VALUES (24, '羽毛球羽翼飞行结构数据集', '羽毛球', 13, 3, '1fa01764-82ec-42ed-b1bb-1a72604bae3f', '文档', '这是一个案例',
        '{\"id\":24,\"createTime\":\"2023-08-24T23:35:24.892Z\",\"status\":2,\"mark\":\"123\"}', 2,
        '2023-08-21 21:57:43'),
       (41, '篮球起跳算法', '篮球', 13, 0, '1fa01764-82ec-42ed-b1bb-1a72604bae3f', '水电费', '这是一个案例',
        '{\"id\":41,\"createTime\":\"2023-08-25T00:08:46.673Z\",\"status\":2,\"mark\":\"123\"}', 2,
        '2023-08-25 08:08:10'),
       (42, '足球运球算法', '足球', 13, 0, 'b2602fcb-1e9a-48d6-bc30-83e63ca53cca', '算法文档说明', 'demo',
        '{\"id\":42,\"createTime\":\"2023-08-25T19:39:48.268Z\",\"status\":3,\"mark\":\"恭喜你通过了\"}', 3,
        '2023-08-26 03:38:52'),
       (43, '足球运球数据集', '足球', 13, 2, 'a0b7a696-8230-4837-ac50-c4f0ef80bb12', '文档说明', 'demo',
        '{\"id\":43,\"createTime\":\"2023-08-25T19:42:50.857Z\",\"status\":2,\"mark\":\"数据集数据格式不对\"}', 2,
        '2023-08-26 03:41:52'),
       (44, '羽毛球拍打分析算法', '羽毛球', 13, 1, '276ccd67-800c-419f-9033-3f4c0663877d',
        '算法步骤\n选择一个基准元素，通常选择第一个元素作为基准元素。\n将序列分为两部分，左边部分都小于基准元素，右边部分都大于基准元素。\n对左右两部分递归执行步骤1和步骤2，直到序列长度为1。',
        'def quick_sort(arr):\n    if len(arr) <= 1:\n        return arr\n    pivot = arr[0]\n    left = [x for x in arr[1:] if x < pivot]\n    right = [x for x in arr[1:] if x >= pivot]\n    return quick_sort(left) + [pivot] + quick_sort(right)\n',
        '{\"id\":44,\"createTime\":\"2023-08-25T20:13:46.823Z\",\"status\":3,\"mark\":\"恭喜你通过了\"}', 3,
        '2023-08-26 04:10:35'),
       (45, '羽毛球拍打算法数据集', '羽毛球', 13, 3, 'b400d278-0612-42e7-b5da-203f0b5e8cd8', '文档', 'demo',
        '{\"id\":45,\"createTime\":\"2023-08-25T20:14:41.642Z\",\"status\":2,\"mark\":\"对不起，格式不对\"}', 2,
        '2023-08-26 04:14:04'),
       (48, '乒乓球挥拍算法', '乒乓球', 13, 1, 'f8ac5980-1e50-4ab3-a169-b2711eacbca1', 'docs', 'demo',
        '{\"id\":48,\"createTime\":\"2023-08-25T20:55:17.779Z\",\"status\":3,\"mark\":\"恭喜 ~你算法通过了！\"}', 3,
        '2023-08-26 04:53:40'),
       (49, '乒乓球挥拍数据集', '乒乓球', 13, 3, 'dad4a009-2e9b-4797-8079-07339eddfe62', 'docs', 'demo',
        '{\"id\":49,\"createTime\":\"2023-08-25T20:55:40.123Z\",\"status\":2,\"mark\":\"对不起，数据格式不对！\"}', 2,
        '2023-08-26 04:54:15'),
       (54, '标枪算法', '标枪', 13, 0, '4051e4db-0dcd-48a7-b113-7036e2aabf06', '部署后操作。。。', '部署启动demo',
        '{\"id\":54,\"createTime\":\"2023-09-22T14:11:41.221Z\",\"status\":3,\"mark\":\"恭喜您通过\"}', 3,
        '2023-09-22 22:09:47'),
       (55, '标枪数据集', '标枪', 13, 2, 'b2dc060b-a8a7-4f38-874c-4efeeb4678f5', '数据集', '数据集',
        '{\"id\":55,\"createTime\":\"2023-09-22T14:11:48.239Z\",\"status\":3,\"mark\":\"恭喜您通过了\"}', 3,
        '2023-09-22 22:11:11'),
       (56, '篮球动作识别算法', '篮球', 13, 0, 'f0a49733-c24e-41bb-99ce-0105fa5cc965', '这样部署', './bin/start.sh ',
        '{\"id\":56,\"createTime\":\"2023-09-24T13:09:15.759Z\",\"status\":3,\"mark\":\"通过\"}', 3,
        '2023-09-24 21:00:41'),
       (57, '篮球动作识别数据集', '篮球', 13, 2, '048696df-95ab-4058-9f66-a2b5f2e803f6', '解压后使用', '{\"\",\"\"}',
        '{\"id\":57,\"createTime\":\"2023-09-24T13:09:05.739Z\",\"status\":3,\"mark\":\"通过\"}', 3,
        '2023-09-24 21:08:14'),
       (58, '高尔夫球球道算法', '高尔夫', 13, 0, '46a72ce1-dd89-486e-a7c3-16c4cc913f3c', '如何部署。。。。如何使用',
        './bin/start.sh', '{\"id\":58,\"createTime\":\"2023-09-24T17:23:09.894Z\",\"status\":3,\"mark\":\"通过\"}', 3,
        '2023-09-25 01:20:45'),
       (59, '高尔夫球球道数据集', '高尔夫', 13, 2, 'cda2709c-bc91-499a-ac62-ad5773c2fe6b', '数据集', '数据集',
        '{\"id\":59,\"createTime\":\"2023-09-24T17:23:05.210Z\",\"status\":3,\"mark\":\"通过\"}', 3,
        '2023-09-25 01:22:16'),
       (60, '测试算法', '铁饼', 13, 0, 'e61afe3c-5eb0-430d-9a7b-5791d73ee40e', '123', '123',
        '{\"id\":60,\"createTime\":\"2023-10-19T15:21:34.643Z\",\"status\":2,\"mark\":\"对不起\"}', 2,
        '2023-10-19 23:21:04'),
       (61, '标枪距离算法', '标枪', 12, 0, '49f77bee-599b-4204-a31a-b31639cbf792', '这里填写算法文档',
        '这里填写算法样例', NULL, 1, '2023-10-22 18:41:39'),
       (62, '123', '123', 12, 0, '9c23bc4b-750d-4a49-8442-f83bf2fd7971', 'wangzijain', 'test', NULL, 1,
        '2023-10-30 10:40:12'),
       (63, '12345', '游泳', 12, 0, '9b60a14f-56a6-46ea-b82f-f33994db1360', '1', '3', NULL, 1, '2023-11-04 22:27:10'),
       (64, '666', '羽毛球', 12, 2, '906737d7-bb1d-476d-8d7e-5cdc89a44708', '444', '555', NULL, 1,
        '2023-11-04 22:29:55'),
       (65, '足球算法', '足球', 12, 0, 'd4759e88-81fb-4b4e-a996-ae7abd8ec76c', '12', '12', NULL, 1,
        '2023-11-05 21:42:09');
/*!40000 ALTER TABLE `developer_review` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `institution`
--

DROP TABLE IF EXISTS `institution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institution`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL COMMENT '机构名',
    `type`        int(11) DEFAULT NULL COMMENT '机构类型（0：培训机构，1：健身场所）',
    `phone`       varchar(255) DEFAULT NULL COMMENT '联系人电话',
    `email`       varchar(255) DEFAULT NULL COMMENT '邮箱 ',
    `address`     varchar(255) DEFAULT NULL COMMENT '地址',
    `map`         text COMMENT '地图位置 ',
    `create_time` datetime     DEFAULT NULL COMMENT '添加时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8mb4 COMMENT='机构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institution`
--

LOCK
TABLES `institution` WRITE;
/*!40000 ALTER TABLE `institution` DISABLE KEYS */;
INSERT INTO `institution`
VALUES (1, '乐刻健身', 2, '15089764365', '15089764365@163.com', '哈尔滨市道外区体育馆道外区南直路88号',
        'https://map.baidu.com/poi/%E5%93%88%E5%B0%94%E6%BB%A8%E4%BD%93%E8%82%B2%E5%AD%A6%E9%99%A2/@14102582.756673405,5712157.242628686,17.38z?uid=2fff5b7869fe25d72b3a9f13&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl',
        '2023-10-19 11:46:22'),
       (2, '润迪', 1, '15089764320', '15089764320@163.com', '哈尔滨市香坊区体育场香坊区哈平路112号',
        'https://map.baidu.com/poi/%E5%93%88%E5%B0%94%E6%BB%A8%E4%BD%93%E8%82%B2%E5%AD%A6%E9%99%A2/@14102582.756673405,5712157.242628686,17.38z?uid=2fff5b7869fe25d72b3a9f13&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl',
        '2023-10-19 11:46:14'),
       (3, 'XT体育', 1, '13890756423', '13890756423@163.com', '哈尔滨市香坊区体育场香坊区哈广路566号',
        'https://map.baidu.com/poi/%E5%93%88%E5%B0%94%E6%BB%A8%E4%BD%93%E8%82%B2%E5%AD%A6%E9%99%A2/@14102582.756673405,5712157.242628686,17.38z?uid=2fff5b7869fe25d72b3a9f13&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl',
        '2023-10-19 11:46:31'),
       (198, '哈尔滨体育俱乐部', 0, '13456789032', '13456789032@163.com',
        '黑龙江省哈尔滨市呼兰区利民西六大街学院路950号哈尔滨信息工程学院东侧',
        'https://map.baidu.com/poi/%E6%8C%91%E6%88%98%E8%80%85%E5%B0%84%E5%87%BB%E4%BF%B1%E4%B9%90%E9%83%A8/@14083751.53,5730661.98,12z?uid=723286a1924ee2143c9a5e8f&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl',
        '2023-10-19 11:34:12'),
       (201, '子健体育俱乐部', 0, '18345670986', '18345670986@163.com', '哈尔滨市松北区体育公园松北区中兴大街356号',
        'https://map.baidu.com/poi/%E6%8C%91%E6%88%98%E8%80%85%E5%B0%84%E5%87%BB%E4%BF%B1%E4%B9%90%E9%83%A8/@14083751.53,5730661.98,12z?uid=723286a1924ee2143c9a5e8f&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl',
        '2023-10-19 11:34:47');
/*!40000 ALTER TABLE `institution` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `user_id`      int(11) NOT NULL COMMENT '用户ID',
    `algorithm_id` int(11) NOT NULL COMMENT '算法ID',
    `result`       json     DEFAULT NULL COMMENT '识别结果',
    `create_time`  datetime NOT NULL COMMENT '创建时间',
    `update_time`  datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='识别报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK
TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `full_name`   varchar(255) DEFAULT NULL,
    `alias`       varchar(255) DEFAULT NULL,
    `suffix`      varchar(255) DEFAULT NULL COMMENT '后缀',
    `size`        bigint(20) DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8mb4 COMMENT='资源表元数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK
TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources`
VALUES (19, '特征识别平台.zip', '4b4901b8-7af4-444a-b044-3067902cfaf2', 'zip', 4975585, '2023-08-04 00:24:21',
        '2023-08-04 00:24:21'),
       (20, '特征识别平台.zip', 'a2279d7d-f6d0-424f-891b-34a892680d35', 'zip', 4975585, '2023-08-04 22:22:56',
        '2023-08-04 22:22:56'),
       (21, '特征识别平台.zip', 'f91af1f9-7452-4cb4-847c-978b8bd6d2ae', 'zip', 4975585, '2023-08-04 22:23:29',
        '2023-08-04 22:23:29'),
       (22, '特征识别平台.zip', '8fef3000-edf4-4944-809e-f580565879f4', 'zip', 4975585, '2023-08-05 17:33:19',
        '2023-08-05 17:33:19'),
       (23, 'DG5501488_x64.ZIP', '1cbee4af-507a-4102-9c24-22b4c0911c98', 'ZIP', 39449361, '2023-08-05 17:58:33',
        '2023-08-05 17:58:33'),
       (24, '特征识别平台.zip', '879fbbe5-49f0-4a9f-8fbf-602fc8537ced', 'zip', 4975585, '2023-08-08 17:58:37',
        '2023-08-05 17:58:37'),
       (26, '2022_s12.zip', '3d61be63-3c33-4347-bb25-a35ab0650548', 'zip', 2539566, '2023-08-11 22:06:44',
        '2023-08-11 22:06:44'),
       (27, '2022_s12.zip', '1a682bda-31d1-4844-8869-ef3af9fd9c2e', 'zip', 2539566, '2023-08-11 22:27:06',
        '2023-08-11 22:27:06'),
       (28, '2022_s12.zip', 'f1107029-e73d-416b-bfef-0f6ca2d2aee1', 'zip', 2539566, '2023-08-11 22:27:32',
        '2023-08-11 22:27:32'),
       (29, '2022_s12.zip', '14cb8363-46f6-47a4-a3d4-8ed46b44b295', 'zip', 2539566, '2023-08-11 22:28:18',
        '2023-08-11 22:28:18'),
       (30, '2022_s12.zip', '43a90608-4d97-4b17-9c14-d42e9fb60bf7', 'zip', 2539566, '2023-08-11 22:29:36',
        '2023-08-11 22:29:36'),
       (31, '2022_s12.zip', 'f2174274-6195-404b-bcd7-33058ef7555e', 'zip', 2539566, '2023-08-11 22:34:20',
        '2023-08-11 22:34:20'),
       (32, '2022_s12.zip', '0367ba90-46ca-499a-9380-1e599ca93012', 'zip', 2539566, '2023-08-11 22:41:12',
        '2023-08-11 22:41:12'),
       (33, '课程表格.zip', '86d40568-774e-4b83-ab6e-b5afde4725bf', 'zip', 34136, '2023-08-11 22:41:22',
        '2023-08-11 22:41:22'),
       (34, '2022_s12.zip', '5bdfc3d9-1de0-4339-8ec5-7e7921361b3b', 'zip', 2539566, '2023-08-11 22:42:31',
        '2023-08-11 22:42:31'),
       (35, '课程表格.zip', 'f02eb0ec-2ece-4183-911e-d2e24af11586', 'zip', 34136, '2023-08-11 22:44:11',
        '2023-08-11 22:44:11'),
       (36, '课程表格.zip', 'ab747dc3-9b46-4b74-8794-a00b588ef7a9', 'zip', 34136, '2023-08-11 22:45:27',
        '2023-08-11 22:45:27'),
       (37, '课程表格.zip', '06c07b24-ee14-400e-8247-776855c9e264', 'zip', 34136, '2023-08-11 22:46:20',
        '2023-08-11 22:46:20'),
       (38, '2022_s12.zip', '897fa69d-67be-4bf5-af10-a333adf9f49a', 'zip', 2539566, '2023-08-11 22:46:50',
        '2023-08-11 22:46:50'),
       (39, '特征识别平台.zip', '36786656-3ec3-4f4f-b690-6a9521a71665', 'zip', 4975585, '2023-08-11 22:49:16',
        '2023-08-11 22:49:16'),
       (40, '课程表格.zip', '3cf8f9c9-0633-4f1a-a817-c552eb3f3592', 'zip', 34136, '2023-08-11 22:49:18',
        '2023-08-11 22:49:18'),
       (41, '特征识别平台.zip', 'f1afdff3-819c-4442-83f3-0f1d2475f02f', 'zip', 4975585, '2023-08-11 22:54:04',
        '2023-08-11 22:54:04'),
       (44, '2022_s12.zip', 'c255a067-cbd8-47a7-8cf9-128cf83aec98', 'zip', 2539566, '2023-08-12 11:04:47',
        '2023-08-12 11:04:47'),
       (45, '课程表格.zip', 'e5c921c9-bee1-4055-b58b-3887734795b3', 'zip', 34136, '2023-08-12 11:05:59',
        '2023-08-12 11:05:59'),
       (46, '2022_s12.zip', '68a1ecaa-9904-41ca-9ef1-ed353149cb90', 'zip', 2539566, '2023-08-12 11:27:57',
        '2023-08-12 11:27:57'),
       (47, '2022_s12.zip', '451eec3b-bb82-4878-9580-363fecd3922f', 'zip', 2539566, '2023-08-12 11:32:10',
        '2023-08-12 11:32:10'),
       (48, '课程表格.zip', '8759a928-fc51-478a-b2aa-2f278d84892b', 'zip', 34136, '2023-08-12 11:33:21',
        '2023-08-12 11:33:21'),
       (49, '2022_s12.zip', '8d948cb6-50be-4581-a981-6ae73636c98a', 'zip', 2539566, '2023-08-12 11:37:08',
        '2023-08-12 11:37:08'),
       (50, '课程表格.zip', '78645b0f-83eb-4ef3-8f7d-9c071a123628', 'zip', 34136, '2023-08-12 11:37:30',
        '2023-08-12 11:37:30'),
       (51, '课程表格.zip', '15d54ad7-eef2-43b2-a1b9-d4aacc1d2035', 'zip', 34136, '2023-08-12 11:39:11',
        '2023-08-12 11:39:11'),
       (53, '2022_s12.zip', '74c9c4a1-2ea3-49a1-85b5-27ac351ba469', 'zip', 2539566, '2023-08-12 11:42:25',
        '2023-08-12 11:42:25'),
       (54, '课程表格.zip', '47d5ce7e-f783-4f5e-86a9-fcaf647c0723', 'zip', 34136, '2023-08-12 11:45:34',
        '2023-08-12 11:45:34'),
       (55, '职位库23京考报名表（标红不填）.zip', '7ceaf0a3-7702-4777-b6b4-19812f57eebc', 'zip', 101901,
        '2023-08-12 11:47:47', '2023-08-12 11:47:47'),
       (57, '职位库23京考报名表（标红不填）.zip', 'fa6ccfb9-524d-4c25-975b-bce68307238d', 'zip', 101901,
        '2023-08-12 12:01:20', '2023-08-12 12:01:20'),
       (58, '2022_s12.zip', '5c4112f8-5083-4d21-862b-73efa1a59467', 'zip', 2539566, '2023-08-12 13:02:33',
        '2023-08-12 13:02:33'),
       (59, '2022_s12.zip', '9b3ab7fc-db9a-4dff-9692-f144a80f56fc', 'zip', 2539566, '2023-08-12 13:19:36',
        '2023-08-12 13:19:36'),
       (60, '课程表格.zip', '0d9f01cc-f64d-4b29-b0ec-c2ec89b03ad9', 'zip', 34136, '2023-08-12 13:23:21',
        '2023-08-12 13:23:21'),
       (61, '课程表格.zip', 'd1dbfdc9-646f-43f7-b60f-79e74c7a74aa', 'zip', 34136, '2023-08-12 13:25:14',
        '2023-08-12 13:25:14'),
       (64, '职位库23京考报名表（标红不填）.zip', 'cc1bc2b0-138f-44b6-bf15-0d2b08f56ca2', 'zip', 101901,
        '2023-08-12 13:32:56', '2023-08-12 13:32:56'),
       (65, '课程表格.zip', 'caa37740-1f53-4160-80ef-0008b7891583', 'zip', 34136, '2023-08-12 13:43:35',
        '2023-08-12 13:43:35'),
       (66, '课程表格.zip', '89b1cf60-f6c7-49f7-ab7c-6f223c1addeb', 'zip', 34136, '2023-08-12 13:45:42',
        '2023-08-12 13:45:42'),
       (67, '2022_s12.zip', '40c40584-40e5-442d-9819-013bd0341fb2', 'zip', 2539566, '2023-08-12 21:18:17',
        '2023-08-12 21:18:17'),
       (68, '课程表格.zip', '228d27f1-4785-4089-9e68-71cfc1a90cd7', 'zip', 34136, '2023-08-12 21:20:55',
        '2023-08-12 21:20:55'),
       (69, '2022_s12.zip', '738472a8-3de8-4b7b-85ae-868a46540bbc', 'zip', 2539566, '2023-08-12 21:32:22',
        '2023-08-12 21:32:22'),
       (70, '职位库23京考报名表（标红不填）.zip', '6084b7b7-62dc-4acd-9cea-5b25f001ecbc', 'zip', 101901,
        '2023-08-12 21:33:48', '2023-08-12 21:33:48'),
       (71, '职位库23京考报名表（标红不填）.zip', 'e1a1f62d-85a4-4a96-8af5-0440d41e0abd', 'zip', 101901,
        '2023-08-12 21:36:15', '2023-08-12 21:36:15'),
       (74, '2022_s12.zip', 'ef26d66a-f217-431b-bd05-59903e5af24c', 'zip', 2539566, '2023-08-13 15:09:25',
        '2023-08-13 15:09:25'),
       (75, '课程表格.zip', '32fa76e6-6202-48c4-84cb-6a7c5fb5cd1f', 'zip', 34136, '2023-08-13 15:20:07',
        '2023-08-13 15:20:07'),
       (76, '课程表格.zip', 'af780e57-511a-4f1d-ba15-f749573f7086', 'zip', 34136, '2023-08-13 15:22:23',
        '2023-08-13 15:22:23'),
       (78, '职位库23京考报名表（标红不填）.zip', '2dcd2adf-3d1b-4da8-bb12-c816223df44c', 'zip', 101901,
        '2023-08-13 15:26:38', '2023-08-13 15:26:38'),
       (83, '课程表格.zip', 'e6764ee3-d3d6-4fae-9a6e-45b8c913f331', 'zip', 34136, '2023-08-13 15:45:43',
        '2023-08-13 15:45:43'),
       (85, '职位库23京考报名表（标红不填）.zip', '7ac16ab4-371d-4fef-bcce-ec00694a292b', 'zip', 101901,
        '2023-08-13 15:50:37', '2023-08-13 15:50:37'),
       (86, '课程表格.zip', 'bac8e2d2-db77-4d9c-806e-de331c9df76c', 'zip', 34136, '2023-08-13 15:52:45',
        '2023-08-13 15:52:45'),
       (87, '职位库23京考报名表（标红不填）.zip', 'd7061e9b-3253-443f-ae5d-2dc9dc8bad67', 'zip', 101901,
        '2023-08-13 15:55:57', '2023-08-13 15:55:57'),
       (93, '课程表格.zip', '7e989f41-1bb8-46df-adb4-d81cec08a9ec', 'zip', 34136, '2023-08-13 16:36:08',
        '2023-08-13 16:36:08'),
       (94, '职位库23京考报名表（标红不填）.zip', '67bff313-13a6-42c1-abf5-2ea82a62e816', 'zip', 101901,
        '2023-08-13 16:39:32', '2023-08-13 16:39:32'),
       (95, '课程表格.zip', '42affae1-e309-4628-93dd-30bf58af94e1', 'zip', 34136, '2023-08-13 16:47:06',
        '2023-08-13 16:47:06'),
       (97, '特征识别平台.zip', '22733e0a-9afc-48f6-bef9-8a7c5e293023', 'zip', 4975585, '2023-08-13 22:52:15',
        '2023-08-13 22:52:15'),
       (98, 'DG5501488_x64.ZIP', '52d5c73d-1c0f-4e2b-9956-6ac09345ece5', 'ZIP', 39449361, '2023-08-13 22:52:56',
        '2023-08-13 22:52:56'),
       (105, 'aDrive.zip', 'ca7c61d3-d97d-412f-afc0-082bee7c3ea6', 'zip', 97688727, '2023-08-20 17:57:02',
        '2023-08-20 17:57:02'),
       (106, '算法文件.zip', '618a847f-58e0-475f-93b2-4625f20bc185', 'zip', 31659, '2023-08-20 22:14:18',
        '2023-08-20 22:14:18'),
       (107, '算法文件 (1).zip', '168fbc34-98ba-49c8-8d77-4a7c01c2d4d1', 'zip', 31659, '2023-08-21 23:21:22',
        '2023-08-21 23:21:22'),
       (108, '算法文件.zip', '0a4006d8-d358-4e15-87d1-32846dd85e3d', 'zip', 31659, '2023-08-21 23:44:47',
        '2023-08-21 23:44:47'),
       (109, 'aDrive.zip', '4ed7c314-e03f-4c51-bc06-eb1a62cf29a6', 'zip', 97688727, '2023-08-21 23:56:53',
        '2023-08-21 23:56:53'),
       (113, '123.zip', 'b7acbe77-39fc-4690-bd43-daa8c0272190', 'zip', 22, '2023-08-24 21:10:52',
        '2023-08-24 21:10:52'),
       (115, '课程表格.zip', '5524eb8d-c417-49ca-a54e-aea39cbe2748', 'zip', 34136, '2023-08-24 21:41:06',
        '2023-08-24 21:41:06'),
       (116, '课程表格.zip', '637d27d9-6ebf-4449-ba63-c83b057f86ae', 'zip', 34136, '2023-08-24 21:44:13',
        '2023-08-24 21:44:13'),
       (117, '职位库23京考报名表（标红不填）.zip', 'ea03c215-bedf-4faa-950a-19e6754fd15a', 'zip', 101901,
        '2023-08-24 21:47:33', '2023-08-24 21:47:33'),
       (118, '2022_s12.zip', 'c27af991-df5b-422c-aed2-90cfcdad4aed', 'zip', 2539566, '2023-08-24 21:49:59',
        '2023-08-24 21:49:59'),
       (119, '123.zip', 'e3097710-d45a-4331-bb63-569e05708e1c', 'zip', 22, '2023-08-24 22:36:33',
        '2023-08-24 22:36:33'),
       (120, '123.zip', '00d3facd-c806-447d-a168-05f22a3153f6', 'zip', 22, '2023-08-24 23:23:31',
        '2023-08-24 23:23:31'),
       (121, '123.zip', '9e37dcce-10f5-4d24-b100-09088b4b4249', 'zip', 22, '2023-08-24 23:23:53',
        '2023-08-24 23:23:53'),
       (122, '123.zip', '97e8f243-3ce0-4b42-9105-6c4e94b8cb43', 'zip', 22, '2023-08-24 23:36:08',
        '2023-08-24 23:36:08'),
       (123, '123.zip', '5a231e74-f764-45ea-81f9-16bed61d27fc', 'zip', 22, '2023-08-24 23:37:03',
        '2023-08-24 23:37:03'),
       (124, '123.zip', '0b3e6bad-d8dc-4f87-a0c2-678d47029f93', 'zip', 22, '2023-08-24 23:59:28',
        '2023-08-24 23:59:28'),
       (125, '123.zip', '3f53baf7-ce07-4f3d-ba42-796f1400a337', 'zip', 22, '2023-08-25 00:00:13',
        '2023-08-25 00:00:13'),
       (126, '123.zip', '1fa01764-82ec-42ed-b1bb-1a72604bae3f', 'zip', 22, '2023-08-25 00:08:26',
        '2023-08-25 00:08:26'),
       (127, '算法文件.zip', 'b2602fcb-1e9a-48d6-bc30-83e63ca53cca', 'zip', 22, '2023-08-25 19:39:08',
        '2023-08-25 19:39:08'),
       (128, '算法文件.zip', 'c3a5f3c1-ebe3-40fe-8598-eaeb9adb509f', 'zip', 22, '2023-08-25 19:41:25',
        '2023-08-25 19:41:25'),
       (129, '算法文件.zip', 'a0b7a696-8230-4837-ac50-c4f0ef80bb12', 'zip', 22, '2023-08-25 19:42:26',
        '2023-08-25 19:42:26'),
       (130, '算法文件.zip', '276ccd67-800c-419f-9033-3f4c0663877d', 'zip', 22, '2023-08-25 20:11:00',
        '2023-08-25 20:11:00'),
       (131, '算法文件.zip', 'b400d278-0612-42e7-b5da-203f0b5e8cd8', 'zip', 22, '2023-08-25 20:14:22',
        '2023-08-25 20:14:22'),
       (132, '算法文件.zip', '2d105f3a-6766-4504-b2b9-64a00ca75650', 'zip', 22, '2023-08-25 20:30:31',
        '2023-08-25 20:30:31'),
       (133, '算法文件.zip', 'ac388d3f-64a4-443d-9dc3-4a8f9a9ee5d1', 'zip', 22, '2023-08-25 20:32:00',
        '2023-08-25 20:32:00'),
       (134, '算法文件.zip', 'f8ac5980-1e50-4ab3-a169-b2711eacbca1', 'zip', 22, '2023-08-25 20:54:00',
        '2023-08-25 20:54:00'),
       (135, '数据集文件.zip', 'dad4a009-2e9b-4797-8079-07339eddfe62', 'zip', 22, '2023-08-25 20:54:29',
        '2023-08-25 20:54:29'),
       (136, '课程表格.zip', 'dd9441e3-8eec-4252-a28a-05e93273b5d2', 'zip', 34136, '2023-08-25 21:16:23',
        '2023-08-25 21:16:23'),
       (137, '职位库23京考报名表（标红不填）.zip', 'f0751326-1a51-40fe-a759-2c3cb6247221', 'zip', 101901,
        '2023-08-25 21:20:59', '2023-08-25 21:20:59'),
       (138, '算法文件.zip', 'b3927f6f-cd34-4fe4-9f69-53a55b52bbb0', 'zip', 22, '2023-08-26 12:18:09',
        '2023-08-26 12:18:09'),
       (139, '算法文件.zip', 'f4181fe8-6842-48ef-aef1-377252e81fdf', 'zip', 22, '2023-08-26 12:18:15',
        '2023-08-26 12:18:15'),
       (140, '数据集文件.zip', '5c0b5abf-6feb-4707-b37d-3038d29c4243', 'zip', 22, '2023-08-26 12:18:28',
        '2023-08-26 12:18:28'),
       (141, '数据集文件.zip', '6175fa1e-4dcd-4885-b092-1325d4d2a163', 'zip', 22, '2023-08-26 12:18:33',
        '2023-08-26 12:18:33'),
       (142, '算法文件.zip', 'a9d19e1d-512f-49b3-bd1d-093c48e98aa9', 'zip', 22, '2023-08-26 12:28:48',
        '2023-08-26 12:28:48'),
       (143, '职位库23京考报名表（标红不填）.zip', '05504ed1-5987-4926-88a1-f09ba91b2078', 'zip', 101901,
        '2023-08-28 22:05:25', '2023-08-28 22:05:25'),
       (144, '课程表格.zip', '2548455c-41d0-408b-8856-7c55a2767cb8', 'zip', 34136, '2023-08-28 22:10:01',
        '2023-08-28 22:10:01'),
       (145, '2022_s12.zip', 'd1b4693e-dcef-4f13-9119-d901522cde9e', 'zip', 2539566, '2023-08-28 22:14:12',
        '2023-08-28 22:14:12'),
       (147, '数据集文件.zip', '146ca066-b4fd-454e-b5b3-bd34d206d364', 'zip', 22, '2023-08-30 00:07:43',
        '2023-08-30 00:07:43'),
       (148, '数据集文件.zip', '6e2eefb3-fc25-4baf-83c3-fad4a432e9fe', 'zip', 22, '2023-08-30 00:42:55',
        '2023-08-30 00:42:55'),
       (149, '算法文件.zip', '14c481d6-160c-40aa-be42-b74ece9c1ded', 'zip', 22, '2023-08-30 21:45:19',
        '2023-08-30 21:45:19'),
       (150, '算法文件.zip', '6e9553f0-2c04-44f0-b8a2-db77e4678c6f', 'zip', 22, '2023-08-30 21:46:51',
        '2023-08-30 21:46:51'),
       (152, '数据集文件.zip', '5059b39d-73d1-4d06-bda3-0b3baca547df', 'zip', 22, '2023-08-30 23:31:46',
        '2023-08-30 23:31:46'),
       (153, '示例文件.zip', '4b5585c6-b122-4165-928f-b9c87f37c444', 'zip', 1572634, '2023-09-21 15:52:49',
        '2023-09-21 15:52:49'),
       (154, '示例文件.zip', 'f36b39e3-30ac-45ab-a237-6a338c06eddd', 'zip', 1572634, '2023-09-21 15:54:25',
        '2023-09-21 15:54:25'),
       (155, '算法示例文件.zip', '4051e4db-0dcd-48a7-b113-7036e2aabf06', 'zip', 64315, '2023-09-22 22:10:49',
        '2023-09-22 22:10:49'),
       (156, '算法示例文件.zip', 'b2dc060b-a8a7-4f38-874c-4efeeb4678f5', 'zip', 64315, '2023-09-22 22:11:22',
        '2023-09-22 22:11:22'),
       (159, 'tensorflow算法文件.zip', 'f0a49733-c24e-41bb-99ce-0105fa5cc965', 'zip', 5992, '2023-09-24 21:07:01',
        '2023-09-24 21:07:01'),
       (160, '数据集文件.zip', '048696df-95ab-4058-9f66-a2b5f2e803f6', 'zip', 22, '2023-09-24 21:08:32',
        '2023-09-24 21:08:32'),
       (161, 'tensorflow算法文件.zip', 'eb1f8893-ae54-4230-b4c8-28295a4826a0', 'zip', 5992, '2023-09-24 21:12:03',
        '2023-09-24 21:12:03'),
       (162, '数据集文件.zip', '4905d42a-53bc-47a7-b246-e39a3df10c25', 'zip', 22, '2023-09-24 21:13:27',
        '2023-09-24 21:13:27'),
       (163, 'tensorflow算法文件.zip', '46a72ce1-dd89-486e-a7c3-16c4cc913f3c', 'zip', 5992, '2023-09-25 01:21:15',
        '2023-09-25 01:21:15'),
       (164, '数据集文件.zip', 'cda2709c-bc91-499a-ac62-ad5773c2fe6b', 'zip', 22, '2023-09-25 01:22:31',
        '2023-09-25 01:22:31'),
       (165, 'tensorflow算法文件.zip', '5cd238fb-5b5f-4ff3-9a6b-56e0ac1c2288', 'zip', 5992, '2023-09-25 01:26:34',
        '2023-09-25 01:26:34'),
       (166, '示例文件.zip', '23f327e7-e9ca-4966-aa26-dcb24f311ad5', 'zip', 1572634, '2023-10-19 21:40:20',
        '2023-10-19 21:40:20'),
       (168, 'tensorflow算法文件.zip', '3ed0ca0d-4b2d-44e6-a776-09c75524409b', 'zip', 5992, '2023-10-19 22:37:00',
        '2023-10-19 22:37:00'),
       (169, 'tensorflow算法文件.zip', '5fdd8f88-d1aa-4217-97aa-80734e9a7d60', 'zip', 5992, '2023-10-19 22:50:22',
        '2023-10-19 22:50:22'),
       (170, 'tensorflow算法文件.zip', '6a4e7644-5f96-4b78-9f34-4dde2ba56f29', 'zip', 5992, '2023-10-19 22:50:44',
        '2023-10-19 22:50:44'),
       (171, 'tensorflow算法文件.zip', '6888c188-2cc8-4d8d-af64-fa2f2e3d2705', 'zip', 5992, '2023-10-19 22:51:22',
        '2023-10-19 22:51:22'),
       (172, 'tensorflow算法文件.zip', '0d3dd12c-b768-43ff-bd30-a8aeeee7c0f7', 'zip', 5992, '2023-10-19 22:51:31',
        '2023-10-19 22:51:31'),
       (173, '示例文件.zip', '5c4685d7-325e-4735-addf-868b1cb3dc9b', 'zip', 1572634, '2023-10-19 22:52:26',
        '2023-10-19 22:52:26'),
       (174, '示例文件.zip', 'e61afe3c-5eb0-430d-9a7b-5791d73ee40e', 'zip', 1572634, '2023-10-19 23:21:23',
        '2023-10-19 23:21:23'),
       (175, '算法文件2.zip', '49f77bee-599b-4204-a31a-b31639cbf792', 'zip', 7192, '2023-10-22 18:43:06',
        '2023-10-22 18:43:06'),
       (176, '示例2文件.zip', 'e61afe3c-5eb0-430d-9a7b-5791d73ee40e', 'zip', 1572634, '2023-10-30 10:40:21',
        '2023-10-30 10:40:21'),
       (177, '1a0968ab0e60968178064641566dac3.jpg', '888ca399-fe20-48bc-933d-8d0e44c78401', 'jpg', 403405,
        '2023-10-30 10:46:39', '2023-10-30 10:46:39'),
       (178, '1a0968ab0e60968178064641566dac3.jpg', '1857e585-71b7-440c-bd32-32624ddc0de1', 'jpg', 403405,
        '2023-11-04 11:46:55', '2023-11-04 11:46:55'),
       (179, 'radio-selected.png', 'ae9ec1c6-7d63-4d25-950a-0ac4cdfc04ac', 'png', 1269, '2023-11-04 20:55:28',
        '2023-11-04 20:55:28'),
       (181, 'moban.png', '4d5c937f-0fba-4518-a99a-a741d3147bc0', 'png', 58499, '2023-11-04 20:57:00',
        '2023-11-04 20:57:00'),
       (182, 'image_bac.png', 'f7008868-1fd9-49d2-8143-05b8f71abc7c', 'png', 8726, '2023-11-04 21:18:16',
        '2023-11-04 21:18:16'),
       (183, 'coloruicss-master.zip', '9b60a14f-56a6-46ea-b82f-f33994db1360', 'zip', 3518970, '2023-11-04 22:27:30',
        '2023-11-04 22:27:30'),
       (184, 'coloruicss-master.zip', '906737d7-bb1d-476d-8d7e-5cdc89a44708', 'zip', 3518970, '2023-11-04 22:30:12',
        '2023-11-04 22:30:12'),
       (185, '1a0968ab0e60968178064641566dac3.jpg', '73e21bd1-c5e4-40e9-bfca-8921554f7b09', 'jpg', 403405,
        '2023-11-04 22:36:46', '2023-11-04 22:36:46'),
       (186, '1a0968ab0e60968178064641566dac3.jpg', '6c7b4e81-6cef-43c9-a814-19ed15f3b61a', 'jpg', 403405,
        '2023-11-05 21:39:34', '2023-11-05 21:39:34'),
       (187, '示例文件.zip', 'd4759e88-81fb-4b4e-a996-ae7abd8ec76c', 'zip', 1572634, '2023-11-05 21:43:09',
        '2023-11-05 21:43:09');
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session`
(
    `id`              varchar(255) NOT NULL COMMENT 'id',
    `user_id`         int(11) DEFAULT NULL COMMENT '用户ID',
    `ip`              varchar(255) DEFAULT NULL COMMENT 'ip地址',
    `last_login_time` datetime     DEFAULT NULL COMMENT '最后登录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录session表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

LOCK
TABLES `session` WRITE;
/*!40000 ALTER TABLE `session` DISABLE KEYS */;
INSERT INTO `session`
VALUES ('a290b07d-eaf2-43a7-980c-6863d746a23a', 1, '106.37.96.61', '2023-10-18 10:55:57'),
       ('c1a2831c-33a7-4cf6-8d7b-790d825e5e69', 1, '117.61.194.85', '2023-10-18 22:15:30'),
       ('f11ba013-d757-41a1-ad45-9dfef6d5b6bc', 1, '103.90.191.167', '2023-10-19 09:24:39'),
       ('16bb0e44-78b7-43a2-8edb-60cdef6d8a17', 1, '118.167.17.236', '2023-10-19 11:36:21'),
       ('ab4110fe-bd79-447d-be7f-450be13eb781', 13, '115.171.85.130', '2023-10-19 21:34:04'),
       ('746d0b8e-1805-410a-8255-6ad4e416d84c', 1, '192.168.28.253', '2023-10-19 22:56:07'),
       ('56953275-501c-4aa5-8ce7-1e1d72676aa9', 1, '117.61.28.97', '2023-10-22 11:59:47'),
       ('aedd9b7d-67e0-4dae-bfb6-6129aee7c70b', 1, '115.171.85.130', '2023-10-22 12:08:20'),
       ('29b8590d-9183-422e-b5c1-db9fa2164cab', 12, '115.171.85.130', '2023-10-22 12:24:05'),
       ('7c35efaa-25cc-4923-8368-167bd6fd6107', 12, '117.61.28.97', '2023-10-22 12:24:13'),
       ('8f6e5511-27a9-483b-974c-c918e25a9dcd', 1, '118.167.12.86', '2023-10-22 18:28:05'),
       ('d6f7eb82-f87d-4cb1-845e-83b47ad74ddb', 12, '118.167.12.86', '2023-10-22 18:30:51'),
       ('38209699-81da-4a8c-a730-2382603597c9', 1, '111.201.26.134', '2023-10-22 18:44:05'),
       ('9bcbdc0b-3eac-4c99-bd1d-367e2a660082', 1, '118.167.12.69', '2023-10-26 16:27:26'),
       ('bb616412-d40b-4726-8b31-6f4d90e5b01a', 1, '123.165.134.235', '2023-10-26 16:36:44'),
       ('7c599ad6-16eb-4dcf-81d4-b09cb4afeb22', 1, '115.171.85.76', '2023-10-29 23:21:12'),
       ('bf0eca00-8a52-40ed-a078-dd77abf0b239', 12, '1.203.110.45', '2023-10-30 10:40:05'),
       ('26dac229-e650-4b7f-8146-243ea0112971', 1, '203.198.94.91', '2023-10-30 11:24:38'),
       ('8f83a023-b383-408e-bcf6-2d728ae6ec42', 1, '1.203.110.45', '2023-10-30 15:16:59'),
       ('ac2a100a-fbf3-4c32-b77b-5ea466077996', 12, '192.168.28.178', '2023-10-30 17:52:36'),
       ('22cb70dc-6670-4590-9b09-316f55ee3355', 12, '111.201.26.134', '2023-11-04 16:43:31'),
       ('a392a01e-5f3f-486e-8b32-f946a88013a0', 1, '117.61.20.199', '2023-11-04 21:22:54'),
       ('cc9fe8a9-e4b8-407b-922e-29739a5b363f', 12, '117.61.20.199', '2023-11-04 22:18:13'),
       ('8625e132-9a7e-4388-9ffd-d6af9f6e7e56', 1, '115.171.202.241', '2023-11-05 21:19:39'),
       ('26e2aa12-ceb8-4323-848b-21181bd60a7f', 1, '192.168.28.178', '2023-11-05 21:21:19'),
       ('896cbefb-a552-4fc3-a171-f25c221e4276', 12, '115.171.202.241', '2023-11-05 21:42:04'),
       ('31f76bf1-2144-4aa0-8c96-e240fe86e586', 1, '115.171.39.220', '2023-11-06 09:40:54'),
       ('96357da9-fe3c-4eff-bbbf-80c3f01ba14b', 1, '1.190.187.22', '2023-11-12 12:09:36'),
       ('b86f8c5e-3b35-45ca-b939-7525e405cc99', 1, '115.171.85.22', '2023-11-13 16:01:39');
/*!40000 ALTER TABLE `session` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `sharing`
--

DROP TABLE IF EXISTS `sharing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sharing`
(
    `id`                    int(11) NOT NULL AUTO_INCREMENT,
    `user_id`               int(11) DEFAULT NULL COMMENT '机构管理员ID',
    `money`                 double      DEFAULT NULL COMMENT '金额',
    `status`                int(11) DEFAULT NULL COMMENT '状态',
    `screenshot_of_payment` varchar(36) DEFAULT NULL COMMENT '付款凭据',
    `mark`                  text COMMENT '备注',
    `create_time`           datetime    DEFAULT NULL COMMENT '到账时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1 COMMENT='分成表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sharing`
--

LOCK
TABLES `sharing` WRITE;
/*!40000 ALTER TABLE `sharing` DISABLE KEYS */;
INSERT INTO `sharing`
VALUES (27, 54, 8800, 1, '6c7b4e81-6cef-43c9-a814-19ed15f3b61a', '', '2023-10-19 11:14:26'),
       (28, 58, 700, 1, '6c7b4e81-6cef-43c9-a814-19ed15f3b61a', '', '2023-10-19 11:14:45'),
       (29, 1, 666, 1, '6c7b4e81-6cef-43c9-a814-19ed15f3b61a', '1234', '2023-10-30 11:42:29'),
       (33, 20, 50, 1, '6c7b4e81-6cef-43c9-a814-19ed15f3b61a', '66', '2023-11-04 20:51:06'),
       (34, 37, 35, 1, '6c7b4e81-6cef-43c9-a814-19ed15f3b61a', '24', '2023-11-04 21:18:06'),
       (35, 32, 666, 1, '6c7b4e81-6cef-43c9-a814-19ed15f3b61a', '', '2023-11-04 22:36:31'),
       (36, 66, 888, 1, '6c7b4e81-6cef-43c9-a814-19ed15f3b61a', '', '2023-11-05 21:39:19');
/*!40000 ALTER TABLE `sharing` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `sport_category`
--

DROP TABLE IF EXISTS `sport_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sport_category`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `type`        varchar(255) DEFAULT NULL COMMENT '体育类型(0:学校体育 1:群众体育 2:竞技体育)',
    `name`        varchar(255) DEFAULT NULL COMMENT '分类名',
    `user_id`     int(11) DEFAULT NULL COMMENT '添加人',
    `mark`        text COMMENT '备注',
    `create_time` datetime     DEFAULT NULL COMMENT '授权时间 ',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COMMENT='体育类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport_category`
--

LOCK
TABLES `sport_category` WRITE;
/*!40000 ALTER TABLE `sport_category` DISABLE KEYS */;
INSERT INTO `sport_category`
VALUES (1, '0', '田径', 1, '备注', '2023-08-14 17:47:35'),
       (2, '0', '铅球', 1, '备注', '2023-07-26 13:47:32'),
       (5, '0', '跳远', 1, '备注', '2023-07-26 13:47:32'),
       (6, '1', '羽毛球', 1, '备注', '2023-07-26 13:47:32'),
       (7, '1', '篮球', 1, '备注', '2023-07-26 13:47:32'),
       (8, '1', '游泳', 1, '备注', '2023-07-26 13:47:32'),
       (9, '2', '击剑', 1, '备注', '2023-07-26 13:47:32'),
       (11, '2', '乒乓球', 1, '备注', '2023-07-26 13:47:32'),
       (12, '0', '网球', 1, '', '2023-10-18 22:39:04'),
       (13, '1', '铅球', 1, '', '2023-10-18 22:39:01'),
       (14, '1', '足球', 1, '', '2023-10-18 22:38:56'),
       (21, '2', '标枪', 1, '', '2023-09-21 15:53:09'),
       (22, '2', '高尔夫', 1, NULL, '2023-09-25 01:25:59');
/*!40000 ALTER TABLE `sport_category` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(255) DEFAULT NULL COMMENT '模板名',
    `content`     json         DEFAULT NULL COMMENT '模板内容',
    `create_time` datetime     DEFAULT NULL,
    `mark`        text COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK
TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template`
VALUES (1, '通用模板', '{
  \"动作准确\": \"姿势评分\", \"有氧能力\": \"准确率评分\", \"柔韧伸展\": \"动作幅度评分\", \"灵活敏捷\": \"完成度评分\", \"肌肉力量\": \"速度评分\"}',
        '2023-10-19 08:59:50', '通用模板可应对常规的体育运动项目'),
       (2, '篮球类模板', '{
         \"动作准确\": \"起跳姿势评分\", \"有氧能力\": \"投篮命中率评分\", \"柔韧伸展\": \"手腕标准度评分\", \"灵活敏捷\": \"投篮时机评分\", \"肌肉力量\": \"球速评分\"}',
        '2023-10-18 22:22:52', '测试请使用篮球模板！'),
       (3, '足球类模板', '{
         \"射门技术\": \"射门准确度评分\", \"盘带技术\": \"盘球控制评分\", \"防守能力\": \"抢断成功率评分\", \"传球准确度\": \"传球精准度评分\", \"速度与爆发力\": \"冲刺速度评分\"}',
        '2023-10-19 09:11:46', ''),
       (4, '乒乓球类模板', '{
         \"反手技术\": \"反手控制评分\", \"发球技术\": \"发球准确度评分\", \"进攻技巧\": \"进攻策略评分\", \"防守能力\": \"防守技巧评分\", \"接发球能力\": \"接发球反应评分\"}',
        '2023-10-19 09:16:38', NULL);
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `type`        int(11) DEFAULT NULL,
    `nickname`    varchar(255) DEFAULT NULL COMMENT '昵称',
    `username`    varchar(255) DEFAULT NULL,
    `password`    varchar(255) DEFAULT NULL,
    `mark`        text,
    `create_time` datetime     DEFAULT NULL COMMENT '授权时间',
    `attr`        json         DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK
TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 0, '算法商家管理员', 'root',
        '$argon2id$v=19$m=65536,t=2,p=1$1VdMY/Yxaal5oXNyd10g5A$jNxdp7UHCVNgp8M5EV9lkRi15ZheMaRbSqKGpWuNCbI',
        '密码123456', '2023-07-27 15:02:54', '{}'),
       (12, 1, '程序员小王12', 'wangzijian',
        '$argon2id$v=19$m=65536,t=2,p=1$g6qS43s+np6mh2r0x4Hp7w$/vzi1nsJrmpXl4sFJpWDX1yZSasWO0fqOHduk218uj4',
        '支付宝：10086', '2023-08-26 04:22:48', '{
         \"phone\": \"18846922264\"}'),
       (13, 1, '算法工程师小张', 'xiaozhang',
        '$argon2id$v=19$m=65536,t=2,p=1$O6XX9NAHYBkZrRWHyTa1cw$XsWUCjHHaxfm3Gv0ldGstYrDRpGUIAcV0pcVzgEXXxE',
        '支付宝：18846945678 小张', '2023-10-19 21:32:40', '{
         \"phone\": \"18846945678\"}'),
       (21, 3, '微信用户gTIQK', NULL, NULL, NULL, '2023-09-25 00:48:01', '{
         \"appId\": \"wxc62afc144417346e\", \"openId\": \"oFDqp5ebx3IpacqU36g0JOxMdSLI\", \"appName\": \"汇通小程序\"}'),
       (22, 3, '微信用户gTIQK', NULL, NULL, NULL, '2023-09-25 00:48:42', '{
         \"appId\": \"wxc62afc144417346e\", \"openId\": \"oFDqp5ebx3IpacqU36g0JOxMdSLI\", \"appName\": \"汇通小程序\"}'),
       (23, 3, '微信用户gTIQK', NULL, NULL, NULL, '2023-09-25 00:50:19', '{
         \"appId\": \"wxc62afc144417346e\", \"openId\": \"oFDqp5ebx3IpacqU36g0JOxMdSLI\", \"appName\": \"汇通小程序\"}'),
       (24, 3, '微信用户gTIQK', NULL, NULL, NULL, '2023-10-17 21:33:16', '{
         \"appId\": \"wxc62afc144417346e\", \"openId\": \"oFDqp5ahIym7_LT-I5o9wGIZeIJU\", \"appName\": \"乐刻小程序\"}'),
       (25, 3, '微信用户OtxLM', NULL, NULL, NULL, '2023-10-17 21:33:48', '{
         \"appId\": \"wxc62afc144417346e\", \"openId\": \"oFDqp5ebx3IpacqU36g0JOxMdSLI\", \"appName\": \"乐刻小程序\"}'),
       (27, 3, '王子健', NULL, NULL, NULL, '2023-10-20 15:29:19', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (28, 3, '王子健', NULL, NULL, NULL, '2023-10-20 15:43:27', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (29, 3, '王子健', NULL, NULL, NULL, '2023-10-20 16:00:19', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (30, 3, '王子健', NULL, NULL, NULL, '2023-10-20 16:31:14', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (31, 3, '王子健', NULL, NULL, NULL, '2023-10-20 16:41:26', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (32, 2, '王子健', '',
        '$argon2id$v=19$m=65536,t=2,p=1$HZz1q1sz9hZCvIyXdt9hlg$y4hfPBf/YxS/DrZ2sjs1wdTkQj3eCcZR3thDw0s5Ki0', 'string',
        '2023-11-05 21:31:04', '{
         \"phone\": \"18846922264\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"payType\": 0, \"payCount\": \"18846922264\", \"proportion\": \"60\", \"configurable\": 1, \"institutionId\": 201}'),
       (33, 3, '王子健', NULL, NULL, NULL, '2023-10-20 16:56:22', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (34, 3, '王子健', NULL, NULL, NULL, '2023-10-20 16:57:13', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (35, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-20 20:52:26', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (36, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-20 20:53:05', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (38, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-20 20:54:55', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (39, 3, '王子健', NULL, NULL, NULL, '2023-10-20 21:41:07', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (40, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-20 21:48:10', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (41, 3, '王子健', NULL, NULL, NULL, '2023-10-20 22:18:19', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (42, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-20 23:05:12', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (43, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-20 23:08:37', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (44, 3, '王子健', NULL, NULL, NULL, '2023-10-20 23:37:26', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (45, 3, '王子健', NULL, NULL, NULL, '2023-10-21 19:33:41', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (46, 3, '王子健', NULL, NULL, NULL, '2023-10-21 19:43:17', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (47, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-21 19:44:26', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (48, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-21 19:53:47', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (49, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-21 19:57:01', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (50, 3, '王子健', NULL, NULL, NULL, '2023-10-21 20:11:20', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (51, 3, '王子健', NULL, NULL, NULL, '2023-10-21 20:13:11', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (52, 3, '王子健', NULL, NULL, NULL, '2023-10-21 20:14:41', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (53, 3, '王子健', NULL, NULL, NULL, '2023-10-21 20:19:14', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (54, 3, '王子健', NULL, NULL, NULL, '2023-10-21 20:33:42', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (55, 3, '王子健', NULL, NULL, NULL, '2023-10-21 21:36:30', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (56, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-21 22:09:44', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (57, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-10-21 22:18:21', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"appName\": \"AI体育大师\"}'),
       (58, 3, '王子健', NULL, NULL, NULL, '2023-10-21 23:26:40', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (59, 3, '王子健', NULL, NULL, NULL, '2023-10-22 00:03:01', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (60, 3, '王子健', NULL, NULL, NULL, '2023-10-22 10:19:15', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (61, 3, '王子健', NULL, NULL, NULL, '2023-10-22 19:33:21', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (62, 3, '王子健', NULL, NULL, NULL, '2023-10-22 19:36:38', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (63, 3, '微信用户brTSI', NULL, NULL, NULL, '2023-10-25 17:06:26', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah690ZxN0Uyw_BFdnaa0b4mqs\", \"appName\": \"AI体育大师\"}'),
       (64, 3, '微信用户brTSI', NULL, NULL, NULL, '2023-10-26 09:45:58', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah63NA28xQKvBpkGe1OTS0hCk\", \"appName\": \"AI体育大师\"}'),
       (65, 3, '王子健', NULL, NULL, NULL, '2023-10-29 23:21:41', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"appName\": \"AI体育大师\"}'),
       (66, 2, '机构管理员小雅', '',
        '$argon2id$v=19$m=65536,t=2,p=1$PAjjSgHmEgqPxc4KzZeGpw$AVSgaOGiU5m/wRZkD1Okg1EkwoHFKk+zG0HTluUmHcQ', '666',
        '2023-11-05 21:31:17', '{
         \"phone\": \"18846929968\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"payType\": 1, \"payCount\": \"622202105791655321\", \"proportion\": \"80\", \"configurable\": 1, \"institutionId\": 2}'),
       (67, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-04 23:28:20', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (68, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 13:10:14', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (69, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 18:39:23', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (70, 3, '王子健', NULL, NULL, NULL, '2023-11-05 20:18:43', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (71, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 21:13:37', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (72, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 21:33:30', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (73, 3, '王子健', NULL, NULL, NULL, '2023-11-05 21:34:35', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6zJWRLU1mVT35P_HoC9WVm0\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (74, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 21:37:03', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (75, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 21:41:01', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (76, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 21:43:53', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (77, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 21:44:48', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (78, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 21:45:07', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (79, 3, '机构管理员小雅', NULL, NULL, NULL, '2023-11-05 21:45:32', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah6yfDtQK-HuNkaSvny0utPCI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (80, 3, '微信用户ILaok', NULL, NULL, NULL, '2023-11-06 00:41:43', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah65b4gtYHerJYj615OJ1OjdI\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}'),
       (81, 3, '微信用户qxFFT', NULL, NULL, NULL, '2023-11-12 12:11:27', '{
         \"appId\": \"wxbd62c87a1e4c9543\", \"openId\": \"oowah63NA28xQKvBpkGe1OTS0hCk\", \"userId\": \"AI体育大师\", \"appName\": \"AI体育大师\"}');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `wechat`
--

DROP TABLE IF EXISTS `wechat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wechat`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT,
    `app_id`         varchar(255) DEFAULT NULL COMMENT '小程序ID',
    `app_secret`     varchar(255) DEFAULT NULL COMMENT '小程序秘钥',
    `link_code`      varchar(36)  DEFAULT NULL COMMENT '小程序链接码',
    `name`           varchar(255) DEFAULT NULL COMMENT '小程序名',
    `institution_id` int(11) DEFAULT NULL COMMENT '机构ID',
    `status`         int(11) DEFAULT NULL COMMENT '进度(0已部署，1审核中)',
    `mark`           text COMMENT '备注',
    `create_time`    datetime     DEFAULT NULL COMMENT '授权时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='小程序表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wechat`
--

LOCK
TABLES `wechat` WRITE;
/*!40000 ALTER TABLE `wechat` DISABLE KEYS */;
INSERT INTO `wechat`
VALUES (1, 'wxc62afc144417346e', '2387d60cc0a34703ae8ec0acedd2a635', 'po80bb43-4a86-463e-8a64-86af145af212',
        '乐刻小程序', 1, 0, '润迪开通的小助手', '2023-09-25 01:31:21'),
       (4, 'wxc62afc1444173888', 'b5a7ea8dcee900d1f1663076ac8dba3b', '3180bb43-4a86-463e-8a64-86af145af245',
        '润迪专属小程序', 2, 1, NULL, '2023-08-14 07:34:45'),
       (9, 'wxbd62c87a1e4c9543', '716af12d545c3a25bc94b0af6b32d26f', '3b3baff4-129a-4bf6-ae91-98da4102e6f9',
        'AI体育大师', 201, 0, NULL, '2023-10-18 21:36:51');
/*!40000 ALTER TABLE `wechat` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `wx_report_data`
--

DROP TABLE IF EXISTS `wx_report_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wx_report_data`
(
    `id`                    int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `link_code`             varchar(255) DEFAULT NULL COMMENT '小程序链接码',
    `user_count_today`      int(11) DEFAULT NULL COMMENT '今日使用人数',
    `user_usage_time_today` int(11) DEFAULT NULL COMMENT '今日用户使用时长总和(秒)',
    `algorithm_count_today` int(11) DEFAULT NULL COMMENT '今日算法使用次数',
    `create_time`           datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COMMENT='微信用户数据上报表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_report_data`
--

LOCK
TABLES `wx_report_data` WRITE;
/*!40000 ALTER TABLE `wx_report_data` DISABLE KEYS */;
INSERT INTO `wx_report_data`
VALUES (1, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 8, 80, 20, '2023-09-05 23:25:29'),
       (12, 'po80bb43-4a86-463e-8a64-86af145af212', 2, 1, 1, '2023-09-17 23:25:29'),
       (16, 'po80bb43-4a86-463e-8a64-86af145af212', 8, 17, 12, '2023-09-19 11:32:55'),
       (17, 'po80bb43-4a86-463e-8a64-86af145af212', 5, 56, 11, '2023-08-10 11:32:55'),
       (18, 'po80bb43-4a86-463e-8a64-86af145af212', 1, 0, 5, '2023-09-23 19:55:00'),
       (19, 'po80bb43-4a86-463e-8a64-86af145af212', 2, 0, 6, '2023-09-24 18:06:28'),
       (20, '3180bb43-4a86-463e-8a64-86af145af245', 3, 0, 3, '2023-09-25 00:48:01'),
       (21, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 2, 0, 0, '2023-10-19 21:33:11'),
       (27, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 15, 9, 2, '2023-10-20 15:43:27'),
       (28, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 15, 29, 1, '2023-10-21 19:33:41'),
       (29, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 3, 2, 11, '2023-10-22 00:03:01'),
       (30, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 3, 5, 0, '2023-10-25 09:12:19'),
       (31, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 2, 8, 5, '2023-10-26 09:30:52'),
       (32, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 0, 0, 1, '2023-10-27 17:46:11'),
       (33, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 1, 0, 0, '2023-10-29 23:19:03'),
       (34, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 0, 0, 0, '2023-10-30 15:18:26'),
       (35, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 0, 0, 0, '2023-10-31 09:24:37'),
       (36, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 0, 0, 0, '2023-11-04 23:28:20'),
       (37, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 11, 71, 20, '2023-11-05 13:10:14'),
       (38, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 1, 18, 2, '2023-11-06 00:07:40'),
       (39, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 0, 0, 0, '2023-11-09 23:25:30'),
       (40, '3b3baff4-129a-4bf6-ae91-98da4102e6f9', 0, 0, 0, '2023-11-12 12:11:27');
/*!40000 ALTER TABLE `wx_report_data` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-13 16:50:47
