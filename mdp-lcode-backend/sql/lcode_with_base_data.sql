/*
 Navicat Premium Data Transfer

 Source Server         : 123.207.117.5
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 123.207.117.5:3306
 Source Schema         : lcode

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 31/08/2024 14:14:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for arc_attachment
-- ----------------------------
DROP TABLE IF EXISTS `arc_attachment`;
CREATE TABLE `arc_attachment`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '附件名称',
  `URL` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问路径',
  `RELATIVE_PATH` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '硬盘存放路径',
  `FILE_SUFFIX` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '后缀名',
  `CDN_URL` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '内容加速器访问路径',
  `IS_IMG` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否图片',
  `ARCHIVE_ID` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '档案主编号',
  `IS_CDN` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否使用CDN',
  `ROOT_PATH` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '根目录',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '存入时间',
  `CAN_DOWN` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可下载',
  `CAN_DEL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可删除',
  `CAN_READ` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可读',
  `BIZ_ID` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务编号、产品编号、商品编号等',
  `REMARK` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务名称、产品名称、商品名称等',
  `STORE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '存储名字',
  `FILE_SIZE` decimal(8, 0) NULL DEFAULT NULL COMMENT '文件大小',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '云用户机构编号',
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号',
  `archive_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|知识库1|新闻2|内部公告3|平台公告5|论坛6|公文4|其它7|归档8|网站栏目,管理元数据categoryType',
  `CATEGORY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类编号',
  `rely_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考类型',
  `rely_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考编号',
  `rely_stype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考子类型',
  `rely_sid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考子编号',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `cdate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段',
  `tag_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '档案附件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arc_attachment
-- ----------------------------
INSERT INTO `arc_attachment` VALUES ('AT1721930041705181', 'logo.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925585898124/AT1721930041705181.png', 'guest/CA1721925585898124/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925585898124/AT1721930041705181.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', '', '', 'AT1721930041705181', NULL, 'guest', '', '0', 'CA1721925585898124', NULL, NULL, NULL, NULL, 'guest', '游客', '2024-07-26 01:54:02', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1721930082013126', '渠道账号管理20230815.xlsx', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925585898124/AT1721930082013126.xlsx', 'guest/CA1721925585898124/', '.xlsx', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925585898124/AT1721930082013126.xlsx', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', '', '', 'AT1721930082013126', NULL, 'guest', '', '0', 'CA1721925585898124', NULL, NULL, NULL, NULL, 'guest', '游客', '2024-07-26 01:54:42', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722435162800156', '1d7f9f5bb1254b2690ead75dd13aa272_7130983724.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926602138135/AT1722435162800156.webp', 'guest/CA1721926602138135/', '.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926602138135/AT1722435162800156.webp', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722435162800156', NULL, 'guest', '', '0', 'CA1721926602138135', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 22:12:43', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722435182360155', '默认随机头像.zip', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925700460185/AT1722435182360155.zip', 'guest/CA1721925700460185/', '.zip', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925700460185/AT1722435182360155.zip', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722435182360155', NULL, 'guest', '', '0', 'CA1721925700460185', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 22:13:02', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722435198793187', '方形.zip', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925585898124/AT1722435198793187.zip', 'guest/CA1721925585898124/', '.zip', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925585898124/AT1722435198793187.zip', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722435198793187', NULL, 'guest', '', '0', 'CA1721925585898124', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 22:13:19', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722435279273187', '144.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1722435236165186/AT1722435279273187.png', 'guest/CA1722435236165186/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1722435236165186/AT1722435279273187.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722435279273187', NULL, 'guest', '', '0', 'CA1722435236165186', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 22:14:39', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722437009231132', '144.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926611614146/AT1722437009231132.png', 'guest/CA1721926611614146/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926611614146/AT1722437009231132.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722437009231132', NULL, 'guest', '', '0', 'CA1721926611614146', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 22:43:29', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722437965175118', '569b92da508244d08d9a8e45fdf91b1f_1529167374.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925700460185/AT1722437965175118.png', 'guest/CA1721925700460185/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721925700460185/AT1722437965175118.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722437965175118', NULL, 'guest', '', '0', 'CA1721925700460185', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 22:59:25', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722437984114155', 'IM1634258233309157.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926757595193/AT1722437984114155.png', 'guest/CA1721926757595193/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926757595193/AT1722437984114155.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722437984114155', NULL, 'guest', '', '0', 'CA1721926757595193', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 22:59:44', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722438174905156', '569b92da508244d08d9a8e45fdf91b1f_1529167374.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926757595193/AT1722438174905156.png', 'guest/CA1721926757595193/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926757595193/AT1722438174905156.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722438174905156', NULL, 'guest', '', '0', 'CA1721926757595193', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 23:02:55', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722438186845184', '569b92da508244d08d9a8e45fdf91b1f_1529167374.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926757595193/AT1722438186845184.png', 'guest/CA1721926757595193/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/guest/CA1721926757595193/AT1722438186845184.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0046-83-9B8A', '', 'AT1722438186845184', NULL, 'guest', '', '0', 'CA1721926757595193', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, 'guest', '游客', '2024-07-31 23:03:07', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722438424305133', '569b92da508244d08d9a8e45fdf91b1f_1529167374.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1721926602138135/AT1722438424305133.png', 'qqkj_001/CA1721926602138135/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1721926602138135/AT1722438424305133.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'mmcloud-xm', '', 'AT1722438424305133', NULL, 'qqkj_001', '4gx1x7v74q', '0', 'CA1721926602138135', 'xm-product', 'mmcloud-xm', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:07:04', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722438437804148', '569b92da508244d08d9a8e45fdf91b1f_1529167374.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1721925700460185/AT1722438437804148.png', 'qqkj_001/CA1721925700460185/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1721925700460185/AT1722438437804148.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'mmcloud-xm', '', 'AT1722438437804148', NULL, 'qqkj_001', '4gx1x7v74q', '0', 'CA1721925700460185', 'xm-product', 'mmcloud-xm', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:07:18', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722438451981149', '1d7f9f5bb1254b2690ead75dd13aa272_7130983724.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722435236165186/AT1722438451981149.webp', 'qqkj_001/CA1722435236165186/', '.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722435236165186/AT1722438451981149.webp', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'mmcloud-xm', '', 'AT1722438451981149', NULL, 'qqkj_001', '4gx1x7v74q', '0', 'CA1722435236165186', 'xm-product', 'mmcloud-xm', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:07:32', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722439541754165', '方形.zip', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722435236165186/AT1722439541754165.zip', 'qqkj_001/CA1722435236165186/', '.zip', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722435236165186/AT1722439541754165.zip', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'mmcloud-xm', '', 'AT1722439541754165', NULL, 'qqkj_001', '4gx1x7v74q', '0', 'CA1722435236165186', 'xm-product', 'mmcloud-xm', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:25:42', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722440309153111', 'f0960f79ab1147c1ab3950af121d50a1_7131188430.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1721925585898124/AT1722440309153111.webp', 'qqkj_001/CA1721925585898124/', '.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1721925585898124/AT1722440309153111.webp', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'mmcloud-xm', '', 'AT1722440309153111', NULL, 'qqkj_001', '4gx1x7v74q', '0', 'CA1721925585898124', 'xm-product', 'mmcloud-xm', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:38:29', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722441162234191', '569b92da508244d08d9a8e45fdf91b1f_1529167374.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722441153748176/AT1722441162234191.png', 'qqkj_001/CA1722441153748176/', '.png', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722441153748176/AT1722441162234191.png', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PJ2024-0060-DI-PJ3F', '', 'AT1722441162234191', NULL, 'qqkj_001', '4gx1x7v74q', '1', 'CA1722441153748176', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:52:42', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722441175521188', '1d7f9f5bb1254b2690ead75dd13aa272_7130983724.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722441129635127/AT1722441175521188.webp', 'qqkj_001/CA1722441129635127/', '.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722441129635127/AT1722441175521188.webp', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PJ2024-0060-DI-PJ3F', '', 'AT1722441175521188', NULL, 'qqkj_001', '4gx1x7v74q', '1', 'CA1722441129635127', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:52:56', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722441222880186', '方形.zip', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722441213946181/AT1722441222880186.zip', 'qqkj_001/CA1722441213946181/', '.zip', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722441213946181/AT1722441222880186.zip', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PJ2024-0060-DI-PJ3F', '', 'AT1722441222880186', NULL, 'qqkj_001', '4gx1x7v74q', '2', 'CA1722441213946181', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:53:43', NULL, NULL);
INSERT INTO `arc_attachment` VALUES ('AT1722441363897111', '1d7f9f5bb1254b2690ead75dd13aa272_7130983724.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722441356552165/AT1722441363897111.webp', 'qqkj_001/CA1722441356552165/', '.webp', 'http://127.0.0.1:7014/mdp/arc/att/attachment/qqkj_001/CA1722441356552165/AT1722441363897111.webp', NULL, '', NULL, 'D:/arcfile/files', NULL, '1', '1', '1', 'PD2024-0045-84-8WN2', '', 'AT1722441363897111', NULL, 'qqkj_001', '4gx1x7v74q', '0', 'CA1722441356552165', 'xm-product', 'PD2024-0045-84-8WN2', NULL, NULL, '4hinb8m16', '陈裕财', '2024-07-31 23:56:04', NULL, NULL);

-- ----------------------------
-- Table structure for arc_category
-- ----------------------------
DROP TABLE IF EXISTS `arc_category`;
CREATE TABLE `arc_category`  (
  `category_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0知识库-1新闻类2企业内部通知公告类3平台通知公告4其它5论坛6公文7归档8网站栏目,关联基础数据categoryType',
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '分类主键',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级分类',
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类名称',
  `SORT_ORDER` decimal(5, 0) NULL DEFAULT NULL COMMENT '分类排序',
  `IS_SHOW` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否显示',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `image_urls` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '图片列表逗号分割',
  `is_leaf` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否叶子节点0否1是，叶子节点不允许再建立下级，非叶子节点不允许挂文章',
  `limit_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '文章限制,1-单篇文章，2-多篇文章',
  `is_auth` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '文章是否需要审核0-否1是',
  `paths` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级分类路径，逗号分割，包括自身',
  `crely_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考类型',
  `crely_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考类型编号',
  `crely_stype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '子参考类型',
  `crely_sid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '子参考类型编号',
  `qx_lvl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限控制要求 0-完全开放，1-内部完全开放，2-受限',
  `pqx` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否强制要求子类继承',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `tag_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ARC_C_BRANCH_INDEX`(`BRANCH_ID` ASC) USING BTREE,
  INDEX `ARC_C_PI_INDEX`(`PID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '档案类目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arc_category
-- ----------------------------
INSERT INTO `arc_category` VALUES ('3', '1', 'C0', '公告', NULL, '1', 'platform-branch-001', 'fa:bell-o', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797629602858553346', 'C0', '办公', NULL, '1', 'platform-branch-001', 'ep:alarm-clock', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('2', '1797629706113929217', 'C0', '学习', NULL, '1', 'platform-branch-001', 'fa:stumbleupon-circle', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('3', '1797630328087269378', '1', 'dddddddddd', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,1,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('2', '1797630393598103554', '1797629706113929217', 'd4444444444ddddddddd', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,1797629706113929217,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797634288294580226', 'c0-1', 'ddddddddddd', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,c1,c0-1,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797634335086235649', 'dddd', 'ddddddddddd', NULL, '1', 'platform-branch-001', 'fa:align-center', '0', '2', '0', '0,dddd,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797642036461719553', '1797629602858553346', '测试', NULL, '1', 'platform-branch-001', 'fa:arrow-circle-left', '0', '2', '0', '0,1797629602858553346,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797644109156732929', '1797629602858553346', '烦烦烦烦烦烦', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,1797629602858553346,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797644141419319297', '1797629602858553346', '烦烦烦烦烦烦', NULL, '1', 'platform-branch-001', 'fa:file-o', '0', '2', '0', '0,1797629602858553346,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797644515802894337', 'c1', '顶顶顶顶33333333333', NULL, '1', 'platform-branch-001', 'fa:file-o', '0', '2', '0', '0,c1,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797734582613491713', '1797629602858553346', '21', NULL, '1', 'platform-branch-001', 'ep:briefcase', '0', '2', '0', '0,1797629602858553346,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1797734674124816386', '1797629602858553346', '22', NULL, '1', 'platform-branch-001', 'ep:arrow-up-bold', '0', '2', '0', '0,1797629602858553346,', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('1', '1797734809902825474', 'C0', '新闻', NULL, '1', 'demo-branch-01', 'fa:binoculars', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '1798068582456524802', 'C0', '财务', NULL, '1', 'demo-branch-01', 'fa-solid:hand-middle-finger', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('7', '1798070532921131010', 'C0', '采购', NULL, '1', 'demo-branch-01', 'ep:avatar', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'c0-1', 'c1', '测试123', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,c1,c0-1', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'c1', 'C0', '项目', NULL, '1', 'platform-branch-001', 'fa-solid:project-diagram', '0', '2', '0', '0,c1', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'c111', 'c1', 'c111', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,c1,c111', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'c1112', 'c1', 'c1112', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,c1,c1112', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'c3', 'c1', 'c3', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,c1,c3', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('6', 'c33', 'C0', '产品', NULL, '1', 'platform-branch-001', 'fa:product-hunt', '0', '2', '0', '0,c33', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('6', 'c333', 'c33', 'c333', NULL, '1', 'platform-branch-001', NULL, '0', '2', '0', '0,c33,c333', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1721925585898124', '0', 'xxxxxx', NULL, NULL, 'qqkj_001', 'ep:avatar', '0', '2', '0', '0,CA1721925585898124', NULL, NULL, NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1721925700460185', 'CA1721925585898124', 'xxxxxx', NULL, NULL, 'qqkj_001', 'ep:avatar', '0', '2', '0', '0,CA1721925585898124,CA1721925700460185', NULL, NULL, NULL, NULL, '2', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1721926602138135', 'CA1721925585898124', 'xxxxxxddddddddddddddddddddddddddddddddd', NULL, NULL, 'qqkj_001', 'ep:avatar', '0', '2', '0', '0,CA1721925585898124,CA1721926602138135', NULL, NULL, NULL, NULL, '2', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1721926611614146', 'CA1721926602138135', 'xxxxxxddddddddddddddddddddddddddddddddd', NULL, NULL, 'qqkj_001', 'ep:avatar', '0', '2', '0', '0,CA1721925585898124,CA1721926602138135,CA1721926611614146', NULL, NULL, NULL, NULL, '2', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1721926757595193', 'CA1721926602138135', 'xxxxxxddddddddddddddddddddddddddddddddd', NULL, NULL, 'qqkj_001', 'ep:avatar', '0', '2', '0', '0,CA1721925585898124,CA1721926602138135,CA1721926757595193', NULL, NULL, NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1722435236165186', '0', 'xxxffff', NULL, NULL, 'qqkj_001', 'ep:avatar', '0', '2', '0', '0,CA1722435236165186', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, '2', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1722440636436143', '0', '产品介绍', NULL, NULL, 'qqkj_001', 'ep:avatar', '0', '2', '0', '0,CA1722440636436143', 'xm-product', 'PD2024-0046-83-9B8A', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('1', 'CA1722441129635127', '0', '项目文档', NULL, NULL, 'qqkj_001', 'ep:aim', '0', '2', '0', '0,CA1722441129635127', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('1', 'CA1722441142605166', 'CA1722441129635127', '项目文档22', NULL, NULL, 'qqkj_001', 'ep:aim', '0', '2', '0', '0,CA1722441129635127,CA1722441142605166', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('1', 'CA1722441153748176', 'CA1722441142605166', '项目文档33', NULL, NULL, 'qqkj_001', 'ep:aim', '0', '2', '0', '0,CA1722441129635127,CA1722441142605166,CA1722441153748176', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('2', 'CA1722441203172182', 'CA1722441142605166', '项目文99999', NULL, NULL, 'qqkj_001', 'ep:aim', '0', '2', '0', '0,CA1722441129635127,CA1722441142605166,CA1722441203172182', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('2', 'CA1722441213946181', 'CA1722441203172182', '项目文992', NULL, NULL, 'qqkj_001', 'ep:aim', '0', '2', '0', '0,CA1722441129635127,CA1722441142605166,CA1722441203172182,CA1722441213946181', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1722441341412195', '0', '产品文档', NULL, NULL, 'qqkj_001', NULL, '0', '2', '0', '0,CA1722441341412195', 'xm-product', 'PD2024-0045-84-8WN2', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1722441348590169', 'CA1722441341412195', '产品文档2', NULL, NULL, 'qqkj_001', NULL, '0', '2', '0', '0,CA1722441341412195,CA1722441348590169', 'xm-product', 'PD2024-0045-84-8WN2', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1722441356552165', 'CA1722441341412195', '产品文档3', NULL, NULL, 'qqkj_001', NULL, '0', '2', '0', '0,CA1722441341412195,CA1722441356552165', 'xm-product', 'PD2024-0045-84-8WN2', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1722441393155121', 'CA1722441341412195', '产品文档3', NULL, NULL, 'qqkj_001', NULL, '0', '2', '0', '0,CA1722441341412195,CA1722441393155121', 'xm-product', 'PD2024-0045-84-8WN2', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1722441418804165', 'CA1722441393155121', '产品文档3', NULL, NULL, 'qqkj_001', NULL, '0', '2', '0', '0,CA1722441341412195,CA1722441393155121,CA1722441418804165', 'xm-product', 'PD2024-0045-84-8WN2', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('0', 'CA1722441441979118', 'CA1722441418804165', '产品文档3', NULL, NULL, 'qqkj_001', NULL, '0', '2', '0', '0,CA1722441341412195,CA1722441393155121,CA1722441418804165,CA1722441441979118', 'xm-product', 'PD2024-0045-84-8WN2', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('7', 'CA1722442062742176', '0', '嘻嘻嘻', NULL, NULL, 'qqkj_001', NULL, '0', '2', '0', '0,CA1722442062742176', 'xm-project', 'PJ2024-0060-DI-PJ3F', NULL, NULL, '0', '', NULL, '4hinb8m16', NULL);
INSERT INTO `arc_category` VALUES ('1', 'ccc', 'C0', '协同', NULL, '1', 'platform-branch-001', 'fa:cogs', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('4', 'ccc2222222', 'C0', '订单', NULL, '1', 'platform-branch-001', 'fa:first-order', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'dddd', 'C0', 'erp', NULL, '1', 'platform-branch-001', 'fa-solid:american-sign-language-interpreting', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'xxx', 'C0', '电商', NULL, '1', 'demo-branch-01', 'ep:shopping-bag', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', 'xxx2', 'xxx', 'xxxxxx', NULL, '1', 'demo-branch-01', 'ep:baseball', '0', '2', '0', '0,xxx,xxx2', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('3', '等等', '1', '顶顶顶顶顶顶顶22222', NULL, '1', 'platform-branch-001', 'ep:bicycle', '0', '2', '0', '0,1,等等', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);
INSERT INTO `arc_category` VALUES ('0', '等等2', 'C0', '人资', NULL, '1', 'demo-branch-01', 'fa-solid:strikethrough', '0', '2', '0', '', NULL, NULL, NULL, NULL, '0', '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for arc_category_qx
-- ----------------------------
DROP TABLE IF EXISTS `arc_category_qx`;
CREATE TABLE `arc_category_qx`  (
  `CATE_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '分类编号',
  `QRY_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色查询,号分割',
  `QRY_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门查询,号分割',
  `QRY_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人查询,号分割',
  `NQ_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色查询',
  `NQ_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门查询',
  `NQ_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人查询',
  `OTH_QUERY` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人查询',
  `OTH_EDIT` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人修改',
  `OTH_DEL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人删除',
  `LVL_CHECK` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否进行部门级别传递权限检查',
  `QRY_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别查询权限',
  `EDIT_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色更新,号分割',
  `EDIT_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门更新,号分割',
  `EDIT_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人更新号分割',
  `NE_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色更新',
  `NE_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门更新',
  `NE_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人更新',
  `DEL_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色删除,号分割',
  `DEL_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门删除,号分割',
  `DEL_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人删除,号分割',
  `ND_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色删除',
  `ND_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门删除',
  `ND_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人查询',
  `EDIT_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别更新权限',
  `DEL_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别删除权限',
  PRIMARY KEY (`CATE_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arc_category_qx
-- ----------------------------
INSERT INTO `arc_category_qx` VALUES ('1818651435623804930', 'shopAdmin,qq_pm', '', '', '', '', '', '1', '0', '0', '0', '', '', '', '', '', '', '', '', '', '', '', NULL, '', '', '');
INSERT INTO `arc_category_qx` VALUES ('1818651612661182466', 'ceo', '', '', '', '', '', '1', '0', '0', '0', '', '', '', '', '', '', '', '', '', '', '', NULL, '', '', '');
INSERT INTO `arc_category_qx` VALUES ('CA1721925585898124', 'ceo,DBA,dept-manager', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `arc_category_qx` VALUES ('CA1721925700460185', 'iterationAdmin', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `arc_category_qx` VALUES ('CA1721926602138135', 'projectAdmin', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `arc_category_qx` VALUES ('CA1721926611614146', 'iterationAdmin', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `arc_category_qx` VALUES ('CA1722435236165186', 'iterationAdmin', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for arc_image
-- ----------------------------
DROP TABLE IF EXISTS `arc_image`;
CREATE TABLE `arc_image`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '附件名称',
  `URL` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问路径',
  `RELATIVE_PATH` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '硬盘存放路径',
  `FILE_SUFFIX` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '后缀名',
  `ROOT_PATH` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '根目录',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '存入时间',
  `FILE_SIZE` decimal(8, 0) NULL DEFAULT NULL COMMENT '文件大小',
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属部门',
  `TAG` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签',
  `REMARK` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注信息',
  `CATEGORY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '图片分类',
  `STORAGE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '硬盘存储名字(不带后缀)',
  `URL_PREFIX` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '链接前缀',
  `IS_OUT_URL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '是否外部链接',
  `OUT_URL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '外部链接',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `archive_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|知识库1|新闻2|内部公告3|平台公告5|论坛6|公文4|其它7|归档8|网站栏目,管理元数据categoryType',
  `rely_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考类型，开放式字段，1-开源社区，2-项目论坛,逗号 分割多个',
  `rely_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考编号,逗号 分割多个',
  `rely_sub_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考子类型,逗号 分割多个',
  `rely_sub_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考子编号,逗号 分割多个',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `cdate` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `ARC_IMAGE_INDEX_C`(`CATEGORY_ID` ASC, `BRANCH_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '图片素材库' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arc_image
-- ----------------------------
INSERT INTO `arc_image` VALUES ('IM1721921561788166', 'logo', 'http://127.0.0.1:7014/mdp/arc/image/IM1721921517495115/IM1721921561788166.png', '/IM1721921517495115', '.png', 'D:/arcfile/images', '2024-07-25 23:32:42', 45001, '', NULL, '', 'IM1721921517495115', 'IM1721921561788166', 'mdp/arc/image/', '0', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `arc_image` VALUES ('IM1721922161558151', 'logo', 'http://127.0.0.1:7014/mdp/arc/image/IM1721921710822158/IM1721922161558151.png', '/IM1721921710822158', '.png', 'D:/arcfile/images', '2024-07-25 23:42:42', 45001, '', NULL, '', 'IM1721921710822158', 'IM1721922161558151', 'mdp/arc/image/', '0', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `arc_image` VALUES ('IM1721922348531188', 'logo', 'http://127.0.0.1:7014/mdp/arc/image/IM1721921517495115/IM1721922348531188.png', '/IM1721921517495115', '.png', 'D:/arcfile/images', '2024-07-25 23:45:49', 45001, '', NULL, '', 'IM1721921517495115', 'IM1721922348531188', 'mdp/arc/image/', '0', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `arc_image` VALUES ('IM1721929310422157', 'logo', 'http://127.0.0.1:7014/mdp/arc/img/image/qqkj_001/IM1721922331903128/IM1721929310422157.png', 'qqkj_001/IM1721922331903128', '.png', 'D:/arcfile/images', '2024-07-26 01:41:50', 45001, '4gx1x7v74q', NULL, '', 'IM1721922331903128', 'IM1721929310422157', 'mdp/arc/img/image/', '0', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for arc_image_category
-- ----------------------------
DROP TABLE IF EXISTS `arc_image_category`;
CREATE TABLE `arc_image_category`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `CATEGORY_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类名称',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上一级',
  `is_pub` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否是公共分类的图片 1是0 否',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段',
  `crely_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考类型',
  `crely_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考类型编号',
  `crely_stype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '子参考类型',
  `crely_sid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '子参考类型编号',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '图片分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arc_image_category
-- ----------------------------
INSERT INTO `arc_image_category` VALUES ('IM1721921517495115', 'xxx', 'demo-branch-01', '0', NULL, NULL, NULL, NULL, NULL, NULL, 'ep:arrow-up-bold');
INSERT INTO `arc_image_category` VALUES ('IM1721921710822158', 'ddd', 'demo-branch-01', '0', NULL, NULL, NULL, NULL, NULL, NULL, 'ep:bowl');
INSERT INTO `arc_image_category` VALUES ('IM1721922331903128', '嘻嘻嘻', 'demo-branch-01', '0', NULL, NULL, NULL, NULL, NULL, NULL, 'ep:bicycle');

-- ----------------------------
-- Table structure for arc_tag
-- ----------------------------
DROP TABLE IF EXISTS `arc_tag`;
CREATE TABLE `arc_tag`  (
  `tag_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签名',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构号',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '标签编号',
  `shop_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '商户编号',
  `category_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签分组',
  `is_pub` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否公共0否1是',
  PRIMARY KEY (`id`, `branch_id`) USING BTREE,
  INDEX `categoryIdIndex`(`category_id` ASC) USING BTREE,
  INDEX `branch_id`(`branch_id` ASC, `category_id` ASC, `is_pub` ASC) USING BTREE,
  INDEX `tag_name`(`branch_id` ASC, `category_id` ASC, `tag_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arc_tag
-- ----------------------------
INSERT INTO `arc_tag` VALUES ('轻松', 'demo-branch-01', '1', NULL, '1', '0');
INSERT INTO `arc_tag` VALUES ('嘻嘻嘻', 'qqkj_001', '1', NULL, '1', '0');
INSERT INTO `arc_tag` VALUES ('888888888', 'qqkj_001', '10', NULL, '3', '0');
INSERT INTO `arc_tag` VALUES ('难', 'demo-branch-01', '2', NULL, '1', '0');
INSERT INTO `arc_tag` VALUES ('调的多', 'qqkj_001', '2', NULL, '1', '0');
INSERT INTO `arc_tag` VALUES ('很难', 'demo-branch-01', '3', NULL, '1', '0');
INSERT INTO `arc_tag` VALUES ('风丰富', 'qqkj_001', '3', NULL, '2', '0');
INSERT INTO `arc_tag` VALUES ('非常难', 'demo-branch-01', '4', NULL, '1', '0');
INSERT INTO `arc_tag` VALUES ('调的多2', 'qqkj_001', '4', NULL, '2', '0');
INSERT INTO `arc_tag` VALUES ('超级难', 'demo-branch-01', '5', NULL, '1', '0');
INSERT INTO `arc_tag` VALUES ('565656', 'qqkj_001', '5', NULL, '2', '0');
INSERT INTO `arc_tag` VALUES ('难上加难', 'demo-branch-01', '6', NULL, '1', '0');
INSERT INTO `arc_tag` VALUES ('2222', 'qqkj_001', '6', NULL, '2', '0');
INSERT INTO `arc_tag` VALUES ('很多', 'demo-branch-01', '7', NULL, '2', '0');
INSERT INTO `arc_tag` VALUES ('8888', 'qqkj_001', '7', NULL, '2', '0');
INSERT INTO `arc_tag` VALUES ('一般多', 'demo-branch-01', '8', NULL, '2', '0');
INSERT INTO `arc_tag` VALUES ('555555', 'qqkj_001', '8', NULL, '3', '0');
INSERT INTO `arc_tag` VALUES ('不多', 'demo-branch-01', '9', NULL, '2', '0');
INSERT INTO `arc_tag` VALUES ('5555555', 'qqkj_001', '9', NULL, '3', '0');

-- ----------------------------
-- Table structure for arc_tag_category
-- ----------------------------
DROP TABLE IF EXISTS `arc_tag_category`;
CREATE TABLE `arc_tag_category`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '分组编号',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '' COMMENT '机构号',
  `shop_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '' COMMENT '商户编号',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '' COMMENT '分组名称',
  `is_pub` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否公共0否1是',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段',
  PRIMARY KEY (`id`, `branch_id`) USING BTREE,
  INDEX `branch_id`(`branch_id` ASC, `is_pub` ASC) USING BTREE,
  INDEX `shopidIndex`(`branch_id` ASC, `category_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of arc_tag_category
-- ----------------------------
INSERT INTO `arc_tag_category` VALUES ('1', 'demo-branch-01', '', '难易程度', '0', NULL);
INSERT INTO `arc_tag_category` VALUES ('1', 'qqkj_001', '4gq2fb5y32', '测试', '0', NULL);
INSERT INTO `arc_tag_category` VALUES ('2', 'demo-branch-01', '', '朋友多少', '0', NULL);
INSERT INTO `arc_tag_category` VALUES ('2', 'qqkj_001', '4gq2fb5y32', '嘻嘻嘻', '0', NULL);
INSERT INTO `arc_tag_category` VALUES ('3', 'qqkj_001', '4gq2fb5y32', '5555', '0', NULL);

-- ----------------------------
-- Table structure for biz_coop
-- ----------------------------
DROP TABLE IF EXISTS `biz_coop`;
CREATE TABLE `biz_coop`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '公司编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '公司名称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phoneno` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '电话 ',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '合作要求',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0-初始，1-申请中，2-进行中，3-已结束',
  `ctime` datetime NULL DEFAULT NULL COMMENT '申请日期',
  `reply_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '根进人编号',
  `reply_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '跟进人姓名',
  `reply_time` datetime NULL DEFAULT NULL COMMENT '跟进时间',
  `biz_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '合作类型1-项目合作，2-广告合作，3-服务商入驻，4-校企合作',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '商务合作申请' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of biz_coop
-- ----------------------------

-- ----------------------------
-- Table structure for dm_data_set
-- ----------------------------
DROP TABLE IF EXISTS `dm_data_set`;
CREATE TABLE `dm_data_set`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '数据集编号',
  `ds_sql` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '数据集sql支持${obj.xxx}应用当前应用参数内置obj可以为currUser,currDept,currBranch,sysParams,dict及当前页面或者接口可访问的任意参数;其中系统参数支持currParams.参数编号};字典支持${dict.具体的itemCode}',
  `data_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据源-关联数据字典dm_data_source',
  `data_model` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据模型-关联字典dm_data_model',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据集标题',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建者',
  `cbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建者机构号',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `euserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最后修改人',
  `etime` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  `id_title_links` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '字段编号与title对照；[{id:字段编号,label:字段说明}]',
  `ds_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据集类型-字典dm_ds_type,1-sql,2-api',
  `api_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'api地址',
  `api_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '请求方法post/get',
  `api_header` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '请求头',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '数据集' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dm_data_set
-- ----------------------------

-- ----------------------------
-- Table structure for dm_model
-- ----------------------------
DROP TABLE IF EXISTS `dm_model`;
CREATE TABLE `dm_model`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '模型编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模型名称',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属企业编号',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of dm_model
-- ----------------------------

-- ----------------------------
-- Table structure for form_data
-- ----------------------------
DROP TABLE IF EXISTS `form_data`;
CREATE TABLE `form_data`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `TWO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性二',
  `THREE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性三',
  `FOUR` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性四',
  `FIVE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性五',
  `SIX` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性六',
  `SEVEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性七',
  `EIGHT` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性八',
  `NINE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性九',
  `TEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性十',
  `LTIME` datetime NULL DEFAULT NULL COMMENT '最后更新日期',
  `ONE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性一',
  `ZERO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性零',
  `REMARK` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注 ',
  `FORM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单编号',
  `USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最后更新人',
  `ELEVEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十一',
  `TWELVE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十二',
  `THIRTEEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十三',
  `FOURTEEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十四',
  `FIFTEEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十五',
  `ATT_URLS` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '附件url多个',
  `ATT_NAMES` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '附件名称多个',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `BIZ_KEY` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '唯一确定该业务的主键竖线分隔多个',
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
  `FSTATE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0初始1审批中2结束审批',
  `CTIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `dqx_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据权限码',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `tag_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签编号列表逗号分割',
  `tag_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签名列表逗号分割',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段json\r\n[\r\n{id:\'\',value:\'属性值\',name:\'属性名称\'},\r\n{id:\'\',value:\'属性值\',name:\'属性名称\'}\r\n]',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `BIZ_KEY`(`BIZ_KEY` ASC) USING BTREE,
  INDEX `FORM_ID`(`FORM_ID` ASC, `cuserid` ASC) USING BTREE,
  INDEX `FORM_ID_2`(`FORM_ID` ASC) USING BTREE,
  INDEX `FORM_ID_3`(`FORM_ID` ASC, `DEPTID` ASC) USING BTREE,
  INDEX `USERID`(`FORM_ID` ASC, `USERID` ASC) USING BTREE,
  INDEX `FORM_ID_4`(`FORM_ID` ASC, `CTIME` ASC) USING BTREE,
  INDEX `FORM_ID_5`(`FORM_ID` ASC, `USERID` ASC, `CTIME` ASC) USING BTREE,
  CONSTRAINT `form_data_ibfk_1` FOREIGN KEY (`FORM_ID`) REFERENCES `form_def` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单数据表-作废' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_data
-- ----------------------------

-- ----------------------------
-- Table structure for form_data_flow
-- ----------------------------
DROP TABLE IF EXISTS `form_data_flow`;
CREATE TABLE `form_data_flow`  (
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `agree` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批状态同意1不同意0',
  `assignee` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批人',
  `main_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程标题',
  `act_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批节点编号',
  `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批环节',
  `comment_msg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批意见',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `event_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '事件类型create/assignment/complete/delete/PROCESS_CREATED/PROCESS_COMPLETE/PROCESS_CANCELLED',
  `biz_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键发起时上送，原样返回',
  `model_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程key，可以根据该key找到对应的流程模型也代表审批事项，就是审什么内容',
  `flow_last_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `flow_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程审批机构号',
  `flow_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `start_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '启动人',
  `proc_def_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程定义编号带版本的',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模型名称，也代表审批事项，就是审什么内容',
  `form_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单编号',
  `form_data_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单数据编号',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单审核流程表-作废' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of form_data_flow
-- ----------------------------

-- ----------------------------
-- Table structure for form_def
-- ----------------------------
DROP TABLE IF EXISTS `form_def`;
CREATE TABLE `form_def`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `TABLE_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表名-当dataType=2,3时有效',
  `DS` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据源-当dataType=2,3时有效',
  `FORM_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单名称',
  `USERID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `DEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
  `FORM_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单类型',
  `TPL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为模板',
  `BIZ_TYPE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务分类',
  `CTIME` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `BRANCH_ID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `CATEGORY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类编号',
  `tag_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签编号列表',
  `tag_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签名字列表',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
  `data_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据存储模式1-api,crud，2-指定表格，3-独立表格（表单设计表根根谁自动变更），4-指定数据集，5-外部处理，9-存粹展示',
  `options` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '表单的扩展字段信息，对应formCreate.option',
  `funcs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '函数列表{func1:body1,func2:body2}',
  `rules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '表单生成规则列表，即页面元素定义列表，对应formCreate.rule',
  `form_qx` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '表单权限信息json字符串',
  `flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审核状态',
  `flow_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
  `flow_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审核人',
  `flow_start_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '发审人',
  `flow_start_time` datetime NULL DEFAULT NULL COMMENT '发审时间',
  `proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `USERID`(`USERID` ASC, `DEPTID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单定义' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_def
-- ----------------------------

-- ----------------------------
-- Table structure for form_field
-- ----------------------------
DROP TABLE IF EXISTS `form_field`;
CREATE TABLE `form_field`  (
  `FORM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '表单编号',
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键-字段编号对应数据库',
  `TITLE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段显示内容',
  `DICT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下拉时候关联的分组',
  `TYP` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段类型',
  `LEN` int NULL DEFAULT NULL COMMENT '字段长度',
  `DVAL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '默认值',
  `MUL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否多选',
  `REQ` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否必输',
  `ID_CAMEL` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段驼峰命名',
  `REMARK` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段备注',
  `TO_FLOW` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否提交到工作流',
  `BKEY` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为主键0否，1是',
  `SEQ` int NULL DEFAULT NULL COMMENT '显示顺序',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属组编号-用于解决多个tab页，或者多个子页面的问题',
  `show_style` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '展示风格origin-原生、tag-标签、x-综合',
  `style_obj` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '样式json',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段json\r\n[\r\n{id:\'\',value:\'属性值\',name:\'属性名称\'},\r\n{id:\'\',value:\'属性值\',name:\'属性名称\'}\r\n]',
  `qx` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '字段权限 json字符串\r\nothQuery:是否进行查询权限检查0-否，1是\r\nqryMinLvl:最低查询级别,\r\nqryRoleids:可查询的角色\r\nqryDeptid:可查询的部门\r\nqryUserids:可查询的人员\r\nnqRoleids:不可查询的角色\r\nnqDeptids:不可查询的部门\r\nnqUserids:不可查询的人员\r\n',
  `SPAN` int NULL DEFAULT NULL COMMENT '如果上级是row布局，存span属性,\r\n',
  `vrules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '验证器列表\r\n[{rule1},{rule2}]',
  `ext_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '扩展类型user、dept、att、img、row、card、tabs等',
  `hol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '输入提示，对应placeholder',
  `gname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分组名称，如果是tabs,这里存tab名称，逗号分割多个\r\n如果上级是tabs,存放tab名称，只存一个',
  `clazz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '样式',
  `ronly` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '只读',
  `clear` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可清除',
  `hidden` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否隐藏',
  PRIMARY KEY (`ID`, `FORM_ID`) USING BTREE,
  UNIQUE INDEX `FORM_ID`(`FORM_ID` ASC, `ID_CAMEL` ASC) USING BTREE,
  INDEX `FORM_ID_2`(`FORM_ID` ASC) USING BTREE,
  CONSTRAINT `form_field_ibfk_1` FOREIGN KEY (`FORM_ID`) REFERENCES `form_def` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单字段定义--作废' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of form_field
-- ----------------------------

-- ----------------------------
-- Table structure for form_qx
-- ----------------------------
DROP TABLE IF EXISTS `form_qx`;
CREATE TABLE `form_qx`  (
  `FORM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '表单编号',
  `QRY_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色查询,号分割',
  `QRY_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门查询,号分割',
  `QRY_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人查询,号分割',
  `NQ_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色查询',
  `NQ_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门查询',
  `NQ_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人查询',
  `OTH_QUERY` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人查询',
  `OTH_EDIT` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人修改',
  `OTH_DEL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人删除',
  `LVL_CHECK` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否进行部门级别传递权限检查',
  `QRY_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别查询权限',
  `EDIT_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色更新,号分割',
  `EDIT_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门更新,号分割',
  `EDIT_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人更新号分割',
  `NE_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色更新',
  `NE_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门更新',
  `NE_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人更新',
  `DEL_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色删除,号分割',
  `DEL_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门删除,号分割',
  `DEL_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人删除,号分割',
  `ND_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色删除',
  `ND_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门删除',
  `ND_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人查询',
  `EDIT_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别更新权限',
  `DEL_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别删除权限',
  PRIMARY KEY (`FORM_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单权限-作废' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of form_qx
-- ----------------------------

-- ----------------------------
-- Table structure for menu_def
-- ----------------------------
DROP TABLE IF EXISTS `menu_def`;
CREATE TABLE `menu_def`  (
  `ID` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '菜单编号',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级菜单编号',
  `ACC_URL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问路径',
  `MSORT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单顺序',
  `MCATE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单大类-指向menu_module.mcate',
  `ICON` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `MNAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `RPATH` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '路由PATH',
  `RID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '路由编号',
  `IS_SHOW` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否显示',
  `MENU_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '1' COMMENT '菜单类型1菜单2按钮',
  `OPER_QX_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '绑定的操作权限编号',
  `SUP_VERS` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '支持版本0-免费版可用，1-企业版。假如企业属于企业版，则可以适用免费版和企业版的功能。假如企业为免费版，则只可以使用免费版功能',
  `MODULE_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模块编号-指向menu_module.id',
  `AROLE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否所有角色可用，0-否，1-不区分角色均可以用',
  `QX_TYPE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '一般按钮才需要区分权限类型c-新增，r-读，u-更新，d-删除',
  `API_RULES` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对应后台的url地址，支持ant表达式匹配，用于后台接口调用时权限控制,多个逗号分隔,多个为or关系',
  `LVL` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单层级0-5级',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '前端功能菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu_def
-- ----------------------------
INSERT INTO `menu_def` VALUES ('101374796', '1509514699', '/mdp/sys/dept/index', '13.02', '2', NULL, '部门管理', 'dept/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1035577097', '430303105', '/mdp/workflow/ru/task/me/calendar', '23.01', '2', NULL, '我的日历', 'ru/task/me/calendar', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1037555514', '1697567528', '/mdp/larc/att', '27.00', '2', 'fa-solid:file', '附件库', 'att', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1037573171', '1697567528', '/mdp/larc/tag', '27.02', '2', 'fa-solid:dice-d20', '标签库', 'tag', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1041553970', '430303105', '/mdp/workflow/ru/execution/list/sponsors/me', '24.03', '2', NULL, '我主办的流程', 'ru/execution/list/sponsors/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('10736013', '430303105', '/mdp/workflow/ru/procinst/parames/exec/set', '23.03', '2', NULL, '任务执行路径', 'ru/procinst/parames/exec/set', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1097908644', '430303105', '/mdp/workflow/re/procdef/index', '26.03', '2', NULL, '模型激活/挂起', 're/procdef/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1110556970', '1697696753', '/mdp/plat/branchValidInfo', '14.02', '2', NULL, '企业认证审核', 'branchValidInfo', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('111465074', '1697402246', '/mdp/form/design/edit/:expandId', '19.02', '2', NULL, '表单编辑', 'design/edit/:expandId', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1156113951', '430303105', '/mdp/workflow/re/procdef/list/start', '23.07', '2', NULL, '发起流程', 're/procdef/list/start', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1191697019', '430303105', '/mdp/workflow/ru/execution/list/sponsors/me/calendar', '24.04', '2', NULL, '我的日历流程', 'ru/execution/list/sponsors/me/calendar', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1192371226', '1353667415', '/my/order/index', '22.03', '2', 'component', '购买产品', 'index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1194148255', '1353667415', '/my/order/addUsers', '22.05', '2', 'component', '增购人数', 'addUsers', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1200424405', '1353667415', '/my/order/renew', '22.06', '2', 'component', '续费', 'renew', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1203868345', '1697601223', '/mdp/meta/item/set/:itemCode', '15.02', '2', NULL, '参数改值', 'item/set/:itemCode', NULL, '0', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1223247853', '1295619531', '/mdp/dm/data/table', '20.03', '2', NULL, '表格数据', 'data/table', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1228083213', 'M0', '/mdp', '12', '2', 'simple-line-icons:people', '角色权限', '/mdp', '角色权限', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('128564337', '829954639', '/mdp/workflow/hi/procinst/list', '25.00', '2', NULL, '所有流程', 'procinst/list', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1295619531', 'M0', '/mdp/dm', '20', '2', 'ep:data-line', '数据模型', '/mdp/dm', 'router.dm.dataModel', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('1300623580', '1353667415', '/my/order/platfrom/list', '22.01', '2', 'component', '订单列表(平台)', 'platfrom/list', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1350779265', '430303105', '/mdp/workflow/ru/execution/list/start/me', '24.01', '2', NULL, '我发起的流程', 'ru/execution/list/start/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1352133025', '1697696753', '/mdp/plat/userValidInfo', '14.01', '2', NULL, '个人认证审核', 'userValidInfo', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1353667415', 'M0', '/my/order', '22', '2', 'simple-line-icons:note', '订单管理', '/my/order', '订单', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('1359026755', '829954639', '/mdp/workflow/hi/procinst/list/start/me', '25.01', '2', NULL, '我发起的流程', 'procinst/list/start/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('139389140', '1562684305', '/user/profile/index', '18.00', '2', 'ep:user', '个人中心', 'index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1505567404', '430303105', '/mdp/workflow/ru/procinst/parames/start/set', '23.04', '2', NULL, '任务启动路径', 'ru/procinst/parames/start/set', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1509514699', 'M0', '/mdp/sys', '13', '2', 'simple-line-icons:grid', '机构管理', '/mdp/sys', '机构管理', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('1509515363', 'M0', '/mdp/tpa', '16', '2', 'fa:wechat', '第三方管理', '/mdp/tpa', '第三方应用', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('151132983', '1228083213', '/mdp/menu/module/branch', '12.04', '2', NULL, '已开通模块', 'menu/module/branch', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1528380458', '1509514699', '/mdp/sys/user/adm', '13.05', '2', NULL, '机构管理员', 'user/adm', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1562684305', 'M0', '/user/profile', '18', '2', 'simple-line-icons:layers', '个人中心', '/user/profile', 'profile', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('1588766697', '1509514699', '/mdp/sys/branch/index', '13.03', '2', NULL, '公司管理', 'branch/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1666608115', '1353667415', '/my/order/paySuccess', '22.07', '2', 'component', '支付成功', 'paySuccess', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1697402246', 'M0', '/mdp/form', '19', '2', 'simple-line-icons:screen-tablet', '智能表单', '/mdp/form', 'router.form.IntelligentForm', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('1697567528', 'M0', '/mdp/larc', '27', '2', 'fa-solid:atom', '内容管理', '/mdp/larc', 'larc', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('1697601223', 'M0', '/mdp/meta', '15', '2', 'simple-line-icons:share-alt', '元数据管理', '/mdp/meta', '元数据管理', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('1697696753', 'M0', '/mdp/plat', '14', '2', 'simple-line-icons:globe-alt', '平台管理', '/mdp/plat', '平台管理', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('1705714802', '430303105', '/mdp/workflow/ru/execution/list/monitors/me', '24.00', '2', NULL, '我监控的流程', 'ru/execution/list/monitors/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1737218734', '1228083213', '/mdp/sys/qx/index', '12.01', '2', NULL, '权限管理', 'sys/qx/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1747114276', '430303105', '/mdp/workflow/hi/procinst/list/archive', '24.06', '2', NULL, '流程归档', 'hi/procinst/list/archive', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1766711081', '1509515363', '/mdp/tpa/user/index/:inviteId', '16.01', '2', NULL, '我邀请的用户', 'user/index/:inviteId', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1775273149', '829954639', '/mdp/workflow/hi/procinst/list/partake/me', '25.02', '2', NULL, '我参与的流程', 'procinst/list/partake/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1790045151', '1295619531', '/mdp/dm/data/list/:dataSource/:tableName', '20.04', '2', NULL, '表格数据查询', 'data/list/:dataSource/:tableName', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1797864725', '1697696753', '/mdp/plat/platform/PlatformMng', '14.00', '2', NULL, '平台管理', 'platform/PlatformMng', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1830797352', '1295619531', '/mdp/dm/model/index', '20.00', '2', NULL, '模型中心', 'model/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1861548730', '1509515363', '/mdp/tpa/user/index', '16.02', '2', NULL, '第三方用户查询', 'user/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1880153208', '430303105', '/mdp/workflow/re/procdef/list/parames', '26.04', '2', NULL, '模型设置', 're/procdef/list/parames', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1929392688', '829954639', '/mdp/workflow/hi/procinst/list/sponsors/me', '25.04', '2', NULL, '我主办的流程', 'procinst/list/sponsors/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1941517501', '1228083213', '/mdp/sys/role/index', '12.00', '2', NULL, '角色管理', 'sys/role/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1956765884', '1509514699', '/mdp/sys/user/unregister', '13.07', '2', NULL, '注销审核', 'user/unregister', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('1968097161', '1562684305', '/user/profile/message', '18.01', '2', 'fa:commenting-o', '消息中心', 'message', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('2007594547', '1353667415', '/my/order/branch/list', '22.00', '2', 'component', '已购模块(平台)', 'branch/list', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('2049969234', '1509514699', '/mdp/sys/user/index', '13.01', '2', NULL, '用户管理', 'user/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('2055788403', '430303105', '/mdp/workflow/ru/execution/list', '24.05', '2', NULL, '执行列表', 'ru/execution/list', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('2076968295', '1509514699', '/mdp/sys/post/index', '13.04', '2', NULL, '岗位管理', 'post/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('2092397775', '1697402246', '/mdp/form/data/add/:formId', '19.04', '2', NULL, '数据填报', 'data/add/:formId', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('2099671808', '1697567528', '/mdp/larc/icon', '27.03', '2', 'fa-solid:atom', '图标库', 'icon', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('2109297691', '1509514699', '/mdp/sys/user/join/require/adm', '13.06', '2', NULL, '加入审核', 'user/join/require/adm', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('2128569720', '430303105', '/mdp/workflow/re/deploy/index', '26.02', '2', NULL, '发布包管理', 're/deploy/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('225564674', '1353667415', '/my/order/alipay', '22.08', '2', 'component', '支付宝支付码页面', 'alipay', NULL, '0', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('255973150', '1697601223', '/mdp/meta/item/index', '15.00', '2', NULL, '字典管理', 'item/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('288231092', '1353667415', '/my/order/create', '22.04', '2', 'component', '创建订单', 'create', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('291886203', '430303105', '/mdp/workflow/ru/execution/list/partake/me', '24.02', '2', NULL, '我参与的流程', 'ru/execution/list/partake/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('293066218', '430303105', '/mdp/workflow/ru/task/list', '23.05', '2', NULL, '任务管理', 'ru/task/list', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('349802645', '1509514699', '/mdp/sys/branch/maxUsersSet', '13.08', '2', NULL, '用户数调整', 'branch/maxUsersSet', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('377450220', '1697601223', '/mdp/meta/item/option/index', '15.01', '2', NULL, '字典列表', 'item/option/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('430303105', 'M0', '/mdp/workflow', '26', '2', 'simple-line-icons:drawer', '模型审批', '/mdp/workflow', 'router.workflow.modelCenter', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('436078073', '1697402246', '/mdp/form/design/detail/:expandId', '19.01', '2', NULL, '表单明细', 'design/detail/:expandId', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('438340508', '1562684305', '/user/profile/loginRecord', '18.02', '2', 'fa:align-justify', '登录日志', 'loginRecord', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('446069872', '829954639', '/mdp/workflow/hi/procinst/list/monitors/me', '25.03', '2', NULL, '我监控的流程', 'procinst/list/monitors/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('447774473', '1697402246', '/mdp/form/index', '19.00', '2', NULL, '表单中心', 'index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('454902849', '1295619531', '/mdp/dm/data/set', '20.05', '2', NULL, '数据集', 'data/set', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('487521175', '430303105', '/mdp/workflow/ru/task/claim/me', '23.02', '2', NULL, '抢任务', 'ru/task/claim/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('490165531', '852072728', '/mdp/lcode/gen/index', '21.00', '2', 'simple-line-icons:organization', '代码生成', 'index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('581309924', '430303105', '/mdp/workflow/ru/task/me', '23.00', '2', NULL, '我的待执行', 'ru/task/me', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('58162402', '430303105', '/mdp/workflow/biz/model/index', '26.05', '2', NULL, '业务模型管理', 'biz/model/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('617719715', '430303105', '/mdp/workflow/re/procdef/list/biz/start', '23.06', '2', NULL, '任务启动模型', 're/procdef/list/biz/start', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('652790770', '1295619531', '/mdp/dm/data/set/list/:id', '20.07', '2', NULL, '数据集数据列表', 'data/set/list/:id', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('665600948', '1697567528', '/mdp/larc/image', '27.01', '2', 'fa-solid:images', '图片库', 'image', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('70237304', '1509515363', '/mdp/tpa/invite/index', '16.00', '2', NULL, '邀请管理', 'invite/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('703337785', '1509514699', '/mdp/sys/branch/set', '13.00', '2', NULL, '机构设置', 'branch/set', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('767843884', '430303105', '/mdp/workflow/de/model/index', '26.01', '2', NULL, '模型管理', 'de/model/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('781183081', '1353667415', '/my/order/my/list', '22.02', '2', 'component', '我的订单', 'my/list', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('804973324', '1295619531', '/mdp/dm/meta/index', '20.01', '2', NULL, '表格结构', 'meta/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('805642890', '1295619531', '/mdp/dm/data/set/create', '20.08', '2', NULL, '创建数据集', 'data/set/create', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('825785593', '1697402246', '/mdp/form/design/add', '19.03', '2', NULL, '表单设计', 'design/add', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('829954639', 'M0', '/mdp/workflow/hi', '25', '2', 'simple-line-icons:book-open', '历史审批', '/mdp/workflow/hi', 'router.workflow.procinstFlowCenter', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('836796753', '1697601223', '/mdp/meta/params/index', '15.03', '2', NULL, '参数定义', 'params/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('852072728', 'M0', '/mdp/lcode/gen', '21', '2', 'simple-line-icons:screen-tablet', '代码生成', '/mdp/lcode/gen', 'router.lcode.gen', '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '0');
INSERT INTO `menu_def` VALUES ('881723938', '1295619531', '/mdp/dm/meta/table/struct/:dataSource/:tableName', '20.02', '2', NULL, '表格结构', 'meta/table/struct/:dataSource/:tableName', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('909177694', '1295619531', '/mdp/dm/data/set/edit/:id', '20.06', '2', NULL, '数据集', 'data/set/edit/:id', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('911781021', '1228083213', '/mdp/menu/module/index', '12.03', '2', NULL, '模块管理', 'menu/module/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('95643492', '1228083213', '/mdp/menu/index', '12.02', '2', NULL, '功能菜单', 'menu/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');
INSERT INTO `menu_def` VALUES ('958385534', '430303105', '/mdp/workflow/editor/index', '26.00', '2', NULL, '模型编辑', 'editor/index', NULL, '1', '1', NULL, '0', 'xm-development', NULL, NULL, NULL, '1');

-- ----------------------------
-- Table structure for menu_favorite
-- ----------------------------
DROP TABLE IF EXISTS `menu_favorite`;
CREATE TABLE `menu_favorite`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `SORT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '排序',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '云用户机构编号',
  `CUSERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `CDATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ACC_URL` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问路径,路由则填写路由的path',
  `ICON` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `STYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单类型1-路由，2-自定义',
  `RNAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单类型为路由时，填写路由的name',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '菜单收藏夹' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu_favorite
-- ----------------------------

-- ----------------------------
-- Table structure for menu_module
-- ----------------------------
DROP TABLE IF EXISTS `menu_module`;
CREATE TABLE `menu_module`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模块名称',
  `fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '模块费用',
  `bill_mode` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费',
  `uni_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '人均费用，单位人天',
  `discount` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '折扣比率，可折上折，叠加折扣。-按最大人数、按天数优惠discount:{days:1:100|2:80|3:60,userNum: 0-10:100|10-50:98|50-100:95}',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '匹配的url,如果匹配得到，则计费，以逗号分割多个',
  `ignore_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '匹配这个地址的不计费',
  `biz_flow_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审核状态',
  `proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审核流程实例编号',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-下架，1-上架。开放后才可以购买使用',
  `mcate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类1-协同、2-研发、3-电商',
  `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'logo地址',
  `fee_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '费用解释',
  `udis_rate` int NULL DEFAULT NULL COMMENT '人数折算比例。企业总人数 x 折扣率为当前模块购买人数',
  `udis` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否折算人数0否1是，按企业总人数x人数折算比例计算',
  `sale_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '营销文案',
  `ucheck` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否控制总人数0-否1是',
  `crowd` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为众包',
  `seq` int NULL DEFAULT NULL COMMENT '序号0-1000，默认999',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '模块定义表-用于进行机构级别的权限控制，机构如果购买了模块，则能够进行访问' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu_module
-- ----------------------------
INSERT INTO `menu_module` VALUES ('mall', '商城', NULL, '0', 10.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '3', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('mall-marketing', '营销', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '3', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('mdp-arc', '内容管理', NULL, '0', 10.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('mdp-dm', '数据模型', 7500.00, '2', NULL, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '', '', '1', '2', '', '', NULL, '', '', '', '', NULL);
INSERT INTO `menu_module` VALUES ('mdp-sys', '组织架构', NULL, '0', 10.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-asset', '资产管理', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-attendance', '考勤管理', NULL, '0', 10.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-car', '车辆中心', NULL, '0', 10.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-contract', '合同管理', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-customer', '客户管理', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-file', '档案信息', NULL, '0', 2.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-finance', '财务', NULL, '1', 3.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-meeting', '会议管理', NULL, '0', 4.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-office', '办公用品', NULL, '0', 4.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-performance', '绩效考核', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-schedule', '日程管理', NULL, '0', 2.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-seal', '印章管理', NULL, '0', 2.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('oa-supervision', '督办管理', NULL, '0', 3.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '1', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('xm-analysis', '智能分析', NULL, '1', 4.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '2', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('xm-crowd', '众包', 1000.00, '2', 2.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '2', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('xm-development', '开发平台', NULL, '0', 2.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '2', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('xm-pipeline', '流水线', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '2', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('xm-product', '产品规划', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '2', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('xm-project', '项目管理', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '2', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('xm-test', '测试管理', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '2', '', '', 50, '1', '', NULL, NULL, NULL);
INSERT INTO `menu_module` VALUES ('xm-workload', '工时管理', NULL, '1', 1.00, '{\"days\":\"0-90:100\\n90-180:98\\n180-360:95\\n360-720:90\",\"userNum\":\"0-10:100\\n10-50:98\\n50-100:95\\n100-200:90\\n200-500:85\\n500-50000:80\"}', '', '', '2', '', '1', '2', '', '', 50, '1', '', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for menu_module_branch
-- ----------------------------
DROP TABLE IF EXISTS `menu_module_branch`;
CREATE TABLE `menu_module_branch`  (
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构编号',
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模块名称',
  `module_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '模块编号',
  `start_time` datetime NULL DEFAULT NULL COMMENT '启用日期',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束日期',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新日期 ',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `luserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '修改人编号',
  `lusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0停用1启用2待续费',
  `musers` int NULL DEFAULT NULL COMMENT '购买用户数',
  `mbid_num` int NULL DEFAULT NULL COMMENT '可投标次数-每月恢复为套餐数量，包含公司账户次数+个人以组织名义接单的次数，以上每接一单扣减此处',
  `sfee_rate` int NULL DEFAULT NULL COMMENT '服务费率，15=15%',
  `ver` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '购买版本0-免费版，1-企业版',
  `fee_date` datetime NULL DEFAULT NULL COMMENT '开始计费日期',
  `crowd` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为众包',
  `ousers` int NULL DEFAULT NULL COMMENT '企业总人数',
  `udis_rate` int NULL DEFAULT NULL COMMENT '人数折算比例。企业总人数 x 折扣率为当前模块购买人数',
  `udis` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否折算人数0否1是，按企业总人数 x 人数折算比例计算',
  `mcate` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类1-协同、2-研发、3-电商',
  PRIMARY KEY (`branch_id`, `module_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '机构开通模块对应关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu_module_branch
-- ----------------------------
INSERT INTO `menu_module_branch` VALUES ('BR1653413892864189', '智能分析', 'xm-analysis', '2022-05-29 04:04:49', '2022-11-25 04:04:49', '2022-05-29 04:04:49', '2022-05-29 04:04:49', NULL, NULL, NULL, NULL, '1', 25, NULL, NULL, '1', '2022-05-29 04:04:49', NULL, 50, 50, '1', '2');
INSERT INTO `menu_module_branch` VALUES ('BR1653413892864189', '众包', 'xm-crowd', '2022-05-29 04:04:49', '2022-11-25 04:04:49', '2022-05-29 04:04:49', '2022-05-29 04:04:49', NULL, NULL, NULL, NULL, '1', 25, NULL, NULL, '1', '2022-05-29 04:04:49', NULL, 50, 50, '1', '2');
INSERT INTO `menu_module_branch` VALUES ('BR1653413892864189', '流水线', 'xm-pipeline', '2022-05-29 04:04:49', '2022-11-25 04:04:49', '2022-05-29 04:04:49', '2022-05-29 04:04:49', NULL, NULL, NULL, NULL, '1', 25, NULL, NULL, '1', '2022-05-29 04:04:49', NULL, 50, 50, '1', '2');
INSERT INTO `menu_module_branch` VALUES ('BR1653413892864189', '产品规划', 'xm-product', '2022-05-29 04:04:49', '2022-11-25 04:04:49', '2022-05-29 04:04:49', '2022-05-29 04:04:49', NULL, NULL, NULL, NULL, '1', 25, NULL, NULL, '1', '2022-05-29 04:04:49', NULL, 50, 50, '1', '2');
INSERT INTO `menu_module_branch` VALUES ('BR1653413892864189', '测试管理', 'xm-test', '2022-05-29 04:04:49', '2022-11-25 04:04:49', '2022-05-29 04:04:49', '2022-05-29 04:04:49', NULL, NULL, NULL, NULL, '1', 25, NULL, NULL, '1', '2022-05-29 04:04:49', NULL, 50, 50, '1', '2');
INSERT INTO `menu_module_branch` VALUES ('qqkj_001', '智能分析', 'xm-analysis', '2022-05-28 17:29:13', '2023-05-23 17:29:13', '2022-05-28 17:29:13', '2022-05-28 17:29:13', NULL, NULL, NULL, NULL, '1', 105025, NULL, NULL, '1', '2022-05-28 17:29:13', NULL, 210050, 50, '1', '2');

-- ----------------------------
-- Table structure for menu_role
-- ----------------------------
DROP TABLE IF EXISTS `menu_role`;
CREATE TABLE `menu_role`  (
  `ROLEID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户组编号',
  `MID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '菜单编号',
  `HALF` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否半选状态',
  PRIMARY KEY (`ROLEID`, `MID`) USING BTREE,
  UNIQUE INDEX `MENU_ROLE_RM`(`ROLEID` ASC, `MID` ASC) USING BTREE,
  INDEX `SYS_ROLEMENU_FK_MID`(`MID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '菜单角色分配' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu_role
-- ----------------------------
INSERT INTO `menu_role` VALUES ('admin', '101374796', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1035577097', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1037555514', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1037573171', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1041553970', NULL);
INSERT INTO `menu_role` VALUES ('admin', '10736013', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1097908644', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1110556970', NULL);
INSERT INTO `menu_role` VALUES ('admin', '111465074', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1156113951', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1191697019', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1192371226', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1194148255', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1200424405', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1203868345', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1223247853', NULL);
INSERT INTO `menu_role` VALUES ('admin', '128564337', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1300623580', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1350779265', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1352133025', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1359026755', NULL);
INSERT INTO `menu_role` VALUES ('admin', '139389140', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1505567404', NULL);
INSERT INTO `menu_role` VALUES ('admin', '151132983', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1528380458', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1588766697', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1666608115', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1705714802', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1737218734', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1747114276', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1766711081', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1775273149', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1790045151', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1797864725', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1830797352', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1861548730', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1880153208', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1929392688', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1941517501', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1956765884', NULL);
INSERT INTO `menu_role` VALUES ('admin', '1968097161', NULL);
INSERT INTO `menu_role` VALUES ('admin', '2007594547', NULL);
INSERT INTO `menu_role` VALUES ('admin', '2049969234', NULL);
INSERT INTO `menu_role` VALUES ('admin', '2055788403', NULL);
INSERT INTO `menu_role` VALUES ('admin', '2076968295', NULL);
INSERT INTO `menu_role` VALUES ('admin', '2092397775', NULL);
INSERT INTO `menu_role` VALUES ('admin', '2099671808', NULL);
INSERT INTO `menu_role` VALUES ('admin', '2109297691', NULL);
INSERT INTO `menu_role` VALUES ('admin', '2128569720', NULL);
INSERT INTO `menu_role` VALUES ('admin', '225564674', NULL);
INSERT INTO `menu_role` VALUES ('admin', '255973150', NULL);
INSERT INTO `menu_role` VALUES ('admin', '288231092', NULL);
INSERT INTO `menu_role` VALUES ('admin', '291886203', NULL);
INSERT INTO `menu_role` VALUES ('admin', '293066218', NULL);
INSERT INTO `menu_role` VALUES ('admin', '349802645', NULL);
INSERT INTO `menu_role` VALUES ('admin', '377450220', NULL);
INSERT INTO `menu_role` VALUES ('admin', '436078073', NULL);
INSERT INTO `menu_role` VALUES ('admin', '438340508', NULL);
INSERT INTO `menu_role` VALUES ('admin', '446069872', NULL);
INSERT INTO `menu_role` VALUES ('admin', '447774473', NULL);
INSERT INTO `menu_role` VALUES ('admin', '454902849', NULL);
INSERT INTO `menu_role` VALUES ('admin', '487521175', NULL);
INSERT INTO `menu_role` VALUES ('admin', '490165531', NULL);
INSERT INTO `menu_role` VALUES ('admin', '581309924', NULL);
INSERT INTO `menu_role` VALUES ('admin', '58162402', NULL);
INSERT INTO `menu_role` VALUES ('admin', '617719715', NULL);
INSERT INTO `menu_role` VALUES ('admin', '652790770', NULL);
INSERT INTO `menu_role` VALUES ('admin', '665600948', NULL);
INSERT INTO `menu_role` VALUES ('admin', '70237304', NULL);
INSERT INTO `menu_role` VALUES ('admin', '703337785', NULL);
INSERT INTO `menu_role` VALUES ('admin', '767843884', NULL);
INSERT INTO `menu_role` VALUES ('admin', '781183081', NULL);
INSERT INTO `menu_role` VALUES ('admin', '804973324', NULL);
INSERT INTO `menu_role` VALUES ('admin', '805642890', NULL);
INSERT INTO `menu_role` VALUES ('admin', '825785593', NULL);
INSERT INTO `menu_role` VALUES ('admin', '836796753', NULL);
INSERT INTO `menu_role` VALUES ('admin', '881723938', NULL);
INSERT INTO `menu_role` VALUES ('admin', '909177694', NULL);
INSERT INTO `menu_role` VALUES ('admin', '911781021', NULL);
INSERT INTO `menu_role` VALUES ('admin', '95643492', NULL);
INSERT INTO `menu_role` VALUES ('admin', '958385534', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '101374796', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1035577097', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1037555514', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1037573171', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1041553970', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '10736013', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1097908644', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1110556970', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '111465074', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1156113951', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1191697019', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1192371226', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1194148255', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1200424405', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1203868345', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1223247853', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '128564337', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1300623580', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1350779265', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1352133025', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1359026755', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '139389140', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1505567404', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '151132983', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1528380458', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1588766697', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1666608115', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1705714802', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1737218734', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1747114276', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1766711081', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1775273149', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1790045151', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1797864725', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1830797352', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1861548730', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1880153208', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1929392688', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1941517501', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1956765884', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '1968097161', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '2007594547', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '2049969234', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '2055788403', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '2076968295', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '2092397775', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '2099671808', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '2109297691', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '2128569720', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '225564674', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '255973150', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '288231092', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '291886203', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '293066218', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '349802645', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '377450220', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '436078073', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '438340508', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '446069872', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '447774473', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '454902849', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '487521175', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '490165531', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '581309924', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '58162402', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '617719715', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '652790770', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '665600948', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '70237304', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '703337785', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '767843884', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '781183081', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '804973324', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '805642890', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '825785593', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '836796753', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '881723938', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '909177694', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '911781021', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '95643492', NULL);
INSERT INTO `menu_role` VALUES ('branchAdmin', '958385534', NULL);
INSERT INTO `menu_role` VALUES ('demoFree', '111465074', NULL);
INSERT INTO `menu_role` VALUES ('demoFree', '2092397775', NULL);
INSERT INTO `menu_role` VALUES ('demoFree', '436078073', NULL);
INSERT INTO `menu_role` VALUES ('demoFree', '447774473', NULL);
INSERT INTO `menu_role` VALUES ('demoFree', '825785593', NULL);

-- ----------------------------
-- Table structure for meta_category
-- ----------------------------
DROP TABLE IF EXISTS `meta_category`;
CREATE TABLE `meta_category`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '分类编号',
  `CATEGORY_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类名称',
  `IS_SHOW` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否显示0否1是',
  `REMARK` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `CTYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类性质0-基础数据，1-系统参数',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '元数据分类-作废' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of meta_category
-- ----------------------------
INSERT INTO `meta_category` VALUES ('all', '通用数据', '1', '通用数据', 'mktv4i2u1', NULL);
INSERT INTO `meta_category` VALUES ('COURT_ALL', '法院_通用数据', '0', '法院_通用数据', '', NULL);
INSERT INTO `meta_category` VALUES ('COURT_JCBM_TPL', '法院_目录模板', '0', '法院_目录模板', '', NULL);
INSERT INTO `meta_category` VALUES ('COURT_SYS_PARAMS', '法院_系统参数', '0', '法院_系统参数', '', NULL);
INSERT INTO `meta_category` VALUES ('sysParam', '系统参数', '1', '单一值系统参数分类', 'mktv4i2u1', NULL);

-- ----------------------------
-- Table structure for meta_item
-- ----------------------------
DROP TABLE IF EXISTS `meta_item`;
CREATE TABLE `meta_item`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `ITEM_CODE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '代码，小写,下横线分割，请不要用驼峰命名',
  `ITEM_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `CATEGORY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类编号 暂时sysParams系统参数，all-通用',
  `ITEM_SIZE` int NULL DEFAULT 50 COMMENT '长度',
  `ITEM_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '1-普通文本，2-数字，3-日期，4-单选列表，5-多选列表，6-单文件，7-多文件，8-富文本，9-单图文，10多图文,11-单视频，12-多视频，13单选radio,14多选checkbox',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号',
  `CMENU` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否创建菜单',
  `DVALUES` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '默认值,如果是列表，则存储列表编号，多个逗号分割',
  `DNAMES` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '默认名称，如果是列表，则存储列表名称，多个则逗号分割',
  `OPTION_LIST` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT 'item_type=4,5时的选项列表-指向item_option表关联的列表，该字段作废',
  `input_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '输入提示',
  `required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '是否必须0否1是',
  `seq` int NULL DEFAULT 999 COMMENT '排序顺序',
  `table_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表名',
  `is_show` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '1' COMMENT '是否显示0否1是',
  `qx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限，是否可以0-新增，1-删除，2-编辑，3-查询，多个以逗号分割',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段[{name:中文名称,id:编号,value:值,remark:备注,type:支持简单的1-普通文本2-数字，3-日期，8-富文本，9单图文，15-是否}]',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `META_ITEM_I_CODE`(`ITEM_CODE` ASC, `CATEGORY_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '数据项定义' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of meta_item
-- ----------------------------
INSERT INTO `meta_item` VALUES ('3gBiaK305', 'nnfy', '南宁法院', '', NULL, 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gC2xGt09', 'wjlx', '文件类型', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gDWYVm00', 'sj', '审级', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gDXO6P00', 'dafl', '档案类型', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gDYzD509', 'bgqx', '保管期限', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gFMPev01', 'fykjj', '法院快捷键', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gFNX2w00', 'ssdw', '诉讼地位', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gFsAmP01', 'qbfy', '全部法院', '', NULL, 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gMItqi01', 'txgjjklx', '挂接接口类型', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gMOcNO08', 'txgjjcgz', '图像挂接卷册规则', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gMOluC05', 'txgjthgz', '图像挂接图号规则', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gTalxF07', 'enabled', '是否启用', '', 'all', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gTtvEr07', 'locked', '锁定', '', 'ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gWtptH02', 'guest', '游客', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3gWuGEp05', 'tryout', '体验者', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3h3L4to00', 'fileDir', '文件存放目录', '', 'COURT_SYS_PARAMS', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3h3MW0r05', 'paramesPublish', '参数发布列表', '', 'COURT_SYS_PARAMS', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3h3mYjS07', 'state', '状态', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3h7Legi08', 'jzlb', '卷宗类别', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3hadoiA00', 'orgType', '组织类型', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3hamdcH04', 'deptType', '部门类型', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3hGfuve01', 'mb3', '2016年民事正卷', '', 'COURT_JCBM_TPL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3hHFWJY07', 'zd', '字第', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3hHUmiW08', 'JCBMMBLB', '卷册编目模板列表', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3hNLBt608', 'mb2', '2015模板2', '', 'COURT_JCBM_TPL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('3hP8v2L06', 'sex', '性别', '', 'COURT_ALL', 10, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('4qqyfcq263', 'urgencyLevel', '紧急程度', '紧急程度', 'all', 10, '4', '', '', NULL, '3', NULL, '[{\"id\":\"1\",\"name\":\"非常紧急\"},{\"id\":\"3\",\"name\":\"一般紧急\"},{\"id\":\"2\",\"name\":\"紧急\"},{\"id\":\"4\",\"name\":\"低\"}]', NULL, NULL, NULL, NULL, NULL, NULL, '[]');
INSERT INTO `meta_item` VALUES ('55c2u14kf9', 'overtimeType', '加班类型', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"双休日加班\"},{\"id\":\"0\",\"name\":\"工作日加班\"},{\"id\":\"2\",\"name\":\"法定假日加班\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('55gxectc61', 'customerType', '客户类型', '客户类型', 'all', 10, '4', '', '', NULL, 'fxs', NULL, '[{\"id\":\"kh\",\"name\":\"客户\"},{\"id\":\"dls\",\"name\":\"代理商\"},{\"id\":\"fxs\",\"name\":\"分销商\"},{\"id\":\"wbzy\",\"name\":\"外部资源\"},{\"id\":\"gryh\",\"name\":\"个人用户\"},{\"id\":\"jjds\",\"name\":\"竞争对手\"},{\"id\":\"hzhb\",\"name\":\"合作伙伴\"},{\"id\":\"yh\",\"name\":\"银行\"},{\"id\":\"ggmt\",\"name\":\"公关媒体\"},{\"id\":\"zjjg\",\"name\":\"中介机构\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('55jhbgr81k', 'language', '语言', '语言', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"TraditionalChinese\",\"name\":\"繁体中文\"},{\"id\":\"SimplifiedChinese\",\"name\":\"简体中文\"},{\"id\":\"English\",\"name\":\"英语\"},{\"id\":\"Japanese\",\"name\":\"日语\"},{\"id\":\"Korean\",\"name\":\"韩语\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('58qhz1pg6k', 'contractType', '合同类型', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"人事合同\",\"name\":\"人事合同\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('58qkc6p61f', 'contractStatus', '合同状态', '', 'all', 10, '4', '', '', NULL, '1', NULL, '[{\"id\":\"0\",\"name\":\"初始\"},{\"id\":\"1\",\"name\":\"正常\"},{\"id\":\"2\",\"name\":\"已终止\"},{\"id\":\"3\",\"name\":\"已暂停\"},{\"id\":\"4\",\"name\":\"未激活\"},{\"id\":\"5\",\"name\":\"履行中\"},{\"id\":\"6\",\"name\":\"已拆分\"},{\"id\":\"7\",\"name\":\"已完成\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('58rjb6tb7u', 'repuType', '奖惩类型', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"现金\",\"name\":\"现金\"},{\"id\":\"豪宅\",\"name\":\"豪宅\"},{\"id\":\"豪车\",\"name\":\"豪车\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('58u4k3j3cc', 'leaveType', '请假类型', '', 'all', 10, '4', '', '', NULL, '1', NULL, '[{\"id\":\"1\",\"name\":\"事假\"},{\"id\":\"2\",\"name\":\"病假\"},{\"id\":\"3\",\"name\":\"年假\"},{\"id\":\"4\",\"name\":\"婚假\"},{\"id\":\"5\",\"name\":\"哺乳假\"},{\"id\":\"6\",\"name\":\"丧假\"},{\"id\":\"7\",\"name\":\"外勤\"},{\"id\":\"8\",\"name\":\"独生子女假\"},{\"id\":\"9\",\"name\":\"销假\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('5aka5u7r3b', 'counterpartyType', '相对方类别', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"kh\",\"name\":\"客户\"},{\"id\":\"gys\",\"name\":\"供应商\"},{\"id\":\"hzhb\",\"name\":\"合作伙伴\"},{\"id\":\"zxdw\",\"name\":\"咨询单位\"},{\"id\":\"zjjg\",\"name\":\"中介机构\"},{\"id\":\"zfdw\",\"name\":\"政府单位\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('5akb2axzya', 'companyStatus', '单位状态', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"existence\",\"name\":\"存续\"},{\"id\":\"cancel\",\"name\":\"注销\"},{\"id\":\"deactive\",\"name\":\"吊销\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('5akcd5ww25', 'counterpartyIndustry', '相对方行业', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"nysh\",\"name\":\"能源石化\"},{\"id\":\"wlys\",\"name\":\"物流运输\"},{\"id\":\"kyys\",\"name\":\"科研院所\"},{\"id\":\"wbyl\",\"name\":\"文体娱乐\"},{\"id\":\"rysh\",\"name\":\"日用生化\"},{\"id\":\"swfw\",\"name\":\"商务服务\"},{\"id\":\"ysmt\",\"name\":\"影视媒体\"},{\"id\":\"fzfz\",\"name\":\"服装纺织\"},{\"id\":\"cgl\",\"name\":\"采购类\"},{\"id\":\"gcl\",\"name\":\"工程类\"},{\"id\":\"hzl\",\"name\":\"合作类\"},{\"id\":\"dll\",\"name\":\"代理类\"},{\"id\":\"fwwbl\",\"name\":\"服务外包类\"},{\"id\":\"zf\",\"name\":\"政府\"},{\"id\":\"zll\",\"name\":\"租赁类\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('5akhjkruhv', 'region', '区域', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"hz\",\"name\":\"华中\"},{\"id\":\"xb\",\"name\":\"西北\"},{\"id\":\"xn\",\"name\":\"西南\"},{\"id\":\"hn\",\"name\":\"华南\"},{\"id\":\"hd\",\"name\":\"华东\"},{\"id\":\"dn\",\"name\":\"东南\"},{\"id\":\"db\",\"name\":\"东北\"},{\"id\":\"hb\",\"name\":\"华北\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('5akmd16tsb', 'clearingForm', '结算方式', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"cash\",\"name\":\"现金结算\"},{\"id\":\"bank\",\"name\":\"银行转账\"},{\"id\":\"wireTransfer\",\"name\":\"电汇\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('5akxx3xxsj', 'counterpartyLevel', '相对方级别', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"A级\",\"name\":\"A级\"},{\"id\":\"B级\",\"name\":\"B级\"},{\"id\":\"D级\",\"name\":\"D级\"},{\"id\":\"C级\",\"name\":\"C级\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('5w3nc7tidx', 'invoiceType', '发票类型', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"增值税专用发票\"},{\"id\":\"2\",\"name\":\"增值税普通发票\"},{\"id\":\"3\",\"name\":\"增值税电子普通发票\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('alipaySet', 'alipaySet', '支付宝相关配置', '', 'sysParam', 50, '1', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[{\"id\":\"gateway\",\"name\":\"网关\",\"value\":\"https://openapi.alipay.com/gateway.do\",\"type\":\"2\",\"remark\":\"网关\",\"required\":\"1\"},{\"id\":\"appid\",\"name\":\"appid\",\"value\":\"2019090266776730\",\"type\":\"2\",\"remark\":\"appid\",\"required\":\"1\"},{\"id\":\"pubKey\",\"name\":\"pubKey\",\"value\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArAogHpBIHp17gATVdz6sFuuWTnpdhRwcFnTkEZfvyRY3O2NpVnUdH4bih77xgzC7agt3TCfXg7plQ8TUeTtKus1BnyHTDnerTi72tgHuDugCvKGjzSchTFZbmd4HkYq7L3xZKAv3hkd8vOKucwcjKW0eaClCiJu+gL4AHRK3mPhnc6x94OGeu7LAV7N7z7EwqVke/hk87Js0orff7pGYSDCIC5BIw6SNHWsJOL8X8OFdCc6DiSTP6mxAQR08Vgay5WtHTDftzJfKHQEK6x4SjTTdBbpALd+QOAJA2HOwCSdDmpZADAdm/9KXrctB53ElgWwAVwJuJMXnlJ97eemwXQIDAQAB\",\"type\":\"2\",\"remark\":\"公钥\",\"required\":\"1\"},{\"id\":\"primaryKey\",\"name\":\"primaryKey\",\"value\":\"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCa/l7i+SC1UVB9/NkFF4V1pmjmswfH0YVmFycRsFxHFwLhr3AzlwmlB07xAmAR4/i/xGwIxaxMtPY0FIk3EoZ6zpsJrudTx021t3PYEPvWcSyVZlOhPUf2lk3rYrgeY6GnBAO5gC7DNlVFB0QXQlS/FqjC0lWlbimGra4NQnmDRmrmnw0CL7dM+mcyOdmKcT+rNwz3wVRgThfgIAV9SQwLee1PQGP8g/FiKecyCg1n/fSNFuCtODq3QSDPHzhlJ+NjPSCIZxMLkthBMWTq+GlZtcEsaNjmIfMJb47NUm1bUtYnkCSOsHORgSwBdLqQOeSlT2mDjNjTQnSxTHudw3xNAgMBAAECggEACTT1A/ySgdLLukAr0A6eoed5scKOwsLibrcPXhCg2qQ2Spu2SYo+S2xMtypR9J4bJ6EOXdMIMiJJwbw6jmfuTxs4wHGAUkyzhAjau8n+e7WLVXCXpStTvAUfqczabQu0DYzCf+FqZbQzin8s/0i0agl5iuEdr08E7nggr670MuUC5B438ekVXnu3ldj0AnrXVXDro0fJDyz1FcRL4hNUbvOUcZK+o4B7AhuIDroITQ4rW8t1OB8tpmExlY+y7PKWtVL87+6Axs2ZgG+SL227R/VYITfcs8haAkwsB50PZvqQiic4WqmMzt/h7aGcyzZDZKVdSS8gc4yitsLRgCf74QKBgQDu3lAJZznWE1YIGyKJqogFtcrVtBVK+riQefHwk6V2dKz91d61woXBMhnvhdkQWndxUzpidcbcCTfVJu/lTpmMQDPLW5kO2vdQaW4TEBfyO/JgyQk8RwJcInaQCmOve9n/5Qhwry3Ox7QJUJ57JHWZs9o0E+MXGNb1H7kHyT6N+QKBgQCmHBhMZGDqyelp+BVIalf6FoxuwHRyrfvVNdJvxGfGoHdbX+uhDvqJtoEP67Vs5zkFbmoz771FU2488Wv894y4Ekmxkl+gSB/iMYTwd0HfoLbPjLyI2fllq/mQwrq5qWuV1aYdnxKgBpVA1yONbDksLIaCAmkCD9XQOpuGDA3F9QKBgBFZZEbKkJDT5rPABMoq8KY5hfnvknxXqYAU21W145au8sGc/wgEkOJvWSLkfdeKOpKokN0F4OOqZ7u7kED/bHebUie7B2rkzIGypqQLzzFw2THeaCVQowM+HY5ossTUIglMAUjt8E3Mg28Jw/j9tOzs+zQ9HYlX7ITxQOcn3rtBAoGBAKRT8u3P99kOOq2s0VE8cyBjojYGiqzQ1evNPQt437kuEiYBNJe2u7/1HqTjaC3+aPiojMQojbPBn7MGmvA87adD4EedKKDl+TTKDFvffvl+gpu8/N7d1M3yXggJvLPjmR03qQhwz/ojyQ/GRdOolr7OpZqES+HQTJOdBzKLRizpAoGAXX+eYSiiUeC+LdlXaH7uIi3pv+os0oETe0/BVlUjdslBz5/yOZLxrPfmmrvCUQOmfAlTCOQy3pdzuPBkACcP/+lhOKTO0wbji0HLV2Xrlq3ZK63KmLkJw4/LscXKzpS7ALqWrCQVU1OeoXmts76ncoKmpCy69Bz0XboJ+Bk2EoM=\",\"type\":\"2\",\"remark\":\"私钥\",\"required\":\"1\"},{\"id\":\"notifyUrl\",\"name\":\"支付结果通知\",\"value\":\"https://www.maimengcloud.com/api/m1/tpa/pay/alipay/notify\",\"type\":\"2\",\"remark\":\"支付结果通知\",\"required\":\"1\"}]');
INSERT INTO `meta_item` VALUES ('alipaySet2', 'alipaySet2', '支付宝相关配置', '', 'sysParam', 50, '1', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[{\"id\":\"gateway\",\"name\":\"网关\",\"value\":\"https://openapi.alipay.com/gateway.do\",\"type\":\"2\",\"remark\":\"网关\",\"required\":\"1\"},{\"id\":\"appid\",\"name\":\"appid\",\"value\":\"2021002173608161\",\"type\":\"2\",\"remark\":\"appid\",\"required\":\"1\"},{\"id\":\"pubKey\",\"name\":\"pubKey\",\"value\":\"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA42iH9W9D0xaYF48LqTy5mDzmcaS4ZQikxp7+fCfKc6a7ZDzruVmwqE11gAdaLIe6+QZqI65mDCmxLWN0q6MjJ2weAn1xdz6CAb8sVS5Ap7pk1eAE+7Pd7VGmv5tSOdVOd47HGVZ+cydBVY3oOOdcmodjU2WLwgznKUxYGWwxuO4R28v3WZkPOzDzolDhPk8DTbB/aG/cGIJluXNuCe4kkyou64G+mJy7vxFycbieZh1xfYxtp5g2qHyQkGXh8Vc9jttZlaAYHQtB0kmCBxiVqL+JipBM28sy63e7w1qMJw2Sub5RlF8fo1sBvNgSL1WVv6iIjOm+q+48uNXj2YzYYwIDAQAB\",\"type\":\"2\",\"remark\":\"公钥\",\"required\":\"1\"},{\"id\":\"primaryKey\",\"name\":\"primaryKey\",\"value\":\"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdxJfveL3H9WbjCbXEfPGOFFbu1evsFAwMA7hMAEBhEUw1RIacNhNmkPuAgnI/2ZVplTIvdcT/Htvv/X4BsZJIbSiVS22Cf5F1PN7qLFtP9k5z9UpriYyN4V5nLKOmirpijhvaMNtsepL+rY5biQgehyig/TPp2MrMZ/MRNhCzje1E80oE/CKdquckCbSdP5i6/a1UEACMKpVnvu73sfKWprt59hng3ltKPhF5kLgbFrzIUkQj/adGcxb2sUawmTpBITWL6JWblmDohw4jIXPYMtAFxiFUkvkp1d6vYTnA5s6QsCODU5uEdBwgCSFciWuRI3undqYt7V3KAStr2WMNAgMBAAECggEAJJiPrcrSKunvvcZo1XUuCwkIyUnx+ccErFzIiUidpmZ+yPRmTSH2ChqjXEHmAo2ULPOguoWU9qDP37FrYzUve4FmoormkhjJQuqlwqgbXkcCF7/UTXGQmvCmF0SxiVYwud/A8jHXFCiypETZ+r2kloA/mmhhfGL/V77dESb7ZMqun54WTeZ1F0lUtnTIUB8968Ukx+2yu4LNwvU09MTnrhaj24klTgISC1cyOyCgVEaIR81m47hTsmV6Zoa1NReTtZmFMktyKcDH2rEjaA/1lCSugIqXEfWyghZrYAfW2/uHe3VXajkvqd76qMbdj9lRtdyBMFEEINtTRwL22nzzwQKBgQDuaXPWvDzEZ7OUM8y9Ogk0gVLoJFgmkGfZLm2Bzx02+AY+fjhDGtxnrUz9euuSb9hZCeRxL5U7fm7G+kuVACP/AjZAxDASJzHDUGUomfZXzKLJ/48yFvryvpJL9OkA8KckrWN4svQg39fHtbW3ecVaHQQukg49MhFgmsI108wWmQKBgQCpaCH04lkiGMI2AtZ/4F+/4nPDC+iTM61LIZdY2ZqvyAOTNYTn7kP2jZi1pvZTFncys1Hs78X1p3ZfWAVxGEeMU61xiH40wi1rym7rrdKa6khXk3udOJioOGRRpse5kwMma0jYwrBYm882AbDV6yMnU1klMWRtqt5gxR3r+IOclQKBgQDW0/LrtibTm9Y3Xw3IHPmadEXupIFCDrFlA+7tH2Hl3ExUF4w++39LdN+BMTgAdgPvB3jvfL7uIxlS9ssQclX9PVMBvUbLtMGki1b75PATYXP2rO+tZQOvpIVTKFak4DTcWdjeM/LDhLB9ZoFd2L46Wxcfl8B46Bq8f/csZbLrUQKBgE3MPbPpcweemSoWuY02bKKBi6oqQN/BHrdfMNMj888AKuwi6utcV6fVtSjPCVZ0/b6x7VDDeITtKAZ3NOCQRuNh1khKZ7Mw7Y0QBUqEpDByoVBesaktQaYXZ7K7xgMqSYsOQAETv8qhm1JxClXjS1yXAVx8R2O50bBdNfWVRlPVAoGALWtH2X5N0mqF/Dpjku306hgUKfh5CG3EDh3i7iU9hycnoqt2D+rhgQRbDN8sG0WfBQpd1o1cRcYPFLDnEeLCFYm0VZfg6pt79i14STyMp7mw/pEmisWzW5N4zmeiejzNYQv+5Fp5CB8Ft0gUaug+5MnFCyka9LGf6I91BSEGoDQ=\",\"type\":\"2\",\"remark\":\"私钥\",\"required\":\"1\"},{\"id\":\"notifyUrl\",\"name\":\"支付结果通知\",\"value\":\"https://www.qingqinkj.com/api/wzsc/mall/mall/tpa/alipay/notify\",\"type\":\"2\",\"remark\":\"支付结果通知\",\"required\":\"1\"}]');
INSERT INTO `meta_item` VALUES ('arcImageDownloadBaseUri', 'arcImageDownloadBaseUri', '内容管理器图片上传下载基础uri', '', 'sysParam', 50, '1', NULL, NULL, NULL, 'https://www.qingqinkj.com/api/m1/arc/arc', NULL, NULL, NULL, '0', 999, NULL, '1', NULL, NULL);
INSERT INTO `meta_item` VALUES ('arcImageUploadRootPath', 'arcImageUploadRootPath', '内容管理图片文件存储硬盘路径', NULL, 'sysParam', 50, '1', NULL, NULL, NULL, '/home/mall/m1/arcfile/images/', NULL, NULL, NULL, '0', 999, NULL, '1', NULL, NULL);
INSERT INTO `meta_item` VALUES ('bookTimes', 'bookTimes', '预约时间段', '预约时间段', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"10:00-10:30\",\"name\":\"10:00-10:30\"},{\"id\":\"10:30-11:00\",\"name\":\"10:30-11:00\"},{\"id\":\"11:00-11:30\",\"name\":\"11:00-11:30\"},{\"id\":\"11:30-12:00\",\"name\":\"11:30-12:00\"},{\"id\":\"12:00-12:30\",\"name\":\"12:00-12:30\"},{\"id\":\"12:30-13:00\",\"name\":\"12:30-13:00\"},{\"id\":\"13:00-13:30\",\"name\":\"13:00-13:30\"},{\"id\":\"13:30-14:00\",\"name\":\"13:30-14:00\"},{\"id\":\"14:00-14:30\",\"name\":\"14:00-14:30\"},{\"id\":\"14:30-15:00\",\"name\":\"14:30-15:00\"},{\"id\":\"15:00-15:30\",\"name\":\"15:00-15:30\"},{\"id\":\"15:30-16:00\",\"name\":\"15:30-16:00\"},{\"id\":\"16:00-16:30\",\"name\":\"16:00-16:30\"},{\"id\":\"16:30-17:00\",\"name\":\"16:30-17:00\"},{\"id\":\"17:00-17:30\",\"name\":\"17:00-17:30\"},{\"id\":\"17:30-18:00\",\"name\":\"17:30-18:00\"},{\"id\":\"18:00-18:30\",\"name\":\"18:00-18:30\"},{\"id\":\"18:30-19:00\",\"name\":\"18:30-19:00\"},{\"id\":\"19:00-19:30\",\"name\":\"19:00-19:30\"},{\"id\":\"19:30-20:00\",\"name\":\"19:30-20:00\"},{\"id\":\"20:00-20:30\",\"name\":\"20:00-20:30\"},{\"id\":\"20:30-21:00\",\"name\":\"20:30-21:00\"},{\"id\":\"21:00-21:30\",\"name\":\"21:00-21:30\"},{\"id\":\"8:00-8:30\",\"name\":\"8:00-8:30\"},{\"id\":\"8:30-9:00\",\"name\":\"8:30-9:00\"},{\"id\":\"9:00-9:30\",\"name\":\"9:00-9:30\"},{\"id\":\"9:30-10:00\",\"name\":\"门店\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('bugSeverity', 'bugSeverity', 'bug严重程度', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"致命\"},{\"id\":\"2\",\"name\":\"严重\"},{\"id\":\"4\",\"name\":\"轻微\"},{\"id\":\"3\",\"name\":\"普通\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('bugSolution', 'bugSolution', 'bug解决方案', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"2\",\"name\":\"重复BUG\"},{\"id\":\"4\",\"name\":\"已解决\"},{\"id\":\"3\",\"name\":\"外部原因\"},{\"id\":\"5\",\"name\":\"无法重现\"},{\"id\":\"1\",\"name\":\"设计如此\"},{\"id\":\"6\",\"name\":\"延期处理\"},{\"id\":\"7\",\"name\":\"不予解决\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('bugStatus', 'bugStatus', 'bug状态', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"新提交\"},{\"id\":\"2\",\"name\":\"处理中\"},{\"id\":\"3\",\"name\":\"已修复\"},{\"id\":\"4\",\"name\":\"重新打开\"},{\"id\":\"5\",\"name\":\"已发布\"},{\"id\":\"6\",\"name\":\"已拒绝\"},{\"id\":\"7\",\"name\":\"挂起\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('bugType', 'bugType', 'bug类型', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"2\",\"name\":\"低级缺陷\"},{\"id\":\"1\",\"name\":\"代码错误\"},{\"id\":\"3\",\"name\":\"设计缺陷\"},{\"id\":\"4\",\"name\":\"配置相关\"},{\"id\":\"6\",\"name\":\"性能问题\"},{\"id\":\"5\",\"name\":\"安全相关\"},{\"id\":\"7\",\"name\":\"其他\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('categoryId', 'categoryId', '字典分类', '字典分类', 'all', 10, '4', '', '', NULL, 'all', NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('commentcontype', 'commentcontype', '评价条件类型', '评价条件类型', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"order\",\"name\":\"订单\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('commentItemfocus', 'commentItemfocus', '评价项目聚焦', '评价项目聚焦', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"focusgoods\",\"name\":\"商品\"},{\"id\":\"focusorder\",\"name\":\"订单\"},{\"id\":\"focusorderandgoods\",\"name\":\"订单和商品\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('commonResOper', 'commonResOper', '通用资源操作', '通用资源操作', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"add\",\"name\":\"增\"},{\"id\":\"del\",\"name\":\"删\"},{\"id\":\"list\",\"name\":\"查\"},{\"id\":\"batchEdit\",\"name\":\"批量修改\"},{\"id\":\"export\",\"name\":\"导出\"},{\"id\":\"edit\",\"name\":\"改\"},{\"id\":\"batchDel\",\"name\":\"批量删\"},{\"id\":\"allOper\",\"name\":\"全部操作\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('contactType', 'contactType', '联系方式', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"wx-person\",\"name\":\"个人微信号\"},{\"id\":\"qq-person\",\"name\":\"个人qq号\"},{\"id\":\"office-phone\",\"name\":\"办公电话\"},{\"id\":\"phoneno\",\"name\":\"手机号\"},{\"id\":\"csworker\",\"name\":\"客服\"},{\"id\":\"qq-group\",\"name\":\"qq群\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('courseAtt', 'courseAtt', '网络课程属性', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"随堂\"},{\"id\":\"1\",\"name\":\"自主学习\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('currency', 'currency', '资产币种', '资产币种', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"CNY\",\"name\":\"人民币\"},{\"id\":\"HKD\",\"name\":\"港元\"},{\"id\":\"USD\",\"name\":\"美元\"},{\"id\":\"GBP\",\"name\":\"英镑\"},{\"id\":\"EUR\",\"name\":\"欧元\"},{\"id\":\"JPY\",\"name\":\"日元\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('dataLvl', 'dataLvl', '数据权限等级', '', 'all', 20, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"禁止所有数据访问\"},{\"id\":\"-1\",\"name\":\"未分配\"},{\"id\":\"2\",\"name\":\"可操作本部门及下属部门数据\"},{\"id\":\"1\",\"name\":\"可操作本部门数据\"},{\"id\":\"3\",\"name\":\"可操作上一级、本级、下级部门数据\"},{\"id\":\"5\",\"name\":\"可操作本机构所有数据\"},{\"id\":\"4\",\"name\":\"可操作上两级及以下部门数据\"},{\"id\":\"6\",\"name\":\"可操作全平台数据\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('deptType', 'deptType', '部门类型', '部门类型', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"技术部门\"},{\"id\":\"2\",\"name\":\"管理部门\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('eduBackground', 'eduBackground', '学历', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"大专\",\"name\":\"大专\"},{\"id\":\"本科\",\"name\":\"本科\"},{\"id\":\"研究生\",\"name\":\"研究生\"},{\"id\":\"博士\",\"name\":\"博士\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('gbuyTimes', 'gbuyTimes', '团购时间段', '团购时间段', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"gbuyTimes08:00\",\"name\":\"08:00\"},{\"id\":\"gbuyTimes10:00\",\"name\":\"10:00\"},{\"id\":\"gbuyTimes12:00\",\"name\":\"12:00\"},{\"id\":\"gbuyTimes14:00\",\"name\":\"14:00\"},{\"id\":\"gbuyTimes16:00\",\"name\":\"16:00\"},{\"id\":\"gbuyTimes18:00\",\"name\":\"18:00\"},{\"id\":\"gbuyTimes20:00\",\"name\":\"20:00\"},{\"id\":\"gbuyTimes22:00\",\"name\":\"22:00\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('goodsStatus', 'goodsStatus', '商品状态', '商品状态', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"未启用\"},{\"id\":\"1\",\"name\":\"正常\"},{\"id\":\"3\",\"name\":\"淘汰\"},{\"id\":\"2\",\"name\":\"预淘汰\"},{\"id\":\"4\",\"name\":\"试销\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('healCondition', 'healCondition', '健康情况', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"健康\",\"name\":\"健康\"},{\"id\":\"生病\",\"name\":\"生病\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('helpCategory', 'helpCategory', '商场帮助分类', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"shoppingGuide\",\"name\":\"购物指南\"},{\"id\":\"accountSupport\",\"name\":\"账户帮助\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('i3k69g7z4', 'rbizCategory', '业务大类', '业务分类', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"wxpub\",\"name\":\"微信公众号\"},{\"id\":\"ac\",\"name\":\"会计核心\"},{\"id\":\"mallWxa\",\"name\":\"商城小程序\"},{\"id\":\"mallm\",\"name\":\"商城后台\"},{\"id\":\"mallFront\",\"name\":\"商城前端\"},{\"id\":\"arc\",\"name\":\"内容管理\"},{\"id\":\"sysAdmin\",\"name\":\"系统管理\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('i8yf45127', 'rtype', '资源类型', '资源类型', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"url\"},{\"id\":\"2\",\"name\":\"菜单\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('IndustryCategory', 'industryCategory', '行业分类', '行业分类', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"computersSoftware\",\"name\":\"计算机软件\"},{\"id\":\"computersHardware\",\"name\":\"计算机硬件\"},{\"id\":\"zfdw-sb\",\"name\":\"政府单位-社保\"},{\"id\":\"sp\",\"name\":\"食品\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('IT1631307316781128', 'receipt_bill', '发票内容选项', '', 'all', NULL, '4', '', '', '', '1', '', '[{\"id\":\"1\",\"name\":\"书籍\"},{\"id\":\"2\",\"name\":\"电子书\"},{\"id\":\"3\",\"name\":\"文化用品\"}]', '', '', NULL, '', '', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1631395478646195', 'deptLvl', '部门级别', '', 'all', 50, '4', '', '', '', 'L0', '', '[{\"id\":\"L1\",\"name\":\"一级\"},{\"id\":\"L2\",\"name\":\"二级\"},{\"id\":\"L3\",\"name\":\"三级\"},{\"id\":\"L4\",\"name\":\"四级\"},{\"id\":\"L5\",\"name\":\"五级\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1631427826041171', 'meta_push_target', '元数据发布目标应用', '已发布的元数据，客户才可以改动，未发布的数据属于原始数据，不允许前端用户订阅及修改', 'all', 50, '4', '', '', '', 'mall', '', '[{\"id\":\"mall\",\"name\":\"商城\"},{\"id\":\"erp\",\"name\":\"ERP\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1631666500591186', 'item_type', '字典输入类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[{\"id\":\"xx\",\"name\":\"字段名0(请修改)\",\"value\":\"xxx\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"}]');
INSERT INTO `meta_item` VALUES ('IT1631738666558189', 'ordersSet', '', '', 'sysParam', 50, '1', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[{\"id\":\"autoCloseMinutes\",\"name\":\"正常订单超过(分钟)\",\"value\":\"4\",\"type\":\"2\",\"remark\":\"未付款，订单自动关闭\",\"required\":\"1\"},{\"id\":\"autoFinishDays\",\"name\":\"发货超过(天)\",\"value\":\"\",\"type\":\"2\",\"remark\":\"未收货，订单自动完成\",\"required\":\"1\"},{\"id\":\"autoOverDays\",\"name\":\"订单完成超过(天)\",\"value\":\"3\",\"type\":\"2\",\"remark\":\"自动结束交易，不能申请售后\",\"required\":\"1\"},{\"id\":\"autoCommentDays\",\"name\":\"订单完成超过(天)\",\"value\":\"\",\"type\":\"2\",\"remark\":\"自动五星好评\",\"required\":\"1\"}]');
INSERT INTO `meta_item` VALUES ('IT1631954002123139', 'ebook_time', '电子书有效期', '', 'all', 50, '4', '', '', '', '', '月', '[{\"id\":\"0\",\"name\":\"个月\"},{\"id\":\"1\",\"name\":\"个季度\"},{\"id\":\"2\",\"name\":\"年\"}]', '请输入', '', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1633431887609192', 'wzscWebUrl', '书城前台', '', 'all', NULL, '1', '', '', '', 'http://39.103.164.98/wuzhou/topic-details.html?id=', '', '', '', '', NULL, '', '', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1634203145935138', 'orgExcelUrl', '书城机构Excel模板', '', 'all', NULL, '1', '', '', '', 'http://wuzhou.admin.xink.cebest.com:30080/api/wzsc/arc/arc/file/CS16336076294186/CS16336076294186/AT1634202724396136.xlsx', '', '', '', '', NULL, '', '', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1640590217408146', 'productType', '合同产品类型', '', 'all', NULL, '4', '', '', '', '1', '', '[{\"id\":\"1\",\"name\":\"软件\"},{\"id\":\"2\",\"name\":\"硬件\"}]', '', '', NULL, '', '', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1640590365394155', 'costType', '成本类型', '', 'all', NULL, '4', '', '', '', '2', '', '[{\"id\":\"1\",\"name\":\"顾问成本\"},{\"id\":\"2\",\"name\":\"实施成本\"},{\"id\":\"3\",\"name\":\"开发成本\"},{\"id\":\"4\",\"name\":\"培训成本\"},{\"id\":\"5\",\"name\":\"其他成本\"}]', '', '', NULL, '', '', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1640590560478117', 'amountType', '款项类别', '', 'all', NULL, '4', '', '', '', '1', '', '[{\"id\":\"1\",\"name\":\"首付款\"},{\"id\":\"2\",\"name\":\"方案设计款\"},{\"id\":\"3\",\"name\":\"开发阶段款\"},{\"id\":\"4\",\"name\":\"测试阶段款\"},{\"id\":\"5\",\"name\":\"验收试运营款\"},{\"id\":\"6\",\"name\":\"中期款\"},{\"id\":\"7\",\"name\":\"尾款\"}]', '', '', NULL, '', '', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1645446807730187', 'xmProductPstatus', '项目管理产品状态', '', 'all', 50, '4', '', '', '', '0', '', '[{\"id\":\"0\",\"name\":\"打开\"},{\"id\":\"1\",\"name\":\"研发中\"},{\"id\":\"2\",\"name\":\"已完成\"},{\"id\":\"3\",\"name\":\"已关闭\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1647242369307127', 'demandType', '需求类型', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"新增功能\"},{\"id\":\"1\",\"name\":\"功能改进\"},{\"id\":\"2\",\"name\":\"bug修复\"},{\"id\":\"3\",\"name\":\"用户体验\"},{\"id\":\"4\",\"name\":\"UI优化\"},{\"id\":\"5\",\"name\":\"内部需求\"},{\"id\":\"6\",\"name\":\"删除需求\"},{\"id\":\"7\",\"name\":\"接口需求\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1647242435289138', 'demandLvl', '需求层次', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"基础需求\"},{\"id\":\"1\",\"name\":\"增值需求\"},{\"id\":\"2\",\"name\":\"扩展需求\"}]', '请输入', '', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1647242537115125', 'demandSource', '需求来源', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"部门意见\"},{\"id\":\"2\",\"name\":\"用户反馈\"},{\"id\":\"3\",\"name\":\"技术反馈\"},{\"id\":\"4\",\"name\":\"运营反馈\"},{\"id\":\"5\",\"name\":\"团队讨论\"},{\"id\":\"6\",\"name\":\"老板需求\"},{\"id\":\"7\",\"name\":\"自身需求\"}]', '请输入', '', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1647268434864172', 'menuStatus', '需求状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"打开\"},{\"id\":\"1\",\"name\":\"进行中\"},{\"id\":\"2\",\"name\":\"已完成\"},{\"id\":\"3\",\"name\":\"已关闭\"}]', '请输入', '', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1647316107064178', 'iterationStatus', '迭代状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"打开\"},{\"id\":\"1\",\"name\":\"需求评审\"},{\"id\":\"2\",\"name\":\"计划会\"},{\"id\":\"3\",\"name\":\"研发中\"},{\"id\":\"4\",\"name\":\"测试中\"},{\"id\":\"5\",\"name\":\"迭代上线\"},{\"id\":\"6\",\"name\":\"已完成\"},{\"id\":\"7\",\"name\":\"关闭\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1647801639994137', 'taskState', '任务状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"待领取\"},{\"id\":\"1\",\"name\":\"执行中\"},{\"id\":\"2\",\"name\":\"已完工\"},{\"id\":\"3\",\"name\":\"已验收\"},{\"id\":\"4\",\"name\":\"已结算\"},{\"id\":\"9\",\"name\":\"已关闭\"}]', '请输入', '0', 999, '', '1', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1647979373083149', 'bugRepRate', '缺陷复现频率', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"必现\"},{\"id\":\"2\",\"name\":\"大概率复现\"},{\"id\":\"3\",\"name\":\"小概率复现\"},{\"id\":\"4\",\"name\":\"仅出现一次\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1648060466254181', 'riskLvl', '风险等级', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"高\"},{\"id\":\"2\",\"name\":\"中\"},{\"id\":\"3\",\"name\":\"低\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1648061675513165', 'bugReason', 'bug原因分析', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"基本场景侧漏\"},{\"id\":\"2\",\"name\":\"特殊场景侧漏\"},{\"id\":\"3\",\"name\":\"重复问题\"},{\"id\":\"4\",\"name\":\"新需求引入\"},{\"id\":\"5\",\"name\":\"修改引入\"},{\"id\":\"6\",\"name\":\"接口变更\"},{\"id\":\"7\",\"name\":\"不是问题\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1649539230402136', 'moduleBillMode', '模块计费模式', '', 'all', 50, '4', '', '', '', '1', '', '[{\"id\":\"0\",\"name\":\"不计费\"},{\"id\":\"1\",\"name\":\"按购买人数计费\"},{\"id\":\"2\",\"name\":\"总包费用\"},{\"id\":\"3\",\"name\":\"1个月免费试用，1个月后按实际使用人数每月计费\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1649541511551186', 'bizFlowState', '流程标准状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"初始\"},{\"id\":\"1\",\"name\":\"审批中\"},{\"id\":\"2\",\"name\":\"审批通过\"},{\"id\":\"3\",\"name\":\"审批不通过\"},{\"id\":\"4\",\"name\":\"流程取消或者删除\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1649768047549179', 'mcate', '模块分类', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"协同\"},{\"id\":\"2\",\"name\":\"研发\"},{\"id\":\"3\",\"name\":\"电商\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1652703728663171', 'xm_plan_lvl', '项目计划级别', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"1级\"},{\"id\":\"2\",\"name\":\"2级\"},{\"id\":\"3\",\"name\":\"3级\"},{\"id\":\"4\",\"name\":\"4级\"},{\"id\":\"5\",\"name\":\"5级\"},{\"id\":\"6\",\"name\":\"6级\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1652705775326118', 'dclass', '需求分类', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"史诗\"},{\"id\":\"2\",\"name\":\"特性\"},{\"id\":\"3\",\"name\":\"故事\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1653640125080133', 'mo_order_status', '模块订单状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"初始\"},{\"id\":\"1\",\"name\":\"待确认\"},{\"id\":\"2\",\"name\":\"待付款\"},{\"id\":\"3\",\"name\":\"已付款\"},{\"id\":\"4\",\"name\":\"已完成\"},{\"id\":\"5\",\"name\":\"已取消\"},{\"id\":\"6\",\"name\":\"退单\"},{\"id\":\"8\",\"name\":\"已关闭\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1653640508527129', 'mo_ooper', '订单操作类型', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"新购\"},{\"id\":\"2\",\"name\":\"续费\"},{\"id\":\"3\",\"name\":\"新增人数\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1653771620457165', 'menuSupVers', '菜单支持版本号', '', 'all', 50, '4', '', '', '', '0', '', '[{\"id\":\"0\",\"name\":\"免费版\"},{\"id\":\"1\",\"name\":\"专业版\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1655628949671124', 'tranMode', '交易模式', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"投标\"},{\"id\":\"2\",\"name\":\"雇佣\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1655629148048182', 'capaLvl', '能力等级', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"学徒级\"},{\"id\":\"2\",\"name\":\"学士级\"},{\"id\":\"3\",\"name\":\"大师级\"},{\"id\":\"4\",\"name\":\"宗师级\"},{\"id\":\"5\",\"name\":\"皇者级\"},{\"id\":\"6\",\"name\":\"圣尊级\"},{\"id\":\"7\",\"name\":\"帝王级\"},{\"id\":\"8\",\"name\":\"神级\"}]', '请输入', '', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1655629261882171', 'supRequire', '保障要求', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"铜牌\"},{\"id\":\"2\",\"name\":\"银牌\"},{\"id\":\"3\",\"name\":\"金牌\"}]', '请输入', '', 999, '', '1', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1655630253666115', 'memInterestLvl', '会员权益等级', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"普通会员\"},{\"id\":\"2\",\"name\":\"青铜会员\"},{\"id\":\"3\",\"name\":\"白银会员\"},{\"id\":\"4\",\"name\":\"黄金会员\"},{\"id\":\"5\",\"name\":\"紫金会员\"},{\"id\":\"6\",\"name\":\"皇冠会员\"}]', '请输入', '', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1655730717345168', 'objType', '对象类型', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"项目\"},{\"id\":\"2\",\"name\":\"任务\"},{\"id\":\"3\",\"name\":\"产品\"},{\"id\":\"4\",\"name\":\"需求\"},{\"id\":\"5\",\"name\":\"bug\"},{\"id\":\"6\",\"name\":\"迭代\"},{\"id\":\"7\",\"name\":\"团队\"},{\"id\":\"8\",\"name\":\"组织\"},{\"id\":\"9\",\"name\":\"个人\"},{\"id\":\"W\",\"name\":\"文章\"},{\"id\":\"H\",\"name\":\"合同\"},{\"id\":\"K\",\"name\":\"客户\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1655833439701197', 'archiveStatus', '文章状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"草稿\"},{\"id\":\"1\",\"name\":\"已上架\"},{\"id\":\"2\",\"name\":\"已下架\"},{\"id\":\"3\",\"name\":\"审核中\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1656613513173147', 'regionType', '地域限制方式', '', 'all', 50, '4', '', '', '', '1', '', '[{\"id\":\"0\",\"name\":\"不限\"},{\"id\":\"1\",\"name\":\"同城\"},{\"id\":\"2\",\"name\":\"同省\"},{\"id\":\"3\",\"name\":\"同国\"},{\"id\":\"4\",\"name\":\"同洲\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1657012908426186', 'crowd_task_market', '众包任务活动参数', '', 'sysParam', 50, '1', '', '', '', '', '', '', '请输入', '0', 999, '', '1', '', '[{\"id\":\"topDays\",\"name\":\"置顶天数\",\"value\":\"10\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"topFee\",\"name\":\"置顶费用\",\"value\":\"1\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"hotDays\",\"name\":\"热搜天数\",\"value\":\"10\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"hotFee\",\"name\":\"热搜费用\",\"value\":\"1\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"urgentDays\",\"name\":\"加急天数\",\"value\":\"10\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"urgentFee\",\"name\":\"加急费用\",\"value\":\"1\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"crmSupFee\",\"name\":\"客服费用\",\"value\":\"1\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"}]');
INSERT INTO `meta_item` VALUES ('IT1657030829847153', 'estate', '众包任务托管资金状态', '', 'all', 50, '4', '', '', '', '0', '', '[{\"id\":\"0\",\"name\":\"未托管\"},{\"id\":\"1\",\"name\":\"待付款\"},{\"id\":\"2\",\"name\":\"已托管资金\"},{\"id\":\"3\",\"name\":\"已付款给服务商\"},{\"id\":\"4\",\"name\":\"已退款\"}]', '请输入', '0', 999, '', '1', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1657117092902195', 'mkInterests', '默认的会员等级', '', 'sysParam', 50, '1', '', '', '', '', '', '', '请输入', '0', 999, '', '1', '', '[{\"id\":\"ilvlId\",\"name\":\"等级编号\",\"value\":\"1\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"ilvlName\",\"name\":\"等级名称\",\"value\":\"默认会员等级\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"discount\",\"name\":\"折扣比例0-100\",\"value\":\"100\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"picUrl\",\"name\":\"等级图标\",\"value\":\"\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"smaxAt\",\"name\":\"单个任务最大金额\",\"value\":\"5000\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"totalAt\",\"name\":\"累计接单金额\",\"value\":\"1000000\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"mtype\",\"name\":\"会员类型\",\"value\":\"3\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"totalExp\",\"name\":\"累计经验值\",\"value\":\"1000000\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"smaxExp\",\"name\":\"单个任务最大经验值\",\"value\":\"5000\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"bids\",\"name\":\"每月投标次数上限\",\"value\":\"30\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"sfeeRate\",\"name\":\"服务费率0-100\",\"value\":\"15\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"maxUsers\",\"name\":\"最大人员数量\",\"value\":\"10\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"isFree\",\"name\":\"是否免费\",\"value\":\"1\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"idesc\",\"name\":\"描述\",\"value\":\"\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"},{\"id\":\"ilevel\",\"name\":\"等级排序\",\"value\":\"0\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"}]');
INSERT INTO `meta_item` VALUES ('IT1658200417191153', 'testPlanStatus', '测试计划状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"未开始\"},{\"id\":\"1\",\"name\":\"执行中\"},{\"id\":\"2\",\"name\":\"已结束\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1658200476395123', 'testPlanTcode', '测试计划执行结果', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"未通过\"},{\"id\":\"1\",\"name\":\"已通过\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1658200621783128', 'testStepTcode', '测试步骤中测试结果', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"未测\"},{\"id\":\"1\",\"name\":\"忽略\"},{\"id\":\"2\",\"name\":\"通过\"},{\"id\":\"3\",\"name\":\"受阻\"},{\"id\":\"4\",\"name\":\"失败\"}]', '请输入', '', 999, '', '1', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1658216481853116', 'testCaseStatus', '测试用例审核状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"草稿\"},{\"id\":\"1\",\"name\":\"评审中\"},{\"id\":\"2\",\"name\":\"审核通过\"},{\"id\":\"3\",\"name\":\"审核未通过\"},{\"id\":\"4\",\"name\":\"废弃\"}]', '请输入', '0', 999, '', '1', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1658305014197168', 'caseType', '测试用例类型', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"功能测试\"},{\"id\":\"1\",\"name\":\"性能测试\"},{\"id\":\"2\",\"name\":\"配置相关\"},{\"id\":\"3\",\"name\":\"安装部署\"},{\"id\":\"4\",\"name\":\"接口测试\"},{\"id\":\"5\",\"name\":\"安全相关\"},{\"id\":\"6\",\"name\":\"兼容性测试\"},{\"id\":\"7\",\"name\":\"UI测试\"},{\"id\":\"8\",\"name\":\"其他\"}]', '请输入', '0', 999, '', '1', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1658337903419152', 'ceshi', '测试', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"男\",\"color\":\"#67C23A\",\"icon\":\"el-icon-female\"},{\"id\":\"2\",\"name\":\"女\",\"color\":\"#409EFF\",\"icon\":\"el-icon-male\"}]', '请输入', '0', 999, '', '1', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1658511571561129', 'casedbStatus', '测试库状态', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"初始\"},{\"id\":\"1\",\"name\":\"启用\"},{\"id\":\"2\",\"name\":\"关闭\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1658516293195139', 'workType', '项目工作方式', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"0\",\"name\":\"不限制\"},{\"id\":\"1\",\"name\":\"scrum\"},{\"id\":\"2\",\"name\":\"看板\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1663065169722118', 'bidStep', '投标步骤', '', 'all', 50, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"发布需求\"},{\"id\":\"2\",\"name\":\"用户投标\"},{\"id\":\"3\",\"name\":\"雇主选标\"},{\"id\":\"4\",\"name\":\"托管赏金\"},{\"id\":\"5\",\"name\":\"用户工作\"},{\"id\":\"6\",\"name\":\"确认验收\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1669924214148198', 'profeType', '职业类型', '', 'all', 50, '4', '', '', '', '1', '', '[{\"id\":\"1\",\"name\":\"开发类\"},{\"id\":\"2\",\"name\":\"测试类\"},{\"id\":\"3\",\"name\":\"设计类\"},{\"id\":\"4\",\"name\":\"管理类\"}]', '请输入', '0', 999, '', '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1691120718771191', 'dept_state', '部门状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1691776688640136', 'roletype', '角色类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1691948950553132', 'module_status', '模块状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1691999268586149', 'branchModuleStatus', '已开通模块状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1693456602063184', 'aqCategory', '咨询分类', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1693456982241118', 'aqStatus', '咨询问题状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1693459711250198', 'faqStatus', '常件问题状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1694064251340118', 'suspensionState', '流程实例状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[{\"id\":\"aaa_set\",\"name\":\"aaa\",\"value\":\"\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"}]');
INSERT INTO `meta_item` VALUES ('IT1694097184036177', 'yesOrNo', '是否', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1694097270479188', 'delegationStatus', '是否代办', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1694816378289112', 'ustatus', '用户状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1694873207591148', 'form_data_type', '智能表单表单数据存储方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1695464397085192', 'invite_state', '第三方邀请状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1695464485186112', 'invite_scene', '邀请场景', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1695464621498144', 'invite_type', '邀请类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1695464732727138', 'app_type', '应用类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1695485364688182', 'cpaType', '协作组织类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1695552235782171', 'wechat-wxpub-tpl', '微信公众号模板消息', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1696115825746188', 'user_type', '登陆通道', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1696115901424135', 'login_type', '登陆方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1696136315756189', 'mem_type', '会员类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1696468156941139', 'request_method', '请求方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1697082612111178', 'lcode_create_table_type', '低代码平台表单建表方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698048367949132', 'duban_category', '督办分类', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698055140245164', 'duban_status', '督办状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698139176276161', 'duban_category_status', '督办分类状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698646774961168', 'vacation_status', '假条状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698648711725167', 'vacation_degree', '假条紧急程度', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698746533985172', 'signetType', '印章类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698746860714119', 'signetForm', '印章形态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698746951774129', 'signetStatus', '印章状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698810204498168', 'device_status', '设备状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1698811713212164', 'device_type', '设备类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1699254565655168', 'card_status', '资产状态', '资产管理-资产卡片', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1699341051669139', 'op_type', '单据类型', '资产管理 单据类型', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1699519561556146', 'allocation_type', '分配用户', '资产管理：「资产责任人盘点 1, 资产责任人盘点与指定盘点人员 2,  仅指定盘点人员 3」', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1699930319224119', 'asset_status', '资产状态', '采购管理：资产状态0闲置1在用2借用3维修4报废', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1700031269520126', 'erp_pur_order_status', '采购单状态', '采购管理', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1706952527358133', 'testType', '测试类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1706971046692139', 'wlBizType', '报工类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1709851193391186', 'sms_sign_status', '签名状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1709851314745158', 'sms_international', '国内国外', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1709851367862178', 'sms_tpl_status', '短信模板状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1709851457287157', 'sms_tpl_type', '短信模板类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1710154575548176', 'ipcm_order_status', 'ipcm订单状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1710225652000177', 'ipcm_pstatus', '产品类型-产品状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1710226056351152', 'ipcm_pay_type', 'order支付方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1710226292822132', 'ipcm_bill_mode', 'product计费模式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1710226483113143', 'ipcm_service_group', '服务项目组别', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1711132082348138', 'wf_hx_type', '候选人选定策略', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1712680523101162', 'wstatus', '工时确认状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1712680591518151', 'sstatus', '工时结算状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1713930215405148', 'formDataType', '智能表单数据存储方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1714452369149184', 'dm_data_model', '数据模型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', NULL);
INSERT INTO `meta_item` VALUES ('IT1715018520088158', 'platformBranchId', '平台机构号', '平台机构号', 'sysParams', 50, '1', '', '', '', 'platform-branch-001', '平台机构号', NULL, '', '', 999, NULL, '', '', '[{\"id\":\"platformBranchName\",\"name\":\"平台机构名称\",\"value\":\"平台管理机构\",\"url\":\"\",\"extInfo\":\"\",\"type\":\"1\",\"remark\":\"\"}]');
INSERT INTO `meta_item` VALUES ('IT1716141779047192', 'lvl', '层级', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1716319161416186', 'bpm_task_candidate_strategy', '工作流审批人分配策略', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1717254785691169', 'wfSignType', '加签方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718881282938116', 'workloadFillType', '项目工时计时方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718946061378186', 'envState', '环境清单状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718946247499132', 'readQx', '浏览权限', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718946451725189', 'writeQx', '写权限', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718986625416197', 'xm_showOut', '项目对外开放程度', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718986981924172', 'xm_groupScope', '团队相关:小组crud、加减人、小组组长管理等', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718987065247138', 'xm_testScope', '测试相关：缺陷crud、用例crud、测试计划、测试执行等', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718987154702157', 'xm_iterationScope', '迭代crud、迭代负责人管理', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718987233944124', 'xm_menuScope', '需求相关：史诗、特性、故事的crud', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1718987814961195', 'xm_taskScope', '任务权限范围', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1723764330636175', 'autoTestMethod', '接口方法', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1723766596279171', 'autoTestBodyType', '接口参数Body类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1723766670174118', 'autoTestAuthType', '接口授权方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724128224877185', 'om_status', '办公用品状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724128659739118', 'om_unit', '办公用品计量单位', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724190168957165', 'om_require_status', '办公用品领用状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724192379224197', 'om_warn_tag', '办公用品警示标签', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724194444816163', 'om_storage_status', '采购单状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724194952241195', 'storage_status_status', '采购单状态', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724195549227138', 'om_warehouse_id', '办公用品仓库', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724202595934151', 'om_use_type', '使用类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724202684849136', 'om_receive_type', '领取方式', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724218211018144', 'om_direct', '库存流水方向', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT1724275007802138', 'om_bill_type', '单据类型', '', 'all', 50, '4', '', '', '', '', '', NULL, '', '', 999, NULL, '', '', '[]');
INSERT INTO `meta_item` VALUES ('IT202109066C-3', '33344', '33344', '33', '', 3, '4', '', '', '', NULL, NULL, NULL, '', '1', 2, NULL, NULL, '', NULL);
INSERT INTO `meta_item` VALUES ('IT20210906PG-1', '333', '33', '33', '', 2, '2', '', '', '', NULL, NULL, NULL, '33', '1', 1, NULL, NULL, '', NULL);
INSERT INTO `meta_item` VALUES ('IT20210906RN-1', 'ceshi001', '测试001', '222222', '', 3, '4', '', '', '', '', '', '[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"3\",\"name\":\"测试3\"},{\"id\":\"2\",\"name\":\"测试插入5\"},{\"id\":\"4\",\"name\":\"测试4\"}]', '踩踩踩', '1', NULL, NULL, '1', '', NULL);
INSERT INTO `meta_item` VALUES ('IT20210913IH-1', 'categoryType', '内容-文章固定分类', '内容-文章固定分类', 'all', NULL, '4', '', '', '', '0', '', '[{\"id\":\"0\",\"name\":\"知识库\"},{\"id\":\"1\",\"name\":\"新闻\"},{\"id\":\"2\",\"name\":\"内部公告\"},{\"id\":\"3\",\"name\":\"平台公告\"},{\"id\":\"5\",\"name\":\"论坛\"},{\"id\":\"6\",\"name\":\"公文\"},{\"id\":\"4\",\"name\":\"其它\"},{\"id\":\"7\",\"name\":\"归档\"},{\"id\":\"8\",\"name\":\"网站栏目\"}]', '', '', NULL, '', '', '', NULL);
INSERT INTO `meta_item` VALUES ('jxSchemeType', 'jxSchemeType', '绩效考核方案类型', '绩效考核方案类型', 'all', 10, '4', '', '', NULL, '3', NULL, '[{\"id\":\"0\",\"name\":\"月度考核\"},{\"id\":\"1\",\"name\":\"季度考核\"},{\"id\":\"2\",\"name\":\"半年度考核\"},{\"id\":\"3\",\"name\":\"年度考核\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('kdgs', 'kdgs', '快递公司', '快递公司列表', 'all', 100, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"sfkd\",\"name\":\"顺丰快递\"},{\"id\":\"ttkd\",\"name\":\"天天快递\"},{\"id\":\"ydkd\",\"name\":\"韵达快递\"},{\"id\":\"ytkd\",\"name\":\"圆通快递\"},{\"id\":\"ztkd\",\"name\":\"中通快递\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('locationtype', 'locationtype', '门店类型', '门店类型', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"直营\"},{\"id\":\"2\",\"name\":\"加盟\"},{\"id\":\"3\",\"name\":\"批发\"},{\"id\":\"4\",\"name\":\"虚拟门店\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('marStatus', 'maritalStatus', '婚姻情况', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"未婚\",\"name\":\"未婚\"},{\"id\":\"已婚\",\"name\":\"已婚\"},{\"id\":\"离异\",\"name\":\"离异\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('mdpRoleMenusRedisTimeoutMillisSeconds', 'mdpRoleMenusRedisTimeoutMillisSeconds', '每个角色对应的菜单过期毫秒数', NULL, 'sysParam', 50, '1', NULL, NULL, NULL, '600000', NULL, NULL, NULL, '0', 999, NULL, '1', NULL, NULL);
INSERT INTO `meta_item` VALUES ('measureUnit', 'measureUnit', '计量单位', '计量单位', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"双\",\"name\":\"双\"},{\"id\":\"件\",\"name\":\"件\"},{\"id\":\"克\",\"name\":\"克\"},{\"id\":\"个\",\"name\":\"个\"},{\"id\":\"吨\",\"name\":\"吨\"},{\"id\":\"组\",\"name\":\"组\"},{\"id\":\"张\",\"name\":\"张\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('msgTemplateType', 'msgTemplateType', '微信消息模板类型', '微信消息模板类型，用于按授权码+模板类型查找到对应模板编号', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"payNotify\",\"name\":\"支付结果通知\"},{\"id\":\"taskNotify\",\"name\":\"任务状态变更通知\"},{\"id\":\"amountChange\",\"name\":\"账户金额变动通知\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('nation ', 'nation ', '民族', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"汉族\",\"name\":\"汉族\"},{\"id\":\"满族\",\"name\":\"满族\"},{\"id\":\"蒙古族\",\"name\":\"蒙古族\"},{\"id\":\"苗族\",\"name\":\"苗族\"},{\"id\":\"壮族\",\"name\":\"壮族\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('needPhonenoToRegister', 'needPhonenoToRegister', '注册用户是否需要手机号码', NULL, 'sysParam', 50, '13', NULL, NULL, NULL, '1', NULL, '[{\"id\":\"1\",\"name\":\"是\"},{\"id\":\"0\",\"name\":\"否\"}]', NULL, '0', 999, NULL, '1', NULL, NULL);
INSERT INTO `meta_item` VALUES ('orgType', 'orgType', '机构类型', '公司级别的机构类型', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"agent\",\"name\":\"代理\"},{\"id\":\"shop\",\"name\":\"加盟商户\"},{\"id\":\"platformCompany\",\"name\":\"平台归属公司\"},{\"id\":\"locationarea\",\"name\":\"门店区管理\"},{\"id\":\"locationcity\",\"name\":\"门店城市管理\"},{\"id\":\"locationshop\",\"name\":\"门店\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('perCategory', 'perCategory', '人员类别', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"总裁\",\"name\":\"总裁\"},{\"id\":\"部门经理\",\"name\":\"部门经理\"},{\"id\":\"公司副总\",\"name\":\"公司副总\"},{\"id\":\"普通员工\",\"name\":\"普通员工\"},{\"id\":\"中心总监\",\"name\":\"中心总监\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('planType', 'planType', '计划类型', '计划类型', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"w2\",\"name\":\"双周\"},{\"id\":\"w1\",\"name\":\"周\"},{\"id\":\"w3\",\"name\":\"三周\"},{\"id\":\"q1\",\"name\":\"季\"},{\"id\":\"m1\",\"name\":\"月\"},{\"id\":\"q2\",\"name\":\"半年\"},{\"id\":\"y1\",\"name\":\"年\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('platformDsdfFeeRate', 'platformDsdfFeeRate', '平台代收代付手续费', '平台代收代付手续费', 'branchParam', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('politicsStatus', 'politicsStatus', '政治面貌', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"群众\",\"name\":\"群众\"},{\"id\":\"共青团员\",\"name\":\"共青团员\"},{\"id\":\"预备党员\",\"name\":\"预备党员\"},{\"id\":\"共产党员\",\"name\":\"共产党员\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('postLvl', 'postLvl', '岗位级别', '岗位级别', 'all', 2, '4', NULL, NULL, NULL, '1', NULL, '[{\"id\":\"1\",\"name\":\"1级\"},{\"id\":\"2\",\"name\":\"2级\"},{\"id\":\"3\",\"name\":\"3级\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('postType', 'postType', '岗位类型', '岗位类型（技术/管理）', 'all', NULL, '4', NULL, NULL, NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"管理\"},{\"id\":\"2\",\"name\":\"技术\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('priority', 'priority', '优先级别', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"最高\"},{\"id\":\"1\",\"name\":\"较高\"},{\"id\":\"2\",\"name\":\"普通\"},{\"id\":\"3\",\"name\":\"较低\"},{\"id\":\"4\",\"name\":\"最低\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('projectGroupType', 'projectGroupType', '项目团队类型', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"nbxmjl\",\"name\":\"内部项目经理\"},{\"id\":\"jgs\",\"name\":\"架构师\"},{\"id\":\"khxmjl\",\"name\":\"客户项目经理\"},{\"id\":\"jsjl\",\"name\":\"技术经理\"},{\"id\":\"khjsjl\",\"name\":\"客户技术经理\"},{\"id\":\"kh\",\"name\":\"客户\"},{\"id\":\"xsjl\",\"name\":\"销售经理/总监\"},{\"id\":\"xs\",\"name\":\"销售\"},{\"id\":\"kfz\",\"name\":\"开发组\"},{\"id\":\"csz\",\"name\":\"测试组\"},{\"id\":\"uat\",\"name\":\"uat测试\"},{\"id\":\"yyz\",\"name\":\"运营组\"},{\"id\":\"nbldz\",\"name\":\"内部领导组\"},{\"id\":\"qtz\",\"name\":\"其他组\"},{\"id\":\"khldz\",\"name\":\"客户领导组\"},{\"id\":\"xmzk\",\"name\":\"项目主控领导\"},{\"id\":\"xmzl\",\"name\":\"项目助理\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('projectStatus', 'projectStatus', '项目状态', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"初始\"},{\"id\":\"1\",\"name\":\"售前\"},{\"id\":\"2\",\"name\":\"立项中\"},{\"id\":\"3\",\"name\":\"实施中\"},{\"id\":\"4\",\"name\":\"暂停中\"},{\"id\":\"5\",\"name\":\"结项中\"},{\"id\":\"6\",\"name\":\"已结项\"},{\"id\":\"7\",\"name\":\"售后\"},{\"id\":\"8\",\"name\":\"已完成\"},{\"id\":\"9\",\"name\":\"已关闭\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('projectSubject', 'projectSubject', '项目管理中对应的科目编号', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"wgzfgz\",\"name\":\"外购结算支付工资\"},{\"id\":\"yggz\",\"name\":\"员工工资\"},{\"id\":\"bgyp\",\"name\":\"办公用品\"},{\"id\":\"xmjj\",\"name\":\"项目奖金\"},{\"id\":\"htskrz\",\"name\":\"合同收款\"},{\"id\":\"hdjf\",\"name\":\"活动经费\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('projectTaskExecuserStatus', 'projectTaskExecuserStatus', '项目任务执行者状态列表', '执行人状态0候选排队中1执行任务中7放弃任务8黑名单', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"候选中\"},{\"id\":\"1\",\"name\":\"执行中\"},{\"id\":\"7\",\"name\":\"放弃任务\"},{\"id\":\"8\",\"name\":\"黑名单\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('projectTaskSettleStatus', 'projectTaskSettleStatus', '项目任务结算状态', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"未结算\"},{\"id\":\"1\",\"name\":\"已结算\"},{\"id\":\"2\",\"name\":\"无需结算\"},{\"id\":\"3\",\"name\":\"已付款\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('projectType', 'projectType', '项目类型', 'projectType', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"it-新建-瀑布\"},{\"id\":\"2\",\"name\":\"it-新建-敏捷\"},{\"id\":\"1\",\"name\":\"it-存量-瀑布\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('qualification', 'qualification', '学位', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"学士\",\"name\":\"学士\"},{\"id\":\"硕士\",\"name\":\"硕士\"},{\"id\":\"博士\",\"name\":\"博士\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('qxCategory', 'qxCategory', '权限业务分类', '权限业务分类', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"im\",\"name\":\"即时聊天系统\"},{\"id\":\"xm\",\"name\":\"项目管理平台\"},{\"id\":\"ac\",\"name\":\"财务系统\"},{\"id\":\"mallm\",\"name\":\"商城后台\"},{\"id\":\"mk\",\"name\":\"营销系统\"},{\"id\":\"vmai\",\"name\":\"虚拟币\"},{\"id\":\"sms\",\"name\":\"短信系统\"},{\"id\":\"wxpub\",\"name\":\"第三方支付系统\"},{\"id\":\"sys\",\"name\":\"组织管理系统\"},{\"id\":\"oa\",\"name\":\"智慧协同办公系统\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('qxType', 'qxType', '权限类型', '权限类型', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('secrecy_level', 'secrecyLevel', '保密级别', '保密级别', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"一般\",\"name\":\"一般\"},{\"id\":\"秘密\",\"name\":\"秘密\"},{\"id\":\"绝密\",\"name\":\"绝密\"},{\"id\":\"机密\",\"name\":\"机密\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('serviceGuarantee', 'serviceGuarantee', '服务保证', '', 'all', 10, '5', '', '', NULL, '', NULL, '[{\"id\":\"0\",\"name\":\"7天退货\"},{\"id\":\"1\",\"name\":\"快速退款\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('serviceName', 'serviceName', '图像挂接ftp服务器地址', '', 'COURT_ALL', 1000, '4', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('sql_link_and_sql_oper', 'sql_link_and_sql_oper', 'sql操作符(包括连接操作符)', NULL, 'all', 50, '4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', 999, NULL, '1', NULL, NULL);
INSERT INTO `meta_item` VALUES ('staffStatus', 'staffStatus', '员工状态', '', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"试用\",\"name\":\"试用\"},{\"id\":\"临时\",\"name\":\"临时\"},{\"id\":\"实习\",\"name\":\"实习\"},{\"id\":\"正式\",\"name\":\"正式\"},{\"id\":\"试用延期\",\"name\":\"试用延期\"},{\"id\":\"解聘\",\"name\":\"解聘\"},{\"id\":\"离职\",\"name\":\"离职\"},{\"id\":\"退休\",\"name\":\"退休\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('storeaddress', 'storeaddress', '仓库地址', '仓库地址', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"storeaddress01\",\"name\":\"123商城1号仓管理员\"},{\"id\":\"storeaddress02\",\"name\":\"123商城2号仓管理员\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('taskType', 'taskType', '任务类型', '任务类型', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"售前\"},{\"id\":\"2\",\"name\":\"投标\"},{\"id\":\"3\",\"name\":\"需求\"},{\"id\":\"4\",\"name\":\"设计\"},{\"id\":\"5\",\"name\":\"测试\"},{\"id\":\"6\",\"name\":\"开发\"},{\"id\":\"7\",\"name\":\"验收\"},{\"id\":\"8\",\"name\":\"运维\"},{\"id\":\"9\",\"name\":\"部署\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('tpAppCategory', 'tpAppCategory', '第三方应用分类', '第三方应用分类', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"wxpub\",\"name\":\"微信公众号\"},{\"id\":\"wxa\",\"name\":\"微信小程序\"},{\"id\":\"mdpApp\",\"name\":\"平台授权应用\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('tpAppPayType', 'tpAppPayType', '第三方支付渠道', '第三方支付渠道', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"微信\"},{\"id\":\"2\",\"name\":\"支付宝 \"},{\"id\":\"3\",\"name\":\"银行\"},{\"id\":\"6\",\"name\":\"其他\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('weekday', 'weekday', '星期几', '星期一到星期天', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"FRIDAY\",\"name\":\"周五\"},{\"id\":\"MONDAY\",\"name\":\"周一\"},{\"id\":\"SATURDAY\",\"name\":\"周六\"},{\"id\":\"SUNDAY\",\"name\":\"周日\"},{\"id\":\"THURSDAY\",\"name\":\"周四\"},{\"id\":\"TUESDAY\",\"name\":\"周二\"},{\"id\":\"WEDNESDAY\",\"name\":\"周三\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('wxcolor', 'wxcolor', '微信卡券接口颜色', '微信卡券接口颜色', 'all', 10, '4', 'mktv4i2u1', NULL, NULL, NULL, NULL, '[{\"id\":\"#2c9f67\",\"name\":\"深绿色\"},{\"id\":\"#509fc9\",\"name\":\"浅蓝色\"},{\"id\":\"#5885cf\",\"name\":\"深蓝色\"},{\"id\":\"#63b359\",\"name\":\"浅绿色\"},{\"id\":\"#9062c0\",\"name\":\"紫色\"},{\"id\":\"#cc463d\",\"name\":\"深红色\"},{\"id\":\"#d09a45\",\"name\":\"浅棕色\"},{\"id\":\"#dd6549\",\"name\":\"浅红色\"},{\"id\":\"#e4b138\",\"name\":\"黄色\"},{\"id\":\"#ee903c\",\"name\":\"橘红色\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('xmMenuPlanStatus', 'xmMenuPlanStatus', '项目功能计划状态', '项目功能计划状态', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"0\",\"name\":\"初始\"},{\"id\":\"1\",\"name\":\"正常\"},{\"id\":\"3\",\"name\":\"延误\"},{\"id\":\"2\",\"name\":\"暂停\"},{\"id\":\"4\",\"name\":\"结束\"},{\"id\":\"5\",\"name\":\"关闭\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item` VALUES ('xmTaskSettleSchemel', 'xmTaskSettleSchemel', '项目任务结算方案', '项目任务结算方案', 'all', 10, '4', '', '', NULL, NULL, NULL, '[{\"id\":\"1\",\"name\":\"按工期延迟比率打折\"},{\"id\":\"2\",\"name\":\"按报价结算不打折\"},{\"id\":\"3\",\"name\":\"按工期提前或者延迟进行奖罚\"}]', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for meta_item_copy1
-- ----------------------------
DROP TABLE IF EXISTS `meta_item_copy1`;
CREATE TABLE `meta_item_copy1`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `ITEM_CODE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '代码，小写,下横线分割，请不要用驼峰命名',
  `ITEM_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `CATEGORY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类编号 暂时sysParams系统参数，all-通用',
  `ITEM_SIZE` int NULL DEFAULT 50 COMMENT '长度',
  `ITEM_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '1-普通文本，2-数字，3-日期，4-单选列表，5-多选列表，6-单文件，7-多文件，8-富文本，9-单图文，10多图文,11-单视频，12-多视频，13单选radio,14多选checkbox',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号',
  `CMENU` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否创建菜单',
  `DVALUES` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '默认值,如果是列表，则存储列表编号，多个逗号分割',
  `DNAMES` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '默认名称，如果是列表，则存储列表名称，多个则逗号分割',
  `OPTION_LIST` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT 'item_type=4,5时的选项列表-指向item_option表关联的列表，该字段作废',
  `input_format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '输入提示',
  `required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '是否必须0否1是',
  `seq` int NULL DEFAULT 999 COMMENT '排序顺序',
  `table_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表名',
  `is_show` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '1' COMMENT '是否显示0否1是',
  `qx` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限，是否可以0-新增，1-删除，2-编辑，3-查询，多个以逗号分割',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段\r\n[{name:\'中文名称\',id:\'编号\',value:\'值\',remark:\'备注\',type:\'支持简单的1-普通文本2-数字，3-日期，8-富文本，9单图文，15-是否\'}]',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `META_ITEM_I_CODE`(`ITEM_CODE` ASC, `CATEGORY_ID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '数据项定义' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of meta_item_copy1
-- ----------------------------

-- ----------------------------
-- Table structure for meta_item_option
-- ----------------------------
DROP TABLE IF EXISTS `meta_item_option`;
CREATE TABLE `meta_item_option`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '名称',
  `rely_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联类型',
  `rely_id` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联类型的编号',
  `rely_stype` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联子类型',
  `rely_sid` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联子类型',
  `color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '显示颜色，参考https://element-plus.gitee.io/zh-CN/component/color.html',
  `un_targets` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '不能跳转到哪些状态,逗号分隔多个',
  `disabled` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前台是否可选1-不可选，0或者空可选',
  `item_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '指向meta_item.id',
  `seq_order` int NULL DEFAULT NULL COMMENT '排位从0-999',
  `targets` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '可达目标状态json格式[{id:目标编号,oper:操作,rules:[roleids:角色列表,deptids:部门编号,userids:用户编号]}]',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '租户',
  `ext_vals` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段值，根据item.extInfos的配置，存值',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`item_id`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '字典选项值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meta_item_option
-- ----------------------------
INSERT INTO `meta_item_option` VALUES ('0', '停用', '', '1', '', '', 'rgba(61, 64, 59, 1)', '1,0', '', '3gTalxF07', 10, NULL, NULL, NULL, 'fa-solid:window-minimize');
INSERT INTO `meta_item_option` VALUES ('1', '启用', '', '', '', '', 'rgba(103, 194, 58, 1)', '', '', '3gTalxF07', 11, NULL, NULL, NULL, 'fa:500px');
INSERT INTO `meta_item_option` VALUES ('0', '正常', '', '0', '', '', 'rgba(154, 104, 43, 1)', '', '', '3gTtvEr07', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '已锁定', '', '', '', '', 'rgba(241, 149, 36, 1)', '', '', '3gTtvEr07', 0, NULL, NULL, NULL, 'ep:chat-dot-round');
INSERT INTO `meta_item_option` VALUES ('y73G', 'OYn2', NULL, NULL, NULL, NULL, 'Hipn', 'd4L0', 'u', '3L6R', 4155, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '非常紧急', NULL, NULL, NULL, NULL, 'rgba(26, 12, 3, 1)', NULL, NULL, '4qqyfcq263', 1, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '紧急', NULL, NULL, NULL, NULL, 'rgba(199, 21, 133, 1)', NULL, NULL, '4qqyfcq263', 2, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '一般紧急', NULL, NULL, NULL, NULL, 'rgba(199, 21, 133, 0.46)', NULL, NULL, '4qqyfcq263', 3, NULL, NULL, NULL, 'fa-solid:abacus');
INSERT INTO `meta_item_option` VALUES ('4', '低', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '4qqyfcq263', 4, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '工作日加班', NULL, NULL, NULL, NULL, 'rgba(255, 140, 0, 1)', NULL, NULL, '55c2u14kf9', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '双休日加班', NULL, '人事合同', NULL, NULL, '#ffd700', NULL, NULL, '55c2u14kf9', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '法定假日加班', NULL, NULL, NULL, NULL, '#00ced1', NULL, NULL, '55c2u14kf9', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dls', '代理商', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('fxs', '分销商', NULL, '现金', NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('ggmt', '公关媒体', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gryh', '个人用户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('hzhb', '合作伙伴', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('jjds', '竞争对手', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('kh', '客户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wbzy', '外部资源', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('yh', '银行', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('zjjg', '中介机构', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55gxectc61', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('English', '英语', 'aaa', NULL, NULL, NULL, NULL, NULL, NULL, '55jhbgr81k', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('Japanese', '日语', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55jhbgr81k', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('Korean', '韩语', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55jhbgr81k', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('SimplifiedChinese', '简体中文', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55jhbgr81k', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('TraditionalChinese', '繁体中文', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55jhbgr81k', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('人事合同', '人事合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58qhz1pg6k', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', NULL, NULL, NULL, NULL, 'rgba(39, 35, 11, 1)', NULL, NULL, '58qkc6p61f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '正常', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58qkc6p61f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已终止', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58qkc6p61f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已暂停', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58qkc6p61f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '未激活', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58qkc6p61f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '履行中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58qkc6p61f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '已拆分', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58qkc6p61f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '已完成', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58qkc6p61f', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('现金', '现金', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58rjb6tb7u', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('豪宅', '豪宅', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58rjb6tb7u', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('豪车', '豪车', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58rjb6tb7u', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '事假', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '病假', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '年假', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '婚假', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '哺乳假', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '丧假', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '外勤', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '独生子女假', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('9', '销假', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '58u4k3j3cc', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gys', '供应商', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5aka5u7r3b', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('hzhb', '合作伙伴', '', NULL, NULL, NULL, NULL, NULL, NULL, '5aka5u7r3b', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('kh', '客户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5aka5u7r3b', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('zfdw', '政府单位', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5aka5u7r3b', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('zjjg', '中介机构', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5aka5u7r3b', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('zxdw', '咨询单位', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5aka5u7r3b', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('cancel', '注销', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akb2axzya', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('deactive', '吊销', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akb2axzya', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('existence', '存续', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akb2axzya', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('cgl', '采购类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dll', '代理类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('fwwbl', '服务外包类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('fzfz', '服装纺织', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gcl', '工程类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('hzl', '合作类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('kyys', '科研院所', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('nysh', '能源石化', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('rysh', '日用生化', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('swfw', '商务服务', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wbyl', '文体娱乐', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wlys', '物流运输', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('ysmt', '影视媒体', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('zf', '政府', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('zll', '租赁类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akcd5ww25', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('db', '东北', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akhjkruhv', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dn', '东南', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akhjkruhv', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('hb', '华北', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akhjkruhv', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('hd', '华东', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akhjkruhv', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('hn', '华南', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akhjkruhv', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('hz', '华中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akhjkruhv', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xb', '西北', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akhjkruhv', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xn', '西南', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akhjkruhv', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('bank', '银行转账', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akmd16tsb', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('cash', '现金结算', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akmd16tsb', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wireTransfer', '电汇', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akmd16tsb', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('A级', 'A级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akxx3xxsj', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('B级', 'B级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akxx3xxsj', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('C级', 'C级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akxx3xxsj', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('D级', 'D级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5akxx3xxsj', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '增值税专用发票', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5w3nc7tidx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '增值税普通发票', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5w3nc7tidx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '增值税电子普通发票', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5w3nc7tidx', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('10:00-10:30', '10:00-10:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('10:30-11:00', '10:30-11:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('11:00-11:30', '11:00-11:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('11:30-12:00', '11:30-12:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('12:00-12:30', '12:00-12:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('12:30-13:00', '12:30-13:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('13:00-13:30', '13:00-13:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('13:30-14:00', '13:30-14:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('14:00-14:30', '14:00-14:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('14:30-15:00', '14:30-15:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('15:00-15:30', '15:00-15:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('15:30-16:00', '15:30-16:00', ' ', NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('16:00-16:30', '16:00-16:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('16:30-17:00', '16:30-17:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('17:00-17:30', '17:00-17:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('17:30-18:00', '17:30-18:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('18:00-18:30', '18:00-18:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('18:30-19:00', '18:30-19:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('19:00-19:30', '19:00-19:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('19:30-20:00', '19:30-20:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('20:00-20:30', '20:00-20:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('20:30-21:00', '20:30-21:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('21:00-21:30', '21:00-21:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8:00-8:30', '8:00-8:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8:30-9:00', '8:30-9:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('9:00-9:30', '9:00-9:30', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('9:30-10:00', '门店', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bookTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '致命', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSeverity', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '严重', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSeverity', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '普通', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSeverity', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '轻微', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSeverity', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '设计如此', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSolution', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '重复BUG', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSolution', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '外部原因', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSolution', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '已解决', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSolution', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '无法重现', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSolution', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '延期处理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSolution', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '不予解决', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugSolution', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '新提交', NULL, NULL, NULL, NULL, '#67C23A', NULL, NULL, 'bugStatus', 1, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '处理中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugStatus', 2, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已修复', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugStatus', 3, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '重新打开', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugStatus', 4, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '已发布', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugStatus', 5, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '已拒绝', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugStatus', 6, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '挂起', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugStatus', 7, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '已关闭', '', '', '', '', '', '', '', 'bugStatus', 8, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '代码错误', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '低级缺陷', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '设计缺陷', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '配置相关', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '安全相关', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '性能问题', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '其他', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'bugType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('all', '通用', '', '0', NULL, NULL, NULL, NULL, NULL, 'categoryId', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('sysParams', '系统参数', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'categoryId', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('order', '订单', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commentcontype', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('focusgoods', '商品', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commentItemfocus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('focusorder', '订单', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commentItemfocus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('focusorderandgoods', '订单和商品', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commentItemfocus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('add', '增', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commonResOper', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('allOper', '全部操作', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commonResOper', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('batchDel', '批量删', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commonResOper', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('batchEdit', '批量修改', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commonResOper', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('del', '删', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commonResOper', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('edit', '改', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commonResOper', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('export', '导出', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commonResOper', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('list', '查', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'commonResOper', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('csworker', '客服', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'contactType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('office-phone', '办公电话', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'contactType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('phoneno', '手机号', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'contactType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('qq-group', 'qq群', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'contactType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('qq-person', '个人qq号', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'contactType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wx-person', '个人微信号', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'contactType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '随堂', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'courseAtt', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '自主学习', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'courseAtt', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('CNY', '人民币', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'currency', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('EUR', '欧元', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'currency', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('GBP', '英镑', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'currency', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('HKD', '港元', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'currency', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('JPY', '日元', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'currency', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('USD', '美元', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'currency', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('-1', '未分配', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dataLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '禁止所有数据访问', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dataLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '可操作本部门数据', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dataLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '可操作本部门及下属部门数据', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dataLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '可操作上一级、本级、下级部门数据', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dataLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '可操作上两级及以下部门数据', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dataLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '可操作本机构所有数据', NULL, NULL, NULL, NULL, '#c71585', NULL, NULL, 'dataLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '可操作全平台数据', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'dataLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '委员会', '', '', '', '', 'rgba(144, 147, 153, 1)', '', '', 'deptType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '监督部', NULL, NULL, NULL, NULL, 'rgba(64, 158, 255, 1)', '2,', NULL, 'deptType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '本部部门', NULL, NULL, NULL, NULL, 'rgba(230, 162, 60, 1)', NULL, NULL, 'deptType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '分公司', '', '', '', '', 'rgba(255, 215, 0, 1)', '', '', 'deptType', 3, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('博士', '博士', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'eduBackground', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('大专', '大专', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'eduBackground', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('本科', '本科', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'eduBackground', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('研究生', '研究生', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'eduBackground', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gbuyTimes08:00', '08:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gbuyTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gbuyTimes10:00', '10:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gbuyTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gbuyTimes12:00', '12:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gbuyTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gbuyTimes14:00', '14:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gbuyTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gbuyTimes16:00', '16:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gbuyTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gbuyTimes18:00', '18:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gbuyTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gbuyTimes20:00', '20:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gbuyTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('gbuyTimes22:00', '22:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'gbuyTimes', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '未启用', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'goodsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '正常', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'goodsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '预淘汰', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'goodsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '淘汰', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'goodsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '试销', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'goodsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('健康', '健康', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'healCondition', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('生病', '生病', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'healCondition', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('accountSupport', '账户帮助', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'helpCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('shoppingGuide', '购物指南', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'helpCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('ac', '会计核心', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i3k69g7z4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('arc', '内容管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i3k69g7z4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('mallFront', '商城前端', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i3k69g7z4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('mallm', '商城后台', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i3k69g7z4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('mallWxa', '商城小程序', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i3k69g7z4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('sysAdmin', '系统管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i3k69g7z4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wxpub', '微信公众号', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i3k69g7z4', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', 'url', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i8yf45127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '菜单', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'i8yf45127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '燃料物流', '', '', '', '', '', '', '', 'IndustryCategory', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('computersHardware', '计算机硬件', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IndustryCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('computersSoftware', '计算机软件', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IndustryCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('sp', '食品', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IndustryCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('zfdw-sb', '政府单位-社保', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IndustryCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '书籍', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631307316781128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '电子书', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631307316781128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '文化用品', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631307316781128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('L1', '一级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631395478646195', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('L2', '二级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631395478646195', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('L3', '三级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631395478646195', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('L4', '四级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631395478646195', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('L5', '五级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631395478646195', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('erp', 'ERP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631427826041171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('mall', '商城', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631427826041171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '', '', '', 'IT1631666500591186', 1, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('10', '多图文', '', '', '', '', '', '', '', 'IT1631666500591186', 10, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('11', '单视频', '', '', '', '', '', '', '', 'IT1631666500591186', 11, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('12', '多视频', '', '', '', '', '', '', '', 'IT1631666500591186', 12, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('13', '单选radio', '', '', '', '', '', '', '', 'IT1631666500591186', 13, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('14', '多选checkbox', '', '', '', '', '', '', '', 'IT1631666500591186', 14, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '数字', '', '', '', '', '', '', '', 'IT1631666500591186', 2, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '日期', '', '', '', '', '', '', '', 'IT1631666500591186', 3, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '单选列表', '', '', '', '', '', '', '', 'IT1631666500591186', 4, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '多选列表', '', '', '', '', '', '', '', 'IT1631666500591186', 5, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '单文件', '', '', '', '', '', '', '', 'IT1631666500591186', 6, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '多文件夹', '', '', '', '', '', '', '', 'IT1631666500591186', 7, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '富文本', '', '', '', '', '', '', '', 'IT1631666500591186', 8, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('9', '单图文', '', '', '', '', '', '', '', 'IT1631666500591186', 9, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '个月', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631954002123139', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '个季度', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631954002123139', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '年', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1631954002123139', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '软件', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590217408146', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '硬件', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590217408146', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '顾问成本', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590365394155', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '实施成本', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590365394155', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '开发成本', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590365394155', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '培训成本', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590365394155', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '其他成本', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590365394155', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '首付款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590560478117', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '方案设计款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590560478117', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '开发阶段款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590560478117', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '测试阶段款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590560478117', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '验收试运营款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590560478117', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '中期款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590560478117', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '尾款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1640590560478117', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '打开', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1645446807730187', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '研发中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1645446807730187', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已完成', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1645446807730187', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已关闭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1645446807730187', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '新增功能', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242369307127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '功能改进', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242369307127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', 'bug修复', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242369307127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '用户体验', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242369307127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', 'UI优化', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242369307127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '内部需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242369307127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '删除需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242369307127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '接口需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242369307127', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '基础需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242435289138', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '增值需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242435289138', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '扩展需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242435289138', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '部门意见', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242537115125', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '用户反馈', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242537115125', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '技术反馈', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242537115125', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '运营反馈', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242537115125', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '团队讨论', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242537115125', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '老板需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242537115125', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '自身需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647242537115125', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1647268434864172', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '评审中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647268434864172', 1, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '设计中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647268434864172', 2, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '开发中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647268434864172', 3, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '测试中', '', '', '', '', '', '', '', 'IT1647268434864172', 4, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('5', 'sit', '', '', '', '', '', '', '', 'IT1647268434864172', 5, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('6', 'uat', '', '', '', '', '', '', '', 'IT1647268434864172', 6, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('7', '已上线', '', '', '', '', '', '', '', 'IT1647268434864172', 7, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('8', '已下线', '', '', '', '', '', '', '', 'IT1647268434864172', 8, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('9', '已删除', '', '', '', '', '', '', '', 'IT1647268434864172', 9, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '打开', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647316107064178', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '需求评审', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647316107064178', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '计划会', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647316107064178', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '研发中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647316107064178', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '测试中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647316107064178', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '迭代上线', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647316107064178', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '已完成', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647316107064178', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '关闭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647316107064178', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '待领取', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647801639994137', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '执行中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647801639994137', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已完工', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647801639994137', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已验收', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647801639994137', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '已结算', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647801639994137', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('9', '已关闭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647801639994137', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '必现', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647979373083149', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '大概率复现', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647979373083149', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '小概率复现', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647979373083149', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '仅出现一次', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1647979373083149', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '高', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648060466254181', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648060466254181', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '低', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648060466254181', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '基本场景侧漏', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648061675513165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '特殊场景侧漏', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648061675513165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '重复问题', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648061675513165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '新需求引入', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648061675513165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '修改引入', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648061675513165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '接口变更', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648061675513165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '不是问题', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1648061675513165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '不计费', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1649539230402136', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '按购买人数计费', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1649539230402136', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '总包费用', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1649539230402136', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '1个月免费试用，1个月后按实际使用人数每月计费', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1649539230402136', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', NULL, NULL, '1111111', '22222', NULL, '', NULL, 'IT1649541511551186', NULL, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '审批中', NULL, NULL, NULL, NULL, 'rgba(194, 50, 50, 1)', NULL, NULL, 'IT1649541511551186', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '审批通过', NULL, NULL, NULL, NULL, 'rgba(96, 66, 66, 1)', NULL, NULL, 'IT1649541511551186', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '审批不通过', NULL, NULL, NULL, NULL, 'rgba(0, 206, 209, 1)', NULL, NULL, 'IT1649541511551186', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '流程取消或者删除', NULL, NULL, NULL, NULL, 'rgba(103, 194, 58, 1)', NULL, NULL, 'IT1649541511551186', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '协同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1649768047549179', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '研发', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1649768047549179', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '电商', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1649768047549179', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '1级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652703728663171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '2级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652703728663171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '3级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652703728663171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '4级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652703728663171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '5级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652703728663171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '6级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652703728663171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '史诗', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652705775326118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '特性', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652705775326118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '故事', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1652705775326118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', NULL, NULL, NULL, NULL, 'rgba(199, 21, 133, 0.46)', NULL, NULL, 'IT1653640125080133', NULL, NULL, NULL, NULL, 'ep:bell-filled');
INSERT INTO `meta_item_option` VALUES ('1', '待确认', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640125080133', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '待付款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640125080133', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已付款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640125080133', NULL, NULL, NULL, NULL, 'ep:avatar');
INSERT INTO `meta_item_option` VALUES ('4', '已完成', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640125080133', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '已取消', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640125080133', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '退单', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640125080133', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '已关闭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640125080133', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '新购', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640508527129', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '续费', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640508527129', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '新增人数', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653640508527129', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '免费版', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653771620457165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '专业版', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1653771620457165', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '投标', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655628949671124', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '雇佣', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655628949671124', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '学徒级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629148048182', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '学士级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629148048182', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '大师级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629148048182', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '宗师级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629148048182', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '皇者级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629148048182', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '圣尊级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629148048182', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '帝王级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629148048182', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '神级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629148048182', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '金牌', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655629261882171', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通会员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655630253666115', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '青铜会员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655630253666115', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '白银会员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655630253666115', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '黄金会员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655630253666115', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '紫金会员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655630253666115', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '皇冠会员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655630253666115', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '项目', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '任务', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '产品', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', 'bug', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '迭代', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '团队', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '组织', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('9', '个人', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('C', '成本', '', '', '', '', '', '', '', 'IT1655730717345168', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('H', '合同', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('K', '客户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('TB', '测试库', '', '', '', '', '', '', '', 'IT1655730717345168', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('TC', '测试用例', '', '', '', '', '', '', '', 'IT1655730717345168', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('TE', '测试执行用例', '', '', '', '', '', '', '', 'IT1655730717345168', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('TP', '测试计划', '', '', '', '', '', '', '', 'IT1655730717345168', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('W', '文章', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655730717345168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '草稿', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655833439701197', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '已上架', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655833439701197', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已下架', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655833439701197', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '审核中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1655833439701197', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '不限', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1656613513173147', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '同城', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1656613513173147', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '同省', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1656613513173147', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '同国', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1656613513173147', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '同洲', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1656613513173147', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '未托管', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1657030829847153', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '待付款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1657030829847153', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已托管资金', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1657030829847153', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已付款给服务商', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1657030829847153', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '已退款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1657030829847153', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '未开始', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200417191153', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '执行中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200417191153', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已结束', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200417191153', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '未通过', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200476395123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '已通过', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200476395123', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '未测', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200621783128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '忽略', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200621783128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '通过', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200621783128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '受阻', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200621783128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '失败', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658200621783128', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '草稿', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658216481853116', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '评审中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658216481853116', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '审核通过', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658216481853116', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '审核未通过', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658216481853116', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '废弃', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658216481853116', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '功能测试', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '性能测试', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '配置相关', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '安装部署', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '接口测试', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '安全相关', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '兼容性测试', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', 'UI测试', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '其他', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658305014197168', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '男', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658337903419152', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '女', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658337903419152', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658511571561129', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '启用', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658511571561129', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '关闭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658511571561129', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '不限制', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658516293195139', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', 'scrum', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658516293195139', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '看板', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1658516293195139', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '传统', '', '', '', '', '', '', '', 'IT1658516293195139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '发布需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1663065169722118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '用户投标', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1663065169722118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '雇主选标', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1663065169722118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '托管赏金', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1663065169722118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '用户工作', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1663065169722118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '确认验收', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1663065169722118', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '开发类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1669924214148198', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '测试类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1669924214148198', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '设计类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1669924214148198', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '管理类', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1669924214148198', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944492645179', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944492646188', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944503839185', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944503839259', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944514303146', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944514303213', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944524851187', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944524851289', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944555902121', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944555902225', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944564645122', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944564645299', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944615752191', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944615752285', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944713873194', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944713873231', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944759724121', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944759724283', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944763568117', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944763568284', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944764674163', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944764674262', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944949768176', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944955492177', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690944958874156', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690944958875187', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945137495198', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945137495265', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945137495133', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945137495372', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945137497183', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945137497284', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945137519189', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945137519218', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945137519326', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945137519534', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945137519441', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945137519655', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945137520145', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945137520278', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945143744163', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945143744243', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945156861124', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945156861234', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690945203822188', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690945203822248', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('IT1690966016690175', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT1690966016691137', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '#409EFF', '', '', 'IT1691041018097128', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '#409EFF', '', '', 'IT1691041024122167', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '#409EFF', '', '', 'IT1691041046190137', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '', '', '', 'IT1691041140885177', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '', '', '', 'IT1691041181645118', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '', '', '', 'IT1691041192525112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '', '', '', 'IT1691041261963133', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '', '', '', 'IT1691041289535196', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '#409EFF', '', '', 'IT1691041782474124', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '', '', '', 'IT1691041836958138', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '普通文本', '', '', '', '', '', '', '', 'IT1691042054883147', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '2', '', '', '', '', '', '', '', 'IT1691042925330167', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '33', '', '', '', '', '', '', '', 'IT1691043059702117', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '3', '', '', '', '', '', '', '', 'IT1691043750573158', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '3', '', '', '', '', '', '', '', 'IT1691044715026114', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('23', '33', '', '', '', '', '', '', '', 'IT1691044847994173', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xx', 'xx', '', '', '', '', '', '', '', 'IT1691044847994173', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '无效', '', '', '', '', '', '', '', 'IT1691120718771191', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '有效', '', '', '', '', '', '', '', 'IT1691120718771191', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '自定义角色', '', '', '', '', '', '', '', 'IT1691776688640136', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '公共角色', '', '', '', '', '', '', '', 'IT1691776688640136', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '已下架', '', '', '', '', '', '', '', 'IT1691948950553132', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '已上架', '', '', '', '', '', '', '', 'IT1691948950553132', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '停用', '', '', '', '', '', '', '', 'IT1691999268586149', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '启用', '', '', '', '', '', '', '', 'IT1691999268586149', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '待续费', '', '', '', '', '', '', '', 'IT1691999268586149', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '产品性能问题', '', '', '', '', '', '', '', 'IT1693456602063184', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '质保期问题', '', '', '', '', '', '', '', 'IT1693456602063184', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '操作说明', '', '', '', '', '', '', '', 'IT1693456602063184', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '售后服务', '', '', '', '', '', '', '', 'IT1693456602063184', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '功能介绍', '', '', '', '', '', '', '', 'IT1693456602063184', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '咨询中', '', '', '', '', '', '', '', 'IT1693456982241118', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已回复', '', '', '', '', '', '', '', 'IT1693456982241118', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已结束', '', '', '', '', '', '', '', 'IT1693456982241118', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '待发布', '', '', '', '', '', '', '', 'IT1693459711250198', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '已发布', '', '', '', '', '', '', '', 'IT1693459711250198', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '取消发布', '', '', '', '', '', '', '', 'IT1693459711250198', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '#c7158577', '', '', 'IT1694064251340118', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '激活', '', '', '', '', 'rgba(103, 194, 58, 1)', '', '', 'IT1694064251340118', 2, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '挂起', '', '', '', '', 'rgba(255, 215, 0, 1)', '', '', 'IT1694064251340118', 1, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '否', '', '', '', '', '', '', '', 'IT1694097184036177', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '是', '', '', '', '', '', '', '', 'IT1694097184036177', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '否', '', '', '', '', '', '', '', 'IT1694097270479188', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('PENDING', '是', '', '', '', '', '', '', '', 'IT1694097270479188', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '#c71585', '1', '', 'IT1694816378289112', 0, '1', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '启用', '', '', '', '', '', '', '', 'IT1694816378289112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '拟停用', '', '', '', '', '', '', '', 'IT1694816378289112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已停用', '', '', '', '', '', '', '', 'IT1694816378289112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '表单引擎', '', '', '', '', '', '', '', 'IT1694873207591148', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '寄存主表某个字段', '', '', '', '', '', '', '', 'IT1694873207591148', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '独立建表', '', '', '', '', '', '', '', 'IT1694873207591148', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '其它文件存储', '', '', '', '', 'rgba(199, 21, 133, 1)', '', '', 'IT1694873207591148', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '发起', '', '', '', '', '', '', '', 'IT1695464397085192', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '待扫码确认', '', '', '', '', '', '', '', 'IT1695464397085192', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已加入', '', '', '', '', '', '', '', 'IT1695464397085192', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '无条件邀请', '', '', '', '', '', '', '', 'IT1695464485186112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '绑定已有账户', '', '', '', '', '', '', '', 'IT1695464485186112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '注册企业内部子账号', '', '', '', '', '', '', '', 'IT1695464485186112', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '微信扫码', '', '', '', '', '', '', '', 'IT1695464621498144', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '手机号码+短信验证码', '', '', '', '', '', '', '', 'IT1695464621498144', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '邮件+验证码', '', '', '', '', '', '', '', 'IT1695464621498144', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '小程序', '', '', '', '', '', '', '', 'IT1695464621498144', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wxminapp', '微信小程序', '', '', '', '', '', '', '', 'IT1695464732727138', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wxopen', '微信公共平台', '', '', '', '', '', '', '', 'IT1695464732727138', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wxpub', '微信公众号', '', '', '', '', '', '', '', 'IT1695464732727138', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('zfbminappp', '支付宝小程序', '', '', '', '', '', '', '', 'IT1695464732727138', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '内部组织', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '供应商', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('10', '代理商', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('11', '外部个人', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('12', '财务做账公司', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('13', '知识产权服务商', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('14', '项目部', '', '', '', '', '', '', '', 'IT1695485364688182', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '渠道商', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', 'it服务商', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '仓储物流合作商', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '子公司', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '分公司', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '金融机构', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('9', '上级机构', '', '', '', '', '', '', '', 'IT1695485364688182', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('MueCKGqIXu2hStRmq9XORHr8l6zRPeIe8-HuG8sVDHE', '任务通知', 'msgTemplateType', 'taskNotify', '', '', '', '', '', 'IT1695552235782171', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('payNotifyxxxxx', '支付通知', 'msgTemplateType', 'payNotify', NULL, NULL, '', '', '', 'IT1695552235782171', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xxxxxxxxx', '账户变动', 'msgTemplateType', 'amountChange', NULL, NULL, '', '', '', 'IT1695552235782171', 0, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('cust', '客户通道', '', '', '', '', '', '', '', 'IT1696115825746188', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('staff', '员工通道', '', '', '', '', '', '', '', 'IT1696115825746188', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '微信二维码扫码登陆', '', '', '', '', '', '', '', 'IT1696115901424135', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '点击小程序登陆', '', '', '', '', '', '', '', 'IT1696115901424135', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '账户密码登陆', '', '', '', '', '', '', '', 'IT1696115901424135', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '手机号码+验证码登陆', '', '', '', '', '', '', '', 'IT1696115901424135', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '个人账户', '', '', '', '', '', '', '', 'IT1696136315756189', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '企业管理员账户', '', '', '', '', '', '', '', 'IT1696136315756189', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '企业员工账户', '', '', '', '', '', '', '', 'IT1696136315756189', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('CONNECT', 'CONNECT', '', '', '', '', '', '', '', 'IT1696468156941139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('GET', 'GET', '', '', '', '', '', '', '', 'IT1696468156941139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('HEAD', 'HEAD', '', '', '', '', '', '', '', 'IT1696468156941139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('OPTIONS', 'OPTIONS', '', '', '', '', '', '', '', 'IT1696468156941139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('PATCH', 'PATCH', '', '', '', '', '', '', '', 'IT1696468156941139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('POST', 'POST', '', '', '', '', '', '', '', 'IT1696468156941139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('TRACE', 'TRACE', '', '', '', '', '', '', '', 'IT1696468156941139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '先删除表(如有)再建表', '', '', '', '', '', '', '', 'IT1697082612111178', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '如没有表则创建', '', '', '', '', '', '', '', 'IT1697082612111178', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '不执行建表操作', '', '', '', '', '', '', '', 'IT1697082612111178', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '重大项目', '', '', '', '', '', '', '', 'IT1698048367949132', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '领导交办', '', '', '', '', '', '', '', 'IT1698048367949132', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '重大会议', '', '', '', '', '', '', '', 'IT1698048367949132', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '投资并购', '', '', '', '', '', '', '', 'IT1698048367949132', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '已申报', '', '', '', '', '#409EFF', '', '', 'IT1698055140245164', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '进行中', '', '', '', '', '#67C23A', '', '', 'IT1698055140245164', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已完成', '', '', '', '', 'rgba(0, 206, 209, 1)', '', '', 'IT1698055140245164', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已超时', '', '', '', '', 'rgba(140, 13, 13, 1)', '', '', 'IT1698055140245164', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '禁用', '', '', '', '', '', '', '', 'IT1698139176276161', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '启用', '', '', '', '', '', '', '', 'IT1698139176276161', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '事假', '', '', '', '', '', '', '', 'IT1698630396520116', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '病假', '', '', '', '', '', '', '', 'IT1698630396520116', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '年假', '', '', '', '', '', '', '', 'IT1698630396520116', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '婚假', '', '', '', '', '', '', '', 'IT1698630396520116', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '哺乳假', '', '', '', '', '', '', '', 'IT1698630396520116', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '丧假', '', '', '', '', '', '', '', 'IT1698630396520116', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '外勤', '', '', '', '', '', '', '', 'IT1698630396520116', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1698646774961168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '审批中', '', '', '', '', '', '', '', 'IT1698646774961168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '审批通过', '', '', '', '', '', '', '', 'IT1698646774961168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '审批不通过', '', '', '', '', '', '', '', 'IT1698646774961168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '流程取消或者删除', '', '', '', '', '', '', '', 'IT1698646774961168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '正常', '', '', '', '', 'rgba(103, 194, 58, 1)', '', '', 'IT1698648711725167', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '重要', '', '', '', '', '', '', '', 'IT1698648711725167', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '紧急', '', '', '', '', '', '', '', 'IT1698648711725167', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '公司纪委章', '', '', '', '', 'rgba(255, 0, 0, 1)', '', '', 'IT1698746533985172', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '公司监督部章', '', '', '', '', 'rgba(64, 158, 255, 1)', '', '', 'IT1698746533985172', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1_1', '公司审计部章', '', '', '', '', 'rgba(199, 21, 133, 1)', '', '', 'IT1698746533985172', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1_1_1', '公司巡视办章', '', '', '', '', 'rgba(255, 215, 0, 1)', '', '', 'IT1698746533985172', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '电子印章', '', '', '', '', '', '', '', 'IT1698746860714119', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0_1', '电子印章', '', '', '', '', '', '', '', 'IT1698746860714119', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '物理印章', '', '', '', '', '', '', '', 'IT1698746860714119', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '启用', '', '', '', '', '', '', '', 'IT1698746951774129', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '停用', '', '', '', '', '', '', '', 'IT1698746951774129', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '停用', '', '', '', '', '', '', '', 'IT1698810204498168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '启用', '', '', '', '', '', '', '', 'IT1698810204498168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '便携机', '', '', '', '', '', '', '', 'IT1698811713212164', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '台式机', '', '', '', '', '', '', '', 'IT1698811713212164', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '普通印章', '', '', '', '', '', '', '', 'IT1698811713212164', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '闲置', '', '', '', '', '', '', '', 'IT1699254565655168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '在用', '', '', '', '', '', '', '', 'IT1699254565655168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '借用', '', '', '', '', '', '', '', 'IT1699254565655168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '维修', '', '', '', '', '', '', '', 'IT1699254565655168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '报废', '', '', '', '', '', '', '', 'IT1699254565655168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '待领取', '', '', '', '', '', '', '', 'IT1699254565655168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '待归还', '', '', '', '', '', '', '', 'IT1699254565655168', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('change', '变更单', '', '', '', '', '', '', '', 'IT1699341051669139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dispose', '处置单', '', '', '', '', '', '', '', 'IT1699341051669139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('inventory', '领用单', '', '', '', '', '', '', '', 'IT1699341051669139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('lease', '租借单', '', '', '', '', '', '', '', 'IT1699341051669139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('maint', '维修单', '', '', '', '', '', '', '', 'IT1699341051669139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('reallocation', '调拨单', '', '', '', '', '', '', '', 'IT1699341051669139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('return', '归还单', '', '', '', '', '', '', '', 'IT1699341051669139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('use', '申领单', '', '', '', '', '', '', '', 'IT1699341051669139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '资产责任人盘点', '', '', '', '', '', '', '', 'IT1699519561556146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '资产责任人盘点与指定盘点人员', '', '', '', '', '', '', '', 'IT1699519561556146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '仅指定盘点人员', '', '', '', '', '', '', '', 'IT1699519561556146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '未发审', '', '', '', '', '', '', '', 'IT1699864099466146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '审核中', '', '', '', '', '', '', '', 'IT1699864099466146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已通过', '', '', '', '', '', '', '', 'IT1699864099466146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '未通过', '', '', '', '', '', '', '', 'IT1699864099466146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '已取消', '', '', '', '', '', '', '', 'IT1699864099466146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '待领取', '', '', '', '', '', '', '', 'IT1699864099466146', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '闲置', '', '', '', '', '', '', '', 'IT1699930319224119', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '在用', '', '', '', '', '', '', '', 'IT1699930319224119', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '借用', '', '', '', '', '', '', '', 'IT1699930319224119', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '维修', '', '', '', '', '', '', '', 'IT1699930319224119', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '报废', '', '', '', '', '', '', '', 'IT1699930319224119', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '未发审', '', '', '', '', '', '', '', 'IT1700031269520126', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '审核中', '', '', '', '', '', '', '', 'IT1700031269520126', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '待入库', '', '', '', '', '', '', '', 'IT1700031269520126', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已入库', '', '', '', '', '', '', '', 'IT1700031269520126', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '流程取消', '', '', '', '', '', '', '', 'IT1700031269520126', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '手工', '', '', '', '', '', '', '', 'IT1706952527358133', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '自动', '', '', '', '', '', '', '', 'IT1706952527358133', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '任务', '', '', '', '', '', '', '', 'IT1706971046692139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '缺陷', '', '', '', '', '', '', '', 'IT1706971046692139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '测试用例设计', '', '', '', '', '', '', '', 'IT1706971046692139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '测试执行', '', '', '', '', '', '', '', 'IT1706971046692139', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '已通过', '', '', '', '', '', '', '', 'IT1709851193391186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '待审核', '', '', '', '', '', '', '', 'IT1709851193391186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已拒绝', '', '', '', '', '', '', '', 'IT1709851193391186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '国内', '', '', '', '', '', '', '', 'IT1709851314745158', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '国外', '', '', '', '', '', '', '', 'IT1709851314745158', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1709851367862178', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '待审核', '', '', '', '', '', '', '', 'IT1709851367862178', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已拒绝', '', '', '', '', '', '', '', 'IT1709851367862178', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '普通短信', '', '', '', '', '', '', '', 'IT1709851457287157', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '营销短信', '', '', '', '', '', '', '', 'IT1709851457287157', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1710154575548176', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '待确认', '', '', '', '', '', '', '', 'IT1710154575548176', 1, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '待付款', '', '', '', '', '', '', '', 'IT1710154575548176', 2, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已付款', '', '', '', '', '', '', '', 'IT1710154575548176', 3, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '已完成', '', '', '', '', '', '', '', 'IT1710154575548176', 4, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '已取消', '', '', '', '', '', '', '', 'IT1710154575548176', 5, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '退单', '', '', '', '', '', '', '', 'IT1710154575548176', 6, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '已关闭', '', '', '', '', '', '', '', 'IT1710154575548176', 7, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1710225652000177', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '已上架', '', '', '', '', '', '', '', 'IT1710225652000177', 1, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已下架', '', '', '', '', '', '', '', 'IT1710225652000177', 2, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '微信', '', '', '', '', '', '', '', 'IT1710226056351152', 1, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '支付宝', '', '', '', '', '', '', '', 'IT1710226056351152', 1, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '线下支付', '', '', '', '', '', '', '', 'IT1710226056351152', 3, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '不计费', '', '', '', '', '', '', '', 'IT1710226292822132', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '按购买数量计费', '', '', '', '', '', '', '', 'IT1710226292822132', 1, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '总包费用', '', '', '', '', '', '', '', 'IT1710226292822132', 2, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '商标业务', '', '', '', '', '', '', '', 'IT1710226411561122', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '专利业务', '', '', '', '', '', '', '', 'IT1710226411561122', 1, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '版权业务', '', '', '', '', '', '', '', 'IT1710226411561122', 2, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '商标业务', '', '', '', '', '', '', '', 'IT1710226483113143', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '专利业务', '', '', '', '', '', '', '', 'IT1710226483113143', 1, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '版权业务', '', '', '', '', '', '', '', 'IT1710226483113143', 2, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '科技项目', '', '', '', '', '', '', '', 'IT1710226483113143', 3, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '待用户自行选定', '', '', '', '', '', '', '', 'IT1711132082348138', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '随机抽取1个', '', '', '', '', '', '', '', 'IT1711132082348138', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '第一个', '', '', '', '', '', '', '', 'IT1711132082348138', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '最后一个', '', '', '', '', '', '', '', 'IT1711132082348138', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '待确认', '', '', '', '', '', '', '', 'IT1712680523101162', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '已确认', '', '', '', '', '', '', '', 'IT1712680523101162', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '无需结算', '', '', '', '', '', '', '', 'IT1712680591518151', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '待结算', '', '', '', '', '', '', '', 'IT1712680591518151', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '已提交', '', '', '', '', '', '', '', 'IT1712680591518151', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已通过', '', '', '', '', '', '', '', 'IT1712680591518151', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '已拒绝', '', '', '', '', '', '', '', 'IT1712680591518151', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', 'api通用接口处理', '', '', '', '', '', '', '', 'IT1713930215405148', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '唛盟表格通用api-新增', '', '', '', '', '', '', '', 'IT1713930215405148', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '唛盟表格通用api-修改', '', '', '', '', '', '', '', 'IT1713930215405148', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '唛盟表格通用api-列表查询', '', '', '', '', '', '', '', 'IT1713930215405148', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '唛盟表格通用api-单记录查询', '', '', '', '', '', '', '', 'IT1713930215405148', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '唛盟数据集api-列表查询', '', '', '', '', '', '', '', 'IT1713930215405148', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '唛盟数据集api-单数据查询', '', '', '', '', '', '', '', 'IT1713930215405148', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '唛盟表格通用api-增删改查一体化', '', '', '', '', '', '', '', 'IT1713930215405148', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', 'crm', '', '', '', '', '', '', '', 'IT1714452369149184', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '项目进度', '', '', '', '', '', '', '', 'IT1714452369149184', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '0级', '', '', '', '', '', '', '', 'IT1716141779047192', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '1级', '', '', '', '', '', '', '', 'IT1716141779047192', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '2级', '', '', '', '', '', '', '', 'IT1716141779047192', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '3级', '', '', '', '', '', '', '', 'IT1716141779047192', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '1', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('10', '指定角色', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('20', '指定部门', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('21', '部门领导', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('211', '指定部门的上级主管领导', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('22', '岗位', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('30', '用户', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('31', '上一步提交者的部门领导', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('311', '上一步任务提交者所属部门的上级主管领导', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('32', '发起人的部门领导', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('321', '发起人所属部门的上级主管领导', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('35', '发起人自选', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('36', '转发起人', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('37', '转主办人', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('38', '转监控人', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('39', '上一步分配', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('40', '用户组', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('41', '项目组长', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('42', '项目组成员', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('55', '前天', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('60', '流程表达式', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('90', '高级自定义查询', '', '', '', '', '', '', '', 'IT1716319161416186', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('after', '后加签', '', '', '', '', '', '', '', 'IT1717254785691169', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('before', '前加签', '', '', '', '', '', '', '', 'IT1717254785691169', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '正常报工(都适用)', '', '', '', '', '', '', '', 'IT1718881282938116', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '按报价工时减去已登记工时一次性填满（适合众包报价任务）', '', '', '', '', '', '', '', 'IT1718881282938116', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '按预估工时减去已登记工时一次性填满（适合不严格要求报工，但为了统计进度等）', '', '', '', '', '', '', '', 'IT1718881282938116', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1718946061378186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '已启用', '', '', '', '', '', '', '', 'IT1718946061378186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '拟停用', '', '', '', '', '', '', '', 'IT1718946061378186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '已停用', '', '', '', '', '', '', '', 'IT1718946061378186', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '全部可看', '', '', '', '', '', '', '', 'IT1718946247499132', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '同机构可看', '', '', '', '', '', '', '', 'IT1718946247499132', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '同机构同项目可看', '', '', '', '', '', '', '', 'IT1718946247499132', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('9', '仅自己可看', '', '', '', '', '', '', '', 'IT1718946247499132', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '全部可写', '', '', '', '', '', '', '', 'IT1718946451725189', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '同机构可写', '', '', '', '', '', '', '', 'IT1718946451725189', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '同机构同项目可写', '', '', '', '', '', '', '', 'IT1718946451725189', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '同项目上级可写', '', '', '', '', '', '', '', 'IT1718946451725189', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('9', '仅自己可写', '', '', '', '', '', '', '', 'IT1718946451725189', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '完全不可见', '', '', '', '', '', '', '', 'IT1718986625416197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '仅本司人员可见', '', '', '', '', '', '', '', 'IT1718986625416197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '关联人员可见（众包-外包-招投标）', '', '', '', '', '', '', '', 'IT1718986625416197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '本部门、上级部门、下属部门可见', '', '', '', '', '', '', '', 'IT1718986625416197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('4', '本部门、上级部门可见', '', '', '', '', '', '', '', 'IT1718986625416197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('5', '本部门、下级部门可见、上级不可见', '', '', '', '', '', '', '', 'IT1718986625416197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('6', '本部门可见', '', '', '', '', '', '', '', 'IT1718986625416197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('9', '任何人可见', '', '', '', '', '', '', '', 'IT1718986625416197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '不限制，允许任何人', '', '', '', '', '', '', '', 'IT1718986981924172', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '同机构下的人员', '', '', '', '', '', '', '', 'IT1718986981924172', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '同产品内人员', '', '', '', '', '', '', '', 'IT1718986981924172', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '同产品下同小组内人员', '', '', '', '', '', '', '', 'IT1718986981924172', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '不限制，允许任何人', '', '', '', '', '', '', '', 'IT1718987065247138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '同机构下的人员', '', '', '', '', '', '', '', 'IT1718987065247138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '同产品内人员', '', '', '', '', '', '', '', 'IT1718987065247138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '同产品下同小组内人员', '', '', '', '', '', '', '', 'IT1718987065247138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '不限制，允许任何人', '', '', '', '', '', '', '', 'IT1718987154702157', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '同机构下的人员', '', '', '', '', '', '', '', 'IT1718987154702157', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '同产品内人员', '', '', '', '', '', '', '', 'IT1718987154702157', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '同产品下同小组内人员', '', '', '', '', '', '', '', 'IT1718987154702157', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '不限制，允许任何人', '', '', '', '', '', '', '', 'IT1718987233944124', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '同机构下的人员', '', '', '', '', '', '', '', 'IT1718987233944124', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '同产品内人员', '', '', '', '', '', '', '', 'IT1718987233944124', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '同产品下同小组内人员', '', '', '', '', '', '', '', 'IT1718987233944124', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '不限制，允许任何人', '', '', '', '', '', '', '', 'IT1718987814961195', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '同机构下的人员', '', '', '', '', '', '', '', 'IT1718987814961195', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '同项目内人员', '', '', '', '', '', '', '', 'IT1718987814961195', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '同项目同小组人员', '', '', '', '', '', '', '', 'IT1718987814961195', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('GET', 'GET', '', '', '', '', '', '', '', 'IT1723764330636175', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('POST', 'POST', '', '', '', '', '', '', '', 'IT1723764330636175', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('form-data', 'form-data', '', '', '', '', '', '', '', 'IT1723766596279171', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('json', 'json', '', '', '', '', '', '', '', 'IT1723766596279171', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('x-www-form-urlencoded', 'x-www-form-urlencoded', '', '', '', '', '', '', '', 'IT1723766596279171', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('xml', 'xml', '', '', '', '', '', '', '', 'IT1723766596279171', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('basic-auth', 'basic-auth', '', '', '', '', '', '', '', 'IT1723766670174118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('bearer-token', 'bearer-token', '', '', '', '', '', '', '', 'IT1723766670174118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1724128224877185', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '启用', '', '', '', '', '', '', '', 'IT1724128224877185', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '拟停用', '', '', '', '', '', '', '', 'IT1724128224877185', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '已报废', '', '', '', '', '', '', '', 'IT1724128224877185', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('克', '克', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('千克', '千克', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('台', '台', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('套', '套', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('本', '本', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('根', '根', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('瓶', '瓶', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('箱', '箱', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('米', '米', '', '', '', '', '', '', '', 'IT1724128659739118', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1724190168957165', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '申请中', '', '', '', '', '', '', '', 'IT1724190168957165', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '审核中', '', '', '', '', '', '', '', 'IT1724190168957165', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '待领用', '', '', '', '', '', '', '', 'IT1724190168957165', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('4', '已领用', '', '', '', '', '', '', '', 'IT1724190168957165', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('5', '已还部分', '', '', '', '', '', '', '', 'IT1724190168957165', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('6', '已全部归还', '', '', '', '', '', '', '', 'IT1724190168957165', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '无警示', '', '', '', '', '', '', '', 'IT1724192379224197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '单价过高', '', '', '', '', '', '', '', 'IT1724192379224197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '库存不足', '', '', '', '', '', '', '', 'IT1724192379224197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '库存过高', '', '', '', '', '', '', '', 'IT1724192379224197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('4', '报废中', '', '', '', '', '', '', '', 'IT1724192379224197', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1724194444816163', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '待发货', '', '', '', '', '', '', '', 'IT1724194444816163', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '待收货', '', '', '', '', '', '', '', 'IT1724194444816163', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '已收货', '', '', '', '', '', '', '', 'IT1724194444816163', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('4', '已入库', '', '', '', '', '', '', '', 'IT1724194444816163', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0', '初始', '', '', '', '', '', '', '', 'IT1724194952241195', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0_1', '初始', '', '', '', '', '', '', '', 'IT1724194952241195', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0_1_1', '初始', '', '', '', '', '', '', '', 'IT1724194952241195', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('0_1_1_1', '初始', '', '', '', '', '', '', '', 'IT1724194952241195', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '子公司仓库', '', '', '', '', '', '', '', 'IT1724195549227138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '总部仓库', '', '', '', '', '', '', '', 'IT1724195549227138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '个人使用', '', '', '', '', '', '', '', 'IT1724202595934151', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '部门使用', '', '', '', '', '', '', '', 'IT1724202595934151', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '自取', '', '', '', '', '', '', '', 'IT1724202684849136', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '快递', '', '', '', '', '', '', '', 'IT1724202684849136', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('3', '他人代领', '', '', '', '', '', '', '', 'IT1724202684849136', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '入库', '', '', '', '', '', '', '', 'IT1724218211018144', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('2', '出库', '', '', '', '', '', '', '', 'IT1724218211018144', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('apply_for_use', '办公用品申领', '', '', '', '', '', '', '', 'IT1724275007802138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('instock', '办公用品入库单', '', '', '', '', '', '', '', 'IT1724275007802138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('outstock', '办公用品出库单', '', '', '', '', '', '', '', 'IT1724275007802138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('purchase', '办公用品采购', '', '', '', '', '', '', '', 'IT1724275007802138', 0, '', NULL, '[]', '');
INSERT INTO `meta_item_option` VALUES ('1', '测试1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210906RN-1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '测试插入5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210906RN-1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '测试3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210906RN-1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '测试4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210906RN-1', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '知识库', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:superscript');
INSERT INTO `meta_item_option` VALUES ('1', '新闻', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:binoculars');
INSERT INTO `meta_item_option` VALUES ('10', '知识产权', '', '', '', '', '', '', '', 'IT20210913IH-1', 10, '', NULL, NULL, 'fa:american-sign-language-interpreting');
INSERT INTO `meta_item_option` VALUES ('2', '内部公告', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:asterisk');
INSERT INTO `meta_item_option` VALUES ('3', '平台公告', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:arrows-alt');
INSERT INTO `meta_item_option` VALUES ('4', '其它', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:arrows');
INSERT INTO `meta_item_option` VALUES ('5', '论坛', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:commenting-o');
INSERT INTO `meta_item_option` VALUES ('6', '公文', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:clone');
INSERT INTO `meta_item_option` VALUES ('7', '归档', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:check-square-o');
INSERT INTO `meta_item_option` VALUES ('8', '网站栏目', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'IT20210913IH-1', NULL, NULL, NULL, NULL, 'fa:amazon');
INSERT INTO `meta_item_option` VALUES ('9', '流程附件', '', '', '', '', '', '', '', 'IT20210913IH-1', 0, NULL, NULL, NULL, 'fa:file-word-o');
INSERT INTO `meta_item_option` VALUES ('0', '月度考核', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'jxSchemeType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '季度考核', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'jxSchemeType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '半年度考核', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'jxSchemeType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '年度考核', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'jxSchemeType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('sfkd', '顺丰快递', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'kdgs', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('ttkd', '天天快递', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'kdgs', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('ydkd', '韵达快递', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'kdgs', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('ytkd', '圆通快递', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'kdgs', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('ztkd', '中通快递', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'kdgs', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '直营', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'locationtype', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '加盟', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'locationtype', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '批发', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'locationtype', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '虚拟门店', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'locationtype', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('已婚', '已婚', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'marStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('未婚', '未婚', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'marStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('离异', '离异', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'marStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('个', '个', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'measureUnit', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('件', '件', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'measureUnit', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('克', '克', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'measureUnit', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('双', '双', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'measureUnit', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('吨', '吨', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'measureUnit', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('张', '张', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'measureUnit', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('组', '组', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'measureUnit', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('amountChange', '账户金额变动通知', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'msgTemplateType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('payNotify', '支付结果通知', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'msgTemplateType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('taskNotify', '任务状态变更通知', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'msgTemplateType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('壮族', '壮族', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nation', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('汉族', '汉族', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nation', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('满族', '满族', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nation', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('苗族', '苗族', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nation', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('蒙古族', '蒙古族', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'nation', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '否', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'needPhonenoToRegister', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '是', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'needPhonenoToRegister', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('agent', '代理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'orgType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('locationarea', '门店区管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'orgType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('locationcity', '门店城市管理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'orgType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('locationshop', '门店', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'orgType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('platformCompany', '平台归属公司', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'orgType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('shop', '加盟商户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'orgType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('中心总监', '中心总监', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'perCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('公司副总', '公司副总', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'perCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('总裁', '总裁', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'perCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('普通员工', '普通员工', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'perCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('部门经理', '部门经理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'perCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('m1', '月', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'planType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('q1', '季', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'planType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('q2', '半年', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'planType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('w1', '周', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'planType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('w2', '双周', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'planType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('w3', '三周', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'planType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('y1', '年', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'planType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('共产党员', '共产党员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'politicsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('共青团员', '共青团员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'politicsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('群众', '群众', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'politicsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('预备党员', '预备党员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'politicsStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '1级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'postLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '2级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'postLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '3级', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'postLvl', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '管理', NULL, NULL, NULL, NULL, 'rgba(183, 141, 89, 1)', NULL, NULL, 'postType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '技术', NULL, NULL, NULL, NULL, 'rgba(61, 163, 79, 1)', NULL, NULL, 'postType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '质量', 'zl', '', '', '', 'rgba(179, 42, 42, 1)', '', '', 'postType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '最高', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'priority', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '较高', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'priority', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '普通', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'priority', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '较低', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'priority', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '最低', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'priority', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('csz', '测试组', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('jgs', '架构师', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('jsjl', '技术经理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('kfz', '开发组', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('kh', '客户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('khjsjl', '客户技术经理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('khldz', '客户领导组', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('khxmjl', '客户项目经理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('nbldz', '内部领导组', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('nbxmjl', '内部项目经理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('qtz', '其他组', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('uat', 'uat测试', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xmzk', '项目主控领导', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xmzl', '项目助理', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xs', '销售', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xsjl', '销售经理/总监', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('yyz', '运营组', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectGroupType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '立项中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '实施中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '暂停中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '已结项', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '售后', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '已完成', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('bgyp', '办公用品', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectSubject', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('hdjf', '活动经费', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectSubject', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('htskrz', '合同收款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectSubject', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wgzfgz', '外购结算支付工资', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectSubject', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xmjj', '项目奖金', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectSubject', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('yggz', '员工工资', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectSubject', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '候选中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectTaskExecuserStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '执行中', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectTaskExecuserStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '放弃任务', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectTaskExecuserStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '黑名单', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectTaskExecuserStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '未结算', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectTaskSettleStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '已结算', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectTaskSettleStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '无需结算', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectTaskSettleStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '已付款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'projectTaskSettleStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '毕业设计项目', NULL, NULL, NULL, NULL, '#00ced1', NULL, NULL, 'projectType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '净水机项目', NULL, NULL, NULL, NULL, '#c71585', NULL, NULL, 'projectType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '饮水机项目', NULL, NULL, NULL, NULL, '#00ced1', '1', NULL, 'projectType', NULL, '2', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '机器人开发项目', NULL, NULL, NULL, NULL, 'rgba(255, 191, 0, 1)', NULL, NULL, 'projectType', 1, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '控制柜定制', NULL, NULL, NULL, NULL, 'rgba(64, 158, 255, 1)', NULL, NULL, 'projectType', 1, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '外部项目', '', '', '', '', '', '', '', 'projectType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('博士', '博士', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qualification', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('学士', '学士', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qualification', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('硕士', '硕士', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qualification', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('ac', '财务系统', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('im', '即时聊天系统', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('mallm', '商城后台', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('mk', '营销系统', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('oa', '智慧协同办公系统', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('sms', '短信系统', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('sys', '组织管理系统', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('vmai', '虚拟币', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wxpub', '第三方支付系统', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('xm', '项目管理平台', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'qxCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('c', '新增', NULL, NULL, NULL, NULL, 'rgba(64, 158, 255, 1)', NULL, NULL, 'qxType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('d', '删除', NULL, NULL, NULL, NULL, 'rgba(245, 108, 108, 1)', NULL, NULL, 'qxType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dm_alert', 'ddl-修改表结构', '', '', '', '', '', '', '', 'qxType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dm_create', 'ddl-建表', '', '', '', '', '', '', '', 'qxType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dm_delete', 'dml-删除数据', '', '', '', '', '', '', '', 'qxType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dm_drop', 'ddl-删表', '', '', '', '', '', '', '', 'qxType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dm_insert', 'dml-新增数据', '', '', '', '', '', '', '', 'qxType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dm_meta_query', 'ddl-元数据查询', '', '', '', '', '', '', '', 'qxType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dm_select', 'dml-查询数据', '', '', '', '', '', '', '', 'qxType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('dm_update', 'dml-修改数据', '', '', '', '', '', '', '', 'qxType', 0, '', NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('l', '数据范围', NULL, NULL, NULL, NULL, 'rgba(199, 21, 133, 0.46)', NULL, NULL, 'qxType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('r', '查询', NULL, NULL, NULL, NULL, 'rgba(103, 194, 58, 1)', NULL, NULL, 'qxType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('u', '更新', NULL, NULL, NULL, NULL, 'rgba(230, 162, 60, 1)', NULL, NULL, 'qxType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('一般', '一般', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'secrecy_level', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('机密', '机密', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'secrecy_level', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('秘密', '秘密', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'secrecy_level', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('绝密', '绝密', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'secrecy_level', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '7天退货', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'serviceGuarantee', NULL, NULL, NULL, NULL, 'ep:arrow-down-bold');
INSERT INTO `meta_item_option` VALUES ('1', '快速退款', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'serviceGuarantee', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('!=', '不等于', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 5, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('<', '小于', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 3, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('<=', '小于等于', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 4, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('=', '等于', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 6, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('>', '大于', 'SQL_OPER', NULL, NULL, NULL, '#909399', NULL, NULL, 'sql_link_and_sql_oper', 1, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('>=', '大于等于', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 2, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$BETWEEN', '区间内', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 7, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$IN', '包含', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 13, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$IS NOT NULL', '不为空', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 12, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$IS NULL', '为空', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 11, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$IS NULL_1', '为空', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 11, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$LIKE', '模糊匹配-两边', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 10, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$LIKE LEFT', '模糊匹配-左边', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 8, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$LIKE RIGHT', '模糊匹配-右边', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 9, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('$NOT IN', '不包含', 'SQL_OPER', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 14, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('AND', '与AND', 'SQL_LINK', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 15, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('OR', '或OR', 'SQL_LINK', NULL, NULL, NULL, NULL, NULL, NULL, 'sql_link_and_sql_oper', 16, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('临时', '临时', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'staffStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('实习', '实习', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'staffStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('正式', '正式', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'staffStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('离职', '离职', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'staffStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('解聘', '解聘', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'staffStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('试用', '试用', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'staffStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('试用延期', '试用延期', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'staffStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('退休', '退休', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'staffStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('storeaddress01', '123商城1号仓管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'storeaddress', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('storeaddress02', '123商城2号仓管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'storeaddress', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '售前', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '投标', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '需求', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '设计', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '测试', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '开发', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('7', '验收', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('8', '运维', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('9', '部署', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'taskType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('mdpApp', '平台授权应用', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'tpAppCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wxa', '微信小程序', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'tpAppCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('wxpub', '微信公众号', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'tpAppCategory', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '微信', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'tpAppPayType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '支付宝 ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'tpAppPayType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '银行', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'tpAppPayType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('6', '其他', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'tpAppPayType', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('FRIDAY', '周五', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'weekday', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('MONDAY', '周一', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'weekday', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('SATURDAY', '周六', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'weekday', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('SUNDAY', '周日', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'weekday', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('THURSDAY', '周四', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'weekday', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('TUESDAY', '周二', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'weekday', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('WEDNESDAY', '周三', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'weekday', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#2c9f67', '深绿色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#509fc9', '浅蓝色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#5885cf', '深蓝色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#63b359', '浅绿色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#9062c0', '紫色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#cc463d', '深红色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#d09a45', '浅棕色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#dd6549', '浅红色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#e4b138', '黄色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('#ee903c', '橘红色', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'wxcolor', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('0', '初始', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xmMenuPlanStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '正常', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xmMenuPlanStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '暂停', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xmMenuPlanStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '延误', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xmMenuPlanStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('4', '结束', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xmMenuPlanStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('5', '关闭', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xmMenuPlanStatus', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('1', '按工期延迟比率打折', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xmTaskSettleSchemel', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('2', '按报价结算不打折', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'xmTaskSettleSchemel', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `meta_item_option` VALUES ('3', '按工期提前或者延迟进行奖罚', NULL, '调的多3334444666', NULL, NULL, NULL, NULL, NULL, 'xmTaskSettleSchemel', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for meta_option_rule
-- ----------------------------
DROP TABLE IF EXISTS `meta_option_rule`;
CREATE TABLE `meta_option_rule`  (
  `from_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '开始选项编号',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '租户编号',
  `target_id` int NOT NULL COMMENT '目标选项编号',
  `rules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '规则列表-采用高级查询一样拼装条件作为规则树',
  PRIMARY KEY (`from_id`, `target_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '状态流转规则-未用' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of meta_option_rule
-- ----------------------------

-- ----------------------------
-- Table structure for meta_push_link
-- ----------------------------
DROP TABLE IF EXISTS `meta_push_link`;
CREATE TABLE `meta_push_link`  (
  `item_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL,
  `target_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '发布到目标编号',
  `ptime` datetime NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`item_id`, `target_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '发布关联，管理端客户管理界面只能看到发布到各自平台的字典' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of meta_push_link
-- ----------------------------
INSERT INTO `meta_push_link` VALUES ('4qqyfcq263', 'erp', '2021-09-14 17:09:08');
INSERT INTO `meta_push_link` VALUES ('55gxectc61', 'mall', '2021-09-14 17:31:46');

-- ----------------------------
-- Table structure for mo_order
-- ----------------------------
DROP TABLE IF EXISTS `mo_order`;
CREATE TABLE `mo_order`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '订单编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单名称',
  `obranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单机构号码',
  `ouserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单用户编号',
  `ousername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单用户名称',
  `mo_final_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '模块合计总金额=模块中的final_fee合计',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单',
  `sstatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算状态0-待结算，1-已结算',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '付款时间',
  `pay_ctime` datetime NULL DEFAULT NULL COMMENT '付款确认时间',
  `mo_orgin_fee` decimal(10, 0) NULL DEFAULT NULL COMMENT '折扣前总价=模块中orgin_fee合计',
  `ousers` int NULL DEFAULT NULL COMMENT '购买人数',
  `ocates` int NULL DEFAULT NULL COMMENT '购买分类数',
  `omodules` int NULL DEFAULT NULL COMMENT '购买模块数',
  `poid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级订单-如果是续费订单，记录原订单号',
  `start_time` datetime NULL DEFAULT NULL COMMENT '启用日期',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束日期',
  `pay_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '支付方式1-微信，2-支付宝，3-线下支付',
  `pay_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)',
  `prepay_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '预下单付款订单号（第三方返回）',
  `topen_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方账号编号',
  `pbank_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '收款银行编号(支付方式为3时必填)',
  `pbank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '收款银行名称(支付方式为3时必填)',
  `pbank_card_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '收款银行卡号(支付方式为3时必填)',
  `pbank_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '收款账户姓名(支付方式为3时必填)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `finish_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `close_time` datetime NULL DEFAULT NULL COMMENT '关闭时间',
  `set_time` datetime NULL DEFAULT NULL COMMENT '结算时间',
  `odays` int NULL DEFAULT NULL COMMENT '购买天数',
  `ofinal_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '最终订单价格、最终付款金额=mo_final_fee+oth_fee',
  `odis_rate` decimal(10, 4) NULL DEFAULT NULL COMMENT '订单折扣率(下单后后台根据不同客户进行折扣调整)=将在模块表中执行折扣操作，再从新合计价格',
  `oth_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '其它费用',
  `otype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单类型0-电商商城商品，1-应用模块使用购买，2-vip购买会员，3-购买服务保障',
  `osource` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单来源1-前端客户下单，2-后台待客下单',
  `mem_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '客户类型-参考sys_user表mem_type',
  `atype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '账户类型-根据sys_user表atype',
  `sale_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '销售经理编号',
  `sale_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '销售经理名称',
  `cust_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '客户联系电话',
  `cust_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '客户联系地址',
  `pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '最终付款金额-客户付款后回填',
  `obranch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单机构名称',
  `ooper` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单操作类型1-新购，2-续费，3-新增人数',
  `tran_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方付款事务号',
  `invoice` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否已开票',
  `invoice_time` datetime NULL DEFAULT NULL COMMENT '开票时间',
  `invoice_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mo_order
-- ----------------------------

-- ----------------------------
-- Table structure for mo_order_fligship
-- ----------------------------
DROP TABLE IF EXISTS `mo_order_fligship`;
CREATE TABLE `mo_order_fligship`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL,
  `contacts` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '联系人',
  `use_peoples` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '使用人数',
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '电话',
  `needs` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求',
  `obranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单机构',
  `ouserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单用户编号',
  `ousername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单用户名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '订单联系人' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mo_order_fligship
-- ----------------------------

-- ----------------------------
-- Table structure for mo_order_module
-- ----------------------------
DROP TABLE IF EXISTS `mo_order_module`;
CREATE TABLE `mo_order_module`  (
  `order_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '订单编号',
  `module_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '模块编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模块名称',
  `fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '模块费用',
  `bill_mode` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计费模式：0-不计费，1-按购买人数计费，2-总包费用,3-按实际使用人数每月计费',
  `uni_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '人均费用,单位人天',
  `discount` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '折扣比率，可折上折，叠加折扣。-按最大人数、按天数优惠discount:{days:1:100|2:80|3:60,userNum: 0-10:100|10-50:98|50-100:95}',
  `mcate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类(关联mo_cate) 1-协同、2-研发、3-电商',
  `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'logo地址',
  `fee_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '费用解释',
  `udis_rate` int NULL DEFAULT NULL COMMENT '人数折算比例。购买总人数 x 折扣率为当前模块购买人数',
  `udis` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否折算人数0否1是',
  `musers` int NULL DEFAULT NULL COMMENT '购买人数=订单表中购买总人数 x 人数折扣',
  `final_fee` decimal(10, 0) NULL DEFAULT NULL COMMENT '最终总费用=orgin_fee x dis_rate',
  `days` int NULL DEFAULT NULL COMMENT '购买天数',
  `orgin_fee` decimal(10, 0) NULL DEFAULT NULL COMMENT '原始总费用，未进行折扣方案前的总费用如果计费模式为1，则为uni_fee x musers x days单价 x 折扣方案天数折扣 x 折扣方案中人数折扣，如果计费模式为2，则为fee',
  `dis_rate` int NULL DEFAULT NULL COMMENT '订单折扣率（可能会根据客户类型后台改订单折扣，从新计算订单价格）',
  `ucheck` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否控制总人数0-否1-是',
  `ousers` int NULL DEFAULT NULL COMMENT '企业总人数=订单表中ousers',
  `ver` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '购买的版本0免费版，1企业版',
  PRIMARY KEY (`order_id`, `module_id`) USING BTREE,
  CONSTRAINT `mo_order_module_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `mo_order` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '订单与模块关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mo_order_module
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_registered_client
-- ----------------------------
DROP TABLE IF EXISTS `oauth_registered_client`;
CREATE TABLE `oauth_registered_client`  (
  `client_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `scopes` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `authorization_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `redirect_uris` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `require_proof_key` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `require_user_consent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `access_token_time_to_live` int NULL DEFAULT NULL,
  `enable_refresh_tokens` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `reuse_refresh_tokens` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `refresh_token_time_to_live` int NULL DEFAULT NULL,
  `authentication_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'basic-post-none',
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oauth_registered_client
-- ----------------------------
INSERT INTO `oauth_registered_client` VALUES ('accore', NULL, 'accore-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'accore', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('arc', NULL, 'arc-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'arc', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('audit', NULL, 'audit-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'audit', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('client-login', NULL, 'client-login-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', 'http://localhost:7002/authorized', NULL, '1', 'client-login', '0', '0', NULL, NULL, NULL, NULL, 'basic');
INSERT INTO `oauth_registered_client` VALUES ('form', NULL, 'form-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'form', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('im', NULL, 'im-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'im', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('mall', NULL, 'mall-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'mall', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('mallm', NULL, 'mallm-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'mallm', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('mdp', NULL, 'mdp-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'mdp', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('mk', NULL, 'mk-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'mk', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('oa', NULL, 'oa-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'oa', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('oauth2client', NULL, 'oauth2client-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'oauth2client', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('oauth2server', NULL, 'oauth2server-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'oauth2server', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('order-client', NULL, 'order-client-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'order-client', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('sms', NULL, 'sms-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'sms', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('sys', NULL, 'sys-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'sys', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('tpa', NULL, 'tpa-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'tpa', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('wechat', NULL, 'wechat-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'wechat', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('wechat-client-login', NULL, 'wechat-client-login-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'wechat-client-login', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('workflow', NULL, 'workflow-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'workflow', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `oauth_registered_client` VALUES ('xm', NULL, 'xm-8888', 'all', 'authorization_code,refresh_token,password,client_credentials', NULL, NULL, '1', 'xm', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for plat_bank_account
-- ----------------------------
DROP TABLE IF EXISTS `plat_bank_account`;
CREATE TABLE `plat_bank_account`  (
  `bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '银行名称',
  `bank_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '银行机构码',
  `card_account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '银行卡账户编号',
  `card_account_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '银行卡账户名称',
  `sub_bank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '银行分支机构名称',
  `sub_bank_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分支机构编码',
  `ctime` datetime NULL DEFAULT NULL COMMENT '新增时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `op_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作用户编号',
  `op_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '账户状态0无效1有效',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '账户性质0-平台收款',
  `platform_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '平台编号',
  `bank_img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '银行logo图片',
  PRIMARY KEY (`platform_id`, `card_account_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '平台收付款账户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plat_bank_account
-- ----------------------------
INSERT INTO `plat_bank_account` VALUES (NULL, NULL, '888', NULL, NULL, NULL, NULL, '2022-04-18 00:51:11', NULL, NULL, '1', '0', 'platform-001', NULL);

-- ----------------------------
-- Table structure for plat_platform
-- ----------------------------
DROP TABLE IF EXISTS `plat_platform`;
CREATE TABLE `plat_platform`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '平台编号建议为platform-001',
  `platform_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '平台名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '0-未启用，1-已启用，只能有一条数据有效',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `logo_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '平台logo图片地址',
  `platform_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前端显示的平台名称',
  `keywords` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关键词逗号分割',
  `min_buy_amount` int NULL DEFAULT NULL COMMENT '最小购买金额，达到此购物金额，才能提交订单',
  `auto_confirm_receipt` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '自动确认收货0否1是，确认收货时长请在订单设置中进行设置',
  `can_bill` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可开发票0否1是',
  `bill_context_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '发票内容选项,逗号分割',
  `cut_stock` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '扣减库存时机0-下单付款完成，1-发货后',
  `ext_config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '其它全局控制参数bill:填写 发票信息时是否必须填写备注0否1是,receipt:修改订单“收货人信息”时,order:修改订单“商品信息”时,orderFee:修改订单“费用信息”时,closeOrder:关闭订单时,cancelOrder:取消订单时,reGoods:退货处理时,reReceipt:退货确认收货时,rePay:退款处理时,cpoints:修改积分时,} ',
  `plus_sales` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '增加销量时机0-发货后，1-付款后',
  `evaluate` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评价是否需要审核0否1是',
  `discount_pct` int NULL DEFAULT NULL COMMENT '全局折扣0~100之间',
  `use_price_group` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否使用价格套0否1是',
  `shop_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '平台商户编号默认platform-shop-001',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '平台机构编号默认platform-branch-001',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL,
  `location_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '默认门店编号',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段[{name:中文名称,id:编号,value:值,remark:备注,type:支持简单的1-普通文本2-数字，3-日期，8-富文本，9单图文，15-是否}]',
  `free_max_users` int NULL DEFAULT NULL COMMENT '免费版用户数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of plat_platform
-- ----------------------------
INSERT INTO `plat_platform` VALUES ('platform-001', '唛盟项目云', '1', NULL, NULL, NULL, NULL, 'https://www.qingqinkj.com/api/m1/arc/arc/image/qqkj_001/IM1632611467940176/IM1633550409547158.png', '唛盟项目云', '唛盟项目云', 0, '1', '1', NULL, NULL, '{\"bill\":\"\",\"receipt\":\"0\",\"order\":\"\",\"orderFee\":\"\",\"closeOrder\":\"1\",\"cancelOrder\":\"\",\"reGoods\":\"\",\"reReceipt\":\"\",\"rePay\":\"\",\"cpoints\":\"\"}', NULL, '1', 1, NULL, 'platform-shop-001', 'platform-branch-001', '唛盟项目云', 'platform-shop-001-0', '[]', NULL);

-- ----------------------------
-- Table structure for platformbranch001_sys_user_ai
-- ----------------------------
DROP TABLE IF EXISTS `platformbranch001_sys_user_ai`;
CREATE TABLE `platformbranch001_sys_user_ai`  (
  `zero` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性',
  `one` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性',
  `two` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性',
  `three` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性',
  `eight` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性',
  `four` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性',
  `six` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性',
  `seven` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性',
  `five` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '测试' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of platformbranch001_sys_user_ai
-- ----------------------------

-- ----------------------------
-- Table structure for res_oper_data_trail
-- ----------------------------
DROP TABLE IF EXISTS `res_oper_data_trail`;
CREATE TABLE `res_oper_data_trail`  (
  `USERID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人',
  `START_DATE` datetime NULL DEFAULT NULL COMMENT '操作开始时间',
  `END_DATE` datetime NULL DEFAULT NULL COMMENT '操作结束时间',
  `FKEY` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关键字1',
  `SKEY` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关键字2',
  `TABLE_NAME` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '针对哪个表的操作',
  `FIELDS` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '针对哪些字段的操作',
  `REMARK` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '人性化描述',
  `ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `DEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '机构编号',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '云用户机构编号',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `SYS_OPER_DATA_TRAIL_FKEY`(`TABLE_NAME` ASC, `FKEY` ASC) USING BTREE,
  INDEX `SYS_OPER_DATA_TRAIL_STARTDATE`(`START_DATE` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '重要操作轨迹' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of res_oper_data_trail
-- ----------------------------

-- ----------------------------
-- Table structure for sys_branch
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch`;
CREATE TABLE `sys_branch`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构编号',
  `BRANCH_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `ENABLED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可用 0-不可用 1-可用 2-待续费',
  `INDUSTRY_CATEGORY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '行业分类',
  `CUSERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号-可以转让,创建人与机构管理员有同样的权限',
  `CDATE` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `CUSERNAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名-可以转让',
  `LPHONE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '联系电话',
  `EMAILL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邮件',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
  `pbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级机构',
  `adm_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '管理员编号（==机构编号，不允许修改，即机构主账户）',
  `adm_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '管理员名称（机构名称+管理员，不允许修改）',
  `lusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `luserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '联系人编号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '公司地址',
  `btype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构类别0-个人虚拟机构，1-实体机构，个人虚拟机构的话sys_branch表没有真正的机构数据',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '企业头像',
  `bcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '税号-统一信用识别号',
  `blicense` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '营业执照图片',
  `legal_person` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '法人名称',
  `reg_capital` decimal(20, 2) NULL DEFAULT NULL COMMENT '注册资本',
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '企业简介',
  `valid_lvls` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段验证级别列表逗号分割多个，0-验证不通过，1-验证通过，2待审核。按顺序位置分别代表1-实名（身份证），2-手机号码，3-邮箱，4-营业执照，5-法人实名1,2,3,4,5比如0,0,0,0,0所有验证都不通过。比如1,1,1,1,1所有验证通过，比如0,1,1,0,0代表实名身份证验证不通过，法人实名认证不通过比如0,0,0,1,2代表实名认证待审核，企业法人实名认证待审核',
  `brely_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考类型',
  `brely_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考编号',
  `brely_stype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考子类型',
  `brely_sid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考子编号',
  `claim_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申领状态，0未申领，1-已申领',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `SYS_BRANCH_I_BRANCHNAME`(`BRANCH_NAME` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '管理端机构表（机构下面若干部门）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_branch
-- ----------------------------
INSERT INTO `sys_branch` VALUES ('000-000-1', 'xxxxxx1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'user-00001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_branch` VALUES ('demo-branch-01', '唛盟平台官方演示机构', '1', '', 'superAdmin', NULL, '超级管理员', '', '', '', '', '', 'demo-branch-01', '唛盟平台官方演示机构管理员', '超级管理员', 'superAdmin', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_branch` VALUES ('platform-branch-001', '平台管理机构', '1', 'computersSoftware', 'vybe8882', NULL, '超级管理员', NULL, '', 'b1fe7243-72ae-11ea-9993-525400033a00', '1', NULL, '5', NULL, NULL, NULL, NULL, NULL, 'https://www.qingqinkj.com/api/m1/arc/arc/image/platform-branch-001/product/IM1634897746918172.png', NULL, NULL, NULL, NULL, '属于平台管理机构', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_branch_del_backup
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch_del_backup`;
CREATE TABLE `sys_branch_del_backup`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构编号',
  `BRANCH_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `ENABLED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可用',
  `INDUSTRY_CATEGORY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '行业分类',
  `CUSERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `CDATE` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `CUSERNAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `PHONE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '联系电话',
  `EMAILL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邮件',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
  `del_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `del_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-待处理1-已处理',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `SYS_BRANCH_I_BRANCHNAME`(`BRANCH_NAME` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '管理端机构表（机构下面若干部门）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_branch_del_backup
-- ----------------------------

-- ----------------------------
-- Table structure for sys_branch_interests
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch_interests`;
CREATE TABLE `sys_branch_interests`  (
  `branch_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司ID',
  `ilvl_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级ID',
  `ilvl_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `idesc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级描述',
  `ilevel` int NULL DEFAULT 1 COMMENT '1-粉丝,2- 注册会员,3-高级会员A,4-高级会员B,  5,6,7,8递增',
  `discount` int NULL DEFAULT 100 COMMENT '权益，折扣 默认100,0-100之间',
  `istatus` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '等级状态 0-已停用   ,1-正常,2-待续费',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级图标url',
  `is_free` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否付费获取权益 ,0:不付费 1:付费',
  `rtime_rule` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间叠加规则：1.有效期日期后叠加续会时间 2.达到续会之日叠加续会时间 3.永久有效',
  `rtime_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间类型：1.天数 2.月 3.年',
  `rtime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '下次续会时间yyyy-MM-dd型',
  `itype` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权益分类',
  `shop_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户编号',
  `inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `smax_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '单个任务最大金额（任务型用户才有）0代表不限制',
  `total_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '累计接单金额（任务型用户才有）0代表不限制',
  `mtype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用会员类型（2商户型、1普通型、3任务型）',
  `total_exp` decimal(20, 0) NULL DEFAULT NULL COMMENT '累计经验值0代表不限制',
  `smax_exp` decimal(20, 0) NULL DEFAULT NULL COMMENT '单个任务最大经验值0代表不限制',
  `bids` int NULL DEFAULT NULL COMMENT '投标次数上限',
  `sfee_rate` int NULL DEFAULT NULL COMMENT '服务费率0-100之间',
  `id_bak` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '覆盖上一条的等级编号，即变成当前等级之前的等级编号',
  `ctotal_exp` decimal(20, 2) NULL DEFAULT NULL COMMENT '累计完成工作量',
  `ctotal_at` decimal(50, 0) NULL DEFAULT NULL COMMENT '当前累计完成金额',
  `ctotal_bids` int NULL DEFAULT NULL COMMENT '累计投标总数',
  `mfee` decimal(20, 2) NULL DEFAULT NULL COMMENT '月均费用',
  `max_users` int NULL DEFAULT NULL COMMENT '最大人员数',
  `curr_users` int NULL DEFAULT NULL COMMENT '当前人员数',
  `max_rtime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品模块下次续费截止时间yyyy-MM-dd类型',
  `mver` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品版本0免费版1企业版',
  `guard_id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '诚信保障金等级金银铜，1-金，2-银，3-铜,0-初始，过期后归0',
  `srv_times` int NULL DEFAULT NULL COMMENT '累计服务次数',
  `guard_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务保障名称',
  `guard_rtime` datetime NULL DEFAULT NULL COMMENT '服务保障下次续费时间',
  `cmonth_bids` int NULL DEFAULT NULL COMMENT '当月投标数',
  `cmonth_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '当月金额',
  `cmonth_exp` int NULL DEFAULT NULL COMMENT '当月经验值',
  `ctotal_receive_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前收款总额',
  `csix_exp` int NULL DEFAULT NULL COMMENT '六个月经验分',
  `csix_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '六个月金额',
  `csix_bids` int NULL DEFAULT NULL COMMENT '六个月投标次数',
  `clear_time` datetime NULL DEFAULT NULL COMMENT '清除时间',
  PRIMARY KEY (`branch_id`) USING BTREE,
  INDEX `company_id`(`branch_id` ASC) USING BTREE,
  INDEX `grade_name`(`ilvl_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构权益关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_branch_interests
-- ----------------------------
INSERT INTO `sys_branch_interests` VALUES ('demo-branch-01', '1', '默认会员等级', '', 0, 100, '1', '2022-10-06 17:52:41', '2022-10-06 09:52:40', '', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5000.00, 1000000.00, '3', 1000000, 5000, 30, 15, NULL, 0.00, 0, 0, NULL, 10, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_branch_interests` VALUES ('platform-branch-001', '5', '紫金', '', 5, 100, '1', '2022-09-27 18:27:53', '2022-12-14 06:28:31', '', '0', NULL, NULL, NULL, '1', NULL, NULL, NULL, 800000.00, 1000000.00, '1', 1000000, 800000, 60000, 15, NULL, 0.00, 0, 1, 1000.00, 10, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_branch_process_approva
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch_process_approva`;
CREATE TABLE `sys_branch_process_approva`  (
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构编号',
  `proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `agree` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批状态同意1不同意0',
  `assignee` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批人',
  `main_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程标题',
  `act_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批节点编号',
  `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批环节',
  `comment_msg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批意见',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `event_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '事件类型create/assignment/complete/delete/PROCESS_CREATED/PROCESS_COMPLETE/PROCESS_CANCELLED',
  `biz_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键发起时上送，原样返回',
  `model_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程key，可以根据该key找到对应的流程模型也代表审批事项，就是审什么内容',
  `flow_last_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `flow_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程审批机构号',
  `flow_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `start_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '启动人',
  `proc_def_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程定义编号带版本的',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模型名称，也代表审批事项，就是审什么内容',
  `flow_end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `assignee_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '企业入驻审核流程' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_branch_process_approva
-- ----------------------------

-- ----------------------------
-- Table structure for sys_branch_svr
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch_svr`;
CREATE TABLE `sys_branch_svr`  (
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '企业编号',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '服务编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务简介',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '服务价格',
  `pimg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务主图',
  `times` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务时间范围',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0初始1上架2下架',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '企业服务列表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_branch_svr
-- ----------------------------

-- ----------------------------
-- Table structure for sys_credit
-- ----------------------------
DROP TABLE IF EXISTS `sys_credit`;
CREATE TABLE `sys_credit`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '信用等级编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '信用名称',
  `start_score` int NULL DEFAULT NULL COMMENT '开始信用分',
  `end_score` int NULL DEFAULT NULL COMMENT '结束信用分',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '信用等级描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户信用等级定义' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_credit
-- ----------------------------
INSERT INTO `sys_credit` VALUES ('A', 'A级', 50, 69, '值得信赖-可持续交易');
INSERT INTO `sys_credit` VALUES ('B', 'B级', 30, 49, '有保障-可安心交易');
INSERT INTO `sys_credit` VALUES ('C', 'C级', 10, 29, '已认证-可选择交易');
INSERT INTO `sys_credit` VALUES ('D', 'D级', 1, 9, '初始级-慎重交易');
INSERT INTO `sys_credit` VALUES ('E', 'E级', -10000, 0, '高风险-禁止交易');
INSERT INTO `sys_credit` VALUES ('S', 'S级', 70, 89, '官方推荐');
INSERT INTO `sys_credit` VALUES ('SS', 'SS级', 90, 99, '官方推荐');
INSERT INTO `sys_credit` VALUES ('SSS', 'SSS级', 100, 1000, '官方推荐');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `DEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '0' COMMENT '部门编号',
  `DEPT_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门全称',
  `PDEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '上级部门编号',
  `DEPT_TYPE` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考数据字典deptType',
  `STATE` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态A正常E无效',
  `MANAGER` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人编号',
  `LEADER` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级领导编号',
  `SHORT_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '简称',
  `DISPLAY_DEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编码外部使用',
  `ORG_TYPE` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考数据字典orgType',
  `MANAGER_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人名称',
  `LEADER_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级领导名称',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '云用户机构编号',
  `LEVEL_TYPE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '层级类型(科云：0国,1省,2市,3区县,4街道,5自然村)',
  `ID_PATH` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号路径',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
  `ltime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `is_cb_center` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '是否为成本中心0否1是',
  `cpa_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作类型 字典cpa_type',
  `cpa_branch_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作组织编号 ',
  `rely_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考类型1-项目团队，2-产品团队',
  `rely_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考编号 参考类型1时填项目编号，类型为2时填产品编号',
  `rely_stype` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考子类型',
  `rely_sid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考子编号',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`DEPTID`) USING BTREE,
  INDEX `SYS_DEPT_IDX_PDEPTID`(`PDEPTID` ASC) USING BTREE,
  INDEX `SYS_DEPT_FK_BRANCH`(`BRANCH_ID` ASC) USING BTREE,
  INDEX `idx_SYS_DEPT02`(`ID_PATH` ASC, `BRANCH_ID` ASC) USING BTREE,
  CONSTRAINT `SYS_DEPT_FK_BRANCH` FOREIGN KEY (`BRANCH_ID`) REFERENCES `sys_branch` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('4fr8gart8x', '平台管理机构-总部', 'A0', '1', 'A', '13622750298', '1234567', '平台管理机构-总部', '4fr8gct6f8', NULL, '郭大头', 'qingqin', 'platform-branch-001', 'L1', 'A0,4fr8gart8x,', '879a3abb-743d-11ea-acc5-525400033a00', '2', '2023-10-09 19:00:00', NULL, '9', 'platform-branch-001', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('DE1665049960457133', '唛盟平台官方演示机构-总部', 'A0', NULL, 'A', 'demo-branch-01', NULL, '唛盟平台官方演示机构-总部', 'DE1665049960457133', NULL, '唛盟平台官方演示机构管理员', NULL, 'demo-branch-01', 'L1', 'A0,DE1665049960457133,', NULL, NULL, '2023-10-09 19:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_dept` VALUES ('platform-dept-001', '平台默认部门', '4fr8gart8x', '1', 'A', '', '', '平台默认部门', 'platform-dept-001', '', '', '', 'platform-branch-001', 'L2', 'A0,4fr8gart8x,platform-dept-001,', NULL, NULL, '2023-10-09 19:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept_del_backup
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_del_backup`;
CREATE TABLE `sys_dept_del_backup`  (
  `DEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '0' COMMENT '部门编号',
  `DEPT_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门全称',
  `PDEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '上级部门编号',
  `DEPT_TYPE` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考数据字典deptType',
  `STATE` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态A正常E无效',
  `MANAGER` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人编号',
  `LEADER` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级领导编号',
  `SHORT_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '简称',
  `DISPLAY_DEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编码外部使用',
  `ORG_TYPE` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '参考数据字典orgType',
  `MANAGER_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人名称',
  `LEADER_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级领导名称',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '云用户机构编号',
  `LEVEL_TYPE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '层级类型(科云：0国,1省,2市,3区县,4街道,5自然村)',
  `ID_PATH` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号路径',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
  `del_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `del_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-待处理1-已处理',
  PRIMARY KEY (`DEPTID`) USING BTREE,
  INDEX `SYS_DEPT_IDX_PDEPTID`(`PDEPTID` ASC) USING BTREE,
  INDEX `SYS_DEPT_FK_BRANCH`(`BRANCH_ID` ASC) USING BTREE,
  INDEX `idx_SYS_DEPT02`(`ID_PATH` ASC, `BRANCH_ID` ASC) USING BTREE,
  CONSTRAINT `sys_dept_del_backup_ibfk_1` FOREIGN KEY (`BRANCH_ID`) REFERENCES `sys_branch` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept_del_backup
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept_location
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_location`;
CREATE TABLE `sys_dept_location`  (
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '部门地址编号',
  `LONGITUDE` decimal(10, 7) NULL DEFAULT NULL COMMENT '经度',
  `LATITUDE` decimal(10, 8) NULL DEFAULT NULL COMMENT '纬度',
  `ADDRESS` varchar(124) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '地址',
  `PROVINCE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省',
  `CITY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '市',
  `DISTRICT` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '区',
  PRIMARY KEY (`DEPTID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept_location
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_post`;
CREATE TABLE `sys_dept_post`  (
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '部门编号',
  `POST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '岗位编号',
  `LDATE` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`DEPTID`, `POST_ID`) USING BTREE,
  INDEX `SYS_DEPT_POST_DEPTID_FK`(`DEPTID` ASC) USING BTREE,
  INDEX `SYS_DEPT_POST_POSTID_FK`(`POST_ID` ASC) USING BTREE,
  CONSTRAINT `SYS_DEPT_POST_DEPTID_FK` FOREIGN KEY (`DEPTID`) REFERENCES `sys_dept` (`DEPTID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `SYS_DEPT_POST_POSTID_FK` FOREIGN KEY (`POST_ID`) REFERENCES `sys_post` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '部门岗位关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept_post
-- ----------------------------
INSERT INTO `sys_dept_post` VALUES ('4fr8gart8x', '4fr8httf48', NULL);
INSERT INTO `sys_dept_post` VALUES ('4fr8gart8x', 'projectManager', NULL);
INSERT INTO `sys_dept_post` VALUES ('4fr8gart8x', 'superAdmin', NULL);
INSERT INTO `sys_dept_post` VALUES ('DE1665049960457133', 'PO1665049960689182', '2022-10-06 17:52:41');
INSERT INTO `sys_dept_post` VALUES ('DE1665049960457133', 'PO1665049960689283', '2022-10-06 17:52:41');
INSERT INTO `sys_dept_post` VALUES ('DE1665049960457133', 'PO1665049960689332', '2022-10-06 17:52:41');
INSERT INTO `sys_dept_post` VALUES ('platform-dept-001', 'platform-user', NULL);

-- ----------------------------
-- Table structure for sys_dept_post_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_post_user`;
CREATE TABLE `sys_dept_post_user`  (
  `POST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '岗位编号',
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '部门编号',
  `USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `START_DATE` datetime NULL DEFAULT NULL COMMENT '开始任职时间',
  `END_DATE` datetime NULL DEFAULT NULL COMMENT '结束任职时间',
  `ACT_END_DATE` datetime NULL DEFAULT NULL COMMENT '实际结束任职时间',
  `ENABLED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0-无效1-有效',
  `LAST_DATE` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `master` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为主岗位0否1是',
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `UN_IDEX_SYS_DEPT_POST_USER`(`USERID` ASC, `DEPTID` ASC, `POST_ID` ASC) USING BTREE,
  INDEX `SYS_DEPT_POST_USER_DEPTID_FK`(`DEPTID` ASC) USING BTREE,
  INDEX `SYS_DEPT_POST_USER_POSTID_FK`(`POST_ID` ASC) USING BTREE,
  CONSTRAINT `SYS_DEPT_POST_USER_DEPTID_FK` FOREIGN KEY (`DEPTID`) REFERENCES `sys_dept` (`DEPTID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `SYS_DEPT_POST_USER_POSTID_FK` FOREIGN KEY (`POST_ID`) REFERENCES `sys_post` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `SYS_DEPT_POST_USER_USERID_FK` FOREIGN KEY (`USERID`) REFERENCES `sys_user` (`USERID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '部门岗位用户关系表（根据部门岗位关系,岗位用户关系自动冗余）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept_post_user
-- ----------------------------
INSERT INTO `sys_dept_post_user` VALUES ('PO1665049960689182', 'DE1665049960457133', 'demo-branch-01', '2022-10-06 17:52:41', NULL, NULL, '1', '2022-10-06 17:52:41', NULL, 'DE1665049960718145');
INSERT INTO `sys_dept_post_user` VALUES ('PO1665049960689283', 'DE1665049960457133', 'demo-branch-01', '2022-10-06 17:52:41', NULL, NULL, '1', '2022-10-06 17:52:41', NULL, 'DE1665049960718239');
INSERT INTO `sys_dept_post_user` VALUES ('PO1665049960689332', 'DE1665049960457133', 'demo-branch-01', '2022-10-06 17:52:41', NULL, NULL, '1', '2022-10-06 17:52:41', NULL, 'DE1665049960718381');

-- ----------------------------
-- Table structure for sys_dept_process_approva
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_process_approva`;
CREATE TABLE `sys_dept_process_approva`  (
  `deptid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '部门编号',
  `proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `agree` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批状态同意1不同意0',
  `assignee` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批人',
  `main_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程标题',
  `act_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批节点编号',
  `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批环节',
  `comment_msg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批意见',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `event_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '事件类型create/assignment/complete/delete/PROCESS_CREATED/PROCESS_COMPLETE/PROCESS_CANCELLED',
  `biz_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键发起时上送，原样返回',
  `model_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程key，可以根据该key找到对应的流程模型也代表审批事项，就是审什么内容',
  `flow_last_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `flow_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程审批机构号',
  `flow_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `start_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '启动人',
  `proc_def_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程定义编号带版本的',
  `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模型名称，也代表审批事项，就是审什么内容',
  `flow_end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `assignee_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行人',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '部门归属机构号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '企业入驻审核流程' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept_process_approva
-- ----------------------------

-- ----------------------------
-- Table structure for sys_guard
-- ----------------------------
DROP TABLE IF EXISTS `sys_guard`;
CREATE TABLE `sys_guard`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '名称',
  `fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `day_limit` int NULL DEFAULT NULL COMMENT '期限,单位天数，360天，付款成功当天算起',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '服务保障定义表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_guard
-- ----------------------------
INSERT INTO `sys_guard` VALUES ('1', '金牌', 3.00, NULL, 360);
INSERT INTO `sys_guard` VALUES ('2', '银牌', 2.00, NULL, 360);
INSERT INTO `sys_guard` VALUES ('3', '铜牌', 1.00, NULL, 360);

-- ----------------------------
-- Table structure for sys_guard_damage
-- ----------------------------
DROP TABLE IF EXISTS `sys_guard_damage`;
CREATE TABLE `sys_guard_damage`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `pay_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `pay_at` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '赔付金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原因',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '赔付时间',
  `biz_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务编号',
  `biz_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务类型，随意暂时1-代表任务',
  `to_userid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '赔付对象用户编号',
  `to_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '赔付对象姓名',
  `pay_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '付款人姓名',
  `pay_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '保障金赔付表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_guard_damage
-- ----------------------------

-- ----------------------------
-- Table structure for sys_guard_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_guard_order`;
CREATE TABLE `sys_guard_order`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '订单编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单名称',
  `obranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单机构号码',
  `ouserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单用户编号',
  `ousername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单用户名称',
  `final_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格=orign_fee x odis_rate',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单',
  `sstatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算状态0-待结算，1-已结算',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '付款时间',
  `pay_ctime` datetime NULL DEFAULT NULL COMMENT '付款确认时间',
  `orgin_fee` decimal(10, 0) NULL DEFAULT NULL COMMENT '折扣前总价=sys_guard中价格',
  `poid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级订单-如果是续费订单，记录原订单号',
  `start_time` datetime NULL DEFAULT NULL COMMENT '启用日期',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束日期',
  `pay_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '支付方式1-微信，2-支付宝，3-线下支付',
  `pay_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)',
  `prepay_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '预下单付款订单号（第三方返回）',
  `topen_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方账号编号',
  `pbank_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '收款银行编号(支付方式为3时必填)',
  `pbank_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '收款银行名称(支付方式为3时必填)',
  `pbank_card_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '收款银行卡号(支付方式为3时必填)',
  `pbank_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '收款账户姓名(支付方式为3时必填)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `finish_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `close_time` datetime NULL DEFAULT NULL COMMENT '关闭时间',
  `set_time` datetime NULL DEFAULT NULL COMMENT '结算时间',
  `ofinal_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '最终订单价格、最终付款金额=final_fee+oth_fee',
  `odis_rate` int NULL DEFAULT NULL COMMENT '订单折扣率(下单后后台根据不同客户进行折扣调整)=将在模块表中执行折扣操作，再从新合计价格',
  `oth_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '其它费用',
  `otype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单类型0-电商商城商品，1-应用模块使用购买，2-vip购买会员，3-任务相关的保证金、佣金、分享赚佣金、加急热搜金额等、4-服务商付款服务保障押金',
  `osource` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单来源1-前端客户下单，2-后台待客下单',
  `mem_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '客户类型-参考sys_user表mem_type',
  `atype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '账户类型-根据sys_user表atype',
  `sale_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '销售经理编号',
  `sale_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '销售经理名称',
  `cust_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '客户联系电话',
  `cust_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '客户联系地址',
  `pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '最终付款金额-客户付款后回填',
  `obranch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下单机构名称',
  `ooper` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '订单操作类型1-新购，2-续费',
  `tran_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方付款事务号',
  `invoice` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否已开票',
  `invoice_time` datetime NULL DEFAULT NULL COMMENT '开票时间',
  `invoice_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票编号',
  `guard_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务保障编号',
  `guard_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务保障名称',
  `pay_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '付款状态0-未付款，1-已付款',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '购买服务保障订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_guard_order
-- ----------------------------

-- ----------------------------
-- Table structure for sys_interests
-- ----------------------------
DROP TABLE IF EXISTS `sys_interests`;
CREATE TABLE `sys_interests`  (
  `ilvl_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级ID',
  `ilvl_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级名称',
  `idesc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级描述',
  `branch_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司ID',
  `ilevel` int NULL DEFAULT 1 COMMENT '1-粉丝,2- 注册会员,3-高级会员A,4-高级会员B,  5,6,7,8递增',
  `istatus` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '等级状态 0-已停用   ,1-正常',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级图标url',
  `is_free` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否付费获取权益 ,0:不付费 1:付费',
  `rtime_rule` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间叠加规则：1.有效期日期后叠加续会时间 2.达到续会之日叠加续会时间 3.永久有效',
  `rtime_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间类型：1.天数 2.月 3.年',
  `rtime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间yyyy-MM-dd型',
  `itype` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权益分类(1-唛盟众包,2-电商平台)',
  `shop_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户编号',
  `inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `smax_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '单个任务最大金额（任务型用户才有）0代表不限制',
  `total_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '累计接单金额（任务型用户才有）0代表不限制',
  `mtype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用会员类型（2商户型、1普通型、3任务型）',
  `total_exp` decimal(20, 2) NULL DEFAULT NULL COMMENT '累计经验值0代表不限制',
  `smax_exp` decimal(20, 2) NULL DEFAULT NULL COMMENT '单个任务最大经验值0代表不限制',
  `bids` int NULL DEFAULT NULL COMMENT '投标次数上限',
  `sfee_rate` int NULL DEFAULT NULL COMMENT '服务费率0-100之间',
  `mfee` decimal(10, 2) NULL DEFAULT NULL COMMENT '每个月费用',
  `discount` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '折扣比率， 按月数优惠discount:{months: 1:100,2:80,3:60,}',
  PRIMARY KEY (`ilvl_id`) USING BTREE,
  INDEX `company_id`(`branch_id` ASC) USING BTREE,
  INDEX `grade_name`(`ilvl_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员权益表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_interests
-- ----------------------------
INSERT INTO `sys_interests` VALUES ('1', '普通', '', 'platform-branch-001', 1, '1', NULL, NULL, NULL, '1', NULL, NULL, NULL, '1', NULL, NULL, NULL, 2000.00, NULL, '1', NULL, 2000.00, 2, 0, 2.00, NULL);
INSERT INTO `sys_interests` VALUES ('2', '青铜', '', 'platform-branch-001', 2, '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, '1', NULL, NULL, NULL, 20000.00, NULL, '1', NULL, 20000.00, 100, 0, 0.01, NULL);
INSERT INTO `sys_interests` VALUES ('3', '白银', '', 'platform-branch-001', 3, '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, '1', NULL, NULL, NULL, 200000.00, NULL, '1', NULL, 200000.00, 10000, 0, 600.00, NULL);
INSERT INTO `sys_interests` VALUES ('4', '黄金', '', 'platform-branch-001', 4, '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, '1', NULL, NULL, NULL, 400000.00, NULL, '1', NULL, 400000.00, 20000, 0, 800.00, NULL);
INSERT INTO `sys_interests` VALUES ('5', '紫金', '', 'platform-branch-001', 5, '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, '1', NULL, NULL, NULL, 800000.00, NULL, '1', NULL, 800000.00, 60000, 0, 1000.00, NULL);
INSERT INTO `sys_interests` VALUES ('6', '皇冠', NULL, 'platform-branch-001', 6, '1', NULL, NULL, NULL, '0', '', '', '', '1', NULL, NULL, NULL, 1000000.00, NULL, '1', NULL, 1000000.00, 80000, 0, 3800.00, '');

-- ----------------------------
-- Table structure for sys_interests_orders
-- ----------------------------
DROP TABLE IF EXISTS `sys_interests_orders`;
CREATE TABLE `sys_interests_orders`  (
  `userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `ilvl_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级ID',
  `ilvl_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `idesc` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级描述',
  `branch_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司ID-下单客户对应的企业',
  `ilevel` int NULL DEFAULT 1 COMMENT '1-粉丝,2- 注册会员,3-高级会员A,4-高级会员B,  5,6,7,8递增',
  `discount` int NULL DEFAULT NULL COMMENT '权益，折扣 默认100,0-100之间',
  `istatus` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '等级状态 0-已停用   ,1-正常',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pic_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级图标url',
  `is_free` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否付费获取权益 ,0:不付费 1:付费',
  `rtime_rule` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间叠加规则：1.有效期日期后叠加续会时间 2.达到续会之日叠加续会时间 3.永久有效',
  `rtime_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间类型：1.天数 2.月 3.年',
  `rtime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间yyyy-MM-dd型',
  `itype` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权益分类',
  `shop_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户编号',
  `inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `smax_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '单个任务最大金额（任务型用户才有）0代表不限制',
  `total_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '累计接单金额（任务型用户才有）0代表不限制',
  `mtype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用会员类型（2商户型、1普通型、3任务型）',
  `total_exp` decimal(20, 0) NULL DEFAULT NULL COMMENT '累计经验值0代表不限制',
  `smax_exp` decimal(20, 0) NULL DEFAULT NULL COMMENT '单个任务最大经验值0代表不限制',
  `bids` int NULL DEFAULT NULL COMMENT '投标次数上限',
  `sfee_rate` int NULL DEFAULT NULL COMMENT '服务费率0-100之间',
  `id_bak` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '覆盖上一条的等级编号，即变成当前等级之前的等级编号',
  `pay_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `pay_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付状态0待付款，1已付款',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `prepay_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方支付订单编号',
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `final_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '最终总费用=mfee x  months  x  discount/100+oth_fee',
  `mfee` decimal(10, 2) NULL DEFAULT NULL COMMENT '月均费用',
  `oth_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '其它费用',
  `months` int NULL DEFAULT NULL COMMENT '购买月数',
  `origin_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '原始价格=mfee x months',
  `pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '最终付款金额-客户付款后回填',
  `ver` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本1-个人版，2-企业版',
  `pay_auth_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付授权码',
  `pay_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付账户对应的第三方openid,注意，下单根付款不一定是同一个人',
  `pay_userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款用户编号',
  `pay_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款用户名称',
  `invoice` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否已开票',
  `invoice_time` datetime NULL DEFAULT NULL COMMENT '开票时间',
  `invoice_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票编号',
  `pay_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)',
  `otye` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单类型，此处填2代表会员等级订单',
  `tran_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方付款事务号（付款通知中返回）',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单说明',
  `ooper` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单操作类型1-新购，2-续费，3-升级',
  `calc_state` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单处理状态1-立即生效(新购、升级时可以立即生效、续费而且会员等级前后一致情况下可以立即生效)，2-到期定时任务处理（一般是续费并且续费等级不一致情况下需要等上一个等级过期再处理）；',
  `calc_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1-已执行（立即生效的默认已执行，定时任务的定时任务执行完后更新为一已执行），2-待执行',
  `calc_exec_time` datetime NULL DEFAULT NULL COMMENT '执行时间',
  `old_rtime` datetime NULL DEFAULT NULL COMMENT '上一会员等级的待续费时间yyyy-MM-dd型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `company_id`(`branch_id` ASC) USING BTREE,
  INDEX `grade_name`(`ilvl_name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会员购买权益关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_interests_orders
-- ----------------------------

-- ----------------------------
-- Table structure for sys_no
-- ----------------------------
DROP TABLE IF EXISTS `sys_no`;
CREATE TABLE `sys_no`  (
  `NOID` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '序列号编号',
  `PREFIX` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前缀',
  `POSTFIX` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '后缀',
  `NEXTID` decimal(20, 0) NULL DEFAULT NULL COMMENT '下一个序号数字',
  `NO_NAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '序列号名称',
  `NO_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '类型1不复位、2按年复位、3按月复位、4按日复位',
  `NO_LENGTH` decimal(4, 0) NULL DEFAULT NULL COMMENT '长度0表示不自动补齐位数',
  `YEAR` decimal(4, 0) NOT NULL COMMENT '年度',
  `MONTH` decimal(2, 0) NOT NULL COMMENT '月份',
  `DAY` decimal(2, 0) NOT NULL COMMENT '日',
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '主键',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '云用户机构编号',
  PRIMARY KEY (`NOID`, `YEAR`, `MONTH`, `DAY`) USING BTREE,
  INDEX `IDX_NOID`(`NOID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '序列号定义' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_no
-- ----------------------------
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_null', '', '', 3, '凭证号按月复位', '3', 0, 2019, 12, 0, '43mmt84mk', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_platform-branch-001_5ijk1rben9_付', '', '', 2, '凭证号按月复位', '3', 0, 2021, 4, 0, '6wkib58qg', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_platform-branch-001_5ijk1rben9_收', '', '', 3, '凭证号按月复位', '3', 0, 2021, 3, 0, '6vuyw4941', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_platform-branch-001_5ijk1rben9_收', '', '', 2, '凭证号按月复位', '3', 0, 2021, 4, 0, '6wkbf7ab2', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_platform-branch-001_5ikwv5c145_收', '', '', 4, '凭证号按月复位', '3', 0, 2021, 3, 0, '6vwee69ns', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_platform-branch-001_5ikwv5c145_收', '', '', 20, '凭证号按月复位', '3', 0, 2021, 4, 0, '6vwqh8qe9', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_付', '', '', 2, '凭证号按月复位', '3', 0, 2021, 3, 0, '6vcvt3cz4', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_付', '', '', 2, '凭证号按月复位', '3', 0, 2021, 4, 0, '6v7j396yq', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 115, '凭证号按月复位', '3', 0, 2019, 6, 0, '2xxb2g5cv', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 81, '凭证号按月复位', '3', 0, 2019, 7, 0, '38ftkdg8t', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 62, '凭证号按月复位', '3', 0, 2019, 8, 0, '3ib3thpfp', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 107, '凭证号按月复位', '3', 0, 2019, 9, 0, '3ubd9k5t3', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 370, '凭证号按月复位', '3', 0, 2019, 10, 0, '3epn18s43', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 36, '凭证号按月复位', '3', 0, 2019, 12, 0, '43nnnqja8', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 2, '凭证号按月复位', '3', 0, 2020, 4, 0, '4puwbb1g8', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 2, '凭证号按月复位', '3', 0, 2021, 3, 0, '6vcvi8fb9', NULL);
INSERT INTO `sys_no` VALUES ('voucher_VoucherId_收', '', '', 17, '凭证号按月复位', '3', 0, 2021, 4, 0, '6usmdn178', NULL);
INSERT INTO `sys_no` VALUES ('voucher_YearSeqNo_null', '', '', 3, '凭证顺序号按年复位', '2', 0, 2019, 0, 0, '43mmt4bb3', NULL);
INSERT INTO `sys_no` VALUES ('voucher_YearSeqNo_platform-branch-001__5ijk1rben9_付', '', '', 2, '凭证顺序号按年复位', '2', 0, 2021, 0, 0, '6wkibca53', NULL);
INSERT INTO `sys_no` VALUES ('voucher_YearSeqNo_platform-branch-001__5ijk1rben9_收', '', '', 4, '凭证顺序号按年复位', '2', 0, 2021, 0, 0, '6vuywgv19', NULL);
INSERT INTO `sys_no` VALUES ('voucher_YearSeqNo_platform-branch-001__5ikwv5c145_收', '', '', 23, '凭证顺序号按年复位', '2', 0, 2021, 0, 0, '6vwqh44s4', NULL);
INSERT INTO `sys_no` VALUES ('voucher_YearSeqNo_付', '', '', 3, '凭证顺序号按年复位', '2', 0, 2021, 0, 0, '6v7j3ujj6', NULL);
INSERT INTO `sys_no` VALUES ('voucher_YearSeqNo_收', '', '', 765, '凭证顺序号按年复位', '2', 0, 2019, 0, 0, '2xxb27v92', NULL);
INSERT INTO `sys_no` VALUES ('voucher_YearSeqNo_收', '', '', 2, '凭证顺序号按年复位', '2', 0, 2020, 0, 0, '4puwb3p82', NULL);
INSERT INTO `sys_no` VALUES ('voucher_YearSeqNo_收', '', '', 18, '凭证顺序号按年复位', '2', 0, 2021, 0, 0, '6usmdd3p5', NULL);
INSERT INTO `sys_no` VALUES ('word_num', '', '', 73, '档案字号', '1', 4, 0, 0, 0, '4rnvk2z16', NULL);

-- ----------------------------
-- Table structure for sys_notify_msg
-- ----------------------------
DROP TABLE IF EXISTS `sys_notify_msg`;
CREATE TABLE `sys_notify_msg`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '消息编号',
  `send_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人id',
  `send_username` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人名字',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `obj_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代-6/团队-7/关注用户-8/点赞文章-9/评论文章-10/收藏文章-11/12-用户注销/13-商务合作',
  `msg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注-完整描述',
  `glo_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '全局根踪号，用于跟踪日志',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键编号',
  `pbiz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号',
  `biz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象名称',
  `to_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '接收用户编号',
  `to_username` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '接收用户名称',
  `had_read` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否已读',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '跳转地址',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_id`(`biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `biz_id_2`(`pbiz_id` ASC, `biz_id` ASC) USING BTREE,
  INDEX `biz_id`(`biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `oper_time`(`oper_time` ASC) USING BTREE,
  INDEX `branch_id`(`branch_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '个人消息通知' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notify_msg
-- ----------------------------
INSERT INTO `sys_notify_msg` VALUES ('NO1713233643193164', 'demo-branch-01', '测试', '2024-04-16 10:14:01', NULL, '您成为需求【445VV】的负责人，请跟进需求！', '202404161014031597DE84R', 'demo-branch-01', '218.84.107.125', NULL, NULL, NULL, 'demo-branch-01', '唛盟平台公开演示账户2', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713233644970156', 'demo-branch-01', '测试', '2024-04-16 10:14:03', NULL, '您成为需求【445VVV】的负责人，请跟进需求！', '20240416101404947HUS3QF', 'demo-branch-01', '218.84.107.125', NULL, NULL, NULL, 'demo-branch-01', '唛盟平台公开演示账户2', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713248704461118', 'demo-branch-01', '测试', '2024-04-16 14:25:03', NULL, '您成为产品【测试】的产品经理，请及时跟进。', '2024041614250444431J2Z7', 'demo-branch-01', '119.85.101.91', NULL, NULL, NULL, 'demo-branch-01', '测试', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713260946035113', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-16 17:49:04', NULL, '21物联网工程1班王朝兴离开任务【T3TSIBNA7STV-UI设计-知识产权页-个人中心-我的订单】！', '20240416174905996NM9ZA5', 'hzxy-001', '115.46.247.28', NULL, NULL, NULL, 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713261004067197', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-16 17:50:02', NULL, '21物联网工程1班张泳春离开任务【T3TBYE4325J1-个人中心-订单查询-版权业务】！', '202404161750040265H3M9T', 'hzxy-001', '115.46.247.28', NULL, NULL, NULL, 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713360364001182', 'M_U3TZTFVT117', '', '2024-04-17 21:26:03', NULL, '您成为项目【111】的项目经理，请及时跟进。', '20240417212603978336Y2R', 'M_U3TZTFVT117', '183.158.10.59', NULL, NULL, NULL, 'M_U3TZTFVT117', NULL, '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363319528156', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-17 22:15:19', NULL, '您已离开任务【T3TBABFT1MX8-null】！', '20240417221519505881AC2', 'hzxy-001', '115.46.142.165', NULL, NULL, NULL, 'M_U3MJHVRA582', '21物联网工程1班张泳春', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363344128144', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-17 22:15:43', NULL, '您已离开任务【T3TBABFT1TFI-null】！', '2024041722154409436WX19', 'hzxy-001', '115.46.142.165', NULL, NULL, NULL, 'M_U3MJHVRA582', '21物联网工程1班张泳春', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363360887139', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-17 22:16:00', NULL, '您已离开任务【T3TBABFT9822-null】！', '2024041722160086385AZ26', 'hzxy-001', '115.46.142.165', NULL, NULL, NULL, 'M_U3MJHVRA582', '21物联网工程1班张泳春', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363378471175', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-17 22:16:17', NULL, '您已离开任务【T3TBYE4332AU-null】！', '202404172216184516GX1HA', 'hzxy-001', '115.46.142.165', NULL, NULL, NULL, 'M_U3MJHVRA582', '21物联网工程1班张泳春', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363494873131', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-17 22:18:14', NULL, '您成为任务【T3TZGPBZVX3F-null】的执行人，请及时跟进任务！', '20240417221814825Y22H1T', 'hzxy-001', '115.46.142.165', NULL, NULL, NULL, 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363686499155', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-17 22:21:25', NULL, '您已离开任务【T3TZGPBZ7J8Y-null】！', '202404172221264776KHB19', 'hzxy-001', '115.46.142.165', NULL, NULL, NULL, 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363702310164', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-17 22:21:41', NULL, '您已离开任务【T3TZGPBZKDAV-null】！', '20240417222142288XB888E', 'hzxy-001', '115.46.142.165', NULL, NULL, NULL, 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363719632173', 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '2024-04-17 22:21:59', NULL, '您已离开任务【T3TZGPBZX638-null】！', '20240417222159616R6K3NT', 'hzxy-001', '115.46.142.165', NULL, NULL, NULL, 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713363942712122', 'M_U3MJHVRA582', '21物联网工程1班张泳春', '2024-04-17 22:25:42', NULL, '用户【21物联网工程1班张泳春】投标任务【UI设计-知识产权页-科技项目】，请及时跟进！', '202404172225426503P7N15', 'hzxy-001', '115.46.208.0', NULL, NULL, NULL, 'M_U3MECZ2Z195', '21物联网工程1班王朝兴', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713402787813182', 'demo-branch-01', '唛盟平台官方演示机构', '2024-04-18 09:13:08', NULL, '您成为项目【测试项目】的项目经理，请及时跟进。', '2024041809130774648R62W', 'demo-branch-01', '127.0.0.1', NULL, NULL, NULL, 'demo-branch-01', '唛盟平台官方演示机构', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713405232621189', 'demo-branch-01', '唛盟平台官方演示机构', '2024-04-18 09:53:53', NULL, '您成为项目【测试项目】的项目经理，请及时跟进。', '20240418095348513171SAB', 'demo-branch-01', '127.0.0.1', NULL, NULL, NULL, 'demo-branch-01', '唛盟平台官方演示机构', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713408942037118', 'demo-branch-01', '唛盟平台官方演示机构', '2024-04-18 10:55:41', NULL, '您成为产品【1111】的产品经理，请及时跟进。', '20240418105541859TAVD8V', 'demo-branch-01', '127.0.0.1', NULL, NULL, NULL, 'demo-branch-01', '唛盟平台官方演示机构', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713412819414148', 'demo-branch-01', '测试', '2024-04-18 12:00:18', NULL, '您成为项目【项目1】的项目经理，请及时跟进。', '20240418120019354K489ED', 'demo-branch-01', '153.3.126.192', NULL, NULL, NULL, 'demo-branch-01', '测试', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713412833453199', 'demo-branch-01', '测试', '2024-04-18 12:00:32', NULL, '您成为项目【项目1】的项目经理，请及时跟进。', '202404181200334288H7684', 'demo-branch-01', '153.3.126.192', NULL, NULL, NULL, 'demo-branch-01', '测试', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713417157765174', 'demo-branch-01', '测试', '2024-04-18 13:12:37', NULL, '您成为需求【445V】的负责人，请跟进需求！', '20240418131237732WNJ98T', 'demo-branch-01', '123.168.72.215', NULL, NULL, NULL, 'demo-branch-01', '唛盟平台公开演示账户2', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713421075518111', 'demo-branch-01', '唛盟平台官方演示机构', '2024-04-18 14:17:55', NULL, '您成为项目【测试】的项目经理，请及时跟进。', '202404181417554522WJY37', 'demo-branch-01', '127.0.0.1', NULL, NULL, NULL, 'demo-branch-01', '唛盟平台官方演示机构', '0', NULL);
INSERT INTO `sys_notify_msg` VALUES ('NO1713421671165126', 'demo-branch-01', '唛盟平台官方演示机构', '2024-04-18 14:27:50', NULL, '您成为产品【测试产品】的产品经理，请及时跟进。', '20240418142751046DYP1MJ', 'demo-branch-01', '127.0.0.1', NULL, NULL, NULL, 'demo-branch-01', '唛盟平台官方演示机构', '0', NULL);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `POST_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `REMARK` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属机构号',
  `CDATE` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `POST_LVL` int NULL DEFAULT NULL COMMENT '岗位级别引用字典',
  `POST_TYPE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '岗位类型引用字典',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '岗位管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('4fr8h798b2', '平台管理机构门店管理岗', NULL, 'platform-branch-001', NULL, 1, '1');
INSERT INTO `sys_post` VALUES ('4fr8h89h67', '平台管理机构商户管理岗', NULL, 'platform-branch-001', NULL, 1, '1');
INSERT INTO `sys_post` VALUES ('4fr8httf48', '平台管理机构机构管理岗', NULL, 'platform-branch-001', NULL, 1, '1');
INSERT INTO `sys_post` VALUES ('platform-user', '默认用户', '默认用户', 'platform-branch-001', NULL, 1, '1');
INSERT INTO `sys_post` VALUES ('PO1631863488418158', '处长', '教学处处长', 'platform-branch-001', NULL, 1, '1');
INSERT INTO `sys_post` VALUES ('PO1632557862017152', '办公室主任', '', 'platform-branch-001', NULL, 1, '1');
INSERT INTO `sys_post` VALUES ('PO1632560803161165', '语合中心', '', 'platform-branch-001', NULL, 1, '1');
INSERT INTO `sys_post` VALUES ('PO1665049960689182', '部门经理', NULL, 'demo-branch-01', '2022-10-06 17:52:41', 1, '1');
INSERT INTO `sys_post` VALUES ('PO1665049960689283', '人力资源-招聘专员', NULL, 'demo-branch-01', '2022-10-06 17:52:41', 1, '1');
INSERT INTO `sys_post` VALUES ('PO1665049960689332', '总经理', NULL, 'demo-branch-01', '2022-10-06 17:52:41', 1, '1');
INSERT INTO `sys_post` VALUES ('projectManager', '项目经理', '项目经理', 'platform-branch-001', NULL, 1, '2');
INSERT INTO `sys_post` VALUES ('qqkj_test_adm', '测试经理', '测试经理', 'platform-branch-001', NULL, 1, '2');
INSERT INTO `sys_post` VALUES ('superAdmin', '超级管理员岗位', '超级管理员岗位', 'platform-branch-001', NULL, 1, '1');

-- ----------------------------
-- Table structure for sys_post_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_post_role`;
CREATE TABLE `sys_post_role`  (
  `POST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '岗位编号',
  `ROLEID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`POST_ID`, `ROLEID`) USING BTREE,
  INDEX `SYS_POST_POSTID_FK`(`POST_ID` ASC) USING BTREE,
  INDEX `SYS_POST_ROLEID_FK`(`ROLEID` ASC) USING BTREE,
  CONSTRAINT `SYS_POST_POSTID_FK` FOREIGN KEY (`POST_ID`) REFERENCES `sys_post` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `SYS_POST_ROLEID_FK` FOREIGN KEY (`ROLEID`) REFERENCES `sys_role` (`ROLEID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '岗位角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_post_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_qx
-- ----------------------------
DROP TABLE IF EXISTS `sys_qx`;
CREATE TABLE `sys_qx`  (
  `QX_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '权限编号',
  `QX_NAME` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限名称',
  `module_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限归属模块编号',
  `QX_SQL` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '权限sql片段，一般查询列表需要配置sql',
  `QX_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限类型c-新增，r-读，u-更新，d-删除，p-打印',
  `QX_REGEX` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限表达式,java正则表达式，匹配出来的是允许的，比如允许访问sys_开头的表，可以写为 sys_ x ,允许访问sys_开头，xm_开头的，写为 (sys_ x )||(xm_ x )',
  PRIMARY KEY (`QX_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '权限定义' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_qx
-- ----------------------------
INSERT INTO `sys_qx` VALUES ('common_batch_add', '普通批量新增', 'mall-marketing', NULL, 'c', 'regex');
INSERT INTO `sys_qx` VALUES ('common_batch_del', '普通批量删除', 'mall', NULL, 'u', NULL);
INSERT INTO `sys_qx` VALUES ('common_batch_edit', '普通批量修改', 'common', NULL, 'u', '22');
INSERT INTO `sys_qx` VALUES ('common_del', '普通删除', 'common', NULL, 'd', NULL);
INSERT INTO `sys_qx` VALUES ('common_edit', '普通修改', 'common', NULL, 'u', NULL);
INSERT INTO `sys_qx` VALUES ('common_export', '普通导出', 'common', NULL, 'r', NULL);
INSERT INTO `sys_qx` VALUES ('common_import', '普通导入', 'common', NULL, 'c', NULL);
INSERT INTO `sys_qx` VALUES ('common_print', '打印', 'common', NULL, 'r', NULL);
INSERT INTO `sys_qx` VALUES ('common_query', '普通查询', 'common', NULL, 'r', NULL);
INSERT INTO `sys_qx` VALUES ('DML_SELECT', 'DML_查询所有表', 'mdp-dm', '', 'dm_select', '.*');
INSERT INTO `sys_qx` VALUES ('sys_branch_batchDel', '批量删除机构', 'mdp-sys', NULL, 'd', NULL);
INSERT INTO `sys_qx` VALUES ('sys_branch_del', '删除机构', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_branch_edit', '修改机构', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_dept_add', '添加部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_dept_batchDel', '批量删除部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_dept_del', '删除部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_dept_edit', '修改部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_dept_post_batchSetDeptsToPost', '批量维护多个部门到一个岗位', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_dept_post_batchSetPostsToDept', '批量维护多个岗位到一个部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_def_add', '新增菜单', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_def_batchDel', '批量删除菜单', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_def_batchInsert', '批量新增菜单', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_def_batchSaveButton', '批量调整菜单', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_def_del', '删除菜单', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_def_edit', '修改菜单', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_role_batchEditMenusToRole', '批量分配多个菜单给到一个角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_role_batchEditMenusToRoles', '批量分配多个菜单到多个角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_menu_role_batchEditMenuToRoles', '批量分配一个菜单给多个角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_deptPostUser_del', '将用户移出岗位', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_post_add', '增加岗位', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_post_batchDel', '批量删除岗位', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_post_del', '删除岗位', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_post_edit', '修改岗位', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_postRole_batchAdd', '批量新增岗位与角色关联关系', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_postRole_batchDel', '批量删除岗位与角色关联关系', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_postRole_del', '删除岗位与角色关联关系', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_post_postRole_setRolesToPost', '批量设置角色到某个岗位上，将删除原来该岗位上的角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_qx_add', '新增权限定义', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_qx_batchDel', '批量删除权限定义', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_qx_del', '删除权限定义', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_qx_edit', '修改权限定义', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_role_add', '新增角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_role_batchDel', '批量删除角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_role_del', '删除角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_role_edit', '修改角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_role_editSomeFields', '批量修改某些几个字段', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_role_public_op', '公共角色操作', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_roleQx_batchEdit', '给角色批量设置权限', 'sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_add', '新增后台用户', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_batchDel', '批量删除后台用户', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_del', '删除后台用户', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_dept_add', '将用户分配到部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_dept_batchEdit', '批量调整用户归属部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_dept_del', '将用户移出部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_dept_edit', '调整用户归属部门', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_edit', '修改后台用户', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_invite_emails', '邀请成员加入公司-邮箱邀请', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_invite_phonenos', '邀请成员加入公司-手机邀请', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_resetPassword', '重置密码', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_role_batchEdit', '调整用户的角色', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_setUsersToBranchAdm', '设置人员为机构管理员', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('sys_user_setUsersUnBranchAdm', '取消机构管理员资格', 'mdp-sys', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('x', 'd', '', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmEnvList_add', '新建环境清单', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmEnvList_batchDel', '批量删除环境清单', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmEnvList_del', '删除环境清单', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmEnvList_edit', '修改环境清单', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmExchange_del', '删除评论', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmFile_add', '新增项目、产品文档', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmFile_del', '删除项目、产品文档', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmFile_edit', '修改项目、产品文档', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmGroup_add', '新增项目团队信息', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmGroup_getGroups', '查找项目团队信息', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmGroup_updateGroup', '批量更新修改项目团队信息', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmIteration_add', '新增迭代计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmIteration_del', '删除迭代计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmIteration_edit', '修改迭代计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmIteration_loadTasksToXmIterationState', '计算迭代的bug、工作量、人员投入、进度等', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenu_add', '新增用户需求', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenu_batchAdd', '批量新增用户需求', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenu_batchChangeParentMenu', '批量修改需求的上级', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenu_batchDel', '批量删除用户需求', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenu_del', '删除用户需求', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenu_edit', '修改用户需求', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenu_editSomeFields', '修改用户需求中的某些字段', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuExchange_add', '发布需求评论', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuExchange_batchDel', '批量删除需求评论', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuExchange_del', '删除需求评论', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuExchange_edit', '修改需求评论', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuPlan_add', '新增需求计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuPlan_batchAddPlanByProjectIdAndMenuList', '由分配到项目的需求创建需求计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuPlan_batchDel', '批量删除需求计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuPlan_batchEdit', '批量修改需求计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuPlan_del', '删除需求计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuPlan_edit', '修改需求计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmMenuPlan_loadTasksToXmMenuPlan', '计算需求对应的bug、task、测试案例等数据', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProduct_add', '创建产品/战略规划等', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProduct_copyTo', '通过复制创建产品/战略规划等', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProduct_createProductCode', '创建产品代号', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProduct_del', '删除产品/战略规划等', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProduct_edit', '修改产品/战略规划等基本信息', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProduct_unDel', '从回收站恢复产品等', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_add', '创建项目', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_copy_to', '存为新项目', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_createProjectCode', '创建项目代号', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_del', '删除项目', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_edit', '修改项目基础信息', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_editAssess', '修改项目估算', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_editBudget', '修改项目预算', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_editStatus', '修改项目状态', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmProject_unDel', '从回收站恢复项目', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmQuestion_add', '新增bug', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmQuestion_edit', '修改bug', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmQuestion_editSomeFields', '修改bug的某些字段', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmQuestion_editStatus', '修改bug状态', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_addTask', '新增任务', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_batchChangeParentTask', '批量修改任务的上级', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_batchDel', '批量删除任务', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_batchImportFromTemplate', '从模板导入任务', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_batchRelTasksWithMenu', '批量将任务与一个用户需求关联', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_del', '删除任务', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_editSomeFields', '批量修改修改任务中的某些字段', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_editTime', '修改任务时间', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTask_editXmTask', '修改任务', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskExecuser_add', '新增任务执行者', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskExecuser_candidate', '变更成为任务候选人', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskExecuser_del', '删除项目中任务的执行人', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskExecuser_execute', '修改任务执行人基础信息', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskExecuser_leave', '执行人离开任务', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskExecuser_quotePrice', '项目中的任务报价', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskSkill_batchAdd', '批量新增任务的技能要求', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskSkill_del', '删除任务的技能要求', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTaskWorkload_editSomeFields', '批量修改修改任务中的某些字段', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCase_batchAdd', '新增测试用例', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCase_batchDel', '批量删除测试用例', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCase_del', '删除测试用例', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCase_edit', '修改测试用例', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCaseExec_add', '新增测试计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCaseExec_batchAdd', '批量新增测试计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCaseExec_batchDel', '批量删除测试计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCaseExec_batchEdit', '批量修改测试计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCaseExec_del', '删除测试计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmTestCaseExec_edit', '修改测试计划', 'xm-project', NULL, NULL, NULL);
INSERT INTO `sys_qx` VALUES ('xm_core_xmWorkload_editSomeFields', '批量修改修改任务中的某些字段', 'xm-project', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_record`;
CREATE TABLE `sys_record`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '日志编号',
  `oper_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人id',
  `oper_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人名字',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `obj_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6',
  `old_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '历史值',
  `new_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '新值',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注-只描述新旧值之间的变化',
  `glo_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '全局根踪号，用于跟踪日志',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键编号',
  `pbiz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_id`(`biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `biz_id_2`(`pbiz_id` ASC, `biz_id` ASC) USING BTREE,
  INDEX `biz_id`(`biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `oper_time`(`oper_time` ASC) USING BTREE,
  INDEX `branch_id`(`branch_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_region
-- ----------------------------
DROP TABLE IF EXISTS `sys_region`;
CREATE TABLE `sys_region`  (
  `ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '行政区域ID',
  `PARENT_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '0' COMMENT '上级行政区域ID',
  `NAME` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '行政区域名称',
  `TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '2' COMMENT '行政区域层级',
  `AGENCY_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT '0',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '云用户机构编号',
  `CUSERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `CDATE` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `LOPUSERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最后操作人',
  `LOPCREATE` datetime NULL DEFAULT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `REGION_IDX_AGENCY_ID`(`AGENCY_ID` ASC) USING BTREE,
  INDEX `REGION_IDX_PARENT_ID`(`PARENT_ID` ASC) USING BTREE,
  INDEX `REGION_IDX_TYPE`(`TYPE` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_region
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ROLEID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '角色编号',
  `ROLENAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '角色名',
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `ROLETYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '角色类型0-机构私有,1-公共',
  `ROLEBEG` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `ROLEEND` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `CRDATE` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `ENABLED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '1' COMMENT '是否启用 1启用 0 不启用',
  `DEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '机构编号',
  `SORT_ORDER` int NULL DEFAULT 99 COMMENT '角色排序',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '云用户机构编号',
  `data_lvl` int NULL DEFAULT NULL COMMENT '数据访问等级',
  PRIMARY KEY (`ROLEID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '角色管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('admin', '管理员', '22', '0', NULL, NULL, NULL, '1', '', 1, 'demo-branch-01', 1);
INSERT INTO `sys_role` VALUES ('branchAdmin', '机构管理员', '可以操作整个机构的数据和功能', '1', NULL, NULL, NULL, '1', 'gw8cb9abg', NULL, 'platform-branch-001', 5);
INSERT INTO `sys_role` VALUES ('ceo', '总经理', '公司总经理', '1', NULL, NULL, NULL, '1', '4gx1x7v74q', NULL, 'platform-branch-001', 5);
INSERT INTO `sys_role` VALUES ('DBA', '数据库管理员', '', '1', NULL, NULL, NULL, '1', '', 99, 'platform-branch-001', 6);
INSERT INTO `sys_role` VALUES ('demoFree', '免费版演示账户', '', '0', NULL, NULL, NULL, '1', '', 11, 'platform-branch-001', 2);
INSERT INTO `sys_role` VALUES ('dept-manager', '部门经理', '部门经理', '1', NULL, NULL, NULL, '1', '', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('dev', '开发者', '开发者', '1', NULL, NULL, NULL, '1', '4fr8gart8x', NULL, 'platform-branch-001', 2);
INSERT INTO `sys_role` VALUES ('flowAdmin', '流程管理员', '流程管理员', '1', NULL, NULL, NULL, '1', '4fr8gart8x', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('freeWorker', '自由工作者', '自由工作者', '1', NULL, NULL, NULL, '1', '4gx1x7v74q', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('guest', '游客', '游客', '1', NULL, NULL, NULL, '1', 'A0', 99, 'platform-branch-001', 1);
INSERT INTO `sys_role` VALUES ('hrrs', '人力资源招聘专员', '人力资源招聘专员', '1', NULL, NULL, NULL, '1', '', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('iterationAdmin', '迭代管理员', '迭代管理员', '1', NULL, NULL, NULL, '1', '4fr8gart8x', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('locationAdmin', '门店管理员', '门店管理员', '1', NULL, NULL, NULL, '1', NULL, NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('log', '日志管理员', '1', '0', NULL, NULL, NULL, '1', '', 1, 'demo-branch-01', 4);
INSERT INTO `sys_role` VALUES ('mallm-test', '商城后端角色-测试', NULL, '0', NULL, NULL, NULL, '1', 'mktwr7698', NULL, 'mktv4i2u1', NULL);
INSERT INTO `sys_role` VALUES ('platformAdmin', '平台管理员', '可以跨机构操作不同企业数据', '1', NULL, NULL, NULL, '1', 'gw8cb9abg', NULL, 'platform-branch-001', 6);
INSERT INTO `sys_role` VALUES ('productAdmin', '产品经理', '产品经理', '1', NULL, NULL, NULL, '1', '4fr8gart8x', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('productTeamAdmin', '产品小组组长', '产品小组组长', '1', NULL, NULL, NULL, '1', '4fr8gart8x', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('projectAdmin', '项目经理', '项目经理', '1', NULL, NULL, NULL, '1', '4fr8gart8x', NULL, 'platform-branch-001', 5);
INSERT INTO `sys_role` VALUES ('projectOfficeAssistant', '项目行政助理', '', '1', NULL, NULL, NULL, '1', '4gx1x7v74q', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('sbgzhAdmin', '社保公众号管理员', '社保公众号管理员', '0', NULL, NULL, NULL, '1', 'jzza8kj51', NULL, 'jzz9b2335', NULL);
INSERT INTO `sys_role` VALUES ('shopAdmin', '商户管理员', '商户管理员', '1', NULL, NULL, NULL, '1', 'mktwr7698', NULL, 'platform-branch-001', NULL);
INSERT INTO `sys_role` VALUES ('SUB_DBA', '机构数据管理员', '机构内部的数据管理员', '1', NULL, NULL, NULL, '1', '', 99, 'platform-branch-001', 5);
INSERT INTO `sys_role` VALUES ('superAdmin', '超级管理员', '超级管理员', '1', NULL, NULL, NULL, '1', 'com2018081600000001', 99, 'platform-branch-001', 6);
INSERT INTO `sys_role` VALUES ('sysAdmin', '系统管理员', '用户,角色,权限,机构,元数据管理,系统参数管理', '1', NULL, NULL, NULL, '1', 'com2018081600000001', NULL, 'platform-branch-001', 5);
INSERT INTO `sys_role` VALUES ('test', '测试角色', '', '0', NULL, NULL, NULL, '1', '', 4, 'demo-branch-01', 4);
INSERT INTO `sys_role` VALUES ('test-0001', '测试自定义角色', '', '0', '2021-09-06 00:00:00', '2021-09-14 00:00:00', NULL, '1', '4fr8gart8x', NULL, 'platform-branch-001', 6);
INSERT INTO `sys_role` VALUES ('user', '普通用户', '普通用户', '1', NULL, NULL, NULL, '1', 'A0', 99, 'platform-branch-001', 1);

-- ----------------------------
-- Table structure for sys_role_qx
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_qx`;
CREATE TABLE `sys_role_qx`  (
  `QX_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '权限编号增删改查批量更新导出导入',
  `ROLEID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`QX_ID`, `ROLEID`) USING BTREE,
  UNIQUE INDEX `SYS_ROLE_QX_I_RQ`(`QX_ID` ASC, `ROLEID` ASC) USING BTREE,
  INDEX `SYS_ROLE_QX_FK_ROLE`(`ROLEID` ASC) USING BTREE,
  CONSTRAINT `sys_role_qx_ibfk_1` FOREIGN KEY (`ROLEID`) REFERENCES `sys_role` (`ROLEID`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_qx_ibfk_2` FOREIGN KEY (`QX_ID`) REFERENCES `sys_qx` (`QX_ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '角色权限关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_qx
-- ----------------------------
INSERT INTO `sys_role_qx` VALUES ('common_batch_edit', 'admin');
INSERT INTO `sys_role_qx` VALUES ('common_edit', 'admin');
INSERT INTO `sys_role_qx` VALUES ('common_export', 'admin');
INSERT INTO `sys_role_qx` VALUES ('common_import', 'admin');
INSERT INTO `sys_role_qx` VALUES ('DML_SELECT', 'admin');
INSERT INTO `sys_role_qx` VALUES ('common_batch_edit', 'branchAdmin');
INSERT INTO `sys_role_qx` VALUES ('common_del', 'branchAdmin');
INSERT INTO `sys_role_qx` VALUES ('common_edit', 'branchAdmin');
INSERT INTO `sys_role_qx` VALUES ('common_import', 'branchAdmin');
INSERT INTO `sys_role_qx` VALUES ('DML_SELECT', 'log');
INSERT INTO `sys_role_qx` VALUES ('DML_SELECT', 'SUB_DBA');
INSERT INTO `sys_role_qx` VALUES ('DML_SELECT', 'test');

-- ----------------------------
-- Table structure for sys_test_lcode
-- ----------------------------
DROP TABLE IF EXISTS `sys_test_lcode`;
CREATE TABLE `sys_test_lcode`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_test_lcode
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `UNIONID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '全局唯一编号，也叫主账户，同一个人（比如同一个微信号，同一个邮箱，同一个手机号视为同一个人）。同一个人在mdp有唯一的主账号。一个主账户下根据不同的机构设立不同的子账户。如果使用主账户登录，需要选子账号。如果使用子账户登录，不需要选，直接登录。子账号可以事后绑定主账号子账号绑定主账户的userid.主账户的unionid=userid。必须相等',
  `DISPLAY_USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '登录展示使用用户编号',
  `USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT ' ' COMMENT '内部用户编号(账户编号)，如果是机构管理员账户，则=机构号',
  `LOCKED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否被锁定0否1是',
  `STARTDATE` datetime NULL DEFAULT NULL COMMENT '启用日期',
  `NICKNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '昵称',
  `USERNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `PHONENO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '移动电话号码',
  `PASSWORD` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '密码',
  `SALT` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '盐值',
  `PWDTYPE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '密码类型1指纹2密码',
  `HEADIMGURL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '头像地址',
  `COUNTRY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '国家',
  `CITY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市',
  `PROVINCE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省份',
  `ADDRESS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '详细地址',
  `SEX` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '性别',
  `ENDDATE` datetime NULL DEFAULT NULL COMMENT '到期日期',
  `DISTRICT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '区县编号',
  `EMAIL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邮箱',
  `FG_ONE` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹1',
  `FG_TWO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹2',
  `FG_THR` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹3',
  `ID_CARD_NO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `office_phoneno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '办公室电话',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
  `mem_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '从平台角度看会员类型0-个人账户、1-企业管理员账户、2-企业员工账户，个人账户无须绑定机构号，个人子账户可升级为企业员工账户，企业账户必须绑定机构编号branchId个人账户升级后，保留个人主账户，个人子账户绑定企业编号成为企业员工账户',
  `org_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备用，暂时不用',
  `email_bak` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备用邮箱',
  `pwd_strong` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '1-高风险，2-中风险，3-低风险',
  `lock_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '锁定类型:0-注册等待邮箱验证，1-注册等待修改初始密码，2-注册等待验证手机号码，3-密码高风险，等待重新修改密码，9-业务需要锁定禁止登录，10-账户被锁定，请联系客服',
  `lock_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '锁定原因',
  `ltime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `atype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对于平台来说的账户类型0-子账户，1-主账户。主账户指个人在平台注册的全域唯一的账户，一个主账户下挂若干子账户，无论是主账户还是子账户都指向同一个主账户，他们都是指同一个人,相同的手机号、邮箱、微信号、身份证上述任意一个相同都代表是同一个人.同一个自然人只有一个主账户，但是可以拥有无数的子账户。主账户用于利用微信公众号、支付宝等公共资源（包括不限于收发短信、支付等）主账户无须指定归属机构号,为方便升级成机构账户，虚拟一个机构号给主账户子账户指各个机构下创建的员工账户，他们具有独立的归属机构，子账户权限范围仅限所归属的机构范围。子账户指向主账户。 子账户必须指定归属机构号。个人可以以主账户登录，也可以以子账户登录。子账户管理权属于归属机构，子账户可以自行修改子账户信息，主账户无权删除子账户。子账户通过unionid关联主账户',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构主子账户归属的机构编号,如果是个人，这里填虚拟机构编号，作为虚拟的机构号，方便将来升级成企业号',
  `continent` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '洲别',
  `cpa_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '从入驻企业角度看协作类型0-企业内部人员，1-客户，2-供应商，3-上级机构，4-下属机构',
  `cpa_org` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作组织编码',
  `roleids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '个人账户拥有的角色，逗号分割',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `shop_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '商户编号',
  `profe_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业编号',
  `profe_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业名称',
  `grade_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '等级会员，根据经验值而定',
  `grade_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '等级会员名称',
  `ilvl_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权益等级青铜、白银、黄金、紫金、钻石',
  `ilvl_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权益等级名称',
  `istatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '会员权益状态0-无效，1-有效，2-过期',
  `istime` datetime NULL DEFAULT NULL COMMENT '权益开始时间',
  `ietime` datetime NULL DEFAULT NULL COMMENT '权益结束时间',
  `valid_lvls` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段验证级别列表逗号分割多个，0-验证不通过，1-验证通过，2待审核。按顺序位置分别代表1-实名（身份证），2-手机号码，3-邮箱，4-营业执照，5-法人实名1,2,3,4,5比如0,0,0,0,0所有验证都不通过。比如1,1,1,1,1所有验证通过，比如0,1,1,0,0代表实名身份证验证不通过，法人实名认证不通过比如0,0,0,1,2代表实名认证待审核，企业法人实名认证待审核',
  `features` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '个性化签名',
  `profe_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业类型1-开发类，2-测试类，3-设计类，4-管理类；多选，逗号分割',
  `ustatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户账户状态0-初始，1-起效，2-注销申请，3-注销后删除',
  `credit_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '信用等级编号',
  `credit_score` int NULL DEFAULT NULL COMMENT '信用等级分数',
  `guard_id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务保障等级0-初始，1-金，2-银，3-铜',
  `open` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否对互联网用户开放查询0-否1是',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '简介备注',
  `biz_hours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '营业时间说明09:00-12:00 14:00-19:00',
  `skill_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能编号列表',
  `skill_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能名称列表',
  `def_login` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否默认账户0-否，1是，在多个账户存在的情况下，默认取1的账户优先登录',
  `cpa_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作组织用户编号',
  PRIMARY KEY (`USERID`) USING BTREE,
  UNIQUE INDEX `USERID`(`USERID` ASC, `LOCKED` ASC) USING BTREE,
  UNIQUE INDEX `DISPLAY_USERID`(`DISPLAY_USERID` ASC, `LOCKED` ASC) USING BTREE,
  INDEX `PHONENO`(`PHONENO` ASC, `LOCKED` ASC) USING BTREE,
  INDEX `EMAIL`(`EMAIL` ASC) USING BTREE,
  INDEX `SYS_USER_INDEX_UNIONID`(`UNIONID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('3jhE07I07', NULL, '171123090917', NULL, '2017-11-23 09:36:02', 'Ms.', 'Ms.', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/6nO5FT56ib35OP60ZjUjZa7IibH3wiajgQz9WZalPf1j8XX1CpK4Zica3Tiam3iaQZiboPjoQ9anQz5zbKQeKiaiaXFIVpw/0', 'China', 'Maoming', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3joUgG205', NULL, '171124150851', NULL, '2017-11-24 15:24:33', 'Kay、小魏', 'Kay、小魏', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/JHQsh5cfE2qhcVEw8m0jHd9Ydeo74KmC5tf4iaogXlm8V05MegHUJibhITiaicIFodFgkNzCib5a9h5wjOjFQibXnM1Q/0', 'China', 'Cathedral', 'Macao', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3jqKGGZ06', NULL, '171124220839', NULL, '2017-11-24 22:59:02', '何勇', '何勇', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/AT6w4n3ibtCkcyrHYhw4S31cUTnA1JJhA99eMxHqCw7fORtKiaWRn8oXdX5fvZZxhsjInxiaIx4M6iarO6gzlJM1kQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3jOkLXO05', NULL, '171128230610', NULL, '2017-11-28 23:46:38', '淡定哥曾', '淡定哥曾', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/bRiaNcbzgwPWeewK5ySsA0rztZYiciaWhWsdwMib81mHKTNhrVKApAdyGjsRFPYNdckpXgnYRpSfuzMc2emBPRJ4Gg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3jR4u2Y09', NULL, '171129110922', NULL, '2017-11-29 11:00:45', '贺文干一App+ERP', '贺文干一App+ERP', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/qDydqdtnicXnH1qmqVuljmwGwLG4UkEJtPiab6AC3l9tQJAyrA00ibyhJqp6SRZUicP69cicqDSTmaJ5Snh7sQYRrfw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3jRxkCh01', NULL, '171129120245', NULL, '2017-11-29 12:55:20', 'rdgztest_OTWXZU', 'rdgztest_OTWXZU', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3jRZ2Sx08', NULL, '171129140093', NULL, '2017-11-29 14:45:25', '帝燊', '帝燊', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'http://wx.qlogo.cn/mmopen/RZcMdItjxalt7DialNLcY8Kd5VxoulRl3ibcUGX1yvNicgYjZIfNFolj7cg0ibpibNco5KRHb1YcMetv4KTB53oovROoETakW4qyI/0', '中国', '广州', '广东', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3jWELiR06', NULL, '171130090306', NULL, '2017-11-30 09:56:13', '春燕', '春燕', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/KcESJd5yPTtPxm25rgYzAeiaAd2CLOI8tbxYy4fdC0XyfHTEDIsA2j2KYcQcTPlickpwfY48TVdgWecA8PYibSgeA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3k8jXkU06', NULL, '171202090489', NULL, '2017-12-02 09:48:50', ' 杨 华', ' 杨 华', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/AopgbmSGaV0ZekfiaAasgnVia2G2xzO3ibdUH4zw4sbz8X23tG3iacx1dCbks71YImicxPdwhglXmQkOzx0jqmRKVbA/0', 'China', 'Lawrence', 'Guizhou', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3kbILJi09', NULL, '171202230629', NULL, '2017-12-02 23:46:13', '方波', '方波', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKWVGKakkmgcn8aOjHLtGkbVUZcKWia3UkiaTRxqJMY4nyBfib3HpusIRCbC9VeRACAfhA1iaN8Cg3IGw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3kfnrZc09', NULL, '171203140507', NULL, '2017-12-03 14:46:37', '老米', '老米', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIFSW0PJiaSBqUhOCVbs975iarIpTsLddm4sNibXxsBG9qq8PXpibWCEOU6KpeAlGEh0wv8miasHWjvzkA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3kgvf6Z02', NULL, '171203190824', NULL, '2017-12-03 19:23:51', '？？丁子？？？？？？', '？？丁子？？？？？？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eryvrKpEvy9TwXTFX8p90s9F5wf9Sx64s4gE3nz52pSj8GEjv7nW6bGNNibQ7wL3MaUD0EcDibkIAVQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3ldteiG03', NULL, '171213210189', NULL, '2017-12-13 21:25:55', '汽修黄仉华', '汽修黄仉华', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/bnSYmuO2iaVEnh8IcQVnPMia3o9AIONoZyPEhN6nU9cj9l0Xn0FoM21ygR1WY659tOe2V5wjRIiaMscYqPBpnpFbw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3ldtMaA05', NULL, '171213210696', NULL, '2017-12-13 21:28:05', '無敌', '無敌', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/nHTeBrP98wwe0NausZL9q08lVSTv97qVMeBBgWshP5EiceuOGzXynONErMLnbe7qAJHsL0rbjEKUwicsoNPpaugg/0', 'Argentina', NULL, 'Buenos Aires', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3loW99L05', NULL, '171215200262', NULL, '2017-12-15 20:29:46', 'YoYo', 'YoYo', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/5EGDrIhkCqy4r2IxhIMcfaibqmC9RmTmdiblbZwSn2RIibEXoR4ZyGyJRtyvBJP00ibaFPnFyAkBiaDSmrAdHX1gqeg/0', 'Austria', NULL, 'Burgenland', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3lsK4Bb01', NULL, '171216120360', NULL, '2017-12-16 12:06:54', '朱八戒①，加新号mxazhe', '朱八戒①，加新号mxazhe', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqGCxJHVVgaEwmFaucOYrJHU6ZZnUSF5lpvN5nhEeW6vyXUPpiboZAbbvZyCTvWUeVkmS9LaaicJLkA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3lvzwYY04', NULL, '171216230941', NULL, '2017-12-16 23:43:50', '广东万事达', '广东万事达', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/7n4XUhicm9gU7RKKqhWibeXm8dLksgsd1scQcd674TTYYMtILhCOz97bEwUv3ACoqwO8pia3VUnvhOGzQHdiabnhfw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3lFq58w09', NULL, '171218160226', NULL, '2017-12-18 16:09:02', 'W先生', 'W先生', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLhib5LLq7XEWhfdQBaZqLqSWoYhjcfdHic6ej4NYEOlW4RpeWC8z9k0j0KTiareKdaljtPHn6UvnWPw/0', 'China', 'Jinan', 'Shandong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3lGObWy00', NULL, '171218210799', NULL, '2017-12-18 21:51:04', '导航倒影360全景贴膜美容', '导航倒影360全景贴膜美容', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/pTD8nS0SsOSxtalbsXkbevHNVHo4ibpCMDApic6P27JSPuCLiawfG19E0TicpT61JMzug9oM1hUHMnm9nTUFfNkbDA/0', 'China', 'Shenzhen', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3lLmDlM02', NULL, '171219160285', NULL, '2017-12-19 16:32:58', '粟祖新', '粟祖新', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/ajNVdqHZLLBLNWibggjw6BHNpXEibwMscT1ibHqtABhKJDjgorRV0ALYZTiaUohCwjTYUA1Z8snticHHJhlC3FbvmxA/0', 'China', 'Xiangxi', 'Hunan', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3m5zM0F01', NULL, '171223030677', NULL, '2017-12-23 03:30:36', '星星点灯', '星星点灯', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/yZAkFt4zAOGCS0ejwV1Pv386MsldY1ibEZ3lYpLDTiapApMCoibJajItW0ib1ZxT3zwVZ50rhyLr6n1Kia3Pn9jdgZQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3m8enl901', NULL, '171223140302', NULL, '2017-12-23 14:24:26', '刘颖新', '刘颖新', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/dWbNv0sWibqsmOQFQjpTTvOcVQxqqGl2kN7Dy3rpkJChXj2Q3PjC9xt09S6VWyzCycq8CKtvexAZQFCwKNbCGUQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mgj0jw05', NULL, '171224230196', NULL, '2017-12-24 23:32:59', 'Charles', 'Charles', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/0iaWEjweUtZp5fUUR3Ou9Du85ArRaiakgibiaJBM2y8EWBvLBwj82sYhoaOBLJhDLfTQlWpUgUzjt1jzEzhTRWlxGA/0', 'China', 'Changsha', 'Hunan', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mieXOV02', NULL, '171225070194', NULL, '2017-12-25 07:29:29', 'Ws', 'Ws', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLAFNR7biasuXuLCXIhyatjFYnIX1QzzpyfqnLibVRduH6jHAqHFfNURwcaoSZQtXDPyAjZZqB2psng/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mjigld08', NULL, '171225110437', NULL, '2017-12-25 11:48:52', '不懂', '不懂', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/oCrmM8icMBGicWSgmO7t7HDOfcnwE58ribnZVibC2hibt1a0c4yuTUooYoqFA918LmVboaGzOT37QygHn53nVyibmJZw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3ml6XvS02', NULL, '171225190672', NULL, '2017-12-25 19:16:30', '海阔天空', '海阔天空', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/KZBNgla4iaeWGGSWzuicPWxAbC3NiauqcccxK8Eu4S2SuOxx80Yyrrc8wpNCeialiaeWvG6dosfxGXJ0A5I0rl04vjQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mnib1u07', NULL, '171226040598', NULL, '2017-12-26 04:13:37', '武群', '武群', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/cic6pdNia37hCYCMcP32GfZJJUdS9hWD14q7VicVcXkq0gGjTgxX1MR34tJdZhShmt4afDSiaBhqmmLWCXB6dBBQNw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mvxeM308', NULL, '171227140018', NULL, '2017-12-27 14:03:37', '-', '-', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/o7AiczaJwYjicCIt1a27CDZnEboWYVKicHPah4Diag5rv4FIMGtjroDs8KcsQBGJbwYZufqPC10tugcmVrVGDVtchw/0', 'Maldives', NULL, 'South Ari Atoll', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mAKjJm00', NULL, '171228110567', NULL, '2017-12-28 11:26:56', 'LiKE.Sun ？', 'LiKE.Sun ？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/PiajxSqBRaELe6SHIticiblAMeIkVYZdA0kPRAjVY8XJmiatlVQ45PumxgibTKNjaZtXF5IABZ1YBK3vRibtsN4yEyOA/0', 'China', 'West', 'Beijing', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mCK1F104', NULL, '171228190941', NULL, '2017-12-28 19:38:19', '夜雨', '夜雨', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ergpPMu26kHPOhKJHGuwVARqsZzn0lQBOo6h4Wz8iaQ0ox7R4X4WuiaA7jBod6QlecIwfticBfcjSWuQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mDYeTX07', NULL, '171229000968', NULL, '2017-12-29 00:41:03', '孙一翔Mars', '孙一翔Mars', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/LUw0gxXwtKvibLT0ibia5MMFgdz3RXh9d9jLlUURR48uHfTW1Jvpfc5LHFNic1lAyZtEygFKoTXlIfIm70SsTjHZEg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mG2OlJ04', NULL, '171229090452', NULL, '2017-12-29 09:11:45', '司马相公', '司马相公', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/lJxJAmyQcodnAd21ib9ECpvIv43tbKlCaib1lI6O8goVXwcHxlpoxFdJLBInLs9Qj62NlMzjYdibrlUTFwtqhXDtA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mIk8bJ08', NULL, '171229180039', NULL, '2017-12-29 18:33:05', '欢雯？？', '欢雯？？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Dibeico9wDRCnsWkT4uTylQL9AoLeo1krxGkBrQMfY5L0Y3oL0HZq1rDQu9XoSZmIseyZtm07yklQUn2VQNQhdnw/0', 'China', 'Jieyang', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mIrQSS09', NULL, '171229190549', NULL, '2017-12-29 19:03:45', '蒸蒸日上？？汽车移动美容店？？', '蒸蒸日上？？汽车移动美容店？？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJWX1Q42uNK5bjAPyApQM7easUPcIzb7IibIvI8LfWp4uicxUZR6y6gOjkB4TOPzku1icySVNm9bgQkA/0', 'China', 'Dezhou', 'Shandong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mKqc6m09', NULL, '171230030432', NULL, '2017-12-30 03:09:43', '？？X ？？', '？？X ？？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/aobibE2ABHn2XGRdMPDAJL1mHp5jOrTY4Gq6bY5QqITSJD7FTQcIGhwLjyQ8GCdz4ib1JkhINsJspPoww0DIOzOA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mPh9D103', NULL, '171230230139', NULL, '2017-12-30 23:05:10', '阿圣哥', '阿圣哥', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/PEZzQp0DOrOgFj3Mm1cuA24yVmnNkfOd3qZicqH74CicS3ooeQuOVyicjZYP51FjIfEuX9DWHraYeU3piajaVae1ibQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mTmOlZ05', NULL, '171231150183', NULL, '2017-12-31 15:52:44', '遇见即是美好', '遇见即是美好', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/olhhB8ndD8gZnnS3RdqUtDF8d0GTKfQhGxndqfgEic8PNIfrkpL5ic9d5tab7v5oicx6gHpMvvZE6KxM5eJXKbHpg/0', 'China', 'Jingzhou', 'Hubei', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3mV1p2008', NULL, '171231220242', NULL, '2017-12-31 22:40:14', '_Hao', '_Hao', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/AaGgyg8FcqXZA6BL5NXKguib5s7wMIBsP5oFQEmIpOMcOFxxWBWRcSiavIwZk15sia7DagqcHJWp3Da5VBsia9e5ZA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3n5gNDE05', NULL, '180102160626', NULL, '2018-01-02 16:44:07', '峰回路转', '峰回路转', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/lmbZUvpKIf2AicKgvoISzf2pxfWA0mfOQNbHVfXPqz2zPEr82KQ3zicG5200KvrBz8M1micVIgOYNaL1ZZLs5ekicw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3n7CGYF08', NULL, '180103020052', NULL, '2018-01-03 02:23:37', '韩磊', '韩磊', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/4KGDYpurPJrJXDaRWdkkLcfrgF0c4dVYwicAvCdlibZ7ibV0VR4GhTTc7Gjwhia1dPmZO5guGuYmZGxyBiaw1qgFsNA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3n9zRPw06', NULL, '180103100627', NULL, '2018-01-03 10:24:57', '放开那个女孩', '放开那个女孩', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83ep2z61oKAgTsELzvnsAXcfvBlpicicbYNy2VCz5fxEuN6ibfrzmODpjibeRs83pg0knOj1MqKLlst5mXQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3nbjefc03', NULL, '180103170805', NULL, '2018-01-03 17:31:24', '强哥°π-8', '强哥°π-8', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIoOX6IfJ8lSPO2OqhITUFHYkXQb0vA2d4rH5ia5I2ydymm4oicIAs2WHcXV9ID1a0UAFvzDvScNQkg/0', 'China', 'Tangshan', 'Hebei', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3ngpsFe02', NULL, '180104140757', NULL, '2018-01-04 14:27:31', 'cloud＂', 'cloud＂', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL8O8j1HZhjWT1l890UZceKFp4HLibWP8CceBWouILIef4tDLoia7qamXXU7M2zrXzRibgxDicfgtSsDg/0', 'China', 'Hangzhou', 'Zhejiang', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3ngXrwD00', NULL, '180104160505', NULL, '2018-01-04 16:42:30', 'LWH', 'LWH', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/LGXAAIp6YCBYNSrxMxGoSsQjqg5bD4CqESgnswR96lbP0gtJax7fib5qNFM4GA0UibrtSx9Uqt8zLbuCXRQfMFxg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3nmut7F07', NULL, '180105150616', NULL, '2018-01-05 15:25:03', '有志者事竟成', '有志者事竟成', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/bM4OPktGjuvAwPPAN78ob9ccHn4a35buAzWfr7uKIlQoiagZkt8ezdvIaSjdq5xEyv0vfPkaYMp1j9icyoqpibrnw/0', 'China', 'Lianyungang', 'Jiangsu', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3nofAq200', NULL, '180105220110', NULL, '2018-01-05 22:38:28', '云影', '云影', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/VVgwHQBkDricnn75F1DVVrwIVnH9GxRzbXjdd71vv4wucyEJ0vXcCiar5vsqzGS8aQE1skleozMqqnms5GvDE44Q/0', 'Australia', 'Kalgoorlie', 'Western Australia', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3nrtDqF09', NULL, '180106110723', NULL, '2018-01-06 11:53:06', '小杜', '小杜', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/FVvySsPll3vibpqtckblIDUGC5s6WWgia8kr74VLhg8qLh0h00ictzTTpC6ZC9wNrhCqAibcnFxXDHbLSicCl1xcR4g/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3nuQagu06', NULL, '180107010856', NULL, '2018-01-07 01:41:24', '？永洪？', '？永洪？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Dea62GLJhFpuqAQlU4AXGBUT9FuxicvEGuem2Jy2wI70CicxrWBCB1bmhJhnfJbrX8W1BFQddIxP9F6uiaTGmhZww/0', 'China', 'Jiangmen', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3nD4dNp01', NULL, '180108110043', NULL, '2018-01-08 11:27:25', 'HK王牌液晶电视', 'HK王牌液晶电视', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJbibsxJPyM36vpszJOJ9ibHOuYoPrywam4aIMl28FrZrWbG8quEnico7WUADJDHR9q1Lzl7X6ojmyyw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('2x816833t', NULL, '2x816734', NULL, '2018-01-21 02:06:52', '寒冰', '寒冰', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Mgw74lbsfPHVc3TMrqezkniasQ7OjFEJSAUJwAcK4uib1MxUq6YLWwufR2ed4INOFtbetvAyaK2cACGIlic7IoFVw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3ntk65bax', NULL, '3ntkxx18', NULL, '2018-01-24 14:13:24', 'Yellow家的蠢程程！！', 'Yellow家的蠢程程！！', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/j8xNOSSrZMeYOQfU5G9mhk8q8D5a8Y4PrSc692sll0HHor1OqibZzKUF1bUCZuic8NTm8UiaAGhHSRh6r9dOicVq9A/0', 'China', 'Chengdu', 'Sichuan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3xcpc547j', NULL, '3xcpgk26', NULL, '2018-01-25 00:36:34', 'rdgztest_BOROXV', 'rdgztest_BOROXV', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('49viz43tn', NULL, '49vi5a94', NULL, '2018-01-25 13:44:53', '利翔.龙俊', '利翔.龙俊', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/icPibHd26exFzUicTv5YHrxu71cic3iaIlvaOblo3wdpK8NUuhrwbXcn9wRDJ9N3cd9YOHatnw20OfSeYAARWNm0LLQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('4g8ycuxu4', NULL, '4g8y5u92', NULL, '2018-01-25 20:22:33', 'hotboy', 'hotboy', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/iavdAuMucXHu2xqYhpTz3FyGta9TJZC0BqwRsTrbZIcOfS4tUIYnficiakT0DFibawx0uWI3z76zTj9Qczbk4ICmsw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('4nir7492a', NULL, '4nir8w84', NULL, '2018-01-27 08:13:50', '晓东', '晓东', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eo7GV7aAc9ow6Tycg0ge5pdK47TXmTYLmicRH4s4Q95Qt9oqibic71Hf5NqSAYhcJIkdYQrxgpeCsoOg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('4vna5eerx', NULL, '4vnaz881', NULL, '2018-01-27 16:24:49', '自由飞翔', '自由飞翔', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/iaYdWCXZ162iasPW0fpLibIkjoPLjEKPqoBziajQ7XdX4P1vM9picrBjS6iaKtJnd4BkdzBGT7ImrEbHktCVTEzicjwWA/0', 'Albania', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('4y3mkeu8z', NULL, '4y3m1926', NULL, '2018-01-26 15:30:49', 'A？顺意来胶管店13535440505', 'A？顺意来胶管店13535440505', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJbESjCL6GyEns0uYqAia9ersz5CDdz9dGhaianBqoscTtvzD8fLn7r3b4z51lYPLTIVEf9FfczZBnQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('564bm4ize', NULL, '564b2385', NULL, '2018-01-28 03:49:56', 'A  卓邦塑业制品材料', 'A  卓邦塑业制品材料', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKelOFLhnNsaXDW28CPic7ia5hOxFhXgP21EqiapFr3eGmX9IldhXBff6yBzStDbtAcFq1bSBCZuhEXQ/0', 'China', 'Foshan', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('665rbh341', NULL, '665r9s22', NULL, '2018-01-30 22:02:56', 'Apr.', 'Apr.', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/CyAph3hPTB0zesPibOKMCicrZMYJRKtBAlibkImlUSSzX0ibM8v5LUdg2yYy6AvhMRcLdmcBiayQv6TsTasOCLSsjqg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('68p87889n', NULL, '68p8u313', NULL, '2018-01-30 16:25:45', 'hay min', 'hay min', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIqYCJt6lKO2EAaUtcB0XsVemcfqSX109tDBb7pqYmbAW4ssYd8x4NBbnbImoSD56DJMsVLNVMPxQ/0', 'China', 'Qingdao', 'Shandong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('6ibqd4k53', NULL, '6ibq1851', NULL, '2018-01-31 11:24:47', '竹影', '竹影', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'http://wx.qlogo.cn/mmopen/XGvic4XGNfCHTYDN7CbialsQPwbkLqBumHWldVia9tveRVPpjFNd697bNgu9VlT5WleNoBz1q6QernR7rKZ0kS6L8je8rUdkb19/132', '中国', '广州', '广东', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('M_U3AMN79N125', 'chentian-001', '6j7vf3p81', '0', '2022-11-29 04:22:14', '陈天', '陈天', '', '$2a$10$apjVsqWt4QM9bOkgIMJWee02WelN/HJDRM0PBkp4MjfH1GUR3N/vu', '', '', '', '', '', '', '', '0', NULL, '', 'chentian-001@163.com', NULL, NULL, NULL, '', '', '0214f985-db69-11eb-81a4-024205ed0df2', '2', '2', NULL, NULL, '3', NULL, NULL, '2023-09-26 20:36:06', NULL, 'platform-branch-001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('6qr82k78t', NULL, '6qr8tz87', NULL, '2018-02-01 23:59:09', 'AA君临天下', 'AA君临天下', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKvLoWFj7CfRuSmxayw1FForgf40s0tvxd0Bh5bBrFSlNnbzfBSP656egrm4xBJooVYmyC9rMcGicw/0', 'China', 'Wuhai', 'Inner Mongolia', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('7ubw4r5xm', NULL, '7ubwz857', NULL, '2018-02-04 21:58:07', '明喆二手车-vavy', '明喆二手车-vavy', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/hdX1HlcVJUWEjIW0iczib4EibjRshfNym4D4GtjrPqWxaxBHJESwJia99GJDj9oG3Xf7x6ykVzdok7mEMEsJpnrbYA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('8bzc987zp', NULL, '8bzbn788', NULL, '2018-02-06 20:04:01', '鲁东东', '鲁东东', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/WCcouXsOEyK5kKibWicX7rVybeqQfCKnHCotT9DHSegbUNPrWHgCtEdRLAzqfFqiblA3V1Pl11fuZCFyIOibzX69Zg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('8jnax1a6f', NULL, '8jna9v79', NULL, '2018-02-07 04:24:07', '李昶霖', '李昶霖', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJPXDw7FHXZf78WEiapAVgyvricmm0M2ibW46dDckQjblGRx227B7PLOsEyiaI8fd4rgianw5QzWLQCtHw/0', 'China', 'Kunming', 'Yunnan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('8pxjssvxs', NULL, '8pxj1798', NULL, '2018-02-06 07:40:08', '(？？ω？)？嘿', '(？？ω？)？嘿', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJxic7uHlNxcSCcSd8mmxMdlHr0FyjJvA55MDPwJCrBOgK6jNI4eIagEZVicbEo0O57PKRWleHlvRWQ/0', 'China', 'Ganzhou', 'Jiangxi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('8qy88ify6', NULL, '8qy8jy48', NULL, '2018-02-07 12:30:47', '李昶霖', '李昶霖', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJPXDw7FHXZf78WEiapAVgyvricmm0M2ibW46dDckQjblGRx227B7PLOsEyiaI8fd4rgianw5QzWLQCtHw/0', 'China', 'Kunming', 'Yunnan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('9khv95r42', NULL, '9khv9115', NULL, '2018-02-08 20:15:36', 'Jen砂力威', 'Jen砂力威', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/JrOuleWLfV4kU08ZOsYUKKMYDt7DpfWjdkGEQWMURiakQpM1raFMbWmZcTpaZlK28KE1KmeDlVOBkYLerq6MwNQ/0', 'China', 'Shaoguan', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('aat1qbxw9', NULL, 'aat1n675', NULL, '2018-04-25 00:33:18', 'Mq丶', 'Mq丶', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/36cFU7FvJwM4jeicFcO2GAQkN1icJZn2WmLlKe4dBUBibLLN1L0iaKAcib3Wspd0wJ0UxXzTicdrJXHPb032vIxpA8sA/0', 'Lebanon', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ajhp9aa78', NULL, 'ajhp4746', NULL, '2018-02-12 16:42:26', '哎哟喂', '哎哟喂', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erkAnnHberDTasYP1LhEYgPuhmHz60MUraVF7XGaNfsYdXf1WQKx4ppV7D5WcdZryWEiaiaWYPPETrg/0', 'China', 'Chongming', 'Shanghai', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('b3p541kgx', NULL, 'b3p55481', NULL, '2018-04-26 07:54:02', 'A00？？三盛上门洗车17602247123', 'A00？？三盛上门洗车17602247123', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIJzTTCtibjCPR69AfmSzHuLa11h34vep2iamL6nkls0qpIFWcW3d00PEDXzVTeBibR5Wf0bt5A8Iahw/0', 'China', 'Jinghai', 'Tianjin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('b5p88f8i7', NULL, 'b5p85f46', NULL, '2018-04-26 10:02:05', '现磨咖啡', '现磨咖啡', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/vThEe4sKQCPERFL4w0AemPYMtibCMG7bdEjaoBpYP8iaFQYXBLkjVDNc7v7sbnzQF6ricD3NicnlruJD3FJQLxlrdw/0', 'China', 'Xuzhou', 'Jiangsu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('b5sabaacy', NULL, 'b5say874', NULL, '2018-04-26 10:05:47', '阿建15396827776', '阿建15396827776', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIUyFOiaEZ0MuaG2kp7F2uia27bnNF1zRQEicHg06cA5blibjU5W1jr4mgUjPaJkoDLdApHmfgpwmby6A/0', 'China', 'Xuzhou', 'Jiangsu', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('b7vvwaht5', NULL, 'b7vvp815', NULL, '2018-04-26 11:50:30', '袁修亚', '袁修亚', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqnKFich3MK28NyOfITBaTkgh27gAhQOFfH8L4XS9ichsE4hD29gJZwRAicj8w1dU38nPLNWM3RvpKGQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('bbshz7y2f', NULL, 'bbsh4496', NULL, '2018-03-22 23:11:00', 'Cj', 'Cj', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/blXR3ibGHKsXJQXN85jjba81e0N02F6pDt1ianSLc5Wu7znOjEb7FYINQue91WWicws5L8aPDKhgtFpZsZk3cORQw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('bcx1vn818', NULL, 'bcx15u18', NULL, '2018-02-15 04:09:06', 'G\'s', 'G\'s', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/WY9NSJiadQbuc8yQgGjOn8KniaDbP8CmNr9BDCjbqXclbX4VArdfCqAqQ3Igot8Hc1Q7SicwDsF2zEYqFBZ6N4ETg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('bn35319ye', NULL, 'bn35p354', NULL, '2018-03-23 11:33:46', 'Sandy', 'Sandy', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/ic83cWUTf83MIlL48OviaF5v3kiahs3mfpjpxgSySw6ogt6Yx9bqYibzfjC66w1MGEIP9JdtIOhegMcHGtFc81ErMA/0', 'Iceland', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('bnwgm1a8d', NULL, 'bnwg8h86', NULL, '2018-03-23 12:31:13', '明', '明', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIo5lGclegteM70lyldiaT2Qu5ZZ78CwOTbMuDTN4JLBvw8s17wmYS8IGuxCMcGmVnLhqj2sTzmjVQ/0', 'China', 'Yanbian', 'Jilin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('bqap93524', NULL, 'bqap3728', NULL, '2018-03-23 14:53:58', '聚蚁科技2？1？ 赵华南', '聚蚁科技2？1？ 赵华南', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epgIAlwh17qhxGFAmYFTb7dZX1dof9xU4lqtAZpuV4iazeA6IoGtniaDdAxUKoNdVUg8PcnicYz3xWdA/0', 'China', 'Ganzhou', 'Jiangxi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('bqwb2s78g', NULL, 'bqwbty88', NULL, '2018-03-22 11:57:36', '小麦', '小麦', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/BSoBFdCXCz9DKV1jUsAtJXjED8fvyRJbmRbZ9DJvAs1zGHrLyBgYWsic7FVE70w5UTmnVekyp5Fs60ootgW5FDQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (' ', 'BR1650128548326195', 'BR1650128548326195', '0', '2023-09-25 04:03:32', NULL, '测试个人注册升级机构015', NULL, '$2a$10$j25ojBUpLNeF/qYuREhf/uaI2E9.DtS8hhS8wfMj/90TxF1lo1N3.', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '1', NULL, NULL, '3', NULL, NULL, '2023-09-27 05:07:31', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('bzgf72u4s', NULL, 'bzgf3s46', NULL, '2018-02-15 00:13:08', '董', '董', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/6JibUunuNY00Yg8PIxXeIbvIeDqFWicBsahiaicTxhKRNYzqQBB1Og5ibmRobohtjsgEvhTjxEhh4kBfjQrhfNiaw68Q/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('c1x85by83', NULL, 'c1x8fs23', NULL, '2018-04-28 23:39:29', '蜜汁叉烧酥', '蜜汁叉烧酥', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/szvWeq2IcAwlEvzydqkl2MPxQao0Ua5USTJgPdGhXmg4P2gUCjBrpZf4mCULCvRrJ84aAKZ8PribfhGiblqfpZog/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('cdg2q9j57', NULL, 'cdg27614', NULL, '2018-04-29 12:10:37', 'A余忆  微信小程序13229929166', 'A余忆  微信小程序13229929166', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epVKZdpcfPAfHcDkeI4fDNkQBgxkTLx2D9ibLS2nIw9xvVcaLWJf9tNN6Gdzks2EAKeJzD09IsTexQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('cftf6ha23', NULL, 'cftf7858', NULL, '2018-02-18 01:02:40', 'aa小蔡-合盛布行', 'aa小蔡-合盛布行', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqgdC3ciaMr5FdXPR5d2HLrECGQ3SJqUxUx6u1XPqhrnZ3mzqqia5pR0V9nS7E428O7O7SCXxmZVRwg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('cgpde3f9i', NULL, 'cgpdwv86', NULL, '2018-02-16 22:17:16', 'Daniels', 'Daniels', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/RHufJAQ4bV6QbU39OSjJyBrEbMlvict3EE0DSicV00nyCmowicXIu52p6v6wIcJRqzvbVMSDh416RqqtLuicM5uxIQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('cw1f2c81i', NULL, 'cw1fc161', NULL, '2018-05-01 11:58:20', 'Because of you', 'Because of you', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIGmI6xQqnZ10uHw3tFxvjIpewhRwpia91uOuWCHGjrH4EzDfHtTEdQrcaj1BzepMZeEBsibVq1vQEg/0', 'China', 'Xi\'an', 'Shaanxi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('M_U3AMN79N125', 'demo-branch-01', 'demo-branch-01', '0', '2022-11-29 04:22:14', '超级管理员', '唛盟平台官方演示机构', '', '$2a$10$s0hTtvNmquASJ3Z.SjPyMO8lGp/sqh9cw9gghlF5FxcMTVB1Edhc6', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'chentian-001@163.com', NULL, NULL, NULL, NULL, '', NULL, '0', '1', NULL, NULL, '3', NULL, NULL, '2023-09-28 11:13:42', '0', 'demo-branch-01', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ditid84bz', NULL, 'diti4t96', NULL, '2018-02-20 22:27:29', 'love...花花', 'love...花花', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/3IAJeiapYnr72d6CCmxl5rnmJLetjmcJ1Cx9m1Eal3cZefXN7A38icjCHCIhVGT5JREibzfQoRGCHsxI8fxf6mF0A/0', 'China', 'Kwun Tong', 'Hong Kong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('dt6x3xwm6', NULL, 'dt6x4383', NULL, '2018-02-20 06:02:32', '夏夜微风', '夏夜微风', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/dD6aYibRBsPwcvccKE7JsWAibwgIdNSfLlWy4ObibU32xXb1xyIkqwdwMt4lib4zQbuURqrzYWpqgUKxicuXBERTlCw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('eczs28q7h', NULL, 'eczs3498', NULL, '2018-02-23 10:21:35', '韦ΞKentiony', '韦ΞKentiony', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/FcRibGPkicSKg3YcBNDO06ACApeuyib27pR6rxkBQ3OriaESCR1asvLAL6icl7w5g7qT63gSmzluksIkHUkozPxtXCA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('eg4b1a49b', NULL, 'eg4b2x36', NULL, '2018-02-22 10:19:35', '天道酬勤', '天道酬勤', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ3ZMr55nmPGzU5pCajAhsab7Ht0Gu41iamJuoMFsSiccKNNkw5PWvEYKRNFZiazXqt64526nM3rH3MA/0', 'China', 'Fuyang', 'Anhui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ek68s1d2x', NULL, 'ek68xg22', NULL, '2018-02-23 18:23:15', '朱燃', '朱燃', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/RZcMdItjxalg5ffuImpc4XqnQQWKKK33DTQOjrsglTIzzFy6ia502VX0k4YYEdeL2rzLoYic5qnibIlQzGUyBcwEM978Ku4Wv4w/132', NULL, 'Diamond Bar', 'Los Angeles', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('gfrnap1b2', NULL, 'gfrn3287', NULL, '2018-03-01 01:49:42', '小辉', '小辉', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/pozWR1stRGQJmR8icBH12sOunIOVZnic6ps1Fnr84MjAVSuo81w2PoqTiaN1CTnDMMZ24F2t9n7LDTiaicjxkI6A8qg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('gka2wmv9v', NULL, 'gka2u877', NULL, '2018-03-01 07:18:33', 'sudy', 'sudy', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/WnbsIzp30vkf8OVicicMHv0E4zPEUpDyWGxrQS8PPGrG9Kt0u28nOibOqicATFrUn58bd3bAWia5Oy53NBFw2B8ibJHQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('gmgqei629', NULL, 'gmgq1w32', NULL, '2018-03-01 09:33:43', 'rdgztest_JEDPLJ', 'rdgztest_JEDPLJ', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('gmgqgb3vh', NULL, 'gmgqy482', NULL, '2018-03-01 09:33:43', 'rdgztest_PHKBBT', 'rdgztest_PHKBBT', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('gptvy81wj', NULL, 'gptvqp92', NULL, '2018-03-01 12:59:26', 'Once', 'Once', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/zBZticicTY3TM3JYtFDgmfP8iaGsvMI4LIeDGxz1gLzbEvz0f3JIBe47v4Xy7ub0FRhOibY7ZJhnfPvXT4eeOXTGtA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('gpue5e222', NULL, 'gpueg245', NULL, '2018-03-01 13:00:11', '俏俏', '俏俏', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eq9z5DicxEJgpqNUD0lWo76h6fbHjbTKVBJCRwIJwgSHhlq9OD4hBxQRib2FOcs1dIKmcHfeojBkibIA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('hcix2vv9f', NULL, 'hcix6281', NULL, '2018-03-03 17:07:22', '薇信公众号服务 -小慧', '薇信公众号服务 -小慧', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/xIJMfpeO8SkZwLGkF9JbzlXbb0icVqTvU6werPKFRkGkAZjnGMQOLxliaZvbTEia8UosflRgt9TbcAjtmw9usyjAA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('hjt4djt4e', NULL, 'hjt42488', NULL, '2018-03-02 21:00:33', '游游', '游游', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTI8DLZrUXqaZrgRRFHLiaGxC959UQRk4GBibTibicb8gBCo6UU2wjToia1icNx0aAG9ariau01H3RDRtCEJg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('hjwzy8914', NULL, 'hjwz9s82', NULL, '2018-03-02 21:04:36', '.', '.', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJvlsDLIgbzsJzt6jBBVa8ZgdUuNqT0Rz0uQ8I8nLMFJnEuoY9EBwAdGRPKwamVHAicagBoWnArfww/0', 'China', 'Pudong New District', 'Shanghai', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('hkhkgnew8', NULL, 'hkhk2969', NULL, '2018-03-02 22:29:41', 'liu哥', 'liu哥', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/tDVFR5S2r4D02iabHo2DTNxwB8IHq8chPLkxB6uD8HFH1w4Z4E4txI1TDJ33tkZVDMgRf98q2q2gJ5FcQPueVmw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('hwdj192a8', NULL, 'hwdj4788', NULL, '2018-03-04 14:22:52', '广州骏兴鹏悦专修文小姐', '广州骏兴鹏悦专修文小姐', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/VHA7iaV6BNzWMMictja05upenVe7eyYG8Ptpud6aVr9D5ehico8jya7kfDoNgWMkzvPCPrBicibbFZYDBYYmTQKgwOQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('i7bh96gzr', NULL, 'i7bh8p83', NULL, '2018-03-05 01:38:38', '王林', '王林', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/qMovQCbGxDAOIgSvq95aibspvicHZliaYKALWH43hcHdFaGXSFsbibGkCrMHdOtiaVo7lFYGzzSbibkP3c7lll1IOvkA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ibbt425d1', NULL, 'ibbtib57', NULL, '2018-03-05 20:01:24', '大葱  ', '大葱  ', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoyCaZup9Mo42MqeogzbxZYmA2BjChlBwPqvzu9ZLP8uxcCLOvWTJYbUB3haOBy3b9Ge61TKsobxQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ikkk5jz8a', NULL, 'ikkk4176', NULL, '2018-03-06 20:30:40', 'D滄岐', 'D滄岐', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/iagw2MeBOA2bB3JVC85NK3ZDicMkc6YyaWbgJIrbMiaF67JsobgKAzp5JpTTuhkuUsDibUiaJdGYiaWxAfb6vyBwpTEA/0', 'China', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('it1gus191', NULL, 'it1gxw37', NULL, '2018-03-06 00:58:11', 'NUC TV-ROH(卢少强)', 'NUC TV-ROH(卢少强)', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Dea62GLJhFpuqAQlU4AXGNeKrcHcdVlicB4JoRQE0xLPgNqNzia1x0ZKIB9HlAgYUrEibb2GleKJfNP2V8MKvXCOw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('j9ctb8bm6', NULL, 'j9ctb153', NULL, '2018-03-07 22:00:34', '程', '程', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/E9ngLh0KSnTfmPjDR9VdmibRUrZkJqWDDbLXq6CxBIjnicIqDMUpL3KwJUExawNEgMtCYLrNbBDIqZQRq2WiaKiawQ/0', 'PS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('jnwtw2uwb', NULL, 'jnwt8h85', NULL, '2018-03-08 13:18:10', '？冯梓森？', '？冯梓森？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLHoib3gj4NqmuVCQa2vnleI6ialjrrPNmcPS1u2cATibLxSfRkRFSNpkfb5ua3oFofGttaBawvBUPSg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('jqsy53x2u', NULL, 'jqsyi486', NULL, '2018-03-09 20:38:55', 'Mr.黄', 'Mr.黄', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/glMH9jUOFSicCAXKxzxv0zU4JZD7peNsFyc77ggRxLSxHxTQE4oxEYnpwIy8uDSClb2ctKVyaSEeKLIsEurAs7w/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('jwiti6f3b', NULL, 'jwit3h12', NULL, '2018-03-08 23:07:10', 'LEE～？？', 'LEE～？？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eopo5ibXHcXtuaIg5xfNIfTzO1oeP4UVOzFVajpUalicXEulRtRHMpEM8oY0Tu4eJPVyzdnaPoC8lCA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('k3h63337u', NULL, 'k3h6w775', NULL, '2018-03-13 04:04:47', 'Bingo', 'Bingo', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/rp4leprLrAVGPmIGibMiaDHs5N6JnAuib9qKnsQuBos0AEt3hic1OEJG16mFicnGZiaFGzmYwAlrvbQeIjIl1md4mH1w/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('k7nk314cm', NULL, 'k7nkgi99', NULL, '2018-03-10 14:42:40', '橄榄', '橄榄', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/q146dnhv93TdLDNwSq5JcIRichmevDVkfHrx8iaiaxmDaBZljsF7cMZxTt0QpE1U3PYIiaJyCayicSOmjsswuSgVeKg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('kb9kf2758', NULL, 'kb9ka569', NULL, '2018-03-11 22:02:54', '杯酒挽歌和', '杯酒挽歌和', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/1SeE2BtwCFaW7aCasb1Chicyibg6nZR2wgyh3g5OgGZHSELdq95NF63Fx9ByfPGPib3XEA32C6icVSYvwSaf7VVv5A/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('kbh536r1g', NULL, 'kbh5n589', NULL, '2018-03-10 18:25:10', '广州百度推广阿聪', '广州百度推广阿聪', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/ibWrDpZWQ7pzvIvuloKkTypNpuVwYR9NUvOYWkwGpwFIarAMY4ico6hu30NI8vRsodEob3RwmmJia8CkIzSmE51Xg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('kcnn5a2x6', NULL, 'kcnnvh41', NULL, '2018-03-11 23:48:46', '崔健', '崔健', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIwibXqnph3Ndhwic9JVMhoa5jZBUTu5nOaATDALztWiagnOtALpMq90Z0NVvm5PpgjsiaGDZ6Hlq1nCw/0', 'China', 'Jiaozuo', 'Henan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('khpeeb9r5', NULL, 'khpebv14', NULL, '2018-03-11 00:58:25', 'A？断？？晴？？音', 'A？断？？晴？？音', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/uD8lc7kWqCbnNmUNndu94HSwM2pfDbaRj1PNSxhqibM9kwwEnyx8phJ53xBQ0dAKibfsibTaNHfeUv765QfLlibosQ/0', 'China', 'Changde', 'Hunan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('kwjcs2yvy', NULL, 'kwjc9491', NULL, '2018-05-22 10:08:13', NULL, '阮奕劲', '13631444574', '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '441702198308251739', NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('M_U3AWMIV8148', 'M_U3AWMIV8148', 'M_U3AWMIV8148', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, NULL, '0', 'platform-branch-001', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, 'mg_001', '一星学徒', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('M_U3AWMIV8148', 'M_U3AWNVJK276', 'M_U3AWNVJK276', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, NULL, '0', 'demo-branch-01', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, 'mg_001', '一星学徒', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('pfik322p6', NULL, 'pfik1287', NULL, '2018-03-24 18:10:15', 'Spring', 'Spring', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/hKb5A121ibQ7ZkEbMnw4zNQnEfHvdCsjwNzgNlQ6EIG0K4kS7lZicjDabaf6zufKribCASNkI3tC01tPAhPQvOMUA/0', 'Belgium', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('platform-branch-001', 'platform-branch-001', 'platform-branch-001', '0', '2022-11-29 04:22:14', 'platform-branch-001', '平台管理机构管理员', '13610336198', '$2a$10$8.ITJLJi3W3t4jPoq3KO9eZBOe3j47brEYR7U.NLcX3H8mkdBNQpW', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2021-09-15 00:00:00', NULL, 'aaa@1444.com', NULL, NULL, NULL, NULL, NULL, 'b1fb87af-743d-11ea-acc5-525400033a00', '2', '2', NULL, NULL, NULL, NULL, NULL, '2023-09-29 13:37:04', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('pqfbsan8c', NULL, 'pqfb6u65', NULL, '2018-03-25 05:25:18', 'rdgztest_OKCKNZ', 'rdgztest_OKCKNZ', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('rckm3sfmu', NULL, 'rckmat42', NULL, '2018-03-30 03:24:25', 'rdgztest_DWOMTK', 'rdgztest_DWOMTK', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('rckm8h6gn', NULL, 'rckmt127', NULL, '2018-03-30 03:24:25', 'rdgztest_DWOMTK', 'rdgztest_DWOMTK', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('rsvdaqc1n', NULL, 'rsvd8785', NULL, '2018-03-30 20:40:42', '小咪咪', '小咪咪', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTITvTdYyRiaQ4oCFMSv7OCbD7EZSwjpsyvFl8hIdAq6BbOHSkI8iaiaSbEKylibOYxiay8mSicnpjzSdVtw/0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('M_U3AWMIV8148', 'superAdmin', 'superAdmin', '0', '2020-04-18 03:10:49', '超级管理员', '超级管理员', '', '$2a$10$J1o3LhU/lOteD6zOntwZXer2OCTRv78wMDFTQZghe9XACRLRYms7u', NULL, NULL, 'https://www.qingqinkj.com/api/m1/arc/arc/image/platform-branch-001/mdp/IM1634626368443187.png', NULL, NULL, NULL, NULL, NULL, NULL, '0', 'chentian-001@163.com', NULL, NULL, NULL, NULL, NULL, '78f4ccac-80e0-11ea-9e68-525400033a00', '2', '2', NULL, NULL, '3', NULL, NULL, '2024-06-14 12:10:12', NULL, 'platform-branch-001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tbbr8w885', NULL, 'tbbrp336', NULL, '2018-04-05 18:07:42', '阿甘正传', '阿甘正传', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/kotIicwReiaS6u6Oic10X7ZJnqduJia7LyKA1pxfxUmFEib3qrmhd3HhvvZxhDPChnts7Y0icRDSozy79VWicrlKePESQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tbsditasm', NULL, 'tbsd5893', NULL, '2018-04-05 18:38:54', '阿兵——', '阿兵——', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/HUVVILan1OMIsJfPeGXDTI4Imhop60SWCZdzciafsAfsnDZ4xBqbDDOXr3BxvUzSbxBuNCSqIC7y1kCTCc3V2SA/0', 'China', 'Shenzhen', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('3abLkUb09', NULL, 'tdrbcf86', NULL, '2018-04-05 20:19:06', '唐春艳Xuan Di？m', '唐春艳Xuan Di？m', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/bWDuF49BicBQFHahzaPJ09q3u4OHoUmEibTiciaA8pn66iaibazCaCxyJud2CUeaItHNMBmuFVYoQfXOWBgOAP7ucE0w/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tdyeaagr7', NULL, 'tdye7h81', NULL, '2018-04-04 16:40:39', '黄学富', '黄学富', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/K9A8dzIo4lVwNpf1EWB0tZbCXSTWe1zPWzwpGHz5XFXY2PD9mUnkBicAOXkXcQYs0viahjVWMOTIYFm7ibpwMSKMg/0', 'China', 'Chengdu', 'Sichuan', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('th8cruyt6', NULL, 'th8c8p41', NULL, '2018-04-06 00:07:29', 'fengstyle', 'fengstyle', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/p2Z4oMDCq0DFY0fb3ATt12AsQQU2ic4BghfIIzgUky0hZjFBVsJr4KpicSm8TTlQK7M3bNmseIfpesxAkElwNA0w/0', 'China', 'Jinan', 'Shandong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ttbbu4j43', NULL, 'ttbb2489', NULL, '2018-04-05 10:01:59', '梁智云', '梁智云', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/jXJ6ucichYqS0zv9Qz78nB0sm6U8UeiaiaLgxxTv6OlIUnRNJpZngV8zVvNHnBGfuia5fHE5Mnriafn9iajn8pOh7bvA/0', 'China', NULL, 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ttffxvzbd', NULL, 'ttffid38', NULL, '2018-04-05 09:52:32', 'chenfeng', 'chenfeng', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/1iak5k4nCzDDfeAqzWNtcl41Z7kbHx7mGdQYl3JBDEWxBeJ6p1QLLFmxRhxBj2hwFLlqvyGPzQl7qZgMh3xwzdw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ttjy38219', NULL, 'ttjy8681', NULL, '2018-04-05 09:56:59', '覃斌', '覃斌', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er3l7a6j6Y03qr0LoCaFNWZgCGLUPhmb09sibCm9iaqZbiboAicp7hpdwMGA1CWvyNY1cfjg0aVkHHCRg/0', 'China', 'Nanning', 'Guangxi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('ttzy684s3', NULL, 'ttzygg22', NULL, '2018-04-05 09:47:05', 'яūyì？？', 'яūyì？？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/RBupHuR0WlhrbNvhtVs31kvichB9bet6O9iavCj7xWaLG69kxQ0iboys9lIWhLHvpx9x3DYlIPOsP9kPzI7ibUgcSg/0', 'Iceland', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tuat513it', NULL, 'tuat8f47', NULL, '2018-04-05 10:51:40', '胧', '胧', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/TDHDFtQKUMsApxAYtIO5fNoMblCKqKrbiaArayAAdz0PtYVv9tFKeL5TrCicfGC5NZ14WkHlxIrvLIp1SvPlUS1A/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tuii86i94', NULL, 'tuii8775', NULL, '2018-04-05 10:59:45', '路人戊@此号在线', '路人戊@此号在线', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/OefmXGrI5KCrIumQzbr1J78jOzXqj5Nh9Odz3criaYwTaWx4caTj3OEPhU6txf8r88Se4fhos8LVncGkWENz41w/0', 'China', 'Langfang', 'Hebei', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tuxwvkwub', NULL, 'tuxw1d56', NULL, '2018-04-05 11:15:29', '胡涛', '胡涛', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epcvjnHmum2zHleOYc78ialQrt5dr7EzFPfPpyrQicQ6wstSnylXLtNDfdPtz8JDiczAQendcLbke4VQ/0', 'China', 'Shenzhen', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tv699y842', NULL, 'tv699v88', NULL, '2018-04-05 11:24:24', '绿色微信', '绿色微信', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83er4vCQfKrNv3wkHatgmEgL7gRtYyabYC5OySIHww9S9Np9CNmjiaRygFT3kNCgXcdz7jibQlZVp8jNQ/0', NULL, NULL, '腾讯官方认证？', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tv9btarat', NULL, 'tv9b4732', NULL, '2018-04-05 11:27:32', '黄狗-？？都吃不饱', '黄狗-？？都吃不饱', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLIb1liabicB3qxeDSkWT5fDib59CNyJabh9h5RTodffalkiaw28mQZ4f22eqDEdtmR9Xd49dqj54ztTg/0', 'AD', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tvbjwszis', NULL, 'tvbj4y75', NULL, '2018-04-05 12:10:28', '清扬木坊', '清扬木坊', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/AsnMoqia4nqTFHLcEUgLJwoOV65tDO1HGv2XZa8Z1eoGR6SwUdNadFgIU5D1ew8dLCnC2gE7Z9AjaPQFzEibrSrw/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tvmk958t9', NULL, 'tvmk8b49', NULL, '2018-04-05 12:08:00', '心竹', '心竹', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/QvMAibr5wkBY4rWJu2hOkPgKy8TKPmkXRbpJfqNvPwWlyTaxtb1v100T1UYxqw2GbrzpRtDCHa75m8k1SFX9ibTQ/0', 'China', 'Nanning', 'Guangxi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tvyp4824d', NULL, 'tvyp3u71', NULL, '2018-04-05 12:20:28', 'SXQ', 'SXQ', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/mC64pXCj1pRBMahouialUL50nT5eIhVWqGqhQmERq9ia6oZ4BBuWJus9Idqy5hGkNEoMzOP9mSAadBwzQJ5TICmw/0', 'China', 'Baoji', 'Shaanxi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('twbb2b8xb', NULL, 'twbb1244', NULL, '2018-04-05 13:00:45', 'L.Y.？？', 'L.Y.？？', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqc5QopCR6BNhJ4ib51Mbbuf71LB7aEdK8tib4DKV7O5KribJlF5AQcuZgnvrdq1RsbebibVnZrncoYeA/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('tztmhb8q8', NULL, 'tztm6852', NULL, '2018-04-05 16:05:05', '张生：宏仁彩钢板', '张生：宏仁彩钢板', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJnkE6UvGFfMQevMStrV05tfQA9oupAOquicr5RyXYNIWtzO3qZX3GdRDdHSLHCsO3XBaiaFICGu7lg/0', 'China', 'Dongguan', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('vk7a3y3e4', NULL, 'vk7a4b18', NULL, '2018-04-10 12:04:59', '野马赢家之王', '野马赢家之王', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7AatzrjqPLibibw0p41OMjJJwtgceBwOEqcSmjRDdbfneib6ibrgwDOX7y4Q0TNCDQDXm05YekicV2aA/0', 'China', 'Yichun', 'Jiangxi', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('vm8cwdbwv', NULL, 'vm8c8t12', NULL, '2018-04-11 17:59:55', '郭青飞', '郭青飞', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/mUwVldRL917OWXus4HMDng3AoOaE58YaJpwXF0IdOOGun5qVe2QGgicFjPeYtUw8lRicabCvCmIvzdvuSiaAV6QYg/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('pckkp2716', NULL, 'w2na1838', NULL, '2018-04-12 11:20:53', '_M@', '_M@', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/jaicOkqsB6LL08AEsR3T2J97nmfsWG5WwKTcYovkjBGMwHnAO2vevDR7S3QBNVpialxmkz6c3J6IYPwmGE1uZxjQ/0', 'China', 'Guangzhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('wegp453jx', NULL, 'wegp6f23', NULL, '2018-04-13 00:29:08', '.', '.', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/249LPSDdbibDEJ9wy4oRLBvekQhE5bblr6pWw90M3rfMjsPlOrteCic1LOEKOVnhjbPahGuVEltqTureswVZ2GtA/0', 'Albania', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('wfxhsq874', NULL, 'wfxhd694', NULL, '2018-04-13 01:50:38', '我的拉不拉不多', '我的拉不拉不多', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKvJiaNbED0ibpX5UIY1UHorILiaPia14iaOGqYUib2f674icpK2X9Uwa1crvDbmPD4FErFKqy0ibwgOW34tw/0', 'China', 'Meizhou', 'Guangdong', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('wpagi7rr7', NULL, 'wpagh118', NULL, '2018-04-14 15:26:49', '酷酷的老友-包包干官方', '酷酷的老友-包包干官方', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIGZXeX3JXwpU1xKUDZQZPG0dUbThzX4xl1RuIkMulFYwFLCuuMUN4ySuaa56cGO2He1TBkfeaDkw/0', 'Northern Mariana Islands', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('U1649452951293', 'xx', 'xx', '', '2022-04-04 00:00:00', 'xx', 'xx', '', '$2a$10$P1upP2zoZIRFCbAwELxr1enlUXpYcFg6SHWisKQ9FbCcuKUGi8X1.', '', '', '', '', '', '', 'xx', '0', '2022-04-30 00:00:00', '', '', NULL, NULL, NULL, '', '', NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('zszz3p89a', NULL, 'zszzv487', NULL, '2018-04-21 21:55:50', '？？KAHO', '？？KAHO', NULL, '$2a$10$ZX3Q3lbZ88wqMsQj24hQ8urgtj2mWHqwUlWBZy6nZ20PUP/HxtjX2', NULL, NULL, 'http://thirdwx.qlogo.cn/mmopen/lCCHeDuA87cc0bSWB8a1ymBo3eLgh4RZUjzfy2Gqd6cqpz00rbJ0K3ibkKSztueVjqZZRFr7e4ic7e5iaTz6EFZBnSC4hhT7ibQQ/132', '爱尔兰', NULL, '西米斯', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2', NULL, NULL, NULL, NULL, NULL, '2022-11-04 04:36:40', NULL, 'qqkj_001', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_credit_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_credit_record`;
CREATE TABLE `sys_user_credit_record`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `score` int NULL DEFAULT NULL COMMENT '信用得分',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '信用得分变化说明',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '引起变化的业务主键，比如任务编号等',
  `biz_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '引起编号的业务分类1-个人实名认证，2-企业认证，3-银行卡认证，4-手机号认证，5-邮箱认证，10-加入三保服务保障，11-完成平台任务，12-邀请用户实名注册，13-邀请用户完成任务。14-其它',
  `direct` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '变化方向0-减，1-增加',
  `ctime` datetime NULL DEFAULT NULL COMMENT '变化时间',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户信用得分记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_credit_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_del_backup
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_del_backup`;
CREATE TABLE `sys_user_del_backup`  (
  `UNIONID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '全局唯一编号',
  `DISPLAY_USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '登录展示使用用户编号',
  `USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT ' ' COMMENT '内部用户编号',
  `LOCKED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否被锁定0否1是',
  `STARTDATE` datetime NULL DEFAULT NULL COMMENT '启用日期',
  `NICKNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '昵称',
  `USERNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `PHONENO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '移动电话号码',
  `PASSWORD` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '密码',
  `SALT` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '盐值',
  `PWDTYPE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '密码类型1指纹2密码',
  `HEADIMGURL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '头像地址',
  `COUNTRY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '国家',
  `CITY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市',
  `PROVINCE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省份',
  `ADDRESS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '详细地址',
  `SEX` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '性别',
  `ENDDATE` datetime NULL DEFAULT NULL COMMENT '到期日期',
  `DISTRICT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '区县编号',
  `EMAIL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邮箱',
  `FG_ONE` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹1',
  `FG_TWO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹2',
  `FG_THR` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹3',
  `ID_CARD_NO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `office_phoneno` varbinary(20) NULL DEFAULT NULL COMMENT '办公室电话',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
  `del_time` datetime NULL DEFAULT NULL COMMENT '删除时间',
  `del_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-待处理1-已处理',
  PRIMARY KEY (`USERID`) USING BTREE,
  INDEX `USERID`(`USERID` ASC, `LOCKED` ASC) USING BTREE,
  INDEX `DISPLAY_USERID`(`DISPLAY_USERID` ASC, `LOCKED` ASC) USING BTREE,
  INDEX `PHONENO`(`PHONENO` ASC, `LOCKED` ASC) USING BTREE,
  INDEX `SYS_USER_INDEX_OPENID`(`DISPLAY_USERID` ASC) USING BTREE,
  INDEX `SYS_USER_INDEX_UNIONID`(`UNIONID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_del_backup
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept`  (
  `USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '部门编号',
  `ENABLED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否启用',
  `SEQ` int NULL DEFAULT NULL COMMENT '顺序号',
  PRIMARY KEY (`USERID`, `DEPTID`) USING BTREE,
  UNIQUE INDEX `SYS_USER_DEPT_I`(`USERID` ASC, `DEPTID` ASC) USING BTREE,
  INDEX `DEPTID`(`DEPTID` ASC) USING BTREE,
  CONSTRAINT `sys_user_dept_ibfk_1` FOREIGN KEY (`DEPTID`) REFERENCES `sys_dept` (`DEPTID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户部门关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
INSERT INTO `sys_user_dept` VALUES ('125ynaq74', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('125ynaq74', 'platform-dept-001', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('12fwb1378', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('12fwb1378', 'platform-dept-001', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('16iqa8448', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('16itvfd67', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('16itvfd67', 'platform-dept-001', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('170820220430', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('170820220430', 'platform-dept-001', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('170827010703', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('4fr8hc696', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('4hinb8m16', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('chenkun', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('danyan-01', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('demo-branch-01', 'DE1665049960457133', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('dujj-01', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('maimengxiangmu@126.com', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('maimengxiangmu@126.com', 'platform-dept-001', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('minjie', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('renli', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('vybe8882', '4fr8gart8x', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('vybe8882', 'platform-dept-001', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('xxxxxxxxxxx', 'platform-dept-001', '1', NULL);
INSERT INTO `sys_user_dept` VALUES ('覃斌', 'platform-dept-001', '1', NULL);

-- ----------------------------
-- Table structure for sys_user_fans
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_fans`;
CREATE TABLE `sys_user_fans`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '我的账户',
  `fuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '我关注的用户，如果是企业则为企业的管理员账户',
  `ftime` datetime NULL DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`userid`, `fuserid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_fans
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_focus
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_focus`;
CREATE TABLE `sys_user_focus`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '我的账户',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '关注对象的主键',
  `pbiz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '关注对象的父主键',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '名称',
  `ftime` datetime NULL DEFAULT NULL COMMENT '关注时间',
  `ubranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户归属机构号',
  `pbiz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级名称',
  `biz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关注对象的名称',
  `focus_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关注对象类型对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代-6/团队-7/组织-8/个人-9/文章-W/合同-H/客户-K',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '回调地址',
  PRIMARY KEY (`userid`, `biz_id`, `pbiz_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_focus
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_form_test
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_form_test`;
CREATE TABLE `sys_user_form_test`  (
  `UNIONID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '全局唯一编号，也叫主账户，同一个人（比如同一个微信号，同一个邮箱，同一个手机号视为同一个人）。同一个人在mdp有唯一的主账号。\r\n一个主账户下根据不同的机构设立不同的子账户。\r\n\r\n如果使用主账户登录，需要选子账号。\r\n如果使用子账户登录，不需要选，直接登录。\r\n子账号可以事后绑定主账号\r\n子账号绑定主账户的userid.\r\n主账户的unionid=userid。必须相等',
  `DISPLAY_USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '登录展示使用用户编号',
  `USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT ' ' COMMENT '内部用户编号(账户编号)，如果是机构管理员账户，则=机构号',
  `LOCKED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否被锁定0否1是',
  `STARTDATE` datetime NULL DEFAULT NULL COMMENT '启用日期',
  `NICKNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '昵称',
  `USERNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `PHONENO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '移动电话号码',
  `PASSWORD` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '密码',
  `SALT` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '盐值',
  `PWDTYPE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '密码类型1指纹2密码',
  `HEADIMGURL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '头像地址',
  `COUNTRY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '国家',
  `CITY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市',
  `PROVINCE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省份',
  `ADDRESS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '详细地址',
  `SEX` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '性别',
  `ENDDATE` datetime NULL DEFAULT NULL COMMENT '到期日期',
  `DISTRICT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '区县编号',
  `EMAIL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邮箱',
  `FG_ONE` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹1',
  `FG_TWO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹2',
  `FG_THR` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹3',
  `ID_CARD_NO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `office_phoneno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '办公室电话',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
  `mem_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '从平台角度看会员类型0-个人账户、1-企业管理员账户、2-企业员工账户，个人账户无须绑定机构号，个人子账户可升级为企业员工账户，企业账户必须绑定机构编号branchId个人账户升级后，保留个人主账户，个人子账户绑定企业编号成为企业员工账户',
  `org_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备用，暂时不用',
  `email_bak` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备用邮箱',
  `pwd_strong` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '1-高风险，2-中风险，3-低风险',
  `lock_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '锁定类型:0-注册等待邮箱验证，1-注册等待修改初始密码，2-注册等待验证手机号码，3-密码高风险，等待重新修改密码，9-业务需要锁定禁止登录，10-账户被锁定，请联系客服',
  `lock_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '锁定原因',
  `ltime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  `atype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对于平台来说的账户类型0-子账户，1-主账户。\r\n主账户指个人在平台注册的全域唯一的账户，一个主账户下挂若干子账户，无论是主账户还是子账户都指向同一个主账户，他们都是指同一个人,相同的手机号、邮箱、微信号、身份证上述任意一个相同都代表是同一个人.\r\n同一个自然人只有一个主账户，但是可以拥有无数的子账户。\r\n主账户用于利用微信公众号、支付宝等公共资源（包括不限于收发短信、支付等）\r\n主账户无须指定归属机构号,为方便升级成机构账户，虚拟一个机构号给主账户\r\n\r\n子账户指各个机构下创建的员工账户，他们具有独立的归属机构，子账户权限范围仅限所归属的机构范围。子账户指向主账户。 \r\n子账户必须指定归属机构号。\r\n个人可以以主账户登录，也可以以子账户登录。子账户管理权属于归属机构，子账户可以自行修改子账户信息，主账户无权删除子账户。\r\n子账户通过unionid关联主账户',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构主子账户归属的机构编号,如果是个人，这里填虚拟机构编号，作为虚拟的机构号，方便将来升级成企业号',
  `continent` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '洲别',
  `cpa_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '从入驻企业角度看协作类型0-企业内部人员，1-客户，2-供应商，3-上级机构，4-下属机构',
  `cpa_org` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作组织编码',
  `roleids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '个人账户拥有的角色，逗号分割',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `shop_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '商户编号',
  `profe_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业编号',
  `profe_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业名称',
  `grade_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '等级会员，根据经验值而定',
  `grade_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '等级会员名称',
  `ilvl_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权益等级青铜、白银、黄金、紫金、钻石',
  `ilvl_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权益等级名称',
  `istatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '会员权益状态0-无效，1-有效，2-过期',
  `istime` datetime NULL DEFAULT NULL COMMENT '权益开始时间',
  `ietime` datetime NULL DEFAULT NULL COMMENT '权益结束时间',
  `valid_lvls` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段\r\n验证级别列表逗号分割多个，0-验证不通过，1-验证通过 ，2待审核。按顺序位置分别代表1-实名（身份证），2-手机号码，3-邮箱，4-营业执照，5-法人实名\r\n1,2,3,4,5\r\n比如0,0,0,0,0所有验证都不通过。\r\n比如1,1,1,1,1所有验证通过，\r\n比如0,1,1,0,0代表实名身份证验证不通过，法人实名认证不通过\r\n比如0,0,0,1,2代表实名认证待审核，企业法人实名认证待审核',
  `features` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '个性化签名',
  `profe_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业类型1-开发类，2-测试类，3-设计类，4-管理类；\r\n多选，逗号分割',
  `ustatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户账户状态0-初始，1-起效，2-注销申请，3-注销后删除',
  `credit_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '信用等级编号',
  `credit_score` int NULL DEFAULT NULL COMMENT '信用等级分数',
  `guard_id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务保障等级0-初始，1-金，2-银，3-铜',
  `open` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否对互联网用户开放查询0-否1是',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '简介备注',
  `biz_hours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '营业时间说明09:00-12:00 14:00-19:00',
  `skill_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能编号列表',
  `skill_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能名称列表',
  `def_login` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否默认账户0-否，1是，在多个账户存在的情况下，默认取1的账户优先登录',
  `cpa_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作组织用户编号',
  `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '拓展字段',
  PRIMARY KEY (`USERID`, `branch_id`) USING BTREE,
  UNIQUE INDEX `USERID`(`USERID` ASC, `LOCKED` ASC) USING BTREE,
  UNIQUE INDEX `DISPLAY_USERID`(`DISPLAY_USERID` ASC, `LOCKED` ASC) USING BTREE,
  INDEX `PHONENO`(`PHONENO` ASC, `LOCKED` ASC) USING BTREE,
  INDEX `EMAIL`(`EMAIL` ASC) USING BTREE,
  INDEX `SYS_USER_INDEX_UNIONID`(`UNIONID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_form_test
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_grade_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_grade_record`;
CREATE TABLE `sys_user_grade_record`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `score` int NULL DEFAULT NULL COMMENT '能力得分',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '能力得分变化说明',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '引起变化的业务主键，比如任务编号等',
  `biz_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '引起变化的业务分类 1-完成任务 ',
  `direct` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '变化方向0-减，1-增加',
  `ctime` datetime NULL DEFAULT NULL COMMENT '变化时间',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属业务日期yyyy-MM-dd类型',
  `at` decimal(10, 2) NULL DEFAULT NULL COMMENT '金额',
  `exp` int NULL DEFAULT NULL COMMENT '经验',
  `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态',
  `biz_month` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务月份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户能力等级得分记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_grade_record
-- ----------------------------
INSERT INTO `sys_user_grade_record` VALUES ('US1713251644098148', 'M_U3MJHVRA582', NULL, '投标获得经验值', 'T3TBABFT1MX8', NULL, '1', '2024-04-16 15:14:04', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713251651371152', 'M_U3MJHVRA582', NULL, '投标获得经验值', 'T3TBABFT5C36', NULL, '1', '2024-04-16 15:14:11', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713251656952185', 'M_U3MJHVRA582', NULL, '投标获得经验值', 'T3TBABFT9822', NULL, '1', '2024-04-16 15:14:17', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713260692546119', 'M_U3MECZ2Z195', NULL, '投标获得经验值', 'T3TSIBNA7STV', NULL, '1', '2024-04-16 17:44:53', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713363482686123', 'M_U3MECZ2Z195', NULL, '投标获得经验值', 'T3TZGPBZ2195', NULL, '1', '2024-04-17 22:18:03', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713363487430183', 'M_U3MECZ2Z195', NULL, '投标获得经验值', 'T3TZGPBZ829P', NULL, '1', '2024-04-17 22:18:07', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713363492403178', 'M_U3MECZ2Z195', NULL, '投标获得经验值', 'T3TZGPBZPP43', NULL, '1', '2024-04-17 22:18:12', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713363496400149', 'M_U3MECZ2Z195', NULL, '投标获得经验值', 'T3TZGPBZYM4V', NULL, '1', '2024-04-17 22:18:16', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713363932442146', 'M_U3MJHVRA582', NULL, '投标获得经验值', 'T3TZHHYB72P4', NULL, '1', '2024-04-17 22:25:32', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713363936721196', 'M_U3MJHVRA582', NULL, '投标获得经验值', 'T3TZHHYBANTS', NULL, '1', '2024-04-17 22:25:37', NULL, 0.00, 80, '1', '2024-04');
INSERT INTO `sys_user_grade_record` VALUES ('US1713363941690162', 'M_U3MJHVRA582', NULL, '投标获得经验值', 'T3TZHHYBWACA', NULL, '1', '2024-04-17 22:25:42', NULL, 0.00, 80, '1', '2024-04');

-- ----------------------------
-- Table structure for sys_user_interests
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_interests`;
CREATE TABLE `sys_user_interests`  (
  `userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_free` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否付费获取权益 ,0:不付费 1:付费',
  `rtime_rule` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间叠加规则：1.有效期日期后叠加续会时间 2.达到续会之日叠加续会时间 3.永久有效',
  `rtime_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '续会时间类型：1.天数 2.月 3.年',
  `rtime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '下次续会时间yyyy-MM-dd型',
  `itype` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权益分类',
  `shop_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户编号',
  `inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态，0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `smax_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '单个任务最大金额（任务型用户才有）0代表不限制',
  `total_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '累计接单金额（任务型用户才有）0代表不限制',
  `mtype` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用会员类型（2商户型、1普通型、3任务型）',
  `total_exp` decimal(20, 0) NULL DEFAULT NULL COMMENT '累计经验值0代表不限制',
  `smax_exp` decimal(20, 0) NULL DEFAULT NULL COMMENT '单个任务最大经验值0代表不限制',
  `bids` int NULL DEFAULT NULL COMMENT '投标次数上限',
  `sfee_rate` int NULL DEFAULT NULL COMMENT '服务费率0-100之间',
  `ctotal_exp` decimal(20, 2) NULL DEFAULT NULL COMMENT '累计完成工作量',
  `curr_bids` int NULL DEFAULT NULL COMMENT '当前月份投标次数（每月最后一天清零）',
  `ctotal_at` decimal(50, 0) NULL DEFAULT NULL COMMENT '当前累计完成金额',
  `ctotal_bids` int NULL DEFAULT NULL COMMENT '累计投标总数',
  `mfee` decimal(20, 2) NULL DEFAULT NULL COMMENT '月均费用',
  `max_users` int NULL DEFAULT NULL COMMENT '最大人员数',
  `curr_users` int NULL DEFAULT NULL COMMENT '当前人员数',
  `max_rtime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品模块下次续费截止时间yyyy-MM-dd类型',
  `mver` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品版本0免费版1企业版',
  `srv_times` int NULL DEFAULT NULL COMMENT '累计服务次数',
  `id_bak` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '覆盖上一条的等级编号，即变成当前等级之前的等级编号',
  `guard_rtime` datetime NULL DEFAULT NULL COMMENT '服务保障下次续费时间',
  `guard_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务保障名字',
  `up_rate` int NULL DEFAULT NULL COMMENT '好评率',
  `ilevel` int NULL DEFAULT NULL COMMENT '等级高低，数字越高越高级',
  `cmonth_bids` int NULL DEFAULT NULL COMMENT '当月投标数',
  `cmonth_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '当月金额',
  `cmonth_exp` int NULL DEFAULT NULL COMMENT '当月经验值',
  `ctotal_receive_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前收款总额',
  `csix_exp` int NULL DEFAULT NULL COMMENT '六个月经验分',
  `csix_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '六个月金额',
  `csix_bids` int NULL DEFAULT NULL COMMENT '六个月投标次数',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '个人权益关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_interests
-- ----------------------------
INSERT INTO `sys_user_interests` VALUES ('M_U3MECZ2Z195', '2024-04-16 17:44:53', '2024-04-16 17:44:53', '1', '1', '1', '1', NULL, NULL, NULL, NULL, 5000.00, 1000000.00, '3', 1000000, 5000, 30, 15, 0.00, NULL, 0, 5, NULL, 10, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 5, 0.00, 0, 0.00, 0, 0.00, 5);
INSERT INTO `sys_user_interests` VALUES ('M_U3MJHVRA582', '2024-04-16 15:14:04', '2024-04-16 15:14:04', '1', '1', '1', '1', NULL, NULL, NULL, NULL, 5000.00, 1000000.00, '3', 1000000, 5000, 30, 15, 0.00, NULL, 0, 6, NULL, 10, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL, 0, 6, 0.00, 0, 0.00, 0, 0.00, 6);

-- ----------------------------
-- Table structure for sys_user_join_require
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_join_require`;
CREATE TABLE `sys_user_join_require`  (
  `agree` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批状态同意1不同意0',
  `join_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `join_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `join_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '要加入的机构号',
  `join_deptid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '要加入的部门号',
  `create_date` datetime NULL DEFAULT NULL COMMENT '申请加入时间',
  `agree_date` datetime NULL DEFAULT NULL COMMENT '同意时间',
  `join_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0初始1同意2拒绝',
  `join_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '加入理由',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
  `join_user_phoneno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '联系电话',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '企业入驻审核流程' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_join_require
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_login_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_record`;
CREATE TABLE `sys_user_login_record`  (
  `userid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户编号',
  `shop_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '商户编号',
  `location_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '门店编号',
  `login_shopId` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '登录的商户编号',
  `login_locationId` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '登录的门店编号',
  `branch_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `login_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '1-微信扫门店二维码，2-点击小程序登录，3-账户密码登录，4-手机号码登录',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `login_branch_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '登录机构号',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '登录用户名',
  `auth_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '授权码',
  `mdp_appid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'mdp平台appid',
  `lock_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '批处理编号',
  `lock_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '批处理状态0-待处理1-处理中2处理完毕',
  `lock_time` datetime NULL DEFAULT NULL COMMENT '批处理时间',
  `phoneno` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '登录手机号',
  `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '登录ip',
  `user_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'mem-会员端用户，adm-管理端用户',
  `login_device_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '登录设备编号',
  `login_device_sn` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '设备特征码',
  `user_agent` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '请求特征码',
  `req_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '请求特征码-方便前端补充回填部分信息',
  `device_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '设备类型COMPUTER/MOBILE/TABLET/GAME_CONSOLE/DMR/WEARABLE/UNKNOWN',
  `os` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作系统Windows/ios/Android',
  `os_version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作系统版本如Android 8.0',
  `os_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作系统名称如Android 8.x',
  `rendering_engine` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '浏览器渲染引擎如WEBKIT',
  `device_manufacturer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '设备生产厂商',
  `brower_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '浏览器组',
  `border_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '浏览器名称',
  `border_version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '浏览器版本',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `LONGITUDE` decimal(10, 7) NULL DEFAULT NULL COMMENT '经度',
  `LATITUDE` decimal(10, 8) NULL DEFAULT NULL COMMENT '纬度',
  `region_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '区域编号精确到4级镇、街道',
  `region_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '定位街道名称',
  `format_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '定位格式化地址名称',
  `DISTRICT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '定位区县编号',
  `mem_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户类型',
  `login_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '1-登录成功，0-登录失败',
  `login_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '登录成功或者失败的说明',
  `auth_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '授权码',
  `grant_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '授权码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_login_record_req_no`(`req_no` ASC) USING BTREE,
  INDEX `idx_login_record_locationId`(`login_shopId` ASC, `login_locationId` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户登录信息登记' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_login_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `ROLEID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户组编号',
  `USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`ROLEID`, `USERID`) USING BTREE,
  UNIQUE INDEX `SYS_USER_ROLE_I_UR`(`ROLEID` ASC, `USERID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户角色表(作废)' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_skill
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_skill`;
CREATE TABLE `sys_user_skill`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `skill_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '技能编号',
  `skill_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能名称',
  `category_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能分类',
  PRIMARY KEY (`userid`, `skill_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '该表属于oa.hr_user_skill的冗余表，避免跨库查询' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_skill
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_svr
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_svr`;
CREATE TABLE `sys_user_svr`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '服务编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务简介',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '服务价格',
  `pimg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务主图',
  `times` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务时间范围',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0初始1上架2下架',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '简介',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户编号',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '企业服务列表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_svr
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_tpa
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tpa`;
CREATE TABLE `sys_user_tpa`  (
  `UNIONID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '全局唯一编号',
  `OPENID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT ' ' COMMENT '帐户加密后的编号，用于对第三方系统进行开放',
  `LOCKED` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否被锁定',
  `STARTDATE` datetime NULL DEFAULT NULL COMMENT '启用日期',
  `NICKNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '昵称',
  `USERNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `PHONENO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '移动电话号码',
  `COUNTRY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '国家',
  `CITY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市',
  `HEADIMGURL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '头像地址',
  `PROVINCE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省份',
  `MDP_APPID` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前端应用系统编号',
  `APPID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方登录应用系统编号',
  `AUTH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '授权编号',
  `BIZ_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '性别',
  `app_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方应用类型，字典tpa_app_type',
  PRIMARY KEY (`OPENID`) USING BTREE,
  UNIQUE INDEX `SYS_USER_TPA_OPENID`(`OPENID` ASC, `AUTH_ID` ASC) USING BTREE,
  INDEX `SYS_USER_TPA_I_UID`(`UNIONID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '第三方系统向我方开放的用户列表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_tpa
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_tpa_apply
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tpa_apply`;
CREATE TABLE `sys_user_tpa_apply`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请人的用户编号(第三方)',
  `USERNAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `PHONE_NO` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '电话号码',
  `POST_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的岗位名称',
  `BRANCH_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的机构编号',
  `SHOP_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的商户编号',
  `SHOP_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的商户名称',
  `LOCATION_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店编号',
  `BUSINESS_NAME` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店名称',
  `DEPTID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店的部门编号',
  `LONGITUDE` decimal(10, 0) NULL DEFAULT NULL COMMENT '申请的门店的经度',
  `LATITUDE` decimal(10, 0) NULL DEFAULT NULL COMMENT '申请的门店的纬度',
  `PROVINCE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店的省份',
  `PROVINCE_CODE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店的省份代码',
  `CITY` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店的城市',
  `CITY_CODE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '很轻的门店的城市代码',
  `DISTRICT` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店的区县',
  `DISTRICT_ID` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店的区县代码',
  `ADDRESS` varchar(124) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的门店的地址',
  `SOURCE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请来源(从哪个途径申请)',
  `STATUS` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '申请的状态(0.申请中 1.申请通过 2.拒绝)(通过关联门店管理员视图判断，暂时没用)',
  `LOGIN_NO` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '后台登录账号',
  `REMARK` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_DATE` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `UPDATE_DATE` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `CUSERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人id',
  `LOPUSERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最后操作人',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '岗位申请记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_tpa_apply
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_tpa_copy1
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tpa_copy1`;
CREATE TABLE `sys_user_tpa_copy1`  (
  `UNIONID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '全局唯一编号',
  `OPENID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT ' ' COMMENT '帐户加密后的编号，用于对第三方系统进行开放',
  `LOCKED` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否被锁定',
  `STARTDATE` datetime NULL DEFAULT NULL COMMENT '启用日期',
  `NICKNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '昵称',
  `USERNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `PHONENO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '移动电话号码',
  `COUNTRY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '国家',
  `CITY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市',
  `HEADIMGURL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '头像地址',
  `PROVINCE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省份',
  `MDP_APPID` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前端应用系统编号',
  `APPID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方登录应用系统编号',
  `AUTH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '授权编号',
  `BIZ_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '性别',
  `app_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第三方应用类型，字典tpa_app_type,1-微信公众号，2-小程序，3-微信开放平台',
  UNIQUE INDEX `SYS_USER_TPA_OPENID`(`OPENID` ASC, `AUTH_ID` ASC) USING BTREE,
  INDEX `SYS_USER_TPA_I_UID`(`UNIONID` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '第三方系统向我方开放的用户列表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user_tpa_copy1
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_tpa_invite
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tpa_invite`;
CREATE TABLE `sys_user_tpa_invite`  (
  `join_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '被邀请的用户编号，对应sys_user.userid',
  `send_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邀请加入的企业编号',
  `send_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '发起邀请的用户编号',
  `invite_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '邀请编号，带到邀请链接中的state字段，通过该邀请码反查邀请信息',
  `send_branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邀请加入的企业编号',
  `send_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '发起邀请的用户名',
  `send_time` datetime NULL DEFAULT NULL COMMENT '邀请时间',
  `invite_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邀请状态，字典invite_state，0-发起，1-待客户扫码确认，2-已加入',
  `invite_scene` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邀请场景，1-裸邦，创建个人虚拟企业及账户 2-绑定已有用户，3-绑定企业，扫码后创建账户',
  `invite_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邀请类型，1-微信-扫码，2-手机号码+短信验证码，3-邮件+验证码，4-小程序',
  `obj_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邀请对象类型，1-指定用户编号，0-不指定用户编号',
  `join_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '被邀请的用户名称',
  PRIMARY KEY (`invite_id`) USING BTREE,
  UNIQUE INDEX `join_userid`(`send_branch_id` ASC, `join_userid` ASC, `invite_scene` ASC, `send_userid` ASC, `invite_type` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方邀请加入流水表，send_branch_id+join_userid唯一索引' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_tpa_invite
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_tpa_invite_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tpa_invite_link`;
CREATE TABLE `sys_user_tpa_invite_link`  (
  `invite_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '邀请码-对应invite.invite_id',
  `join_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '加入的用户',
  `join_time` datetime NULL DEFAULT NULL COMMENT '加入时间',
  `join_tpa_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '对应sys_user_tpa.openid',
  `join_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邀请类型，1-微信-扫码，2-手机号码+短信验证码，3-邮件+验证码，4-小程序',
  `join_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '加入的机构编号=invite.send_branch_id=join_userid的归属机构号',
  PRIMARY KEY (`invite_id`, `join_userid`, `join_tpa_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邀请对象响应流水表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_tpa_invite_link
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_tpa_link
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tpa_link`;
CREATE TABLE `sys_user_tpa_link`  (
  `sys_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `tpa_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '第三方应用的sys_user_tpa.id',
  `bind_time` datetime NULL DEFAULT NULL COMMENT '绑定时间',
  `bind_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '绑定的机构号=sys_user.branch_id',
  PRIMARY KEY (`sys_userid`, `tpa_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_tpa_link
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_valid_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_valid_code`;
CREATE TABLE `sys_user_valid_code`  (
  `USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '内部用户编号',
  `vali_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '验证码',
  `code_send_time` datetime NULL DEFAULT NULL COMMENT '验证码发送时间',
  `code_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '验证码接收邮箱编号',
  `code_scene` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '验证场景0-重置密码，1-注册验证，2-更换常用邮箱第一步验证旧邮箱,，3-更换常用邮箱第二步验证新邮箱，4-更换常用邮箱第一步验证旧邮箱,，5-更换常用邮箱第二步验证新邮箱，',
  `code_valid_time` datetime NULL DEFAULT NULL COMMENT '验证码验证时间',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '会员表（前端商城 适用 ）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_valid_code
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_valid_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_valid_info`;
CREATE TABLE `sys_user_valid_info`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号，如果是机构就是机构编号对应的用户编号',
  `id_front` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '身份证正面',
  `id_back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '身份证反面',
  `id_hold` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '手持身份证',
  `biz_license` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '营业执照',
  `oths` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '其它逗号分割多个',
  `id_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '身份证号',
  `id_etime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '身份证到期日期',
  `uscc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '统一信用代码号税号营业执照号等',
  `act_bname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构实名名称',
  `act_uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '实名用户名称或者法人名称',
  `ctime` datetime NULL DEFAULT NULL COMMENT '新增时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审核状态0-初始，1-审核中，2-通过，3-拒绝',
  `is_org` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为机构，0-否，1-是，机构指企业认证，个人指实名认证',
  `valid_remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审核原因说明',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属机构编号',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户实名验证资料库' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_valid_info
-- ----------------------------

-- ----------------------------
-- View structure for v_sys_dept_post_user
-- ----------------------------
DROP VIEW IF EXISTS `v_sys_dept_post_user`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_sys_dept_post_user` AS select `dpu`.`ID` AS `ID`,`dpu`.`DEPTID` AS `DEPTID`,`d`.`DEPT_NAME` AS `DEPT_NAME`,`d`.`PDEPTID` AS `PDEPTID`,`d`.`DEPT_TYPE` AS `DEPT_TYPE`,`d`.`STATE` AS `STATE`,`d`.`BRANCH_ID` AS `BRANCH_ID`,`d`.`LEVEL_TYPE` AS `LEVEL_TYPE`,`dpu`.`POST_ID` AS `POST_ID`,`p`.`POST_NAME` AS `POST_NAME`,`p`.`POST_LVL` AS `POST_LVL`,`p`.`POST_TYPE` AS `POST_TYPE`,`dpu`.`USERID` AS `USERID`,`u`.`USERNAME` AS `USERNAME`,`u`.`LOCKED` AS `LOCKED`,`u`.`HEADIMGURL` AS `HEADIMGURL`,`dpu`.`START_DATE` AS `START_DATE`,`dpu`.`END_DATE` AS `END_DATE`,`dpu`.`ACT_END_DATE` AS `ACT_END_DATE`,`dpu`.`ENABLED` AS `ENABLED`,`dpu`.`LAST_DATE` AS `LAST_DATE`,`dpu`.`master` AS `master`,`u`.`DISPLAY_USERID` AS `DISPLAY_USERID` from (((`sys_dept_post_user` `dpu` join `sys_dept` `d` on((`dpu`.`DEPTID` = `d`.`DEPTID`))) join `sys_post` `p` on((`dpu`.`POST_ID` = `p`.`ID`))) join `sys_user` `u` on((`dpu`.`USERID` = `u`.`USERID`)));

-- ----------------------------
-- Procedure structure for clear_data
-- ----------------------------
DROP PROCEDURE IF EXISTS `clear_data`;
delimiter ;;
CREATE PROCEDURE `clear_data`()
begin
  BEGIN	
		delete from sys_post where not exists(select 1 from adm.sys_branch where id=sys_post.branch_id);
  end; 
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for clear_data_after_delete_branch_or_dept
-- ----------------------------
DROP PROCEDURE IF EXISTS `clear_data_after_delete_branch_or_dept`;
delimiter ;;
CREATE PROCEDURE `clear_data_after_delete_branch_or_dept`()
begin
  BEGIN	
		delete from sys_post where exists(select 1 from adm.sys_branch_del_backup where id=sys_post.branch_id);
  end; 
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for pro_up_sys_dept_first
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_up_sys_dept_first`;
delimiter ;;
CREATE PROCEDURE `pro_up_sys_dept_first`()
begin
  declare v_maxlevels int default 20;
 	declare	v_levels int default 1;
  BEGIN	
	 update sys_dept c 
      set c.level_type = NULL,
			    c.id_path = NULL;
  end;
	begin
		update sys_dept c 
       set c.level_type = CONCAT('L',cast(v_levels as char)),
					 c.id_path = CONCAT(c.pdeptid,',',c.deptid,',')
     where c.pdeptid ='A0' COLLATE utf8mb4_croatian_ci;
	end;
	while v_levels < 10 
	DO
	  call pro_up_sys_dept_next(v_levels); 
		 set v_levels = v_levels+1;
  end while;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for pro_up_sys_dept_next
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_up_sys_dept_next`;
delimiter ;;
CREATE PROCEDURE `pro_up_sys_dept_next`(in v_levels int)
begin
       declare done int default 0;
       DECLARE v_id varchar(64) ;
       DECLARE v_id_path varchar(150);
    
       declare cur cursor for select deptid,id_path from sys_dept where  level_type= CONCAT('L',cast(v_levels as char)) COLLATE utf8mb4_croatian_ci;
       declare continue handler for not found set done = 1;
    
       open cur;
    
       repeat
         fetch cur into v_id, v_id_path;
         update sys_dept c
            set c.level_type = CONCAT('L',cast(v_levels+1 as char)),
    						c.id_path =  CONCAT(v_id_path,c.deptid,',' )
          where c.pdeptid =  v_id COLLATE utf8mb4_croatian_ci;
       until done end repeat;
       close cur;
     end
;;
delimiter ;

-- ----------------------------
-- Event structure for event_execute_sys_dept
-- ----------------------------
DROP EVENT IF EXISTS `event_execute_sys_dept`;
delimiter ;;
CREATE EVENT `event_execute_sys_dept`
ON SCHEDULE
EVERY '1' DAY STARTS '2019-07-10 19:00:00'
DO call pro_up_sys_dept_first()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
