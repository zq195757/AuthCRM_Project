/*
 Navicat Premium Data Transfer

 Source Server         : localDB
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : practical_train

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 22/05/2022 11:02:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (14, '财务部3', '5501000');
INSERT INTO `department` VALUES (15, '财务部5', '12305');
INSERT INTO `department` VALUES (16, '财务部4', '1235');
INSERT INTO `department` VALUES (17, '财务部6', '12345');
INSERT INTO `department` VALUES (18, '财务部7', '01651');
INSERT INTO `department` VALUES (19, '技术部1', '2302');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `admin` bit(1) NULL DEFAULT NULL,
  `dept_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (7, '孙三明', '123', 'xx@xx.com', 25, b'0', 5);
INSERT INTO `employee` VALUES (8, '李总', '1', 'xx@xx.com', 35, b'0', 14);
INSERT INTO `employee` VALUES (9, '李四明', '44', 'xx@xx.com', 25, b'0', 4);
INSERT INTO `employee` VALUES (10, '周总', '345', 'xx@xx.com', 35, b'0', 5);
INSERT INTO `employee` VALUES (11, '周五明', '4545', 'xx@xx.com', 25, b'0', 5);
INSERT INTO `employee` VALUES (12, '吴总', '2334', 'xx@xx.com', 35, b'0', 6);
INSERT INTO `employee` VALUES (13, '吴六明', '23454', 'xx@xx.com', 25, b'0', 6);
INSERT INTO `employee` VALUES (14, '郑总', '2356236', 'xx@xx.com', 35, b'0', 7);
INSERT INTO `employee` VALUES (15, '郑七明', '236', 'xx@xx.com', 25, b'0', 7);
INSERT INTO `employee` VALUES (16, '孙四明', '2345', 'xx@xx.com', 25, b'0', 4);
INSERT INTO `employee` VALUES (17, '孙五明', '666', 'xx@xx.com', 25, b'0', 7);
INSERT INTO `employee` VALUES (18, '李五明', '334', 'xx@xx.com', 25, b'0', 4);
INSERT INTO `employee` VALUES (22, 'leons9th', '1', 'leons9th@sina.com', 11, b'0', 9);
INSERT INTO `employee` VALUES (24, 'leons9th', '111', 'leons9th@sina.com', 11, b'0', 8);
INSERT INTO `employee` VALUES (25, 'zs', '123', '123@qq.com', 17, b'0', 4);
INSERT INTO `employee` VALUES (26, 'ls', '123', '123@qq.com', 18, b'0', 6);
INSERT INTO `employee` VALUES (27, 'admin', '123', 'admin123@qq.com', 22, b'1', 11);
INSERT INTO `employee` VALUES (28, 'ws', '123', '123@qq.com', 21, b'0', 5);
INSERT INTO `employee` VALUES (30, 'jua', '123', '1584@qq.com', 24, b'0', 6);
INSERT INTO `employee` VALUES (31, 'zq', '123', '123@qq.com', 20, b'0', 19);

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role`  (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_role
-- ----------------------------
INSERT INTO `employee_role` VALUES (26, 7);
INSERT INTO `employee_role` VALUES (25, 4);
INSERT INTO `employee_role` VALUES (29, 3);
INSERT INTO `employee_role` VALUES (28, 4);
INSERT INTO `employee_role` VALUES (28, 2);
INSERT INTO `employee_role` VALUES (28, 3);
INSERT INTO `employee_role` VALUES (30, 1);
INSERT INTO `employee_role` VALUES (30, 7);
INSERT INTO `employee_role` VALUES (6, 2);
INSERT INTO `employee_role` VALUES (8, 2);
INSERT INTO `employee_role` VALUES (8, 4);
INSERT INTO `employee_role` VALUES (31, 8);
INSERT INTO `employee_role` VALUES (31, 3);
INSERT INTO `employee_role` VALUES (31, 2);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (3, '部门保存或更新', 'department:saveOrUpdate');
INSERT INTO `permission` VALUES (4, '部门编辑', 'department:input');
INSERT INTO `permission` VALUES (5, '部门删除', 'department:delete');
INSERT INTO `permission` VALUES (6, '部门列表', 'department:list');
INSERT INTO `permission` VALUES (7, '员工编辑', 'employee:input');
INSERT INTO `permission` VALUES (8, '员工删除', 'employee:delete');
INSERT INTO `permission` VALUES (9, '员工添加或修改', 'employee:saveOrUpdate');
INSERT INTO `permission` VALUES (10, '员工列表', 'employee:list');
INSERT INTO `permission` VALUES (11, '权限加载', 'permission:reload');
INSERT INTO `permission` VALUES (12, '权限编辑', 'permission:input');
INSERT INTO `permission` VALUES (13, '权限删除', 'permission:delete');
INSERT INTO `permission` VALUES (14, '权限修改或更新', 'permission:saveOrUpdate');
INSERT INTO `permission` VALUES (15, '权限列表', 'permission:list');
INSERT INTO `permission` VALUES (16, '角色编辑', 'role:input');
INSERT INTO `permission` VALUES (17, '角色删除', 'role:delete');
INSERT INTO `permission` VALUES (18, '角色添加或修改', 'role:saveOrUpdate');
INSERT INTO `permission` VALUES (19, '角色列表', 'role:list');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, '采购管理', 'ORDER MGR');
INSERT INTO `role` VALUES (3, '仓储管理', 'WAREHOUSING MGR');
INSERT INTO `role` VALUES (4, '行政部管理', 'Admin MGR');
INSERT INTO `role` VALUES (7, 'leons9th', 'jyfjhg');
INSERT INTO `role` VALUES (8, '运维工', '0124');
INSERT INTO `role` VALUES (9, '技术支持', '123450');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (8, 3);
INSERT INTO `role_permission` VALUES (1, 3);
INSERT INTO `role_permission` VALUES (1, 6);
INSERT INTO `role_permission` VALUES (9, 3);
INSERT INTO `role_permission` VALUES (9, 4);
INSERT INTO `role_permission` VALUES (10, 4);
INSERT INTO `role_permission` VALUES (10, 3);
INSERT INTO `role_permission` VALUES (2, 6);
INSERT INTO `role_permission` VALUES (3, 6);
INSERT INTO `role_permission` VALUES (4, 6);
INSERT INTO `role_permission` VALUES (7, 6);
INSERT INTO `role_permission` VALUES (8, 6);
INSERT INTO `role_permission` VALUES (10, 6);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `age` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'zs', 20);

SET FOREIGN_KEY_CHECKS = 1;
