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
                             `type` int DEFAULT NULL COMMENT '类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
                             `sport_category` int DEFAULT NULL,
                             `template_id` int DEFAULT NULL,
                             `install_type` int DEFAULT NULL COMMENT '部署方式（0：云端部署 1：本地部署）',
                             `uploader` int DEFAULT NULL,
                             `file` varchar(255) DEFAULT NULL,
                             `docs` text,
                             `example` text,
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm`
--

INSERT INTO `algorithm` VALUES (1,'足球射门姿势算法',0,1,1,1,1,'ea','文档','这是一个案例','2023-08-14 17:47:57'),(3,'游泳姿势优化算法',1,8,1,1,1,'ea','文档','这是一个案例','2023-08-14 17:45:11'),(10,'田径起跑姿势优化算法',1,1,2,2,1,'e1a1f62d-85a4-4a96-8af5-0440d41e0abd','文档','样例','2023-08-14 17:47:20'),(14,'足球专用犯规识别算法',0,7,1,0,1,NULL,'文档','文档demo','2023-08-14 02:27:31'),(15,'篮球手部识别算法',1,7,1,0,1,'a66bd977-82c5-4d3b-9a97-f3aa7c5f24a7','文档','样例demo','2023-08-14 02:32:31'),(16,'乒乓球角度识别算法',1,11,4,0,12,'a45af1c1-a66a-4a80-b3e7-2ae6c743907f','文档','demo','2023-08-30 21:47:10'),(17,'新建铅球算法',0,2,1,0,1,'642df7e3-1318-4457-b621-a08657a81b75','123','456','2023-08-28 22:15:06'),(20,'跳远距离识别',1,9,1,0,1,'ae93811f-3fe9-41ab-8163-279151440323','1234','1234','2023-08-30 23:32:30');

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='授权机构算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_institution_alg`
--

INSERT INTO `auth_institution_alg` VALUES (1,1,0,1,1,'备注','2023-08-14 08:19:49'),(2,2,0,1,1,'备注','2023-07-26 00:00:00'),(3,3,0,1,1,'备注','2023-07-26 00:00:00'),(28,3,0,14,1,'234567890','2023-08-14 09:03:32'),(29,16,0,20,1,NULL,'2023-08-31 00:09:19'),(30,2,0,3,1,'123','2023-09-05 21:38:40'),(31,3,0,16,1,'备注','2023-07-26 00:00:00');

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charge` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `type` int DEFAULT NULL COMMENT '收费类型（0免费 1按次付费 2按月付费 3按季付费 4 按年付费 5永久） ',
                          `institution_id` int DEFAULT NULL COMMENT '机构ID',
                          `charge_time` datetime DEFAULT NULL COMMENT '收款时间时间',
                          `confirm_people` int DEFAULT NULL COMMENT '确认人',
                          `confirm_time` datetime DEFAULT NULL COMMENT '到账确认时间',
                          `status` int DEFAULT NULL COMMENT '状态（0：已到账，1：未到账）',
                          `mark` text COMMENT '备注信息',
                          `create_time` datetime DEFAULT NULL COMMENT '录入时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='付费表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge`
--

INSERT INTO `charge` VALUES (1,0,2,'2023-07-28 09:27:06',1,'2023-07-28 09:27:06',0,'备注','2023-07-28 09:27:06'),(6,1,2,'2023-08-14 08:45:09',1,'2023-08-14 08:45:09',0,'1222','2023-08-14 08:45:04'),(7,0,17,'2023-08-30 23:35:02',1,'2023-08-30 23:35:02',1,'123','2023-08-30 23:34:45'),(8,0,17,'2023-08-30 23:35:29',1,'2023-08-30 23:35:29',1,NULL,'2023-08-30 23:35:16');

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commission` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `review_id` int NOT NULL COMMENT '佣金项ID',
                              `money` double NOT NULL COMMENT '佣金金额',
                              `status` int NOT NULL COMMENT '状态(0:未付款，1：已付款)',
                              `mark` text COMMENT '备注',
                              `create_time` datetime NOT NULL COMMENT '添加时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='佣金发放表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

INSERT INTO `commission` VALUES (24,42,999,1,'支付宝10086','2023-08-26 04:21:56'),(26,48,9999,1,'已付款到支付宝10086','2023-08-26 04:56:43');

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(50) NOT NULL COMMENT '课程名称',
                          `cover` varchar(100) DEFAULT NULL COMMENT '课程封面',
                          `resource_id` int DEFAULT NULL COMMENT '资源ID',
                          `description` varchar(500) DEFAULT NULL COMMENT '课程描述',
                          `create_time` datetime NOT NULL COMMENT '创建时间',
                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学习表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--


--
-- Table structure for table `data_set`
--

DROP TABLE IF EXISTS `data_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `data_set` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL COMMENT '数据集名',
                            `type` int DEFAULT NULL COMMENT '数据集类型（0:普通数据集 ,1:专用数据集）',
                            `sport_category` int DEFAULT NULL COMMENT '支持的体育类型',
                            `file` varchar(255) DEFAULT NULL COMMENT '文件',
                            `demo` text COMMENT '数据集样例',
                            `install_type` int DEFAULT NULL COMMENT '部署方式（0：云端部署 1：本地部署）',
                            `uploader` int DEFAULT NULL COMMENT '上传人',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据集表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `data_set`
--

INSERT INTO `data_set` VALUES (1,'足球数据集',1,1,'73863c41-b494-48e5-841e-815b8ba701b3','demo',1,1,'2023-08-13 23:57:51'),(5,'篮球投篮动作数据集',0,7,'3d5a1466-51dc-4da7-ab9f-01eaae60a568','123',0,1,'2023-08-14 07:00:10'),(6,'跳远数据集',1,4,'5059b39d-73d1-4d06-bda3-0b3baca547df','123',0,1,'2023-08-30 23:31:21');

--
-- Table structure for table `developer_review`
--

DROP TABLE IF EXISTS `developer_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `developer_review` (
                                    `id` int NOT NULL AUTO_INCREMENT,
                                    `commit_name` varchar(255) DEFAULT NULL COMMENT '提交名',
                                    `sport_type` varchar(255) DEFAULT NULL COMMENT '支持体育类型',
                                    `developer_id` int DEFAULT NULL COMMENT '开发者',
                                    `type` int DEFAULT NULL COMMENT '数据类型(0普通算法 1专用算法  2普通数据集 3 专用数据集)',
                                    `file` varchar(255) DEFAULT NULL COMMENT '算法或数据集文件',
                                    `docs` text COMMENT '文档',
                                    `demo` varchar(255) DEFAULT NULL COMMENT '数据集样例',
                                    `mark` varchar(255) DEFAULT NULL COMMENT '备注',
                                    `status` int DEFAULT NULL COMMENT '审核状态（0：未提交 1：已提交未审核 2： 已提交未通过 3： 已提交已通过）',
                                    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='开发者审核表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `developer_review`
--

INSERT INTO `developer_review` VALUES (24,'羽毛球羽翼飞行结构数据集','羽毛球',13,3,'1fa01764-82ec-42ed-b1bb-1a72604bae3f','文档','这是一个案例','{\"id\":24,\"createTime\":\"2023-08-24T23:35:24.892Z\",\"status\":2,\"mark\":\"123\"}',2,'2023-08-21 21:57:43'),(41,'篮球起跳算法','篮球',13,0,'1fa01764-82ec-42ed-b1bb-1a72604bae3f','水电费','这是一个案例','{\"id\":41,\"createTime\":\"2023-08-25T00:08:46.673Z\",\"status\":2,\"mark\":\"123\"}',2,'2023-08-25 08:08:10'),(42,'足球运球算法','足球',13,0,'b2602fcb-1e9a-48d6-bc30-83e63ca53cca','算法文档说明','demo','{\"id\":42,\"createTime\":\"2023-08-25T19:39:48.268Z\",\"status\":3,\"mark\":\"恭喜你通过了\"}',3,'2023-08-26 03:38:52'),(43,'足球运球数据集','足球',13,2,'a0b7a696-8230-4837-ac50-c4f0ef80bb12','文档说明','demo','{\"id\":43,\"createTime\":\"2023-08-25T19:42:50.857Z\",\"status\":2,\"mark\":\"数据集数据格式不对\"}',2,'2023-08-26 03:41:52'),(44,'羽毛球拍打分析算法','羽毛球',13,1,'276ccd67-800c-419f-9033-3f4c0663877d','算法步骤\n选择一个基准元素，通常选择第一个元素作为基准元素。\n将序列分为两部分，左边部分都小于基准元素，右边部分都大于基准元素。\n对左右两部分递归执行步骤1和步骤2，直到序列长度为1。','def quick_sort(arr):\n    if len(arr) <= 1:\n        return arr\n    pivot = arr[0]\n    left = [x for x in arr[1:] if x < pivot]\n    right = [x for x in arr[1:] if x >= pivot]\n    return quick_sort(left) + [pivot] + quick_sort(right)\n','{\"id\":44,\"createTime\":\"2023-08-25T20:13:46.823Z\",\"status\":3,\"mark\":\"恭喜你通过了\"}',3,'2023-08-26 04:10:35'),(45,'羽毛球拍打算法数据集','羽毛球',13,3,'b400d278-0612-42e7-b5da-203f0b5e8cd8','文档','demo','{\"id\":45,\"createTime\":\"2023-08-25T20:14:41.642Z\",\"status\":2,\"mark\":\"对不起，格式不对\"}',2,'2023-08-26 04:14:04'),(48,'乒乓球挥拍算法','乒乓球',13,1,'f8ac5980-1e50-4ab3-a169-b2711eacbca1','docs','demo','{\"id\":48,\"createTime\":\"2023-08-25T20:55:17.779Z\",\"status\":3,\"mark\":\"恭喜 ~你算法通过了！\"}',3,'2023-08-26 04:53:40'),(49,'乒乓球挥拍数据集','乒乓球',13,3,'dad4a009-2e9b-4797-8079-07339eddfe62','docs','demo','{\"id\":49,\"createTime\":\"2023-08-25T20:55:40.123Z\",\"status\":2,\"mark\":\"对不起，数据格式不对！\"}',2,'2023-08-26 04:54:15'),(50,'游泳算法','游泳',13,1,'dd9441e3-8eec-4252-a28a-05e93273b5d2','算法文档1','算法样例2',NULL,1,'2023-08-26 05:15:33'),(51,'新游泳算法','游泳',13,0,'f0751326-1a51-40fe-a759-2c3cb6247221','算法文档\n','算法样例\n',NULL,1,'2023-08-26 05:20:35'),(52,'1234','体育',13,0,'123','123','1234','123',1,'2023-08-28 14:24:52'),(53,'王子健测试的算法','走路',13,0,'6e2eefb3-fc25-4baf-83c3-fad4a432e9fe','123','123','{\"id\":53,\"createTime\":\"2023-08-29T16:43:12.013Z\",\"status\":3,\"mark\":\"123\"}',3,'2023-08-30 00:42:37');

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exam` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) NOT NULL COMMENT '考试名称',
                        `description` varchar(500) DEFAULT NULL COMMENT '考试描述',
                        `start_time` datetime NOT NULL COMMENT '开始时间',
                        `end_time` datetime NOT NULL COMMENT '结束时间',
                        `resource_id` int DEFAULT NULL COMMENT '资源ID',
                        `exam_count` int DEFAULT NULL COMMENT '考试次数',
                        `create_time` datetime NOT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
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
                               `map` text COMMENT '地图位置 ',
                               `create_time` datetime DEFAULT NULL COMMENT '添加时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institution`
--

INSERT INTO `institution` VALUES (1,'乐刻健身',2,'10086','123@qq.com','beijing','https://map.baidu.com/poi/%E5%93%88%E5%B0%94%E6%BB%A8%E4%BD%93%E8%82%B2%E5%AD%A6%E9%99%A2/@14102582.756673405,5712157.242628686,17.38z?uid=2fff5b7869fe25d72b3a9f13&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl','2023-07-27 13:50:06'),(2,'润迪2',1,'10085','123456@qq.com','中国','https://map.baidu.com/poi/%E5%93%88%E5%B0%94%E6%BB%A8%E4%BD%93%E8%82%B2%E5%AD%A6%E9%99%A2/@14102582.756673405,5712157.242628686,17.38z?uid=2fff5b7869fe25d72b3a9f13&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl','2023-07-25 22:09:53'),(3,'XT体育',1,'10085','123456@qq.com','中国','https://map.baidu.com/poi/%E5%93%88%E5%B0%94%E6%BB%A8%E4%BD%93%E8%82%B2%E5%AD%A6%E9%99%A2/@14102582.756673405,5712157.242628686,17.38z?uid=2fff5b7869fe25d72b3a9f13&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl','2023-08-08 22:09:53'),(17,'子健云123',0,'18846922264','1219116968@qq.com','北京市','https://map.baidu.com/poi/%E6%8C%91%E6%88%98%E8%80%85%E5%B0%84%E5%87%BB%E4%BF%B1%E4%B9%90%E9%83%A8/@14083751.53,5730661.98,12z?uid=723286a1924ee2143c9a5e8f&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl','2023-08-30 23:43:21'),(198,'哈尔滨体育俱乐部',0,'10086','1219116968@qq.com','黑龙江省哈尔滨市呼兰区利民西六大街学院路950号哈尔滨信息工程学院东侧','https://map.baidu.com/poi/%E6%8C%91%E6%88%98%E8%80%85%E5%B0%84%E5%87%BB%E4%BF%B1%E4%B9%90%E9%83%A8/@14083751.53,5730661.98,12z?uid=723286a1924ee2143c9a5e8f&ugc_type=3&ugc_ver=1&device_ratio=2&compat=1&pcevaname=pc4.1&querytype=detailConInfo&da_src=shareurl',NULL);

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `user_id` int NOT NULL COMMENT '用户ID',
                          `algorithm_id` int NOT NULL COMMENT '算法ID',
                          `result` json DEFAULT NULL COMMENT '识别结果',
                          `create_time` datetime NOT NULL COMMENT '创建时间',
                          `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='识别报告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--


--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `full_name` varchar(255) DEFAULT NULL,
                             `alias` varchar(255) DEFAULT NULL,
                             `suffix` varchar(255) DEFAULT NULL COMMENT '后缀',
                             `size` bigint DEFAULT NULL,
                             `create_time` datetime DEFAULT NULL,
                             `update_time` datetime DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表元数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` VALUES (19,'特征识别平台.zip','4b4901b8-7af4-444a-b044-3067902cfaf2','zip',4975585,'2023-08-04 00:24:21','2023-08-04 00:24:21'),(20,'特征识别平台.zip','a2279d7d-f6d0-424f-891b-34a892680d35','zip',4975585,'2023-08-04 22:22:56','2023-08-04 22:22:56'),(21,'特征识别平台.zip','f91af1f9-7452-4cb4-847c-978b8bd6d2ae','zip',4975585,'2023-08-04 22:23:29','2023-08-04 22:23:29'),(22,'特征识别平台.zip','8fef3000-edf4-4944-809e-f580565879f4','zip',4975585,'2023-08-05 17:33:19','2023-08-05 17:33:19'),(23,'DG5501488_x64.ZIP','1cbee4af-507a-4102-9c24-22b4c0911c98','ZIP',39449361,'2023-08-05 17:58:33','2023-08-05 17:58:33'),(24,'特征识别平台.zip','879fbbe5-49f0-4a9f-8fbf-602fc8537ced','zip',4975585,'2023-08-08 17:58:37','2023-08-05 17:58:37'),(26,'2022_s12.zip','3d61be63-3c33-4347-bb25-a35ab0650548','zip',2539566,'2023-08-11 22:06:44','2023-08-11 22:06:44'),(27,'2022_s12.zip','1a682bda-31d1-4844-8869-ef3af9fd9c2e','zip',2539566,'2023-08-11 22:27:06','2023-08-11 22:27:06'),(28,'2022_s12.zip','f1107029-e73d-416b-bfef-0f6ca2d2aee1','zip',2539566,'2023-08-11 22:27:32','2023-08-11 22:27:32'),(29,'2022_s12.zip','14cb8363-46f6-47a4-a3d4-8ed46b44b295','zip',2539566,'2023-08-11 22:28:18','2023-08-11 22:28:18'),(30,'2022_s12.zip','43a90608-4d97-4b17-9c14-d42e9fb60bf7','zip',2539566,'2023-08-11 22:29:36','2023-08-11 22:29:36'),(31,'2022_s12.zip','f2174274-6195-404b-bcd7-33058ef7555e','zip',2539566,'2023-08-11 22:34:20','2023-08-11 22:34:20'),(32,'2022_s12.zip','0367ba90-46ca-499a-9380-1e599ca93012','zip',2539566,'2023-08-11 22:41:12','2023-08-11 22:41:12'),(33,'课程表格.zip','86d40568-774e-4b83-ab6e-b5afde4725bf','zip',34136,'2023-08-11 22:41:22','2023-08-11 22:41:22'),(34,'2022_s12.zip','5bdfc3d9-1de0-4339-8ec5-7e7921361b3b','zip',2539566,'2023-08-11 22:42:31','2023-08-11 22:42:31'),(35,'课程表格.zip','f02eb0ec-2ece-4183-911e-d2e24af11586','zip',34136,'2023-08-11 22:44:11','2023-08-11 22:44:11'),(36,'课程表格.zip','ab747dc3-9b46-4b74-8794-a00b588ef7a9','zip',34136,'2023-08-11 22:45:27','2023-08-11 22:45:27'),(37,'课程表格.zip','06c07b24-ee14-400e-8247-776855c9e264','zip',34136,'2023-08-11 22:46:20','2023-08-11 22:46:20'),(38,'2022_s12.zip','897fa69d-67be-4bf5-af10-a333adf9f49a','zip',2539566,'2023-08-11 22:46:50','2023-08-11 22:46:50'),(39,'特征识别平台.zip','36786656-3ec3-4f4f-b690-6a9521a71665','zip',4975585,'2023-08-11 22:49:16','2023-08-11 22:49:16'),(40,'课程表格.zip','3cf8f9c9-0633-4f1a-a817-c552eb3f3592','zip',34136,'2023-08-11 22:49:18','2023-08-11 22:49:18'),(41,'特征识别平台.zip','f1afdff3-819c-4442-83f3-0f1d2475f02f','zip',4975585,'2023-08-11 22:54:04','2023-08-11 22:54:04'),(44,'2022_s12.zip','c255a067-cbd8-47a7-8cf9-128cf83aec98','zip',2539566,'2023-08-12 11:04:47','2023-08-12 11:04:47'),(45,'课程表格.zip','e5c921c9-bee1-4055-b58b-3887734795b3','zip',34136,'2023-08-12 11:05:59','2023-08-12 11:05:59'),(46,'2022_s12.zip','68a1ecaa-9904-41ca-9ef1-ed353149cb90','zip',2539566,'2023-08-12 11:27:57','2023-08-12 11:27:57'),(47,'2022_s12.zip','451eec3b-bb82-4878-9580-363fecd3922f','zip',2539566,'2023-08-12 11:32:10','2023-08-12 11:32:10'),(48,'课程表格.zip','8759a928-fc51-478a-b2aa-2f278d84892b','zip',34136,'2023-08-12 11:33:21','2023-08-12 11:33:21'),(49,'2022_s12.zip','8d948cb6-50be-4581-a981-6ae73636c98a','zip',2539566,'2023-08-12 11:37:08','2023-08-12 11:37:08'),(50,'课程表格.zip','78645b0f-83eb-4ef3-8f7d-9c071a123628','zip',34136,'2023-08-12 11:37:30','2023-08-12 11:37:30'),(51,'课程表格.zip','15d54ad7-eef2-43b2-a1b9-d4aacc1d2035','zip',34136,'2023-08-12 11:39:11','2023-08-12 11:39:11'),(53,'2022_s12.zip','74c9c4a1-2ea3-49a1-85b5-27ac351ba469','zip',2539566,'2023-08-12 11:42:25','2023-08-12 11:42:25'),(54,'课程表格.zip','47d5ce7e-f783-4f5e-86a9-fcaf647c0723','zip',34136,'2023-08-12 11:45:34','2023-08-12 11:45:34'),(55,'职位库23京考报名表（标红不填）.zip','7ceaf0a3-7702-4777-b6b4-19812f57eebc','zip',101901,'2023-08-12 11:47:47','2023-08-12 11:47:47'),(57,'职位库23京考报名表（标红不填）.zip','fa6ccfb9-524d-4c25-975b-bce68307238d','zip',101901,'2023-08-12 12:01:20','2023-08-12 12:01:20'),(58,'2022_s12.zip','5c4112f8-5083-4d21-862b-73efa1a59467','zip',2539566,'2023-08-12 13:02:33','2023-08-12 13:02:33'),(59,'2022_s12.zip','9b3ab7fc-db9a-4dff-9692-f144a80f56fc','zip',2539566,'2023-08-12 13:19:36','2023-08-12 13:19:36'),(60,'课程表格.zip','0d9f01cc-f64d-4b29-b0ec-c2ec89b03ad9','zip',34136,'2023-08-12 13:23:21','2023-08-12 13:23:21'),(61,'课程表格.zip','d1dbfdc9-646f-43f7-b60f-79e74c7a74aa','zip',34136,'2023-08-12 13:25:14','2023-08-12 13:25:14'),(64,'职位库23京考报名表（标红不填）.zip','cc1bc2b0-138f-44b6-bf15-0d2b08f56ca2','zip',101901,'2023-08-12 13:32:56','2023-08-12 13:32:56'),(65,'课程表格.zip','caa37740-1f53-4160-80ef-0008b7891583','zip',34136,'2023-08-12 13:43:35','2023-08-12 13:43:35'),(66,'课程表格.zip','89b1cf60-f6c7-49f7-ab7c-6f223c1addeb','zip',34136,'2023-08-12 13:45:42','2023-08-12 13:45:42'),(67,'2022_s12.zip','40c40584-40e5-442d-9819-013bd0341fb2','zip',2539566,'2023-08-12 21:18:17','2023-08-12 21:18:17'),(68,'课程表格.zip','228d27f1-4785-4089-9e68-71cfc1a90cd7','zip',34136,'2023-08-12 21:20:55','2023-08-12 21:20:55'),(69,'2022_s12.zip','738472a8-3de8-4b7b-85ae-868a46540bbc','zip',2539566,'2023-08-12 21:32:22','2023-08-12 21:32:22'),(70,'职位库23京考报名表（标红不填）.zip','6084b7b7-62dc-4acd-9cea-5b25f001ecbc','zip',101901,'2023-08-12 21:33:48','2023-08-12 21:33:48'),(71,'职位库23京考报名表（标红不填）.zip','e1a1f62d-85a4-4a96-8af5-0440d41e0abd','zip',101901,'2023-08-12 21:36:15','2023-08-12 21:36:15'),(72,'课程表格.zip','73863c41-b494-48e5-841e-815b8ba701b3','zip',34136,'2023-08-12 22:50:45','2023-08-12 22:50:45'),(74,'2022_s12.zip','ef26d66a-f217-431b-bd05-59903e5af24c','zip',2539566,'2023-08-13 15:09:25','2023-08-13 15:09:25'),(75,'课程表格.zip','32fa76e6-6202-48c4-84cb-6a7c5fb5cd1f','zip',34136,'2023-08-13 15:20:07','2023-08-13 15:20:07'),(76,'课程表格.zip','af780e57-511a-4f1d-ba15-f749573f7086','zip',34136,'2023-08-13 15:22:23','2023-08-13 15:22:23'),(78,'职位库23京考报名表（标红不填）.zip','2dcd2adf-3d1b-4da8-bb12-c816223df44c','zip',101901,'2023-08-13 15:26:38','2023-08-13 15:26:38'),(83,'课程表格.zip','e6764ee3-d3d6-4fae-9a6e-45b8c913f331','zip',34136,'2023-08-13 15:45:43','2023-08-13 15:45:43'),(85,'职位库23京考报名表（标红不填）.zip','7ac16ab4-371d-4fef-bcce-ec00694a292b','zip',101901,'2023-08-13 15:50:37','2023-08-13 15:50:37'),(86,'课程表格.zip','bac8e2d2-db77-4d9c-806e-de331c9df76c','zip',34136,'2023-08-13 15:52:45','2023-08-13 15:52:45'),(87,'职位库23京考报名表（标红不填）.zip','d7061e9b-3253-443f-ae5d-2dc9dc8bad67','zip',101901,'2023-08-13 15:55:57','2023-08-13 15:55:57'),(93,'课程表格.zip','7e989f41-1bb8-46df-adb4-d81cec08a9ec','zip',34136,'2023-08-13 16:36:08','2023-08-13 16:36:08'),(94,'职位库23京考报名表（标红不填）.zip','67bff313-13a6-42c1-abf5-2ea82a62e816','zip',101901,'2023-08-13 16:39:32','2023-08-13 16:39:32'),(95,'课程表格.zip','42affae1-e309-4628-93dd-30bf58af94e1','zip',34136,'2023-08-13 16:47:06','2023-08-13 16:47:06'),(96,'特征识别平台.zip','a66bd977-82c5-4d3b-9a97-f3aa7c5f24a7','zip',4975585,'2023-08-13 18:32:36','2023-08-13 18:32:36'),(97,'特征识别平台.zip','22733e0a-9afc-48f6-bef9-8a7c5e293023','zip',4975585,'2023-08-13 22:52:15','2023-08-13 22:52:15'),(98,'DG5501488_x64.ZIP','52d5c73d-1c0f-4e2b-9956-6ac09345ece5','ZIP',39449361,'2023-08-13 22:52:56','2023-08-13 22:52:56'),(102,'特征识别平台.zip','3d5a1466-51dc-4da7-ab9f-01eaae60a568','zip',4975585,'2023-08-13 23:00:45','2023-08-13 23:00:45'),(104,'测试算法文件.zip','a45af1c1-a66a-4a80-b3e7-2ae6c743907f','zip',4975585,'2023-08-14 09:53:48','2023-08-14 09:53:48'),(105,'aDrive.zip','ca7c61d3-d97d-412f-afc0-082bee7c3ea6','zip',97688727,'2023-08-20 17:57:02','2023-08-20 17:57:02'),(106,'算法文件.zip','618a847f-58e0-475f-93b2-4625f20bc185','zip',31659,'2023-08-20 22:14:18','2023-08-20 22:14:18'),(107,'算法文件 (1).zip','168fbc34-98ba-49c8-8d77-4a7c01c2d4d1','zip',31659,'2023-08-21 23:21:22','2023-08-21 23:21:22'),(108,'算法文件.zip','0a4006d8-d358-4e15-87d1-32846dd85e3d','zip',31659,'2023-08-21 23:44:47','2023-08-21 23:44:47'),(109,'aDrive.zip','4ed7c314-e03f-4c51-bc06-eb1a62cf29a6','zip',97688727,'2023-08-21 23:56:53','2023-08-21 23:56:53'),(113,'123.zip','b7acbe77-39fc-4690-bd43-daa8c0272190','zip',22,'2023-08-24 21:10:52','2023-08-24 21:10:52'),(115,'课程表格.zip','5524eb8d-c417-49ca-a54e-aea39cbe2748','zip',34136,'2023-08-24 21:41:06','2023-08-24 21:41:06'),(116,'课程表格.zip','637d27d9-6ebf-4449-ba63-c83b057f86ae','zip',34136,'2023-08-24 21:44:13','2023-08-24 21:44:13'),(117,'职位库23京考报名表（标红不填）.zip','ea03c215-bedf-4faa-950a-19e6754fd15a','zip',101901,'2023-08-24 21:47:33','2023-08-24 21:47:33'),(118,'2022_s12.zip','c27af991-df5b-422c-aed2-90cfcdad4aed','zip',2539566,'2023-08-24 21:49:59','2023-08-24 21:49:59'),(119,'123.zip','e3097710-d45a-4331-bb63-569e05708e1c','zip',22,'2023-08-24 22:36:33','2023-08-24 22:36:33'),(120,'123.zip','00d3facd-c806-447d-a168-05f22a3153f6','zip',22,'2023-08-24 23:23:31','2023-08-24 23:23:31'),(121,'123.zip','9e37dcce-10f5-4d24-b100-09088b4b4249','zip',22,'2023-08-24 23:23:53','2023-08-24 23:23:53'),(122,'123.zip','97e8f243-3ce0-4b42-9105-6c4e94b8cb43','zip',22,'2023-08-24 23:36:08','2023-08-24 23:36:08'),(123,'123.zip','5a231e74-f764-45ea-81f9-16bed61d27fc','zip',22,'2023-08-24 23:37:03','2023-08-24 23:37:03'),(124,'123.zip','0b3e6bad-d8dc-4f87-a0c2-678d47029f93','zip',22,'2023-08-24 23:59:28','2023-08-24 23:59:28'),(125,'123.zip','3f53baf7-ce07-4f3d-ba42-796f1400a337','zip',22,'2023-08-25 00:00:13','2023-08-25 00:00:13'),(126,'123.zip','1fa01764-82ec-42ed-b1bb-1a72604bae3f','zip',22,'2023-08-25 00:08:26','2023-08-25 00:08:26'),(127,'算法文件.zip','b2602fcb-1e9a-48d6-bc30-83e63ca53cca','zip',22,'2023-08-25 19:39:08','2023-08-25 19:39:08'),(128,'算法文件.zip','c3a5f3c1-ebe3-40fe-8598-eaeb9adb509f','zip',22,'2023-08-25 19:41:25','2023-08-25 19:41:25'),(129,'算法文件.zip','a0b7a696-8230-4837-ac50-c4f0ef80bb12','zip',22,'2023-08-25 19:42:26','2023-08-25 19:42:26'),(130,'算法文件.zip','276ccd67-800c-419f-9033-3f4c0663877d','zip',22,'2023-08-25 20:11:00','2023-08-25 20:11:00'),(131,'算法文件.zip','b400d278-0612-42e7-b5da-203f0b5e8cd8','zip',22,'2023-08-25 20:14:22','2023-08-25 20:14:22'),(132,'算法文件.zip','2d105f3a-6766-4504-b2b9-64a00ca75650','zip',22,'2023-08-25 20:30:31','2023-08-25 20:30:31'),(133,'算法文件.zip','ac388d3f-64a4-443d-9dc3-4a8f9a9ee5d1','zip',22,'2023-08-25 20:32:00','2023-08-25 20:32:00'),(134,'算法文件.zip','f8ac5980-1e50-4ab3-a169-b2711eacbca1','zip',22,'2023-08-25 20:54:00','2023-08-25 20:54:00'),(135,'数据集文件.zip','dad4a009-2e9b-4797-8079-07339eddfe62','zip',22,'2023-08-25 20:54:29','2023-08-25 20:54:29'),(136,'课程表格.zip','dd9441e3-8eec-4252-a28a-05e93273b5d2','zip',34136,'2023-08-25 21:16:23','2023-08-25 21:16:23'),(137,'职位库23京考报名表（标红不填）.zip','f0751326-1a51-40fe-a759-2c3cb6247221','zip',101901,'2023-08-25 21:20:59','2023-08-25 21:20:59'),(138,'算法文件.zip','b3927f6f-cd34-4fe4-9f69-53a55b52bbb0','zip',22,'2023-08-26 12:18:09','2023-08-26 12:18:09'),(139,'算法文件.zip','f4181fe8-6842-48ef-aef1-377252e81fdf','zip',22,'2023-08-26 12:18:15','2023-08-26 12:18:15'),(140,'数据集文件.zip','5c0b5abf-6feb-4707-b37d-3038d29c4243','zip',22,'2023-08-26 12:18:28','2023-08-26 12:18:28'),(141,'数据集文件.zip','6175fa1e-4dcd-4885-b092-1325d4d2a163','zip',22,'2023-08-26 12:18:33','2023-08-26 12:18:33'),(142,'算法文件.zip','a9d19e1d-512f-49b3-bd1d-093c48e98aa9','zip',22,'2023-08-26 12:28:48','2023-08-26 12:28:48'),(143,'职位库23京考报名表（标红不填）.zip','05504ed1-5987-4926-88a1-f09ba91b2078','zip',101901,'2023-08-28 22:05:25','2023-08-28 22:05:25'),(144,'课程表格.zip','2548455c-41d0-408b-8856-7c55a2767cb8','zip',34136,'2023-08-28 22:10:01','2023-08-28 22:10:01'),(145,'2022_s12.zip','d1b4693e-dcef-4f13-9119-d901522cde9e','zip',2539566,'2023-08-28 22:14:12','2023-08-28 22:14:12'),(146,'2022_s12.zip','642df7e3-1318-4457-b621-a08657a81b75','zip',2539566,'2023-08-28 22:15:34','2023-08-28 22:15:34'),(147,'数据集文件.zip','146ca066-b4fd-454e-b5b3-bd34d206d364','zip',22,'2023-08-30 00:07:43','2023-08-30 00:07:43'),(148,'数据集文件.zip','6e2eefb3-fc25-4baf-83c3-fad4a432e9fe','zip',22,'2023-08-30 00:42:55','2023-08-30 00:42:55'),(149,'算法文件.zip','14c481d6-160c-40aa-be42-b74ece9c1ded','zip',22,'2023-08-30 21:45:19','2023-08-30 21:45:19'),(150,'算法文件.zip','6e9553f0-2c04-44f0-b8a2-db77e4678c6f','zip',22,'2023-08-30 21:46:51','2023-08-30 21:46:51'),(151,'算法文件.zip','ae93811f-3fe9-41ab-8163-279151440323','zip',22,'2023-08-30 23:30:20','2023-08-30 23:30:20'),(152,'数据集文件.zip','5059b39d-73d1-4d06-bda3-0b3baca547df','zip',22,'2023-08-30 23:31:46','2023-08-30 23:31:46');

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `session` (
                           `id` varchar(255) NOT NULL COMMENT 'id',
                           `user_id` int DEFAULT NULL COMMENT '用户ID',
                           `ip` varchar(255) DEFAULT NULL COMMENT 'ip地址',
                           `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录session表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session`
--

INSERT INTO `session` VALUES ('8d88dc18-99e4-45b1-b871-e50f929ae54e',1,'192.168.0.100','2023-08-31 20:28:10'),('f65fea20-5087-4b1e-974f-6644871a5401',1,'192.168.0.104','2023-08-31 20:56:33'),('82d8532c-a082-4b7c-971f-0543c39f6ad5',13,'192.168.0.104','2023-08-31 20:56:44'),('78c93c64-f617-4de4-a834-926fe185d850',1,'127.0.0.1','2023-09-05 14:46:33');

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
                                  `user_id` int DEFAULT NULL COMMENT '添加人',
                                  `mark` text COMMENT '备注',
                                  `create_time` datetime DEFAULT NULL COMMENT '授权时间 ',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='体育类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport_category`
--

INSERT INTO `sport_category` VALUES (1,'0','田径',1,'备注','2023-08-14 17:47:35'),(2,'0','铅球',1,'备注','2023-07-26 13:47:32'),(5,'0','立定跳远',1,'备注','2023-07-26 13:47:32'),(6,'1','羽毛球',1,'备注','2023-07-26 13:47:32'),(7,'1','篮球',1,'备注','2023-07-26 13:47:32'),(8,'1','游泳',1,'备注','2023-07-26 13:47:32'),(9,'2','击剑',1,'备注','2023-07-26 13:47:32'),(11,'2','乒乓球',1,'备注','2023-07-26 13:47:32'),(12,'0','网球',1,'网球添加2','2023-08-14 17:49:36'),(13,'1','铅球',1,'汪汪汪','2023-07-27 15:26:35');

--
-- Table structure for table `sports_ability`
--

DROP TABLE IF EXISTS `sports_ability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sports_ability` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `user_id` int NOT NULL COMMENT '用户ID',
                                  `ability_type` tinyint(1) NOT NULL COMMENT '0有氧能力,1肌肉力量,2柔韧伸展,3灵活敏捷,4动作准确',
                                  `value` int NOT NULL DEFAULT '0' COMMENT '能力值',
                                  `create_time` datetime NOT NULL COMMENT '创建时间',
                                  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户体育能力表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports_ability`
--


--
-- Table structure for table `study_data`
--

DROP TABLE IF EXISTS `study_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_data` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `user_id` int NOT NULL COMMENT '用户ID',
                              `course_id` int NOT NULL COMMENT '课程ID',
                              `duration` int NOT NULL DEFAULT '0' COMMENT '学习时长（秒）',
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户学习数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_data`
--


--
-- Table structure for table `study_record`
--

DROP TABLE IF EXISTS `study_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_record` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `user_id` int NOT NULL COMMENT '用户ID',
                                `course_id` int NOT NULL COMMENT '课程ID',
                                `lesson_id` int NOT NULL COMMENT '课时ID',
                                `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '学习状态，0-未学习，1-学习中，2-已学完',
                                `start_time` datetime DEFAULT NULL COMMENT '开始学习时间',
                                `end_time` datetime DEFAULT NULL COMMENT '结束学习时间',
                                `create_time` datetime NOT NULL COMMENT '创建时间',
                                `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户学习记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_record`
--


--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `template` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL COMMENT '模板名',
                            `content` json DEFAULT NULL COMMENT '模板内容',
                            `create_time` datetime DEFAULT NULL,
                            `mark` text COMMENT '备注',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

INSERT INTO `template` VALUES (1,'通用模板','{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}','2023-08-14 17:49:52','修改后备注'),(2,'篮球类模板','{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}','2023-08-14 17:50:06','备注'),(3,'足球类模板','{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}','2023-08-14 17:50:16','1211111'),(4,'乒乓球类模板','{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}','2023-08-14 17:51:05',NULL),(5,'模板名','{\"distance\": \"number\", \"player_name\": \"string\", \"follow_through\": \"string\"}','2023-08-26 19:33:31','123');

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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,0,'王子健','root','$argon2id$v=19$m=65536,t=2,p=1$1VdMY/Yxaal5oXNyd10g5A$jNxdp7UHCVNgp8M5EV9lkRi15ZheMaRbSqKGpWuNCbI','密码123456','2023-07-27 15:02:54','{}'),(4,0,'程序员小王','wangzijian121','$argon2id$v=19$m=65536,t=2,p=1$Qpo5pGW3a20epFq6MXHbaA$out6LUQ8s7AcFi1USfCMQ4cfNQ9NZczA9Q4QRE9/Ht4','小明','2023-07-27 15:08:12','{}'),(6,0,'程序员小王','wangzijian123','$argon2id$v=19$m=65536,t=2,p=1$8DLavyqz+npDmF3DT2wI5g$XE/GCCbR5K7TDFmdxenuByhfMhdaFLals2APTPYtwWo','开发者','2023-07-23 22:24:19','{}'),(12,1,'程序员小王12','wangzijian','$argon2id$v=19$m=65536,t=2,p=1$g6qS43s+np6mh2r0x4Hp7w$/vzi1nsJrmpXl4sFJpWDX1yZSasWO0fqOHduk218uj4','支付宝：10086','2023-08-26 04:22:48','{\"phone\": \"18846922264\"}'),(13,1,'程序员小王13','wangzijian13','$argon2id$v=19$m=65536,t=2,p=1$qLOynwPpnMNMD9rvPUYdSw$sJwlQGaHl3brLdzvy7mzIxw+6PgGHg2fO1L4cKQXkl8','支付宝：10086','2023-08-26 04:23:05','{\"phone\": \"10086\"}'),(14,0,'admin','admin','$argon2id$v=19$m=65536,t=2,p=1$azoAkY126sM6d8rkKX+7OA$CXq1B2tm27jC3tkzAVbJKk3yunK9aOti+gE2WzLgin0',NULL,'2023-08-31 00:05:19',NULL),(15,3,'学生小王','18846922264',NULL,NULL,NULL,'{\"login_count\": 5, \"lastLogintime\": \"2023-09-05 00:05:00\"}');

--
-- Table structure for table `wechat`
--

DROP TABLE IF EXISTS `wechat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wechat` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `wechat_id` varchar(255) DEFAULT NULL COMMENT '小程序ID',
                          `name` varchar(255) DEFAULT NULL COMMENT '小程序名',
                          `institution_id` int DEFAULT NULL COMMENT '机构ID',
                          `status` int DEFAULT NULL COMMENT '进度(0已部署，1审核中)',
                          `mark` text COMMENT '备注',
                          `create_time` datetime DEFAULT NULL COMMENT '授权时间',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='小程序表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wechat`
--

INSERT INTO `wechat` VALUES (1,'wxc62afc1444173666','汇通小程序',1,0,'润迪开通的小助手','2023-07-28 09:49:59'),(2,'wxc62afc1444173777','王子健小程序',3,1,'备注','2023-07-28 11:31:30'),(3,'wxc62afc1444173999','哈尔滨2小程序',16,1,'123','2023-09-07 01:07:02'),(4,'wxc62afc1444173888','润迪专属小程序',2,1,NULL,'2023-08-14 07:34:45');

--
-- Table structure for table `wx_report_data`
--

DROP TABLE IF EXISTS `wx_report_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wx_report_data` (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                  `app_id` varchar(255) DEFAULT NULL COMMENT '小程序ID',
                                  `use_count_today` int DEFAULT NULL COMMENT '今日使用人数',
                                  `user_usage_time_today` int DEFAULT NULL COMMENT '今日用户使用时长总和(秒)',
                                  `use_algorithm_count_today` int DEFAULT NULL COMMENT '算法使用次数',
                                  `algorithm_type_today` json DEFAULT NULL COMMENT '{算法种类 : 使用次数}',
                                  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='微信用户数据上报表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wx_report_data`
--

INSERT INTO `wx_report_data` VALUES (1,'1',8,800,20,'{\"篮球\": 4, \"足球\": 16}',NULL),(12,'wxc62afc144417346e',1,1,1,'123','2023-09-05 23:25:29');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-07  2:15:50
