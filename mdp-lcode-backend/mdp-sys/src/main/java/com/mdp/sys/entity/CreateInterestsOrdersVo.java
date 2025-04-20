package com.mdp.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 组织 com  顶级模块 mk 大模块 mem  小模块 <br> 
 * 实体 InterestsOrders所有属性名: <br>
 *	userid,ilvlId,ilvlName,idesc,branchId,ilevel,discount,istatus,ctime,ltime,picUrl,isFree,rtimeRule,rtimeType,rtime,itype,shopId,instId,flowState,smaxAt,totalAt,mtype,totalExp,smaxExp,bids,sfeeRate,idBak,payType,payStatus,payTime,prepayId,id,finalFee,mfee,othFee,months,originFee;<br>
 * 表 mem_interests_orders 会员权益关联表的所有字段名: <br>
 *	userid,ilvl_id,ilvl_name,idesc,branch_id,ilevel,discount,istatus,ctime,ltime,pic_url,is_free,rtime_rule,rtime_type,rtime,itype,shop_id,inst_id,flow_state,smax_at,total_at,mtype,total_exp,smax_exp,bids,sfee_rate,id_bak,pay_type,pay_status,pay_time,prepay_id,id,final_fee,mfee,oth_fee,months,origin_fee;<br>
 * 当前主键(包括多主键):<br>
 *	id;<br>
 */
@Data
@ApiModel(description="会员购买权益请求参数表")
public class CreateInterestsOrdersVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes="订单编号",allowEmptyValue=true,example="",allowableValues="")
	String id;

	@ApiModelProperty(notes="用户编号",allowEmptyValue=true,example="",allowableValues="")
	String userid;

	@ApiModelProperty(notes="等级ID",allowEmptyValue=true,example="",allowableValues="")
	String ilvlId;

	@ApiModelProperty(notes="购买月数",allowEmptyValue=true,example="",allowableValues="")
	int months;

	@ApiModelProperty(notes="订单操作类型1-新购，2-续费，3-升级",allowEmptyValue=true,example="",allowableValues="")
	String ooper;

	@ApiModelProperty(notes="是否仅仅计算订单数据",allowEmptyValue=true,example="",allowableValues="")
	boolean isCalc=false;

}