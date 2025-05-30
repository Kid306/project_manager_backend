/*
 Navicat Premium Data Transfer

 Source Server         : 123.207.117.5
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 123.207.117.5:3306
 Source Schema         : xm

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 30/08/2024 10:57:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xm_branch_state
-- ----------------------------
DROP TABLE IF EXISTS `xm_branch_state`;
CREATE TABLE `xm_branch_state`  (
  `project_cnt` int NULL DEFAULT NULL COMMENT '项目数',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `product_budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '产品工时',
  `product_act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '产品实际工时',
  PRIMARY KEY (`branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '机构内所有项目指标汇总' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_branch_state_his
-- ----------------------------
DROP TABLE IF EXISTS `xm_branch_state_his`;
CREATE TABLE `xm_branch_state_his`  (
  `project_cnt` int NULL DEFAULT NULL COMMENT '项目数',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `product_budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '产品工时',
  `product_act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '产品实际工时',
  PRIMARY KEY (`branch_id`, `biz_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '机构内所有项目指标汇总' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_branch_task_type_state
-- ----------------------------
DROP TABLE IF EXISTS `xm_branch_task_type_state`;
CREATE TABLE `xm_branch_task_type_state`  (
  `task_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务类型',
  `plan_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '工作量',
  `plan_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算金额',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际完成工作量',
  `act_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际完成金额',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务日期yyyy-MM-dd型',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '计算日期',
  `plan_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购资金预算',
  `plan_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '内购资金预算',
  `act_out_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际外购成本',
  `act_inner_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际内购成本',
  `plan_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '计划外购工作量',
  `plan_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '计划内购工作量',
  `act_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际外购工作量',
  `act_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际内购工作量',
  `plan_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '计划非人力成本',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际非人力成本',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '按机构编号任务类型汇总' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_budget_labor
-- ----------------------------
DROP TABLE IF EXISTS `xm_budget_labor`;
CREATE TABLE `xm_budget_labor`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目成员编号',
  `budget_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '预算金额/每月',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名',
  `subject_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '预算科目编号',
  `biz_sdate` datetime NULL DEFAULT NULL COMMENT '费用归属周期开始日期',
  `biz_edate` datetime NULL DEFAULT NULL COMMENT '费用归属周期结束日期',
  `biz_month` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '费用归属月份yyyy-mm',
  `INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `cost_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '成本类型0非人力1内部人力2外购人力',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '科目名称',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目归属机构编号',
  `ubranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户归属机构编号-也就是将来的结算对象',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '拆分时填原来的id',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目人力成本预算-作废' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_budget_nlabor
-- ----------------------------
DROP TABLE IF EXISTS `xm_budget_nlabor`;
CREATE TABLE `xm_budget_nlabor`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `budget_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '预算金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `subject_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '预算科目',
  `biz_sdate` datetime NULL DEFAULT NULL COMMENT '费用归属周期开始日期',
  `biz_edate` datetime NULL DEFAULT NULL COMMENT '费用归属周期结束日期',
  `INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `cost_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '成本类型0非人力1内部人力2外购人力',
  `biz_month` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '费用归属月份yyyy-MM',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '科目名称',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目归属企业编号',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '拆分时填原来的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目人力成本预算-作废' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_budget_record
-- ----------------------------
DROP TABLE IF EXISTS `xm_budget_record`;
CREATE TABLE `xm_budget_record`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目成员编号',
  `budget_at` decimal(20, 4) NULL DEFAULT NULL COMMENT '预算金额/每月',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名',
  `subject_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '预算科目编号',
  `biz_sdate` datetime NULL DEFAULT NULL COMMENT '费用归属周期开始日期',
  `biz_edate` datetime NULL DEFAULT NULL COMMENT '费用归属周期结束日期',
  `biz_month` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '费用归属月份yyyy-mm',
  `INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `cost_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '成本类型0非人力1内部人力2外购人力',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '科目名称',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目归属机构编号',
  `ubranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户归属机构编号-也就是将来的结算对象',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '拆分时填原来的id',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务编号',
  `cost_center_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '成本中心-关联到部门.deptid',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `luserid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '更新人编号',
  `lusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '更新人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目成本预算' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_collect
-- ----------------------------
DROP TABLE IF EXISTS `xm_collect`;
CREATE TABLE `xm_collect`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目集编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目集名称',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属机构号',
  `deptid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_collect_link
-- ----------------------------
DROP TABLE IF EXISTS `xm_collect_link`;
CREATE TABLE `xm_collect_link`  (
  `collect_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目集编号',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  PRIMARY KEY (`collect_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_cost_nlabor
-- ----------------------------
DROP TABLE IF EXISTS `xm_cost_nlabor`;
CREATE TABLE `xm_cost_nlabor`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户编号-费用主责人',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `send_time` datetime NULL DEFAULT NULL COMMENT '费用发放时间',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务编号',
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务名称',
  `subject_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '科目编号',
  `biz_sdate` datetime NULL DEFAULT NULL COMMENT '费用归属周期开始日期',
  `biz_edate` datetime NULL DEFAULT NULL COMMENT '费用归属周期结束日期',
  `act_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际成本金额',
  `cost_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '成本类型0非人力1内部人力2外购人力,此表都是非人力',
  `biz_month` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务归属月份yyyy-MM',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务归属日期yyyy-MM-dd',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '科目名称',
  `ubranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户归属机构',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目归属机构',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目实际人工成本费用-作废' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_cost_record
-- ----------------------------
DROP TABLE IF EXISTS `xm_cost_record`;
CREATE TABLE `xm_cost_record`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户编号-费用主责人',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `send_time` datetime NULL DEFAULT NULL COMMENT '费用发放时间',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务编号',
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务名称',
  `subject_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '科目编号',
  `biz_sdate` datetime NULL DEFAULT NULL COMMENT '费用归属周期开始日期',
  `biz_edate` datetime NULL DEFAULT NULL COMMENT '费用归属周期结束日期',
  `act_at` decimal(20, 4) NULL DEFAULT NULL COMMENT '实际成本金额',
  `cost_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '成本类型0非人力1内部人力2外购人力,此表都是非人力',
  `biz_month` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务归属月份yyyy-MM',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务归属日期yyyy-MM-dd',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '科目名称',
  `ubranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户归属机构',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目归属机构',
  `INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `ref_sbill_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算单编号',
  `ref_sbill_sub_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算单明细编号',
  `cost_center_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '成本中心-关联到部门.deptid',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `luserid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '更新人编号',
  `lusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '更新人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目实际成本费用记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_env_list
-- ----------------------------
DROP TABLE IF EXISTS `xm_env_list`;
CREATE TABLE `xm_env_list`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注说明',
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '内网ip地址',
  `port` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '内网访问端口',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属机构',
  `access_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问用户编号',
  `access_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问密码',
  `access_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问链接',
  `supplier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '供应商',
  `web_ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '外网ip地址',
  `web_port` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '外网端口',
  `create_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '添加人员',
  `create_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '添加人员姓名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `env_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0不可用1已启用2已过期',
  `start_time` datetime NULL DEFAULT NULL COMMENT '有效日期开始',
  `end_time` datetime NULL DEFAULT NULL COMMENT '有效日期结束',
  `fee_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '费用',
  `fee_rule` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计费规则',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属项目编号',
  `read_qx` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-全部可看，1-同机构可看，2-同机构同项目可看，9-仅自己可看',
  `write_qx` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-全部可写，1-同机构可写，2-同机构同项目可写，3-同项目上级可写，9-仅自己可修改',
  `ltime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `luserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '修改人编号',
  `lusername` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目环境清单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_file
-- ----------------------------
DROP TABLE IF EXISTS `xm_file`;
CREATE TABLE `xm_file`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '文档编号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '文件名称',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `project_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '文件说明',
  `create_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `create_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `read_qx` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-全部可看，1-同机构可看，2-同机构同项目可看，3-同项目上级可看，9-仅自己可看',
  `write_qx` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-全部可写，1-同机构可写，2-同机构同项目可写，3-同项目上级可写，9-仅自己可修改',
  `ltime` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `luserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '修改人编号',
  `lusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_func
-- ----------------------------
DROP TABLE IF EXISTS `xm_func`;
CREATE TABLE `xm_func`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '名称',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级编号',
  `pname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级名称',
  `pid_paths` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级路径，直到自身，逗号分割，包含自身',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `lvl` int NULL DEFAULT NULL COMMENT '菜单级别0-根，1，2，3，4，5依次类推',
  `pbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品归属企业',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '功能模块表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_group
-- ----------------------------
DROP TABLE IF EXISTS `xm_group`;
CREATE TABLE `xm_group`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `group_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '团队名称',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号-属于产品线则可为空',
  `pg_type_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目团队类型编号',
  `pg_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '团队类型名称',
  `leader_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '团队负责人',
  `leader_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号，属于项目组的团队则可为空',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属机构编号',
  `pg_class` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否启用-0否，1-是',
  `pgroup_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级团队编号',
  `lvl` int NULL DEFAULT NULL COMMENT '级别0级1级2级3级4级',
  `pid_paths` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级编号路径逗号分割,0,开始，本组编号+逗号结束',
  `is_tpl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为模板',
  `ass_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '副组长编号',
  `ass_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '副组长姓名',
  `children_cnt` int NULL DEFAULT NULL COMMENT '下级团队数量',
  `user_cnt` int NULL DEFAULT NULL COMMENT '组员数量',
  `qx_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限码',
  `calc_workload` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否计算工作量0否1是',
  `ntype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '节点类型0管理团队、1执行团队',
  `crow_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作公司编号',
  `crow_branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '协作公司名称',
  `is_crow` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否众包团队',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `xm_project_group_ibfk_1`(`project_id` ASC) USING BTREE,
  INDEX `xm_project_group_ibfk_2`(`product_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '团队表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_group_state
-- ----------------------------
DROP TABLE IF EXISTS `xm_group_state`;
CREATE TABLE `xm_group_state`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `project_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算总金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  `group_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '组编号',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '团队名称',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目指标日统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_group_state_his
-- ----------------------------
DROP TABLE IF EXISTS `xm_group_state_his`;
CREATE TABLE `xm_group_state_his`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `project_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算总金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  `group_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '组编号',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '团队名称',
  PRIMARY KEY (`group_id`, `biz_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目指标日统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_group_user
-- ----------------------------
DROP TABLE IF EXISTS `xm_group_user`;
CREATE TABLE `xm_group_user`  (
  `join_time` datetime NULL DEFAULT NULL COMMENT '加入时间',
  `group_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '团队编号',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '团队成员编号',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '团队成员',
  `out_time` datetime NULL DEFAULT NULL COMMENT '离队时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前状态0参与中1已退出团队',
  `obranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '组员原归属机构编号',
  `is_pri` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否私人加入0否1是',
  `seq_no` int NULL DEFAULT NULL COMMENT '排序号--从1开始',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `pg_class` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-项目小组，1-产品小组，2-团队',
  `obranch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '原归属机构名称',
  PRIMARY KEY (`group_id`, `userid`) USING BTREE,
  CONSTRAINT `xm_group_user_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `xm_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '团队成员表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_iteration
-- ----------------------------
DROP TABLE IF EXISTS `xm_iteration`;
CREATE TABLE `xm_iteration`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '迭代编码',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '机构编号',
  `iteration_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '迭代名称',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `online_time` datetime NULL DEFAULT NULL COMMENT '上线时间',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级迭代-作废，不以树状结构',
  `admin_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人',
  `admin_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `budget_cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '预算成本',
  `budget_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '预算工作量',
  `seq_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '顺序号',
  `istatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '迭代阶段:0未开始,1需求评审,2计划会,3研发中,4测试中,5迭代上线,6已完成7已关闭',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人人姓名',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `iphase` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '迭代阶段:0未开始,1需求评审,2计划会,3研发中,4测试中,5迭代上线,6已完成7已关闭',
  `is_tpl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为模板',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `online_time`(`online_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '迭代定义' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_iteration_state
-- ----------------------------
DROP TABLE IF EXISTS `xm_iteration_state`;
CREATE TABLE `xm_iteration_state`  (
  `iteration_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '迭代编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `iteration_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '迭代名称',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `project_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `project_cnt` int NULL DEFAULT NULL COMMENT '项目数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算总金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  PRIMARY KEY (`iteration_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目指标日统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_iteration_state_his
-- ----------------------------
DROP TABLE IF EXISTS `xm_iteration_state_his`;
CREATE TABLE `xm_iteration_state_his`  (
  `iteration_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '迭代编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `iteration_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '迭代名称',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `project_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `project_cnt` int NULL DEFAULT NULL COMMENT '项目数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算总金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  PRIMARY KEY (`iteration_id`, `biz_date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目指标日统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_menu
-- ----------------------------
DROP TABLE IF EXISTS `xm_menu`;
CREATE TABLE `xm_menu`  (
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '功能编号',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能名称',
  `pmenu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级功能',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '归属产品编号',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0初始1待评审2待设计3待开发4待sit测试5待uat测试6已测试待上线7已上线8已下线9已删除',
  `online` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否已上线',
  `demand_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求链接',
  `code_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '代码链接',
  `design_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '设计链接',
  `doc_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '文档链接',
  `help_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '帮助文档链接',
  `oper_doc_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作手册链接',
  `seq_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '排序序号',
  `mm_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '故事管理员编号',
  `mm_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '故事管理员姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ntype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '节点类型0-叶子节点，1非叶子节点',
  `since_version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '开始版本',
  `children_cnt` int NULL DEFAULT NULL COMMENT '儿子节点个数',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `tag_ids` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签编号,逗号分割',
  `tag_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签名称,逗号分割',
  `pid_paths` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '父级id逗号分割，最后一个为本节点节点编号,以,号结尾',
  `lvl` int NULL DEFAULT NULL COMMENT '层级0-顶级，1-一级，2-二级，3-三级，4-四级。总共5级',
  `is_tpl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为模板',
  `phase_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前置需求编号，逗号分隔多个',
  `iteration_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '迭代编号',
  `source` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求来源1部门意见、2用户反馈、3技术反馈、4运营反馈、5团队讨论、6老板需求、7自身需求',
  `proposer_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '提出人编号',
  `proposer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '提出人姓名',
  `dlvl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求层次0-基础需求,1-增值需求,2-扩展需求',
  `dtype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求类型;0-新增功能;1-功能改进;2-bug修复;3-用户体验;4-UI优化;5-内部需求;6-删除需求;7-接口需求;',
  `priority` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '优先级;0-紧急重要；1-紧急不重要；2-不紧急重要；3-不紧急不重要',
  `dclass` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求分类1-史诗，2-特性，3-用户故事，4-任务，5-缺陷',
  `iteration_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '迭代名称',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `func_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能菜单编号-故事才有',
  `func_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能菜单名称-故事才有',
  `comments` int NULL DEFAULT NULL COMMENT '评论数',
  `ups` int NULL DEFAULT NULL COMMENT '点赞数',
  `read_num` int NULL DEFAULT NULL COMMENT '阅读数',
  `pbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品归属企业',
  `sub_ef_cnt` int NULL DEFAULT NULL COMMENT '儿子节点树-不包括孙子，不包括故事',
  `sub_story_cnt` int NULL DEFAULT NULL COMMENT '儿子故事树-不包括孙子，仅仅计算故事数量',
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `xm_project_menu_ibfk_1`(`product_id` ASC) USING BTREE,
  INDEX `ctime`(`ctime` ASC) USING BTREE,
  INDEX `pid_paths`(`pid_paths` ASC) USING BTREE,
  INDEX `pmenu_id`(`pmenu_id` ASC) USING BTREE,
  INDEX `iteration_id`(`iteration_id` ASC) USING BTREE,
  INDEX `mm_userid`(`mm_userid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '用户故事(需求)表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_menu_comment
-- ----------------------------
DROP TABLE IF EXISTS `xm_menu_comment`;
CREATE TABLE `xm_menu_comment`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评论人',
  `USERNAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评论人姓名',
  `STAR` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '星级',
  `CDATE` datetime NULL DEFAULT NULL COMMENT '时间',
  `MENU_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求编号',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级评论 编号',
  `ups` decimal(10, 0) NULL DEFAULT 0 COMMENT '点赞数量',
  `IS_SHOW` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否显示0否1是',
  `TO_USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '回复用户编号',
  `TO_USERNAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '回复用户名',
  `LVL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '层级0,1,2,3,4',
  `CONTEXT` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '评论内容',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `city_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市编号',
  `city_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0未审核，1已审核，3审核不通过',
  `child_nums` int NULL DEFAULT NULL COMMENT '儿子节点数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '档案评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_menu_exchange
-- ----------------------------
DROP TABLE IF EXISTS `xm_menu_exchange`;
CREATE TABLE `xm_menu_exchange`  (
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能编号',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能名称',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属产品编号',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '评论编号',
  `pid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级评论编号',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评论人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评论人名称',
  `ctime` datetime NULL DEFAULT NULL COMMENT '评论时间',
  `cbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评论人所属机构',
  `adopt` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否采纳0否1采纳',
  `adopt_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '采纳人编号',
  `adopt_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '采纳人名称',
  `adopt_time` datetime NULL DEFAULT NULL COMMENT '采纳时间',
  `closed` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关闭该评论0否1是',
  `puserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级用户编号',
  `pusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级姓名',
  `premark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '上级备注',
  `notify_userids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '本评论需要同步给的人列表,逗号分隔',
  `notify_channels` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '发送通知渠道inner-email/wxpub/sms/im/out-email等逗号分割',
  `notify_usernames` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '通知用户姓名逗号分隔',
  `cuser_head_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '发言人头像地址',
  `reply_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '回复方式1引用2回复',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `xm_project_menu_ibfk_1`(`product_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '功能表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_menu_state
-- ----------------------------
DROP TABLE IF EXISTS `xm_menu_state`;
CREATE TABLE `xm_menu_state`  (
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '功能编号',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '总体完成比例0-100之间,根据taskType进行汇总',
  `menu_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0初始1设计中2开发中3测试中4uat测试2已上线3已下线4已删除',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '汇总时间',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工时数',
  `plan_worker_cnt` decimal(10, 2) NULL DEFAULT NULL COMMENT '总人数',
  `closed_bugs` decimal(10, 0) NULL DEFAULT NULL COMMENT '总关闭bugs',
  `active_bugs` decimal(10, 0) NULL DEFAULT NULL COMMENT '激活bugs',
  `confirmed_bugs` decimal(10, 0) NULL DEFAULT NULL COMMENT '已确认bugs总数',
  `resolved_bugs` decimal(10, 0) NULL DEFAULT NULL COMMENT '已解决bugs总数',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数-指测试库中总用例数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数-指有测试计划的阻塞和失败的用例总数-去重',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数-指有测试计划的未测状态的用例数-去重',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数-指有测试计划的已通过和忽略状态的用例数-去重',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务日期yyyy-MM-dd字符串',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug总数',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表+缺陷表+用例表+用例执行表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自工时明细表同需求的汇总',
  `act_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `product_cnt` int NULL DEFAULT NULL COMMENT '关联产品数（主要是指子节点关联）',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '关联迭代数（主要是指子节点关联）',
  `project_cnt` int NULL DEFAULT NULL COMMENT '关联项目数（主要是指子节点关联）',
  `sub_ef_cnt` int NULL DEFAULT NULL COMMENT '儿子节点树-不包括孙子，不包括故事',
  `sub_story_cnt` int NULL DEFAULT NULL COMMENT '儿子故事树-不包括孙子，仅仅计算故事数量',
  PRIMARY KEY (`menu_id`) USING BTREE,
  INDEX `menu_id`(`menu_id` ASC) USING BTREE,
  CONSTRAINT `xm_menu_state_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `xm_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '功能状态表,无需前端维护，所有数据由汇总统计得出' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_my_focus
-- ----------------------------
DROP TABLE IF EXISTS `xm_my_focus`;
CREATE TABLE `xm_my_focus`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '关注的对象主键',
  `focus_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6',
  `pbiz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号',
  `biz_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务名称',
  `pbiz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象上级名称',
  `ftime` datetime NULL DEFAULT NULL COMMENT '关注时间',
  `ubranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户归属机构',
  PRIMARY KEY (`userid`, `biz_id`, `pbiz_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '我关注的项目或者任务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_my_foot_print
-- ----------------------------
DROP TABLE IF EXISTS `xm_my_foot_print`;
CREATE TABLE `xm_my_foot_print`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户名称',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '关注的对象主键',
  `focus_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象类型:项目-1/任务-2/产品-3/需求-4/bug-5',
  `pbiz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号',
  `biz_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务名称',
  `pbiz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象上级名称',
  `ftime` datetime NULL DEFAULT NULL COMMENT '进入时间',
  `ubranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户归属机构',
  `ltime` datetime NULL DEFAULT NULL COMMENT '离开时间',
  PRIMARY KEY (`userid`, `biz_id`, `pbiz_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '我关注的项目或者任务' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_product
-- ----------------------------
DROP TABLE IF EXISTS `xm_product`;
CREATE TABLE `xm_product`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '产品编号',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品名称',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '版本号',
  `pm_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品经理编号',
  `pm_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品经理名称',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `deptid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属部门',
  `pstatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品阶段:0未开始,1研发中,2已完成,3已关闭',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始日期',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束日期',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '主管部门名称',
  `adm_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '主管领导编号',
  `adm_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '主管领导名称',
  `ass_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '副经理编号',
  `ass_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '副经理名称',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `is_tpl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为模板',
  `baseline_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '基线编号',
  `base_time` datetime NULL DEFAULT NULL COMMENT '基线时间',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编码',
  `pbudget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '产品预计总工作量，应该大于一级需求总预算工作量',
  `pbudget_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '产品预计总金额，应该大于一级需求总预算金额',
  `pmenu_budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '从需求汇总来的总预算工作量',
  `pmenu_budget_amount` decimal(20, 2) NULL DEFAULT NULL COMMENT '从需求汇总的总预算金额',
  `budget_ctrl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否进行预算控制，计划中一级计划总预算不能大于项目预算',
  `phase_budget_ctrl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否进行计划明细预算控制，计划中下级预算不能大于上级预算',
  `phase_act_ctrl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计划是否进行实际金额控制，实际金额不能大于预算金额',
  `locked` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否锁定不允许编号0否1是',
  `del` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否已删除0否一是',
  `ltime` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `qx_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限码0,1,2,3,4,5,67,8,9，逗号分割共10位,不定长，暂时只启用前8个位第0位代表团队建立及成员管理及crud权限：    0-代表不限制,1-同组织，2-同项目组（默认），3-同小组第1位代表团队建立及成员管理及crud权限是否检查上下级关系：0-否（默认），1是 第2位代表测试相关(包括测试用例、测试库、测试计划、测试报告)指派及crud权限同第0位，第3位代表测试相关(包括测试用例、测试库、测试计划、测试报告)指派及crud时是否检查上下级关系，同第1位第4位代表需求指派及crud时权限，同第0位第5位代表需求指派及crud时是否检查上下级关系，同第1位第6位代表迭代指派及crud时权限，同第0位第7位代表迭代指派及crud时是否检查上下级关系，同第1位',
  `show_out` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否对外公开0-完全不可见，1-仅本司人员可见，2-关联人员可见（众包-外包-招投标）,3-本部门上级及下属部门可见,4-仅本部及上级可见，5-仅本部及下级可见,6-仅本部人员可见    9-不区分',
  `deptid_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号全路径编号',
  `create_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目创建人编号',
  `create_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ctime`(`ctime` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '产品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_product_project_link
-- ----------------------------
DROP TABLE IF EXISTS `xm_product_project_link`;
CREATE TABLE `xm_product_project_link`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目表中的主键',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '产品表中的主键',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `link_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联状态1关联0取消关联',
  `seq` int NULL DEFAULT NULL COMMENT '显示顺序0-999,从小到大排序',
  PRIMARY KEY (`project_id`, `product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '产品与项目的关联关系表，一般由产品经理挂接项目到产品上' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_product_state
-- ----------------------------
DROP TABLE IF EXISTS `xm_product_state`;
CREATE TABLE `xm_product_state`  (
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '产品编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品名称',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `project_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `project_cnt` int NULL DEFAULT NULL COMMENT '项目数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算总金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  PRIMARY KEY (`product_id`) USING BTREE,
  CONSTRAINT `xm_product_state_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `xm_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目指标日统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_product_state_his
-- ----------------------------
DROP TABLE IF EXISTS `xm_product_state_his`;
CREATE TABLE `xm_product_state_his`  (
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '产品编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品名称',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `project_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `project_cnt` int NULL DEFAULT NULL COMMENT '项目数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算总金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  PRIMARY KEY (`product_id`, `biz_date`) USING BTREE,
  CONSTRAINT `xm_product_state_his_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `xm_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目指标日统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_product_version
-- ----------------------------
DROP TABLE IF EXISTS `xm_product_version`;
CREATE TABLE `xm_product_version`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '版本号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '版本描述',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '产品编号',
  `vstatus` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '版本状态0-未开始，1-进行中，2-已发布',
  `vrate` int NULL DEFAULT NULL COMMENT '进度0-100之间',
  `ptime` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `adm_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人',
  `adm_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '版本名称',
  PRIMARY KEY (`product_id`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '产品版本编号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_project
-- ----------------------------
DROP TABLE IF EXISTS `xm_project`;
CREATE TABLE `xm_project`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目代号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `xm_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目类型',
  `start_time` datetime NULL DEFAULT NULL COMMENT '项目开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '项目结束时间',
  `urgent` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '紧急程度',
  `priority` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '优先程度',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目描述',
  `create_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目创建人编号',
  `create_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `assess` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目考核',
  `assess_remarks` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '考核备注',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `plan_total_cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '总预算',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `plan_nouser_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '非人力成本总预算-应该大于或等于阶段计划非人力总成本',
  `plan_iuser_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '内部人力成本总预算-应该大于或等于阶段计划内部人力总成本',
  `plan_ouser_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '外购人力成本总预算-应该大于或等于阶段计划外购人力总成本',
  `locked` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否锁定整个项目不允许变化0否1是',
  `base_time` datetime NULL DEFAULT NULL COMMENT '基线时间',
  `base_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '基线备注',
  `baseline_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '基线主键',
  `plan_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '总预算工作量-应该大于或等于阶段计划总工作量',
  `total_receivables` decimal(10, 2) NULL DEFAULT NULL COMMENT '总预计收款金额',
  `budget_margin_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '预估毛利率 (总预估收入-总预估费用)/总预估收入 0-100之间',
  `contract_amt` decimal(10, 2) NULL DEFAULT NULL COMMENT '合同总金额',
  `plan_iuser_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '内部人力成本单价元/人时',
  `plan_ouser_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '外购人力成本单价元/人时',
  `plan_ouser_cnt` int NULL DEFAULT NULL COMMENT '外购人数',
  `plan_iuser_cnt` int NULL DEFAULT NULL COMMENT '内部人数',
  `plan_working_hours` int NULL DEFAULT NULL COMMENT '预计工作小时数目',
  `tax_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '税率0-100之间',
  `plan_iuser_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `plan_ouser_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `from_tpl_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联模板编号',
  `budget_ctrl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否进行预算控制，计划中一级计划总预算大于项目预算则拒绝添加计划,一般用于瀑布型项目',
  `deptid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号',
  `show_out` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否对外公开0-完全不可见，1-仅本司人员可见，2-关联人员可见（众包-外包-招投标）,3-本部门上级及下属部门可见,4-仅本部及上级可见，5-仅本部及下级可见,6-仅本部人员可见    9-不区分',
  `is_tpl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为模板',
  `pm_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目经理编号',
  `pm_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目经理名称',
  `ass_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '助理、副经理编号',
  `ass_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '助理、副经理姓名',
  `adm_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '主管领导编号',
  `adm_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '主管领导姓名',
  `budget_early` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否进行计划预算预警，计划预算超出项目预算既定额度进行预警',
  `phase_act_ctrl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计划是否进行实际金额控制，实际金额不能大于预算金额（大于预算金额不得结算）',
  `del` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否已删除0否1是',
  `ltime` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `ostatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '原状态，暂停时记录原状态，暂停恢复后把原状态恢复',
  `work_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '工作方式1-scrum、2-kanban',
  `wtype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '报工方式0-无须报工，1-每日报工，2-工期内报工',
  `early_amt` decimal(10, 2) NULL DEFAULT NULL COMMENT '超出预算金额多少金额进行预警，正数代表超出的额度，负数代表距离预算的额度',
  `max_task_amt` decimal(10, 2) NULL DEFAULT NULL COMMENT '单个任务最大金额',
  `menu_link` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务是否必须严格关联用户故事，0不限制，1必须关联，2-完全不关联',
  `phase_link` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务是否必须关联计划，0-不限制，1必须关联，2完全不关联',
  `tpl_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模板类型1-全域公开，2-本机构公开',
  `qx_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '权限码0,1,2,3,4,5,67,8,9，逗号分割共10位,不定长，暂时只启用前6个位第0位代表团队建立及成员管理及crud权限：    0-代表不限制,1-同组织，2-同项目组（默认），3-同小组第1位代表团队建立及成员管理及crud权限是否检查上下级关系：0-否（默认），1是 第2位代表测试指派及crud时权限，同第0位第3位代表测试指派及crud时是否检查上下级关系，同第1位第4位代表任务指派及crud时权限，同第0位第5位代表任务指派及crud时是否检查上下级关系，同第1位',
  `deptid_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号全路径编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_project_env_list
-- ----------------------------
DROP TABLE IF EXISTS `xm_project_env_list`;
CREATE TABLE `xm_project_env_list`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注说明',
  `ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `port` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问端口',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属项目组',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属项目组名称',
  `access_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问用户编号',
  `access_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问密码',
  `effect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '作用说明',
  `access_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问链接',
  `web_ip_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '外网ip地址',
  `web_port` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '外网端口',
  `other_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '其它说明',
  `create_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '添加人员',
  `create_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '添加人员姓名',
  `create_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目环境清单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_project_kpi
-- ----------------------------
DROP TABLE IF EXISTS `xm_project_kpi`;
CREATE TABLE `xm_project_kpi`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编码',
  `kpi_index` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指标编号',
  `kpi_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指标名称',
  `max_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最大值',
  `min_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最小值',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `score` int NULL DEFAULT NULL COMMENT '得分0~10分',
  `score_date` datetime NULL DEFAULT NULL COMMENT '评分日期',
  `biz_flow_state` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程状态',
  `biz_proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `kpi_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'kpi当前值',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `calc_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '考核方式0月1季度3半年4一年',
  `next_calc_date` datetime NULL DEFAULT NULL COMMENT '下次考核开始时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目关键指标考核' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_project_kpi_his
-- ----------------------------
DROP TABLE IF EXISTS `xm_project_kpi_his`;
CREATE TABLE `xm_project_kpi_his`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编码',
  `kpi_index` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指标编号',
  `kpi_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指标名称',
  `max_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最大值',
  `min_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最小值',
  `kpi_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT 'kpi主表主键',
  `score` int NULL DEFAULT NULL COMMENT '得分0~10分',
  `score_date` datetime NULL DEFAULT NULL COMMENT '评分日期',
  `biz_flow_state` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程状态',
  `biz_proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `kpi_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'kpi当前值',
  `cdate` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `calc_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '考核方式0月1季度3半年4一年',
  `next_calc_date` datetime NULL DEFAULT NULL COMMENT '下次考核开始时间',
  PRIMARY KEY (`kpi_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目关键指标考核' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_project_state
-- ----------------------------
DROP TABLE IF EXISTS `xm_project_state`;
CREATE TABLE `xm_project_state`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `project_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算总金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  PRIMARY KEY (`project_id`) USING BTREE,
  CONSTRAINT `xm_project_state_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `xm_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目指标日统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_project_state_his
-- ----------------------------
DROP TABLE IF EXISTS `xm_project_state_his`;
CREATE TABLE `xm_project_state_his`  (
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '统计日期yyyy-mm-dd类型',
  `file_cnt` int NULL DEFAULT NULL COMMENT '文件数据',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '统计执行日期',
  `calc_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-暂时的1稳定的，暂时的可以被覆盖，稳定的不允许覆盖',
  `phase_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划数',
  `phase_finish_cnt` int NULL DEFAULT NULL COMMENT '项目阶段计划已完成数',
  `need_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待付款总金额',
  `finish_pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已付款总金额',
  `need_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '待收款总金额',
  `finish_col_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '已收款总金额',
  `risk_cnt` int NULL DEFAULT NULL COMMENT '项目风险总数',
  `risk_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成风险总数',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `branch_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构名称',
  `budget_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力预算-来自任务表',
  `budget_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力预算-来自任务表',
  `budget_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力预算-来自任务表',
  `act_user_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总人力成本',
  `act_iuser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总内部人力成本金额',
  `act_ouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总外购人力成本金额',
  `act_nouser_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总非人力成本',
  `finish_rate` decimal(10, 2) NULL DEFAULT NULL COMMENT '项目进度0~100之间，来自任务表',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '项目总预算工作量-来自任务表',
  `budget_ouser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '外购人力总工作量-应该大于或等于阶段计划外购人力总成本',
  `budget_iuser_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '内部人力总工作量-应该大于或等于阶段计划内部人力总成本',
  `estimate_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预估工时=计划结束时间在计算当日前完成的任务的预算工时总和',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '已完成工作量-来自计划中实际完成工作量',
  `project_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0|初始1|售前2|立项中3|实施中4|暂停中5|结项中6|已结项7|售后8|已完成9|已关闭',
  `act_ouser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际外购总工作量，来自任务表',
  `act_iuser_workload` decimal(20, 0) NULL DEFAULT NULL COMMENT '实际内部总工作量，来自任务表',
  `need_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款笔数',
  `finish_pay_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '完成付款总比数',
  `finish_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '已付款总人数',
  `need_pay_user_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '待付款总人数',
  `test_cases` int NULL DEFAULT NULL COMMENT '测试案例总数',
  `exec_cases` int NULL DEFAULT NULL COMMENT '测试中案例总数',
  `design_cases` int NULL DEFAULT NULL COMMENT '设计中案例总数',
  `finish_cases` int NULL DEFAULT NULL COMMENT '完成案例总数',
  `iteration_cnt` int NULL DEFAULT NULL COMMENT '迭代数',
  `product_cnt` int NULL DEFAULT NULL COMMENT '产品数',
  `min_start_time` datetime NULL DEFAULT NULL COMMENT '最早开始日期',
  `max_end_time` datetime NULL DEFAULT NULL COMMENT '最晚结束时间',
  `menu_cnt` int NULL DEFAULT NULL COMMENT '故事数',
  `menu_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成需求数，2状态需求',
  `menu_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中需求数，1状态的需求',
  `menu_unstart_cnt` int NULL DEFAULT NULL COMMENT '未开始需求数，0状态数据',
  `menu_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭需求数，3状态数据',
  `task_cnt` int NULL DEFAULT NULL COMMENT '任务总数',
  `task_unstart_cnt` int NULL DEFAULT NULL COMMENT '待开始任务',
  `task_exec_cnt` int NULL DEFAULT NULL COMMENT '执行中任务',
  `task_finish_cnt` int NULL DEFAULT NULL COMMENT '已完成任务总数-来自任务表',
  `task_set_cnt` int NULL DEFAULT NULL COMMENT '已结算任务',
  `task_out_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '外购任务数，来自任务表',
  `task_close_cnt` int NULL DEFAULT NULL COMMENT '已关闭任务',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `plan_workhours` decimal(10, 2) NULL DEFAULT NULL COMMENT '工期（小时）',
  `plan_worker_cnt` int NULL DEFAULT NULL COMMENT '总人数',
  `act_worker_cnt` decimal(10, 0) NULL DEFAULT NULL COMMENT '实际投入人员数',
  `budget_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算总金额',
  `act_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  PRIMARY KEY (`project_id`, `biz_date`) USING BTREE,
  CONSTRAINT `xm_project_state_his_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `xm_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目指标日统计表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_question
-- ----------------------------
DROP TABLE IF EXISTS `xm_question`;
CREATE TABLE `xm_question`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '问题编号',
  `name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '问题标题',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '项目编号',
  `project_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `case_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试案例编号',
  `case_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试案例名称',
  `end_time` datetime NULL DEFAULT NULL COMMENT '到期时间',
  `ask_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '提出人编号',
  `ask_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '提出人',
  `handler_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '处理人编号',
  `handler_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '处理人',
  `priority` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '优先级别1-非常紧急，2-紧急，3-一般紧急，4-低',
  `solution` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '解决方案: 1设计如此、2重复BUG、3外部原因、4已解决、5无法重现、6延期处理、 7不予解决。',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '问题描述',
  `create_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '问题创建人编号',
  `create_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '问题创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `bug_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '缺陷状态1|新提交2|处理中3|已修复4|已挂起5|已提测6|已拒绝7|已解决8|已关闭9|重新打开',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '故事编号',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '故事名称',
  `budget_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '预估工时单位人时',
  `budget_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '预估成本金额',
  `act_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际工时（取报工实际工时汇总）',
  `act_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际总金额',
  `expect_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '期望结果',
  `op_step` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '测试步骤[{id:,op:,eresult:,aresult:,tcode:测试结果代码}]',
  `curr_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '当前结果',
  `ref_require` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '相关需求',
  `bug_severity` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '严重程度1、2、3、4，分别对应：致命缺陷、严重缺陷、普通缺陷、轻微缺陷',
  `bug_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '缺陷类型1、2、3、4，分别对应：代码错误、低级缺陷、设计缺陷、配置相关、安全相关、性能问题、其他',
  `tag_ids` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签id列表逗号分隔',
  `tag_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标签名称列表逗号分隔',
  `urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '链接地址列表逗号分隔',
  `ltime` datetime NULL DEFAULT NULL COMMENT '最后更新时间',
  `qtype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '问题类型2-风险、1-功能问题、3-普通咨询、（暂时不用这个字段了）',
  `case_exec_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联的案例执行编号，作废',
  `remarks` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '最后更新说明',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `rep_rate` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '复现频率1-必现，2-大概率复现，3-小概率复现，4-仅出现一次',
  `ver_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '版本号',
  `vpath` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '访问路径/斜杠分割',
  `pver_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '发布版本',
  `bug_reason` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '原因分析',
  `rate` int NULL DEFAULT NULL COMMENT '进度0-100',
  `init_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '原始预估工作量，budget_workload发生变化后，进行备份',
  `task_out` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否众包0否1是',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务编号-可以在任务下直接创建bug-废弃，不用了',
  `func_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能菜单编号',
  `func_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能菜单名称',
  `func_pnames` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级名称逗号分割',
  `plan_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试计划编号',
  `casedb_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试库编号',
  `pbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品或者项目归属企业编号',
  `sort_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '排序序号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create_time`(`create_time` ASC) USING BTREE,
  INDEX `project_id`(`project_id` ASC, `handler_userid` ASC) USING BTREE,
  INDEX `menu_id`(`menu_id` ASC, `product_id` ASC) USING BTREE,
  INDEX `task_id`(`task_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '缺陷列表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_question_handle
-- ----------------------------
DROP TABLE IF EXISTS `xm_question_handle`;
CREATE TABLE `xm_question_handle`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `handler_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '处理人编号',
  `handler_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '处理人',
  `handle_solution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '解决方案: 1设计如此、2重复BUG、3外部原因、4已解决、5无法重现、6延期处理、 7不予解决。',
  `receipt_message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '回执信息',
  `receipt_time` datetime NULL DEFAULT NULL COMMENT '回执时间',
  `handle_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '=bugStatus',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `question_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '问题编号',
  `last_update_time` datetime NULL DEFAULT NULL COMMENT '最后更新日期',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `act_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际工时',
  `act_cost_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '链接地址列表逗号分隔',
  `target_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指派给谁',
  `target_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指派给谁',
  `oper_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人编号',
  `oper_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `old_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '原状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_question_handle_his
-- ----------------------------
DROP TABLE IF EXISTS `xm_question_handle_his`;
CREATE TABLE `xm_question_handle_his`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `handler_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '处理人编号',
  `handler_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '处理人',
  `handle_solution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '解决方案: 1设计如此、2重复BUG、3外部原因、4已解决、5无法重现、6延期处理、 7不予解决。',
  `receipt_message` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '回执信息',
  `receipt_time` datetime NULL DEFAULT NULL COMMENT '回执时间',
  `handle_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '=bugStatus',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `question_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '问题编号',
  `last_update_time` datetime NULL DEFAULT NULL COMMENT '最后更新日期',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `act_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际工时',
  `act_cost_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际金额',
  `urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '链接地址列表逗号分隔',
  `target_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指派给谁',
  `target_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '指派给谁',
  `oper_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人编号',
  `oper_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `old_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '原状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_record
-- ----------------------------
DROP TABLE IF EXISTS `xm_record`;
CREATE TABLE `xm_record`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '日志编号',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  `oper_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人id',
  `oper_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人名字',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `obj_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6',
  `action` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作的id',
  `old_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '历史值',
  `new_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '新值',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注-只描述新旧值之间的变化',
  `glo_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '全局根踪号，用于跟踪日志',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键编号',
  `pbiz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试计划编号、故事编号，其它勿填',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `biz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象名称',
  `top_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试库编号、迭代编号，其它勿填',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_id`(`project_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `biz_id_2`(`pbiz_id` ASC, `biz_id` ASC) USING BTREE,
  INDEX `biz_id`(`product_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `oper_time`(`oper_time` ASC) USING BTREE,
  INDEX `branch_id`(`branch_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '重点数据操作记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_record_his
-- ----------------------------
DROP TABLE IF EXISTS `xm_record_his`;
CREATE TABLE `xm_record_his`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '日志编号',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  `oper_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人id',
  `oper_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人名字',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `obj_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6',
  `action` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作的id',
  `old_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '历史值',
  `new_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '新值',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注-只描述新旧值之间的变化',
  `glo_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '全局根踪号，用于跟踪日志',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键编号',
  `pbiz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试计划编号、故事编号，其它勿填',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `biz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象名称',
  `top_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试库编号、迭代编号，其它勿填',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_id`(`project_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `biz_id_2`(`pbiz_id` ASC, `biz_id` ASC) USING BTREE,
  INDEX `biz_id`(`product_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `oper_time`(`oper_time` ASC) USING BTREE,
  INDEX `branch_id`(`branch_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '重点数据操作记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_record_visit
-- ----------------------------
DROP TABLE IF EXISTS `xm_record_visit`;
CREATE TABLE `xm_record_visit`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '日志编号',
  `oper_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人id',
  `oper_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作人名字',
  `oper_time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `obj_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象类型:项目-1/任务-2/产品-3/需求-4/bug-5/迭代6',
  `action` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '操作的id',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注-只描述新旧值之间的变化',
  `glo_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '全局根踪号，用于跟踪日志',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务主键编号',
  `pbiz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象上级编号,项目时填项目编号，任务时填项目编号，产品时填产品编号，需求时填产品编号，bug时填产品编号，迭代时填产品编号',
  `biz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '对象名称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `project_id`(`biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `biz_id_2`(`pbiz_id` ASC, `biz_id` ASC) USING BTREE,
  INDEX `biz_id`(`biz_id` ASC, `pbiz_id` ASC) USING BTREE,
  INDEX `oper_time`(`oper_time` ASC) USING BTREE,
  INDEX `branch_id`(`branch_id` ASC, `biz_id` ASC, `pbiz_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '重要页面访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_rpt_config
-- ----------------------------
DROP TABLE IF EXISTS `xm_rpt_config`;
CREATE TABLE `xm_rpt_config`  (
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '业务编号',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '报告编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '报告名称',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `cbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建机构',
  `cfg` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '报告配置项 [  {compId:组件编号,name:组件名,params:{任意条件参数},title:,remark:文字说明}]',
  `biz_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务类型1-产品报告，2-迭代报告，3-测试计划报告，4-项目报告，5-企业报告，6-测试库报告',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '测试报告配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_rpt_data
-- ----------------------------
DROP TABLE IF EXISTS `xm_rpt_data`;
CREATE TABLE `xm_rpt_data`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '报表编号',
  `rpt_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '报表名称',
  `cfg_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '报表配置主键',
  `rpt_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '报表数据json对象,比rptCfg.cfg多了rawDatas数组类型[{compId:组件编号,title:标题,remark:说明,params:各种参数,rawDatas:该组件的所有数据}]',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人机构号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属业务日期yyyy-MM-dd型',
  `biz_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务类型，同rpt_config.biz_type',
  `biz_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_task
-- ----------------------------
DROP TABLE IF EXISTS `xm_task`;
CREATE TABLE `xm_task`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '任务编号',
  `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务名称',
  `parent_taskid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '父任务编号',
  `parent_taskname` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '父任务名称',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务级别',
  `sort_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '序号',
  `executor_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务执行人编号',
  `executor_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务执行人',
  `pre_taskid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前置任务编号',
  `pre_taskname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前置任务名称',
  `start_time` datetime NULL DEFAULT NULL COMMENT '任务开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '任务结束时间',
  `milestone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '里程碑',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '任务描述',
  `remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注',
  `create_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务创建人编号（谁创建谁负责）',
  `create_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务创建人（谁创建谁负责）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `rate` int NULL DEFAULT NULL COMMENT '任务进度0-100（=实际工时/(实际工时+剩余工时)*100）',
  `budget_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前任务预算金额（calc_type=2时预算工时*单价，calc_type=1时下级汇总）',
  `budget_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '预算工时（calc_type=2时手工填写，calc_type=1时下级汇总）',
  `act_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '当前任务实际费用金额（calc_type=2时，取实际工时*单价，calc_type=1时取下级汇总数据）待结算金额',
  `act_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '任务取工时表报工工时汇总，其余取下级汇总',
  `task_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务状态0待领取1已领取执行中2已完工3已验收4已结算9已关闭',
  `task_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0售前方案1投标2需求3设计4开发5测试6验收7部署8运维--来自基础数据表taskType',
  `task_class` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '1需结算0不需结算',
  `to_task_center` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否发布到任务大厅0否1是,1时互联网可访问',
  `act_start_time` datetime NULL DEFAULT NULL COMMENT '实际开始时间-任务状态变成执行中的时间',
  `act_end_time` datetime NULL DEFAULT NULL COMMENT '实际结束时间-任务状态变成完工状态时的时间',
  `BIZ_PROC_INST_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程实例编号',
  `biz_flow_state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '当前流程状态0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `phase_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目阶段编号(作废)',
  `phase_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目阶段名称(作废)',
  `task_skill_names` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '技能列表,逗号分隔',
  `exe_usernames` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '执行人列表逗号分隔如陈x(审核人),王x(监控人)',
  `task_skill_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '技能编号列表逗号分隔',
  `exe_userids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '执行人编号列表逗号分隔如u1(1),u2(2)',
  `task_out` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行方式-0内研1外购',
  `plan_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计划类型w1-周,w2-2周,w3-3周,m1-1月,m2-2月,q1-季,q2-半年，y1-年',
  `settle_schemel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务结算方案-来自数字字典xmTaskSettleSchemel',
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属功能编号',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属功能名称',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号根据功能变化带进',
  `cbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建机构',
  `cdeptid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建部门',
  `tag_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '标签编号，逗号分割',
  `tag_names` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '标签名称，逗号分割',
  `ntype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '节点类型0-任务，1-计划。计划下可建立计划和任务，任务下不允许再扩展。也就是非叶子节点都是计划，叶子节点有可能是计划或者任务',
  `children_cnt` int NULL DEFAULT NULL COMMENT '儿子节点个数',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pid_paths` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '父级id逗号分割，最后一个为本节点节点编号,以,号结尾',
  `lvl` int NULL DEFAULT NULL COMMENT '层级0-顶级，1-一级，2-二级，3-三级，4-四级。总共5级',
  `is_tpl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为模板',
  `key_path` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为关键路径上的节点',
  `uni_inner_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '内部单位工时单价',
  `uni_out_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '外部单位工时单价',
  `calc_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '数据统计方式 0-不计算(ntype=0时适用)，1-下级汇总(ntype=1时适用)，2-工时表汇总也就是手工填报(ntype=0时适用)',
  `ptype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计划分类0-项目，1产品,空为不区分',
  `wtype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '2' COMMENT '报工方式1-强制每日报工，2-工期内报工，0-无需报工',
  `bctrl` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '报工限制0-不限制，1-不得超出预估工时',
  `init_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '原始预估工作量，budget_workload发生变化后，进行备份',
  `share_fee` decimal(10, 0) NULL DEFAULT NULL COMMENT '分享赚佣金',
  `oshare` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '开启分享赚功能0-否1-待付款，2已付款',
  `crowd` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否众包0否1是，众包属于外购的一种',
  `browse_users` int NULL DEFAULT NULL COMMENT '浏览人数',
  `exec_users` int NULL DEFAULT NULL COMMENT '投标人数',
  `city_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市编号',
  `city_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市名称',
  `region_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '地域限制方式0-不限制，1-同城，2-同省，3-同国，4-同洲',
  `browse_times` int NULL DEFAULT NULL COMMENT '浏览次数',
  `capa_lvls` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '能力等级最小要求',
  `tran_mode` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '交易模式1-招标，2-雇佣',
  `sup_requires` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '保障要求编号0-不限制，1铜牌，2银牌，3金牌',
  `hot` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为热搜0否1待付款2已开通3已过期,每次热搜3天，3天后自动取消热搜',
  `top` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否为置顶0否1待付款2已开通3已过期,每次置顶3天，3天后自动取消置顶',
  `urgent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '加急0否1待付款2已开通3已过期',
  `crm_sup` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '客服包办0否1待付款2已开通，理顺需求、比稿选稿',
  `bid_step` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '投标流程0-草稿，1-发布需求，2-用户投标，3雇主选标，4拓管赏金，5用户工作，6验收付款,7完结雇主正式发布后由0->2雇主选标后由2->4雇主托管资金后由4->5服务商提交任务成功，由5->6任务验收完毕后，雇主手动点击付款，由6->7',
  `interest_lvls` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '会员等级最小要求',
  `file_paths` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '附件地址列表，逗号分割',
  `estate` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '资金托管状况0-无须托管，1-待付款，2-已托管资金，3-已付款给服务商，4-已退款',
  `efunds` decimal(10, 4) NULL DEFAULT NULL COMMENT '托管金额=quote_final_at',
  `eto_plat_time` datetime NULL DEFAULT NULL COMMENT '托管资金付款给平台的时间',
  `eto_dev_time` datetime NULL DEFAULT NULL COMMENT '托管资金支付给服务商的时间',
  `eback_time` datetime NULL DEFAULT NULL COMMENT '托管资金退回甲方时间',
  `top_stime` datetime NULL DEFAULT NULL COMMENT '置顶开始时间',
  `top_etime` datetime NULL DEFAULT NULL COMMENT '置顶结束时间',
  `hot_stime` datetime NULL DEFAULT NULL COMMENT '热搜开始时间',
  `hot_etime` datetime NULL DEFAULT NULL COMMENT '热搜结束时间',
  `urgent_stime` datetime NULL DEFAULT NULL COMMENT '加急开始时间',
  `urgent_etime` datetime NULL DEFAULT NULL COMMENT '加急结束时间',
  `quote_final_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '众包最终确定价格',
  `province_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省编号',
  `province_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省名称',
  `area_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '区县编号',
  `area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '区县名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '0-草稿，1-正式',
  `bid_etime` datetime NULL DEFAULT NULL COMMENT '供应商投标截止时间',
  `service_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '服务编号-对应服务商中我的服务里面的服务编号',
  `credit_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最低信用等级',
  `pbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目归属企业编号',
  `sub_task_cnt` int NULL DEFAULT NULL COMMENT '儿子任务树-不包括孙子',
  `sub_plan_cnt` int NULL DEFAULT NULL COMMENT '儿子计划树-不包括孙子',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `xm_task_ibfk_1`(`menu_id` ASC) USING BTREE,
  INDEX `project_id`(`cbranch_id` ASC, `project_id` ASC, `executor_userid` ASC) USING BTREE,
  INDEX `create_time`(`create_time` ASC) USING BTREE,
  INDEX `pid_paths`(`pid_paths` ASC) USING BTREE,
  INDEX `project_id_2`(`project_id` ASC) USING BTREE,
  INDEX `product_id`(`product_id` ASC) USING BTREE,
  INDEX `bid_etime`(`bid_etime` ASC) USING BTREE,
  INDEX `parent_taskid`(`parent_taskid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '项目任务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_task_bid_order
-- ----------------------------
DROP TABLE IF EXISTS `xm_task_bid_order`;
CREATE TABLE `xm_task_bid_order`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `ouserid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '下单用户编号',
  `obranch_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司ID-下单客户对应的企业',
  `ostatus` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pay_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式1微信2支付宝',
  `pay_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付状态0待付款，1已付款',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `prepay_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方支付订单编号',
  `final_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '最终总费用=origin_fee * discount/100+oth_fee',
  `oth_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '其它费用',
  `origin_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '原始价格=任务佣金*平台配置的投标直通车收费比率',
  `pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '最终付款金额-客户付款后回填',
  `pay_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付账户对应的第三方openid,注意，下单根付款不一定是同一个人',
  `pay_userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款用户编号',
  `pay_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款用户名称',
  `task_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务编号',
  `calc_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时检查日期是否已过期，已过期则取消任务中的置顶、加急、热搜状态计算状态0-无须计算，1-本周期已计算待下周期计算，2-结束',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '计算时间',
  `pay_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)',
  `tran_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方付款事务号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单名称',
  `biz_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单业务类',
  `project_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `otype` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单类型7-投标直通车',
  `task_budget_at` decimal(50, 0) NULL DEFAULT NULL COMMENT '任务预算金额',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `company_id`(`obranch_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务相关投标直通车订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_task_comment
-- ----------------------------
DROP TABLE IF EXISTS `xm_task_comment`;
CREATE TABLE `xm_task_comment`  (
  `ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评论人',
  `USERNAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评论人姓名',
  `STAR` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '星级',
  `CDATE` datetime NULL DEFAULT NULL COMMENT '时间',
  `TASK_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求编号',
  `PID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级评论 编号',
  `ups` decimal(10, 0) NULL DEFAULT 0 COMMENT '点赞数量',
  `IS_SHOW` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否显示0否1是',
  `TO_USERID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '回复用户编号',
  `TO_USERNAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '回复用户名',
  `LVL` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT '0' COMMENT '层级0,1,2,3,4',
  `CONTEXT` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '评论内容',
  `BRANCH_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `city_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市编号',
  `city_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市名称',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0未审核，1已审核，3审核不通过',
  `child_nums` int NULL DEFAULT NULL COMMENT '儿子节点数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '档案评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_task_eval
-- ----------------------------
DROP TABLE IF EXISTS `xm_task_eval`;
CREATE TABLE `xm_task_eval`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '评价',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评价类型1-雇主对服务商的评价，2-服务商对雇主的评价，3-组长对组员的评价',
  `wspeed` int NULL DEFAULT NULL COMMENT '工作速度0-5分',
  `wattit` int NULL DEFAULT NULL COMMENT '工作态度0-5分',
  `wquality` int NULL DEFAULT NULL COMMENT '工作质量0-5分',
  `total_star` int NULL DEFAULT NULL COMMENT '总体评价0-5分',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评价内容',
  `eval_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评价人编号',
  `eval_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评价人姓名',
  `to_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '被评价人编号',
  `to_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '被评价人姓名',
  `eval_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评价人归属机构',
  `to_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '被评价人归属机构号',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务编号',
  `eval_time` datetime NULL DEFAULT NULL COMMENT '评价时间',
  `pay_speed` int NULL DEFAULT NULL COMMENT '付款及时度0-5分',
  `coop_happy` int NULL DEFAULT NULL COMMENT '合作愉快度0-5分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_task_execuser
-- ----------------------------
DROP TABLE IF EXISTS `xm_task_execuser`;
CREATE TABLE `xm_task_execuser`  (
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '任务id',
  `prj_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行人id-投标者在项目归属公司的内部用户编号，对应sys_user.userid。投标人中标后，项目公司设立内部用户编号后回填，也就是中标后，项目公司应该给中标人设立内部用户编号，让其具有更多的权限',
  `start_time` datetime NOT NULL COMMENT '加入时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '离开时间',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行人状态0候选排队中1执行任务中7放弃任务8黑名单',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `create_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `prj_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行人姓名',
  `match_score` decimal(10, 2) NULL DEFAULT NULL COMMENT '任务能力匹配分数100分，占比50%',
  `quote_weekday` decimal(10, 2) NULL DEFAULT NULL COMMENT '报价天数，不包括周六日',
  `quote_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '报价金额',
  `quote_time` datetime NULL DEFAULT NULL COMMENT '报价时间',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  `phase_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '阶段计划编号',
  `skill_remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '技能说明',
  `quote_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '报价工作量单位人时',
  `quote_start_time` datetime NULL DEFAULT NULL COMMENT '报价-开始工作日期',
  `quote_end_time` datetime NULL DEFAULT NULL COMMENT '报价-结束工作日期',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目所属机构',
  `phase_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '阶段计划名称',
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务名称',
  `dist_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '推荐人编号',
  `dist_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '推荐人姓名',
  `share_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分享码',
  `sfee_rate` int NULL DEFAULT NULL COMMENT '服务费率',
  `sfee` decimal(10, 2) NULL DEFAULT NULL COMMENT '众包服务费',
  `province_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省编号',
  `province_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '省名称',
  `city_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市编号',
  `city_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '城市名称',
  `area_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '区县编号',
  `area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '区县名称',
  `grade_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '能力等级编号',
  `guard_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '保障等级编号',
  `ilvl_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '会员等级编号',
  `credit_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '信用等级编号',
  `ctotal_bids` int NULL DEFAULT NULL COMMENT '投标总数',
  `srv_times` int NULL DEFAULT NULL COMMENT '服务总次数',
  `cmonth_exp` decimal(10, 2) NULL DEFAULT NULL COMMENT '本月获得经验值',
  `cmonth_bids` int NULL DEFAULT NULL COMMENT '本月投标数',
  `bid_direct` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否开启投标直通车',
  `skill_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能编号列表',
  `skill_names` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能名称列表',
  `up_rate` int NULL DEFAULT NULL COMMENT '好评率',
  `adjust_score` int NULL DEFAULT NULL COMMENT '雇主打分100分，占比50%,默认60分',
  `final_score` decimal(10, 0) NULL DEFAULT NULL COMMENT '总得分(100分)=match_score*50%+adjust_score*50',
  `adjust_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '打分说明',
  `csix_bids` int NULL DEFAULT NULL COMMENT '最近6个月投标次数',
  `csix_exp` decimal(10, 2) NULL DEFAULT NULL COMMENT '最近6个月经验值总额',
  `csix_at` decimal(10, 0) NULL DEFAULT NULL COMMENT '最近6个月收入总额',
  `bid_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '投标人用户编号-该用户编号不一定属于项目归属公司的内部账户',
  `bid_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '投标人名称',
  `bid_branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '投标人归属公司-根据cpa_userid带出,该机构号不一定等同于项目归属公司编号。如果投标人属于项目归属公司，则等于项目归属公司编号，否则不同',
  PRIMARY KEY (`task_id`, `bid_userid`) USING BTREE,
  INDEX `task_id`(`task_id` ASC, `prj_userid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '任务候选人、执行人表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_task_order
-- ----------------------------
DROP TABLE IF EXISTS `xm_task_order`;
CREATE TABLE `xm_task_order`  (
  `ouserid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '下单用户编号',
  `obranch_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司ID-下单客户对应的企业',
  `ostatus` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '订单状态0-初始，1-待确认，2-待付款，3-已付款，4-已完成，5-已取消-未付款前可取消，取消后可删除，6-退单-退单后变为已取消，8已关闭-售后完成后可以关闭订单',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `pay_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式1微信2支付宝',
  `pay_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付状态0待付款，1已付款',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `prepay_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方支付订单编号',
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `final_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '最终总费用=origin_fee * discount/100+oth_fee',
  `oth_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '其它费用',
  `origin_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '原始价格=top_fee+urgent_fee+crm_sup_fee+hot_fee+efunds+share_fee',
  `pay_at` decimal(20, 2) NULL DEFAULT NULL COMMENT '最终付款金额-客户付款后回填',
  `pay_auth_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付授权码',
  `pay_openid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付账户对应的第三方openid,注意，下单根付款不一定是同一个人',
  `pay_userid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款用户编号',
  `pay_username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款用户名称',
  `discount` int NULL DEFAULT NULL COMMENT '折扣率0-199',
  `top_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '置顶费用',
  `top_stime` datetime NULL DEFAULT NULL COMMENT '置顶开始时间',
  `top_etime` datetime NULL DEFAULT NULL COMMENT '置顶结束时间',
  `hot_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '热搜费用',
  `hot_stime` datetime NULL DEFAULT NULL COMMENT '热搜开始时间',
  `hot_etime` datetime NULL DEFAULT NULL COMMENT '热搜结束时间',
  `top` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否置顶0否1待付款2已开通3已过期',
  `hot` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否热搜0否1待付款2已开通3已过期',
  `crm_sup_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '客服包办费用',
  `urgent_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '加急费用',
  `urgent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否加急0否1待付款2已开通3已过期',
  `crm_sup` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否客服包办0否1待付款2已开通3已过期',
  `efunds` decimal(20, 2) NULL DEFAULT NULL COMMENT '托管金额',
  `estate` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '资金托管状况0-无须托管，1-待付款，2-已托管资金，3-已付款给服务商，4-已退款',
  `eto_plat_time` datetime NULL DEFAULT NULL COMMENT '托管资金付款给平台的时间',
  `eto_dev_time` datetime NULL DEFAULT NULL COMMENT '托管资金支付给服务商的时间',
  `eback_time` datetime NULL DEFAULT NULL COMMENT '托管资金退回甲方时间',
  `task_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务编号',
  `top_days` int NULL DEFAULT NULL COMMENT '置顶天数',
  `hot_days` int NULL DEFAULT NULL COMMENT '热搜天数',
  `urgent_days` int NULL DEFAULT NULL COMMENT '加急天数',
  `urgent_stime` datetime NULL DEFAULT NULL COMMENT '加急开始时间',
  `urgent_etime` datetime NULL DEFAULT NULL COMMENT '加急结束时间',
  `calc_status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时检查日期是否已过期，已过期则取消任务中的置顶、加急、热搜状态计算状态0-无须计算，1-本周期已计算待下周期计算，2-结束',
  `calc_time` datetime NULL DEFAULT NULL COMMENT '计算时间',
  `oshare` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否开启分享赚0否1待付款2已开通3已过期',
  `share_fee` decimal(20, 2) NULL DEFAULT NULL COMMENT '分享赚佣金',
  `pay_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '付款流水号(内部生成，传给第三方原样传回，如果不正确，不允许更新数据库，防止作弊)',
  `tran_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '第三方付款事务号',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单名称',
  `biz_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单业务类型1-保证金，2-营销推广活动',
  `project_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `company_id`(`obranch_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务相关费用订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_task_sbill
-- ----------------------------
DROP TABLE IF EXISTS `xm_task_sbill`;
CREATE TABLE `xm_task_sbill`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '结算单据编号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算单标题',
  `amt` decimal(20, 2) NULL DEFAULT NULL COMMENT '金额=工时表中结算金额之和',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '机构编号',
  `deptid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '部门编号',
  `cp_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对方编号(机构写机构号，个人写个人编号)',
  `cp_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对方名称（机构写机构名称，个人写个人名称）',
  `workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '结算工作量=工时表中工时之和',
  `biz_month` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务月份yyyy-MM',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务日期yyyy-MM-dd',
  `biz_flow_state` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算流程状态：0初始1审批中2审批通过3审批不通过4流程取消或者删除',
  `biz_proc_inst_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算流程实例',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-待提交，1-已提交，2-审核已通过，3-待开票，4-已开票待付款，5-已付款',
  `fmsg` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '最后审核意见',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `user_cnt` int NULL DEFAULT NULL COMMENT '结算人数',
  `cp_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对方类型1-个人，2-企业',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '任务结算表-一个结算单对应1个或者多个任务，结算对象只能有一个' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_task_sbill_detail
-- ----------------------------
DROP TABLE IF EXISTS `xm_task_sbill_detail`;
CREATE TABLE `xm_task_sbill_detail`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '员工编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '业务对象主键任务编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务日期yyyy-MM-dd',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `sbill_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算单据编号-来自task_sbill.id',
  `stime` datetime NULL DEFAULT NULL COMMENT '结算提交时间',
  `sstatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算状态0-无需结算，1-待结算2-已提交3-已通过4-已结算',
  `amt` decimal(10, 2) NULL DEFAULT NULL COMMENT '工时对应金额',
  `samt` decimal(10, 0) NULL DEFAULT NULL COMMENT '结算工时对应结算金额-根据结算方案计算结算金额',
  `workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '报工工时',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属项目',
  `sworkload` decimal(10, 2) NULL DEFAULT NULL COMMENT '结算工时，用于结算，默认=workload',
  `biz_month` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '月份yyyy-MM型',
  `budget_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '任务预算金额-来自task表',
  `budget_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '任务预算工时-来自task表',
  `init_workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '任务初始工时-来自task表',
  `quote_at` decimal(10, 2) NULL DEFAULT NULL COMMENT '报价金额-来自task_execuser表',
  `quote_workload` decimal(10, 0) NULL DEFAULT NULL COMMENT '报价工时-来自task_execuser表',
  `sschemel` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务结算方案,来自task表、来自数字字典xmTaskSettleSchemel',
  `uni_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '工时单价，来自task表，根据task_out判断取内部还是外部单价',
  `qend_time` datetime NULL DEFAULT NULL COMMENT '报价结束时间',
  `qstart_time` datetime NULL DEFAULT NULL COMMENT '报价开始时间',
  `act_end_time` datetime NULL DEFAULT NULL COMMENT '实际完工时间-来自task表',
  `act_start_time` datetime NULL DEFAULT NULL COMMENT '实际开始时间-来自task表',
  `oshare` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否开启分享赚',
  `share_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '分享赚佣金',
  `sfee` decimal(10, 2) NULL DEFAULT NULL COMMENT '平台服务费',
  `sfee_rate` int NULL DEFAULT NULL COMMENT '服务费率',
  `cp_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对方编号(机构写机构号，个人写个人编号)',
  `cp_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对方名称（机构写机构名称，个人写个人名称）',
  `cp_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '相对方类型1-个人，2-企业',
  `dist_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '推荐人编号',
  `dist_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '推荐人姓名',
  `share_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '分享码',
  `task_out` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否外购0否1是',
  `crowd` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否众包',
  `oth_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '其它费用',
  `fee_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '费用说明',
  `tact_at` decimal(1, 0) NULL DEFAULT NULL COMMENT '该任务在本次结算前已结算的金额',
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务名称',
  `subject_id` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '费用科目编号',
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '费用科目名称',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目归属机构号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_uni_sbill_Id_userid_task_id`(`userid` ASC, `task_id` ASC, `sbill_id` ASC) USING BTREE COMMENT '同一个结算单的同一个任务、同一个人人不允许重复结算',
  INDEX `sbill_id`(`sbill_id` ASC) USING BTREE,
  CONSTRAINT `xm_task_sbill_detail_ibfk_1` FOREIGN KEY (`sbill_id`) REFERENCES `xm_task_sbill` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '工时结算单明细-一个任务只允许结算一次，一次性结算完毕。任务必须已完工' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_task_skill
-- ----------------------------
DROP TABLE IF EXISTS `xm_task_skill`;
CREATE TABLE `xm_task_skill`  (
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '任务编号',
  `skill_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '技能要求',
  `skill_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能名称',
  `category_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '技能分类',
  PRIMARY KEY (`task_id`, `skill_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '任务技能关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_test_case
-- ----------------------------
DROP TABLE IF EXISTS `xm_test_case`;
CREATE TABLE `xm_test_case`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `case_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `case_remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注',
  `test_step` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '测试步骤[{id:,op:,eresult:,aresult:,tcode:测试结果代码}]',
  `expect_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '期望结果',
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联的故事',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '关联故事名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `luserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '更新人编号',
  `lusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '更新人姓名',
  `cbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建机构',
  `module_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模块编号',
  `module_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模块名称',
  `case_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用例状态0-草稿，1-评审中，2-审核通过，3-审核未通过，4-废弃',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `ver_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '版本号',
  `casedb_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用例库编号',
  `casedb_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用例库名称',
  `func_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能菜单编号',
  `func_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '功能菜单名称',
  `func_pnames` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '上级菜单名称列表逗号分割',
  `pre_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '前置条件描述',
  `case_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用例类型-与bug类型相同',
  `cpriority` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '优先级',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算工时',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际工时',
  `init_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '原估工时',
  `retest` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '是否可作为回归测试用例0-否，1-是',
  `pbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品归属企业',
  `test_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试方式0-手工，1-自动',
  `auto_step` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '自动测试步骤',
  `rate` int NULL DEFAULT NULL COMMENT '进度0-100',
  `sort_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `ctime`(`ctime` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '测试用例' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_test_casedb
-- ----------------------------
DROP TABLE IF EXISTS `xm_test_casedb`;
CREATE TABLE `xm_test_casedb`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用例库名称',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `cbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属机构编号',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品名称',
  `total_cases` int NULL DEFAULT NULL COMMENT '总用例数',
  `ok_cases` int NULL DEFAULT NULL COMMENT '通过用例数',
  `err_cases` int NULL DEFAULT NULL COMMENT '失败用例数',
  `ig_cases` int NULL DEFAULT NULL COMMENT '忽略用例数',
  `bl_cases` int NULL DEFAULT NULL COMMENT '阻塞用例数',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `test_plans` int NULL DEFAULT NULL COMMENT '测试计划数',
  `menus` int NULL DEFAULT NULL COMMENT '需求数目',
  `funcs` int NULL DEFAULT NULL COMMENT '功能模块数',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0初始，1-启用，2关闭',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算工时',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际工时',
  `pbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品归属企业',
  `env_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '环境变量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '测试用例库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_test_plan
-- ----------------------------
DROP TABLE IF EXISTS `xm_test_plan`;
CREATE TABLE `xm_test_plan`  (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '测试计划编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计划名称',
  `casedb_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用例库编号',
  `casedb_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用例库名称',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目名称',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人编号',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `stime` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `etime` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0-未开始，1-进行中，2已结束',
  `tcode` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试结果0未通过，1已通过',
  `total_cases` int NULL DEFAULT NULL COMMENT '总用例数',
  `ok_cases` int NULL DEFAULT NULL COMMENT '通过用例数',
  `err_cases` int NULL DEFAULT NULL COMMENT '失败用例数',
  `ig_cases` int NULL DEFAULT NULL COMMENT '忽略用例数',
  `bl_cases` int NULL DEFAULT NULL COMMENT '阻塞用例数',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品名称',
  `flow_state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '评审结果0-待评审，1-已评审通过，2-已拒绝',
  `bug_cnt` int NULL DEFAULT NULL COMMENT 'bug数目',
  `closed_bugs` int NULL DEFAULT NULL COMMENT '已关闭bug总数',
  `resolved_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `active_bugs` int NULL DEFAULT NULL COMMENT '激活的bug总数',
  `confirmed_bugs` int NULL DEFAULT NULL COMMENT '已解决bug总数',
  `menus` int NULL DEFAULT NULL COMMENT '需求数目',
  `funcs` int NULL DEFAULT NULL COMMENT '功能模块数',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算工时',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际工时',
  `summary_remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '报告总结',
  `cbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建机构编号',
  `to_test_cases` int NULL DEFAULT NULL COMMENT '未测用例数',
  `pbranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品归属企业',
  `ptype` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '计划类型0-普通测试，2-迭代测试，1-发布测试，',
  `test_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试方式0-手工，1-自动化',
  `env_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '环境变量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '测试计划' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xm_test_plan_case
-- ----------------------------
DROP TABLE IF EXISTS `xm_test_plan_case`;
CREATE TABLE `xm_test_plan_case`  (
  `bugs` int NULL DEFAULT NULL COMMENT 'bug数目',
  `exec_userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '执行人',
  `case_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '测试用例编号',
  `ltime` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `exec_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '0-未测，1-通过，2-受阻，3-忽略，4-失败',
  `exec_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行人姓名',
  `priority` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '优先级',
  `remark` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '执行备注',
  `test_step` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '测试步骤[{id:,op:,eresult:,aresult:,tcode:测试结果代码}]',
  `plan_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '计划编号',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目编号',
  `budget_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '预算工时',
  `act_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '实际工时',
  `init_workload` decimal(20, 2) NULL DEFAULT NULL COMMENT '原估工时',
  `exec_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行日期，以执行状态变更日期为准yyyy-MM-dd型',
  `exec_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '执行类型0-手工，1-自动化',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属产品',
  `exec_rate` int NULL DEFAULT NULL COMMENT '进度0-100',
  PRIMARY KEY (`case_id`, `plan_id`) USING BTREE,
  INDEX `create_time`(`ctime` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '测试计划与用例关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xm_workload
-- ----------------------------
DROP TABLE IF EXISTS `xm_workload`;
CREATE TABLE `xm_workload`  (
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '员工编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '姓名',
  `ctime` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `task_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '任务编号，任务报工必填',
  `cuserid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '创建人编号',
  `biz_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务日期yyyy-MM-dd',
  `wstatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '状态0-待确认，1-已确认，2-无效',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL COMMENT '备注',
  `ttype` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '任务类型-关联字典taskType',
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL COMMENT '主键',
  `stime` datetime NULL DEFAULT NULL COMMENT '结算提交时间',
  `sstatus` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算状态0-无需结算，1-待结算2-已提交3-已通过4-已结算',
  `workload` decimal(10, 2) NULL DEFAULT NULL COMMENT '工时，一个task_id可多次提交，小时',
  `rworkload` decimal(10, 2) NULL DEFAULT NULL COMMENT '任务剩余工时（同一天取最后日期更新到task表budget_workload中）',
  `cusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `project_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '归属项目',
  `branch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '项目归属机构',
  `ubranch_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '用户归属机构',
  `sbill_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算单编号',
  `detail_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '结算单明细表id',
  `menu_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '需求编号，缺陷报工、测试报工、任务报工都可以填',
  `product_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '产品编号，能关联到的都填',
  `case_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试用例编号(如果是测试执行报工，必填)',
  `plan_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '测试计划编号(如果是测试执行报工，必填)',
  `bug_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '缺陷编号(如果是缺陷报工，必填)',
  `biz_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '报工类型1-任务，2-缺陷，3-测试用例设计，4-测试执行',
  `biz_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '业务名称',
  `func_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NULL DEFAULT NULL COMMENT '模块编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id` ASC) USING BTREE,
  INDEX `ubranch_id`(`ubranch_id` ASC, `userid` ASC) USING BTREE,
  INDEX `project_id`(`project_id` ASC, `branch_id` ASC, `userid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_croatian_ci COMMENT = '工时登记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- View structure for xxx
-- ----------------------------
DROP VIEW IF EXISTS `xxx`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `xxx` AS select `xm_branch_state`.`project_cnt` AS `project_cnt`,`xm_branch_state`.`biz_date` AS `biz_date`,`xm_branch_state`.`file_cnt` AS `file_cnt`,`xm_branch_state`.`calc_time` AS `calc_time`,`xm_branch_state`.`calc_status` AS `calc_status`,`xm_branch_state`.`phase_cnt` AS `phase_cnt`,`xm_branch_state`.`phase_finish_cnt` AS `phase_finish_cnt`,`xm_branch_state`.`need_pay_at` AS `need_pay_at`,`xm_branch_state`.`finish_pay_at` AS `finish_pay_at`,`xm_branch_state`.`need_col_at` AS `need_col_at`,`xm_branch_state`.`finish_col_at` AS `finish_col_at`,`xm_branch_state`.`risk_cnt` AS `risk_cnt`,`xm_branch_state`.`risk_finish_cnt` AS `risk_finish_cnt`,`xm_branch_state`.`branch_id` AS `branch_id`,`xm_branch_state`.`branch_name` AS `branch_name`,`xm_branch_state`.`budget_nouser_at` AS `budget_nouser_at`,`xm_branch_state`.`budget_ouser_at` AS `budget_ouser_at`,`xm_branch_state`.`budget_iuser_at` AS `budget_iuser_at`,`xm_branch_state`.`act_user_at` AS `act_user_at`,`xm_branch_state`.`act_iuser_at` AS `act_iuser_at`,`xm_branch_state`.`act_ouser_at` AS `act_ouser_at`,`xm_branch_state`.`act_nouser_at` AS `act_nouser_at`,`xm_branch_state`.`finish_rate` AS `finish_rate`,`xm_branch_state`.`budget_workload` AS `budget_workload`,`xm_branch_state`.`budget_ouser_workload` AS `budget_ouser_workload`,`xm_branch_state`.`budget_iuser_workload` AS `budget_iuser_workload`,`xm_branch_state`.`estimate_workload` AS `estimate_workload`,`xm_branch_state`.`act_workload` AS `act_workload`,`xm_branch_state`.`act_ouser_workload` AS `act_ouser_workload`,`xm_branch_state`.`act_iuser_workload` AS `act_iuser_workload`,`xm_branch_state`.`need_pay_cnt` AS `need_pay_cnt`,`xm_branch_state`.`finish_pay_cnt` AS `finish_pay_cnt`,`xm_branch_state`.`finish_pay_user_cnt` AS `finish_pay_user_cnt`,`xm_branch_state`.`need_pay_user_cnt` AS `need_pay_user_cnt`,`xm_branch_state`.`test_cases` AS `test_cases`,`xm_branch_state`.`exec_cases` AS `exec_cases`,`xm_branch_state`.`design_cases` AS `design_cases`,`xm_branch_state`.`finish_cases` AS `finish_cases`,`xm_branch_state`.`iteration_cnt` AS `iteration_cnt`,`xm_branch_state`.`product_cnt` AS `product_cnt`,`xm_branch_state`.`min_start_time` AS `min_start_time`,`xm_branch_state`.`max_end_time` AS `max_end_time`,`xm_branch_state`.`menu_cnt` AS `menu_cnt`,`xm_branch_state`.`menu_finish_cnt` AS `menu_finish_cnt`,`xm_branch_state`.`menu_exec_cnt` AS `menu_exec_cnt`,`xm_branch_state`.`menu_unstart_cnt` AS `menu_unstart_cnt`,`xm_branch_state`.`menu_close_cnt` AS `menu_close_cnt`,`xm_branch_state`.`task_cnt` AS `task_cnt`,`xm_branch_state`.`task_unstart_cnt` AS `task_unstart_cnt`,`xm_branch_state`.`task_exec_cnt` AS `task_exec_cnt`,`xm_branch_state`.`task_finish_cnt` AS `task_finish_cnt`,`xm_branch_state`.`task_set_cnt` AS `task_set_cnt`,`xm_branch_state`.`task_out_cnt` AS `task_out_cnt`,`xm_branch_state`.`task_close_cnt` AS `task_close_cnt`,`xm_branch_state`.`bug_cnt` AS `bug_cnt`,`xm_branch_state`.`closed_bugs` AS `closed_bugs`,`xm_branch_state`.`resolved_bugs` AS `resolved_bugs`,`xm_branch_state`.`active_bugs` AS `active_bugs`,`xm_branch_state`.`confirmed_bugs` AS `confirmed_bugs`,`xm_branch_state`.`plan_workhours` AS `plan_workhours`,`xm_branch_state`.`plan_worker_cnt` AS `plan_worker_cnt`,`xm_branch_state`.`act_worker_cnt` AS `act_worker_cnt`,`xm_branch_state`.`budget_at` AS `budget_at`,`xm_branch_state`.`act_at` AS `act_at`,`xm_branch_state`.`product_budget_workload` AS `product_budget_workload`,`xm_branch_state`.`product_act_workload` AS `product_act_workload` from `xm_branch_state`;

-- ----------------------------
-- Procedure structure for load_project_state_to_xm_branch_state
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_project_state_to_xm_branch_state`;
delimiter ;;
CREATE PROCEDURE `load_project_state_to_xm_branch_state`(IN  inBranchId VARCHAR(50))
BEGIN
	DECLARE calcDate datetime  DEFAULT now();
	DECLARE bizDate VARCHAR(10)  DEFAULT date_format(now(), "%Y-%m-%d"); 
	if inBranchId is null or inBranchId ="" then 
		set inBranchId= null;
	end if;
	
	set time_zone = '+8:00'; 
	set calcDate = now();
	set bizDate = date_format(calcDate, "%Y-%m-%d");
	begin 
	
	insert into xm_branch_state (branch_id,branch_name,biz_date,calc_time,calc_status )
		select ps.* from (select DISTINCT p.branch_id,max(p.branch_name),bizDate,now(),"0"   from xm_project_state p where p.branch_id=  ifnull(inBranchId,p.branch_id) group by p.branch_id  ) as ps left join xm_branch_state s on ps.branch_id=s.branch_id where  ps.branch_id=  ifnull(inBranchId,ps.branch_id)   and s.branch_id is null ;

	
		update xm_branch_state s left join
	
	( 
		select  
		 ss.branch_id,
		  sum(ifnull(ss.budget_workload,0)) as budget_workload , 
			sum(ifnull(ss.budget_iuser_workload,0)) as budget_iuser_workload ,
			sum(ifnull(ss.budget_ouser_workload,0)) as budget_ouser_workload ,
			sum(ifnull(ss.budget_at,0)) as budget_at ,
			sum(ifnull(ss.budget_iuser_at,0)) as budget_iuser_at ,
			sum(ifnull(ss.budget_ouser_at,0)) as budget_ouser_at ,
			sum(ifnull(ss.estimate_workload,0)) as estimate_workload ,
			
			sum(ifnull(ss.act_workload,0)) as act_workload ,
			sum(ifnull(ss.act_iuser_workload,0)) as act_iuser_workload ,
			sum(ifnull(ss.act_ouser_workload,0)) as act_ouser_workload , 
			sum(ifnull(ss.act_at,0)) as act_at ,
			sum(ifnull(ss.act_iuser_at,0)) as act_iuser_at ,
			sum(ifnull(ss.act_ouser_at,0)) as act_ouser_at , 
			
			sum(ifnull(ss.finish_rate,0))/count(1) as raw_rate ,
			
			sum(ifnull(ss.task_cnt,0)) as task_cnt ,
			sum(ifnull(ss.task_unstart_cnt,0)) as task_unstart_cnt ,
			sum(ifnull(ss.task_exec_cnt,0)) as task_exec_cnt ,
			sum(ifnull(ss.task_finish_cnt,0)) as task_finish_cnt ,
			sum(ifnull(ss.task_set_cnt,0)) as task_set_cnt ,
			sum(ifnull(ss.task_close_cnt,0)) as task_close_cnt ,
			
			
			
			sum(ifnull(ss.phase_cnt,0)) as phase_cnt ,
			sum(ifnull(ss.phase_finish_cnt,0)) as phase_finish_cnt ,
			
			sum(ifnull(ss.test_cases,0)) as test_cases ,
			sum(ifnull(ss.exec_cases,0)) as exec_cases ,
			sum(ifnull(ss.design_cases,0)) as design_cases ,
			sum(ifnull(ss.finish_cases,0)) as finish_cases ,
			
			
			sum(ifnull(ss.bug_cnt,0)) as bug_cnt ,
			sum(ifnull(ss.active_bugs,0)) as active_bugs ,
			sum(ifnull(ss.confirmed_bugs,0)) as confirmed_bugs ,
			sum(ifnull(ss.resolved_bugs,0)) as resolved_bugs ,
			sum(ifnull(ss.closed_bugs,0)) as closed_bugs, 
			
			count(distinct ss.project_id ) as project_cnt,
			sum(ifnull(ss.plan_worker_cnt,0)) as plan_worker_cnt,
			 
			min(ss.min_start_time) as min_start_time,
			max(ss.max_end_time) as max_end_time
			
		from xm_project_state ss inner join xm_project p on p.id=ss.project_id 
		where ss.branch_id=  ifnull(inBranchId,ss.branch_id) and  p.del!='1' and p.is_tpl!='1'
		group by ss.branch_id
		) as s2 on s2.branch_id=s.branch_id
	set 
	
			s.budget_workload=ifnull(s2.budget_workload,0),
			s.budget_iuser_workload=ifnull(s2.budget_iuser_workload,0),
			s.budget_ouser_workload=ifnull(s2.budget_ouser_workload,0),
	    s.budget_at=ifnull(s2.budget_at,0),
			s.budget_iuser_at=ifnull(s2.budget_iuser_at,0),
			s.budget_ouser_at=ifnull(s2.budget_ouser_at,0),
			s.estimate_workload=ifnull(s2.estimate_workload,0),
			
	    s.act_workload=ifnull(s2.act_workload,0),
			s.act_iuser_workload=ifnull(s2.act_iuser_workload,0),
	    s.act_ouser_workload=ifnull(s2.act_ouser_workload,0), 
			s.act_at=ifnull(s2.act_at,0),
	    s.act_iuser_at=ifnull(s2.act_iuser_at,0), 
			s.act_ouser_at=ifnull(s2.act_ouser_at,0), 
			
			s.finish_rate=case when  ifnull(s2.budget_workload,0)  >0
			     then 100*(ifnull(s2.act_workload,0)) / s2.budget_workload
					 when ifnull(s2.act_workload,0)>0 then 100
					 else  0  end,
					 
			s.task_cnt=ifnull(s2.task_cnt,0),
			s.task_unstart_cnt=ifnull(s2.task_unstart_cnt,0),
			s.task_finish_cnt=ifnull(s2.task_finish_cnt,0),
			s.task_set_cnt=ifnull(s2.task_set_cnt,0),
			s.task_close_cnt=ifnull(s2.task_close_cnt,0),   
			
			s.phase_cnt=ifnull(s2.phase_cnt,0),    
			s.phase_finish_cnt=ifnull(s2.phase_finish_cnt,0),   
 			
			s.test_cases=ifnull(s2.test_cases,0),
			s.exec_cases=ifnull(s2.exec_cases,0),
			s.design_cases=ifnull(s2.design_cases,0),
			s.finish_cases=ifnull(s2.finish_cases,0),
			
			s.bug_cnt=ifnull(s2.bug_cnt,0),
			s.active_bugs=ifnull(s2.active_bugs,0),
			s.confirmed_bugs=ifnull(s2.confirmed_bugs,0),
			s.resolved_bugs=ifnull(s2.resolved_bugs,0),
			s.closed_bugs=ifnull(s2.closed_bugs,0), 
			
			s.project_cnt=ifnull(s2.project_cnt,0),
			s.plan_worker_cnt=ifnull(s2.plan_worker_cnt,0),
			
			s.biz_date=bizDate,
			s.min_start_time=s2.min_start_time,
			s.max_end_time=s2.max_end_time
			
	where  s.branch_id=  ifnull(inBranchId,s.branch_id); 
	
	
	update xm_branch_state s left join (select p.branch_id, count( * ) as product_cnt,sum(i.budget_workload) as product_budget_workload,sum(i.act_workload) as product_act_workload,sum(i.iteration_cnt) as iteration_cnt,sum(i.menu_cnt) as menu_cnt ,
	
						sum( i.menu_unstart_cnt ) as menu_unstart_cnt,
						sum( i.menu_exec_cnt ) as menu_exec_cnt,
						sum( i.menu_finish_cnt) as menu_finish_cnt,
						sum( i.menu_close_cnt ) as menu_close_cnt
	from xm_product_state i inner join xm_product p on i.product_id=p.id  where p.branch_id=ifnull(inBranchId,p.branch_id) and p.is_tpl!='1' and p.del!='1' group by p.branch_id ) as i on s.branch_id=s.branch_id 
	set s.iteration_cnt=i.iteration_cnt,
	s.product_cnt=i.product_cnt,
	s.menu_cnt=i.menu_cnt,
	s.product_budget_workload=i.product_budget_workload,
	s.product_act_workload=i.product_act_workload,
	s.menu_unstart_cnt=i.menu_unstart_cnt,
	s.menu_exec_cnt=i.menu_exec_cnt,
	s.menu_finish_cnt=i.menu_finish_cnt,
	s.menu_close_cnt=i.menu_close_cnt
	where s.branch_id=ifnull(inBranchId,s.branch_id);
	 
		
	
	delete from xm_branch_state_his h where h.branch_id=ifnull(inBranchId,h.branch_id) and h.biz_date=bizDate;
		insert into xm_branch_state_his select s.* from  xm_branch_state	s left join xm_branch_state_his his2 on s.branch_id=his2.branch_id and s.biz_date=his2.biz_date 
	where his2.biz_date is null and s.branch_id = ifnull(inBranchId,s.branch_id);
	
	end; 
	
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_project_to_xm_project_state
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_project_to_xm_project_state`;
delimiter ;;
CREATE PROCEDURE `load_project_to_xm_project_state`(IN inProjectId VARCHAR(50))
BEGIN
	DECLARE sqlx TEXT DEFAULT 'replace into xm_project_state (project_id,project_status,project_name,biz_date,calc_time,calc_status,branch_id,total_budget_nouser_amount,total_budget_out_user_amount,total_budget_inner_user_amount,total_plan_workload,total_plan_inner_user_workload
	,total_plan_out_user_workload)
		select  p.id,p.status,p.name,date_format(now(), "%Y-%m-%d"),now(),"0",p.branch_id,p.plan_nouser_at,p.plan_out_user_at,p.plan_inner_user_at,p.plan_workload,p.plan_inner_user_workload,p.plan_out_user_workload  from xm_project p';
		
		DECLARE sqlxHis TEXT DEFAULT 'replace into xm_project_state_his select * from  xm_project_state p where p.biz_date = date_format(date_sub(now(),interval 1 day), "%Y-%m-%d") ';
		
	begin
		IF  inProjectId = null or inProjectId = ''
		THEN  
			set sqlx = concat(sqlx,' where p.status !="3" ');
		ELSE 
			set sqlx = concat(sqlx,' where  p.status !="3" ');
			set sqlxHis = concat(sqlxHis,'  and  p.project_id= "',inProjectId,'" ');
		END IF; 
		
		set @sql =sqlx;
		PREPARE stmt FROM @sql;
		EXECUTE stmt;
		
		
		set @sqlxHis = sqlxHis;
		PREPARE stmt2 FROM @sqlxHis;
		EXECUTE stmt2;
		 
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_tasks_settle_to_xm_project_state
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_tasks_settle_to_xm_project_state`;
delimiter ;;
CREATE PROCEDURE `load_tasks_settle_to_xm_project_state`(IN inProjectId VARCHAR(50))
BEGIN
	DECLARE calcDate datetime  DEFAULT now();
	DECLARE bizDate VARCHAR(10)  DEFAULT date_format(now(), "%Y-%m-%d");
	if inProjectId is null or inProjectId ="" then 
		set inProjectId= null;
	end if;
	begin
		update xm_project_state s left join
	
	( 
		select  
		 q.project_id,
			sum(q.act_cost_amount) as total_cost_user_amount,
			sum(case when q.cost_type='1' then ifnull(q.act_cost_amount,0) else 0 end)  as total_cost_iuser_amount,
			sum(case when q.cost_type!='2' then ifnull(q.act_cost_amount,0) else 0 end)  as total_cost_ouser_amount, 
			sum(case when q.pay_status='0' then ifnull(q.act_cost_amount,0) else 0 end)  as total_need_pay_amount, 
			sum(case when q.pay_status='1' then ifnull(q.act_cost_amount,0) else 0 end)  as total_finish_pay_amount,  
			sum(case when q.pay_status='0' then 1 else 0 end)  as total_need_pay_cnt,
			sum(case when q.pay_status='1' then 1 else 0 end)  as total_finish_pay_cnt, 
			count( distinct case  when q.pay_status='0' then q.userid else null end)  as total_need_pay_user_cnt, 
			count( distinct case  when q.pay_status='1' then q.userid else null end)  as total_finish_pay_user_cnt
	from xm_project_m_cost_user q 
	where  q.project_id=  ifnull(inProjectId,q.project_id) 
	group by q.project_id 
	
	) as tc on s.project_id=tc.project_id
	left join 
	(
		select  
		 nuser.project_id,
		 sum( ifnull(nuser.act_cost_amount,0) ) as total_cost_nouser_amount

		from xm_project_m_cost_nouser nuser
	where  nuser.project_id=  ifnull(inProjectId,nuser.project_id)
	
	 group by nuser.project_id 
	) as nuser on nuser.project_id=s.project_id
	set s.total_cost_iuser_amount=tc.total_cost_iuser_amount,
			s.total_cost_ouser_amount=tc.total_cost_ouser_amount,
			s.total_need_pay_amount=tc.total_need_pay_amount,
	    s.total_finish_pay_amount=tc.total_finish_pay_amount,
			s.total_need_pay_cnt=tc.total_need_pay_cnt,
			s.total_finish_pay_cnt=tc.total_finish_pay_cnt,
			s.total_finish_pay_cnt=tc.total_finish_pay_cnt,
			s.total_need_pay_user_cnt=tc.total_need_pay_user_cnt,
			s.total_finish_pay_user_cnt=tc.total_finish_pay_user_cnt,
			
			
			s.total_cost_nouser_amount=nuser.total_cost_nouser_amount,
			s.total_cost_user_amount=ifnull(tc.total_cost_iuser_amount,0)+ifnull(tc.total_cost_ouser_amount,0)
			
	where   s.project_id=  ifnull(inProjectId,s.project_id) and s.project_status!='3'   
			;
		
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_tasks_to_xm_iteration_state
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_tasks_to_xm_iteration_state`;
delimiter ;;
CREATE PROCEDURE `load_tasks_to_xm_iteration_state`(IN inIterationId VARCHAR(50))
BEGIN
	DECLARE calcDate datetime  DEFAULT now();
	DECLARE bizDate VARCHAR(10)  DEFAULT date_format(now(), "%Y-%m-%d");
 	
	if inIterationId is null or inIterationId ="" then 
		set inIterationId= null;
	end if;

	set time_zone = '+8:00'; 
	set calcDate = now();
	set bizDate = date_format(calcDate, "%Y-%m-%d");
	begin  
 		insert into xm_iteration_state (iteration_id,iteration_name,biz_date) select i.id as iteration_id,i.iteration_name,bizDate from xm_iteration i left join xm_iteration_state s on s.iteration_id=i.id  where i.id=  ifnull(inIterationId,i.id)   and s.iteration_id is null and i.ctime > date_sub(calcDate, INTERVAL 12 month);
		 
		update xm_iteration_state s inner join xm_iteration ii on s.iteration_id=ii.id left join 
	( select m.iteration_id,
			sum(ifnull(ss.budget_workload,0)) as budget_workload , 
			sum(ifnull(ss.budget_iuser_workload,0)) as budget_iuser_workload ,
			sum(ifnull(ss.budget_ouser_workload,0)) as budget_ouser_workload ,
			sum(ifnull(ss.budget_at,0)) as budget_at ,
			sum(ifnull(ss.budget_iuser_at,0)) as budget_iuser_at ,
			sum(ifnull(ss.budget_ouser_at,0)) as budget_ouser_at ,
			sum(ifnull(ss.estimate_workload,0)) as estimate_workload,
			
			sum(ifnull(ss.act_workload,0)) as act_workload ,
			sum(ifnull(ss.act_iuser_workload,0)) as act_iuser_workload ,
			sum(ifnull(ss.act_ouser_workload,0)) as act_ouser_workload , 
			sum(ifnull(ss.act_at,0)) as act_at ,
			sum(ifnull(ss.act_iuser_at,0)) as act_iuser_at ,
			sum(ifnull(ss.act_ouser_at,0)) as act_ouser_at , 
			
			sum(ifnull(ss.finish_rate,0))/count(1) as raw_rate ,
			
			sum(ifnull(ss.task_cnt,0)) as task_cnt ,
			sum(ifnull(ss.task_unstart_cnt,0)) as task_unstart_cnt ,
			sum(ifnull(ss.task_exec_cnt,0)) as task_exec_cnt ,
			sum(ifnull(ss.task_finish_cnt,0)) as task_finish_cnt ,
			sum(ifnull(ss.task_set_cnt,0)) as task_set_cnt ,
			sum(ifnull(ss.task_close_cnt,0)) as task_close_cnt ,
			
			
			sum(ifnull(ss.test_cases,0)) as test_cases ,
			sum(ifnull(ss.exec_cases,0)) as exec_cases ,
			sum(ifnull(ss.design_cases,0)) as design_cases ,
			sum(ifnull(ss.finish_cases,0)) as finish_cases ,
			
			
			sum(ifnull(ss.bug_cnt,0)) as bug_cnt ,
			sum(ifnull(ss.active_bugs,0)) as active_bugs ,
			sum(ifnull(ss.confirmed_bugs,0)) as confirmed_bugs ,
			sum(ifnull(ss.resolved_bugs,0)) as resolved_bugs ,
			sum(ifnull(ss.closed_bugs,0)) as closed_bugs,
			
			count(1) as menu_cnt,
 			count( distinct if(m.`status`='0' and m.dclass='3',m.menu_id,null)) as menu_unstart_cnt,
 			count( distinct if(m.`status`in ('1','2','3','4','5','6') and m.dclass='3',m.menu_id,null)) as menu_exec_cnt,
 			count( distinct if(m.`status`='7' and m.dclass='3',m.menu_id,null)) as menu_finish_cnt,
 			count( distinct if(m.`status`in ('8','9') and m.dclass='3',m.menu_id,null)) as menu_close_cnt,
			 
			min(ss.min_start_time) as min_start_time,
			max(ss.max_end_time) as max_end_time
			
		from xm_menu_state ss inner join xm_menu m on m.menu_id=ss.menu_id where m.iteration_id=ifnull(inIterationId,m.iteration_id)
    and m.iteration_id is not null and m.dclass='3'	group by m.iteration_id ) as s2 on s2.iteration_id=s.iteration_id
	set 
	
			s.budget_workload=ifnull(s2.budget_workload,0),
			ii.budget_workload=ifnull(s2.budget_workload,0),
			s.budget_iuser_workload=ifnull(s2.budget_iuser_workload,0),
			s.budget_ouser_workload=ifnull(s2.budget_ouser_workload,0),
	    s.budget_at=ifnull(s2.budget_at,0),
			ii.budget_cost=ifnull(s2.budget_at,0),
			s.budget_iuser_at=ifnull(s2.budget_iuser_at,0),
			s.budget_ouser_at=ifnull(s2.budget_ouser_at,0),
			s.estimate_workload=ifnull(s2.estimate_workload,0),
			
	    s.act_workload=ifnull(s2.act_workload,0),
			s.act_iuser_workload=ifnull(s2.act_iuser_workload,0),
	    s.act_ouser_workload=ifnull(s2.act_ouser_workload,0), 
			s.act_at=ifnull(s2.act_at,0), 
	    s.act_iuser_at=ifnull(s2.act_iuser_at,0), 
			s.act_ouser_at=ifnull(s2.act_ouser_at,0), 
			
			s.finish_rate=case when  ifnull(s2.budget_workload,0)  >0
			     then 100*(ifnull(s2.act_workload,0)) / s2.budget_workload
					 when ifnull(s2.act_workload,0)>0 then 100
					 else  0  end,
					 
			s.task_cnt=ifnull(s2.task_cnt,0),
			s.task_unstart_cnt=ifnull(s2.task_unstart_cnt,0),
			s.task_finish_cnt=ifnull(s2.task_finish_cnt,0),
			s.task_set_cnt=ifnull(s2.task_set_cnt,0),
			s.task_close_cnt=ifnull(s2.task_close_cnt,0),   
			 
 			
			s.test_cases=ifnull(s2.test_cases,0),
			s.exec_cases=ifnull(s2.exec_cases,0),
			s.design_cases=ifnull(s2.design_cases,0),
			s.finish_cases=ifnull(s2.finish_cases,0),
			
			s.bug_cnt=ifnull(s2.bug_cnt,0),
			s.active_bugs=ifnull(s2.active_bugs,0),
			s.confirmed_bugs=ifnull(s2.confirmed_bugs,0),
			s.resolved_bugs=ifnull(s2.resolved_bugs,0),
			s.closed_bugs=ifnull(s2.closed_bugs,0),  
			
			s.menu_cnt=ifnull(s2.menu_cnt,0),
			s.menu_unstart_cnt=ifnull(s2.menu_unstart_cnt,0),
			s.menu_exec_cnt=ifnull(s2.menu_exec_cnt,0),
			s.menu_finish_cnt=ifnull(s2.menu_finish_cnt,0),
			s.menu_close_cnt=ifnull(s2.menu_close_cnt,0), 
			s.product_cnt=1,
			
			s.biz_date=bizDate,
			s.min_start_time=s2.min_start_time,
			s.max_end_time=s2.max_end_time
			
	where  s.iteration_id=   ifnull(inIterationId,s.iteration_id)   
	and   ii.istatus<"7" and ii.ctime > date_sub(calcDate, interval 12 month);  
	 
	 
	 	update xm_iteration_state s inner join (
	
	select iteration_id,
	count(distinct q.project_id) as project_cnt, 
	count(distinct  q.executor_userid) as plan_worker_cnt, 
	
	count(*) as task_cnt,
	sum( case when q.task_state ='0'	then 1 else 0 end ) as task_unstart_cnt,
	sum( case when q.task_state ='1' then 1 else 0 end ) as task_exec_cnt,
	sum( case when q.task_state ='2' then 1 else 0 end ) as task_finish_cnt,
	sum( case when q.task_state ='3' then 1 else 0 end ) as task_set_cnt,
	sum( case when q.task_state ='4' then 1 else 0 end ) as task_close_cnt,
	sum( case when q.task_out='1' then 1 else 0 end ) as task_out_cnt

from xm_task q inner join xm_menu m on q.menu_id=m.menu_id and m.iteration_id=ifnull(inIterationId,m.iteration_id)
	where m.iteration_id=ifnull(inIterationId,m.iteration_id) group by m.iteration_id ) as tc on tc.iteration_id=s.iteration_id 
	set 
			s.project_cnt=ifnull(tc.project_cnt,0),
			s.plan_worker_cnt=ifnull(tc.plan_worker_cnt,0),
			s.task_cnt=ifnull(tc.task_cnt,0),
			s.task_unstart_cnt=ifnull(tc.task_unstart_cnt,0),
			s.task_exec_cnt=ifnull(tc.task_exec_cnt,0),
			s.task_finish_cnt=ifnull(tc.task_finish_cnt,0),
			s.task_set_cnt=ifnull(tc.task_set_cnt,0),
			s.task_close_cnt=ifnull(tc.task_close_cnt,0),
			s.task_out_cnt=ifnull(tc.task_out_cnt,0)
	where s.iteration_id=ifnull(inIterationId,s.iteration_id);
			
	delete from xm_iteration_state_his h where h.iteration_id=ifnull(inIterationId,h.iteration_id) and h.biz_date=bizDate;
		insert into xm_iteration_state_his select s.* from  xm_iteration_state	s left join xm_iteration_state_his his2 on s.iteration_id=his2.iteration_id and s.biz_date=his2.biz_date 
	where his2.biz_date is null and s.iteration_id = ifnull(inIterationId,s.iteration_id);
	
	
			
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_tasks_to_xm_menu_state
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_tasks_to_xm_menu_state`;
delimiter ;;
CREATE PROCEDURE `load_tasks_to_xm_menu_state`(IN inProductId VARCHAR(50))
BEGIN
	DECLARE calcDate datetime  DEFAULT now();
	DECLARE bizDate VARCHAR(10)  DEFAULT date_format(now(), "%Y-%m-%d");
			declare i int;
			
	set time_zone = '+8:00'; 
	set calcDate = now();
	set bizDate = date_format(calcDate, "%Y-%m-%d");
	begin 
 
 		insert into xm_menu_state (product_id,menu_id,biz_date) select i.product_id,i.menu_id,bizDate from xm_menu i left join xm_menu_state s on s.product_id=i.product_id and s.menu_id=i.menu_id  where i.product_id=ifnull(inProductId,i.product_id)  and s.menu_id is null;
		 
		update xm_menu_state s inner join    xm_menu m on s.menu_id=s.menu_id left join 
		(select  
		  q.product_id,  
			q.menu_id,
			sum( ifnull(q.budget_workload,0) ) as budget_workload,
			sum(case when q.task_out='1' then ifnull(q.budget_workload,0) else 0 end)  as budget_ouser_workload,
			sum(case when q.task_out!='1' then ifnull(q.budget_workload,0) else 0 end)  as budget_iuser_workload,
		  sum(case when q.task_out!='1' then ifnull(q.budget_at,0) else 0 end) as budget_iuser_at,
		  sum(case when q.task_out='1' then ifnull(q.budget_at,0) else 0 end) as budget_ouser_at,
		  sum(case when q.task_out!='1' then ifnull(q.act_at,0) else 0 end) as act_iuser_at,
		  sum(case when q.task_out='1' then ifnull(q.act_at,0) else 0 end) as act_ouser_at,
		  sum( ifnull(q.act_at,0)  ) as act_at,
			sum( ifnull(q.act_workload,0) ) as act_workload,
			sum(case when q.task_out='1' then ifnull(q.act_workload,0) else 0 end)  as act_ouser_workload,
			sum(case when q.task_out!='1' then ifnull(q.act_workload,0) else 0 end)  as act_iuser_workload,
			sum( ifnull( q.rate ,0) * ifnull(q.budget_workload ,0) ) as budget_workload_rate,   
			sum( ifnull( q.rate ,0) )/count(1) as raw_rate,   
			sum( if(q.end_time<=now(),ifnull(q.budget_workload,DATEDIFF(q.end_time,q.start_time)*8),0))+sum( if(q.end_time>now() and q.start_time<=now(),ifnull(q.budget_workload,DATEDIFF(q.end_time,q.start_time)*8)*(now()-q.start_time)/(q.end_time-q.start_time),0 ))+sum( if(q.start_time>now(),0,0) ) as estimate_workload, 
			count(1) as task_cnt,
			sum( ifnull(q.budget_at,0)) as budget_at,
			sum( case when q.task_state ='0'	then 1 else 0 end ) as task_unstart_cnt,
			sum( case when q.task_state ='1' then 1 else 0 end ) as task_exec_cnt,
			sum( case when q.task_state in ('2','3') then 1 else 0 end ) as task_finish_cnt,
			sum( case when q.task_state ='4' then 1 else 0 end ) as task_set_cnt,
			sum( case when q.task_state ='9' then 1 else 0 end ) as task_close_cnt,
			sum( case when q.task_out='1' then 1 else 0 end ) as task_out_cnt,
			count( distinct q.executor_userid) as plan_worker_cnt,
			min(q.start_time) as start_time,
			max(q.end_time) as end_time
		from  xm_task q 
		where q.product_id=ifnull(inProductId,q.product_id) and q.ntype='0'
		group by q.product_id ,q.menu_id ) as tc  on s.menu_id=tc.menu_id and s.product_id= ifnull(inProductId,s.product_id) 
	
		left join 
		(
 
		select 
		c.menu_id, 
		sum( ifnull(e.budget_workload,0) )  as budget_workload,
		sum( ifnull(e.init_workload,0) )  as init_workload,
		sum( ifnull(e.act_workload,0) )  as act_workload,
		sum( if(p.etime<=now(),ifnull(e.budget_workload ,0),0 ))+sum( if( p.etime>now() and p.stime<=now(),IFNULL(e.budget_workload,0)*(now()-p.stime)/(p.etime-p.stime),0 ))+sum(if(p.stime>now(),0,0)) as estimate_workload,
		
		sum( ifnull( e.exec_rate ,0) )/count(1) as raw_rate,    
		count( * ) as test_cases,
		sum( case when e.exec_status in ('1','3','4') then 1 else 0 end ) as exec_cases, 
		sum( case when e.exec_status='0' then 1 else 0 end ) as design_cases,
		sum( case when e.exec_status='2' then 1 else 0 end ) as finish_cases
		from xm_test_plan_case e inner join xm_test_case c on c.id=e.case_id  inner join xm_test_plan p on p.id=e.plan_id
		where c.product_id=ifnull(inProductId,c.product_id)
 		group by c.menu_id
		) as bc on s.menu_id=bc.menu_id		
		
	  left join 
		(
 
		select 
		e.menu_id, 
		sum( ifnull(e.budget_workload,0) )  as budget_workload,
		sum( ifnull(e.init_workload,0) )  as init_workload,
		sum( ifnull(e.act_workload,0) )  as act_workload,
		sum( if(e.ctime+1<=now(),ifnull(e.budget_workload ,0),0 )) as estimate_workload, 
				
		sum( ifnull( e.rate ,0) )/count(1) as raw_rate,    
		count( * ) as test_cases
		from xm_test_case e   
		where e.product_id=ifnull(inProductId,e.product_id)
 		group by e.menu_id
		) as cc on s.menu_id=cc.menu_id		
		
		left join 
		(
 
		select  
		e.menu_id,
		sum( ifnull(e.budget_workload,0) )  as budget_workload,
			sum(case when e.task_out='1' then ifnull(e.budget_workload,0) else 0 end)  as budget_ouser_workload,
			sum(case when e.task_out!='1' then ifnull(e.budget_workload,0) else 0 end)  as budget_iuser_workload,
		  sum(case when e.task_out!='1' then ifnull(e.budget_at,0) else 0 end) as budget_iuser_at,
		  sum(case when e.task_out='1' then ifnull(e.budget_at,0) else 0 end) as budget_ouser_at,
		  sum(case when e.task_out!='1' then ifnull(e.act_at,0) else 0 end) as act_iuser_at,
		  sum(case when e.task_out='1' then ifnull(e.act_at,0) else 0 end) as act_ouser_at,
		  sum( ifnull(e.act_at,0)  ) as act_at,
			sum( ifnull(e.act_workload,0) ) as act_workload,
			sum(case when e.task_out='1' then ifnull(e.act_workload,0) else 0 end)  as act_ouser_workload,
			sum(case when e.task_out!='1' then ifnull(e.act_workload,0) else 0 end)  as act_iuser_workload,
			sum( if(e.end_time<=now(),ifnull(e.budget_workload,0),0))+sum( if(e.end_time>now() and e.create_time<=now(),ifnull(e.budget_workload,0)*(now()-e.create_time)/(e.end_time-e.create_time),0 )) as estimate_workload, 
 			sum( ifnull( e.rate ,0))/count(1)    as raw_rate,
		sum( 1 ) as bug_cnt,
		sum( case when e.bug_status='1' then 1 else 0 end ) as active_bugs,
		sum( case when e.bug_status in ('2','3','4','5','6','9') then 1 else 0 end ) as confirmed_bugs,
		sum( case when e.bug_status='7' then 1 else 0 end ) as resolved_bugs,
		sum( case when e.bug_status='8' then 1 else 0 end ) as closed_bugs,
		min(e.create_time) as start_time,
		max(e.end_time) as end_time
		from xm_question  e inner join xm_menu m on e.menu_id=m.menu_id 
		where m.product_id=ifnull(inProductId,m.product_id) and m.dclass='3'
 		group by e.menu_id
		) as b on  s.menu_id=b.menu_id
	set  
			s.estimate_workload=ifnull(tc.estimate_workload,0)+ifnull(b.estimate_workload,0)+ifnull(bc.estimate_workload,0)+ifnull(cc.estimate_workload,0),
			s.budget_nouser_at=0, 
			s.budget_iuser_at=ifnull(tc.budget_iuser_at,0)+ifnull(b.budget_iuser_at,0),
			s.budget_workload=ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0)+ifnull(bc.budget_workload,0)+ifnull(cc.budget_workload,0), 
			s.budget_iuser_workload=ifnull(tc.budget_iuser_workload,0)+ifnull(b.budget_iuser_workload,0),
			s.budget_ouser_workload=ifnull(tc.budget_ouser_workload,0)+ifnull(b.budget_ouser_workload,0),
			s.act_workload=ifnull(tc.act_workload,0)+ifnull(b.act_workload,0)+ifnull(bc.act_workload,0)+ifnull(cc.act_workload,0),
			s.act_ouser_workload=ifnull(tc.act_ouser_workload,0)+ifnull(b.budget_workload,0),
	    s.act_iuser_workload=ifnull(tc.act_iuser_workload,0)+ifnull(b.budget_workload,0),
			s.finish_rate=case when  
			ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0)+ifnull(bc.budget_workload,0)+ifnull(cc.budget_workload,0) >0 then (ifnull(tc.act_workload,0)+ifnull(b.act_workload,0)+ifnull(bc.act_workload,0)+ifnull(cc.act_workload,0))*100/(ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0)+ifnull(bc.budget_workload,0)+ifnull(cc.budget_workload,0))
			when (ifnull(tc.act_workload,0)+ifnull(b.act_workload,0)+ifnull(bc.act_workload,0)+ifnull(cc.act_workload,0))>0 then 100
			when   (ifnull(tc.raw_rate,0)+ifnull(b.raw_rate,0)++ifnull(bc.raw_rate,0)+ifnull(cc.raw_rate,0))>0 then  (ifnull(tc.raw_rate,0)+ifnull(b.raw_rate,0)+ifnull(bc.raw_rate,0)+ifnull(cc.raw_rate,0)) /(if(tc.raw_rate>0,1,0)+if(b.raw_rate>0,1,0)+if(bc.raw_rate>0,1,0)+if(cc.raw_rate>0,1,0))
			else 0 end, 
			s.budget_at=ifnull(tc.budget_at,0),
			s.budget_iuser_at=ifnull(tc.budget_iuser_at,0)+ifnull(b.budget_iuser_at,0),
			s.budget_ouser_at=ifnull(tc.budget_at,0)+ifnull(b.budget_ouser_at,0),
			s.act_at=ifnull(tc.act_at,0)+ifnull(b.act_at,0),
			s.act_iuser_at=ifnull(tc.act_iuser_at,0)+ifnull(b.act_iuser_at,0),
			s.act_ouser_at=ifnull(tc.act_ouser_at,0)+ifnull(b.act_ouser_at,0),
			
			s.task_cnt=ifnull(tc.task_cnt,0),
			s.task_unstart_cnt=ifnull(tc.task_unstart_cnt,0),
			s.task_exec_cnt=ifnull(tc.task_exec_cnt,0),
			s.task_finish_cnt=ifnull(tc.task_finish_cnt,0),
			s.task_set_cnt=ifnull(tc.task_set_cnt,0),
			s.task_close_cnt=ifnull(tc.task_close_cnt,0),
			s.task_out_cnt=ifnull(tc.task_out_cnt,0),
			
			s.plan_worker_cnt=ifnull(tc.plan_worker_cnt,0),   
			
			s.test_cases=ifnull(bc.test_cases,0),
			s.exec_cases=ifnull(bc.exec_cases,0),
			s.design_cases=ifnull(bc.design_cases,0),
			s.finish_cases=ifnull(bc.finish_cases,0),
			
			s.bug_cnt=ifnull(b.bug_cnt,0),
			s.active_bugs=ifnull(b.active_bugs,0),
			s.confirmed_bugs=ifnull(b.confirmed_bugs,0),
			s.resolved_bugs=ifnull(b.resolved_bugs,0),
			s.closed_bugs=ifnull(b.closed_bugs,0),
			
			s.calc_time=calcDate, 
			s.biz_date=bizDate,
			s.min_start_time=if(tc.start_time<b.start_time,tc.start_time,b.start_time),
			s.max_end_time=if(tc.end_time>b.end_time,tc.end_time,b.end_time)
			
	where  s.product_id=  ifnull(inProductId,s.product_id) and m.dclass='3' ;
			
			set i=4;
			while i>0 do
           
					 update xm_menu_state s  left join 
			
			(select m.pmenu_id,
			count(1) as children_cnt,
			sum(ss.budget_workload) as budget_workload , 
			sum(ss.budget_iuser_workload) as budget_iuser_workload ,
			sum(ss.budget_ouser_workload) as budget_ouser_workload ,
			sum(ss.budget_at) as budget_at ,
			sum(ss.budget_iuser_at) as budget_iuser_at ,
			sum(ss.budget_ouser_at) as budget_ouser_at ,
			
			sum(ss.act_workload) as act_workload ,
			sum(ss.act_iuser_workload) as act_iuser_workload ,
			sum(ss.act_ouser_workload) as act_ouser_workload , 
			sum(ss.act_at) as act_at ,
			sum(ss.act_iuser_at) as act_iuser_at ,
			sum(ss.act_ouser_at) as act_ouser_at , 
			
			sum(ifnull(ss.finish_rate,0))/count(1) as raw_rate ,
			
			sum(ss.task_cnt) as task_cnt ,
			sum(ss.task_unstart_cnt) as task_unstart_cnt ,
			sum(ss.task_exec_cnt) as task_exec_cnt ,
			sum(ss.task_finish_cnt) as task_finish_cnt ,
			sum(ss.task_set_cnt) as task_set_cnt ,
			sum(ss.task_close_cnt) as task_close_cnt ,
			
			
			sum(ss.test_cases) as test_cases ,
			sum(ss.exec_cases) as exec_cases ,
			sum(ss.design_cases) as design_cases ,
			sum(ss.finish_cases) as finish_cases ,
			
			
			sum(ss.bug_cnt) as bug_cnt ,
			sum(ss.active_bugs) as active_bugs ,
			sum(ss.confirmed_bugs) as confirmed_bugs ,
			sum(ss.resolved_bugs) as resolved_bugs ,
			sum(ss.closed_bugs) as closed_bugs,
			 
			min(ss.min_start_time) as min_start_time,
			max(ss.max_end_time) as max_end_time
		
	
	from  xm_menu m inner join xm_menu_state ss on ss.menu_id=m.menu_id   where m.product_id= ifnull(inProductId,m.product_id)  group by m.pmenu_id) s2 on s2.pmenu_id=s.menu_id inner join xm_menu xmm on xmm.menu_id=s.menu_id
				set
			
			s.budget_workload=ifnull(s2.budget_workload,0),
			s.budget_iuser_workload=ifnull(s2.budget_iuser_workload,0),
			s.budget_ouser_workload=ifnull(s2.budget_ouser_workload,0),
	    s.budget_at=ifnull(s2.budget_at,0),
			s.budget_iuser_at=ifnull(s2.budget_iuser_at,0),
			s.budget_ouser_at=ifnull(s2.budget_ouser_at,0),
			
	    s.act_workload=ifnull(s2.act_workload,0),
			s.act_iuser_workload=ifnull(s2.act_iuser_workload,0),
	    s.act_ouser_workload=ifnull(s2.act_ouser_workload,0), 
			s.act_at=ifnull(s2.act_at,0),
	    s.act_iuser_at=ifnull(s2.act_iuser_at,0), 
			s.act_ouser_at=ifnull(s2.act_ouser_at,0), 
			
			s.finish_rate=case when  ifnull(s2.budget_workload,0)  >0
			     then 100*(ifnull(s2.act_workload,0)) / s2.budget_workload
					 when ifnull(s2.act_workload,0)>0 then 100
					 else  0  end,
					 
			s.task_cnt=ifnull(s2.task_cnt,0),
			s.task_unstart_cnt=ifnull(s2.task_unstart_cnt,0),
			s.task_finish_cnt=ifnull(s2.task_finish_cnt,0),
			s.task_set_cnt=ifnull(s2.task_set_cnt,0),
			s.task_close_cnt=ifnull(s2.task_close_cnt,0),   
			 
 			
			s.test_cases=ifnull(s2.test_cases,0),
			s.exec_cases=ifnull(s2.exec_cases,0),
			s.design_cases=ifnull(s2.design_cases,0),
			s.finish_cases=ifnull(s2.finish_cases,0),
			
			s.bug_cnt=ifnull(s2.bug_cnt,0),
			s.active_bugs=ifnull(s2.active_bugs,0),
			s.confirmed_bugs=ifnull(s2.confirmed_bugs,0),
			s.resolved_bugs=ifnull(s2.resolved_bugs,0),
			s.closed_bugs=ifnull(s2.closed_bugs,0),
			 
			s.biz_date=bizDate,
			s.min_start_time=s2.min_start_time,
			s.max_end_time=s2.max_end_time
			 
			where s.product_id= ifnull(inProductId,s.product_id) and xmm.lvl=i and xmm.dclass!='3';
					 
					 
					 
          set i=i-1;
      end while;
			
			
			
update xm_menu_state s inner join (
select m.menu_id, count(distinct m1.iteration_id) as iteration_cnt,count(distinct m1.product_id) as product_cnt,count(distinct m1.menu_id) as menu_cnt,max(m.children_cnt) as children_cnt,count(distinct t.project_id) as project_cnt from xm_menu m left join  xm_menu m1 on m1.pid_paths like CONCAT(m.pid_paths,'%') left join xm_task t on  m1.menu_id=t.menu_id 
where m.product_id = ifnull(inProductId,m.product_id) and m.dclass!='3'
group by m.menu_id ) as b on s.menu_id = b.menu_id 
set s.iteration_cnt=b.iteration_cnt,s.project_cnt=b.project_cnt;
			
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_tasks_to_xm_product_state
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_tasks_to_xm_product_state`;
delimiter ;;
CREATE PROCEDURE `load_tasks_to_xm_product_state`(IN inProductId VARCHAR(50))
BEGIN
	DECLARE calcDate datetime  DEFAULT now();
	DECLARE bizDate VARCHAR(10)  DEFAULT date_format(now(), "%Y-%m-%d");

 	
	if inProductId is null or inProductId ="" then 
		set inProductId= null;
	end if;
	
	set time_zone = '+8:00'; 
	set calcDate = now();
	set bizDate = date_format(calcDate, "%Y-%m-%d");
	begin 
 
 		insert into xm_product_state (branch_id,product_id,product_name,biz_date) select i.branch_id,i.id,i.product_name,bizDate from xm_product i left join xm_product_state s on s.product_id=i.id    where i.id= ifnull(inProductId,i.id)  and s.product_id is null;
		 
		update xm_product_state s left join
	
	( select   
			m.product_id,
			sum(ifnull(ss.budget_workload,0)) as budget_workload , 
			sum(ifnull(ss.budget_iuser_workload,0)) as budget_iuser_workload ,
			sum(ifnull(ss.budget_ouser_workload,0)) as budget_ouser_workload ,
			sum(ifnull(ss.budget_at,0)) as budget_at ,
			sum(ifnull(ss.budget_iuser_at,0)) as budget_iuser_at ,
			sum(ifnull(ss.budget_ouser_at,0)) as budget_ouser_at ,
			sum(ifnull(ss.estimate_workload,0)) as estimate_workload,
			
			sum(ifnull(ss.act_workload,0)) as act_workload ,
			sum(ifnull(ss.act_iuser_workload,0)) as act_iuser_workload ,
			sum(ifnull(ss.act_ouser_workload,0)) as act_ouser_workload , 
			sum(ifnull(ss.act_at,0)) as act_at ,
			sum(ifnull(ss.act_iuser_at,0)) as act_iuser_at ,
			sum(ifnull(ss.act_ouser_at,0)) as act_ouser_at , 
			
			sum(ifnull(ss.finish_rate,0))/count(1) as raw_rate ,
			
			sum(ifnull(ss.task_cnt,0)) as task_cnt ,
			sum(ifnull(ss.task_unstart_cnt,0)) as task_unstart_cnt ,
			sum(ifnull(ss.task_exec_cnt,0)) as task_exec_cnt ,
			sum(ifnull(ss.task_finish_cnt,0)) as task_finish_cnt ,
			sum(ifnull(ss.task_set_cnt,0)) as task_set_cnt ,
			sum(ifnull(ss.task_close_cnt,0)) as task_close_cnt ,
			
			
			sum(ifnull(ss.test_cases,0)) as test_cases ,
			sum(ifnull(ss.exec_cases,0)) as exec_cases ,
			sum(ifnull(ss.design_cases,0)) as design_cases ,
			sum(ifnull(ss.finish_cases,0)) as finish_cases ,
			
			
			sum(ifnull(ss.bug_cnt,0)) as bug_cnt ,
			sum(ifnull(ss.active_bugs,0)) as active_bugs ,
			sum(ifnull(ss.confirmed_bugs,0)) as confirmed_bugs ,
			sum(ifnull(ss.resolved_bugs,0)) as resolved_bugs ,
			sum(ifnull(ss.closed_bugs,0)) as closed_bugs,
			
			count( DISTINCT  m.menu_id ) as menu_cnt,
 			count( distinct if(m.`status`='0' and m.dclass='3',m.menu_id,null)) as menu_unstart_cnt,
 			count( distinct if(m.`status`in ('1','2','3','4','5','6') and m.dclass='3',m.menu_id,null)) as menu_exec_cnt,
 			count( distinct if(m.`status`='7' and m.dclass='3',m.menu_id,null)) as menu_finish_cnt,
 			count( distinct if(m.`status`in ('8','9') and m.dclass='3',m.menu_id,null)) as menu_close_cnt,
			
			count(DISTINCT m.iteration_id ) as iteration_cnt,
			 
			min(ss.min_start_time) as min_start_time,
			max(ss.max_end_time) as max_end_time
			
		from xm_menu m left join xm_menu_state ss on m.menu_id=ss.menu_id 
		 where m.product_id=ifnull(inProductId,m.product_id) and m.dclass='3'
		 group by m.product_id
		) as s2 on  s.product_id=s2.product_id
		left join 
		(
 
		select 
		e.product_id, 
		
			sum( ifnull(e.budget_workload,0) )  as budget_workload,
			sum(case when e.task_out='1' then ifnull(e.budget_workload,0) else 0 end)  as budget_ouser_workload,
			sum(case when e.task_out!='1' then ifnull(e.budget_workload,0) else 0 end)  as budget_iuser_workload,
		  sum(case when e.task_out!='1' then ifnull(e.budget_at,0) else 0 end) as budget_iuser_at,
		  sum(case when e.task_out='1' then ifnull(e.budget_at,0) else 0 end) as budget_ouser_at,
		  sum(case when e.task_out!='1' then ifnull(e.act_at,0) else 0 end) as act_iuser_at,
		  sum(case when e.task_out='1' then ifnull(e.act_at,0) else 0 end) as act_ouser_at,
		  sum( ifnull(e.act_at,0)  ) as act_at,
			sum( ifnull(e.act_workload,0) ) as act_workload,
			sum(case when e.task_out='1' then ifnull(e.act_workload,0) else 0 end)  as act_ouser_workload,
			sum(case when e.task_out!='1' then ifnull(e.act_workload,0) else 0 end)  as act_iuser_workload,
			sum( if(e.end_time<=now(),ifnull(e.budget_workload ,0),0 ))+sum( if(e.budget_workload!=null and e.end_time>now() and e.create_time<now(),e.budget_workload*(now()-e.create_time)/(e.end_time-e.create_time),0 )) as estimate_workload,
 			sum( ifnull( e.rate ,0))/count(1)    as raw_rate,
		sum( 1 ) as bug_cnt,
		sum( case when e.bug_status='1' then 1 else 0 end ) as active_bugs,
		sum( case when e.bug_status in ('2','3','4','5','6','9') then 1 else 0 end ) as confirmed_bugs,
		sum( case when e.bug_status='7' then 1 else 0 end ) as resolved_bugs,
		sum( case when e.bug_status='8' then 1 else 0 end ) as closed_bugs,
		min(e.create_time) as start_time,
		max(e.end_time) as end_time
		from xm_question e 
		where e.product_id=  ifnull(inProductId,e.product_id)
		group by e.product_id
		) as b on s.product_id=b.product_id
	set  
			s.budget_workload=ifnull(s2.budget_workload,0),
			s.budget_iuser_workload=ifnull(s2.budget_iuser_workload,0),
			s.budget_ouser_workload=ifnull(s2.budget_ouser_workload,0),
	    s.budget_at=ifnull(s2.budget_at,0),
			s.budget_iuser_at=ifnull(s2.budget_iuser_at,0),
			s.budget_ouser_at=ifnull(s2.budget_ouser_at,0),
			s.estimate_workload=ifnull(s2.estimate_workload,0),
			
	    s.act_workload=ifnull(s2.act_workload,0),
			s.act_iuser_workload=ifnull(s2.act_iuser_workload,0),
	    s.act_ouser_workload=ifnull(s2.act_ouser_workload,0), 
			s.act_at=ifnull(s2.act_at,0),
	    s.act_iuser_at=ifnull(s2.act_iuser_at,0), 
			s.act_ouser_at=ifnull(s2.act_ouser_at,0), 
			
			s.finish_rate=case when  ifnull(s2.budget_workload,0)  >0
			     then 100*(ifnull(s2.act_workload,0)) / s2.budget_workload
					 when ifnull(s2.act_workload,0)>0 then 100
					 else  0  end,
					 
			s.task_cnt=ifnull(s2.task_cnt,0),
			s.task_unstart_cnt=ifnull(s2.task_unstart_cnt,0),
			s.task_exec_cnt=ifnull(s2.task_exec_cnt,0),
			s.task_finish_cnt=ifnull(s2.task_finish_cnt,0),
			s.task_set_cnt=ifnull(s2.task_set_cnt,0),
			s.task_close_cnt=ifnull(s2.task_close_cnt,0),   
			
			s.menu_cnt=ifnull(s2.menu_cnt,0),
			s.menu_unstart_cnt=ifnull(s2.menu_unstart_cnt,0),
			s.menu_exec_cnt=ifnull(s2.menu_exec_cnt,0),
			s.menu_finish_cnt=ifnull(s2.menu_finish_cnt,0),
			s.menu_close_cnt=ifnull(s2.menu_close_cnt,0), 
			 
 			
			s.test_cases=ifnull(s2.test_cases,0),
			s.exec_cases=ifnull(s2.exec_cases,0),
			s.design_cases=ifnull(s2.design_cases,0),
			s.finish_cases=ifnull(s2.finish_cases,0),
			
			s.bug_cnt=ifnull(b.bug_cnt,0),
			s.active_bugs=ifnull(b.active_bugs,0),
			s.confirmed_bugs=ifnull(b.confirmed_bugs,0),
			s.resolved_bugs=ifnull(b.resolved_bugs,0),
			s.closed_bugs=ifnull(b.closed_bugs,0), 
			
			s.iteration_cnt= ifnull(s2.iteration_cnt,0),
			
			s.biz_date=bizDate,
			s.min_start_time=s2.min_start_time,
			s.max_end_time=s2.max_end_time
			
	where  s.product_id=   ifnull(inProductId,s.product_id);
	
	update xm_product_state s inner join (
	
	select product_id,
	count(distinct q.project_id) as project_cnt,
	count(distinct if(q.ntype='1',q.id,null)) as phase_cnt,
	count(distinct if(q.ntype='1' and q.task_state >'1',q.id,null)) as phase_finish_cnt,
	count(distinct  q.executor_userid) as plan_worker_cnt

from xm_task q 
	where q.product_id=ifnull(inProductId,q.product_id) group by q.product_id ) as t on t.product_id=s.product_id 
	set s.project_cnt=ifnull(t.project_cnt,0),s.phase_cnt=ifnull(t.phase_cnt,0),s.phase_finish_cnt=ifnull(t.phase_finish_cnt,0),
	s.plan_worker_cnt=ifnull(t.plan_worker_cnt,0)
	where s.product_id=ifnull(inProductId,s.product_id);
 
	
	delete from xm_product_state_his h where h.product_id=ifnull(inProductId,h.product_id) and h.biz_date=bizDate;
		insert into xm_product_state_his select s.* from  xm_product_state	s left join xm_product_state_his his2 on s.product_id=his2.product_id and s.biz_date=his2.biz_date 
	where his2.biz_date is null and s.product_id = ifnull(inProductId,s.product_id);
	
	
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_tasks_to_xm_project_group_state
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_tasks_to_xm_project_group_state`;
delimiter ;;
CREATE PROCEDURE `load_tasks_to_xm_project_group_state`(IN inProjectId VARCHAR(50))
BEGIN
	
	DECLARE calcDate datetime  DEFAULT now();
	DECLARE bizDate VARCHAR(10)  DEFAULT date_format(now(), "%Y-%m-%d");
  	
	if inProjectId is null or inProjectId ="" then 
		set inProjectId= null;
	end if;
	set time_zone = '+8:00'; 
	set calcDate = now();
	set bizDate = date_format(calcDate, "%Y-%m-%d");
	begin
	
	insert into xm_group_state (group_id,group_name,project_id,project_name,project_status,biz_date,calc_time,calc_status,branch_id,budget_nouser_at,budget_ouser_at,budget_iuser_at,budget_workload,budget_iuser_workload,budget_ouser_workload)
		select  g.id,g.group_name,p.id,p.name,p.status,bizDate,now(),"0",p.branch_id,p.plan_nouser_at,p.plan_ouser_at,p.plan_iuser_at,p.plan_workload,p.plan_iuser_workload,p.plan_ouser_workload  from xm_project p inner join xm_group g on p.id=g.project_id left join xm_group_state s on s.group_id=g.id  where  p.id=  ifnull(inProjectId,p.id)	and s.group_id is null and p.status !="9" and p.del!='1';
		
		update xm_group_state s inner join
	
	( 
		select  
		 gu.group_id,
		 gu.project_id,
			sum( ifnull(q.budget_workload,0) ) as budget_workload,
			sum(case when q.task_out='1' then ifnull(q.budget_workload,0) else 0 end)  as budget_ouser_workload,
			sum(case when q.task_out!='1' then ifnull(q.budget_workload,0) else 0 end)  as budget_iuser_workload,
		  sum(case when q.task_out!='1' then ifnull(q.budget_at,0) else 0 end) as budget_iuser_at,
		  sum(case when q.task_out='1' then ifnull(q.budget_at,0) else 0 end) as budget_ouser_at,
		  sum(case when q.task_out!='1' then ifnull(q.act_at,0) else 0 end) as act_iuser_at,
		  sum(case when q.task_out='1' then ifnull(q.act_at,0) else 0 end) as act_ouser_at,
		  sum( ifnull(q.act_at,0)  ) as act_at,
			sum( ifnull(q.act_workload,0) ) as act_workload,
			sum(case when q.task_out='1' then ifnull(q.act_workload,0) else 0 end)  as act_ouser_workload,
			sum(case when q.task_out!='1' then ifnull(q.act_workload,0) else 0 end)  as act_iuser_workload,
			sum( ifnull( q.rate ,0) * ifnull(q.budget_workload ,0) ) as budget_workload_rate,   
			sum( ifnull( q.rate ,0) )/count(1) as raw_rate,   

			sum( if(q.end_time<=now(),ifnull(q.budget_workload,DATEDIFF(q.end_time,q.start_time)*8),0))+sum( if(q.end_time>now() and q.start_time<=now(),ifnull(q.budget_workload,DATEDIFF(q.end_time,q.start_time)*8)*(now()-q.start_time)/(q.end_time-q.start_time),0 ))+sum( if(q.start_time>now(),0,0) ) as estimate_workload, 
			
			sum( ifnull(q.budget_at,0)) as budget_at,
			count(*) as task_cnt,
			sum( case when q.task_state ='0'	then 1 else 0 end ) as task_unstart_cnt,
			sum( case when q.task_state ='1' then 1 else 0 end ) as task_exec_cnt,
			sum( case when q.task_state  in ('2', '3') then 1 else 0 end ) as task_finish_cnt,
			sum( case when q.task_state ='4' then 1 else 0 end ) as task_set_cnt,
			sum( case when q.task_state ='9' then 1 else 0 end ) as task_close_cnt,
			sum( case when q.task_out='1' then 1 else 0 end ) as task_out_cnt,
 			count( distinct if(im.dclass='3',q.menu_id,null)) as menu_cnt, 
 			count( distinct if(im.`status`='0' and im.dclass='3',im.menu_id,null)) as menu_unstart_cnt,
 			count( distinct if(im.`status`in ('1','2','3','4','5','6') and im.dclass='3',im.menu_id,null)) as menu_exec_cnt,
 			count( distinct if(im.`status`='7' and im.dclass='3',im.menu_id,null)) as menu_finish_cnt,
 			count( distinct if(im.`status`in ('8','9') and im.dclass='3',im.menu_id,null)) as menu_close_cnt,
			count( distinct im.product_id) as product_cnt,
			count( distinct im.iteration_id) as iteration_cnt,
			count( distinct gu.userid) as plan_worker_cnt,
			min(q.start_time) as start_time,
			max(q.end_time) as end_time
			

	from (select gu.project_id,gu.userid,max(gu.group_id) group_id from xm_group_user gu where  gu.project_id =  ifnull(inProjectId,gu.project_id)  group by gu.project_id,gu.userid) as gu left join xm_task q  on q.project_id=gu.project_id and ifnull(q.executor_userid,q.create_userid)=gu.userid and q.ntype!='1'  left join  xm_menu im on q.menu_id=im.menu_id and im.dclass ='3'
	where  gu.project_id =  ifnull(inProjectId,gu.project_id)
	
	group by gu.project_id,gu.group_id
	
	) as tc on s.project_id=tc.project_id and s.group_id=tc.group_id
	
		left join 
		( 
		select 
		gu.project_id,
		gu.group_id,
		count( DISTINCT e.case_id ) as test_cases,
		sum( case when e.exec_status>'1' then 1 else 0 end ) as exec_cases,
		sum( case when e.exec_status='0' then 1 else 0 end ) as design_cases,
		sum( case when e.exec_status='1' then 1 else 0 end ) as finish_cases
		from xm_test_plan_case e right join (select gu.project_id,gu.userid,max(gu.group_id) group_id from xm_group_user gu where  gu.project_id =  ifnull(inProjectId,gu.project_id)  group by gu.project_id,gu.userid) as gu on e.project_id = gu.project_id and e.exec_userid=gu.userid
		where gu.project_id=  ifnull(inProjectId,gu.project_id)
		group by gu.project_id,gu.group_id
		) as bc on s.project_id=bc.project_id	and s.group_id=bc.group_id	left join 
		( 
		select 
		gu.project_id, 
		gu.group_id,
			sum( ifnull(e.budget_workload,0) )  as budget_workload,
			sum(case when e.task_out='1' then ifnull(e.budget_workload,0) else 0 end)  as budget_ouser_workload,
			sum(case when e.task_out!='1' then ifnull(e.budget_workload,0) else 0 end)  as budget_iuser_workload,
		  sum(case when e.task_out!='1' then ifnull(e.budget_at,0) else 0 end) as budget_iuser_at,
		  sum(case when e.task_out='1' then ifnull(e.budget_at,0) else 0 end) as budget_ouser_at,
		  sum(case when e.task_out!='1' then ifnull(e.act_at,0) else 0 end) as act_iuser_at,
		  sum(case when e.task_out='1' then ifnull(e.act_at,0) else 0 end) as act_ouser_at,
		  sum( ifnull(e.act_at,0)  ) as act_at,
			sum( ifnull(e.act_workload,0) ) as act_workload,
			sum(case when e.task_out='1' then ifnull(e.act_workload,0) else 0 end)  as act_ouser_workload,
			sum(case when e.task_out!='1' then ifnull(e.act_workload,0) else 0 end)  as act_iuser_workload,
			sum( if(e.end_time<=now(),ifnull(e.budget_workload ,0),0 ))+sum( if(e.budget_workload!=null and e.end_time>now() and e.create_time<now(),e.budget_workload*(now()-e.create_time)/(e.end_time-e.create_time),0 )) as estimate_workload,
 			sum( ifnull( e.rate ,0))/count(1)    as raw_rate,
		count( DISTINCT e.id ) as bug_cnt,
		sum( case when e.bug_status='1' then 1 else 0 end ) as active_bugs,
		sum( case when e.bug_status in ('2','3','4','5','6','9') then 1 else 0 end ) as confirmed_bugs,
		sum( case when e.bug_status='7' then 1 else 0 end ) as resolved_bugs,
		sum( case when e.bug_status='8' then 1 else 0 end ) as closed_bugs,
		min(e.create_time) as start_time,
		max(e.end_time) as end_time
		from xm_question e right join (select gu.project_id,gu.userid,max(gu.group_id) group_id from xm_group_user gu where  gu.project_id =  ifnull(inProjectId,gu.project_id)  group by gu.project_id,gu.userid) as gu on gu.project_id=e.project_id and gu.userid=e.handler_userid
		where gu.project_id=  ifnull(inProjectId,gu.project_id)
		group by gu.project_id,gu.group_id
		) as b on s.project_id=b.project_id and s.group_id=b.group_id
	set   
			s.estimate_workload=ifnull(tc.estimate_workload,0)+ifnull(b.estimate_workload,0),
			s.budget_nouser_at=0, 
			s.budget_iuser_at=ifnull(tc.budget_iuser_at,0)+ifnull(b.budget_iuser_at,0),
			s.budget_workload=ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0), 
			s.budget_iuser_workload=ifnull(tc.budget_iuser_workload,0)+ifnull(b.budget_iuser_workload,0),
			s.budget_ouser_workload=ifnull(tc.budget_ouser_workload,0)+ifnull(b.budget_ouser_workload,0),
			s.act_workload=ifnull(tc.act_workload,0)+ifnull(b.act_workload,0),
			s.act_ouser_workload=ifnull(tc.act_ouser_workload,0)+ifnull(b.budget_workload,0),
	    s.act_iuser_workload=ifnull(tc.act_iuser_workload,0)+ifnull(b.budget_workload,0),
			s.finish_rate=case when  
			ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0) >0 then (ifnull(tc.act_workload,0)+ifnull(b.act_workload,0))*100/(ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0))
			when (ifnull(tc.act_workload,0)+ifnull(b.act_workload,0))>0 then 100
			when   (ifnull(tc.raw_rate,0)+ifnull(b.raw_rate,0))>0 then  (ifnull(tc.raw_rate,0)+ifnull(b.raw_rate,0)) /(if(tc.raw_rate>0,1,0)+if(b.raw_rate>0,1,0))
			else 0 end, 
			s.budget_at=ifnull(tc.budget_at,0),
			s.budget_iuser_at=ifnull(tc.budget_iuser_at,0)+ifnull(b.budget_iuser_at,0),
			s.budget_ouser_at=ifnull(tc.budget_at,0)+ifnull(b.budget_ouser_at,0),
			s.act_at=ifnull(tc.act_at,0)+ifnull(b.act_at,0),
			s.act_iuser_at=ifnull(tc.act_iuser_at,0)+ifnull(b.act_iuser_at,0),
			s.act_ouser_at=ifnull(tc.act_ouser_at,0)+ifnull(b.act_ouser_at,0),
			
			s.task_cnt=ifnull(tc.task_cnt,0),
			s.task_unstart_cnt=ifnull(tc.task_unstart_cnt,0),
			s.task_exec_cnt=ifnull(tc.task_exec_cnt,0),
			s.task_finish_cnt=ifnull(tc.task_finish_cnt,0),
			s.task_set_cnt=ifnull(tc.task_set_cnt,0),
			s.task_close_cnt=ifnull(tc.task_close_cnt,0),
			s.task_out_cnt=ifnull(tc.task_out_cnt,0),
			
			s.plan_worker_cnt=ifnull(tc.plan_worker_cnt,0),  
			s.product_cnt=ifnull(tc.product_cnt,0),
			s.iteration_cnt=ifnull(tc.iteration_cnt,0),
			
			s.test_cases=ifnull(bc.test_cases,0),
			s.exec_cases=ifnull(bc.exec_cases,0),
			s.design_cases=ifnull(bc.design_cases,0),
			s.finish_cases=ifnull(bc.finish_cases,0),
			
			s.bug_cnt=ifnull(b.bug_cnt,0),
			s.active_bugs=ifnull(b.active_bugs,0),
			s.confirmed_bugs=ifnull(b.confirmed_bugs,0),
			s.resolved_bugs=ifnull(b.resolved_bugs,0),
			s.closed_bugs=ifnull(b.closed_bugs,0),
			
			s.calc_time=calcDate, 
			s.biz_date=bizDate,
			s.min_start_time=if(tc.start_time<b.start_time,tc.start_time,b.start_time),
			s.max_end_time=if(tc.end_time>b.end_time,tc.end_time,b.end_time),
			
			s.menu_cnt=ifnull(tc.menu_cnt,0),
			s.menu_unstart_cnt=tc.menu_unstart_cnt,
			s.menu_exec_cnt=tc.menu_exec_cnt,
			s.menu_finish_cnt=ifnull(tc.menu_finish_cnt,0), 
			s.menu_close_cnt=tc.menu_close_cnt
			
	where  s.project_id=  ifnull(inProjectId,s.project_id) 
			;
			
			update xm_group_state s inner join (select gu.project_id,gu.group_id, count(if(phase.ntype='1',phase.id,null)) as phase_cnt, count(if(phase.ntype='1' and phase.task_state in ('3','4','9'),phase.id,null)) as phase_finish_cnt from xm_task phase right join xm_group_user gu on phase.create_userid=gu.userid and phase.project_id=gu.project_id 
			where gu.project_id=ifnull(inProjectId,gu.project_id)
			group by gu.project_id,gu.group_id
			) as p on s.project_id=p.project_id and s.group_id=p.group_id
			set s.phase_cnt=p.phase_cnt,s.phase_finish_cnt=p.phase_finish_cnt
			where s.project_id=ifnull(inProjectId,s.project_id) ;
			
				
	delete from xm_group_state_his h where h.project_id=ifnull(inProjectId,h.project_id) and h.biz_date=bizDate;
		insert into xm_group_state_his select s.* from  xm_group_state	s left join xm_group_state_his his2 on s.project_id=his2.project_id and s.biz_date=his2.biz_date 
	where his2.biz_date is null and s.project_id = ifnull(inProjectId,s.project_id);
	
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_tasks_to_xm_project_state
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_tasks_to_xm_project_state`;
delimiter ;;
CREATE PROCEDURE `load_tasks_to_xm_project_state`(IN inProjectId VARCHAR(50))
BEGIN
	
	DECLARE calcDate datetime  DEFAULT now();
	DECLARE bizDate VARCHAR(10)  DEFAULT date_format(now(), "%Y-%m-%d");
  	
	if inProjectId is null or inProjectId ="" then 
		set inProjectId= null;
	end if;
	set time_zone = '+8:00'; 
	set calcDate = now();
	set bizDate = date_format(calcDate, "%Y-%m-%d");
	begin
	
	insert into xm_project_state (project_id,project_status,project_name,biz_date,calc_time,calc_status,branch_id,budget_nouser_at,budget_ouser_at,budget_iuser_at,budget_workload,budget_iuser_workload,budget_ouser_workload)
		select  p.id,p.status,p.name,bizDate,now(),"0",p.branch_id,p.plan_nouser_at,p.plan_ouser_at,p.plan_iuser_at,p.plan_workload,p.plan_iuser_workload,p.plan_ouser_workload  from xm_project p left join xm_project_state s on p.id=s.project_id  where  p.id=  ifnull(inProjectId,p.id)  and s.project_id is null and p.status !="9" and p.del!='1';
		
		update xm_project_state s inner join
	
	( 
		select  
		 p.id as project_id,
		 max(p.`status`) as `status`,  
			sum( ifnull(q.budget_workload,0) ) as budget_workload,
			sum(case when q.task_out='1' then ifnull(q.budget_workload,0) else 0 end)  as budget_ouser_workload,
			sum(case when q.task_out!='1' then ifnull(q.budget_workload,0) else 0 end)  as budget_iuser_workload,
		  sum(case when q.task_out!='1' then ifnull(q.budget_at,0) else 0 end) as budget_iuser_at,
		  sum(case when q.task_out='1' then ifnull(q.budget_at,0) else 0 end) as budget_ouser_at,
		  sum(case when q.task_out!='1' then ifnull(q.act_at,0) else 0 end) as act_iuser_at,
		  sum(case when q.task_out='1' then ifnull(q.act_at,0) else 0 end) as act_ouser_at,
		  sum( ifnull(q.act_at,0)  ) as act_at,
			sum( ifnull(q.act_workload,0) ) as act_workload,
			sum(case when q.task_out='1' then ifnull(q.act_workload,0) else 0 end)  as act_ouser_workload,
			sum(case when q.task_out!='1' then ifnull(q.act_workload,0) else 0 end)  as act_iuser_workload,
			sum( ifnull( q.rate ,0) * ifnull(q.budget_workload ,0) ) as budget_workload_rate,   
			sum( ifnull( q.rate ,0) )/count(1) as raw_rate, 
			sum( if(q.end_time<=now(),ifnull(q.budget_workload,DATEDIFF(q.end_time,q.start_time)*8),0))+sum( if(q.end_time>now() and q.start_time<=now(),ifnull(q.budget_workload,DATEDIFF(q.end_time,q.start_time)*8)*(now()-q.start_time)/(q.end_time-q.start_time),0 ))+sum( if(q.start_time>now(),0,0) ) as estimate_workload, 
			
			sum( ifnull(q.budget_at,0)) as budget_at,
			count(*) as task_cnt,
			sum( case when q.task_state ='0'	then 1 else 0 end ) as task_unstart_cnt,
			sum( case when q.task_state ='1' then 1 else 0 end ) as task_exec_cnt,
			sum( case when q.task_state in ('2', '3') then 1 else 0 end ) as task_finish_cnt,
			sum( case when q.task_state ='4' then 1 else 0 end ) as task_set_cnt,
			sum( case when q.task_state ='9' then 1 else 0 end ) as task_close_cnt,
			sum( case when q.task_out='1' then 1 else 0 end ) as task_out_cnt,
 			count( distinct if(im.dclass='3',q.menu_id,null)) as menu_cnt, 
 			count( distinct if(im.`status`='0' and im.dclass='3',im.menu_id,null)) as menu_unstart_cnt,
 			count( distinct if(im.`status`in ('1','2','3','4','5','6') and im.dclass='3',im.menu_id,null)) as menu_exec_cnt,
 			count( distinct if(im.`status`='7' and im.dclass='3',im.menu_id,null)) as menu_finish_cnt,
 			count( distinct if(im.`status`in ('8','9') and im.dclass='3',im.menu_id,null)) as menu_close_cnt,
			count( distinct im.product_id) as product_cnt,
			count( distinct im.iteration_id) as iteration_cnt,
			count( distinct q.executor_userid) as plan_worker_cnt,
			min(q.start_time) as start_time,
			max(q.end_time) as end_time
			

	from xm_project p left join xm_task q  on q.project_id=p.id and q.ntype!='1'  left join  xm_menu im on q.menu_id=im.menu_id and im.dclass ='3'
	where  p.id=  ifnull(inProjectId,p.id)  and p.status not in('8','9')
	
	group by p.id 
	
	) as tc on s.project_id=tc.project_id
	
		left join 
		( 
		select 
		e.project_id,
		count( * ) as test_cases,
		sum( case when e.exec_status>'1' then 1 else 0 end ) as exec_cases,
		sum( case when e.exec_status='0' then 1 else 0 end ) as design_cases,
		sum( case when e.exec_status='1' then 1 else 0 end ) as finish_cases
		from xm_test_plan_case e 
		where e.project_id=  ifnull(inProjectId,e.project_id)
		group by e.project_id
		) as bc on s.project_id=bc.project_id		left join 
		( 
		select 
		e.project_id, 
		
			sum( ifnull(e.budget_workload,0) )  as budget_workload,
			sum(case when e.task_out='1' then ifnull(e.budget_workload,0) else 0 end)  as budget_ouser_workload,
			sum(case when e.task_out!='1' then ifnull(e.budget_workload,0) else 0 end)  as budget_iuser_workload,
		  sum(case when e.task_out!='1' then ifnull(e.budget_at,0) else 0 end) as budget_iuser_at,
		  sum(case when e.task_out='1' then ifnull(e.budget_at,0) else 0 end) as budget_ouser_at,
		  sum(case when e.task_out!='1' then ifnull(e.act_at,0) else 0 end) as act_iuser_at,
		  sum(case when e.task_out='1' then ifnull(e.act_at,0) else 0 end) as act_ouser_at,
		  sum( ifnull(e.act_at,0)  ) as act_at,
			sum( ifnull(e.act_workload,0) ) as act_workload,
			sum(case when e.task_out='1' then ifnull(e.act_workload,0) else 0 end)  as act_ouser_workload,
			sum(case when e.task_out!='1' then ifnull(e.act_workload,0) else 0 end)  as act_iuser_workload, 
			sum( if(e.end_time<=now(),ifnull(e.budget_workload,1),0))+sum( if(e.end_time>now() and e.create_time<=now(),ifnull(e.budget_workload,1)*(now()-e.create_time)/(e.end_time-e.create_time),0 )) as estimate_workload, 
 			sum( ifnull( e.rate ,0))/count(1)    as raw_rate,
		sum( 1 ) as bug_cnt,
		sum( case when e.bug_status='1' then 1 else 0 end ) as active_bugs,
		sum( case when e.bug_status in ('2','3','4','5','6','9') then 1 else 0 end ) as confirmed_bugs,
		sum( case when e.bug_status='7' then 1 else 0 end ) as resolved_bugs,
		sum( case when e.bug_status='8' then 1 else 0 end ) as closed_bugs,
		min(e.create_time) as start_time,
		max(e.end_time) as end_time
		from xm_question e 
		where e.project_id=  ifnull(inProjectId,e.project_id)
		group by e.project_id
		) as b on s.project_id=b.project_id
	set   
			s.estimate_workload=ifnull(tc.estimate_workload,0)+ifnull(b.estimate_workload,0),
			s.budget_nouser_at=0, 
			s.budget_iuser_at=ifnull(tc.budget_iuser_at,0)+ifnull(b.budget_iuser_at,0),
			s.budget_workload=ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0), 
			s.budget_iuser_workload=ifnull(tc.budget_iuser_workload,0)+ifnull(b.budget_iuser_workload,0),
			s.budget_ouser_workload=ifnull(tc.budget_ouser_workload,0)+ifnull(b.budget_ouser_workload,0),
			s.act_workload=ifnull(tc.act_workload,0)+ifnull(b.act_workload,0),
			s.act_ouser_workload=ifnull(tc.act_ouser_workload,0)+ifnull(b.budget_workload,0),
	    s.act_iuser_workload=ifnull(tc.act_iuser_workload,0)+ifnull(b.budget_workload,0),
			s.finish_rate=case when  
			ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0) >0 then (ifnull(tc.act_workload,0)+ifnull(b.act_workload,0))*100/(ifnull(tc.budget_workload,0)+ifnull(b.budget_workload,0))
			when (ifnull(tc.act_workload,0)+ifnull(b.act_workload,0))>0 then 100
			when   (ifnull(tc.raw_rate,0)+ifnull(b.raw_rate,0))>0 then  (ifnull(tc.raw_rate,0)+ifnull(b.raw_rate,0)) /(if(tc.raw_rate>0,1,0)+if(b.raw_rate>0,1,0))
			else 0 end, 
			s.budget_at=ifnull(tc.budget_at,0),
			s.budget_iuser_at=ifnull(tc.budget_iuser_at,0)+ifnull(b.budget_iuser_at,0),
			s.budget_ouser_at=ifnull(tc.budget_at,0)+ifnull(b.budget_ouser_at,0),
			s.act_at=ifnull(tc.act_at,0)+ifnull(b.act_at,0),
			s.act_iuser_at=ifnull(tc.act_iuser_at,0)+ifnull(b.act_iuser_at,0),
			s.act_ouser_at=ifnull(tc.act_ouser_at,0)+ifnull(b.act_ouser_at,0),
			
			s.task_cnt=ifnull(tc.task_cnt,0),
			s.task_unstart_cnt=ifnull(tc.task_unstart_cnt,0),
			s.task_exec_cnt=ifnull(tc.task_exec_cnt,0),
			s.task_finish_cnt=ifnull(tc.task_finish_cnt,0),
			s.task_set_cnt=ifnull(tc.task_set_cnt,0),
			s.task_close_cnt=ifnull(tc.task_close_cnt,0),
			s.task_out_cnt=ifnull(tc.task_out_cnt,0),
			
			s.plan_worker_cnt=ifnull(tc.plan_worker_cnt,0),  
			s.product_cnt=ifnull(tc.product_cnt,0),
			s.iteration_cnt=ifnull(tc.iteration_cnt,0),
			
			s.test_cases=ifnull(bc.test_cases,0),
			s.exec_cases=ifnull(bc.exec_cases,0),
			s.design_cases=ifnull(bc.design_cases,0),
			s.finish_cases=ifnull(bc.finish_cases,0),
			
			s.bug_cnt=ifnull(b.bug_cnt,0),
			s.active_bugs=ifnull(b.active_bugs,0),
			s.confirmed_bugs=ifnull(b.confirmed_bugs,0),
			s.resolved_bugs=ifnull(b.resolved_bugs,0),
			s.closed_bugs=ifnull(b.closed_bugs,0),
			
			s.calc_time=calcDate,
			s.project_status=tc.`status`,
			s.biz_date=bizDate,
			s.min_start_time=if(tc.start_time<b.start_time,tc.start_time,b.start_time),
			s.max_end_time=if(tc.end_time>b.end_time,tc.end_time,b.end_time),
			
			s.menu_cnt=ifnull(tc.menu_cnt,0),
			s.menu_unstart_cnt=tc.menu_unstart_cnt,
			s.menu_exec_cnt=tc.menu_exec_cnt,
			s.menu_finish_cnt=ifnull(tc.menu_finish_cnt,0), 
			s.menu_close_cnt=tc.menu_close_cnt
			
	where  s.project_id=  ifnull(inProjectId,s.project_id) 
			;
			
			update xm_project_state s inner join (select phase.project_id, count(if(phase.ntype='1',phase.id,null)) as phase_cnt, count(if(phase.ntype='1' and phase.task_state in ('3','4','9'),phase.id,null)) as phase_finish_cnt from xm_task phase
			where phase.project_id=ifnull(inProjectId,phase.project_id)
			group by phase.project_id
			) as p on s.project_id=p.project_id
			set s.phase_cnt=p.phase_cnt,s.phase_finish_cnt=p.phase_finish_cnt
			where s.project_id=ifnull(inProjectId,s.project_id);
			
				
	delete from xm_project_state_his h where h.project_id=ifnull(inProjectId,h.project_id) and h.biz_date=bizDate;
		insert into xm_project_state_his select s.* from  xm_project_state	s left join xm_project_state_his his2 on s.project_id=his2.project_id and s.biz_date=his2.biz_date 
	where his2.biz_date is null and s.project_id = ifnull(inProjectId,s.project_id);
	
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_xm_question_handle_to_his
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_xm_question_handle_to_his`;
delimiter ;;
CREATE PROCEDURE `load_xm_question_handle_to_his`()
BEGIN
	set time_zone = '+8:00'; 
	begin 
  insert into xm_question_handle_his select res.* from xm_question_handle res left join xm_question_handle_his his on res.id=his.id
where his.id is null;


delete from xm_question_handle where create_time <  DATE_SUB(CURDATE(), INTERVAL 360 DAY);
			
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for load_xm_record_to_his
-- ----------------------------
DROP PROCEDURE IF EXISTS `load_xm_record_to_his`;
delimiter ;;
CREATE PROCEDURE `load_xm_record_to_his`()
BEGIN
	set time_zone = '+8:00'; 
	begin 
  insert into xm_record_his select res.* from xm_record res left join xm_record_his his on res.id=his.id
where his.id is null;


delete from xm_record where oper_time <  DATE_SUB(CURDATE(), INTERVAL 360 DAY);
			
	end; 
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for pro_up_xm_menu_id_paths_first
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_up_xm_menu_id_paths_first`;
delimiter ;;
CREATE PROCEDURE `pro_up_xm_menu_id_paths_first`()
begin
  declare v_maxlevels int default 20;
 	declare	v_levels int default 1;
 
  BEGIN	
	 update xm_menu c 
      set c.lvl = NULL,
			    c.pid_paths = NULL;
  end;
 
	begin
		update xm_menu c 
       set c.lvl = v_levels,
					 c.pid_paths = CONCAT('0',',',c.menu_id,',')
     where c.pmenu_id ='0' or c.pmenu_id='' or c.pmenu_id is null  ;
	end;
	while v_levels < 5 
	DO
	  call pro_up_xm_menu_id_paths_next(v_levels); 
		 set v_levels = v_levels+1;
  end while;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for pro_up_xm_menu_id_paths_next
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_up_xm_menu_id_paths_next`;
delimiter ;;
CREATE PROCEDURE `pro_up_xm_menu_id_paths_next`(in v_levels int)
begin
       declare done int default 0;
       DECLARE v_id varchar(64) ;
       DECLARE v_id_path varchar(150);
    
       declare cur cursor for select menu_id,pid_paths from xm_menu where  lvl=  v_levels COLLATE utf8mb4_croatian_ci;
       declare continue handler for not found set done = 1;
    
       open cur;
    
       repeat
         fetch cur into v_id, v_id_path;
 
         update xm_menu c
            set c.lvl = v_levels+1,
    						c.pid_paths =  CONCAT(v_id_path,c.menu_id,',' )
          where c.pmenu_id =  v_id COLLATE utf8mb4_croatian_ci;
       until done end repeat;
       close cur;
     end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for pro_up_xm_task_id_paths_first
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_up_xm_task_id_paths_first`;
delimiter ;;
CREATE PROCEDURE `pro_up_xm_task_id_paths_first`()
begin
  declare v_maxlevels int default 20;
 	declare	v_levels int default 1;
 
  BEGIN	
	 update xm_task c 
      set c.lvl = NULL,
			    c.pid_paths = NULL;
  end;
 
	begin
		update xm_task c 
       set c.lvl = v_levels,
					 c.pid_paths = CONCAT('0',',',c.id,',')
     where c.parent_taskid ='0' or c.parent_taskid='' or c.parent_taskid is null  ;
	end;
	while v_levels < 5 
	DO
	  call pro_up_xm_task_id_paths_next(v_levels); 
		 set v_levels = v_levels+1;
  end while;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for pro_up_xm_task_id_paths_next
-- ----------------------------
DROP PROCEDURE IF EXISTS `pro_up_xm_task_id_paths_next`;
delimiter ;;
CREATE PROCEDURE `pro_up_xm_task_id_paths_next`(in v_levels int)
begin
       declare done int default 0;
       DECLARE v_id varchar(64) ;
       DECLARE v_id_path varchar(150);
    
       declare cur cursor for select id,pid_paths from xm_task where  lvl=  v_levels COLLATE utf8mb4_croatian_ci;
       declare continue handler for not found set done = 1;
    
       open cur;
    
       repeat
         fetch cur into v_id, v_id_path;
 
         update xm_task c
            set c.lvl = v_levels+1,
    						c.pid_paths =  CONCAT(v_id_path,c.id,',' )
          where c.parent_taskid =  v_id COLLATE utf8mb4_croatian_ci;
       until done end repeat;
       close cur;
     end
;;
delimiter ;

-- ----------------------------
-- Event structure for sch_load_project_state_to_xm_branch_state
-- ----------------------------
DROP EVENT IF EXISTS `sch_load_project_state_to_xm_branch_state`;
delimiter ;;
CREATE EVENT `sch_load_project_state_to_xm_branch_state`
ON SCHEDULE
EVERY '1' DAY STARTS '2020-11-01 06:00:00'
DO call load_project_state_to_xm_branch_state(null)
;;
delimiter ;

-- ----------------------------
-- Event structure for sch_load_project_task_type_state_to_branch_task_type_state
-- ----------------------------
DROP EVENT IF EXISTS `sch_load_project_task_type_state_to_branch_task_type_state`;
delimiter ;;
CREATE EVENT `sch_load_project_task_type_state_to_branch_task_type_state`
ON SCHEDULE
EVERY '1' DAY STARTS '2020-11-01 05:00:00'
DO call load_project_task_type_state_to_branch_task_type_state(null)
;;
delimiter ;

-- ----------------------------
-- Event structure for sch_load_tasks_settle_to_xm_project_state
-- ----------------------------
DROP EVENT IF EXISTS `sch_load_tasks_settle_to_xm_project_state`;
delimiter ;;
CREATE EVENT `sch_load_tasks_settle_to_xm_project_state`
ON SCHEDULE
EVERY '1' DAY STARTS '2020-10-31 05:00:00'
DO call load_tasks_settle_to_xm_project_state(null)
;;
delimiter ;

-- ----------------------------
-- Event structure for sch_load_tasks_to_project_task_type_state
-- ----------------------------
DROP EVENT IF EXISTS `sch_load_tasks_to_project_task_type_state`;
delimiter ;;
CREATE EVENT `sch_load_tasks_to_project_task_type_state`
ON SCHEDULE
EVERY '1' DAY STARTS '2020-11-01 04:00:00'
DO call load_tasks_to_project_task_type_state(null)
;;
delimiter ;

-- ----------------------------
-- Event structure for sch_load_tasks_to_xm_iteration_state
-- ----------------------------
DROP EVENT IF EXISTS `sch_load_tasks_to_xm_iteration_state`;
delimiter ;;
CREATE EVENT `sch_load_tasks_to_xm_iteration_state`
ON SCHEDULE
EVERY '1' DAY STARTS '2020-11-01 04:00:00'
DO call load_tasks_to_xm_iteration_state(null)
;;
delimiter ;

-- ----------------------------
-- Event structure for sch_load_tasks_to_xm_product_state
-- ----------------------------
DROP EVENT IF EXISTS `sch_load_tasks_to_xm_product_state`;
delimiter ;;
CREATE EVENT `sch_load_tasks_to_xm_product_state`
ON SCHEDULE
EVERY '1' DAY STARTS '2020-11-01 03:00:00'
DO call load_tasks_to_xm_product_state(null)
;;
delimiter ;

-- ----------------------------
-- Event structure for sch_load_tasks_to_xm_project_state
-- ----------------------------
DROP EVENT IF EXISTS `sch_load_tasks_to_xm_project_state`;
delimiter ;;
CREATE EVENT `sch_load_tasks_to_xm_project_state`
ON SCHEDULE
EVERY '1' DAY STARTS '2020-11-01 03:00:00'
DO call load_tasks_to_xm_project_state(null)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
