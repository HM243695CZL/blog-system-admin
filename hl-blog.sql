/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : hl-blog

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 15/12/2022 14:40:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_info
-- ----------------------------
DROP TABLE IF EXISTS `blog_info`;
CREATE TABLE `blog_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '博客标题',
  `summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '博客摘要',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '博客内容',
  `type` int(11) NULL DEFAULT NULL COMMENT '所属专栏',
  `views` int(10) NULL DEFAULT NULL COMMENT '浏览量',
  `tags` int(11) NULL DEFAULT NULL COMMENT '标签',
  `comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论',
  `picture_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '首图地址',
  `is_recommend` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启推荐',
  `is_reprint` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启转载声明',
  `is_appreciation` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启赞赏',
  `is_comment` tinyint(1) NULL DEFAULT NULL COMMENT '是否开启评论',
  `property` int(11) NULL DEFAULT NULL COMMENT '1.原创; 2.转载; 3.翻译',
  `state` tinyint(1) NULL DEFAULT 1 COMMENT '是否发布',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_info
-- ----------------------------

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `number` int(11) NULL DEFAULT 0 COMMENT '博客数量',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES (4, 'Vue', 0, '2022-12-15 11:41:27', '2022-12-15 11:41:27', 1);
INSERT INTO `blog_tag` VALUES (5, 'React', 0, '2022-12-15 11:41:33', '2022-12-15 11:41:33', 1);
INSERT INTO `blog_tag` VALUES (6, 'Angular', 0, '2022-12-15 11:41:41', '2022-12-15 11:41:41', 1);

-- ----------------------------
-- Table structure for blog_type
-- ----------------------------
DROP TABLE IF EXISTS `blog_type`;
CREATE TABLE `blog_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专栏名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专栏简介',
  `number` int(11) NULL DEFAULT 0 COMMENT '博客数量',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_type
-- ----------------------------
INSERT INTO `blog_type` VALUES (10, '开发笔记', NULL, 0, '2022-12-15 11:42:06', '2022-12-15 11:42:06', 1);
INSERT INTO `blog_type` VALUES (9, '项目实战', NULL, 0, '2022-12-15 11:41:55', '2022-12-15 11:41:55', 1);
INSERT INTO `blog_type` VALUES (7, '面试心得', NULL, 0, '2022-12-15 11:09:35', '2022-12-15 11:09:35', 1);

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员名称',
  `password` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近一次登录时间',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '\'' COMMENT '头像图片',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES (3, 'admin', '123456', '0:0:0:0:0:0:0:1', '2022-12-15 09:12:11', 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20220620/lihezong.webp', '2022-06-20 16:11:43', '2022-06-23 10:01:03', 1);
INSERT INTO `ums_admin` VALUES (4, 'test', '123456', '', NULL, 'https://hl-mall-tiny.oss-cn-chengdu.aliyuncs.com/hlmall/images/20220623/hw-logo.png', '2022-06-23 10:15:19', '2022-06-23 10:15:19', 1);

-- ----------------------------
-- Table structure for ums_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role`;
CREATE TABLE `ums_admin_role`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `admin_id` int(20) NULL DEFAULT NULL COMMENT '管理员id',
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_admin_role
-- ----------------------------
INSERT INTO `ums_admin_role` VALUES (3, 3, 10);
INSERT INTO `ums_admin_role` VALUES (4, 4, 11);

-- ----------------------------
-- Table structure for ums_dict
-- ----------------------------
DROP TABLE IF EXISTS `ums_dict`;
CREATE TABLE `ums_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `data_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典键',
  `data_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_dict
-- ----------------------------
INSERT INTO `ums_dict` VALUES (1, 'express', 'ZTO', '中通快递', '快递公司', '2022-09-05 23:55:18', '2022-09-06 00:01:42', 1);
INSERT INTO `ums_dict` VALUES (2, 'express', 'YTO', '圆通速递', '快递公司', '2022-09-06 00:03:02', '2022-09-06 00:03:02', 1);
INSERT INTO `ums_dict` VALUES (3, 'express', 'YD', '韵达速递', '快递公司', '2022-09-06 00:03:20', '2022-09-06 00:03:20', 1);
INSERT INTO `ums_dict` VALUES (4, 'express', 'SF', '顺丰速运', '快递公司', '2022-09-06 00:04:01', '2022-09-06 18:12:36', 1);
INSERT INTO `ums_dict` VALUES (5, 'express', 'DBL', '德邦快递', '快递公司', '2022-09-06 00:04:17', '2022-09-06 00:04:17', 1);

-- ----------------------------
-- Table structure for ums_form
-- ----------------------------
DROP TABLE IF EXISTS `ums_form`;
CREATE TABLE `ums_form`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单名称',
  `form_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单key',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单备注',
  `config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表单配置',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '表单配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_form
-- ----------------------------
INSERT INTO `ums_form` VALUES (2, '用户表单', 'userFormKey', '用户的操作表单', '[{\"type\":\"input\",\"field\":\"username\",\"title\":\"用户名\",\"info\":\"\",\"props\":{\"placeholder\":\"请输入用户名称\",\"clearable\":true},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true},{\"type\":\"input\",\"field\":\"password\",\"title\":\"密码\",\"info\":\"\",\"props\":{\"placeholder\":\"请输入密码\",\"type\":\"password\"},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true,\"$required\":false}]', '2022-11-16 17:51:10', '2022-11-16 17:51:10', 1);
INSERT INTO `ums_form` VALUES (6, '博客类型', 'BlogTypeKey', '博客类型的表单', '[{\"type\":\"input\",\"field\":\"name\",\"title\":\"专栏名称\",\"info\":\"\",\"props\":{\"clearable\":true,\"placeholder\":\"请输入专栏名称\"},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true,\"$required\":\"请输入专栏名称\"},{\"type\":\"input\",\"field\":\"desc\",\"title\":\"专栏简介\",\"info\":\"\",\"props\":{\"type\":\"textarea\",\"showWordLimit\":true,\"placeholder\":\"请输入专栏名称\",\"clearable\":true,\"rows\":3},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true,\"$required\":false}]', '2022-12-12 23:35:42', '2022-12-12 23:35:42', 1);
INSERT INTO `ums_form` VALUES (7, '博客标签', 'BlogTagKey', '博客标签的表单', '[{\"type\":\"input\",\"field\":\"name\",\"title\":\"标签名称\",\"info\":\"\",\"props\":{\"clearable\":true},\"_fc_drag_tag\":\"input\",\"hidden\":false,\"display\":true,\"$required\":\"请输入标签名称\"}]', '2022-12-14 13:17:20', '2022-12-14 13:17:20', 1);

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单地址',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组件名称',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组件地址',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `is_link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '超链接地址',
  `is_hide` tinyint(1) NULL DEFAULT NULL COMMENT '是否隐藏',
  `is_keep_alive` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存组件',
  `is_affix` tinyint(1) NULL DEFAULT NULL COMMENT '是否固定',
  `is_iframe` tinyint(1) NULL DEFAULT NULL COMMENT '是否内嵌窗口',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES (1, NULL, '/system', 'system', 'layout', '系统管理', '', 0, 1, 0, 0, 'iconfont icon-bolangneng', 1);
INSERT INTO `ums_menu` VALUES (2, 1, '/system/meun', 'systemMenu', '/system/menu', '菜单管理', '', 0, 1, 0, 0, 'iconfont icon--chaifenhang', 1);
INSERT INTO `ums_menu` VALUES (4, 1, '/system/user', 'systemUser', '/system/user', '用户管理', '', 0, 1, 0, 0, 'ele-Avatar', 2);
INSERT INTO `ums_menu` VALUES (5, 1, '/system/role', 'systemRole', '/system/role', '角色管理', '', 0, 1, 0, 0, 'ele-Briefcase', 3);
INSERT INTO `ums_menu` VALUES (24, 1, '/system/config', 'systemConfig', '/system/config', '配置管理', '', 1, 1, 0, 0, 'iconfont icon-wenducanshu-05', 4);
INSERT INTO `ums_menu` VALUES (29, 1, '/system/dict', 'systemDict', '/system/dict', '字典管理', '', 1, 1, 0, 0, 'iconfont icon-728bianjiqi_zitidaxiao', 5);
INSERT INTO `ums_menu` VALUES (33, 1, '/system/form-config', 'form-config', '/system/form-config', '表单配置', NULL, 0, 1, 0, NULL, 'iconfont icon-xitongshezhi', 6);
INSERT INTO `ums_menu` VALUES (34, 1, '/system/form-designer', 'form-designer', '/system/form-designer', '表单设计', NULL, 0, 1, 0, NULL, 'iconfont icon-tongzhi1', 7);
INSERT INTO `ums_menu` VALUES (35, NULL, '/blog', 'blog', 'layout', '博客管理', '', 0, 1, 0, 0, 'iconfont icon-diannao1', 2);
INSERT INTO `ums_menu` VALUES (36, 35, '/blog/type', 'blogType', '/blog/type', '博客类型', '', 0, 1, 0, 0, 'iconfont icon-wenducanshu-05', 1);
INSERT INTO `ums_menu` VALUES (37, 35, '/blog/tag', 'blogTag', '/blog/tag', '博客标签', '', 0, 1, 0, 0, 'iconfont icon-wenducanshu-05', 2);
INSERT INTO `ums_menu` VALUES (38, 35, '/blog/blogInfo', 'blogInfo', '/blog/blogInfo', '博客详情', '', 0, 1, 0, 0, 'iconfont icon-bolangneng', 3);

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色key',
  `desc` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_UNIQUE`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES (10, '超级管理员', 'admin', '拥有所有权限', 1, '2022-06-20 11:13:09', '2022-11-14 16:15:11', NULL);
INSERT INTO `ums_role` VALUES (11, '普通管理员', 'common', '拥有部分权限', 1, '2022-06-20 11:27:15', '2022-06-21 11:50:05', 1);

-- ----------------------------
-- Table structure for ums_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu`;
CREATE TABLE `ums_role_menu`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int(20) NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 399 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_role_menu
-- ----------------------------
INSERT INTO `ums_role_menu` VALUES (387, 10, 1);
INSERT INTO `ums_role_menu` VALUES (388, 10, 2);
INSERT INTO `ums_role_menu` VALUES (389, 10, 4);
INSERT INTO `ums_role_menu` VALUES (390, 10, 5);
INSERT INTO `ums_role_menu` VALUES (391, 10, 24);
INSERT INTO `ums_role_menu` VALUES (392, 10, 29);
INSERT INTO `ums_role_menu` VALUES (393, 10, 33);
INSERT INTO `ums_role_menu` VALUES (394, 10, 34);
INSERT INTO `ums_role_menu` VALUES (395, 10, 35);
INSERT INTO `ums_role_menu` VALUES (396, 10, 36);
INSERT INTO `ums_role_menu` VALUES (397, 10, 37);
INSERT INTO `ums_role_menu` VALUES (398, 10, 38);

-- ----------------------------
-- Table structure for ums_system
-- ----------------------------
DROP TABLE IF EXISTS `ums_system`;
CREATE TABLE `ums_system`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统配置名',
  `key_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统配置值',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 1 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ums_system
-- ----------------------------
INSERT INTO `ums_system` VALUES (21, 'yyx_express_freight_value', '15', '2022-08-04 17:58:58', '2022-08-05 13:27:19', 1);
INSERT INTO `ums_system` VALUES (22, 'yyx_order_unpaid', '30', '2022-08-04 17:58:58', '2022-08-19 17:35:35', 1);
INSERT INTO `ums_system` VALUES (23, 'yyx_order_comment', '7', '2022-08-04 17:58:58', '2022-08-19 17:35:35', 1);
INSERT INTO `ums_system` VALUES (24, 'yyx_express_freight_min', '88', '2022-08-04 17:58:58', '2022-08-05 13:27:19', 1);
INSERT INTO `ums_system` VALUES (25, 'yyx_order_un_confirm', '7', '2022-08-04 17:58:58', '2022-08-19 17:35:35', 1);

SET FOREIGN_KEY_CHECKS = 1;
