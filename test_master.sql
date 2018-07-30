/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : test_master

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-30 16:06:53
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('1', '小花_master');
