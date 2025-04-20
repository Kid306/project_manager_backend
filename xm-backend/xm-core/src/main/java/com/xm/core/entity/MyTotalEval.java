package com.xm.core.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 xm 大模块 core  小模块 <br> 
 * 实体 XmTaskEval所有属性名: <br>
 *	"id","评价","type","评价类型1-雇主对服务商的评价，2-服务商对雇主的评价，3-组长对组员的评价","wspeed","工作速度0-5分","wattit","工作态度0-5分","wquality","工作质量0-5分","totalStar","总体评价0-5分","remark","评价内容","evalUserid","评价人编号","evalUsername","评价人姓名","toUserid","被评价人编号","toUsername","被评价人姓名","evalBranchId","评价人归属机构","toBranchId","被评价人归属机构号","taskId","任务编号","evalTime","评价时间";<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
 @Data
@ApiModel(description="MyTotalEval")
public class MyTotalEval implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes="工作速度0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer wspeed;

	@ApiModelProperty(notes="工作态度0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer wattit;

	@ApiModelProperty(notes="工作质量0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer wquality;

	@ApiModelProperty(notes="总体评价0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer totalStar;

	@ApiModelProperty(notes="付款速度0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer paySpeed;

	@ApiModelProperty(notes="合作愉快度0-5分",allowEmptyValue=true,example="",allowableValues="")
	Integer coopHappy;

	@ApiModelProperty(notes="评价人数",allowEmptyValue=true,example="",allowableValues="")
	Integer evalUserCnt;

    /**
     * xm_task_eval
     **/
	public MyTotalEval() {
	}

}