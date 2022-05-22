/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 13/05/2022 19:38:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (4, '仓储部', 'Warehousing Department');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (5, '财务部', 'Finance Department');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (6, '技术部', 'Technolog Department ');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (7, '测试部门', 'test');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (8, '摸鱼部门', 'moyu');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (9, 'hhhhhh', 'hhhhhh');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (10, '睡觉部门', 'sleep');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (11, '游戏部门', 'game');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (12, '采购部)))))))))', 'Order Department>>>>>>>>>');
INSERT INTO `department` (`id`, `name`, `sn`) VALUES (13, '采购部', 'Order Department7777777');
COMMIT;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
BEGIN;
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (1, 'admin', '1', 'admin@abc.com', 20, b'1', 7);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (4, '钱总', '1', 'xx@xx.com', 35, b'0', 2);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (5, '钱二明', '1', 'xx@xx.com', 25, b'0', 4);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (6, '孙总', '111', 'xx@xx.com', 35, b'0', 3);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (7, '孙三明', '123', 'xx@xx.com', 25, b'0', 3);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (8, '李总', '1', 'xx@xx.com', 35, b'0', 4);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (9, '李四明', '44', 'xx@xx.com', 25, b'0', 4);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (10, '周总', '345', 'xx@xx.com', 35, b'0', 5);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (11, '周五明', '4545', 'xx@xx.com', 25, b'0', 5);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (12, '吴总', '2334', 'xx@xx.com', 35, b'0', 6);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (13, '吴六明', '23454', 'xx@xx.com', 25, b'0', 6);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (14, '郑总', '2356236', 'xx@xx.com', 35, b'0', 7);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (15, '郑七明', '236', 'xx@xx.com', 25, b'0', 7);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (16, '孙四明', '2345', 'xx@xx.com', 25, b'0', 3);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (17, '孙五明', '666', 'xx@xx.com', 25, b'0', 3);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (18, '李五明', '334', 'xx@xx.com', 25, b'0', 4);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (19, '李六明', '66445', 'xx@xx.com', 25, b'0', 4);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (22, 'leons9th', '1', 'leons9th@sina.com', 11, b'0', 9);
INSERT INTO `employee` (`id`, `name`, `password`, `email`, `age`, `admin`, `dept_id`) VALUES (24, 'leons9th', '111', 'leons9th@sina.com', 11, b'0', 8);
COMMIT;

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `expression` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` (`id`, `name`, `expression`) VALUES (3, '部门保存或更新', '/department/saveOrUpdate');
INSERT INTO `permission` (`id`, `name`, `expression`) VALUES (4, '部门编辑', '/department/input');
INSERT INTO `permission` (`id`, `name`, `expression`) VALUES (5, '部门删除', '/department/delete');
INSERT INTO `permission` (`id`, `name`, `expression`) VALUES (6, '部门列表', '/department/list');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `name`, `sn`) VALUES (1, '人事管理', 'HR MGR');
INSERT INTO `role` (`id`, `name`, `sn`) VALUES (2, '采购管理', 'ORDER MGR');
INSERT INTO `role` (`id`, `name`, `sn`) VALUES (3, '仓储管理', 'WAREHOUSING MGR');
INSERT INTO `role` (`id`, `name`, `sn`) VALUES (4, '行政部管理', 'Admin MGR');
INSERT INTO `role` (`id`, `name`, `sn`) VALUES (7, 'leons9th', 'jyfjhg');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
