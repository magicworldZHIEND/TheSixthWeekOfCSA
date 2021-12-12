/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50616
Source Host           : localhost:3306
Source Database       : thesixthweek

Target Server Type    : MYSQL
Target Server Version : 50616
File Encoding         : 65001

Date: 2021-12-13 00:21:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `SNO` varchar(20) DEFAULT NULL,
  `Name` varchar(10) DEFAULT NULL,
  `Age` int(11) DEFAULT NULL,
  `College` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('s001', '老大', '20', '通信学院');
INSERT INTO `student` VALUES ('s002', '老二', '19', '计算机学院');
INSERT INTO `student` VALUES ('s003', '老三', '18', '计算机学院');
