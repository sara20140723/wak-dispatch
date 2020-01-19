/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : wak_workstation

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 14/01/2020 17:18:05
*/

-- DROP DATABASE wak_workstation;
CREATE DATABASE wak_workstation;
USE wak_workstation;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dispatch_user
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_user`;
CREATE TABLE `dispatch_user` (
  `user_id` varchar(20) NOT NULL COMMENT '唯一编号',
  `user_code` varchar(20) DEFAULT NULL COMMENT '编号',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:正常;1:缺货;2:备货中)',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人',
  `deleted` int(2) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of dispatch_user
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_user` VALUES ('1', 'admin', 'admin', 'QUJBNUYyM0M3OTNEN0I4MUFBOTZBOTkwOEI1NDI0MUE=', 0, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for dispatch_user_profile
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_user_profile`;
CREATE TABLE `dispatch_user_profile` (
  `dispatch_user_id` varchar(20) NOT NULL COMMENT '派送人唯一编号',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '派送人昵称',
  `avatars` varchar(200) DEFAULT NULL COMMENT '头像',
  `description` varchar(2000) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `reg_time` datetime(6) DEFAULT NULL COMMENT '注册时间',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:正常;1:缺货;2:备货中)',
  `business_time_begin` datetime(6) DEFAULT NULL COMMENT '营业开始时间',
  `business_time_end` datetime(6) DEFAULT NULL COMMENT '营业时间结束',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人',
  `deleted` int(2) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`dispatch_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of dispatch_user_profile
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_user_profile` VALUES ('1', '18217611867', '1', 'sophy', 'http://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1579591689&di=966f2c7ca25e8dfba543121c541217b5&imgtype=jpg&src=http%3A%2F%2Fimg6.bdstatic.com%2Fimg%2Fimage%2Fpublic%2Ftanran2.jpg', '我送单,我快乐', '2020-01-14 15:31:01.000000', 0, NULL, NULL, NULL, NULL, NULL, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for dispatch_user_relation_workstation
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_user_relation_workstation`;
CREATE TABLE `dispatch_user_relation_workstation` (
  `workstation_id` varchar(20) DEFAULT NULL COMMENT '站点唯一编号',
  `dispatch_user_id` varchar(20) DEFAULT NULL COMMENT '派送人编号',
  `description` varchar(1000) DEFAULT NULL COMMENT '产品描述',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:正常;1:已删除)',
  `business_time_begin` datetime(6) DEFAULT NULL COMMENT '营业开始时间',
  `business_time_end` datetime(6) DEFAULT NULL COMMENT '营业时间结束',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人',
  `deleted` int(2) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  KEY `idx_id` (`workstation_id`,`dispatch_user_id`) COMMENT '工作站关联派送人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for workstation
-- ----------------------------
DROP TABLE IF EXISTS `workstation`;
CREATE TABLE `workstation` (
  `id` varchar(20) NOT NULL COMMENT '唯一编号',
  `code` varchar(20) DEFAULT NULL COMMENT '编号',
  `workstation_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `location` point DEFAULT NULL COMMENT '位置',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `work_manager_user_name` varchar(20) DEFAULT NULL COMMENT '负责人',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:正常;1:禁用)',
  `business_time_begin` datetime(6) DEFAULT NULL COMMENT '营业开始时间',
  `business_time_end` datetime(6) DEFAULT NULL COMMENT '营业时间结束',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人',
  `deleted` int(2) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for workstation_relation_product
-- ----------------------------
DROP TABLE IF EXISTS `workstation_relation_product`;
CREATE TABLE `workstation_relation_product` (
  `workstation_id` varchar(20) DEFAULT NULL COMMENT '工作站唯一编号',
  `product_id` varchar(20) DEFAULT NULL COMMENT '产品唯一编号',
  `product_no` varchar(20) DEFAULT NULL COMMENT '产品编码',
  `product_name` varchar(20) DEFAULT NULL COMMENT '产品名称',
  `product_abstract` varchar(200) DEFAULT NULL COMMENT '产品摘要',
  `product_description` varchar(1000) DEFAULT NULL COMMENT '产品描述',
  `amount` int(6) DEFAULT NULL COMMENT '数量',
  `detail_json` varchar(1000) DEFAULT NULL COMMENT '详情',
  `status` int(4) DEFAULT '0' COMMENT '状态(0:正常;1:缺货;2:备货中)',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人',
  `deleted` int(2) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
