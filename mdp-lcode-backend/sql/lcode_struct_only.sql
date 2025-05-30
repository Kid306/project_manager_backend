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

 Date: 31/08/2024 14:14:28
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

-- -- ----------------------------
-- -- Table structure for form_data
-- -- ----------------------------
-- DROP TABLE IF EXISTS `form_data`;
-- CREATE TABLE `form_data`  (
--   `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
--   `TWO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性二',
--   `THREE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性三',
--   `FOUR` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性四',
--   `FIVE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性五',
--   `SIX` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性六',
--   `SEVEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性七',
--   `EIGHT` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性八',
--   `NINE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性九',
--   `TEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性十',
--   `LTIME` datetime NULL DEFAULT NULL COMMENT '最后更新日期',
--   `ONE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性一',
--   `ZERO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '属性零',
--   `REMARK` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注 ',
--   `FORM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单编号',
--   `USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最后更新人',
--   `ELEVEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十一',
--   `TWELVE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十二',
--   `THIRTEEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十三',
--   `FOURTEEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十四',
--   `FIFTEEN` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段十五',
--   `ATT_URLS` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '附件url多个',
--   `ATT_NAMES` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '附件名称多个',
--   `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
--   `BIZ_KEY` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '唯一确定该业务的主键竖线分隔多个',
--   `DEPTID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
--   `FSTATE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0初始1审批中2结束审批',
--   `CTIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
--   `dqx_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据权限码',
--   `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
--   `tag_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签编号列表逗号分割',
--   `tag_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签名列表逗号分割',
--   `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
--   `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
--   `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段json\r\n[\r\n{id:\'\',value:\'属性值\',name:\'属性名称\'},\r\n{id:\'\',value:\'属性值\',name:\'属性名称\'}\r\n]',
--   PRIMARY KEY (`ID`) USING BTREE,
--   UNIQUE INDEX `BIZ_KEY`(`BIZ_KEY` ASC) USING BTREE,
--   INDEX `FORM_ID`(`FORM_ID` ASC, `cuserid` ASC) USING BTREE,
--   INDEX `FORM_ID_2`(`FORM_ID` ASC) USING BTREE,
--   INDEX `FORM_ID_3`(`FORM_ID` ASC, `DEPTID` ASC) USING BTREE,
--   INDEX `USERID`(`FORM_ID` ASC, `USERID` ASC) USING BTREE,
--   INDEX `FORM_ID_4`(`FORM_ID` ASC, `CTIME` ASC) USING BTREE,
--   INDEX `FORM_ID_5`(`FORM_ID` ASC, `USERID` ASC, `CTIME` ASC) USING BTREE,
--   CONSTRAINT `form_data_ibfk_1` FOREIGN KEY (`FORM_ID`) REFERENCES `form_def` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
-- ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单数据表-作废' ROW_FORMAT = COMPACT;
-- 
-- -- ----------------------------
-- -- Table structure for form_data_flow
-- -- ----------------------------
-- DROP TABLE IF EXISTS `form_data_flow`;
-- CREATE TABLE `form_data_flow`  (
--   `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
--   `proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程实例编号',
--   `agree` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批状态同意1不同意0',
--   `assignee` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批人',
--   `main_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程标题',
--   `act_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批节点编号',
--   `task_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批环节',
--   `comment_msg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审批意见',
--   `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
--   `event_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '事件类型create/assignment/complete/delete/PROCESS_CREATED/PROCESS_COMPLETE/PROCESS_CANCELLED',
--   `biz_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键发起时上送，原样返回',
--   `model_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程key，可以根据该key找到对应的流程模型也代表审批事项，就是审什么内容',
--   `flow_last_time` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
--   `flow_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程审批机构号',
--   `flow_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0初始1审批中2审批通过3审批不通过4流程取消或者删除',
--   `start_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '启动人',
--   `proc_def_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程定义编号带版本的',
--   `model_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模型名称，也代表审批事项，就是审什么内容',
--   `form_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单编号',
--   `form_data_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单数据编号',
--   `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
--   PRIMARY KEY (`id`) USING BTREE
-- ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单审核流程表-作废' ROW_FORMAT = COMPACT;
-- 
-- -- ----------------------------
-- -- Table structure for form_def
-- -- ----------------------------
-- DROP TABLE IF EXISTS `form_def`;
-- CREATE TABLE `form_def`  (
--   `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
--   `TABLE_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表名-当dataType=2,3时有效',
--   `DS` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据源-当dataType=2,3时有效',
--   `FORM_NAME` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单名称',
--   `USERID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
--   `DEPTID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
--   `FORM_TYPE` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '表单类型',
--   `TPL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为模板',
--   `BIZ_TYPE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务分类',
--   `CTIME` datetime NULL DEFAULT NULL COMMENT '创建日期',
--   `BRANCH_ID` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
--   `CATEGORY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分类编号',
--   `tag_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签编号列表',
--   `tag_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签名字列表',
--   `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
--   `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
--   `data_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据存储模式1-api,crud，2-指定表格，3-独立表格（表单设计表根根谁自动变更），4-指定数据集，5-外部处理，9-存粹展示',
--   `options` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '表单的扩展字段信息，对应formCreate.option',
--   `funcs` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '函数列表{func1:body1,func2:body2}',
--   `rules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '表单生成规则列表，即页面元素定义列表，对应formCreate.rule',
--   `form_qx` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '表单权限信息json字符串',
--   `flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审核状态',
--   `flow_time` datetime NULL DEFAULT NULL COMMENT '审核时间',
--   `flow_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '审核人',
--   `flow_start_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '发审人',
--   `flow_start_time` datetime NULL DEFAULT NULL COMMENT '发审时间',
--   `proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程实例编号',
--   PRIMARY KEY (`ID`) USING BTREE,
--   INDEX `USERID`(`USERID` ASC, `DEPTID` ASC) USING BTREE
-- ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单定义' ROW_FORMAT = DYNAMIC;
-- 
-- -- ----------------------------
-- -- Table structure for form_field
-- -- ----------------------------
-- DROP TABLE IF EXISTS `form_field`;
-- CREATE TABLE `form_field`  (
--   `FORM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '表单编号',
--   `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键-字段编号对应数据库',
--   `TITLE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段显示内容',
--   `DICT` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '下拉时候关联的分组',
--   `TYP` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段类型',
--   `LEN` int NULL DEFAULT NULL COMMENT '字段长度',
--   `DVAL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '默认值',
--   `MUL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否多选',
--   `REQ` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否必输',
--   `ID_CAMEL` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段驼峰命名',
--   `REMARK` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '字段备注',
--   `TO_FLOW` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否提交到工作流',
--   `BKEY` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为主键0否，1是',
--   `SEQ` int NULL DEFAULT NULL COMMENT '显示顺序',
--   `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属组编号-用于解决多个tab页，或者多个子页面的问题',
--   `show_style` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '展示风格origin-原生、tag-标签、x-综合',
--   `style_obj` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '样式json',
--   `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '扩展字段json\r\n[\r\n{id:\'\',value:\'属性值\',name:\'属性名称\'},\r\n{id:\'\',value:\'属性值\',name:\'属性名称\'}\r\n]',
--   `qx` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '字段权限 json字符串\r\nothQuery:是否进行查询权限检查0-否，1是\r\nqryMinLvl:最低查询级别,\r\nqryRoleids:可查询的角色\r\nqryDeptid:可查询的部门\r\nqryUserids:可查询的人员\r\nnqRoleids:不可查询的角色\r\nnqDeptids:不可查询的部门\r\nnqUserids:不可查询的人员\r\n',
--   `SPAN` int NULL DEFAULT NULL COMMENT '如果上级是row布局，存span属性,\r\n',
--   `vrules` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '验证器列表\r\n[{rule1},{rule2}]',
--   `ext_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '扩展类型user、dept、att、img、row、card、tabs等',
--   `hol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '输入提示，对应placeholder',
--   `gname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分组名称，如果是tabs,这里存tab名称，逗号分割多个\r\n如果上级是tabs,存放tab名称，只存一个',
--   `clazz` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '样式',
--   `ronly` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '只读',
--   `clear` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可清除',
--   `hidden` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否隐藏',
--   PRIMARY KEY (`ID`, `FORM_ID`) USING BTREE,
--   UNIQUE INDEX `FORM_ID`(`FORM_ID` ASC, `ID_CAMEL` ASC) USING BTREE,
--   INDEX `FORM_ID_2`(`FORM_ID` ASC) USING BTREE,
--   CONSTRAINT `form_field_ibfk_1` FOREIGN KEY (`FORM_ID`) REFERENCES `form_def` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
-- ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单字段定义--作废' ROW_FORMAT = COMPACT;
-- 
-- -- ----------------------------
-- -- Table structure for form_qx
-- -- ----------------------------
-- DROP TABLE IF EXISTS `form_qx`;
-- CREATE TABLE `form_qx`  (
--   `FORM_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '表单编号',
--   `QRY_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色查询,号分割',
--   `QRY_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门查询,号分割',
--   `QRY_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人查询,号分割',
--   `NQ_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色查询',
--   `NQ_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门查询',
--   `NQ_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人查询',
--   `OTH_QUERY` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人查询',
--   `OTH_EDIT` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人修改',
--   `OTH_DEL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否允许其它人删除',
--   `LVL_CHECK` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否进行部门级别传递权限检查',
--   `QRY_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别查询权限',
--   `EDIT_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色更新,号分割',
--   `EDIT_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门更新,号分割',
--   `EDIT_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人更新号分割',
--   `NE_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色更新',
--   `NE_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门更新',
--   `NE_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人更新',
--   `DEL_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些角色删除,号分割',
--   `DEL_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许那些部门删除,号分割',
--   `DEL_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '允许哪些人删除,号分割',
--   `ND_ROLEIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些角色删除',
--   `ND_DEPTIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些部门删除',
--   `ND_USERIDS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '禁止哪些人查询',
--   `EDIT_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别更新权限',
--   `DEL_MIN_LVL` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低级别删除权限',
--   PRIMARY KEY (`FORM_ID`) USING BTREE
-- ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '表单权限-作废' ROW_FORMAT = COMPACT;
-- 
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

-- -- ----------------------------
-- -- Table structure for sys_user_form_test
-- -- ----------------------------
-- DROP TABLE IF EXISTS `sys_user_form_test`;
-- CREATE TABLE `sys_user_form_test`  (
--   `UNIONID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '全局唯一编号，也叫主账户，同一个人（比如同一个微信号，同一个邮箱，同一个手机号视为同一个人）。同一个人在mdp有唯一的主账号。\r\n一个主账户下根据不同的机构设立不同的子账户。\r\n\r\n如果使用主账户登录，需要选子账号。\r\n如果使用子账户登录，不需要选，直接登录。\r\n子账号可以事后绑定主账号\r\n子账号绑定主账户的userid.\r\n主账户的unionid=userid。必须相等',
--   `DISPLAY_USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT ' ' COMMENT '登录展示使用用户编号',
--   `USERID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL DEFAULT ' ' COMMENT '内部用户编号(账户编号)，如果是机构管理员账户，则=机构号',
--   `LOCKED` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否被锁定0否1是',
--   `STARTDATE` datetime NULL DEFAULT NULL COMMENT '启用日期',
--   `NICKNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '昵称',
--   `USERNAME` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
--   `PHONENO` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '移动电话号码',
--   `PASSWORD` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '密码',
--   `SALT` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '盐值',
--   `PWDTYPE` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '密码类型1指纹2密码',
--   `HEADIMGURL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '头像地址',
--   `COUNTRY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '国家',
--   `CITY` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市',
--   `PROVINCE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省份',
--   `ADDRESS` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '详细地址',
--   `SEX` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '性别',
--   `ENDDATE` datetime NULL DEFAULT NULL COMMENT '到期日期',
--   `DISTRICT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '区县编号',
--   `EMAIL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '邮箱',
--   `FG_ONE` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹1',
--   `FG_TWO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹2',
--   `FG_THR` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指纹3',
--   `ID_CARD_NO` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '身份证号码',
--   `office_phoneno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '办公室电话',
--   `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
--   `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态',
--   `mem_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '从平台角度看会员类型0-个人账户、1-企业管理员账户、2-企业员工账户，个人账户无须绑定机构号，个人子账户可升级为企业员工账户，企业账户必须绑定机构编号branchId个人账户升级后，保留个人主账户，个人子账户绑定企业编号成为企业员工账户',
--   `org_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备用，暂时不用',
--   `email_bak` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备用邮箱',
--   `pwd_strong` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '1-高风险，2-中风险，3-低风险',
--   `lock_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '锁定类型:0-注册等待邮箱验证，1-注册等待修改初始密码，2-注册等待验证手机号码，3-密码高风险，等待重新修改密码，9-业务需要锁定禁止登录，10-账户被锁定，请联系客服',
--   `lock_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '锁定原因',
--   `ltime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
--   `atype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对于平台来说的账户类型0-子账户，1-主账户。\r\n主账户指个人在平台注册的全域唯一的账户，一个主账户下挂若干子账户，无论是主账户还是子账户都指向同一个主账户，他们都是指同一个人,相同的手机号、邮箱、微信号、身份证上述任意一个相同都代表是同一个人.\r\n同一个自然人只有一个主账户，但是可以拥有无数的子账户。\r\n主账户用于利用微信公众号、支付宝等公共资源（包括不限于收发短信、支付等）\r\n主账户无须指定归属机构号,为方便升级成机构账户，虚拟一个机构号给主账户\r\n\r\n子账户指各个机构下创建的员工账户，他们具有独立的归属机构，子账户权限范围仅限所归属的机构范围。子账户指向主账户。 \r\n子账户必须指定归属机构号。\r\n个人可以以主账户登录，也可以以子账户登录。子账户管理权属于归属机构，子账户可以自行修改子账户信息，主账户无权删除子账户。\r\n子账户通过unionid关联主账户',
--   `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构主子账户归属的机构编号,如果是个人，这里填虚拟机构编号，作为虚拟的机构号，方便将来升级成企业号',
--   `continent` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '洲别',
--   `cpa_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '从入驻企业角度看协作类型0-企业内部人员，1-客户，2-供应商，3-上级机构，4-下属机构',
--   `cpa_org` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作组织编码',
--   `roleids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '个人账户拥有的角色，逗号分割',
--   `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
--   `shop_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '商户编号',
--   `profe_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业编号',
--   `profe_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业名称',
--   `grade_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '等级会员，根据经验值而定',
--   `grade_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '等级会员名称',
--   `ilvl_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权益等级青铜、白银、黄金、紫金、钻石',
--   `ilvl_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权益等级名称',
--   `istatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '会员权益状态0-无效，1-有效，2-过期',
--   `istime` datetime NULL DEFAULT NULL COMMENT '权益开始时间',
--   `ietime` datetime NULL DEFAULT NULL COMMENT '权益结束时间',
--   `valid_lvls` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '人工验证结果，当审核状态为2时，同步到sys_user表同一个字段，或者sys_branch同一个字段\r\n验证级别列表逗号分割多个，0-验证不通过，1-验证通过 ，2待审核。按顺序位置分别代表1-实名（身份证），2-手机号码，3-邮箱，4-营业执照，5-法人实名\r\n1,2,3,4,5\r\n比如0,0,0,0,0所有验证都不通过。\r\n比如1,1,1,1,1所有验证通过，\r\n比如0,1,1,0,0代表实名身份证验证不通过，法人实名认证不通过\r\n比如0,0,0,1,2代表实名认证待审核，企业法人实名认证待审核',
--   `features` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '个性化签名',
--   `profe_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '职业类型1-开发类，2-测试类，3-设计类，4-管理类；\r\n多选，逗号分割',
--   `ustatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户账户状态0-初始，1-起效，2-注销申请，3-注销后删除',
--   `credit_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '信用等级编号',
--   `credit_score` int NULL DEFAULT NULL COMMENT '信用等级分数',
--   `guard_id` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务保障等级0-初始，1-金，2-银，3-铜',
--   `open` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否对互联网用户开放查询0-否1是',
--   `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '简介备注',
--   `biz_hours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '营业时间说明09:00-12:00 14:00-19:00',
--   `skill_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能编号列表',
--   `skill_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能名称列表',
--   `def_login` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否默认账户0-否，1是，在多个账户存在的情况下，默认取1的账户优先登录',
--   `cpa_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作组织用户编号',
--   `ext_infos` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '拓展字段',
--   PRIMARY KEY (`USERID`, `branch_id`) USING BTREE,
--   UNIQUE INDEX `USERID`(`USERID` ASC, `LOCKED` ASC) USING BTREE,
--   UNIQUE INDEX `DISPLAY_USERID`(`DISPLAY_USERID` ASC, `LOCKED` ASC) USING BTREE,
--   INDEX `PHONENO`(`PHONENO` ASC, `LOCKED` ASC) USING BTREE,
--   INDEX `EMAIL`(`EMAIL` ASC) USING BTREE,
--   INDEX `SYS_USER_INDEX_UNIONID`(`UNIONID` ASC) USING BTREE
-- ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

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
