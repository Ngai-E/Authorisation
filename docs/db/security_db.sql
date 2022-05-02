/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100421
 Source Host           : localhost:3306
 Source Schema         : security_db

 Target Server Type    : MySQL
 Target Server Version : 100421
 File Encoding         : 65001

 Date: 03/05/2022 00:42:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_country_codes
-- ----------------------------
DROP TABLE IF EXISTS `t_country_codes`;
CREATE TABLE `t_country_codes`  (
  `KEY_COUNTRY_CODE` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_country_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`KEY_COUNTRY_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_country_codes
-- ----------------------------
INSERT INTO `t_country_codes` VALUES ('237', 'CAMEROON');

-- ----------------------------
-- Table structure for t_paramters
-- ----------------------------
DROP TABLE IF EXISTS `t_paramters`;
CREATE TABLE `t_paramters`  (
  `str_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `b_status` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`str_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_paramters
-- ----------------------------
INSERT INTO `t_paramters` VALUES ('JWT_ENCRYPTION_KEY', '2021@Ad', 'key used to sign jwt tokens.', 1);
INSERT INTO `t_paramters` VALUES ('JWT_TOKEN_EXPIRATION_TIME', '1800000', 'time for bearer token to expire in milliseconds', 1);

-- ----------------------------
-- Table structure for t_token
-- ----------------------------
DROP TABLE IF EXISTS `t_token`;
CREATE TABLE `t_token`  (
  `lg_token_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_refresh` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dt_created` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`lg_token_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_token
-- ----------------------------
INSERT INTO `t_token` VALUES ('1f727130-2f31-4d04-b63c-5c3c8de0ab0c', '8089fbaa-5f14-4144-ad71-8c947f91ecb0', 'active', '2022-05-02 22:14:59');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dt_created` datetime(0) NULL DEFAULT NULL,
  `dt_updated` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `user status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1f727130-2f31-4d04-b63c-5c3c8de0ab0c', 'Ngai', 'Ngai_e', '237650931636', '$2a$10$5LwOwU6JnmG7Tfcsjem4hOLGcQfpCEFHMfcaxEK22wLj6K2vJjpBW', 'active', '2022-05-02 11:39:13', NULL);

SET FOREIGN_KEY_CHECKS = 1;
