/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : test_read2

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-30 16:07:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('1', '小花2');
