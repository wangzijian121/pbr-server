-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pbr-scmp
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
                             `name` varchar(50) NOT NULL COMMENT '算法名称',
                             `description` varchar(500) DEFAULT NULL COMMENT '算法描述',
                             `create_time` datetime NOT NULL COMMENT '创建时间',
                             `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机构算法表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `algorithm`
--


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
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

INSERT INTO `resources` VALUES (19,'特征识别平台.zip','4b4901b8-7af4-444a-b044-3067902cfaf2','zip',4975585,'2023-08-04 00:24:21','2023-08-04 00:24:21'),(20,'特征识别平台.zip','a2279d7d-f6d0-424f-891b-34a892680d35','zip',4975585,'2023-08-04 22:22:56','2023-08-04 22:22:56'),(21,'特征识别平台.zip','f91af1f9-7452-4cb4-847c-978b8bd6d2ae','zip',4975585,'2023-08-04 22:23:29','2023-08-04 22:23:29'),(22,'特征识别平台.zip','8fef3000-edf4-4944-809e-f580565879f4','zip',4975585,'2023-08-05 17:33:19','2023-08-05 17:33:19'),(23,'DG5501488_x64.ZIP','1cbee4af-507a-4102-9c24-22b4c0911c98','ZIP',39449361,'2023-08-05 17:58:33','2023-08-05 17:58:33'),(24,'特征识别平台.zip','879fbbe5-49f0-4a9f-8fbf-602fc8537ced','zip',4975585,'2023-08-08 17:58:37','2023-08-05 17:58:37'),(26,'2022_s12.zip','3d61be63-3c33-4347-bb25-a35ab0650548','zip',2539566,'2023-08-11 22:06:44','2023-08-11 22:06:44'),(27,'2022_s12.zip','1a682bda-31d1-4844-8869-ef3af9fd9c2e','zip',2539566,'2023-08-11 22:27:06','2023-08-11 22:27:06'),(28,'2022_s12.zip','f1107029-e73d-416b-bfef-0f6ca2d2aee1','zip',2539566,'2023-08-11 22:27:32','2023-08-11 22:27:32'),(29,'2022_s12.zip','14cb8363-46f6-47a4-a3d4-8ed46b44b295','zip',2539566,'2023-08-11 22:28:18','2023-08-11 22:28:18'),(30,'2022_s12.zip','43a90608-4d97-4b17-9c14-d42e9fb60bf7','zip',2539566,'2023-08-11 22:29:36','2023-08-11 22:29:36'),(31,'2022_s12.zip','f2174274-6195-404b-bcd7-33058ef7555e','zip',2539566,'2023-08-11 22:34:20','2023-08-11 22:34:20'),(32,'2022_s12.zip','0367ba90-46ca-499a-9380-1e599ca93012','zip',2539566,'2023-08-11 22:41:12','2023-08-11 22:41:12'),(33,'课程表格.zip','86d40568-774e-4b83-ab6e-b5afde4725bf','zip',34136,'2023-08-11 22:41:22','2023-08-11 22:41:22'),(34,'2022_s12.zip','5bdfc3d9-1de0-4339-8ec5-7e7921361b3b','zip',2539566,'2023-08-11 22:42:31','2023-08-11 22:42:31'),(35,'课程表格.zip','f02eb0ec-2ece-4183-911e-d2e24af11586','zip',34136,'2023-08-11 22:44:11','2023-08-11 22:44:11'),(36,'课程表格.zip','ab747dc3-9b46-4b74-8794-a00b588ef7a9','zip',34136,'2023-08-11 22:45:27','2023-08-11 22:45:27'),(37,'课程表格.zip','06c07b24-ee14-400e-8247-776855c9e264','zip',34136,'2023-08-11 22:46:20','2023-08-11 22:46:20'),(38,'2022_s12.zip','897fa69d-67be-4bf5-af10-a333adf9f49a','zip',2539566,'2023-08-11 22:46:50','2023-08-11 22:46:50'),(39,'特征识别平台.zip','36786656-3ec3-4f4f-b690-6a9521a71665','zip',4975585,'2023-08-11 22:49:16','2023-08-11 22:49:16'),(40,'课程表格.zip','3cf8f9c9-0633-4f1a-a817-c552eb3f3592','zip',34136,'2023-08-11 22:49:18','2023-08-11 22:49:18'),(41,'特征识别平台.zip','f1afdff3-819c-4442-83f3-0f1d2475f02f','zip',4975585,'2023-08-11 22:54:04','2023-08-11 22:54:04'),(44,'2022_s12.zip','c255a067-cbd8-47a7-8cf9-128cf83aec98','zip',2539566,'2023-08-12 11:04:47','2023-08-12 11:04:47'),(45,'课程表格.zip','e5c921c9-bee1-4055-b58b-3887734795b3','zip',34136,'2023-08-12 11:05:59','2023-08-12 11:05:59'),(46,'2022_s12.zip','68a1ecaa-9904-41ca-9ef1-ed353149cb90','zip',2539566,'2023-08-12 11:27:57','2023-08-12 11:27:57'),(47,'2022_s12.zip','451eec3b-bb82-4878-9580-363fecd3922f','zip',2539566,'2023-08-12 11:32:10','2023-08-12 11:32:10'),(48,'课程表格.zip','8759a928-fc51-478a-b2aa-2f278d84892b','zip',34136,'2023-08-12 11:33:21','2023-08-12 11:33:21'),(49,'2022_s12.zip','8d948cb6-50be-4581-a981-6ae73636c98a','zip',2539566,'2023-08-12 11:37:08','2023-08-12 11:37:08'),(50,'课程表格.zip','78645b0f-83eb-4ef3-8f7d-9c071a123628','zip',34136,'2023-08-12 11:37:30','2023-08-12 11:37:30'),(51,'课程表格.zip','15d54ad7-eef2-43b2-a1b9-d4aacc1d2035','zip',34136,'2023-08-12 11:39:11','2023-08-12 11:39:11'),(53,'2022_s12.zip','74c9c4a1-2ea3-49a1-85b5-27ac351ba469','zip',2539566,'2023-08-12 11:42:25','2023-08-12 11:42:25'),(54,'课程表格.zip','47d5ce7e-f783-4f5e-86a9-fcaf647c0723','zip',34136,'2023-08-12 11:45:34','2023-08-12 11:45:34'),(55,'职位库23京考报名表（标红不填）.zip','7ceaf0a3-7702-4777-b6b4-19812f57eebc','zip',101901,'2023-08-12 11:47:47','2023-08-12 11:47:47'),(57,'职位库23京考报名表（标红不填）.zip','fa6ccfb9-524d-4c25-975b-bce68307238d','zip',101901,'2023-08-12 12:01:20','2023-08-12 12:01:20'),(58,'2022_s12.zip','5c4112f8-5083-4d21-862b-73efa1a59467','zip',2539566,'2023-08-12 13:02:33','2023-08-12 13:02:33'),(59,'2022_s12.zip','9b3ab7fc-db9a-4dff-9692-f144a80f56fc','zip',2539566,'2023-08-12 13:19:36','2023-08-12 13:19:36'),(60,'课程表格.zip','0d9f01cc-f64d-4b29-b0ec-c2ec89b03ad9','zip',34136,'2023-08-12 13:23:21','2023-08-12 13:23:21'),(61,'课程表格.zip','d1dbfdc9-646f-43f7-b60f-79e74c7a74aa','zip',34136,'2023-08-12 13:25:14','2023-08-12 13:25:14'),(64,'职位库23京考报名表（标红不填）.zip','cc1bc2b0-138f-44b6-bf15-0d2b08f56ca2','zip',101901,'2023-08-12 13:32:56','2023-08-12 13:32:56'),(65,'课程表格.zip','caa37740-1f53-4160-80ef-0008b7891583','zip',34136,'2023-08-12 13:43:35','2023-08-12 13:43:35'),(66,'课程表格.zip','89b1cf60-f6c7-49f7-ab7c-6f223c1addeb','zip',34136,'2023-08-12 13:45:42','2023-08-12 13:45:42'),(67,'2022_s12.zip','40c40584-40e5-442d-9819-013bd0341fb2','zip',2539566,'2023-08-12 21:18:17','2023-08-12 21:18:17'),(68,'课程表格.zip','228d27f1-4785-4089-9e68-71cfc1a90cd7','zip',34136,'2023-08-12 21:20:55','2023-08-12 21:20:55'),(69,'2022_s12.zip','738472a8-3de8-4b7b-85ae-868a46540bbc','zip',2539566,'2023-08-12 21:32:22','2023-08-12 21:32:22'),(70,'职位库23京考报名表（标红不填）.zip','6084b7b7-62dc-4acd-9cea-5b25f001ecbc','zip',101901,'2023-08-12 21:33:48','2023-08-12 21:33:48'),(71,'职位库23京考报名表（标红不填）.zip','e1a1f62d-85a4-4a96-8af5-0440d41e0abd','zip',101901,'2023-08-12 21:36:15','2023-08-12 21:36:15'),(72,'课程表格.zip','73863c41-b494-48e5-841e-815b8ba701b3','zip',34136,'2023-08-12 22:50:45','2023-08-12 22:50:45'),(74,'2022_s12.zip','ef26d66a-f217-431b-bd05-59903e5af24c','zip',2539566,'2023-08-13 15:09:25','2023-08-13 15:09:25'),(75,'课程表格.zip','32fa76e6-6202-48c4-84cb-6a7c5fb5cd1f','zip',34136,'2023-08-13 15:20:07','2023-08-13 15:20:07'),(76,'课程表格.zip','af780e57-511a-4f1d-ba15-f749573f7086','zip',34136,'2023-08-13 15:22:23','2023-08-13 15:22:23'),(78,'职位库23京考报名表（标红不填）.zip','2dcd2adf-3d1b-4da8-bb12-c816223df44c','zip',101901,'2023-08-13 15:26:38','2023-08-13 15:26:38'),(83,'课程表格.zip','e6764ee3-d3d6-4fae-9a6e-45b8c913f331','zip',34136,'2023-08-13 15:45:43','2023-08-13 15:45:43'),(85,'职位库23京考报名表（标红不填）.zip','7ac16ab4-371d-4fef-bcce-ec00694a292b','zip',101901,'2023-08-13 15:50:37','2023-08-13 15:50:37'),(86,'课程表格.zip','bac8e2d2-db77-4d9c-806e-de331c9df76c','zip',34136,'2023-08-13 15:52:45','2023-08-13 15:52:45'),(87,'职位库23京考报名表（标红不填）.zip','d7061e9b-3253-443f-ae5d-2dc9dc8bad67','zip',101901,'2023-08-13 15:55:57','2023-08-13 15:55:57'),(93,'课程表格.zip','7e989f41-1bb8-46df-adb4-d81cec08a9ec','zip',34136,'2023-08-13 16:36:08','2023-08-13 16:36:08'),(94,'职位库23京考报名表（标红不填）.zip','67bff313-13a6-42c1-abf5-2ea82a62e816','zip',101901,'2023-08-13 16:39:32','2023-08-13 16:39:32'),(95,'课程表格.zip','42affae1-e309-4628-93dd-30bf58af94e1','zip',34136,'2023-08-13 16:47:06','2023-08-13 16:47:06'),(96,'特征识别平台.zip','a66bd977-82c5-4d3b-9a97-f3aa7c5f24a7','zip',4975585,'2023-08-13 18:32:36','2023-08-13 18:32:36'),(97,'特征识别平台.zip','22733e0a-9afc-48f6-bef9-8a7c5e293023','zip',4975585,'2023-08-13 22:52:15','2023-08-13 22:52:15'),(98,'DG5501488_x64.ZIP','52d5c73d-1c0f-4e2b-9956-6ac09345ece5','ZIP',39449361,'2023-08-13 22:52:56','2023-08-13 22:52:56'),(102,'特征识别平台.zip','3d5a1466-51dc-4da7-ab9f-01eaae60a568','zip',4975585,'2023-08-13 23:00:45','2023-08-13 23:00:45'),(104,'测试算法文件.zip','a45af1c1-a66a-4a80-b3e7-2ae6c743907f','zip',4975585,'2023-08-14 09:53:48','2023-08-14 09:53:48'),(105,'aDrive.zip','ca7c61d3-d97d-412f-afc0-082bee7c3ea6','zip',97688727,'2023-08-20 17:57:02','2023-08-20 17:57:02'),(106,'算法文件.zip','618a847f-58e0-475f-93b2-4625f20bc185','zip',31659,'2023-08-20 22:14:18','2023-08-20 22:14:18'),(107,'算法文件 (1).zip','168fbc34-98ba-49c8-8d77-4a7c01c2d4d1','zip',31659,'2023-08-21 23:21:22','2023-08-21 23:21:22'),(108,'算法文件.zip','0a4006d8-d358-4e15-87d1-32846dd85e3d','zip',31659,'2023-08-21 23:44:47','2023-08-21 23:44:47'),(109,'aDrive.zip','4ed7c314-e03f-4c51-bc06-eb1a62cf29a6','zip',97688727,'2023-08-21 23:56:53','2023-08-21 23:56:53');

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `type` int DEFAULT NULL COMMENT '0:管理员 1: 学生',
                        `username` varchar(50) NOT NULL COMMENT '用户名',
                        `password` varchar(50) NOT NULL COMMENT '密码',
                        `nickname` varchar(50) NOT NULL COMMENT '昵称',
                        `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
                        `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
                        `duration_of_use` varchar(255) DEFAULT NULL COMMENT '使用时长',
                        `clocking_days` int DEFAULT NULL COMMENT '打卡天数',
                        `create_time` datetime NOT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-04  9:30:53
