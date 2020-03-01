/*
 Navicat MySQL Data Transfer

 Source Server         : AlwaysXu
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : stu

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 01/03/2020 21:37:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admid
-- ----------------------------
DROP TABLE IF EXISTS `admid`;
CREATE TABLE `admid`  (
  `user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `passwd` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admid
-- ----------------------------
INSERT INTO `admid` VALUES ('admin', '123456');

-- ----------------------------
-- Table structure for stu
-- ----------------------------
DROP TABLE IF EXISTS `stu`;
CREATE TABLE `stu`  (
  `stuId` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `stuName` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stuSex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stuAge` int(11) NULL DEFAULT NULL,
  `stuJG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stuDept` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`stuId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stu
-- ----------------------------
INSERT INTO `stu` VALUES ('0001', '徐斌', '男', 19, '吉安', '计科');
INSERT INTO `stu` VALUES ('0002', '李一一', '男', 21, '乐安', '通信');
INSERT INTO `stu` VALUES ('0003', '李一一', '男', 21, '乐安', '111');

-- ----------------------------
-- Table structure for stuid
-- ----------------------------
DROP TABLE IF EXISTS `stuid`;
CREATE TABLE `stuid`  (
  `user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `passwd` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stuid
-- ----------------------------
INSERT INTO `stuid` VALUES ('20172361', '2361');
INSERT INTO `stuid` VALUES ('20172371', '2371');

-- ----------------------------
-- Table structure for sturesume
-- ----------------------------
DROP TABLE IF EXISTS `sturesume`;
CREATE TABLE `sturesume`  (
  `num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `relationship` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `class` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `college` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `experience1` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `experience2` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `experience3` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sturesume
-- ----------------------------
INSERT INTO `sturesume` VALUES ('20172361', '徐斌', '男', '共青团员', '计科1701', '计算机信息与工程学院', '获得XXX奖', '在XXX比赛中获', '无');
INSERT INTO `sturesume` VALUES ('20172371', '夏经埔', '男', '共青团员', '计科1702', '计算机信息与工程学院', '代表学院参加XXX', '在XXX比赛中获得', '代表学校参加XXX');

-- ----------------------------
-- Table structure for teaid
-- ----------------------------
DROP TABLE IF EXISTS `teaid`;
CREATE TABLE `teaid`  (
  `user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `passwd` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teaid
-- ----------------------------
INSERT INTO `teaid` VALUES ('20001212', '1212');

-- ----------------------------
-- Table structure for 计科1701
-- ----------------------------
DROP TABLE IF EXISTS `计科1701`;
CREATE TABLE `计科1701`  (
  `num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `班级` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `C语言` int(11) NULL DEFAULT NULL,
  `面向对象的程序设计` int(11) NULL DEFAULT NULL,
  `Java` int(11) NULL DEFAULT NULL,
  `总分` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 计科1701
-- ----------------------------
INSERT INTO `计科1701` VALUES ('20172361', '计科1701', '徐斌', 88, 88, 88, 264);
INSERT INTO `计科1701` VALUES ('20172362', '计科1701', '姚晨', 100, 100, 100, 300);
INSERT INTO `计科1701` VALUES ('20172363', '计科1701', '刘发鑫', 90, 90, 90, 270);
INSERT INTO `计科1701` VALUES ('20172364', '计科1701', '彭艺', 89, 89, 89, 267);
INSERT INTO `计科1701` VALUES ('20172366', '计科1701', '徐志翔', 98, 98, 98, 294);
INSERT INTO `计科1701` VALUES ('20172391', '计科1701', '曹佳杰', 91, 91, 91, 273);
INSERT INTO `计科1701` VALUES ('20172394', '计科1701', '杰洛特', 99, 98, 97, 294);
INSERT INTO `计科1701` VALUES ('20172395', '计科1701', '曹杰', 95, 95, 95, 285);
INSERT INTO `计科1701` VALUES ('20172399', '计科1701', '楼发生', 85, 84, 83, 252);
INSERT INTO `计科1701` VALUES ('20172400', '计科1701', '桃白白', 77, 78, 79, 234);
INSERT INTO `计科1701` VALUES ('20172401', '计科1701', '刘青山', 72, 72, 72, 216);
INSERT INTO `计科1701` VALUES ('20172402', '计科1701', '孙悟空', 79, 78, 77, 234);
INSERT INTO `计科1701` VALUES ('20172403', '计科1701', '孙悟饭', 81, 81, 81, 243);
INSERT INTO `计科1701` VALUES ('20172404', '计科1701', '孙悟天', 81, 82, 83, 246);
INSERT INTO `计科1701` VALUES ('20172405', '计科1701', '鹤笔翁', 91, 92, 93, 276);
INSERT INTO `计科1701` VALUES ('20172406', '计科1701', '鹿杖客', 85, 86, 87, 258);
INSERT INTO `计科1701` VALUES ('20172407', '计科1701', '纪晓芙', 95, 94, 93, 282);

-- ----------------------------
-- Table structure for 计科1702
-- ----------------------------
DROP TABLE IF EXISTS `计科1702`;
CREATE TABLE `计科1702`  (
  `num` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `班级` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `C语言` int(11) NULL DEFAULT NULL,
  `面向对象的程序设计` int(11) NULL DEFAULT NULL,
  `Java` int(11) NULL DEFAULT NULL,
  `总分` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 计科1702
-- ----------------------------
INSERT INTO `计科1702` VALUES ('20172371', '计科1702', '夏经埔', 90, 90, 90, 270);
INSERT INTO `计科1702` VALUES ('20172374', '计科1702', '曹佳杰', 87, 87, 87, 261);
INSERT INTO `计科1702` VALUES ('20172375', '计科1702', '罗云飞', 99, 99, 99, 297);
INSERT INTO `计科1702` VALUES ('20172378', '计科1702', '张伟', 68, 68, 68, 204);
INSERT INTO `计科1702` VALUES ('20172379', '计科1702', '天诺', 68, 67, 66, 201);
INSERT INTO `计科1702` VALUES ('20172383', '计科1702', '张益达', 86, 86, 86, 258);

SET FOREIGN_KEY_CHECKS = 1;
