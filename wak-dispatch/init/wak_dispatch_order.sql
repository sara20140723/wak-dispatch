/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : wak_dispatch_order

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 14/01/2020 17:18:20
*/
-- DROP DATABASE wak_dispatch_order;
CREATE DATABASE wak_dispatch_order;
USE wak_dispatch_order;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dispatch_order
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_order`;
CREATE TABLE `dispatch_order` (
  `dispatch_id` varchar(20) NOT NULL COMMENT '调度流水唯一编号',
  `order_id` varchar(20) NOT NULL COMMENT '订单唯一编号',
  `order_no` varchar(20) DEFAULT NULL COMMENT '订单编号',
  `order_address_id` varchar(20) NOT NULL COMMENT '收货地址唯一编号',
  `order_user_id` int(11) DEFAULT NULL COMMENT '下单用户编号',
  `dispatch_user_id` varchar(20) DEFAULT NULL COMMENT '派送人唯一编号',
  `order_create_time` datetime(6) DEFAULT NULL COMMENT '下单时间',
  `order_address_detail` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `order_location` point DEFAULT NULL COMMENT '位置',
  `customer_user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名称',
  `customer_phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `order_json` varchar(20) NOT NULL COMMENT '订单详情',
  `dispatch_status` int(4) DEFAULT '0' COMMENT '调度状态(0:待接单;1:待取货;2:配送中;3:已送达)',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人',
  `deleted` int(2) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `status` int(2) DEFAULT NULL COMMENT '订单状态',
  `workstation_id` varchar(20) DEFAULT NULL COMMENT '工作站ID',
  PRIMARY KEY (`dispatch_id`),
  KEY `index_join` (`order_id`,`order_user_id`,`order_address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of dispatch_order
-- ----------------------------
BEGIN;
INSERT INTO `dispatch_order` VALUES ('1', '1', 'N001', '1', 1, '1', '2020-01-14 16:11:43.000000', '华山路100号', NULL, '王素飞', '18217611867', '', 0, NULL, NULL, NULL, NULL, 0, 2, NULL);
COMMIT;

-- ----------------------------
-- Table structure for dispatch_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `dispatch_order_detail`;
CREATE TABLE `dispatch_order_detail` (
  `dispatch_id` varchar(20) NOT NULL COMMENT '调度流水唯一编号',
  `order_id` varchar(20) NOT NULL COMMENT '订单唯一编号',
  `order_no` varchar(20) DEFAULT NULL COMMENT '订单编号',
  `order_address_id` varchar(20) NOT NULL COMMENT '收货地址唯一编号',
  `order_user_id` int(11) DEFAULT NULL COMMENT '下单用户编号',
  `dispatch_user_id` varchar(20) DEFAULT NULL COMMENT '派送人唯一编号',
  `product_id` varchar(20) DEFAULT NULL COMMENT '产品唯一编号',
  `product_number` varchar(20) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `product_abstract` varchar(255) DEFAULT NULL COMMENT '产品摘要',
  `product_description` varchar(1000) DEFAULT NULL COMMENT '产品描述',
  `sale_price` decimal(6,4) DEFAULT NULL COMMENT '销售价格',
  `market_price` decimal(6,4) DEFAULT NULL COMMENT '市场价格',
  `quantity` int(11) DEFAULT NULL COMMENT '总数量',
  `cover` varchar(200) DEFAULT NULL COMMENT '封面',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '修改人',
  `deleted` int(2) DEFAULT '0' COMMENT '删除标记(0:未删除,1:已删除)',
  `status` int(2) DEFAULT NULL COMMENT '调度状态(0:未接单;1:待取货;2:配送中;3:已完成;4:取消)',
  PRIMARY KEY (`dispatch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
