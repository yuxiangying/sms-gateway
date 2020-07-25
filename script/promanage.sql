/*
Navicat MySQL Data Transfer

Source Server         : 192.168.15.108 bs 后端
Source Server Version : 50721
Source Host           : 192.168.15.108:3316
Source Database       : promanage

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-01-16 12:44:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pro_item
-- ----------------------------
DROP TABLE IF EXISTS `pro_item`;
CREATE TABLE `pro_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT '项目编号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '项目名称',
  `model` varchar(100) NOT NULL DEFAULT '' COMMENT '型号',
  `content` varchar(1000) NOT NULL DEFAULT '' COMMENT '工作内容',
  `concern_level` int(11) NOT NULL DEFAULT '0' COMMENT '关注度',
  `charge_man` varchar(100) NOT NULL DEFAULT '' COMMENT '项目负责人',
  `members` varchar(1000) NOT NULL DEFAULT '' COMMENT '项目组成员',
  `start_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '项目下达时间',
  `end_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '要求完成时间',
  `scheme_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '方案时间',
  `scheme_man` varchar(100) NOT NULL DEFAULT '' COMMENT '方案负责人',
  `scheme_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '方案实际完成时间',
  `scheme_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '方案进度说明',
  `phone_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '手机/开模时间',
  `phone_man` varchar(100) NOT NULL DEFAULT '' COMMENT '手机/开模负责人',
  `phone_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '手机/开模实际完成时间',
  `phone_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '手机/开模进度说明',
  `prototype_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '样机时间',
  `prototype_man` varchar(100) NOT NULL DEFAULT '' COMMENT '样机负责人',
  `prototype_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '样机实际完成时间',
  `prototype_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '样机进度说明',
  `test_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '测试时间',
  `test_man` varchar(100) NOT NULL DEFAULT '' COMMENT '测试负责人',
  `test_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '测试实际完成时间',
  `test_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '测试进度说明',
  `publish_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '资料发布时间',
  `publish_man` varchar(100) NOT NULL DEFAULT '' COMMENT '资料发布负责人',
  `publish_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '资料发布实际完成时间',
  `publish_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '资料发布进度说明',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `reason` varchar(500) NOT NULL DEFAULT '' COMMENT '变更/暂停原因',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0.暂停,1.进行中,2.已完成,-1.取消',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`) USING BTREE,
  UNIQUE KEY `index_name` (`name`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='研发项目表';

-- ----------------------------
-- Table structure for pro_item
-- ----------------------------
DROP TABLE IF EXISTS `pro_item_history`;
CREATE TABLE `pro_item_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT '项目编号',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '项目名称',
  `model` varchar(100) NOT NULL DEFAULT '' COMMENT '型号',
  `content` varchar(1000) NOT NULL DEFAULT '' COMMENT '工作内容',
  `concern_level` int(11) NOT NULL DEFAULT '0' COMMENT '关注度',
  `charge_man` varchar(100) NOT NULL DEFAULT '' COMMENT '项目负责人',
  `members` varchar(1000) NOT NULL DEFAULT '' COMMENT '项目组成员',
  `start_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '项目下达时间',
  `end_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '预计完成时间',
  `scheme_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '方案时间',
  `scheme_man` varchar(100) NOT NULL DEFAULT '' COMMENT '方案负责人',
  `scheme_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '方案实际完成时间',
  `scheme_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '方案进度说明',
  `phone_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '手机/开模时间',
  `phone_man` varchar(100) NOT NULL DEFAULT '' COMMENT '手机/开模负责人',
  `phone_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '手机/开模实际完成时间',
  `phone_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '手机/开模进度说明',
  `prototype_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '样机时间',
  `prototype_man` varchar(100) NOT NULL DEFAULT '' COMMENT '样机负责人',
  `prototype_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '样机实际完成时间',
  `prototype_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '样机进度说明',
  `test_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '测试时间',
  `test_man` varchar(100) NOT NULL DEFAULT '' COMMENT '测试负责人',
  `test_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '测试实际完成时间',
  `test_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '测试进度说明',
  `publish_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '资料发布时间',
  `publish_man` varchar(100) NOT NULL DEFAULT '' COMMENT '资料发布负责人',
  `publish_real_time` date NOT NULL DEFAULT '1970-01-01' COMMENT '资料发布实际完成时间',
  `publish_progress` varchar(1000) NOT NULL DEFAULT '' COMMENT '资料发布进度说明',
  `mod_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '变更发生时间',
  `reason` varchar(500) NOT NULL DEFAULT '' COMMENT '变更/暂停原因',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态 0.暂停,1.进行中,2.已完成,-1.取消',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='研发项目变更历史表';